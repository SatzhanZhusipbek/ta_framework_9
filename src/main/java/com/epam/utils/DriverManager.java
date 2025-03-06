package com.epam.utils;

import java.util.Objects;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.epam.driver.DriverFactory;

public class DriverManager {

  private static final Logger logger = LoggerFactory.getLogger(DriverManager.class);
  private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

  private DriverManager() {

  }

  public static WebDriver getDriver(String browser) {
    if (browser == null || browser.isEmpty()) {
      browser = "chrome";
    }
    if (Objects.isNull(driverThreadLocal.get())) {
      driverThreadLocal.set(DriverFactory.createWebDriver(browser));
    }
    logger.info("Returning WebDriver for browser: {}", browser);
    return driverThreadLocal.get();
  }

  public static synchronized void closeDriver() {
    WebDriver driver = driverThreadLocal.get();
    if (Objects.nonNull(driver)) {
      try {
        driver.quit();
        logger.info("Closed WebDriver");
      } catch (Exception e) {
        logger.error("Error while quitting WebDriver", e);
      } finally {
        driverThreadLocal.remove();
      }
    }
  }

}
