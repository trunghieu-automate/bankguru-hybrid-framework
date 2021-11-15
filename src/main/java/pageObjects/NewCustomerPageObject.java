package pageObjects;

import actions.common.BasePage;
import globalConstants.GlobalConstant;
import intefaces.pageUIs.BasePageUI;
import intefaces.pageUIs.NewCustomerPageUI;
import io.qameta.allure.Step;
import org.jsoup.Connection;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.jsonDataHelper.JsonDataHelper;

import java.util.List;

public class NewCustomerPageObject extends BasePage {
    public NewCustomerPageObject(WebDriver driver) {
        super(driver);
    }
    @Step("Click To {0} field name.")
    public void clickToInputByName(String fieldName) {
        clickElement(getDynamicXpath(BasePageUI.DYNAMIC_ELEMENT_BY_NAME, fieldName));
    }

    @Step("Get error message text from {0} field name's to verify")
    public String getErrorMsgTextByName(String fieldName) {
        return getElementText(getDynamicXpath(NewCustomerPageUI.INPUT_ERROR_MESSAGE_TEXT, fieldName));
    }
    @Step("Enter text: {1} to input filed name {0}")
    public void enterToElementByName(String fieldName, String text) {
        sendKeysToElement(getDynamicXpath(BasePageUI.DYNAMIC_ELEMENT_BY_NAME, fieldName), text);
    }
    @Step("Get {0} filed name to verify.")
    public String getFieldNameText(String fieldName) {
        return getElementText(getDynamicXpath(NewCustomerPageUI.FILED_NAME_BY_ATTRIBUTE_NAME, fieldName));
    }

    @Step("Check the {1} checkbox")
    public void checkGenderCheckbox(String radioName, String gender) {
        List<WebElement> radioElements = waitElementsVisible(getDynamicXpath(NewCustomerPageUI.INPUT_ELEMENT_BY_NAME, radioName));
        for(WebElement radioElement: radioElements){
            if (radioElement.getAttribute("value").equalsIgnoreCase(gender)){
                if(!radioElement.isSelected()){
                    radioElement.click();
                }
            }
        }
    }

    @Step("Click to {0} button.")
    public void clickToElementByName(String btnName) {
        clickElement(getDynamicXpath(BasePageUI.DYNAMIC_ELEMENT_BY_NAME, btnName));
    }



}
