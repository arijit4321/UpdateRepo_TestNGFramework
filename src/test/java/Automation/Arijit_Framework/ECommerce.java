package Automation.Arijit_Framework;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import arijit_DataProviderutils.ExcelData;
import arijit_PageObjects.CartPage;
import arijit_PageObjects.LandingPage;
import arijit_PageObjects.ProductCatalogPage;
import arijit_TestComponents.Base;



/**
 * Hello world!
 *
 */
public class ECommerce extends Base
{
	@Test(dataProvider = "DataProvider_Selenium", dataProviderClass = ExcelData.class)
    public void submitorder(String username, String password) throws InterruptedException, IOException
    {
    	
        String NameofProductToBeAdded = "Pure Pleasure Single Electric Blanket (750 x 1500mm)";
    	
    	//LandingPage landingPage = launchApp();
    	ProductCatalogPage prodcatalog = new ProductCatalogPage(driver);
    	CartPage cart =  new CartPage(driver);
    	
    	//to get the page load time
    	Duration pageLoad = driver.manage().timeouts().getPageLoadTimeout();
    	System.out.println(pageLoad);
    
    	landingPage.loginApplication(username, password);
    	prodcatalog.searchProducts();
    	List<WebElement>productnames = prodcatalog.getProductList();
    	List<WebElement>addToCartAvailable = prodcatalog.getaddToCartAvailableList();
    	prodcatalog.addToCart(NameofProductToBeAdded);
    	prodcatalog.verifyProductAddtoCartpopup();
    	cart.goToCartPage();
    	cart.verifyItemcheckoutItem(NameofProductToBeAdded);
    	
    	
    }
    
    
}
