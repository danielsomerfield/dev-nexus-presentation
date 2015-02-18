package phonebook.cukes.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.fluentlenium.adapter.IsolatedTest;
import org.fluentlenium.core.Fluent;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import phonebook.test.ServiceClient;

import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;
import static org.junit.Assert.assertTrue;
import static phonebook.test.ServiceClient.client;

public class E2ESteps {

    private Optional<IsolatedTest> browserRuntime = Optional.empty();
    private Optional<ServiceClient.Entry> currentEntry = Optional.empty();

    @Given("I am not logged in")
    public void I_am_not_logged_in() throws Throwable {
        final Fluent fluent = browseTo("http://localhost:8080/");
        assertTrue(fluent
                .findFirst("#login-form").isDisplayed());
    }

    private Fluent browseTo(final String url) {
        if (!browserRuntime.isPresent()) {
            browserRuntime = Optional.of(new IsolatedTest(new PhantomJSDriver()));
        }
        return browserRuntime.get().goTo(url);
    }

    @And("^an entry has been entered into the phone list$")
    public void an_entry_has_been_entered_into_the_phone_list() throws Throwable {
        final ServiceClient.Entry newEntry = createNewEntry();
        client().addEntry(newEntry);
    }

    private ServiceClient.Entry createNewEntry() {
        final String uuid = UUID.randomUUID().toString();
        final ServiceClient.Entry entry = new ServiceClient.Entry(
                format("lastname-%s", uuid),
                format("firstname-%s", uuid),
                format("email-%s@example.com", uuid)
        );
        currentEntry = Optional.of(entry);
        return entry;
    }
}
