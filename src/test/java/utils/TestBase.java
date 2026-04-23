package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Base class for test classes providing common setup and teardown functionality.
 */
public class TestBase {

    protected WebDriver driver;

    /** Base URL for the application under test. */
    public static final String BASE_URL = "https://atid.store/";

    /**
     * Sets up the WebDriver before each test method.
     * Initializes Chrome driver and navigates to the base URL.
     */
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver("chrome");
        driver = DriverFactory.getDriver();
        driver.get(BASE_URL);
    }

    /**
     * Tears down the WebDriver after each test method.
     */
    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            try {
                TakesScreenshot ts = (TakesScreenshot) driver;
                File source = ts.getScreenshotAs(OutputType.FILE);
                String screenshotPath = "screenshots/" + result.getName() + ".png";
                Files.createDirectories(Paths.get("screenshots"));
                Files.copy(source.toPath(), Paths.get(screenshotPath));
                System.out.println("Screenshot saved: " + screenshotPath);
            } catch (IOException e) {
                System.out.println("Failed to capture screenshot: " + e.getMessage());
            }
        }
        DriverFactory.quitDriver();
    }
}
