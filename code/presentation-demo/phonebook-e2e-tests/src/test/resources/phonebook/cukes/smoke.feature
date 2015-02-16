Feature: Smoke Test

  Background: Make sure all basic service for the phone book system are up

  Scenario: Health check responding
    When I ping the health check
    Then I see a healthy response

#  @wip
#  Scenario: Application is healthy
#
#  @wip
#  Scenario: Database is healthy

