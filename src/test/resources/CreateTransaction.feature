Feature: Create a transaction

  Scenario As a customer I want to transfer money between my savings account and current account
    Given I am already an existing customer
    And I have a current account
    And I have a savings account
    And I am authorized
    When I create a transaction
    Then The money is transferred between my accounts
    And The transaction is stored in the database
    And I can see the transaction

  Scenario: As a customer I want to withdraw cash into my account
    Given I am already an existing customer
    And I have a current account
    And I am authorized
    When I deposit money
    Then It is stored in my account
    And The transaction is stored in the database
    And I can see the transaction

  Scenario: As a customer I want to withdraw money from my account
    Given I am already an existing customer
    And I have a current account
    And I am authorized
    When I withdraw money
    Then The money is remove from my account
    And The transaction is stored in the database
    And I can see the transaction

  Scenario: As a customer I want to transfer money to another customers account
    Given I am already an existing customer
    And I have a current account
    And I am authorized
    And The other account exists
    When I create a transaction
    Then The money is removed my account
    And The money is added to the other account
    And The transaction is stored in the database
    And I can see the transaction

  Scenario: As an employee I want to review a transaction for a customer

  Scenario: As an employee I want to transfer money to a savings account on behalf of a customer

  Scenario: As an employee I want to transfer money to another customer on behalf of a customer

