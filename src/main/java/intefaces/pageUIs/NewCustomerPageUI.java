package intefaces.pageUIs;

public class NewCustomerPageUI {
    public static final String INPUT_ELEMENT_BY_NAME = "//*[@name='%s']";
    public static final String INPUT_ERROR_MESSAGE_TEXT = "//*[@name='%s']//following-sibling::label";
    public static final String CUSTOMER_ID = "//td[text()='Customer ID']/following-sibling::td";
    public static final String FILED_NAME_BY_ATTRIBUTE_NAME = "//*[@name='%s']//parent::td//preceding-sibling::td";
}
