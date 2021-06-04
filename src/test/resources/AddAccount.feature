Feature: Add an Account
  As a customer I would like to create a saving account

  Scenario: I do not have a saving account
    Given I am already an existing customer
    When I want to open a new account
    Then I should be able to open a new account

