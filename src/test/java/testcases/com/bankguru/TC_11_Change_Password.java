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

public class TC_11_Change_Password extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    NewCustomerPageObject newCustomerPage;
    NewAccountPageObject newAccountPage;
    JsonDataHelper.Customer customer;
    ChangePasswordPageObject changePasswordPage;

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
        changePasswordPage = (ChangePasswordPageObject) homePage.clickRightNavBarLinkByTextName("Change Password");
    }

    @Test
    public void TC_01_Old_Password_Cannot_Be_Empty(){
        changePasswordPage.enterToElementByName("oldpassword", "");
        changePasswordPage.clickToElementByName("newpassword");
        verifyEquals(changePasswordPage.getErrorMsgTextByName("oldpassword"), "Old Password must not be blank");
    }

    @Test
    public void TC_02_New_Password_Cannot_Be_Empty(){
        changePasswordPage.enterToElementByName("newpassword", "");
        changePasswordPage.clickToElementByName("confirmpassword");
        verifyEquals(changePasswordPage.getErrorMsgTextByName("newpassword"), "New Password must not be blank");
    }

    @Test
    public void TC_03_New_Password_Must_Have_One_Numeric_Value(){
        changePasswordPage.enterToElementByName("newpassword", "acv");
        verifyEquals(changePasswordPage.getErrorMsgTextByName("newpassword"), "Enter at-least one numeric value");
    }

    @Test
    public void TC_04_New_Password_Must_Have_One_Special_Value(){
        changePasswordPage.enterToElementByName("newpassword", "acv12");
        verifyEquals(changePasswordPage.getErrorMsgTextByName("newpassword"), "Enter at-least one special character");
    }

    @Test
    public void TC_05_New_Password_Cannot_Have_String_Password_Or_password(){
        changePasswordPage.enterToElementByName("newpassword", "password1!");
        verifyEquals(changePasswordPage.getErrorMsgTextByName("newpassword"), "Choose a difficult Password");

        changePasswordPage.enterToElementByName("newpassword", "Password1!");
        verifyEquals(changePasswordPage.getErrorMsgTextByName("newpassword"), "Choose a difficult Password");
    }
    @Test
    public void TC_06_New_Password_Match_Confirm_Password(){
        changePasswordPage.enterToElementByName("newpassword", "abc123!@#");
        changePasswordPage.enterToElementByName("confirmpassword", "abc123#@!");
        verifyEquals(changePasswordPage.getErrorMsgTextByName("confirmpassword"), "Passwords do not Match");
    }

    @AfterClass
    public void afterClass(){
        closeBrowserAndDriver();
    }
}
