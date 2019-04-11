Feature: List Games
  In order to allow the admin to get the games
  As an admin
  I want to list the games

  Scenario: Try to list games as admin
    Given I login as "admin" with password "password"
    And I register a new game with id "1"
    And I register a new game with id "2"
    When I try to list games
    Then The response code is 200

  Scenario: Try to list games as player
    Given I login as "admin" with password "password"
    And I register a new game with id "1"
    And I register a new game with id "2"
    Then I login as "player" with password "password"
    When I try to list games
    Then The response code is 401

  Scenario: Try to list games non-authenticated
    Given I login as "admin" with password "password"
    And I register a new game with id "1"
    And I register a new game with id "2"
    Then I'm not logged in
    When I try to list games
    Then The response code is 401
  
  Scenario: Try to list empty games
    Given I login as "admin" with password "password"
    When I try to list games
    Then The response code is 200
    And Total elements is 0