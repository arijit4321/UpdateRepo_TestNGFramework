package arijit_StepDef;

import org.testng.Assert;
import org.testng.annotations.Test;

import arijit_DataProviderutils.ExcelData;
import arijit_TestComponents.Base;

public class ErrorValidation extends Base{
	
	@Test(dataProvider = "DataProvider_Selenium", dataProviderClass = ExcelData.class)
	public void erroValidate(String username, String password)
	{
		landingPage.loginApplication(username, password);
		Assert.assertEquals("Invalid email address or password entered", landingPage.getLoginErrorMsg());
	}
	
	@Test(dataProvider = "DataProvider_Selenium", dataProviderClass = ExcelData.class)
	public void erroValidate2(String username, String password)
	{
		landingPage.loginApplication(username, password);
		Assert.assertEquals("Invalid email address o password entered", landingPage.getLoginErrorMsg());
	}

}
