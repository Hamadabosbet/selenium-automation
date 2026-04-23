package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private By logo = By.cssSelector("img[src*='logo_store_bright']");
    private WebDriver driver;
    private By h1Title = By.tagName("h1");
    private WebDriverWait wait;

    public  HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isLogoDisplayed() {
        WebElement logoElement = driver.findElement(logo);
        return logoElement.isDisplayed();
    }
    public  String getH1Title() {
        WebElement h1Element= wait.until(ExpectedConditions.visibilityOfElementLocated(h1Title));
        return h1Element.getText();

    }
    public WebElement getH1Element() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(h1Title));
    }


}
