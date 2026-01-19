# Java Cucumber JUnit Appium Example

This project demonstrates how to run automated UI tests on Android devices using Appium, Cucumber, and JUnit, with seamless integration into Sauce Labs for cloud execution and advanced reporting.

## Features

- **BDD Framework**: Uses Cucumber to write readable test scenarios in Gherkin syntax.
- **Test Runner**: Utilizes JUnit 4 to execute Cucumber features.
- **Mobile Automation**: Uses Appium Java Client to interact with Android devices (via Chrome browser).
- **Sauce Labs Integration**: 
  - Runs tests on Real Devices in the Sauce Labs cloud.
  - Automatically updates job status (Passed/Failed).
  - Logs Scenario names to the Sauce Labs job.
  - **Live Step Logging**: Uses a custom listener to log every Cucumber step execution to the Sauce Labs "Context" tab.

## Prerequisites

- Java 11 or higher
- Maven
- A [Sauce Labs](https://saucelabs.com/) account

## Project Structure

Here is an overview of the key files in this repository:

- **`pom.xml`**  
  Manages dependencies (Cucumber, JUnit, Appium, Selenium) and build plugins.

- **`src/test/resources/features/login.feature`**  
  The Cucumber feature file containing the test scenarios (e.g., "Successful login on Android Real Device").

- **`src/test/java/com/example/runner/TestRunner.java`**  
  The JUnit runner class. It is configured to:
  - Locate feature files and step definitions.
  - Register the `SauceStepListener` plugin for enhanced logging.

- **`src/test/java/com/example/utils/DriverFactory.java`**  
  Responsible for:
  - Setting up `AndroidDriver` with Sauce Labs options (credentials, device selection).
  - Managing the driver instance.

- **`src/test/java/com/example/steps/Hooks.java`**  
  Contains Cucumber lifecycle hooks:
  - `@Before`: Initializes the driver and logs the **Scenario Name** to Sauce Labs.
  - `@After`: Updates the final **Job Status** (passed/failed) on Sauce Labs and quits the driver.

- **`src/test/java/com/example/steps/LoginSteps.java`**  
  The "Glue Code" implementation for the steps defined in `login.feature` (e.g., entering username/password).

- **`src/test/java/com/example/utils/SauceStepListener.java`**  
  A custom Cucumber `ConcurrentEventListener`. It intercepts step execution events and sends the step text to Sauce Labs using `sauce:context`, allowing you to see exactly which step is running in the Sauce Labs dashboard.

## Configuration

The project reads Sauce Labs credentials from environment variables for security. You must set the following variables:

- `SAUCE_USERNAME`
- `SAUCE_ACCESS_KEY`

## How to Run

1. Open your terminal.
2. Navigate to the project root directory.
3. Run the tests using Maven:

```bash
# Option 1: Inline environment variables
SAUCE_USERNAME=your_username SAUCE_ACCESS_KEY=your_access_key mvn clean test

# Option 2: If environment variables are already exported
mvn clean test
```

4. Once the tests complete, go to your Sauce Labs dashboard to view the execution. You will see the job name matching your scenario, and under the "Context" tab (or logs), you will see the individual Cucumber steps.

