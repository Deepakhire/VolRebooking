@TC_004
Feature: PNR_Flow

  Scenario Outline: <Testcase>
    Given User launches "<app_type>" web application
    And User enters "credential" in login page
# When User selects LH airline on homepage
    And User clicks on "Booking_availability"
    And User clicks on "select_cabin"
    And User clicks on "Economy_button"
    #And User clicks on "Round_Trip"
    And User enters "origin_city" in "Origin_City"
    And User clicks on "Ocity"
    And User enters "destination_city" in "Destination_City"
    And User clicks on "Dcity"
    And User clicks on "Departure_Date"
    And User clicks on "date"
#    And User clicks on "Month_Arrow"
    And User clicks on "Return_Date"
    And User clicks on "Date"
    And User enters "adults" in "Adults"
    And User enters "children" in "Children"
    And User clicks on "Checkbox"
# And User clicks on "Submit_Button"
# And User clicks on "Abtn"

    And User handle Privacy Settings Page and proceeds with LH booking

#    And User opens the Amadeus Agate Picker


#   And User clicks on "OS_Tenant"
    And User clicks on "Manage_Booking_RefX"
    And User clicks on "Manage_Booking_RefX"
    And User enters "record_locator" in "Record_Locator"

#    And User enters the pnr in Record_Locator

    And User enters "last_name" in "last_Name"
    And User clicks on "check_box"
#    And User clicks on "submit_btn"
#    And User clicks on "Change_Flights"
    And User handles Privacy Setting Page and proceed with rebooking












#    And User handle Privacy Settings Page and proceeds with booking
#    And User opens the Amadeus Agate Picker
#    And User clicks on "Tenant"
#    And User clicks on "Manage_Booking_RefX"
#    And User enters "record_locator" in "Record_Locator"
#    And User enters "last_name" in "last_Name"
#    And User clicks on "check_box"
#    And User clicks on "submit_btn"
#    And User clicks on "Change_flights"
#    And User clicks on "LHG_Debug_Panel"
#    And User clicks on "feature_flag_custserv_3464"
#    And User clicks on "Apply"
#    And User clicks on "Enabel_LHG_Degub_Panel"
#    And User clicks on "flag_Custserv_2616"
#    And User clicks on "apply"
#    And User clicks on "one_of_the_flight_Check_box"
#    And User clicks on "Search"
#    And User clicks on "Original_Flight_Details"
#    And User clicks on "Flights"
#    And User clicks on "Dates"
#    And User enters "AMEX_card_number" in "AMEX_Card_Number"
#    And User enters "AMEX_cardholder_name" in "AMEX_Cardholder_Name"
#    And User enters "AMEX_card_expiry" in "AMEX_Card_Expiry"
#    And User enters "AMEX_card_cvv" in "AMEX_Card_CVV"
#    And User enters "AMEX_billing_address" in "AMEX_Card_CVV"
#    And User clicks on "Agree"






    Examples:
      | Testcase         | app_type |
      | PNR_Flow004 | Amadeus  |