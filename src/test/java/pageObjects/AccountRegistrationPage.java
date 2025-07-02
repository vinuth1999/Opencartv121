package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class AccountRegistrationPage extends BasePage{

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}

	//https://tutorialsninja.com/demo/ (Alterantive Website name of OpenCart Application)
	
	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement Email;
	
	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement Telephone;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement Password;
	
	@FindBy(xpath = "(//input[@id='input-confirm'])[1]")
	WebElement CnfmPassword;
	
	@FindBy(xpath = "//label[normalize-space()='Yes']")
	WebElement RadioBtnYes;
	
	@FindBy(xpath = "//input[@value='0']")
	WebElement RadioBtnNo;
	
	@FindBy(xpath = "//input[@name='agree']")
	WebElement CheckBox;
	
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement ContinueBtn;
	
	@FindBy(xpath = "//h1[text()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	
	public void SetFirstName(String fname)
	{
		txtFirstName.sendKeys(fname);
	}
	
	public void SetLastName(String lname)
	{
		txtLastName.sendKeys(lname);
	}
	
	public void SetEmail(String email)
	{
		Email.sendKeys(email);
	}
	
	public void SetTelephone(String tphone)
	{
		Telephone.sendKeys(tphone);
	}
	
	
	public void SetPassword(String pwd)
	{
		Password.sendKeys(pwd);
	}
	
	public void SetCnfrmPassword(String cpwd)
	{
		CnfmPassword.sendKeys(cpwd);
	}
	
	public void SetRadiobtn()
	{
		RadioBtnYes.click();
	}
	
	public void CheckBox()
	{
		CheckBox.click();
	}

	public void Button()
	{
		ContinueBtn.click();
	}
	
	public String getConfirmationMsg()
	{
		try {
			return msgConfirmation.getText();
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
