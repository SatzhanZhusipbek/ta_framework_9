package com.epam.pages;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SauceBasePage {

  protected WebDriver driver;
  private static final Logger logger = LoggerFactory.getLogger(SauceBasePage.class);

  public SauceBasePage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public String takeScreenshot(WebDriver driver, String testName) {
    File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
    String filepath = "screenshots/" + testName + "-" + timestamp + ".png";

    try {
      FileUtils.copyFile(srcFile, new File(filepath));
      logger.info("Screenshot saved: {}", filepath);
      return filepath;
    } catch (IOException e) {
      logger.error("Failed to save screenshot: {}", e.getMessage());
      return null;
    }
  }

  /**
   * Highlights an element for better debugging.
   */
  protected void highlightElement(WebElement element) {
    try {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("arguments[0].style.border='3px solid red'", element);
    } catch (Exception e) {
      logger.warn("Could not highlight element: {}", e.getMessage());
    }
  }

}
