package com.backbase.test;

import com.backbase.pom.AddComputerPage;
import com.backbase.pom.EditComputerPage;
import com.backbase.pom.HomePage;
import com.backbase.testhelper.TestHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.AssertJUnit.assertTrue;

public class RegressionTests {
    static WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/chromedriver.exe");
        driver.get(HomePage.URL);
        driver.manage().window().fullscreen();
        PageFactory.initElements(driver, HomePage.class);
        PageFactory.initElements(driver, AddComputerPage.class);
        PageFactory.initElements(driver, EditComputerPage.class);
    }

    @AfterMethod
    public void afterMethod(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            TestHelper.snapScreenShot("FAIL", testResult.getName(), driver);
        } else if (testResult.getStatus() == ITestResult.SUCCESS) {
            TestHelper.snapScreenShot("PASS", testResult.getName(), driver);
        }
        HomePage.HOME_PAGE_LINK.click();
    }

    @Test
    public void verifyAppLaunch() {
        assertTrue(HomePage.isPageDisplayed(driver));
    }

    @Test
    public void verifyAddComputer() {
        TestHelper.navigateToAddComputer();
        assertTrue(AddComputerPage.isPageDisplayed());
        assertTrue(TestHelper.addComputer(TestHelper.generateString(), TestHelper.generateStartDate(), TestHelper.generateEndDate())
                .equals(HomePage.RESULT_MESSAGE.getText()));
    }

    @Test
    public void verifyAddComputerWithAllFields() {
        TestHelper.navigateToAddComputer();
        Select COMPANY = new Select(AddComputerPage.COMPANY_NAME);
        COMPANY.selectByVisibleText("ASUS");
        assertTrue(TestHelper.addComputer(TestHelper.generateString(), TestHelper.generateStartDate(), TestHelper.generateEndDate())
                .equals(HomePage.RESULT_MESSAGE.getText()));
    }

    @Test
    public void verifySearchForAddedComputer() {
        String name = TestHelper.generateString();
        TestHelper.navigateToAddComputer();
        TestHelper.addComputer(name, TestHelper.generateStartDate(), TestHelper.generateEndDate());
        assertTrue("One computer found".equals(TestHelper.searchComputer(name)));
    }

    @Test
    public void verifyEditingComputer() {
        String name = TestHelper.generateString();
        TestHelper.navigateToAddComputer();
        TestHelper.addComputer(name, TestHelper.generateStartDate(), TestHelper.generateEndDate());
        TestHelper.searchComputer(name);
        HomePage.TABLE_DATA.get(0).click();
        assertTrue(EditComputerPage.isPageDisplayed());
        EditComputerPage.DISCONTINUED_DATE.clear();
        EditComputerPage.DISCONTINUED_DATE.sendKeys(TestHelper.generateEndDate());
        EditComputerPage.CREATE_COMPUTER_BUTTON.click();
        assertTrue(TestHelper.getUpdateMessage(name).equals(HomePage.RESULT_MESSAGE.getText()));
    }

    @Test
    public void verifyEditingComputerByDeletingName() {
        String name = TestHelper.generateString();
        TestHelper.navigateToAddComputer();
        TestHelper.addComputer(name, TestHelper.generateStartDate(), TestHelper.generateEndDate());
        TestHelper.searchComputer(name);
        HomePage.TABLE_DATA.get(0).click();
        assertTrue(EditComputerPage.isPageDisplayed());
        EditComputerPage.COMPUTER_NAME.clear();
        EditComputerPage.CREATE_COMPUTER_BUTTON.click();
        assertTrue(AddComputerPage.REQUIRED_TEXT.isDisplayed());
    }

    @Test
    public void verifyDeletingComputer() {
        String name = TestHelper.generateString();
        TestHelper.navigateToAddComputer();
        TestHelper.addComputer(name, TestHelper.generateStartDate(), TestHelper.generateEndDate());
        assertTrue(TestHelper.deleteComputerData(name).equals(HomePage.RESULT_MESSAGE.getText()));
    }

    @Test
    public void verifySearchListNotCaseSensitive() {
        String name = TestHelper.generateString();
        TestHelper.navigateToAddComputer();
        TestHelper.addComputer(name.toLowerCase());
        TestHelper.navigateToAddComputer();
        TestHelper.addComputer(name.toUpperCase());
        assertTrue(TestHelper.searchComputer(name.toLowerCase()).equals(TestHelper.searchComputer(name.toUpperCase())));
    }

    @Test
    public void verifyBigSearchResultsNavigation() {
        String name = TestHelper.generateString();
        for (int i = 1; i <= 25; i++) {
            TestHelper.navigateToAddComputer();
            TestHelper.addComputer(name + "" + i);
        }
        assertTrue("25 computers found".equals(TestHelper.searchComputer(name)));
        int expectedNumberOfPages = 3;
        TestHelper.searchComputer(name);
        for (int i = 1; i <= expectedNumberOfPages; i++) {
            int start = 1 + 10 * (i - 1);
            int end = i * 10;
            if (i == expectedNumberOfPages) {
                end = 25;
            }
            int remaining = 25 - (10 * (i - 1));
            String expectedText = "Displaying " + start + " to " + end + " of " + remaining;
            assertTrue(expectedText.equals(HomePage.SEARCH_DISPLAY_NUMBER.getText()));
            HomePage.NEXTPAGE_LINK.click();
        }
    }

    @Test
    public void verifySearchWithNumber() {
        String name = TestHelper.generateString();
        String number = TestHelper.generateNumber();
        TestHelper.navigateToAddComputer();
        TestHelper.addComputer(name.toLowerCase() + number);
        TestHelper.navigateToAddComputer();
        TestHelper.addComputer(name.toUpperCase() + number);
        assertTrue("2 computers found".equals(TestHelper.searchComputer(number)));
    }

    @Test
    public void verifyAddWithEmptyName() {
        TestHelper.navigateToAddComputer();
        AddComputerPage.CREATE_COMPUTER_BUTTON.click();
        assertTrue(AddComputerPage.REQUIRED_TEXT.isDisplayed());
    }

    @AfterSuite
    public void tearDown() {
        driver.close();
    }

}
