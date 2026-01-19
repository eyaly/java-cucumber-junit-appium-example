package com.example.utils;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestStepStarted;
import org.openqa.selenium.JavascriptExecutor;

public class SauceStepListener implements ConcurrentEventListener {

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepStarted.class, this::handleTestStepStarted);
    }

    private void handleTestStepStarted(TestStepStarted event) {
        if (event.getTestStep() instanceof PickleStepTestStep) {
            PickleStepTestStep testStep = (PickleStepTestStep) event.getTestStep();
            String stepText = testStep.getStep().getText();
            
            try {
                if (DriverFactory.getDriver() != null) {
                    ((JavascriptExecutor) DriverFactory.getDriver())
                        .executeScript("sauce:context=" + stepText);
                }
            } catch (Exception e) {
                // Ignore if driver not ready or fails
                System.err.println("Failed to update Sauce Labs context: " + e.getMessage());
            }
        }
    }
}

