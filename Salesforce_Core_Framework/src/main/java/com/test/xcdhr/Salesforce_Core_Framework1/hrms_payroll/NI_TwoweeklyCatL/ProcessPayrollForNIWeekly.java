package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.NI_TwoweeklyCatL;




import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Test_Util;



public class ProcessPayrollForNIWeekly extends TestSuiteBase
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
	public void CheckTestSkip() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		if (!Test_Util.IsTestcaseRunMode(Payroll_2WeeklyCatL_SuiteXls, this
				.getClass().getSimpleName()))
		{
			Skip = true;
			Test_Util.ReportDataSetResult(Payroll_2WeeklyCatL_SuiteXls, "first",
					Test_Util.GetRowNum(Payroll_2WeeklyCatL_SuiteXls, this
							.getClass().getSimpleName()), "Skipped");
			// Test_Util.ReportDataSetResult(Payroll_2WeeklyCatL_SuiteXls,
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
		runmodes = Test_Util.getDataSetRunmodes(Payroll_2WeeklyCatL_SuiteXls, this
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
			logingIntoDesiredORG(OrgFlag);
			driver.manage().window().maximize();
			try
			{
				System.out
				.println("The test script logged in successfully into salesforce account");
				System.out.println("");
				PayrollForWeeklyTax(EmployerName,EmpName,Payrolid,Frquency,WeekName,ExcelInputSheet,FirstReportNameInApplication,worksheetNo,PayrollVeiw);
			}
			catch (Throwable t)
			{
				System.out.println(t.getMessage().toString());
				System.out.println(t.getStackTrace().toString());
			}
		}
		ExcludeIncludeEmp112(EmpName,ExcelInputSheet,worksheetNo);
		
		if (finalRows != dTRows)
		{
			Thread.sleep(3000L);
			System.out.println("Since the app is not displaying employee records same"
					+ " as excel file employees of this Tax worksheet");
			ProcessPayrollForNIWeekly obj1 = new ProcessPayrollForNIWeekly();
			
			for(Repeat=2; Repeat < 5; Repeat++)
			{
				// I have set 3 times to repeat the payroll script so that by the time it processess
				// 4th round 7 minutes would be as per Tutu. the appln should process the generate draft functionality.
				System.out.println("The value of Repeat is "+Repeat);
				obj1.PayrollForWeeklyTax(EmployerName,EmpName,Payrolid,Frquency,WeekName,ExcelInputSheet,FirstReportNameInApplication,worksheetNo,PayrollVeiw);

				obj1.ExcludeIncludeEmp112(EmpName,ExcelInputSheet,worksheetNo);
			}
		}
		
	}
	
	
	
	
	
	
	@DataProvider
	public Object[][] getData() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		return Test_Util.getData(Payroll_2WeeklyCatL_SuiteXls,"ProcessPayrollForNIWeekly");
	}

	@AfterMethod
	public void ReportDataSetResult() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		if (Skip)
		{
			Test_Util.ReportDataSetResult(Payroll_2WeeklyCatL_SuiteXls, this
					.getClass().getSimpleName(), count + 2, "Skip");
		} else if (Fail)
		{

			IsTestPass = false;

			Test_Util.ReportDataSetResult(Payroll_2WeeklyCatL_SuiteXls, this
					.getClass().getSimpleName(), count + 2, "Fail");
		} else
		{
			Test_Util.ReportDataSetResult(Payroll_2WeeklyCatL_SuiteXls, this
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

			Test_Util.ReportDataSetResult(Payroll_2WeeklyCatL_SuiteXls, "first",
					Test_Util.GetRowNum(Payroll_2WeeklyCatL_SuiteXls, this
							.getClass().getSimpleName()), "Pass");

		} else
		{

			Test_Util.ReportDataSetResult(Payroll_2WeeklyCatL_SuiteXls, "first",
					Test_Util.GetRowNum(Payroll_2WeeklyCatL_SuiteXls, this
							.getClass().getSimpleName()), "Fail");

		}
		closeBrowser();
	}

}
