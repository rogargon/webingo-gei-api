Feature: List Invitation
  In order to allow players to list their invitations
  As a player
  As an admin
  I want to list invitations


  Scenario: List owner's invitation
    Given I login as "user" with password "password"
    And There is an invitation with message "asd" by user "user"
    When I list the invitations
    Then The response contains one invitation with message "asd"
