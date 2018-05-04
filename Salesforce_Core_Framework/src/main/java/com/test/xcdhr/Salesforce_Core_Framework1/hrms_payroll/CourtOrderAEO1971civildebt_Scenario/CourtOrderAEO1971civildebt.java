package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.CourtOrderAEO1971civildebt_Scenario;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import atu.webdriver.utils.table.WebTable;

import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.ErrorUtil;
import com.test.xcdhr.Salesforce_Core_Framework1.enumPackage.EnumTestClass;
import com.test.xcdhr.Salesforce_Core_Framework1.enumPackage.ModifiedReport;

/*
 * Allready the inputscript methods are working fine.
 * This class will implement payroll methods also a
 *
 *  */

public class CourtOrderAEO1971civildebt extends TestSuiteBase
{
	String runmodes[] = null;
	static int count = -1;
	static int countCompensation = -1;
	public static boolean Skip=false;
	boolean shouldOpenBrowser = true; 
	public String payrollRecordId;
	public int rownum;
	public String monthOneRecordId;
	public String payfreqncy;
	boolean exlude = true;
	boolean compensationFirsttimeView = true;
	boolean windowExclude = true;
	public int Repeat;
	public int finalRows;
	

	public void toSelectDesiredOrg(int OrgFlag)throws Throwable
	{
		try
		{
			if(shouldOpenBrowser)
			{
				shouldOpenBrowser = false;
				/*
				 * Desired browser is going to get invoked 
				 */
				openBrowser();
				/*
				 * Desired ORG is going to get invoked 
				 */
				logingIntoDesiredORG(OrgFlag);
				driver.manage().window().maximize();
				try
				{
					/*
					 * Temporary pop up window is going to be closed 
					 */
					closePopupWindow();

					/*
					 * Particular tab is being verified after logging successfully in the Org. 
					 */
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
					System.out.println(t.getMessage());
					System.out.println(t.getStackTrace().toString());
				}
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage());
			System.out.println(t.getStackTrace().toString());
		}
	}




