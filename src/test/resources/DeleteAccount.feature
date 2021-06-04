Feature: Delete an Account
  As a customer I would like to delete my saving account

  Scenario: I have a saving account and I want to delete it
    Given I am already an existing customer with a saving account that is with 0 balance
    When I want to delete a my account
    Then I should be able to delete my account