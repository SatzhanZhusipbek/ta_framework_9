package com.epam;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.epam.driver.WebDriverDecorator;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import io.qameta.allure.Attachment;

public class TestsListener implements ITestListener {

  private static final Logger logger = LoggerFactory.getLogger(TestsListener.class);

  @Override
  public void onTestFailure(ITestResult result) {
    ITestContext context = result.getTestContext();
    WebDriver driver = (WebDriver) context.getAttribute("WebDriver");

    if (driver == null) {
      logger.error("❌ Test '{}' failed, but WebDriver instance was not found.", result.getName());
      return;
    }

    // ✅ Unwrap WebDriverDecorator if necessary
    if (driver instanceof WebDriverDecorator) {
      driver = ((WebDriverDecorator) driver).getOriginalDriver();
    }

    // ✅ Capture Screenshot
    String screenshotPath = captureScreenshot(driver, result.getName());

    if (screenshotPath == null) {
      logger.error("❌ Test '{}' failed, but screenshot capture failed.", result.getName());
      return;  // Stop execution to prevent attaching a non-existent file
    }

    File screenshot = new File(screenshotPath);

    // ✅ Ensure Screenshot Exists Before Attaching
    if (screenshot.exists()) {
      try {
        attachScreenshot(screenshot);
        logger.error("🖼️ Test '{}' failed. Screenshot attached to ReportPortal.", result.getName());
      } catch (Exception e) {
        logger.error("❌ Failed to attach screenshot to ReportPortal: {}", e.getMessage());
      }
    } else {
      logger.error("❌ Test '{}' failed, but screenshot file is missing: {}", result.getName(),
          screenshotPath);
    }

    // ✅ Log Failure Reason
    logger.error("💥 Failure Reason: ", result.getThrowable());
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

  public String captureScreenshot(WebDriver driver, String testName) {
    try {
      if (driver == null) {
        logger.warn("⚠ WebDriver instance is null. Cannot take screenshot.");
        return null;
      }

      if (!(driver instanceof TakesScreenshot)) {
        logger.warn("⚠ WebDriver does not support screenshots!");
        return null;
      }

      // ✅ Ensure directory exists
      File screenshotDir = new File("screenshots");
      if (!screenshotDir.exists()) {
        boolean created = screenshotDir.mkdirs();
        if (!created) {
          logger.error("❌ Failed to create 'screenshots' directory.");
          return null;
        }
      }

      // ✅ Capture Screenshot
      File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      if (srcFile == null || !srcFile.exists()) {
        logger.error("❌ Screenshot file was not created. WebDriver may not support screenshots.");
        return null;
      }

      // ✅ Use absolute paths to avoid relative path issues
      File destFile = new File(screenshotDir.getAbsolutePath(), testName + ".png");

      // ✅ Ensure file does not already exist (prevents file lock issues)
      if (destFile.exists()) {
        boolean deleted = destFile.delete();
        if (!deleted) {
          logger.error("❌ Cannot overwrite existing screenshot: {}", destFile.getAbsolutePath());
          return null;
        }
      }

      // ✅ Add a small wait to ensure file is fully written
      Thread.sleep(500);

      // ✅ Save Screenshot
      try {
        Files.copy(srcFile.toPath(), destFile.toPath());
        logger.info("✅ Screenshot saved at: {}", destFile.getAbsolutePath());
        return destFile.getAbsolutePath();
      } catch (IOException e) {
        logger.error("❌ Failed to save screenshot to '{}': {}", destFile.getAbsolutePath(),
            e.getMessage());
        return null;
      }

    } catch (WebDriverException e) {
      logger.error("❌ WebDriverException while taking screenshot: {}", e.getMessage());
      return null;
    } catch (Exception e) {
      logger.error("❌ Unexpected error capturing screenshot: {}", e.getMessage());
      return null;
    }
  }

  @Attachment(value = "Failure Screenshot", type = "image/png")
  public void attachScreenshot(File screenshot) {
    try {
      if (screenshot.exists()) {
        byte[] fileContent = Files.readAllBytes(screenshot.toPath());
        String base64Screenshot = Base64.getEncoder().encodeToString(fileContent);

        // ✅ Log using ReportPortal-compatible format
        logger.info("RP_MESSAGE#BASE64#{}#", base64Screenshot);

        logger.info("✅ Screenshot successfully attached to ReportPortal: {}", screenshot.getAbsolutePath());
      } else {
        logger.error("❌ Screenshot file not found: {}", screenshot.getAbsolutePath());
      }
    } catch (Exception e) {
      logger.error("❌ Could not attach screenshot: {}", e.getMessage());
    }
  }
}



