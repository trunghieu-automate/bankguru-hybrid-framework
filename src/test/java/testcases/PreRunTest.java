package testcases;

import actions.common.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.logger.Log;

public class PreRunTest extends BaseTest {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = getDriver("chrome");
    }


    @Test
    public void BaseTestRun(){
        Log.info("Step 01 - check driver & browser initialization");
        driver.get("https://www.google.com/");

        Log.info("Step 02 - Check title");
        verifyTrue(driver.getTitle().contains("Google"));

        Log.info("Step 03 - Verify Google img is displayed");
        verifyFalse(driver.findElement(By.xpath("//img[@alt='Google']")).isDisplayed());
    }

    @AfterClass (alwaysRun = true)
    public void afterClass(){
        Log.info("Finish!");
        closeBrowserAndDriver();
    }
}
