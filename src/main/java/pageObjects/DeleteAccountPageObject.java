package pageObjects;

import actions.common.BasePage;
import org.openqa.selenium.WebDriver;

public class DeleteAccountPageObject extends BasePage {
    public DeleteAccountPageObject(WebDriver driver) {
        super(driver);
    }

    public void acceptDeleteAccountNotificationAlert() {
        acceptAlert();
    }
}
