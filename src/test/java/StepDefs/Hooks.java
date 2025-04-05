package StepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import static Utilities.DriverSetup.closeBrowser;
import static Utilities.DriverSetup.openABrowser;

public class Hooks {
    public static String browserName = System.getProperty("browser", "Chrome");
    public static SoftAssert softAssert;

    @Before
    public void beforeScenario() throws InterruptedException {
        openABrowser(browserName);
        softAssert = new SoftAssert();
    }


    @After
    public void afterScenario() throws InterruptedException {
        try {
            softAssert.assertAll(); // Ensure all soft assertions are checked
        } catch (AssertionError e) {
            System.out.println("Soft assertions failed: " + e.getMessage());
            Assert.fail(e.getMessage()); // This makes Allure mark the test as failed
        }
        finally {
            closeBrowser();
        }
    }
}
