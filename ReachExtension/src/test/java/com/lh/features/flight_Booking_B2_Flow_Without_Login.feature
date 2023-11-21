@B2
Feature: flight_Booking_B2

  Scenario Outline: flight_Booking_Flow
    Given User launches "swiss" web application
    And User verify the home page <ProductName>
    When User selects <Trip_type> in the "trip_Type"
    And User enters <Source_city> in the "origin_Field"
    And User enters <Destination_city> in the "destination_Field"
    And User selects <Date> as the "journey_Date
    And User clicks on "continue_Button"
    And User clicks on "travel_Details"
    And User selects <travel_class_val> from "travel_Class"
#    And User selects <No_of_passenger> as the "passenger_count"
    And User clicks on "continue_travelDetails"
    And User clicks on the "Search_flight_button"

    #And User verify "date with total_Price_For_Seat"
    And User selects "flight" on "Flight_selection" page
    And User selects <fare_class_val> from "fare_class"
    And User clicks on the "confirm_and_continue"
    And User verify presence of "total_Price_For_Flight"
    And User clicks on the "enter_Passenger_Details"

   # And User enters <Title> in "title"
    And User selects <Title> from "Title_dropdown"
    And User enter <First_name> in "firstName"
    And User enter <Last_name> in "lastName"
#    And User enter <Miles & More or other program> in "miles_&_more_or_other_program"
#    And User enter <Frequent flyer number> in "frequent_flyer_number"
    And User enter <Email_Address> in "emailAddress"
    And User enters <Country_calling_Code> in the "countryCode"
    And User enter <Phone_Number> in "phoneNumber"
    And User clicks on the "confirm"
    And User clicks on the "Continue_to_payment"

#    And User clicks on the "Select_Your_Seat"
###  And User selects <preferred_seat> from "seat_Map"
#    And User select <preferred_seat> from "seat_Map"
#    And User clicks on the "Confirm_and_Continue"
#    And User verify presence of "total_Price_For_Seat"
#    And User clicks on the "confirm"
#    And User clicks on the "Continue_to_payment"
    And User chooses "payment_Type_CC" as Payment Method
    And User enter Credit Card details
      | CCard_Type       | American Express |
      | Card_number      | 340000000001098  |
      | Card_Holder_Name | Testuser         |
      | Card_Expiry      | 12/23            |
      | Security_Code    | 1234             |
      | Street_Address   | ABC              |
      | Postal_Code      | 11056            |
      | City             | Berlin           |
      | Country          | Germany          |
    And User clicks on "TermsAndCondition"
    And User clicks on the "PayNow"
    Then User performs "card_Authentication"
    And User verify presence of "Booking_reference" on Booking Confirmation Page
    And User verify presence of "Booking_confirmation_message" on Booking Confirmation Page
    And User captures Booking_confirmation details
      | Booking_Details | message/reference/itenary |

    Examples:
      | ProductName | Trip_type | Source_city | Destination_city | Date         | travel_class_val | fare_class_val | preferred_seat | Title | First_name | Last_name | Phone_Number | Email_Address      | Country_calling_Code | Card_type |
      | Swiss       | One-way   | FRA         | DXB              | 22 July 2023 | Economy          | Economy Basic  | 18A            | Mr    | Test User  | ADT       | 1234567893   | red025@yopmail.com | Germany              | Visa      |