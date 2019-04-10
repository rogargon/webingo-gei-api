Feature: List Games
  In order to allow to list games
  As an admin
  I want to list all the previous games

  Scenario: List two new games that I registered as admin
    Given I login as "admin" with password "password"
    And I register a new game with id "1" and pricePerCard "2.00"
    And I register a new game with id "2" and pricePerCard "3.00"
    When I list the previous games
    Then The response code is 200
    And The list contains "2" games

  Scenario: Try to list games as user
    Given I login as "player" with password "password"
    When I list the previous games
    Then The response code is 401

  Scenario: Try to list 0 games
    Given I login as "admin" with password "password"
    When I list the previous games
    Then The response code is 200
    And The list contains "0" games