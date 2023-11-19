package testPackage;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dataManager.ExcelDataReader;

import java.io.IOException;

public class TestClass2 {

    @DataProvider(name = "excelData")
    public Object[][] testData() throws IOException {
        String filePath = "TestData.xlsx";
        String sheetName = "dataSheet";
        ExcelDataReader reader = new ExcelDataReader();
        return reader.readExcelData(filePath, sheetName);
    }

    @Test(dataProvider = "excelData")
    public void TestCase_2(String Category, String Supplier, String StockName, String UOM, String PP, String SP, String Notes) {
        // Your test logic gets the data from Excel
        System.out.println("Category		: " + Category);
        System.out.println("Supplier		: " + Supplier);
        System.out.println("Stock Name		: " + StockName);
        System.out.println("Unit of Measurement	: " + UOM);
        System.out.println("Purchase Price		: " + PP);
        System.out.println("Selling Price		: " + SP);
        System.out.println("Notes: " + Notes);
        System.out.println("--------------------------------");
        
    }
}
