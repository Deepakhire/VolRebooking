@TC8
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
    And User clicks on "Checkbox"
    And User handles Privacy Settingss Page and proceeds with booking
    And User opens the Amadeus Agate Picker
    And User clicks on "Tenant"
    And User clicks on "Manage_Booking_RefX"
    And User enters "record_locator" in "Record_Locator"
    And User enters "last_name" in "last_Name"
    And User clicks on "check_box"
    And User clicks on "submit_btn"
    And User clicks on "Change_flights"
    And User clicks on "LHG_Debug_Panel"
    And User clicks on "feature_flag_custserv_3464"
    And User clicks on "Apply"
    And User clicks on "Enabel_LHG_Degub_Panel"
    And User clicks on "flag_Custserv_2616"
    And User clicks on "apply"
    And User clicks on "one_of_the_flight_Check_box"
    And User clicks on "Search"
    And User clicks on "Original_Flight_Details"
    And User clicks on "Flights"
    And User clicks on "Dates"
    And User enters "master_Card_Information" in "Master_Card_Information"
    And User clicks on "Agree"



    Examples:
      | Testcase         | app_type |
      | PNR_Flow008 | Amadeus  |