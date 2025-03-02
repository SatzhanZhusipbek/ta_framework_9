package com.epam.pages;

import com.epam.utils.ConfigManager;
import java.time.Duration;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SauceLoginPage extends SauceBasePage{

  private static final Logger logger = LoggerFactory.getLogger(SauceLoginPage.class);

  @FindBy(xpath = "//input[@id='user-name']")
  WebElement usernameInput;
  @FindBy(xpath = "//input[@id='password']")
  WebElement passwordInput;
  @FindBy(xpath = "//input[@id='login-button']")
  WebElement loginButton;
  @FindBy(xpath = "//div[@class='error-message-container error']")
  WebElement errorMessage;

  public SauceLoginPage(WebDriver driver) {
    super(driver);
  }

  public void openPage() {
    driver.get("https://www.saucedemo.com");
    logger.info("Navigated to Login Page");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOf(usernameInput));
    driver.manage().window().maximize();
    logger.info("Browser window maximized");
  }

  public void typeAnyCred() {
    String username = ConfigManager.get("username");
    String password = ConfigManager.get("password");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    wait.until(ExpectedConditions.visibilityOf(usernameInput));
    highlightElement(usernameInput);
    usernameInput.sendKeys(Keys.CONTROL + "a");
    usernameInput.sendKeys(Keys.DELETE);
    usernameInput.sendKeys(username);

    wait.until(ExpectedConditions.visibilityOf(passwordInput));
    highlightElement(passwordInput);
    passwordInput.sendKeys(Keys.CONTROL + "a");
    passwordInput.sendKeys(Keys.DELETE);
    passwordInput.sendKeys(password);

    logger.info("Entered credentials: Username = {}, Password = [HIDDEN]", username);
  }

  public SauceInventoryPage pressLoginButton() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.elementToBeClickable(loginButton));

    highlightElement(loginButton);
    loginButton.click();

    logger.info("Clicked Login Button");
    return new SauceInventoryPage(driver);
  }

  public String getErrorMessage() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOf(errorMessage));
    String errorText = errorMessage.getText();
    logger.error("Login failed with error: {}", errorText);
    return errorText;
  }

  public void typeValidName() {
    String username = ConfigManager.get("username");
    String password = ConfigManager.get("password");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOf(usernameInput));
    usernameInput.sendKeys(username);
    wait.until(ExpectedConditions.visibilityOf(passwordInput));
    passwordInput.sendKeys(password);
    logger.info("Login happened");
  }

}
