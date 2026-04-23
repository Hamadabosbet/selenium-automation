package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.TestBase;

import java.util.Arrays;
import java.util.List;

public class HomePageTest  extends TestBase {
    private static final String EXPECTED_TITLE = "ATID Demo Store – ATID College Demo Store for Practicing QA Automation";

    @Test
    public void verifyPageTitle() {
        HomePage homePage = new HomePage(driver);
        String actualTitle = homePage.getPageTitle();
        Assert.assertEquals(actualTitle, EXPECTED_TITLE, "Page title is incorrect!");

    }

    @Test
    public void verifyLogoIsDisplayed() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isLogoDisplayed(), "Logo is not displayed!");
    }
    @Test
    public void verifyH1Title() throws InterruptedException {
        Thread.sleep(500);
        HomePage homePage = new HomePage(driver);
        try {
            Assert.assertEquals(homePage.getH1Title(), "dsdf Demo Store", "H1 title is incorrect!");
        } catch (AssertionError e) {
            highlightElement(homePage.getH1Element());  // highlight the element in red
            throw e;               // rethrow so test still fails
        }


    }

    @Test
    public void verifyNavigationLinks() {
        HomePage homePage = new HomePage(driver);

        List<String> expectedLinks = Arrays.asList(
                "HOME", "STORE", "MEN", "WOMEN", "ACCESSORIES", "ABOUT", "CONTACT US"
        );

        List<String> actualLinks = homePage.getNavLinksText();
        System.out.println(actualLinks);
        Assert.assertEquals(actualLinks, expectedLinks, "Navigation links are incorrect!");
    }
}
