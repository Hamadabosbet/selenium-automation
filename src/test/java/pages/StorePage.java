package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StorePage {

    private WebDriverWait wait;
    private WebDriver driver;
    private By storeLink= By.linkText("STORE");
    private By products = By.cssSelector("ul.products li.product");


    public StorePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void goToStorePage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(storeLink)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(products));
    }
    public int getProductCount() {
        List<WebElement> productList = driver.findElements(products);
        return productList.size();
    }

    public boolean areAllProductsDisplayed(){
        List<WebElement> ProductList=driver.findElements(products);
        for(WebElement product:ProductList){
            if (!product.isDisplayed()){
                return false;
            }

        }
        return true;
    }





}
