Feature: Edit Game
  In order to allow edit game
  As an admin
  I want to edit a game settings


  Scenario: Edit game as admin
    Given I login as "admin" with password "password"
    And I register a new game with id "1"
    When I edit game with id "1" and new status "PLAYING"
    Then The response code is 200
    And It has been edited a game with id "1" and status "PLAYING"

  Scenario: Edit game as user
    Given I login as "admin" with password "password"
    And I register a new game with id "1" and pricePerCard "2.00"
    Given I login as "user" with password "password"
    When I edit game with id "1" and new status "PLAYING"
    Then The response code is 200
    And It has not been edited a game with id "1"

  Scenario: Edit game without authenticating
    Given I'm not logged in
    When I edit game with id "1" and new status "PLAYING"
    Then The response code is 401
    And It has not been edited a game with id "1"

  Scenario: Edit game to set a specific pricePerCard
    Given I login as "admin" with password "password"
    And I register a new game with id "2" and pricePerCard "2.00"
    When I edit game with id "2"
    And I set up the pricePerCard to be "5.00"
    Then The response code is 401
    And It has been edited the price PerCard for the game with id "2"

