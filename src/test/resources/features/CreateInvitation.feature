Feature: Create Invitation
  In order to allow a player to send invitation to another player
  As an user
  I want to send invitations


  Scenario: Create invitation
    Given I login as "user" with password "password"
    When I create an invitation with message "asd"
    Then The response code is 201
    And Exists an invitation with message "asd"