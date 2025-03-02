package com.epam.tests;

import com.epam.driver.DriverFactory;
import com.epam.pages.SauceInventoryPage;
import com.epam.pages.SauceLoginPage;
import com.epam.utils.ConfigManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class WebPageTest {

  private static WebDriver driver;
  private static SauceLoginPage sauceLoginPage;
  private static SauceInventoryPage sauceInventoryPage;

  @BeforeClass
  @Parameters({"browser", "env"})
  public void setup() {
    String browser = ConfigManager.get("browser");
    String baseUrl = ConfigManager.get("baseUrl");
    System.setProperty("browser", browser);
    System.setProperty("env", ConfigManager.get("env"));
    driver = DriverFactory.getDriver();
    sauceLoginPage = new SauceLoginPage(driver);
    sauceInventoryPage = new SauceInventoryPage(driver);
    driver.get(baseUrl);
    sauceLoginPage.openPage();
  }

  @Test()
  public void testUserName() {
    sauceLoginPage.typeAnyCred();
    sauceLoginPage.pressLoginButton();
    Assert.assertEquals(sauceLoginPage.getErrorMessage(), "Epic sadface: Username is required", "Username validation failed");
  }

  @Test()
  public void testValidCred() {
    sauceLoginPage.typeValidName();
    sauceInventoryPage = sauceLoginPage.pressLoginButton();
    Assert.assertTrue(sauceInventoryPage.isLogoDisplayed(), "Login validation failed");
    driver.navigate().to(ConfigManager.get("baseUrl"));
  }

  @AfterClass
  public void finish() {
    DriverFactory.closeDriver();
  }

}
