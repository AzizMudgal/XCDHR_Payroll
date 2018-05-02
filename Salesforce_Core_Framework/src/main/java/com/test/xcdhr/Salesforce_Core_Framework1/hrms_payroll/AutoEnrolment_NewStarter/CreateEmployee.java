package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.AutoEnrolment_NewStarter;

import java.text.DateFormat;
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


public class CreateEmployee extends TestSuiteBase {

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
	public void CheckTestSkip() throws Exception{
		if(! Test_Util.IsTestcaseRunMode(Payroll_AutoEnrolment_Starter_SuiteXls, this.getClass().getSimpleName())){

			Skip=true;
			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Starter_SuiteXls, "first", Test_Util.GetRowNum(Payroll_AutoEnrolment_Starter_SuiteXls, this.getClass().getSimpleName()),"Skipped");
			//Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Starter_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase" +this.getClass().getSimpleName() +" as the runmode is set to 'no' ");// this message would display in logs

			throw new Exception("Testcase is being skipped" + this.getClass().getSimpleName()+ "as it's Runmode is set to 'NO'"); // this msg would display in Reports.

		}

		// Load the runmodes of the tests

		runmodes=Test_Util.getDataSetRunmodes(Payroll_AutoEnrolment_Starter_SuiteXls, this.getClass().getSimpleName());

	}
	
	public String payfreqncy;
	boolean employeeFirsttimeView = true;
	boolean AllowanceFirsttimeView = true;
	boolean shouldOpenBrowser = true; 
	boolean MyCompany = true;
	
	@Test(dataProvider="getData")
	public void EmpsSetup_WithNICategory(String empFirstName, String LastName,String Email, String UserName, String WorkMobile, String WorkPhone, String Profile, String ActivateLicense, String JobTitle,String Post,String Company,String EmploymentType, String Location, String EmploymentStatus, String Department, String PatternType, String NoOfWorkingDays, String ContractualHours, String SpinalPoint, String Manager, String StartDate, String ContinousStdate, String PayrollStDate, String Rejoiner ) throws Throwable
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
			login_To_QA_Org();

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

		// The script updates the BenifitAndAward for the Automation employees
		CreateNewJoiner1(empFirstName, LastName, Email, UserName, WorkMobile, WorkPhone, Profile,ActivateLicense,JobTitle,Post,Company,EmploymentType, Location, EmploymentStatus,  Department, PatternType, NoOfWorkingDays, ContractualHours, SpinalPoint, Manager, StartDate, ContinousStdate, PayrollStDate, Rejoiner);

		
	

		
		/*************************************************************************/
	
	
	}
	
	public void CreateNewJoiner1(String empFirstName, String LastName,String Email, String UserName, String WorkMobile, String WorkPhone, String Profile, String ActivateLicense,String JobTitle, String Post,String Company,String EmploymentType, String Location, String EmploymentStatus, String Department, String PatternType, String NoOfWorkingDays, String ContractualHours, String SpinalPoint, String Manager, String StartDate, String ContinousStdate, String PayrollStDate, String Rejoiner) throws Throwable
	{
		if(MyCompany)
		{
				try
				{
					if(existsElement(OR.getProperty("MyCompanyTab")))
					{
						getObject("MyCompanyTab").click();
					}
										
					if(existsElement(OR.getProperty("NewJoiner")))
					{
						System.out.println("I am in MyCompany page");
						Thread.sleep(1000L);
						getObject("NewJoiner").click();
						Thread.sleep(4000L);

					}
					
					if(existsElement(OR.getProperty("FirstName")))
					{
						getObject("FirstName").sendKeys(empFirstName);
					}
					Thread.sleep(2000L);
					if(existsElement(OR.getProperty("LastName")))
					{
						getObject("LastName").sendKeys(LastName);
					}
					Thread.sleep(2000L);
					if(existsElement(OR.getProperty("Email")))
					{
						getObject("Email").sendKeys(Email);
					}
					Thread.sleep(2000L);
					if(existsElement(OR.getProperty("UserName")))
					{
						getObject("UserName").sendKeys(UserName);
					}
					Thread.sleep(2000L);
					if(existsElement(OR.getProperty("WorkMobile")))
					{
						getObject("WorkMobile").sendKeys(WorkMobile);
					}
					Thread.sleep(2000L);
					if(existsElement(OR.getProperty("WorkPhone")))
					{
						getObject("WorkPhone").sendKeys(WorkPhone);
					}
					Thread.sleep(2000L);
					if(existsElement(OR.getProperty("ActivateLicense")))
					{
						Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("ActivateLicense"))));
						// This select by value needs to be called from OR.Properties
						selectByValue.selectByValue(ActivateLicense);
					}
					
										
				}
				catch(Throwable t)
				{
					System.out.println(t.getMessage().toString());
					System.out.println(t.getStackTrace().toString());
				}
				
		}	
		
		Thread.sleep(2000L);
		if(existsElement(OR.getProperty("JobTitle")))
		{
			getObject("JobTitle").sendKeys(JobTitle);
			
		}
		
			Thread.sleep(2000L);
			if(existsElement(OR.getProperty("Companylistbox")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("Companylistbox"))));
				// This select by value needs to be called from OR.Properties
				selectByValue.selectByValue(Company);
			}
			Thread.sleep(2000L);
			if(existsElement(OR.getProperty("EmplymentType")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("EmplymentType"))));
				// This select by value needs to be called from OR.Properties
				selectByValue.selectByValue(EmploymentType);
			}
			Thread.sleep(2000L);
			if(existsElement(OR.getProperty("Location")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("Location"))));
				// This select by value needs to be called from OR.Properties
				selectByValue.selectByValue(Location);
			}
			Thread.sleep(2000L);
			if(existsElement(OR.getProperty("EmplymntStatus")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("EmplymntStatus"))));
				// This select by value needs to be called from OR.Properties
				selectByValue.selectByValue(EmploymentStatus);
			}
			
			Thread.sleep(5000);
			getObject("Departmentlookup").click();
			Thread.sleep(5000);
			String mainHandle2 = driver.getWindowHandle(); // To save the parent window
			// create one more method for reading employee from excel sheet.
			ReadEmployee(Department);
			Thread.sleep(2000L);
			driver.switchTo().window(mainHandle2); // finally switch back to parent window and perform the operations.
			Thread.sleep(2000L);
			if(existsElement(OR.getProperty("PatternType")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("PatternType"))));
				// This select by value needs to be called from OR.Properties
				selectByValue.selectByValue(PatternType);
			}
			Thread.sleep(2000L);
			if(existsElement(OR.getProperty("NoWorkingDays")))
			{
				getObject("NoWorkingDays").clear();
				Thread.sleep(1000L);
				getObject("NoWorkingDays").sendKeys(NoOfWorkingDays);
			}
			Thread.sleep(2000L);
			if(existsElement(OR.getProperty("ContractualHours")))
			{
				getObject("ContractualHours").clear();
				getObject("ContractualHours").sendKeys(ContractualHours);
			}
			/*
			Thread.sleep(2000L);
			if(existsElement(OR.getProperty("SpinalPoint")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("SpinalPoint"))));
				// This select by value needs to be called from OR.Properties
				selectByValue.selectByValue(SpinalPoint);
			}
			
			Thread.sleep(5000);
			*/
			getObject("SelectManager").click();
			Thread.sleep(5000);
			String mainHandle3 = driver.getWindowHandle(); // To save the parent window
			// create one more method for reading employee from excel sheet.
			ReadEmployee(Manager);
			Thread.sleep(2000L);
			driver.switchTo().window(mainHandle3); // finally switch back to parent window and perform the operations.
			Thread.sleep(2000L);
			//// still emp start date etc needs to be input.
			System.out.println("Still emp start date needs to input");
			keyDates(StartDate, ContinousStdate, PayrollStDate);
			Thread.sleep(2000L);
		
			getObject("clikOutside").click();
			Thread.sleep(2000L);
			if(existsElement(OR.getProperty("CreateJoinerButton")))
			{
				getObject("CreateJoinerButton").sendKeys("");
				getObject("CreateJoinerButton").click();
				System.out.println("joiner button got clicked");
				Thread.sleep(10000L);
			}
			
			
	}
					
	
	public void ReadEmployee(String Post)throws Throwable
	{
		
		String[] handles = driver.getWindowHandles().toArray(new String[0]); // To get the child window(s)
		driver.switchTo().window(handles[handles.length - 1]); 
			
				String axb=  driver.getTitle();
				System.out.println(axb);
				if (driver.getTitle().equalsIgnoreCase(axb))
				{
					WebElement dddframe1 = driver.findElement(By.id("searchFrame"));  // you encountered two frames so, find the frame id and save as webelement
					driver.switchTo().frame(dddframe1); // now using the frame id and switch to the frame
					System.out.println("title is matching");
						System.out.println("I am now in the child window");
						Thread.sleep(3000);
						getObject("searchField").clear();
						Thread.sleep(3000);
						getObject("searchField").sendKeys(Post);
						System.out.println("I entered the empname reading from excel sheet");
						Thread.sleep(2000);
						getObject("Gobutton").click();
						System.out.println("I clicked Go button");
						Thread.sleep(5000);
						driver.switchTo().defaultContent();        // now that you encountered one more frame hence switch back to main page
						WebElement dddframe2 = driver.findElement(By.id("resultsFrame"));// and save the frame id and 
						driver.switchTo().frame(dddframe2); // switch to the other frame and perform the operations
						System.out.println("I switched to Results Frame");
						Thread.sleep(2000L);
						if(existsElement(OR.getProperty("clkSortedone1")))
						{
							getObject("clkSortedone1").click();
						}
						else if(existsElement(OR.getProperty("clkSortedone2")))
						{
							getObject("clkSortedone2").click();
						}
						
						
						System.out.println("I clicked the user finally");

				}
			
	}
	
	
	public void keyDates(String StartDate, String ContinousStdate, String PayrollStDate)throws Throwable
	{
		try
		{
			
			if(existsElement(OR.getProperty("StDate")))
			{
				
				getObject("StDate").sendKeys("");
				String dateStr = StartDate;
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
				getObject("StDate").sendKeys(formattedDate);
					
				
			}

	}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
		
		try
		{
			
			if(existsElement(OR.getProperty("continousServiceDate")))
			{
				
				getObject("continousServiceDate").sendKeys("");
				String dateStr = ContinousStdate;
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
				getObject("continousServiceDate").sendKeys(formattedDate);
					Thread.sleep(3000L);
			}
			
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
		
		
		try
		{
			
			if(existsElement(OR.getProperty("payrollStartDate")))
			{
				
				getObject("payrollStartDate").sendKeys("");
				String dateStr = PayrollStDate;
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
				getObject("payrollStartDate").sendKeys(formattedDate);
					Thread.sleep(3000L);
			}
			
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
		
		
		
	}
	
	
	
	
	@DataProvider
	public Object[][] getData()
	{

		return Test_Util.getData(Payroll_AutoEnrolment_Starter_SuiteXls,"CreateEmployee");
	}


	@AfterMethod
	public void ReportDataSetResult()
	{
		if(Skip){
			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Starter_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
		}else if(Fail)
		{

			IsTestPass = false;

			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Starter_SuiteXls, this.getClass().getSimpleName(), count+2, "Fail");
		}else
		{
			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Starter_SuiteXls, this.getClass().getSimpleName(), count+2, "Pass");
		}

		Skip=false;
		Fail=false;


	}


	@AfterTest
	public void ReportTestResult(){

		if(IsTestPass){

			// This will update the testresult in the first worksheet where in for that test case , even if one of the test data specified in second worksheet fails, the test 
			// would be considered as fail.And the same would be updated.

			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Starter_SuiteXls, "first", Test_Util.GetRowNum(Payroll_AutoEnrolment_Starter_SuiteXls, this.getClass().getSimpleName()),"Pass");

		}else{

			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Starter_SuiteXls, "first", Test_Util.GetRowNum(Payroll_AutoEnrolment_Starter_SuiteXls, this.getClass().getSimpleName()),"Fail");

		}	

		closeBrowser();
	}


	
	
}
