Feature: Register Game
  In order to allow a new game to use the app
  As an admin
  I want to register a new player account

  Scenario: Register new game as admin
    Given I login as "admin" with password "password"
    When I register a new game with id "1" and pricePerCard "2.00"
    Then The response code is 201
    And It has been created a game with id "1"

  Scenario: Register new game as user
    Given I login as "player" with password "password"
    When I register a new game with id "1" and pricePerCard "2.00"
    Then The response code is 401
    And It has not been created a game with id "1"

  Scenario: Try to register new game without authenticating
    Given I'm not logged in
    When I register a new game with id "1" and pricePerCard "2.00"
    Then The response code is 401
    And It has not been created a game with id "1"

  Scenario: Register new game with free pricePerCard
    Given I login as "admin" with password "password"
    When I register a new game with id "1" and pricePerCard "0.0"
    Then The response code is 500
    And The error message is "Price per card can not be negative or 0.0"
    And It has not been created a game with id "1"

  Scenario: Register new game with negative pricePerCard
    Given I login as "admin" with password "password"
    When I register a new game with id "1" and pricePerCard "-1.0"
    Then The response code is 500
    And The error message is "Price per card can not be negative or 0.0"
    And It has not been created a game with id "1"

  Scenario: Register new game and check jackpot is 0
    Given I login as "admin" with password "password"
    When I register a new game with id "1" and pricePerCard "2.0"
    Then The response code is 201
    And Jackpot from game with id "1" is "0.0"
