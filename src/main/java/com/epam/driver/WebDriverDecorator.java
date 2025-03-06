package com.epam.driver;

import java.util.List;
import java.util.Set;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebDriverDecorator implements WebDriver {

  protected final WebDriver driver;
  private static final Logger logger = LoggerFactory.getLogger(WebDriverDecorator.class);

  public WebDriverDecorator(WebDriver driver) {
    this.driver = driver;
  }

  @Override
  public void get(String url) {
    logger.info("Navigating to: " + url);
    driver.get(url);
  }

  @Override
  public void quit() {
    logger.info("Closing browser...");
    driver.quit();
  }

  @Override
  public WebElement findElement(By by) {
    logger.info("Finding element: " + by);
    return driver.findElement(by);
  }

  @Override
  public @Nullable String getCurrentUrl() {
    return driver.getCurrentUrl();
  }

  @Override
  public @Nullable String getTitle() {
    return driver.getTitle();
  }

  @Override
  public List<WebElement> findElements(By by) {
    return driver.findElements(by);
  }

  @Override
  public @Nullable String getPageSource() {
    return driver.getPageSource();
  }

  @Override
  public void close() {
    driver.close();
  }


  @Override
  public Set<String> getWindowHandles() {
    return driver.getWindowHandles();
  }

  @Override
  public String getWindowHandle() {
    return driver.getWindowHandle();
  }

  @Override
  public TargetLocator switchTo() {
    return driver.switchTo();
  }

  @Override
  public Navigation navigate() {
    return driver.navigate();
  }

  @Override
  public Options manage() {
    return driver.manage();
  }
}


