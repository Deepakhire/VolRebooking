package com.lh.steps;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.xml.sax.SAXException;

import com.genz.xray.XRAY_CONFIG;
import com.genz.xray.Xray;
import com.lh.library.CommonActions;
import com.lh.runner.JunitRunner;
import com.lh.utilities.Configurations;
import com.lh.xray.Log;
import com.lh.xray.XrayHelper;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GenericSteps
{
    //**************************************For Normal PNR Creation **************************************************
//    CommonActions commonActions;
//    private Scenario scenario;
//    Page page;
//    Page privacyPage;
//    String testFinish = "";
//    String XrayIssueKey = "";
//
//    public GenericSteps(CommonActions con) {
//        this.commonActions = con;
//    }
//
//    private final static Logger LOGGER = Logger.getLogger(GenericSteps.class);
//
//    @Before
//    public void before(Scenario s) throws Exception {
//        this.scenario = s;
//        try {
//
//            page = commonActions.getPage();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if ((Configurations.RunOnBrowserStack).equals("Y")) {
//            commonActions.initReports(s.getName() + "_" + System.getProperty("browser"));
//        } else {
//            commonActions.initReports(s.getName() + "_" + "chrome");
//        }
//        commonActions.setfeaturefilenameandsceanrio(s.getId(), s.getName());
//        commonActions.setScenario(s);
//    }
//
//    /**
//     * Description Closing the resources after execution of each scenario
//     *
//     * @throws IOException
//     */
//    @After
//    public void after(Scenario s)
//    {
//        commonActions.quit();
//    }
//
//    @Given("User launches lufthansa web application")
//    public void user_launches_lufthansa_web_application() {
//        try {
//            page.navigate(Configurations.Appurl_QA_LH_De);
//            page.waitForLoadState();
//            System.out.println("Lufthansa url loaded : successfully");
//            commonActions.handle_Privacy_Settings_Page();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @And("User launches {string} web application")
//    public void user_launches_web_application(String tenant) {
//        LOGGER.info(CommonActions.getCurrentMethodName());
//        try {
//
//            System.out.println("App_type : " + tenant);
//            if (tenant.equalsIgnoreCase("swiss")) {
//                page.navigate(Configurations.Appurl_QA_SN_Fr_B2);
//            } else if (tenant.equalsIgnoreCase("lh")) {
//                page.navigate(Configurations.Appurl_QA_LH_De);
//            } else if (tenant.equalsIgnoreCase("amadeus")) {
//                page.navigate(Configurations.Appurl_QA_Amadeus_B2);
//            } else {
//                page.navigate(Configurations.Appurl_QA_LH_De);
//            }
//            page.waitForLoadState();
//            System.out.println("Lufthansa url loaded : successfully");
//            commonActions.handle_Privacy_Settings_Page();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            LOGGER.error(e.getMessage());
//        }
//
//    }
//
//    @And("User chooses {string}")
//    public void user_chooses(String locator) {
//        commonActions.click(locator);
//    }
//
//    @Given("User enters {string} in login page")
//    public void user_enters_credential_in_login_page(String jsonKeyDetails) {
//        try {
//            LOGGER.info(CommonActions.getCurrentMethodName());
//            commonActions.user_Performs_Amadues_Login(jsonKeyDetails);
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
//        }
//    }
//
//
//    @And("User enters {string} in {string}")
//    public void user_enters_data_in_text_field(String jsonDataKey, String locator) {
//
//        try {
//            String data = commonActions.getTestData(jsonDataKey);
//            commonActions.enterText(locator, data);
//        } catch (Exception e) {
//            System.out.println("Exception in enterText : " + e.getMessage());
//        }
//    }
//
//    @And("User handles Privacy Settings Page and proceeds with booking")
//    public void User_handle_Privacy_Settings_Page() {
//        page.waitForTimeout(200);
//        try {
//            System.out.println("Privacy Handle Function Body");
//
//            page.waitForPopup(new Page.WaitForPopupOptions()
//                    .setPredicate(p -> p.context().pages().size() == 2), () ->
//            {
//                page.locator("(//button[text()='Submit'])[1]").click();
//
//            });
//
//            System.out.println("Submit button clicked: Successfully");
//
//            List<Page> pages = page.context().pages();
//            for (Page tabs : pages) {
//                tabs.waitForLoadState();
//                System.out.println(tabs.url());
//            }
//            Page PrivacyPage = pages.get(1);
//            privacyPage=PrivacyPage;
//
//            System.out.println("Submit button :New URL");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.waitForLoadState();
//            PrivacyPage.locator(commonActions.getLocatorStr("cookieAEM")).isVisible();
//
//            PrivacyPage.click(commonActions.getLocatorStr("cookieAEM"));
//            System.out.println("Privacy setting page handled : successfully");
//
//            PrivacyPage.waitForLoadState(LoadState.LOAD);
//
//            PrivacyPage.locator("//div[text()='Economy']").first().click();
//
//            PrivacyPage.locator("//span[text()='Select']").first().click();
//            PrivacyPage.waitForTimeout(10000);
//
//            //Passenger details
//             PrivacyPage.locator("//span[text()='Enter passenger details']").first().click();
//
//            //select title
//            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("Test");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("User");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your email address']").fill("test@yopmail.com");
//
//            PrivacyPage.waitForTimeout(3000);
//            PrivacyPage.locator("//input[@placeholder='Your country calling code']").fill("+49");
//            PrivacyPage.waitForTimeout(3000);
//            PrivacyPage.locator("//input[@placeholder='Your mobile phone']").fill("123456");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//button[contains(@class,\"mat-button\")]//span[contains(text(),'Confirm')]").first().click();
//            PrivacyPage.waitForTimeout(30000);
//
//            PrivacyPage.locator("//button[contains(@class,\"mat-button\")]//span[contains(text(),'Continue to payment')]").first().click();
//            PrivacyPage.waitForTimeout(30000);
//            // select credit card
//            PrivacyPage.locator("//label[contains(text(),'Credit')]/..//input[@id='radio_1']").first().click();
//           PrivacyPage.waitForTimeout(10000);
//
//           //name and street
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"street-label\"]").fill("street name");
//            PrivacyPage.waitForTimeout(2000);
//
//            // card number
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"number-label\"]").fill("4000000000001000");
//            PrivacyPage.waitForTimeout(2000);
//
//            // card holder name
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"name-label\"]").fill("George John");
//            PrivacyPage.waitForTimeout(2000);
//
//            // zip name
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"zip-label\"]").fill("123456");
//            PrivacyPage.waitForTimeout(2000);
//
//            // city
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"city-label\"]").fill("Frankfurt");
//            PrivacyPage.waitForTimeout(2000);
//
//            // exp month
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-month-label\"]").fill("06");
//            PrivacyPage.waitForTimeout(2000);
//
//            // exp year
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-year-label\"]").fill("25");
//            PrivacyPage.waitForTimeout(2000);
//
//            // cvv
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"cvv-label\"]").fill("123");
//            PrivacyPage.waitForTimeout(2000);
//
//            // Visa
//            PrivacyPage.locator("//div[contains(@class,\"cardtype-select\")]//span[contains(@class,\"dropdown-icon\")]").first().click();
//
//            PrivacyPage.querySelector("//li[@class='mdc-list-item']//span[contains(text(), 'Visa')]").click();
//
//            // terms and cond
//            PrivacyPage.locator("//input[@type='checkbox' and contains(@class, 'mdc-checkbox__native-control') and @id='terms-checkbox']").first().click();
//            PrivacyPage.waitForTimeout(5000);
//
//            // pay now
//            PrivacyPage.locator("//span[contains(text(), 'Pay Now')]").first().click();
//            System.out.println("PNR created : successfully");
//            Thread.sleep(90000);
//
//        privacyPage.locator("//div[text()='Your booking is confirmed!']").click();
//            Thread.sleep(90000);
//
//        } catch (Exception e) {
//            e.getMessage();
//            LOGGER.error(e.getMessage());
//        }
//    }
//
//    private void saveTestResultsToXray(Scenario s) {
//
//        ZonedDateTime finishDateTime = ZonedDateTime.now();
//        testFinish = finishDateTime.truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
//        Log.info("Test Finish Time: " + testFinish);
//
//        if (s.isFailed()) {
//            Log.error("Test Failed!");
//            JunitRunner.featureTestPassed = false;
//            Xray.writeResultsForSingleTest(JunitRunner.ExecutionID, XrayIssueKey, XRAY_CONFIG.TEST_STATUS_FAIL,
//                    JunitRunner.testStart, testFinish);
//        } else {
//            if (JunitRunner.featureTestPassed == true) {
//                Log.info("Test Passed!");
//                Xray.writeResultsForSingleTest(JunitRunner.ExecutionID, XrayIssueKey, XRAY_CONFIG.TEST_STATUS_PASS,
//                        JunitRunner.testStart, testFinish);
//            }
//        }
//
//    }
//
//    private void checkNewTest(Scenario s) {
//        XrayIssueKey = XrayHelper.getTestIdFromFileName(s.getId());
//
//        if (!JunitRunner.currentXrayIssueKey.contains(XrayIssueKey)) {
//            System.out.println("This is a new Feature!");
//            JunitRunner.currentXrayIssueKey = XrayIssueKey;
//            JunitRunner.featureTestPassed = true;
//        }
//
//    }
//
//    @And("User scrolls up")
//    public void userScrollsUp() {
//        // Scroll up the page
//        page.evaluate("window.scrollTo(0, 0);");
//    }

    //******************************************************* For TC_001 **************************************************

    CommonActions commonActions;
    private Scenario scenario;
    Page page;
    Page privacyPage;
    String testFinish = "";
    String XrayIssueKey = "";

    private String pnr;
    public GenericSteps(CommonActions con) {
        this.commonActions = con;
    }

    private final static Logger LOGGER = Logger.getLogger(GenericSteps.class);

    @Before
    public void before(Scenario s) throws Exception {
        this.scenario = s;
        try {

            page = commonActions.getPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if ((Configurations.RunOnBrowserStack).equals("Y")) {
            commonActions.initReports(s.getName() + "_" + System.getProperty("browser"));
        } else {
            commonActions.initReports(s.getName() + "_" + "chrome");
        }
        commonActions.setfeaturefilenameandsceanrio(s.getId(), s.getName());
        commonActions.setScenario(s);
    }

    /**
     * Description Closing the resources after execution of each scenario
     *
     * @throws IOException
     */
    @After
    public void after(Scenario s)
    {
        commonActions.quit();
    }

    @Given("User launches lufthansa web application")
    public void user_launches_lufthansa_web_application() {
        try {
            page.navigate(Configurations.Appurl_QA_LH_De);
            page.waitForLoadState();
            System.out.println("Lufthansa url loaded : successfully");
            commonActions.handle_Privacy_Settings_Page();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @And("User launches {string} web application")
    public void user_launches_web_application(String tenant) {
        LOGGER.info(CommonActions.getCurrentMethodName());
        try {

            System.out.println("App_type : " + tenant);
            if (tenant.equalsIgnoreCase("swiss")) {
                page.navigate(Configurations.Appurl_QA_SN_Fr_B2);
            } else if (tenant.equalsIgnoreCase("lh")) {
                page.navigate(Configurations.Appurl_QA_LH_De);
            } else if (tenant.equalsIgnoreCase("amadeus")) {
                page.navigate(Configurations.Appurl_QA_Amadeus_B2);
            }
            else if (tenant.equalsIgnoreCase("os")) {
                page.navigate(Configurations.Appurl_QA_Amadeus_B2_OS);
            }
            else {
                page.navigate(Configurations.Appurl_QA_LH_De);
            }
            page.waitForLoadState();
            System.out.println("Lufthansa url loaded : successfully");
            commonActions.handle_Privacy_Settings_Page();

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }

    }

    @And("User chooses {string}")
    public void user_chooses(String locator) {
        commonActions.click(locator);
    }

    @Given("User enters {string} in login page")
    public void user_enters_credential_in_login_page(String jsonKeyDetails) {
        try {
            LOGGER.info(CommonActions.getCurrentMethodName());
            commonActions.user_Performs_Amadues_Login(jsonKeyDetails);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @And("User clicks on {string}")
    public void user_clicks_on(String locator) {
        if (locator.equals("BackToMerchant")) {
            commonActions.clickOnIframeElement(locator);
        } else {
            commonActions.waitForLoadState();
            commonActions.click(locator);
        }
    }
    //
    @And("User enters {string} in {string}")
    public void user_enters_data_in_text_field(String jsonDataKey, String locator) {

        try {
            String data = commonActions.getTestData(jsonDataKey);
            commonActions.enterText(locator, data);
        } catch (Exception e) {
            System.out.println("Exception in enterText : " + e.getMessage());
        }
    }

    @And("User handles Privacy Settings Page and proceeds with booking")
    public void User_handle_Privacy_Settings_Page() {
        page.waitForTimeout(200);
        try {
            System.out.println("Privacy Handle Function Body");

            page.waitForPopup(new Page.WaitForPopupOptions()
                    .setPredicate(p -> p.context().pages().size() == 2), () ->
            {
                page.locator("(//button[text()='Submit'])[1]").click();

            });

            System.out.println("Submit button clicked: Successfully");

            List<Page> pages = page.context().pages();
            for (Page tabs : pages) {
                tabs.waitForLoadState();
                System.out.println(tabs.url());
            }
            Page PrivacyPage = pages.get(1);
            privacyPage=PrivacyPage;

            System.out.println("Submit button :New URL");
            PrivacyPage.waitForTimeout(3000);

            PrivacyPage.waitForLoadState();
            PrivacyPage.locator(commonActions.getLocatorStr("cookieAEM")).isVisible();

            PrivacyPage.click(commonActions.getLocatorStr("cookieAEM"));
            System.out.println("Privacy setting page handled : successfully");

            PrivacyPage.waitForLoadState(LoadState.LOAD);

            PrivacyPage.locator("//div[text()='Economy']").first().click();

            PrivacyPage.locator("(//span[text()='Select'])[2]").first().click();
            PrivacyPage.waitForTimeout(10000);

            //Passenger details
            PrivacyPage.locator("//span[text()='Enter passenger details']").first().click();
            PrivacyPage.waitForTimeout(3000);
            //select title
            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("Test");
            PrivacyPage.waitForTimeout(3000);

            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("User");
            PrivacyPage.waitForTimeout(3000);

            PrivacyPage.locator("//input[@placeholder='Your email address']").fill("test@yopmail.com");

            PrivacyPage.waitForTimeout(3000);
            PrivacyPage.locator("//input[@placeholder='Your country calling code']").fill("+49");
            PrivacyPage.waitForTimeout(3000);

            PrivacyPage.locator("//input[@placeholder='Your mobile phone']").fill("123456");
            PrivacyPage.waitForTimeout(3000);

            //Enter next passerger
            PrivacyPage.locator("//span[text()='Next passenger']").first().click();
            PrivacyPage.waitForTimeout(3000);
            PrivacyPage.pause();

            //Enter first name
            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("jon");
            PrivacyPage.waitForTimeout(5000);

            //Enter Last NAme
            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("dey");
            PrivacyPage.waitForTimeout(5000);

            //DOB
            PrivacyPage.locator("//span[@class='mat-mdc-button-touch-target']").click();
            PrivacyPage.waitForTimeout(5000);

            //select year
            PrivacyPage.locator("//span[text()=' 2021 ']").click();
            PrivacyPage.waitForTimeout(5000);

            //select month
            PrivacyPage.locator("//span[text()=' OCT ']").click();
            PrivacyPage.waitForTimeout(5000);

            //select day
            PrivacyPage.locator("//span[text()=' 23 ']").click();
//            PrivacyPage.waitForTimeout(5000);

            //click on Confirm
            PrivacyPage.locator("//span[contains(text(),'Confirm')]").click();
//            PrivacyPage.locator("//button[contains(@class,\"mat-button\")]//span[contains(text(),'Confirm')]").first().click();
            PrivacyPage.waitForTimeout(30000);

            //span[text()='Confirm']
            PrivacyPage.pause();
//            PrivacyPage.locator("//button[contains(@class,\"mat-button\")]//span[contains(text(),'Continue to payment')]").first().click();
            PrivacyPage.locator("//span[contains(text(),'Continue to payment')]").click();
            PrivacyPage.waitForTimeout(30000);
//PrivacyPage.pause();
            // select credit card
            PrivacyPage.locator("//label[contains(text(),'Credit')]/..//input[@id='radio_1']").first().click();
            PrivacyPage.waitForTimeout(30000);

            //name and street
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"street-label\"]").fill("street name");
            PrivacyPage.waitForTimeout(2000);

            // card number
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"number-label\"]").fill("4000000000001000");
            PrivacyPage.waitForTimeout(2000);

            // card holder name
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"name-label\"]").fill("George John");
            PrivacyPage.waitForTimeout(2000);

            // zip name
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"zip-label\"]").fill("123456");
            PrivacyPage.waitForTimeout(2000);

            // city
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"city-label\"]").fill("Frankfurt");
            PrivacyPage.waitForTimeout(2000);

            // exp month
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-month-label\"]").fill("06");
            PrivacyPage.waitForTimeout(2000);

            // exp year
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-year-label\"]").fill("26");
            PrivacyPage.waitForTimeout(2000);

            // cvv
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"cvv-label\"]").fill("123");
            PrivacyPage.waitForTimeout(2000);

            // Visa
            PrivacyPage.locator("//div[contains(@class,\"cardtype-select\")]//span[contains(@class,\"dropdown-icon\")]").first().click();

            PrivacyPage.querySelector("//li[@class='mdc-list-item']//span[contains(text(), 'Visa')]").click();

            // terms and cond
            PrivacyPage.locator("//input[@type='checkbox' and contains(@class, 'mdc-checkbox__native-control') and @id='terms-checkbox']").first().click();
            PrivacyPage.waitForTimeout(5000);

            // pay now
            PrivacyPage.locator("//span[contains(text(), 'Pay Now')]").first().click();
            System.out.println("PNR created : successfully");
            Thread.sleep(5000);

            privacyPage.locator("//div[text()='Your booking is confirmed!']").click();
//            Thread.sleep(90000);
            Thread.sleep(5000);
            PrivacyPage.pause();
            page.bringToFront();

        }
        catch (Exception e) {
            e.getMessage();
            LOGGER.error(e.getMessage());
        }
    }

    @And("User handles Privacy Settings Page and proceed with LH booking")
    public void User_handle_Privacy_Settings_Page_002() {
//        page.waitForTimeout(200);
//        try {
//            System.out.println("Privacy Handle Function Body");
//
//            page.waitForPopup(new Page.WaitForPopupOptions()
//                    .setPredicate(p -> p.context().pages().size() == 2), () ->
//            {
//                page.locator("(//button[text()='Submit'])[1]").click();
//
//            });
//            System.out.println("Submit button clicked: Successfully");
//
//            List<Page> pages = page.context().pages();
//            for (Page tabs : pages) {
//                tabs.waitForLoadState();
//                System.out.println(tabs.url());
//            }
//            Page PrivacyPage = pages.get(1);
//            privacyPage=PrivacyPage;
//
//            System.out.println("Submit button :New URL");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.waitForLoadState();
//            PrivacyPage.locator(commonActions.getLocatorStr("Privacy")).isVisible();
//
//            PrivacyPage.click(commonActions.getLocatorStr("Privacy"));
//            System.out.println("Privacy setting page handled : successfully");
//
//            PrivacyPage.waitForLoadState(LoadState.LOAD);
//
//            //Select classS
//            PrivacyPage.locator("(//div[text()='Economy'])[1]").first().click();
//
//            //Select class from econumy
//            PrivacyPage.locator("//button[@id=\"selectFare-STWWM4FF0E\"]/span[1]").first().click();
//            PrivacyPage.waitForTimeout(10000);
//
//            //select return class
//            PrivacyPage.locator("(//div[text()='Economy'])[1]").first().click();
//
//            //Select class from econumy
//            PrivacyPage.locator("//button[@id=\"selectFare-STWWM4FF0E\"]/span[1]").first().click();
//            PrivacyPage.waitForTimeout(10000);
//
//            //Enter Passenger details
//            PrivacyPage.locator("//span[text()='Enter passenger details']").first().click();
//
//            //select First Name
//            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("Test");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("User");
//            PrivacyPage.waitForTimeout(3000);
//
//            // select gender
//            PrivacyPage.locator("//span[text()='Male']").first().click();
//            PrivacyPage.waitForTimeout(3000);
//
////enter date of birth
//            PrivacyPage.locator("//span[@class='mat-mdc-button-touch-target']").first().click();
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//span[text()=' 2011 ']").first().click();
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//span[text()=' NOV ']").first().click();
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//span[text()=' 30 ']").first().click();
//            PrivacyPage.waitForTimeout(3000);
//
//
//            PrivacyPage.locator("//input[@placeholder='Your email address']").fill("test@yopmail.com");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your country calling code']").fill("+49");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your mobile phone']").fill("123456");
//            PrivacyPage.waitForTimeout(3000);
//
//            //Next passerger Details
//            PrivacyPage.locator("//span[text()='Next passenger']").first().click();
//            PrivacyPage.waitForTimeout(3000);
//            PrivacyPage.pause();
//
//            //Enter first name
//            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("jon");
//            PrivacyPage.waitForTimeout(3000);
//
//            //Enter Last NAme
//            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("dey");
//            PrivacyPage.waitForTimeout(3000);
//
//            //DOB
//            PrivacyPage.locator("//span[@class='mat-mdc-button-touch-target']").click();
//            PrivacyPage.waitForTimeout(3000);
//
//            //select year
//            PrivacyPage.locator("//span[text()=' 2021 ']").click();
//            PrivacyPage.waitForTimeout(3000);
//
//            //select month
//            PrivacyPage.locator("//span[text()=' OCT ']").click();
//            PrivacyPage.waitForTimeout(3000);
//
//            //select day
//            PrivacyPage.locator("//span[text()=' 23 ']").click();
//            PrivacyPage.waitForTimeout(3000);
//
//            //click on Confirm
//            PrivacyPage.locator("//button[contains(@class,\"mat-button\")]//span[contains(text(),'Confirm')]").first().click();
//            PrivacyPage.waitForTimeout(30000);
//
//            //add extra baggage
//            PrivacyPage.locator("//span[text()='Add extra bags']").click();
//            PrivacyPage.waitForTimeout(3000);
//
//
//            //click on plus 1st passenger
//            PrivacyPage.locator("//button[@id='955d8f24-fcd1-45b2-add1-d5b3e612f104-plusButton']//i[@class='refx-icon-add']").click();
//            PrivacyPage.waitForTimeout(3000);
//
//            //click on plus 2st passenger
//            PrivacyPage.locator("//button[@id='15ff26cc-df7b-4f19-824e-77324bfad1fa-plusButton']//i[@class='refx-icon-add']").click();
//            PrivacyPage.waitForTimeout(3000);
//
//            //Continue to Payment
//            PrivacyPage.locator("//button[contains(@class,\"mat-button\")]//span[contains(text(),'Continue to payment')]").first().click();
//            PrivacyPage.waitForTimeout(30000);
//
//            // select credit card
//            PrivacyPage.locator("//label[contains(text(),'Credit')]/..//input[@id='radio_1']").first().click();
//            PrivacyPage.waitForTimeout(10000);
//
//            // creadit card type- Visa
//            PrivacyPage.locator("//div[contains(@class,\"cardtype-select\")]//span[contains(@class,\"dropdown-icon\")]").first().click();
//            PrivacyPage.querySelector("//li[@class='mdc-list-item']//span[contains(text(), 'Visa')]").click();
//            PrivacyPage.waitForTimeout(2000);
//
//            // card number
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"number-label\"]").fill("4000000000001000");
//            PrivacyPage.waitForTimeout(2000);
//
//            // card holder name
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"name-label\"]").fill("Lisa John");
//            PrivacyPage.waitForTimeout(2000);
//
//            // exp month
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-month-label\"]").fill("06");
//            PrivacyPage.waitForTimeout(2000);
//
//            // exp year
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-year-label\"]").fill("26");
//            PrivacyPage.waitForTimeout(2000);
//
//            // cvv
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"cvv-label\"]").fill("123");
//            PrivacyPage.waitForTimeout(2000);
//
//            //name and street
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"street-label\"]").fill("street name");
//            PrivacyPage.waitForTimeout(2000);
//
//            // zip name
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"zip-label\"]").fill("123456");
//            PrivacyPage.waitForTimeout(2000);
//
//            // city
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"city-label\"]").fill("Frankfurt");
//            PrivacyPage.waitForTimeout(2000);
//
//            // terms and cond
//            PrivacyPage.locator("//input[@type='checkbox' and contains(@class, 'mdc-checkbox__native-control') and @id='terms-checkbox']").first().click();
//            PrivacyPage.waitForTimeout(5000);
//
//            // pay now
//            PrivacyPage.locator("//span[contains(text(), 'Pay Now')]").first().click();
//            System.out.println("PNR created : successfully");
//            Thread.sleep(90000);
//
//            privacyPage.locator("//div[text()='Your booking is confirmed!']").click();
//            PrivacyPage.waitForTimeout(10000);
//            //Thread.sleep(10000);
//            PrivacyPage.pause();
//            page.bringToFront();

        page.waitForTimeout(200);
        try {
            System.out.println("Privacy Handle Function Body");

            page.waitForPopup(new Page.WaitForPopupOptions()
                    .setPredicate(p -> p.context().pages().size() == 2), () ->
            {
                page.locator("(//button[text()='Submit'])[1]").click();
            });

            System.out.println("Submit button clicked: Successfully");

            List<Page> pages = page.context().pages();
            for (Page tabs : pages) {
                tabs.waitForLoadState();
                System.out.println(tabs.url());
            }
            Page PrivacyPage = pages.get(1);
            privacyPage=PrivacyPage;

            System.out.println("Submit button :New URL");
            PrivacyPage.waitForTimeout(500);

            PrivacyPage.waitForLoadState();
            PrivacyPage.locator(commonActions.getLocatorStr("Privacy")).isVisible();

            PrivacyPage.click(commonActions.getLocatorStr("Privacy"));
            System.out.println("Privacy setting page handled : successfully");

            PrivacyPage.waitForLoadState(LoadState.LOAD);
            PrivacyPage.waitForTimeout(3000);


//            PrivacyPage.pause();


            //Select classS
            PrivacyPage.locator("(//div[contains(text(),'Economy')])[1]").first().click();

            //Select class economy
            PrivacyPage.locator("(//span[contains(text(),'Select')])[2]").first().click();
//            PrivacyPage.locator("//button[@id=\"selectFare-BFEUM2NC0E\"]/span[1]").first().click();
            PrivacyPage.waitForTimeout(3000);

            //Confirm and continue to next page
//            PrivacyPage.locator("//span[contains(text(),'Confirm and continue')]").first().click();

            //Select inward flight class
            PrivacyPage.locator("(//div[contains(text(),'Economy')])[1]").first().click();

            //Select inward class
            PrivacyPage.locator("(//span[contains(text(),'Select')])[1]").first().click();
            PrivacyPage.waitForTimeout(3000);


            //Enter Passenger details
            PrivacyPage.locator("//span[text()='Enter passenger details']").first().click();
            PrivacyPage.waitForTimeout(20000);


            //select First Name
            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("Test");
            PrivacyPage.waitForTimeout(500);

            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("User");
            PrivacyPage.waitForTimeout(500);
//            PrivacyPage.pause();

            //DOB
            PrivacyPage.locator("//span[@class='mat-mdc-button-touch-target']").click();
            PrivacyPage.waitForTimeout(500);

            //select year
            PrivacyPage.locator("//span[text()=' 1990 ']").click();
            PrivacyPage.waitForTimeout(500);

            //select month
            PrivacyPage.locator("//span[text()=' OCT ']").click();
            PrivacyPage.waitForTimeout(500);

            //select day
            PrivacyPage.locator("//span[text()=' 23 ']").click();
            PrivacyPage.waitForTimeout(500);

            //select gender
            PrivacyPage.locator("//span[contains(text(),'Male')]").first().click();

            //mail
            PrivacyPage.locator("//input[@placeholder='Your email address']").fill("test@yopmail.com");
            PrivacyPage.waitForTimeout(500);

            PrivacyPage.locator("//input[@placeholder='Your country calling code']").fill("+49");
            PrivacyPage.waitForTimeout(500);

            PrivacyPage.locator("//input[@placeholder='Your mobile phone']").fill("123456");
            PrivacyPage.waitForTimeout(500);
//            PrivacyPage.pause();

            //Enter next passerger
            PrivacyPage.locator("//span[text()='Next passenger']").first().click();
            PrivacyPage.waitForTimeout(10000);
//            PrivacyPage.pause();

            //Enter first name
            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("jon");
            PrivacyPage.waitForTimeout(500);

            //Enter Last NAme
            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("dey");
            PrivacyPage.waitForTimeout(500);

            //DOB
            PrivacyPage.locator("//span[@class='mat-mdc-button-touch-target']").click();
            PrivacyPage.waitForTimeout(500);

            //select year
            PrivacyPage.locator("//span[text()=' 2021 ']").click();
            PrivacyPage.waitForTimeout(500);

            //select month
            PrivacyPage.locator("//span[text()=' OCT ']").click();
            PrivacyPage.waitForTimeout(500);

            //select day
            PrivacyPage.locator("//span[text()=' 23 ']").click();
            PrivacyPage.waitForTimeout(500);

            //select gender
            PrivacyPage.locator("//span[contains(text(),'Male')]").first().click();

            //click on Confirm
            PrivacyPage.locator("//button[contains(@class,\"mat-button\")]//span[contains(text(),'Confirm')]").first().click();
            PrivacyPage.waitForTimeout(2000);
//            PrivacyPage.pause();


            //Continue to Payment
            PrivacyPage.locator("//span[contains(text(),'Continue to payment')]").first().click();
            PrivacyPage.waitForTimeout(60000);
//            PrivacyPage.pause();

            // select credit card
            PrivacyPage.locator("//label[contains(text(),'Credit')]/..//input[@id='radio_1']").first().click();
            PrivacyPage.waitForTimeout(2000);

            // creadit card type- Visa
            PrivacyPage.locator("//div[contains(@class,\"cardtype-select\")]//span[contains(@class,\"dropdown-icon\")]").first().click();
            PrivacyPage.querySelector("//span[contains(text(), 'Visa')]").click();
            PrivacyPage.waitForTimeout(500);

            // card number
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"number-label\"]").fill("4000000000001000");
            PrivacyPage.waitForTimeout(500);

            // card holder name
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"name-label\"]").fill("Test User");
            PrivacyPage.waitForTimeout(500);

            // exp month
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-month-label\"]").fill("06");
            PrivacyPage.waitForTimeout(500);

            // exp year
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-year-label\"]").fill("26");
            PrivacyPage.waitForTimeout(500);

            // cvv
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"cvv-label\"]").fill("123");
            PrivacyPage.waitForTimeout(500);

            //name and street
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"street-label\"]").fill("street name");
            PrivacyPage.waitForTimeout(500);

            // zip name
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"zip-label\"]").fill("123456");
            PrivacyPage.waitForTimeout(500);

            // city
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"city-label\"]").fill("Frankfurt");
            PrivacyPage.waitForTimeout(500);

            // terms and cond
            PrivacyPage.locator("//input[@type='checkbox' and contains(@class, 'mdc-checkbox__native-control') and @id='terms-checkbox']").first().click();
            PrivacyPage.waitForTimeout(1000);

            // pay now
            PrivacyPage.locator("//span[contains(text(), 'Pay Now')]").first().click();
            System.out.println("PNR created : successfully");
            Thread.sleep(60000);

            privacyPage.locator("//div[text()='Your booking is confirmed!']").click();
