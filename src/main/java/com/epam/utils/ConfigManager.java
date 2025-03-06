package com.epam.utils;

import java.util.Properties;

public class ConfigManager {

  private static final ConfigLoader configLoader = new FileConfigLoader();

  private static final Properties properties = configLoader.load();

  public static String get(String key) {
    String systemValue = System.getProperty(key);
    if (systemValue != null && !systemValue.isEmpty()) {
      return systemValue;
    }
    return properties.getProperty(key);
  }

}
