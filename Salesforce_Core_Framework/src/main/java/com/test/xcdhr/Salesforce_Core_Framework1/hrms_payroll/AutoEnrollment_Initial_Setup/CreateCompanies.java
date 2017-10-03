package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.AutoEnrollment_Initial_Setup;




import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Test_Util;




public class CreateCompanies extends TestSuiteBase
{

	String runmodes[] = null;
	static int count = -1;
	static int countAllowance = -1;
	public static boolean Fail=false;
	public static boolean Skip=false;
	public static boolean IsTestPass=true;
	public String divId;
	public String divId2;
	public String firstxpath;
	public int Row_count;
	public String eiththCellOfBody1;
	public String EmpName;
	public String eSAL;
	public int rownum;
	
	

	@BeforeTest
	public void CheckTestSkip() throws Exception
	{
		if(! Test_Util.IsTestcaseRunMode(Payroll_AutoEnrolment_Initial_Setup_SuiteXls, this.getClass().getSimpleName()))
		{

			Skip=true;
			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Initial_Setup_SuiteXls, "first", Test_Util.GetRowNum(Payroll_AutoEnrolment_Initial_Setup_SuiteXls, this.getClass().getSimpleName()),"Skipped");
			//Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Initial_Setup_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase" +this.getClass().getSimpleName() +" as the runmode is set to 'no' ");// this message would display in logs

			throw new Exception("Testcase is being skipped" + this.getClass().getSimpleName()+ "as it's Runmode is set to 'NO'"); // this msg would display in Reports.

		}

		// Load the runmodes of the tests

		runmodes=Test_Util.getDataSetRunmodes(Payroll_AutoEnrolment_Initial_Setup_SuiteXls, this.getClass().getSimpleName());

	}
	
	public String payfreqncy;
	boolean employeeFirsttimeView = true;
	boolean AllowanceFirsttimeView = true;
	boolean shouldOpenBrowser = true; 
	boolean compnees = true;
	
	@Test(dataProvider="getData")
	public void EmpsSetup_WithNICategory(String comName, String country,String currency) throws Throwable
	{	
		//APP_LOGS.debug("Entering the Leave parameters");
		//APP_LOGS.debug(EmpName+"--"+NICategory+"--"+AnnualSalary+"--"+PayFrequency);

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
			driver.get(CONFIG.getProperty("testSiteName"));
			login_To_Application();

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
/*
 * NOTE: Creating the Female Employee script needs to be run only once
 *  after that in the suite , the script should
 *   be set to 'NO' in the "Payroll Suite StatutoryMaternityPay"  excel sheet
 *   
 *   
 */
		
		
		// The script updates the BenifitAndAward for the Automation employees
		createCompanys(comName, country, currency);


	

		
		/*************************************************************************/
	
	
	}
	
	public void createCompanys(String comName, String country,String currency) throws Throwable
	{
		if(compnees)
		{
				try
				{
					if(existsElement(OR.getProperty("CompaniesTab")))
					{
						getObject("CompaniesTab").click();
					}
										
					if(existsElement(OR.getProperty("NewCompanyButton")))
					{
						System.out.println("I am in compnees page");
						Thread.sleep(1000L);
						getObject("NewCompanyButton").click();
						Thread.sleep(4000L);

					}
				
					if(existsElement(OR.getProperty("AutoCompanyName")))
					{
						getObject("AutoCompanyName").sendKeys(comName);
					}
					Thread.sleep(2000L);
					if(existsElement(OR.getProperty("AutoCountryName")))
					{
						Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("AutoCountryName"))));
						// This select by value needs to be called from OR.Properties
						selectByValue.selectByValue(country);
					}
					Thread.sleep(2000L);
					if(existsElement(OR.getProperty("AutoCurrency")))
					{
						Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("AutoCurrency"))));
						// This select by value needs to be called from OR.Properties
						selectByValue.selectByValue(currency);
					}
					
					Thread.sleep(2000L);
					if(existsElement(OR.getProperty("AutoCurrency")))
					{
						getObject("AutoCompanySave").click();
					}
				}
				catch(Throwable t)
				{
					System.out.println(t.getMessage());
					System.out.println(t.getStackTrace().toString());
				}
				
		}
				
			
			
	}
	
	
	@DataProvider
	public Object[][] getData()
	{
		return Test_Util.getData(Payroll_AutoEnrolment_Initial_Setup_SuiteXls,"CreateCompanies");
	}


	@AfterMethod
	public void ReportDataSetResult()
	{
		if(Skip){
			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Initial_Setup_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
		}else if(Fail)
		{

			IsTestPass = false;

			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Initial_Setup_SuiteXls, this.getClass().getSimpleName(), count+2, "Fail");
		}else
		{
			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Initial_Setup_SuiteXls, this.getClass().getSimpleName(), count+2, "Pass");
		}

		Skip=false;
		Fail=false;


	}


	@AfterTest
	public void ReportTestResult(){

		if(IsTestPass){

			// This will update the testresult in the first worksheet where in for that test case , even if one of the test data specified in second worksheet fails, the test 
			// would be considered as fail.And the same would be updated.

			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Initial_Setup_SuiteXls, "first", Test_Util.GetRowNum(Payroll_AutoEnrolment_Initial_Setup_SuiteXls, this.getClass().getSimpleName()),"Pass");

		}else{

			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Initial_Setup_SuiteXls, "first", Test_Util.GetRowNum(Payroll_AutoEnrolment_Initial_Setup_SuiteXls, this.getClass().getSimpleName()),"Fail");

		}	

		closeBrowser();
	}


	
	
}

