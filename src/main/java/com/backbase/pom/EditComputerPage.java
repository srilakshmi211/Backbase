package com.backbase.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditComputerPage extends AddComputerPage {

    @FindBy(xpath = "//*[@id=\"main\"]/form[2]/input")
    public static WebElement DELETE_COMPUTER_BUTTON;

    public static boolean isPageDisplayed() {
        return (PAGE_HEADER.getText().equals("Edit computer"));
    }

}
