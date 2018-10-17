package com.backbase.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddComputerPage {
    @FindBy(id = "name")
    public static WebElement COMPUTER_NAME;

    @FindBy(id = "introduced")
    public static WebElement INTRODUCED_DATE;

    @FindBy(id = "discontinued")
    public static WebElement DISCONTINUED_DATE;

    @FindBy(id = "company")
    public static WebElement COMPANY_NAME;

    @FindBy(xpath = "//*[@id=\"main\"]/form/div/input")

    public static WebElement CREATE_COMPUTER_BUTTON;

    @FindBy(linkText = "Cancel")
    public static WebElement CANCEL_BUTTON;

    @FindBy(xpath = "//*[@id=\"main\"]/form/fieldset/div[1]/div/span")
    public static WebElement ERROR_MESSAGE;

    @FindBy(xpath = "//*[@id=\"main\"]/h1")
    public static WebElement PAGE_HEADER;

    //*[@id="main"]/form/fieldset/div[1]/div/span

    @FindBy(xpath = "//*[@id=\"main\"]/form/fieldset/div[1]/div/span")
    public static WebElement REQUIRED_TEXT;

    public static boolean isPageDisplayed() {
        return (PAGE_HEADER.getText().equals("Add a computer"));
    }
}


