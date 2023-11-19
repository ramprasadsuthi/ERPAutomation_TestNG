package testPackage;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import dataManager.ExtentManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ET_1 {
    private ExtentReports extentReports = ExtentManager.getInstance();
    private ExtentTest test;

    @BeforeMethod
    public void setUp() {
        //test = extentReports.createTest("yourTestMethod");
        // Your setup code here
    }

    @Test
    public void TestCase_1() {
        // Your test logic here
    	test = extentReports.createTest("TestCase_1");
        test.log(Status.INFO, "Your test case-1");
        // More test steps and assertions
    }
    
    @Test
    public void TestCase_2() {
        // Your test logic here
    	test = extentReports.createTest("TestCase_2");
        test.log(Status.INFO, "Your test case-2");
        // More test steps and assertions
    }

    @AfterMethod
    public void tearDown() {
        // Your teardown code here
    	extentReports.flush();
    }
}
