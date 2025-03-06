package com.epam.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SauceBasePage {

  private static final Logger logger = LoggerFactory.getLogger(SauceBasePage.class);

  protected void highlightElement(SelenideElement element) {
    try {
      Selenide.executeJavaScript("arguments[0].style.border='3px solid red'", element);
      logger.info("Highlighted element: {}", element);
    } catch (Exception e) {
      logger.warn("Could not highlight element: {}", e.getMessage());
    }
  }

}
