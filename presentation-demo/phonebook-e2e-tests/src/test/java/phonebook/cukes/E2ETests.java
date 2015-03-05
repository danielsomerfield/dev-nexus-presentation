package phonebook.cukes;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        plugin = "pretty",
        glue = "phonebook.cukes.steps",
        tags = {"~@wip"}
)
public class E2ETests {

}
