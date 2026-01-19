Feature: Login to SauceDemo
  As a user
  I want to login to the SauceDemo website
  So that I can access the inventory

  @saucelabs
  Scenario: Successful login on Android Real Device
    Given I am on the SauceDemo login page
    When I enter username "standard_user" and password "secret_sauce"
    And I click the login button
    Then I should be redirected to the inventory page

