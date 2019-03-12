Feature: Delete Invitation
  In order to allow a player to delete an invitation previously sended to another player
  As an user
  I want to delete invitations

  Scenario: Delete owned invitation
    Given I login as "user" with password "password"
    And There is an invitation with message "asd"
    When I delete the previously created invitation
    Then The response code is 204
    And There are 0 invitations created