Feature: Sign Up page

  Background:
    Given User lunch browser and navigate to the Web Page
    And User verify the Home Page load successfully

  @TC-0001 @Pass
  Scenario: 1 - New user can buy a laptop
    When User click on 'SignUp' button
    And User enters the Username credential in SignUp Username
    And User enters the Password credential in SignUp Password
    And User click on 'SignUpAccept' button
    And User click on Ok button pop up
    And User click on 'Log In' button
    And User enters the Username credential in Login Username
    And User enters the Password credential in Login Password
    And User click on 'Login Accept' button
    Then User sees the 'Welcome' text
    When User selects the Laptop Sony Vaio i5
    And User click on 'Add To Cart' button
    And User click on Ok button pop up
    And User click on 'Cart' button
    Then User sees the 'Sony vaio i5' text
    
