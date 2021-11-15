package intefaces.pageUIs;

public class HomePageUI {
    public static final String ACCESS_DATA_GENERATION_LINK = " //a[text()='here']";
    public static final String EMAIL_TEXTBOX = "//input[@name='emailid']";
    public static final String SUBMIT_BUTON = "//input[@value='Submit']";
    public static final String EMAIL_ERROR_MSG = "//label[@id='message9']";
    public static final String ACCESS_INFO_TABLE = "//table";
    public static final String ACCESS_USER_ID = "//table//td[text()='User ID :']//following-sibling::td";
    public static final String ACCESS_USER_PASSWORD = "//table//td[text()='Password :']//following-sibling::td";
    public static final String WELCOME_MANAGE_USER = "//marquee";

    // Dynamic xpath
    public static final String DYNAMIC_HOME_PAGE_INPUT_ELEMENT_NAME = "//input[@name='%s']";
}

