package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	@Test(groups={"Regression","Master"})
	public void verify_Login()
	{
		logger.info("**** Login Test is Started ****");
		
		try
		{//HomePage Object Creation
		HomePage hm=new HomePage(driver);
		hm.MyAccount();
		hm.Login();
		
		//Login Object Creation
		LoginPage lp=new LoginPage(driver);
		lp.SetEmail(p.getProperty("email"));
		lp.SetPassword(p.getProperty("password"));
		lp.ClickLogin();
		
		//MyAccountPage Object Creation
		MyAccountPage macc=new MyAccountPage(driver);
		
		boolean value=macc.MyAccPageisDisplayed();
		Assert.assertTrue(value);
		
		}
		catch (Exception e) {
			Assert.fail();
		}
		
		logger.info("**** Login test is Finished ****");
		
	}
}
