Feature: Register Game
  In order to allow a new game to use the app
  As an admin
  I want to register a new player account

  Scenario: Register new game as admin GOOD!
    Given I login as "admin" with password "password"
    When I register a new game with id "1"
    Then The response code is 201
    And It has been created a game with id "1"

  Scenario: Register new game as player GOOD!
    Given I login as "player" with password "password"
    When I register a new game with id "1"
    Then The response code is 401
    And It has not been created a game with id "1"

  Scenario: Register new game without authenticating GOOD!
    Given I'm not logged in
    When I register a new game with id "1"
    Then The response code is 401
    And It has not been created a game with id "1"

  Scenario: Register new game as admin with free pricePerCard GOOD! (depends on free pricepercard option)
    Given I login as "admin" with password "password"
    When I register a new game with id "1" and pricePerCard "0.0"
    Then The response code is 201
    And It has been created a game with id "1"

  Scenario: Register new game as admin with specific pricePerCard GOOD!
    Given I login as "admin" with password "password"
    When I register a new game with id "1" and pricePerCard "5.0"
    Then The response code is 201
    And It has been created a game with id "1"

  Scenario: Register new game as admin with negative pricePerCard GOOD!
    Given I login as "admin" with password "password"
    When I register a new game with id "1" and pricePerCard "-1.0"
    Then The response code is 400
    And The error message is "Bad Param"
    And It has not been created a game with id "1"
    
  Scenario: Register new game as admin and status differs from LOADING GOOD!
    Given I login as "admin" with password "password"
    When I register a new game with id "1" and status "PLAYING"
    Then The response code is 201
    And It has been created a game with id "1" and status as "LOADING"