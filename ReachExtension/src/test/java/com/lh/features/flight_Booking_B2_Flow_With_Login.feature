@B2_login @test
Feature: flight_Booking_B2_with_login

  Scenario Outline: flight_Booking_B2_flow_with_login
#    Given User launches "swiss" web application
    Given User launches "<app_type>" web application
    And User verify the home page <ProductName>
    And User clicks on "Login"
    And User enters login credentials
      | Email_address | red025@yopmail.com |
      | password      | Test@123           |
    When User clicks on "Log_in"
    And User get logged_in with "user_Profile_Name"
    And User clicks on "Flights_Tab"
    Then User selects <Trip_type> in the "trip_Type"
    And User enters <Source_city> in the "origin_Field"
    And User enters <Destination_city> in the "destination_Field"
    And User selects <Date> as the "journey_Date
    And User clicks on "continue_Button"
    And User clicks on "travel_Details"
    And User selects <travel_class_val> from "travel_Class"
#    And User selects <No_of_passenger> as the "passenger_count"
    And User clicks on "continue_travelDetails"
    And User clicks on the "Search_flight_button"
    And User selects "flight" on "Flight_selection" page
    And User selects <fare_class_val> from "fare_class"
    And User clicks on the "confirm_and_continue"
    And User verify presence of "total_Price_For_Flight"
    And User clicks on the "enter_Passenger_Details"
    And User enter Passenger details
      | Title_dropdown | Mr                 |
      | firstName      | Test User          |
      | lastName       | ADT                |
      | emailAddress   | red025@yopmail.com |
      | countryCode    | Germany            |
      | phoneNumber    | 1234567893         |
    And User clicks on the "confirm"
    And User clicks on the "Continue_to_payment"
    And User chooses "saved_payment_Type" as Payment Method
    And User enter Credit Card details
      | Security_Code_fop | 1234 |
#    Steps for not logged in user
#    And User chooses "payment_Type_CC" as Payment Method
#    And User enter Credit Card details
#      | CCard_Type       | American Express |
#      | Card_number      | 340000000001098  |
#      | Card_Holder_Name | Testuser         |
#      | Card_Expiry      | 12/23            |
#      | Security_Code    | 1234             |
#      | Street_Address   | ABC              |
#      | Postal_Code      | 11056            |
#      | City             | Berlin           |
#      | Country          | Germany          |
    And User clicks on "TermsAndCondition"
    And User clicks on the "PayNow"
#    Then User performs "card_Authentication"
    And User verify presence of "Booking_reference" on Booking Confirmation Page
    And User verify presence of "Booking_confirmation_message" on Booking Confirmation Page
    And User captures Booking_confirmation details
      | Booking_Details | message/reference/itenary |

    Examples:
      | ProductName | app_type | Trip_type | Source_city | Destination_city | Date            | travel_class_val | fare_class_val | preferred_seat | Title | First_name | Last_name | Phone_Number | Email_Address      | Country_calling_Code | Card_type |
      | Swiss       | swiss    | One-way   | FRA         | DXB              | 22 October 2023 | Economy          | Economy Basic  | 18A            | Mr    | Test User  | ADT       | 1234567893   | red025@yopmail.com | Germany              | Visa      |