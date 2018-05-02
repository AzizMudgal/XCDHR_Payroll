package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.hrms_Payroll_SAPP_Statutory_Scenario;




import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Test_Util;


public class ProcessPayrollForJulyMonthSAPP extends TestSuiteBase
{
	String runmodes[] = null;
	static int count = -1;
	static int countCompensation = -1;

	public static boolean Fail = false;
	public static boolean Skip = false;
	public static boolean IsTestPass = true;
	public String payrollRecordId;
	public int rownum;
	public String monthOneRecordId;
	
	

	@BeforeTest
	public void CheckTestSkip() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);

		if (!Test_Util.IsTestcaseRunMode(Payroll_Statutory_AdoptionPaternitypay_SuiteXls, this
				.getClass().getSimpleName()))
		{
			Skip = true;
			Test_Util.ReportDataSetResult(Payroll_Statutory_AdoptionPaternitypay_SuiteXls, "first",
					Test_Util.GetRowNum(Payroll_Statutory_AdoptionPaternitypay_SuiteXls, this
							.getClass().getSimpleName()), "Skipped");
			// Test_Util.ReportDataSetResult(Payroll_Statutory_AdoptionPaternitypay_SuiteXls,
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
		runmodes = Test_Util.getDataSetRunmodes(Payroll_Statutory_AdoptionPaternitypay_SuiteXls, this
				.getClass().getSimpleName());
	}

	
	
	public String payfreqncy;
	boolean exlude = true;
	boolean compensationFirsttimeView = true;
	boolean shouldOpenBrowser = true;

	@Test(dataProvider = "getData")
	public void EmpsPayroll_Setup_ForIncomeTax(String EmployerName,String EmpName,String Payrolid,String Frquency,String MonthName,String ExcelInputSheet,String FirstReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,String PayrollVeiw,String TestReportworksheetNo) throws Throwable
	{
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
				closePopupWindow();
				if(existsElementchkFor1mts(OR.getProperty("PersonalTab")))
				{
					String personalTab = getObject("PersonalTab").getText();
					System.out.println("Tab name is :"+ personalTab);
					Assert.assertEquals("Personal", personalTab);
					System.out.println("The test script verified that it successfully logged into XCD HR Org.");
					System.out.println("");
				}
				PayrollForStatutoryMonthly(EmployerName,EmpName,Payrolid,Frquency,MonthName,ExcelInputSheet,FirstReportNameInApplication,TestResultExcelFilePath,PayrollVeiw);
			}
			catch (Throwable t)
			{
				System.out.println(t.getMessage().toString());
				System.out.println(t.getStackTrace().toString());
			}
		}
		ExcludeIncludeEmp(EmpName,ExcelInputSheet,worksheetNo);
		if (finalRows != dTRows)
		{
			Thread.sleep(3000L);
			System.out.println("Since the app is not displaying employee records same"
					+ " as excel file employees of this Tax worksheet");
			ProcessPayrollForJulyMonthSAPP obj1 = new ProcessPayrollForJulyMonthSAPP();
			for(Repeat=2; Repeat < 5; Repeat++)
			{
				// I have set 3 times to repeat the payroll script so that by the time it processess
				// 4th round 7 minutes would be as per Tutu. the appln should process the generate draft functionality.
				System.out.println("The value of Repeat is "+Repeat);
				obj1.PayrollForStatutoryMonthly(EmployerName,EmpName,Payrolid,Frquency,MonthName,ExcelInputSheet,FirstReportNameInApplication,TestResultExcelFilePath,PayrollVeiw);
				obj1.ExcludeIncludeEmp(EmpName,ExcelInputSheet,worksheetNo);
			}
		}
	}



	@DataProvider
	public Object[][] getData() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		return Test_Util.getData(Payroll_Statutory_AdoptionPaternitypay_SuiteXls,"ProcessPayrollForJulyMonthSAPP");
	}
	
	

	@AfterMethod
	public void ReportDataSetResult() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		if (Skip)
		{
			Test_Util.ReportDataSetResult(Payroll_Statutory_AdoptionPaternitypay_SuiteXls, this
					.getClass().getSimpleName(), count + 2, "Skip");
		} 
		else if (Fail)
		{
			IsTestPass = false;
			Test_Util.ReportDataSetResult(Payroll_Statutory_AdoptionPaternitypay_SuiteXls, this
					.getClass().getSimpleName(), count + 2, "Fail");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_Statutory_AdoptionPaternitypay_SuiteXls, this
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
			Test_Util.ReportDataSetResult(Payroll_Statutory_AdoptionPaternitypay_SuiteXls, "first",
					Test_Util.GetRowNum(Payroll_Statutory_AdoptionPaternitypay_SuiteXls, this
							.getClass().getSimpleName()), "Pass");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_Statutory_AdoptionPaternitypay_SuiteXls, "first",
					Test_Util.GetRowNum(Payroll_Statutory_AdoptionPaternitypay_SuiteXls, this
							.getClass().getSimpleName()), "Fail");
		}
		closeBrowser();
	}
}
