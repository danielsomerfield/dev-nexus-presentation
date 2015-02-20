package phonebook.cukes.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
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
    private Optional<Fluent> currentPage = Optional.empty();

    @Given("I am not logged in")
    public void I_am_not_logged_in() throws Throwable {
        assertTrue(browseTo("http://localhost:8080/")
                .findFirst("#login-form").isDisplayed());
    }

    private Fluent browseTo(final String url) {
        if (!browserRuntime.isPresent()) {
            browserRuntime = Optional.of(new IsolatedTest(new PhantomJSDriver()));
        }

        final Fluent page = browserRuntime.get().goTo(url);
        currentPage = Optional.of(page);
        return page;
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

    @When("^I go to the entry list page$")
    public void I_go_to_the_entry_list_page() throws Throwable {
        browseTo("http://localhost:8080/");
    }

//    @Then("^I see the entry in the entry list$")
//    public void I_see_the_entry_in_the_entry_list() throws Throwable {
//        currentPage.map(fluent -> fluent.findFirst())
//    }
}
