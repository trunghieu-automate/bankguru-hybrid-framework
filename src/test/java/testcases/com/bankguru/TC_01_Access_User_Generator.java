package testcases.com.bankguru;

import actions.common.BaseTest;
import globalConstants.GlobalConstant;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import pageObjects.HomePageObject;

import java.io.IOException;
import java.util.Random;

public class TC_01_Access_User_Generator extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    String accessEmail;

    @BeforeClass
    public void beforeClass(){
        accessEmail = "guest" + randomNumber() + "@hotmail.com";
        driver = getDriver("chrome", GlobalConstant.homePageUrl);
        homePage = PageGeneratorManager.getHomePage(driver);
        homePage.clickGenerateAccessLink();
    }


    @Test
    public void TC_01_Invalid_Email(){
        homePage.enterEmailID("s  hksd @s ;c");
        homePage.clickSubmitBtn();
        verifyEquals(homePage.getEmailInvalidMsg(), "Email ID is not valid");
    }

    @Test
    public void TC_02_Valid_Email() throws IOException {
        homePage.enterEmailID(accessEmail);
        homePage.clickSubmitBtn();
        verifyTrue(homePage.getAccessTableElement().isDisplayed());
        homePage.writeAccessDataToFile();
    }

    @AfterClass
    public void afterClass(){
        closeBrowserAndDriver();
    }


    private int randomNumber() {
        Random rand = new Random();
        return rand.nextInt(99);
    }

}
