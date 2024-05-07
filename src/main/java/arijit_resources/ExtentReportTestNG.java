package arijit_resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportTestNG {
	
	public static ExtentReports getReport()
	{
		String path = System.getProperty("user.dir")+"//reports//ExtentReports.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path) ;
		reporter.config().setReportName("Web Automation Results"); 
		reporter.config().setDocumentTitle("Test Results");
		ExtentReports extent =new ExtentReports ( ); 
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Arijit Ghose");
		return extent;
	}

}
