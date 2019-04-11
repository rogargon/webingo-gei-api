Feature: Edit Game
  In order to allow edit game
  As an admin
  I want to edit a game settings


  Scenario: Edit game as admin
    Given I login as "admin" with password "password"
    And There is a game with id 1
    When I edit the game with id 1
    Then The response code is 201
    And The game with id 1 has been edited

  Scenario: Edit game as user
    Given  I login as "user" with password "password"
    And There is a game with id 1
    When I edit the game with id 1
    Then The response code is 201
    And The game with id 1 has not been edited

  Scenario: Edit game without authenticating
    Given I'm not logged in
    And There is a game with id 1
    When I edit the game with id 1
    Then The response code is 401
    And The error message is "Unauthorized"
    And The game with id 1 has not been edited

  Scenario: Edit game to set a specific pricePerCard
    Given I login as "admin" with password "password"
    And There is a game with the pricePerCard 8.0 and id 2
    When I edit the game with id 2
    And I edit the pricePerCard to be 5.0 for the game with id 2
    Then The response code is 201
    And The game with id 2 has been edited

  Scenario: Edit game as admin with incorrect password
    Given Exists an admin "admin" with password "password"
    And There is a game with id 2
    When I login as "admin" with password "password1"
    Then The response code is 401
    And The error message is "Unauthorized"

  Scenario:Edit game to change the jackpot amount
    Given I login as "admin" with password "password"
    And There is a game with id 1
    When I edit the game with id 1
    And I edit the jackpot to be 500.0 for the game with id 1
    Then The response code is 200
    And The game with id 1 has been edited

  Scenario: Edit game to change the starting time
    Given I login as "admin" with password "password"
    And There is a game with id 3
    When I edit the game with id 3
    And I edit the starting time startAt to be "2019-04-25T10:24:00+01:00" for the game with id 3
    Then The response code is 201
    And The game with id 3 has been edited

  Scenario: Edit game to change the finishing time
    Given I login as "admin" with password "password"
    And There is a game with id 4
    When I edit the game with id 4
    And I edit the finishing time finishedAt to be "2019-04-25T10:24:00+01:00" for the game with id 4
    Then The response code is 201
    And The game with id 4 has been edited



