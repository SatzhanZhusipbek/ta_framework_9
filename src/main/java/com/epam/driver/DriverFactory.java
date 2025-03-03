package com.epam.driver;


import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.Objects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverFactory {

    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverFactory() {}

    public static synchronized WebDriver getDriver(String browser) {
      if (Objects.isNull(driverThreadLocal.get())) {
        WebDriver driver;
        try {
          switch (browser) {
            case "firefox":
              WebDriverManager.firefoxdriver().setup();
              driver = new FirefoxDriver();
              logger.info("Initialized Firefox WebDriver");
              break;
            case "chrome":
            default:
              WebDriverManager.chromedriver().setup();
              driver = new ChromeDriver();
              logger.info("Initialized Chrome WebDriver");
              break;
          }
          driver.manage().window().maximize();
          driverThreadLocal.set(driver);
        } catch (Exception e) {
          assert logger != null;
          logger.error("Failed to initialize WebDriver for browser: {}", browser, e);
          throw new RuntimeException("WebDriver initialization failed", e);
        }
      }
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


