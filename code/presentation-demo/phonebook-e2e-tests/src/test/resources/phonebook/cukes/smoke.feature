Feature: Smoke Test

  Background: Make sure all basic service for the phone book system are up

  Scenario: Health check responding
    When I ping the health check
    Then I see a healthy response

  Scenario: The application UI shows up
    When I go to the application UI home
    Then I see the application main page

#  @wip
#  Scenario: Database is healthy

