@TC7
Feature: CrossTenant_LH_OS

  Scenario Outline: <Testcase>
    Given User launches "<app_type>" web application
    And User enters "credential" in login page
    And User clicks on "Booking_availability"
    And User clicks on "select_cabin"
    And User clicks on "Economy_button"
    And User clicks on "oneway"
    And User enters "origin_city" in "Origin_City"
    And User clicks on "Ocity"
    And User enters "destination_city" in "Destination_City"
    And User clicks on "Dcity"
    And User clicks on "Date_button"
    And User clicks on "Date"
#    And User clicks on "Adults"
    And User enters "children" in "Children"
    And User enters "infant" in "Infant"
    And User clicks on "Checkbox"
    And User handles Privacy Settings Page and proceedss with booking
    And User opens the Amadeus Agate Picker
#    And User clicks on "Tenant"
#    And User clicks on "Manage_Booking_RefX"
#    And User enters "record_locator" in "Record_Locator"
#    And User enters "last_name" in "last_Name"
#    And User clicks on "check_box"
#    And User clicks on "submit_btn"



    Examples:
      | Testcase         | app_type |
      | PNR_Flow007 | Amadeus  |