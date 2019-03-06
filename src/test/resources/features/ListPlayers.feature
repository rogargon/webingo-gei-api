Feature: List players
  In order to allow the admin to get the players
  As an admin
  I want to list the players

  Scenario: Try to list players without authenticating
    Given I'm not logged in
    When I list players
    Then The response code is 401

  Scenario: Having 2 players, list players authenticated as admin
    Given I login as "admin" with password "password"
    And It has been created a player with username "Manolo" and email "manolo@arriba.españa", the password is not returned
    And It has been created a player with username "Joan" and email "joan@catalunya.independent", the password is not returned
    When I list players
    Then The response code is 200
    And The player with name "Manolo" is in the response
    And The player with name "Joan" is in the response

  Scenario: Having 2 players, try to list players authenticated as a player
    Given I login as "player" with password "password"
    And It has been created a player with username "Manolo" and email "manolo@arriba.españa", the password is not returned
    And It has been created a player with username "Joan" and email "joan@catalunya.independent", the password is not returned
    When I list tags
    Then The response code is 401

