package com.epam;

import static com.codeborne.selenide.Selenide.screenshot;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestsListener implements ITestListener {

  private static final Logger logger = LoggerFactory.getLogger(TestsListener.class);

    @Override
    public void onTestFailure(ITestResult result) {
      String screenshotPath = captureScreenshot(result.getName());
      if (screenshotPath != null) {
        logger.error("Test '{}' failed. Screenshot saved at: {}", result.getName(), screenshotPath);
      } else {
        logger.error("Test '{}' failed, but screenshot capture failed.", result.getName());
      }
      logger.error("Failure Reason: ", result.getThrowable());
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

  public String captureScreenshot(String testName) {
    try {
      Files.createDirectories(Paths.get("screenshots"));

      String screenshotFile = "screenshots/" + testName + ".png";
      screenshot(screenshotFile);
      logger.info("Screenshot taken: {}", screenshotFile);
      return new File(screenshotFile).getAbsolutePath();
    } catch (Exception e) {
      logger.error("Failed to capture screenshot: {}", e.getMessage());
      return null;
    }
  }
}



