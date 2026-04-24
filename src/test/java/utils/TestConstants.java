package utils;

/**
 * Central repository for test constants.
 * Maintains all hard-coded test data to ensure single point of modification.
 */
public class TestConstants {

    // Home Page Constants
    public static final String HOME_PAGE_TITLE = "ATID Demo Store – ATID College Demo Store for Practicing QA Automation";
    public static final String HOME_PAGE_H1_TITLE = "ATID Demo Store";

    // Navigation Links
    public static final String[] EXPECTED_NAV_LINKS = {
            "HOME", "STORE", "MEN", "WOMEN", "ACCESSORIES", "ABOUT", "CONTACT US"
    };

    // Store Page Constants
    public static final int EXPECTED_PRODUCT_COUNT = 12;

    // Wait Timeouts (in seconds)
    public static final int LONG_WAIT = 10;
    public static final int MEDIUM_WAIT = 5;
    public static final int SHORT_WAIT = 2;

    // Screenshot Settings
    public static final String SCREENSHOT_DIRECTORY = "screenshots/";

    private TestConstants() {
        // Private constructor to prevent instantiation
    }
}

