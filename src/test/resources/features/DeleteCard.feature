Feature: Delete Card
  In order to delete a card
  As an Admin
  I want to delete a previously created card

  Scenario: Delete a card as an admin
    Given I login as "admin" with password "password"
    And I create a new game with price 10.0
    When I delete a card
    Then  The response code is 201
    And A card has been deleted