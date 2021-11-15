package testcases.com.bankguru;

import actions.common.BaseTest;
import globalConstants.GlobalConstant;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;
import utilities.jsonDataHelper.JsonDataHelper;

public class TC_08_Mini_Statement extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    JsonDataHelper.Customer customer;
    MiniStatementPageObject miniStatementPage;


    @Parameters({"browserName"})
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getDriver(browserName, GlobalConstant.homePageUrl);
        homePage = PageGeneratorManager.getHomePage(driver);
        JsonDataHelper.Manager manager = JsonDataHelper.readAccessData(GlobalConstant.mainResourcePath + "accessData.json");
        customer = JsonDataHelper.readOneRandomData(GlobalConstant.mainResourcePath + "customerDatas.json");
        homePage.enterToElementByName("uid", manager.getUserId());
        homePage.enterToElementByName("password", manager.getUserPassword());
        homePage.clickToElementByName("btnLogin");
        verifyEquals(homePage.getWelcomeText(), "Welcome To Manager's Page of Guru99 Bank");
        miniStatementPage = (MiniStatementPageObject) homePage.clickRightNavBarLinkByTextName("Mini Statement");

    }

    @Test
    public void TC_01_Account_ID_Cannot_Be_Empty() {
        miniStatementPage.clickToElementByName("accountno");
        miniStatementPage.clickToElementByName("AccSubmit");
        verifyEquals(miniStatementPage.getErrorMsgTextByName("accountno"), "Account Number must not be blank");
    }

    @Test
    public void TC_02_Account_ID_Must_Be_Numeric() {
        miniStatementPage.enterToElementByName("accountno", "123sdds");
        verifyEquals(miniStatementPage.getErrorMsgTextByName("accountno"), "Characters are not allowed");
        miniStatementPage.enterToElementByName("accountno", "askkf");
        verifyEquals(miniStatementPage.getErrorMsgTextByName("accountno"), "Characters are not allowed");
    }

    @Test
    public void TC_03_Account_ID_Cannot_Have_Special_Characters() {
        miniStatementPage.enterToElementByName("accountno", "^^&&*");
        verifyEquals(miniStatementPage.getErrorMsgTextByName("accountno"), "Special characters are not allowed");
        miniStatementPage.enterToElementByName("accountno", "124@$%");
        verifyEquals(miniStatementPage.getErrorMsgTextByName("accountno"), "Special characters are not allowed");
    }

    @Test
    public void TC_04_Account_ID_Cannot_Have_Blank_Space() {
        miniStatementPage.enterToElementByName("accountno", "12 334");
        verifyEquals(miniStatementPage.getErrorMsgTextByName("accountno"), "Characters are not allowed");
    }

    @AfterClass
    public void afterClass() {
        closeBrowserAndDriver();
    }
}
