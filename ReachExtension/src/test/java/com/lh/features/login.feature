@login @test
Feature: login

  Scenario Outline: <TestCase>
    Given User launches "<app_type>" web application
    And User clicks on "Login"
    And User enters login credentials
      | Email_address | red025@yopmail.com |
      | password      | Test@123           |
    When User clicks on "Log_in"
    Then User get logged_in with "user_Profile_Name"

    Examples:
      | TestCase      | app_type |
      | lh_Login_Flow | LH       |
