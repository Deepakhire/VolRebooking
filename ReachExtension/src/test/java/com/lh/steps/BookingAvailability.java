//package com.lh.steps;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.time.ZonedDateTime;
//import java.time.format.DateTimeFormatter;
//import java.time.temporal.ChronoUnit;
//
//import com.microsoft.playwright.Page;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.log4j.Logger;
//import org.json.simple.parser.ParseException;
//import org.openqa.selenium.ElementClickInterceptedException;
//import org.openqa.selenium.ElementNotInteractableException;
//import org.openqa.selenium.StaleElementReferenceException;
//import org.xml.sax.SAXException;
//
//import com.genz.xray.XRAY_CONFIG;
//import com.genz.xray.Xray;
//import com.lh.library.CommonActions;
//import com.lh.runner.JunitRunner;
//import com.lh.utilities.Configurations;
//import com.lh.xray.Log;
//import com.lh.xray.XrayHelper;
//
//import cucumber.api.Scenario;
//import cucumber.api.java.After;
//import cucumber.api.java.Before;
//import cucumber.api.java.en.And;
//import cucumber.api.java.en.Given;
//import cucumber.api.java.en.Then;
//import cucumber.api.java.en.When;
//
//public class BookingAvailability {
//
//    CommonActions commonActions;
//    private Scenario scenario;
//    Page page;
//    // String testStart = "";
//    String testFinish = "";
//    String XrayIssueKey = "";
//
//    public BookingAvailability(CommonActions con) {
//        this.commonActions = con;
//    }
//
//    private final static Logger LOGGER = Logger.getLogger(GenericSteps.class);
//
//    //	/**
////	 *
////	 * @param s
////	 * @throws Exception
////	 *             Description Initialization before starting of each scenario
////	 *             execution
////	 */
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
//
////            checkNewTest(s);
//    }
//
//
//    /**
//     * Description Closing the resources after execution of each scenario
//     *
//     * @throws IOException
//     */
//    @After
//    public void after(Scenario s) {
//
////        context.close();
////        browser.close();
////        page.close();
//        commonActions.quit();
//
//        //saveTestResultsToXray(s);
//
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
//    @Given("User enters {string} in LH login page")
//    public void user_enters_credential_in_LH_login_page(String jsonKeyDetails) {
//        try {
//            LOGGER.info(CommonActions.getCurrentMethodName());
//            commonActions.user_Performs_Amadues_Login(jsonKeyDetails);
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
//        }
//    }
//
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
//
//
//
//}