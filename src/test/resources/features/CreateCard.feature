Feature: Create Card
  In order to be able to play a Game
  As a player
  I want to be able to create a card


  Scenario: Create a card
    Given A card is created
    When I generate the numbers
    Then The resulting card is generated properly

  Scenario: Generate a new card as a the player who created the game
    Given I login as "user" with password "password"
    And There is a game with price 10.0 and id 1
    When I join the Game with id 1 as user "user"
    Then  The response code is 201
    And A card has been created with price 10.0 for the game with id 1

  Scenario: Try to join the game twice
    Given I login as "user" with password "password"
    And There is a game with price 10.0 and id 1
    When I join the Game with id 1 as user "user"
    And I join the Game with id 1 as user "user"
    Then  The response code is 403

  Scenario: Generate a new card logged with a different user than the one who created the game
    Given I login as "user" with password "password"
    And There is a game with price 10.0 and id 1
    And I'm not logged in
    And I login as "user2" with password "password"
    When I join the Game with id 1 as user "user"
    Then  The response code is 201
    And A card has been created with price 10.0 for the game with id 1

  Scenario: Generate a new card logged with a different user than the one who created the game
    Given I login as "user" with password "password"
    And There is a game with price 10.0 and id 1
    And I'm not logged in
    When I join the Game with id 1 as user "user"
    Then  The response code is 401
    And The card with id 1 does not exist

  Scenario: Generate a new card with price 0
    Given I login as "user" with password "password"
    When There is a game with price 0.0 and id 1
    Then  A "TransactionSystemException" occurs
    And The card with id 1 does not exist


  Scenario: Generate a new card with negative price
    Given I login as "user" with password "password"
    When There is a game with price -5.0 and id 1
    Then  A "TransactionSystemException" occurs
    And The card with id 1 does not exist

  Scenario: Generate a new card with negative price
    Given I login as "user" with password "password"
    When There is a game with price -5.0 and id 1
    Then  A "TransactionSystemException" occurs
    And The card with id 1 does not exist

