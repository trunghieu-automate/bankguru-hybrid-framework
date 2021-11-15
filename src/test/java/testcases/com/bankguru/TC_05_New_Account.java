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

public class TC_05_New_Account extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    NewAccountPageObject newAccountPage;
    NewCustomerPageObject newCustomerPage;
    JsonDataHelper.Customer customer;

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
        newCustomerPage = (NewCustomerPageObject) homePage.clickRightNavBarLinkByTextName("New Customer");
        newCustomerPage.enterToElementByName("name", customer.getName());
        newCustomerPage.enterToElementByName("addr", customer.getAddr());
        newCustomerPage.enterToElementByName("emailid", customer.getEmailid());
        newCustomerPage.enterToElementByName("state", customer.getState());
        newCustomerPage.enterToElementByName("city", customer.getCity());
        newCustomerPage.enterToElementByName("telephoneno", customer.getTelephoneno());
        newCustomerPage.enterToElementByName("password", customer.getPassword());
        newCustomerPage.enterToElementByName("pinno", customer.getPinno());
        newCustomerPage.checkGenderCheckbox("rad1", customer.getGender());
        newCustomerPage.enterToElementByName("dob", customer.getDob());
        newCustomerPage.clickToElementByName("sub");
        verifyEquals(newCustomerPage.getHeadingTableText(),"Customer Registered Successfully!!!");
        newCustomerPage.updateNewCustomerInfoToFile(customer, GlobalConstant.mainResourcePath + "currentCustomerData.json", "Customer ID");
        newAccountPage = (NewAccountPageObject) newCustomerPage.clickRightNavBarLinkByTextName("New Account");

    }
    @Test
    public void TC_01_Customer_ID_Cannot_Be_Empty(){
        newAccountPage.clickToElementByName("cusid");
        newAccountPage.clickToElementByName("button2");
        verifyEquals(newAccountPage.getErrorMsgTextByName("cusid"), "Customer ID is required");

    }
    @Test
    public void TC_02_Customer_ID_Must_Be_Numeric(){
        newAccountPage.enterToElementByName("cusid", "123sdds");
        verifyEquals(newAccountPage.getErrorMsgTextByName("cusid"), "Characters are not allowed");
        newAccountPage.enterToElementByName("cusid", "askkf");
        verifyEquals(newAccountPage.getErrorMsgTextByName("cusid"), "Characters are not allowed");
    }
    @Test
    public void TC_03_Customer_ID_Cannot_Have_Special_Characters(){
        newAccountPage.enterToElementByName("cusid", "^^&&*");
        verifyEquals(newAccountPage.getErrorMsgTextByName("cusid"), "Special characters are not allowed");
        newAccountPage.enterToElementByName("cusid", "124@$%");
        verifyEquals(newAccountPage.getErrorMsgTextByName("cusid"), "Special characters are not allowed");
    }
    @Test
    public void TC_04_Customer_ID_Cannot_Have_Blank_Space(){
        newAccountPage.enterToElementByName("cusid", "12 334");
        verifyEquals(newAccountPage.getErrorMsgTextByName("cusid"), "Characters are not allowed");
    }
    @Test
    public void TC_05_Customer_ID_First_Char_Cannot_Be_Space(){
        newAccountPage.enterToElementByName("cusid", " 12334");
        verifyEquals(newAccountPage.getErrorMsgTextByName("cusid"), "First character can not have space");
    }

    // Initial_Deposit
    @Test
    public void TC_06_Initial_Deposit_Cannot_Be_Empty(){
        newAccountPage.enterToElementByName("inideposit", "");
        newAccountPage.clickToElementByName("cusid");
        verifyEquals(newAccountPage.getErrorMsgTextByName("inideposit"), "Initial Deposit must not be blank");
    }

    @Test
    public void TC_07_Initial_Deposit_Must_Be_Numeric(){
        newAccountPage.enterToElementByName("inideposit", "accxx");
        verifyEquals(newAccountPage.getErrorMsgTextByName("inideposit"), "Characters are not allowed");
        newAccountPage.enterToElementByName("inideposit", "accx1223x");
        verifyEquals(newAccountPage.getErrorMsgTextByName("inideposit"), "Characters are not allowed");
    }

    @Test
    public void TC_08_Initial_Deposit_Cannot_Have_Special_Character(){
        newAccountPage.enterToElementByName("inideposit", "&&*");
        verifyEquals(newAccountPage.getErrorMsgTextByName("inideposit"), "Special characters are not allowed");
        newAccountPage.enterToElementByName("inideposit", "123&&*");
        verifyEquals(newAccountPage.getErrorMsgTextByName("inideposit"), "Special characters are not allowed");
    }

    @Test
    public void TC_09_Initial_Deposit_Cannot_Have_Blank_Space(){
        newAccountPage.enterToElementByName("inideposit", "123 233");
        verifyEquals(newAccountPage.getErrorMsgTextByName("inideposit"), "Characters are not allowed");
    }

    @Test
    public void TC_10_Initial_Deposit_First_Char_Cannot_Be_Space(){
        newAccountPage.enterToElementByName("inideposit", " 12233");
        verifyEquals(newAccountPage.getErrorMsgTextByName("inideposit"), "First character can not have space");
    }

    @Test
    public void TC_11_New_Account_Valid_Input(){
        newAccountPage.enterToElementByName("cusid", customer.getUserId());
        newAccountPage.enterToElementByName("inideposit", "100000");
        newAccountPage.clickToElementByName("button2");
        newAccountPage.updateAccountInfoToFile(GlobalConstant.mainResourcePath+ "currentCustomerData.json", customer, "Account ID", "Account Type", "Current Amount");
    }

    @AfterClass
    public void afterClass(){
        closeBrowserAndDriver();
    }
}
