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
				//PayrollForMonthlyTax(AprilMonth);
				PayrollForStatutoryMonthlyd(EmployerName,EmpName,Payrolid,Frquency,MonthName,ExcelInputSheet,FirstReportNameInApplication,TestResultExcelFilePath,PayrollVeiw);
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
				obj1.PayrollForStatutoryMonthly(EmployerName,EmpName,Payrolid,Frquency,MonthName,ExcelInputSheet,FirstReportNameInApplication,TestResultExcelFilePath,PayrollVeiw);

				obj1.ExcludeIncludeEmp(EmpName,ExcelInputSheet,worksheetNo);
			}
		}
		
	}


	public void PayrollForStatutoryMonthlyd(String EmployerName, String EmpName,
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
							ProcessingToWeeklyForStatutory1d(EmployerName,
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
							ProcessingToWeeklyForStatutory1d(EmployerName,
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

	public void ProcessingToWeeklyForStatutory1d(String EmployerName,
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
							TaxPayRun_For_Week(MonthName, ExcelInputSheet,
									FirstReportNameInApplication,
									TestResultExcelFilePath);
						} else if (Frquency.equalsIgnoreCase("Monthly")) {
							TaxPayRun_For_MonthlyPayrun(MonthName, ExcelInputSheet,
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

	
	public void TaxPayRun_For_MonthlyPayrun(String MonthName, String ExcelInputSheet,
			String FirstReportNameInApplication, String TestResultExcelFilePath)
			throws Throwable {
		try {
				Thread.sleep(4000L);
				if (existsElementchkFor1mts(OR.getProperty("payRunWeekTable")))
				{
				System.out.println("Thee table exists");

				WebElement payRunWeekOneTable = getObject("payRunWeekTable");

				List<WebElement> rows = payRunWeekOneTable.findElements(By
						.xpath(OR.getProperty("WeekOneTablerows")));
				lastRowCount = rows.size();
				System.out.println("The total pay run records for the page is equal to : "+lastRowCount);
				java.util.Iterator<WebElement> x = rows.iterator();
				rownum = 2;
				while (x.hasNext())
				{
					System.out.println("Now the count of Rownum is : "+ rownum);
					WebElement MonthPayRun_Record = driver.findElement(By
							.xpath("//div[" + "5" + "]/" + "div[" + "1]/"
									+ "div/" + "div[" + "2]/" + "table/"
									+ "tbody/tr[" + (rownum) + "]/" + "th/"
									+ "a"));

					String PayRunTextName = MonthPayRun_Record.getText();
					System.out.println("The Month name is :" + PayRunTextName);
					if (PayRunTextName != null && PayRunTextName.equalsIgnoreCase(MonthName))
					{
						System.out.println("The Month name" + MonthName
								+ " matched");
						MonthPayRun_Record.sendKeys("");
						Thread.sleep(4000L);
						MonthPayRun_Record.click();
						break;
					}
					else if (rownum == lastRowCount && PayRunTextName != null
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
									.println("As the required Payrun is not found in first page,hence clicked to next page of personal Tab");
							Thread.sleep(8000L);
						}
						else
						{
							System.out
							.println("The Pay run record which you are searching "
									+ "is not available in all the pages"
									+ "of this Pay run page "
									+ "of the Application. Hence the script unfortunately is "
									+ "not able to perform required functionality.");
						}
					}
				else
				System.out.println("Payrun not matched hence incrementing the row number");
				rownum++;
				}
		   }
		 }
		 catch (Throwable t)
		 {
			System.out.println(t.getMessage());
		 }
	}

	
	
	
	/////Format method needs to be deleted after testing
	
	public void UpdateEmployeeNICategoryd(String empName, String NICategory)
			throws Throwable {
		try {
			if (employeeFirsttimeView) {
				employeeFirsttimeView = false;
				getObject("PersonalTab").click();
				if (existsElementchkFor1mts(OR.getProperty("PersonalText"))) {
					System.out.println("I am in personal page");
					if (existsElementchkFor1mts(OR.getProperty("EmployeeView"))) {
						System.out.println("I recognised the Employee view");
						Select selectByValue = new Select(driver.findElement(By
								.xpath(OR.getProperty("EmployeeView"))));
						selectByValue
								.selectByVisibleText("DO NOT TOUCH PAYROLL AUTOMATION TESTING");
						Thread.sleep(2000L);
						if (existsElementchkFor1mts(OR
								.getProperty("ViewGoButton"))) {
							getObject("ViewGoButton").sendKeys("");
							getObject("ViewGoButton").click();
							System.out.println("The Go button got clicked");
						}
						Thread.sleep(7000L);
					}
				}
			}
			Thread.sleep(2000L);
			try {
				if (existsElementchkFor1mts(OR
						.getProperty("firstRecordOfTaxCodecoulmnTable"))) {
					WebElement postsTable = driver.findElement(By.xpath(OR
							.getProperty("firstRecordOfTaxCodecoulmnTable")));
					if (existsWebElement(postsTable)) {
						searchEmployeeAndUpdateNICatgory(empName, NICategory);
					}
				}
			} catch (Throwable t) {
				System.out.println(t.getStackTrace().toString());
				System.out.println("");
			}
		} catch (Throwable t) {
			System.out.println(t.getStackTrace().toString());
			System.out.println("");
		}
	}

	public void searchEmployeeAndUpdateNICatgory(String empName,
			String NICategory) throws Throwable {
		try {
			WebElement tableheader = driver.findElement(By.xpath(OR
					.getProperty("PersonalAndCompensationHeadingTable")));
			List<WebElement> th = tableheader.findElements(By.tagName("td"));
			for (a = 0; a < th.size(); a++) {
				if ("Employee".equalsIgnoreCase(th.get(a).getText())) {
					empcolnum = a + 1;
					break;
				}
			}

			for (b = 0; b < th.size(); b++) {
				if ("NI category".equalsIgnoreCase(th.get(b).getText())) {
					niCategoryColumn = b + 1;
					break;
				}
			}
			WebElement postsTable = driver.findElement(By.xpath(OR
					.getProperty("firstRecordOfTaxCodecoulmnTable")));
			List<WebElement> rows = postsTable.findElements(By.xpath(OR
					.getProperty("firstRecordOfTaxCodecoulmnTableRows")));
			lastRowCount = rows.size();
			java.util.Iterator<WebElement> x = rows.iterator();
			rownum = 1;
			outerbreak: while (x.hasNext()) {
				// Thread.sleep(2000L);
				String firstRowOfEmployeeColumn = "//div[" + rownum
						+ "]/table/tbody/tr/td" + "[" + empcolnum + "]" + "/"
						+ "div/a/span";
				WebElement tempElement = driver.findElement(By
						.xpath(firstRowOfEmployeeColumn));
				String tempEmp = tempElement.getText();
				System.out.println(tempEmp + "-------" + empName + "------"
						+ rownum);
				String firstRowOfTaxCode = "//div[" + rownum + "]" + "/"
						+ "table/" + "tbody/" + "tr/" + "td["
						+ niCategoryColumn + "]" + "/" + "div";
				if (tempEmp != null && tempEmp.equalsIgnoreCase(empName)) {
					System.out.println("Employee name  :" + tempEmp
							+ "  matched ");
					Thread.sleep(2000L);
					if (existsElementchkFor1mts(firstRowOfTaxCode)) {
						Actions action = new Actions(driver);
						action.doubleClick(
								driver.findElement(By.xpath(firstRowOfTaxCode)))
								.perform();
						action.moveToElement(getObject("InlineDropdown"))
								.perform();
						// Thread.sleep(2000L);
						if (existsElementchkFor1mts(OR
								.getProperty("InlineDropdown"))) {
							getObject("InlineDropdown").sendKeys("");
							getObject("InlineDropdown").sendKeys(NICategory);
							System.out.println("Selected the NI Picklist item "
									+ NICategory);
							Thread.sleep(2000L);
							if (existsElementchkFor1mts(OR
									.getProperty("InlineUpdateButn"))) {
								getObject("InlineUpdateButn").click();
								System.out
										.println("The update button got clicked and NI Category got saved");
								Thread.sleep(8000L);
								break outerbreak;
							}
						}
					}
				} else if (rownum == lastRowCount && tempEmp != null
						&& tempEmp != (empName)) {
					rownum++;
					System.out
							.println("The row number of the page reached"
									+ rownum
									+ " to 200 and"
									+ " Required Employee not found hence clicking the"
									+ " pagination link so that Employee search continues for next page");
					if (existsElementchkFor1mts(OR
							.getProperty("paginationElementPersonal"))) {
						getObject("paginationNextPersonal").sendKeys("");
						getObject("paginationNextPersonal").click();
						System.out
								.println("As the required employees are not found in first page,hence clicked to next page of personal Tab");
						Thread.sleep(8000L);
						searchEmployeeAndUpdateNICatgory(empName, NICategory);
					} else {
						System.out
								.println("The employee which you are searching "
										+ "is not available in all the pages"
										+ "of this Personal / Compensation Tab "
										+ "of the Application. Hence the script unfortunately is "
										+ "not able to execute successfully. Please include the said employee"
										+ "in the said Tab of the application and run once again the script");
						closeBrowser();
					}

				} else
					System.out.println("incrementing the row number");
				rownum++;
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage());
			System.out.println(t.getStackTrace().toString());
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
