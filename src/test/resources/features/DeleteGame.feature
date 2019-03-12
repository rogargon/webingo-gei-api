Feature: Delete Game
  In order to allow a admin to delete a game
  As an user
  I want to delete a game.

  Scenario: Admin try to delete a game not started
    Given I login as "admin" with password "password"
    And  I register a new game with id "1" and status "0"
    When I delete a game with id "1"
    Then The response code is 204
    And It has been deleted a game with id "1"

  Scenario: Player try to delete a game not started
    Given I login as "admin" with password "password"
    And  I register a new game with id "1" and status "0"
    Given I login as "player" with password "password"
    When I delete a game with id "1"
    Then The response code is 401
    And It has not been deleted a game with id "1"

  Scenario: Remove game that not exist
    Given I login as "admin" with password "password"
    When I delete a game with id "1"
    Then The response code is 404

  Scenario: Admin try to delete a game wich is running
    Given I login as "admin" with password "password"
    And  I register a new game with id "1" and status "1"
    When I delete a game with id "1"
    Then The response code is 403
    And It has not been deleted a game with id "1"
