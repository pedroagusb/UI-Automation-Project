import io.cucumber.java.AfterAll;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterMethod;
import utils.WebDriverUtils;

@CucumberOptions(
        features = "classpath:features/",
        glue = {"common"},
        tags = "",
        plugin = {"pretty"},
        publish = true
)

public class TestRunner extends AbstractTestNGCucumberTests {

    @AfterAll()
    public void tearDownClass(){
        new WebDriverUtils().tearDown();
    }
}