//            page.screenshot(new Page.ScreenshotOptions().setPath("screenshot.png"));
            PrivacyPage.waitForTimeout(10000);
            Thread.sleep(10000);
//            PrivacyPage.pause();

            page.bringToFront();
        }
        catch (Exception e) {
            e.getMessage();
            LOGGER.error(e.getMessage());
        }
    }

    @And("User handles Privacy Setting Page and proceed with LH rebooking")
    public void User_handle_Privacy_Settings_Page_002RB()  {
//        page.waitForTimeout(2000);
//        try {
//
//            System.out.println("Privacy Handle Function Body");
//
//            page.waitForPopup(new Page.WaitForPopupOptions()
//                    .setPredicate(p -> p.context().pages().size() == 2), () ->
//            {
//                page.locator("(//button[text()='Submit'])[1]").click();
//
//            });
//
//            System.out.println("Submit button clicked: Successfully");
//
//            List<Page> pages = page.context().pages();
//            for (Page tabs : pages) {
//                tabs.waitForLoadState();
//                System.out.println(tabs.url());
//            }
//            Page PrivacyPage = pages.get(1);
//            privacyPage=PrivacyPage;
//
//
//            System.out.println("Submit button :New URL");
//            PrivacyPage.waitForTimeout(500);
//
//            PrivacyPage.waitForLoadState();
//            PrivacyPage.locator(commonActions.getLocatorStr("Privacy")).isVisible();
//
//            PrivacyPage.click(commonActions.getLocatorStr("Privacy"));
//            System.out.println("Privacy setting page handled : successfully");
//
//            PrivacyPage.waitForLoadState(LoadState.LOAD);
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.pause();
//
//
//            //          additional baggage
//            PrivacyPage.locator("//span[text()='Add extra bags']").first().click();
//            PrivacyPage.waitForTimeout(500);
//
//            //          additional baggage plus button 1st passenger
//            PrivacyPage.locator("(//i[@class='refx-icon-add'])[1]").first().click();
//            PrivacyPage.waitForTimeout(500);
//
//            //          additional baggage plus button 2nd passenger
//            PrivacyPage.locator("//button[@id='15ff26cc-df7b-4f19-824e-77324bfad1fa-plusButton']//i[@class='refx-icon-add']").first().click();
//            PrivacyPage.waitForTimeout(500);
//
//            //     confirm your bag
//            PrivacyPage.locator("//span[text()='Confirm your bags']").first().click();
//            PrivacyPage.waitForTimeout(500);
//
//            //     click on change button under baggage
//            PrivacyPage.locator("(//span[contains(text(),'Change')])[1]").first().click();
//            PrivacyPage.waitForTimeout(500);
//
//            //     click on verify baggage
//            PrivacyPage.locator("(//span[text()='1 carry-on bag'])[1]").first().click();
//            PrivacyPage.waitForTimeout(500);
//
//            //     verify baggage price
//            PrivacyPage.locator("(//span[contains(text(),'Total price:')])[1]").first().click();
//            PrivacyPage.waitForTimeout(500);
//
//            //     continue to payment
//            PrivacyPage.locator("//span[text()='Continue to payment']").first().click();
//            PrivacyPage.waitForTimeout(500);
//
//            //     paypal radio
//            PrivacyPage.locator("//input[@id=\"radio_3\"]").first().click();
//            PrivacyPage.waitForTimeout(500);
//
//
//            //     paypal email
//            PrivacyPage.locator("//*[@id=\"paypal-form\"]/div/label/input").fill(" amadeus@test.com ");
//            PrivacyPage.waitForTimeout(500);
//
//            // terms and cond
//            PrivacyPage.locator("//input[@type='checkbox' and contains(@class, 'mdc-checkbox__native-control') and @id='terms-checkbox']").first().click();
//            PrivacyPage.waitForTimeout(5000);
//
//
//            // pay now
//            PrivacyPage.locator("//span[contains(text(), 'Pay Now')]").first().click();
//
//            //     paypal password
//            PrivacyPage.locator("//input[@class='hasHelp  validateEmpty   pin-password']").fill("c#Ag?d2Es3Us");
//            PrivacyPage.waitForTimeout(500);
//
//            // paypal login
//            PrivacyPage.locator("//button[@class='button actionContinue scTrack:unifiedlogin-login-submit']").first().click();
//
//            // paypal review order
//            PrivacyPage.locator("//button[text()='Continue to Review Order']").first().click();
//
////          Change flight
//            PrivacyPage.locator("//span[contains(text(),'Change flights')]").first().click();
//            PrivacyPage.waitForTimeout(500);
//
////          Debug panel
//            PrivacyPage.locator("//p[normalize-space()='LHG debug panel']").first().click();
//            PrivacyPage.waitForTimeout(500);
//
////          Custserv-3464
//            PrivacyPage.locator("//span[text()='CUSTSERV-3464']").first().click();
//            PrivacyPage.waitForTimeout(500);
//
//            PrivacyPage.locator("//span[normalize-space()='Apply']").first().click();
//            PrivacyPage.waitForTimeout(5000);
//
//            PrivacyPage.pause();
//
////          Select date
//            PrivacyPage.getByPlaceholder("dd/mm/yyyy").fill("2023-11-20");
//
////            PrivacyPage.locator("//div[@id=\"cdk-accordion-child-0\"]/div/div/mat-form-field[1]/div/div[1]/div/mat-datepicker").first().click();
//
//            PrivacyPage.locator("//span[text()='Search']").first().click();
//            PrivacyPage.waitForTimeout(500);
//
//            PrivacyPage.locator("//p[normalize-space()='LHG debug panel']").first().click();
//            PrivacyPage.waitForTimeout(1000);
//
//            PrivacyPage.locator("//span[text()='CUSTSERV-2616']").first().click();
//            PrivacyPage.waitForTimeout(500);
//
//            PrivacyPage.locator("//span[normalize-space()='Apply']").first().click();
//            PrivacyPage.waitForTimeout(5000);
//
//            PrivacyPage.locator("//span[text()='Original flights details']").isVisible();
//            PrivacyPage.waitForTimeout(500);
//
//            PrivacyPage.pause();
//
//            PrivacyPage.locator("(//span[text()='from'])[1]").click();
//            PrivacyPage.locator("(//span[text()='Select'])[4]").click();
//            PrivacyPage.waitForTimeout(10000);
//
//
//            privacyPage.locator("//span[text()='Continue to payment']").click();
//            privacyPage.waitForTimeout(5000);
//
//            PrivacyPage.locator("//label[contains(text(),'Credit')]/..//input[@id='radio_1']").first().click();
//            PrivacyPage.waitForTimeout(2000);
//
//            // creadit card type- Visa
//            PrivacyPage.locator("//div[contains(@class,\"cardtype-select\")]//span[contains(@class,\"dropdown-icon\")]").first().click();
//            PrivacyPage.querySelector("//span[contains(text(), 'Visa')]").click();
//            PrivacyPage.waitForTimeout(500);
//
//            // card number
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"number-label\"]").fill("4000000000001000");
//            PrivacyPage.waitForTimeout(500);
//
//            // card holder name
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"name-label\"]").fill("Test User");
//            PrivacyPage.waitForTimeout(500);
//
//            // exp month
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-month-label\"]").fill("06");
//            PrivacyPage.waitForTimeout(500);
//
//            // exp year
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-year-label\"]").fill("26");
//            PrivacyPage.waitForTimeout(500);
//
//            // cvv
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"cvv-label\"]").fill("123");
//            PrivacyPage.waitForTimeout(500);
//
//            //name and street
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"street-label\"]").fill("street name");
//            PrivacyPage.waitForTimeout(500);
//
//            // zip name
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"zip-label\"]").fill("123456");
//            PrivacyPage.waitForTimeout(500);
//
//            // city
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"city-label\"]").fill("Frankfurt");
//            PrivacyPage.waitForTimeout(500);
//
//            // terms and cond
//            PrivacyPage.locator("//input[@type='checkbox' and contains(@class, 'mdc-checkbox__native-control') and @id='terms-checkbox']").first().click();
//            PrivacyPage.waitForTimeout(1000);
//
//            // pay now
//            PrivacyPage.locator("//span[contains(text(), 'Pay Now')]").first().click();
//            System.out.println("PNR created : successfully");
//            Thread.sleep(60000);
//
//            PrivacyPage.locator("//span[text()='Your booking reference is']").isVisible();
//            Thread.sleep(2000);

        page.waitForTimeout(200);

        try {

            System.out.println("Privacy Handle Function Body");

            page.waitForPopup(new Page.WaitForPopupOptions(), () ->
            {
                page.locator("(//button[text()='Submit'])[1]").click();
            });

            System.out.println("Submit button clicked: Successfully");

            List<Page> pages = page.context().pages();
            for (Page tabs : pages) {
                tabs.waitForLoadState();
                System.out.println(tabs.url());
            }
            Page PrivacyPage = pages.get(1);
            privacyPage=PrivacyPage;

            PrivacyPage.waitForTimeout(5000);

            System.out.println("Submit button :New URL");
            PrivacyPage.waitForTimeout(5000);

            PrivacyPage.waitForLoadState();

            ElementHandle privacyElement = privacyPage.querySelector(commonActions.getLocatorStr("Privacy"));
            if (privacyElement != null) {
                if (privacyElement.isVisible()) {
                    privacyElement.click();
                    System.out.println("Privacy setting page handled successfully");
                } else {
                    System.out.println("Privacy setting element found but not visible");
                }
            } else {
                System.out.println("Privacy setting element not found");
            }

            PrivacyPage.waitForLoadState(LoadState.LOAD);
            PrivacyPage.waitForTimeout(3000);
//            Privacy page handled till here

//            PrivacyPage.pause();

//            Add extra baggage
            privacyPage.locator("//span[text()='Add extra bags']").click();
            privacyPage.waitForTimeout(2000);

//            Add for inbound flight for both pax
            privacyPage.locator("(//button[@aria-label='Add'])[3]").click();
            privacyPage.waitForTimeout(500);
            privacyPage.locator("(//button[@aria-label='Add'])[4]").click();

//            confirm bags
            privacyPage.locator("//span[text()='Confirm your bags']").click();
            privacyPage.waitForTimeout(10000);

//            Continue to payment
            privacyPage.locator("//span[text()='Continue to payment']").click();
            privacyPage.waitForTimeout(5000);

            PrivacyPage.locator("//input[@id='radio_3']").first().click();
            PrivacyPage.waitForTimeout(2000);

            privacyPage.locator("//span[@id='email-label-pp']").fill("amadeus@test.com");


            //name and street
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"street-label\"]").fill("street name");
            PrivacyPage.waitForTimeout(500);

            // zip name
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"zip-label\"]").fill("123456");
            PrivacyPage.waitForTimeout(500);

            // city
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"city-label\"]").fill("Frankfurt");
            PrivacyPage.waitForTimeout(500);

            // terms and cond
            PrivacyPage.locator("//input[@type='checkbox' and contains(@class, 'mdc-checkbox__native-control') and @id='terms-checkbox']").first().click();
            PrivacyPage.waitForTimeout(1000);

            // pay now
            PrivacyPage.locator("//span[contains(text(), 'Pay Now')]").first().click();

//            paypal page
            privacyPage.locator("//input[@id='password']").fill("c#Ag?d2Es3Us");

            privacyPage.locator("//button[@value='Login']").click();
            privacyPage.waitForTimeout(2000);

            privacyPage.locator("//span[text()='Rabobank Nederland']").click();
            privacyPage.locator("//button[text()='Continue to Review Order']").click();
            privacyPage.waitForTimeout(10000);




            System.out.println("PNR created : successfully");
            Thread.sleep(60000);

            PrivacyPage.locator("//span[text()='Your booking reference is']").isVisible();
            Thread.sleep(2000);




//          Change Flight scenario


//          Change flight
            PrivacyPage.locator("//span[contains(text(),'Change flights')]").first().click();
            PrivacyPage.waitForTimeout(500);

//          Debug panel
            PrivacyPage.locator("//p[normalize-space()='LHG debug panel']").first().click();
            PrivacyPage.waitForTimeout(500);

//          Custserv-3464
            PrivacyPage.locator("//span[text()='CUSTSERV-3464']").first().click();
            PrivacyPage.waitForTimeout(500);

            PrivacyPage.locator("//span[normalize-space()='Apply']").first().click();
            PrivacyPage.waitForTimeout(5000);

            PrivacyPage.pause();

//            select outbound
            privacyPage.locator("//mat-checkbox[@id=\"mat-checkbox-1\"]").click();

//          Select date
            PrivacyPage.getByPlaceholder("dd/mm/yyyy").fill("2023-11-28");

            PrivacyPage.locator("//span[text()='Search']").first().click();
            PrivacyPage.waitForTimeout(500);

            PrivacyPage.locator("//p[normalize-space()='LHG debug panel']").first().click();
            PrivacyPage.waitForTimeout(1000);

            PrivacyPage.locator("//span[text()='CUSTSERV-2616']").first().click();
            PrivacyPage.waitForTimeout(500);

            PrivacyPage.locator("//span[normalize-space()='Apply']").first().click();
            PrivacyPage.waitForTimeout(5000);

            PrivacyPage.locator("//span[text()='Original flights details']").isVisible();
            PrivacyPage.waitForTimeout(500);

//            PrivacyPage.pause();

            PrivacyPage.locator("(//span[text()='from'])[1]").click();
            PrivacyPage.locator("(//span[@class='mat-button-wrapper']//span[text()='Select'])[1]").click();
            PrivacyPage.waitForTimeout(20000);

            privacyPage.locator("//span[text()='Continue to payment']").click();
            privacyPage.waitForTimeout(5000);

            PrivacyPage.locator("//label[contains(text(),'Credit')]/..//input[@id='radio_1']").first().click();
            PrivacyPage.waitForTimeout(2000);

            // creadit card type- Visa
            PrivacyPage.locator("//div[contains(@class,\"cardtype-select\")]//span[contains(@class,\"dropdown-icon\")]").first().click();
            PrivacyPage.querySelector("//span[contains(text(), 'Visa')]").click();
            PrivacyPage.waitForTimeout(500);

            // card number
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"number-label\"]").fill("4000000000001000");
            PrivacyPage.waitForTimeout(500);

            // card holder name
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"name-label\"]").fill("Test User");
            PrivacyPage.waitForTimeout(500);

            // exp month
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-month-label\"]").fill("06");
            PrivacyPage.waitForTimeout(500);

            // exp year
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-year-label\"]").fill("26");
            PrivacyPage.waitForTimeout(500);

            // cvv
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"cvv-label\"]").fill("123");
            PrivacyPage.waitForTimeout(500);

            //name and street
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"street-label\"]").fill("street name");
            PrivacyPage.waitForTimeout(500);

            // zip name
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"zip-label\"]").fill("123456");
            PrivacyPage.waitForTimeout(500);

            // city
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"city-label\"]").fill("Frankfurt");
            PrivacyPage.waitForTimeout(500);

            // terms and cond
            PrivacyPage.locator("//input[@type='checkbox' and contains(@class, 'mdc-checkbox__native-control') and @id='terms-checkbox']").first().click();
            PrivacyPage.waitForTimeout(1000);

            // pay now
            PrivacyPage.locator("//span[contains(text(), 'Pay Now')]").first().click();
            System.out.println("PNR created : successfully");
            Thread.sleep(60000);

            PrivacyPage.locator("//span[text()='Your booking reference is']").isVisible();
            Thread.sleep(2000);

        }

        catch (Exception e)
        {
            e.getMessage();
            LOGGER.error(e.getMessage());
        }
    }


//
    @And("User handles Privacy Setting Page and proceeds with LH booking")
    public void User_handle_Privacy_Settings_Page_003() {
        page.waitForTimeout(200);
        try {
            System.out.println("Privacy Handle Function Body");

            page.waitForPopup(new Page.WaitForPopupOptions()
                    .setPredicate(p -> p.context().pages().size() == 2), () ->
            {
                page.locator("(//button[text()='Submit'])[1]").click();

            });
            System.out.println("Submit button clicked: Successfully");

            List<Page> pages = page.context().pages();
            for (Page tabs : pages) {
                tabs.waitForLoadState();
                System.out.println(tabs.url());
            }
            Page PrivacyPage = pages.get(1);
            privacyPage = PrivacyPage;

            System.out.println("Submit button :New URL");
            PrivacyPage.waitForTimeout(3000);

            PrivacyPage.waitForLoadState();
            PrivacyPage.locator(commonActions.getLocatorStr("Privacy")).isVisible();

            PrivacyPage.click(commonActions.getLocatorStr("Privacy"));
            System.out.println("Privacy setting page handled : successfully");

            PrivacyPage.waitForLoadState(LoadState.LOAD);

            //Select classS
            PrivacyPage.locator("(//span[@class='refx-display-1 flight-starting-price-label ng-star-inserted'])[1]").first().click();
            //div[@id='2c19aaad-f819-4df5-b916-a0de20c5a38a-flightCardecoPremium-buttonLabel']
            PrivacyPage.waitForTimeout(10000);

            //Select Fare class from Economy
            PrivacyPage.locator("(//span[text()='Select'])[1]").first().click();
            PrivacyPage.waitForTimeout(10000);
            PrivacyPage.pause();

            //select return class
            PrivacyPage.locator("(//button[@data-fare-family-group='eco'])[1]").first().click();
            PrivacyPage.waitForTimeout(10000);
            PrivacyPage.pause();

//            Select Fare class from econumy
            PrivacyPage.locator("(//span[text()='Select'])[1]").first().click();
            PrivacyPage.waitForTimeout(10000);
            PrivacyPage.pause();

            //Enter Passenger details
            PrivacyPage.locator("//span[text()='Enter passenger details']").first().click();
            PrivacyPage.waitForTimeout(10000);
            PrivacyPage.pause();

            //Enter First PAssenger Details
            //select First Name
            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("Test");
            PrivacyPage.waitForTimeout(3000);

            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("User");
            PrivacyPage.waitForTimeout(3000);
// select gender
            PrivacyPage.locator("//span[text()='Male']").first().click();
            PrivacyPage.waitForTimeout(3000);

//enter date of birth
            PrivacyPage.locator("//span[@class='mat-mdc-button-touch-target']").first().click();
            PrivacyPage.waitForTimeout(3000);

            PrivacyPage.locator("//span[text()=' 2011 ']").first().click();
            PrivacyPage.waitForTimeout(3000);

            PrivacyPage.locator("//span[text()=' NOV ']").first().click();
            PrivacyPage.waitForTimeout(3000);

            PrivacyPage.locator("//span[text()=' 30 ']").first().click();
            PrivacyPage.waitForTimeout(3000);

            PrivacyPage.locator("//input[@placeholder='Your email address']").fill("test@yopmail.com");
            PrivacyPage.waitForTimeout(3000);

            PrivacyPage.locator("//input[@placeholder='Your country calling code']").fill("+49");
            PrivacyPage.waitForTimeout(3000);

            PrivacyPage.locator("//input[@placeholder='Your mobile phone']").fill("123456");
            PrivacyPage.waitForTimeout(3000);

            //Next passerger
            PrivacyPage.locator("//span[text()='Next passenger']").first().click();
            PrivacyPage.waitForTimeout(3000);
            PrivacyPage.pause();

//            Enter the adult 2 information
//             Enter first name
            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("jon");
            PrivacyPage.waitForTimeout(5000);

            //Enter Last NAme
            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("lee");
            PrivacyPage.waitForTimeout(5000);

            //Next passerger
            PrivacyPage.locator("//span[text()='Next passenger']").first().click();
            PrivacyPage.waitForTimeout(3000);
            PrivacyPage.pause();

//            Enter the child information
//            Enter first name
            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("jolly");
            PrivacyPage.waitForTimeout(3000);

            //Enter Last Name
            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("lee");
            PrivacyPage.waitForTimeout(3000);

            //DOB
            PrivacyPage.locator("//span[@class='mat-mdc-button-touch-target']").click();
            PrivacyPage.waitForTimeout(3000);
            PrivacyPage.pause();

//            select year
            PrivacyPage.locator("//span[text()=' 2021 ']").click();
            PrivacyPage.waitForTimeout(3000);

            //select month
            PrivacyPage.locator("//span[text()=' OCT ']").click();
            PrivacyPage.waitForTimeout(3000);

            //select day
            PrivacyPage.locator("//span[text()=' 23 ']").click();
            //PrivacyPage.waitForTimeout(5000);
            Thread.sleep(90000);
            //PrivacyPage.waitForTimeout(30000);
            PrivacyPage.pause();

            //click on Confirm
            //PrivacyPage.locator("//span[contains(text(),'Confirm')]").first().click();
            PrivacyPage.locator("//button[contains(@class,\"mat-button\")]//span[contains(text(),'Confirm')]").first().click();
            PrivacyPage.waitForTimeout(30000);
            PrivacyPage.pause();

            //Continue to Payment
            PrivacyPage.locator("//span[contains(text(),'Continue to payment')]").first().click();
            //PrivacyPage.locator("//button[contains(@class,\"mat-button\"mat-button)]//span[contains(text(),'Continue to payment')]").first().click();
            PrivacyPage.waitForTimeout(30000);

            // select credit card
            PrivacyPage.locator("//label[contains(text(),'Credit')]/..//input[@id='radio_1']").first().click();
            PrivacyPage.waitForTimeout(10000);
            PrivacyPage.pause();

            // creadit card type- Visa
            PrivacyPage.locator("//div[contains(@class,\"cardtype-select\")]//span[contains(@class,\"dropdown-icon\")]").first().click();
            PrivacyPage.querySelector("//li[@class='mdc-list-item']//span[contains(text(), 'Visa')]").click();
            PrivacyPage.waitForTimeout(2000);

            // card number
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"number-label\"]").fill("4000000000001000");
            PrivacyPage.waitForTimeout(2000);

            // card holder name
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"name-label\"]").fill("Lisa John");
            PrivacyPage.waitForTimeout(2000);

            // exp month
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-month-label\"]").fill("06");
            PrivacyPage.waitForTimeout(2000);
            PrivacyPage.pause();

            // exp year
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-year-label\"]").fill("26");
            PrivacyPage.waitForTimeout(2000);

            // cvv
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"cvv-label\"]").fill("123");
            PrivacyPage.waitForTimeout(2000);

            //name and street
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"street-label\"]").fill("street name");
            PrivacyPage.waitForTimeout(2000);

            // zip name
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"zip-label\"]").fill("123456");
            PrivacyPage.waitForTimeout(2000);

            // city
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"city-label\"]").fill("Frankfurt");
            PrivacyPage.waitForTimeout(2000);

            // terms and cond
            PrivacyPage.locator("//input[@type='checkbox' and contains(@class, 'mdc-checkbox__native-control') and @id='terms-checkbox']").first().click();
            PrivacyPage.waitForTimeout(5000);
            PrivacyPage.pause();

            // pay now
            PrivacyPage.locator("//span[contains(text(), 'Pay Now')]").first().click();
            System.out.println("PNR created : successfully");
            Thread.sleep(90000);
            PrivacyPage.pause();

            privacyPage.locator("//div[text()='Your booking is confirmed!']").click();
            Thread.sleep(10000);
            PrivacyPage.pause();
            page.bringToFront();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
    }
//
    @And("User handle Privacy Settings Page and proceeds with LH booking")
    public void User_handle_Privacy_Settings_Page_004() {
        page.waitForTimeout(200);
        try {
            System.out.println("Privacy Handle Function Body");

            page.waitForPopup(new Page.WaitForPopupOptions()
                    .setPredicate(p -> p.context().pages().size() == 2), () ->
            {
                page.locator("(//button[text()='Submit'])[1]").click();

            });
            System.out.println("Submit button clicked: Successfully");

            List<Page> pages = page.context().pages();
            for (Page tabs : pages) {
                tabs.waitForLoadState();
                System.out.println(tabs.url());
            }
            Page PrivacyPage = pages.get(1);
            privacyPage = PrivacyPage;

            System.out.println("Submit button :New URL");
            PrivacyPage.waitForTimeout(3000);

            PrivacyPage.waitForLoadState();
            PrivacyPage.locator(commonActions.getLocatorStr("Privacy")).isVisible();

            PrivacyPage.click(commonActions.getLocatorStr("Privacy"));
            System.out.println("Privacy setting page handled : successfully");

            PrivacyPage.waitForLoadState(LoadState.LOAD);

            //Select classS
            PrivacyPage.locator("(//button[@data-fare-family-group='eco'])[1]").first().click();
            PrivacyPage.waitForTimeout(10000);

            //Select Fare class from Economy
            PrivacyPage.locator("//button[@id='selectFare-BFEUM2NC0E']//span[contains(text(),'Select')]").first().click();
            PrivacyPage.waitForTimeout(10000);
            PrivacyPage.pause();

            //select return class
            PrivacyPage.locator("(//button[@data-fare-family-group='eco'])[1]").first().click();
            PrivacyPage.waitForTimeout(10000);
            PrivacyPage.pause();

            //Select Fare class from econumy
            PrivacyPage.locator("//button[@id='selectFare-BFEUM2NC0E']//span[contains(text(),'Select')]").first().click();
            PrivacyPage.waitForTimeout(10000);
            PrivacyPage.pause();

            //Enter Passenger details
            PrivacyPage.locator("//span[text()='Enter passenger details']").first().click();
            PrivacyPage.waitForTimeout(10000);
            PrivacyPage.pause();

            //Enter First PAssenger Details
            //select First Name
            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("Test");
            PrivacyPage.waitForTimeout(3000);

            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("User");
            PrivacyPage.waitForTimeout(3000);

            PrivacyPage.locator("//input[@placeholder='Your email address']").fill("test@yopmail.com");
            PrivacyPage.waitForTimeout(3000);

            PrivacyPage.locator("//input[@placeholder='Your country calling code']").fill("+49");
            PrivacyPage.waitForTimeout(3000);

            PrivacyPage.locator("//input[@placeholder='Your mobile phone']").fill("123456");
            PrivacyPage.waitForTimeout(3000);

            //Next passerger
            PrivacyPage.locator("//span[text()='Next passenger']").first().click();
            PrivacyPage.waitForTimeout(3000);
            PrivacyPage.pause();

            //Enter the adult 2 information
            // Enter first name
            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("jon");
            PrivacyPage.waitForTimeout(5000);

            //Enter Last NAme
            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("lee");
            PrivacyPage.waitForTimeout(5000);

            //Next passerger
            PrivacyPage.locator("//span[text()='Next passenger']").first().click();
            PrivacyPage.waitForTimeout(3000);
            PrivacyPage.pause();

            //Enter the child information
            //Enter first name
            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("jolly");
            PrivacyPage.waitForTimeout(3000);

            //Enter Last Name
            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("lee");
            PrivacyPage.waitForTimeout(3000);

            //DOB
            PrivacyPage.locator("//span[@class='mat-mdc-button-touch-target']").click();
            PrivacyPage.waitForTimeout(3000);
            PrivacyPage.pause();

            //select year
            PrivacyPage.locator("//span[text()=' 2021 ']").click();
            PrivacyPage.waitForTimeout(3000);

            //select month
            PrivacyPage.locator("//span[text()=' OCT ']").click();
            PrivacyPage.waitForTimeout(3000);

            //select day
            PrivacyPage.locator("//span[text()=' 23 ']").click();
            //PrivacyPage.waitForTimeout(5000);
            Thread.sleep(90000);
            //PrivacyPage.waitForTimeout(30000);
            PrivacyPage.pause();

            //click on Confirm
            //PrivacyPage.locator("//span[contains(text(),'Confirm')]").first().click();
            PrivacyPage.locator("//button[contains(@class,\"mat-button\")]//span[contains(text(),'Confirm')]").first().click();
            PrivacyPage.waitForTimeout(30000);
            PrivacyPage.pause();

            //add extra baggage
            PrivacyPage.locator("//span[text()='Add extra bags']").click();
            PrivacyPage.waitForTimeout(3000);


            //click on plus 1st passenger
            PrivacyPage.locator("//button[@id='955d8f24-fcd1-45b2-add1-d5b3e612f104-plusButton']//i[@class='refx-icon-add']").click();
            PrivacyPage.waitForTimeout(3000);

            //click on plus 2st passenger
            PrivacyPage.locator("//button[@id='15ff26cc-df7b-4f19-824e-77324bfad1fa-plusButton']//i[@class='refx-icon-add']").click();
            PrivacyPage.waitForTimeout(3000);

            //Confirm your bag
            PrivacyPage.locator("//span[contains(text(),'Confirm your bags')]").first().click();
            PrivacyPage.waitForTimeout(30000);

            //Continue to Payment
            PrivacyPage.locator("//span[contains(text(),'Continue to payment')]").first().click();
            //PrivacyPage.locator("//button[contains(@class,\"mat-button\"mat-button)]//span[contains(text(),'Continue to payment')]").first().click();
            PrivacyPage.waitForTimeout(30000);

            // select credit card
            PrivacyPage.locator("//label[contains(text(),'Credit')]/..//input[@id='radio_1']").first().click();
            PrivacyPage.waitForTimeout(10000);
            PrivacyPage.pause();

            // creadit card type- Visa
            PrivacyPage.locator("//div[contains(@class,\"cardtype-select\")]//span[contains(@class,\"dropdown-icon\")]").first().click();
            PrivacyPage.querySelector("//li[@class='mdc-list-item']//span[contains(text(), 'Visa')]").click();
            PrivacyPage.waitForTimeout(2000);

            // card number
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"number-label\"]").fill("4000000000001000");
            PrivacyPage.waitForTimeout(2000);

            // card holder name
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"name-label\"]").fill("Lisa John");
            PrivacyPage.waitForTimeout(2000);

            // exp month
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-month-label\"]").fill("06");
            PrivacyPage.waitForTimeout(2000);
            PrivacyPage.pause();

            // exp year
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-year-label\"]").fill("26");
            PrivacyPage.waitForTimeout(2000);

            // cvv
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"cvv-label\"]").fill("123");
            PrivacyPage.waitForTimeout(2000);

            //name and street
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"street-label\"]").fill("street name");
            PrivacyPage.waitForTimeout(2000);

            // zip name
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"zip-label\"]").fill("123456");
            PrivacyPage.waitForTimeout(2000);

            // city
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"city-label\"]").fill("Frankfurt");
            PrivacyPage.waitForTimeout(2000);

            // terms and cond
            PrivacyPage.locator("//input[@type='checkbox' and contains(@class, 'mdc-checkbox__native-control') and @id='terms-checkbox']").first().click();
            PrivacyPage.waitForTimeout(5000);
            PrivacyPage.pause();

            // pay now
            PrivacyPage.locator("//span[contains(text(), 'Pay Now')]").first().click();
            System.out.println("PNR created : successfully");
            Thread.sleep(90000);
            PrivacyPage.pause();

            privacyPage.locator("//div[text()='Your booking is confirmed!']").click();
            Thread.sleep(10000);
            PrivacyPage.pause();
            page.bringToFront();
        }
            catch (Exception e)
            {
                e.printStackTrace();
                LOGGER.error(e.getMessage());
            }
        }
