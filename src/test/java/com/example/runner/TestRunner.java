package com.example.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.example.steps",
        plugin = {"pretty", "html:target/cucumber-reports.html", "com.example.utils.SauceStepListener"},
        monochrome = true,
        tags = "@saucelabs"
)
public class TestRunner {
}

