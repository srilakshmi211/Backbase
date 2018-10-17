package com.backbase.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage {
    public static final String URL = "http://computer-database.herokuapp.com/computers";

    @FindBy(id = "add")
    public static WebElement ADD_NEW_COMPUTER_BUTTON;

    @FindBy(id = "searchbox")
    public static WebElement SEARCH_TEXTBOX;

    @FindBy(id = "searchsubmit")
    public static WebElement SEARCH_SUBMIT_BUTTON;

    @FindBy(linkText = "Next →")
    public static WebElement NEXTPAGE_LINK;

    @FindBy(linkText = "← Previous")
    public static WebElement PREVIOUSPAGE_LINK;

    @FindBy(linkText = "Computer name")
    public static WebElement COMPUTER_NAME;

    @FindBy(linkText = "Introduced")
    public static WebElement INTRODUCED_DATE;

    @FindBy(linkText = "Discontinued")
    public static WebElement DISCONTINUED_DATE;

    @FindBy(linkText = "Company")
    public static WebElement COMPANY_NAME;

    @FindBy(xpath = "//*[@id=\"main\"]/h1")
    public static WebElement NO_OF_COMPUTERS_FOUND_MESSAGE;

    @FindBy(xpath = "//*[@id=\"main\"]/div[1]")
    public static WebElement RESULT_MESSAGE;

    @FindBy(xpath = "//*[@id=\"main\"]/table/tbody/tr/td[1]/a")
    public static List<WebElement> TABLE_DATA;

    @FindBy(xpath = "//*[@id=\"main\"]/h1")
    public static WebElement PAGE_HEADER;

    @FindBy(xpath = "/html/body/header/h1/a")
    public static WebElement HOME_PAGE_LINK;

    @FindBy(xpath = "//*[@id=\"pagination\"]/ul/li[2]/a")
    public static WebElement SEARCH_DISPLAY_NUMBER;

    public static boolean isPageDisplayed(WebDriver driver) {
        return (driver.getCurrentUrl().equals(URL));
    }
}
