package phonebook.cukes.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.net.URI;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SmokeTestSteps {

    private Optional<Integer> pingResult = Optional.empty();

    @When("^I ping the health check$")
    public void I_ping_the_health_check() throws Throwable {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            pingResult = Optional.of(client.execute(
                    new HttpGet(URI.create("http://localhost:8080/ping"))).getStatusLine().getStatusCode());
        }
    }

    @Then("^I see a healthy response$")
    public void I_see_a_healthy_response() throws Throwable {
        assertThat(pingResult, is(Optional.of(HttpStatus.SC_OK)));
    }

}
