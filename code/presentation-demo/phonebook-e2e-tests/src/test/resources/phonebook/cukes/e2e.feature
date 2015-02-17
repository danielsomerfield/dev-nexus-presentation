Feature: End-to-end Test

  Background: Basic tests of full application functionality

  @wip
  Scenario: Add / View entry
    Given I am not logged in
    And   an entry has been entered into the phone list
    When  I go to the entry list page
    Then  I see the entry in the entry list


  @wip
  Scenario: Basic User View
    Given I am not logged in
    When  I go to the entry list page
    Then  I am unable to delete a user

  @wip
  Scenario: Admin delete and add
    Given I am logged in
    And   an entry has been entered into the phone list
    When  I go to the entry list page
    Then  I am able to delete the user