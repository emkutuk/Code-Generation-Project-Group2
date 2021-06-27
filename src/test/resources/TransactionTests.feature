Feature: Transaction Tests

  Scenario: As a customer I want to transfer money between my accounts
    Given I am A Customer
    When I want to transfer "20" Euro from my one account that has iban "NL01INHO0000000055" to my another account that has iban "NL01INHO0000000054"
    Then Return HTTPStatus 200

  Scenario: As a customer I want to return all my transactions
    Given I am A Customer
    When I want to return all my transactions with offset "0" and max "10"
    Then Return HTTPStatus 200

  Scenario: As a customer I want to return transaction
    Given I am A Customer
    When I want to return my transaction with id ""
    Then Return HTTPStatus 200

  Scenario: As a customer I want to withdraw cash into my account

  Scenario: As a customer I want to withdraw money from my account

  Scenario: As a customer I want to transfer money to another customers account

  Scenario: As an employee I want to review a transaction for a customer

  Scenario: As an employee I want to transfer money to a savings account on behalf of a customer

  Scenario: As an employee I want to transfer money to another customer on behalf of a customer

