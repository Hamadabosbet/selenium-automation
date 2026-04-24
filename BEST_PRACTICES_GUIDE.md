# QA Automation Best Practices Guide

## Quick Reference for Writing Quality Tests

---

## 1. Page Object Model (POM) Structure

### ✅ DO:
```java
public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    
    // Locators should be private final
    private final By usernameField = By.id("username");
    private final By loginButton = By.id("login");
    
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    /**
     * Logs in with given credentials.
     */
    public void login(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField))
            .sendKeys(username);
        driver.findElement(loginButton).click();
    }
}
```

### ❌ DON'T:
```java
public class LoginPage {
    // Bad: public locators
    public By usernameField = By.id("username");
    
    // Bad: no documentation
    public void login(String u, String p) {
        // Bad: direct find without wait
        driver.findElement(usernameField).sendKeys(u);
    }
}
```

---

## 2. Test Class Structure

### ✅ DO:
```java
@Test
public void verifyLoginWithValidCredentials() {
    LoginPage loginPage = new LoginPage(driver);
    loginPage.login("user@example.com", "password123");
    Assert.assertTrue(loginPage.isDashboardDisplayed(), 
        "Dashboard should be displayed after login");
}
```

### ❌ DON'T:
```java
@Test
public void test1() {  // Bad: non-descriptive name
    LoginPage loginPage = new LoginPage(driver);
    Thread.sleep(2000);  // Bad: using sleep
    loginPage.login("user@example.com", "password123");
    assert loginPage.isDashboardDisplayed() : "Failed";  // Bad: native assert
}
```

---

## 3. Wait Strategies

### ✅ DO - Explicit Waits:
```java
// Wait for element to be visible
WebElement element = wait.until(
    ExpectedConditions.visibilityOfElementLocated(By.id("element"))
);

// Wait for element to be clickable
wait.until(ExpectedConditions.elementToBeClickable(By.id("button"))).click();

// Wait for element invisibility
wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("spinner")));
```

### ❌ DON'T:
```java
// Bad: Hard-coded sleep
Thread.sleep(2000);

// Bad: No wait
driver.findElement(By.id("element")).click();
```

---

## 4. Locators Best Practices

### ✅ DO:
```java
// ID - Most reliable
private final By idLocator = By.id("unique-id");

// CSS Selector - Efficient
private final By cssLocator = By.cssSelector("button.primary-btn");

// XPath - When necessary
private final By xpathLocator = By.xpath("//button[@type='submit']");
```

### ❌ DON'T:
```java
// Bad: Generic CSS selectors
By.cssSelector("div");  // Too generic

// Bad: Brittle XPath
By.xpath("/html/body/div[1]/div[2]/button[3]");  // Index-based, fragile

// Bad: Text-based selectors (prone to i18n issues)
By.xpath("//*[contains(text(), 'Click Me')]");
```

---

## 5. Assertions

### ✅ DO - Use TestNG/JUnit Assertions:
```java
// Hard assertions (test fails immediately)
Assert.assertTrue(condition, "Message if false");
Assert.assertEquals(actual, expected, "Objects should be equal");
Assert.assertNotNull(object, "Object should not be null");

// Soft assertions (continue after failure)
SoftAssert soft = new SoftAssert();
soft.assertTrue(condition1, "Check 1");
soft.assertTrue(condition2, "Check 2");
soft.assertAll();  // Report all failures
```

### ❌ DON'T:
```java
// Native assert - not recognized by test runners
assert condition : "message";

// Multiple conditions without proper separation
assertTrue(cond1 && cond2 && cond3);
```

---

## 6. Test Data Management

### ✅ DO - Use Constants:
```java
// In TestConstants.java
public static final String VALID_EMAIL = "test@example.com";
public static final String VALID_PASSWORD = "SecurePassword123!";
public static final int WAIT_TIMEOUT = 10;

// In Test
@Test
public void verifyLogin() {
    loginPage.login(TestConstants.VALID_EMAIL, TestConstants.VALID_PASSWORD);
}
```

### ❌ DON'T:
```java
@Test
public void verifyLogin() {
    loginPage.login("test@example.com", "SecurePassword123!");
    // Hard-coded values scattered everywhere
}
```

---

## 7. Error Handling

### ✅ DO:
```java
public boolean isElementVisible() {
    try {
        WebElement element = wait.until(
            ExpectedConditions.visibilityOfElementLocated(locator)
        );
        return element.isDisplayed();
    } catch (TimeoutException | NoSuchElementException e) {
        System.err.println("Element not visible: " + e.getMessage());
        return false;
    }
}
```

### ❌ DON'T:
```java
public boolean isElementVisible() {
    WebElement element = driver.findElement(locator);  // No error handling
    return element.isDisplayed();
}
```

---

## 8. Test Naming Convention

