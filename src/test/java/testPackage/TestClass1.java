package testPackage;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dataManager.ExcelDataReader;

import java.io.IOException;

public class TestClass1 {

    @DataProvider(name = "excelData")
    public Object[][] testData() throws IOException {
        String filePath = "TestData.xlsx";
        String sheetName = "loginDetails";
        ExcelDataReader reader = new ExcelDataReader();
        return reader.readExcelData(filePath, sheetName);
    }

    @Test(dataProvider = "excelData")
    public void TestCase_1(String username, String password, String url) {
        // Your test logic using username and password from Excel
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("URL : " + url);
        // Perform your test actions using username and password
    }
}
