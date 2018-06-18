package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.rti_Payroll_Scenario7_Month2;




import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Test_Util;



public class ProcessFinalPayrollForMay extends TestSuiteBase
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
		if (!Test_Util.IsTestcaseRunMode(Payroll_RecognitionScenarioSeven_SuiteXls, this
				.getClass().getSimpleName()))
		{
			Skip = true;
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarioSeven_SuiteXls, "first",
					Test_Util.GetRowNum(Payroll_RecognitionScenarioSeven_SuiteXls, this
							.getClass().getSimpleName()), "Skipped");
			// Test_Util.ReportDataSetResult(Payroll_RecognitionScenarioSeven_SuiteXls,
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
		runmodes = Test_Util.getDataSetRunmodes(Payroll_RecognitionScenarioSeven_SuiteXls, this
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
				PayrollForStatutoryMonthly(EmployerName,EmpName,Payrolid,Frquency,MonthName,ExcelInputSheet,FirstReportNameInApplication,PayrollVeiw);
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
			if(existsElementchkFor2mts(OR.getProperty("generateFinalDraft")))
			 {
				System.out.println("Generate final draft button exists");
				getObject("generateFinalDraft").sendKeys("");
				getObject("generateFinalDraft").click();
				System.out.println("The generate Final draft button got clicked");
					if(existsElementchkFor3mts(OR.getProperty("progressBar")))
					{
						System.out.println("The progress bar got displayed , plz wait for FPS Button to display");
						if(existsElementchkFor1mts(OR.getProperty("createFPS")))
						{
							System.out.println("The generate Final draft Functionality executed successfully");
							System.out.println("And also Create FPS button got displayed");
							System.out.println("");
						}
						else
						{
							payRunExecution1();
						}
					}
					else
					{
						System.out.println("Still progressBar is not displayed");
						System.out.println("");
						generateFinalDraft();
					}
			}
			if(existsElementchkFor2mts(OR.getProperty("createFPS")))
			{
			    createFPS();
			}
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

	
	
	public void createFPS()throws Throwable
	{
		try
		{
			System.out.println("I am now in CreateFPS Method");
			if(existsElementchkFor2mts(OR.getProperty("createFPS")))
			{
				getObject("createFPS").sendKeys("");
				getObject("createFPS").click();
                if(existsElementchkFor2mts(OR.getProperty("fpsSubmitTable")))
    			{
    				System.out.println("The FPS Functionality got processed successfully as the Submit button got displayed");
    			}
                else
                {
                	payRunExecution2();
                }
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
			if(!existsElementchkFor2mts(OR.getProperty("fpsSubmitTable")))
 			{
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
		return Test_Util.getData(Payroll_RecognitionScenarioSeven_SuiteXls,"ProcessFinalPayrollForMay");
	}



	@AfterMethod
	public void ReportDataSetResult() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		if (Skip)
		{
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarioSeven_SuiteXls, this
					.getClass().getSimpleName(), count + 2, "Skip");
		} 
		else if (Fail)
		{
			IsTestPass = false;
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarioSeven_SuiteXls, this
					.getClass().getSimpleName(), count + 2, "Fail");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarioSeven_SuiteXls, this
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
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarioSeven_SuiteXls, "first",
					Test_Util.GetRowNum(Payroll_RecognitionScenarioSeven_SuiteXls, this
							.getClass().getSimpleName()), "Pass");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarioSeven_SuiteXls, "first",
					Test_Util.GetRowNum(Payroll_RecognitionScenarioSeven_SuiteXls, this
							.getClass().getSimpleName()), "Fail");
		}
		closeBrowser();

	}

}

