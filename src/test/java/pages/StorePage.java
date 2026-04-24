package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.TestConstants;

import java.time.Duration;
import java.util.List;

/**
 * Page Object Model for Store Page of ATID Store.
 * Encapsulates all store page elements and product interactions.
 */
public class StorePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By storeLink = By.linkText("STORE");
    private final By products = By.cssSelector("ul.products li.product");
    private final By productName = By.cssSelector("h2.woocommerce-loop-product__title");
    private final By productPrice = By.cssSelector("span.price");
    private final By productImage = By.cssSelector("img.attachment-woocommerce_thumbnail");
    private final By firstProduct = By.cssSelector("ul.products li.product:first-child a.woocommerce-LoopProduct-link");
    private final By productPageTitle = By.cssSelector("h1.product_title");

    /**
     * Constructor to initialize StorePage with WebDriver.
     * Sets up WebDriverWait with default timeout.
     *
     * @param driver WebDriver instance
     */
    public StorePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TestConstants.LONG_WAIT));
    }

    /**
     * Navigates to the Store page by clicking the STORE navigation link.
     * Waits for products to be visible after navigation.
     */
    public void goToStorePage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(storeLink)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(products));
    }

    /**
     * Retrieves the total count of products displayed on the store page.
     *
     * @return the number of products
     */
    public int getProductCount() {
        List<WebElement> productList = driver.findElements(products);
        return productList.size();
    }

    /**
     * Verifies that all products are displayed on the store page.
     *
     * @return true if all products are displayed, false otherwise
     */
    public boolean areAllProductsDisplayed() {
        List<WebElement> productList = driver.findElements(products);
        for (WebElement product : productList) {
            if (!product.isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifies if all product details (name, price, image) are displayed for a given product.
     *
     * @param product the product WebElement to verify
     * @return true if all details are displayed, false otherwise
     */
    public boolean areAllProductDetailsDisplayed(WebElement product) {
        try {
            WebElement name = product.findElement(productName);
            WebElement price = product.findElement(productPrice);
            WebElement image = product.findElement(productImage);

            return name.isDisplayed() &&
                    !name.getText().isEmpty() &&
                    !price.getText().isEmpty() && price.isDisplayed()
                    && image.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verifies if all product details are displayed for all products on the store page.
     *
     * @return true if all products have complete details displayed, false otherwise
     */
    public boolean areAllProductsDetailsDisplayed() {
        List<WebElement> productList = driver.findElements(products);
        for (WebElement product : productList) {
            if (!areAllProductDetailsDisplayed(product)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Deprecated: Use {@link #areAllProductsDetailsDisplayed()} instead.
     * Kept for backward compatibility.
     */
    @Deprecated
    public boolean areAllProductsDetialDisplayed() {
        return areAllProductsDetailsDisplayed();
    }

    /**
     * Clicks on the first product in the store.
     */
    public void clickFirstProduct() {
        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(firstProduct));
        product.click();
    }

    /**
     * Verifies if the product detail page has opened.
     *
     * @return true if product page is displayed, false otherwise
     */
    public boolean isProductPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(productPageTitle));
            return driver.findElement(productPageTitle).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Retrieves the product title displayed on the product detail page.
     *
     * @return the product title text
     */
    public String getProductPageTitle() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productPageTitle));
        return driver.findElement(productPageTitle).getText().trim();
    }

    /**
     * Retrieves the name of the first product before clicking on it.
     *
     * @return the product name text
     */
    public String getProductNameBeforeClick() {
        WebElement name = driver.findElement(productName);
        return name.getText().trim();
    }
}