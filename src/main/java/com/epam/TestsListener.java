package com.epam;

import com.epam.driver.DriverFactory;
import com.epam.pages.SauceBasePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestsListener implements ITestListener {

  private static final Logger logger = LoggerFactory.getLogger(TestsListener.class);

    @Override
    public void onTestFailure(ITestResult result) {
      WebDriver driver = DriverFactory.getDriver();
      if (driver != null) {
        SauceBasePage sauceBasePage = new SauceBasePage(driver);
        String screenshotPath = sauceBasePage.takeScreenshot(driver, result.getName());
        if (screenshotPath != null) {
          logger.error("Test '{}' failed. Screenshot saved at: {}", result.getName(), screenshotPath);
        } else {
          logger.error("Test '{}' failed, but screenshot capture failed.", result.getName());
        }
      } else {
        logger.error("Test '{}' failed, but WebDriver was null. Screenshot not taken.", result.getName());
      }
      logger.error("Failure Reason: ", result.getThrowable());  // Log stack trace
    }

    @Override
    public void onTestStart(ITestResult result) {
      logger.info("Test '{}' started.", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
      logger.info("Test '{}' passed.", result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
      logger.warn("Test '{}' skipped.", result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
      logger.info("Test suite '{}' started.", context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
      logger.info("Test suite '{}' finished.", context.getName());
    }
}



