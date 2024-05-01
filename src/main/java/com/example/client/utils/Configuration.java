package com.example.client.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    private static Configuration instance;
    private Properties properties;

    // Private constructor to prevent instantiation
    private Configuration() {
        properties = new Properties();
        try (FileInputStream input = new FileInputStream("config.properties")) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // to get single instance from class
    public static synchronized Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

    // to retrieve property values
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
