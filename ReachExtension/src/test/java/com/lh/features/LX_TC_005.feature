@LX_TC_005
Feature: LX_ManageBooking_5

  Scenario Outline: <Testcase>
    Given User launches "<app_type>" web application
    And User enters "credential" in login page
    And User clicks on "Home"
    And User clicks on "LX_Tenant"
    And User clicks on "Booking_availability"
    And User clicks on "select_cabin"
    And User clicks on "Economy_button"
    And User enters "origin_city" in "Origin_City"
    And User clicks on "Ocity"
    And User enters "destination_city" in "Destination_City"
    And User clicks on "Dcity"
    And User clicks on "Departure_Date"
    And User clicks on "date"
    And User clicks on "Return_Date"
    And User clicks on "Date"
    And User clicks on "Checkbox"
    And User handles Privacy Settings Page and proceeds with booking
    And User opens the Amadeus Agate Picker
    And User clicks on "LX_Tenant"
    And User clicks on "Manage_Booking_RefX"
    And User enters "record_locator" in "Record_Locator"
    And User enters "last_name" in "last_Name"
    And User clicks on "check_box"
#    And User clicks on "submit_btn"
#    And User clicks on submit button
    And User handles Privacy Settings Page and proceeds with LX booking
#    And User clicks on "Change_Flights"
#    And User clicks on "LHG_Debug_Panel"
#    And User clicks on "Feature_flag_custserv_3464"
#    And User clicks on "Apply"
#    And User clicks on "Outbound_Flight"
#    And User clicks on "date"
#    And User clicks on "required_Outbound_Date"
#    And User clicks on "Search"
#    And User clicks on "LHG_Debug_Panel"
#    And User clicks on "flag_Custserv_2616"
#    And User clicks on "Apply"
#    And User clicks on "Original_Flight_details"
#    And User clicks on "Economy_Class"
#    And User clicks on "Economy_Basic_Fare"
#    And User clicks on "continue_To_payment"
#    And User clicks on "Credit/Debit_Card"
#    And User clicks on "creadit_card_tyoe"
#    And User clicks on "Visa"
#    And User enters "card_number" in "Card_Number"
#    And User enters "cardholders_full_name" in "Cardholders_Full_Name"
#    And User enters "month" in "Month"
#    And User enters "year" in "Year"
#    And User enters "security_code" in "Security_Coode"
#    And User enters "number_street_name" in "Number_Street_Name"
#    And User enters "postcode/zip" in "Postcode/Zip"
#    And User enters "City" in "city"
#    And User clicks on "Terms_And_Conditions"
#    And User clicks on "Agree"
#    And User clicks on "Pay_Now"

    Examples:
      | Testcase         | app_type |
      | LX_ManageBooking_5| Amadeus  |