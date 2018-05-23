package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payrol.StudentLoanAndCourtOrderScenario3;
import com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.CourtOrderAEO1971civildebt_Scenario.*;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Test_Util;


public class CAPSIOAndPayroll extends CourtOrderAEO1971civildebt
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
		if(! Test_Util.IsTestcaseRunMode(Payroll_CourtOrderScenarioThree_SuiteXls, this.getClass().getSimpleName()))
		{
			Skip=true;
			Test_Util.ReportDataSetResult(Payroll_CourtOrderScenarioThree_SuiteXls, "first", Test_Util.GetRowNum(Payroll_CourtOrderScenarioThree_SuiteXls, this.getClass().getSimpleName()),"Skipped");
			//Test_Util.ReportDataSetResult(Payroll_CourtOrderScenarioThree_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase" +this.getClass().getSimpleName() +" as the runmode is set to 'no' ");// this message would display in logs
			throw new Exception("Testcase is being skipped" + this.getClass().getSimpleName()+ "as it's Runmode is set to 'NO'"); // this msg would display in Reports.
		}
		// Load the runmodes of the tests
		runmodes=Test_Util.getDataSetRunmodes(Payroll_CourtOrderScenarioThree_SuiteXls, this.getClass().getSimpleName());
		System.out.println("The testcase Runmode is set to YES hence passed the CheckTestSkip method .Now it moves forward to exectute the test scenario");
		System.out.println("");


		// Please update the Tax year from the Base class as per your Test configuration
		processDesiredTaxYearInputExcelFile(TaxYear);
	}



	@Test(dataProvider="getData", priority=1)
	public void toSelectOrgForPerformingAutomationTests(String EmpName,String NICategory, String AnnualSalary,
			String PayFrequency,String EmployerName,String Payrolid,String SepMonthName,String OctMonthName,
			String NovMonthName, String ExcelInputSheet,String FirstReportNameInApplication,
			String SecondReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,
			String PayrollView,String TestReportworksheetNo,
			String OctExpectedResultRowNumOfTestResultFile,String OctActualResultRowNumOfTestResultFile,
			String OctTestRemarkRowNumOfTestResultFile,String NovExpectedResultRowNumOfTestResultFile,
			String NovActualResultRowNumOfTestResultFile,String NovTestRemarkRowNumOfTestResultFile) throws Throwable
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
		/*
		 * The Org based on the selection at base class would be invoked.
		 */

		toSelectDesiredOrg(OrgFlag);
	}

	public String payfreqncy;
	boolean employeeFirsttimeView = true;
	boolean compensationFirsttimeView = true;
	boolean shouldOpenBrowser = true; 



	@Test(dataProvider="getData", priority=2,dependsOnMethods = {"toSelectOrgForPerformingAutomationTests"})
	public void toSetEmployeesNICategory(String EmpName,String NICategory, String AnnualSalary,
			String PayFrequency,String EmployerName,String Payrolid,String SepMonthName,String OctMonthName,
			String NovMonthName, String ExcelInputSheet,String FirstReportNameInApplication,
			String SecondReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,
			String PayrollView,String TestReportworksheetNo,
			String OctExpectedResultRowNumOfTestResultFile,String OctActualResultRowNumOfTestResultFile,
			String OctTestRemarkRowNumOfTestResultFile,String NovExpectedResultRowNumOfTestResultFile,
			String NovActualResultRowNumOfTestResultFile,String NovTestRemarkRowNumOfTestResultFile) throws Throwable
	{
		try
		{
			// The script updates the NI Category for the Automation employees
			System.out.println("");
			System.out.println("The script now going to select the desired employee(s) as per input sheet and updates the NI Category");
			updateEmployeesNICategory(EmpName,NICategory,AnnualSalary,PayFrequency,EmployerName,Payrolid,
					SepMonthName,OctMonthName,NovMonthName,
					SecondReportNameInApplication,ExcelInputSheet,FirstReportNameInApplication,
					TestResultExcelFilePath,worksheetNo,PayrollView,TestReportworksheetNo,
					OctExpectedResultRowNumOfTestResultFile,OctActualResultRowNumOfTestResultFile,
					OctTestRemarkRowNumOfTestResultFile,NovExpectedResultRowNumOfTestResultFile,
					NovActualResultRowNumOfTestResultFile,NovTestRemarkRowNumOfTestResultFile);
		}
		catch(Throwable t)
		{
			APP_LOGS.debug("Could not assert the home page title, Check for error");
			System.out.println("");
		}
	}




	@Test(dataProvider="getData", priority=3, dependsOnMethods = {"toSetEmployeesNICategory"})
	public void setEmployeesAnnualSalaryAndPayfrequency(String EmpName,String NICategory, String AnnualSalary,
			String PayFrequency,String EmployerName,String Payrolid,String SepMonthName,String OctMonthName,
			String NovMonthName, String ExcelInputSheet,String FirstReportNameInApplication,
			String SecondReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,
			String PayrollView,String TestReportworksheetNo,
			String OctExpectedResultRowNumOfTestResultFile,String OctActualResultRowNumOfTestResultFile,
			String OctTestRemarkRowNumOfTestResultFile,String NovExpectedResultRowNumOfTestResultFile,
			String NovActualResultRowNumOfTestResultFile,String NovTestRemarkRowNumOfTestResultFile) throws Throwable
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
		updateEmpAnnualSalaryAndPayFrequency(EmpName,NICategory,AnnualSalary,PayFrequency,EmployerName,Payrolid,SepMonthName,ExcelInputSheet,FirstReportNameInApplication,TestResultExcelFilePath,worksheetNo,PayrollView,TestReportworksheetNo);
	}

	/*******************************************************************************/
	/*
	 * oct payroll methods to execute
	 */
	/********************************************************************************/

	@Test(dataProvider="getData", priority=6,dependsOnMethods = {"setEmployeesAnnualSalaryAndPayfrequency"})
	public void toProcessOctPayroll(String EmpName,String NICategory, String AnnualSalary,
			String PayFrequency,String EmployerName,String Payrolid,String SepMonthName,String OctMonthName,
			String NovMonthName, String ExcelInputSheet,String FirstReportNameInApplication,
			String SecondReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,
			String PayrollView,String TestReportworksheetNo,
			String OctExpectedResultRowNumOfTestResultFile,String OctActualResultRowNumOfTestResultFile,
			String OctTestRemarkRowNumOfTestResultFile,String NovExpectedResultRowNumOfTestResultFile,
			String NovActualResultRowNumOfTestResultFile,String NovTestRemarkRowNumOfTestResultFile) throws Throwable
	{
		try
		{
			// The script updates the NI Category for the Automation employees
			System.out.println("");
			processOctMonthlyPayroll(EmpName,NICategory,AnnualSalary,PayFrequency,EmployerName,Payrolid,OctMonthName,ExcelInputSheet,FirstReportNameInApplication,TestResultExcelFilePath,worksheetNo,PayrollView,TestReportworksheetNo);
		}
		catch(Throwable t)
		{
			APP_LOGS.debug("Could not assert the home page title, Check for error");
			System.out.println("");
		}
	}




	@Test(dataProvider="getData", priority=7,dependsOnMethods = {"toProcessOctPayroll"})
	public void toSelectEmployeesToProcessOctPayroll(String EmpName,String NICategory, String AnnualSalary,
			String PayFrequency,String EmployerName,String Payrolid,String SepMonthName,String OctMonthName,
			String NovMonthName, String ExcelInputSheet,String FirstReportNameInApplication,
			String SecondReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,
			String PayrollView,String TestReportworksheetNo,
			String OctExpectedResultRowNumOfTestResultFile,String OctActualResultRowNumOfTestResultFile,
			String OctTestRemarkRowNumOfTestResultFile,String NovExpectedResultRowNumOfTestResultFile,
			String NovActualResultRowNumOfTestResultFile,String NovTestRemarkRowNumOfTestResultFile) throws Throwable
	{
		try
		{
			// The script updates the NI Category for the Automation employees
			System.out.println("");
			excludeIncludeEmpForOctPayroll(EmpName,NICategory,AnnualSalary,PayFrequency,EmployerName,Payrolid,OctMonthName,ExcelInputSheet,FirstReportNameInApplication,TestResultExcelFilePath,worksheetNo,PayrollView,TestReportworksheetNo);
		}
		catch(Throwable t)
		{
			APP_LOGS.debug("Could not assert the home page title, Check for error");
			System.out.println("");
		}
	}


	/****************************************************************************************/
	/*
	 * Nov payroll methods to execute
	 */
	/*******************************************************************************************/

	@Test(dataProvider="getData", priority=8,dependsOnMethods = {"toSelectEmployeesToProcessOctPayroll"})
	public void toProcessNovPayroll(String EmpName,String NICategory, String AnnualSalary,
			String PayFrequency,String EmployerName,String Payrolid,String SepMonthName,String OctMonthName,
			String NovMonthName, String ExcelInputSheet,String FirstReportNameInApplication,
			String SecondReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,
			String PayrollView,String TestReportworksheetNo,
			String OctExpectedResultRowNumOfTestResultFile,String OctActualResultRowNumOfTestResultFile,
			String OctTestRemarkRowNumOfTestResultFile,String NovExpectedResultRowNumOfTestResultFile,
			String NovActualResultRowNumOfTestResultFile,String NovTestRemarkRowNumOfTestResultFile) throws Throwable
	{
		try
		{
			// The script updates the NI Category for the Automation employees
			System.out.println("");
			processNovMonthlyPayroll(EmpName,NICategory,AnnualSalary,PayFrequency,EmployerName,Payrolid,NovMonthName,ExcelInputSheet,FirstReportNameInApplication,TestResultExcelFilePath,worksheetNo,PayrollView,TestReportworksheetNo);
		}
		catch(Throwable t)
		{
			APP_LOGS.debug("Could not assert the home page title, Check for error");
			System.out.println("");
		}
	}



	@Test(dataProvider="getData", priority=9,dependsOnMethods = {"toProcessNovPayroll"})
	public void toSelectEmployeesToProcessNovPayroll(String EmpName,String NICategory, String AnnualSalary,
			String PayFrequency,String EmployerName,String Payrolid,String SepMonthName,String OctMonthName,
			String NovMonthName, String ExcelInputSheet,String FirstReportNameInApplication,
			String SecondReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,
			String PayrollView,String TestReportworksheetNo,
			String OctExpectedResultRowNumOfTestResultFile,String OctActualResultRowNumOfTestResultFile,
			String OctTestRemarkRowNumOfTestResultFile,String NovExpectedResultRowNumOfTestResultFile,
			String NovActualResultRowNumOfTestResultFile,String NovTestRemarkRowNumOfTestResultFile) throws Throwable
	{
		try
		{
			// The script updates the NI Category for the Automation employees
			System.out.println("");
			excludeIncludeEmpForNovPayroll(EmpName,NICategory,AnnualSalary,PayFrequency,EmployerName,Payrolid,NovMonthName,ExcelInputSheet,FirstReportNameInApplication,TestResultExcelFilePath,worksheetNo,PayrollView,TestReportworksheetNo);
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
		return Test_Util.getData(Payroll_CourtOrderScenarioThree_SuiteXls,"CAPSIOAndPayroll");
	}



	@AfterMethod
	public void toReportDataSetResult() throws Throwable
	{
		if(Skip)
		{
			Assert.assertTrue(false);
			Test_Util.ReportDataSetResult(Payroll_CourtOrderScenarioThree_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
		}
		else if(Fail)
		{
			IsTestPass = false;
			Assert.assertTrue(false);

			Test_Util.ReportDataSetResult(Payroll_CourtOrderScenarioThree_SuiteXls, this.getClass().getSimpleName(), count+2, "Fail");
		}
		else
		{
			Assert.assertTrue(true);

			Test_Util.ReportDataSetResult(Payroll_CourtOrderScenarioThree_SuiteXls, this.getClass().getSimpleName(), count+2, "Pass");
		}
		Skip=false;
		Fail=false;
	}


	/*
	 *  This will update the test result in the first work sheet where in for that test case , even if one of the test data specified in second worksheet fails, the test 
		would be considered as fail.And the same would be updated.
	 */


	@AfterTest
	public void toReportTestResult() throws Throwable
	{
		if(IsTestPass)
		{
			Assert.assertTrue(true);

			Test_Util.ReportDataSetResult(Payroll_CourtOrderScenarioThree_SuiteXls, "first", Test_Util.GetRowNum(Payroll_CourtOrderScenarioThree_SuiteXls, this.getClass().getSimpleName()),"Pass");
		}
		else
		{
			Assert.assertTrue(false);

			Test_Util.ReportDataSetResult(Payroll_CourtOrderScenarioThree_SuiteXls, "first", Test_Util.GetRowNum(Payroll_CourtOrderScenarioThree_SuiteXls, this.getClass().getSimpleName()),"Fail");
		}	
		//After performing all the said functionalities the test script closes the browser.
		closeBrowser();
	}
}
