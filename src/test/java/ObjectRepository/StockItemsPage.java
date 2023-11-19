package ObjectRepository;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StockItemsPage {
    public WebDriver driver;
    private WebDriverWait wait;

    // Page elements using @FindBy annotation
    @FindBy(xpath = "(//span[@data-caption='Add'])[1]")
    private WebElement stockItemAddButton;
    
    @FindBy(xpath = "//select[@id='x_Category']")
    private WebElement stockCategory;
    
    @FindBy(xpath = "//select[@id='x_Supplier_Number']")
    private WebElement supplierNumber;
    
    @FindBy(xpath = "//select[@id='x_Unit_Of_Measurement']")
    private WebElement uom;
    
    @FindBy(xpath = "//input[@id='x_Stock_Name']")
    private WebElement stockNameInput;
    
    @FindBy(xpath = "//input[@id='x_Purchasing_Price']")
    private WebElement purchasingPriceInput;
    
    @FindBy(xpath = "//input[@id='x_Selling_Price']")
    private WebElement sellingPriceInput;
    
    @FindBy(xpath = "//input[@id='x_Notes']")
    private WebElement stockNotes;
    
    @FindBy(xpath = "//button[@id='btnAction']")
    private WebElement submitButton;
    
    @FindBy(xpath = "//button[@class='ajs-button btn btn-primary']")
    private WebElement confirmButton;
    
    //Stock Catogories
    @FindBy(xpath = "//input[@id='x_Category_Name']")
    private WebElement stockCategoryInput;
    
    //UOM
    @FindBy(xpath = "//input[@id='x_UOM_ID']")
    private WebElement uom_id;
    
    @FindBy(xpath = "//input[@id='x_UOM_Description']")
    private WebElement uom_desc;
    
    //Simple Search    
    @FindBy(xpath = "//span[@data-caption='Search']")
    private WebElement simpleSearchIcon;
    
    @FindBy(xpath = "//input[@id='psearch']")
    private WebElement inputSimpleSearch;
    
    @FindBy(xpath = "//span[@id='el1_a_stock_items_Stock_Name']")
    private WebElement searchElement;
    
    @FindBy(xpath = "//button[@id='btnsubmit']")
    public WebElement searchButton;
    
    //Advanced Search
    @FindBy(xpath = "//span[@data-caption='Advanced Search']")
    private WebElement advancedSearchIcon;
    
    @FindBy(xpath = "//select[@id='z_Purchasing_Price']/option[@value='<=']")
    private WebElement purchasingPriceDropdown;
    
    @FindBy(xpath = "//input[@id='x_Purchasing_Price']")
    private WebElement purchasingPrice;
    
    @FindBy(xpath = "//button[@id='btnAction']")
    public WebElement AdvanceSearchButton;
    
    @FindBy(xpath = "//table[@id='tbl_a_stock_itemslist']")
    public WebElement resultsTable;
    
    @FindBy(xpath = "//span[@id='el1_a_stock_items_checkbox']")
    private WebElement firstCheckbox;
    
    @FindBy(xpath = "//span[@id='el2_a_stock_items_checkbox']")
    private WebElement secondCheckbox;
    
    @FindBy(xpath = "//span[@id='elh_a_stock_items_checkbox']//preceding::button[@data-original-title='Actions']")
    private WebElement selectActionsDropdown;
  
    @FindBy(xpath = "//span[@id='elh_a_stock_items_checkbox']//preceding::a[@data-caption='Update Selected Records']")
    private WebElement selectUpdateRecords;
    
    @FindBy(xpath = "//span[@id='elh_a_stock_items_checkbox']//preceding::a[@data-caption='Delete Selected Records']")
    private WebElement selectDeleteRecords;
  
    @FindBy(xpath = "//input[@id='u_Quantity']")
    private WebElement selectQuantityCheckbox;
  
    @FindBy(xpath = "//input[@id='x_Quantity']")
    private WebElement inputQuantity;
    
    @FindBy(xpath = "//span[@id='el1_a_stock_items_Quantity']")
    private WebElement getQuantity;
    
    @FindBy(xpath = "//button[text()='Update']")
    private WebElement updateButton;
    
    @FindBy(xpath = "//div[@class='alert alert-success ewSuccess']")
    private WebElement getAlertSuccessMessage;
    
    @FindBy(xpath = "//span[@data-caption='Filters']")
    private WebElement createFilterDropdown;
    
    @FindBy(xpath = "//a[@class='ewSaveFilter']")
    private WebElement saveFilter;
    
    @FindBy(xpath = "//div[@class='ajs-content']/input[@class='form-control']")
    private WebElement inputFilter;
    
    @FindBy(xpath = "(//a[text()='Filter-1'])[1]")
    private WebElement getFilterName;
    
    @FindBy(xpath = "(//a[text()='Filter-1'])[2]")
    private WebElement selectFilterName;
    
    @FindBy(xpath = "//a[@class='ewDeleteFilter']")
    private WebElement selectDeleteFilter;
    
    //Export
    @FindBy(xpath = "//span[@data-caption='Export']")
    private WebElement exportDropdown;
    
    @FindBy(xpath = "//a[@data-caption='Word']")
    private WebElement selectWordOption;
    
    @FindBy(xpath = "//a[@data-caption='Excel']")
    private WebElement selectExcelOption;
    
    @FindBy(xpath = "//a[@data-caption='HTML']")
    private WebElement selectHTMLOption;
    
    @FindBy(xpath = "//a[@data-caption='XML']")
    private WebElement selectXMLOption;
    
    @FindBy(xpath = "//a[@data-caption='CSV']")
    private WebElement selectCSVOption;
    
    @FindBy(xpath = "//a[@data-caption='Printer Friendly']")
    private WebElement selectPrintOption;
    
    @FindBy(xpath = "(//select[@name='recperpage']/option[@value='5'])[1]")
    private WebElement PageSizeDropdown;
    
    @FindBy(xpath = "(//select[@name='recperpage']/option[@selected='selected'])[1]")
    private WebElement getPageSizeValue;
    
    @FindBy(xpath = "(//a[@data-original-title='Next'])[1]")
    private WebElement clickOnNextPage;
    
    @FindBy(xpath = "(//input[@name='pageno'])[1]")
    private WebElement getPageNo;
    
   
    
    // Constructor initializes elements with PageFactory
    public StockItemsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Actions performed on the page elements
    public void clickOnStockItemAddButton() {
    	wait.until(ExpectedConditions.visibilityOf(stockItemAddButton));
    	stockItemAddButton.click();
    }
    
    public void selectCategory(String categoryName) {
    	wait.until(ExpectedConditions.visibilityOf(stockCategory));
    	Select dropdown = new Select(stockCategory);
    	dropdown.selectByVisibleText(categoryName);
    }
    
    public void selectSupplier(String supplierName) {
    	wait.until(ExpectedConditions.visibilityOf(supplierNumber));
    	Select dropdown = new Select(supplierNumber);
    	dropdown.selectByVisibleText(supplierName);
    }
    
    public void selectUOM(String UOM) {
    	wait.until(ExpectedConditions.visibilityOf(uom));
    	Select dropdown = new Select(uom);
    	dropdown.selectByVisibleText(UOM);
    }
    
    public void enterStockname(String stockName) {
    	wait.until(ExpectedConditions.visibilityOf(stockNameInput));
    	stockNameInput.sendKeys(stockName);
    }
    
    public void enterPurchasingPrice(String purchasingPrice) {
    	wait.until(ExpectedConditions.visibilityOf(purchasingPriceInput));
    	purchasingPriceInput.sendKeys(purchasingPrice);
    }
    
    public void enterSellingPrice(String sellingPrice) {
    	wait.until(ExpectedConditions.visibilityOf(sellingPriceInput));
    	sellingPriceInput.sendKeys(sellingPrice);
    }
    
    public void enterStockNotes(String notes) {
    	wait.until(ExpectedConditions.visibilityOf(stockNotes));
    	stockNotes.sendKeys(notes);
    }
    
    public void clickSubmitButton() {
    	wait.until(ExpectedConditions.elementToBeClickable(submitButton));
    	submitButton.click();
     }
    
    public void clickConfirmButton() {
    	wait.until(ExpectedConditions.elementToBeClickable(confirmButton));
    	confirmButton.click();
     }
    
    public void enterStockCategoryName(String cateogoryName) {
    	wait.until(ExpectedConditions.visibilityOf(stockCategoryInput));
    	stockCategoryInput.sendKeys(cateogoryName);
    }
    
    public void enterUOMId(String uom_ID) {
    	wait.until(ExpectedConditions.visibilityOf(uom_id));
    	uom_id.sendKeys(uom_ID);
    }
    
    public void enterUOMDesc(String uom_Desc) {
    	wait.until(ExpectedConditions.visibilityOf(uom_desc));
    	uom_desc.sendKeys(uom_Desc);
    }
    
    public void clickOnSimpleSearchIcon() {
    	wait.until(ExpectedConditions.elementToBeClickable(simpleSearchIcon));
    	simpleSearchIcon.click();
     }
    
    public void enterSimpleSearchInput(String searchText) {
    	wait.until(ExpectedConditions.visibilityOf(inputSimpleSearch));
    	inputSimpleSearch.sendKeys(searchText);
    }
    
    public String getSimpleSearchResult() {
    	wait.until(ExpectedConditions.visibilityOf(searchElement));
    	return searchElement.getText();
    }
    
    public void clickOnSearchButton() {
    	wait.until(ExpectedConditions.elementToBeClickable(searchButton));
    	searchButton.click();
    }
    
    public void clickOnAdvancedSearchIcon() {
    	wait.until(ExpectedConditions.elementToBeClickable(advancedSearchIcon));
    	advancedSearchIcon.click();
    }
    
    public void clickOnPurchasepriceDropDown() {
    	wait.until(ExpectedConditions.elementToBeClickable(purchasingPriceDropdown));
    	purchasingPriceDropdown.click();
    }
    
    public void enterPurchaseprice(String purchasePrice) {
    	wait.until(ExpectedConditions.visibilityOf(purchasingPrice));
    	purchasingPrice.sendKeys(purchasePrice);
    }
    
    public void clickOnAdvanceSearchButton() {
    	wait.until(ExpectedConditions.elementToBeClickable(AdvanceSearchButton));
    	AdvanceSearchButton.click();
    }
    
    public boolean isTableEmpty() {
    	wait.until(ExpectedConditions.visibilityOf(resultsTable));
    	// Check if the table contains any rows (records)
        boolean isTableEmpty = resultsTable.findElements(By.tagName("tr")).size() == 0;
        return isTableEmpty;
    }
    
    public void clickOnFirstCheckBox() {
    	wait.until(ExpectedConditions.elementToBeClickable(firstCheckbox));
    	firstCheckbox.click();
    }
    
    public void clickOnSecondCheckBox() {
    	wait.until(ExpectedConditions.elementToBeClickable(secondCheckbox));
    	secondCheckbox.click();
    }
    
    public void selectActionsDropdown() {
    	wait.until(ExpectedConditions.elementToBeClickable(selectActionsDropdown));
    	selectActionsDropdown.click();
    }
    
    public void selectUpdateRecords() {
    	wait.until(ExpectedConditions.elementToBeClickable(selectUpdateRecords));
    	selectUpdateRecords.click();
    }
    
    public void selectDeleteRecords() {
    	wait.until(ExpectedConditions.elementToBeClickable(selectDeleteRecords));
    	selectDeleteRecords.click();
    }
    
    public void selectQuantityCheckbox() {
    	wait.until(ExpectedConditions.elementToBeClickable(selectQuantityCheckbox));
    	selectQuantityCheckbox.click();
    }
    
    public void enterQuantity(String quantity) {
    	wait.until(ExpectedConditions.visibilityOf(inputQuantity));
    	inputQuantity.clear();
    	inputQuantity.sendKeys(quantity);
    }
    
    public void clickOnUpdateButton() {
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", updateButton);
    	wait.until(ExpectedConditions.elementToBeClickable(updateButton));
    	updateButton.click();
    }
    
    public String getQuantity() {
    	wait.until(ExpectedConditions.visibilityOf(getQuantity));
    	return getQuantity.getText();
    }
    
    public String getAlertSuccessMesg() {
    	wait.until(ExpectedConditions.visibilityOf(getAlertSuccessMessage));
    	return getAlertSuccessMessage.getText();
    }
    
    public void selectCreateFilterDropdown() {
    	wait.until(ExpectedConditions.elementToBeClickable(createFilterDropdown));
    	createFilterDropdown.click();
    }
    
    public void selectSaveFilter() {
    	wait.until(ExpectedConditions.elementToBeClickable(saveFilter));
    	saveFilter.click();
    }
    
    public boolean IsSaveFilterClickable() {
    	return saveFilter.isEnabled();
    }
    
    public void inputFilterName(String filterName) {
    	wait.until(ExpectedConditions.elementToBeClickable(inputFilter));
    	inputFilter.sendKeys(filterName);
    }
    
    public String getFilterName() {
    	wait.until(ExpectedConditions.visibilityOf(getFilterName));
    	return getFilterName.getText();
    }
    
    public void selectDeleteFilter() {
    	wait.until(ExpectedConditions.visibilityOf(selectDeleteFilter));
    	Actions actions = new Actions(driver);
		actions.moveToElement(selectDeleteFilter).build().perform();
    }
    
    public void selectFilterNameToDelete() {
    	wait.until(ExpectedConditions.elementToBeClickable(selectDeleteFilter));
    	selectDeleteFilter.click();
    }
    
    public void clickExportDropdown() {
    	wait.until(ExpectedConditions.elementToBeClickable(exportDropdown));
    	exportDropdown.click();
    }
    
    public void selectWordOption() {
    	wait.until(ExpectedConditions.elementToBeClickable(selectWordOption));
    	selectWordOption.click();
    }
    
    public void selectExcelOption() {
    	wait.until(ExpectedConditions.elementToBeClickable(selectExcelOption));
    	selectExcelOption.click();
    }
    
    public void selectHTMLOption() {
    	wait.until(ExpectedConditions.elementToBeClickable(selectHTMLOption));
    	selectHTMLOption.click();
    }
    
    public void selectXMLOption() {
    	wait.until(ExpectedConditions.elementToBeClickable(selectXMLOption));
    	selectXMLOption.click();
    }
    
    public void selectCSVOption() {
    	wait.until(ExpectedConditions.elementToBeClickable(selectCSVOption));
    	selectCSVOption.click();
    }
    
    public void selectPrintOption() {
    	wait.until(ExpectedConditions.elementToBeClickable(selectPrintOption));
    	selectPrintOption.click();
    }
    
    public void clickOnPageSizeDropdown() {
    	wait.until(ExpectedConditions.elementToBeClickable(PageSizeDropdown));
    	PageSizeDropdown.click();
    }
    
    public String getPageSizeValue() {
    	wait.until(ExpectedConditions.visibilityOf(getPageSizeValue));
    	return getPageSizeValue.getText();
    }
    
    public void clickOnNextPage() {
    	wait.until(ExpectedConditions.elementToBeClickable(clickOnNextPage));
    	clickOnNextPage.click();
    }
    
    public String getPageNumber() {
    	wait.until(ExpectedConditions.visibilityOf(getPageNo));
    	return getPageNo.getAttribute("value");
    }

 }

