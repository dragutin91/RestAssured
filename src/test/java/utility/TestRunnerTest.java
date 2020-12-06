package utility;
import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;
import cucumber.api.junit.*;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources",
        plugin = {"pretty","html:target/cucumber","json:target/cucumber.json","junit:target/cukes.xml"},
        strict = true,
        monochrome = true,
        glue="steps"

)

public class TestRunnerTest  {
}
