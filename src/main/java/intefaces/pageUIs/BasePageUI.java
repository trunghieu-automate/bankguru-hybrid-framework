package intefaces.pageUIs;

public class BasePageUI {
    public static final String DYNAMIC_RIGHT_NAV_BAR_LINK = "//ul[@class='menusubnav']//a[text()='%s'] ";
    public static final String DYNAMIC_ELEMENT_BY_NAME = "//*[@name='%s']";
    public static final String DYNAMIC_ERROR_MESSAGE_TEXT_BY_NAME = "//*[@name='%s']//following-sibling::label";
    public static final String DYNAMIC_FIELD_VALUE_BY_FILED_NAME = "//td[text()='%s']//following-sibling::td";

    public static final String TABLE_HEADING = "//p[@class='heading3']";
}
