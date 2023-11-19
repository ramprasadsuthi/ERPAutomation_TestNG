package commonMethods;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;

public class BaseMethods {
	private WebDriver driver;
	private LoginPage lp;
    private HomePage hp;

    // Constructor initializes elements with PageFactory
    public BaseMethods(WebDriver driver) {
        this.driver = driver;
		lp = new LoginPage(driver);
		hp = new HomePage(driver);
        PageFactory.initElements(driver, this);
    }
    
    public void loginApp(String username, String password) {
    	lp.enterUsername(username);
		lp.enterPassword(password);
		lp.clickLoginButton();
    }
    
    public void closeApp() {
    	driver.quit();
    	
    }

    public String getLatestFile() {
        String directoryPath = "C:/Users/rampr/Downloads"; // Replace with the actual path to your directory
        String latestFileName = null;

        try {
        	latestFileName = getLatestFileName(directoryPath);
            System.out.println("Latest file: " + latestFileName);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return latestFileName;
    }

    public static String getLatestFileName(String directoryPath) throws IOException {
        Path dir = Paths.get(directoryPath);

        if (!Files.isDirectory(dir)) {
            throw new IllegalArgumentException("Provided path is not a directory.");
        }

        // Get the latest file based on last modified time
        return Files.walk(dir)
                .filter(Files::isRegularFile)
                .max(Comparator.comparingLong(file -> {
                    try {
                        return Files.getLastModifiedTime(file).toMillis();
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                }))
                .map(Path::getFileName)
                .map(Path::toString)
                .orElse(null);
    }
}
