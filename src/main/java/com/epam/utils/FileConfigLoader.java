package com.epam.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileConfigLoader implements ConfigLoader {

  private static final Logger logger = LoggerFactory.getLogger(FileConfigLoader.class);

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
