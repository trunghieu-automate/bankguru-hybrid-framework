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

public class TC_10_Fund_Transfer extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    NewCustomerPageObject newCustomerPage;
    NewAccountPageObject newAccountPage;
    JsonDataHelper.Customer customer;
    FundTransferPageObject fundTransferPage;


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
        fundTransferPage = (FundTransferPageObject) homePage.clickRightNavBarLinkByTextName("Fund Transfer");
    }

    @Test
    public void TC_01_Payer_Account_Limit_Cannot_Be_Empty(){
        fundTransferPage.clickToElementByName("payersaccount");
        fundTransferPage.clickToElementByName("AccSubmit");
        verifyEquals(fundTransferPage.getErrorMsgTextByName("payersaccount"), "Payers Account Number must not be blank");

    }
    @Test
    public void TC_02_Payer_Account_Must_Be_Numeric(){
        fundTransferPage.enterToElementByName("payersaccount", "123sdds");
        verifyEquals(fundTransferPage.getErrorMsgTextByName("payersaccount"), "Characters are not allowed");
        fundTransferPage.enterToElementByName("payersaccount", "askkf");
        verifyEquals(fundTransferPage.getErrorMsgTextByName("payersaccount"), "Characters are not allowed");
    }
    @Test
    public void TC_03_Payer_Account_Cannot_Have_Special_Characters(){
        fundTransferPage.enterToElementByName("payersaccount", "^^&&*");
        verifyEquals(fundTransferPage.getErrorMsgTextByName("payersaccount"), "Special characters are not allowed");
        fundTransferPage.enterToElementByName("payersaccount", "124@$%");
        verifyEquals(fundTransferPage.getErrorMsgTextByName("payersaccount"), "Special characters are not allowed");
    }
    @Test
    public void TC_04_Payer_Account_Cannot_Have_Blank_Space(){
        fundTransferPage.enterToElementByName("payersaccount", "12 334");
        verifyEquals(fundTransferPage.getErrorMsgTextByName("payersaccount"), "Characters are not allowed");
    }

    @Test
    public void TC_05_Payee_Account_Cannot_Be_Empty(){
        fundTransferPage.clickToElementByName("payeeaccount");
        fundTransferPage.clickToElementByName("AccSubmit");
        verifyEquals(fundTransferPage.getErrorMsgTextByName("payeeaccount"), "Payees Account Number must not be blank");

    }
    @Test
    public void TC_06_Payee_Account_Must_Be_Numeric(){
        fundTransferPage.enterToElementByName("payeeaccount", "123sdds");
        verifyEquals(fundTransferPage.getErrorMsgTextByName("payeeaccount"), "Characters are not allowed");
        fundTransferPage.enterToElementByName("payeeaccount", "askkf");
        verifyEquals(fundTransferPage.getErrorMsgTextByName("payeeaccount"), "Characters are not allowed");
    }
    @Test
    public void TC_07_Payee_Account_Cannot_Have_Special_Characters(){
        fundTransferPage.enterToElementByName("payeeaccount", "^^&&*");
        verifyEquals(fundTransferPage.getErrorMsgTextByName("payeeaccount"), "Special characters are not allowed");
        fundTransferPage.enterToElementByName("payeeaccount", "124@$%");
        verifyEquals(fundTransferPage.getErrorMsgTextByName("payeeaccount"), "Special characters are not allowed");
    }
    @Test
    public void TC_08_Payee_Account_Cannot_Have_Blank_Space(){
        fundTransferPage.enterToElementByName("payeeaccount", "12 334");
        verifyEquals(fundTransferPage.getErrorMsgTextByName("payeeaccount"), "Characters are not allowed");
    }
    @Test
    public void TC_09_Amount_Cannot_Be_Empty(){
        fundTransferPage.clickToElementByName("ammount");
        fundTransferPage.clickToElementByName("AccSubmit");
        verifyEquals(fundTransferPage.getErrorMsgTextByName("ammount"), "Amount field must not be blank");

    }
    @Test
    public void TC_10_Amount_Must_Be_Numeric(){
        fundTransferPage.enterToElementByName("ammount", "123sdds");
        verifyEquals(fundTransferPage.getErrorMsgTextByName("ammount"), "Characters are not allowed");
        fundTransferPage.enterToElementByName("ammount", "askkf");
        verifyEquals(fundTransferPage.getErrorMsgTextByName("ammount"), "Characters are not allowed");
    }
    @Test
    public void TC_11_Amount_Cannot_Have_Special_Characters(){
        fundTransferPage.enterToElementByName("ammount", "^^&&*");
        verifyEquals(fundTransferPage.getErrorMsgTextByName("ammount"), "Special characters are not allowed");
        fundTransferPage.enterToElementByName("ammount", "124@$%");
        verifyEquals(fundTransferPage.getErrorMsgTextByName("ammount"), "Special characters are not allowed");
    }

    @AfterClass
    public void afterClass(){
        closeBrowserAndDriver();
    }
}
