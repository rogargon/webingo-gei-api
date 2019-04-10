Feature: Register Game
  In order to allow a new game to use the app
  As an admin
  I want to register a new player account

  Scenario: Register new game as admin Good!
    Given I login as "admin" with password "password"
    When I register a new game with id "1" and pricePerCard "2.00"
    Then The response code is 201
    And It has been created a game with id "1"

  Scenario: Register new game as user Good!
    Given I login as "player" with password "password"
    When I register a new game with id "1" and pricePerCard "2.00"
    Then The response code is 401
    And It has not been created a game with id "1"

  Scenario: Try to register new game without authenticating Good!
    Given I'm not logged in
    When I register a new game with id "1" and pricePerCard "2.00"
    Then The response code is 401
    And It has not been created a game with id "1"

  Scenario: Register new game with free pricePerCard Good! (depends on free pricepercard)
    Given I login as "admin" with password "password"
    When I register a new game with id "1" and pricePerCard "0.0"
    Then The response code is 201

  Scenario: Register new game with negative pricePerCard Good!
    Given I login as "admin" with password "password"
    When I register a new game with id "1" and pricePerCard "-1.0"
    Then The response code is 400
    And The error message is "Bad Param"
    And It has not been created a game with id "1"