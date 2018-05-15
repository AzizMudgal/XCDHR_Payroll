package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payrol.StudentLoanAndCourtOrderScenario1;
import com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.CourtOrderAEO1971civildebt_Scenario.*;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Test_Util;


public class TCOAEO1971civildebtSepPayroll extends CourtOrderAEO1971civildebt
{
	String runmodes[] = null;
	static int count = -1;
	static int countCompensation = -1;

	public static boolean Fail=false;
	public static boolean Skip=false;
	public static boolean IsTestPass=true;
	public int Row_count;
	public int rownumaz;
	public int rownum;
	public int rownumc;
	public int rowSize;


	@BeforeTest
	public void checkTestSkip() throws Throwable
	{
		if(! Test_Util.IsTestcaseRunMode(Payroll_CourtOrderScenarioOne_SuiteXls, this.getClass().getSimpleName()))
		{
			Skip=true;
			Test_Util.ReportDataSetResult(Payroll_CourtOrderScenarioOne_SuiteXls, "first", Test_Util.GetRowNum(Payroll_CourtOrderScenarioOne_SuiteXls, this.getClass().getSimpleName()),"Skipped");
			//Test_Util.ReportDataSetResult(Payroll_CourtOrderScenarioOne_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase" +this.getClass().getSimpleName() +" as the runmode is set to 'no' ");// this message would display in logs
			throw new Exception("Testcase is being skipped" + this.getClass().getSimpleName()+ "as it's Runmode is set to 'NO'"); // this msg would display in Reports.
		}
		// Load the runmodes of the tests
		runmodes=Test_Util.getDataSetRunmodes(Payroll_CourtOrderScenarioOne_SuiteXls, this.getClass().getSimpleName());
		System.out.println("The testcase Runmode is set to YES hence passed the CheckTestSkip method .Now it moves forward to exectute the test scenario");
		System.out.println("");

		// Please update the Tax year from the Base class as per your Test configuration
		processDesiredTaxYearInputExcelFile(TaxYear);
	}


	@Test(dataProvider="getData", priority=1)
	public void toSelectOrgForPerformingAutomationTests(String EmpName,String NICategory, String AnnualSalary, String PayFrequency,String EmployerName,String Payrolid,String MonthName,String ExcelInputSheet,String FirstReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,String PayrollView,String TestReportworksheetNo) throws Throwable
	{
		//APP_LOGS.debug(EmpName+"--"+NICategory+"--"+AnnualSalary+"--"+PayFrequency);
		count++;
		if(! runmodes[count].equalsIgnoreCase("Y"))
		{
			Skip=true;
			throw new SkipException("Runmode for Test set data is set to 'NO' "+count);
		}
		System.out.println("Runmode for Test set data is set to YES hence Executing the test case to select the desired Org");
		System.out.println("");
		toSelectDesiredOrg(OrgFlag);
	}

	public String payfreqncy;
	boolean employeeFirsttimeView = true;
	boolean compensationFirsttimeView = true;
	boolean shouldOpenBrowser = true; 


	@Test(dataProvider="getData", priority=2,dependsOnMethods = {"toSelectOrgForPerformingAutomationTests"})
	public void toSetEmployeesNICategory(String EmpName,String NICategory, String AnnualSalary, String PayFrequency,String EmployerName,String Payrolid,String MonthName,String ExcelInputSheet,String FirstReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,String PayrollView,String TestReportworksheetNo) throws Throwable
	{
		try
		{
			// The script updates the NI Category for the Automation employees
			System.out.println("");
			System.out.println("The script now going to select the desired employee(s) as per input sheet and updates the NI Category");
			updateEmployeesNICategory(EmpName,NICategory,AnnualSalary,PayFrequency,EmployerName,Payrolid,MonthName,ExcelInputSheet,FirstReportNameInApplication,TestResultExcelFilePath,worksheetNo,PayrollView,TestReportworksheetNo);
		}
		catch(Throwable t)
		{
			APP_LOGS.debug("Could not assert the home page title, Check for error");
			System.out.println("");
		}
	}




