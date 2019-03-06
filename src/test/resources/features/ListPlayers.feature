Feature: List players
  In order to allow the admin to get the players
  As an admin
  I want to list the players

  Scenario: Try to list players without authenticating
    Given I'm not logged in
    When I list players
    Then The response code is 401