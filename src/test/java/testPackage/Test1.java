package testPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Test1 {
  @Test
  public void TestCase_1() {
	  
	  System.setProperty("web.chrome.driver", "Tools/chromedriver.exe");
	  
	  WebDriver driver = new ChromeDriver();
	  driver.get("http://webapp.qedgetech.com/login.php");
	  
	  System.out.println("Chrome Browser is successfully launched..");
  }
}