	@Test(dataProvider="getData", priority=3, dependsOnMethods = {"toSetEmployeesNICategory"})
	public void setEmployeesAnnualSalaryAndPayfrequency(String EmpName,String NICategory, String AnnualSalary, String PayFrequency,String EmployerName,String Payrolid,String MonthName,String ExcelInputSheet,String FirstReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,String PayrollView,String TestReportworksheetNo) throws Throwable
	{
		countCompensation++;
		if(! runmodes[countCompensation].equalsIgnoreCase("Y"))
		{
			Skip=true;
			throw new SkipException("Runmode for Test set data is set to 'NO' "+countCompensation);
		}
		System.out.println("");
		System.out.println("The script after updating the NI Category successfuly goes to update the Annual salary and pay frequency for the said employees.");

		// The script updates the Annual salary in the compensation Tab for the Automation employees
		updateEmpAnnualSalaryAndPayFrequency(EmpName,NICategory,AnnualSalary,PayFrequency,EmployerName,Payrolid,MonthName,ExcelInputSheet,FirstReportNameInApplication,TestResultExcelFilePath,worksheetNo,PayrollView,TestReportworksheetNo);
	}
	
	
	
	@Test(dataProvider="getData", priority=4,dependsOnMethods = {"setEmployeesAnnualSalaryAndPayfrequency"})
	public void toProcessPayroll(String EmpName,String NICategory, String AnnualSalary, String PayFrequency,String EmployerName,String Payrolid,String MonthName,String ExcelInputSheet,String FirstReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,String PayrollView,String TestReportworksheetNo) throws Throwable
	{
		try
		{
			// The script updates the NI Category for the Automation employees
			System.out.println("");
			System.out.println("The script now going to select the desired employee(s) as per input sheet and updates the NI Category");
			processMonthlyPayroll(EmpName,NICategory,AnnualSalary,PayFrequency,EmployerName,Payrolid,MonthName,ExcelInputSheet,FirstReportNameInApplication,TestResultExcelFilePath,worksheetNo,PayrollView,TestReportworksheetNo);
		}
		catch(Throwable t)
		{
			APP_LOGS.debug("Could not assert the home page title, Check for error");
			System.out.println("");
		}
	}


	@Test(dataProvider="getData", priority=5,dependsOnMethods = {"toProcessPayroll"})
	public void toSelectEmployeesToProcessPayroll(String EmpName,String NICategory, String AnnualSalary, String PayFrequency,String EmployerName,String Payrolid,String MonthName,String ExcelInputSheet,String FirstReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,String PayrollView,String TestReportworksheetNo) throws Throwable
	{
		try
		{
			// The script updates the NI Category for the Automation employees
			System.out.println("");
			//System.out.println("The script now going to select the desired employee(s) as per input sheet and updates the NI Category");
			excludeIncludeEmp(EmpName,NICategory,AnnualSalary,PayFrequency,EmployerName,Payrolid,MonthName,ExcelInputSheet,FirstReportNameInApplication,TestResultExcelFilePath,worksheetNo,PayrollView,TestReportworksheetNo);
		}
		catch(Throwable t)
		{
			APP_LOGS.debug("Could not assert the home page title, Check for error");
			System.out.println("");
		}
	}

	

	/*
	 * To get the data from the specific input excel sheet 
	 */
	@DataProvider
	public Object[][] getData() throws Throwable
	{
		return Test_Util.getData(Payroll_CourtOrderScenarioOne_SuiteXls,"TCOAEO1971civildebtSepPayroll");
	}



	@AfterMethod
	public void toReportDataSetResult() throws Throwable
	{
		if(Skip)
		{
			Test_Util.ReportDataSetResult(Payroll_CourtOrderScenarioOne_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
		}
		else if(Fail)
		{
			IsTestPass = false;
			Test_Util.ReportDataSetResult(Payroll_CourtOrderScenarioOne_SuiteXls, this.getClass().getSimpleName(), count+2, "Fail");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_CourtOrderScenarioOne_SuiteXls, this.getClass().getSimpleName(), count+2, "Pass");
		}
		Skip=false;
		Fail=false;
	}


	/*
	 *  This will update the testresult in the first worksheet where in for that test case , even if one of the test data specified in second worksheet fails, the test 
		would be considered as fail.And the same would be updated.
	 */
	@AfterTest
	public void toReportTestResult() throws Throwable
	{
		if(IsTestPass)
		{
			Test_Util.ReportDataSetResult(Payroll_CourtOrderScenarioOne_SuiteXls, "first", Test_Util.GetRowNum(Payroll_CourtOrderScenarioOne_SuiteXls, this.getClass().getSimpleName()),"Pass");
			System.out.println("The script successfuly updated the NI Category,Annual salary and Pay frequency for the said employees.");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_CourtOrderScenarioOne_SuiteXls, "first", Test_Util.GetRowNum(Payroll_CourtOrderScenarioOne_SuiteXls, this.getClass().getSimpleName()),"Fail");
		}	
		//After performing all the said functionalities the test script closes the browser.
		closeBrowser();
	}
}
