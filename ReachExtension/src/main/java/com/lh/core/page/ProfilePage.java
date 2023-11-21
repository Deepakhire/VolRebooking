package com.lh.core.page;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;

import com.lh.core.library.CommonActions;

public class ProfilePage {
    private Page page;
    private String profile_btn = "//a[@class='link-small btn btn-primary btn-sm mb-0']";
    private String profile_page = "//nav[@class='local']//span";
    private String collapse_arrow = "//span[text()='Saved payment methods']/following-sibling:: i[@class='icon expandable-card-icon lh mb-0 ml-auto lh lh-arrow-collapse']";
    private String expand_arrow = "//span[text()='Saved payment methods']/following-sibling:: i[@class='icon expandable-card-icon lh mb-0 ml-auto lh lh-arrow-expand']";
    private String payment_method_block = "//*[@id='dcep-expandable42-section']/div/div/h4";
    //private String Add_or_edit_payment_method = "//*[@id='dcep-expandable29-section']/div/div//a[@class='mb-0 payment-hub-link']";
    private String Add_or_edit_payment_method = "//a[text()='Add or edit payment method']";
            //*[@id="dcep-expandable29-section"]/div/div/a
    //#dcep-expandable29-section > div > div > a
            CommonActions comm;


//initialize Page using constructor

    public ProfilePage(Page page) {

        this.page = page;
        comm = new CommonActions(page);
    }
    //Method
    public void verifyPresence(String locator){
        if (locator.equalsIgnoreCase("profile_page")){
            verifyPresenceOfElement(locator);
        }else if(locator.equalsIgnoreCase("payment_method_block")){
            verifyPresenceOfElement(locator);
        }
    }
    public void verifyAbsence(String locator){
        if (locator.equalsIgnoreCase("payment_method_block")){
            verifyAbsenceOfElement(locator);
        }else if(locator.equalsIgnoreCase("")){
            verifyAbsenceOfElement(locator);
        }
    }
    public void verifyPresenceOfElement(String locator){
        //page.isVisible(profile_page);
        if(page.isVisible(locator)){
            System.out.println("Element is visible on page : "+ page.isVisible(locator));
        }else{
            System.out.println("Element is not visible on page : "+ page.isVisible(locator));
        }
    }
    public void verifyAbsenceOfElement(String locator){
        if(page.isHidden(locator)){
            System.out.println("Element is hidden : "+ locator);
        }else{
            System.out.println("Element is not hidden : "+ locator);
        }
    }
    public void clickOn(String locator){
        if (locator.equalsIgnoreCase("collapse_arrow")){
            clickOnCollapseArrow();
        }else if(locator.equalsIgnoreCase("expand_arrow")){
            clickOnExpandArrow();
        }else if(locator.equalsIgnoreCase("Add_or_edit_payment_method")){
            clicksOnElement(locator);
        }
    }
    public void clickOnCollapseArrow(){
        page.locator(collapse_arrow).click();
    }
    public void clickOnExpandArrow(){
        page.locator(expand_arrow).click();
    }
    public void clicksOnElement(String locator){
        //page.isVisible(profile_page);
        if(page.locator(locator).isHidden())
        {
            scrollDownFromCurrentPosition();
            page.locator(Add_or_edit_payment_method).click();
        }else{
            page.locator(locator).click();
        }

    }
    public void scrollDownTillElementVisible(String locator)
    {
        page.waitForSelector(locator);
        ElementHandle element = page.querySelector(locator);
        if (element == null) {
            throw new RuntimeException("Element not found");
        }

        element.scrollIntoViewIfNeeded();
    }
    public void scrollDownFromCurrentPosition()
    {
//         JsonObject result = (JsonObject) page.evaluateHandle("({x: window.scrollX, y: window.scrollY})");
//
//        // Extract the x and y values as a JsonObject
//        //JsonObject json = (JsonObject) result.jsonValue();
//
//        // Get the integer value of x and y
//        int x = json.get("x").getAsInt();
//        int y = json.get("y").getAsInt();
//        System.out.println("Current window coordinates: (" + x + ", " + y + ")");
//        page.evaluate("window.scrollTo(0,y+250)");
        page.evaluate("window.scrollBy(0, 500)");

    }
}
