package pageObjects;

import actions.common.BasePage;
import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
    private static HomePageObject homePage;
    private static NewCustomerPageObject newCustomerPage;
    private static EditCustomerPageObject editCustomerPage;
    private static DeleteCustomerPageObject deleteCustomerPage;
    private static NewAccountPageObject newAccountPage;
    private static EditAccountPageObject editAccountPage;
    private static DeleteAccountPageObject deleteAccountPage;
    private static MiniStatementPageObject miniStatementPage;
    private static CustomisedStatementPageObject customisedStatementPage;
    private static FundTransferPageObject fundTransferPage;
    private static ChangePasswordPageObject changePasswordPage;
    private static DepositPageObject depositPage;
    private static WithdrawalPageObject withdrawalPage;
    private static BalanceEnquiryPageObject balanceEnquiryPage;

    private PageGeneratorManager(){
    }


    public static HomePageObject getHomePage(WebDriver driver) {
        if (homePage == null) {
            return homePage = new HomePageObject(driver);
        }
        return new HomePageObject(driver);
    }

    public static NewCustomerPageObject getNewCustomerPage(WebDriver driver) {
        if (newCustomerPage == null) {
            return newCustomerPage = new NewCustomerPageObject(driver);
        }
        return new NewCustomerPageObject(driver);
    }

    public static EditCustomerPageObject getEditCustomerPage(WebDriver driver) {
        if (editCustomerPage == null) {
            return editCustomerPage = new EditCustomerPageObject(driver);
        }
        return new EditCustomerPageObject(driver);
    }
    public static DeleteCustomerPageObject getDeleteCustomerPage(WebDriver driver) {
        if (deleteCustomerPage == null) {
            return deleteCustomerPage = new DeleteCustomerPageObject(driver);
        }
        return deleteCustomerPage;
    }

    public static BasePage getNewAccountPage(WebDriver driver) {
        if (newAccountPage == null) {
            return newAccountPage = new NewAccountPageObject(driver);
        }
        return newAccountPage;
    }

    public static BasePage getEditAccountPage(WebDriver driver) {
        if (editAccountPage == null) {
            return editAccountPage = new EditAccountPageObject(driver);
        }
        return new EditAccountPageObject(driver);
    }

    public static BasePage getDeleteAccountPage(WebDriver driver) {
        if (deleteAccountPage == null) {
            return deleteAccountPage = new DeleteAccountPageObject(driver);
        }
        return deleteAccountPage;
    }

    public static BasePage getMiniStatementPage(WebDriver driver) {
        if (miniStatementPage == null) {
            return miniStatementPage = new MiniStatementPageObject(driver);
        }
        return miniStatementPage;
    }

    public static BasePage getCustomiedStatementPage(WebDriver driver) {
        if (customisedStatementPage == null) {
            return customisedStatementPage = new CustomisedStatementPageObject(driver);
        }
        return customisedStatementPage;
    }

    public static BasePage getFundTransferPage(WebDriver driver) {
        if (fundTransferPage == null) {
            return fundTransferPage = new FundTransferPageObject(driver);
        }
        return fundTransferPage;
    }
    public static BasePage getChangePasswordPage(WebDriver driver) {
        if (changePasswordPage == null) {
            return changePasswordPage = new ChangePasswordPageObject(driver);
        }
        return changePasswordPage;
    }
    public static BasePage getDepositPage(WebDriver driver) {
        if (depositPage == null) {
            return depositPage = new DepositPageObject(driver);
        }
        return depositPage;
    }
    public static BasePage getWithdrawalPage(WebDriver driver) {
        if (withdrawalPage == null) {
            return withdrawalPage = new WithdrawalPageObject(driver);
        }
        return withdrawalPage;
    }
    public static BasePage getBalanceEnquiryPage(WebDriver driver) {
        if (balanceEnquiryPage == null) {
            return balanceEnquiryPage = new BalanceEnquiryPageObject(driver);
        }
        return balanceEnquiryPage;
    }
}