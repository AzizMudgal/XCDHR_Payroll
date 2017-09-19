package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.hrms_Payroll_SMP_Statutory_Scenario;



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

		if(! Test_Util.IsTestcaseRunMode(Payroll_Statutory_maternitypay_SuiteXls, this.getClass().getSimpleName())){

			Skip=true;
			Test_Util.ReportDataSetResult(Payroll_Statutory_maternitypay_SuiteXls, "first", Test_Util.GetRowNum(Payroll_Statutory_maternitypay_SuiteXls, this.getClass().getSimpleName()),"Skipped");
			//Test_Util.ReportDataSetResult(Payroll_Statutory_maternitypay_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase" +this.getClass().getSimpleName() +" as the runmode is set to 'no' ");// this message would display in logs
			throw new Exception("Testcase is being skipped" + this.getClass().getSimpleName()+ "as it's Runmode is set to 'NO'"); // this msg would display in Reports.
		}
		// Load the runmodes of the tests
		runmodes=Test_Util.getDataSetRunmodes(Payroll_Statutory_maternitypay_SuiteXls, this.getClass().getSimpleName());
	}

	public String payfreqncy;
	boolean employeeFirsttimeView = true;
	boolean compensationFirsttimeView = true;
	boolean shouldOpenBrowser = true; 
	public int ttrows;

	@Test(dataProvider = "getData")
	public void ResetEmploymentAndLeaveTabs(String EmpName,String firstXCDpayDate,String payinStartPeriod) throws Throwable
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
		DeleteLeavefunction(EmpName,firstXCDpayDate,payinStartPeriod);

		/*************************************************************************/

	}



	public void DeleteLeavefunction(String EmpName,String firstXCDpayDate,String payinStartPeriod) throws Throwable
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
			java.util.Iterator<WebElement> x = rows.iterator();
			int rownum = 1;			
			while(x.hasNext())
			{
				String empRecord="//div["+rownum+"]/table/tbody/tr/td[4]/div/a/span";
				WebElement empwebelement= driver.findElement(By.xpath(empRecord));
				String AppnEmp= empwebelement.getText();
				//System.out.println(tempEmp+"-------"+empName+"------"+rownum);
				if(AppnEmp!=null && AppnEmp.equalsIgnoreCase(EmpName))
				{
					System.out.println("Employee matched");
					System.out.println("Employee name is  :"+EmpName);
					Thread.sleep(3000L);
					empwebelement.click();
					break;
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
				updateFirstXcdPayDate(firstXCDpayDate);
				Thread.sleep(2000L);
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
			if(existsElement(OR.getProperty("leaveTabclk")))
			{
				deleteLeaveRecords();
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



	/*
	 * while passing the parameter to the below method you can pass with any string name.
	 * 
	 */


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
				ttrows= rows.size();
				System.out.println("Total Leave records are :"+ttrows);
				java.util.Iterator<WebElement> x = rows.iterator();
				int rownumv = ttrows;	
				endSearchingCompnRecord:
					while(x.hasNext())
					{
						System.out.println("the index of rownumv is  :"+rownumv);

						if(existsElement(OR.getProperty("sppLeavSummaryTableLocator")))
						{
							RowOfAttachementRecord="//div[contains(@id,'leaveReq')]/div/table/tbody/"+"tr["+(rownumv - 1)+"]"+"/td[2]/a";

							WebElement attachmentlink= driver.findElement(By.xpath(RowOfAttachementRecord));
							attachmentlink.click();
							System.out.println("Leave record link got clicked");
						}

						if(existsElement(OR.getProperty("leaverecordDeleteLocator")))
						{
							getObject("leaverecordDeleteLocator").sendKeys("");
							getObject("leaverecordDeleteLocator").click();
							System.out.println("The leave record delete button got clicked");
							Thread.sleep(5000L);
							isAlertPresent();
						}

						rownumv--;
						if(rownumv==1)
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


	public void updateFirstXcdPayDate(String firstXCDpayDate)throws Throwable
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

		return Test_Util.getData(Payroll_Statutory_maternitypay_SuiteXls,"ResetData");
	}


	@AfterMethod
	public void ReportDataSetResult() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);

		if(Skip)
		{
			Test_Util.ReportDataSetResult(Payroll_Statutory_maternitypay_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
		}
		else if(Fail)
		{
			IsTestPass = false;
			Test_Util.ReportDataSetResult(Payroll_Statutory_maternitypay_SuiteXls, this.getClass().getSimpleName(), count+2, "Fail");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_Statutory_maternitypay_SuiteXls, this.getClass().getSimpleName(), count+2, "Pass");
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
			Test_Util.ReportDataSetResult(Payroll_Statutory_maternitypay_SuiteXls, "first", Test_Util.GetRowNum(Payroll_Statutory_maternitypay_SuiteXls, this.getClass().getSimpleName()),"Pass");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_Statutory_maternitypay_SuiteXls, "first", Test_Util.GetRowNum(Payroll_Statutory_maternitypay_SuiteXls, this.getClass().getSimpleName()),"Fail");
		}	
		closeBrowser();
	}

}
