package pageObjects;

import actions.common.BasePage;
import intefaces.pageUIs.BasePageUI;
import org.openqa.selenium.WebDriver;
import utilities.jsonDataHelper.JsonDataHelper;

public class NewAccountPageObject extends BasePage {
    public NewAccountPageObject(WebDriver driver) {
        super(driver);
    }



    public void selectAccountTypeByName(String accountType) {
        selectItemDefaultDropdownListByName(getDynamicXpath(BasePageUI.DYNAMIC_ELEMENT_BY_NAME, "selaccount"), accountType);
    }
}