### ✅ DO - Descriptive Names:
```java
@Test
public void verifyUserCanLoginWithValidCredentials() { }

@Test
public void verifyErrorMessageDisplayedForInvalidPassword() { }

@Test
public void verifySearchResultsFilterByPrice() { }
```

### ❌ DON'T:
```java
@Test
public void test1() { }

@Test
public void testLogin() { }

@Test
public void verifyStuff() { }
```

---

## 9. Setup and Teardown

### ✅ DO:
```java
@BeforeMethod
public void setUp() {
    driver = DriverFactory.getDriver();
    driver.get(BASE_URL);
}

@AfterMethod
public void tearDown() {
    DriverFactory.quitDriver();
}

// Page objects for each test
@BeforeMethod
public void initializePage() {
    loginPage = new LoginPage(driver);
}
```

### ❌ DON'T:
```java
@Test
public void test1() {
    driver = new ChromeDriver();  // Initialize in test
    driver.get("https://example.com");  // Navigate in test
    // ... test code
    driver.quit();  // Cleanup in test
}
```

---

## 10. Logging Best Practices

### ✅ DO - Use Logging Framework:
```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage {
    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);
    
    public void login(String username, String password) {
        logger.info("Attempting to login with username: {}", username);
        // ... action ...
        logger.debug("Login successful");
    }
}
```

### ❌ DON'T:
```java
public void login(String username, String password) {
    System.out.println("Logging in with username: " + username);
    // ... action ...
    System.out.println("Login done");
}
```

---

## 11. Documentation (JavaDoc)

### ✅ DO:
```java
/**
 * Logs in to the application with valid credentials.
 * 
 * Precondition: User must be on the login page
 * Postcondition: User is redirected to the dashboard
 *
 * @param username the email address of the user
 * @param password the password associated with the email
 * @return true if login is successful, false otherwise
 * @throws TimeoutException if page elements not found within timeout
 */
public boolean login(String username, String password) {
    // ...
}
```

### ❌ DON'T:
```java
// No documentation
public boolean login(String username, String password) {
    // ...
}
```

---

## 12. XPath Best Practices

### ✅ DO - Write Maintainable XPath:
```java
// Specific attributes
By.xpath("//button[@id='submit-btn']");

// Partial attribute matching
By.xpath("//div[contains(@class, 'error-message')]");

// Text matching
By.xpath("//button[text()='Login']");

// Combine conditions
By.xpath("//input[@type='email' and @name='username']");
```

### ❌ DON'T:
```java
// Absolute XPath - brittle
By.xpath("/html/body/div[1]/div[2]/form[1]/input[1]");

// Generic
By.xpath("//*");

// Text with extra spaces
By.xpath("//button[text()=' Submit ']");
```

---

## 13. Common Exceptions & Solutions

| Exception | Cause | Solution |
|-----------|-------|----------|
| NoSuchElementException | Element not found | Use explicit wait |
| TimeoutException | Element not found before timeout | Increase wait time or verify locator |
| StaleElementReferenceException | Page refreshed after finding element | Refind element or use Page Objects |
| ElementNotInteractableException | Element not in viewport or visible | Scroll to element or use Actions |

---

## 14. Checklist Before Submitting Code

- [ ] Test is descriptively named
- [ ] All locators are in Page Object
- [ ] Using explicit waits (no Thread.sleep)
- [ ] Proper exception handling
- [ ] TestNG assertions only
- [ ] JavaDoc comments added
- [ ] No hard-coded test data
- [ ] Page objects follow POM pattern
- [ ] @BeforeMethod and @AfterMethod used
- [ ] Test is independent and can run in any order
- [ ] No System.out.println() (use logging instead)
- [ ] Code is DRY (Don't Repeat Yourself)

---

## 15. Useful TestNG Annotations

```java
@BeforeSuite      // Before all tests in suite
@BeforeClass      // Before all methods in class (static)
@BeforeMethod     // Before each test method
@Test             // Marks as test method
@AfterMethod      // After each test method
@AfterClass       // After all methods in class (static)
@AfterSuite       // After all tests in suite

@Test(groups = {"smoke"})              // Group tests
@Test(dependsOnMethods = {"loginTest"}) // Specify dependencies
@Test(priority = 1)                    // Execution priority
@Test(timeOut = 5000)                  // Timeout in milliseconds
@Test(enabled = false)                 // Skip test
```

---

## Running Tests

```bash
# Run all tests
mvn test

# Run with TestNG XML
mvn test -Dsurefire.suiteXmlFiles=testng.xml

# Run specific group
mvn test -Dgroups=smoke

# Run in parallel
mvn test -DthreadCount=3

# Generate report
mvn surefire-report:report
```

---

## Resources

- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [TestNG Documentation](https://testng.org/doc/)
- [Page Object Model Pattern](https://martinfowler.com/bliki/PageObject.html)

---

**Last Updated:** April 24, 2026

