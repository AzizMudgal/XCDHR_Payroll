package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.Rti_EmployeeCreation;


import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Test_Util;

public class CreateNewRTIEmployees extends TestSuiteBase
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
		if(! Test_Util.IsTestcaseRunMode(Payroll_CreateEmployees_For_RecognitionScenarious_SuiteXls, this.getClass().getSimpleName()))
		{
			Skip=true;
			Test_Util.ReportDataSetResult(Payroll_CreateEmployees_For_RecognitionScenarious_SuiteXls, "first", Test_Util.GetRowNum(Payroll_CreateEmployees_For_RecognitionScenarious_SuiteXls, this.getClass().getSimpleName()),"Skipped");
			//Test_Util.ReportDataSetResult(Payroll_CreateEmployees_For_RecognitionScenarious_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase" +this.getClass().getSimpleName() +" as the runmode is set to 'no' ");// this message would display in logs
			throw new Exception("Testcase is being skipped" + this.getClass().getSimpleName()+ "as it's Runmode is set to 'NO'"); // this msg would display in Reports.
		}
		// Load the runmodes of the tests
		runmodes=Test_Util.getDataSetRunmodes(Payroll_CreateEmployees_For_RecognitionScenarious_SuiteXls, this.getClass().getSimpleName());
	}
	
	public String payfreqncy;
	boolean employeeFirsttimeView = true;
	boolean AllowanceFirsttimeView = true;
	boolean shouldOpenBrowser = true; 
	boolean MyCompany = true;
	
	
	@Test(dataProvider="getData")
	public void EmpsSetup_WithNICategory(String CompanyName,String FirstName, String LastName,String Email, String UserName, String Profile, String ActivateLicense, String JobTitle, String Company,String EmploymentType, String EmploymentStatus,String PatternType,String NoOfWorkingDays, String ContractualHours, String Manager, String StartDate, String ContinousStDate, String KnownName, String DOB, String Gender, String RegularPay, String Period, String PayrollEligibility, String PayrollFrequency,String TaxCode,String TaxBasis,String StudentLoan,String NICategory,String EffectiveFrom,String StudentLoanPlan,String PayinStartPeriod,String NINO,String StartDeclaration,String DateOfNoticeOfTermination,String LeavingDate,String LastWorkingDate,String ReasonForLeaving) throws Throwable
	{	
	//String empFirstName, String LastName,String Email, String UserName, String WorkMobile, String WorkPhone, String Profile, String ActivateLicense, String Post,String Company,String EmploymentType, String Location, String EmploymentStatus, String Department, String PatternType, String NoOfWorkingDays, String ContractualHours, String SpinalPoint, String Manager, String StartDate, String ContinousStdate, String PayrollStDate, String Rejoiner,  String EmpDOB,String Gender,String Nationality,String FromDate,String ToDate,String Address1,String  Address2,String Street,String City,String Country,String PostCode,String Region,String ParentLocation,String AddnalContrctualLeave,String MinimumYrsService,String HoursAM,String HoursPM,String WorkingDays,String AnnualSalary,String Bonus,String BonusOTE,String Commission,String CommissionOTE,String EmpContrbnPenSal,String EmployerContrbPenSal,String AddnalEmplyeeContrbn,String AddnalEmployerContrbn,String bonusNotes,String DailyRateOfPay,String Departmentt,String EmployeeSalarySacrifice,String EmployeeContbnlnLeiu,String Payfrequency,String CreateLeaveYrs
		count++;
		if(! runmodes[count].equalsIgnoreCase("Y"))
		{
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
				Thread.sleep(5000L);
				if(existsElementchkFor1mts(OR.getProperty("popupwindowAfterLoginSuccess")))
				{
					String oldWindow = driver.getWindowHandle();
					driver.switchTo().window(driver.getWindowHandle());
					getObject("popupwindowAfterLoginSuccess").click();
					//driver.findElement(By.xpath("//div/a[@id='tryLexDialogX']")).click();
					System.out.println("The Popup window got closed");
					driver.switchTo().window(oldWindow);
				}
				else
				{
					System.out.println("The Popwindow does not exist in this Org");
				}

				if(existsElementchkFor1mts(OR.getProperty("PersonalTab")))
				{
					String personalTab = getObject("PersonalTab").getText();
					System.out.println("Tab name is :"+ personalTab);
					Assert.assertEquals("Personal", personalTab);
					System.out.println("The test script verified that it successfully logged into XCD HR Org.");
					System.out.println("");
				}
				RTICore rtic = new RTICore();
				rtic.FetchCompanyRecord(CompanyName, FirstName, LastName, Email, UserName, Profile, ActivateLicense, JobTitle, Company, EmploymentType, EmploymentStatus, PatternType, NoOfWorkingDays, ContractualHours, Manager, StartDate, ContinousStDate, KnownName, DOB, Gender, RegularPay, Period, PayrollEligibility, PayrollFrequency, TaxCode, TaxBasis, StudentLoan, NICategory, EffectiveFrom, StudentLoanPlan, PayinStartPeriod, NINO, StartDeclaration, DateOfNoticeOfTermination, LeavingDate, LastWorkingDate, ReasonForLeaving);
			}
			catch(Throwable t)
			{
				APP_LOGS.debug("Could not assert the home page title, Check for error");
				System.out.println("");
			}
		}

		/*************************************************************************/
		
		/*RTICore startic = new RTICore();
		rtic.FetchCompanyRecord(CompanyName, FirstName, LastName, Email, UserName, Profile, ActivateLicense, JobTitle, Company, EmploymentType, EmploymentStatus, PatternType, NoOfWorkingDays, ContractualHours, Manager, StartDate, ContinousStDate, KnownName, DOB, Gender, RegularPay, Period, PayrollEligibility, PayrollFrequency, TaxCode, TaxBasis, StudentLoan, NICategory, EffectiveFrom, StudentLoanPlan, PayinStartPeriod, NINO, StartDeclaration, DateOfNoticeOfTermination, LeavingDate, LastWorkingDate, ReasonForLeaving);
*/
		// The script updates the BenifitAndAward for the Automation employees
		//rtic.CreateCompenLeavedetails(EmpDOB,Gender, Nationality,FromDate,ToDate,Address1,Address2,Street,City,Country,PostCode,Region,AddressesType,ParentLocation,MinimumYrsService,HoursAM,HoursPM,WorkingDays,AnnualSalary,Bonus,BonusOTE,Commission,CommissionOTE,EmpContrbnPenSal,EmployerContrbPenSal,Regularsalary,AddnalEmplyeeContrbn,AddnalEmployerContrbn,bonusNotes,DailyRateOfPay,Perid,Departmentt, EmployeeSalarySacrifice,EmployeeContbnlnLeiu,Payfrequency,CreateLeaveYrs);
		
		/*************************************************************************/
  }
	
	
	
	@DataProvider
	public Object[][] getData()
	{
		return Test_Util.getData(Payroll_CreateEmployees_For_RecognitionScenarious_SuiteXls,"CreateNewRTIEmployees");
	}


	@AfterMethod
	public void ReportDataSetResult()
	{
		if(Skip)
		{
			Test_Util.ReportDataSetResult(Payroll_CreateEmployees_For_RecognitionScenarious_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
		}
		else if(Fail)
		{
			IsTestPass = false;
			Test_Util.ReportDataSetResult(Payroll_CreateEmployees_For_RecognitionScenarious_SuiteXls, this.getClass().getSimpleName(), count+2, "Fail");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_CreateEmployees_For_RecognitionScenarious_SuiteXls, this.getClass().getSimpleName(), count+2, "Pass");
		}
		Skip=false;
		Fail=false;
	}


	@AfterTest
	public void ReportTestResult()
	{
		if(IsTestPass)
		{
			// This will update the testresult in the first worksheet where in for that test case , even if one of the test data specified in second worksheet fails, the test 
			// would be considered as fail.And the same would be updated.
			Test_Util.ReportDataSetResult(Payroll_CreateEmployees_For_RecognitionScenarious_SuiteXls, "first", Test_Util.GetRowNum(Payroll_CreateEmployees_For_RecognitionScenarious_SuiteXls, this.getClass().getSimpleName()),"Pass");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_CreateEmployees_For_RecognitionScenarious_SuiteXls, "first", Test_Util.GetRowNum(Payroll_CreateEmployees_For_RecognitionScenarious_SuiteXls, this.getClass().getSimpleName()),"Fail");
		}	
		closeBrowser();
	}
}
