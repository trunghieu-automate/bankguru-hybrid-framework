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


public class TC_06_Edit_Account extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    NewCustomerPageObject newCustomerPage;
    NewAccountPageObject newAccountPage;
    JsonDataHelper.Customer customer;
    EditAccountPageObject editAccountPage;


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
        newCustomerPage.enterToElementByName("emailid", customer.getEmailid()+ getRandomNumber());
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
        newAccountPage.enterToElementByName("cusid", customer.getUserId());
        newAccountPage.enterToElementByName("inideposit", newCustomerPage.randomDeposit());
        newAccountPage.clickToElementByName("button2");
        newAccountPage.updateAccountInfoToFile(GlobalConstant.mainResourcePath+ "currentCustomerData.json", customer, "Account ID", "Account Type", "Current Amount");
        editAccountPage = (EditAccountPageObject) newAccountPage.clickRightNavBarLinkByTextName("Edit Account");
    }

    @Test
    public void TC_01_Account_ID_Cannot_Be_Empty(){
        editAccountPage.clickToElementByName("accountno");
        editAccountPage.clickToElementByName("AccSubmit");
        verifyEquals(editAccountPage.getErrorMsgTextByName("accountno"), "Account Number must not be blank");

    }
    @Test
    public void TC_02_Account_ID_Must_Be_Numeric(){
        editAccountPage.enterToElementByName("accountno", "123sdds");
        verifyEquals(editAccountPage.getErrorMsgTextByName("accountno"), "Characters are not allowed");
        editAccountPage.enterToElementByName("accountno", "askkf");
        verifyEquals(editAccountPage.getErrorMsgTextByName("accountno"), "Characters are not allowed");
    }
    @Test
    public void TC_03_Account_ID_Cannot_Have_Special_Characters(){
        editAccountPage.enterToElementByName("accountno", "^^&&*");
        verifyEquals(editAccountPage.getErrorMsgTextByName("accountno"), "Special characters are not allowed");
        editAccountPage.enterToElementByName("accountno", "124@$%");
        verifyEquals(editAccountPage.getErrorMsgTextByName("accountno"), "Special characters are not allowed");
    }
    @Test
    public void TC_04_Account_ID_Cannot_Have_Blank_Space(){
        editAccountPage.enterToElementByName("accountno", "12 334");
        verifyEquals(editAccountPage.getErrorMsgTextByName("accountno"), "Characters are not allowed");
    }

    @Test
    public void TC_05_Account_ID_Valid_Account_Number(){
        editAccountPage.enterToElementByName("accountno", customer.getAccount().getAccountId());
        editAccountPage.clickToElementByName("AccSubmit");
        verifyEquals(editAccountPage.getHeadingTableText(), "Edit Account Entry Form");
    }

    @AfterClass
    public void afterClass(){
        closeBrowserAndDriver();
    }
}
