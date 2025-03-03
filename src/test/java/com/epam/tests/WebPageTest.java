package com.epam.tests;

import static com.codeborne.selenide.Selenide.open;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.epam.TestsListener;
import com.epam.driver.DriverFactory;
import com.epam.pages.SauceInventoryPage;
import com.epam.pages.SauceLoginPage;
import com.epam.utils.ConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(TestsListener.class)
public class WebPageTest {

  private static final Logger logger = LoggerFactory.getLogger(WebPageTest.class);
  private static SauceLoginPage sauceLoginPage;
  private static SauceInventoryPage sauceInventoryPage;

  @BeforeMethod
  @Parameters({"browser", "env"})
  public void setup(@Optional("chrome") String browser, @Optional("dev") String env) {
    logger.info("Starting test with browser: {} and environment: {}", browser, env);
    System.setProperty("env", env);
    Configuration.browser = browser;
    Configuration.reportsFolder = "screenshots";
    Configuration.timeout = 10000;
    sauceLoginPage = new SauceLoginPage();
    sauceInventoryPage = new SauceInventoryPage();
    open(ConfigManager.get("baseUrl"));
    logger.info("Navigated to: {}", ConfigManager.get("baseUrl"));
  }

  @Test()
  public void testUserName() {
    logger.info("Starting testUserName test case");
    sauceLoginPage.typeAnyCred();
    logger.info("Entered credentials");
    sauceLoginPage.pressLoginButton();
    logger.info("Clicked login button");
    Assert.assertEquals(sauceLoginPage.getErrorMessage(),
        "Epic sadface: Username is required", "Username validation failed");
  }

  @Test()
  public void testValidCred() {
    logger.info("Starting testValidCred test case");
    sauceLoginPage.typeValidName();
    sauceInventoryPage = sauceLoginPage.pressLoginButton();

    String currentUrl = WebDriverRunner.url();
    logger.info("Current URL after login: {}", currentUrl);
    Assert.assertTrue(currentUrl.contains("inventory.html"), "User was not redirected to the inventory page.");

    Assert.assertTrue(sauceInventoryPage.isLogoDisplayed(), "Login validation failed");
    open(ConfigManager.get("baseUrl"));
    logger.info("Test completed, navigating back to base URL.");
  }

  @AfterMethod
  public void finish() {
    logger.info("Closing driver for test");
    DriverFactory.closeDriver();
  }

}
