@Pnrf
Feature: PNR_Flow

  Scenario Outline: <Testcase>
    Given User launches "<app_type>" web application
    And User enters "credential" in login page
#      When User selects LH airline on homepage
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
    And User clicks on "Checkbox"
##    And User clicks on "Submit_Button"
##    And User clicks on "Abtn"
    And User handles Privacy Settings Page and proceeds with booking
##    And User selects "flight" on "Flight_selection" page
##    And User selects <fare_class_val> from "fare_class"
##    And User clicks on the "confirm_and_continue"
##    And User verify presence of "total_Price_For_Flight"
##    And User clicks on the "enter_Passenger_Details"
##  PrivacyPage.locator("//div[text()='Economy']").first().click();
##    And User enters "title_dropdown" in "Title_dropdown"
##    And User enters "firstName" in "First_Name"
##    And User enters "lastName" in "Last_Name"
##    And User enters "emailAddress" in "Email"
##    And User enters "countryCode" in "Country_Calling_Code"
##    And User enters "phoneNumber" in "Phone_Number"
##    And User clicks on the "enter_Passenger_Details"
##    And User clicks on "Selct_Flight_Option"
##    And User clicks on "FareClass"
##    And User clicks on "confirm_And_continue"
##    And User clicks on "FareClass"
##    And User clicks on "Enter_Passenger_Details"
##    And User clicks on "Title"
##    And User clicks on "MrOption"
###    And User clicks on "enterFirst"
##    And User enters "first_name" in "First_Name"
###    And User clicks on "enterLast"
##    And User enters "last_name" in "Last_Name"
###    And User clicks on "enteremail"
##    And User enters "email" in "Email"
###    And User clicks on "enterCountryCalling"
##    And User enters "country_calling_code" in "Country_Calling_Code"
###    And User enters "india" in "India"
###    And User clicks on "IndiaOption"
###    And User clicks on "enterPhone"
##    And User enters "phone" in "Phone_Number"
##    And User clicks on "Confirm"
##    And User clicks on "Continue_to_Payment"
##    And User clicks on "credit/debit_card"
##    And User clicks on "card_type"
##    And User selects "card_Type" from "Card_List"
##    And User enters "card_number" in "Card_Number_Field"
##    And User enters "full_name" in "Full_Name"
##    And User enters "expiry_month" in "Expiry_Month"
##    And User enters "expiry_year" in "Expiry_Year"
##    And User enters "security_code" in "Security_code"
##    And User enters "street_name" in "Street_Name"
##    And User enters "zip_code" in "Zip_Code"
##    And User enters "city" in "City_Name"
##    And User select "province" from "Province"
##    And User selects checkbox "Terms_And_Condition"
##    And User clicks on "pay_now"
##    And User verify presence of "Booking_confirmation_message" on Booking Confirmation Page
##
##
##      And User clicks on "destination_city"
##      And User select the departure date
#  #    And User click on the enable debug panel
#  #    And User click on the submit button
#
    Examples:
      | Testcase         | app_type |
      | Amadeus_PNR_Flow | Amadeus  |
