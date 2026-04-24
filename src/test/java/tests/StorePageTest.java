package tests;

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
}
