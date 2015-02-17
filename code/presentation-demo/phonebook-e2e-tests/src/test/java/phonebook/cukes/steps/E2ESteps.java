package phonebook.cukes.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.fluentlenium.adapter.IsolatedTest;
import phonebook.test.ServiceClient;

import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static phonebook.test.ServiceClient.client;

public class E2ESteps {

    private Optional<IsolatedTest> browserRuntime = Optional.empty();

    @Given("I am not logged in")
    public void I_am_not_logged_in() throws Throwable {
        final boolean logInDisplayed = browserRuntime.get()
                .goTo("http://localhost:8080/")
                .findFirst("#login-form").isDisplayed();
        assertTrue(logInDisplayed);
    }

    @And("^an entry has been entered into the phone list$")
    public void an_entry_has_been_entered_into_the_phone_list() throws Throwable {
        client().addEntry(new ServiceClient.Entry("test-lastname", "test-firstname", "test@example.com"));
    }


}
