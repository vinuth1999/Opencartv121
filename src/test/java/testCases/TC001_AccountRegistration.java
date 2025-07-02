 package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistration extends BaseClass {
	
	@Test(groups={"Sanity","Master"})
	public void verify_Account_registration()
	{
		logger.info("***** Starting TC001_AccountRegistrationTest ****");
		
		try
		{
		HomePage hp=new HomePage(driver);
		hp.MyAccount();
		logger.info("Clicked on MyAccount Link");
		hp.Register();
		logger.info("Clicked on Register Link");
		AccountRegistrationPage AccountRegister=new AccountRegistrationPage(driver);
		logger.info("Providing the Customer Details.....");
		
		
		AccountRegister.SetFirstName(GenerateString().toUpperCase());
		AccountRegister.SetLastName(GenerateString().toUpperCase());
//		AccountRegister.SetEmail(p.getProperty("email"));
		AccountRegister.SetEmail(GeneratedAlphaNumeric()+"gmail.com");
		AccountRegister.SetTelephone(GeneratedNumeric());
		String pwd= GenerateString()+GeneratedNumeric();
//		AccountRegister.SetPassword(p.getProperty("password"));
		AccountRegister.SetPassword(pwd);
		AccountRegister.SetCnfrmPassword(pwd);
		AccountRegister.SetRadiobtn();
		AccountRegister.CheckBox();
		AccountRegister.Button();
		
		logger.info("Validation expecting meassge");
		
		if(AccountRegister.getConfirmationMsg().equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test is Falied");
			logger.debug("Debug Logs...");
			Assert.assertTrue(false);
		}
		}
		catch (Exception e) {
			
			Assert.fail();
		}
		
		logger.info("***** Finished the TC001_AccountRegistrationTest *****");
		
	}
	
	
	
	
	
	
	
	
}
