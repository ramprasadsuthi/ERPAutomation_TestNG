package automationScripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import commonMethods.BaseMethods;
import dataManager.BaseTest;
import dataManager.ExcelDataReader;
import java.io.IOException;

public class LoginModule extends BaseTest{
	private WebDriver driver;
    private LoginPage lp;
    private HomePage hp;
    private BaseMethods base;
 //   private ExtentReports extentReports;
//    private ExtentReports extentReports = ExtentManager.getInstance();
    private ExtentTest test;

    @DataProvider(name = "excelData")
    public Object[][] testData() throws IOException {
        String filePath = "TestData.xlsx";
        String sheetName = "loginDetails";
        ExcelDataReader reader = new ExcelDataReader();
        return reader.readExcelData(filePath, sheetName);
    }
    
    @BeforeMethod
    public void setup() {
       	System.setProperty("web.driver.chrome", "chromedriver.exe");
		WebDriver driver = new ChromeDriver(); 
		driver.get("http://webapp.qedgetech.com/login.php"); 
		driver.manage().window().maximize();
		lp = new LoginPage(driver);
		hp = new HomePage(driver);
		base = new BaseMethods(driver);
    }
    
    @AfterMethod
    public void closeApp(ITestResult result) {
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
       	base.closeApp();
       	extentReports.flush();
    }
    
    @Test(dataProvider = "excelData")
    public void TC1_ValidLogin(String username, String password, String url) {
    	try {
    		test = extentReports.createTest("TC1_ValidLogin");
    		// Assign the test to a category representing the TestNG class
            test.assignCategory("Login Page");
         	base.loginApp(username, password);
        	Assert.assertEquals(hp.getPageCaption(), "Dashboard");
        	test.log(Status.INFO, "Login to ERP Application is success");
    	} catch (AssertionError e) {
    		test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
    		test.log(Status.FAIL, "Exception details: " + e);
    		// Re-throw the exception to mark the test as failed in TestNG
    		throw e;
    	}
    	
     }
      
    @Test(dataProvider = "excelData")
    public void TC2_Logout(String username, String password, String url) {
    	try {
    		test = extentReports.createTest("TC2_Logout");
    		test.assignCategory("Login Page");
         	base.loginApp(username, password);
        	Assert.assertEquals(hp.getPageCaption(), "Dashboard");
        	test.log(Status.INFO, "Login to ERP Application is success");
        	lp.clickOnLogoutButton();
        	test.log(Status.INFO, "Click on Logout button");
        	Assert.assertTrue((lp.loginButton.isEnabled()));
        	test.log(Status.INFO, "Logout is success - Test Pass");
    	} catch (AssertionError e) {
    		test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
    		test.log(Status.FAIL, "Exception details: " + e);
    		// Re-throw the exception to mark the test as failed in TestNG
    		throw e;
    	}
    	
     }
    
    @Test(dataProvider = "excelData")
    public void TC3_InvalidLogin(String username, String password, String url) {
    	try {
    		test = extentReports.createTest("TC3_InvalidLogin");
    		test.assignCategory("Login Page");
         	lp.enterUsername("admin123");
         	lp.enterPassword("master123");
         	lp.clickLoginButton();
         	Assert.assertTrue((lp.getErrorMesg().contains("Incorrect")));
        	test.log(Status.INFO, "Invalid Login test is success");
       	} catch (AssertionError e) {
    		test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
    		test.log(Status.FAIL, "Exception details: " + e);
    		// Re-throw the exception to mark the test as failed in TestNG
    		throw e;
    	}
    	
     }
    
    @Test(dataProvider = "excelData")
    public void TC4_RESET(String username, String password, String url) {
    	try {
    		test = extentReports.createTest("TC4_RESET");
    		test.assignCategory("Login Page");
         	lp.enterUsername(username);
         	lp.enterPassword(password);
         	lp.clickOnResetButton();
         	Assert.assertTrue(lp.usernameInput.getAttribute("value").isEmpty() && lp.passwordInput.getAttribute("value").isEmpty());
        	test.log(Status.INFO, "RESET test is success");
       	} catch (AssertionError e) {
    		test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
    		test.log(Status.FAIL, "Exception details: " + e);
    		// Re-throw the exception to mark the test as failed in TestNG
    		throw e;
    	}
    	
     }
}
