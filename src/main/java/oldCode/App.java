package oldCode;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;



/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {
    	
        String NameofProductToBeAdded = "Pure Pleasure Single Electric Blanket (750 x 1500mm)";
        
    	ChromeOptions option = new ChromeOptions();
    	option.addArguments("--remote-allow-origins=*");
    	
    	//browser level location accept/block notification
    	
        System.setProperty("webdriver.chrome.driver", "C:/Users/ARIGHOSE/Documents/chromedriver-win64/chromedriver-win64/chromedriver.exe");
    	WebDriver driver = new ChromeDriver(option);
    	driver.navigate().to("https://qa.builders.co.za/");
    	//to get the page load time
    	Duration pageLoad = driver.manage().timeouts().getPageLoadTimeout();
    	System.out.println(pageLoad);
    	
    	//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
    	driver.manage().window().maximize();
    	
    	WebElement AcceptCookies = driver.findElement(By.xpath("//div[@id='react-app']//div[contains(text(),'Accept')]"));
    	AcceptCookies.click();//accept cookies
    	
    	WebElement SigninBtn = driver.findElement(By.xpath("//div[@id='react-app']//span[contains(text(),'Sign in')]"));
    	SigninBtn.click();// sign btn
    	
    	WebElement EmailID = driver.findElement(By.xpath("//input[@name='email']"));
    	EmailID.sendKeys("riju.ghose4321@gmail.com");// enter mail id
    	
    	String Actualvalue = EmailID.getAttribute("value");
    	Assert.assertEquals("riju.ghose4321@gmail.com", Actualvalue); // to check if sent data is matching with expected. Sendkeys data present in value attribute
    	
    	WebElement pass = driver.findElement(By.xpath("//input[@type='password']"));
    	pass.sendKeys("Arijit#1995");// enter password
    	
    	TimeUnit.SECONDS.sleep(5);
    	
    	WebElement loginbtn = driver.findElement(By.xpath("//div[@data-testid='button-signIn']//div[contains(text(),'Sign in')]"));
    	loginbtn.click();// Login with id button
    	
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='react-app']//input[@placeholder='What can we help you find?']")));
    	WebElement srchTextBox = driver.findElement(By.xpath("//div[@id='react-app']//input[@placeholder='What can we help you find?']"));
    	srchTextBox.sendKeys("Appliances");// search
    	srchTextBox.sendKeys(Keys.ENTER);
    	
    	TimeUnit.SECONDS.sleep(5);
    	
    	JavascriptExecutor js = (JavascriptExecutor) driver;
	
    	List<WebElement> productnames = driver.findElements(By.xpath("//div[@class='css-175oi2r r-1m04atk']/div[@class='css-175oi2r']//div[@data-testid='label-wishListProductName']")); // product names list
    	List<WebElement> productAvailable = driver.findElements(By.xpath("//div[@class='css-175oi2r r-1m04atk']//div[contains(text(),'Add') or contains(text(),'Out of stock')]")); // AddtoCart or OutofStock xpath using 'or'
    	
    	
    	for(int i=0;i<productnames.size();i++)
    	{
    		String text = productnames.get(i).getText();
    		if(text.equals(NameofProductToBeAdded))
    		{
    			js.executeScript("arguments[0].scrollIntoView(true);", productnames.get(i));
    			TimeUnit.SECONDS.sleep(5);
    			productAvailable.get(i).click();
    			
    			String ProductAddedtoCart = driver.findElement(By.xpath("//*[contains(text(),'Product added to cart')]")).getText();
    			
    			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Product added to cart')]")));
    			Assert.assertEquals("Product added to cart", ProductAddedtoCart);
    			break;
    		}
    	}
    	
    	TimeUnit.SECONDS.sleep(5);
    	
    	//Its react.js so element is unique but before its showing 2 times. So if we clck nrmally shows elementnotinteratcble exception.
    	//putting in try catch to refresh and reassigning the element to avoid staleelement exception
    	WebElement CartBtn = driver.findElement(By.xpath("//div[@data-testid='label-cartItemCount']/.."));
    	
    	try {
    	CartBtn.click();
    	}
    	catch(Exception e)
    	{
    		driver.navigate().refresh();
    		WebElement CartBtnAfterRefresh = driver.findElement(By.xpath("//div[@data-testid='label-cartItemCount']/.."));
    		CartBtnAfterRefresh.click();
    	}
    	
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Cart')]"))); // wait until Cart Text is displayed
    	
    	//checking added product matches with the product present in the cart
    	List<WebElement> CartProductnames = driver.findElements(By.xpath("//div[@class='css-175oi2r r-17h3185 r-1xfd6ze r-rs99b7 r-1fphf79 r-mvpalk r-usiww2 r-ymttw5']//div[@data-testid='label-productName']"));
    	WebElement checkoutBtn = driver.findElement(By.xpath("//div[contains(text(),'Proceed to checkout')]"));
    	
    	for(WebElement ele:CartProductnames)
    	{
    		String name = ele.getText();
    		if(name.equals(NameofProductToBeAdded))
    		{
    			checkoutBtn.click();
    		}
    	}
    	
    	driver.quit();
    }
    
    
}
