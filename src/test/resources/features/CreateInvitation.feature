Feature: Create Invitation
  In order to allow a player to send invitation to another player
  As an user
  I want to send invitations


  Scenario: Create invitation
    Given I login as "user" with password "password"
    When I create an invitation with message "asd"
    Then The response code is 201
    And Exists an invitation with message "asd"

  Scenario: Create invitation while not logged in
    Given I'm not logged in
    When I create an invitation with message "asd"
    Then The response code is 401
    And And it doesn't exist an invitation with message "asd"