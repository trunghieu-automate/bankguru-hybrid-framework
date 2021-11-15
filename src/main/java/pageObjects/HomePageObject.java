package pageObjects;

import actions.common.BasePage;
import intefaces.pageUIs.HomePageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.jsonDataHelper.JsonDataHelper;

import java.io.IOException;

public class HomePageObject extends BasePage {

    public HomePageObject(WebDriver driver) {
        super(driver);
    }

    @Step("Click to access generator page link")
    public void clickGenerateAccessLink() {
        clickElement(HomePageUI.ACCESS_DATA_GENERATION_LINK);
    }

    @Step("Enter email text box with email: {0}")
    public void enterEmailID(String email) {
        sendKeysToElement(HomePageUI.EMAIL_TEXTBOX, email);
    }

    @Step("Click submit button")
    public void clickSubmitBtn() {
        clickElement(HomePageUI.SUBMIT_BUTON);
    }

    @Step("Get error message text to verify")
    public Object getEmailInvalidMsg() {
        return getElementText(HomePageUI.EMAIL_ERROR_MSG);
    }

    @Step("Write and save access information to accessData.json file in main/resources/")
    public void writeAccessDataToFile() throws IOException {
        String accessEmail = getElementText(HomePageUI.ACCESS_USER_ID);
        String accessPassword = getElementText(HomePageUI.ACCESS_USER_PASSWORD);
        JsonDataHelper.writeAccessDataToJsonFile(accessEmail, accessPassword);
    }

    @Step("Get access info table's element to verify display")
    public WebElement getAccessTableElement() {
        return waitElementVisible(HomePageUI.ACCESS_INFO_TABLE);
    }

    public void enterToElementByName(String textboxName, String expectedText) {
        sendKeysToElement(getDynamicXpath(HomePageUI.DYNAMIC_HOME_PAGE_INPUT_ELEMENT_NAME, textboxName), expectedText);
    }

    public void clickToElementByName(String buttonName) {
        clickElement(getDynamicXpath(HomePageUI.DYNAMIC_HOME_PAGE_INPUT_ELEMENT_NAME, buttonName));
    }

    public Object getWelcomeText() {
        return getElementText(HomePageUI.WELCOME_MANAGE_USER);
    }
}
