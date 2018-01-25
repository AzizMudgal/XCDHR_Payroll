package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.hrms_Payroll_ShPP_Statutory_Scenario;


import java.text.DateFormat;
//import java.text.DecimalFormat;
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
	public String formattedDate;


	@BeforeTest
	public void CheckTestSkip() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		if(! Test_Util.IsTestcaseRunMode(Payroll_Statutory_SharedParentalpay_SuiteXls, this.getClass().getSimpleName()))
		{
			Skip=true;
			Test_Util.ReportDataSetResult(Payroll_Statutory_SharedParentalpay_SuiteXls, "first", Test_Util.GetRowNum(Payroll_Statutory_SharedParentalpay_SuiteXls, this.getClass().getSimpleName()),"Skipped");
			//Test_Util.ReportDataSetResult(Payroll_Statutory_SharedParentalpay_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase" +this.getClass().getSimpleName() +" as the runmode is set to 'no' ");// this message would display in logs
			throw new Exception("Testcase is being skipped" + this.getClass().getSimpleName()+ "as it's Runmode is set to 'NO'"); // this msg would display in Reports.
		}
		// Load the runmodes of the tests
		runmodes=Test_Util.getDataSetRunmodes(Payroll_Statutory_SharedParentalpay_SuiteXls, this.getClass().getSimpleName());
	}

	public String payfreqncy;
	boolean employeeFirsttimeView = true;
	boolean compensationFirsttimeView = true;
	boolean shouldOpenBrowser = true; 




	@Test(dataProvider = "getData")
	public void EmpsSetup_WithNICategory(String EmpName,String LeaveYear,String LeaveCategry,String BirthdueDate, String BabyBorndate, String LeaveStDate,String LeaveEndDate,String StatutoryPaybasis,String Conditionsatisfd) throws Throwable
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
				if(existsElementchkFor1mts(OR.getProperty("PersonalTab")))
				{
					String personalTab = getObject("PersonalTab").getText();
					System.out.println("Tab name is :"+ personalTab);
					Assert.assertEquals("Personal", personalTab);
					System.out.println("The test script verified that it successfully logged into XCD HR Org.");
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
		FetchEmployeeRecord(EmpName,LeaveYear,LeaveCategry,BirthdueDate,BabyBorndate,LeaveStDate,LeaveEndDate,StatutoryPaybasis,Conditionsatisfd);
		/*************************************************************************/
	}



	public void FetchEmployeeRecord(String EmpName,String LeaveYear,String LeaveCategory,String BirthdueDate, String BabyBorndate, String LeaveStDate,String LeaveEndDate,String StatutoryPaybasis,String Conditionsatisfd) throws Throwable
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
				lastRowCount = rows.size();
				java.util.Iterator<WebElement> x = rows.iterator();
				int rownum = 1;	
				outerbreak:
					while(x.hasNext())
					{
						String firstRowOfEmployeeColumn="//div["+rownum+"]/table/tbody/tr/td[4]/div/a/span";
						WebElement firstEmployee= driver.findElement(By.xpath(firstRowOfEmployeeColumn));
						if(existsWebElement(firstEmployee))
						{
							String AppnEmp= firstEmployee.getText();
							System.out.println(AppnEmp+"-------"+EmpName+"------"+rownum);
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
								System.out.println("The row number of the page reached"+ rownum +" to 200 and"
										+ " Required Employee not found hence clicking the"
										+ " pagination link so that Employee search continues for next page");
								if (existsElementchkFor1mts(OR.getProperty("paginationElementPersonal")))
								{
									getObject("paginationNextPersonal").sendKeys("");
									getObject("paginationNextPersonal").click();
									System.out.println("As the required employees are not found in first page,hence clicked to next page of personal Tab");
									Thread.sleep(8000L);
									rownum = 0;
								}
							}
						}
						rownum++;
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
		LeaveTab(LeaveYear,LeaveCategory,BirthdueDate,BabyBorndate,LeaveStDate,LeaveEndDate,StatutoryPaybasis,Conditionsatisfd);
		//Thread.sleep(3000L);
	}



	public void LeaveTab(String LeaveYear,String LeaveCategory,String BirthdueDate, String BabyBorndate, String LeaveStDate,String LeaveEndDate,String StatutoryPaybasis,String Conditionsatisfd)throws Throwable
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
		Thread.sleep(3000L);
		bookLeave(LeaveYear,LeaveCategory,BirthdueDate,BabyBorndate,LeaveStDate,LeaveEndDate,StatutoryPaybasis,Conditionsatisfd);
	}


	public void bookLeave(String LeaveYear,String LeaveCategory,String BirthdueDate, String BabyBorndate, String LeaveStDate,String LeaveEndDate,String StatutoryPaybasis,String Conditionsatisfd)throws Throwable
	{
		try
		{
			if(existsElementchkFor1mts(OR.getProperty("bookLeavebuttonlocator")))
			{
				String bookLeaveText = getObject("bookLeavebuttonlocator").getAttribute("value");
				System.out.println("the book leave button text is :"+bookLeaveText);
				Assert.assertEquals("Book leave", bookLeaveText);
				System.out.println("The book leave button of the leave tab of the employee's Record is displayed successfully");
				getObject("bookLeavebuttonlocator").sendKeys("");
				getObject("bookLeavebuttonlocator").click();
				System.out.println("Book leave button got clicked");
			}
		}
		catch(Throwable t)
		{
			System.out.println("Book leave button did not got clicked");
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
		Thread.sleep(3000L);
		selectMaternityLeave(LeaveYear,LeaveCategory,BirthdueDate,BabyBorndate,LeaveStDate,LeaveEndDate,StatutoryPaybasis,Conditionsatisfd);
	}




	public void selectMaternityLeave(String LeaveYear,String LeaveCategory,String BirthdueDate, String BabyBorndate, String LeaveStDate,String LeaveEndDate,String StatutoryPaybasis,String Conditionsatisfd)throws Throwable
	{
		try
		{
			if(existsElementchkFor1mts(OR.getProperty("sickleaveSubmitrequest")))
			{
				String submitLeaveRequestBtnText = getObject("sickleaveSubmitrequest").getAttribute("value");
				System.out.println("The leave submit request button is displayed successfully");
				Assert.assertEquals("Submit leave request", submitLeaveRequestBtnText);
			}
			if(existsElement(OR.getProperty("leaveCategorypicklistlocator")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("leaveCategorypicklistlocator"))));
				// This select by value needs to be called from OR.Properties
				selectByValue.selectByValue(LeaveCategory);
				System.out.println("");
				System.out.println("The MATERNITY PICK LIST ITEM got selected sucessfully");
				Thread.sleep(3000L);
				if(existsElementchkFor1mts(OR.getProperty("submitLeaverqstlocator")))
				{
					submitSickleave();
					System.out.println("Hence the sick leave got created sucessfully");
				}
			}

		}
		catch(Throwable t)
		{
			System.out.println("Book leave did not happened");
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
		enterLeaveDates(BirthdueDate,BabyBorndate,LeaveStDate,LeaveEndDate,StatutoryPaybasis,Conditionsatisfd);
	}




	public void enterLeaveDates(String BirthdueDate, String BabyBorndate, String LeaveStDate,String LeaveEndDate,String StatutoryPaybasis,String ConditionSatisfied)throws Throwable
	{
		try
		{

			if(existsElement(OR.getProperty("sickLeaveEditbuttnLocator")))
			{
				getObject("sickLeaveEditbuttnLocator").sendKeys("");
				getObject("sickLeaveEditbuttnLocator").click();
				Thread.sleep(6000L);
				Thread.sleep(6000L);
				if(existsElement(OR.getProperty("leaveRecordEditMode")))
				{
					String empLabelTxtInLeaveEditMode = getObject("leaveRecordEditMode").getText();
					Assert.assertEquals("Employee", empLabelTxtInLeaveEditMode);
					System.out.println("We are in the sick record edit mode");
				}
			}
		}
		catch(Throwable t)
		{
			System.out.println("Edit button did not got clicked");
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}

		/*
		 * The following methods enters the required dates,selects the statutory
		 * conditions and pics the pymt basis by reading from input
		 * script and creates the leave requests.
		 * 
		 */

		keyDates(BirthdueDate,BabyBorndate,LeaveStDate,LeaveEndDate);

		if(existsElement(OR.getProperty("sspEditTable")))
		{
			selectCheckbox(StatutoryPaybasis,ConditionSatisfied);
			StatutoryPaybasis(StatutoryPaybasis);
		}
	}



	public void keyDates(String BirthdueDate, String BabyBorndate, String LeaveStDate,String LeaveEndDate)throws Throwable
	{
		try
		{
			if(existsElement(OR.getProperty("birthduedatelocator")))
			{
				getObject("birthduedatelocator").sendKeys("");
				String dateStr = BirthdueDate;
				dateFormaterMethod(dateStr);
				getObject("birthduedatelocator").sendKeys(formattedDate);
				System.out.println("");
				System.out.println("The Birth due date was entered sucessfully");	
			}
			else if(existsElement(OR.getProperty("QAOrgbirthduedatelocator")))
			{
				getObject("QAOrgbirthduedatelocator").sendKeys("");
				String dateStr = BirthdueDate;
				dateFormaterMethod(dateStr);
				getObject("QAOrgbirthduedatelocator").sendKeys(formattedDate);
				System.out.println("");
				System.out.println("selected from qa org locator The Birth due date was entered sucessfully");	
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}

		try
		{
			if(existsElement(OR.getProperty("babyBornDatelocator")))
			{
				getObject("babyBornDatelocator").sendKeys("");
				String dateStr = BabyBorndate;
				dateFormaterMethod(dateStr);
				getObject("babyBornDatelocator").sendKeys(formattedDate);
				System.out.println("");
				System.out.println("The baby born date was entered sucessfully");
				Thread.sleep(3000L);
			}
			else if(existsElement(OR.getProperty("QAOrgbabyBornDatelocator")))
			{
				getObject("QAOrgbabyBornDatelocator").sendKeys("");
				String dateStr = BabyBorndate;
				dateFormaterMethod(dateStr);
				getObject("QAOrgbabyBornDatelocator").sendKeys(formattedDate);
				System.out.println("");
				System.out.println("selected from qa org locator The baby born date was entered sucessfully");
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

			if(existsElement(OR.getProperty("leaveReqstStdate")))
			{

				getObject("leaveReqstStdate").sendKeys("");
				String dateStr = LeaveStDate;
				dateFormaterMethod(dateStr);
				getObject("leaveReqstStdate").sendKeys(formattedDate);
				System.out.println("");
				System.out.println("The Leave request start date was entered sucessfully");
				Thread.sleep(3000L);
			}
			else if(existsElement(OR.getProperty("QAOrgleaveReqstStdate")))
			{
				getObject("QAOrgleaveReqstStdate").sendKeys("");
				String dateStr = LeaveStDate;
				dateFormaterMethod(dateStr);
				getObject("QAOrgleaveReqstStdate").sendKeys(formattedDate);
				System.out.println("");
				System.out.println("The Leave request start date was entered sucessfully");
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

			if(existsElement(OR.getProperty("leaveReqstEndDate")))
			{
				getObject("leaveReqstEndDate").sendKeys("");
				String dateStr = LeaveEndDate;
				dateFormaterMethod(dateStr);
				getObject("leaveReqstEndDate").sendKeys(formattedDate);
				System.out.println("");
				System.out.println("The Leave request end date was entered sucessfully");
				Thread.sleep(3000L);
			}
			else if(existsElement(OR.getProperty("QAOrgleaveReqstEndDate")))
			{
				getObject("QAOrgleaveReqstEndDate").sendKeys("");
				String dateStr = LeaveEndDate;
				dateFormaterMethod(dateStr);
				getObject("QAOrgleaveReqstEndDate").sendKeys(formattedDate);
				System.out.println("");
				System.out.println("The Leave request end date was entered sucessfully");
				Thread.sleep(3000L);
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}



	public void dateFormaterMethod(String dateStr)throws Throwable
	{
		try
		{
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

			formattedDate = null;
			if( date != null ) 
			{
				formattedDate = writeFormat.format( date );
			}
			System.out.println("The entered date is  " +formattedDate);		
			Thread.sleep(4000L);
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}



	public void selectCheckbox(String StatutoryPaybasis,String ConditionSatisfied)throws Throwable
	{
		try
		{
			Thread.sleep(4000L);
			WebElement postsTable = driver.findElement(By.xpath(OR.getProperty("sspEditTable")));
			if(existsWebElement(postsTable))
			{
				System.out.println("details table exists");
				List<WebElement> rows = postsTable.findElements(By.xpath(OR.getProperty("sspEditTableRows")));	
				System.out.println("NUMBER OF ROWS IN THIS TABLE = "+rows.size());
				int row_num,col_num;
				row_num=1;
				outerloop:
					for(WebElement trElement : rows)
					{
						List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
						System.out.println("NUMBER OF COLUMNS="+td_collection.size());
						col_num=1;
						for(WebElement tdElement : td_collection)
						{
							System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
							if(tdElement.getText()!=null && tdElement.getText().equalsIgnoreCase("Statutory conditions met - make payment"))
							{
								System.out.println("Label name  :"+tdElement.getText()+ "  matched ");
								ckbox ="//following-sibling::td[1][@class='dataCol col02']/input[@type='checkbox'][@value='1']";
								WebElement clkchkbox = driver.findElement(By.xpath(ckbox));
								boolean	smallERchekbox = clkchkbox.isSelected();
								if(smallERchekbox)
								{
									System.out.println("yes the condition is checked");
								}
								double valueOfsmallReliefChkbox = Double.parseDouble(ConditionSatisfied);
								System.out.println("converted smallER value is :"+valueOfsmallReliefChkbox);
								if(valueOfsmallReliefChkbox == 1.0)
								{
									Thread.sleep(4000L);
									if(smallERchekbox)
									{
										System.out.println("Statutory conditions met - make paymentcheckbox was allready checked, Hence our condition got satisfied");
										break  outerloop;
									}
									else
									{
										clkchkbox.sendKeys("");
										clkchkbox.click();
										System.out.println("Statutory conditions met - make payment checkbox was NOT checked,and now checked hence Condition now satisfied successfully");
										break  outerloop;
									}
								}	

								col_num++;
							}
							row_num++;
						}
					}
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	} 



	public void StatutoryPaybasis(String StatutoryPaybasis)throws Throwable
	{
		try
		{
			Thread.sleep(4000L);
			WebElement postsTable = driver.findElement(By.xpath(OR.getProperty("sspEditTable")));
			if(existsWebElement(postsTable))
			{
				System.out.println("details table exists");
				List<WebElement> rows = postsTable.findElements(By.xpath(OR.getProperty("sspEditTableRows")));	
				System.out.println("NUMBER OF ROWS IN THIS TABLE = "+rows.size());
				int row_num,col_num;
				row_num=1;
				outerloop:
					for(WebElement trElement : rows)
					{
						List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
						System.out.println("NUMBER OF COLUMNS="+td_collection.size());
						col_num=1;
						for(WebElement tdElement : td_collection)
						{
							System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
							if(tdElement.getText()!=null && tdElement.getText().equalsIgnoreCase("Payment basis"))
							{
								System.out.println("Label name  :"+tdElement.getText()+ "  matched ");
								//String imglookup ="//following-sibling::td[1]/span/a[@id='CF00N0O00000D0w79_lkwgt']/img";
								String imglookup ="//following-sibling::td[1]/span/a[contains(@id,'_lkwgt')][@title='Payment basis Lookup (New Window)']/img";

								WebElement clkchkbox = driver.findElement(By.xpath(imglookup));
								clkchkbox.sendKeys("");
								clkchkbox.click();
								System.out.println("I clicked Go button");
								Thread.sleep(5000);
								String ParentWindow = driver.getWindowHandle(); // To save the parent window
								// create one more method for reading employee from excel sheet.
								ReadStatutoryPayBasis(StatutoryPaybasis);
								Thread.sleep(2000L);
								driver.switchTo().window(ParentWindow); // finally switch back to parent window and perform the operations.
								Thread.sleep(2000L);
								SickSavebutton();
								System.out.println("Save button got clicked and all data saved sucessfully");
							}
							col_num++;
						}
						row_num++;
					}
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	} 



	public void ReadStatutoryPayBasis(String StatutoryPaybasis)throws Throwable
	{

		String[] handles = driver.getWindowHandles().toArray(new String[0]); // To get the child window(s)
		driver.switchTo().window(handles[handles.length - 1]); 
		String axb=  driver.getTitle();
		System.out.println(axb);
		if(driver.getTitle().equalsIgnoreCase(axb))
		{
			WebElement dddframe1 = driver.findElement(By.id("searchFrame"));  // you encountered two frames so, find the frame id and save as webelement
			driver.switchTo().frame(dddframe1); // now using the frame id and switch to the frame
			System.out.println("title is matching");
			System.out.println("I am now in the child window");
			Thread.sleep(3000);
			getObject("searchField").clear();
			Thread.sleep(1000);
			getObject("searchField").sendKeys(StatutoryPaybasis);
			System.out.println("I entered the statutory pay basis reading from excel sheet");
			Thread.sleep(2000);
			getObject("Gobutton").click();
			Thread.sleep(3000);
			System.out.println("I clicked Go button");

			driver.switchTo().defaultContent();   // now that you encountered one more frame hence switch back to main page
			WebElement dddframe2 = driver.findElement(By.id("resultsFrame"));// and save the frame id and 
			driver.switchTo().frame(dddframe2); // switch to the other frame and perform the operations
			System.out.println("I switched to Results Frame");
			Thread.sleep(2000L);
			if(existsElement(OR.getProperty("clkSortedone1")))
			{
				getObject("clkSortedone1").click();
				String PymtBasisGotSelected = "PymtGotSelected";
				Assert.assertEquals("PymtGotSelected", PymtBasisGotSelected);
				System.out.println("The statutory pay basis got selected successfully");
				Thread.sleep(2000L);
			}
			else if(existsElement(OR.getProperty("clkSortedone2")))
			{
				getObject("clkSortedone2").click();
				String PymtBasisGotSelected = "PymtGotSelected";
				Assert.assertEquals("PymtGotSelected", PymtBasisGotSelected);
				System.out.println("The statutory pay basis got selected successfully");
				Thread.sleep(2000L);
			}
		}
	}



	public void SickSavebutton()throws Throwable
	{
		try
		{
			if(existsElement(OR.getProperty("sickSaveButton")))
			{
				getObject("sickSaveButton").sendKeys("");
				getObject("sickSaveButton").click();
				System.out.println("");
				System.out.println("The sick save button got clicked sucessfully");
			}
			else
			{
				System.out.println("Save button did not got clicked Hence the "
						+ "Leave record did not got updated as per the requirment");
				Thread.sleep(3000L);
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}


	/*public void submitSickleave()throws Throwable
	{
		try
		{  												
			if(existsElementchkFor1mts(OR.getProperty("submitLeaverqstlocator")))
			{
				getObject("submitLeaverqstlocator").sendKeys("");
				getObject("submitLeaverqstlocator").click();
				System.out.println("The submit leave request button got clicked sucessfully");
				if(existsElementchkFor1mts(OR.getProperty("leaveRequstOkbutton")))
				{			
					getObject("leaveRequstOkbutton").sendKeys("");
					getObject("leaveRequstOkbutton").click();
					System.out.println("");
					System.out.println("The submit leave request ok button also got clicked sucessfully");
				}
			}
			else if(!existsElementchkFor1mts(OR.getProperty("submitLeaverqstlocator")))
			{
				System.out.println("waiting for the submit button to be dispalyed please wait..");
				submitSickleave();
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}
	 */

	@DataProvider
	public Object[][] getData() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		return Test_Util.getData(Payroll_Statutory_SharedParentalpay_SuiteXls,"CreateLeaveRequest");
	}


	@AfterMethod
	public void ReportDataSetResult() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		if(Skip)
		{
			Test_Util.ReportDataSetResult(Payroll_Statutory_SharedParentalpay_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
		}
		else if(Fail)
		{
			IsTestPass = false;
			Test_Util.ReportDataSetResult(Payroll_Statutory_SharedParentalpay_SuiteXls, this.getClass().getSimpleName(), count+2, "Fail");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_Statutory_SharedParentalpay_SuiteXls, this.getClass().getSimpleName(), count+2, "Pass");
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
			Test_Util.ReportDataSetResult(Payroll_Statutory_SharedParentalpay_SuiteXls, "first", Test_Util.GetRowNum(Payroll_Statutory_SharedParentalpay_SuiteXls, this.getClass().getSimpleName()),"Pass");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_Statutory_SharedParentalpay_SuiteXls, "first", Test_Util.GetRowNum(Payroll_Statutory_SharedParentalpay_SuiteXls, this.getClass().getSimpleName()),"Fail");
		}	
		closeBrowser();
	}
}
