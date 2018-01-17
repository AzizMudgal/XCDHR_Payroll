package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.AutoEnrolment_ChangeOf_Earnings;



import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Test_Util;


public class ProcessPayrollForWeek1 extends TestSuiteBase
{
	String runmodes[] = null;
	static int count = -1;
	static int countCompensation = -1;

	public static boolean Fail = false;
	public static boolean Skip = false;
	public static boolean IsTestPass = true;
	public String payrollRecordId;
	public int rownum;
	public String weekOneRecordId;
	
		

	@BeforeTest
	public void CheckTestSkip() throws Exception
	{
		if (!Test_Util.IsTestcaseRunMode(Payroll_AutoEnrolment_Employee_ChangeInEarnings_SuiteXls, this
				.getClass().getSimpleName()))
		{
			Skip = true;
			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Employee_ChangeInEarnings_SuiteXls, "first",
					Test_Util.GetRowNum(Payroll_AutoEnrolment_Employee_ChangeInEarnings_SuiteXls, this
							.getClass().getSimpleName()), "Skipped");
			// Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Employee_ChangeInEarnings_SuiteXls,
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
		runmodes = Test_Util.getDataSetRunmodes(Payroll_AutoEnrolment_Employee_ChangeInEarnings_SuiteXls, this
				.getClass().getSimpleName());

	}

	public String payfreqncy;
	boolean exlude = true;
	boolean compensationFirsttimeView = true;
	boolean shouldOpenBrowser = true;

	@Test(dataProvider = "getData")
	public void EmpsPayroll_Setup_ForIncomeTax(String EmployerName,String EmpName,String Payrolid,String Frquency,String WeekName,String ExcelInputSheet,String FirstReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,String PayrollVeiw) throws Throwable
	{
		//APP_LOGS.debug(EmpName);
		count++;
		if (!runmodes[count].equalsIgnoreCase("Y"))
		{
			Skip = true;
			throw new SkipException("Runmode for Test set data is set to 'NO' "
					+ count);
		}

		APP_LOGS.debug("Executing the test case");
		if (shouldOpenBrowser) {
			shouldOpenBrowser = false;
			openBrowser();
			driver.get(CONFIG.getProperty("testSiteName"));
			login_To_QA_Org();

			driver.manage().window().maximize();
			try
			{
				System.out
				.println("The test script logged in successfully into salesforce account");
				
				
				
				PayrollNIWeeklyAutoEnrol(EmployerName,EmpName,Payrolid,Frquency,WeekName,ExcelInputSheet,FirstReportNameInApplication,TestResultExcelFilePath,PayrollVeiw);
				/*
				 *Following two methods goes to 'Generate draft details page' and process the 'Payroll for required employees' .
				 */
				
				ExcludeIncludeEmp(EmpName,ExcelInputSheet,worksheetNo);
				
			}
			catch (Throwable t)
			{
				System.out.println(t.getMessage().toString());
				System.out.println(t.getStackTrace().toString());
			}
			
		}
		
		
	}

	
	/*
	 * FOLLOWING METHODS FOR Weekly auto enrolment
	 */
	
	/*********************************************************/
	
	
	/**********************************************************************/
	
	

	@DataProvider
	public Object[][] getData()
	{
		return Test_Util.getData(Payroll_AutoEnrolment_Employee_ChangeInEarnings_SuiteXls,"ProcessPayrollForWeek1");
	}
	


	@AfterMethod
	public void ReportDataSetResult()
	{
		if (Skip)
		{
			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Employee_ChangeInEarnings_SuiteXls, this
					.getClass().getSimpleName(), count + 2, "Skip");
		} else if (Fail)
		{

			IsTestPass = false;

			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Employee_ChangeInEarnings_SuiteXls, this
					.getClass().getSimpleName(), count + 2, "Fail");
		} else
		{
			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Employee_ChangeInEarnings_SuiteXls, this
					.getClass().getSimpleName(), count + 2, "Pass");
		}

		Skip = false;
		Fail = false;

	}

	@AfterTest
	public void ReportTestResult()
	{

		if (IsTestPass)
		{

			// This will update the testresult in the first worksheet where in
			// for that test case , even if one of the test data specified in
			// second worksheet fails, the test
			// would be considered as fail.And the same would be updated.

			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Employee_ChangeInEarnings_SuiteXls, "first",
					Test_Util.GetRowNum(Payroll_AutoEnrolment_Employee_ChangeInEarnings_SuiteXls, this
							.getClass().getSimpleName()), "Pass");

		} else
		{

			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Employee_ChangeInEarnings_SuiteXls, "first",
					Test_Util.GetRowNum(Payroll_AutoEnrolment_Employee_ChangeInEarnings_SuiteXls, this
							.getClass().getSimpleName()), "Fail");

		}
		closeBrowser();
	}

}
