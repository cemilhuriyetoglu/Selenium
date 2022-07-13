package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase extends Listeners {


    @BeforeMethod
    public void setup() {

        BrowserUtils.openBrowser();

    }

    @AfterMethod
    public void tearDown() {
        BrowserUtils.closeBrowser();
    }

}