//
    @And("User handles Privacy Settings Page and proceeds with LH bookings")
    public void User_handle_Privacy_Settings_Page_005() {
        page.waitForTimeout(200);
        try {
            System.out.println("Privacy Handle Function Body");

            page.waitForPopup(new Page.WaitForPopupOptions()
                    .setPredicate(p -> p.context().pages().size() == 2), () ->
            {
                page.locator("(//button[text()='Submit'])[1]").click();

            });
            System.out.println("Submit button clicked: Successfully");

            List<Page> pages = page.context().pages();
            for (Page tabs : pages) {
                tabs.waitForLoadState();
                System.out.println(tabs.url());
            }
            Page PrivacyPage = pages.get(1);
            privacyPage=PrivacyPage;

            System.out.println("Submit button :New URL");
            PrivacyPage.waitForTimeout(3000);

            PrivacyPage.waitForLoadState();
            PrivacyPage.locator(commonActions.getLocatorStr("Privacy")).isVisible();

            PrivacyPage.click(commonActions.getLocatorStr("Privacy"));
            System.out.println("Privacy setting page handled : successfully");

            PrivacyPage.waitForLoadState(LoadState.LOAD);

            //Select classS
            PrivacyPage.locator("(//div[text()='Economy'])[1]").first().click();

            //Select class from econumy
            PrivacyPage.locator("//button[@id=\"selectFare-STWWM4FF0E\"]/span[1]").first().click();
            PrivacyPage.waitForTimeout(10000);

            //select return class
            PrivacyPage.locator("(//div[text()='Economy'])[1]").first().click();

            //Select class from econumy
            PrivacyPage.locator("//button[@id=\"selectFare-STWWM4FF0E\"]/span[1]").first().click();
            PrivacyPage.waitForTimeout(10000);

            //Enter Passenger details
            PrivacyPage.locator("//span[text()='Enter passenger details']").first().click();

            //select First Name
            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("Test");
            PrivacyPage.waitForTimeout(3000);

            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("User");
            PrivacyPage.waitForTimeout(3000);

            PrivacyPage.locator("//input[@placeholder='Your email address']").fill("test@yopmail.com");
            PrivacyPage.waitForTimeout(3000);

            PrivacyPage.locator("//input[@placeholder='Your country calling code']").fill("+49");
            PrivacyPage.waitForTimeout(3000);

            PrivacyPage.locator("//input[@placeholder='Your mobile phone']").fill("123456");
            PrivacyPage.waitForTimeout(3000);
            PrivacyPage.pause();

            //click on Confirm
            PrivacyPage.locator("//button[contains(@class,\"mat-button\")]//span[contains(text(),'Confirm')]").first().click();
            PrivacyPage.waitForTimeout(30000);
            PrivacyPage.pause();

            //add extra baggage
            PrivacyPage.locator("//span[text()='Add extra bags']").click();
            PrivacyPage.waitForTimeout(3000);


            //click on plus 1st passenger
            PrivacyPage.locator("//button[@id='955d8f24-fcd1-45b2-add1-d5b3e612f104-plusButton']//i[@class='refx-icon-add']").click();
            PrivacyPage.waitForTimeout(3000);

            //Continue to Payment
            PrivacyPage.locator("//span[contains(text(),'Continue to payment')]").first().click();
            PrivacyPage.waitForTimeout(30000);
            PrivacyPage.pause();

            // select credit card
            PrivacyPage.locator("//label[contains(text(),'Credit')]/..//input[@id='radio_1']").first().click();
            PrivacyPage.waitForTimeout(10000);

            // creadit card type- Visa
            PrivacyPage.locator("//div[contains(@class,\"cardtype-select\")]//span[contains(@class,\"dropdown-icon\")]").first().click();
            PrivacyPage.querySelector("//li[@class='mdc-list-item']//span[contains(text(), 'Visa')]").click();
            PrivacyPage.waitForTimeout(2000);

            // card number
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"number-label\"]").fill("4000000000001000");
            PrivacyPage.waitForTimeout(2000);

            // card holder name
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"name-label\"]").fill("Lisa John");
            PrivacyPage.waitForTimeout(2000);

            // exp month
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-month-label\"]").fill("06");
            PrivacyPage.waitForTimeout(2000);

            // exp year
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-year-label\"]").fill("26");
            PrivacyPage.waitForTimeout(2000);

            // cvv
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"cvv-label\"]").fill("123");
            PrivacyPage.waitForTimeout(2000);

            //name and street
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"street-label\"]").fill("street name");
            PrivacyPage.waitForTimeout(2000);

            // zip name
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"zip-label\"]").fill("123456");
            PrivacyPage.waitForTimeout(2000);

            // city
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"city-label\"]").fill("Frankfurt");
            PrivacyPage.waitForTimeout(2000);

            // terms and cond
            PrivacyPage.locator("//input[@type='checkbox' and contains(@class, 'mdc-checkbox__native-control') and @id='terms-checkbox']").first().click();
            PrivacyPage.waitForTimeout(5000);

            // pay now
            PrivacyPage.locator("//span[contains(text(), 'Pay Now')]").first().click();
            System.out.println("PNR created : successfully");
            Thread.sleep(90000);

            privacyPage.locator("//div[text()='Your booking is confirmed!']").click();
            PrivacyPage.waitForTimeout(10000);
            Thread.sleep(10000);
            PrivacyPage.pause();
            //page.bringToFront();
        }

        catch (Exception e)
        {
            e.getMessage();
            LOGGER.error(e.getMessage());
        }
    }
//
    @And("User handles Privacy Settings Page and proceeds with LH bookingss")
    public void User_handle_Privacy_Settings_Page_006() {
        page.waitForTimeout(200);
        try {
            System.out.println("Privacy Handle Function Body");

            page.waitForPopup(new Page.WaitForPopupOptions()
                    .setPredicate(p -> p.context().pages().size() == 2), () ->
            {
                page.locator("(//button[text()='Submit'])[1]").click();

            });
            System.out.println("Submit button clicked: Successfully");

            List<Page> pages = page.context().pages();
            for (Page tabs : pages) {
                tabs.waitForLoadState();
                System.out.println(tabs.url());
            }
            Page PrivacyPage = pages.get(1);
            privacyPage = PrivacyPage;

            System.out.println("Submit button :New URL");
            PrivacyPage.waitForTimeout(3000);

            PrivacyPage.waitForLoadState();
            PrivacyPage.locator(commonActions.getLocatorStr("Privacy")).isVisible();

            PrivacyPage.click(commonActions.getLocatorStr("Privacy"));
            System.out.println("Privacy setting page handled : successfully");

            PrivacyPage.waitForLoadState(LoadState.LOAD);

            //Select classS
            PrivacyPage.locator("(//span[@class='refx-display-1 flight-starting-price-label ng-star-inserted'])[1]").first().click();
            //div[@id='2c19aaad-f819-4df5-b916-a0de20c5a38a-flightCardecoPremium-buttonLabel']
            PrivacyPage.waitForTimeout(10000);

            //Select Fare class from Economy
            PrivacyPage.locator("(//span[text()='Select'])[2]").first().click();
            PrivacyPage.waitForTimeout(10000);
//            PrivacyPage.pause();

            //select return class
//            PrivacyPage.locator("(//button[@data-fare-family-group='eco'])[1]").first().click();
//            PrivacyPage.waitForTimeout(10000);
//            PrivacyPage.pause();

            //Select Fare class from econumy
//            PrivacyPage.locator("//button[@id='selectFare-BFEUM2NC0E']//span[contains(text(),'Select')]").first().click();
//            PrivacyPage.waitForTimeout(10000);
//            PrivacyPage.pause();


            //Enter Passenger details
            PrivacyPage.locator("//span[text()='Enter passenger details']").first().click();
            PrivacyPage.waitForTimeout(10000);
            PrivacyPage.pause();

            //Enter First PAssenger Details
            //select First Name
            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("Test");
            PrivacyPage.waitForTimeout(3000);

            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("User");
            PrivacyPage.waitForTimeout(3000);

//select passenger DOB
            PrivacyPage.locator("//span[@class='mat-mdc-button-touch-target']").first().click();
            PrivacyPage.waitForTimeout(3000);

            PrivacyPage.locator("//span[text()=' 2011 ']").first().click();
            PrivacyPage.waitForTimeout(3000);

            PrivacyPage.locator("//span[text()=' DEC ']").first().click();
            PrivacyPage.waitForTimeout(3000);

            PrivacyPage.locator("//span[text()=' 30 ']").first().click();
            PrivacyPage.waitForTimeout(3000);

            //gender
            PrivacyPage.locator("//span[text()='Male']").first().click();
            PrivacyPage.waitForTimeout(3000);

            PrivacyPage.locator("//input[@placeholder='Your email address']").fill("test@yopmail.com");
            PrivacyPage.waitForTimeout(3000);

            PrivacyPage.locator("//input[@placeholder='Your country calling code']").fill("+49");
            PrivacyPage.waitForTimeout(3000);

            PrivacyPage.locator("//input[@placeholder='Your mobile phone']").fill("123456");
            PrivacyPage.waitForTimeout(3000);


            //click on Confirm
            //PrivacyPage.locator("//span[contains(text(),'Confirm')]").first().click();
            PrivacyPage.locator("//button[contains(@class,\"mat-button\")]//span[contains(text(),'Confirm')]").first().click();
            PrivacyPage.waitForTimeout(5000);
            PrivacyPage.pause();

            //Continue to Payment
            PrivacyPage.locator("//span[contains(text(),'Continue to payment')]").first().click();
            //PrivacyPage.locator("//button[contains(@class,\"mat-button\"mat-button)]//span[contains(text(),'Continue to payment')]").first().click();
            PrivacyPage.waitForTimeout(5000);

            // select credit card
            PrivacyPage.locator("//label[contains(text(),'Credit')]/..//input[@id='radio_1']").first().click();
            PrivacyPage.waitForTimeout(5000);
            PrivacyPage.pause();

            // creadit card type- Visa
            PrivacyPage.locator("//div[contains(@class,\"cardtype-select\")]//span[contains(@class,\"dropdown-icon\")]").first().click();
            PrivacyPage.querySelector("//li[@class='mdc-list-item']//span[contains(text(), 'Visa')]").click();
            PrivacyPage.waitForTimeout(2000);

            // card number
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"number-label\"]").fill("4000000000001000");
            PrivacyPage.waitForTimeout(2000);

            // card holder name
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"name-label\"]").fill("Lisa John");
            PrivacyPage.waitForTimeout(2000);

            // exp month
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-month-label\"]").fill("06");
            PrivacyPage.waitForTimeout(2000);
            PrivacyPage.pause();

            // exp year
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-year-label\"]").fill("26");
            PrivacyPage.waitForTimeout(2000);

            // cvv
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"cvv-label\"]").fill("123");
            PrivacyPage.waitForTimeout(2000);

            //name and street
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"street-label\"]").fill("street name");
            PrivacyPage.waitForTimeout(2000);

            // zip name
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"zip-label\"]").fill("123456");
            PrivacyPage.waitForTimeout(2000);

            // city
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"city-label\"]").fill("Frankfurt");
            PrivacyPage.waitForTimeout(2000);

            // terms and cond
            PrivacyPage.locator("//input[@type='checkbox' and contains(@class, 'mdc-checkbox__native-control') and @id='terms-checkbox']").first().click();
            PrivacyPage.waitForTimeout(5000);
            PrivacyPage.pause();

            // pay now
            PrivacyPage.locator("//span[contains(text(), 'Pay Now')]").first().click();
            System.out.println("PNR created : successfully");
            Thread.sleep(3000);
            PrivacyPage.pause();

            privacyPage.locator("//div[text()='Your booking is confirmed!']").click();
            Thread.sleep(5000);
            PrivacyPage.pause();
            page.bringToFront();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
    }
//
//    @And("User handles Privacy Settings Page and proceedss with LH booking")
//    public void User_handle_Privacy_Settings_Page_007() {
//        page.waitForTimeout(200);
//        try {
//            System.out.println("Privacy Handle Function Body");
//
//            page.waitForPopup(new Page.WaitForPopupOptions()
//                    .setPredicate(p -> p.context().pages().size() == 2), () ->
//            {
//                page.locator("(//button[text()='Submit'])[1]").click();
//
//            });
//            System.out.println("Submit button clicked: Successfully");
//
//            List<Page> pages = page.context().pages();
//            for (Page tabs : pages) {
//                tabs.waitForLoadState();
//                System.out.println(tabs.url());
//            }
//            Page PrivacyPage = pages.get(1);
//            privacyPage = PrivacyPage;
//
//            System.out.println("Submit button :New URL");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.waitForLoadState();
//            PrivacyPage.locator(commonActions.getLocatorStr("Privacy")).isVisible();
//
//            PrivacyPage.click(commonActions.getLocatorStr("Privacy"));
//            System.out.println("Privacy setting page handled : successfully");
//
//            PrivacyPage.waitForLoadState(LoadState.LOAD);
//
//            //Select classS
//            PrivacyPage.locator("(//span[@class='refx-display-1 flight-starting-price-label ng-star-inserted'])[1]").first().click();
//            //div[@id='2c19aaad-f819-4df5-b916-a0de20c5a38a-flightCardecoPremium-buttonLabel']
//            PrivacyPage.waitForTimeout(10000);
//
//            //Select Fare class from Economy
//            PrivacyPage.locator("(//span[text()='Select'])[2]").first().click();
//            PrivacyPage.waitForTimeout(10000);
////            PrivacyPage.pause();
//
//            //select return class
////            PrivacyPage.locator("(//button[@data-fare-family-group='eco'])[1]").first().click();
////            PrivacyPage.waitForTimeout(10000);
////            PrivacyPage.pause();
//
//            //Select Fare class from econumy
////            PrivacyPage.locator("//button[@id='selectFare-BFEUM2NC0E']//span[contains(text(),'Select')]").first().click();
////            PrivacyPage.waitForTimeout(10000);
////            PrivacyPage.pause();
//
//            //Enter Passenger details
//            PrivacyPage.locator("//span[text()='Enter passenger details']").first().click();
//            PrivacyPage.waitForTimeout(10000);
//            PrivacyPage.pause();
//
//            //Enter First PAssenger Details
//            //select First Name
//            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("Test");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("User");
//            PrivacyPage.waitForTimeout(3000);
//// select gender
//
//
//            PrivacyPage.locator("//input[@placeholder='Your email address']").fill("test@yopmail.com");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your country calling code']").fill("+49");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your mobile phone']").fill("123456");
//            PrivacyPage.waitForTimeout(3000);
//
//           // Next passerger
//            PrivacyPage.locator("//span[text()='Next passenger']").first().click();
//            PrivacyPage.waitForTimeout(3000);
////            PrivacyPage.pause();
//
////            Enter the adult 2 information
////             Enter first name
//            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("jon");
//            PrivacyPage.waitForTimeout(5000);
//
//            //Enter Last NAme
//            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("lee");
//            PrivacyPage.waitForTimeout(5000);
//            PrivacyPage.pause();
//            // select calender
//            PrivacyPage.locator("//span[@class='mat-mdc-button-touch-target']").click();
//            PrivacyPage.waitForTimeout(3000);
//            //span[@class='mat-mdc-button-touch-target']
//// select year
//            PrivacyPage.locator("//span[text()=' 2021 ']").click();
//            PrivacyPage.waitForTimeout(3000);
//
//            //select month
//            PrivacyPage.locator("//span[text()=' OCT ']").click();
//            PrivacyPage.waitForTimeout(3000);
//
//            //select day
//            PrivacyPage.locator("//span[text()=' 30 ']").click();
//            //PrivacyPage.waitForTimeout(5000);
//            Thread.sleep(3000);
//            //PrivacyPage.waitForTimeout(30000);
//            PrivacyPage.pause();
//
//            //Next passerger
//            PrivacyPage.locator("//span[text()='Next passenger']").first().click();
//            PrivacyPage.waitForTimeout(3000);
//            PrivacyPage.pause();
//
//
//            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("jolly");
//            PrivacyPage.waitForTimeout(3000);
//
//            //Enter Last Name
//            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("lee");
//            PrivacyPage.waitForTimeout(3000);
//
//            //DOB
//            PrivacyPage.locator("//span[@class='mat-mdc-button-touch-target']").click();
//            PrivacyPage.waitForTimeout(3000);
//            PrivacyPage.pause();
//
//           // select year
//            PrivacyPage.locator("//span[text()=' 2023 ']").click();
//            PrivacyPage.waitForTimeout(3000);
//
//            //select month
//            PrivacyPage.locator("//span[text()=' OCT ']").click();
//            PrivacyPage.waitForTimeout(3000);
//
//            //select day
//            PrivacyPage.locator("//span[text()=' 23 ']").click();
//            //PrivacyPage.waitForTimeout(5000);
//            Thread.sleep(3000);
//            //PrivacyPage.waitForTimeout(30000);
//            PrivacyPage.pause();
//
//            //click on Confirm
//            //PrivacyPage.locator("//span[contains(text(),'Confirm')]").first().click();
//            PrivacyPage.locator("//button[contains(@class,\"mat-button\")]//span[contains(text(),'Confirm')]").first().click();
//            PrivacyPage.waitForTimeout(5000);
//            PrivacyPage.pause();
//
//            //Continue to Payment
//            PrivacyPage.locator("//span[contains(text(),'Continue to payment')]").first().click();
//            //PrivacyPage.locator("//button[contains(@class,\"mat-button\"mat-button)]//span[contains(text(),'Continue to payment')]").first().click();
//            PrivacyPage.waitForTimeout(5000);
//
//            // select credit card
//            PrivacyPage.locator("//label[contains(text(),'Credit')]/..//input[@id='radio_1']").first().click();
//            PrivacyPage.waitForTimeout(5000);
//            PrivacyPage.pause();
//
//            // creadit card type- Visa
//            PrivacyPage.locator("//div[contains(@class,\"cardtype-select\")]//span[contains(@class,\"dropdown-icon\")]").first().click();
//            PrivacyPage.querySelector("//li[@class='mdc-list-item']//span[contains(text(), 'Visa')]").click();
//            PrivacyPage.waitForTimeout(2000);
//
//            // card number
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"number-label\"]").fill("4000000000001000");
//            PrivacyPage.waitForTimeout(2000);
//
//            // card holder name
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"name-label\"]").fill("Lisa John");
//            PrivacyPage.waitForTimeout(2000);
//
//            // exp month
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-month-label\"]").fill("06");
//            PrivacyPage.waitForTimeout(2000);
//            PrivacyPage.pause();
//
//            // exp year
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-year-label\"]").fill("26");
//            PrivacyPage.waitForTimeout(2000);
//
//            // cvv
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"cvv-label\"]").fill("123");
//            PrivacyPage.waitForTimeout(2000);
//
//            //name and street
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"street-label\"]").fill("street name");
//            PrivacyPage.waitForTimeout(2000);
//
//            // zip name
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"zip-label\"]").fill("123456");
//            PrivacyPage.waitForTimeout(2000);
//
//            // city
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"city-label\"]").fill("Frankfurt");
//            PrivacyPage.waitForTimeout(2000);
//
//            // terms and cond
//            PrivacyPage.locator("//input[@type='checkbox' and contains(@class, 'mdc-checkbox__native-control') and @id='terms-checkbox']").first().click();
//            PrivacyPage.waitForTimeout(5000);
//            PrivacyPage.pause();
//
//            // pay now
//            PrivacyPage.locator("//span[contains(text(), 'Pay Now')]").first().click();
//            System.out.println("PNR created : successfully");
//            Thread.sleep(3000);
//            PrivacyPage.pause();
//
//            privacyPage.locator("//div[text()='Your booking is confirmed!']").click();
//            Thread.sleep(5000);
//            PrivacyPage.pause();
//            page.bringToFront();
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            LOGGER.error(e.getMessage());
//        }
//    }
//
//    @And("User handles Privacy Settingss Page and proceeds with LH booking")
//    public void User_handle_Privacy_Settings_Page_008() {
//        page.waitForTimeout(200);
//        try {
//            System.out.println("Privacy Handle Function Body");
//
//            page.waitForPopup(new Page.WaitForPopupOptions()
//                    .setPredicate(p -> p.context().pages().size() == 2), () ->
//            {
//                page.locator("(//button[text()='Submit'])[1]").click();
//
//            });
//            System.out.println("Submit button clicked: Successfully");
//
//            List<Page> pages = page.context().pages();
//            for (Page tabs : pages) {
//                tabs.waitForLoadState();
//                System.out.println(tabs.url());
//            }
//            Page PrivacyPage = pages.get(1);
//            privacyPage = PrivacyPage;
//
//            System.out.println("Submit button :New URL");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.waitForLoadState();
//            PrivacyPage.locator(commonActions.getLocatorStr("Privacy")).isVisible();
//
//            PrivacyPage.click(commonActions.getLocatorStr("Privacy"));
//            System.out.println("Privacy setting page handled : successfully");
//
//            PrivacyPage.waitForLoadState(LoadState.LOAD);
//
//            //Select classS
//            PrivacyPage.locator("(//span[@class='refx-display-1 flight-starting-price-label ng-star-inserted'])[1]").first().click();
//            //div[@id='2c19aaad-f819-4df5-b916-a0de20c5a38a-flightCardecoPremium-buttonLabel']
//            PrivacyPage.waitForTimeout(10000);
//
//            //Select Fare class from Economy
//            PrivacyPage.locator("(//span[text()='Select'])[4]").first().click();
//            PrivacyPage.waitForTimeout(10000);
//            PrivacyPage.pause();
//
//            //select return class
////            PrivacyPage.locator("(//button[@data-fare-family-group='eco'])[1]").first().click();
////            PrivacyPage.waitForTimeout(10000);
////            PrivacyPage.pause();
//
//            //Select Fare class from econumy
////            PrivacyPage.locator("//button[@id='selectFare-BFEUM2NC0E']//span[contains(text(),'Select')]").first().click();
////            PrivacyPage.waitForTimeout(10000);
////            PrivacyPage.pause();
//
//            //Enter Passenger details
//            PrivacyPage.locator("//span[text()='Enter passenger details']").first().click();
//            PrivacyPage.waitForTimeout(10000);
//            PrivacyPage.pause();
//
//            //Enter First PAssenger Details
//            //select First Name
//            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("Test");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("User");
//            PrivacyPage.waitForTimeout(3000);
//// select gender
//            PrivacyPage.locator("//span[text()='Male']").first().click();
//            PrivacyPage.waitForTimeout(3000);
//
////enter date of birth
//            PrivacyPage.locator("//span[@class='mat-mdc-button-touch-target']").first().click();
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//span[text()=' 2011 ']").first().click();
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//span[text()=' NOV ']").first().click();
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//span[text()=' 30 ']").first().click();
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your email address']").fill("test@yopmail.com");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your country calling code']").fill("+49");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your mobile phone']").fill("123456");
//            PrivacyPage.waitForTimeout(3000);
//
//            //Next passerger
////            PrivacyPage.locator("//span[text()='Next passenger']").first().click();
////            PrivacyPage.waitForTimeout(3000);
////            PrivacyPage.pause();
//
//            //Enter the adult 2 information
//            // Enter first name
////            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("jon");
////            PrivacyPage.waitForTimeout(5000);
////
////            //Enter Last NAme
////            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("lee");
////            PrivacyPage.waitForTimeout(5000);
////
////            //Next passerger
////            PrivacyPage.locator("//span[text()='Next passenger']").first().click();
////            PrivacyPage.waitForTimeout(3000);
////            PrivacyPage.pause();
//
//            //Enter the child information
//            //Enter first name
////            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("jolly");
////            PrivacyPage.waitForTimeout(3000);
////
////            //Enter Last Name
////            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("lee");
////            PrivacyPage.waitForTimeout(3000);
////
////            //DOB
////            PrivacyPage.locator("//span[@class='mat-mdc-button-touch-target']").click();
////            PrivacyPage.waitForTimeout(3000);
////            PrivacyPage.pause();
//
//            //select year
////            PrivacyPage.locator("//span[text()=' 2021 ']").click();
////            PrivacyPage.waitForTimeout(3000);
////
////            //select month
////            PrivacyPage.locator("//span[text()=' OCT ']").click();
////            PrivacyPage.waitForTimeout(3000);
////
////            //select day
////            PrivacyPage.locator("//span[text()=' 23 ']").click();
////            //PrivacyPage.waitForTimeout(5000);
////            Thread.sleep(90000);
////            //PrivacyPage.waitForTimeout(30000);
////            PrivacyPage.pause();
//
//            //click on Confirm
//            //PrivacyPage.locator("//span[contains(text(),'Confirm')]").first().click();
//            PrivacyPage.locator("//button[contains(@class,\"mat-button\")]//span[contains(text(),'Confirm')]").first().click();
//            PrivacyPage.waitForTimeout(30000);
//            PrivacyPage.pause();
//
//            //Continue to Payment
//            PrivacyPage.locator("//span[contains(text(),'Continue to payment')]").first().click();
//            //PrivacyPage.locator("//button[contains(@class,\"mat-button\"mat-button)]//span[contains(text(),'Continue to payment')]").first().click();
//            PrivacyPage.waitForTimeout(30000);
//
//            // select credit card
//            PrivacyPage.locator("//label[contains(text(),'Credit')]/..//input[@id='radio_1']").first().click();
//            PrivacyPage.waitForTimeout(10000);
//            PrivacyPage.pause();
//
//            // creadit card type- Visa
//            PrivacyPage.locator("//div[contains(@class,\"cardtype-select\")]//span[contains(@class,\"dropdown-icon\")]").first().click();
//            //PrivacyPage.querySelector("//li[@class='mdc-list-item mdc-list-item--selected']//span[contains(text(), 'Mastercard')]").click();
//            PrivacyPage.querySelector("//span[contains(text(), 'Mastercard')]").click();
//            PrivacyPage.waitForTimeout(2000);
//
//            // card number
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"number-label\"]").fill("5200000000001096");
//            PrivacyPage.waitForTimeout(2000);
//
//            // card holder name
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"name-label\"]").fill("Lisa John");
//            PrivacyPage.waitForTimeout(2000);
//
//            // exp month
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-month-label\"]").fill("06");
//            PrivacyPage.waitForTimeout(2000);
//            PrivacyPage.pause();
//
//            // exp year
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-year-label\"]").fill("26");
//            PrivacyPage.waitForTimeout(2000);
//
//            // cvv
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"cvv-label\"]").fill("123");
//            PrivacyPage.waitForTimeout(2000);
//
//            //name and street
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"street-label\"]").fill("street name");
//            PrivacyPage.waitForTimeout(2000);
//
//            // zip name
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"zip-label\"]").fill("123456");
//            PrivacyPage.waitForTimeout(2000);
//
//            // city
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"city-label\"]").fill("Frankfurt");
//            PrivacyPage.waitForTimeout(2000);
//
//            // terms and cond
//            PrivacyPage.locator("//input[@type='checkbox' and contains(@class, 'mdc-checkbox__native-control') and @id='terms-checkbox']").first().click();
//            PrivacyPage.waitForTimeout(5000);
//            PrivacyPage.pause();
//
//            // pay now
//            PrivacyPage.locator("//span[contains(text(), 'Pay Now')]").first().click();
//            System.out.println("PNR created : successfully");
//            Thread.sleep(90000);
//            PrivacyPage.pause();
//
//            privacyPage.locator("//div[text()='Your booking is confirmed!']").click();
//            Thread.sleep(10000);
//            PrivacyPage.pause();
//            page.bringToFront();
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            LOGGER.error(e.getMessage());
//        }
//    }
//
@And("User handles Privacy Settings Page and proceeds with OS booking")
public void User_handle_Privacy_Settings_Page_OS_002() {
    page.waitForTimeout(200);
    try {
        System.out.println("Privacy Handle Function Body");

        page.waitForPopup(new Page.WaitForPopupOptions()
                .setPredicate(p -> p.context().pages().size() == 2), () ->
        {
            page.locator("(//button[text()='Submit'])[1]").click();
        });

        System.out.println("Submit button clicked: Successfully");

        List<Page> pages = page.context().pages();
        for (Page tabs : pages) {
            tabs.waitForLoadState();
            System.out.println(tabs.url());
        }
        Page PrivacyPage = pages.get(1);
        privacyPage=PrivacyPage;

        System.out.println("Submit button :New URL");
        PrivacyPage.waitForTimeout(500);

        PrivacyPage.waitForLoadState();
        PrivacyPage.locator(commonActions.getLocatorStr("Privacy")).isVisible();

        PrivacyPage.click(commonActions.getLocatorStr("Privacy"));
        System.out.println("Privacy setting page handled : successfully");

        PrivacyPage.waitForLoadState(LoadState.LOAD);
        PrivacyPage.waitForTimeout(3000);


//            PrivacyPage.pause();


        //Select classS
        PrivacyPage.locator("(//div[contains(text(),'Economy')])[1]").first().click();

        //Select class economy
        PrivacyPage.locator("(//span[contains(text(),'Select')])[2]").first().click();
//            PrivacyPage.locator("//button[@id=\"selectFare-BFEUM2NC0E\"]/span[1]").first().click();
        PrivacyPage.waitForTimeout(3000);

        //Confirm and continue to next page
//            PrivacyPage.locator("//span[contains(text(),'Confirm and continue')]").first().click();

        //Select inward flight class
        PrivacyPage.locator("(//div[contains(text(),'Economy')])[1]").first().click();

        //Select inward class
        PrivacyPage.locator("(//span[contains(text(),'Select')])[1]").first().click();
        PrivacyPage.waitForTimeout(3000);


        //Enter Passenger details
        PrivacyPage.locator("//span[text()='Enter passenger details']").first().click();
        PrivacyPage.waitForTimeout(20000);


        //select First Name
        PrivacyPage.locator("//input[@placeholder='Your first name']").fill("Test");
        PrivacyPage.waitForTimeout(500);

        PrivacyPage.locator("//input[@placeholder='Your last name']").fill("User");
        PrivacyPage.waitForTimeout(500);
//            PrivacyPage.pause();

        //DOB
        PrivacyPage.locator("//span[@class='mat-mdc-button-touch-target']").click();
        PrivacyPage.waitForTimeout(500);

        //select year
        PrivacyPage.locator("//span[text()=' 1990 ']").click();
        PrivacyPage.waitForTimeout(500);

        //select month
        PrivacyPage.locator("//span[text()=' OCT ']").click();
        PrivacyPage.waitForTimeout(500);

        //select day
        PrivacyPage.locator("//span[text()=' 23 ']").click();
        PrivacyPage.waitForTimeout(500);

        //select gender
        PrivacyPage.locator("//span[contains(text(),'Male')]").first().click();

        //mail
        PrivacyPage.locator("//input[@placeholder='Your email address']").fill("test@yopmail.com");
        PrivacyPage.waitForTimeout(500);

        PrivacyPage.locator("//input[@placeholder='Your country calling code']").fill("+49");
        PrivacyPage.waitForTimeout(500);

        PrivacyPage.locator("//input[@placeholder='Your mobile phone']").fill("123456");
        PrivacyPage.waitForTimeout(500);
//            PrivacyPage.pause();

        //Enter next passerger
        PrivacyPage.locator("//span[text()='Next passenger']").first().click();
        PrivacyPage.waitForTimeout(10000);
//            PrivacyPage.pause();

        //Enter first name
        PrivacyPage.locator("//input[@placeholder='Your first name']").fill("jon");
        PrivacyPage.waitForTimeout(500);

        //Enter Last NAme
        PrivacyPage.locator("//input[@placeholder='Your last name']").fill("dey");
        PrivacyPage.waitForTimeout(500);

        //DOB
        PrivacyPage.locator("//span[@class='mat-mdc-button-touch-target']").click();
        PrivacyPage.waitForTimeout(500);

        //select year
        PrivacyPage.locator("//span[text()=' 2021 ']").click();
        PrivacyPage.waitForTimeout(500);

        //select month
        PrivacyPage.locator("//span[text()=' OCT ']").click();
        PrivacyPage.waitForTimeout(500);

        //select day
        PrivacyPage.locator("//span[text()=' 23 ']").click();
        PrivacyPage.waitForTimeout(500);

        //select gender
        PrivacyPage.locator("//span[contains(text(),'Male')]").first().click();

        //click on Confirm
        PrivacyPage.locator("//button[contains(@class,\"mat-button\")]//span[contains(text(),'Confirm')]").first().click();
        PrivacyPage.waitForTimeout(2000);
//            PrivacyPage.pause();


        //Continue to Payment
        PrivacyPage.locator("//span[contains(text(),'Continue to payment')]").first().click();
        PrivacyPage.waitForTimeout(60000);
//            PrivacyPage.pause();

        // select credit card
        PrivacyPage.locator("//label[contains(text(),'Credit')]/..//input[@id='radio_1']").first().click();
        PrivacyPage.waitForTimeout(2000);

        // creadit card type- Visa
        PrivacyPage.locator("//div[contains(@class,\"cardtype-select\")]//span[contains(@class,\"dropdown-icon\")]").first().click();
        PrivacyPage.querySelector("//span[contains(text(), 'Visa')]").click();
        PrivacyPage.waitForTimeout(500);

        // card number
        PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"number-label\"]").fill("4000000000001000");
        PrivacyPage.waitForTimeout(500);

        // card holder name
        PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"name-label\"]").fill("Test User");
        PrivacyPage.waitForTimeout(500);

        // exp month
        PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-month-label\"]").fill("06");
        PrivacyPage.waitForTimeout(500);

        // exp year
        PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-year-label\"]").fill("26");
        PrivacyPage.waitForTimeout(500);

        // cvv
        PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"cvv-label\"]").fill("123");
        PrivacyPage.waitForTimeout(500);

        //name and street
        PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"street-label\"]").fill("street name");
        PrivacyPage.waitForTimeout(500);

        // zip name
        PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"zip-label\"]").fill("123456");
        PrivacyPage.waitForTimeout(500);

        // city
        PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"city-label\"]").fill("Frankfurt");
        PrivacyPage.waitForTimeout(500);

        // terms and cond
        PrivacyPage.locator("//input[@type='checkbox' and contains(@class, 'mdc-checkbox__native-control') and @id='terms-checkbox']").first().click();
        PrivacyPage.waitForTimeout(1000);

        // pay now
        PrivacyPage.locator("//span[contains(text(), 'Pay Now')]").first().click();
        System.out.println("PNR created : successfully");
        Thread.sleep(60000);

        privacyPage.locator("//div[text()='Your booking is confirmed!']").click();
