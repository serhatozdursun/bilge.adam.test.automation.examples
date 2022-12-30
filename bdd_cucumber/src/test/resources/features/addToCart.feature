Feature: Add Product to Cart

  Rule: Product should be available

    @addToCard
    Scenario: Adding product to cart
    As customer I want to add selected product to card
      Given On HB home page
      When I search "Samsung tv"
      Then All "Samsung tv" related products should be listed
      When Sort from lowest to highest price
      And Click 3 product




    @addToCard
    Scenario Outline: Adding product to cart - data driven
    As customer I want to add selected product to card
      Given On HB home page
      When I search "<product>"
      Then All "<product>" related products should be listed
      When Sort from lowest to highest price
      And Click <index> product
      Examples:
        | product    | index |
        | Samsung tv | 1     |
        | Tablet     | 2     |
        | IPhone     | 1     |






