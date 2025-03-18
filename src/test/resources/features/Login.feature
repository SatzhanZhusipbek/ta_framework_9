Feature: User Login

  Background:
    Given the user is on the login page

  Scenario Outline: Login with valid credentials
    When the user enters "<username>" and "<password>"
    And the user clicks the login button
    Then the user should see the inventory page

  Examples:
    | username       | password     |
    | standard_user  | secret_sauce |

  Scenario Outline: Login with invalid credentials
    When the user enters invalid "<username>" and "<password>"
    And the user clicks the login button
    Then the user should see the error message

  Examples:
    |username    | password  |
    |non_user    | secret    |
