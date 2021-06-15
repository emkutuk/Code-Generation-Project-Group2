Feature: Create a transaction
  Scenario A user can create a transaction
    When a user creates a new transaction
    Then it is stored in the database
    And the transaction is returned to the user

  Scenario: As a customer I want to deposit cash into my account
    Given I am already an existing customer
    And I am authorized
    When I deposit money
    Then It is stored in my account
    And It is stored in the database
