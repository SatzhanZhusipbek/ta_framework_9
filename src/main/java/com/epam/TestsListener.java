package com.epam;

import com.epam.reportportal.testng.ReportPortalTestNGListener;
import com.epam.utils.ConfigManager;
import com.epam.utils.DriverManager;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;


public class TestsListener extends ReportPortalTestNGListener {

  private static final Logger logger = LogManager.getLogger(TestsListener.class);

  @Override
  public void onTestFailure(ITestResult result) {
    WebDriver driver = DriverManager.getDriver(ConfigManager.get("driver"));
    if (driver == null) {
      logger.error("Test '{}' failed, because WebDriver was null. "
          + "Screenshot was not taken.", result.getName());
      return;
    }
    String screenshotPath = captureScreenshot(driver, result.getName());

    if (screenshotPath != null) {
      File screenshotFile = new File(screenshotPath);
      if (screenshotFile.exists()) {
        try {
          byte[] fileContent = Files.readAllBytes(Paths.get(screenshotPath));
          String base64Screenshot = Base64.getEncoder().encodeToString(fileContent);

          logger.info("RP_MESSAGE#BASE64#{}#{}", base64Screenshot, "Failure Screenshot");
        } catch (IOException e) {
          throw new RuntimeException(e);
        }

      } else {
        logger.error("Screenshot file not found: " + screenshotPath);
      }
      super.onTestFailure(result);
    }
  }

  public String captureScreenshot(WebDriver driver, String testName) {
    if (driver == null) {
      logger.error("WebDriver instance is null.");
      return null;
    }

    String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    String screenshotPath = "screenshots/" + testName + "_" + timestamp + ".png";

    try {
      Files.createDirectories(Paths.get("screenshots"));
      File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      Files.copy(screenshot.toPath(), Paths.get(screenshotPath));
      logger.info("Screenshot saved at: {}", screenshotPath);
      return screenshotPath;
    } catch (IOException e) {
      logger.error("Failed to save a screenshot: {}", e.getMessage());
      return null;
    }
  }

}



