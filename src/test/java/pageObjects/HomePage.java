package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[@title='My Account']")
	WebElement MyAccount;
	
	
	@FindBy(xpath = "//a[text()='Register']")
	WebElement Register;
	
	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")
	WebElement Login;
	
	
	public void MyAccount()
	{
		MyAccount.click();
	}
	
	
	public void Register()
	{
		Register.click();
	}
	
	public void Login()
	{
		Login.click();
	}
	
	
}
