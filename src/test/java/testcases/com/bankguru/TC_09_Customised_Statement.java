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

public class TC_09_Customised_Statement extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    JsonDataHelper.Customer customer;
    CustomisedStatementPageObject customisedStatementPage;


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
        customisedStatementPage = (CustomisedStatementPageObject) homePage.clickRightNavBarLinkByTextName("Customised Statement");

    }

    @Test
    public void TC_01_Account_ID_Cannot_Be_Empty() {
        customisedStatementPage.clickToElementByName("accountno");
        customisedStatementPage.clickToElementByName("AccSubmit");
        verifyEquals(customisedStatementPage.getErrorMsgTextByName("accountno"), "Account Number must not be blank");

    }

    @Test
    public void TC_02_Account_ID_Must_Be_Numeric() {
        customisedStatementPage.enterToElementByName("accountno", "123sdds");
        verifyEquals(customisedStatementPage.getErrorMsgTextByName("accountno"), "Characters are not allowed");
        customisedStatementPage.enterToElementByName("accountno", "askkf");
        verifyEquals(customisedStatementPage.getErrorMsgTextByName("accountno"), "Characters are not allowed");
    }

    @Test
    public void TC_03_Account_ID_Cannot_Have_Special_Characters() {
        customisedStatementPage.enterToElementByName("accountno", "^^&&*");
        verifyEquals(customisedStatementPage.getErrorMsgTextByName("accountno"), "Special characters are not allowed");
        customisedStatementPage.enterToElementByName("accountno", "124@$%");
        verifyEquals(customisedStatementPage.getErrorMsgTextByName("accountno"), "Special characters are not allowed");
    }

    @Test
    public void TC_04_Account_ID_Cannot_Have_Blank_Space() {
        customisedStatementPage.enterToElementByName("accountno", "12 334");
        verifyEquals(customisedStatementPage.getErrorMsgTextByName("accountno"), "Characters are not allowed");
    }


    @Test
    public void TC_05_Amount_Lower_Limit_Must_Be_Numeric() {
        customisedStatementPage.enterToElementByName("amountlowerlimit", "123sdds");
        verifyEquals(customisedStatementPage.getErrorMsgTextByName("amountlowerlimit"), "Characters are not allowed");
        customisedStatementPage.enterToElementByName("amountlowerlimit", "askkf");
        verifyEquals(customisedStatementPage.getErrorMsgTextByName("amountlowerlimit"), "Characters are not allowed");
    }

    @Test
    public void TC_06_Amount_Lower_Limit_Cannot_Have_Special_Characters() {
        customisedStatementPage.enterToElementByName("amountlowerlimit", "^^&&*");
        verifyEquals(customisedStatementPage.getErrorMsgTextByName("amountlowerlimit"), "Special characters are not allowed");
        customisedStatementPage.enterToElementByName("amountlowerlimit", "124@$%");
        verifyEquals(customisedStatementPage.getErrorMsgTextByName("amountlowerlimit"), "Special characters are not allowed");
    }

    @Test
    public void TC_07_Amount_Lower_Limit_Cannot_Have_Blank_Space() {
        customisedStatementPage.enterToElementByName("amountlowerlimit", "12 334");
        verifyEquals(customisedStatementPage.getErrorMsgTextByName("amountlowerlimit"), "Characters are not allowed");
    }

    @Test
    public void TC_08_Number_Of_Transaction_Must_Be_Numeric() {
        customisedStatementPage.enterToElementByName("numtransaction", "123sdds");
        verifyEquals(customisedStatementPage.getErrorMsgTextByName("numtransaction"), "Characters are not allowed");
        customisedStatementPage.enterToElementByName("numtransaction", "askkf");
        verifyEquals(customisedStatementPage.getErrorMsgTextByName("numtransaction"), "Characters are not allowed");
    }

    @Test
    public void TC_09_Number_Of_Transaction_Cannot_Have_Special_Characters() {
        customisedStatementPage.enterToElementByName("numtransaction", "^^&&*");
        verifyEquals(customisedStatementPage.getErrorMsgTextByName("numtransaction"), "Special characters are not allowed");
        customisedStatementPage.enterToElementByName("numtransaction", "124@$%");
        verifyEquals(customisedStatementPage.getErrorMsgTextByName("numtransaction"), "Special characters are not allowed");
    }

    @Test
    public void TC_10_Number_Of_Transaction_Cannot_Have_Blank_Space() {
        customisedStatementPage.enterToElementByName("numtransaction", "12 334");
        verifyEquals(customisedStatementPage.getErrorMsgTextByName("numtransaction"), "Characters are not allowed");
    }

    @AfterClass
    public void afterClass() {
        closeBrowserAndDriver();
    }
}
