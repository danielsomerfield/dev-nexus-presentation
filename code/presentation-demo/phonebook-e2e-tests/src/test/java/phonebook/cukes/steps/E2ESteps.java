package phonebook.cukes.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class E2ESteps {

    @Given("I am not logged in")
    public void I_am_not_logged_in() throws Throwable {
        throw new PendingException();
    }

    @And("^an entry has been entered into the phone list$")
    public void an_entry_has_been_entered_into_the_phone_list() throws Throwable {
        throw new PendingException();
    }
}
