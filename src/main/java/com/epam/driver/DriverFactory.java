package com.epam.driver;

import com.epam.utils.ConfigManager;
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

  private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

  private DriverFactory(){

  }

  public static WebDriver getDriver() {
    if (Objects.isNull(driverThreadLocal.get())) {
      String browser = ConfigManager.get("browser");
      WebDriver driver;
      switch (browser.toLowerCase()) {
        case "firefox":
          WebDriverManager.firefoxdriver().setup();
          driver = new FirefoxDriver();
          logger.info("Initialized Firefox WebDriver");
          break;
        case "edge":
          WebDriverManager.edgedriver().setup();
          driver = new EdgeDriver();
          logger.info("Initialized Edge WebDriver");
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
    }
    return driverThreadLocal.get();
  }

  public static void closeDriver() {
    WebDriver driver = driverThreadLocal.get();
    if (Objects.nonNull(driver)) {
      driver.quit();
      logger.info("Closed WebDriver");
      driverThreadLocal.remove();
    }
  }

}
