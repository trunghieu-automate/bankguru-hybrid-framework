package actions.common;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;
import org.testng.Reporter;
import utilities.logger.Log;

import java.io.IOException;
import java.util.Random;

public class BaseTest {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }
    // Define enum BROWSER
    private enum BROWSER{
        CHROME, FIREFOX, EDGE, IE, H_CHROME, H_FIREFOX, EDGE_LEGACY, COCCOC, OPERA
    }

    @Step("Init {0} browser")
    public WebDriver getDriver(String browserName){
        BROWSER browser = BROWSER.valueOf(browserName.toUpperCase());
        if(browser == BROWSER.CHROME){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            Log.info("Opening Chrome browser!");
        } else if (browser == BROWSER.FIREFOX){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            Log.info("Opening Firefox browser!");
        } else if (browser == BROWSER.EDGE){
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            Log.info("Opening Microsoft Edge browser!");
        } else if (browser == BROWSER.IE){
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
            Log.info("Opening Internet Explorer browser!");
        } else if (browser == BROWSER.H_CHROME){
            WebDriverManager.iedriver().setup();
            ChromeOptions option = new ChromeOptions();
            option.setHeadless(true);
            driver = new ChromeDriver(option);
            Log.info("Opening headless chrome browser!");
        } else if (browser == BROWSER.H_FIREFOX){
            WebDriverManager.iedriver().setup();
            FirefoxOptions option = new FirefoxOptions();
            option.setHeadless(true);
            driver = new FirefoxDriver(option);
            Log.info("Opening headless firefox browser!");
        } else if (browser == BROWSER.OPERA){
            WebDriverManager.operadriver().setup();
            driver = new OperaDriver();
            Log.info("Opening opera browser!");
        } else if (browser == BROWSER.EDGE_LEGACY){
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            Log.info("Opening Edge legacy browser!");
        } else if (browser == BROWSER.COCCOC){
            WebDriverManager.chromedriver().driverVersion("driver version = coccoc version - 6").setup();
            ChromeOptions option = new ChromeOptions();
            option.setBinary("coccoc's path");
            driver = new EdgeDriver();
            Log.info("Opening coc coc browser!");
        } else {
            throw new RuntimeException("Driver's fault, please check browser name");
        }
        return driver;
    }

    @Step("Init {0} browser and get {1}")
    public WebDriver getDriver(String browserName, String url){
        BROWSER browser = BROWSER.valueOf(browserName.toUpperCase());
        if(browser == BROWSER.CHROME){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            Log.info("Opening Chrome browser!");
        } else if (browser == BROWSER.FIREFOX){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            Log.info("Opening Firefox browser!");
        } else if (browser == BROWSER.EDGE){
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            Log.info("Opening Microsoft Edge browser!");
        } else if (browser == BROWSER.IE){
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
            Log.info("Opening Internet Explorer browser!");
        } else if (browser == BROWSER.H_CHROME){
            WebDriverManager.iedriver().setup();
            ChromeOptions option = new ChromeOptions();
            option.setHeadless(true);
            driver = new ChromeDriver(option);
            Log.info("Opening headless chrome browser!");
        } else if (browser == BROWSER.H_FIREFOX){
            WebDriverManager.iedriver().setup();
            FirefoxOptions option = new FirefoxOptions();
            option.setHeadless(true);
            driver = new FirefoxDriver(option);
            Log.info("Opening headless firefox browser!");
        } else if (browser == BROWSER.OPERA){
            WebDriverManager.operadriver().setup();
            driver = new OperaDriver();
            Log.info("Opening opera browser!");
        } else if (browser == BROWSER.EDGE_LEGACY){
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            Log.info("Opening Edge legacy browser!");
        } else if (browser == BROWSER.COCCOC){
            WebDriverManager.chromedriver().driverVersion("driver version = coccoc version - 6").setup();
            ChromeOptions option = new ChromeOptions();
            option.setBinary("coccoc's path");
            driver = new EdgeDriver();
            Log.info("Opening coc coc browser!");
        } else {
            throw new RuntimeException("Driver's fault, please check browser name");
        }
        driver.get(url);
        return driver;
    }

    private boolean checkTrue(boolean condition) {
        boolean pass = true;
        try {
            if (condition) {
                Log.info(" -------------------------- PASSED -------------------------- ");
            } else {
                Log.info(" -------------------------- FAILED -------------------------- ");
            }
            Assert.assertTrue(condition);
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }

        return pass;
    }

    @Step("Verify true")
    protected boolean verifyTrue(boolean condition) {
        return checkTrue(condition);
    }

    private boolean checkFailed(boolean condition) {
        boolean pass = true;
        try {
            if (condition) {
                Log.info(" -------------------------- PASSED -------------------------- ");
            } else {
                Log.info(" -------------------------- FAILED -------------------------- ");
            }
            Assert.assertFalse(condition);
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    @Step("Verify false")
    protected boolean verifyFalse(boolean condition) {
        return checkFailed(condition);
    }

    private boolean checkEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
            Log.info(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            pass = false;
            Log.info(" -------------------------- FAILED -------------------------- ");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    @Step("Verify equals")
    protected boolean verifyEquals(Object actual, Object expected) {
        return checkEquals(actual, expected);
    }

    protected void closeBrowserAndDriver() {
        String cmd = "";
        try {
            String osName = System.getProperty("os.name").toLowerCase();
            //Log.info("OS name = " + osName);

            String driverInstanceName = driver.toString().toLowerCase();
            //Log.info("Driver instance name = " + osName);

            if (driverInstanceName.contains("chrome")) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
                } else {
                    cmd = "pkill chromedriver";
                }
            } else if (driverInstanceName.contains("internetexplorer")) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
                }
            } else if (driverInstanceName.contains("firefox")) {
                if (osName.contains("windows")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
                } else {
                    cmd = "pkill geckodriver";
                }
            } else if (driverInstanceName.contains("edge")) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
                } else {
                    cmd = "pkill msedgedriver";
                }
            } else if (driverInstanceName.contains("opera")) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
                } else {
                    cmd = "pkill operadriver";
                }
            } else if (driverInstanceName.contains("safari")) {
                if (osName.contains("mac")) {
                    cmd = "pkill safaridriver";
                }
            }

            if (driver != null) {
                driver.manage().deleteAllCookies();
                driver.quit();
            }
        } catch (Exception e) {
            Log.info(e.getMessage());
        } finally {
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getRandomNumber(){
        return String.valueOf(new Random().nextInt(99));
    }
}
