Feature: Login page
  Background:
    Given User on the login page

  @ui
  Scenario: UI validation
    Then Username field should present
    And Username placeholder should be "Username"
    And Username field should be writable
    And Password field should present
    And Password field placeholder should be "Password"
    And Password field should be writable
    And Login button should present


  @ui
  Scenario: Login button validation
    Then Login button label should be "Login"
    And Login button color should be "rgba(61, 220, 145, 1)"
    And Login button height should be "49"
    And Login button width should be "292"
    And Login button type is "submit"

  Scenario Outline: Login with valid credentials
    When User enter <username> in the username field
    And User enter <password> in the password field
    And User click on the login button
    Then Page url should be "https://www.saucedemo.com/inventory.html"
    And User should see the cart on the page
    Examples:
      | username                  | password       |
      | "standard_user"           | "secret_sauce" |
      | "problem_user"            | "secret_sauce" |
      | "performance_glitch_user" | "secret_sauce" |
      | "error_user"              | "secret_sauce" |
      | "visual_user"             | "secret_sauce" |

  Scenario Outline: Login with invalid credentials
    When User enter <username> in the username field
    And User enter <password> in the password field
    And User click on the login button
    Then User should see the <errorMgs> in the login page
    Examples:
      | username          | password       | errorMgs                                                                    |
      | "standard_user"   | "invalidPass"  | "Epic sadface: Username and password do not match any user in this service" |
      | "standard_user"   | ""             | "Epic sadface: Password is required"                                        |
      | ""                | "secret_sauce" | "Epic sadface: Username is required"                                        |
      | ""                | "invalidPass"  | "Epic sadface: Username is required"                                        |
      | ""                | ""             | "Epic sadface: Username is required"                                        |
      | "invaliduser"     | "secret_sauce" | "Epic sadface: Username and password do not match any user in this service" |
      | "invaliduser"     | ""             | "Epic sadface: Password is required"                                        |
      | "locked_out_user" | "secret_sauce" | "Epic sadface: Sorry, this user has been locked out."                       |

  Scenario: Checking error message remove button
    When User enter "standard_user" in the username field
    And User enter "invalidPass" in the password field
    And User click on the login button
    Then User should see the "Epic sadface: Username and password do not match any user in this service" in the login page
    And User click on the remove button
    Then The error message should disappear
    But Username and password should not be removed from the respective fields