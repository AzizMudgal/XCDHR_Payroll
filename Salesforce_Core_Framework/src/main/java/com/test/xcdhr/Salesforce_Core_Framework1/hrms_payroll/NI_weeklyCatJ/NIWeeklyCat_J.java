package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.NI_weeklyCatJ;


import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Test_Util;



public class NIWeeklyCat_J extends TestSuiteBase {

	String runmodes[] = null;
	static int count = -1;
	static int countCompensation = -1;

	public static boolean Fail=false;
	public static boolean Skip=false;
	public static boolean IsTestPass=true;
	public int Row_count;
	public int rownum;

	@BeforeTest
	public void CheckTestSkip() throws Throwable{
		processDesiredTaxYearInputExcelFile(TaxYear);
		if(! Test_Util.IsTestcaseRunMode(Payroll_CatJ_SuiteXls, this.getClass().getSimpleName())){

			Skip=true;
			Test_Util.ReportDataSetResult(Payroll_CatJ_SuiteXls, "first", Test_Util.GetRowNum(Payroll_CatJ_SuiteXls, this.getClass().getSimpleName()),"Skipped");
			//Test_Util.ReportDataSetResult(Payroll_CatJ_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase" +this.getClass().getSimpleName() +" as the runmode is set to 'no' ");// this message would display in logs

			throw new Exception("Testcase is being skipped" + this.getClass().getSimpleName()+ "as it's Runmode is set to 'NO'"); // this msg would display in Reports.

		}

		// Load the runmodes of the tests

		runmodes=Test_Util.getDataSetRunmodes(Payroll_CatJ_SuiteXls, this.getClass().getSimpleName());

	}

	public String payfreqncy;
	boolean employeeFirsttimeView = true;
	boolean compensationFirsttimeView = true;
	boolean shouldOpenBrowser = true; 


	@Test(dataProvider="getData", priority=1)
	public void EmpsSetup_WithNICategory(String EmpName,String NICategory, String AnnualSalary, String PayFrequency) throws Throwable
	{
		//APP_LOGS.debug("Entering the Leave parameters");
		APP_LOGS.debug(EmpName+"--"+NICategory+"--"+AnnualSalary+"--"+PayFrequency);

		count++;
		if(! runmodes[count].equalsIgnoreCase("Y")){

			Skip=true;
			throw new SkipException("Runmode for Test set data is set to 'NO' "+count);
		}

		APP_LOGS.debug("Executing the test case");
		if(shouldOpenBrowser)
		{
			shouldOpenBrowser = false;
			openBrowser();
			logingIntoDesiredORG(OrgFlag);

			driver.manage().window().maximize();

			try
			{
				if(existsElement(OR.getProperty("Homepage_txt")))
				{
					Assert.assertEquals(driver.getTitle(), "salesforce.com - Enterprise Edition");
					System.out.println("The test script logged in successfully into salesforce account");
					System.out.println("");
				}
			}
			catch(Throwable t)
			{
				APP_LOGS.debug("Could not assert the home page title, Check for error");
				System.out.println("");
			}

		}

		/*************************************************************************/

		// The script updates the NI Category for the Automation employees
		UpdateEmployeeNICategory(EmpName,NICategory);

		/*************************************************************************/

	}



	@Test(dataProvider="getData", priority=2)
	public void EmpsSetup_WithAnnualSalary(String EmpName,String NICategory, String AnnualSalary, String PayFrequency) throws Throwable
	{
		countCompensation++;
		if(! runmodes[countCompensation].equalsIgnoreCase("Y"))
		{
			Skip=true;
			throw new SkipException("Runmode for Test set data is set to 'NO' "+countCompensation);
		}

		/*************************************************************************/
		// The script updates the Annual salary in the compensation Tab for the Automation employees
		UpdateAnnualSalary(EmpName,AnnualSalary,PayFrequency);
		/*************************************************************************/
	}


	@DataProvider
	public Object[][] getData() throws Throwable{
		processDesiredTaxYearInputExcelFile(TaxYear);
		return Test_Util.getData(Payroll_CatJ_SuiteXls,"NIWeeklyCat_J");
	}


	@AfterMethod
	public void ReportDataSetResult() throws Throwable{
		processDesiredTaxYearInputExcelFile(TaxYear);
		if(Skip){
			Test_Util.ReportDataSetResult(Payroll_CatJ_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
		}else if(Fail){

			IsTestPass = false;

			Test_Util.ReportDataSetResult(Payroll_CatJ_SuiteXls, this.getClass().getSimpleName(), count+2, "Fail");
		}else{
			Test_Util.ReportDataSetResult(Payroll_CatJ_SuiteXls, this.getClass().getSimpleName(), count+2, "Pass");
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
			Test_Util.ReportDataSetResult(Payroll_CatJ_SuiteXls, "first", Test_Util.GetRowNum(Payroll_CatJ_SuiteXls, this.getClass().getSimpleName()),"Pass");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_CatJ_SuiteXls, "first", Test_Util.GetRowNum(Payroll_CatJ_SuiteXls, this.getClass().getSimpleName()),"Fail");
		}	

		closeBrowser();
	}


}
