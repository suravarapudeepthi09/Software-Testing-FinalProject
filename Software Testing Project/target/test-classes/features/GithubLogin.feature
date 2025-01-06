Feature: GitHub Repository Status Verification

    Scenario: Verify GitHub repositories' status
      Given I open GitHub
      And I log in with valid credentials
      When I navigate to repository page
      Then I should sign out from GitHub