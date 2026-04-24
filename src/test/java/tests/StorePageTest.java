package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.StorePage;
import utils.TestBase;

public class StorePageTest extends TestBase {

    @Test
    public void verifyProductsAreDisplayed() {
        StorePage storePage = new StorePage(driver);
        storePage.goToStorePage();
        storePage.areAllProductsDisplayed();
    }


    @Test
    public void verifyProductCount() {
        StorePage storePage = new StorePage(driver);
        storePage.goToStorePage();
        int actualProductCount = storePage.getProductCount();
        int expectedProductCount = 12;
        assert actualProductCount == expectedProductCount : "Expected " + expectedProductCount + " products but found " + actualProductCount;


    }

    @Test
    public void verifyProductDetailsAreDisplayed() {
        StorePage storePage = new StorePage(driver);
        storePage.goToStorePage();
        assert storePage.areAllProductsDetialDisplayed() : "All product details are not displayed correctly : " ;



    }


    @Test
    public void verifyProductPageOpens() {
        StorePage storePage = new StorePage(driver);
        storePage.goToStorePage();

        // Get product name before clicking
        String productNameBeforeClick = storePage.getProductNameBeforeClick();
        System.out.println("Clicking on product: " + productNameBeforeClick);

        // Click on the product
        storePage.clickFirstProduct();

        // Verify product page opened
        Assert.assertTrue(storePage.isProductPageOpened(),
                "Product page did not open!");

        // Verify product name matches
        String productPageTitle = storePage.getProductPageTitle();
        Assert.assertEquals(productPageTitle, productNameBeforeClick,
                "Product page title does not match clicked product!");

        System.out.println("Product page opened: " + productPageTitle);
    }
}