//            page.screenshot(new Page.ScreenshotOptions().setPath("screenshot.png"));
        PrivacyPage.waitForTimeout(10000);
        Thread.sleep(10000);
//            PrivacyPage.pause();

        page.bringToFront();
    }

    catch (Exception e)
    {
        e.getMessage();
        LOGGER.error(e.getMessage());
    }
}

    @And("User handles Privacy Settings Page and proceeds with OS rebooking")
    public void User_handle_Privacy_Settings_Page_OS_002_RB()  {
        page.waitForTimeout(200);

        try {

            System.out.println("Privacy Handle Function Body");

            page.waitForPopup(new Page.WaitForPopupOptions(), () ->
            {
                page.locator("(//button[text()='Submit'])[1]").click();
            });

            System.out.println("Submit button clicked: Successfully");

            List<Page> pages = page.context().pages();
            for (Page tabs : pages) {
                tabs.waitForLoadState();
                System.out.println(tabs.url());
            }
            Page PrivacyPage = pages.get(1);
            privacyPage=PrivacyPage;

            PrivacyPage.waitForTimeout(5000);

            System.out.println("Submit button :New URL");
            PrivacyPage.waitForTimeout(5000);

            PrivacyPage.waitForLoadState();

            ElementHandle privacyElement = privacyPage.querySelector(commonActions.getLocatorStr("Privacy"));
            if (privacyElement != null) {
                if (privacyElement.isVisible()) {
                    privacyElement.click();
                    System.out.println("Privacy setting page handled successfully");
                } else {
                    System.out.println("Privacy setting element found but not visible");
                }
            } else {
                System.out.println("Privacy setting element not found");
            }

            PrivacyPage.waitForLoadState(LoadState.LOAD);
            PrivacyPage.waitForTimeout(3000);
//            Privacy page handled till here

//            PrivacyPage.pause();

//            Add extra baggage
            privacyPage.locator("//span[text()='Add extra bags']").click();
            privacyPage.waitForTimeout(2000);

//            Add for inbound flight for both pax
            privacyPage.locator("(//button[@aria-label='Add'])[3]").click();
            privacyPage.waitForTimeout(500);
            privacyPage.locator("(//button[@aria-label='Add'])[4]").click();

//            confirm bags
            privacyPage.locator("//span[text()='Confirm your bags']").click();
            privacyPage.waitForTimeout(10000);

//            Continue to payment
            privacyPage.locator("//span[text()='Continue to payment']").click();
            privacyPage.waitForTimeout(5000);

            PrivacyPage.locator("//input[@id='radio_3']").first().click();
            PrivacyPage.waitForTimeout(2000);

            privacyPage.locator("//span[@id='email-label-pp']").fill("amadeus@test.com");


            //name and street
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"street-label\"]").fill("street name");
            PrivacyPage.waitForTimeout(500);

            // zip name
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"zip-label\"]").fill("123456");
            PrivacyPage.waitForTimeout(500);

            // city
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"city-label\"]").fill("Frankfurt");
            PrivacyPage.waitForTimeout(500);

            // terms and cond
            PrivacyPage.locator("//input[@type='checkbox' and contains(@class, 'mdc-checkbox__native-control') and @id='terms-checkbox']").first().click();
            PrivacyPage.waitForTimeout(1000);

            // pay now
            PrivacyPage.locator("//span[contains(text(), 'Pay Now')]").first().click();

//            paypal page
            privacyPage.locator("//input[@id='password']").fill("c#Ag?d2Es3Us");

            privacyPage.locator("//button[@value='Login']").click();
            privacyPage.waitForTimeout(2000);

            privacyPage.locator("//span[text()='Rabobank Nederland']").click();
            privacyPage.locator("//button[text()='Continue to Review Order']").click();
            privacyPage.waitForTimeout(10000);




            System.out.println("PNR created : successfully");
            Thread.sleep(60000);

            PrivacyPage.locator("//span[text()='Your booking reference is']").isVisible();
            Thread.sleep(2000);




//          Change Flight scenario


//          Change flight
            PrivacyPage.locator("//span[contains(text(),'Change flights')]").first().click();
            PrivacyPage.waitForTimeout(500);

//          Debug panel
            PrivacyPage.locator("//p[normalize-space()='LHG debug panel']").first().click();
            PrivacyPage.waitForTimeout(500);

//          Custserv-3464
            PrivacyPage.locator("//span[text()='CUSTSERV-3464']").first().click();
            PrivacyPage.waitForTimeout(500);

            PrivacyPage.locator("//span[normalize-space()='Apply']").first().click();
            PrivacyPage.waitForTimeout(5000);

            PrivacyPage.pause();

//            select outbound
            privacyPage.locator("//mat-checkbox[@id=\"mat-checkbox-1\"]").click();

//          Select date
            PrivacyPage.getByPlaceholder("dd/mm/yyyy").fill("2023-11-28");

            PrivacyPage.locator("//span[text()='Search']").first().click();
            PrivacyPage.waitForTimeout(500);

            PrivacyPage.locator("//p[normalize-space()='LHG debug panel']").first().click();
            PrivacyPage.waitForTimeout(1000);

            PrivacyPage.locator("//span[text()='CUSTSERV-2616']").first().click();
            PrivacyPage.waitForTimeout(500);

            PrivacyPage.locator("//span[normalize-space()='Apply']").first().click();
            PrivacyPage.waitForTimeout(5000);

            PrivacyPage.locator("//span[text()='Original flights details']").isVisible();
            PrivacyPage.waitForTimeout(500);

//            PrivacyPage.pause();

            PrivacyPage.locator("(//span[text()='from'])[1]").click();
            PrivacyPage.locator("(//span[@class='mat-button-wrapper']//span[text()='Select'])[1]").click();
            PrivacyPage.waitForTimeout(20000);

            privacyPage.locator("//span[text()='Continue to payment']").click();
            privacyPage.waitForTimeout(5000);

            PrivacyPage.locator("//label[contains(text(),'Credit')]/..//input[@id='radio_1']").first().click();
            PrivacyPage.waitForTimeout(2000);

            // creadit card type- Visa
            PrivacyPage.locator("//div[contains(@class,\"cardtype-select\")]//span[contains(@class,\"dropdown-icon\")]").first().click();
            PrivacyPage.querySelector("//span[contains(text(), 'Visa')]").click();
            PrivacyPage.waitForTimeout(500);

            // card number
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"number-label\"]").fill("4000000000001000");
            PrivacyPage.waitForTimeout(500);

            // card holder name
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"name-label\"]").fill("Test User");
            PrivacyPage.waitForTimeout(500);

            // exp month
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-month-label\"]").fill("06");
            PrivacyPage.waitForTimeout(500);

            // exp year
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-year-label\"]").fill("26");
            PrivacyPage.waitForTimeout(500);

            // cvv
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"cvv-label\"]").fill("123");
            PrivacyPage.waitForTimeout(500);

            //name and street
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"street-label\"]").fill("street name");
            PrivacyPage.waitForTimeout(500);

            // zip name
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"zip-label\"]").fill("123456");
            PrivacyPage.waitForTimeout(500);

            // city
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"city-label\"]").fill("Frankfurt");
            PrivacyPage.waitForTimeout(500);

            // terms and cond
            PrivacyPage.locator("//input[@type='checkbox' and contains(@class, 'mdc-checkbox__native-control') and @id='terms-checkbox']").first().click();
            PrivacyPage.waitForTimeout(1000);

            // pay now
            PrivacyPage.locator("//span[contains(text(), 'Pay Now')]").first().click();
            System.out.println("PNR created : successfully");
            Thread.sleep(60000);

            PrivacyPage.locator("//span[text()='Your booking reference is']").isVisible();
            Thread.sleep(2000);

        }

        catch (Exception e)
        {
            e.getMessage();
            LOGGER.error(e.getMessage());
        }
    }
    //
    @And("User handles Privacy Settings Page and proceeds with OS bookings")
    public void User_handle_Privacy_Settings_Page_OS_003() {
        page.waitForTimeout(200);
        try {
            System.out.println("Privacy Handle Function Body");
            page.waitForPopup(new Page.WaitForPopupOptions()
                    .setPredicate(p -> p.context().pages().size() == 2), () ->
            {
                page.locator("(//button[text()='Submit'])[1]").click();
            });
            System.out.println("Submit button clicked: Successfully");
            List<Page> pages = page.context().pages();
            for (Page tabs : pages) {
                tabs.waitForLoadState();
                System.out.println(tabs.url());
            }
            Page PrivacyPage = pages.get(1);
            privacyPage=PrivacyPage;
            System.out.println("Submit button :New URL");
            PrivacyPage.waitForTimeout(500);
            PrivacyPage.waitForLoadState();
            PrivacyPage.locator(commonActions.getLocatorStr("Privacy")).isVisible();
            PrivacyPage.click(commonActions.getLocatorStr("Privacy"));
            System.out.println("Privacy setting page handled : successfully");
            PrivacyPage.waitForLoadState(LoadState.LOAD);
            PrivacyPage.waitForTimeout(3000);

//            PrivacyPage.pause();

            //Select classS
            PrivacyPage.locator("(//div[contains(text(),'Economy')])[1]").first().click();
            //Select class economy
            PrivacyPage.locator("(//span[contains(text(),'Select')])[1]").first().click();
//            PrivacyPage.locator("//button[@id=\"selectFare-BFEUM2NC0E\"]/span[1]").first().click();
            PrivacyPage.waitForTimeout(3000);
            //Confirm and continue to next page
//            PrivacyPage.locator("//span[contains(text(),'Confirm and continue')]").first().click();
            //Enter Passenger details
            PrivacyPage.locator("//span[text()='Enter passenger details']").first().click();
            PrivacyPage.waitForTimeout(20000);
            //select First Name
            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("Test");
            PrivacyPage.waitForTimeout(500);
            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("User");
            PrivacyPage.waitForTimeout(500);
//            PrivacyPage.pause();
            //DOB
            PrivacyPage.locator("//span[@class='mat-mdc-button-touch-target']").click();
            PrivacyPage.waitForTimeout(500);
            //select year
            PrivacyPage.locator("//span[text()=' 1990 ']").click();
            PrivacyPage.waitForTimeout(500);
            //select month
            PrivacyPage.locator("//span[text()=' OCT ']").click();
            PrivacyPage.waitForTimeout(500);
            //select day
            PrivacyPage.locator("//span[text()=' 23 ']").click();
            PrivacyPage.waitForTimeout(500);
            //select gender
            PrivacyPage.locator("//span[contains(text(),'Male')]").first().click();
            //mail
            PrivacyPage.locator("//input[@placeholder='Your email address']").fill("test@yopmail.com");
            PrivacyPage.waitForTimeout(500);
            PrivacyPage.locator("//input[@placeholder='Your country calling code']").fill("+49");
            PrivacyPage.waitForTimeout(500);
            PrivacyPage.locator("//input[@placeholder='Your mobile phone']").fill("123456");
            PrivacyPage.waitForTimeout(500);
//            PrivacyPage.pause();
            //click on Confirm
            PrivacyPage.locator("//button[contains(@class,\"mat-button\")]//span[contains(text(),'Confirm')]").first().click();
            PrivacyPage.waitForTimeout(2000);
//            PrivacyPage.pause();

            //Continue to Payment
            PrivacyPage.locator("//span[contains(text(),'Continue to payment')]").first().click();
            PrivacyPage.waitForTimeout(10000);
//            PrivacyPage.pause();
            // select credit card
            PrivacyPage.locator("//label[contains(text(),'Credit')]/..//input[@id='radio_1']").first().click();
            PrivacyPage.waitForTimeout(2000);
            // creadit card type- Visa
            PrivacyPage.locator("//div[contains(@class,\"cardtype-select\")]//span[contains(@class,\"dropdown-icon\")]").first().click();
            PrivacyPage.querySelector("//span[contains(text(), 'UATP')]").click();
            PrivacyPage.waitForTimeout(500);
            // card number
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"number-label\"]").fill("192072420096379");
            PrivacyPage.waitForTimeout(500);
            // card holder name
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"name-label\"]").fill("Adult Test User");
            PrivacyPage.waitForTimeout(500);
            // exp month
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-month-label\"]").fill("06");
            PrivacyPage.waitForTimeout(500);
            // exp year
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-year-label\"]").fill("25");
            PrivacyPage.waitForTimeout(500);
            //name and street
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"street-label\"]").fill("street name");
            PrivacyPage.waitForTimeout(500);
            // zip name
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"zip-label\"]").fill("123456");
            PrivacyPage.waitForTimeout(500);
            // city
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"city-label\"]").fill("Frankfurt");
            PrivacyPage.waitForTimeout(00);
            // terms and cond
            PrivacyPage.locator("//input[@type='checkbox' and contains(@class, 'mdc-checkbox__native-control') and @id='terms-checkbox']").first().click();
            PrivacyPage.waitForTimeout(1000);
            // pay now
            PrivacyPage.locator("//span[contains(text(), 'Pay Now')]").first().click();
            System.out.println("PNR created : successfully");
            Thread.sleep(60000);
            privacyPage.locator("//div[text()='Your booking is confirmed!']").click();
//            page.screenshot(new Page.ScreenshotOptions().setPath("screenshot.png"));
            PrivacyPage.waitForTimeout(10000);
            Thread.sleep(10000);
            PrivacyPage.pause();
            page.bringToFront();
        }
        catch (Exception e)
        {
            e.getMessage();
            LOGGER.error(e.getMessage());
        }
    }

    @And("User handles Privacy Setting Page and proceeds with OS booking")
    public void User_handle_Privacy_Settings_Page_OS_004() {
        page.waitForTimeout(200);
        try {
            System.out.println("Privacy Handle Function Body");

            page.waitForPopup(new Page.WaitForPopupOptions()
                    .setPredicate(p -> p.context().pages().size() == 2), () ->
            {
                page.locator("(//button[text()='Submit'])[1]").click();

            });
            System.out.println("Submit button clicked: Successfully");

            List<Page> pages = page.context().pages();
            for (Page tabs : pages) {
                tabs.waitForLoadState();
                System.out.println(tabs.url());
            }
            Page PrivacyPage = pages.get(1);
            privacyPage=PrivacyPage;

            System.out.println("Submit button :New URL");
            PrivacyPage.waitForTimeout(500);

            PrivacyPage.waitForLoadState();
            PrivacyPage.locator(commonActions.getLocatorStr("Privacy")).isVisible();

            PrivacyPage.click(commonActions.getLocatorStr("Privacy"));
            System.out.println("Privacy setting page handled : successfully");

            PrivacyPage.waitForLoadState(LoadState.LOAD);
            PrivacyPage.waitForTimeout(3000);


            PrivacyPage.pause();


            //Select classS
            PrivacyPage.locator("(//div[contains(text(),'Economy')])[1]").first().click();

            //Select class economy
            PrivacyPage.locator("(//span[contains(text(),'Select')])[2]").first().click();
//            PrivacyPage.locator("//button[@id=\"selectFare-BFEUM2NC0E\"]/span[1]").first().click();
            PrivacyPage.waitForTimeout(3000);

            //Confirm and continue to next page
//            PrivacyPage.locator("//span[contains(text(),'Confirm and continue')]").first().click();

            //Select inward flight class
            PrivacyPage.locator("(//div[contains(text(),'Economy')])[1]").first().click();

            //Select inward class
            PrivacyPage.locator("(//span[contains(text(),'Select')])[2]").first().click();
            PrivacyPage.waitForTimeout(5000);


            //Enter Passenger details
            PrivacyPage.locator("//span[text()='Enter passenger details']").first().click();
            PrivacyPage.waitForTimeout(20000);


            //select First Name
            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("Test");
            PrivacyPage.waitForTimeout(500);

            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("User");
            PrivacyPage.waitForTimeout(500);
//            PrivacyPage.pause();

            //DOB
//            PrivacyPage.locator("//span[@class='mat-mdc-button-touch-target']").click();
//            PrivacyPage.waitForTimeout(500);

            //select year
//            PrivacyPage.locator("//span[text()=' 1990 ']").click();
//            PrivacyPage.waitForTimeout(500);

            //select month
//            PrivacyPage.locator("//span[text()=' OCT ']").click();
//            PrivacyPage.waitForTimeout(500);

            //select day
//            PrivacyPage.locator("//span[text()=' 23 ']").click();
//            PrivacyPage.waitForTimeout(500);

            //select gender
//            PrivacyPage.locator("//span[contains(text(),'Male')]").first().click();

            //mail
            PrivacyPage.locator("//input[@placeholder='Your email address']").fill("test@yopmail.com");
            PrivacyPage.waitForTimeout(500);

            PrivacyPage.locator("//input[@placeholder='Your country calling code']").fill("+49");
            PrivacyPage.waitForTimeout(500);

            PrivacyPage.locator("//input[@placeholder='Your mobile phone']").fill("123456");
            PrivacyPage.waitForTimeout(500);
//            PrivacyPage.pause();

            //Enter next passenger
            PrivacyPage.locator("//span[text()='Next passenger']").first().click();
            PrivacyPage.waitForTimeout(10000);
//            PrivacyPage.pause();

            //Enter first name
            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("tester");
            PrivacyPage.waitForTimeout(500);

            //Enter Last NAme
            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("users");
            PrivacyPage.waitForTimeout(500);

            //DOB
            PrivacyPage.locator("//span[@class='mat-mdc-button-touch-target']").click();
            PrivacyPage.waitForTimeout(500);

            //Enter next passenger
            PrivacyPage.locator("//span[text()='Next passenger']").first().click();
            PrivacyPage.waitForTimeout(10000);

            //Enter first name
            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("joh");
            PrivacyPage.waitForTimeout(500);

            //Enter Last NAme
            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("doe");
            PrivacyPage.waitForTimeout(500);

            //select year
            PrivacyPage.locator("//span[text()=' 2020 ']").click();
            PrivacyPage.waitForTimeout(500);

            //select month
            PrivacyPage.locator("//span[text()=' OCT ']").click();
            PrivacyPage.waitForTimeout(500);

            //select day
            PrivacyPage.locator("//span[text()=' 23 ']").click();
            PrivacyPage.waitForTimeout(500);


            //click on Confirm
            PrivacyPage.locator("//button[contains(@class,\"mat-button\")]//span[contains(text(),'Confirm')]").first().click();
            PrivacyPage.waitForTimeout(6000);
//            PrivacyPage.pause();


            //Continue to Payment
            PrivacyPage.locator("//span[contains(text(),'Continue to payment')]").first().click();
            PrivacyPage.waitForTimeout(60000);
//            PrivacyPage.pause();

            // select credit card
            PrivacyPage.locator("//label[contains(text(),'Credit')]/..//input[@id='radio_1']").first().click();
            PrivacyPage.waitForTimeout(2000);

            // creadit card type- Visa
            PrivacyPage.locator("//div[contains(@class,\"cardtype-select\")]//span[contains(@class,\"dropdown-icon\")]").first().click();
            PrivacyPage.querySelector("//span[contains(text(), 'Visa')]").click();
            PrivacyPage.waitForTimeout(500);

            // card number
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"number-label\"]").fill("4000000000001000");
            PrivacyPage.waitForTimeout(500);

            // card holder name
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"name-label\"]").fill("Test User");
            PrivacyPage.waitForTimeout(500);

            // exp month
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-month-label\"]").fill("06");
            PrivacyPage.waitForTimeout(500);

            // exp year
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-year-label\"]").fill("26");
            PrivacyPage.waitForTimeout(500);

            // cvv
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"cvv-label\"]").fill("123");
            PrivacyPage.waitForTimeout(500);

            //name and street
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"street-label\"]").fill("street name");
            PrivacyPage.waitForTimeout(500);

            // zip name
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"zip-label\"]").fill("123456");
            PrivacyPage.waitForTimeout(500);

            // city
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"city-label\"]").fill("Frankfurt");
            PrivacyPage.waitForTimeout(500);

            // terms and cond
            PrivacyPage.locator("//input[@type='checkbox' and contains(@class, 'mdc-checkbox__native-control') and @id='terms-checkbox']").first().click();
            PrivacyPage.waitForTimeout(1000);

            // pay now
            PrivacyPage.locator("//span[contains(text(), 'Pay Now')]").first().click();
            System.out.println("PNR created : successfully");
            Thread.sleep(60000);

            privacyPage.locator("//div[text()='Your booking is confirmed!']").click();
