Feature: Register Game
  In order to allow a new game to use the app
  As an admin
  I want to register a new player account

  Scenario: Register new game as admin
    Given I login as "admin" with password "password"
    When I register a new game with id "1" and pricePerCard "2.00"
    Then The response code is 201
    And It has been created a game with id "1"

  Scenario: Register new game as user
    Given I login as "player" with password "password"
    When I register a new game with id "1" and pricePerCard "2.00"
    Then The response code is 401
    And It has not been created a game with id "1"

  Scenario: Try to register new game without authenticating
    Given I'm not logged in
    When I register a new game with id "1" and pricePerCard "2.00"
    Then The response code is 401
    And It has not been created a game with id "1"

  Scenario: Register new game with free pricePerCard
    Given I login as "admin" with password "password"
    When I register a new game with id "1" and pricePerCard "0.0"
    Then The response code is 500
    And The error message is "Price per card can not be negative or 0.0"
    And It has not been created a game with id "1"

  Scenario: Register new game with negative pricePerCard
    Given I login as "admin" with password "password"
    When I register a new game with id "1" and pricePerCard "-1.0"
    Then The response code is 500
    And The error message is "Price per card can not be negative or 0.0"
    And It has not been created a game with id "1"


  Scenario: Register game with status "PLAYING"
    Given I login as "admin" with password "password"
    When I register a new game with id "1" and status "PLAYING"
    Then The response code is 401
    And The error message is "Not allowed to register new game with status playing"

  Scenario: Register game with status "FINISHED"
    Given I login as "admin" with password "password"
    When I register a new game with id "1" and status "FINISHED"
    Then The response code is 401
    And The error message is "Not allowed to register new game with status finished"
    
  Scenario: Register game with status "LOADING"
    Given I login as "admin" with password "password"
    When I register a new game with id "1" and status "LOADING"
    Then The response code is 200