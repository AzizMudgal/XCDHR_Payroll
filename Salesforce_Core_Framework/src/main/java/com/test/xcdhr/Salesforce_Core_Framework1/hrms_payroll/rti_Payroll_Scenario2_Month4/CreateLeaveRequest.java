package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.rti_Payroll_Scenario2_Month4;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.HashMap;

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
	public String datefield;
	private HashMap<String,String> LeaveReqPageFieldNameStorage= new  HashMap<String,String>();
	public String datefield1;
	public String datefield2;
	public String inputdateone;
	public String inputdatetwo;
	public String ckbox;

	
	@BeforeTest
	public void CheckTestSkip() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		if(! Test_Util.IsTestcaseRunMode(Payroll_RecognitionScenarioTwo_SuiteXls, this.getClass().getSimpleName())){
			Skip=true;
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarioTwo_SuiteXls, "first", Test_Util.GetRowNum(Payroll_RecognitionScenarioTwo_SuiteXls, this.getClass().getSimpleName()),"Skipped");
			//Test_Util.ReportDataSetResult(Payroll_RecognitionScenarioTwo_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase" +this.getClass().getSimpleName() +" as the runmode is set to 'no' ");// this message would display in logs
			throw new Exception("Testcase is being skipped" + this.getClass().getSimpleName()+ "as it's Runmode is set to 'NO'"); // this msg would display in Reports.
		}
		// Load the runmodes of the tests
		runmodes=Test_Util.getDataSetRunmodes(Payroll_RecognitionScenarioTwo_SuiteXls, this.getClass().getSimpleName());
	}

	public String payfreqncy;
	boolean employeeFirsttimeView = true;
	boolean compensationFirsttimeView = true;
	boolean shouldOpenBrowser = true; 

	

	@Test(dataProvider = "getData")
	public void EmpsSetup_WithNICategory(String EmpName,String LeaveYear,String LeaveCategry,String LeaveStDate,String BirthdueDate,String QualifyingWeek,String AverageWeeklyEarnings,String EarningsRate,String PartnersFirstName,String PartnersMiddleName,String PartnersSurname,String PartnersNINONumber) throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
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
				if(existsElementchkFor1mts(OR.getProperty("Homepage_txt")))
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
		FetchEmployeeRecord(EmpName,LeaveYear,LeaveCategry,LeaveStDate,BirthdueDate,QualifyingWeek,AverageWeeklyEarnings,EarningsRate,PartnersFirstName,PartnersMiddleName,PartnersSurname,PartnersNINONumber);
		/*************************************************************************/
	}



	public void FetchEmployeeRecord(String EmpName,String LeaveYear,String LeaveCategry,String LeaveStDate,String BirthdueDate,String QualifyingWeek,String AverageWeeklyEarnings,String EarningsRate,String PartnersFirstName,String PartnersMiddleName,String PartnersSurname,String PartnersNINONumber) throws Throwable
	{
		try
		{
			if(employeeFirsttimeView)
			{
				employeeFirsttimeView = false;
				getObject("PersonalTab").click();
				if(existsElementchkFor1mts(OR.getProperty("PersonalText")))
				{
					System.out.println("I am in personal page");
					System.out.println("I am in personal page");
					if(existsElementchkFor1mts(OR.getProperty("EmployeeView")))
					{
						System.out.println("I recognised the Employee view");
						Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("EmployeeView"))));
						selectByValue.selectByVisibleText("DO NOT TOUCH PAYROLL AUTOMATION TESTING");
						Thread.sleep(2000L);
						if(existsElementchkFor1mts(OR.getProperty("ViewGoButton")))
						{
							getObject("ViewGoButton").sendKeys("");
							getObject("ViewGoButton").click();
						}
						Thread.sleep(7000L);
					}
				}
			}
			WebElement postsTable = driver.findElement(By.xpath(OR.getProperty("firstRecordOfNIcoulmnTable")));
			if(existsWebElement(postsTable))
			{
				List<WebElement> rows = postsTable.findElements(By.xpath(OR.getProperty("firstRecordOfNIcoulmnTableRows")));
				java.util.Iterator<WebElement> x = rows.iterator();
				lastRowCount = rows.size();
				int rownum = 1;	
				outerbreak:
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
								break outerbreak;
							}
						}
						
						else if(rownum == lastRowCount && AppnEmp!=null && AppnEmp!=(EmpName))
						{
							System.out.println("The row number of the page reached"+ rownum +" to 200 and"+ " 	Required Employee not found "
							+ "hence clicking the"	+ "	pagination link so that Employee search continues for next page");
							if(existsElementchkFor1mts(OR.getProperty("paginationElementPersonal")))
							{
								getObject
								("paginationNextPersonal").sendKeys("");
								getObject
								("paginationNextPersonal").click();
								System.out.println("As the required employees are "
								+ "not found in first page,hence clicked to next page of personal Tab");
								Thread.sleep
								(8000L);
								rownum = 0;
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
		LeaveTab(EmpName,LeaveYear,LeaveCategry,LeaveStDate,BirthdueDate,QualifyingWeek,AverageWeeklyEarnings,EarningsRate,PartnersFirstName,PartnersMiddleName,PartnersSurname,PartnersNINONumber);
	}
	


	public void LeaveTab(String EmpName,String LeaveYear,String LeaveCategry,String LeaveStDate,String BirthdueDate,String QualifyingWeek,String AverageWeeklyEarnings,String EarningsRate,String PartnersFirstName,String PartnersMiddleName,String PartnersSurname,String PartnersNINONumber)throws Throwable
	{
		try
		{
			if(existsElementchkFor1mts(OR.getProperty("leaveTabclk")))
			{
				getObject("leaveTabclk").sendKeys("");
				getObject("leaveTabclk").click();
				System.out.println("The leave tab got clicked");
				Thread.sleep(3000L);
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
		selectLeaveYear(EmpName,LeaveYear,LeaveCategry,LeaveStDate,BirthdueDate,QualifyingWeek,AverageWeeklyEarnings,EarningsRate,PartnersFirstName,PartnersMiddleName,PartnersSurname,PartnersNINONumber);
	}



	public void selectLeaveYear(String EmpName,String LeaveYear,String LeaveCategry,String LeaveStDate,String BirthdueDate,String QualifyingWeek,String AverageWeeklyEarnings,String EarningsRate,String PartnersFirstName,String PartnersMiddleName,String PartnersSurname,String PartnersNINONumber)throws Throwable
	{
		try
		{
			Thread.sleep(3000L);
			bookLeave(EmpName,LeaveYear,LeaveCategry,LeaveStDate,BirthdueDate,QualifyingWeek,AverageWeeklyEarnings,EarningsRate,PartnersFirstName,PartnersMiddleName,PartnersSurname,PartnersNINONumber);
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}
	


	public void bookLeave(String EmpName,String LeaveYear,String LeaveCategry,String LeaveStDate,String BirthdueDate,String QualifyingWeek,String AverageWeeklyEarnings,String EarningsRate,String PartnersFirstName,String PartnersMiddleName,String PartnersSurname,String PartnersNINONumber)throws Throwable
	{
		try
		{
			if(existsElementchkFor1mts(OR.getProperty("bookLeavebuttonlocator")))
			{
				getObject("bookLeavebuttonlocator").sendKeys("");
				getObject("bookLeavebuttonlocator").click();
			}
		}
		catch(Throwable t)
		{
			System.out.println("Book leave button did not got clicked");
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
		Thread.sleep(3000L);
		selectMaternityLeave(EmpName,LeaveYear,LeaveCategry,LeaveStDate,BirthdueDate,QualifyingWeek,AverageWeeklyEarnings,EarningsRate,PartnersFirstName,PartnersMiddleName,PartnersSurname,PartnersNINONumber);
	}



	public void selectMaternityLeave(String EmpName,String LeaveYear,String LeaveCategry,String LeaveStDate,String BirthdueDate,String QualifyingWeek,String AverageWeeklyEarnings,String EarningsRate,String PartnersFirstName,String PartnersMiddleName,String PartnersSurname,String PartnersNINONumber)throws Throwable
	{
		try
		{
			if(existsElementchkFor1mts(OR.getProperty("leaveCategorypicklistlocator")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("leaveCategorypicklistlocator"))));
				// This select by value needs to be called from OR.Properties
				//selectByValue.selectByValue(LeaveCategory);
				selectByValue.selectByVisibleText(LeaveCategry);
				System.out.println("");
				System.out.println("The MATERNITY PICK LIST ITEM got selected sucessfully");
			}
			Thread.sleep(3000L);
			if(existsElementchkFor1mts(OR.getProperty("submitLeaverqstlocator")))
			{
				getObject("submitLeaverqstlocator").sendKeys("");
				getObject("submitLeaverqstlocator").click();
				System.out.println("");
				System.out.println("The submit leave request button got clicked sucessfully");
			}
			Thread.sleep(3000L);
			if(existsElementchkFor1mts(OR.getProperty("leaveRequstOkbutton")))
			{
				getObject("leaveRequstOkbutton").sendKeys("");
				getObject("leaveRequstOkbutton").click();
				System.out.println("");
				System.out.println("The submit leave request ok button also got clicked sucessfully");
			}
			Thread.sleep(4000L);
		}
		catch(Throwable t)
		{
			System.out.println("Book leave did not happened");
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
		enterLeaveDates(EmpName,LeaveYear,LeaveCategry,LeaveStDate,BirthdueDate,QualifyingWeek,AverageWeeklyEarnings,EarningsRate,PartnersFirstName,PartnersMiddleName,PartnersSurname,PartnersNINONumber);
	}
	


	public void enterLeaveDates(String EmpName,String LeaveYear,String LeaveCategry,String LeaveStDate,String BirthdueDate,String QualifyingWeek,String AverageWeeklyEarnings,String EarningsRate,String PartnersFirstName,String PartnersMiddleName,String PartnersSurname,String PartnersNINONumber)throws Throwable
	{
		try
		{
			if(existsElementchkFor1mts(OR.getProperty("materinityEditbuttonlocator")))
			{
				getObject("materinityEditbuttonlocator").sendKeys("");
				getObject("materinityEditbuttonlocator").click();
			}
		}
		catch(Throwable t)
		{
			System.out.println("Edit button did not got clicked");
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
		selectCheckbox(EmpName,LeaveYear,LeaveCategry,LeaveStDate,BirthdueDate,QualifyingWeek,AverageWeeklyEarnings,EarningsRate,PartnersFirstName,PartnersMiddleName,PartnersSurname,PartnersNINONumber);
		MaternitySavebutton();
	}

	

	public void MaternitySavebutton()throws Throwable
	{
		try
		{
			if(existsElementchkFor1mts(OR.getProperty("maternitySavelocator")))
			{
				getObject("maternitySavelocator").sendKeys("");
				getObject("maternitySavelocator").click();
				System.out.println("");
				System.out.println("The save button got clicked sucessfully");
			}
			Thread.sleep(4000L);
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	

	public void selectCheckbox(String EmpName,String LeaveYear,String LeaveCategry,String LeaveStDate,String BirthdueDate,String QualifyingWeek,String AverageWeeklyEarnings,String EarningsRate,String PartnersFirstName,String PartnersMiddleName,String PartnersSurname,String PartnersNINONumber)throws Throwable
	{
			try
			{
				if(existsElementchkFor1mts(OR.getProperty("MatrnityStDate")))
				{
					getObject("MatrnityStDate").sendKeys("");
					String dateStr = LeaveStDate;
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
					getObject("MatrnityStDate").sendKeys(formattedDate);
					System.out.println("");
					System.out.println("The Maternity start date was entered sucessfully");	
					Thread.sleep(2000);
				}
			}
			catch(Throwable t)
			{
				System.out.println(t.getMessage().toString());
				System.out.println(t.getStackTrace().toString());
			}

			
			try
			{
				if(existsElementchkFor1mts(OR.getProperty("MatrnityBabyDueDate")))
				{
					getObject("MatrnityBabyDueDate").sendKeys("");
					String dateStr = BirthdueDate;
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
					getObject("MatrnityBabyDueDate").sendKeys(formattedDate);
					System.out.println("");
					System.out.println("The baby due date was entered sucessfully");	
					Thread.sleep(2000);
				}
			}
			catch(Throwable t)
			{
				System.out.println(t.getMessage().toString());
				System.out.println(t.getStackTrace().toString());
			}
			
			try
			{
				if(existsElementchkFor1mts(OR.getProperty("MatrnityAWE")))
				{
					getObject("MatrnityAWE").sendKeys("");
					getObject("MatrnityAWE").sendKeys(AverageWeeklyEarnings);
					System.out.println("");
					System.out.println("The AWE Value was entered sucessfully");
				}
				
				if(existsElementchkFor1mts(OR.getProperty("PartnersFirstname")))
				{
					getObject("PartnersFirstname").sendKeys("");
					getObject("PartnersFirstname").sendKeys(PartnersFirstName);
					System.out.println("");
					System.out.println("The partners first name was entered sucessfully");
				}
				
				if(existsElementchkFor1mts(OR.getProperty("partners2ndName")))
				{
					getObject("partners2ndName").sendKeys("");
					getObject("partners2ndName").sendKeys(PartnersMiddleName);
					System.out.println("");
					System.out.println("The partners 2nd name was entered sucessfully");
				}
				
				if(existsElementchkFor1mts(OR.getProperty("partnersSurname")))
				{
					getObject("partnersSurname").sendKeys("");
					getObject("partnersSurname").sendKeys(PartnersSurname);
					System.out.println("");
					System.out.println("The partners surname was entered sucessfully");
				}
				
				if(existsElementchkFor1mts(OR.getProperty("partnersNINO")))
				{
					getObject("partnersNINO").sendKeys("");
					getObject("partnersNINO").sendKeys(PartnersNINONumber);
					System.out.println("");
					System.out.println("The partners nino no was entered sucessfully");
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
		return Test_Util.getData(Payroll_RecognitionScenarioTwo_SuiteXls,"CreateLeaveRequest");
	}
	
	

	@AfterMethod
	public void ReportDataSetResult() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		if(Skip)
		{
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarioTwo_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
		}
		else if(Fail)
		{
			IsTestPass = false;
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarioTwo_SuiteXls, this.getClass().getSimpleName(), count+2, "Fail");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarioTwo_SuiteXls, this.getClass().getSimpleName(), count+2, "Pass");
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
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarioTwo_SuiteXls, "first", Test_Util.GetRowNum(Payroll_RecognitionScenarioTwo_SuiteXls, this.getClass().getSimpleName()),"Pass");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarioTwo_SuiteXls, "first", Test_Util.GetRowNum(Payroll_RecognitionScenarioTwo_SuiteXls, this.getClass().getSimpleName()),"Fail");
		}	
		closeBrowser();
	}
}
