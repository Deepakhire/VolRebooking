@pymnt
Feature: Payment_Wallet_Login

  Scenario Outline: <Testcase>
    Given User launches "<app_type>" web application
    And User verify the home page <ProductName>
    And User clicks on "Login"
    And User enters login credentials
      | Email_address | red025@yopmail.com |
      | password      | Test@123           |
    When User clicks on "Log_in"
    And User clicks on "logged_in_userName"
    And User clicks on "profile_btn"
    And User verify presence of "profile_page"
    And User scrolls down to "payment_method_block"
    And User clicks on "collapse_arrow"
    And User verify absence of "payment_method_block"
    And User clicks on "expand_arrow"
    And User verify presence of "payment_method_block"
    And User clicks on "Add_or_edit_payment_method"
#    And User selects "saved_payment_method" in paymentHub page
    And User chooses "saved_payment_method"
    And User clicks on "Add_new"
    And User clicks on "Back"

    Examples:
      | Testcase                                                                | app_type | ProductName |
      | User_Login_into_LH_WebApplication_and_performs_paymentWallet_operations | LH       | Swiss       |