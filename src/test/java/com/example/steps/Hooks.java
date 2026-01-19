package com.example.steps;

import com.example.utils.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.JavascriptExecutor;

import java.net.MalformedURLException;

public class Hooks {

    @Before
    public void setUp(Scenario scenario) throws MalformedURLException {
        DriverFactory.initializeDriver();
        if (DriverFactory.getDriver() != null) {
            ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("sauce:context=" + scenario.getName());
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        String status = scenario.isFailed() ? "failed" : "passed";
        
        if (DriverFactory.getDriver() != null) {
            ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("sauce:job-result=" + status);
        }

        DriverFactory.quitDriver();
    }
}
