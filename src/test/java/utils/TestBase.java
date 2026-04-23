package utils;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

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
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
