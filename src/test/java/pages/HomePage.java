package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.TestConstants;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomePage {

    private By logo = By.cssSelector("img[src*='logo_store_bright']");
    private WebDriver driver;
    private By h1Title = By.tagName("h1");
    private WebDriverWait wait;
    private By navLinks = By.cssSelector("#primary-site-navigation a");

    /**
     * Constructor to initialize HomePage with WebDriver.
     * Sets up WebDriverWait with default timeout.
     *
     * @param driver WebDriver instance
     */
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TestConstants.LONG_WAIT));
    }

    /**
     * Retrieves the page title.
     *
     * @return the title of the current page
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Verifies if the logo element is displayed on the home page.
     *
     * @return true if logo is displayed, false otherwise
     */
    public boolean isLogoDisplayed() {
        try {
            WebElement logoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(logo));
            return logoElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Retrieves the H1 title text from the page.
     *
     * @return the H1 title text
     */
    public String getH1Title() {
        WebElement h1Element = wait.until(ExpectedConditions.visibilityOfElementLocated(h1Title));
        return h1Element.getText().trim();
    }

    /**
     * Retrieves the H1 WebElement for highlighting or advanced operations.
     *
     * @return the H1 WebElement
     */
    public WebElement getH1Element() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(h1Title));
    }

    /**
     * Retrieves all navigation link texts from the navigation menu.
     * Filters out empty texts automatically.
     *
     * @return list of navigation link texts
     */
    public List<String> getNavLinksText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(navLinks));
        List<WebElement> links = driver.findElements(navLinks);
        List<String> linkTexts = new ArrayList<>();
        for (WebElement link : links) {
            String text = link.getText().trim();
            if (!text.isEmpty()) {
                linkTexts.add(text);
            }
        }
        return linkTexts;
    }
}

