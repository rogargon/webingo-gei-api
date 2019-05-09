Feature: Register Game
  In order to allow a new game to use the app
  As an admin
  I want to register a new player account

  Scenario: Register new game as admin
    Given I login as "admin" with password "password"
    When I register a new game with id "1" and pricePerCard "4.00"
    Then The response code is 201
    And It has been created a game with id "1"
    And The game is registered by "admin"

  Scenario: Register new game as player
    Given I login as "player" with password "password"
    When I register a new game with id "1" and pricePerCard "5.00"
    Then The response code is 401
    And It has not been created a game with id "1"

  Scenario: Try to register new game without authenticating
    Given I'm not logged in
    When I register a new game with id "1" and pricePerCard "5.00"
    Then The response code is 401
    And It has not been created a game with id "1"

  Scenario: Register new game as admin with specific pricePerCard
    Given I login as "admin" with password "password"
    When I register a new game with id "1" and pricePerCard "5.0"
    Then The response code is 201
    And It has been created a game with id "1"

  Scenario: Register new game as admin with negative pricePerCard
    Given I login as "admin" with password "password"
    When I register a new game with id "1" and pricePerCard "-1.0"
    Then The response code is 400
    And The error message is "Bad Param"
    And It has not been created a game with id "1"

  Scenario: Register new game as admin and status differs from LOADING
    Given I login as "admin" with password "password"
    When I register a new game with id "1" and status "PLAYING"
    Then The response code is 201
    And It has been created a game with id "1" and status as "LOADING"

  Scenario: Register new game with start date and end date
    Given I login as "admin" with password "password"
    When I register a new game with id "1", pricePerCard "4.0", start date "2018-06-04" and finish date "2018-09-04" at  "14:04:13"
    Then The response code is 201
    And  A game with the id 1 has been register
    And The game is registered by "admin"
