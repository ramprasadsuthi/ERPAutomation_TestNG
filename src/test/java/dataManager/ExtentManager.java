package dataManager;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extentReports;

    public static ExtentReports getInstance() {
        if (extentReports == null) {
        	String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            ExtentSparkReporter htmlReporter = new ExtentSparkReporter("test-output/ExtentReport_" + timeStamp + ".html");
            htmlReporter.config().setTheme(Theme.DARK);
            extentReports = new ExtentReports();
            extentReports.attachReporter(htmlReporter);
        }
        return extentReports;
    }
}

