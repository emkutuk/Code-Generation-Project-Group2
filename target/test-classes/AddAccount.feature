Feature: Account Tests

  Scenario: As a customer I would like to create a saving account
    Given I am customer
    When I want to open a new account
    Then I should be able to open a new account

  Scenario: As a customer I would like to close my saving account
    Given I am already an existing customer with a saving account that is with zero balance
    When I want to close my account
    Then I should be able to delete my account

  Scenario: As a customer I would like to see my balance
    Given I have an account
    When I want to learn about my account's balance
    Then I should be able to see my account's balance