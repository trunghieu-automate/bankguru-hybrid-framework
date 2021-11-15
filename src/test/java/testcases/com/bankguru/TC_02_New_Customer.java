package testcases.com.bankguru;

import actions.common.BaseTest;

import globalConstants.GlobalConstant;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.HomePageObject;
import pageObjects.NewCustomerPageObject;
import pageObjects.PageGeneratorManager;
import utilities.jsonDataHelper.JsonDataHelper;

public class TC_02_New_Customer extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    NewCustomerPageObject newCustomerPage;

    @Parameters({"browserName"})
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getDriver(browserName, GlobalConstant.homePageUrl);
        homePage = PageGeneratorManager.getHomePage(driver);
        JsonDataHelper.Manager manager = JsonDataHelper.readAccessData(GlobalConstant.mainResourcePath + "accessData.json");
        homePage.enterToElementByName("uid", manager.getUserId());
        homePage.enterToElementByName("password", manager.getUserPassword());
        homePage.clickToElementByName("btnLogin");
        verifyEquals(homePage.getWelcomeText(), "Welcome To Manager's Page of Guru99 Bank");
        newCustomerPage = (NewCustomerPageObject) homePage.clickRightNavBarLinkByTextName("New Customer");
    }

    @Test
    public void TC_01_Name_Cannot_Empty() {
        newCustomerPage.clickToElementByName("name");
        newCustomerPage.clickToElementByName("addr");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("name"), "Customer name must not be blank"); //*[@name='name']//following-sibling::label
    }

    @Test
    public void TC_02_Name_Cannot_Be_Numeric() {
        newCustomerPage.enterToElementByName("name", "12345");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("name"), "Numbers are not allowed");
        newCustomerPage.enterToElementByName("name", "abc1234");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("name"), "Numbers are not allowed");
    }

    @Test
    public void TC_03_Name_Cannot_Have_Special_Characters() {
        newCustomerPage.enterToElementByName("name", "abc%%");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("name"), "Special characters are not allowed");
        newCustomerPage.enterToElementByName("name", "@^!&^@");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("name"), "Special characters are not allowed");
    }

    @Test
    public void TC_04_Name_First_Char_Cannot_Be_Space() {
        newCustomerPage.enterToElementByName("name", " hugo");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("name"), "First character can not have space");
    }

    @Test
    public void TC_05_Address_Cannot_Be_Empty() {
        newCustomerPage.clickToElementByName("addr");
        newCustomerPage.clickToElementByName("name");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("addr"), "Address Field must not be blank");
    }

    @Test
    public void TC_06_Address_First_Char_Cannot_Be_Space() {
        newCustomerPage.enterToElementByName("addr", " 155 PCT");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("addr"), "First character can not have space");
    }

    @Test
    public void TC_07_City_Cannot_Be_Empty() {
        newCustomerPage.clickToElementByName("city");
        newCustomerPage.clickToElementByName("name");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("city"), "City Field must not be blank");
    }

    @Test
    public void TC_08_City_Cannot_Be_Numeric() {
        newCustomerPage.enterToElementByName("city", "12345");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("city"), "Numbers are not allowed");
        newCustomerPage.enterToElementByName("city", "abc1234");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("city"), "Numbers are not allowed");
    }

    @Test
    public void TC_09_City_Cannot_Have_Special_Characters() {
        newCustomerPage.enterToElementByName("city", "abc%%");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("city"), "Special characters are not allowed");
        newCustomerPage.enterToElementByName("city", "@^!&^@");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("city"), "Special characters are not allowed");
    }

    @Test
    public void TC_10_City_First_Char_Cannot_Be_Space() {
        newCustomerPage.enterToElementByName("city", " Da Nang");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("city"), "First character can not have space");
    }

    @Test
    public void TC_11_State_Cannot_Be_Empty() {
        newCustomerPage.clickToElementByName("state");
        newCustomerPage.clickToElementByName("name");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("state"), "State must not be blank");
    }

    @Test
    public void TC_12_State_Cannot_Be_Numeric() {
        newCustomerPage.enterToElementByName("state", "12345");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("state"), "Numbers are not allowed");
        newCustomerPage.enterToElementByName("state", "abc1234");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("state"), "Numbers are not allowed");
    }

    @Test
    public void TC_13_State_Cannot_Have_Special_Characters() {
        newCustomerPage.enterToElementByName("state", "abc%%");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("state"), "Special characters are not allowed");
        newCustomerPage.enterToElementByName("state", "@^!&^@");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("state"), "Special characters are not allowed");
    }

    @Test
    public void TC_14_State_First_Char_Cannot_Be_Space() {
        newCustomerPage.enterToElementByName("state", " Da Nang");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("state"), "First character can not have space");
    }

    @Test
    public void TC_15_Pin_Must_Be_Numeric() {
        newCustomerPage.enterToElementByName("pinno", "12345z");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("pinno"), "Characters are not allowed");
    }

    @Test
    public void TC_16_Pin_Cannot_Be_Empty() {
        newCustomerPage.enterToElementByName("pinno", "");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("pinno"), "PIN Code must not be blank");
    }

    @Test
    public void TC_17_Pin_Must_Have_Six_Digits() {
        newCustomerPage.enterToElementByName("pinno", "12345");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("pinno"), "PIN Code must have 6 Digits");
    }

    @Test
    public void TC_18_Pin_Cannot_Have_Special_Character() {
        newCustomerPage.enterToElementByName("pinno", "123@45");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("pinno"), "Special characters are not allowed");
    }

    @Test
    public void TC_19_Pin_Cannot_Have_Special_Character() {
        newCustomerPage.enterToElementByName("pinno", "123@45");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("pinno"), "Special characters are not allowed");
    }

    @Test
    public void TC_20_Pin_Cannot_Have_Blank_Space() {
        newCustomerPage.enterToElementByName("pinno", "12 345");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("pinno"), "Characters are not allowed");
    }

    @Test
    public void TC_21_Telephone_Cannot_Be_Empty() {
        newCustomerPage.clickToElementByName("telephoneno");
        newCustomerPage.clickToElementByName("name");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("telephoneno"), "Mobile no must not be blank");
    }

    @Test
    public void TC_22_Telephone_Cannot_Be_Special_Character() {
        newCustomerPage.enterToElementByName("telephoneno", "09855333@1");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("telephoneno"), "Special characters are not allowed");
        newCustomerPage.enterToElementByName("telephoneno", "$#3154911@");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("telephoneno"), "Special characters are not allowed");
    }

    @Test
    public void TC_23_Telephone_Cannot_Have_Space() {
        newCustomerPage.enterToElementByName("telephoneno", "09856 1596");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("telephoneno"), "Characters are not allowed");
    }

    @Test
    public void TC_24_Telephone_First_Char_Cannot_Be_Space() {
        newCustomerPage.enterToElementByName("telephoneno", " 9898941572");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("telephoneno"), "First character can not have space");
    }

    @Test
    public void TC_25_Email_Cannot_Be_Empty() {
        newCustomerPage.clickToElementByName("emailid");
        newCustomerPage.clickToElementByName("name");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("emailid"), "Email-ID must not be blank");
    }

    @Test
    public void TC_26_Email_Must_Be_In_Correct_Format() {
        newCustomerPage.enterToElementByName("emailid", "abc@bca");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("emailid"), "Email-ID is not valid");
        newCustomerPage.enterToElementByName("emailid", "abc@");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("emailid"), "Email-ID is not valid");
        newCustomerPage.enterToElementByName("emailid", "abcgmail.com");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("emailid"), "Email-ID is not valid");
        newCustomerPage.enterToElementByName("emailid", "abc@gmail.");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("emailid"), "Email-ID is not valid");
        newCustomerPage.enterToElementByName("emailid", "abc");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("emailid"), "Email-ID is not valid");
    }

    @Test
    public void TC_27_Telephone_First_Char_Cannot_Be_Space() {
        newCustomerPage.enterToElementByName("emailid", " abc@abc.vn");
        verifyEquals(newCustomerPage.getErrorMsgTextByName("emailid"), "First character can not have space");
    }

    @Test
    public void TC_28_Check_All_Field_Name() {
        verifyEquals(newCustomerPage.getFieldNameText("name"), "Customer Name");
        verifyEquals(newCustomerPage.getFieldNameText("rad1"), "Gender");
        verifyEquals(newCustomerPage.getFieldNameText("dob"), "Date of Birth");
        verifyEquals(newCustomerPage.getFieldNameText("addr"), "Address");
        verifyEquals(newCustomerPage.getFieldNameText("city"), "City");
        verifyEquals(newCustomerPage.getFieldNameText("state"), "State");
        verifyEquals(newCustomerPage.getFieldNameText("pinno"), "PIN");
        verifyEquals(newCustomerPage.getFieldNameText("telephoneno"), "Mobile Number");
        verifyEquals(newCustomerPage.getFieldNameText("emailid"), "E-mail");
        verifyEquals(newCustomerPage.getFieldNameText("password"), "Password");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver();
    }

}
