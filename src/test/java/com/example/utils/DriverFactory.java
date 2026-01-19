package com.example.utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.MutableCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    private static AndroidDriver driver;

    public static void initializeDriver() throws MalformedURLException {

        String username = System.getenv("SAUCE_USERNAME");
        String accessKey = System.getenv("SAUCE_ACCESS_KEY");

        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("appium:deviceName", "Samsung.*"); // Updated device name regex
        caps.setCapability("appium:automationName", "UiAutomator2");

        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("appiumVersion", "latest");
        sauceOptions.setCapability("username", username);
        sauceOptions.setCapability("accessKey", accessKey);
        sauceOptions.setCapability("build", "appium-build-1");
        sauceOptions.setCapability("name", "Login Scenario");

        caps.setCapability("sauce:options", sauceOptions);

        URL url = new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub");
        
        driver = new AndroidDriver(url, caps);
    }

    public static AndroidDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
