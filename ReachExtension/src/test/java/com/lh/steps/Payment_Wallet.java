package com.lh.steps;

import com.lh.library.CommonActions;
import com.lh.core.page.HomePage;
import com.lh.core.page.LoginPage;
import com.lh.core.page.PaymentHubPage;
import com.lh.core.page.ProfilePage;
import com.microsoft.playwright.*;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class Payment_Wallet {

    CommonActions commonactions;
     Playwright playwright;
     Browser browser;
     BrowserContext context;
    Page page;
    HomePage home;
    LoginPage login;
    ProfilePage profilePage;
    PaymentHubPage paymentHubPage;
    private String Privacy_setting_accept = "//div[@class='cc-row-buttons']/button[@class='button-primary']";
    private String home_page_title = "#header > div.header > h1";
    private String payment_method_block = "//h2[text()='Payment methods']";

    public Payment_Wallet(CommonActions commonactions) {
        this.commonactions = commonactions;
    }

//    @Before
//    public void setUp() {
//        try{
//            Playwright playwright = Playwright.create();
//            BrowserType chromium = playwright.chromium();
//            //BrowserType firefox = playwright.firefox();
//            Browser browser = chromium.launch(new BrowserType.LaunchOptions().setHeadless(false));
//            BrowserContext context = browser.newContext(new Browser.NewContextOptions()
//                    .setHttpCredentials("u192312", "Lufthansa@123"));
//            page = context.newPage();
//            home = new HomePage(page);
//            login = new LoginPage(page);
//            profilePage = new ProfilePage(page);
//            paymentHubPage = new PaymentHubPage(page);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//    }
   // @Given("User launches lufthansa web application")
    public void user_launches_lufthansa_web_application() throws Exception {
        try {
            page.waitForLoadState();
            page.navigate("https://www.lufthansa.com/de/en/homepage");
            System.out.println("Lufthansa url loaded : successfully");
            home.handle_Privacy_Settings_Page();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @When("^User logged in the app using username (.*) and password (.*)$")
    public void user_logged_in_the_app_using_username_UserName_and_password_Password(String username,String password) {
        login.loginIntoApplication(username, password);
    }

    //Verify product name after login
   // @And("^User verify the home page (.*)$")
    public void user_verify_the_home_page(String page_title) {
        String productName = home.getProductName();
        System.out.println("Product Name : "+productName);
        Assert.assertEquals(page_title, productName);
        System.out.println("Page Name verified successfully :");
        //Assert.assertEquals("Page Name verified successfully :", page_title, productName);
    }
    @And("^User verify the product name (.*)$")
    public void user_verify_the_product_name(String page_title) {
        String productName = home.getProductName();
        System.out.println("Product Name : "+productName);
        Assert.assertEquals(page_title, productName);
    }
    @And("User clicks on login_option")
    public void user_clicks_on_login_option() {
        home.clickOnLoginOption();
    }


//    @And("User clicks on {string}")
    public void user_clicks_on(String locator) {
        home.clickOn(locator);
        //home.clickOnLoginOption(locator);
    }
    @And("User clicks on {string} in profile page")
    public void user_clicks_on_in_profile_page(String locator) {
        profilePage.clickOn(locator);
        //home.clickOnLoginOption(locator);
    }

   // @And("User verify presence of {string}")
    public void user_verify_presence_of(String locator) {
        profilePage.verifyPresence(locator);
    }

    @And("User verify absence of {string}")
    public void user_verify_absence_of(String locator) {
       profilePage.verifyAbsence(locator);
    }

    @Then("User scrolls down to {string}")
    public void user_scrolls_down_to(String locator) {
        commonactions.scrollDownToElementView(locator);
//        while(page.locator(payment_method_block).isVisible() == false){
//            page.mouse().wheel(25,1300);
//        }
//        while (page.querySelectorAll(payment_method_block).isEmpty()) {
//            page.evaluate("window.scrollBy(0, 1000)");
//        }

//        page.waitForSelector(payment_method_block);
//        ElementHandle element = page.querySelector(payment_method_block);
//        if (element == null) {
//            throw new RuntimeException("Element not found");
//        }
//
//        element.scrollIntoViewIfNeeded();
//        page.mouse.wheel(0,100)
//        #page.keyboard.down(PageDown) also works
    }
    @And("User clicks on {string} in paymentHub page")
    public void user_clicks_on_in_paymentHub_page(String locator) {
        paymentHubPage.clickOn(locator);
        //home.clickOnLoginOption(locator);
    }

    @And("User selects {string} in paymentHub page")
    public void user_selects_in_paymentHub_page(String locator) {
        paymentHubPage.clickOn(locator);
    }


}
