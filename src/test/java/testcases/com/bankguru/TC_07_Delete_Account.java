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

public class TC_07_Delete_Account extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    NewCustomerPageObject newCustomerPage;
    NewAccountPageObject newAccountPage;
    JsonDataHelper.Customer customer;
    DeleteAccountPageObject deleteAccountPage;


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
        deleteAccountPage = (DeleteAccountPageObject) homePage.clickRightNavBarLinkByTextName("Delete Account");

    }
    @Test
    public void TC_01_Account_ID_Cannot_Be_Empty(){
        deleteAccountPage.clickToElementByName("accountno");
        deleteAccountPage.clickToElementByName("AccSubmit");
        verifyEquals(deleteAccountPage.getErrorMsgTextByName("accountno"), "Account Number must not be blank");

    }
    @Test
    public void TC_02_Account_ID_Must_Be_Numeric(){
        deleteAccountPage.enterToElementByName("accountno", "123sdds");
        verifyEquals(deleteAccountPage.getErrorMsgTextByName("accountno"), "Characters are not allowed");
        deleteAccountPage.enterToElementByName("accountno", "askkf");
        verifyEquals(deleteAccountPage.getErrorMsgTextByName("accountno"), "Characters are not allowed");
    }
    @Test
    public void TC_03_Account_ID_Cannot_Have_Special_Characters(){
        deleteAccountPage.enterToElementByName("accountno", "^^&&*");
        verifyEquals(deleteAccountPage.getErrorMsgTextByName("accountno"), "Special characters are not allowed");
        deleteAccountPage.enterToElementByName("accountno", "124@$%");
        verifyEquals(deleteAccountPage.getErrorMsgTextByName("accountno"), "Special characters are not allowed");
    }
    @Test
    public void TC_04_Account_ID_Cannot_Have_Blank_Space(){
        deleteAccountPage.enterToElementByName("accountno", "12 334");
        verifyEquals(deleteAccountPage.getErrorMsgTextByName("accountno"), "Characters are not allowed");
    }

    @AfterClass
    public void afterClass(){
        closeBrowserAndDriver();
    }
}
