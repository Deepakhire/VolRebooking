package com.lh.core.page;

import com.lh.core.library.CommonActions;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import java.lang.reflect.InvocationTargetException;

public class PaymentHubPage {
    private Page page;
    //private String Add_new = "//span[text()='Add new']";
     private String Add_new = "#add-new-button";
    //private String Back = "//button[@id='back-button']";
    private String Back = "#back-button > div";
    private String saved_Payment_methods_title = "//h1[text()='Saved payment methods']";
    private String saved_payment_method = "#radio_1";

    CommonActions comm;

    public PaymentHubPage(Page page) {

        this.page = page;
        comm = new CommonActions(page);
    }

    public void clickOn(String locator){
        if (locator.equalsIgnoreCase("Add_new")){
            //clickOnAddNewBtn();
            clickOnAddNewBtn(locator);
        }else if(locator.equalsIgnoreCase("Back")){
            clickOnElement(locator);
        }else if(locator.equalsIgnoreCase("saved_payment_method")){
            clickOnElement(locator);
        }
    }
    public void clickOnAddNewBtn(String locator){
        page.waitForLoadState();

        //page.isVisible(saved_Payment_methods);
        page.waitForSelector(saved_Payment_methods_title, new Page.WaitForSelectorOptions().setTimeout(10000));
        if(page.isVisible(saved_Payment_methods_title)){
            scrollDownToElementView(locator);
            page.locator(locator).click();
        }else{
            System.out.println("Payment Hub Saved Payment method Page not loaded completely");
        }
    }
    public void clickOnBackBtn(){
        page.locator(Back).click();
        System.out.println("Navigate back to Profile Page : successfully");
    }
    public void clickOnElement(String locator){
        try {

            if(page.locator(locator).isHidden())
            {
                scrollDownFromCurrentPosition();
                page.locator(locator).click();

            }else{
                page.locator(locator).click();
            }
            System.out.println("Navigate back to Profile Page : successfully");

        } catch (RuntimeException e) {
           e.getCause().printStackTrace();
        }
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
    public void scrollDownToElementView(String locator) {
//        page.waitForSelector(locator);
//        ElementHandle element = page.querySelector(locator);
//        if (element == null) {
//            throw new RuntimeException("Element not found");
//        }
//        element.scrollIntoViewIfNeeded();
       // page.evaluate("window.scrollTo(0, 10000)");
        page.evaluate("window.scrollTo(0, document.body.scrollHeight);");

        // Find element below viewport
        page.waitForSelector(locator);
        ElementHandle element = page.querySelector(locator);
//        Locator element = page.locator(locator);
//        element.click();


// Use Playwright's evaluate method to click the element using JavaScript
        page.evaluate("arguments[0].click()", element);

    }
}