//            page.screenshot(new Page.ScreenshotOptions().setPath("screenshot.png"));
            PrivacyPage.waitForTimeout(10000);
            Thread.sleep(10000);
            PrivacyPage.pause();
            page.bringToFront();
        }

        catch (Exception e)
        {
            e.getMessage();
            LOGGER.error(e.getMessage());
        }
    }

    @And("User handles Privacy Settings Page and proceed with OS booking")
    public void User_handle_Privacy_Settings_Page_OS_005() {
        page.waitForTimeout(200);
        try {
            System.out.println("Privacy Handle Function Body");

            page.waitForPopup(new Page.WaitForPopupOptions()
                    .setPredicate(p -> p.context().pages().size() == 2), () ->
            {
                page.locator("(//button[text()='Submit'])[1]").click();

            });
            System.out.println("Submit button clicked: Successfully");

            List<Page> pages = page.context().pages();
            for (Page tabs : pages) {
                tabs.waitForLoadState();
                System.out.println(tabs.url());
            }
            Page PrivacyPage = pages.get(1);
            privacyPage=PrivacyPage;

            System.out.println("Submit button :New URL");
            PrivacyPage.waitForTimeout(500);

            PrivacyPage.waitForLoadState();
            PrivacyPage.locator(commonActions.getLocatorStr("Privacy")).isVisible();

            PrivacyPage.click(commonActions.getLocatorStr("Privacy"));
            System.out.println("Privacy setting page handled : successfully");

            PrivacyPage.waitForLoadState(LoadState.LOAD);
            PrivacyPage.waitForTimeout(3000);


            PrivacyPage.pause();


            //Select classS
            PrivacyPage.locator("(//div[contains(text(),'Economy')])[1]").first().click();

            //Select class economy
            PrivacyPage.locator("(//span[contains(text(),'Select')])[2]").first().click();
//            PrivacyPage.locator("//button[@id=\"selectFare-BFEUM2NC0E\"]/span[1]").first().click();
            PrivacyPage.waitForTimeout(3000);

            //Confirm and continue to next page
//            PrivacyPage.locator("//span[contains(text(),'Confirm and continue')]").first().click();

            //Select inward flight class
            PrivacyPage.locator("(//div[contains(text(),'Economy')])[1]").first().click();

            //Select inward class
            PrivacyPage.locator("(//span[contains(text(),'Select')])[1]").first().click();
            PrivacyPage.waitForTimeout(10000);


            //Enter Passenger details
            PrivacyPage.locator("//span[text()='Enter passenger details']").first().click();
            PrivacyPage.waitForTimeout(20000);


            //select First Name
            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("Test");
            PrivacyPage.waitForTimeout(500);

            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("User");
            PrivacyPage.waitForTimeout(500);
//            PrivacyPage.pause();

            //DOB
            PrivacyPage.locator("//span[@class='mat-mdc-button-touch-target']").click();
            PrivacyPage.waitForTimeout(500);

            //select year
            PrivacyPage.locator("//span[text()=' 1990 ']").click();
            PrivacyPage.waitForTimeout(500);

            //select month
            PrivacyPage.locator("//span[text()=' OCT ']").click();
            PrivacyPage.waitForTimeout(500);

            //select day
            PrivacyPage.locator("//span[text()=' 23 ']").click();
            PrivacyPage.waitForTimeout(500);

            //select gender
            PrivacyPage.locator("//span[contains(text(),'Female')]").first().click();

            //mail
            PrivacyPage.locator("//input[@placeholder='Your email address']").fill("test@yopmail.com");
            PrivacyPage.waitForTimeout(500);

            PrivacyPage.locator("//input[@placeholder='Your country calling code']").fill("+49");
            PrivacyPage.waitForTimeout(500);

            PrivacyPage.locator("//input[@placeholder='Your mobile phone']").fill("123456");
            PrivacyPage.waitForTimeout(500);
//            PrivacyPage.pause();

            //click on Confirm
            PrivacyPage.locator("//button[contains(@class,\"mat-button\")]//span[contains(text(),'Confirm')]").first().click();
            PrivacyPage.waitForTimeout(60000);
//            PrivacyPage.pause();


            //Continue to Payment
            PrivacyPage.locator("//span[contains(text(),'Continue to payment')]").first().click();
            PrivacyPage.waitForTimeout(60000);
//            PrivacyPage.pause();

            // select credit card
            PrivacyPage.locator("//label[contains(text(),'Credit')]/..//input[@id='radio_1']").first().click();
            PrivacyPage.waitForTimeout(2000);

            // creadit card type- Visa
            PrivacyPage.locator("//div[contains(@class,\"cardtype-select\")]//span[contains(@class,\"dropdown-icon\")]").first().click();
            PrivacyPage.querySelector("//span[contains(text(), 'Visa')]").click();
            PrivacyPage.waitForTimeout(500);

            // card number
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"number-label\"]").fill("4000000000001000");
            PrivacyPage.waitForTimeout(500);

            // card holder name
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"name-label\"]").fill("Test User");
            PrivacyPage.waitForTimeout(500);

            // exp month
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-month-label\"]").fill("06");
            PrivacyPage.waitForTimeout(500);

            // exp year
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-year-label\"]").fill("26");
            PrivacyPage.waitForTimeout(500);

            // cvv
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"cvv-label\"]").fill("123");
            PrivacyPage.waitForTimeout(500);

            //name and street
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"street-label\"]").fill("street name");
            PrivacyPage.waitForTimeout(500);

            // zip name
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"zip-label\"]").fill("123456");
            PrivacyPage.waitForTimeout(500);

            // city
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"city-label\"]").fill("Frankfurt");
            PrivacyPage.waitForTimeout(500);

            // terms and cond
            PrivacyPage.locator("//input[@type='checkbox' and contains(@class, 'mdc-checkbox__native-control') and @id='terms-checkbox']").first().click();
            PrivacyPage.waitForTimeout(1000);

            // pay now
            PrivacyPage.locator("//span[contains(text(), 'Pay Now')]").first().click();
            System.out.println("PNR created : successfully");
            Thread.sleep(60000);

            privacyPage.locator("//div[text()='Your booking is confirmed!']").click();
//            page.screenshot(new Page.ScreenshotOptions().setPath("screenshot.png"));
            PrivacyPage.waitForTimeout(10000);
            Thread.sleep(10000);
            PrivacyPage.pause();
            page.bringToFront();
        }

        catch (Exception e)
        {
            e.getMessage();
            LOGGER.error(e.getMessage());
        }
    }
//
    @And("User handles Privacy Setting Page and proceed with OS booking")
    public void User_handle_Privacy_Settings_Page_OS_006() {
        page.waitForTimeout(200);
        try {
            System.out.println("Privacy Handle Function Body");
            page.waitForPopup(new Page.WaitForPopupOptions()
                    .setPredicate(p -> p.context().pages().size() == 2), () ->
            {
                page.locator("(//button[text()='Submit'])[1]").click();
            });
            System.out.println("Submit button clicked: Successfully");
            List<Page> pages = page.context().pages();
            for (Page tabs : pages) {
                tabs.waitForLoadState();
                System.out.println(tabs.url());
            }
            Page PrivacyPage = pages.get(1);
            privacyPage=PrivacyPage;
            System.out.println("Submit button :New URL");
            PrivacyPage.waitForTimeout(500);
            PrivacyPage.waitForLoadState();
            PrivacyPage.locator(commonActions.getLocatorStr("Privacy")).isVisible();
            PrivacyPage.click(commonActions.getLocatorStr("Privacy"));
            System.out.println("Privacy setting page handled : successfully");
            PrivacyPage.waitForLoadState(LoadState.LOAD);
            PrivacyPage.waitForTimeout(3000);

            PrivacyPage.pause();

            //Select classS
            PrivacyPage.locator("(//div[contains(text(),'Premium Economy')])[1]").first().click();
            //Select class economy
            PrivacyPage.locator("(//span[contains(text(),'Select')])[1]").first().click();
//            PrivacyPage.locator("//button[@id=\"selectFare-BFEUM2NC0E\"]/span[1]").first().click();
            PrivacyPage.waitForTimeout(5000);

            //Enter Passenger details
            PrivacyPage.locator("//span[text()='Enter passenger details']").first().click();
            PrivacyPage.waitForTimeout(10000);

            //select First Name
            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("Test");
            PrivacyPage.waitForTimeout(500);
            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("User");
            PrivacyPage.waitForTimeout(500);
//            PrivacyPage.pause();
            //DOB
            PrivacyPage.locator("//span[@class='mat-mdc-button-touch-target']").click();
            PrivacyPage.waitForTimeout(500);
            //select year
            PrivacyPage.locator("//span[text()=' 1990 ']").click();
            PrivacyPage.waitForTimeout(500);
            //select month
            PrivacyPage.locator("//span[text()=' OCT ']").click();
            PrivacyPage.waitForTimeout(500);
            //select day
            PrivacyPage.locator("//span[text()=' 23 ']").click();
            PrivacyPage.waitForTimeout(500);
            //select gender
            PrivacyPage.locator("//span[contains(text(),'Female')]").first().click();
            //mail
            PrivacyPage.locator("//input[@placeholder='Your email address']").fill("test@yopmail.com");
            PrivacyPage.waitForTimeout(500);
            PrivacyPage.locator("//input[@placeholder='Your country calling code']").fill("+49");
            PrivacyPage.waitForTimeout(500);
            PrivacyPage.locator("//input[@placeholder='Your mobile phone']").fill("123456");
            PrivacyPage.waitForTimeout(500);
//            PrivacyPage.pause();
            //click on Confirm
            PrivacyPage.locator("//button[contains(@class,\"mat-button\")]//span[contains(text(),'Confirm')]").first().click();
            PrivacyPage.waitForTimeout(20000);
//            PrivacyPage.pause();

            //Continue to Payment
            PrivacyPage.locator("//span[contains(text(),'Continue to payment')]").first().click();
            PrivacyPage.waitForTimeout(30000);
//            PrivacyPage.pause();
            // select credit card
            PrivacyPage.locator("//label[contains(text(),'Credit')]/..//input[@id='radio_1']").first().click();
            PrivacyPage.waitForTimeout(2000);
            // creadit card type- Visa
            PrivacyPage.locator("//div[contains(@class,\"cardtype-select\")]//span[contains(@class,\"dropdown-icon\")]").first().click();
            PrivacyPage.querySelector("//span[contains(text(), 'Visa')]").click();
            PrivacyPage.waitForTimeout(500);
            // card number
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"number-label\"]").fill("4000000000001000");
            PrivacyPage.waitForTimeout(500);
            // card holder name
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"name-label\"]").fill("Test User");
            PrivacyPage.waitForTimeout(500);
            // exp month
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-month-label\"]").fill("06");
            PrivacyPage.waitForTimeout(500);
            // exp year
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-year-label\"]").fill("26");
            PrivacyPage.waitForTimeout(500);
            // cvv
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"cvv-label\"]").fill("123");
            PrivacyPage.waitForTimeout(500);
            //name and street
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"street-label\"]").fill("street name");
            PrivacyPage.waitForTimeout(500);
            // zip name
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"zip-label\"]").fill("123456");
            PrivacyPage.waitForTimeout(500);
            // city
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"city-label\"]").fill("Frankfurt");
            PrivacyPage.waitForTimeout(500);
            // terms and cond
            PrivacyPage.locator("//input[@type='checkbox' and contains(@class, 'mdc-checkbox__native-control') and @id='terms-checkbox']").first().click();
            PrivacyPage.waitForTimeout(1000);
            // pay now
            PrivacyPage.locator("//span[contains(text(), 'Pay Now')]").first().click();
            System.out.println("PNR created : successfully");
            Thread.sleep(60000);
            privacyPage.locator("//div[text()='Your booking is confirmed!']").click();
//            page.screenshot(new Page.ScreenshotOptions().setPath("screenshot.png"));
            PrivacyPage.waitForTimeout(10000);
            Thread.sleep(10000);
            PrivacyPage.pause();
           page.bringToFront();

        }
        catch (Exception e)
        {
            e.getMessage();
            LOGGER.error(e.getMessage());
        }
    }



    @And("User handles Privacy Setting Page and proceed with rebooking")
    public void User_handle_Privacy_Settings_Page_OS_006_RB()  {
        page.waitForTimeout(2000);
        try {
            System.out.println("Privacy Handle Function Body");
            page.waitForPopup(new Page.WaitForPopupOptions()
                    .setPredicate(p -> p.context().pages().size() == 2), () ->
            {
                page.locator("(//button[text()='Submit'])[1]").click();
            });

            System.out.println("Submit button clicked: Successfully");

            List<Page> pages = page.context().pages();
            for (Page tabs : pages) {
                tabs.waitForLoadState();
                System.out.println(tabs.url());
            }
            Page PrivacyPage = pages.get(1);
            privacyPage=PrivacyPage;


            System.out.println("Submit button :New URL");
            PrivacyPage.waitForTimeout(5000);

            PrivacyPage.waitForLoadState();
            PrivacyPage.locator(commonActions.getLocatorStr("Privacy")).isVisible();

            PrivacyPage.click(commonActions.getLocatorStr("Privacy"));
            System.out.println("Privacy setting page handled : successfully");

            PrivacyPage.waitForLoadState(LoadState.LOAD);
            PrivacyPage.waitForTimeout(3000);

            PrivacyPage.pause();

//          Change flight
            PrivacyPage.locator("//button[contains(@class, 'modify-flights-button')]").click();
            PrivacyPage.waitForTimeout(5000);

//span[contains(text(),'Change flights')]

//          Debug panel
            PrivacyPage.locator("//p[normalize-space()='LHG debug panel']").first().click();
            PrivacyPage.waitForTimeout(1000);

//          Custserv-3464
            PrivacyPage.locator("//span[text()='CUSTSERV-3464']").first().click();
            PrivacyPage.waitForTimeout(1000);

            PrivacyPage.locator("//span[normalize-space()='Apply']").first().click();
            PrivacyPage.waitForTimeout(5000);

            PrivacyPage.pause();

//          Select date
            PrivacyPage.getByPlaceholder("dd/mm/yyyy").fill("2023-11-20");

//            PrivacyPage.locator("//div[@id=\"cdk-accordion-child-0\"]/div/div/mat-form-field[1]/div/div[1]/div/mat-datepicker").first().click();

            PrivacyPage.locator("//span[text()='Search']").first().click();
            PrivacyPage.waitForTimeout(1000);

            PrivacyPage.locator("//p[normalize-space()='LHG debug panel']").first().click();
            PrivacyPage.waitForTimeout(1000);

            PrivacyPage.locator("//span[text()='CUSTSERV-2616']").first().click();
            PrivacyPage.waitForTimeout(1000);

            PrivacyPage.locator("//span[normalize-space()='Apply']").first().click();
            PrivacyPage.waitForTimeout(5000);

            PrivacyPage.locator("//span[text()='Original flights details']").isVisible();
            PrivacyPage.waitForTimeout(1000);

            PrivacyPage.pause();

            PrivacyPage.locator("(//span[text()='from'])[1]").click();
            PrivacyPage.locator("(//span[text()='Select'])[4]").click();
            PrivacyPage.waitForTimeout(10000);


            privacyPage.locator("//span[text()='Continue to payment']").click();
            privacyPage.waitForTimeout(5000);

            PrivacyPage.locator("//label[contains(text(),'Credit')]/..//input[@id='radio_1']").first().click();
            PrivacyPage.waitForTimeout(2000);

            // creadit card type- Visa
            PrivacyPage.locator("//div[contains(@class,\"cardtype-select\")]//span[contains(@class,\"dropdown-icon\")]").first().click();
            PrivacyPage.querySelector("//span[contains(text(), 'Visa')]").click();
            PrivacyPage.waitForTimeout(500);

            // card number
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"number-label\"]").fill("4000000000001000");
            PrivacyPage.waitForTimeout(500);

            // card holder name
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"name-label\"]").fill("Test User");
            PrivacyPage.waitForTimeout(500);

            // exp month
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-month-label\"]").fill("06");
            PrivacyPage.waitForTimeout(500);

            // exp year
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-year-label\"]").fill("26");
            PrivacyPage.waitForTimeout(500);

            // cvv
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"cvv-label\"]").fill("123");
            PrivacyPage.waitForTimeout(500);

            //name and street
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"street-label\"]").fill("street name");
            PrivacyPage.waitForTimeout(500);

            // zip name
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"zip-label\"]").fill("123456");
            PrivacyPage.waitForTimeout(500);

            // city
            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"city-label\"]").fill("Frankfurt");
            PrivacyPage.waitForTimeout(500);

            // terms and cond
            PrivacyPage.locator("//input[@type='checkbox' and contains(@class, 'mdc-checkbox__native-control') and @id='terms-checkbox']").first().click();
            PrivacyPage.waitForTimeout(1000);

            // pay now
            PrivacyPage.locator("//span[contains(text(), 'Pay Now')]").first().click();
            System.out.println("PNR created : successfully");
            Thread.sleep(60000);

            PrivacyPage.locator("//span[text()='Your booking reference is']").isVisible();
            Thread.sleep(2000);


        }

        catch (Exception e)
        {
            e.getMessage();
            LOGGER.error(e.getMessage());
        }
    }


    @And("User enters the pnr in Record_Locator")
    public void user_enters_pnr_in_text_field() {
        privacyPage.locator("//input[@id='recLoc']").fill("VA9DF7");
//        privacyPage.fill("//input[@id='recLoc']", pnr);
    }



    @And("User opens the Amadeus Agate Picker")
    public void user_opens_amadeus_agate_picker()
    {
        try
        {
            //System.out.println("App_type : " + tenant);
            page.navigate(Configurations.Agate_home);
            page.waitForLoadState();

            System.out.println("Amadeus Agate Picker URL loaded successfully");
            commonActions.handle_Privacy_Settings_Page();

        } catch (Exception e)
        {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
    }


    //User click on Submit on Agate Portal
    @And("User clicks on submit button")
    public void userClicksOnSubmitButton()
    {
        try
        {
            page.waitForPopup(new Page.WaitForPopupOptions()
                    .setPredicate(p -> p.context().pages().size() == 2), () ->
            {
                page.waitForTimeout(3000);
                page.locator("(//button[text()='Submit'])[1]").click();
            });
            System.out.println("Submit button clicked : Successfully");
            List<Page> pages = page.context().pages();
            for (Page tabs : pages)
            {
                tabs.waitForLoadState();
                System.out.println(tabs.url());
            }
            Page confirmationPage = pages.get(1);
            // errormsg will be displayed
            confirmationPage.locator("//span[text()='Your flights are not eligible for online modification. Please contact our call center for more details.']").click();
            confirmationPage.waitForTimeout(3000);
            confirmationPage.pause();

// Rebook flight change
//            confirmationPage.pause();
//            confirmationPage.locator("(//span[@class='mat-button-wrapper'])[2]").click();
//            confirmationPage.waitForTimeout(3000);
//
//            confirmationPage.locator("//input[@placeholder='dd/mm/yyyy']").fill("29/12/2023");
//            confirmationPage.waitForTimeout(3000);
//
//            confirmationPage.locator("//span[text()='Search']").click();
//            confirmationPage.waitForTimeout(3000);
//// select flight
//            confirmationPage.locator("(//button[@type='button']//span[@class='mat-button-focus-overlay'])[1]").click();
//            confirmationPage.waitForTimeout(3000);
//// select new fare
//            confirmationPage.locator("(//span[text()='Select'])[1]").click();
//            confirmationPage.waitForTimeout(3000);
//// continue to payment
//            confirmationPage.locator("//span[text()='Continue to payment']").click();
//            confirmationPage.waitForTimeout(3000);
//
//            // select credit card
//            confirmationPage.locator("//label[contains(text(),'Credit')]/..//input[@id='radio_1']").first().click();
//            confirmationPage.waitForTimeout(30000);
//
//            //name and street
//            confirmationPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"street-label\"]").fill("street name");
//            confirmationPage.waitForTimeout(2000);
//
//            // card number
//            confirmationPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"number-label\"]").fill("4000000000001000");
//            confirmationPage.waitForTimeout(2000);
//
//            // card holder name
//            confirmationPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"name-label\"]").fill("George John");
//            confirmationPage.waitForTimeout(2000);
//
//            // zip name
//            confirmationPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"zip-label\"]").fill("123456");
//            confirmationPage.waitForTimeout(2000);
//
//            // city
//            confirmationPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"city-label\"]").fill("Frankfurt");
//            confirmationPage.waitForTimeout(2000);
//
//            // exp month
//            confirmationPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-month-label\"]").fill("06");
//            confirmationPage.waitForTimeout(2000);
//
//            // exp year
//            confirmationPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-year-label\"]").fill("26");
//            confirmationPage.waitForTimeout(2000);
//
//            // cvv
//            confirmationPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"cvv-label\"]").fill("123");
//            confirmationPage.waitForTimeout(2000);
//
//            // Visa
//            confirmationPage.locator("//div[contains(@class,\"cardtype-select\")]//span[contains(@class,\"dropdown-icon\")]").first().click();
//
//            confirmationPage.querySelector("//li[@class='mdc-list-item']//span[contains(text(), 'Visa')]").click();
//
//            // terms and cond
//            confirmationPage.locator("//input[@type='checkbox' and contains(@class, 'mdc-checkbox__native-control') and @id='terms-checkbox']").first().click();
//            confirmationPage.waitForTimeout(5000);
//
//            // pay now
//            confirmationPage.locator("//span[contains(text(), 'Pay Now')]").first().click();
//
//            Thread.sleep(5000);


        }
        catch(Exception e)
        {
            LOGGER.error("Error: " + e.getMessage());
        }
    }


    private void saveTestResultsToXray(Scenario s) {

        ZonedDateTime finishDateTime = ZonedDateTime.now();
        testFinish = finishDateTime.truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        Log.info("Test Finish Time: " + testFinish);

        if (s.isFailed()) {
            Log.error("Test Failed!");
            JunitRunner.featureTestPassed = false;
            Xray.writeResultsForSingleTest(JunitRunner.ExecutionID, XrayIssueKey, XRAY_CONFIG.TEST_STATUS_FAIL,
                    JunitRunner.testStart, testFinish);
        } else {
            if (JunitRunner.featureTestPassed == true) {
                Log.info("Test Passed!");
                Xray.writeResultsForSingleTest(JunitRunner.ExecutionID, XrayIssueKey, XRAY_CONFIG.TEST_STATUS_PASS,
                        JunitRunner.testStart, testFinish);
            }
        }

    }

    private void checkNewTest(Scenario s) {
        XrayIssueKey = XrayHelper.getTestIdFromFileName(s.getId());

        if (!JunitRunner.currentXrayIssueKey.contains(XrayIssueKey)) {
            System.out.println("This is a new Feature!");
            JunitRunner.currentXrayIssueKey = XrayIssueKey;
            JunitRunner.featureTestPassed = true;
        }

    }

    @And("User scrolls up")
    public void userScrollsUp() {
        // Scroll up the page
        page.evaluate("window.scrollTo(0, 0);");
    }


}


//****************************************************** TC_002 *****************************************************//

//    CommonActions commonActions;
//    private Scenario scenario;
//    Page page;
//    Page privacyPage;
//    String testFinish = "";
//    String XrayIssueKey = "";
//
//    public GenericSteps(CommonActions con) {
//        this.commonActions = con;
//    }
//
//    private final static Logger LOGGER = Logger.getLogger(GenericSteps.class);
//
//    @Before
//    public void before(Scenario s) throws Exception {
//        this.scenario = s;
//        try {
//
//            page = commonActions.getPage();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if ((Configurations.RunOnBrowserStack).equals("Y")) {
//            commonActions.initReports(s.getName() + "_" + System.getProperty("browser"));
//        } else {
//            commonActions.initReports(s.getName() + "_" + "chrome");
//        }
//        commonActions.setfeaturefilenameandsceanrio(s.getId(), s.getName());
//        commonActions.setScenario(s);
//    }
//
//    /**
//     * Description Closing the resources after execution of each scenario
//     *
//     * @throws IOException
//     */
//    @After
//    public void after(Scenario s)
//    {
//        commonActions.quit();
//    }
//
//    @Given("User launches lufthansa web application")
//    public void user_launches_lufthansa_web_application() {
//        try {
//            page.navigate(Configurations.Appurl_QA_LH_De);
//            page.waitForLoadState();
//            System.out.println("Lufthansa url loaded : successfully");
//            commonActions.handle_Privacy_Settings_Page();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @And("User launches {string} web application")
//    public void user_launches_web_application(String tenant) {
//        LOGGER.info(CommonActions.getCurrentMethodName());
//        try
//        {
//
//            System.out.println("App_type : " + tenant);
//            if (tenant.equalsIgnoreCase("swiss")) {
//                page.navigate(Configurations.Appurl_QA_SN_Fr_B2);
//            }
//            else if (tenant.equalsIgnoreCase("lh")) {
//                page.navigate(Configurations.Appurl_QA_LH_De);
//            }
//            else if (tenant.equalsIgnoreCase("amadeus")) {
//                page.navigate(Configurations.Appurl_QA_Amadeus_B2);
//            }
//            else {
//                page.navigate(Configurations.Appurl_QA_LH_De);
//            }
//
//            page.waitForLoadState();
//            System.out.println("Lufthansa url loaded : successfully");
//            commonActions.handle_Privacy_Settings_Page();
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            LOGGER.error(e.getMessage());
//        }
//
//    }
//
//    @And("User chooses {string}")
//    public void user_chooses(String locator) {
//        commonActions.click(locator);
//    }
//
//    @Given("User enters {string} in login page")
//    public void user_enters_credential_in_login_page(String jsonKeyDetails) {
//        try {
//            LOGGER.info(CommonActions.getCurrentMethodName());
//            commonActions.user_Performs_Amadues_Login(jsonKeyDetails);
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
//        }
//    }
//
//    @And("User enters {string} in {string}")
//    public void user_enters_data_in_text_field(String jsonDataKey, String locator) {
//
//        try {
//            String data = commonActions.getTestData(jsonDataKey);
//            commonActions.enterText(locator, data);
//        } catch (Exception e) {
//            System.out.println("Exception in enterText : " + e.getMessage());
//        }
//    }
//
//    @And("User handles Privacy Settings Page and proceeds with booking")
//    public void User_handle_Privacy_Settings_Page() {
//        page.waitForTimeout(200);
//        try {
//            System.out.println("Privacy Handle Function Body");
//
//            page.waitForPopup(new Page.WaitForPopupOptions()
//                    .setPredicate(p -> p.context().pages().size() == 2), () ->
//            {
//                page.locator("(//button[text()='Submit'])[1]").click();
//
//            });
//            System.out.println("Submit button clicked: Successfully");
//
//            List<Page> pages = page.context().pages();
//            for (Page tabs : pages) {
//                tabs.waitForLoadState();
//                System.out.println(tabs.url());
//            }
//            Page PrivacyPage = pages.get(1);
//            privacyPage=PrivacyPage;
//
//            System.out.println("Submit button :New URL");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.waitForLoadState();
//            PrivacyPage.locator(commonActions.getLocatorStr("Privacy")).isVisible();
//
//            PrivacyPage.click(commonActions.getLocatorStr("Privacy"));
//            System.out.println("Privacy setting page handled : successfully");
//
//            PrivacyPage.waitForLoadState(LoadState.LOAD);
//
//            //Select classS
//            PrivacyPage.locator("(//div[text()='Economy'])[1]").first().click();
//
//            //Select class from econumy
//            PrivacyPage.locator("//button[@id=\"selectFare-STWWM4FF0E\"]/span[1]").first().click();
//            PrivacyPage.waitForTimeout(10000);
//
//            //select return class
//            PrivacyPage.locator("(//div[text()='Economy'])[1]").first().click();
//
//            //Select class from econumy
//            PrivacyPage.locator("//button[@id=\"selectFare-STWWM4FF0E\"]/span[1]").first().click();
//            PrivacyPage.waitForTimeout(10000);
//
//            //Enter Passenger details
//            PrivacyPage.locator("//span[text()='Enter passenger details']").first().click();
//
//            //select First Name
//            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("Test");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("User");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your email address']").fill("test@yopmail.com");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your country calling code']").fill("+49");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your mobile phone']").fill("123456");
//            PrivacyPage.waitForTimeout(3000);
//
//            //Next passerger Details
//            PrivacyPage.locator("//span[text()='Next passenger']").first().click();
//            PrivacyPage.waitForTimeout(3000);
//            PrivacyPage.pause();
//
//            //Enter first name
//            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("jon");
//            PrivacyPage.waitForTimeout(3000);
//
//            //Enter Last NAme
//            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("dey");
//            PrivacyPage.waitForTimeout(3000);
//
//            //DOB
//            PrivacyPage.locator("//span[@class='mat-mdc-button-touch-target']").click();
//            PrivacyPage.waitForTimeout(3000);
//
//            //select year
//            PrivacyPage.locator("//span[text()=' 2021 ']").click();
//            PrivacyPage.waitForTimeout(3000);
//
//            //select month
//            PrivacyPage.locator("//span[text()=' OCT ']").click();
//            PrivacyPage.waitForTimeout(3000);
//
//            //select day
//            PrivacyPage.locator("//span[text()=' 23 ']").click();
//            PrivacyPage.waitForTimeout(3000);
//
//            //click on Confirm
//            PrivacyPage.locator("//button[contains(@class,\"mat-button\")]//span[contains(text(),'Confirm')]").first().click();
//            PrivacyPage.waitForTimeout(30000);
//
//            //Continue to Payment
//            PrivacyPage.locator("//button[contains(@class,\"mat-button\")]//span[contains(text(),'Continue to payment')]").first().click();
//            PrivacyPage.waitForTimeout(30000);
//
//            // select credit card
//            PrivacyPage.locator("//label[contains(text(),'Credit')]/..//input[@id='radio_1']").first().click();
//            PrivacyPage.waitForTimeout(10000);
//
//            // creadit card type- Visa
//            PrivacyPage.locator("//div[contains(@class,\"cardtype-select\")]//span[contains(@class,\"dropdown-icon\")]").first().click();
//            PrivacyPage.querySelector("//li[@class='mdc-list-item']//span[contains(text(), 'Visa')]").click();
//            PrivacyPage.waitForTimeout(2000);
//
//            // card number
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"number-label\"]").fill("4000000000001000");
//            PrivacyPage.waitForTimeout(2000);
//
//            // card holder name
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"name-label\"]").fill("Lisa John");
//            PrivacyPage.waitForTimeout(2000);
//
//            // exp month
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-month-label\"]").fill("06");
//            PrivacyPage.waitForTimeout(2000);
//
//            // exp year
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-year-label\"]").fill("26");
//            PrivacyPage.waitForTimeout(2000);
//
//            // cvv
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"cvv-label\"]").fill("123");
//            PrivacyPage.waitForTimeout(2000);
//
//            //name and street
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"street-label\"]").fill("street name");
//            PrivacyPage.waitForTimeout(2000);
//
//            // zip name
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"zip-label\"]").fill("123456");
//            PrivacyPage.waitForTimeout(2000);
//
//            // city
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"city-label\"]").fill("Frankfurt");
//            PrivacyPage.waitForTimeout(2000);
//
//            // terms and cond
//            PrivacyPage.locator("//input[@type='checkbox' and contains(@class, 'mdc-checkbox__native-control') and @id='terms-checkbox']").first().click();
//            PrivacyPage.waitForTimeout(5000);
//
//            // pay now
//            PrivacyPage.locator("//span[contains(text(), 'Pay Now')]").first().click();
//            System.out.println("PNR created : successfully");
//            Thread.sleep(90000);
//
//            privacyPage.locator("//div[text()='Your booking is confirmed!']").click();
//            PrivacyPage.waitForTimeout(10000);
//            //Thread.sleep(10000);
//            PrivacyPage.pause();
//            page.bringToFront();
//        }
//
//        catch (Exception e)
//        {
//            e.getMessage();
//            LOGGER.error(e.getMessage());
//        }
//    }
//
//    @And("User scrolls up")
//    public void userScrollsUp()
//    {
//        // Scroll up the page
//        page.evaluate("window.scrollTo(0, 0);");
//    }
//
//    //For Adding FBAG
//
//    @And("User opens the Amadeus Agate Picker")
//    public void user_opens_amadeus_agate_picker()
//    {
//        try
//        {
//            //System.out.println("App_type : " + tenant);
//            page.navigate(Configurations.Agate_home);
//            page.waitForLoadState();
//
//            System.out.println("Amadeus Agate Picker URL loaded successfully");
//            commonActions.handle_Privacy_Settings_Page();
//        } catch (Exception e)
//        {
//            e.printStackTrace();
//            LOGGER.error(e.getMessage());
//        }
//    }
//
//    private void saveTestResultsToXray(Scenario s) {
//
//        ZonedDateTime finishDateTime = ZonedDateTime.now();
//        testFinish = finishDateTime.truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
//        Log.info("Test Finish Time: " + testFinish);
//
//        if (s.isFailed()) {
//            Log.error("Test Failed!");
//            JunitRunner.featureTestPassed = false;
//            Xray.writeResultsForSingleTest(JunitRunner.ExecutionID, XrayIssueKey, XRAY_CONFIG.TEST_STATUS_FAIL,
//                    JunitRunner.testStart, testFinish);
//        } else {
//            if (JunitRunner.featureTestPassed == true) {
//                Log.info("Test Passed!");
//                Xray.writeResultsForSingleTest(JunitRunner.ExecutionID, XrayIssueKey, XRAY_CONFIG.TEST_STATUS_PASS,
//                        JunitRunner.testStart, testFinish);
//            }
//        }
//    }
//
//    private void checkNewTest(Scenario s) {
//        XrayIssueKey = XrayHelper.getTestIdFromFileName(s.getId());
//
//        if (!JunitRunner.currentXrayIssueKey.contains(XrayIssueKey)) {
//            System.out.println("This is a new Feature!");
//            JunitRunner.currentXrayIssueKey = XrayIssueKey;
//            JunitRunner.featureTestPassed = true;
//        }
//    }
//}


