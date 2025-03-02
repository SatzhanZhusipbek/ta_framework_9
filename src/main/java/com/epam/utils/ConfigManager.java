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
    String env = System.getProperty("env", "dev"); // Default to 'dev' if no env is specified
    String propertiesFilePath = "src/main/resources/config/" + env + ".properties";

    try (FileInputStream fis = new FileInputStream(propertiesFilePath)) {
      properties.load(fis);
      System.out.println("Loaded environment: " + env);
    } catch (IOException e) {
      throw new RuntimeException("Failed to load config file: " + propertiesFilePath, e);
    }
  }

  public static String get(String key) {
    return properties.getProperty(key);
  }

}
