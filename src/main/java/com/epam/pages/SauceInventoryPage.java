package com.epam.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class SauceInventoryPage extends SauceBasePage{

  private static final Logger logger = LogManager.getLogger(SauceInventoryPage.class);

  private final SelenideElement logoElement = $(".app_logo");

  public boolean isLogoDisplayed() {
    try {
      logoElement.shouldBe(visible);
      logger.info("Logo is visible on Inventory Page.");
      return logoElement.exists();
    } catch (Exception e) {
      logger.error("Logo is NOT visible on Inventory Page: {}", e.getMessage());
      return false;
    }
  }

}