	public void updateEmployeesNICategory(String EmpName,String NICategory, String AnnualSalary, String PayFrequency,String EmployerName,String Payrolid,String MonthName,String ExcelInputSheet,String FirstReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,String PayrollView,String TestReportworksheetNo)
			throws Throwable
	{
		try {
			if (employeeFirsttimeView)
			{
				employeeFirsttimeView = false;
				getObject("PersonalTab").click();
				if (existsElementchkFor1mts(OR.getProperty("PersonalText")))
				{
					System.out.println("I am in personal page");
					if (existsElementchkFor1mts(OR.getProperty("EmployeeView")))
					{
						System.out.println("I recognised the Employee view");
						Select selectByValue = new Select(driver.findElement(By
								.xpath(OR.getProperty("EmployeeView"))));
						selectByValue
						.selectByVisibleText("DO NOT TOUCH PAYROLL AUTOMATION TESTING");
						Thread.sleep(2000L);
						if (existsElementchkFor1mts(OR
								.getProperty("ViewGoButton")))
						{
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
						.getProperty("firstRecordOfTaxCodecoulmnTable")))
				{
					WebElement postsTable = driver.findElement(By.xpath(OR
							.getProperty("firstRecordOfTaxCodecoulmnTable")));
					if (existsWebElement(postsTable))
					{
						try
						{
							WebElement tableheader = driver.findElement(By.xpath(OR
									.getProperty("PersonalAndCompensationHeadingTable")));
							List<WebElement> th = tableheader.findElements(By.tagName("td"));
							for (a = 0; a < th.size(); a++)
							{
								if ("Employee".equalsIgnoreCase(th.get(a).getText()))
								{
									empcolnum = a + 1;
									break;
								}
							}

							for (b = 0; b < th.size(); b++)
							{
								if ("NI category".equalsIgnoreCase(th.get(b).getText()))
								{
									niCategoryColumn = b + 1;
									break;
								}
							}
							WebElement postsTable1 = driver.findElement(By.xpath(OR
									.getProperty("firstRecordOfTaxCodecoulmnTable")));
							List<WebElement> rows = postsTable1.findElements(By.xpath(OR
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
								/*System.out.println(tempEmp + "-------" + empName + "------"
										+ rownum);*/
								String firstRowOfTaxCode = "//div[" + rownum + "]" + "/"
										+ "table/" + "tbody/" + "tr/" + "td["
										+ niCategoryColumn + "]" + "/" + "div";
								if (tempEmp != null && tempEmp.equalsIgnoreCase(EmpName)) {
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
										&& tempEmp != (EmpName)) {
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
										rownum = 0;
										Thread.sleep(8000L);

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
									//System.out.println("incrementing the row number");
									rownum++;
							}
						} catch (Throwable t) {
							System.out.println(t.getMessage());
							System.out.println(t.getStackTrace().toString());
						}
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



	public void updateEmpAnnualSalaryAndPayFrequency(String EmpName,String NICategory, String AnnualSalary, String PayFrequency,String EmployerName,String Payrolid,String MonthName,String ExcelInputSheet,String FirstReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,String PayrollView,String TestReportworksheetNo) throws Throwable {
		try {
			if (compensationFirsttimeView) {
				compensationFirsttimeView = false;
				if (existsElement(OR.getProperty("CompensationTab"))) {
					getObject("CompensationTab").click();
					Thread.sleep(4000L);
					/*
					 * Calling the following method from the base class since
					 * "Select value is not able to call the value from
					 * OR.Properties page.
					 */
					compensationSelectValue();
				}
			}
			Thread.sleep(1000L);
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
				if ("Annual salary".equalsIgnoreCase(th.get(b).getText())) {
					compnAnnualSalColumn = b + 1;
					break;
				}
			}

			for (c = 0; c < th.size(); c++) {
				if ("Payroll frequency".equalsIgnoreCase(th.get(c).getText())) {
					compPayfrequencyColumn = c + 1;
					break;
				}
			}
			WebElement postsTable = driver.findElement(By.xpath(OR
					.getProperty("firstRecordOfTaxCodecoulmnTable")));
			if (existsWebElement(postsTable)) {
				List<WebElement> rows = postsTable.findElements(By.xpath(OR
						.getProperty("firstRecordOfTaxCodecoulmnTableRows")));
				lastRowCount = rows.size();
				java.util.Iterator<WebElement> x = rows.iterator();
				rownum = 1;
				outerbreak: while (x.hasNext()) {
					String firstEmpXpath = "//div[" + rownum
							+ "]/table/tbody/tr/td" + "[" + empcolnum + "]"
							+ "/" + "div/a/span";
					if (existsElementchkFor1mts(firstEmpXpath)) {
						WebElement FirstrowofEmpColumn = driver.findElement(By
								.xpath(firstEmpXpath));
						String AppnEmp = FirstrowofEmpColumn.getText();
						if (AppnEmp != null
								&& AppnEmp.equalsIgnoreCase(EmpName)) {
							// System.out.println("Employee matched");
							Thread.sleep(1000L);
							String firstRowOfAnnualsalary = "//div[" + rownum
									+ "]" + "/" + "table/" + "tbody/" + "tr/"
									+ "td[" + compnAnnualSalColumn + "]" + "/"
									+ "div";
							if (existsElement(firstRowOfAnnualsalary)) {
								Actions action1 = new Actions(driver);
								action1.doubleClick(
										driver.findElement(By
												.xpath(firstRowOfAnnualsalary)))
												.perform();
								WebElement updatesal = driver
										.findElement(By.xpath(OR
												.getProperty("annualSalTextField")));
								action1.moveToElement(updatesal).perform();
								Thread.sleep(1000L);
								updatesal.clear();
								Thread.sleep(1000L);
								updatesal.sendKeys(AnnualSalary);
								Thread.sleep(1000L);
								if (existsElement(OR
										.getProperty("CompnSavebuton"))) {
									getObject("CompnSavebuton").sendKeys("");
									getObject("CompnSavebuton").click();
									System.out
									.println("The annual salary got saved");
								}
								Thread.sleep(3000L);
							}
							updateEmpPayFrequency(EmpName,NICategory,AnnualSalary,PayFrequency,EmployerName,Payrolid,MonthName,ExcelInputSheet,FirstReportNameInApplication,TestResultExcelFilePath,worksheetNo,PayrollView,TestReportworksheetNo);
							break outerbreak;
						} else if (rownum == lastRowCount && AppnEmp != null
								&& AppnEmp != (EmpName)) {
							System.out
							.println("The row number of the page reached"
									+ rownum
									+ " to 200 and"
									+ " Required Employee not found hence clicking the"
									+ " pagination link so that Employee search continues for next page");
							if (existsElementchkFor1mts(OR
									.getProperty("paginationElementPersonal"))) {
								getObject("paginationNextPersonal")
								.sendKeys("");
								getObject("paginationNextPersonal").click();
								System.out
								.println("As the required employees are not found in first page,hence clicked to next page of personal Tab");
								Thread.sleep(8000L);
								rownum = 0;
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
						}
					}
					rownum++;
				}
			}
		} catch (Throwable t) {
			APP_LOGS.debug("Check the Annual salary Method for errors");
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
			ErrorUtil.addVerificationFailure(t);
		}
	}



	public void updateEmpPayFrequency(String EmpName,String NICategory, String AnnualSalary, String PayFrequency,String EmployerName,String Payrolid,String MonthName,String ExcelInputSheet,String FirstReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,String PayrollView,String TestReportworksheetNo) throws Throwable {
		try {
			String firstRowOfPayFrequency = "//div[" + rownum + "]" + "/"
					+ "table/" + "tbody/" + "tr/" + "td["
					+ compPayfrequencyColumn + "]" + "/" + "div";
			if (existsElement(firstRowOfPayFrequency))
			{
				Thread.sleep(2000L);
				Actions action2 = new Actions(driver);
				action2.doubleClick(
						driver.findElement(By.xpath(firstRowOfPayFrequency)))
						.perform();
				action2.moveToElement(getObject("payFrequencyDropdown"))
				.perform();
				Thread.sleep(2000L);
				if (existsElement(OR.getProperty("payFrequencyDropdown"))) {

					getObject("payFrequencyDropdown").sendKeys("");
					getObject("payFrequencyDropdown").sendKeys(PayFrequency);
					System.out.println("Selected the PayFrequency item as :"
							+ PayFrequency);
					Thread.sleep(2000L);
					if (existsElement(OR.getProperty("payFrequencyUpdate"))) {
						getObject("payFrequencyUpdate").click();
						System.out
						.println("The update button got clicked and Pay frequency Category got saved");
						Thread.sleep(2000L);
					}
				}
			}
		} catch (Throwable t)
		{
			APP_LOGS.debug("Check the Pay frequency Method for errors");
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}
	
	//*********************** End of input script methods*************************************
	
	
/******************************************************************************/
	/*
	 * Payroll methods follows:----
	 */
/*******************************************************************************/
	/*
	 * Need to update the input excel sheet with payroll and report script parameters also.
	 * 
	 */
	
	public void processMonthlyPayroll(String EmpName,String NICategory, String AnnualSalary, String PayFrequency,String EmployerName,String Payrolid,String MonthName,String ExcelInputSheet,String FirstReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,String PayrollView,String TestReportworksheetNo)
			throws Throwable
	{

		if (existsElementchkFor1mts(OR.getProperty("payrollTab"))) 
		{
			getObject("payrollTab").click();
			System.out.println("The payroll tab got clicked");
		}
		Thread.sleep(6000L);
		
		/*Aziz commented on 4th may 2018.
		 * In QA Org, becuase of HEAP ERROR not able to select the 'All' from view drop
		 * down list item. hence commented the selection and searching the payrollid AS
		 * by default the view pick list item is showing 'Current pay run'.
		 * 
		 * THIS IS JUST TEMPORARY PHASE . AS soon as heap error is resolved we shall
		 * have to uncomment the following methods.
		 * 
		 */
		
		/*if (existsElementchkFor1mts(OR.getProperty("payrollViewLocator"))) 
		{
			Select selectByValue = new Select(driver.findElement(By.xpath(OR
					.getProperty("payrollViewLocator"))));
			selectByValue.selectByValue(PayrollView);
		}
		Thread.sleep(4000L);
		
		if (existsElementchkFor1mts(OR.getProperty("payrollSearchField")))
		{
			System.out.println("The payroll search field is displayed");
			getObject("payrollSearchField").sendKeys("");
			getObject("payrollSearchField").sendKeys(Payrolid);
		}
		Thread.sleep(5000L);*/
	
		try
		{
			if (existsElementchkFor1mts(OR.getProperty("payroll2weeklytable")))
			{
				WebElement table = driver.findElement(By.xpath(OR
						.getProperty("payroll2weeklytable")));
				if (existsWebElement(table)) {
					System.out.println("payroll table existt");
					
					System.out.println("Total pages :");
				
					for (int i = 2; i <= 50; i++)
					{
						List<WebElement> allrows = table
								.findElements(By.xpath(OR
										.getProperty("payroll2weeklytablerowss")));

						for (int row = 1; row <= allrows.size(); row++) 
						{
							ProcessingToWeeklyForStatutory1(EmployerName,
									EmpName, Payrolid, PayFrequency, MonthName,
									ExcelInputSheet,
									FirstReportNameInApplication,
									TestResultExcelFilePath, PayrollView);
							System.out
									.println("No record matched in first page hence clicked to next page");
						}

						if (existsElement(OR.getProperty("paginationElement")))
						{
							System.out.println("hence clicked to next page");
						}
						List<WebElement> allrows1 = table
								.findElements(By.xpath(OR
										.getProperty("payroll2weeklytablerowss")));

						for (int row = 1; row <= allrows1.size(); row++)
						{
							toProcessMonthlyPayrun(EmployerName,
									EmpName, Payrolid, PayFrequency, MonthName,
									ExcelInputSheet,
									FirstReportNameInApplication,
									TestResultExcelFilePath, PayrollView);
						}
					}
				}
			}
		}
		catch (Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}
	
	

	public void toProcessMonthlyPayrun(String EmployerName,
			String EmpName, String Payrolid, String Frquency, String MonthName,
			String ExcelInputSheet, String FirstReportNameInApplication,
			String TestResultExcelFilePath, String PayrollView)
			throws Throwable
	{
		try {
			WebElement tableheader = getObject("payroll2weeklytable");
			List<WebElement> th = tableheader.findElements(By.tagName("th"));

			for (int i = 0; i < th.size(); i++)
			{
				if ("Payroll".equalsIgnoreCase(th.get(i).getText()))
				{
					payrollcol_position = i + 1;
					break;
				}
			}

			for (int j = 0; j < th.size(); j++)
			{
				if ("Employer".equalsIgnoreCase(th.get(j).getText()))
				{
					Emplpoyercol_position = j + 1;
					break;
				}

			}

			for (int k = 0; k < th.size(); k++)
			{
				if ("Frequency".equalsIgnoreCase(th.get(k).getText()))
				{
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
			while (x.hasNext())
			{
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
						&& ffr1.equalsIgnoreCase(Frquency))
				{
					System.out
							.println("Finally the Employer name,Payfrequency 'Monthly' and required Payroll matched");

					driver.findElement(By.xpath(payrollRecordId)).click();
					/*
					 * ENTERING INTO NEXT PAGE
					 */
					if (existsElement(OR.getProperty("twoweeklyPayrolldetails")))
					{
						String pfrequencey = getObject(
								"twoweeklyPayrolldetails").getText();
						System.out.println("the employer name is :"
								+ pfrequencey);
						if (Frquency.equalsIgnoreCase("Four Weekly"))
						{
							TaxPayRun_For_FourWeek(MonthName, ExcelInputSheet,
									FirstReportNameInApplication,
									TestResultExcelFilePath);
						}
						else if (Frquency.equalsIgnoreCase("Two Weekly"))
						{
							TaxPayRun_For_TwoWeek(MonthName, ExcelInputSheet,
									FirstReportNameInApplication,
									TestResultExcelFilePath);
						}
						else if (Frquency.equalsIgnoreCase("Weekly"))
						{
							TaxPayRun_For_Week(MonthName, ExcelInputSheet,
									FirstReportNameInApplication,
									TestResultExcelFilePath);
						} else if (Frquency.equalsIgnoreCase("Monthly"))
						{
							toProcessMonthlyPayrunFinalStep(MonthName, ExcelInputSheet,
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
		}
		catch (Throwable t) 
		{
			t.getMessage().toString();
			t.getStackTrace().toString();
		}
	}
	
	
	
	public void toProcessMonthlyPayrunFinalStep(String MonthName, String ExcelInputSheet,
			String FirstReportNameInApplication, String TestResultExcelFilePath)
			throws Throwable
		{
		try {
			if (existsElement(OR.getProperty("payRunWeekTable")))
			{
				System.out.println("Thee table exists");

				WebElement payRunWeekOneTable = getObject("payRunWeekTable");

				List<WebElement> rows = payRunWeekOneTable.findElements(By
						.xpath(OR.getProperty("WeekOneTablerows")));
				lastRowCount = rows.size();
				System.out
						.println("The total pay run records for the page is equal to : "
								+ lastRowCount);
				java.util.Iterator<WebElement> x = rows.iterator();
				rownum = 2;
				counter = 1;
				while (x.hasNext())
				{
					// Thread.sleep(2000L);
					System.out
							.println("Now the count of Rownum is : " + rownum);
					WebElement MonthPayRun_Record = driver.findElement(By
							.xpath("//div[" + "5" + "]/" + "div[" + "1]/"
									+ "div/" + "div[" + "2]/" + "table/"
									+ "tbody/tr[" + (rownum) + "]/" + "th/"
									+ "a"));

					if (existsWebElement(MonthPayRun_Record))
					{
						System.out.println("first payroll table record existt");
						String PayRunTextName = MonthPayRun_Record.getText();
						System.out.println("The Month name is :"
								+ PayRunTextName);
						if (PayRunTextName != null
								&& PayRunTextName.equalsIgnoreCase(MonthName))
						{
							System.out.println("The Month name"
									+ PayRunTextName + " matched");
							MonthPayRun_Record.sendKeys("");
							// Thread.sleep(1000L);
							MonthPayRun_Record.click();
							System.out
									.println("The Payrun record whose Month name is "
											+ MonthName
											+ "successfully clicked for processing payroll");
							break;
						}
						System.out.println("The Month name" + PayRunTextName
								+ " is not matched");
						if (counter < 12 && rownum == 6 || rownum > 10
								&& PayRunTextName != null
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
								getObject(
										"payrollMonthWeeekSubPaginToDisplayAllRecords")
										.sendKeys("");
								getObject(
										"payrollMonthWeeekSubPaginToDisplayAllRecords")
										.click();
								System.out
										.println("As the required Payrun is not found in first page,hence clicked to pagination link");
								Thread.sleep(5000L);
							}
						}
						System.out
								.println("Payrun not matched hence incrementing the row number");
						rownum++;
						counter++;
					}
				}
			}
		}
		catch (Throwable t)
		{
			System.out.println(t.getMessage());
		}
	}

	
	
	/******************************************************/
	/*
	 * Following is the Method used in the payroll script which selects the
	 * employees and processes the 'Generate draft payroll' functionality
	 */
	/******************************************************/
	public int rowMatchedDD = 0;

	public void excludeIncludeEmp(String EmpName,String NICategory, String AnnualSalary, String PayFrequency,String EmployerName,String Payrolid,String MonthName,String ExcelInputSheet,String FirstReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,String PayrollView,String TestReportworksheetNo) throws Throwable {
		try {
			System.out.println("entering into ExcludeIncludeEmp method");
			double worksheetvalue = Double.parseDouble(worksheetNo);
			DecimalFormat df = new DecimalFormat("###.#");
			String worksheetNoWithoutDecimal = df.format(worksheetvalue);
			int wNo = Integer.parseInt(worksheetNoWithoutDecimal);
			System.out.println("The converted post value is  :" + wNo);

			FileInputStream fis = new FileInputStream(
					new File(
							System.getProperty("user.dir")
									+ "\\src\\main\\java\\com\\test\\xcdhr\\Salesforce_Core_Framework1\\salesforce_XLS_Files\\"
									+ ExcelInputSheet));

			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet spreadsheet = workbook.getSheetAt(wNo);
			totalRows = spreadsheet.getLastRowNum();
			System.out
					.println("Total rows in the processpayrollforMonthlytax worksheet is :"
							+ totalRows);
			String oldWindow = driver.getWindowHandle();

			if (existsElementchkFor5mts(OR.getProperty("changeToDraft"))) {
				System.out.println("yest the Change to Draft button exist");
				retryForGenerateDraft();
			}
			driver.switchTo().window(driver.getWindowHandle());
			if (windowExclude) {
				windowExclude = false;
				if (getObject("excludeAllemployees").isDisplayed()) {
					System.out
							.println("the exclude include check box is displayed");
					getObject("excludeAllemployees").click();
					System.out
							.println("the exclude include check box got checked");
					Thread.sleep(1000L);
					if (getObject("excludeAllemployees").isSelected()) {
						getObject("excludeAllemployees").click();
						System.out
								.println("After checking the chckbox onceagain the exclude include check box is made UNchecked");
						// Thread.sleep(1000L);
					}
				}
			}
			if (existsElementchkFor1mts(OR
					.getProperty("excludeIncludeAllEmployees"))) {
				WebElement excludeincludeTable = driver.findElement(By.xpath(OR
						.getProperty("excludeIncludeAllEmployees")));
				List<WebElement> rows = excludeincludeTable
						.findElements(By.xpath(OR
								.getProperty("excludeIncludeAllEmployeesrows")));
				java.util.Iterator<WebElement> x = rows.iterator();
				rownumx = 1;
				while (x.hasNext()) {
					
					WebElement appEmployes = driver
							.findElement(By
									.xpath("//div[@id='turtle-info']/div/div/div[2]/table[2]/tbody/tr["
											+ rownumx + "]/td[2]/a"));
				
					
					String appEmployeesName = appEmployes.getText();
					System.out.println("empname is  :" + appEmployeesName);
					if (appEmployeesName != null
							&& appEmployeesName.equalsIgnoreCase(EmpName)) {
						rowMatchedDD++;
						WebElement empchkBox = driver
								.findElement(By
										.xpath("//div[@id='turtle-info']/div/div/div[2]/table[2]/tbody/tr["
												+ rownumx + "]/td/input"));
					
						System.out.println("empchkBox=====" + empchkBox);
						
						if (existsWebElement(empchkBox)) {
							empchkBox.click();
							System.out.println("");
							System.out.println("The Employee name  : "
									+ appEmployeesName
									+ "  check box got clicked");
							System.out.println("The rowMatchedDD------>:" +rowMatchedDD);
						}
						if (totalRows == rowMatchedDD) {
							System.out
									.println("The employees rows now matched,hence will now exit the window by saving the required employees");
							break;
						}
					}
					rownumx++;
				}
				
				
				if (existsElementchkFor1mts(OR.getProperty("closeWindow"))) {
					getObject("closeWindow").click();
					System.out
							.println("The save button of the popup window got clicked");
					Thread.sleep(1000L);
				}
				driver.switchTo().window(oldWindow);
				Thread.sleep(1000L);
				if (existsElementchkFor1mts(OR
						.getProperty("genratedraftPayroll"))) {
					getObject("genratedraftPayroll").sendKeys("");
					getObject("genratedraftPayroll").click();
					if (existsElementchkFor1mts(OR.getProperty("progressBar"))) {
						System.out.println("");
						System.out
								.println("The generate draft button got clicked, please wait till draft payroll process gets executed");
						Thread.sleep(4000L);
						payRunExecution();
						Thread.sleep(6000L);
						if (existsElementchkFor1mts(OR
								.getProperty("emprecordsTableRowsAftergeneratedraft"))) {
							verifyEmpRecordInPaySummaryTable();
						}
					}
				}
			}

		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
		
		/*
		 * The following statements gets executed if the payroll could
		 *  not process properly as per no of employees present in the 
		 *  input excel sheet.
		 */
		
		if (finalRows != dTRows)
		{
			Thread.sleep(3000L);
			System.out.println("Since the app is not displaying employee records same"
					+ " as excel file employees of this Tax worksheet");

			for(Repeat=2; Repeat < 5; Repeat++)
			{
				// I have set 3 times to repeat the payroll script so that by the time it processess
				// 4th round 7 minutes would be as per Tutu. the appln should process the generate draft functionality.
				System.out.println("The value of Repeat is "+Repeat);
				
				processMonthlyPayroll(EmpName,NICategory,AnnualSalary,PayFrequency,EmployerName,Payrolid,MonthName,ExcelInputSheet,FirstReportNameInApplication,TestResultExcelFilePath,worksheetNo,PayrollView,TestReportworksheetNo);

				excludeIncludeEmp(EmpName,NICategory,AnnualSalary,PayFrequency,EmployerName,Payrolid,MonthName,ExcelInputSheet,FirstReportNameInApplication,TestResultExcelFilePath,worksheetNo,PayrollView,TestReportworksheetNo);
			}
		}
	}
	
	public int dTRows;
	public int draftTotalRows;

	public void verifyEmpRecordInPaySummaryTable() throws Throwable {
		try {
			System.out
					.println("Now the new method 'verifyEmpRecordInPaySummaryTable()' "
							+ "would execute to find out the employee record in PaySummary Table after waiting period of 10 seconds");
			Thread.sleep(8000L);
			if (existsElement(OR
					.getProperty("emprecordsTableAftergeneratedraft"))) {
				System.out
						.println("The script recognised the tax generated employee table locator");
				Thread.sleep(9000L);
				System.out.println("Waited for 9 seconds");
				WebElement empTableAfterDraftgenerate = getObject("emprecordsTableAftergeneratedraft");
				List<WebElement> draftRows = empTableAfterDraftgenerate
						.findElements(By.xpath(OR
								.getProperty("emprecordsTableRowsAftergeneratedraft")));
				Thread.sleep(3000L);
				draftTotalRows = draftRows.size();
				System.out.println("Total rows are " + draftTotalRows);
				if (totalRows == (draftTotalRows - 1)) {
					Thread.sleep(1000L);
					System.out
							.println("After generating draft payroll the app is displaying employee records same"
									+ " as excel file employees of this Tax worksheet");
				} else {
					System.out
							.println(" the app is not displaying employee records same"
									+ " as excel file employees of this Tax worksheet");
					finalRows = totalRows;
					dTRows = draftTotalRows;
					System.out.println("The Final rows in the table are :"
							+ finalRows);

					System.out.println("The rows in the table are :" + dTRows);

				}
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}
	
	
	
	public void retryForGenerateDraft() throws Throwable
	{
		try {

			if (existsElement(OR.getProperty("genratedraftPayroll")))
			{
				if (existsElement(OR.getProperty("changeToDraft")))
				{
					getObject("changeToDraft").sendKeys("");
					getObject("changeToDraft").click();
					Thread.sleep(2000L);
					if (existsElementchkFor1mts(OR
							.getProperty("statusPickList"))) {
						Select selectByValue = new Select(driver.findElement(By
								.xpath(OR.getProperty("statusPickList"))));
						selectByValue.selectByVisibleText("Draft");
					}
					Thread.sleep(2000L);
					if (existsElementchkFor1mts(OR
							.getProperty("payrollDraftSave"))) {
						getObject("payrollDraftSave").sendKeys("");
						getObject("payrollDraftSave").click();
						System.out.println("The Save button got clicked");
					}
				}
				if (existsElementchkFor1mts(OR
						.getProperty("genratedraftPayroll"))) {
					if (existsElementchkFor1mts(OR
							.getProperty("excludeIncludeEmployees"))) {
						getObject("excludeIncludeEmployees").click();
						System.out
								.println("Exclude Include Employees link got clicked");
						Thread.sleep(5000);
					}
				} else {
					System.out
							.println("May be the payroll status is still New. Please make this payroll status to Draft and rerun the script");
				}
			}

		} catch (Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}
	
	
	
	/**************************************************************************************/
	/*************************************************************************************/
	
	
	/*
	 * Report methods follows
	 * 
	 */
	public void DownloadReports(String EmployerName,String EmpName,String Payrolid,String Frquency,String MonthName,String ExcelInputSheet,String FirstReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,String PayrollVeiw,String TestReportworksheetNo) throws Throwable
	{
		if(existsElementchkFor1mts(OR.getProperty("reportTablocator")))
		{
			getObject("reportTablocator").click();
			System.out.println("2> Clicked to Report Tab");
			Thread.sleep(4000L);
			//driver.navigate().refresh();
		}

		if(existsElementchkFor1mts(OR.getProperty("findReportTextboxLocator")))
		{				
			SearchReport(FirstReportNameInApplication);
		}

		if(existsElementchkFor1mts(OR.getProperty("reportCustomisebtn")))
		{
			editCustomButton();
		}

		if(existsElementchkFor1mts(OR.getProperty("customEditbtn")))
		{				
			UpdateReportPage(Payrolid,Frquency,MonthName);
			System.out.println("");
		}

		if(existsElementchkFor1mts(OR.getProperty("customRunReport")))
		{
			RunReport();
		}

		if(existsElementchkFor1mts(OR.getProperty("reportTableLocatorNI")))
		{
			processReport(EmployerName,EmpName,Payrolid,Frquency,MonthName,ExcelInputSheet,FirstReportNameInApplication,TestResultExcelFilePath,worksheetNo,PayrollVeiw,TestReportworksheetNo);
			System.out.println("7> Entered the values and processed the Test Remarks");
		}
	}



	public void processReport(String EmployerName,String EmpName,String Payrolid,String Frquency,String MonthName,String ExcelInputSheet,String FirstReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,String PayrollVeiw,String TestReportworksheetNo)throws Throwable
	{
		try
		{
			if(existsElementchkFor1mts(OR.getProperty("reportTableLocatorNI")))
			{
				//Get number of rows In table using table/tbody/tr
				Row_count = driver.findElements(By.xpath(OR.getProperty("reportTableRowsLocatorNI"))).size();
				System.out.println("Number Of Rows = "+Row_count);
				//Get number of columns In table by using Tr/td
				int Col_count = driver.findElements(By.xpath(OR.getProperty("reportTableColumnsNI"))).size();
				System.out.println("Number Of Columns = "+Col_count); // DISPLAYING
			}
			Thread.sleep(3000L);
			WebElement threecolms = driver.findElement(By.xpath(OR.getProperty("reportTableLocatorNI")));
			WebTable table = WebTable.getTable(threecolms);
			List<WebElement> rows = threecolms.findElements(By.xpath(OR.getProperty("reportTableRowsLocatorNI")));
			java.util.Iterator<WebElement> x = rows.iterator();
			int rownum = 1;
			gotobreak:
				while(x.hasNext())
				{
					if(rownum==(Row_count-2))
					{	
						System.out.println("no of rows is equal to expected rows");
						System.out.println("4> Total count of Employee records displayed in the report are :"+rownum);
						System.out.println("");
						System.out.println("5> The script successfully read and output the values and accordingly gave the TEST REMARKS in NI-HMRC Excel file");
						break gotobreak;
					} 
					else
					{
						firstCellOfBody= table.getTBody().getRow(rownum).getCell(0).getText();
						//System.out.println("firstCellOfBody is :"+firstCellOfBody);
						String statutoryAdoptionPay= table.getTBody().getRow(rownum).getCell(1).getText();
						System.out.println("statutoryAdoptionPay is :"+statutoryAdoptionPay);
						String statutoryMaternityPay= table.getTBody().getRow(rownum).getCell(2).getText();
						System.out.println("statutoryMaternityPay is :"+statutoryMaternityPay);
						//call the function which reads the excel sheet.
						ReadsExpectedData(EmpName,statutoryAdoptionPay,statutoryMaternityPay,TestResultExcelFilePath,TestReportworksheetNo);
					}
					rownum++;
				}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}



	public void ReadsExpectedData(String EmpName,String statutoryAdoptionPay,String statutoryMaternityPay,String TestResultExcelFilePath,String TestReportworksheetNo) throws Throwable
	{
		double worksheetvalue = Double.parseDouble(TestReportworksheetNo);
		DecimalFormat df = new DecimalFormat("###.#");
		String worksheetNoWithoutDecimal= df.format(worksheetvalue);
		int TRwNo=Integer.parseInt(worksheetNoWithoutDecimal);
		System.out.println("The converted post value is  :"+TRwNo);
	
		
		
		File excel = new File(TestResultExcelFilePath);
		FileInputStream fis = new FileInputStream(excel);
		org.apache.poi.ss.usermodel.Workbook wb = WorkbookFactory.create(fis);
		org.apache.poi.ss.usermodel.Sheet ws = wb.getSheetAt(TRwNo);

		CellStyle style = wb.createCellStyle();
		style.setFillPattern(CellStyle.ALIGN_FILL);
		style.setFillBackgroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
		Font font = wb.createFont();	
		font.setColor(IndexedColors.BLACK.getIndex());
		style.setFont(font);

		CellStyle styleFalse = wb.createCellStyle();
		styleFalse.setFillPattern(CellStyle.ALIGN_FILL);
		styleFalse.setFillBackgroundColor(IndexedColors.GOLD.getIndex());

		FileOutputStream webdata = new FileOutputStream (TestResultExcelFilePath);

		int rowNum = ws.getLastRowNum()+1;

		for(int i =2; i< rowNum; i++)
		{
			Row row = ws.getRow(i);
			String value1 = cellToString(row.getCell(1));
			String value2 = cellToString(row.getCell(7));
			String value3 = cellToString(row.getCell(8));

			if(value1 != null && value1.equalsIgnoreCase(firstCellOfBody))
			{
				row.createCell(9).setCellValue(statutoryAdoptionPay);
				row.createCell(10).setCellValue(statutoryMaternityPay);
				if(value2 != null && value2.equalsIgnoreCase(statutoryAdoptionPay))
				{
					Cell cell1 = row.createCell(11);			
					row.createCell(11).setCellValue("TRUE");
					cell1.setCellStyle(style);
				}
				else
				{
					Cell cell1 = row.createCell(11);
					row.createCell(11).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if(value3 != null && value3.equalsIgnoreCase(statutoryMaternityPay))
				{
					Cell cell1 = row.createCell(12);			
					row.createCell(12).setCellValue("TRUE");
					cell1.setCellStyle(style);
				}
				else
				{
					Cell cell1 = row.createCell(12);
					row.createCell(12).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}
				break;
			}
		}	
		wb.write(webdata);
		webdata.close();
		fis.close();
	}


	public String cellToString(Cell cell)
	{
		int type;
		Object result;
		type = cell.getCellType();
		switch(type)
		{
		case 0: // to get numeric value from the cell 
			result = Double.toString(cell.getNumericCellValue());
			break;
		case 1: // to get string value from the cell
			result = cell.getStringCellValue();
			break;
		case 2: result=cell.getCellFormula();
		break;
		case 3: result= cell==null;
		break;	
		case 4: result=cell.getRichStringCellValue();
		break;
		default: 
			throw new RuntimeException("there are no othe values");
		}
		return result.toString();
	}



	public void SearchReport(String TaxReport) throws Throwable {
		try {
			if (existsElementchkFor1mts(OR
					.getProperty("findReportTextboxLocator"))) {
				getObject("findReportTextboxLocator").sendKeys("");
				Thread.sleep(1000L);
				getObject("findReportTextboxLocator").sendKeys(TaxReport);
				Thread.sleep(2000L);
				if (existsElementchkFor1mts(OR
						.getProperty("ReportTablelocator"))) {
					System.out.println("Entered FetchReport==========");
					FetchReport();
				}

			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
			SearchReport(TaxReport);
		}
	}
	
	

	public void FetchReport() throws Throwable {

		try {
			System.out.println("Entered FetchReport==========");
			WebElement TableOfReportGrid = driver.findElement(By.xpath(OR
					.getProperty("ReportTablelocator")));
			WebTable RTable = WebTable.getTable(TableOfReportGrid);
			List<WebElement> Table_Report = TableOfReportGrid.findElements(By
					.xpath(OR.getProperty("ReportTableRows")));
			java.util.Iterator<WebElement> Rx = Table_Report.iterator();
			int Reportrownum = 0;
			while (Rx.hasNext()) {
				NameOfReprt = RTable.getTBody().getRow(Reportrownum).getCell(2)
						.getText();
				System.out.println("Report name is :" + NameOfReprt);
				System.out.println("Report name is matched");
				String modifiedReport = getReportNamee(NameOfReprt);
				if (modifiedReport != null) {
					ModifiedReport rp = Enum.valueOf(ModifiedReport.class,
							modifiedReport);
					EnumTestClass enumTestCls = new EnumTestClass();
					enumTestCls.runTestReport(rp);
				}
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
		System.out.println("Exit FetchReport==========");
	}
	
	
	
	public void editCustomButton() throws Throwable {
		try {
			getObject("reportCustomisebtn").click();
			System.out.println("custom button got clicked");
			Thread.sleep(3000L);
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}
	
	

	public void UpdateReportPage(String PayrollId, String PayFrequency,
			String Monthname) throws Throwable {

		Thread.sleep(2000L);
		if (existsElementchkFor1mts(OR.getProperty("customEditbtn"))) {
			System.out.println("Custom Edit button exists");
			getObject("customEditbtn").sendKeys("");
			getObject("customEditbtn").click();
			Thread.sleep(1000L);
		}

		if (existsElementchkFor1mts(OR.getProperty("customPayrollRecordid")))
		{
			getObject("customPayrollRecordid").sendKeys("");
			getObject("customPayrollRecordid").clear();
			/*
			 * temporarily i am not passing parameter for payroll id since the
			 * report is not taking the 'Monthly_Payroll' as payroll id.
			 */
			getObject("customPayrollRecordid").sendKeys(PayrollId);
			Thread.sleep(4000L);
			getObject("customOkbtn").click();
			System.out.println("Payrun updated");
			Thread.sleep(6000L);
		}
		getObject("customPayfrequencyEditbtn").sendKeys("");
		getObject("customPayfrequencyEditbtn").click();
		Thread.sleep(1000L);
		getObject("customPayfrqncyTextfield").clear();
		getObject("customPayfrqncyTextfield").sendKeys(PayFrequency);
		Thread.sleep(1000L);
		getObject("2ncustombtn").click();
		Thread.sleep(6000L);

		getObject("customPayrunEditbtn").click();
		Thread.sleep(4000L);
		getObject("customPaytextfield").clear();
		getObject("customPaytextfield").sendKeys(Monthname);
		Thread.sleep(4000L);
		getObject("3dcustomOkbutton").click();
		Thread.sleep(6000L);

	}

	

	public void RunReport() throws Throwable {
		try {
			if (existsElement(OR.getProperty("customRunReport"))) {
				getObject("customRunReport").click();
				Thread.sleep(6000L);
				System.out.println("");
				System.out
						.println("3> Successfully customized the Report as required");
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

