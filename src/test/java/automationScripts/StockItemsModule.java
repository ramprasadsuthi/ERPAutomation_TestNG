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
import ObjectRepository.StockItemsPage;
import commonMethods.BaseMethods;
import dataManager.BaseTest;
import dataManager.ExcelDataReader;

import java.io.IOException;

public class StockItemsModule extends BaseTest {
	private WebDriver driver;
    private LoginPage lp;
    private HomePage hp;
    private StockItemsPage sp;
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
    
    @DataProvider(name = "getStockItemData")
    public Object[][] getStockData() throws IOException {
        String filePath = "TestData.xlsx";
        String sheetName = "dataSheet";
        ExcelDataReader reader = new ExcelDataReader();
        return reader.readExcelData(filePath, sheetName);
    }
    
    @DataProvider(name = "getStockCategories")
    public Object[][] getStockCategoriesData() throws IOException {
        String filePath = "TestData.xlsx";
        String sheetName = "Categories";
        ExcelDataReader reader = new ExcelDataReader();
        return reader.readExcelData(filePath, sheetName);
    }
    
    @DataProvider(name = "getUOMData")
    public Object[][] getUOMData() throws IOException {
        String filePath = "TestData.xlsx";
        String sheetName = "UOM";
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
		sp = new StockItemsPage(driver);
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
    
    @Test(dataProvider = "getStockItemData")
    public void TC12_CreateNewStockItem(String categoryName, String supplierName, String stockName, String uom, String purchasePrice, String sellingPrice, String stockNotes) {
    	try {
    		test = extentReports.createTest("TC12_CreateNewStockItem");
    		// Assign the test to a category representing the TestNG class
            test.assignCategory("Stock Items Module");
    		hp.clickOnStockItemsMenu();
          	Assert.assertEquals(hp.getPageCaption(), "Stock Items");
        	test.log(Status.INFO, "Stock Items page is displayed");
        	sp.clickOnStockItemAddButton();
        	sp.selectCategory(categoryName);
        	test.log(Status.INFO, "Category Name selected as " + categoryName);
        	sp.selectSupplier(supplierName);
        	test.log(Status.INFO, "Supplier Name selected as " + supplierName);
        	sp.enterStockname(stockName);
        	test.log(Status.INFO, "Stock Name entered as " + stockName);
        	sp.selectUOM(uom);
        	test.log(Status.INFO, "Unit of Measurement Name selected as " + uom);
        	sp.enterPurchasingPrice(purchasePrice);
        	test.log(Status.INFO, "Purchase price entered as " + purchasePrice);
        	sp.enterSellingPrice(sellingPrice);
        	test.log(Status.INFO, "Selling price entered as " + sellingPrice);
        	sp.enterStockNotes(stockNotes);
        	test.log(Status.INFO, "Stock Notes entered as " + stockNotes);
        	sp.clickSubmitButton();
        	sp.clickConfirmButton();
        	test.log(Status.INFO, "Clicked on confirm button");
    	} catch (AssertionError e) {
    		test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
    		test.log(Status.FAIL, "Exception details: " + e);
    		// Re-throw the exception to mark the test as failed in TestNG
    		throw e;
    	}
    	
     }
    
    @Test(dataProvider = "getStockCategories")
    public void TC13_CreateNewStockCategory(String categoryName) {
    	try {
    		test = extentReports.createTest("TC13_CreateNewStockCategory");
    		// Assign the test to a category representing the TestNG class
            test.assignCategory("Stock Items Module");
            hp.clickOnStockCategoriesMenu();
    		Assert.assertEquals(hp.getPageCaption(), "Stock Categories");
        	test.log(Status.INFO, "Stock Categories page is displayed");
        	sp.clickOnStockItemAddButton();
          	sp.enterStockCategoryName(categoryName);
        	test.log(Status.INFO, "Category Name entered as " + categoryName);
           	sp.clickSubmitButton();
        	sp.clickConfirmButton();
        	test.log(Status.INFO, "Clicked on confirm button");
    	} catch (AssertionError e) {
    		test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
    		test.log(Status.FAIL, "Exception details: " + e);
    		// Re-throw the exception to mark the test as failed in TestNG
    		throw e;
    	}
    	
     }
    
    @Test(dataProvider = "getUOMData")
    public void TC14_CreateNewUOM(String uom_ID, String uom_Desc) {
    	try {
    		test = extentReports.createTest("TC14_CreateNewUOM");
    		// Assign the test to a category representing the TestNG class
            test.assignCategory("Stock Items Module");
            hp.clickOnUOMMenu();
          	Assert.assertEquals(hp.getPageCaption(), "Unit of Measurement");
        	test.log(Status.INFO, "Unit of Measurement page is displayed");
        	sp.clickOnStockItemAddButton();
          	sp.enterUOMId(uom_ID);
          	sp.enterUOMDesc(uom_Desc);
        	test.log(Status.INFO, "UOM Details entered as " + uom_Desc);
           	sp.clickSubmitButton();
        	sp.clickConfirmButton();
        	test.log(Status.INFO, "Clicked on confirm button");
    	} catch (AssertionError e) {
    		test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
    		test.log(Status.FAIL, "Exception details: " + e);
    		// Re-throw the exception to mark the test as failed in TestNG
    		throw e;
    	}
    	
     }
    
    @Test
    public void TC15_StockItemSimpleSearch() {
    	try {
    		test = extentReports.createTest("TC15_StockItemSimpleSearch");
    		// Assign the test to a category representing the TestNG class
            test.assignCategory("Stock Items Module");
            hp.clickOnStockItemsMenu();
            sp.clickOnSimpleSearchIcon();
            sp.enterSimpleSearchInput("Samsung");
            sp.clickOnSearchButton();
          	Assert.assertTrue(sp.getSimpleSearchResult().contains("Samsung"));
        	test.log(Status.INFO, "Search successful Stock Item " + sp.getSimpleSearchResult() + " found..");
        } catch (AssertionError e) {
    		test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
    		test.log(Status.FAIL, "Exception details: " + e);
    		// Re-throw the exception to mark the test as failed in TestNG
    		throw e;
    	}
    	
     }
    
    @Test
    public void TC16_StockItemAdvancedSearch() {
    	try {
    		test = extentReports.createTest("TC16_StockItemAdvancedSearch");
    		// Assign the test to a category representing the TestNG class
            test.assignCategory("Stock Items Module");
            hp.clickOnStockItemsMenu();
            sp.clickOnAdvancedSearchIcon();
            sp.clickOnPurchasepriceDropDown();
            sp.enterPurchaseprice("80000");
            sp.clickOnAdvanceSearchButton();
           	Assert.assertFalse(sp.isTableEmpty());
        	test.log(Status.INFO, "Advanced Search successful Stock Items found..");
        } catch (AssertionError e) {
    		test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
    		test.log(Status.FAIL, "Exception details: " + e);
    		// Re-throw the exception to mark the test as failed in TestNG
    		throw e;
    	}
       }
    
    @Test
    public void TC17_UpdateTheStockItem() {
    	try {
    		test = extentReports.createTest("TC17_UpdateTheStockItem");
    		// Assign the test to a category representing the TestNG class
            test.assignCategory("Stock Items Module");
            hp.clickOnStockItemsMenu();
            sp.clickOnFirstCheckBox();
            sp.selectActionsDropdown();
            sp.selectUpdateRecords();
            sp.selectQuantityCheckbox();
            sp.enterQuantity("20");
            sp.clickOnUpdateButton();
            sp.clickConfirmButton();
            sp.clickConfirmButton();
            Assert.assertEquals(sp.getQuantity(),"20");
        	test.log(Status.INFO, "Updating Stock Item is sucessful..");
        } catch (AssertionError e) {
    		test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
    		test.log(Status.FAIL, "Exception details: " + e);
    		// Re-throw the exception to mark the test as failed in TestNG
    		throw e;
    	}
       }
    
    @Test
    public void TC18_DeleteTheStockItem() {
    	try {
    		test = extentReports.createTest("TC18_DeleteTheStockItem");
    		// Assign the test to a category representing the TestNG class
            test.assignCategory("Stock Items Module");
            hp.clickOnStockItemsMenu();
            sp.clickOnFirstCheckBox();
            sp.selectActionsDropdown();
            sp.selectDeleteRecords();
            test.log(Status.INFO, "Selecting the Stock Item to Delete..");
            sp.clickConfirmButton();
            Assert.assertEquals(sp.getAlertSuccessMesg(),"Delete succeeded");
        	test.log(Status.INFO, "Deleting Stock Item is sucessful..");
        } catch (AssertionError e) {
    		test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
    		test.log(Status.FAIL, "Exception details: " + e);
    		// Re-throw the exception to mark the test as failed in TestNG
    		throw e;
    	}
       }
    
    @Test
    public void TC19_DeleteMultipleStockItem() {
    	try {
    		test = extentReports.createTest("TC19_DeleteMultipleStockItem");
    		// Assign the test to a category representing the TestNG class
            test.assignCategory("Stock Items Module");
            hp.clickOnStockItemsMenu();
            sp.clickOnFirstCheckBox();
            sp.clickOnSecondCheckBox();
            sp.selectActionsDropdown();
            sp.selectDeleteRecords();
            test.log(Status.INFO, "Selecting the Stock Items to Delete..");
            sp.clickConfirmButton();
            Assert.assertEquals(sp.getAlertSuccessMesg(),"Delete succeeded");
        	test.log(Status.INFO, "Deleting Stock Items is sucessful..");
        } catch (AssertionError e) {
    		test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
    		test.log(Status.FAIL, "Exception details: " + e);
    		// Re-throw the exception to mark the test as failed in TestNG
    		throw e;
    	}
       }
    
    @Test
    public void TC20_CreateSearchFilter() {
    	try {
    		test = extentReports.createTest("TC20_CreateSearchFilter");
    		// Assign the test to a category representing the TestNG class
            test.assignCategory("Stock Items Module");
            hp.clickOnStockItemsMenu();
            sp.clickOnSimpleSearchIcon();
            sp.enterSimpleSearchInput("Samsung");
            sp.clickOnSearchButton();
            sp.selectCreateFilterDropdown();
            sp.selectSaveFilter();
            sp.inputFilterName("Filter-1");
            sp.clickConfirmButton();
            sp.selectCreateFilterDropdown();
          	Assert.assertTrue(sp.getFilterName().contains("Filter-1"));
        	test.log(Status.INFO, "Creating a Search filter is successful..");
        } catch (AssertionError e) {
    		test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
    		test.log(Status.FAIL, "Exception details: " + e);
    		// Re-throw the exception to mark the test as failed in TestNG
    		throw e;
    	}
      }
    
    @Test
    public void TC21_DeleteSearchFilter() {
    	try {
    		test = extentReports.createTest("TC21_DeleteSearchFilter");
    		// Assign the test to a category representing the TestNG class
            test.assignCategory("Stock Items Module");
            hp.clickOnStockItemsMenu();
            sp.selectCreateFilterDropdown();
            sp.selectDeleteFilter();
            sp.selectFilterNameToDelete();
           // sp.clickConfirmButton();
            sp.selectCreateFilterDropdown();
          	Assert.assertFalse(sp.IsSaveFilterClickable());
        	test.log(Status.INFO, "Creating a Search filter is successful..");
        } catch (AssertionError e) {
    		test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
    		test.log(Status.FAIL, "Exception details: " + e);
    		// Re-throw the exception to mark the test as failed in TestNG
    		throw e;
    	}
      }
    
    @Test
    public void TC22_ExportStockItemsInAWordFormat() {
    	try {
    		test = extentReports.createTest("TC22_ExportStockItemsInAWordFormat");
    		// Assign the test to a category representing the TestNG class
            test.assignCategory("Stock Items Module");
            hp.clickOnStockItemsMenu();
            sp.clickExportDropdown();
            sp.selectWordOption();
           	Assert.assertTrue(sp.driver.getCurrentUrl().contains("word"));
        	test.log(Status.INFO, "Export StockItems in word format is success..");
        } catch (AssertionError e) {
    		test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
    		test.log(Status.FAIL, "Exception details: " + e);
    		// Re-throw the exception to mark the test as failed in TestNG
    		throw e;
    	}
      }
    
    @Test
    public void TC23_ExportStockItemsInAExcelFormat() {
    	try {
    		test = extentReports.createTest("TC23_ExportStockItemsInAExcelFormat");
    		// Assign the test to a category representing the TestNG class
            test.assignCategory("Stock Items Module");
            hp.clickOnStockItemsMenu();
            sp.clickExportDropdown();
            sp.selectExcelOption();
           	Assert.assertTrue(sp.driver.getCurrentUrl().contains("excel"));
        	test.log(Status.INFO, "Export StockItems in excel format is success..");
        } catch (AssertionError e) {
    		test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
    		test.log(Status.FAIL, "Exception details: " + e);
    		// Re-throw the exception to mark the test as failed in TestNG
    		throw e;
    	}
      }
    
    @Test
    public void TC24_ExportStockItemsPrintOption() {
    	try {
    		test = extentReports.createTest("TC24_ExportStockItemsPrintOption");
    		// Assign the test to a category representing the TestNG class
            test.assignCategory("Stock Items Module");
            hp.clickOnStockItemsMenu();
            sp.clickExportDropdown();
            sp.selectPrintOption();
           	Assert.assertTrue(sp.driver.getCurrentUrl().contains("print"));
        	test.log(Status.INFO, "Export StockItems in print option is success..");
        } catch (AssertionError e) {
    		test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
    		test.log(Status.FAIL, "Exception details: " + e);
    		// Re-throw the exception to mark the test as failed in TestNG
    		throw e;
    	}
      }
    
    @Test
    public void TC26_ExportStockItemsInCSVFormat() throws InterruptedException {
    	try {
    		test = extentReports.createTest("TC26_ExportStockItemsInCSVFormat");
    		// Assign the test to a category representing the TestNG class
            test.assignCategory("Stock Items Module");
            hp.clickOnStockItemsMenu();
            sp.clickExportDropdown();
            sp.selectCSVOption();
            Thread.sleep(3000);
           	Assert.assertTrue(base.getLatestFile().contains("stock"));
        	test.log(Status.INFO, "Export StockItems in csv format is success..");
        } catch (AssertionError e) {
    		test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
    		test.log(Status.FAIL, "Exception details: " + e);
    		// Re-throw the exception to mark the test as failed in TestNG
    		throw e;
    	}
      }
    
    @Test
    public void TC27_ExportStockItemsInXMLFormat() {
    	try {
    		test = extentReports.createTest("TC27_ExportStockItemsInXMLFormat");
    		// Assign the test to a category representing the TestNG class
            test.assignCategory("Stock Items Module");
            hp.clickOnStockItemsMenu();
            sp.clickExportDropdown();
            sp.selectXMLOption();
           	Assert.assertTrue(sp.driver.getCurrentUrl().contains("xml"));
        	test.log(Status.INFO, "Export StockItems in xml format is success..");
        } catch (AssertionError e) {
    		test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
    		test.log(Status.FAIL, "Exception details: " + e);
    		// Re-throw the exception to mark the test as failed in TestNG
    		throw e;
    	}
      }
    
    @Test
    public void TC28_ExportStockItemsInHTMLFormat() {
    	try {
    		test = extentReports.createTest("TC28_ExportStockItemsInHTMLFormat");
    		// Assign the test to a category representing the TestNG class
            test.assignCategory("Stock Items Module");
            hp.clickOnStockItemsMenu();
            sp.clickExportDropdown();
            sp.selectHTMLOption();
           	Assert.assertTrue(sp.driver.getCurrentUrl().contains("html"));
        	test.log(Status.INFO, "Export StockItems in html format is success..");
        } catch (AssertionError e) {
    		test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
    		test.log(Status.FAIL, "Exception details: " + e);
    		// Re-throw the exception to mark the test as failed in TestNG
    		throw e;
    	}
      }
    
    @Test
    public void TC29_VerifyUserIsAbleToSetThePageSize() {
    	try {
    		test = extentReports.createTest("TC29_VerifyUserIsAbleToSetThePageSize");
    		// Assign the test to a category representing the TestNG class
            test.assignCategory("Stock Items Module");
            hp.clickOnStockItemsMenu();
            sp.clickOnPageSizeDropdown();
            Assert.assertEquals(sp.getPageSizeValue(),"5");
        	test.log(Status.INFO, "Page Size is modification is working fine..");
        } catch (AssertionError e) {
    		test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
    		test.log(Status.FAIL, "Exception details: " + e);
    		// Re-throw the exception to mark the test as failed in TestNG
    		throw e;
    	}
      }
    
    @Test
    public void TC30_VerifyUserIsAbleToNavigateToNextPage() {
    	try {
    		test = extentReports.createTest("TC30_VerifyUserIsAbleToNavigateToNextPage");
    		// Assign the test to a category representing the TestNG class
            test.assignCategory("Stock Items Module");
            hp.clickOnStockItemsMenu();
            sp.clickOnNextPage();
            Assert.assertEquals(sp.getPageNumber(),"2");
        	test.log(Status.INFO, "Page Navigation is working fine..");
        } catch (AssertionError e) {
    		test.log(Status.FAIL, "Assertion failed: " + e.getMessage());
    		test.log(Status.FAIL, "Exception details: " + e);
    		// Re-throw the exception to mark the test as failed in TestNG
    		throw e;
    	}
      }
    
    
  
} // End of TestNG Class
