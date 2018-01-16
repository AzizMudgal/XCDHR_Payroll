package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.hrms_Payroll_SSP_Statutory_Scenario;


import java.text.DecimalFormat;
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
		if(! Test_Util.IsTestcaseRunMode(Payroll_SSP_ProcessPayroll_SuiteXls, this.getClass().getSimpleName()))
		{
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
						System.out.println("Employee "+EmpName+" got clicked to reset the data");
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
				/*
				 * This method performs the delete operations
				 */
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
			if(existsElement(OR.getProperty("leaveTabclk")))
			{
				getObject("leaveTabclk").sendKeys("");
				getObject("leaveTabclk").click();
				System.out.println("The leave tab got clicked");
				Thread.sleep(3000L);
			}
			if(existsElementchkFor1mts(OR.getProperty("bookLeavebuttonlocator")))
			{
				String bookLeaveText = getObject("bookLeavebuttonlocator").getAttribute("value");
				System.out.println("the book leave button text is :"+bookLeaveText);
				Assert.assertEquals("Book leave", bookLeaveText);
				System.out.println("The book leave button of the leave tab of the employee's Record is displayed successfully");

			}
			if(existsElementchkFor1mts(OR.getProperty("leaveYrVal")))
			{
				System.out.println("recognised the leave year value");
				double levyeear = Double.parseDouble(LeaveYear);
				DecimalFormat df = new DecimalFormat("###.#");
				String LeaveYrconvert= df.format(levyeear);
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("leaveYrVal"))));
				selectByValue.selectByVisibleText(LeaveYrconvert);
				Thread.sleep(1000L);
				if(existsElementchkFor1mts(OR.getProperty("PlzWaitFor2015leaveYear")))
				{
					String prgrssBarText = getObject("PlzWaitFor2015leaveYear").getText();
					Assert.assertEquals("Please wait...", prgrssBarText);
					System.out.println("progress bar message got displayed");
					payRunExecutionForLeaveYear(LeaveYear);
				}
			}
			LeaveYrSummary(LeaveYear);
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
			if(existsElementchkFor1mts(OR.getProperty("sspLeavSummaryTableLocator")))
			{
				System.out.println("The progress bar PLEASE WAIT now got exited");
				boolean tableDisplay = getObject("sspLeavSummaryTableLocator").isDisplayed();
				if(tableDisplay)
				{
					System.out.println("Its once again verified that The table indeed is being displayed and recognised by script");
				}
			}
			else if(!existsElementchkFor1mts(OR.getProperty("sspLeavSummaryTableLocator")))
			{
				System.out.println("The table is not displayed hence please wait for few seconds...");
				payRunExecutionForLeaveYear(LeaveYear);
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
				int rownumv = (ttrows);	
				endSearchingCompnRecord:
					while(x.hasNext())
					{
						System.out.println("the index of rownumv is  :"+rownumv);
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


	@DataProvider
	public Object[][] getData() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		return Test_Util.getData(Payroll_SSP_ProcessPayroll_SuiteXls,"ResetData");
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
