Feature: Create Card
  In order to be able to play a Game
  As a player
  I want to be able to create a card

Scenario: Generate a new card as a the player who created the game
    Given I login as "player1" with password "password"
    When I create a new game with price 10.0
    And I create a card
    Then  The response code is 201
    And A card has been created with price 10.0

Scenario: Generate a new card as another player
  Given I login as "player1" with password "password"
  And I create a new game with price 10.0
  When I'm not logged in
  And I login as "player2" with password "password"
  And I create a card
  Then  The response code is 201
  And A card has been created with price 10.0