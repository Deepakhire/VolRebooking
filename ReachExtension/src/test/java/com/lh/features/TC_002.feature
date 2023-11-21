@TC02
Feature: PNR_Flow

  Scenario Outline: <Testcase>
    Given User launches "<app_type>" web application
    And User enters "credential" in login page

    And User clicks on "Round_Trip"
    And User enters "origin_city" in "Origin_City"
    And User clicks on "Ocity"
    And User enters "destination_city" in "Destination_City"
    And User clicks on "Dcity"
    And User clicks on "Departure_Date"
    And User clicks on "date"
#    And User clicks on "Month_Arrow"
    And User clicks on "Return_Date"
    And User clicks on "Date"
    And User enters "children" in "Children"
    And User clicks on "Checkbox"
# And User clicks on "Submit_Button"
# And User clicks on "Abtn"


    And User handles Privacy Settings Page and proceed with LH booking

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
    And User handles Privacy Setting Page and proceed with LH rebooking



#    And User handles Privacy Settings Page and proceed with booking
#    And User opens the Amadeus Agate Picker
#    And User opens the Amadeus Agate Picker
#    And User clicks on "Tenant"
#    And User clicks on "Manage_Booking_RefX"
#    And User enters "record_locator" in "Record_Locator"
#    And User enters "last_name" in "last_Name"
#    And User clicks on "check_box"
#    And User clicks on "submit_btn"
#
#    And User clicks on "Extra_Baggage"
#    And User clicks on "First_plus"
#    And User clicks on "Second_plus"
#    And User clicks on "Confirm_baggage"
#    And User clicks on "see_bags"
#    And User clicks on "Continue_payment"
#    And User clicks on "Paypal"
#    And User enters "paypal_email" in "Paypal_Email"
#    And User clicks on "check_box"
#    And User enters "paypal_password" in "Paypal_Password"
#    And User clicks on "Login"
#    And User clicks on "Review_order"
#    And User clicks on "change_flight"
#    And User clicks on "LHG_Debug_panel"
#    And User clicks on "3464"
#    And User clicks on "Apply"
#    And User clicks on "check_box"
#    And User clicks on "Rebook_calender"
#    And User clicks on "Rebook_Date"
#    And User clicks on ""
#    And User clicks on "LHG_Debug_panel"
#    And User clicks on "2616"
#    And User clicks on "Apply"
#    And User clicks on "Rebook_Flight"
#    And User clicks on "Rebook_Flight_Fare"
#    And User clicks on "Rebbok_Continue_payment"
#    And User clicks on "Radio_button"
#    And User clicks on "Credit_Card_Type"
#    And User clicks on "American_Express"
#    And User enters "american_card_number" in "American_Card_Number"
#    And User enters "card_holder_name" in "Card_Holder_Name"
#    And User enters "card_month" in "Card_Year"
#    And User enters "security_code" in "Security_code"
#    And User clicks on "check_box"
#    And User clicks on "PayNow"
#




    Examples:
      | Testcase         | app_type |
      | PNR_Flow | Amadeus  |