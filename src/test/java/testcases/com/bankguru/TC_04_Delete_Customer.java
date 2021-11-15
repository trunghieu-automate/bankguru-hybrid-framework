package testcases.com.bankguru;

import actions.common.BaseTest;
import globalConstants.GlobalConstant;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.DeleteCustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.PageGeneratorManager;
import utilities.jsonDataHelper.JsonDataHelper;

public class TC_04_Delete_Customer extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    DeleteCustomerPageObject deleteCustomerPage;

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
        deleteCustomerPage = (DeleteCustomerPageObject) homePage.clickRightNavBarLinkByTextName("Delete Customer");

    }

    @Test
    public void TC_01_Customer_ID_Cannot_Be_Empty() {
        deleteCustomerPage.clickToElementByName("cusid");
        deleteCustomerPage.clickToElementByName("AccSubmit");
        verifyEquals(deleteCustomerPage.getErrorMsgTextByName("cusid"), "Customer ID is required");

    }

    @Test
    public void TC_02_Customer_ID_Must_Be_Numeric() {
        deleteCustomerPage.enterToElementByName("cusid", "expectedText");
        verifyEquals(deleteCustomerPage.getErrorMsgTextByName("cusid"), "Characters are not allowed");
    }

    @Test
    public void TC_03_Customer_ID_Cannot_Have_Special_Characters() {
        deleteCustomerPage.enterToElementByName("cusid", "^^&&*");
        verifyEquals(deleteCustomerPage.getErrorMsgTextByName("cusid"), "Special characters are not allowed");
    }

    @Test
    public void TC_04_Customer_ID_Cannot_Have_Blank_Space() {
        deleteCustomerPage.enterToElementByName("cusid", "12 334");
        verifyEquals(deleteCustomerPage.getErrorMsgTextByName("cusid"), "Characters are not allowed");
    }

    @Test
    public void TC_05_Customer_ID_First_Char_Cannot_Be_Space() {
        deleteCustomerPage.enterToElementByName("cusid", " 12334");
        verifyEquals(deleteCustomerPage.getErrorMsgTextByName("cusid"), "First character can not have space");
    }

    @AfterClass
    public void afterClass() {
        closeBrowserAndDriver();
    }
}
