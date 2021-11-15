package testcases.com.bankguru;

import actions.common.BaseTest;
import globalConstants.GlobalConstant;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.EditCustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.NewCustomerPageObject;
import pageObjects.PageGeneratorManager;
import utilities.jsonDataHelper.JsonDataHelper;

public class TC_03_Edit_Customer extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    NewCustomerPageObject newCustomerPage;
    EditCustomerPageObject editCustomerPage;
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
        verifyEquals(newCustomerPage.getHeadingTableText(), "Customer Registered Successfully!!!");
        newCustomerPage.updateNewCustomerInfoToFile(customer, "currentCustomerData.json", "Customer ID");
        editCustomerPage = (EditCustomerPageObject) newCustomerPage.clickRightNavBarLinkByTextName("Edit Customer");

    }

    @Test
    public void TC_01_Customer_ID_Cannot_Be_Empty() {
        editCustomerPage.clickToElementByName("cusid");
        editCustomerPage.clickToElementByName("AccSubmit");
        verifyEquals(editCustomerPage.getErrorMsgTextByName("cusid"), "Customer ID is required");

    }

    @Test
    public void TC_02_Customer_ID_Must_Be_Numeric() {
        editCustomerPage.enterToElementByName("cusid", "expectedText");
        verifyEquals(editCustomerPage.getErrorMsgTextByName("cusid"), "Characters are not allowed");
    }

    @Test
    public void TC_03_Customer_ID_Cannot_Have_Special_Characters() {
        editCustomerPage.enterToElementByName("cusid", "^^&&*");
        verifyEquals(editCustomerPage.getErrorMsgTextByName("cusid"), "Special characters are not allowed");
    }

    @Test
    public void TC_04_Customer_ID_With_Valid_ID() {
        editCustomerPage.enterToElementByName("cusid", customer.getUserId());
        editCustomerPage.clickToElementByName("AccSubmit");
        verifyTrue(editCustomerPage.isFieldNameUndisplayed("cusid"));
    }

    @Test
    public void TC_05_Address_Cannot_Be_Empty() {
        editCustomerPage.enterToElementByName("addr", "");
        editCustomerPage.clickToElementByName("city");
        verifyEquals(editCustomerPage.getErrorMsgTextByName("addr"), "Address Field must not be blank");
    }

    @Test
    public void TC_06_City_Cannot_Be_Empty() {
        editCustomerPage.enterToElementByName("city", "");
        editCustomerPage.clickToElementByName("state");
        verifyEquals(editCustomerPage.getErrorMsgTextByName("city"), "City Field must not be blank");
    }

    @Test
    public void TC_07_City_Cannot_Be_Numeric() {
        editCustomerPage.enterToElementByName("city", "123456");
        verifyEquals(editCustomerPage.getErrorMsgTextByName("city"), "Numbers are not allowed");
    }

    @Test
    public void TC_08_City_Cannot_Have_Special_Character() {
        editCustomerPage.enterToElementByName("city", "******");
        verifyEquals(editCustomerPage.getErrorMsgTextByName("city"), "Special characters are not allowed");
    }

    @Test
    public void TC_09_State_Cannot_Be_Empty() {
        editCustomerPage.enterToElementByName("state", "");
        editCustomerPage.clickToElementByName("pinno");
        verifyEquals(editCustomerPage.getErrorMsgTextByName("state"), "State must not be blank");
    }

    @Test
    public void TC_10_State_Cannot_Have_Special_Character() {
        editCustomerPage.enterToElementByName("state", "******");
        verifyEquals(editCustomerPage.getErrorMsgTextByName("state"), "Special characters are not allowed");
    }

    @Test
    public void TC_11_State_Cannot_Be_numeric() {
        editCustomerPage.enterToElementByName("state", "123456");
        verifyEquals(editCustomerPage.getErrorMsgTextByName("state"), "Numbers are not allowed");
    }

    @Test
    public void TC_12_PIN_Must_Be_Numeric() {
        editCustomerPage.enterToElementByName("pinno", "abcdef");
        verifyEquals(editCustomerPage.getErrorMsgTextByName("pinno"), "Characters are not allowed");
    }

    @Test
    public void TC_13_PIN_Cannot_Be_Empty() {
        editCustomerPage.enterToElementByName("pinno", "");
        editCustomerPage.clickToElementByName("state");
        verifyEquals(editCustomerPage.getErrorMsgTextByName("pinno"), "PIN Code must not be blank");
    }

    @Test
    public void TC_14_PIN_Must_Have_6_Digits() {
        editCustomerPage.enterToElementByName("pinno", "12345");
        verifyEquals(editCustomerPage.getErrorMsgTextByName("pinno"), "PIN Code must have 6 Digits");
    }

    @Test
    public void TC_15_PIN_Cannot_Have_Special_Character() {
        editCustomerPage.enterToElementByName("pinno", "******");
        verifyEquals(editCustomerPage.getErrorMsgTextByName("pinno"), "Special characters are not allowed");
    }

    @Test
    public void TC_16_Telephone_Cannot_Be_Empty() {
        editCustomerPage.enterToElementByName("telephoneno", "");
        editCustomerPage.clickToElementByName("pinno");
        verifyEquals(editCustomerPage.getErrorMsgTextByName("telephoneno"), "Mobile no must not be blank");
    }

    @Test
    public void TC_17_Telephone_Cannot_Have_Special_Character() {
        editCustomerPage.enterToElementByName("telephoneno", "******");
        verifyEquals(editCustomerPage.getErrorMsgTextByName("telephoneno"), "Special characters are not allowed");
    }

    @Test
    public void TC_18_Email_Cannot_Be_Empty() {
        editCustomerPage.enterToElementByName("emailid", "");
        editCustomerPage.clickToElementByName("pinno");
        verifyEquals(editCustomerPage.getErrorMsgTextByName("emailid"), "Email-ID must not be blank");
    }

    @Test
    public void TC_19_Email_Must_Be_In_Format() {
        editCustomerPage.enterToElementByName("emailid", "abc@");
        verifyEquals(editCustomerPage.getErrorMsgTextByName("emailid"), "Email-ID is not valid");
        editCustomerPage.enterToElementByName("emailid", "abc@hotmailvn");
        verifyEquals(editCustomerPage.getErrorMsgTextByName("emailid"), "Email-ID is not valid");
        editCustomerPage.enterToElementByName("emailid", "abc@jhd");
        verifyEquals(editCustomerPage.getErrorMsgTextByName("emailid"), "Email-ID is not valid");
    }

    @AfterClass
    public void afterClass() {
        closeBrowserAndDriver();
    }
}
