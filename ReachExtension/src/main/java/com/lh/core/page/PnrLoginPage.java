package com.lh.core.page;

import com.lh.core.library.CommonActions;
import com.microsoft.playwright.Page;

public class PnrLoginPage {

    Page page;

// Locator — — — -

    //span[text()='Login and Register']
    //getByRole('link', { name: 'Login and Register' })
    String username = "input[name='emailLoginStepOne']";
    String organiser ="//input[@id='organizationInput']";

    String nextButton = "//button[@id='id_confirmButton']";
    String password = "//input[@id='passwordInput']";

    //String next_btn_login="//button[@id='nextButton']"

   String clickLogin = "//button[@id='nextButton']";
    //String logged_in_userName = “id=react-burger-menu-btn”;
    //String clickLogout = “id=logout_sidebar_link”;

// initialize Page using constructor

    public PnrLoginPage(Page page) {
        this.page = page;

    }
    public String verifyTitle() {
        String title = page.title();
        return title;}

//Create methods

// Login into the application

    public void loginIntoApplication(String email, String org, String pass) {
        enterUserName(email);
        enterOrganization(org);
        clickNextButton();
        enterPassword(pass);
        clickLoginButton();
    }

    public void logoutApplication() {
        clickOnHamburger();
        clickOnLogout(); } // Logout from the application

    public void enterUserName(String email) {
        page.fill(username, email);
    }
    public void enterOrganization(String org){
        page.fill(organiser,org);
    }
    public void clickNextButton() {
        page.click(nextButton);}

    public void enterPassword(String pass) {
        page.fill(password, pass);}

    public void clickLoginButton() {
        page.click(clickLogin);}

    public void clickOnHamburger() {
        //page.click(clickHamburger);

    }
    public void clickOnLogout() {
        //page.click(clickLogout);}
    }
}


