package hepsiburada.runners;


import hepsiburada.utilities.ConfigurationReader;
import hepsiburada.utilities.Driver;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.*;

@Test
@CucumberOptions(
        plugin = {
                "pretty",
                "json:target/cucumber.json",
                "html:target/cucumber-reports.html",
                "json:target/cucumber.json",
                "junit:target/cucumber-results.xml"
        },
        features = "src/test/resources/features/",
        glue = "hepsiburada/step_definitions",
        dryRun = false,
        publish = true,
        tags = "@all"
)

public class CukesRunner extends AbstractTestNGCucumberTests {


    @BeforeMethod
    @Parameters("env")
    public void setUp(@Optional String env) {
        String url = ConfigurationReader.get("hepsiburada_url");
        Driver.get().get(url);

    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
