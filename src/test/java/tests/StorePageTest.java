package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.StorePage;
import utils.TestBase;
import utils.TestConstants;

/**
 * Test class for Store Page functionality.
 * Tests product display, count, details, and product page navigation.
 */
public class StorePageTest extends TestBase {
    private StorePage storePage;

    /**
     * Initialize StorePage and navigate to it before each test.
     */
    @BeforeMethod
    public void initializeStorePage() {
        storePage = new StorePage(driver);
        storePage.goToStorePage();
    }

    /**
     * Verifies that all products are displayed on the store page.
     */
    @Test(description = "Verify all products are displayed on store page")
    public void verifyProductsAreDisplayed() {
        boolean allProductsDisplayed = storePage.areAllProductsDisplayed();
        Assert.assertTrue(allProductsDisplayed,
                "Not all products are displayed on the store page!");
    }

    /**
     * Verifies that the product count matches the expected count.
     */
    @Test(description = "Verify product count is correct")
    public void verifyProductCount() {
        int actualProductCount = storePage.getProductCount();
        Assert.assertEquals(actualProductCount, TestConstants.EXPECTED_PRODUCT_COUNT,
                "Product count mismatch! Expected: " + TestConstants.EXPECTED_PRODUCT_COUNT +
                " but found: " + actualProductCount);
    }

    /**
     * Verifies that all product details (name, price, image) are displayed for all products.
     */
    @Test(description = "Verify all product details are displayed")
    public void verifyProductDetailsAreDisplayed() {
        boolean allDetailsDisplayed = storePage.areAllProductsDetailsDisplayed();
        Assert.assertTrue(allDetailsDisplayed,
                "Not all product details are displayed correctly!");
    }

    /**
     * Verifies that clicking on a product opens the product detail page
     * and the product name matches the clicked product.
     */
    @Test(description = "Verify product page opens when product is clicked")
    public void verifyProductPageOpens() {
        // Get product name before clicking
        String productNameBeforeClick = storePage.getProductNameBeforeClick();
        Assert.assertNotNull(productNameBeforeClick,
                "Product name should not be null before clicking!");

        // Click on the product
        storePage.clickFirstProduct();

        // Verify product page opened
        Assert.assertTrue(storePage.isProductPageOpened(),
                "Product page did not open after clicking the product!");

        // Verify product name matches
        String productPageTitle = storePage.getProductPageTitle();
        Assert.assertEquals(productPageTitle, productNameBeforeClick,
                "Product page title '" + productPageTitle +
                "' does not match clicked product '" + productNameBeforeClick + "'!");
    }
}
