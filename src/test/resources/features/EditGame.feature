Feature: Edit Game
  In order to allow edit game
  As an admin
  I want to edit a game settings

  Scenario: Edit game as admin
    Given I login as "admin" with password "password"
    When I edit game with id "1"
    Then The response code is 200
    And It has been edited a game with id "1"

  Scenario: Edit game as user
    Given  I login as "user" with password "password"
    When I edit game with id "1"
    Then The response code is 401
    And The error message is "you are not authorized"
    And It has not been edited a game with id "1"

  Scenario: Edit game without authenticating
    Given I'm not logged in
    When I edit game with id "1"
    Then The response code is 401
    And The error message is "you need to authenticate"
    And It has not been edited a game with id "1"

  Scenario: Edit game with incorrect id
    Given I login as "admin" with password "password"
    When I edit game with id "incorrect"
    Then The response code is 400
    And The error message is "incorrect game id"
    And It has not been edited a game with id "1"

  Scenario: Edit a deleted game
    Given I login as "admin" with password "password"
    And I register a new game with id "1" and status "2"
    When I edit game with id "1"
    Then The response code is 404
    And The error message is "the game is deleted"
    And It has not been edited a game with id "1"
