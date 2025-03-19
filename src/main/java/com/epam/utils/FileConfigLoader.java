package com.epam.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileConfigLoader implements ConfigLoader {

  private static final Logger logger = LogManager.getLogger(FileConfigLoader.class);

  @Override
  public Properties load() {
    Properties properties = new Properties();
    String env = System.getProperty("environment");
    if (env == null || env.isEmpty()) {
      throw new RuntimeException("Environment variable 'environment' is not set.");
    }
    String propertiesFilePath = "src/main/resources/" + env + ".properties";

    try (FileInputStream fis = new FileInputStream(propertiesFilePath)) {
      properties.load(fis);
      logger.info("Loaded configuration from file: {}", propertiesFilePath);
    } catch (IOException e) {
      throw new RuntimeException("Failed to load config file: " + propertiesFilePath, e);
    }
    return properties;
  }
}
