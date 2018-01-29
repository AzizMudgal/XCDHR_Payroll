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
			Thread.sleep(9000L);
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
		DeleteLeavefunction(EmpName,firstXCDpayDate,payinStartPeriod);

		/*************************************************************************/

	}



	public void DeleteLeavefunctionDraft(String EmpName,String firstXCDpayDate,String payinStartPeriod) throws Throwable
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


	public void PayrollForStatutoryMonthly(String EmployerName, String EmpName,
			String Payrolid, String Frquency, String MonthName,
			String ExcelInputSheet, String FirstReportNameInApplication,
			String TestResultExcelFilePath, String PayrollView)
			throws Throwable {

		if (existsElementchkFor1mts(OR.getProperty("payrollTab"))) {
			getObject("payrollTab").click();
			System.out.println("The payroll tab got clicked");
		}
		Thread.sleep(5000L);
		if (existsElementchkFor1mts(OR.getProperty("payrollViewLocator"))) {
			Select selectByValue = new Select(driver.findElement(By.xpath(OR
					.getProperty("payrollViewLocator"))));
			// This select by value needs to be called from OR.Properties
			selectByValue.selectByValue(PayrollView);// "Current"
		}
		Thread.sleep(2000L);
		/*
		 * This code clicks to pagination from 1 to last page till it finds the
		 * '2Weekly' pay run Once it finds the '2Weekly' payrun, it clicks to
		 * it. Hence the following code finds the '2Weekly' payrun automatically
		 * from pagination ProcessingTo2Weekly() method searches the required
		 * company name and payrun
		 */
		try {
			if (existsElementchkFor1mts(OR.getProperty("payroll2weeklytable"))) {
				WebElement table = driver.findElement(By.xpath(OR
						.getProperty("payroll2weeklytable")));
				if (existsWebElement(table)) {
					System.out.println("payroll table existt");
					/*
					 * Since the pagination is changed and the next button holds
					 * constant value as 3 . i have taken this as locator
					 * element.
					 */
					// List<WebElement> allpages =
					// driver.findElements(By.xpath(OR.getProperty("totalPages")));
					System.out.println("Total pages :");
					//
					for (int i = 2; i <= 50; i++) {

						List<WebElement> allrows = table
								.findElements(By.xpath(OR
										.getProperty("payroll2weeklytablerowss")));

						for (int row = 1; row <= allrows.size(); row++) {
							ProcessingToWeeklyForStatutory1(EmployerName,
									EmpName, Payrolid, Frquency, MonthName,
									ExcelInputSheet,
									FirstReportNameInApplication,
									TestResultExcelFilePath, PayrollView);
							System.out
									.println("No record matched in first page hence clicked to next page");

						}

						if (existsElement(OR.getProperty("paginationElement"))) {
							getObject("paginationNext").sendKeys("");
							getObject("paginationNext").click();
							System.out.println("hence clicked to next page");

						}
						List<WebElement> allrows1 = table
								.findElements(By.xpath(OR
										.getProperty("payroll2weeklytablerowss")));

						for (int row = 1; row <= allrows1.size(); row++) {
							ProcessingToWeeklyForStatutory1(EmployerName,
									EmpName, Payrolid, Frquency, MonthName,
									ExcelInputSheet,
									FirstReportNameInApplication,
									TestResultExcelFilePath, PayrollView);
						}

					}
				}
			}

		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	
	
	public void ProcessingToWeeklyForStatutory1(String EmployerName,
			String EmpName, String Payrolid, String Frquency, String MonthName,
			String ExcelInputSheet, String FirstReportNameInApplication,
			String TestResultExcelFilePath, String PayrollView)
			throws Throwable {
		try {
			WebElement tableheader = getObject("payroll2weeklytable");
			List<WebElement> th = tableheader.findElements(By.tagName("th"));

			for (int i = 0; i < th.size(); i++) {
				if ("Payroll".equalsIgnoreCase(th.get(i).getText())) {
					payrollcol_position = i + 1;
					break;
				}
			}

			for (int j = 0; j < th.size(); j++) {
				if ("Employer".equalsIgnoreCase(th.get(j).getText())) {
					Emplpoyercol_position = j + 1;
					break;
				}

			}

			for (int k = 0; k < th.size(); k++) {
				if ("Frequency".equalsIgnoreCase(th.get(k).getText())) {
					frequencyCol_Postition = k + 1;
					break;

				}
			}
			Thread.sleep(4000L);
			WebElement niweeklyPayrollTable = getObject("payroll2weeklytable");
			// need to check webelement exist
			// WebTable table = WebTable.getTable(niweeklyPayrollTable);
			List<WebElement> rows = niweeklyPayrollTable.findElements(By
					.xpath(OR.getProperty("payroll2weeklytablerows")));
			// //
			java.util.Iterator<WebElement> x = rows.iterator();
			rownum = 1;
			System.out.println("rownum is  :" + rownum);
			while (x.hasNext()) {
				WebElement emr1 = driver.findElement(By.xpath("//table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/"
						+ "td[" + Emplpoyercol_position + "]"));
				String empr = emr1.getText();

				WebElement ffr = driver.findElement(By.xpath("//table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/"
						+ "td[" + frequencyCol_Postition + "]"));
				String ffr1 = ffr.getText();

				WebElement ppr1 = driver.findElement(By.xpath("//table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/"
						+ "td[" + payrollcol_position + "]"));
				String ppr = ppr1.getText();
				// Thread.sleep(4000L);

				payrollRecordId = "//table/tbody/tr/td/form/div[1]/table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/"
						+ "td[" + payrollcol_position + "]" + "/" + "a";
				
				if (empr != null && empr.equalsIgnoreCase(EmployerName)
						&& ppr.equalsIgnoreCase(Payrolid)
						&& ffr1.equalsIgnoreCase(Frquency)) {
					System.out
							.println("Finally the Employer name,Payfrequency 'Monthly' and required Payroll matched");

					driver.findElement(By.xpath(payrollRecordId)).click();
					/*
					 * ENTERING INTO NEXT PAGE
					 */
					if (existsElement(OR.getProperty("twoweeklyPayrolldetails"))) {
						String pfrequencey = getObject(
								"twoweeklyPayrolldetails").getText();
						System.out.println("the employer name is :"
								+ pfrequencey);
						if(Frquency.equalsIgnoreCase("Four Weekly"))
						{
							TaxPayRun_For_FourWeek(MonthName, ExcelInputSheet,
									FirstReportNameInApplication,
									TestResultExcelFilePath);
						}
						else if(Frquency.equalsIgnoreCase("Two Weekly"))
						{
							TaxPayRun_For_TwoWeek(MonthName, ExcelInputSheet,
									FirstReportNameInApplication,
									TestResultExcelFilePath);
						}
						else if(Frquency.equalsIgnoreCase("Weekly"))
						{
							TaxPayRun_For_Week(MonthName, ExcelInputSheet,
									FirstReportNameInApplication,
									TestResultExcelFilePath);
						}
						else if(Frquency.equalsIgnoreCase("Monthly"))
						{
							TaxPayRun_For_FourWeek(MonthName, ExcelInputSheet,
									FirstReportNameInApplication,
									TestResultExcelFilePath);
						}
						break;
					}
					break;
				}
				else
				{
					System.out.println("payfrequency not matched");
				}
				rownum++;
			}
		} catch (Throwable t) {
			t.getMessage().toString();
			t.getStackTrace().toString();
		}
	}
	
	
	public void TaxPayRun_For_FourWeek(String WeekName, String ExcelInputSheet,
			String FirstReportNameInApplication, String TestResultExcelFilePath)
			throws Throwable {
		try {
			
			if(existsElementchkFor1mts(OR.getProperty("payrollMonthWeeekSubPaginToDisplayAllRecords")))
			{
				for(int i = 1; i<3; i++)
				{
					getObject("payrollMonthWeeekSubPaginToDisplayAllRecords").sendKeys("");
					getObject("payrollMonthWeeekSubPaginToDisplayAllRecords").click();
					System.out.println("The expandable page got clicked for"+i+"st time");
					Thread.sleep(4000L);
				}
			}
			Thread.sleep(4000L);
			if (existsElement(OR.getProperty("payRunWeekTable"))) {
				System.out.println("Thee table exists");
				
				WebElement payRunWeekOneTable = getObject("payRunWeekTable");
				
				List<WebElement> rows = payRunWeekOneTable.findElements(By
						.xpath(OR.getProperty("WeekOneTablerows")));
				java.util.Iterator<WebElement> x = rows.iterator();
				System.out.println("total number of week records are :"
						+ rows.size());

				rownum = 2;
				while (x.hasNext()) {
					WebElement Weekrecord = driver.findElement(By
							.xpath("//div[" + "5" + "]/" + "div[" + "1]/"
									+ "div/" + "div[" + "2]/" + "table/"
									+ "tbody/tr[" + (rownum) + "]/" + "th/"
									+ "a"));

					String weekText = Weekrecord.getText();

					if (weekText != null && weekText.equalsIgnoreCase(WeekName)) {
						System.out.println("The week name" + WeekName
								+ " matched");
						Weekrecord.sendKeys("");
						Weekrecord.click();
						break;
					}
					else 
					{
						System.out.println("payRun text " + WeekName
								+ "did not matched");
						rownum++;
					}
				}
			}
		}
		catch (Throwable t)
		{
			System.out.println(t.getMessage());
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
