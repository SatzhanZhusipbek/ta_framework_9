package com.epam.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChromeDriverFactory extends WebDriverFactory{

  private static final Logger logger = LogManager.getLogger(ChromeDriverFactory.class);

    @Override
    public WebDriver createWebDriver() {
      WebDriverManager.chromedriver().setup();
      logger.info("Initialized Chrome WebDriver");
      return new WebDriverDecorator(new ChromeDriver());
    }
}


