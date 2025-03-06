package com.epam.driver;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

  private DriverFactory() {}

  public static WebDriver createWebDriver(String browser) {
    return getWebDriverFactory(browser).createWebDriver();
  }

  private static WebDriverFactory getWebDriverFactory(String browser) {
    switch (browser.toLowerCase()) {
      case "firefox":
        return new FirefoxDriverFactory();
      case "chrome":
      default:
        return new ChromeDriverFactory();
    }
  }

}


