import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "classpath:features/",
        glue = {"common"},
        tags = "",
        plugin = {"pretty","json:target/cucumber-reports/cucumber.json"},
        publish = true
)

public class TestRunner extends AbstractTestNGCucumberTests {
}

