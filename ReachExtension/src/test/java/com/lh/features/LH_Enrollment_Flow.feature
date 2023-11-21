@test2
Feature: LH_Enrollment_Flow

  Scenario Outline: <Testcase>
    Given User launches lufthansa web application
    And User verify the home page <ProductName>
    And User clicks on "Login"
    And User clicks on "Register_Travel_ID"
    And User fills "Registration" form
      | Email_address | red025@gmail.com |
      | password      | Test@123         |
    And User clicks on "Continue"
    And User clicks on the "Connect_later"
    And User fills "Personal_Details" form
      | Salutation    | Mr         |
      | FirstName     | TestUSer   |
      | LastName      | ADT        |
      | Date_of_Birth | 10/05/2001 |
    And User clicks on "Continue"
    And User accept "consent"
   # When User clicks on "Confirm_and_continue"
    Then User login to UserAccount
    And clicks on activation_link

    Examples:
      | Testcase           | ProductName | Trip_type | Source_city | Destination_city | Date         | travel_class_val | fare_class_val | preferred_seat | Title | First_name | Last_name | Phone_Number | Email_Address      | Country_calling_Code | Card_type |
      | LH_Enrollment_Flow | Swiss       | One-way   | MUC         | DXB              | 22 June 2023 | Economy          | Economy Basic  | 18A            | Mr    | Test User  | ADT       | 1234567893   | red025@yopmail.com | Germany              | Visa      |