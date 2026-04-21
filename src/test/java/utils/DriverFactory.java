package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
    public static WebDriver getDriver() {
        // In Selenium 4, WebDriverManager is not needed, but for older, add it.
        return new ChromeDriver();
    }
}
