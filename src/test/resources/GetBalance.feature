Feature: Get an Account Balance
  As a customer I would like to see my balance

  Scenario: As a customer I want to see my account's balance
    Given I have an account
    When I want to learn about my account's balance
    Then I should be able to see my account's balance

