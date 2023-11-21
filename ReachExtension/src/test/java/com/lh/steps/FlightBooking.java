package com.lh.steps;

import com.lh.core.page.HomePage;
import com.lh.core.page.LoginPage;
import com.lh.core.page.PnrLoginPage;
import com.lh.core.page.PaymentHubPage;
import com.lh.core.page.ProfilePage;
import com.lh.library.CommonActions;
import com.lh.utilities.Configurations;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.interactions.touch.UpAction;

import java.io.IOException;

public class FlightBooking
{
    CommonActions commonActions;

    public FlightBooking(CommonActions commonactions) {
        this.commonActions = commonactions;
    }

    @And("^User verify the home page (.*)$")
    public void user_verify_the_home_page(String page_title) {
        String productName = commonActions.getProductName(page_title);
        System.out.println("Product Name : " + page_title);
        System.out.println("Product Name : " + productName);
        Assert.assertEquals(page_title, productName);
        System.out.println("Page Name verified successfully :");
        //Assert.assertEquals("Page Name verified successfully :", page_title, productName);
        commonActions.verifyHomePageTitle(page_title);
    }

    @When("^User selects (.*?) in the (.*?)$")
    public void user_selects_trip_type_in_the(String data, String locator) {
        //removes double quotes from locator value
        String objectKey = commonActions.getObjectKey(locator);
        System.out.println("locator :" + objectKey);
        System.out.println("data :" + data);
        commonActions.selectValFromDropDown(data, objectKey);
    }

    @And("^User enters (.*?) in the (.*?)$")
    public void user_enters_travel_city_in_the(String data, String locator) {
        //removes double quotes from locator value
        String objectKey = commonActions.getObjectKey(locator);
        System.out.println("locator :" + objectKey);
        System.out.println("data :" + data);
        commonActions.enterTextSelectOption(objectKey, data);
    }

    @And("^User selects (.*?) as the (.*?)$")
    public void user_selects_Date_as_the(String date, String locator) {
        //removes double quotes from locator value
        String objectKey = commonActions.getObjectKey(locator);
        System.out.println("locator :" + objectKey);
        commonActions.click(objectKey);
        if (objectKey.contains("Date")) {
            commonActions.selectDate(objectKey, date);
        } else {
            commonActions.click(objectKey);
        }
        //commonActions.enterText(locator, date);
    }

    @And("User selects checkbox {string}")
    public void user_selects_checkbox(String locator) {
        System.out.println("Locator : " + locator);
        commonActions.selectCheckBox(locator);
    }



    @And("User clicks on the {string}")
    public void user_clicks_on_the(String locator) {
        commonActions.waitForLoadState();
        commonActions.clickAndWaitForPageLoad(locator);
    }

    @And("^User selects (.*?) from (.*?)$")
    public void user_selects_val_from_list(String data, String locator) throws Exception {
        //removes double quotes from locator value
        String objectKey = commonActions.getObjectKey(locator);
        try {
            if (objectKey.equals("travel_Class") || objectKey.equals("Title_dropdown") || objectKey.equals("cardType_options") || objectKey.equals("fare_class") ||
                    objectKey.equals("CC_CardType")) {
                commonActions.click(objectKey);
            }
            commonActions.selectFromDropDownList(objectKey, data);
        } catch (ElementNotInteractableException e) {
            e.printStackTrace();
            commonActions.logAssert_Fail("Please check element is visible on the page: " + objectKey + ":" + data);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            commonActions.logAssert_Fail("User selects failed: " + objectKey + ":" + data);
        }
    }

    @And("^User select (.*?) from (.*?)$")
    public void user_select_val_from_list(String data, String locator) throws Exception {
        //removes double quotes from locator value
        String objectKey = commonActions.getObjectKey(locator);
        try {
            commonActions.select_Seat(data, objectKey);
        } catch (ElementNotInteractableException e) {
            e.printStackTrace();
            commonActions.logAssert_Fail("Please check element is visible on the page: " + objectKey + ":" + data);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            commonActions.logAssert_Fail("User selects failed: " + objectKey + ":" + data);
        }
    }

