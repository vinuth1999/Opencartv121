package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static WebDriver driver;
	public org.apache.logging.log4j.Logger logger;   //log4j
	public Properties p;
	
	@BeforeClass(groups= {"Sanity","Regression","Master","Datadriven"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException
	{
		//Loading config.properties file
//		FileReader fi=new FileReader("./src//test//resources//config.properties");
		FileInputStream fi=new FileInputStream("./src//test//resources//config.properties");
		p=new Properties();
		p.load(fi);
		
		
		
		logger = LogManager.getLogger(this.getClass());
		
		switch(br)
		{
		case "chrome" : driver=new ChromeDriver(); break;
		case "edge" : driver=new EdgeDriver(); break;
		case "firefox": driver=new FirefoxDriver(); break;
		default : System.out.println("incorrect browser name");return;
		
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
//		driver.get("https://tutorialsninja.com/demo/");
		driver.get(p.getProperty("appURL")); //reading url from the property file
		driver.manage().window().maximize();
	}
	
	public String GenerateString()
	{
		String GeneratedValue=RandomStringUtils.randomAlphabetic(5);
		return GeneratedValue;
	}
	
	
	public String GeneratedNumeric()
	{
		String GeneratedNumeric=RandomStringUtils.randomNumeric(10);
		return GeneratedNumeric;
	}
	
	
	public String GeneratedAlphaNumeric()
	{
		String generatedAlpha=RandomStringUtils.randomAlphabetic(3);
		String generatedNumeric=RandomStringUtils.randomNumeric(3);
		return generatedAlpha+"@"+generatedNumeric;
	}
	
	public String captureScreen(String name) {
		String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot= (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilepath = System.getProperty("user.dir")+"\\screenshots\\"+name+"_"+timeStamp;
		File targetFile = new File(targetFilepath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilepath;
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master","Datadriven"})
	public void teardown()
	{
		driver.close();
	}

	

}
