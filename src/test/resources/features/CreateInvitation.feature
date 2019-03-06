Feature: Create Invitation
  In order to allow a player to send invitation to another player
  As an user
  I want to send invitations

  Scenario: Send invitation to registered player
    Given I login as "admin" with password "password"
    When I register a new player with username "player", email "player@webingo.org" and password "password"
    Then The response code is 201
    And It has been created a player with username "player" and email "player@webingo.org", the password is not returned
