import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src\\test\\java\\features",
        glue = {"stepDefinitions"},
        plugin = { "pretty", "html:target/cucumber-reports" },
        monochrome = true,
        dryRun = false,
        strict = true
)

public class TestRunner {
}
