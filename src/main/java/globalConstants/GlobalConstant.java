package globalConstants;

import java.io.File;

public class GlobalConstant {
    // path
    public static final String sysPath = System.getProperty("user.dir");
    public static final String mainResourcePath = sysPath + File.separator+ "src" +File.separator + "main" +File.separator +"resources" + File.separator;

    // url
    public static final String homePageUrl = "http://demo.guru99.com/v4/";

    // timeout
    public static final long LONG_TIME_OUT = 20;
    public static final long SHORT_TIME_OUT = 10;

    public static void main(String[] args) throws Exception {
        System.out.println(mainResourcePath);
    }
}
