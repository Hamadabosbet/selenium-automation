package utils;

import org.openqa.selenium.*;
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
     * Captures screenshot on failure before quitting the driver.
     */
    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            captureScreenshot(result.getName());
        }
        DriverFactory.quitDriver();
    }

    /**
     * Captures screenshot on test failure with retry mechanism.
     */
    private void captureScreenshot(String testName) {
        if (driver == null) {
            System.out.println("Driver is null, unable to capture screenshot");
            return;
        }

        try {
            // Add small delay to ensure page is fully rendered
            Thread.sleep(500);

            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);

            // Create screenshots directory if it doesn't exist
            Files.createDirectories(Paths.get("screenshots"));

            String screenshotPath = "screenshots/" + testName + "_" + System.currentTimeMillis() + ".png";
            Files.copy(source.toPath(), Paths.get(screenshotPath));

            System.out.println("Screenshot saved: " + screenshotPath);
        } catch (InterruptedException ie) {
            System.out.println("Thread interrupted while capturing screenshot: " + ie.getMessage());
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unexpected error during screenshot capture: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "arguments[0].style.border = '3px solid red';",
                element
        );
    }
}
