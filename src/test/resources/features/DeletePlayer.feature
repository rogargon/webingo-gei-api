Feature: Remove Player
  In order to allow remove player
  As an admin
  I want to remove a player account

  Scenario: Remove player as admin
    Given I login as "admin" with password "password"
    And I register a new player with username "player", email "player@webingo.org" and password "password"
    When I delete a player with username "player"
    Then The response code is 204

  Scenario: Remove player as user
    Given I login as "admin" with password "password"
    And I register a new player with username "player", email "player@webingo.org" and password "password"
    Given I login as "user" with password "password"
    When I delete a player with username "player"
    Then The response code is 403
    And It has not been deleted a player with username "player"

  Scenario: Remove a player without authenticating
    Given I'm not logged in
    When I delete a player with username "player"
    Then The response code is 401
    And It has not been edited a player with username "player"
    
