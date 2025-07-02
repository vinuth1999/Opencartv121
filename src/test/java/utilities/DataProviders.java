package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path="C:\\Users\\vinut\\eclipse-newworkspace\\Opencartv121\\testData\\AutomationExcelPage.xlsx"; //taking xl file from testData folder
		
		ExcelUtility xlutil=new ExcelUtility(path);
		
		int totalrows=xlutil.getRowCount("sheet1");
		int totalcols=xlutil.getCellCount("Sheet1", 1);
		
		String logindata[][]=new String[totalrows][totalcols];//creating 2d array
		
		for(int i=1;i<=totalrows;i++)
		{
			for(int j=0;j<=totalcols-1;j++)
			{
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);
			}
		}
		return logindata;
	}
	
	
	
}
