Feature: Hepsiburada Test Automation

 Background: The user go to home page and login
   Given user go to website
   And user click to login button
   And user login to the site

  @product-like @all
  Scenario: The user searches for the given product and goes to the details of a random product and likes it
    When user search "samsung" and search button
    And user selects product from list and goes to product page
    Then user likes the product on the product detail page


    @product-add-cart @all
    Scenario: The user selects a random product under a random category, adds it to the cart and checks the price.
      When selects a random category and clicks on the subcategory under the category
      And user selects product from list and goes to product page
      And user checks the price of the product on the product detail page
      And user adds the product to the cart
      Then user goes to the cart and checks the price in the cart with the price on the product detail page