package testPackage;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExtentReportsExample {
    private WebDriver driver;
    private ExtentReports extentReports;
    private ExtentTest test;

    @BeforeMethod
    public void setUp() {
        // Set up ExtentReports
    	String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/ExtentReport_" + timeStamp + ".html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);

        // Initialize WebDriver
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void sampleTest() {
        test = extentReports.createTest("Sample Test");
        driver.get("http://webapp.qedgetech.com/login.php");
        // Your test logic here
        // Log test steps and other information
        test.log(Status.INFO, "Navigated to ERP Application");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            // If the test fails, log it as FAIL in the Extent Report
            test.log(Status.FAIL, "Test Case Failed: " + result.getName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            // If the test passes, log it as PASS in the Extent Report
            test.log(Status.PASS, "Test Case Passed: " + result.getName());
        } else {
            // If the test is skipped, log it as SKIP in the Extent Report
            test.log(Status.SKIP, "Test Case Skipped: " + result.getName());
        }

        // Close the browser and flush the Extent Report after each test method
        driver.quit();
        extentReports.flush();
    }
}