    @And("User verify presence of {string}")
    public void user_verify_presence_of(String locator) {
        try {
            commonActions.waitForLoadState();
            if (locator.equals("Booking_confirmation_message")) {
                commonActions.waitForDOMLoadStateWithOption();
            }
            commonActions.verifyPresenceOfElement(locator);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @When("User enter Passenger details")
    public void user_enter_Passenger_Details(DataTable dataTable) {
        try {
            commonActions.enterPassengerDetails(dataTable);
        } catch (Exception e) {
            System.out.println("Exception in Enter Passenger Details :" + e.getMessage());
        }
    }

    @And("User verify presence of {string} on Booking Confirmation Page")
    public void user_verify_presence_of_element_on_Booking_Confirmation_Page(String locator) {
        try {
//            if(locator.equals("Booking_confirmation_message")){
//                commonActions.waitForTimeOutAndPageLoad();
//                commonActions.waitForDOMLoadStateWithOption();
//            }
            commonActions.waitForLoadState();
            commonActions.waitForDOMLoadStateWithOption();
            commonActions.verifyPresenceOfElementWithLoadStateWait(locator);
//            commonActions.verifyPresenceOfElement(locator);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Then("^User captures (.*?) of (.*?)$")
    public void user_captures(String data, String locator) {
        String objectKey = commonActions.getObjectKey(locator);
        try {
            commonActions.captureConfirmationDetails(data, objectKey);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @And("User selects {string} on {string} page")
    public void user_selects_on_Flight_selection_page(String locator, String page_Name) {
        System.out.println("Locator : " + locator);
        commonActions.click(locator);
    }

    @And("^User enter (.*?) in (.*?)$")
    public void user_enter_data_in_text_field(String data, String locator) {
        //removes double quotes from locator value
        String objectKey = commonActions.getObjectKey(locator);
        try {
            if (objectKey.contains("Card_Authentication")) {
                commonActions.performCardAuthentication(objectKey, data);
            } else {
                commonActions.enterText(objectKey, data);
            }
        } catch (Exception e) {
            System.out.println("Exception in Card Authentication : " + e.getMessage());
        }
//        commonActions.enterText(objectKey, data);
    }

    @And("^User select value (.*?) from (.*?)$")
    public void user_select_value_from_selector(String data, String locator) {
        //removes double quotes from locator value
        String objectKey = commonActions.getObjectKey(locator);
        commonActions.selectFromDropDownByValue(objectKey, data);
    }

    @And("^User enters (.*?) details in (.*?)$")
    public void user_selects_LH_as_the(String data, String locator) {
        //removes double quotes from locator value
        String objectKey = commonActions.getObjectKey(locator);
        commonActions.enterAddress(data, objectKey);
    }

    @And("User chooses {string} as Payment Method")
    public void userChoosesAsPaymentMethod(String objectkey) {
        try {
            commonActions.click(objectkey);
            commonActions.waitForLoadState();
        } catch (Exception e) {
            System.out.println("Exception in payment method selection :" + e.getMessage());
        }
    }

    @When("User enter Credit Card details")
    public void user_enter_CC_Card_Details(DataTable dataTable) {
        try {
            commonActions.enterCreditCardDetails(dataTable);
        } catch (Exception e) {
            System.out.println("Exception in Enter Card Details :" + e.getMessage());
        }
    }

    @Then("User performs {string}")
    public void user_performs_card_Authentication(String objectKey) {
        try {
            commonActions.verifyPresenceOfElement(objectKey);
            commonActions.performCardAuthentication(objectKey, "1234");
        } catch (Exception e) {
            String errMsg = String.format("Card Authentication : ");
            System.out.println(errMsg + " " + e);
        }
    }

    @Then("User captures Booking_confirmation details")
    public void user_captures_Booking_confirmation_details(DataTable dataTable) {
        try {
            commonActions.captureBookingConfirmationDetails(dataTable);
        } catch (Exception e) {
            String errMsg = String.format("Booking confirmation details: ");
            System.out.println(errMsg + " " + e);
        }
    }

    @Given("User fills {string} form")
    public void user_fills_form(String detailType, DataTable dataTable) {
        try {
            commonActions.enterDetailsInForm(detailType, dataTable);
        } catch (Exception e) {
            System.out.println("Exception in Enter Card Details :" + e.getMessage());
        }
    }

    @Given("User accept {string}")
    public void user_accept(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("User login to UserAccount")
    public void user_login_to_UserAccount() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("clicks on activation_link")
    public void clicks_on_activation_link() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @And("user selects {string} fare")
    public void user_selects_fare(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @And("user clicks on the continue button")
    public void user_clicks_on_the_continue_button() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @And("User enters login credentials")
    public void user_enters_login_credentials(DataTable dataTable) {
        try {
            commonActions.waitForLoadState();
            commonActions.enterLoginCredentials(dataTable);
        } catch (Exception e) {
            System.out.println("Exception in Enter Login Details :" + e.getMessage());
        }
    }

    @Then("User get logged_in with {string}")
    public void user_get_logged_in(String locator) {
        try {
            commonActions.waitForLoadState();
            commonActions.verifyPresenceOfElement(locator);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    ////////////////////////////////// AMD PNR /////////////////////////////////////////////


}
