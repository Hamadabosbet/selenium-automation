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
    private final By storeLink = By.linkText("STORE");
    private final By products = By.cssSelector("ul.products li.product");
    private final By productName    = By.cssSelector("h2.woocommerce-loop-product__title");
    private final By productPrice   = By.cssSelector("span.price");
    private final By productImage   = By.cssSelector("img.attachment-woocommerce_thumbnail");
    private By firstProduct     = By.cssSelector("ul.products li.product:first-child a.woocommerce-LoopProduct-link");
    private By productPageTitle = By.cssSelector("h1.product_title");



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

    public boolean areAllProductsDisplayed() {
        List<WebElement> ProductList = driver.findElements(products);
        for (WebElement product : ProductList) {
            if (!product.isDisplayed()) {
                return false;
            }

        }
        return true;
    }

    public boolean areAllProductDetialDisplayed(WebElement product) {
        WebElement name = product.findElement(productName);
        WebElement price = product.findElement(productPrice);
        WebElement image = product.findElement(productImage);

        return name.isDisplayed()&&
                !name.getText().isEmpty()&&
                !price.getText().isEmpty() && price.isDisplayed()
                && image.isDisplayed();


    }
    public boolean areAllProductsDetialDisplayed() {
        List<WebElement> ProductList = driver.findElements(products);
        for (WebElement product : ProductList) {
            if (!areAllProductDetialDisplayed(product)) {
                return false;
            }

        }
        return true;
    }

    public void clickFirstProduct() {
        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(firstProduct));
        product.click();
    }

    public boolean isProductPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productPageTitle));
        return driver.findElement(productPageTitle).isDisplayed();
    }

    public String getProductPageTitle() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productPageTitle));
        return driver.findElement(productPageTitle).getText();
    }

    public String getProductNameBeforeClick() {
        WebElement name = driver.findElement(productName);
        return name.getText();
    }

    }
