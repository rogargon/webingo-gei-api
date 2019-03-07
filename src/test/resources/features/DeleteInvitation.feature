Feature: Delete Invitation
  In order to allow a player to delete an invitation previously sended to another player
  As an user
  I want to delete invitations

  Scenario: Delete owned invitation
    Given I login as "player" with password "password"
    When I send an invitation with message "asd"
    Then There are 1 invitations created
    When I delete the invitation with message "asd"
    Then The response code is 204
    And There are 0 invitations created