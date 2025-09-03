Feature: Sample feature
  Scenario: Open home and check title
    Given I open the application
    Then page title should contain "Example Domain"