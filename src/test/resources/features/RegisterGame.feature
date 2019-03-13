Feature: Register Game
  In order to allow a new game to use the app
  As an admin
  I want to register a new player account

  Scenario: Register new game as admin
    Given I login as "admin" with password "password"
    When I register a new game with id "1"
    Then The response code is 201
    And It has been created a game with id "1"

  Scenario: Register new game as user
    Given I login as "player" with password "password"
    When I register a new game with id "1"
    Then The response code is 401
    And It has not been created a game with id "1"

  Scenario: Try to register new player without authenticating
    Given I'm not logged in
    When I register a new game with id "1"
    Then The response code is 401
    And It has not been created a game with id "1"