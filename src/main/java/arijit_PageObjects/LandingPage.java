package arijit_PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import arijit_AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		//sending driver object to parent class using super
		super(driver);
		
		// initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void loginApplication(String email, String password)
	{
		AcceptCookies.click();//accept cookies
		
		SigninBtn.click();// sign btn
		EmailID.sendKeys(email);// enter mail id
		
		//String Actualvalue = EmailID.getAttribute("value"); // For knowledge purpose so commented during execution
    	//Assert.assertEquals("riju.ghose4321@gmail.com", Actualvalue); // to check if sent data is matching with expected. Sendkeys data present in value attribute
    	
    	pass.sendKeys(password);// enter password
    	waitToLoad(5);
    	loginbtn.click();// Login with id button
	}
	
	public String getLoginErrorMsg()
	{
		String msg = ErrorMsg.getText();
		return msg;
	}
	
	public void goTo()
	{
		driver.navigate().to("https://qa.builders.co.za/");
	}
	
	@FindBy(xpath="//div[@id='react-app']//div[contains(text(),'Accept')]")
	WebElement AcceptCookies;
	
	@FindBy(xpath="//div[@id='react-app']//span[contains(text(),'Sign in')]")
	WebElement SigninBtn;
	
	@FindBy(xpath="//input[@name='email']")
	WebElement EmailID;
	
	@FindBy(xpath="//input[@type='password']")
	WebElement pass;
	
	@FindBy(xpath="//div[@data-testid='button-signIn']//div[contains(text(),'Sign in')]")
	WebElement loginbtn;
	
	@FindBy(xpath="//span[contains(text(),'Invalid email address or password entered')]")
	WebElement ErrorMsg;

}
