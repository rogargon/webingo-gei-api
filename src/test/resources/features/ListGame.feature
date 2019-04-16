Feature: List Games
  In order to allow to list games
  As an admin
  I want to list the  games

  Scenario:List games without authenticating
    Given I'm not logged in
    When I list games
    Then The response code is 401

  Scenario: List game authenticated as admin
    Given I login as "admin" with password "password"
    And I register a new game with id "1" and pricePerCard "2.00"
    When I list game with id "1"
    Then The response code is 200

  Scenario: List 2 games that I registered as admin
    Given I login as "admin" with password "password"
    And I register a new game with id "1" and pricePerCard "2.00"
    And I register a new game with id "2" and pricePerCard "3.00"
    When I list games
    Then The response code is 200
    Then The game with id "1" is in the response
    Then The game with id "2" is in the response

  Scenario: Having 0 games, list games authenticated as admin
    Given I login as "admin" with password "password"
    When I list games
    Then The response code is 200
    And The game list is empty

  Scenario: Try to list games as user
    Given I login as "player" with password "password"
    When I list games
    Then The response code is 401

  Scenario:No having a game with id 3, try to list game 3 authenticated as admin
    Given I login as "admin" with password "password"
    And I register a new game with id "2" and pricePerCard "4.0"
    When I list game with id "3"
    Then The response code is 404
