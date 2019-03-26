Feature: List Card
  In order to be able to see all the cards related to an User or a Game
  As an admin
  I want to be able to list all cards related to them

  Scenario: As an admin I
    Given I login as "player1" with password "password"
    And I create a new game with price 10.0
    When I'm not logged in
    And I login as "player2" with password "password"
    And I create a card
    Then  The response code is 201
    And A card has been created with price 10.0

  Scenario: List a card as admin
    Given I login as "user" with password "password"
    And There is a game with price 10.0 and id 1
    When I'm not logged in
    And I login as "player2" with password "password"
    And I create a card
    Then  The response code is 201
    And The admin list a card

  Scenario: List a card as user
    Given I login as "user" with password "password"
    And There is a game with price 10.0 and id 1
    When I'm not logged in
    And I login as "player2" with password "password"
    And I create a card
    Then  The response code is 201
    And The user list a card

  Scenario: List a player 1 card as player 2
    Given I login as "player1" with password "password"
    And There is a game with price 10.0 and id 1
    When I'm not logged in
    And I login as "player2" with password "password"
    And I create a card
    Then  The response code is 201
    And The player 1 card has not been list by player 2
