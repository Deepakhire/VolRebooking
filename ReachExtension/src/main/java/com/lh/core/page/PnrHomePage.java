package com.lh.core.page;

import com.microsoft.playwright.Page;

public class PnrHomePage {
    private Page page;

    // Locator — — — -
    //private String Privacy_setting_accept = "button[@id='cm-acceptAll']";
    private String Privacy_setting_accept = "//div[@class='cc-row-buttons']/button[@class='button-primary']";
    // private String home_page_title = "div[@class='header']//h1[text()='Lufthansa']";
    private String home_page_title = "#header > div.header > h1";
    //css "button#cm-acceptAll.button-primary"
    private String login_icon_home_Page = "//div[@class='one-id-login']";
    private String logged_in_userName = "//span[@id='lh-loginModule-name']";
    private String profile_btn = "//a[@class='link-small btn btn-primary btn-sm mb-0']";



}
