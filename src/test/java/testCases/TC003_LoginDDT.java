package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{

	@Test(dataProvider="LoginData", dataProviderClass = DataProviders.class, groups={"Datadriven","Master"})
	public void verify_loginDDT(String email, String pwd, String exp)
	{
		
		logger.info("**** Starting TC003_LoginDDT ****");
		try
		{
		//HomePage Creation
		HomePage hm=new HomePage(driver);
		hm.MyAccount();
		hm.Login();
		
		//LoginPage Creation
		LoginPage lp=new LoginPage(driver);
		lp.SetEmail(email);
		lp.SetPassword(pwd);
		lp.ClickLogin();
		
		//MyAccountPage Creation
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.MyAccPageisDisplayed();
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetPage==true)
			{
				
				macc.ClickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetPage==true)
			{
				macc.ClickLogout();
				Assert.assertTrue(false);
				
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("**** Finished TC003_LoginDDT ****");
		
	}
	
	
}
