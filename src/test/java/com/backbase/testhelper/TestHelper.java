package com.backbase.testhelper;

import com.backbase.pom.AddComputerPage;
import com.backbase.pom.EditComputerPage;
import com.backbase.pom.HomePage;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

public class TestHelper {

    private static String startDate;
    private static String endDate;

    public static void navigateToAddComputer() {
        HomePage.ADD_NEW_COMPUTER_BUTTON.click();
    }

    public static String addComputer(String name) {
        AddComputerPage.COMPUTER_NAME.sendKeys(name);
        AddComputerPage.CREATE_COMPUTER_BUTTON.click();
        return getAddMessage(name);
    }

    public static String addComputer(String name, String introducedDate) {
        AddComputerPage.COMPUTER_NAME.sendKeys(name);
        AddComputerPage.INTRODUCED_DATE.sendKeys(introducedDate);
        AddComputerPage.CREATE_COMPUTER_BUTTON.click();
        return getAddMessage(name);
    }

    public static String addComputer(String name, String introducedDate, String discontinuedDate) {
        AddComputerPage.COMPUTER_NAME.sendKeys(name);
        AddComputerPage.INTRODUCED_DATE.sendKeys(introducedDate);
        AddComputerPage.DISCONTINUED_DATE.sendKeys(discontinuedDate);
        AddComputerPage.CREATE_COMPUTER_BUTTON.click();
        return getAddMessage(name);
    }

    public static String getAddMessage(String name) {
        return ("Done! Computer " + name + " has been created");
    }

    public static String getDeleteMessage() {
        return ("Done! Computer has been deleted");
    }

    public static String getUpdateMessage(String name) {
        return ("Done! Computer " + name + " has been updated");
    }

    public static String generateString() {
        String generatedString = RandomStringUtils.randomAlphanumeric(10);
        return generatedString;
    }

    public static String generateStartDate() {
        startDate = LocalDate.now().minus(Period.ofDays((new Random().nextInt(365 * 50)))).toString();
        return startDate;
    }

    public static String generateEndDate() {
        endDate = LocalDate.now().minus(Period.ofDays((new Random().nextInt(365 * 5)))).toString();
        int compareValue = endDate.compareTo(startDate);
        if (compareValue < 0) {
            generateEndDate();
        }
        return endDate;
    }

    public static String generateNumber() {
        String generatedString = RandomStringUtils.randomNumeric(10);
        return generatedString;
    }

    public static String searchComputer(String name) {
        HomePage.SEARCH_TEXTBOX.clear();
        HomePage.SEARCH_TEXTBOX.sendKeys(name);
        HomePage.SEARCH_SUBMIT_BUTTON.click();
        return HomePage.PAGE_HEADER.getText();
    }

    public static String deleteComputerData(String name) {
        HomePage.SEARCH_TEXTBOX.sendKeys(name);
        HomePage.SEARCH_SUBMIT_BUTTON.click();
        HomePage.TABLE_DATA.get(0).click();
        EditComputerPage.DELETE_COMPUTER_BUTTON.click();
        return getDeleteMessage();
    }

    public static void snapScreenShot(String result, String name, WebDriver driver) throws IOException {
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String fileName = System.getProperties().get("user.dir") + "/screenshots";
        File directory = new File(fileName);
        if (!directory.exists()) {
            directory.mkdir();
        }
        fileName = fileName + "/" + name + "_" + result + ".jpg";
        File destinationFile = new File(fileName);
        FileHandler.copy(sourceFile, destinationFile);
    }
}
