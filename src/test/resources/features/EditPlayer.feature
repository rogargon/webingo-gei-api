Feature: Edit Player
  In order to allow edit player
  As an admin
  I want to edit a player account

  Scenario: Edit player as admin
    Given I login as "admin" with password "password"
    And I register a new player with username "player", email "player@webingo.org" and password "password"
    When I edit player with username "player" a new email "player_1@webingo.org" and password "password"
    Then The response code is 200
    And It has been edited a player with username "player" and email "player_1@webingo.org", the password is not returned

  Scenario: Edit player as user
    Given I login as "admin" with password "password"
    And I register a new player with username "player", email "player@webingo.org" and password "password"
    Given I login as "user" with password "password"
    When I edit player with username "player" a new email "player_1@webingo.org" and password "password"
    Then The response code is 200
    And It has not been edited a player with username "player"

  Scenario: Edit a player without authenticating
    Given I'm not logged in
    When I edit player with username "player" a new email "player_2@webingo.org>" and password "password"
    Then The response code is 401
    And It has not been edited a player with username "player"

  Scenario: Edit player with invalid email
    Given I login as "admin" with password "password"
    And I register a new player with username "player", email "player@webingo.org" and password "password"
    When I edit player with username "player" and a new email "playerawebingo.org"
    Then The response code is 400
    And The error message is "must be a well-formed email address"
    And It has not been edited a player with username "player"

  Scenario: Edit player with invalid password
    Given I login as "admin" with password "password"
    And I register a new player with username "player", email "player@webingo.org" and password "password"
    When I edit player with username "player" and a new password "123"
    Then The response code is 400
    And The error message is "length must be between 8 and 256"
    And It has not been edited a player with username "player"

  Scenario: Edit player with invalid email and invalid password
    Given I login as "admin" with password "password"
    And I register a new player with username "player", email "player@webingo.org" and password "password"
    When I edit player with username "player" a new email "playerawebingo.org" and password "1234"
    Then The response code is 400
    And The error message is "must be a well-formed email address"
    And The error message is "length must be between 8 and 256"
    And It has not been edited a player with username "player"

  Scenario: Edit player with correct password
    Given I login as "admin" with password "password"
    And I register a new player with username "player", email "player@webingo.org" and password "password"
    Given I login as "user" with password "password"
    When I edit player with username "player" and a new password "123456789"
    Then The response code is 200
    And It has been edited a player with username "player" and email "player@webingo.org", the password is not returned
