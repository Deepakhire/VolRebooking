package com.lh.core.page;

import com.microsoft.playwright.Page;

public class LoginPage {
    Page page;

// Locator — — — -

    //span[text()='Login and Register']
    //getByRole('link', { name: 'Login and Register' })
    String username = "input[name='emailLoginStepOne']";

    String nextButton = "//button/span[@class='travelid-button__text']";
    String password = "//input[@type='password']";

    String clickLogin = "//button[@type='submit']/span[text()='Log in']";
    //String logged_in_userName = “id=react-burger-menu-btn”;
    //String clickLogout = “id=logout_sidebar_link”;

// initialize Page using constructor

    public LoginPage(Page page) {
        this.page = page;

    }
    public String verifyTitle() {
        String title = page.title();
        return title;}

//Create methods

// Login into the application

    public void loginIntoApplication(String email, String pass) {
        enterUserName(email);
        clickNextButton();
        enterPassword(pass);
        clickLoginButton();}

    public void logoutApplication() {
        clickOnHamburger();
        clickOnLogout(); } // Logout from the application

    public void enterUserName(String email) {
        page.fill(username, email);
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
