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

public class ProductCatalogPage extends AbstractComponents{
	
	WebDriver driver;
	
	public ProductCatalogPage(WebDriver driver)
	{
		//sending driver object to parent class using super
		super(driver);
		
		// initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void searchProducts()
	{
		waitforElementToAppear(searchTextBoxBy);
		searchTextBox.sendKeys("Appliances");// search
    	searchTextBox.sendKeys(Keys.ENTER);
    	//waitToLoad(5);
	}
	
	public List<WebElement> getProductList()
	{
		waitforElementToAppear(productnamesBy);
		return productnames;
	}
	
	public List<WebElement> getaddToCartAvailableList()
	{
		return addToCartAvailable;
	}
	
	public void addToCart(String expectedProductname)
	{
		for(int i=0;i<getProductList().size();i++)
    	{
			String text = getProductList().get(i).getText();
			if(text.equals(expectedProductname))
    		{
				scrollTillElement(getProductList().get(i));
				waitToLoad(5);
				getaddToCartAvailableList().get(i).click();
    		}
    	}
	}
	
	public void verifyProductAddtoCartpopup()
	{
		//waitforElementToAppear(ProductAddedtoCartPopupBy);
		String ProductAddedtoCart = ProductAddedtoCartpopup.getText();
		Assert.assertEquals("Product added to cart", ProductAddedtoCart);
		waitToLoad(5);
	}
	
	
	By searchTextBoxBy = By.xpath("//div[@id='react-app']//input[@placeholder='What can we help you find?']"); // xpath for search Text Box
	
	By productnamesBy = By.xpath("//div[@class='css-175oi2r r-1m04atk']/div[@class='css-175oi2r']//div[@data-testid='label-wishListProductName']");
	
	By addToCartAvailableBy = By.xpath("//div[@class='css-175oi2r r-1m04atk']//div[contains(text(),'Add') or contains(text(),'Out of stock')]");
	
	By ProductAddedtoCartPopupBy = By.xpath("//*[contains(text(),'Product added to cart')]");
	
	
	@FindBy(xpath="//div[@id='react-app']//input[@placeholder='What can we help you find?']")
	WebElement searchTextBox;
	
	@FindBy(xpath="//div[@class='css-175oi2r r-1m04atk']/div[@class='css-175oi2r']//div[@data-testid='label-wishListProductName']")
	List<WebElement> productnames; // product names list
	
	@FindBy(xpath="//div[@class='css-175oi2r r-1m04atk']//div[contains(text(),'Add') or contains(text(),'Out of stock')]")
	List<WebElement> addToCartAvailable; // AddtoCart or OutofStock xpath using 'or'
	
	@FindBy(xpath="//*[contains(text(),'Product added to cart')]")
	WebElement ProductAddedtoCartpopup;
	

}
