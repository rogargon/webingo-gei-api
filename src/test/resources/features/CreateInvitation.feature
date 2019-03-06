Feature: Create Invitation
  In order to allow a player to send invitation to another player
  As an user
  I want to send invitations

  Scenario: Send invitation to registered player
    Given I login as "user" with password "password"
    And There is an user "user2" with password "password"
    When I send an invitation to "user2"
    Then The response code is 201

  Scenario: Create invitation
    Given I login as "user" with password "password"
    When I send an invitation with message "asd"
    Then The response code is 201
    And Exists an invitation with