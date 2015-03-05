package phonebook.cukes.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.fluentlenium.adapter.IsolatedTest;
import org.fluentlenium.core.Fluent;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import phonebook.test.ServiceClient;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

import static java.lang.String.format;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.fluentlenium.core.filter.FilterConstructor.with;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static phonebook.test.ServiceClient.client;

public class E2ESteps {

    public static final String DELETE_BUTTON_CSS_CLASS = ".delete-button";
    public static final String PHONEBOOK_ENTRY_CSS_CLASS = ".phonebook-entry";
    public static final String DATA_ID_CSS_ATTRIBUTE = "data-id";
    public static final String PHONEBOOK_ENTRY_EMAIL_ADDRESS_CSS_CLASS = ".phonebook-entry-email-address";
    public static final String PHONEBOOK_ENTRY_FIRST_NAME_CSS_CLASS = ".phonebook-entry-first-name";
    public static final String PHONEBOOK_ENTRY_LAST_NAME_CSS_CLASS = ".phonebook-entry-last-name";
    public static final String DATA_ID = "data-id";
    public static final String PHONEBOOK_ENTRY = ".phonebook-entry";
    private static final String USERNAME_FIELD_ID = "#username-field";
    private static final String PASSWORD_FIELD_ID = "#password-field";
    private static final String LOGIN_FORM_ID = "#login-form";
    private Optional<IsolatedTest> browserRuntime = Optional.empty();
    private Optional<ServiceClient.Entry> currentEntry = Optional.empty();
    private Optional<Fluent> currentPage = Optional.empty();

    @Given("I am logged in")
    public void I_am_logged_in() throws Throwable {
        assertFalse(browseTo("http://localhost:8080/")
                .findFirst("#login-form").isDisplayed());
    }

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
        currentEntry = Optional.of(client().addEntry(newEntry));
    }

    private ServiceClient.Entry createNewEntry() {
        final String uuid = UUID.randomUUID().toString();
        return new ServiceClient.Entry(
                null,
                format("lastname-%s", uuid),
                format("firstname-%s", uuid),
                format("email-%s@example.com", uuid)
        );
    }

    @When("^I go to the entry list page$")
    public void I_go_to_the_entry_list_page() throws Throwable {
        browseTo("http://localhost:8080/");
    }

    @Then("^I see the entry in the entry list$")
    public void I_see_the_entry_in_the_entry_list() throws Throwable {

        final ServiceClient.Entry entry = currentEntry.get();
        currentPage.get()
                .await().atMost(3, SECONDS)
                .until(PHONEBOOK_ENTRY)
                .with(DATA_ID).equalTo(entry.getId())
                .isPresent();

        findEntryElementAnd(entryElement -> {
            assertThat(getTextValue(entryElement, PHONEBOOK_ENTRY_LAST_NAME_CSS_CLASS), is(entry.getLastName()));
            assertThat(getTextValue(entryElement, PHONEBOOK_ENTRY_FIRST_NAME_CSS_CLASS), is(entry.getFirstName()));
            assertThat(getTextValue(entryElement, PHONEBOOK_ENTRY_EMAIL_ADDRESS_CSS_CLASS), is(entry.getEmailAddress()));
        });
    }

    private void findEntryElementAnd(Consumer<FluentList<FluentWebElement>> assertionFunction) {
        final ServiceClient.Entry entry = currentEntry.get();
        assertionFunction.accept(currentPage.get()
                .find(PHONEBOOK_ENTRY_CSS_CLASS, with(DATA_ID_CSS_ATTRIBUTE).equalTo(entry.getId())));
    }

    private String getTextValue(final FluentList<FluentWebElement> entryElement, final String nodeClass) {
        return entryElement
                .find(nodeClass).getText();
    }


    @Then("^I am unable to delete a user$")
    public void I_am_unable_to_delete_a_user() throws Throwable {
        assertThat(currentPage.get().find(DELETE_BUTTON_CSS_CLASS).size(), is(0));
    }

    @When("^I log in$")
    public void I_log_in() throws Throwable {
        currentPage.get()
                .fill(USERNAME_FIELD_ID).with("admin")
                .fill(PASSWORD_FIELD_ID).with("admin")
                .submit(LOGIN_FORM_ID);
    }

}
