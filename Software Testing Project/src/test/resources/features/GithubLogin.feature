Feature: GitHub Repository Status Verification

  Background:
    Given I open GitHub
    And I log in with valid credentials

  Scenario: Verify repository details and status
    When I navigate to the "Repositories" section in my profile
    And I search for the repository named "SampleProject"
    Then I should see it listed with visibility as "Public" or "Private"
    And the last activity date displayed

  Scenario: Sign out after verification
    When I complete repository checks
    And click "Sign out" in the profile menu
    Then I should be logged out successfully
