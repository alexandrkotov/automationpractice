@automationpractice
Feature: Practice With automationpractice.com

  Background:
    Given I open url "http://automationpractice.com/index.php" and verify title contains "My Store"
    Given I set email as "info20@mail.com" and password as "123456" for further scenarios

  @automationpractice1
  Scenario: Fill Out and Validate “Search” Field.
    When I have empty search field and click on magnifier button
    And I wait for 2 seconds
    Then message "Please enter a search keyword" should be appear on the page
    And I wait for 1 seconds
    When I type "Blouse" in search field and click on magnifier button
    And I wait for 2 seconds
    Then a non-empty search result should appear
    And I wait for 1 seconds
    When I type "Noitem" in search field and click on magnifier button
    And I wait for 2 seconds
    Then message "No results were found for your search" should be appear on the page
    And I wait for 3 seconds

  @automationpractice2
  Scenario Outline: Validate ”Create an Account” Behavior - Negative Test
    When I click on "Sign in" in top of the page
    And I wait for 1 seconds
    Then authentication page should be appear
    When I type <email> into email field and click on [Create an account] button
    And I wait for 2 seconds
    Then error message <errorMessage> should be appear
    Examples:
      | email            | errorMessage             |
      | ""               | "Invalid email address." |
      | "@"              | "Invalid email address." |
      | "info@"          | "Invalid email address." |
      | "info@mail"      | "Invalid email address." |
      | "info@mail."     | "Invalid email address." |
      | "info@ mail.com" | "Invalid email address." |
      | "info @mail.com" | "Invalid email address." |
      | " info@mail.com" | "Invalid email address." |

  @automationpractice3
  Scenario: Validate ”Create an Account” Behavior - Positive Test
    When I click on "Sign in" in top of the page
    And I wait for 1 seconds
    Then authentication page should be appear
    When I type "emailGiven" into email field and click on [Create an account] button
    And I wait for 3 seconds
    Then registration form should be displayed
    And I wait for 1 seconds
    And I put all necessary information into registration form: "Uma", "Thurman", "passGiven", "300 SE 200 Ave", "Vancouver", "Washington", "98600", "United States", "3601234567"
    And I wait for 1 seconds
    Then welcome to an account page should be appear

  @automationpractice4
  Scenario: Validate ”Create an Account” Behavior - Verifying that email field does not accept email addresses that have already been registered
    When I click on "Sign in" in top of the page
    And I wait for 1 seconds
    Then authentication page should be appear
    When I type "emailGiven" into email field and click on [Create an account] button
    And I wait for 3 seconds
    Then error message "An account using this email address has already been registered." should be appear

  @automationpractice5
  Scenario Outline: Verifying “Password” Set of Fields - Negative Test
    When I click on "Sign in" in top of the page
    And I wait for 2 seconds
    Then authentication page should be appear
    When I type "emailGiven" in email field of sign in form
    And I type <pass> in password field of sign in form and click on [Sign in] button
    And I wait for 2 seconds
    Then sign in error message <errorMessage> should be appear
    Examples:
      | pass    | errorMessage            |
      | "1"     | "Invalid password"      |
      | "12"    | "Invalid password"      |
      | "123"   | "Invalid password"      |
      | "1234"  | "Invalid password"      |
      | "12345" | "Authentication failed" |

  @automationpractice6
  Scenario: Verifying “Password” Set of Fields - Positive Test
    When I click on "Sign in" in top of the page
    And I wait for 2 seconds
    Then authentication page should be appear
    When I type "emailGiven" in email field of sign in form
    And I type "passwordGiven" in password field of sign in form and click on [Sign in] button
    Then welcome to an account page should be appear

  @automationpractice7
  Scenario: Verifying Required Fields Behavior
    When I click on "Sign in" in top of the page
    And I wait for 2 seconds
    Then authentication page should be appear
    # Creating an account with empty email
    When I type "" into email field and click on [Create an account] button
    And I wait for 3 seconds
    Then error message "Invalid email address" should be appear
    And I wait for 1 seconds
    # Login with empty email, given email and empty password
    When I type "" in email field of sign in form
    And I type "" in password field of sign in form and click on [Sign in] button
    And I wait for 2 seconds
    Then sign in error message "An email address required" should be appear
    And I wait for 1 seconds
    When I type "emailGiven" in email field of sign in form
    And I type "" in password field of sign in form and click on [Sign in] button
    And I wait for 2 seconds
    Then sign in error message "Password is required" should be appear
    # Field out the registration form
    When I type "emailGivenExtra" into email field and click on [Create an account] button
    And I wait for 4 seconds
    Then registration form should be displayed
    #
    When I have empty field "First name" and  click [Register] button
    Then error message containing ["firstname" is required] should be appear above the registration form
    And I wait for 1 seconds
    And I type "Uma" in the field "First name"
    And I wait for 1 seconds
    #
    When I have empty field "Last name" and  click [Register] button
    Then error message containing ["lastname" is required] should be appear above the registration form
    And I wait for 1 seconds
    And I type "Thurman" in the field "Last name"
    And I wait for 1 seconds
    #
    When I have empty field "Password" and  click [Register] button
    Then error message containing ["passwd" is required] should be appear above the registration form
    And I wait for 1 seconds
    And I type "passwordGiven" in the field "Password"
    And I wait for 1 seconds
    #
    When I have empty field "Address" and  click [Register] button
    Then error message containing ["address1" is required] should be appear above the registration form
    And I wait for 1 seconds
    And I type "300 SE 200th Ave" in the field "Address"
    And I wait for 1 seconds
    #
    When I have empty field "City" and  click [Register] button
    Then error message containing ["city" is required] should be appear above the registration form
    And I wait for 1 seconds
    And I type "Vancouver" in the field "City"
    #
    When I have empty field "Zip/Postal Code" and  click [Register] button
    Then error message containing ["Postal code" is required] should be appear above the registration form
    And I wait for 1 seconds
    And I type "98600" in the field "Zip/Postal Code"
    #
    When I have empty field "Mobile phone" and  click [Register] button
    Then error message containing ["one phone number" is required] should be appear above the registration form
    And I wait for 1 seconds
    And I type "3601234567" in the field "Mobile phone"
    #
    When I have empty field "Assign an address alias for future reference" and  click [Register] button
    Then error message containing ["alias" is required] should be appear above the registration form
    And I wait for 1 seconds
    And I type "300 SE 200th Ave, Vancouver WA" in the field "Assign an address alias for future reference"
    #
    When I clear country in drop down list and click [Register] button
    Then error message containing "Country is invalid" should be appear above the registration form
    And I wait for 1 seconds
    And I choose [United States] in country drop down list
    #
    When I clear state in drop down list and click [Register] button
    Then error message containing "This country requires you to choose a State" should be appear above the registration form
    And I wait for 1 seconds
    And I choose [Washington] in state drop down list
    And I wait for 1 seconds

  @automationpractice8
  Scenario: Submitting the Form and Verifying the Data on My Account Page: My Address and My Personal Information data
    When I click on "Sign in" in top of the page
    And I wait for 2 seconds
    Then authentication page should be appear
    # creating data
    When I type "emailGivenExtra" into email field and click on [Create an account] button
    And I wait for 3 seconds
    Then registration form should be displayed
    And I put all necessary information into registration form: "Uma", "Thurman", "passGiven", "301 SE 201 Ave", "Vancouver", "Washington", "98600", "United States", "3601234567"
    And I wait for 1 seconds
    Then welcome to an account page should be appear
    And I wait for 3 seconds
    # verifying data
    When I click "MY ADDRESSES" button in my account
    And I wait for 3 seconds
    Then page with my addresses should be appear
    And I wait for 1 seconds
    And "Uma" should be on the page
    And "Thurman" should be on the page
    And "301 SE 201 Ave" should be on the page
    And "Vancouver" should be on the page
    And "Washington" should be on the page
    And "98600" should be on the page
    And "United States" should be on the page
    And "3601234567" should be on the page
    And I wait for 2 seconds
    When I click [Back to your account] button
    And I wait for 3 seconds
    When I click "MY PERSONAL INFORMATION" button in my account
    And I wait for 3 seconds
    Then page with my personal information should be appear
    And "Uma" should be on this page
    And "Thurman" should be on this page
    And "emailGivenExtra" should be on this page




