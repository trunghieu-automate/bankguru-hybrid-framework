package pageObjects;

import actions.common.BasePage;
import intefaces.pageUIs.BasePageUI;
import org.openqa.selenium.WebDriver;

import java.util.Random;

public class EditAccountPageObject extends BasePage {
    public EditAccountPageObject(WebDriver driver) {
        super(driver);
    }


    public void selectAccountTypeByName(String accountType) {
        selectItemDefaultDropdownListByName(getDynamicXpath(BasePageUI.DYNAMIC_ELEMENT_BY_NAME, "a_type"), accountType);
    }
}
