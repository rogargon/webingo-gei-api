Feature: List players
  In order to allow the admin to get the players
  As an admin
  I want to list the players

  Scenario:List players without authenticating
    Given I'm not logged in
    When I list players
    Then The response code is 401


  Scenario: Having 2 players, list players authenticated as admin
    Given I login as "admin" with password "password"
    And I register a new player with username "Manolo", email "manolo@webingo.org" and password "password"
    And I register a new player with username "Joan", email "joan@webingo.org" and password "password"
    When I list players
    Then The response code is 200
    And The player with name "Manolo" is in the List response
    And The player with name "Joan" is in the List response

  Scenario: Having 2 players, try to list players authenticated as a player
    Given I login as "user" with password "password"
    And I register a new player with username "Manolo", email "manolo@webingo.org" and password "password"
    And I register a new player with username "Joan", email "joan@webingo.org" and password "password"
    When I list players
    Then The response code is 200

  Scenario: Having 0 players, list players authenticated as admin
    Given I login as "admin" with password "password"
    When I list players
    Then The response code is 200
    And The players list is empty

  Scenario: List player authenticated as player
    Given I login as "admin" with password "password"
    And I register a new player with username "player", email "player@webingo.org" and password "password"
    And I login as "player" with password "password"
    When I list player with username "user"
    Then The response code is 200
    And The player with name "user" is in the response


  Scenario:No having a player with username Pepe, try to list player Pepe authenticated as player
    Given I login as "admin" with password "password"
    And I register a new player with username "player", email "player@webingo.org" and password "password"
    And I login as "player" with password "password"
    When I list player with username "Pepe"
    Then The response code is 404

  Scenario: List player authenticated as admin
    Given I login as "admin" with password "password"
    When I list player with username "user"
    Then The response code is 200
    And The player with name "user" is in the response

  Scenario:No having a player with username Pepe, try to list player Pepe authenticated as admin
    Given I login as "admin" with password "password"
    When I list player with username "Pepe"
    Then The response code is 404




