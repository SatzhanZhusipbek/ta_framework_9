package com.epam.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import java.time.Duration;
import org.openqa.selenium.Keys;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SauceLoginPage extends SauceBasePage{

  private static final Logger logger = LogManager.getLogger(SauceLoginPage.class);
  private final SelenideElement usernameInput = $("#user-name");
  private final SelenideElement passwordInput = $("#password");
  private final SelenideElement loginButton = $("#login-button");
  private final SelenideElement errorMessage = $(".error-message-container.error");

  public void typeAnyCred(String username, String password) {
    usernameInput.shouldBe(visible).setValue(username);
    highlightElement(usernameInput);
    usernameInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
    usernameInput.sendKeys(Keys.BACK_SPACE);
    passwordInput.shouldBe(visible).setValue(password);
    highlightElement(passwordInput);
    logger.info("Entered credentials: Username = {}, Password = [HIDDEN]", username);
  }

  public SauceInventoryPage pressLoginButton() {
    highlightElement(loginButton);
    loginButton.shouldBe(visible).click();
    logger.info("Clicked Login Button");
    return new SauceInventoryPage();
  }

  public String getErrorMessage() {
    String errorText = errorMessage.shouldBe(visible, Duration.ofSeconds(15)).getText();
    logger.error("Login failed with error: {}", errorText);
    return errorText;
  }

  public void typeValidName(String username, String password) {
    usernameInput.shouldBe(visible).setValue(username);
    highlightElement(usernameInput);
    passwordInput.shouldBe(visible).setValue(password);
    highlightElement(passwordInput);
    logger.info("Login happened");
  }

}
