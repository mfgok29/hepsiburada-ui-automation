package hepsiburada.step_definitions;

import hepsiburada.utilities.ConfigurationReader;
import hepsiburada.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;


public class Hooks {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void setUp() {
        driver = Driver.get();

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get(ConfigurationReader.get("hepsiburada_url"));


    }

    @After
    public void tearDown(Scenario scenario) {
        if(scenario.isFailed()){
            final byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png",scenario.getName());
            saveScreenshot(screenshot);
        }

        Driver.closeDriver();

    }

    private void saveScreenshot(byte[] screenshot) {
        String path = "target/screenshots/" + System.currentTimeMillis() + ".png";
        try {
            Files.createDirectories(Paths.get("target/screenshots/"));
            Files.write(Paths.get(path), screenshot);
        } catch (IOException e) {
            System.err.println("Exception while writing screenshot to file: " + e.getMessage());
        }

    }
}