//    *********************************************** TC_003*****************************************
//CommonActions commonActions;
//    private Scenario scenario;
//    Page page;
//    Page privacyPage;
//    String testFinish = "";
//    String XrayIssueKey = "";
//
//    public GenericSteps(CommonActions con) {
//        this.commonActions = con;
//    }
//
//    private final static Logger LOGGER = Logger.getLogger(GenericSteps.class);
//
//    @Before
//    public void before(Scenario s) throws Exception {
//        this.scenario = s;
//        try {
//
//            page = commonActions.getPage();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if ((Configurations.RunOnBrowserStack).equals("Y")) {
//            commonActions.initReports(s.getName() + "_" + System.getProperty("browser"));
//        } else {
//            commonActions.initReports(s.getName() + "_" + "chrome");
//        }
//        commonActions.setfeaturefilenameandsceanrio(s.getId(), s.getName());
//        commonActions.setScenario(s);
//    }
//
//    /**
//     * Description Closing the resources after execution of each scenario
//     *
//     * @throws IOException
//     */
//    @After
//    public void after(Scenario s)
//    {
//        commonActions.quit();
//    }
//
//    @Given("User launches lufthansa web application")
//    public void user_launches_lufthansa_web_application() {
//        try {
//            page.navigate(Configurations.Appurl_QA_LH_De);
//            page.waitForLoadState();
//            System.out.println("Lufthansa url loaded : successfully");
//            commonActions.handle_Privacy_Settings_Page();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @And("User launches {string} web application")
//    public void user_launches_web_application(String tenant) {
//        LOGGER.info(CommonActions.getCurrentMethodName());
//        try
//        {
//
//            System.out.println("App_type : " + tenant);
//            if (tenant.equalsIgnoreCase("swiss")) {
//                page.navigate(Configurations.Appurl_QA_SN_Fr_B2);
//            }
//            else if (tenant.equalsIgnoreCase("lh")) {
//                page.navigate(Configurations.Appurl_QA_LH_De);
//            }
//            else if (tenant.equalsIgnoreCase("amadeus")) {
//                page.navigate(Configurations.Appurl_QA_Amadeus_B2);
//            }
//            else {
//                page.navigate(Configurations.Appurl_QA_LH_De);
//            }
//
//            page.waitForLoadState();
//            System.out.println("Lufthansa url loaded : successfully");
//            commonActions.handle_Privacy_Settings_Page();
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            LOGGER.error(e.getMessage());
//        }
//
//    }
//
//    @And("User chooses {string}")
//    public void user_chooses(String locator) {
//        commonActions.click(locator);
//    }
//
//    @Given("User enters {string} in login page")
//    public void user_enters_credential_in_login_page(String jsonKeyDetails) {
//        try {
//            LOGGER.info(CommonActions.getCurrentMethodName());
//            commonActions.user_Performs_Amadues_Login(jsonKeyDetails);
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
//        }
//    }
//
//    @And("User enters {string} in {string}")
//    public void user_enters_data_in_text_field(String jsonDataKey, String locator) {
//
//        try {
//            String data = commonActions.getTestData(jsonDataKey);
//            commonActions.enterText(locator, data);
//        } catch (Exception e) {
//            System.out.println("Exception in enterText : " + e.getMessage());
//        }
//    }
//
//    @And("User handles Privacy Settings Page and proceeds with booking")
//    public void User_handle_Privacy_Settings_Page() {
//        page.waitForTimeout(200);
//        try {
//            System.out.println("Privacy Handle Function Body");
//
//            page.waitForPopup(new Page.WaitForPopupOptions()
//                    .setPredicate(p -> p.context().pages().size() == 2), () ->
//            {
//                page.locator("(//button[text()='Submit'])[1]").click();
//
//            });
//            System.out.println("Submit button clicked: Successfully");
//
//            List<Page> pages = page.context().pages();
//            for (Page tabs : pages) {
//                tabs.waitForLoadState();
//                System.out.println(tabs.url());
//            }
//            Page PrivacyPage = pages.get(1);
//            privacyPage = PrivacyPage;
//
//            System.out.println("Submit button :New URL");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.waitForLoadState();
//            PrivacyPage.locator(commonActions.getLocatorStr("Privacy")).isVisible();
//
//            PrivacyPage.click(commonActions.getLocatorStr("Privacy"));
//            System.out.println("Privacy setting page handled : successfully");
//
//            PrivacyPage.waitForLoadState(LoadState.LOAD);
//
//            //Select classS
//            PrivacyPage.locator("(//span[@class='refx-display-1 flight-starting-price-label ng-star-inserted'])[1]").first().click();
//            //div[@id='2c19aaad-f819-4df5-b916-a0de20c5a38a-flightCardecoPremium-buttonLabel']
//            PrivacyPage.waitForTimeout(10000);
//
//            //Select Fare class from Economy
//            PrivacyPage.locator("(//span[text()='Select'])[1]").first().click();
//            PrivacyPage.waitForTimeout(10000);
//            PrivacyPage.pause();
//
//            //select return class
////            PrivacyPage.locator("(//button[@data-fare-family-group='eco'])[1]").first().click();
////            PrivacyPage.waitForTimeout(10000);
////            PrivacyPage.pause();
//
//            //Select Fare class from econumy
////            PrivacyPage.locator("//button[@id='selectFare-BFEUM2NC0E']//span[contains(text(),'Select')]").first().click();
////            PrivacyPage.waitForTimeout(10000);
////            PrivacyPage.pause();
//
//            //Enter Passenger details
//            PrivacyPage.locator("//span[text()='Enter passenger details']").first().click();
//            PrivacyPage.waitForTimeout(10000);
//            PrivacyPage.pause();
//
//            //Enter First PAssenger Details
//            //select First Name
//            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("Test");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("User");
//            PrivacyPage.waitForTimeout(3000);
//// select gender
//            PrivacyPage.locator("//span[text()='Male']").first().click();
//            PrivacyPage.waitForTimeout(3000);
//
////enter date of birth
//            PrivacyPage.locator("//span[@class='mat-mdc-button-touch-target']").first().click();
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//span[text()=' 2011 ']").first().click();
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//span[text()=' NOV ']").first().click();
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//span[text()=' 30 ']").first().click();
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your email address']").fill("test@yopmail.com");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your country calling code']").fill("+49");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your mobile phone']").fill("123456");
//            PrivacyPage.waitForTimeout(3000);
//
//            //Next passerger
////            PrivacyPage.locator("//span[text()='Next passenger']").first().click();
////            PrivacyPage.waitForTimeout(3000);
////            PrivacyPage.pause();
//
//            //Enter the adult 2 information
//            // Enter first name
////            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("jon");
////            PrivacyPage.waitForTimeout(5000);
////
////            //Enter Last NAme
////            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("lee");
////            PrivacyPage.waitForTimeout(5000);
////
////            //Next passerger
////            PrivacyPage.locator("//span[text()='Next passenger']").first().click();
////            PrivacyPage.waitForTimeout(3000);
////            PrivacyPage.pause();
//
//            //Enter the child information
//            //Enter first name
////            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("jolly");
////            PrivacyPage.waitForTimeout(3000);
////
////            //Enter Last Name
////            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("lee");
////            PrivacyPage.waitForTimeout(3000);
////
////            //DOB
////            PrivacyPage.locator("//span[@class='mat-mdc-button-touch-target']").click();
////            PrivacyPage.waitForTimeout(3000);
////            PrivacyPage.pause();
//
//            //select year
////            PrivacyPage.locator("//span[text()=' 2021 ']").click();
////            PrivacyPage.waitForTimeout(3000);
////
////            //select month
////            PrivacyPage.locator("//span[text()=' OCT ']").click();
////            PrivacyPage.waitForTimeout(3000);
////
////            //select day
////            PrivacyPage.locator("//span[text()=' 23 ']").click();
////            //PrivacyPage.waitForTimeout(5000);
////            Thread.sleep(90000);
////            //PrivacyPage.waitForTimeout(30000);
////            PrivacyPage.pause();
//
//            //click on Confirm
//            //PrivacyPage.locator("//span[contains(text(),'Confirm')]").first().click();
//            PrivacyPage.locator("//button[contains(@class,\"mat-button\")]//span[contains(text(),'Confirm')]").first().click();
//            PrivacyPage.waitForTimeout(30000);
//            PrivacyPage.pause();
//
//            //Continue to Payment
//            PrivacyPage.locator("//span[contains(text(),'Continue to payment')]").first().click();
//            //PrivacyPage.locator("//button[contains(@class,\"mat-button\"mat-button)]//span[contains(text(),'Continue to payment')]").first().click();
//            PrivacyPage.waitForTimeout(30000);
//
//            // select credit card
//            PrivacyPage.locator("//label[contains(text(),'Credit')]/..//input[@id='radio_1']").first().click();
//            PrivacyPage.waitForTimeout(10000);
//            PrivacyPage.pause();
//
//            // creadit card type- Visa
//            PrivacyPage.locator("//div[contains(@class,\"cardtype-select\")]//span[contains(@class,\"dropdown-icon\")]").first().click();
//            PrivacyPage.querySelector("//li[@class='mdc-list-item']//span[contains(text(), 'Visa')]").click();
//            PrivacyPage.waitForTimeout(2000);
//
//            // card number
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"number-label\"]").fill("4000000000001000");
//            PrivacyPage.waitForTimeout(2000);
//
//            // card holder name
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"name-label\"]").fill("Lisa John");
//            PrivacyPage.waitForTimeout(2000);
//
//            // exp month
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-month-label\"]").fill("06");
//            PrivacyPage.waitForTimeout(2000);
//            PrivacyPage.pause();
//
//            // exp year
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-year-label\"]").fill("26");
//            PrivacyPage.waitForTimeout(2000);
//
//            // cvv
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"cvv-label\"]").fill("123");
//            PrivacyPage.waitForTimeout(2000);
//
//            //name and street
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"street-label\"]").fill("street name");
//            PrivacyPage.waitForTimeout(2000);
//
//            // zip name
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"zip-label\"]").fill("123456");
//            PrivacyPage.waitForTimeout(2000);
//
//            // city
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"city-label\"]").fill("Frankfurt");
//            PrivacyPage.waitForTimeout(2000);
//
//            // terms and cond
//            PrivacyPage.locator("//input[@type='checkbox' and contains(@class, 'mdc-checkbox__native-control') and @id='terms-checkbox']").first().click();
//            PrivacyPage.waitForTimeout(5000);
//            PrivacyPage.pause();
//
//            // pay now
//            PrivacyPage.locator("//span[contains(text(), 'Pay Now')]").first().click();
//            System.out.println("PNR created : successfully");
//            Thread.sleep(90000);
//            PrivacyPage.pause();
//
//            privacyPage.locator("//div[text()='Your booking is confirmed!']").click();
//            Thread.sleep(10000);
//            PrivacyPage.pause();
//            page.bringToFront();
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            LOGGER.error(e.getMessage());
//        }
//    }
//
//    @And("User scrolls up")
//    public void userScrollsUp()
//    {
//        // Scroll up the page
//        page.evaluate("window.scrollTo(0, 0);");
//    }
//
//
//    @And("User opens the Amadeus Agate Picker")
//    public void user_opens_amadeus_agate_picker()
//    {
//        try
//        {
//            //System.out.println("App_type : " + tenant);
//            page.navigate(Configurations.Agate_home);
//            page.waitForLoadState();
//
//            System.out.println("Amadeus Agate Picker URL loaded successfully");
//            commonActions.handle_Privacy_Settings_Page();
//        } catch (Exception e)
//        {
//            e.printStackTrace();
//            LOGGER.error(e.getMessage());
//        }
//    }
//
//    private void saveTestResultsToXray(Scenario s) {
//
//        ZonedDateTime finishDateTime = ZonedDateTime.now();
//        testFinish = finishDateTime.truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
//        Log.info("Test Finish Time: " + testFinish);
//
//        if (s.isFailed()) {
//            Log.error("Test Failed!");
//            JunitRunner.featureTestPassed = false;
//            Xray.writeResultsForSingleTest(JunitRunner.ExecutionID, XrayIssueKey, XRAY_CONFIG.TEST_STATUS_FAIL,
//                    JunitRunner.testStart, testFinish);
//        } else {
//            if (JunitRunner.featureTestPassed == true) {
//                Log.info("Test Passed!");
//                Xray.writeResultsForSingleTest(JunitRunner.ExecutionID, XrayIssueKey, XRAY_CONFIG.TEST_STATUS_PASS,
//                        JunitRunner.testStart, testFinish);
//            }
//        }
//    }
//
//    private void checkNewTest(Scenario s) {
//        XrayIssueKey = XrayHelper.getTestIdFromFileName(s.getId());
//
//        if (!JunitRunner.currentXrayIssueKey.contains(XrayIssueKey)) {
//            System.out.println("This is a new Feature!");
//            JunitRunner.currentXrayIssueKey = XrayIssueKey;
//            JunitRunner.featureTestPassed = true;
//        }
//    }
//}




//******************************************************* For TC_004 **************************************************

//    CommonActions commonActions;
//    private Scenario scenario;
//    Page page;
//    Page privacyPage;
//    String testFinish = "";
//    String XrayIssueKey = "";
//
//    public GenericSteps(CommonActions con) {
//        this.commonActions = con;
//    }
//
//    private final static Logger LOGGER = Logger.getLogger(GenericSteps.class);
//
//    @Before
//    public void before(Scenario s) throws Exception {
//        this.scenario = s;
//        try {
//
//            page = commonActions.getPage();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if ((Configurations.RunOnBrowserStack).equals("Y")) {
//            commonActions.initReports(s.getName() + "_" + System.getProperty("browser"));
//        } else {
//            commonActions.initReports(s.getName() + "_" + "chrome");
//        }
//        commonActions.setfeaturefilenameandsceanrio(s.getId(), s.getName());
//        commonActions.setScenario(s);
//    }
//
//    /**
//     * Description Closing the resources after execution of each scenario
//     *
//     * @throws IOException
//     */
//    @After
//    public void after(Scenario s)
//    {
//        commonActions.quit();
//    }
//
//    @Given("User launches lufthansa web application")
//    public void user_launches_lufthansa_web_application() {
//        try {
//            page.navigate(Configurations.Appurl_QA_LH_De);
//            page.waitForLoadState();
//            System.out.println("Lufthansa url loaded : successfully");
//            commonActions.handle_Privacy_Settings_Page();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @And("User launches {string} web application")
//    public void user_launches_web_application(String tenant) {
//        LOGGER.info(CommonActions.getCurrentMethodName());
//        try
//        {
//
//            System.out.println("App_type : " + tenant);
//            if (tenant.equalsIgnoreCase("swiss")) {
//                page.navigate(Configurations.Appurl_QA_SN_Fr_B2);
//            }
//            else if (tenant.equalsIgnoreCase("lh")) {
//                page.navigate(Configurations.Appurl_QA_LH_De);
//            }
//            else if (tenant.equalsIgnoreCase("amadeus")) {
//                page.navigate(Configurations.Appurl_QA_Amadeus_B2);
//            }
//            else {
//                page.navigate(Configurations.Appurl_QA_LH_De);
//            }
//
//            page.waitForLoadState();
//            System.out.println("Lufthansa url loaded : successfully");
//            commonActions.handle_Privacy_Settings_Page();
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            LOGGER.error(e.getMessage());
//        }
//
//    }
//
//    @And("User chooses {string}")
//    public void user_chooses(String locator) {
//        commonActions.click(locator);
//    }
//
//    @Given("User enters {string} in login page")
//    public void user_enters_credential_in_login_page(String jsonKeyDetails) {
//        try {
//            LOGGER.info(CommonActions.getCurrentMethodName());
//            commonActions.user_Performs_Amadues_Login(jsonKeyDetails);
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
//        }
//    }
//
//    @And("User enters {string} in {string}")
//    public void user_enters_data_in_text_field(String jsonDataKey, String locator) {
//
//        try {
//            String data = commonActions.getTestData(jsonDataKey);
//            commonActions.enterText(locator, data);
//        } catch (Exception e) {
//            System.out.println("Exception in enterText : " + e.getMessage());
//        }
//    }
//
//    @And("User handles Privacy Settings Page and proceeds with booking")
//    public void User_handle_Privacy_Settings_Page() {
//        page.waitForTimeout(200);
//        try {
//            System.out.println("Privacy Handle Function Body");
//
//            page.waitForPopup(new Page.WaitForPopupOptions()
//                    .setPredicate(p -> p.context().pages().size() == 2), () ->
//            {
//                page.locator("(//button[text()='Submit'])[1]").click();
//
//            });
//            System.out.println("Submit button clicked: Successfully");
//
//            List<Page> pages = page.context().pages();
//            for (Page tabs : pages) {
//                tabs.waitForLoadState();
//                System.out.println(tabs.url());
//            }
//            Page PrivacyPage = pages.get(1);
//            privacyPage = PrivacyPage;
//
//            System.out.println("Submit button :New URL");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.waitForLoadState();
//            PrivacyPage.locator(commonActions.getLocatorStr("Privacy")).isVisible();
//
//            PrivacyPage.click(commonActions.getLocatorStr("Privacy"));
//            System.out.println("Privacy setting page handled : successfully");
//
//            PrivacyPage.waitForLoadState(LoadState.LOAD);
//
//            //Select classS
//            PrivacyPage.locator("(//button[@data-fare-family-group='eco'])[1]").first().click();
//            PrivacyPage.waitForTimeout(10000);
//
//            //Select Fare class from Economy
//            PrivacyPage.locator("//button[@id='selectFare-BFEUM2NC0E']//span[contains(text(),'Select')]").first().click();
//            PrivacyPage.waitForTimeout(10000);
//            PrivacyPage.pause();
//
//            //select return class
//            PrivacyPage.locator("(//button[@data-fare-family-group='eco'])[1]").first().click();
//            PrivacyPage.waitForTimeout(10000);
//            PrivacyPage.pause();
//
//            //Select Fare class from econumy
//            PrivacyPage.locator("//button[@id='selectFare-BFEUM2NC0E']//span[contains(text(),'Select')]").first().click();
//            PrivacyPage.waitForTimeout(10000);
//            PrivacyPage.pause();
//
//            //Enter Passenger details
//            PrivacyPage.locator("//span[text()='Enter passenger details']").first().click();
//            PrivacyPage.waitForTimeout(10000);
//            PrivacyPage.pause();
//
//            //Enter First PAssenger Details
//            //select First Name
//            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("Test");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("User");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your email address']").fill("test@yopmail.com");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your country calling code']").fill("+49");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your mobile phone']").fill("123456");
//            PrivacyPage.waitForTimeout(3000);
//
//            //Next passerger
//            PrivacyPage.locator("//span[text()='Next passenger']").first().click();
//            PrivacyPage.waitForTimeout(3000);
//            PrivacyPage.pause();
//
//            //Enter the adult 2 information
//            // Enter first name
//            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("jon");
//            PrivacyPage.waitForTimeout(5000);
//
//            //Enter Last NAme
//            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("lee");
//            PrivacyPage.waitForTimeout(5000);
//
//            //Next passerger
//            PrivacyPage.locator("//span[text()='Next passenger']").first().click();
//            PrivacyPage.waitForTimeout(3000);
//            PrivacyPage.pause();
//
//            //Enter the child information
//            //Enter first name
//            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("jolly");
//            PrivacyPage.waitForTimeout(3000);
//
//            //Enter Last Name
//            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("lee");
//            PrivacyPage.waitForTimeout(3000);
//
//            //DOB
//            PrivacyPage.locator("//span[@class='mat-mdc-button-touch-target']").click();
//            PrivacyPage.waitForTimeout(3000);
//            PrivacyPage.pause();
//
//            //select year
//            PrivacyPage.locator("//span[text()=' 2021 ']").click();
//            PrivacyPage.waitForTimeout(3000);
//
//            //select month
//            PrivacyPage.locator("//span[text()=' OCT ']").click();
//            PrivacyPage.waitForTimeout(3000);
//
//            //select day
//            PrivacyPage.locator("//span[text()=' 23 ']").click();
//            //PrivacyPage.waitForTimeout(5000);
//            Thread.sleep(90000);
//            //PrivacyPage.waitForTimeout(30000);
//            PrivacyPage.pause();
//
//            //click on Confirm
//            //PrivacyPage.locator("//span[contains(text(),'Confirm')]").first().click();
//            PrivacyPage.locator("//button[contains(@class,\"mat-button\")]//span[contains(text(),'Confirm')]").first().click();
//            PrivacyPage.waitForTimeout(30000);
//            PrivacyPage.pause();
//
//            //Continue to Payment
//            PrivacyPage.locator("//span[contains(text(),'Continue to payment')]").first().click();
//            //PrivacyPage.locator("//button[contains(@class,\"mat-button\"mat-button)]//span[contains(text(),'Continue to payment')]").first().click();
//            PrivacyPage.waitForTimeout(30000);
//
//            // select credit card
//            PrivacyPage.locator("//label[contains(text(),'Credit')]/..//input[@id='radio_1']").first().click();
//            PrivacyPage.waitForTimeout(10000);
//            PrivacyPage.pause();
//
//            // creadit card type- Visa
//            PrivacyPage.locator("//div[contains(@class,\"cardtype-select\")]//span[contains(@class,\"dropdown-icon\")]").first().click();
//            PrivacyPage.querySelector("//li[@class='mdc-list-item']//span[contains(text(), 'Visa')]").click();
//            PrivacyPage.waitForTimeout(2000);
//
//            // card number
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"number-label\"]").fill("4000000000001000");
//            PrivacyPage.waitForTimeout(2000);
//
//            // card holder name
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"name-label\"]").fill("Lisa John");
//            PrivacyPage.waitForTimeout(2000);
//
//            // exp month
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-month-label\"]").fill("06");
//            PrivacyPage.waitForTimeout(2000);
//            PrivacyPage.pause();
//
//            // exp year
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-year-label\"]").fill("26");
//            PrivacyPage.waitForTimeout(2000);
//
//            // cvv
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"cvv-label\"]").fill("123");
//            PrivacyPage.waitForTimeout(2000);
//
//            //name and street
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"street-label\"]").fill("street name");
//            PrivacyPage.waitForTimeout(2000);
//
//            // zip name
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"zip-label\"]").fill("123456");
//            PrivacyPage.waitForTimeout(2000);
//
//            // city
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"city-label\"]").fill("Frankfurt");
//            PrivacyPage.waitForTimeout(2000);
//
//            // terms and cond
//            PrivacyPage.locator("//input[@type='checkbox' and contains(@class, 'mdc-checkbox__native-control') and @id='terms-checkbox']").first().click();
//            PrivacyPage.waitForTimeout(5000);
//            PrivacyPage.pause();
//
//            // pay now
//            PrivacyPage.locator("//span[contains(text(), 'Pay Now')]").first().click();
//            System.out.println("PNR created : successfully");
//            Thread.sleep(90000);
//            PrivacyPage.pause();
//
//            privacyPage.locator("//div[text()='Your booking is confirmed!']").click();
//            Thread.sleep(10000);
//            PrivacyPage.pause();
//            page.bringToFront();
//        }
//            catch (Exception e)
//            {
//                e.printStackTrace();
//                LOGGER.error(e.getMessage());
//            }
//        }
//
//    @And("User scrolls up")
//    public void userScrollsUp()
//    {
//        // Scroll up the page
//        page.evaluate("window.scrollTo(0, 0);");
//    }
//
//
//    @And("User opens the Amadeus Agate Picker")
//    public void user_opens_amadeus_agate_picker()
//    {
//        try
//        {
//            //System.out.println("App_type : " + tenant);
//            page.navigate(Configurations.Agate_home);
//            page.waitForLoadState();
//
//            System.out.println("Amadeus Agate Picker URL loaded successfully");
//            commonActions.handle_Privacy_Settings_Page();
//        } catch (Exception e)
//        {
//            e.printStackTrace();
//            LOGGER.error(e.getMessage());
//        }
//    }
//
//    private void saveTestResultsToXray(Scenario s) {
//
//        ZonedDateTime finishDateTime = ZonedDateTime.now();
//        testFinish = finishDateTime.truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
//        Log.info("Test Finish Time: " + testFinish);
//
//        if (s.isFailed()) {
//            Log.error("Test Failed!");
//            JunitRunner.featureTestPassed = false;
//            Xray.writeResultsForSingleTest(JunitRunner.ExecutionID, XrayIssueKey, XRAY_CONFIG.TEST_STATUS_FAIL,
//                    JunitRunner.testStart, testFinish);
//        } else {
//            if (JunitRunner.featureTestPassed == true) {
//                Log.info("Test Passed!");
//                Xray.writeResultsForSingleTest(JunitRunner.ExecutionID, XrayIssueKey, XRAY_CONFIG.TEST_STATUS_PASS,
//                        JunitRunner.testStart, testFinish);
//            }
//        }
//    }
//
//    private void checkNewTest(Scenario s) {
//        XrayIssueKey = XrayHelper.getTestIdFromFileName(s.getId());
//
//        if (!JunitRunner.currentXrayIssueKey.contains(XrayIssueKey)) {
//            System.out.println("This is a new Feature!");
//            JunitRunner.currentXrayIssueKey = XrayIssueKey;
//            JunitRunner.featureTestPassed = true;
//        }
//    }
//}

//######################################## TC_05##############################################

