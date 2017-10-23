package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.rti_Payroll_Scenario1_Month12;



import java.util.Iterator;
import java.util.Set;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Test_Util;



public class ProcessFinalPayrollForMarch extends TestSuiteBase
{
	String runmodes[] = null;
	static int count = -1;
	static int countCompensation = -1;
	public static boolean Fail = false;
	public static boolean Skip = false;
	public static boolean IsTestPass = true;


	@BeforeTest
	public void CheckTestSkip() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		if (!Test_Util.IsTestcaseRunMode(Payroll_RecognitionScenarious_SuiteXls, this
				.getClass().getSimpleName()))
		{
			Skip = true;
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarious_SuiteXls, "first",
					Test_Util.GetRowNum(Payroll_RecognitionScenarious_SuiteXls, this
							.getClass().getSimpleName()), "Skipped");
			// Test_Util.ReportDataSetResult(Payroll_RecognitionScenarious_SuiteXls,
			// this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase"
					+ this.getClass().getSimpleName()
					+ " as the runmode is set to 'no' ");// this message would
			// display in logs

			throw new Exception("Testcase is being skipped"
					+ this.getClass().getSimpleName()
					+ "as it's Runmode is set to 'NO'"); // this msg would
			// display in
			// Reports.
		}
		// Load the runmodes of the tests
		runmodes = Test_Util.getDataSetRunmodes(Payroll_RecognitionScenarious_SuiteXls, this
				.getClass().getSimpleName());
	}
	
	

	public String payfreqncy;
	public String sbmtBtn;
	public int rowtd;
	boolean exlude = true;
	boolean compensationFirsttimeView = true;
	boolean shouldOpenBrowser = true;

	@Test(dataProvider = "getData")
	public void EmpsPayroll_Setup_ForIncomeTax(String EmployerName,String EmpName,String Payrolid,String Frquency,String MonthName,String ExcelInputSheet,String FirstReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,String PayrollVeiw,String TestReportworksheetNo) throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		count++;
		if (!runmodes[count].equalsIgnoreCase("Y"))
		{
			Skip = true;
			throw new SkipException("Runmode for Test set data is set to 'NO' "
					+ count);
		}
		if (shouldOpenBrowser)
		{
			shouldOpenBrowser = false;
			openBrowser();
			logingIntoDesiredORG(OrgFlag);
			driver.manage().window().maximize();
			try
			{
				System.out
				.println("The test script logged in successfully into salesforce account");
				System.out.println("");
				//PayrollForMonthlyTax(AugustMonth);
				PayrollForStatutoryMonthly(EmployerName,EmpName,Payrolid,Frquency,MonthName,ExcelInputSheet,FirstReportNameInApplication,TestResultExcelFilePath,PayrollVeiw);
				// in base class need to update the method with new parameter
			}
			catch (Throwable t)
			{
				System.out.println(t.getMessage().toString());
				System.out.println(t.getStackTrace().toString());
			}
		}
		generateFinalDraft();
	}

	
	
	public void generateFinalDraft()throws Throwable
	{
		try
		{
			if(existsElementchkFor2mts(OR.getProperty("editButttonInPayrollPage")))
			{
				boolean b=getObject("editButttonInPayrollPage").isDisplayed();
				Assert.assertEquals(true, b);
				getObject("editButttonInPayrollPage").sendKeys("");
				getObject("editButttonInPayrollPage").click();
				Thread.sleep(4000);
				getObject("payRunDateLabel").isDisplayed();
				System.out.println("====>payRunDateLabel Is Pressent<======");
				getObject("payRunDate").isDisplayed();
				Thread.sleep(4000);
				System.out.println("====>payRunDate Is Pressent<======");
				getObject("payRunDate").sendKeys("");
				getObject("payRunDate").click();
				System.out.println("====>payRunDate Is Clicked<======");
				getObject("savePayrollButton").isDisplayed();
				Thread.sleep(4000);
				getObject("savePayrollButton").sendKeys("");
				getObject("savePayrollButton").click();
				System.out.println("====>Save Button Is Clicked<======");		
			}
			else
			{
				System.out.println("=========>Edit Button Is Not Pressent<=========");
			}
			Thread.sleep(3000L);
			if(existsElement(OR.getProperty("generateFinalDraft")))
			 {
				System.out.println("Generate final draft button exists");
				getObject("generateFinalDraft").sendKeys("");
				getObject("generateFinalDraft").click();
				System.out.println("The generate Final draft button got clicked");
				Thread.sleep(10000L);
					if(existsElement(OR.getProperty("progressBar")))
					{
						System.out.println("The progress bar got displayed , plz wait for FPS Button to display");
						Thread.sleep(10000L);

						if(existsElement(OR.getProperty("createFPS")))
						{
							System.out.println("Create FPS Button got displayed");
							System.out.println("");
						}
						else
						{
							Thread.sleep(10000L);
							payRunExecution1();
						}
					}
					else
					{
						Thread.sleep(10000L);
						System.out.println("Still progressBar is not displayed");
						System.out.println("");
						generateFinalDraft();
					}
				}
			    Thread.sleep(15000L);
			    createFPS();
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());	
		}
	}
	
	
	public void payRunExecution1()throws Throwable
	{
		try
		{
			System.out.println("Still generate final payroll functionality execution did not completed...please wait");

		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	
	
	public String mainWindowHandle;
	public void createFPS()throws Throwable
	{
		try
		{
			System.out.println("I am now in CreateFPS Method");
			if(existsElement(OR.getProperty("createFPS")))
			{
				mainWindowHandle=driver.getWindowHandle();
				getObject("createFPS").sendKeys("");
				getObject("createFPS").click();
                Thread.sleep(7000L);
				if(existsElement(OR.getProperty("windowsForm")))
				{
					System.out.println("Yes Create FPS Button got clicked");
					 Set<String> s = driver.getWindowHandles();
					 Iterator<String> ite = s.iterator();
			            while(ite.hasNext())
			            {
			                 String popupHandle=ite.next().toString();
			                 if(!popupHandle.contains(mainWindowHandle))
			                 {
		                        driver.switchTo().window(popupHandle);
		                        Thread.sleep(2000L);
			                 }
			            }
			            if(existsElement(OR.getProperty("fpsClickOkbutton")))
	       				{
	       			    	getObject("fpsClickOkbutton").sendKeys("");
	       			    	getObject("fpsClickOkbutton").click();
		       		    	System.out.println("clicked the popup window OK Button successfully");
	       				}
	       		    	Thread.sleep(14000L);
			            driver.switchTo().window( mainWindowHandle );
			            if(existsElement(OR.getProperty("fpsSubmitTable")))
						{
				            System.out.println("Popup  window closed successfully!!");
						}
				}
			}
			else if(!existsElement(OR.getProperty("windowsForm")))
			{
				payRunExecution2();
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}
	
	

	public void payRunExecution2()throws Throwable
	{
		try
		{
			if(!existsElement(OR.getProperty("windowsForm")))
			{
				Thread.sleep(15000L);
				System.out.println("Still generate create FPS functionality execution did not completed...please wait");
				createFPS();
			}
			else
			{
				System.out.println("Finally  fps creation functionality execution completed successfully and popup form got displayed");
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	
	@DataProvider
	public Object[][] getData() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		return Test_Util.getData(Payroll_RecognitionScenarious_SuiteXls,"ProcessFinalPayrollForMarch");
	}



	@AfterMethod
	public void ReportDataSetResult() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		if (Skip)
		{
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarious_SuiteXls, this
					.getClass().getSimpleName(), count + 2, "Skip");
		} 
		else if (Fail)
		{
			IsTestPass = false;
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarious_SuiteXls, this
					.getClass().getSimpleName(), count + 2, "Fail");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarious_SuiteXls, this
					.getClass().getSimpleName(), count + 2, "Pass");
		}
		Skip = false;
		Fail = false;
	}

	

	@AfterTest
	public void ReportTestResult() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		if (IsTestPass)
		{
			// This will update the testresult in the first worksheet where in
			// for that test case , even if one of the test data specified in
			// second worksheet fails, the test
			// would be considered as fail.And the same would be updated.
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarious_SuiteXls, "first",
					Test_Util.GetRowNum(Payroll_RecognitionScenarious_SuiteXls, this
							.getClass().getSimpleName()), "Pass");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarious_SuiteXls, "first",
					Test_Util.GetRowNum(Payroll_RecognitionScenarious_SuiteXls, this
							.getClass().getSimpleName()), "Fail");
		}
	}

}

