package com.lh.utilities;

public class Configurations
{
	// DriverPaths and Run on browser parameter
	public static final String ExecutionEnvnmt = "QA"; // Valid values: QA, Stage, PROD
	public static final String BrowserName = "Chrome";

	// Application URL
	public final static String AppurlLoad = "https://www.lufthansa.com/de/en/homepage";
	public final static String Appurl_QA_LH_De = "https://qa-www.lufthansa.com/de/en/homepage";
	public final static String Appurl_QA_SN_De_B1 = "https://qa-www.swiss.com/de/en/homepage";
	public final static String Appurl_QA_SN_Fr_B2 = "https://qa-www.swiss.com/fr/en/homepage";

//	public final static String Appurl_QA_Amadeus_B2 = "https://pdt.accounts.amadeus.com/LoginService/authorizeAngular?service=BCM&response_type=code&scope=&nonce=bdf90063-db3c-47ca-925f-9f5a9ffaa9a2&client_id=LQI4MMU2lWMLyBh6&redirect_uri=https%3A%2F%2Ftest.ui-api.business-configuration-manager.amadeus.com%2Fbcm%2Frs%2Fauth%2Fv1%2Fagate%2Flogin%3Fredirect-url%3Dhttps%3A%2F%2Fuat.digital.airline.amadeus.com%2F1a%2Fagate%2Fpicker#/login";
	public final static String Appurl_QA_Amadeus_B2 = "https://uat.digital.airline.amadeus.com/1a/agate/shooter/configLHEmbedded::LH";

	public final static String Appurl_QA_OS_Fr_B2 = "https://qa-www.austrian.com/de/en/homepage";

	public final static String Appurl_QA_Amadeus_B2_OS = "https://uat.digital.airline.amadeus.com/1a/agate/shooter/configOSEmbedded::OS";

	public final static String AppurlEnv1 = "";
	public final static String AppurlEnv2 = "";
	// Test Data source path
	public final static String testDataResourcePath = "../src/test/java/com/TestData/";

	// Browser Stack configuration
	public static final String RunOnBrowserStack = "N";
	public static final String USERNAME = "";

	public static final String AUTOMATE_KEY = "5tW8jrFVdPxbpgUSvssc";

	public static final String URL_BS = "https://" + USERNAME + ":" + AUTOMATE_KEY
			+ "@hub-cloud.browserstack.com/wd/hub";
	public static boolean cloud = false;

	// Output Reports path
	public static final String reportPath = "./Reports/";

	// download file path
	public static String downloadPath = System.getProperty("user.dir") + "\\Downloads\\";

	// Take screenshots on run parameter settings.
	public static final String takeScreenshots = "Y";

	public final static String XrayConfigPath = "./src/test/java/com/lh/xray/xray_config.properties";

	public static final String Agate_home = "https://uat.digital.airline.amadeus.com/1a/agate/picker";
}
