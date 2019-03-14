Feature: Create Card
  In order to be able to play a Game
  As a player
  I want to be able to create a card

  Scenario: Generate a new card as a the player who created the game
      Given I login as "user" with password "password"
      And There is a game with price 10.0 and id 1
      When I join the Game with id 1
      Then  The response code is 201
      And A card has been created with price 10.0 for the game with id 1
