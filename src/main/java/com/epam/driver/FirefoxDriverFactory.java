package com.epam.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FirefoxDriverFactory extends WebDriverFactory {

  private static final Logger logger = LogManager.getLogger(FirefoxDriverFactory.class);

  @Override
  public WebDriver createWebDriver() {
    WebDriverManager.firefoxdriver().setup();
    logger.info("Initialized Firefox WebDriver");
    return new WebDriverDecorator(new FirefoxDriver());
  }
}
