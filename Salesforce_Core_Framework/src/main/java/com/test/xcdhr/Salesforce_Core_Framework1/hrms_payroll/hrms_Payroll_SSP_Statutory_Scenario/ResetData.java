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

import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Test_Util;




public class ResetData extends TestSuiteBase
{
	String runmodes[] = null;
	static int count = -1;
	static int countCompensation = -1;
	public static boolean Fail=false;
	public static boolean Skip=false;
	public static boolean IsTestPass=true;
	public String firstRowOfCompnRecord;
	public String effectiveFrom;
	public String AutoEnrolNotifyAttahment;
	public String AutoEnrolNotifyAttahmentFalse;
	public String RowOfAttachementRecord;


	@BeforeTest
	public void CheckTestSkip() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		if(! Test_Util.IsTestcaseRunMode(Payroll_Statutory_SickPay_SuiteXls, this.getClass().getSimpleName()))
		{
			Skip=true;
			Test_Util.ReportDataSetResult(Payroll_Statutory_SickPay_SuiteXls, "first", Test_Util.GetRowNum(Payroll_Statutory_SickPay_SuiteXls, this.getClass().getSimpleName()),"Skipped");
			//Test_Util.ReportDataSetResult(Payroll_Statutory_SickPay_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase" +this.getClass().getSimpleName() +" as the runmode is set to 'no' ");// this message would display in logs
			throw new Exception("Testcase is being skipped" + this.getClass().getSimpleName()+ "as it's Runmode is set to 'NO'"); // this msg would display in Reports.
		}
		// Load the runmodes of the tests
		runmodes=Test_Util.getDataSetRunmodes(Payroll_Statutory_SickPay_SuiteXls, this.getClass().getSimpleName());
	}

	public String payfreqncy;
	boolean employeeFirsttimeView = true;
	boolean compensationFirsttimeView = true;
	boolean shouldOpenBrowser = true; 
	public int ttrows;
	public String ckbox;
	

	@Test(dataProvider = "getData")
	public void ResetEmploymentAndLeaveTab(String EmpName,String firstXCDpayDate,String payinStartPeriod,String LeaveYear) throws Throwable
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
		// The script updates the compensation record for the Automation employees
		DeleteLeavefunction(EmpName,firstXCDpayDate,payinStartPeriod,LeaveYear);
		/*************************************************************************/
	}



	public void DeleteLeavefunction(String EmpName,String firstXCDpayDate,String payinStartPeriod,String LeaveYear) throws Throwable
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
			List<WebElement> rows = postsTable.findElements(By.xpath(OR.getProperty("firstRecordOfNIcoulmnTableRows")));
			lastRowCount = rows.size();
			java.util.Iterator<WebElement> x = rows.iterator();
			int rownum = 1;	
			outerbreak:
			while(x.hasNext())
			{
				String empRecord="//div["+rownum+"]/table/tbody/tr/td[4]/div/a/span";
				WebElement empwebelement= driver.findElement(By.xpath(empRecord));
				String AppnEmp= empwebelement.getText();
				System.out.println(AppnEmp+"-------"+EmpName+"------"+rownum);
				if(AppnEmp!=null && AppnEmp.equalsIgnoreCase(EmpName))
				{
					System.out.println("Employee matched");
					System.out.println("Employee name is  :"+EmpName);
					Thread.sleep(3000L);
					empwebelement.click();
					break outerbreak;
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
				rownum++;
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
			
			if(existsElement(OR.getProperty("leaveTabclk")))
			{
				deleteLeaveRecords(LeaveYear);
				Thread.sleep(2000L);
			}
			/*
			 * when passing the argument to the 'ReadsExpectedData' method , first declare the public string at the top and use it in the method as argument.
			 * But keep in mind, you are passing the arguments in the same order (sequence) that of method parameters
			 */
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}
	
	
	
	public void selectPayinStartPeriod1(String payinStartPeriod)throws Throwable
	{
		try
		{
			Thread.sleep(4000L);
			WebElement postsTable = driver.findElement(By.xpath(OR.getProperty("sspEditTable")));
			if(existsWebElement(postsTable))
			{
				System.out.println("details table exists");
				List<WebElement> rows = postsTable.findElements(By.xpath(OR.getProperty("sspEditTableRows")));				  System.out.println("NUMBER OF ROWS IN THIS TABLE = "+rows.size());
				int row_num,col_num;
				row_num=1;
				outerloop:
					for(WebElement trElement : rows)
					{
						List<WebElement> td_collection=trElement.findElements(By.xpath("th"));
						System.out.println("NUMBER OF COLUMNS="+td_collection.size());
						col_num=1;
						for(WebElement tdElement : td_collection)
						{
							System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
							if(tdElement.getText()!=null && tdElement.getText().equalsIgnoreCase("Pay in start period"))
							{
								System.out.println("Label name  :"+tdElement.getText()+ "  matched ");
								ckbox ="//following-sibling::td[1]/input[contains(@id,'j_id0:j_id2:')]";
								WebElement clkchkbox = driver.findElement(By.xpath(ckbox));
								boolean	smallERchekbox = clkchkbox.isSelected();
								if(smallERchekbox)
								{
									System.out.println("yes the condition is checked");
								}
								double valueOfsmallReliefChkbox = Double.parseDouble(payinStartPeriod);
								System.out.println("converted smallER value is :"+valueOfsmallReliefChkbox);
								if(valueOfsmallReliefChkbox == 1.0)
								{
									Thread.sleep(4000L);
									if(smallERchekbox)
									{
										System.out.println("Pay in start period checkbox was allready checked, Hence our condition got satisfied");
										employmentSavebutton();
										System.out.println("Save button got clicked and all data saved sucessfully");
										break  outerloop;
									}
									else
									{
										clkchkbox.sendKeys("");
										clkchkbox.click();
										System.out.println("Pay in start period checkbox was NOT checked,and now checked hence Condition now satisfied successfully");
										employmentSavebutton();
										System.out.println("Save button got clicked and all data saved sucessfully");
										break  outerloop;
									}
								}	
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

	
	

	public void employmentSavebutton()throws Throwable
	{
		try
		{
			if(existsElement(OR.getProperty("employmentTabSave")))
			{
				getObject("employmentTabSave").sendKeys("");
				getObject("employmentTabSave").click();
				System.out.println("The employment tab edit button got clicked");
				Thread.sleep(4000L);
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}


	/*
	 * while passing the parameter to the below method you can pass with any string name.
	 * 
	 */


	public void deleteLeaveRecords(String LeaveYear)throws Throwable
	{
		try
		{
			if(compensationFirsttimeView)
			{
				compensationFirsttimeView=false;
				if(existsElement(OR.getProperty("leaveTabclk")))
				{
					getObject("leaveTabclk").sendKeys("");
					getObject("leaveTabclk").click();
					Thread.sleep(3000L);
				}
				if(existsElement(OR.getProperty("leaveYrVal")))
				{
					Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("leaveYrVal"))));
					selectByValue.selectByVisibleText(LeaveYear);
					if(getObject("PlzWaitFor2015leaveYear").getText().equalsIgnoreCase("Please wait..."))
					{
						System.out.println("The progress bar PLEASE WAIT got displayed");
						payRunExecutionForLeaveYear(LeaveYear);
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

	
	
	
	public void payRunExecutionForLeaveYear(String LeaveYear)throws Throwable
	 {
		try
		{
			if(existsElement(OR.getProperty("PlzWaitFor2015leaveYear")))
			{
				String tenPercent = getObject("PlzWaitFor2015leaveYear").getText();
				System.out.println(tenPercent);
				if(tenPercent.equalsIgnoreCase("Please wait..."))
				{
					boolean payrun100percent=getObject("PlzWaitFor2015leaveYear").isDisplayed();
					System.out.println("The please wait message is displayed");
					if(payrun100percent)
					{
						Thread.sleep(4000L);
						if(!getObject("PlzWaitFor2015leaveYear").isDisplayed())
						{
							System.out.println("The progress bar PLEASE WAIT got exited");
							System.out.println("The Leave year 2015 now got selected");
							LeaveYrSummary(LeaveYear);
						}
						else
						{
							payRunExecutionForLeaveYear(LeaveYear);
						}
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

	
	
	public void payRunExecutionForLeaveYear1(String LeaveYear)throws Throwable
	 {
		try
		{
			if(existsElement(OR.getProperty("PlzWaitFor2015leaveYear")))
			{
				String tenPercent = getObject("PlzWaitFor2015leaveYear").getText();
				System.out.println(tenPercent);
				if(tenPercent.equalsIgnoreCase("Please wait..."))
				{
					boolean payrun100percent=getObject("PlzWaitFor2015leaveYear").isDisplayed();
					System.out.println("The please wait message is displayed");
					if(payrun100percent)
					{
						Thread.sleep(4000L);
						if(!getObject("PlzWaitFor2015leaveYear").isDisplayed())
						{
							System.out.println("The progress bar PLEASE WAIT got exited");
							System.out.println("The Leave year 2015 now got selected");
						}
						else
						{
							payRunExecutionForLeaveYear1(LeaveYear);
						}
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

	
	
	public void LeaveYrSummary(String LeaveYear)throws Throwable
	{
		try
		{
			Thread.sleep(4000L);
			if(existsElement(OR.getProperty("sspLeavSummaryTableLocator")))
			{
				WebElement AEnotifyNoticeTablelocator = driver.findElement(By.xpath(OR.getProperty("sspLeavSummaryTableLocator")));
				List<WebElement> rows = AEnotifyNoticeTablelocator.findElements(By.xpath(OR.getProperty("sspLeavSummaryTableRowsLocator")));
				ttrows= rows.size();
				System.out.println("Total Leave records are :"+ttrows);
				java.util.Iterator<WebElement> x = rows.iterator();
				int count=0;
				int rownumv = (ttrows);	
				endSearchingCompnRecord:
					while(x.hasNext())
					{
						System.out.println("the index of rownumv is  :"+rownumv);
						if(count>0)
						{
							if(existsElement(OR.getProperty("leaveYrVal")))
							{
								Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("leaveYrVal"))));
								selectByValue.selectByVisibleText(LeaveYear);
								if(getObject("PlzWaitFor2015leaveYear").getText().equalsIgnoreCase("Please wait..."))
								{
									System.out.println("The progress bar PLEASE WAIT got displayed");
									payRunExecutionForLeaveYear1(LeaveYear);
								}
							}
						}
							RowOfAttachementRecord="//div[@class='pbBody']/table/tbody/"+"tr["+(rownumv)+"]"+"/td[2]/a";
							WebElement attachmentlink= driver.findElement(By.xpath(RowOfAttachementRecord));
							attachmentlink.click();
							System.out.println("Leave record link got clicked");
						if(existsElement(OR.getProperty("leaverecordDeleteLocator")))
						{
							getObject("leaverecordDeleteLocator").sendKeys("");
							getObject("leaverecordDeleteLocator").click();
							System.out.println("The leave record delete button got clicked");
							Thread.sleep(5000L);
							isAlertPresent();
						}
						rownumv--;
						count++;
						if(rownumv==0)
						{
							System.out.println("All the leave records got deleted ");
							break endSearchingCompnRecord;
						}
					}
			}
			else if(!existsElement(OR.getProperty("sppLeavSummaryTableLocator")))
			{
				System.out.println("Threre are no leave records to delete");
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	/*
	public void deleteLeaveRecords()throws Throwable
	{
		try
		{

			if(compensationFirsttimeView)
			{
				compensationFirsttimeView=false;
					if(existsElement(OR.getProperty("leaveTabclk")))
					{
						getObject("leaveTabclk").sendKeys("");
						getObject("leaveTabclk").click();
						Thread.sleep(3000L);
					}

			}

			if(existsElement(OR.getProperty("sppLeavSummaryTableLocator")))
			{
				WebElement AEnotifyNoticeTablelocator = driver.findElement(By.xpath(OR.getProperty("sppLeavSummaryTableLocator")));
				List<WebElement> rows = AEnotifyNoticeTablelocator.findElements(By.xpath(OR.getProperty("sppLeavSummaryTableRowsLocator")));
				int ttrows= rows.size();
				System.out.println("Total Leave records are :"+ttrows);
				java.util.Iterator<WebElement> x = rows.iterator();
				int rownumv = rows.size();	
				endSearchingCompnRecord:
					while(x.hasNext())
					{
						System.out.println("the index of rownumv is  :"+rownumv);

						if(existsElement(OR.getProperty("sppLeavSummaryTableLocator")))
						{
							RowOfAttachementRecord="//form/table/tbody/tr[2]/td/table/tbody/tr/td/span/div[3]/div/div[2]/table/tbody/"+"tr["+rownumv+"]"+"/td[2]/a";
							WebElement attachmentlink= driver.findElement(By.xpath(RowOfAttachementRecord));
							attachmentlink.click();
							System.out.println("attachment link got clicked");
						}



							if(existsElement(OR.getProperty("leaverecordDeleteLocator")))
							{
								getObject("leaverecordDeleteLocator").sendKeys("");
								getObject("leaverecordDeleteLocator").click();
								System.out.println("The leave record delete button got clicked");

								Thread.sleep(3000L);
								Alert alert = driver.switchTo().alert();
								alert.accept();
								System.out.println("The leave record deleted successfully");
							}

						rownumv--;
						if(rownumv==0)
						{
							System.out.println("There are no attachments to delete");
							break endSearchingCompnRecord;
						}
					}
			}
			else if(!existsElement(OR.getProperty("sppLeavSummaryTableLocator")))
			{
				System.out.println("Threre are no leave records to delete");
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}
	 */



	public void updateFirstXcdPayDate(String firstXCDpayDate,String payinStartPeriod)throws Throwable
	{
		try
		{
			if(existsElement(OR.getProperty("employmentTabEdit")))
			{
				getObject("employmentTabEdit").sendKeys("");
				getObject("employmentTabEdit").click();
				System.out.println("The employment tab edit button got clicked");
				Thread.sleep(4000L);
			}
			if(existsElement(OR.getProperty("firstXCDPayDate")))
			{
				getObject("firstXCDPayDate").sendKeys("");
				getObject("firstXCDPayDate").clear();
				String dateStr = firstXCDpayDate;
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
				getObject("firstXCDPayDate").sendKeys(formattedDate);
				System.out.println("");
				System.out.println("The first xcd pay date was entered sucessfully");	
				Thread.sleep(2000);
			}
			Thread.sleep(2000L);
			if(existsElement(OR.getProperty("sspEditTable")))
			{
				selectPayinStartPeriod(payinStartPeriod);
				Thread.sleep(4000L);
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage());
			System.out.println(t.getStackTrace());
		}
	}





	@DataProvider
	public Object[][] getData() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		return Test_Util.getData(Payroll_Statutory_SickPay_SuiteXls,"ResetData");
	}





	@AfterMethod
	public void ReportDataSetResult() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		if(Skip)
		{
			Test_Util.ReportDataSetResult(Payroll_Statutory_SickPay_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
		}
		else if(Fail)
		{
			IsTestPass = false;
			Test_Util.ReportDataSetResult(Payroll_Statutory_SickPay_SuiteXls, this.getClass().getSimpleName(), count+2, "Fail");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_Statutory_SickPay_SuiteXls, this.getClass().getSimpleName(), count+2, "Pass");
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
			Test_Util.ReportDataSetResult(Payroll_Statutory_SickPay_SuiteXls, "first", Test_Util.GetRowNum(Payroll_Statutory_SickPay_SuiteXls, this.getClass().getSimpleName()),"Pass");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_Statutory_SickPay_SuiteXls, "first", Test_Util.GetRowNum(Payroll_Statutory_SickPay_SuiteXls, this.getClass().getSimpleName()),"Fail");
		}	
		closeBrowser();
	}
	
	
	/*
	if(existsElement(OR.getProperty("employmentTab")))
	{
		getObject("employmentTab").sendKeys("");
		getObject("employmentTab").click();
		System.out.println("The employment tab got clicked");
		Thread.sleep(4000L);
	}

	if(existsElement(OR.getProperty("employmentTabEdit")))
	{
		updateFirstXcdPayDate(firstXCDpayDate,payinStartPeriod);
		//Thread.sleep(2000L);
		//getObject("makeWaytoDisplayChkbox").sendKeys("");
		//getObject("makeWaytoDisplayChkbox").click();
						
	}
	
	Thread.sleep(2000L);
	if(existsElement(OR.getProperty("sspEditTable")))
	{
		selectPayinStartPeriod(payinStartPeriod);
		Thread.sleep(4000L);
	}
	*/
	

}
