package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    private By logo = By.cssSelector("img[src*='logo_store_bright']");
    private WebDriver driver;
    private By h1Title = By.tagName("h1");

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
    public  String getH1Title() {
        WebElement h1Element= driver.findElement(h1Title);
        return h1Element.getText();

    }

}
