Feature: Edit Game
  In order to allow edit game
  As an admin
  I want to edit a game settings

  Scenario: Edit game as admin Good!
    Given I login as "admin" with password "password"
    And I register a new game with id "1"
    When I edit game with id "1"
    Then The response code is 200

  Scenario: Edit game as player Good!
    Given I login as "player" with password "password"
    When I edit game with id "1"
    Then The response code is 401

  Scenario: Edit game as user Good!
    Given I login as "admin" with password "password"
    And I register a new game with id "2"
    Then I login as "player" with password "password"
    When I edit game with id "2"
    Then The response code is 401

  Scenario: Edit game without authenticating Good!
    Given I'm not logged in
    When I edit game with id "1"
    Then The response code is 401
    And It has not been edited a game with id "1"

  Scenario: Edit game as player to set a specific pricePerCard Good!
    Given I login as "admin" with password "password"
    And I register a new game with id "3"
    Then I login as "player" with password "password"
    When I edit game with id "3" and set up the pricePerCard to be "5.00"
    Then The response code is 401

  Scenario: Edit game as admin to set a specific pricePerCard Good!
    Given I login as "admin" with password "password"
    And I register a new game with id "1"
    When I edit game with id "1" and set up the pricePerCard to be "-5.00"
    Then The response code is 400

  Scenario: Edit game status as player Good!
    Given I login as "admin" with password "password"
    And I register a new game with id "1"
    Then I login as "player" with password "password"
    When I edit game with id "1" and new status "PLAYING"
    Then The response code is 401
    And It has not been deleted a game with id "1"

  Scenario: Edit game status as admin
    Given I login as "admin" with password "password"
    And I register a new game with id "1"
    When I edit game with id "1" and new status "PLAYING"
    Then The response code is 200
    And It has been edited a game with id "1" and status "PLAYING"


