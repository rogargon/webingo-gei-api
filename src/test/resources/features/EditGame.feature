Feature: Edit Game
  In order to allow edit game
  As an admin
  I want to edit a game settings


  Scenario: Edit game as admin
    Given I login as "admin" with password "password"
    And I register a new game with id "1" and pricePerCard "4.00"
    When I edit game with id "1" and new status "PLAYING"
    Then The response code is 200
    And It has been edited a game with id "1" and status "PLAYING"

  Scenario: Edit game as user
    Given I login as "admin" with password "password"
    And I register a new game with id "1" and pricePerCard "2.00"
    Given I login as "player" with password "password"
    When I edit game with id "1" and new status "PLAYING"
    Then The response code is 401
    And It has not been edited a game with id "1"

  Scenario: Edit game without authenticating
    Given I'm not logged in
    When I edit game with id "1" and new status "PLAYING"
    Then The response code is 401
    And It has not been edited a game with id "1"