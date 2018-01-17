package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.NI_MonthlyCatH;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Test_Util;


public class ProcessPayrollForNIMonthly extends TestSuiteBase
{
	String runmodes[] = null;
	static int count = -1;
	static int countCompensation = -1;

	public static boolean Fail = false;
	public static boolean Skip = false;
	public static boolean IsTestPass = true;
	public String payrollRecordId;
	public int rownum;
	public String monthOneRecordId;
	public int counter;
		

	@BeforeTest
	public void CheckTestSkip() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		if (!Test_Util.IsTestcaseRunMode(Payroll_MonthlyCatH_SuiteXls, this
				.getClass().getSimpleName()))
		{
			Skip = true;
			Test_Util.ReportDataSetResult(Payroll_MonthlyCatH_SuiteXls, "first",
					Test_Util.GetRowNum(Payroll_MonthlyCatH_SuiteXls, this
							.getClass().getSimpleName()), "Skipped");
			// Test_Util.ReportDataSetResult(Payroll_MonthlyCatH_SuiteXls,
			// this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase"
					+ this.getClass().getSimpleName()
					+ " as the runmode is set to 'no' ");// this message would
			// display in logs

			throw new Exception("Testcase is being skipped"
					+ this.getClass().getSimpleName()
					+ "as it's Runmode is set to 'NO'"); // this msg would
			// display in
			// Reports.
		}
		// Load the runmodes of the tests
		runmodes = Test_Util.getDataSetRunmodes(Payroll_MonthlyCatH_SuiteXls, this
				.getClass().getSimpleName());
	}
	
	

	public String payfreqncy;
	boolean exlude = true;
	boolean compensationFirsttimeView = true;
	boolean shouldOpenBrowser = true;
	/*
	 *   This test processes the Payroll for the selected
	 *   Automation employees. I am calling this method from the 'TestBase' class.
	 * 
	 */
	
	@Test(dataProvider = "getData")
	public void EmpsPayroll_Setup_ForIncomeTax(String EmployerName,String EmpName,String Payrolid,String Frquency,String MonthName,String ExcelInputSheet,String FirstReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,String PayrollVeiw) throws Throwable
	{
		count++;
		if (!runmodes[count].equalsIgnoreCase("Y"))
		{
			Skip = true;
			throw new SkipException("Runmode for Test set data is set to 'NO' "
					+ count);
		}
		if (shouldOpenBrowser)
		{
			shouldOpenBrowser = false;
			openBrowser();
			logingIntoDesiredORG(OrgFlag);
			driver.manage().window().maximize();
			try
			{
				System.out
				.println("The test script logged in successfully into salesforce account");
				System.out.println("");
				/*
				 * This method is being called from the TestBase
				 * This method goes to the payroll Tab and searches 
				 * the appropriate company,payrun and pay frequency
				 * 
				 */
				PayrollForStatutoryMonthlyLocal(EmployerName,EmpName,Payrolid,Frquency,MonthName,ExcelInputSheet,FirstReportNameInApplication,TestResultExcelFilePath,PayrollVeiw);
			}
			catch (Throwable t)
			{
				System.out.println(t.getMessage().toString());
				System.out.println(t.getStackTrace().toString());
			}
		}
		/*
		 * This method is being called from the TestBase class.
		 * This method selects the employee and performs the 
		 * Generate draft payroll functionality.
		 */
		ExcludeIncludeEmp(EmpName,ExcelInputSheet,worksheetNo);
		
		if (finalRows != dTRows)
		{
			Thread.sleep(3000L);
			System.out.println("Since the app is not displaying employee records same"
					+ " as excel file employees of this Tax worksheet");
			ProcessPayrollForNIMonthly obj1 = new ProcessPayrollForNIMonthly();
			for(Repeat=2; Repeat < 5; Repeat++)
			{
				// I have set 3 times to repeat the payroll script so that by the time it processess
				// 4th round 7 minutes would be as per Tutu. the appln should process the generate draft functionality.
				System.out.println("The value of Repeat is "+Repeat);
				obj1.PayrollForStatutoryMonthlyLocal(EmployerName,EmpName,Payrolid,Frquency,MonthName,ExcelInputSheet,FirstReportNameInApplication,TestResultExcelFilePath,PayrollVeiw);
				obj1.ExcludeIncludeEmp(EmpName,ExcelInputSheet,worksheetNo);
			}
		}
	}

		
	public void PayrollForStatutoryMonthlyLocal(String EmployerName, String EmpName,
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
							ProcessingToWeeklyForStatutory1Local(EmployerName,
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
							ProcessingToWeeklyForStatutory1Local(EmployerName,
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

	public void ProcessingToWeeklyForStatutory1Local(String EmployerName,
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
						if (Frquency.equalsIgnoreCase("Four Weekly")) {
							TaxPayRun_For_FourWeek(MonthName, ExcelInputSheet,
									FirstReportNameInApplication,
									TestResultExcelFilePath);
						} else if (Frquency.equalsIgnoreCase("Two Weekly")) {
							TaxPayRun_For_TwoWeek(MonthName, ExcelInputSheet,
									FirstReportNameInApplication,
									TestResultExcelFilePath);
						} else if (Frquency.equalsIgnoreCase("Weekly")) {
							TaxPayRun_For_WeekLocal(MonthName, ExcelInputSheet,
									FirstReportNameInApplication,
									TestResultExcelFilePath);
						} else if (Frquency.equalsIgnoreCase("Monthly")) {
							TaxPayRun_For_Monthly(MonthName, ExcelInputSheet,
									FirstReportNameInApplication,
									TestResultExcelFilePath);
						}

						break;
					}
					break;
				} else {
					System.out.println("payfrequency not matched");
				}
				rownum++;
			}

		} catch (Throwable t) {
			t.getMessage().toString();
			t.getStackTrace().toString();
		}

	}
	
	
	public void TaxPayRun_For_Monthly(String MonthName, String ExcelInputSheet,
			String FirstReportNameInApplication, String TestResultExcelFilePath)
			throws Throwable {
		try {/*
            
			 if (existsElementchkFor1mts(OR
					.getProperty("payrollMonthWeeekSubPaginToDisplayAllRecords")))
					{
				for (int i = 1; i < 3; i++) {
					getObject("payrollMonthWeeekSubPaginToDisplayAllRecords")
							.sendKeys("");
					getObject("payrollMonthWeeekSubPaginToDisplayAllRecords")
							.click();
					System.out.println("The expandable page got clicked for"
							+ i + "st time");
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
						Thread.sleep(4000L);
						Weekrecord.click();
						break;
					} else {
						System.out.println("payRun text " + WeekName
								+ "did not matched");
						rownum++;
					}

				}

			}

		*/

			//Code Added By Swamy
			
			//Thread.sleep(2000L);
			if (existsElement(OR.getProperty("payRunWeekTable")))
			{
			System.out.println("Thee table exists");

			WebElement payRunWeekOneTable = getObject("payRunWeekTable");

			List<WebElement> rows = payRunWeekOneTable.findElements(By
					.xpath(OR.getProperty("WeekOneTablerows")));
			lastRowCount = rows.size();
			System.out.println("The total pay run records for the page is equal to : "+lastRowCount);
			java.util.Iterator<WebElement> x = rows.iterator();
			rownum = 2;
		counter = 1;
			while (x.hasNext())
			{
				//Thread.sleep(2000L);
				System.out.println("Now the count of Rownum is : "+ rownum);
				WebElement MonthPayRun_Record = driver.findElement(By
						.xpath("//div[" + "5" + "]/" + "div[" + "1]/"
								+ "div/" + "div[" + "2]/" + "table/"
								+ "tbody/tr[" + (rownum) + "]/" + "th/"
								+ "a"));
				
				if (existsWebElement(MonthPayRun_Record)) {
					System.out.println("first payroll table record existt");
				String PayRunTextName = MonthPayRun_Record.getText();
				System.out.println("The Month name is :" + PayRunTextName);
				if (PayRunTextName != null && PayRunTextName.equalsIgnoreCase(MonthName))
				{
					System.out.println("The Month name" + PayRunTextName
							+ " matched");
					MonthPayRun_Record.sendKeys("");
					//Thread.sleep(1000L);
					MonthPayRun_Record.click();
					System.out.println("The Payrun record whose Month name is " + MonthName
							+ "successfully clicked for processing payroll");

					break;
				}
					System.out.println("The Month name" + PayRunTextName
							+ " is not matched");
					if (counter < 12 && rownum == 6|| rownum >10 && PayRunTextName != null
							&& PayRunTextName != (MonthName))
					{
						System.out
								.println("The row number of the page reached"
								+ rownum
								
								+ " Required payrun not found hence clicking the"
								+ " pagination link so that payrun search continues for next page");
					
					if (existsElementchkFor1mts(OR
								.getProperty("payrollMonthWeeekSubPaginToDisplayAllRecords")))
						{
							getObject("payrollMonthWeeekSubPaginToDisplayAllRecords").sendKeys("");
							getObject("payrollMonthWeeekSubPaginToDisplayAllRecords").click();
							System.out
									.println("As the required Payrun is not found in first page,hence clicked to pagination link");
							Thread.sleep(5000L);
	
						}
					}
						
			System.out.println("Payrun not matched hence incrementing the row number");
			rownum++;
			counter++;
			}
		 }
	   }
	 	
		} catch (Throwable t) {
			System.out.println(t.getMessage());

		}
	}

	
	
	public void TaxPayRun_For_WeekLocal(String WeekName, String ExcelInputSheet,
			String FirstReportNameInApplication, String TestResultExcelFilePath)
			throws Throwable {
		try {

		/*	if (existsElementchkFor1mts(OR
					.getProperty("payrollMonthWeeekSubPaginToDisplayAllRecords"))) {
				for (int i = 1; i < 5; i++) {
					getObject("payrollMonthWeeekSubPaginToDisplayAllRecords")
							.sendKeys("");
					getObject("payrollMonthWeeekSubPaginToDisplayAllRecords")
							.click();
					System.out.println("The expandable page got clicked for"
							+ i + "st time");
					Thread.sleep(2000L);
				}
			}*/
			
			Thread.sleep(4000L);
			if (existsElementchkFor1mts(OR.getProperty("payRunWeekTable"))) {
				System.out.println("table exists");
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
					} else {
						System.out.println("payRun text " + WeekName
								+ "did not matched");
						rownum++;
					}

				}

			}

		} catch (Throwable t) {
			System.out.println(t.getMessage());

		}
	}

	
	
	@DataProvider
	public Object[][] getData() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		return Test_Util.getData(Payroll_MonthlyCatH_SuiteXls,"ProcessPayrollForNIMonthly");
	}
	
	

	@AfterMethod
	public void ReportDataSetResult() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		if (Skip)
		{
			Test_Util.ReportDataSetResult(Payroll_MonthlyCatH_SuiteXls, this
					.getClass().getSimpleName(), count + 2, "Skip");
		} else if (Fail)
		{
			IsTestPass = false;
			Test_Util.ReportDataSetResult(Payroll_MonthlyCatH_SuiteXls, this
					.getClass().getSimpleName(), count + 2, "Fail");
		} else
		{
			Test_Util.ReportDataSetResult(Payroll_MonthlyCatH_SuiteXls, this
					.getClass().getSimpleName(), count + 2, "Pass");
		}
		Skip = false;
		Fail = false;
	}
	
	

	@AfterTest
	public void ReportTestResult() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		if (IsTestPass)
		{
			// This will update the testresult in the first worksheet where in
			// for that test case , even if one of the test data specified in
			// second worksheet fails, the test
			// would be considered as fail.And the same would be updated.
			Test_Util.ReportDataSetResult(Payroll_MonthlyCatH_SuiteXls, "first",
					Test_Util.GetRowNum(Payroll_MonthlyCatH_SuiteXls, this
							.getClass().getSimpleName()), "Pass");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_MonthlyCatH_SuiteXls, "first",
					Test_Util.GetRowNum(Payroll_MonthlyCatH_SuiteXls, this
							.getClass().getSimpleName()), "Fail");
		}
		closeBrowser();
	}

}
