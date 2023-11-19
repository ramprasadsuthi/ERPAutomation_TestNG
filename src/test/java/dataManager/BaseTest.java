package dataManager;

import org.testng.annotations.BeforeClass;
import com.aventstack.extentreports.ExtentReports;

public class BaseTest {

    protected ExtentReports extentReports;

    @BeforeClass
    public void setUp() {
    	extentReports = ExtentManager.getInstance();
    }
}

