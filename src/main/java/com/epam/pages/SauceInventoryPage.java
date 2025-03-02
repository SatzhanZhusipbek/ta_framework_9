package com.epam.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SauceInventoryPage extends SauceBasePage{

  private static final Logger logger = LoggerFactory.getLogger(SauceInventoryPage.class);

  @FindBy(xpath = "//div[@class='app_logo']")
  WebElement logoElement;
  public SauceInventoryPage(WebDriver driver) {
    super(driver);
  }

  public boolean isLogoDisplayed() {
    try {
      WebDriverWait logoWait = new WebDriverWait(driver, Duration.ofSeconds(25));
      logoWait.until(ExpectedConditions.visibilityOf(logoElement));
      logger.info("Logo is visible on Inventory Page.");
      return logoElement.isDisplayed();
    } catch (Exception e) {
      logger.error("Logo is NOT visible on Inventory Page: {}", e.getMessage());
      return false;
    }
  }

}
