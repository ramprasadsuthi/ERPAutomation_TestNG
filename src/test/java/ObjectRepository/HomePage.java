package ObjectRepository;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Page elements using @FindBy annotation
    @FindBy(id = "ewPageCaption")
    private WebElement pageCaption;
    
    @FindBy(id = "mi_a_stock_items")
    private WebElement stockItemsMenu;
    
    @FindBy(id = "mi_a_customers")
    private WebElement customersMenu;
    
    @FindBy(id = "mi_a_suppliers")
    private WebElement suppliersMenu;
    
    @FindBy(id = "mi_a_purchases")
    private WebElement purchasesMenu;
    
    @FindBy(id = "mi_a_sales")
    private WebElement salesMenu;
    
    @FindBy(id = "mi_a_stock_categories")
    private WebElement stockCategoriesMenu;
    
    @FindBy(id = "mi_a_unit_of_measurement")
    private WebElement UOM;

    // Constructor initializes elements with PageFactory
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Actions performed on the page elements
    public String getPageCaption() {
    	wait.until(ExpectedConditions.visibilityOf(pageCaption));
    	return pageCaption.getText();
    }
    
    public void clickOnStockItemsMenu() {
    	wait.until(ExpectedConditions.visibilityOf(stockItemsMenu));
    	stockItemsMenu.click();
    }
    
    public void clickOnCustomersMenu() {
    	wait.until(ExpectedConditions.visibilityOf(customersMenu));
    	customersMenu.click();
    }
    
    public void clickOnStockCategoriesMenu() {
    	wait.until(ExpectedConditions.visibilityOf(stockItemsMenu));
    	Actions actions = new Actions(driver);
		actions.moveToElement(stockItemsMenu).build().perform();
		actions.moveToElement(stockCategoriesMenu).build().perform();
		stockCategoriesMenu.click();
    }
    
    public void clickOnUOMMenu() {
    	wait.until(ExpectedConditions.visibilityOf(stockItemsMenu));
    	Actions actions = new Actions(driver);
		actions.moveToElement(stockItemsMenu).build().perform();
		actions.moveToElement(UOM).build().perform();
		UOM.click();
    }
    
    public void clickOnSuppliersMenu() {
    	wait.until(ExpectedConditions.visibilityOf(suppliersMenu));
    	suppliersMenu.click();
    }
    
    public void clickOnPurchasesMenu() {
    	wait.until(ExpectedConditions.visibilityOf(purchasesMenu));
    	purchasesMenu.click();
    }
    
    public void clickOnSalesMenu() {
    	wait.until(ExpectedConditions.visibilityOf(salesMenu));
    	salesMenu.click();
    }

 }

