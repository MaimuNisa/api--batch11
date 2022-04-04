Feature: Test all pet operations
  Scenario: Create pet
    Given I have valid url to create post
    When  I send Post request to create a pet
    Then  status code should be 200
    And response should be in json format