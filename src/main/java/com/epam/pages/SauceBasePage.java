package com.epam.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SauceBasePage {

  private static final Logger logger = LogManager.getLogger(SauceBasePage.class);

  protected void highlightElement(SelenideElement element) {
    try {
      Selenide.executeJavaScript("arguments[0].style.border='3px solid red'", element);
      logger.info("Highlighted element: {}", element);
    } catch (Exception e) {
      logger.warn("Could not highlight element: {}", e.getMessage());
    }
  }

}
