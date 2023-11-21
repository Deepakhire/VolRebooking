@test
Feature: flight_Booking


  Scenario Outline: flight_Booking_Flow
    Given User launches lufthansa web application
    And User verify the home page <ProductName>
    When User selects <Trip_type> in the "trip_Type"
    And User enters <Source_city> in the "origin_Field"
    And User enters <Destination_city> in the "destination_Field"
    And User selects <Date> as the "journey_Date"
#    And User selects checkbox "One_way"
    And User clicks on "continue_Button"
    And User clicks on "travel_Details"
    And User selects <travel_class_val> from "travel_Class"
#    And User selects <No_of_passenger> as the "passenger_count"
    And User clicks on "continue_travelDetails"
    And User clicks on the "Search_flight_button"
    And User selects "flight" on "Flight_selection" page
    And User selects <fare_val> from "fare_Option"
    And User clicks on the "confirm_and_continue"
    And User clicks on the "Select_seats"
#   And user selects "E" as the cabin type
    And User selects <preferred_seat_number> from "seat_Map"
    And User verify presence of "total_Price_For_Seat"
    And User clicks on the "Add_to_shopping_cart"
#    And User verify presence of "introduction_message"
#    And User clicks on the "Add_baggage"
#    And User selects checkbox "Additional_baggage"
#    And User verify presence of "total_Price_For_Baggage"
#    And User clicks on the "Add_to_shopping_cart"
#    And User verify presence of "total_Price_E2E"
#    And User verify presence of "introduction_message"
    And User clicks on the "continueToAddBaggage"
    And User clicks on "No_proceed_without_login"
    And User selects <Title> from "Title_dropdown"
    And User enter <First_name> in "firstName"
#    And user enters <Middle_name> in "middleName" text field
    And User enter <Last_name> in "lastName"
    And User enter <Phone_Number> in "phoneNumber"
    And User enter <Email_address> in "emailAddress"
    And User clicks on the "continue"
    And User selects <Payment_type> in the "payment_option"
    And User selects <Card_type> from "cardType_options"
    And User enter <Card_Number> in "cardNumberField"
    And User select value <Expiry_Date> from "expiryDate"
    And User enter <Pin> in "secure_Pin"
    And User enters <Address> details in "billingDetail"
    And User selects checkbox "terms_and_condition"
    And User clicks on the "Yes_book_and_pay_for_ticket"
#    And User enter <OTP> in "Purchase_Authentication"
#    Then User clicks on "Submit"
    And User enter <Pass_Code> in "Card_Authentication"
#    Then User clicks on the "Do_Authentication"
    Then User clicks on "BackToMerchant"
    And User verify presence of "Booking_confirmation_message"
    And User captures <Booking_Details> of "Booking_confirmation"


    Examples:
      | ProductName | Trip_type | Source_city | Destination_city | Date         | travel_class_val | fare_val      | preferred_seat_number | Title | First_name | Last_name | Phone_Number | Email_address      | Payment_type | Card_type | Card_Number      | Expiry_Date | Pin | Address          | Pass_Code | Booking_Details  |
      | Swiss       | One-way   | MUC         | DXB              | 22 June 2023 | Economy          | Economy Basic | 21B                   | MR    | Test User  | ADT       | 1234567893   | red025@yopmail.com | CreditCard   | Visa      | 4000000000001091 | 12/2023     | 123 | Abc/Berlin/10115 | 123       | message/code/PNR |