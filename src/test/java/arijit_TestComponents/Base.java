package arijit_TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import arijit_PageObjects.LandingPage;

public class Base {

	public WebDriver driver;
	
	public LandingPage landingPage;
	
	public WebDriver initializedriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir")+"/src/main/java/arijit_resources/Global.properties");
		prop.load(file);

		String browserName = prop.getProperty("browser");
		//String browserName = "chrome";

		if (browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions option = new ChromeOptions();
	
			option.addArguments("--remote-allow-origins=*");

			// browser level location accept/block notification

			System.setProperty("webdriver.chrome.driver",
					"C:/Users/ARIGHOSE/Documents/chromedriver-win64/chromedriver-win64/chromedriver.exe");
			driver = new ChromeDriver(option);
		}
		
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			//Firefox
		}
		
		else if(browserName.equalsIgnoreCase("edge"))
		{
			//Edge
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		
		return driver; // returning the driver
	}
	
	public String getScreenshot(String testcaseName, WebDriver driver) throws IOException
	{
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"/reports/"+testcaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"/reports/"+testcaseName+".png";
	}
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApp() throws IOException
	{
		driver = initializedriver(); //above driver is now present in the global driver object. simple return logic
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun = true)
	public void teardown()
	{
		driver.quit();
	}
}
