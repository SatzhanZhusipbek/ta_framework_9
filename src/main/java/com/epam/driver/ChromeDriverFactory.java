package com.epam.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChromeDriverFactory extends WebDriverFactory{

  private static final Logger logger = LoggerFactory.getLogger(ChromeDriverFactory.class);

    @Override
    public WebDriver createWebDriver() {
      WebDriverManager.chromedriver().setup();
      logger.info("Initialized Chrome WebDriver");
      return new WebDriverDecorator(new ChromeDriver());
    }
}


