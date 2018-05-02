package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.NI_Director_AtoDMonth8;


import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Test_Util;

public class ProcessPayrollForNI_AtoD extends TestSuiteBase
{
	String runmodes[] = null;
	static int count = -1;
	static int countCompensation = -1;

	public static boolean Fail = false;
	public static boolean Skip = false;
	public static boolean IsTestPass = true;
	public String payrollRecordId;
	public int Row_count;
	public int rownum;
	private String NovMonth="November-2015";
	private String ExcelInputSheet="Payroll Suite NI Director AtoD.xlsx";
	
	

	@BeforeTest
	public void CheckTestSkip() throws Exception
	{
		if (!Test_Util.IsTestcaseRunMode(Payroll_NI_Director_AtoD_SuiteXls, this
				.getClass().getSimpleName()))
		{
			Skip = true;
			Test_Util.ReportDataSetResult(Payroll_NI_Director_AtoD_SuiteXls, "first",
					Test_Util.GetRowNum(Payroll_NI_Director_AtoD_SuiteXls, this
							.getClass().getSimpleName()), "Skipped");
			// Test_Util.ReportDataSetResult(Payroll_NI_Director_AtoD_SuiteXls,
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
		runmodes = Test_Util.getDataSetRunmodes(Payroll_NI_Director_AtoD_SuiteXls, this
				.getClass().getSimpleName());

	}

	public String payfreqncy;
	boolean exlude = true;
	boolean compensationFirsttimeView = true;
	boolean shouldOpenBrowser = true;

	@Test(dataProvider = "getData")
	public void EmpsPayroll_Setup_ForIncomeTax(String EmpName) throws Throwable
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
				PayrollForMonthlyTax(NovMonth);
			}
			catch (Throwable t)
			{
				System.out.println(t.getMessage().toString());
			}
		}
		ExcludeIncludeEmp(EmpName,ExcelInputSheet);
	}

	


	@DataProvider
	public Object[][] getData()
	{
		return Test_Util.getData(Payroll_NI_Director_AtoD_SuiteXls,"ProcessPayrollForNI_AtoD");
	}

	@AfterMethod
	public void ReportDataSetResult()
	{
		if (Skip)
		{
			Test_Util.ReportDataSetResult(Payroll_NI_Director_AtoD_SuiteXls, this
					.getClass().getSimpleName(), count + 2, "Skip");
		} 
		else if (Fail)
		{
			IsTestPass = false;
			Test_Util.ReportDataSetResult(Payroll_NI_Director_AtoD_SuiteXls, this
					.getClass().getSimpleName(), count + 2, "Fail");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_NI_Director_AtoD_SuiteXls, this
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
			Test_Util.ReportDataSetResult(Payroll_NI_Director_AtoD_SuiteXls, "first",
					Test_Util.GetRowNum(Payroll_NI_Director_AtoD_SuiteXls, this
							.getClass().getSimpleName()), "Pass");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_NI_Director_AtoD_SuiteXls, "first",
					Test_Util.GetRowNum(Payroll_NI_Director_AtoD_SuiteXls, this
							.getClass().getSimpleName()), "Fail");
		}
		closeBrowser();
	}
}
