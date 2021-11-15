package actions.common;

import globalConstants.GlobalConstant;
import intefaces.pageUIs.BasePageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.json.Json;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.PageGeneratorManager;
import utilities.jsonDataHelper.JsonDataHelper;
import utilities.logger.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;
    JavascriptExecutor jsExecutor;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, GlobalConstant.LONG_TIME_OUT);
        jsExecutor = (JavascriptExecutor) driver;
    }

    // dynamic xpath
    public String getDynamicXpath(String xpathLocator, String... params) {
        return String.format(xpathLocator, (Object[]) params);
    }

    //wait element visible
    public WebElement waitElementVisible(String xpath) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        } catch (Exception e) {
            Log.info("Element doesn't exist.");
            return waitElementsVisible(xpath).get(0);
        }
    }

    // wait and find elements
    public List<WebElement> waitElementsVisible(String xpath) {
        List<WebElement> elements = new ArrayList<>(0);
        try {
            return wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath(xpath))));
        } catch (Exception e) {
            Log.info("Element doesn't exist");
            return elements;
        }
    }

    // wait Alert
    public Alert waitAlert() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    // Handle Alert
    public void acceptAlert() {
        waitAlert().accept();
    }

    public String getAlertText() {
        return waitAlert().getText();
    }

    public void dismissAlert() {
        waitAlert().dismiss();
    }


    public boolean isElementVisible(String xpath) {
        if (waitElementsVisible(xpath).size() == 0) {
            return false;
        }
        return true;
    }

    public boolean isElementInvisible(String xpath) {
        if (waitElementsVisible(xpath).size() == 0) {
            return true;
        }
        return false;
    }

    // click
    public void clickElement(String xpath) {
        waitElementVisible(xpath).click();
    }

    // sendKeys
    public void sendKeysToElement(String xpath, String expectedText) {
        waitElementVisible(xpath).clear();
        waitElementVisible(xpath).sendKeys(expectedText);
    }

    // Select
    public void selectItemDefaultDropdownListByName(String xpath, String itemName) {
        Select select = new Select(waitElementVisible(xpath));
        select.selectByVisibleText(itemName);
    }

    // get Element Text
    public String getElementText(String xpath) {
        return waitElementVisible(xpath).getText();
    }

    public void checkRadioBtn(String xpath) {
        WebElement radioBtn = waitElementVisible(xpath);
        if (!radioBtn.isSelected()) {
            radioBtn.click();
        }
    }

    // check status
    public boolean isRadioChecked(String xpath) {
        WebElement radioBtn = waitElementVisible(xpath);
        if (!radioBtn.isSelected()) {
            return false;
        }
        return true;
    }

    public String getElementValue(String xpath) {
        return waitElementVisible(xpath).getAttribute("value");
    }

    // js injection
    public void removeAttribute(String xpath, String attributeName) {
        jsExecutor.executeScript("arguments[0].removeAttribute(" + attributeName + ")", waitElementVisible(xpath));
    }

    public void setAttribute(String xpath, String attributeName, String attrValue) {
        jsExecutor.executeScript("arguments[0].setAttribute(" + attributeName + "," + attrValue + ")", waitElementVisible(xpath));
    }


    // Thread sleep
    public void sleepInSecond(long sec) {
        try {
            Thread.sleep(1000 * sec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Bankguru specific method
    @Step("Click to Right nav bar to get {0} page")
    public BasePage clickRightNavBarLinkByTextName(String menuName) {
        clickElement(getDynamicXpath(BasePageUI.DYNAMIC_RIGHT_NAV_BAR_LINK, menuName));
        switch (menuName) {
            case "New Customer":
                return PageGeneratorManager.getNewCustomerPage(driver);
            case "Edit Customer":
                return PageGeneratorManager.getEditCustomerPage(driver);
            case "Delete Customer":
                return PageGeneratorManager.getDeleteCustomerPage(driver);
            case "New Account":
                return PageGeneratorManager.getNewAccountPage(driver);
            case "Edit Account":
                return PageGeneratorManager.getEditAccountPage(driver);
            case "Delete Account":
                return PageGeneratorManager.getDeleteAccountPage(driver);
            case "Mini Statement":
                return PageGeneratorManager.getMiniStatementPage(driver);
            case "Customised Statement":
                return PageGeneratorManager.getCustomiedStatementPage(driver);
            case "Fund Transfer":
                return PageGeneratorManager.getFundTransferPage(driver);
            case "Change Password":
                return PageGeneratorManager.getChangePasswordPage(driver);
            case "Deposit":
                return PageGeneratorManager.getDepositPage(driver);
            case "Withdrawal":
                return PageGeneratorManager.getWithdrawalPage(driver);
            case "Balance Enquiry":
                return PageGeneratorManager.getBalanceEnquiryPage(driver);
        }
        throw new IllegalArgumentException("Wrong menuName");
    }

    @Step("Click to Element with attribute name: {0}")
    public void clickToElementByName(String nameAttr) {
        clickElement(getDynamicXpath(BasePageUI.DYNAMIC_ELEMENT_BY_NAME, nameAttr));
    }

    @Step("Get error message's text Element with attribute name: {0} ")
    public String getErrorMsgTextByName(String nameAttr) {
        return getElementText(getDynamicXpath(BasePageUI.DYNAMIC_ERROR_MESSAGE_TEXT_BY_NAME, nameAttr));
    }

    @Step("Sendkey to Element has attribute name: {0}, with text: {1} ")
    public void enterToElementByName(String nameAttr, String expectedText) {
        sendKeysToElement(getDynamicXpath(BasePageUI.DYNAMIC_ELEMENT_BY_NAME, nameAttr), expectedText);
    }

    @Step("Check field's undisplayed")
    public boolean isFieldNameUndisplayed(String nameAttr) {
        return isElementInvisible(getDynamicXpath(BasePageUI.DYNAMIC_FIELD_VALUE_BY_FILED_NAME, nameAttr));
    }

    @Step("Get table heading text to verify")
    public String getHeadingTableText() {
        return getElementText(BasePageUI.TABLE_HEADING);
    }

    public String randomDeposit() {
        return String.valueOf(new Random().nextInt(999999));
    }

    public void updateNewCustomerInfoToFile(JsonDataHelper.Customer customer, String outputFilename, String... infoName) {
        ArrayList<String> infoNames = new ArrayList<>(Arrays.asList(infoName));
        for (String info : infoNames) {
            switch (info) {
                case "Customer ID":
                    customer.setUserId(getElementText(getDynamicXpath(BasePageUI.DYNAMIC_FIELD_VALUE_BY_FILED_NAME, info)));
                    break;
                case "Address":
                    customer.setAddr(getElementText(getDynamicXpath(BasePageUI.DYNAMIC_FIELD_VALUE_BY_FILED_NAME, info)));
                    break;
                case "City":
                    customer.setCity(getElementText(getDynamicXpath(BasePageUI.DYNAMIC_FIELD_VALUE_BY_FILED_NAME, info)));
                    break;
                case "State":
                    customer.setState(getElementText(getDynamicXpath(BasePageUI.DYNAMIC_FIELD_VALUE_BY_FILED_NAME, info)));
                    break;
                case "Pin":
                    customer.setPinno(getElementText(getDynamicXpath(BasePageUI.DYNAMIC_FIELD_VALUE_BY_FILED_NAME, info)));
                    break;
                case "Mobile No.":
                    customer.setTelephoneno(getElementText(getDynamicXpath(BasePageUI.DYNAMIC_FIELD_VALUE_BY_FILED_NAME, info)));
                    break;
                case "Email":
                    customer.setEmailid(getElementText(getDynamicXpath(BasePageUI.DYNAMIC_FIELD_VALUE_BY_FILED_NAME, info)));
                    break;
                case "Birthdate":
                    customer.setDob(getElementText(getDynamicXpath(BasePageUI.DYNAMIC_FIELD_VALUE_BY_FILED_NAME, info)));
                    break;
                case "Gender":
                    customer.setGender(getElementText(getDynamicXpath(BasePageUI.DYNAMIC_FIELD_VALUE_BY_FILED_NAME, info)));
                    break;
                case "Customer Name":
                    customer.setName(getElementText(getDynamicXpath(BasePageUI.DYNAMIC_FIELD_VALUE_BY_FILED_NAME, info)));
                    break;
            }
        }
        JsonDataHelper.overwriteCustomerInfoToFile(outputFilename, customer);
    }

    public String getValueByFieldName(String fieldName) {
        return getElementText(getDynamicXpath(BasePageUI.DYNAMIC_FIELD_VALUE_BY_FILED_NAME, fieldName));
    }

    public void updateAccountInfoToFile(String filename, JsonDataHelper.Customer customer, String... accountInfo) {
        ArrayList<String> accInfo = new ArrayList<>(Arrays.asList(accountInfo));
        if (customer.getAccount() != null) {
            for (String info : accInfo) {
                switch (info) {
                    case "Account ID":
                        customer.getAccount().setAccountId(getValueByFieldName(info));
                        break;
                    case "Account Type":
                        customer.getAccount().setAccountType(getValueByFieldName(info));
                        break;
                    case "Current Balance":
                    case "Current Amount":
                    case "Balance":
                        customer.getAccount().setAccountAmount(getValueByFieldName(info));
                        break;
                }
            }
        }
        else {
            JsonDataHelper.Customer.Account acc = new JsonDataHelper.Customer.Account();
            for (String info : accInfo) {
                switch (info) {
                    case "Account ID":
                        acc.setAccountId(getValueByFieldName(info));
                        break;
                    case "Account Type":
                        acc.setAccountType(getValueByFieldName(info));
                        break;
                    case "Current Balance":
                    case "Balance":
                    case "Current Amount":
                        acc.setAccountAmount(getValueByFieldName(info));
                        break;
                }
            }
            customer.setAccount(acc);
        }
        JsonDataHelper.overwriteCustomerInfoToFile(filename, customer);
    }

}