//    CommonActions commonActions;
//    private Scenario scenario;
//    Page page;
//    Page privacyPage;
//    String testFinish = "";
//    String XrayIssueKey = "";
//
//    public GenericSteps(CommonActions con)
//    {
//        this.commonActions = con;
//    }
//
//    private final static Logger LOGGER = Logger.getLogger(GenericSteps.class);
//
//    @Before
//    public void before(Scenario s) throws Exception {
//        this.scenario = s;
//        try {
//
//            page = commonActions.getPage();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if ((Configurations.RunOnBrowserStack).equals("Y")) {
//            commonActions.initReports(s.getName() + "_" + System.getProperty("browser"));
//        } else {
//            commonActions.initReports(s.getName() + "_" + "chrome");
//        }
//        commonActions.setfeaturefilenameandsceanrio(s.getId(), s.getName());
//        commonActions.setScenario(s);
//    }
//
//    /**
//     * Description Closing the resources after execution of each scenario
//     *
//     * @throws IOException
//     */
//    @After
//    public void after(Scenario s)
//    {
//        commonActions.quit();
//    }
//
//    @Given("User launches lufthansa web application")
//    public void user_launches_lufthansa_web_application() {
//        try {
//            page.navigate(Configurations.Appurl_QA_LH_De);
//            page.waitForLoadState();
//            System.out.println("Lufthansa url loaded : successfully");
//            commonActions.handle_Privacy_Settings_Page();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @And("User launches {string} web application")
//    public void user_launches_web_application(String tenant) {
//        LOGGER.info(CommonActions.getCurrentMethodName());
//        try
//        {
//
//            System.out.println("App_type : " + tenant);
//            if (tenant.equalsIgnoreCase("swiss")) {
//                page.navigate(Configurations.Appurl_QA_SN_Fr_B2);
//            }
//            else if (tenant.equalsIgnoreCase("lh")) {
//                page.navigate(Configurations.Appurl_QA_LH_De);
//            }
//            else if (tenant.equalsIgnoreCase("amadeus")) {
//                page.navigate(Configurations.Appurl_QA_Amadeus_B2);
//            }
//            else {
//                page.navigate(Configurations.Appurl_QA_LH_De);
//            }
//
//            page.waitForLoadState();
//            System.out.println("Lufthansa url loaded : successfully");
//            commonActions.handle_Privacy_Settings_Page();
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            LOGGER.error(e.getMessage());
//        }
//
//    }
//
//    @And("User chooses {string}")
//    public void user_chooses(String locator) {
//        commonActions.click(locator);
//    }
//
//
//
//    @Given("User enters {string} in login page")
//    public void user_enters_credential_in_login_page(String jsonKeyDetails) {
//        try {
//            LOGGER.info(CommonActions.getCurrentMethodName());
//            commonActions.user_Performs_Amadues_Login(jsonKeyDetails);
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
//        }
//    }
//
//    @And("User enters {string} in {string}")
//    public void user_enters_data_in_text_field(String jsonDataKey, String locator) {
//
//        try {
//            String data = commonActions.getTestData(jsonDataKey);
//            commonActions.enterText(locator, data);
//        } catch (Exception e) {
//            System.out.println("Exception in enterText : " + e.getMessage());
//        }
//    }
//
//    @And("User handles Privacy Settings Page and proceeds with booking")
//    public void User_handle_Privacy_Settings_Page() {
//        page.waitForTimeout(200);
//        try {
//            System.out.println("Privacy Handle Function Body");
//
//            page.waitForPopup(new Page.WaitForPopupOptions()
//                    .setPredicate(p -> p.context().pages().size() == 2), () ->
//            {
//                page.locator("(//button[text()='Submit'])[1]").click();
//
//            });
//            System.out.println("Submit button clicked: Successfully");
//
//            List<Page> pages = page.context().pages();
//            for (Page tabs : pages) {
//                tabs.waitForLoadState();
//                System.out.println(tabs.url());
//            }
//            Page PrivacyPage = pages.get(1);
//            privacyPage=PrivacyPage;
//
//            System.out.println("Submit button :New URL");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.waitForLoadState();
//            PrivacyPage.locator(commonActions.getLocatorStr("Privacy")).isVisible();
//
//            PrivacyPage.click(commonActions.getLocatorStr("Privacy"));
//            System.out.println("Privacy setting page handled : successfully");
//
//            PrivacyPage.waitForLoadState(LoadState.LOAD);
//
//            //Select classS
//            PrivacyPage.locator("(//div[text()='Economy'])[1]").first().click();
//
//            //Select class from econumy
//            PrivacyPage.locator("//button[@id=\"selectFare-STWWM4FF0E\"]/span[1]").first().click();
//            PrivacyPage.waitForTimeout(10000);
//
//            //select return class
//            PrivacyPage.locator("(//div[text()='Economy'])[1]").first().click();
//
//            //Select class from econumy
//            PrivacyPage.locator("//button[@id=\"selectFare-STWWM4FF0E\"]/span[1]").first().click();
//            PrivacyPage.waitForTimeout(10000);
//
//            //Enter Passenger details
//            PrivacyPage.locator("//span[text()='Enter passenger details']").first().click();
//
//            //select First Name
//            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("Test");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("User");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your email address']").fill("test@yopmail.com");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your country calling code']").fill("+49");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your mobile phone']").fill("123456");
//            PrivacyPage.waitForTimeout(3000);
//            PrivacyPage.pause();
//
//            //click on Confirm
//            PrivacyPage.locator("//button[contains(@class,\"mat-button\")]//span[contains(text(),'Confirm')]").first().click();
//            PrivacyPage.waitForTimeout(30000);
//            PrivacyPage.pause();
//
//            //Continue to Payment
//            PrivacyPage.locator("//span[contains(text(),'Continue to payment')]").first().click();
//            PrivacyPage.waitForTimeout(30000);
//            PrivacyPage.pause();
//
//            // select credit card
//            PrivacyPage.locator("//label[contains(text(),'Credit')]/..//input[@id='radio_1']").first().click();
//            PrivacyPage.waitForTimeout(10000);
//
//            // creadit card type- Visa
//            PrivacyPage.locator("//div[contains(@class,\"cardtype-select\")]//span[contains(@class,\"dropdown-icon\")]").first().click();
//            PrivacyPage.querySelector("//li[@class='mdc-list-item']//span[contains(text(), 'Visa')]").click();
//            PrivacyPage.waitForTimeout(2000);
//
//            // card number
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"number-label\"]").fill("4000000000001000");
//            PrivacyPage.waitForTimeout(2000);
//
//            // card holder name
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"name-label\"]").fill("Lisa John");
//            PrivacyPage.waitForTimeout(2000);
//
//            // exp month
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-month-label\"]").fill("06");
//            PrivacyPage.waitForTimeout(2000);
//
//            // exp year
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-year-label\"]").fill("26");
//            PrivacyPage.waitForTimeout(2000);
//
//            // cvv
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"cvv-label\"]").fill("123");
//            PrivacyPage.waitForTimeout(2000);
//
//            //name and street
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"street-label\"]").fill("street name");
//            PrivacyPage.waitForTimeout(2000);
//
//            // zip name
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"zip-label\"]").fill("123456");
//            PrivacyPage.waitForTimeout(2000);
//
//            // city
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"city-label\"]").fill("Frankfurt");
//            PrivacyPage.waitForTimeout(2000);
//
//            // terms and cond
//            PrivacyPage.locator("//input[@type='checkbox' and contains(@class, 'mdc-checkbox__native-control') and @id='terms-checkbox']").first().click();
//            PrivacyPage.waitForTimeout(5000);
//
//            // pay now
//            PrivacyPage.locator("//span[contains(text(), 'Pay Now')]").first().click();
//            System.out.println("PNR created : successfully");
//            Thread.sleep(90000);
//
//            privacyPage.locator("//div[text()='Your booking is confirmed!']").click();
//            PrivacyPage.waitForTimeout(10000);
//            Thread.sleep(10000);
//            PrivacyPage.pause();
//            //page.bringToFront();
//        }
//
//        catch (Exception e)
//        {
//            e.getMessage();
//            LOGGER.error(e.getMessage());
//        }
//    }
//
//    @And("User scrolls up")
//    public void userScrollsUp()
//    {
//        // Scroll up the page
//        page.evaluate("window.scrollTo(0, 0);");
//    }
//
////    @And("User opens the Amadeus Agate Picker")
////    public void user_opens_amadeus_agate_picker()
////    {
////        try
////        {
////            //System.out.println("App_type : " + tenant);
////            page.navigate(Configurations.Agate_home);
////            page.waitForLoadState();
////
////            System.out.println("Amadeus Agate Picker URL loaded successfully");
////            commonActions.handle_Privacy_Settings_Page();
////        } catch (Exception e)
////        {
////            e.printStackTrace();
////            LOGGER.error(e.getMessage());
////        }
////    }
//
//    private void saveTestResultsToXray(Scenario s) {
//
//        ZonedDateTime finishDateTime = ZonedDateTime.now();
//        testFinish = finishDateTime.truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
//        Log.info("Test Finish Time: " + testFinish);
//
//        if (s.isFailed()) {
//            Log.error("Test Failed!");
//            JunitRunner.featureTestPassed = false;
//            Xray.writeResultsForSingleTest(JunitRunner.ExecutionID, XrayIssueKey, XRAY_CONFIG.TEST_STATUS_FAIL,
//                    JunitRunner.testStart, testFinish);
//        } else {
//            if (JunitRunner.featureTestPassed == true) {
//                Log.info("Test Passed!");
//                Xray.writeResultsForSingleTest(JunitRunner.ExecutionID, XrayIssueKey, XRAY_CONFIG.TEST_STATUS_PASS,
//                        JunitRunner.testStart, testFinish);
//            }
//        }
//    }
//
//    private void checkNewTest(Scenario s) {
//        XrayIssueKey = XrayHelper.getTestIdFromFileName(s.getId());
//
//        if (!JunitRunner.currentXrayIssueKey.contains(XrayIssueKey)) {
//            System.out.println("This is a new Feature!");
//            JunitRunner.currentXrayIssueKey = XrayIssueKey;
//            JunitRunner.featureTestPassed = true;
//        }
//    }
//}

//##################################### TC_006 #####################################

//    CommonActions commonActions;
//    private Scenario scenario;
//    Page page;
//    Page privacyPage;
//    String testFinish = "";
//    String XrayIssueKey = "";
//
//    public GenericSteps(CommonActions con) {
//        this.commonActions = con;
//    }
//
//    private final static Logger LOGGER = Logger.getLogger(GenericSteps.class);
//
//    @Before
//    public void before(Scenario s) throws Exception {
//        this.scenario = s;
//        try {
//
//            page = commonActions.getPage();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if ((Configurations.RunOnBrowserStack).equals("Y")) {
//            commonActions.initReports(s.getName() + "_" + System.getProperty("browser"));
//        } else {
//            commonActions.initReports(s.getName() + "_" + "chrome");
//        }
//        commonActions.setfeaturefilenameandsceanrio(s.getId(), s.getName());
//        commonActions.setScenario(s);
//    }
//
//    /**
//     * Description Closing the resources after execution of each scenario
//     *
//     * @throws IOException
//     */
//    @After
//    public void after(Scenario s)
//    {
//        commonActions.quit();
//    }
//
//    @Given("User launches lufthansa web application")
//    public void user_launches_lufthansa_web_application() {
//        try {
//            page.navigate(Configurations.Appurl_QA_LH_De);
//            page.waitForLoadState();
//            System.out.println("Lufthansa url loaded : successfully");
//            commonActions.handle_Privacy_Settings_Page();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @And("User launches {string} web application")
//    public void user_launches_web_application(String tenant) {
//        LOGGER.info(CommonActions.getCurrentMethodName());
//        try
//        {
//
//            System.out.println("App_type : " + tenant);
//            if (tenant.equalsIgnoreCase("swiss")) {
//                page.navigate(Configurations.Appurl_QA_SN_Fr_B2);
//            }
//            else if (tenant.equalsIgnoreCase("lh")) {
//                page.navigate(Configurations.Appurl_QA_LH_De);
//            }
//            else if (tenant.equalsIgnoreCase("amadeus")) {
//                page.navigate(Configurations.Appurl_QA_Amadeus_B2);
//            }
//            else {
//                page.navigate(Configurations.Appurl_QA_LH_De);
//            }
//
//            page.waitForLoadState();
//            System.out.println("Lufthansa url loaded : successfully");
//            commonActions.handle_Privacy_Settings_Page();
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            LOGGER.error(e.getMessage());
//        }
//
//    }
//
//    @And("User chooses {string}")
//    public void user_chooses(String locator) {
//        commonActions.click(locator);
//    }
//
//    @Given("User enters {string} in login page")
//    public void user_enters_credential_in_login_page(String jsonKeyDetails) {
//        try {
//            LOGGER.info(CommonActions.getCurrentMethodName());
//            commonActions.user_Performs_Amadues_Login(jsonKeyDetails);
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
//        }
//    }
//    @And("User clicks on {string}")
//    public void user_clicks_on(String locator) {
//        if (locator.equals("BackToMerchant")) {
//            commonActions.clickOnIframeElement(locator);
//        } else {
//            commonActions.waitForLoadState();
//            commonActions.click(locator);
//        }
//    }
//
//
//
//    @And("User enters {string} in {string}")
//    public void user_enters_data_in_text_field(String jsonDataKey, String locator) {
//
//        try {
//            String data = commonActions.getTestData(jsonDataKey);
//            commonActions.enterText(locator, data);
//        } catch (Exception e) {
//            System.out.println("Exception in enterText : " + e.getMessage());
//        }
//    }
//
//    @And("User handles Privacy Settings Page and proceeds with booking")
//    public void User_handle_Privacy_Settings_Page() {
//        page.waitForTimeout(200);
//        try {
//            System.out.println("Privacy Handle Function Body");
//
//            page.waitForPopup(new Page.WaitForPopupOptions()
//                    .setPredicate(p -> p.context().pages().size() == 2), () ->
//            {
//                page.locator("(//button[text()='Submit'])[1]").click();
//
//            });
//            System.out.println("Submit button clicked: Successfully");
//
//            List<Page> pages = page.context().pages();
//            for (Page tabs : pages) {
//                tabs.waitForLoadState();
//                System.out.println(tabs.url());
//            }
//            Page PrivacyPage = pages.get(1);
//            privacyPage = PrivacyPage;
//
//            System.out.println("Submit button :New URL");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.waitForLoadState();
//            PrivacyPage.locator(commonActions.getLocatorStr("Privacy")).isVisible();
//
//            PrivacyPage.click(commonActions.getLocatorStr("Privacy"));
//            System.out.println("Privacy setting page handled : successfully");
//
//            PrivacyPage.waitForLoadState(LoadState.LOAD);
//
//            //Select classS
//            PrivacyPage.locator("(//span[@class='refx-display-1 flight-starting-price-label ng-star-inserted'])[1]").first().click();
//            //div[@id='2c19aaad-f819-4df5-b916-a0de20c5a38a-flightCardecoPremium-buttonLabel']
//            PrivacyPage.waitForTimeout(10000);
//
//            //Select Fare class from Economy
//            PrivacyPage.locator("(//span[text()='Select'])[2]").first().click();
//            PrivacyPage.waitForTimeout(10000);
////            PrivacyPage.pause();
//
//            //select return class
////            PrivacyPage.locator("(//button[@data-fare-family-group='eco'])[1]").first().click();
////            PrivacyPage.waitForTimeout(10000);
////            PrivacyPage.pause();
//
//            //Select Fare class from econumy
////            PrivacyPage.locator("//button[@id='selectFare-BFEUM2NC0E']//span[contains(text(),'Select')]").first().click();
////            PrivacyPage.waitForTimeout(10000);
////            PrivacyPage.pause();
//
//
//            //Enter Passenger details
//            PrivacyPage.locator("//span[text()='Enter passenger details']").first().click();
//            PrivacyPage.waitForTimeout(10000);
//            PrivacyPage.pause();
//
//            //Enter First PAssenger Details
//            //select First Name
//            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("Test");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("User");
//            PrivacyPage.waitForTimeout(3000);
//
////select passenger DOB
//            PrivacyPage.locator("//span[@class='mat-mdc-button-touch-target']").first().click();
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//span[text()=' 1990 ']").first().click();
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("").first().click();
//            PrivacyPage.waitForTimeout(3000);
//
//
//            PrivacyPage.locator("//input[@placeholder='Your email address']").fill("test@yopmail.com");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your country calling code']").fill("+49");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your mobile phone']").fill("123456");
//            PrivacyPage.waitForTimeout(3000);
//
//            // Next passerger
//            PrivacyPage.locator("//span[text()='Next passenger']").first().click();
//            PrivacyPage.waitForTimeout(3000);
////            PrivacyPage.pause();
//
////
//
//
//
//
//            //click on Confirm
//            //PrivacyPage.locator("//span[contains(text(),'Confirm')]").first().click();
//            PrivacyPage.locator("//button[contains(@class,\"mat-button\")]//span[contains(text(),'Confirm')]").first().click();
//            PrivacyPage.waitForTimeout(5000);
//            PrivacyPage.pause();
//
//            //Continue to Payment
//            PrivacyPage.locator("//span[contains(text(),'Continue to payment')]").first().click();
//            //PrivacyPage.locator("//button[contains(@class,\"mat-button\"mat-button)]//span[contains(text(),'Continue to payment')]").first().click();
//            PrivacyPage.waitForTimeout(5000);
//
//            // select credit card
//            PrivacyPage.locator("//label[contains(text(),'Credit')]/..//input[@id='radio_1']").first().click();
//            PrivacyPage.waitForTimeout(5000);
//            PrivacyPage.pause();
//
//            // creadit card type- Visa
//            PrivacyPage.locator("//div[contains(@class,\"cardtype-select\")]//span[contains(@class,\"dropdown-icon\")]").first().click();
//            PrivacyPage.querySelector("//li[@class='mdc-list-item']//span[contains(text(), 'Visa')]").click();
//            PrivacyPage.waitForTimeout(2000);
//
//            // card number
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"number-label\"]").fill("4000000000001000");
//            PrivacyPage.waitForTimeout(2000);
//
//            // card holder name
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"name-label\"]").fill("Lisa John");
//            PrivacyPage.waitForTimeout(2000);
//
//            // exp month
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-month-label\"]").fill("06");
//            PrivacyPage.waitForTimeout(2000);
//            PrivacyPage.pause();
//
//            // exp year
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-year-label\"]").fill("26");
//            PrivacyPage.waitForTimeout(2000);
//
//            // cvv
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"cvv-label\"]").fill("123");
//            PrivacyPage.waitForTimeout(2000);
//
//            //name and street
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"street-label\"]").fill("street name");
//            PrivacyPage.waitForTimeout(2000);
//
//            // zip name
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"zip-label\"]").fill("123456");
//            PrivacyPage.waitForTimeout(2000);
//
//            // city
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"city-label\"]").fill("Frankfurt");
//            PrivacyPage.waitForTimeout(2000);
//
//            // terms and cond
//            PrivacyPage.locator("//input[@type='checkbox' and contains(@class, 'mdc-checkbox__native-control') and @id='terms-checkbox']").first().click();
//            PrivacyPage.waitForTimeout(5000);
//            PrivacyPage.pause();
//
//            // pay now
//            PrivacyPage.locator("//span[contains(text(), 'Pay Now')]").first().click();
//            System.out.println("PNR created : successfully");
//            Thread.sleep(3000);
//            PrivacyPage.pause();
//
//            privacyPage.locator("//div[text()='Your booking is confirmed!']").click();
//            Thread.sleep(5000);
//            PrivacyPage.pause();
//            page.bringToFront();
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            LOGGER.error(e.getMessage());
//        }
//    }
//
//    @And("User scrolls up")
//    public void userScrollsUp()
//    {
//        // Scroll up the page
//        page.evaluate("window.scrollTo(0, 0);");
//    }
//
//
//    @And("User opens the Amadeus Agate Picker")
//    public void user_opens_amadeus_agate_picker()
//    {
//        try
//        {
//            //System.out.println("App_type : " + tenant);
//            page.navigate(Configurations.Agate_home);
//            page.waitForLoadState();
//
//            System.out.println("Amadeus Agate Picker URL loaded successfully");
//            commonActions.handle_Privacy_Settings_Page();
//        } catch (Exception e)
//        {
//            e.printStackTrace();
//            LOGGER.error(e.getMessage());
//        }
//    }
//
//    private void saveTestResultsToXray(Scenario s) {
//
//        ZonedDateTime finishDateTime = ZonedDateTime.now();
//        testFinish = finishDateTime.truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
//        Log.info("Test Finish Time: " + testFinish);
//
//        if (s.isFailed()) {
//            Log.error("Test Failed!");
//            JunitRunner.featureTestPassed = false;
//            Xray.writeResultsForSingleTest(JunitRunner.ExecutionID, XrayIssueKey, XRAY_CONFIG.TEST_STATUS_FAIL,
//                    JunitRunner.testStart, testFinish);
//        } else {
//            if (JunitRunner.featureTestPassed == true) {
//                Log.info("Test Passed!");
//                Xray.writeResultsForSingleTest(JunitRunner.ExecutionID, XrayIssueKey, XRAY_CONFIG.TEST_STATUS_PASS,
//                        JunitRunner.testStart, testFinish);
//            }
//        }
//    }
//
//    private void checkNewTest(Scenario s) {
//        XrayIssueKey = XrayHelper.getTestIdFromFileName(s.getId());
//
//        if (!JunitRunner.currentXrayIssueKey.contains(XrayIssueKey)) {
//            System.out.println("This is a new Feature!");
//            JunitRunner.currentXrayIssueKey = XrayIssueKey;
//            JunitRunner.featureTestPassed = true;
//        }
//    }
//}


//######################################  TC_007  ##################################

//    CommonActions commonActions;
//    private Scenario scenario;
//    Page page;
//    Page privacyPage;
//    String testFinish = "";
//    String XrayIssueKey = "";
//
//    public GenericSteps(CommonActions con) {
//        this.commonActions = con;
//    }
//
//    private final static Logger LOGGER = Logger.getLogger(GenericSteps.class);
//
//    @Before
//    public void before(Scenario s) throws Exception {
//        this.scenario = s;
//        try {
//
//            page = commonActions.getPage();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if ((Configurations.RunOnBrowserStack).equals("Y")) {
//            commonActions.initReports(s.getName() + "_" + System.getProperty("browser"));
//        } else {
//            commonActions.initReports(s.getName() + "_" + "chrome");
//        }
//        commonActions.setfeaturefilenameandsceanrio(s.getId(), s.getName());
//        commonActions.setScenario(s);
//    }
//
//    /**
//     * Description Closing the resources after execution of each scenario
//     *
//     * @throws IOException
//     */
//    @After
//    public void after(Scenario s)
//    {
//        commonActions.quit();
//    }
//
//    @Given("User launches lufthansa web application")
//    public void user_launches_lufthansa_web_application() {
//        try {
//            page.navigate(Configurations.Appurl_QA_LH_De);
//            page.waitForLoadState();
//            System.out.println("Lufthansa url loaded : successfully");
//            commonActions.handle_Privacy_Settings_Page();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @And("User launches {string} web application")
//    public void user_launches_web_application(String tenant) {
//        LOGGER.info(CommonActions.getCurrentMethodName());
//        try
//        {
//
//            System.out.println("App_type : " + tenant);
//            if (tenant.equalsIgnoreCase("swiss")) {
//                page.navigate(Configurations.Appurl_QA_SN_Fr_B2);
//            }
//            else if (tenant.equalsIgnoreCase("lh")) {
//                page.navigate(Configurations.Appurl_QA_LH_De);
//            }
//            else if (tenant.equalsIgnoreCase("amadeus")) {
//                page.navigate(Configurations.Appurl_QA_Amadeus_B2);
//            }
//            else {
//                page.navigate(Configurations.Appurl_QA_LH_De);
//            }
//
//            page.waitForLoadState();
//            System.out.println("Lufthansa url loaded : successfully");
//            commonActions.handle_Privacy_Settings_Page();
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            LOGGER.error(e.getMessage());
//        }
//
//    }
//
//    @And("User chooses {string}")
//    public void user_chooses(String locator) {
//        commonActions.click(locator);
//    }
//
//    @Given("User enters {string} in login page")
//    public void user_enters_credential_in_login_page(String jsonKeyDetails) {
//        try {
//            LOGGER.info(CommonActions.getCurrentMethodName());
//            commonActions.user_Performs_Amadues_Login(jsonKeyDetails);
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
//        }
//    }
//    @And("User clicks on {string}")
//    public void user_clicks_on(String locator) {
//        if (locator.equals("BackToMerchant")) {
//            commonActions.clickOnIframeElement(locator);
//        } else {
//            commonActions.waitForLoadState();
//            commonActions.click(locator);
//        }
//    }
//
//
//
//    @And("User enters {string} in {string}")
//    public void user_enters_data_in_text_field(String jsonDataKey, String locator) {
//
//        try {
//            String data = commonActions.getTestData(jsonDataKey);
//            commonActions.enterText(locator, data);
//        } catch (Exception e) {
//            System.out.println("Exception in enterText : " + e.getMessage());
//        }
//    }
//
//    @And("User handles Privacy Settings Page and proceeds with booking")
//    public void User_handle_Privacy_Settings_Page() {
//        page.waitForTimeout(200);
//        try {
//            System.out.println("Privacy Handle Function Body");
//
//            page.waitForPopup(new Page.WaitForPopupOptions()
//                    .setPredicate(p -> p.context().pages().size() == 2), () ->
//            {
//                page.locator("(//button[text()='Submit'])[1]").click();
//
//            });
//            System.out.println("Submit button clicked: Successfully");
//
//            List<Page> pages = page.context().pages();
//            for (Page tabs : pages) {
//                tabs.waitForLoadState();
//                System.out.println(tabs.url());
//            }
//            Page PrivacyPage = pages.get(1);
//            privacyPage = PrivacyPage;
//
//            System.out.println("Submit button :New URL");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.waitForLoadState();
//            PrivacyPage.locator(commonActions.getLocatorStr("Privacy")).isVisible();
//
//            PrivacyPage.click(commonActions.getLocatorStr("Privacy"));
//            System.out.println("Privacy setting page handled : successfully");
//
//            PrivacyPage.waitForLoadState(LoadState.LOAD);
//
//            //Select classS
//            PrivacyPage.locator("(//span[@class='refx-display-1 flight-starting-price-label ng-star-inserted'])[1]").first().click();
//            //div[@id='2c19aaad-f819-4df5-b916-a0de20c5a38a-flightCardecoPremium-buttonLabel']
//            PrivacyPage.waitForTimeout(10000);
//
//            //Select Fare class from Economy
//            PrivacyPage.locator("(//span[text()='Select'])[2]").first().click();
//            PrivacyPage.waitForTimeout(10000);
////            PrivacyPage.pause();
//
//            //select return class
////            PrivacyPage.locator("(//button[@data-fare-family-group='eco'])[1]").first().click();
////            PrivacyPage.waitForTimeout(10000);
////            PrivacyPage.pause();
//
//            //Select Fare class from econumy
////            PrivacyPage.locator("//button[@id='selectFare-BFEUM2NC0E']//span[contains(text(),'Select')]").first().click();
////            PrivacyPage.waitForTimeout(10000);
////            PrivacyPage.pause();
//
//            //Enter Passenger details
//            PrivacyPage.locator("//span[text()='Enter passenger details']").first().click();
//            PrivacyPage.waitForTimeout(10000);
//            PrivacyPage.pause();
//
//            //Enter First PAssenger Details
//            //select First Name
//            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("Test");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("User");
//            PrivacyPage.waitForTimeout(3000);
//// select gender
//
//
//            PrivacyPage.locator("//input[@placeholder='Your email address']").fill("test@yopmail.com");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your country calling code']").fill("+49");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your mobile phone']").fill("123456");
//            PrivacyPage.waitForTimeout(3000);
//
//           // Next passerger
//            PrivacyPage.locator("//span[text()='Next passenger']").first().click();
//            PrivacyPage.waitForTimeout(3000);
////            PrivacyPage.pause();
//
////            Enter the adult 2 information
////             Enter first name
//            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("jon");
//            PrivacyPage.waitForTimeout(5000);
//
//            //Enter Last NAme
//            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("lee");
//            PrivacyPage.waitForTimeout(5000);
//            PrivacyPage.pause();
//            // select calender
//            PrivacyPage.locator("//span[@class='mat-mdc-button-touch-target']").click();
//            PrivacyPage.waitForTimeout(3000);
//            //span[@class='mat-mdc-button-touch-target']
//// select year
//            PrivacyPage.locator("//span[text()=' 2021 ']").click();
//            PrivacyPage.waitForTimeout(3000);
//
//            //select month
//            PrivacyPage.locator("//span[text()=' OCT ']").click();
//            PrivacyPage.waitForTimeout(3000);
//
//            //select day
//            PrivacyPage.locator("//span[text()=' 30 ']").click();
//            //PrivacyPage.waitForTimeout(5000);
//            Thread.sleep(3000);
//            //PrivacyPage.waitForTimeout(30000);
//            PrivacyPage.pause();
//
//            //Next passerger
//            PrivacyPage.locator("//span[text()='Next passenger']").first().click();
//            PrivacyPage.waitForTimeout(3000);
//            PrivacyPage.pause();
//
//
//            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("jolly");
//            PrivacyPage.waitForTimeout(3000);
//
//            //Enter Last Name
//            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("lee");
//            PrivacyPage.waitForTimeout(3000);
//
//            //DOB
//            PrivacyPage.locator("//span[@class='mat-mdc-button-touch-target']").click();
//            PrivacyPage.waitForTimeout(3000);
//            PrivacyPage.pause();
//
//           // select year
//            PrivacyPage.locator("//span[text()=' 2023 ']").click();
//            PrivacyPage.waitForTimeout(3000);
//
//            //select month
//            PrivacyPage.locator("//span[text()=' OCT ']").click();
//            PrivacyPage.waitForTimeout(3000);
//
//            //select day
//            PrivacyPage.locator("//span[text()=' 23 ']").click();
//            //PrivacyPage.waitForTimeout(5000);
//            Thread.sleep(3000);
//            //PrivacyPage.waitForTimeout(30000);
//            PrivacyPage.pause();
//
//            //click on Confirm
//            //PrivacyPage.locator("//span[contains(text(),'Confirm')]").first().click();
//            PrivacyPage.locator("//button[contains(@class,\"mat-button\")]//span[contains(text(),'Confirm')]").first().click();
//            PrivacyPage.waitForTimeout(5000);
//            PrivacyPage.pause();
//
//            //Continue to Payment
//            PrivacyPage.locator("//span[contains(text(),'Continue to payment')]").first().click();
//            //PrivacyPage.locator("//button[contains(@class,\"mat-button\"mat-button)]//span[contains(text(),'Continue to payment')]").first().click();
//            PrivacyPage.waitForTimeout(5000);
//
//            // select credit card
//            PrivacyPage.locator("//label[contains(text(),'Credit')]/..//input[@id='radio_1']").first().click();
//            PrivacyPage.waitForTimeout(5000);
//            PrivacyPage.pause();
//
//            // creadit card type- Visa
//            PrivacyPage.locator("//div[contains(@class,\"cardtype-select\")]//span[contains(@class,\"dropdown-icon\")]").first().click();
//            PrivacyPage.querySelector("//li[@class='mdc-list-item']//span[contains(text(), 'Visa')]").click();
//            PrivacyPage.waitForTimeout(2000);
//
//            // card number
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"number-label\"]").fill("4000000000001000");
//            PrivacyPage.waitForTimeout(2000);
//
//            // card holder name
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"name-label\"]").fill("Lisa John");
//            PrivacyPage.waitForTimeout(2000);
//
//            // exp month
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-month-label\"]").fill("06");
//            PrivacyPage.waitForTimeout(2000);
//            PrivacyPage.pause();
//
//            // exp year
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-year-label\"]").fill("26");
//            PrivacyPage.waitForTimeout(2000);
//
//            // cvv
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"cvv-label\"]").fill("123");
//            PrivacyPage.waitForTimeout(2000);
//
//            //name and street
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"street-label\"]").fill("street name");
//            PrivacyPage.waitForTimeout(2000);
//
//            // zip name
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"zip-label\"]").fill("123456");
//            PrivacyPage.waitForTimeout(2000);
//
//            // city
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"city-label\"]").fill("Frankfurt");
//            PrivacyPage.waitForTimeout(2000);
//
//            // terms and cond
//            PrivacyPage.locator("//input[@type='checkbox' and contains(@class, 'mdc-checkbox__native-control') and @id='terms-checkbox']").first().click();
//            PrivacyPage.waitForTimeout(5000);
//            PrivacyPage.pause();
//
//            // pay now
//            PrivacyPage.locator("//span[contains(text(), 'Pay Now')]").first().click();
//            System.out.println("PNR created : successfully");
//            Thread.sleep(3000);
//            PrivacyPage.pause();
//
//            privacyPage.locator("//div[text()='Your booking is confirmed!']").click();
//            Thread.sleep(5000);
//            PrivacyPage.pause();
//            page.bringToFront();
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            LOGGER.error(e.getMessage());
//        }
//    }
//
//    @And("User scrolls up")
//    public void userScrollsUp()
//    {
//        // Scroll up the page
//        page.evaluate("window.scrollTo(0, 0);");
//    }
//
//
//    @And("User opens the Amadeus Agate Picker")
//    public void user_opens_amadeus_agate_picker()
//    {
//        try
//        {
//            //System.out.println("App_type : " + tenant);
//            page.navigate(Configurations.Agate_home);
//            page.waitForLoadState();
//
//            System.out.println("Amadeus Agate Picker URL loaded successfully");
//            commonActions.handle_Privacy_Settings_Page();
//        } catch (Exception e)
//        {
//            e.printStackTrace();
//            LOGGER.error(e.getMessage());
//        }
//    }
//
//    private void saveTestResultsToXray(Scenario s) {
//
//        ZonedDateTime finishDateTime = ZonedDateTime.now();
//        testFinish = finishDateTime.truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
//        Log.info("Test Finish Time: " + testFinish);
//
//        if (s.isFailed()) {
//            Log.error("Test Failed!");
//            JunitRunner.featureTestPassed = false;
//            Xray.writeResultsForSingleTest(JunitRunner.ExecutionID, XrayIssueKey, XRAY_CONFIG.TEST_STATUS_FAIL,
//                    JunitRunner.testStart, testFinish);
//        } else {
//            if (JunitRunner.featureTestPassed == true) {
//                Log.info("Test Passed!");
//                Xray.writeResultsForSingleTest(JunitRunner.ExecutionID, XrayIssueKey, XRAY_CONFIG.TEST_STATUS_PASS,
//                        JunitRunner.testStart, testFinish);
//            }
//        }
//    }
//
//    private void checkNewTest(Scenario s) {
//        XrayIssueKey = XrayHelper.getTestIdFromFileName(s.getId());
//
//        if (!JunitRunner.currentXrayIssueKey.contains(XrayIssueKey)) {
//            System.out.println("This is a new Feature!");
//            JunitRunner.currentXrayIssueKey = XrayIssueKey;
//            JunitRunner.featureTestPassed = true;
//        }
//    }
//}

