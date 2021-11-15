package testcases.com.bankguru;

import actions.common.BaseTest;
import globalConstants.GlobalConstant;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.*;
import utilities.jsonDataHelper.JsonDataHelper;

public class TC_12_Payment extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    NewCustomerPageObject newCustomerPage;
    EditCustomerPageObject editCustomerPage;
    NewAccountPageObject newAccountPage;
    EditAccountPageObject editAccountPage;
    DepositPageObject depositPage;
    WithdrawalPageObject withdrawalPage;
    FundTransferPageObject fundTransferPage;
    BalanceEnquiryPageObject balanceEnquiryPage;
    DeleteAccountPageObject deleteAccountPage;
    DeleteCustomerPageObject deleteCustomerPage;

    JsonDataHelper.Customer customer;
    JsonDataHelper.Manager manager;

    @Parameters("filename")
    @BeforeClass
    public void beforeClass(String filename) {
        customer = JsonDataHelper.readOneRandomData(GlobalConstant.mainResourcePath + filename);
        manager = JsonDataHelper.readAccessData(GlobalConstant.mainResourcePath + "accessData.json");
    }

    @Parameters("browserName")
    @BeforeMethod
    public void beforeMethod(String browserName) {
        driver = getDriver(browserName, GlobalConstant.homePageUrl);
        homePage = PageGeneratorManager.getHomePage(driver);
        homePage.enterToElementByName("uid", manager.getUserId());
        homePage.enterToElementByName("password", manager.getUserPassword());
        homePage.clickToElementByName("btnLogin");
        verifyEquals(homePage.getWelcomeText(), "Welcome To Manager's Page of Guru99 Bank");
    }

    @AfterMethod
    public void afterMethod() {
        closeBrowserAndDriver();
    }

    @Test
    public void TC_01_Create_New_Customer() {
        newCustomerPage = (NewCustomerPageObject) homePage.clickRightNavBarLinkByTextName("New Customer");
        newCustomerPage.enterToElementByName("name", customer.getName());
        newCustomerPage.enterToElementByName("addr", customer.getAddr());
        newCustomerPage.enterToElementByName("emailid", customer.getEmailid() + getRandomNumber());
        newCustomerPage.enterToElementByName("state", customer.getState());
        newCustomerPage.enterToElementByName("city", customer.getCity());
        newCustomerPage.enterToElementByName("telephoneno", customer.getTelephoneno());
        newCustomerPage.enterToElementByName("password", customer.getPassword());
        newCustomerPage.enterToElementByName("pinno", customer.getPinno());
        newCustomerPage.checkGenderCheckbox("rad1", customer.getGender());
        newCustomerPage.enterToElementByName("dob", customer.getDob());
        newCustomerPage.clickToElementByName("sub");
        verifyEquals(newCustomerPage.getHeadingTableText(), "Customer Registered Successfully!!!");
        newCustomerPage.updateNewCustomerInfoToFile(customer, GlobalConstant.mainResourcePath + "cCustomer.json", "Customer ID");
    }

    @Test
    public void TC_02_Edit_Customer() {
        editCustomerPage = (EditCustomerPageObject) homePage.clickRightNavBarLinkByTextName("Edit Customer");
        editCustomerPage.enterToElementByName("cusid", customer.getUserId());
        editCustomerPage.clickToElementByName("AccSubmit");
        editCustomerPage.enterToElementByName("addr", "222 Bird Nest");
        editCustomerPage.enterToElementByName("city", "Sky Fall");
        editCustomerPage.enterToElementByName("state", "Cloudy");
        editCustomerPage.enterToElementByName("pinno", "797979");
        editCustomerPage.clickToElementByName("sub");
        verifyEquals(editCustomerPage.getHeadingTableText(), "Customer details updated Successfully!!!");
        editCustomerPage.updateNewCustomerInfoToFile(customer, GlobalConstant.mainResourcePath + "cCustomer.json",
                "Address", "City", "State", "PIN");
    }

    @Test
    public void TC_03_Add_New_Account() {
        //Verify initial deposit and current amount are equals
        newAccountPage = (NewAccountPageObject) homePage.clickRightNavBarLinkByTextName("New Account");
        newAccountPage.enterToElementByName("cusid", customer.getUserId());
        newAccountPage.enterToElementByName("inideposit", "500000");
        newAccountPage.selectAccountTypeByName("Savings");
        newAccountPage.clickToElementByName("button2");
        verifyEquals(newAccountPage.getValueByFieldName("Current Amount"), "500000");
        newAccountPage.updateAccountInfoToFile(GlobalConstant.mainResourcePath + "cCustomer.json", customer, "Account ID", "Account Type", "Current Amount");

    }

    @Test
    public void TC_04_Edit_Account() {
        //edit account type and verify
        editAccountPage = (EditAccountPageObject) homePage.clickRightNavBarLinkByTextName("Edit Account");
        editAccountPage.enterToElementByName("accountno", customer.getAccount().getAccountId());
        editAccountPage.clickToElementByName("AccSubmit");
        editAccountPage.selectAccountTypeByName("Current");
        editAccountPage.clickToElementByName("AccSubmit");
        verifyEquals(editAccountPage.getHeadingTableText(), "Account details updated Successfully!!!");
        editAccountPage.updateAccountInfoToFile(GlobalConstant.mainResourcePath + "cCustomer.json", customer, "Account Type");
        verifyEquals(editAccountPage.getValueByFieldName("Account Type"), customer.getAccount().getAccountType());
    }

    @Test
    public void TC_05_Transfer_To_Current_Account() {
        depositPage = (DepositPageObject) homePage.clickRightNavBarLinkByTextName("Deposit");
        depositPage.enterToElementByName("accountno", customer.getAccount().getAccountId());
        depositPage.enterToElementByName("ammount", "5000");
        depositPage.enterToElementByName("desc", "Deposit");
        depositPage.clickToElementByName("AccSubmit");
        verifyEquals(depositPage.getHeadingTableText(), "Transaction details of Deposit for Account " + customer.getAccount().getAccountId());
        depositPage.updateAccountInfoToFile(GlobalConstant.mainResourcePath + "cCustomer.json", customer, "Current Balance");
    }

    @Test
    public void TC_06_Withdrawal_From_Current_Account() {
        withdrawalPage = (WithdrawalPageObject) homePage.clickRightNavBarLinkByTextName("Withdrawal");
        withdrawalPage.enterToElementByName("accountno", customer.getAccount().getAccountId());
        withdrawalPage.enterToElementByName("ammount", "15000");
        withdrawalPage.enterToElementByName("desc", "Withdrawal");
        withdrawalPage.clickToElementByName("AccSubmit");
        verifyEquals(withdrawalPage.getHeadingTableText(), "Transaction details of Withdrawal for Account " + customer.getAccount().getAccountId());
        withdrawalPage.updateAccountInfoToFile(GlobalConstant.mainResourcePath + "cCustomer.json", customer, "Current Balance");
    }

    @Test
    public void TC_07_Transfer_To_Another_Account() {
        fundTransferPage = (FundTransferPageObject) homePage.clickRightNavBarLinkByTextName("Fund Transfer");
        fundTransferPage.enterToElementByName("payersaccount", customer.getAccount().getAccountId());
        fundTransferPage.enterToElementByName("payeeaccount", "100505");
        fundTransferPage.enterToElementByName("ammount", "10000");
        fundTransferPage.enterToElementByName("desc", "Transfer");
        fundTransferPage.clickToElementByName("AccSubmit");
        verifyEquals(fundTransferPage.getValueByFieldName("Amount"), "10000");
    }

    @Test
    public void TC_08_Check_Current_Account_At_Enquiry_Page() {
        balanceEnquiryPage = (BalanceEnquiryPageObject) homePage.clickRightNavBarLinkByTextName("Balance Enquiry");
        balanceEnquiryPage.enterToElementByName("accountno", customer.getAccount().getAccountId());
        balanceEnquiryPage.clickToElementByName("AccSubmit");
        balanceEnquiryPage.updateAccountInfoToFile(GlobalConstant.mainResourcePath + "cCustomer.json", customer, "Balance");
        verifyEquals(balanceEnquiryPage.getValueByFieldName("Balance"), customer.getAccount().getAccountAmount());
    }

    @Test
    public void TC_09_Delete_Account() {
        deleteAccountPage = (DeleteAccountPageObject) homePage.clickRightNavBarLinkByTextName("Delete Account");
        deleteAccountPage.enterToElementByName("accountno", customer.getAccount().getAccountId());
        deleteAccountPage.clickToElementByName("AccSubmit");
        deleteAccountPage.acceptAlert();
        verifyEquals(deleteAccountPage.getAlertText(), "Account Deleted Sucessfully");
        deleteAccountPage.acceptAlert();

        editAccountPage = (EditAccountPageObject) deleteAccountPage.clickRightNavBarLinkByTextName("Edit Account");
        editAccountPage.enterToElementByName("accountno", customer.getAccount().getAccountId());
        editAccountPage.clickToElementByName("AccSubmit");
        verifyEquals(editAccountPage.getAlertText(), "Account does not exist");
        editAccountPage.acceptAlert();
    }

    @Test
    public void TC_10_Delete_Customer() {
        deleteCustomerPage = (DeleteCustomerPageObject) homePage.clickRightNavBarLinkByTextName("Delete Customer");
        deleteCustomerPage.enterToElementByName("cusid", customer.getUserId());
        deleteCustomerPage.clickToElementByName("AccSubmit");
        deleteCustomerPage.acceptAlert();
        verifyEquals(deleteCustomerPage.getAlertText(), "Customer deleted Successfully");
        deleteCustomerPage.acceptAlert();

        editCustomerPage = (EditCustomerPageObject) deleteCustomerPage.clickRightNavBarLinkByTextName("Edit Customer");
        editCustomerPage.enterToElementByName("cusid", customer.getAccount().getAccountId());
        editCustomerPage.clickToElementByName("AccSubmit");
        verifyEquals(editCustomerPage.getAlertText(), "Customer does not exist!!");
        editCustomerPage.acceptAlert();
    }

}
