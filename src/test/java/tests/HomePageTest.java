package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.TestBase;

public class HomePageTest  extends TestBase {
    private static final String EXPECTED_TITLE = "ATID Demo Store – ATID College Demo Store for Practicing QA Automation";

    @Test
    public void verifyPageTitle() {
        HomePage homePage = new HomePage(driver);
        String actualTitle = homePage.getPageTitle();
        System.out.println(actualTitle);
        Assert.assertEquals(actualTitle, EXPECTED_TITLE, "Page title is incorrect!");

    }
}
