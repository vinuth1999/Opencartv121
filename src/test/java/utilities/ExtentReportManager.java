package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repname;
	
	
	public void onStart(ITestContext testcontext) {
		SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt=new Date();
		String	timestamp=df.format(dt);
		
		repname="Test-Report-"+timestamp+".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+repname);
		
		sparkReporter.config().setDocumentTitle("opencart Automation Report");
		sparkReporter.config().setReportName("opencart Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
			String os=testcontext.getCurrentXmlTest().getParameter("os");
			extent.setSystemInfo("Operating System",os);
			
			String browser=testcontext.getCurrentXmlTest().getParameter("browser");
			extent.setSystemInfo("Browser", browser);
			
			List<String> includeGroups= testcontext.getCurrentXmlTest().getIncludedGroups();
			if(!includeGroups.isEmpty())
			{
				extent.setSystemInfo("Groups", includeGroups.toString());
			}
	  }
	
	public void onTestStart(ITestResult result) {
	  }
	
	public void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+ " got successfully executed");
	  }
	
	
	
	public void onTestFailure(ITestResult result) {
	    test=extent.createTest(result.getTestClass().getName());
	    test.assignCategory(result.getMethod().getGroups());
	    
	    test.log(Status.FAIL, result.getName()+ " got Failed ");
	    test.log(Status.INFO,result.getThrowable().getMessage());
	    
	    try
	    {
	    	String imgpath=new BaseClass().captureScreen(result.getName());
	    	test.addScreenCaptureFromPath(imgpath);	    	
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
	  }
	
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+" got Skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	
	}
	
	
	
	public void onFinish(ITestContext context) {
	    extent.flush();
	    
	    String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repname;
	    File extentReport = new File(pathOfExtentReport);
	    
	    try
	    {
	    	Desktop.getDesktop().browse(extentReport.toURI());
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
		
		
		
		
		
	  }
	
	
	

}
