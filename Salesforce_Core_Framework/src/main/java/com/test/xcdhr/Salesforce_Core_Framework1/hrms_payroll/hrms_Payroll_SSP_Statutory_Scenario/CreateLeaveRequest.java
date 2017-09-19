package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.hrms_Payroll_SSP_Statutory_Scenario;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.ErrorUtil;
import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Test_Util;


public class CreateLeaveRequest extends TestSuiteBase
{

	String runmodes[] = null;
	static int count = -1;
	static int countCompensation = -1;

	public static boolean Fail=false;
	public static boolean Skip=false;
	public static boolean IsTestPass=true;
	public String cancelbuttn;
	public String leaveRequestId;
	public String leaveStDate;
	public String leaveEndDate;
	public String leaveCategory;
	public String leaveType;
	public String leaveapprovalStatus;
	


	@BeforeTest
	public void CheckTestSkip() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);

		if(! Test_Util.IsTestcaseRunMode(Payroll_SSP_ProcessPayroll_SuiteXls, this.getClass().getSimpleName())){
			Skip=true;
			Test_Util.ReportDataSetResult(Payroll_SSP_ProcessPayroll_SuiteXls, "first", Test_Util.GetRowNum(Payroll_SSP_ProcessPayroll_SuiteXls, this.getClass().getSimpleName()),"Skipped");
			//Test_Util.ReportDataSetResult(Payroll_SSP_ProcessPayroll_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase" +this.getClass().getSimpleName() +" as the runmode is set to 'no' ");// this message would display in logs
			throw new Exception("Testcase is being skipped" + this.getClass().getSimpleName()+ "as it's Runmode is set to 'NO'"); // this msg would display in Reports.
		}
		// Load the runmodes of the tests
		runmodes=Test_Util.getDataSetRunmodes(Payroll_SSP_ProcessPayroll_SuiteXls, this.getClass().getSimpleName());
	}

	public String payfreqncy;
	boolean employeeFirsttimeView = true;
	boolean compensationFirsttimeView = true;
	boolean shouldOpenBrowser = true; 


	@Test(dataProvider = "getData")
	public void EmpsSetup_WithNICategory(String EmpName,String LeaveYear,String LeaveCategry,String leaveStDate, String LeaveEndDate,String StatutoryPaybasis,String Conditionsatisfd) throws Throwable
	{
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
				if(existsElement(OR.getProperty("Homepage_txt")))
				{
					Assert.assertEquals(driver.getTitle(), "Salesforce - Enterprise Edition");
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
		FetchEmployeeRecord(EmpName,LeaveYear,LeaveCategry,leaveStDate,LeaveEndDate,StatutoryPaybasis,Conditionsatisfd);
		/*************************************************************************/
	}

	

	public void FetchEmployeeRecord(String EmpName,String LeaveYear,String LeaveCategory,String leaveStDate, String LeaveEndDate,String StatutoryPaybasis,String Conditionsatisfd) throws Throwable
	{
		try
		{
			if(employeeFirsttimeView)
			{
				employeeFirsttimeView = false;
				getObject("PersonalTab").click();
				System.out.println("I am in personal page");
				if(existsElement(OR.getProperty("EmployeeView")))
				{
					System.out.println("I recognised the Employee view");
					Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("EmployeeView"))));
					selectByValue.selectByVisibleText("DO NOT TOUCH PAYROLL AUTOMATION TESTING");
					Thread.sleep(2000L);
					if(existsElement(OR.getProperty("ViewGoButton")))
					{
						getObject("ViewGoButton").sendKeys("");
						getObject("ViewGoButton").click();
					}
					Thread.sleep(7000L);
				}
			}
			WebElement postsTable = driver.findElement(By.xpath(OR.getProperty("firstRecordOfNIcoulmnTable")));
			if(existsWebElement(postsTable))
			{
				List<WebElement> rows = postsTable.findElements(By.xpath(OR.getProperty("firstRecordOfNIcoulmnTableRows")));
				java.util.Iterator<WebElement> x = rows.iterator();
				int rownum = 1;			
				while(x.hasNext())
				{
					String firstRowOfEmployeeColumn="//div["+rownum+"]/table/tbody/tr/td[4]/div/a/span";
					WebElement firstEmployee= driver.findElement(By.xpath(firstRowOfEmployeeColumn));
					if(existsWebElement(firstEmployee))
					{
						String AppnEmp= firstEmployee.getText();
						//System.out.println(tempEmp+"-------"+empName+"------"+rownum);
						if(AppnEmp!=null && AppnEmp.equalsIgnoreCase(EmpName))
						{
							System.out.println("Employee matched");
							System.out.println("Employee name is  :"+EmpName);
							if(existsWebElement(firstEmployee))
							{
								firstEmployee.click();
								System.out.println("The employee namely :"+AppnEmp+"got clicked");
								break;
							}
						}
						rownum++;
					}
				}
			}
		}
		catch(Throwable t)
		{
			APP_LOGS.debug(" Check for error in NI Category method");
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
			ErrorUtil.addVerificationFailure(t);
			System.out.println("");
		}
		Thread.sleep(3000L);
		LeaveTab(LeaveYear,LeaveCategory,leaveStDate,LeaveEndDate,StatutoryPaybasis,Conditionsatisfd);
	}


	public void LeaveTab(String LeaveYear,String LeaveCategory,String leaveStDate, String LeaveEndDate,String StatutoryPaybasis,String Conditionsatisfd)throws Throwable
	{
		try
		{
			if(existsElement(OR.getProperty("leaveTabclk")))
			{
				getObject("leaveTabclk").sendKeys("");
				getObject("leaveTabclk").click();
				Thread.sleep(3000L);
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
		bookLeave(LeaveYear,LeaveCategory,leaveStDate,LeaveEndDate,StatutoryPaybasis,Conditionsatisfd);
	}


	
	public void bookLeave(String LeaveYear,String LeaveCategory,String leaveStDate, String LeaveEndDate,String StatutoryPaybasis,String Conditionsatisfd)throws Throwable
	{
		try
		{
			if(existsElement(OR.getProperty("bookLeavebuttonlocator")))
			{
				getObject("bookLeavebuttonlocator").sendKeys("");
				getObject("bookLeavebuttonlocator").click();
			}
			Thread.sleep(3000L);
			if(existsElement(OR.getProperty("leaveCategorypicklistlocator")))
			{
				selectMaternityLeave(LeaveYear,LeaveCategory,leaveStDate,LeaveEndDate,StatutoryPaybasis,Conditionsatisfd);
			}
		}
		catch(Throwable t)
		{
			System.out.println("Book leave button did not got clicked");
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}


	public void selectMaternityLeave(String LeaveYear,String LeaveCategory,String leaveStDate, String LeaveEndDate,String StatutoryPaybasis,String Conditionsatisfd)throws Throwable
	{
		try
		{
			if(existsElement(OR.getProperty("leaveCategorypicklistlocator")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("leaveCategorypicklistlocator"))));
				// This select by value needs to be called from OR.Properties
				selectByValue.selectByValue(LeaveCategory);
				System.out.println("");
				System.out.println("The Sick leave ITEM got selected sucessfully");
			}
			Thread.sleep(3000L);
			//illnessForSickleave
			if(existsElement(OR.getProperty("illnessForSickleave")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("illnessForSickleave"))));
				// This select by value needs to be called from OR.Properties
				selectByValue.selectByValue("Back & neck");
				System.out.println("");
				System.out.println("The illness ITEM got selected sucessfully");
			}
			Thread.sleep(3000L);
			keyDates(leaveStDate,LeaveEndDate);
		}
		catch(Throwable t)
		{
			System.out.println("Book leave did not happened");
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}
	

	public void keyDates(String leaveStDate, String LeaveEndDate)throws Throwable
	{
		try
		{
			if(existsElement(OR.getProperty("sickLeaveStDatelocator")))
			{
				getObject("sickLeaveStDatelocator").sendKeys("");
				getObject("sickLeaveStDatelocator").clear();
				String dateStr = leaveStDate;
				DateFormat readFormat = new SimpleDateFormat("MM/dd/yyyy");
				DateFormat writeFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date date = null;				
				try 
				{
					date = readFormat.parse( dateStr.trim() );
					System.out.println(date.toString());
				} 
				catch ( ParseException e ) 
				{
					System.out.println(e.getMessage());
				}
				String formattedDate = null;
				if( date != null ) 
				{
					formattedDate = writeFormat.format( date );
				}
				System.out.println("The entered date is  " +formattedDate);		
				Thread.sleep(4000L);
				getObject("sickLeaveStDatelocator").sendKeys(formattedDate);
				System.out.println("");
				System.out.println("The leave st date was entered sucessfully");	
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
		Thread.sleep(3000L);
		try
		{
			if(existsElement(OR.getProperty("sickLeaveEndDatelocator")))
			{
				getObject("sickLeaveEndDatelocator").sendKeys("");
				getObject("sickLeaveEndDatelocator").clear();
				Thread.sleep(1000L);
				String dateStr = LeaveEndDate;
				DateFormat readFormat = new SimpleDateFormat("MM/dd/yyyy");
				DateFormat writeFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date date = null;				
				try 
				{
					date = readFormat.parse( dateStr.trim() );
					System.out.println(date.toString());
				} 
				catch ( ParseException e ) 
				{
					System.out.println(e.getMessage());
				}
				String formattedDate = null;
				if( date != null ) 
				{
					formattedDate = writeFormat.format( date );
				}
				System.out.println("The entered date is  " +formattedDate);		
				Thread.sleep(4000L);
				getObject("sickLeaveEndDatelocator").sendKeys(formattedDate);
				System.out.println("");
				System.out.println("The sick leave end date was entered sucessfully");
				Thread.sleep(3000L);
			}
			submitSickleave();
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
					
	}
	
	
	public void submitSickleave()throws Throwable
	{
		try
		{  
			if(existsElement(OR.getProperty("sickleaveSubmitrequest")))
			{
				getObject("sickleaveSubmitrequest").sendKeys("");
				getObject("sickleaveSubmitrequest").click();
				System.out.println("The submit leave request button got clicked sucessfully");				
			}
			Thread.sleep(7000L);
			if(existsElement(OR.getProperty("leaveRequstOkbutton")))
			{
				getObject("leaveRequstOkbutton").sendKeys("");
				getObject("leaveRequstOkbutton").click();
				System.out.println("");
				System.out.println("The submit leave request ok button also got clicked sucessfully");
			}
			else if(!existsElement(OR.getProperty("leaveRequstOkbutton")))
			{
				submitSickleave();
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

		return Test_Util.getData(Payroll_SSP_ProcessPayroll_SuiteXls,"CreateLeaveRequest");
	}

	
	
	@AfterMethod
	public void ReportDataSetResult() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);

		if(Skip)
		{
			Test_Util.ReportDataSetResult(Payroll_SSP_ProcessPayroll_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
		}
		else if(Fail)
		{
			IsTestPass = false;
			Test_Util.ReportDataSetResult(Payroll_SSP_ProcessPayroll_SuiteXls, this.getClass().getSimpleName(), count+2, "Fail");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_SSP_ProcessPayroll_SuiteXls, this.getClass().getSimpleName(), count+2, "Pass");
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

			Test_Util.ReportDataSetResult(Payroll_SSP_ProcessPayroll_SuiteXls, "first", Test_Util.GetRowNum(Payroll_SSP_ProcessPayroll_SuiteXls, this.getClass().getSimpleName()),"Pass");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_SSP_ProcessPayroll_SuiteXls, "first", Test_Util.GetRowNum(Payroll_SSP_ProcessPayroll_SuiteXls, this.getClass().getSimpleName()),"Fail");
		}	

		closeBrowser();
	 }

}
