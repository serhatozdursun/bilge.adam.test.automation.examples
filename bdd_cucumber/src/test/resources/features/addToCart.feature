Feature: Add Product to Cart

  Rule: Product should be available

    @addToCard
    Scenario: Adding product to cart
    As customer I want to add selected product to card
      Given On HB home page
      When I search "tablet"
      Then All "tablet" related products should be listed
      When Sort from lowest to highest price
      And Click 1 product





