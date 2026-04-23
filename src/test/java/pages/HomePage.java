package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    private By logo = By.cssSelector("img[src*='logo_store_bright']");
    private WebDriver driver;

    public  HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
    public boolean isLogoDisplayed() {
        WebElement logoElement = driver.findElement(logo);
        return logoElement.isDisplayed();
    }

}
