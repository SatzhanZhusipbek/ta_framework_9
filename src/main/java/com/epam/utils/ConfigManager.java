package com.epam.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

  private static final Properties properties = new Properties();

  static {
    loadProperties();
  }

  private static void loadProperties() {
    String env = System.getProperty("env");
    if (env == null || env.isEmpty()) {
      throw new RuntimeException("Environment variable 'env' is not set. Ensure it's passed via TestNG.");
    }
    String propertiesFilePath = "src/main/resources/" + env + ".properties";
    try (FileInputStream fis = new FileInputStream(propertiesFilePath)) {
      properties.load(fis);
      System.out.println("Loaded environment: " + env);
    } catch (IOException e) {
      throw new RuntimeException("Failed to load config file: " + propertiesFilePath, e);
    }
  }

  public static String get(String key) {
    String systemValue = System.getProperty(key);
    if (systemValue != null && !systemValue.isEmpty()) {
      return systemValue;
    }
    return properties.getProperty(key);
  }

}
