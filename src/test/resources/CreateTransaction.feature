Feature: Create a transaction
  Scenario A user can create a transaction
    When a user creates a new transaction
    Then it is stored in the database
    And the transaction is returned to the user