//#################################### TC008  #######################################
//CommonActions commonActions;
//    private Scenario scenario;
//    Page page;
//    Page privacyPage;
//    String testFinish = "";
//    String XrayIssueKey = "";
//
//    public GenericSteps(CommonActions con) {
//        this.commonActions = con;
//    }
//
//    private final static Logger LOGGER = Logger.getLogger(GenericSteps.class);
//
//    @Before
//    public void before(Scenario s) throws Exception {
//        this.scenario = s;
//        try {
//
//            page = commonActions.getPage();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if ((Configurations.RunOnBrowserStack).equals("Y")) {
//            commonActions.initReports(s.getName() + "_" + System.getProperty("browser"));
//        } else {
//            commonActions.initReports(s.getName() + "_" + "chrome");
//        }
//        commonActions.setfeaturefilenameandsceanrio(s.getId(), s.getName());
//        commonActions.setScenario(s);
//    }
//
//    /**
//     * Description Closing the resources after execution of each scenario
//     *
//     * @throws IOException
//     */
//    @After
//    public void after(Scenario s)
//    {
//        commonActions.quit();
//    }
//
//    @Given("User launches lufthansa web application")
//    public void user_launches_lufthansa_web_application() {
//        try {
//            page.navigate(Configurations.Appurl_QA_LH_De);
//            page.waitForLoadState();
//            System.out.println("Lufthansa url loaded : successfully");
//            commonActions.handle_Privacy_Settings_Page();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @And("User launches {string} web application")
//    public void user_launches_web_application(String tenant) {
//        LOGGER.info(CommonActions.getCurrentMethodName());
//        try
//        {
//
//            System.out.println("App_type : " + tenant);
//            if (tenant.equalsIgnoreCase("swiss")) {
//                page.navigate(Configurations.Appurl_QA_SN_Fr_B2);
//            }
//            else if (tenant.equalsIgnoreCase("lh")) {
//                page.navigate(Configurations.Appurl_QA_LH_De);
//            }
//            else if (tenant.equalsIgnoreCase("amadeus")) {
//                page.navigate(Configurations.Appurl_QA_Amadeus_B2);
//            }
//            else {
//                page.navigate(Configurations.Appurl_QA_LH_De);
//            }
//
//            page.waitForLoadState();
//            System.out.println("Lufthansa url loaded : successfully");
//            commonActions.handle_Privacy_Settings_Page();
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            LOGGER.error(e.getMessage());
//        }
//
//    }
//
//    @And("User chooses {string}")
//    public void user_chooses(String locator) {
//        commonActions.click(locator);
//    }
//
//    @Given("User enters {string} in login page")
//    public void user_enters_credential_in_login_page(String jsonKeyDetails) {
//        try {
//            LOGGER.info(CommonActions.getCurrentMethodName());
//            commonActions.user_Performs_Amadues_Login(jsonKeyDetails);
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
//        }
//    }
//
//    @And("User enters {string} in {string}")
//    public void user_enters_data_in_text_field(String jsonDataKey, String locator) {
//
//        try {
//            String data = commonActions.getTestData(jsonDataKey);
//            commonActions.enterText(locator, data);
//        } catch (Exception e) {
//            System.out.println("Exception in enterText : " + e.getMessage());
//        }
//    }
//    @And("User clicks on {string}")
//    public void user_clicks_on(String locator) {
//        if (locator.equals("BackToMerchant")) {
//            commonActions.clickOnIframeElement(locator);
//        } else {
//            commonActions.waitForLoadState();
//            commonActions.click(locator);
//        }
//    }
//
//    @And("User handles Privacy Settings Page and proceeds with booking")
//    public void User_handle_Privacy_Settings_Page() {
//        page.waitForTimeout(200);
//        try {
//            System.out.println("Privacy Handle Function Body");
//
//            page.waitForPopup(new Page.WaitForPopupOptions()
//                    .setPredicate(p -> p.context().pages().size() == 2), () ->
//            {
//                page.locator("(//button[text()='Submit'])[1]").click();
//
//            });
//            System.out.println("Submit button clicked: Successfully");
//
//            List<Page> pages = page.context().pages();
//            for (Page tabs : pages) {
//                tabs.waitForLoadState();
//                System.out.println(tabs.url());
//            }
//            Page PrivacyPage = pages.get(1);
//            privacyPage = PrivacyPage;
//
//            System.out.println("Submit button :New URL");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.waitForLoadState();
//            PrivacyPage.locator(commonActions.getLocatorStr("Privacy")).isVisible();
//
//            PrivacyPage.click(commonActions.getLocatorStr("Privacy"));
//            System.out.println("Privacy setting page handled : successfully");
//
//            PrivacyPage.waitForLoadState(LoadState.LOAD);
//
//            //Select classS
//            PrivacyPage.locator("(//span[@class='refx-display-1 flight-starting-price-label ng-star-inserted'])[1]").first().click();
//            //div[@id='2c19aaad-f819-4df5-b916-a0de20c5a38a-flightCardecoPremium-buttonLabel']
//            PrivacyPage.waitForTimeout(10000);
//
//            //Select Fare class from Economy
//            PrivacyPage.locator("(//span[text()='Select'])[4]").first().click();
//            PrivacyPage.waitForTimeout(10000);
//            PrivacyPage.pause();
//
//            //select return class
////            PrivacyPage.locator("(//button[@data-fare-family-group='eco'])[1]").first().click();
////            PrivacyPage.waitForTimeout(10000);
////            PrivacyPage.pause();
//
//            //Select Fare class from econumy
////            PrivacyPage.locator("//button[@id='selectFare-BFEUM2NC0E']//span[contains(text(),'Select')]").first().click();
////            PrivacyPage.waitForTimeout(10000);
////            PrivacyPage.pause();
//
//            //Enter Passenger details
//            PrivacyPage.locator("//span[text()='Enter passenger details']").first().click();
//            PrivacyPage.waitForTimeout(10000);
//            PrivacyPage.pause();
//
//            //Enter First PAssenger Details
//            //select First Name
//            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("Test");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("User");
//            PrivacyPage.waitForTimeout(3000);
//// select gender
//            PrivacyPage.locator("//span[text()='Male']").first().click();
//            PrivacyPage.waitForTimeout(3000);
//
////enter date of birth
//            PrivacyPage.locator("//span[@class='mat-mdc-button-touch-target']").first().click();
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//span[text()=' 2011 ']").first().click();
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//span[text()=' NOV ']").first().click();
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//span[text()=' 30 ']").first().click();
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your email address']").fill("test@yopmail.com");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your country calling code']").fill("+49");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your mobile phone']").fill("123456");
//            PrivacyPage.waitForTimeout(3000);
//
//            //Next passerger
////            PrivacyPage.locator("//span[text()='Next passenger']").first().click();
////            PrivacyPage.waitForTimeout(3000);
////            PrivacyPage.pause();
//
//            //Enter the adult 2 information
//            // Enter first name
////            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("jon");
////            PrivacyPage.waitForTimeout(5000);
////
////            //Enter Last NAme
////            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("lee");
////            PrivacyPage.waitForTimeout(5000);
////
////            //Next passerger
////            PrivacyPage.locator("//span[text()='Next passenger']").first().click();
////            PrivacyPage.waitForTimeout(3000);
////            PrivacyPage.pause();
//
//            //Enter the child information
//            //Enter first name
////            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("jolly");
////            PrivacyPage.waitForTimeout(3000);
////
////            //Enter Last Name
////            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("lee");
////            PrivacyPage.waitForTimeout(3000);
////
////            //DOB
////            PrivacyPage.locator("//span[@class='mat-mdc-button-touch-target']").click();
////            PrivacyPage.waitForTimeout(3000);
////            PrivacyPage.pause();
//
//            //select year
////            PrivacyPage.locator("//span[text()=' 2021 ']").click();
////            PrivacyPage.waitForTimeout(3000);
////
////            //select month
////            PrivacyPage.locator("//span[text()=' OCT ']").click();
////            PrivacyPage.waitForTimeout(3000);
////
////            //select day
////            PrivacyPage.locator("//span[text()=' 23 ']").click();
////            //PrivacyPage.waitForTimeout(5000);
////            Thread.sleep(90000);
////            //PrivacyPage.waitForTimeout(30000);
////            PrivacyPage.pause();
//
//            //click on Confirm
//            //PrivacyPage.locator("//span[contains(text(),'Confirm')]").first().click();
//            PrivacyPage.locator("//button[contains(@class,\"mat-button\")]//span[contains(text(),'Confirm')]").first().click();
//            PrivacyPage.waitForTimeout(30000);
//            PrivacyPage.pause();
//
//            //Continue to Payment
//            PrivacyPage.locator("//span[contains(text(),'Continue to payment')]").first().click();
//            //PrivacyPage.locator("//button[contains(@class,\"mat-button\"mat-button)]//span[contains(text(),'Continue to payment')]").first().click();
//            PrivacyPage.waitForTimeout(30000);
//
//            // select credit card
//            PrivacyPage.locator("//label[contains(text(),'Credit')]/..//input[@id='radio_1']").first().click();
//            PrivacyPage.waitForTimeout(10000);
//            PrivacyPage.pause();
//
//            // creadit card type- Visa
//            PrivacyPage.locator("//div[contains(@class,\"cardtype-select\")]//span[contains(@class,\"dropdown-icon\")]").first().click();
//            //PrivacyPage.querySelector("//li[@class='mdc-list-item mdc-list-item--selected']//span[contains(text(), 'Mastercard')]").click();
//            PrivacyPage.querySelector("//span[contains(text(), 'Mastercard')]").click();
//            PrivacyPage.waitForTimeout(2000);
//
//            // card number
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"number-label\"]").fill("5200000000001096");
//            PrivacyPage.waitForTimeout(2000);
//
//            // card holder name
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"name-label\"]").fill("Lisa John");
//            PrivacyPage.waitForTimeout(2000);
//
//            // exp month
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-month-label\"]").fill("06");
//            PrivacyPage.waitForTimeout(2000);
//            PrivacyPage.pause();
//
//            // exp year
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-year-label\"]").fill("26");
//            PrivacyPage.waitForTimeout(2000);
//
//            // cvv
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"cvv-label\"]").fill("123");
//            PrivacyPage.waitForTimeout(2000);
//
//            //name and street
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"street-label\"]").fill("street name");
//            PrivacyPage.waitForTimeout(2000);
//
//            // zip name
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"zip-label\"]").fill("123456");
//            PrivacyPage.waitForTimeout(2000);
//
//            // city
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"city-label\"]").fill("Frankfurt");
//            PrivacyPage.waitForTimeout(2000);
//
//            // terms and cond
//            PrivacyPage.locator("//input[@type='checkbox' and contains(@class, 'mdc-checkbox__native-control') and @id='terms-checkbox']").first().click();
//            PrivacyPage.waitForTimeout(5000);
//            PrivacyPage.pause();
//
//            // pay now
//            PrivacyPage.locator("//span[contains(text(), 'Pay Now')]").first().click();
//            System.out.println("PNR created : successfully");
//            Thread.sleep(90000);
//            PrivacyPage.pause();
//
//            privacyPage.locator("//div[text()='Your booking is confirmed!']").click();
//            Thread.sleep(10000);
//            PrivacyPage.pause();
//            page.bringToFront();
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            LOGGER.error(e.getMessage());
//        }
//    }
//
//    @And("User scrolls up")
//    public void userScrollsUp()
//    {
//        // Scroll up the page
//        page.evaluate("window.scrollTo(0, 0);");
//    }
//
//
//    @And("User opens the Amadeus Agate Picker")
//    public void user_opens_amadeus_agate_picker()
//    {
//        try
//        {
//            //System.out.println("App_type : " + tenant);
//            page.navigate(Configurations.Agate_home);
//            page.waitForLoadState();
//
//            System.out.println("Amadeus Agate Picker URL loaded successfully");
//            commonActions.handle_Privacy_Settings_Page();
//        } catch (Exception e)
//        {
//            e.printStackTrace();
//            LOGGER.error(e.getMessage());
//        }
//    }
//
//    private void saveTestResultsToXray(Scenario s) {
//
//        ZonedDateTime finishDateTime = ZonedDateTime.now();
//        testFinish = finishDateTime.truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
//        Log.info("Test Finish Time: " + testFinish);
//
//        if (s.isFailed()) {
//            Log.error("Test Failed!");
//            JunitRunner.featureTestPassed = false;
//            Xray.writeResultsForSingleTest(JunitRunner.ExecutionID, XrayIssueKey, XRAY_CONFIG.TEST_STATUS_FAIL,
//                    JunitRunner.testStart, testFinish);
//        } else {
//            if (JunitRunner.featureTestPassed == true) {
//                Log.info("Test Passed!");
//                Xray.writeResultsForSingleTest(JunitRunner.ExecutionID, XrayIssueKey, XRAY_CONFIG.TEST_STATUS_PASS,
//                        JunitRunner.testStart, testFinish);
//            }
//        }
//    }
//
//    private void checkNewTest(Scenario s) {
//        XrayIssueKey = XrayHelper.getTestIdFromFileName(s.getId());
//
//        if (!JunitRunner.currentXrayIssueKey.contains(XrayIssueKey)) {
//            System.out.println("This is a new Feature!");
//            JunitRunner.currentXrayIssueKey = XrayIssueKey;
//            JunitRunner.featureTestPassed = true;
//        }
//    }
//}


//################################### OS TC 001 #####################################

//    CommonActions commonActions;
//    private Scenario scenario;
//    Page page;
//    Page privacyPage;
//    String testFinish = "";
//    String XrayIssueKey = "";
//    String PNR;
//
//    public GenericSteps(CommonActions con) {
//        this.commonActions = con;
//    }
//
//    private final static Logger LOGGER = Logger.getLogger(GenericSteps.class);
//
//    @Before
//    public void before(Scenario s) throws Exception {
//        this.scenario = s;
//        try {
//
//            page = commonActions.getPage();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if ((Configurations.RunOnBrowserStack).equals("Y")) {
//            commonActions.initReports(s.getName() + "_" + System.getProperty("browser"));
//        } else {
//            commonActions.initReports(s.getName() + "_" + "chrome");
//        }
//        commonActions.setfeaturefilenameandsceanrio(s.getId(), s.getName());
//        commonActions.setScenario(s);
//    }
//
//    /**
//     * Description Closing the resources after execution of each scenario
//     *
//     * @throws IOException
//     */
//    @After
//    public void after(Scenario s) {
//        commonActions.quit();
//    }
//
//    //For Lufthansa
////    @Given("User launches lufthansa web application")
////    public void user_launches_lufthansa_web_application() {
////        try {
////            page.navigate(Configurations.Appurl_QA_LH_De);
////            page.waitForLoadState();
////            System.out.println("Lufthansa url loaded : successfully");
////            commonActions.handle_Privacy_Settings_Page();
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }
//
//    //For Austrian
//    @Given("User launches lufthansa web application")
//    public void user_launches_austrian_web_application() {
//        try {
//            page.navigate(Configurations.Appurl_QA_OS_Fr_B2);
//            page.waitForLoadState();
//            System.out.println("Austrian url loaded : successfully");
//            commonActions.handle_Privacy_Settings_Page();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @And("User launches {string} web application")
//    public void user_launches_web_application(String tenant) {
//        LOGGER.info(CommonActions.getCurrentMethodName());
//
////        //For LH Tenant
////        try
////        {
////            System.out.println("App_type : " + tenant);
////            if (tenant.equalsIgnoreCase("swiss")) {
////                page.navigate(Configurations.Appurl_QA_SN_Fr_B2);
////            }
////            else if (tenant.equalsIgnoreCase("lh")) {
////                page.navigate(Configurations.Appurl_QA_LH_De);
////            }
////            else if (tenant.equalsIgnoreCase("amadeus")) {
////                page.navigate(Configurations.Appurl_QA_Amadeus_B2);
////            }
////            else            {
////                page.navigate(Configurations.Appurl_QA_LH_De);
////            }
////
////            page.waitForLoadState();
////            System.out.println("Lufthansa url loaded : successfully");
////            commonActions.handle_Privacy_Settings_Page();
////        }
////        catch (Exception e) {
////            e.printStackTrace();
////            LOGGER.error(e.getMessage());
////        }
//
//        //For OS Tenant
//        try {
//            System.out.println("App_type : " + tenant);
//            if (tenant.equalsIgnoreCase("swiss")) {
//                page.navigate(Configurations.Appurl_QA_SN_Fr_B2);
//            } else if (tenant.equalsIgnoreCase("os")) {
//                page.navigate(Configurations.Appurl_QA_Amadeus_B2_OS);
//            } else if (tenant.equalsIgnoreCase("amadeus")) {
//                page.navigate(Configurations.Appurl_QA_Amadeus_B2);
//            } else {
//                page.navigate(Configurations.Appurl_QA_Amadeus_B2_OS);
//            }
//
//            page.waitForLoadState();
//            System.out.println("Austrian url loaded : successfully");
//            commonActions.handle_Privacy_Settings_Page();
//        } catch (Exception e) {
//            e.printStackTrace();
//            LOGGER.error(e.getMessage());
//        }
//
//    }
//
//    @And("User chooses {string}")
//    public void user_chooses(String locator) {
//        commonActions.click(locator);
//    }
//
//    @Given("User enters {string} in login page")
//    public void user_enters_credential_in_login_page(String jsonKeyDetails) {
//        try {
//            LOGGER.info(CommonActions.getCurrentMethodName());
//            commonActions.user_Performs_Amadues_Login(jsonKeyDetails);
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
//        }
//    }
//
//    @And("User enters {string} in {string}")
//    public void user_enters_data_in_text_field(String jsonDataKey, String locator) {
//
//        try {
//            String data = commonActions.getTestData(jsonDataKey);
//            commonActions.enterText(locator, data);
//        } catch (Exception e) {
//            System.out.println("Exception in enterText : " + e.getMessage());
//        }
//    }
//
//    @And("User handles Privacy Settings Page and proceeds with booking")
//    public void User_handle_Privacy_Settings_Page() {
//        page.waitForTimeout(200);
//        try {
//            System.out.println("Privacy Handle Function Body");
//
//            page.waitForPopup(new Page.WaitForPopupOptions()
//                    .setPredicate(p -> p.context().pages().size() == 2), () ->
//            {
//                page.locator("(//button[text()='Submit'])[1]").click();
//
//            });
//            System.out.println("Submit button clicked: Successfully");
//
//            List<Page> pages = page.context().pages();
//            for (Page tabs : pages) {
//                tabs.waitForLoadState();
//                System.out.println(tabs.url());
//            }
//            Page PrivacyPage = pages.get(1);
//            privacyPage = PrivacyPage;
//
//            System.out.println("Submit button :New URL");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.waitForLoadState();
//            PrivacyPage.locator(commonActions.getLocatorStr("Privacy")).isVisible();
//
//            PrivacyPage.click(commonActions.getLocatorStr("Privacy"));
//            System.out.println("Privacy setting page handled : successfully");
//
//            PrivacyPage.waitForLoadState(LoadState.LOAD);
//
//            //Select classS
//            PrivacyPage.locator("(//span[contains(@class, 'mat-button-wrapper')])[10]").first().click();
//
//            //Select class from Preminum econumy
//            PrivacyPage.locator("//button[@id=\"selectFare-BFEUM2NC0E\"]/span[1]").first().click();
//            PrivacyPage.waitForTimeout(10000);
//
//            //Enter Passenger details
//            PrivacyPage.locator("//span[text()='Enter passenger details']").first().click();
//
//            //select First Name
//            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("Test");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("User");
//            PrivacyPage.waitForTimeout(3000);
////            PrivacyPage.pause();
//
//            PrivacyPage.locator("//input[@placeholder='Your email address']").fill("test@yopmail.com");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your country calling code']").fill("+49");
//            PrivacyPage.waitForTimeout(3000);
//
//            PrivacyPage.locator("//input[@placeholder='Your mobile phone']").fill("123456");
//            PrivacyPage.waitForTimeout(3000);
////            PrivacyPage.pause();
//
//            //Enter next passerger
//            PrivacyPage.locator("//span[text()='Next passenger']").first().click();
//            PrivacyPage.waitForTimeout(3000);
//            PrivacyPage.pause();
//
//            //Enter first name
//            PrivacyPage.locator("//input[@placeholder='Your first name']").fill("jon");
//            PrivacyPage.waitForTimeout(3000);
//
//            //Enter Last NAme
//            PrivacyPage.locator("//input[@placeholder='Your last name']").fill("dey");
//            PrivacyPage.waitForTimeout(3000);
//
//            //DOB
//            PrivacyPage.locator("//span[@class='mat-mdc-button-touch-target']").click();
//            PrivacyPage.waitForTimeout(3000);
//
//            //select year
//            PrivacyPage.locator("//span[text()=' 2021 ']").click();
//            PrivacyPage.waitForTimeout(3000);
//
//            //select month
//            PrivacyPage.locator("//span[text()=' OCT ']").click();
//            PrivacyPage.waitForTimeout(3000);
//
//            //select day
//            PrivacyPage.locator("//span[text()=' 23 ']").click();
//            PrivacyPage.waitForTimeout(3000);
//
//            //click on Confirm
//            PrivacyPage.locator("//button[contains(@class,\"mat-button\")]//span[contains(text(),'Confirm')]").first().click();
//            PrivacyPage.waitForTimeout(30000);
//            PrivacyPage.pause();
//
//
//            //Continue to Payment
//            PrivacyPage.locator("//span[contains(text(),'Continue to payment')]").first().click();
//            PrivacyPage.waitForTimeout(30000);
//            PrivacyPage.pause();
//
//            // select credit card
//            PrivacyPage.locator("//label[contains(text(),'Credit')]/..//input[@id='radio_1']").first().click();
//            PrivacyPage.waitForTimeout(10000);
//
//            // creadit card type- Visa
//            PrivacyPage.locator("//div[contains(@class,\"cardtype-select\")]//span[contains(@class,\"dropdown-icon\")]").first().click();
//            PrivacyPage.querySelector("//span[contains(text(), 'Mastercard')]").click();
//            PrivacyPage.waitForTimeout(2000);
//
//            // card number
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"number-label\"]").fill("5200000000001005");
//            PrivacyPage.waitForTimeout(2000);
//
//            // card holder name
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"name-label\"]").fill("Test User");
//            PrivacyPage.waitForTimeout(2000);
//
//            // exp month
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-month-label\"]").fill("06");
//            PrivacyPage.waitForTimeout(2000);
//
//            // exp year
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"expirydate-year-label\"]").fill("25");
//            PrivacyPage.waitForTimeout(2000);
//
//            // cvv
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"cvv-label\"]").fill("123");
//            PrivacyPage.waitForTimeout(2000);
//
//            //name and street
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"street-label\"]").fill("street name");
//            PrivacyPage.waitForTimeout(2000);
//
//            // zip name
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"zip-label\"]").fill("123456");
//            PrivacyPage.waitForTimeout(2000);
//
//            // city
//            PrivacyPage.locator("//input[contains(@class, 'mdc-text-field__input') and @aria-labelledby=\"city-label\"]").fill("Frankfurt");
//            PrivacyPage.waitForTimeout(2000);
//
//            // terms and cond
//            PrivacyPage.locator("//input[@type='checkbox' and contains(@class, 'mdc-checkbox__native-control') and @id='terms-checkbox']").first().click();
//            PrivacyPage.waitForTimeout(5000);
//
//            // pay now
//            PrivacyPage.locator("//span[contains(text(), 'Pay Now')]").first().click();
//            System.out.println("PNR created : successfully");
//            Thread.sleep(90000);
//
//            PrivacyPage.locator("//div[text()='Your booking is confirmed!']").click();
//            PrivacyPage.waitForTimeout(10000);
//            Thread.sleep(10000);
//            PrivacyPage.pause();
////       store the PNR In one Variable
//
//
//            page.bringToFront();
//        } catch (Exception e) {
//            e.getMessage();
//            LOGGER.error(e.getMessage());
//        }
//    }
//
//    @And("User scrolls up")
//    public void userScrollsUp() {
//        // Scroll up the page
//        page.evaluate("window.scrollTo(0, 0);");
//    }
//
//    @And("User opens the Amadeus Agate Picker")
//    public void user_opens_amadeus_agate_picker() {
//        try {
//            //System.out.println("App_type : " + tenant);
//            page.navigate(Configurations.Agate_home);
//            page.waitForLoadState();
//
//            System.out.println("Amadeus Agate Picker URL loaded successfully");
//            commonActions.handle_Privacy_Settings_Page();
//        } catch (Exception e) {
//            e.printStackTrace();
//            LOGGER.error(e.getMessage());
//        }
//    }
//
//
//    //User click on Submit on Agate Portal
//    @And("User clicks on submit button")
//    public void userClicksOnSubmitButton()
//    {
//        try
//        {
//            page.waitForPopup(new Page.WaitForPopupOptions()
//                    .setPredicate(p -> p.context().pages().size() == 2), () ->
//            {
//                page.waitForTimeout(3000);
//                page.locator("(//button[text()='Submit'])[1]").click();
//            });
//            System.out.println("Submit button clicked : Successfully");
//            List<Page> pages = page.context().pages();
//            for (Page tabs : pages) {
//                tabs.waitForLoadState();
//                System.out.println(tabs.url());
//            }
//            Page confirmationPage = pages.get(1);
//
//            // Click on "Change flights"
//            confirmationPage.locator("//span[contains(text(),'Change flights')]").click();
//            confirmationPage.waitForTimeout(3000);
//            //confirmationPage.pause();
//
//            confirmationPage.locator("//p[normalize-space()='LHG debug panel']").click();
//            confirmationPage.waitForTimeout(3000);
//
//            confirmationPage.locator("//label[@for='81-feature-flag-checkbox-input']//span[@class='mat-checkbox-inner-container']").click();
//            confirmationPage.waitForTimeout(3000);
//
//            confirmationPage.locator("//span[normalize-space()='Apply']").click();
//            confirmationPage.waitForTimeout(3000);
//
//            confirmationPage.locator("//p[normalize-space()='LHG debug panel']").click();
//            confirmationPage.waitForTimeout(3000);
//
//            confirmationPage.locator("//label[@for='80-feature-flag-checkbox-input']//span[@class='mat-checkbox-inner-container']").click();
//            confirmationPage.waitForTimeout(3000);
//
//            confirmationPage.locator("//span[normalize-space()='Apply']").click();
//            confirmationPage.waitForTimeout(3000);
//
//        }
//        catch(Exception e)
//        {
//            LOGGER.error("Error: " + e.getMessage());
//        }
//    }
//
//    private void saveTestResultsToXray(Scenario s) {
//
//        ZonedDateTime finishDateTime = ZonedDateTime.now();
//        testFinish = finishDateTime.truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
//        Log.info("Test Finish Time: " + testFinish);
//
//        if (s.isFailed()) {
//            Log.error("Test Failed!");
//            JunitRunner.featureTestPassed = false;
//            Xray.writeResultsForSingleTest(JunitRunner.ExecutionID, XrayIssueKey, XRAY_CONFIG.TEST_STATUS_FAIL,
//                    JunitRunner.testStart, testFinish);
//        } else {
//            if (JunitRunner.featureTestPassed == true) {
//                Log.info("Test Passed!");
//                Xray.writeResultsForSingleTest(JunitRunner.ExecutionID, XrayIssueKey, XRAY_CONFIG.TEST_STATUS_PASS,
//                        JunitRunner.testStart, testFinish);
//            }
//        }
//    }
//
//    private void checkNewTest(Scenario s) {
//        XrayIssueKey = XrayHelper.getTestIdFromFileName(s.getId());
//
//        if (!JunitRunner.currentXrayIssueKey.contains(XrayIssueKey)) {
//            System.out.println("This is a new Feature!");
//            JunitRunner.currentXrayIssueKey = XrayIssueKey;
//            JunitRunner.featureTestPassed = true;
//        }
//    }
//}
