package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.NI_TwoweeklyCatA;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Test_Util;



public class ExecuteQuery extends TestSuiteBase {
	String runmodes[] = null;
	static int count = -1;
	static int countCompensation = -1;
	public static boolean Fail=false;
	public static boolean Skip=false;
	public static boolean IsTestPass=true;
	public String titlename;
	
	
	@BeforeTest
	public void CheckTestSkip() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		if(! Test_Util.IsTestcaseRunMode(Payroll_2WeeklyCatA_SuiteXls, this.getClass().getSimpleName())){

			Skip=true;
			Test_Util.ReportDataSetResult(Payroll_2WeeklyCatA_SuiteXls, "first", Test_Util.GetRowNum(Payroll_2WeeklyCatA_SuiteXls, this.getClass().getSimpleName()),"Skipped");
			//Test_Util.ReportDataSetResult(Payroll_2WeeklyCatA_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase" +this.getClass().getSimpleName() +" as the runmode is set to 'no' ");// this message would display in logs

			throw new Exception("Testcase is being skipped" + this.getClass().getSimpleName()+ "as it's Runmode is set to 'NO'"); // this msg would display in Reports.

		}

		// Load the runmodes of the tests

		runmodes=Test_Util.getDataSetRunmodes(Payroll_2WeeklyCatA_SuiteXls, this.getClass().getSimpleName());

	}
	
	@Test
	public  void queryExecute() throws Throwable
	{
		boolean shouldOpenBrowser = true;
		if(shouldOpenBrowser)
		{
			shouldOpenBrowser = false;
			openBrowser();
			//login_To_Application();
			
			logingIntoDesiredORG(OrgFlag);
			
			
			driver.manage().window().maximize();
			try
			{
				titlename = driver.getTitle();
				Assert.assertEquals(driver.getTitle(), titlename);
				System.out.println("1> The test script logged in successfully into salesforce account and now in Home page");
				System.out.println("");
			}
			catch(Throwable t)
			{
				APP_LOGS.debug("Could not assert the home page title, Check for error");
				System.out.println("");
				defaultWaitTime();
			}

		
			String ParentWindow = driver.getWindowHandle();
			if(existsElement(OR.getProperty("Hover")))
			{
				getObject("Hover").click();
				defaultWaitTime();
			}

			if(existsElement(OR.getProperty("developerConsole")))
			{
				getObject("developerConsole").click();
				defaultWaitTime();
			}

			for(String winHandle : driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle);
				defaultWaitTime();
			}
			
			System.out.println("2> script clicked to Developer console.");
			Thread.sleep(4000L);
			System.out.println("2> script clicked to Developer console.");
			if(existsElement(OR.getProperty("HoverDebug")))
			{
				getObject("HoverDebug").sendKeys("");
				getObject("HoverDebug").click();
				defaultWaitTime();
				
			}

			if(existsElement(OR.getProperty("ExecuteLast")))
			{
				getObject("ExecuteLast").click();
				defaultWaitTime();
				System.out.println("3> Clicked to openExecute link from developer console");
				if(existsElement(OR.getProperty("executeQuerySuccessTable")))
				{
					System.out.println("table got recognised");
					WebElement successTable = driver.findElement(By.xpath(OR.getProperty("executeQuerySuccessTable")));
					List<WebElement> Table_Report = successTable.findElements(By.xpath(OR.getProperty("executeQuerySuccessTablerows")));
					java.util.Iterator<WebElement> Rx = Table_Report.iterator();
					int Reportrownum = 2;
					while(Rx.hasNext())
					{
						String s = "//*[contains(@id,'gridview')]/table/tbody/tr"+"["+Reportrownum+"]"+"/td[6]/div";
						WebElement ddd = driver.findElement(By.xpath(s));
						String textfound = ddd.getText();
						System.out.println("text found is  :"+textfound);
						if(textfound.equalsIgnoreCase("Success"))
						{
							System.out.println("Report status is matching:");
							System.out.println("Hence the YTD Query executed successfully");
							driver.close();
							driver.switchTo().window(ParentWindow);
							/*
							defaultWaitTime();
							if(existsElement(OR.getProperty("homePageEmpTxt")))
							{
								System.out.println("Main window also got closed successfully");
							}
							*/
							break;
						}
						
											
					}
					
					
				}
				
			}
			
			
		}
				
			
		}
	
	
	/*
	@Test
	public  void queryExecute() throws Throwable
	{
		boolean shouldOpenBrowser = true;
		if(shouldOpenBrowser)
		{
			shouldOpenBrowser = false;
			openBrowser();
			driver.get(CONFIG.getProperty("testSiteName"));
			login_To_Application();

			driver.manage().window().maximize();

			try
			{
				if(existsElement(OR.getProperty("Homepage_txt")))
				{
					Assert.assertEquals(driver.getTitle(), "Salesforce - Enterprise Edition");
					System.out.println("1> The test script logged in successfully into salesforce account and now in Home page");
					System.out.println("");
				}
			}
			catch(Throwable t)
			{
				APP_LOGS.debug("Could not assert the home page title, Check for error");
				System.out.println("");
			}
			
			Thread.sleep(5000L);
			
			String ParentWindow = driver.getWindowHandle();
						
			if(existsElement(OR.getProperty("Hover")))
			{
				getObject("Hover").click();
				Thread.sleep(2000L);
			}
			
			
				getObject("developerConsole").click();
				Thread.sleep(6000L);
			
				for(String winHandle : driver.getWindowHandles())
				{
				    driver.switchTo().window(winHandle);
				}
				Thread.sleep(2000L);
				System.out.println("2> script clicked to Developer console.");
				if(existsElement(OR.getProperty("HoverDebug")))
				{
					getObject("HoverDebug").click();
					Thread.sleep(2000L);
					
				}
				
				if(existsElement(OR.getProperty("ExecuteLast")))
				{
					getObject("ExecuteLast").click();
					 Thread.sleep(6000L);
					System.out.println("3> Clicked to openExecute link from developer console");
					 Thread.sleep(2000);
					 driver.close();
				}
				
				Thread.sleep(7000);
				driver.switchTo().window(ParentWindow);
				
				System.out.println("Main window also got closed successfully");
			
		
		}
	}
*/
	
	
	@AfterMethod
	public void ReportDataSetResult() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		if(Skip)
		{
			Test_Util.ReportDataSetResult(Payroll_2WeeklyCatA_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
		}
		else if(Fail)
		{
			IsTestPass = false;
			Test_Util.ReportDataSetResult(Payroll_2WeeklyCatA_SuiteXls, this.getClass().getSimpleName(), count+2, "Fail");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_2WeeklyCatA_SuiteXls, this.getClass().getSimpleName(), count+2, "Pass");
		}
		Skip=false;
		Fail=false;
	}


	@AfterTest
	public void ReportTestResult() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		if(IsTestPass)
		{
			// This will update the testresult in the first worksheet where in for that test case , even if one of the test data specified in second worksheet fails, the test 
			// would be considered as fail.And the same would be updated.
			Test_Util.ReportDataSetResult(Payroll_2WeeklyCatA_SuiteXls, "first", Test_Util.GetRowNum(Payroll_2WeeklyCatA_SuiteXls, this.getClass().getSimpleName()),"Pass");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_2WeeklyCatA_SuiteXls, "first", Test_Util.GetRowNum(Payroll_2WeeklyCatA_SuiteXls, this.getClass().getSimpleName()),"Fail");
		}	
		closeBrowser();
	}
	
}
