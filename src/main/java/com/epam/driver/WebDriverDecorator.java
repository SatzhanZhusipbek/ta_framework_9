package com.epam.driver;

import java.util.List;
import java.util.Set;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WebDriverDecorator implements WebDriver, TakesScreenshot {

  protected final WebDriver driver;
  private static final Logger logger = LogManager.getLogger(WebDriverDecorator.class);

  public WebDriverDecorator(WebDriver driver) {
    this.driver = driver;
  }

  public WebDriver getOriginalDriver() {
    return this.driver;
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

  @Override
  public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
    if (driver instanceof TakesScreenshot) {
      return ((TakesScreenshot) driver).getScreenshotAs(target);
    } else {
      throw new WebDriverException("Driver does not support screenshots.");
    }
  }
}


