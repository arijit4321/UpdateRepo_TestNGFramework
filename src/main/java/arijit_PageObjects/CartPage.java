package arijit_PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import arijit_AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		//sending driver object to parent class using super
		super(driver);
		
		// initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void goToCartPage()
	{
		//Its react.js so element is unique but before its showing 2 times. So if we clck nrmally shows elementnotinteratcble exception.
    	//putting in try catch to refresh and reassigning the element to avoid staleelement exception
		try {
	    	CartBtn.click();
	    	}
	    	catch(Exception e)
	    	{
	    		driver.navigate().refresh();
	    		WebElement CartBtnAfterRefresh = driver.findElement(By.xpath("//div[@data-testid='label-cartItemCount']/.."));
	    		CartBtnAfterRefresh.click();
	    	}
		
		waitforElementToAppear(CartPageTextBy); // wait until Cart Text is displayed
	}
	
	//checking added product matches with the product present in the cart
	public void verifyItemcheckoutItem(String expectedProductname)
	{
		for(WebElement ele:CartProductnames)
    	{
    		String name = ele.getText();
    		if(name.equals(expectedProductname))
    		{
    			checkoutBtn.click();
    		}
    	}
    	
    	driver.quit();
	}
	
	
	By CartPageTextBy = By.xpath("//div[contains(text(),'Cart')]");
	
	@FindBy(xpath="//div[@data-testid='label-cartItemCount']/..")
	WebElement CartBtn;
	
	@FindBy(xpath="//div[@class='css-175oi2r r-17h3185 r-1xfd6ze r-rs99b7 r-1fphf79 r-mvpalk r-usiww2 r-ymttw5']//div[@data-testid='label-productName']")
	List<WebElement> CartProductnames;
	
	@FindBy(xpath="//div[contains(text(),'Proceed to checkout')]")
	WebElement checkoutBtn;

}
