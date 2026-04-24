package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.TestBase;
import utils.TestConstants;

import java.util.Arrays;
import java.util.List;

/**
 * Test class for Home Page functionality.
 * Tests page title, logo visibility, heading, and navigation links.
 */
public class HomePageTest extends TestBase {
    private HomePage homePage;

    /**
     * Initialize HomePage before each test.
     */
    @BeforeMethod
    public void initializeHomePage() {
        homePage = new HomePage(driver);
    }

    /**
     * Verifies that the browser page title matches the expected title.
     */
    @Test(description = "Verify home page title is correct")
    public void verifyPageTitle() {
        String actualTitle = homePage.getPageTitle();
        Assert.assertEquals(actualTitle, TestConstants.HOME_PAGE_TITLE,
                "Home page title mismatch!");
    }

    /**
     * Verifies that the logo is displayed on the home page.
     */
    @Test(description = "Verify logo is displayed on home page")
    public void verifyLogoIsDisplayed() {
        Assert.assertTrue(homePage.isLogoDisplayed(),
                "Logo is not displayed on home page!");
    }

    /**
     * Verifies that the H1 heading matches the expected title.
     * Highlights the H1 element if assertion fails.
     */
    @Test(description = "Verify H1 heading is displayed correctly")
    public void verifyH1Title() {
        String actualH1Title = homePage.getH1Title();
        try {
            Assert.assertEquals(actualH1Title, TestConstants.HOME_PAGE_H1_TITLE,
                    "H1 title mismatch!");
        } catch (AssertionError e) {
            // Highlight the H1 element for debugging
            highlightElement(homePage.getH1Element());
            throw e;
        }
    }

    /**
     * Verifies that all navigation links are displayed and have correct text.
     */
    @Test(description = "Verify navigation links are correct")
    public void verifyNavigationLinks() {
        List<String> expectedLinks = Arrays.asList(TestConstants.EXPECTED_NAV_LINKS);
        List<String> actualLinks = homePage.getNavLinksText();

        Assert.assertEquals(actualLinks, expectedLinks,
                "Navigation links mismatch! Expected: " + expectedLinks + " but found: " + actualLinks);
    }

    /**
     * Helper method to highlight an element with red border for debugging purposes.
     *
     * @param element the WebElement to highlight
     */
    public void highlightElement(org.openqa.selenium.WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].style.border='2px solid red'", element);
        } catch (Exception e) {
            System.err.println("Unable to highlight element: " + e.getMessage());
        }
    }
}
