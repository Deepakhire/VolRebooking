package com.lh.core.page;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class HomePage {
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


//initialize Page using constructor

    public HomePage(Page page) {
        this.page = page;
        if(page!=null){
            System.out.println("Page initialized : successfully");
        }
    }

//Method

    public String getProductName() {

        String productName = page.textContent(home_page_title);
        System.out.println("Page title : " + productName);

        return productName;
    }

    public void handle_Privacy_Settings_Page()
    {
        try{
            //page.locator(Privacy_setting_accept).click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Agree").setExact(true)).click();
            System.out.println("Privacy setting page handled : successfully");
        }catch(Exception e)
        {
            e.getMessage();
        }

    }
    public void clickOnLoginOption(){
        page.locator(login_icon_home_Page).click();
    }
    public void clickOnLoggedInUserName(){
        page.locator(logged_in_userName).click();
    }
    public void clickOnProfileOption(){
        page.locator(profile_btn).click();
    }
    public void clickOn(String locator){
        if (locator.equalsIgnoreCase("logged_in_userName")){
        clickOnLoggedInUserName();
        }else if(locator.equalsIgnoreCase("profile_btn")){
            clickOnProfileOption();
        }
    }
}
