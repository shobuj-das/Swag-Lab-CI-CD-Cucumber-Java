Feature: Product Page Validation
  Background:
    Given User on the login page
    And User enter "standard_user" in the username field
    And User enter "secret_sauce" in the password field
    And User click on the login button
#    Given User on the login page
#    And User login with valid credentials


  @ui
  Scenario: UI Validation
    Then Product page header should be "Swag Labs"
    And Page title should be "Swag Labs"
    And Current url should be "https://www.saucedemo.com/inventory.html"
    And Menu bar should be displayed
    And Menu bar should be clickable
    And Cart should be displayed
    And Cart should be clickable
    And All products should be displayed

  Scenario: Adding products to the cart
    When User add first product to the cart
    Then The digit "1" should be visible on the cart logo
    And User add second product to the cart
    Then The digit "2" should be visible on the cart logo


  Scenario: Adding and removing items from cart
    When User add first product to the cart
    Then The digit "1" should be visible on the cart logo
    And User add second product to the cart
    Then The digit "2" should be visible on the cart logo
    And User remove first product from the cart
    Then The digit "1" should be visible on the cart logo

  Scenario: Product sort option should be selected A to Z by default
    Then Product sort option should be selected "Name (A to Z)"


  Scenario: Validate all products are shown in Descending order when select Name (Z to A)
    When User select "Name (Z to A)" from the sort products
    Then User should see all products in descending order according to product name

  Scenario: Validate all products are shown in Ascending order when select Name (A to Z)
    When User select "Name (A to Z)" from the sort products
    Then User should see all products in ascending order according to product name

  Scenario: Validate all products are shown in Descending order when select Price (low to high)
    When User select "Price (low to high)" from the sort products
    Then User should see all products in low to high price order

  Scenario: Validate all products are shown in Descending order when select Price (high to low)
    When User select "Price (high to low)" from the sort products
    Then User should see all products in high to low price order