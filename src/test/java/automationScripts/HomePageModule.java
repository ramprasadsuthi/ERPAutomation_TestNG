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

public class HomePageModule extends BaseTest {
	private WebDriver driver;
    private LoginPage lp;
    private HomePage hp;
    private BaseMethods base;
 //   private ExtentReports extentReports;
  //  private ExtentReports extentReports = ExtentManager.getInstance();
    private ExtentTest test;
    private Object[][] testData;

    @DataProvider(name = "excelData")
    public Object[][] getTestData() throws IOException {
        String filePath = "TestData.xlsx";
        String sheetName = "loginDetails";
        ExcelDataReader reader = new ExcelDataReader();
        return reader.readExcelData(filePath, sheetName);
    }
    
    @BeforeMethod
    public void setup() throws IOException {
       	System.setProperty("web.driver.chrome", "chromedriver.exe");
		WebDriver driver = new ChromeDriver(); 
		driver.get("http://webapp.qedgetech.com/login.php"); 
		driver.manage().window().maximize();
		lp = new LoginPage(driver);
		hp = new HomePage(driver);
		base = new BaseMethods(driver);
		testData = getTestData();
		// Access testData elements if needed
		String username = null, password = null;
        for (Object[] data : testData) {
            username = (String) data[0];
            password = (String) data[1];
        }
        base.loginApp(username, password);
		
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
    
    @Test
    public void TC5_AccessToStockItemsPage() {
    	try {
    		test = extentReports.createTest("TC5_AccessToStockItemsPage");
    		// Assign the test to a category representing the TestNG class
            test.assignCategory("Home Page");
    		hp.clickOnStockItemsMenu();
          	Assert.assertEquals(hp.getPageCaption(), "Stock Items");
        	test.log(Status.INFO, "Access to stock Items page is success");
    	} catch (AssertionError e) {
    		test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
    		test.log(Status.FAIL, "Exception details: " + e);
    		// Re-throw the exception to mark the test as failed in TestNG
    		throw e;
    	}
    	
     }
    
    @Test
    public void TC6_AccessToCustomersPage() {
    	try {
    		test = extentReports.createTest("TC6_AccessToCustomersPage");
    		// Assign the test to a category representing the TestNG class
            test.assignCategory("Home Page");
    		hp.clickOnCustomersMenu();
          	Assert.assertEquals(hp.getPageCaption(), "Customers");
        	test.log(Status.INFO, "Access to Customers page is success - Test Pass");
    	} catch (AssertionError e) {
    		test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
    		test.log(Status.FAIL, "Exception details: " + e);
    		// Re-throw the exception to mark the test as failed in TestNG
    		throw e;
    	}
    	
     }
    
    @Test
    public void TC7_AccessToStockCategoriesPage() {
    	try {
    		test = extentReports.createTest("TC7_AccessToStockCategoriesPage");
    		// Assign the test to a category representing the TestNG class
            test.assignCategory("Home Page");
    		hp.clickOnStockCategoriesMenu();
          	Assert.assertEquals(hp.getPageCaption(), "Stock Categories");
        	test.log(Status.INFO, "Access to Stock Categories page is success - Test Pass");
    	} catch (AssertionError e) {
    		test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
    		test.log(Status.FAIL, "Exception details: " + e);
    		// Re-throw the exception to mark the test as failed in TestNG
    		throw e;
    	}
    	
     }
    
    @Test
    public void TC8_AccessToUOMPage() {
    	try {
    		test = extentReports.createTest("TC8_AccessToUOMPage");
    		// Assign the test to a category representing the TestNG class
            test.assignCategory("Home Page");
    		hp.clickOnUOMMenu();
          	Assert.assertEquals(hp.getPageCaption(), "Unit of Measurement");
        	test.log(Status.INFO, "Access to Unit of Measurement page is success - Test Pass");
    	} catch (AssertionError e) {
    		test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
    		test.log(Status.FAIL, "Exception details: " + e);
    		// Re-throw the exception to mark the test as failed in TestNG
    		throw e;
    	}
    	
     }
    
    @Test
    public void TC9_AccessToSuppliersPage() {
    	try {
    		test = extentReports.createTest("TC9_AccessToSuppliersPage");
    		// Assign the test to a category representing the TestNG class
            test.assignCategory("Home Page");
    		hp.clickOnSuppliersMenu();
          	Assert.assertEquals(hp.getPageCaption(), "Suppliers");
        	test.log(Status.INFO, "Access to Suppliers page is success - Test Pass");
    	} catch (AssertionError e) {
    		test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
    		test.log(Status.FAIL, "Exception details: " + e);
    		// Re-throw the exception to mark the test as failed in TestNG
    		throw e;
    	}
      }
    
    @Test
    public void TC10_AccessToPurchasesPage() {
    	try {
    		test = extentReports.createTest("TC10_AccessToPurchasesPage");
    		// Assign the test to a category representing the TestNG class
            test.assignCategory("Home Page");
    		hp.clickOnPurchasesMenu();
          	Assert.assertEquals(hp.getPageCaption(), "Purchases");
        	test.log(Status.INFO, "Access to Purchases page is success - Test Pass");
    	} catch (AssertionError e) {
    		test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
    		test.log(Status.FAIL, "Exception details: " + e);
    		// Re-throw the exception to mark the test as failed in TestNG
    		throw e;
    	}
      }
    
    @Test
    public void TC11_AccessToSalesPage() {
    	try {
    		test = extentReports.createTest("TC11_AccessToSalesPage");
    		// Assign the test to a category representing the TestNG class
            test.assignCategory("Home Page");
    		hp.clickOnSalesMenu();
          	Assert.assertEquals(hp.getPageCaption(), "Sales");
        	test.log(Status.INFO, "Access to Sales page is success - Test Pass");
    	} catch (AssertionError e) {
    		test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
    		test.log(Status.FAIL, "Exception details: " + e);
    		// Re-throw the exception to mark the test as failed in TestNG
    		throw e;
    	}
      }
 
}
