package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.NI_weeklyCatA;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

public class NIWeeklyCat_A extends TestSuiteBase {

	String runmodes[] = null;
	static int count = -1;
	static int countCompensation = -1;

	public static boolean Fail=false;
	public static boolean Skip=false;
	public static boolean IsTestPass=true;
	public int Row_count;
	public int rownumaz;
	public int rownum;
	public int rownumc;
	public int rowSize;

	
	@BeforeTest
	public void CheckTestSkip() throws Throwable{
		processDesiredTaxYearInputExcelFile(TaxYear);
		if(! Test_Util.IsTestcaseRunMode(Payroll_CatA_SuiteXls, this.getClass().getSimpleName())){

			Skip=true;
			Test_Util.ReportDataSetResult(Payroll_CatA_SuiteXls, "first", Test_Util.GetRowNum(Payroll_CatA_SuiteXls, this.getClass().getSimpleName()),"Skipped");
			//Test_Util.ReportDataSetResult(Payroll_CatA_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase" +this.getClass().getSimpleName() +" as the runmode is set to 'no' ");// this message would display in logs

			throw new Exception("Testcase is being skipped" + this.getClass().getSimpleName()+ "as it's Runmode is set to 'NO'"); // this msg would display in Reports.

		}

		// Load the runmodes of the tests

		runmodes=Test_Util.getDataSetRunmodes(Payroll_CatA_SuiteXls, this.getClass().getSimpleName());

	}

	public String payfreqncy;
	boolean employeeFirsttimeView = true;
	boolean compensationFirsttimeView = true;
	boolean shouldOpenBrowser = true; 


	@Test(dataProvider="getData", priority=1)
	public void EmpsSetup_WithNICategory(String EmpName,String NICategory, String AnnualSalary, String PayFrequency) throws Throwable
	{
		//APP_LOGS.debug("Entering the Leave parameters");
		APP_LOGS.debug(EmpName+"--"+NICategory+"--"+AnnualSalary+"--"+PayFrequency);

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
			logingIntoDesiredORG(OrgFlag);

			driver.manage().window().maximize();
			try
			{
				closePopupWindow();
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

		// The script updates the NI Category for the Automation employees
		UpdateEmployeeNICategory(EmpName,NICategory);

		/*************************************************************************/
	}



	@Test(dataProvider="getData", priority=2)
	public void EmpsSetup_WithAnnualSalary(String EmpName,String NICategory, String AnnualSalary, String PayFrequency) throws Throwable
	{
		countCompensation++;
		if(! runmodes[countCompensation].equalsIgnoreCase("Y"))
		{
			Skip=true;
			throw new SkipException("Runmode for Test set data is set to 'NO' "+countCompensation);
		}

		/*************************************************************************/
		// The script updates the Annual salary in the compensation Tab for the Automation employees
		UpdateAnnualSalary1(EmpName,AnnualSalary,PayFrequency);
		/*************************************************************************/
	}

	
	
	
	
	public void UpdateEmployeeNICategory1(String empName, String NICategory)
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
									System.out.println("incrementing the row number");
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

	public void searchEmployeeAndUpdateNICatgory(String empName,
			String NICategory) throws Throwable {
		
	}


	
	/**
	 * 
	 * This is correct one
	 * 
	 */
	
	public void UpdateAnnualSalary1(String EmpName, String annualSalary,
			String PayFrequency) throws Throwable {
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
								updatesal.sendKeys(annualSalary);
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
							UpdatePayFrequency1(EmpName, annualSalary,
									PayFrequency);
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

	
	public void UpdatePayFrequency1(String empName, String AnnualSalary,
			String PayFrequency) throws Throwable {
		try {
			String firstRowOfPayFrequency = "//div[" + rownum + "]" + "/"
					+ "table/" + "tbody/" + "tr/" + "td["
					+ compPayfrequencyColumn + "]" + "/" + "div";
			if (existsElement(firstRowOfPayFrequency)) {
				Thread.sleep(2000L);
				// String RowOfPayFrequency =
				// "//div["+rownum+"]"+"/"+"table/"+"tbody/"+"tr/"+"td["+"7]"+"/"+"div";
				Actions action2 = new Actions(driver);
				action2.doubleClick(
						driver.findElement(By.xpath(firstRowOfPayFrequency)))
						.perform();
				action2.moveToElement(getObject("payFrequencyDropdown"))
						.perform();
				Thread.sleep(2000L);
				if (existsElement(OR.getProperty("payFrequencyDropdown"))) {
					// Select selectByValue = new
					// Select(driver.findElement(By.xpath(OR.getProperty("payFrequencyDropdown"))));
					// selectByValue.selectByVisibleText(PayFrequency);
					getObject("payFrequencyDropdown").sendKeys("");
					getObject("payFrequencyDropdown").sendKeys(PayFrequency);
					System.out.println("Selected the PayFrequency item as :"
							+ PayFrequency);
					Thread.sleep(2000L);
					if (existsElement(OR.getProperty("payFrequencyUpdate"))) {
						getObject("payFrequencyUpdate").click();
						System.out
								.println("The update button got clicked and Pay frequency Category got saved");
					}
				}
			}

		} catch (Throwable t) {
			APP_LOGS.debug("Check the Pay frequency Method for errors");
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
			ErrorUtil.addVerificationFailure(t);
		}

	}
	
	
	@DataProvider
	public Object[][] getData() throws Throwable{
		processDesiredTaxYearInputExcelFile(TaxYear);
		return Test_Util.getData(Payroll_CatA_SuiteXls,"NIWeeklyCat_A");
	}


	@AfterMethod
	public void ReportDataSetResult() throws Throwable{
		processDesiredTaxYearInputExcelFile(TaxYear);
		if(Skip){
			Test_Util.ReportDataSetResult(Payroll_CatA_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
		}else if(Fail){

			IsTestPass = false;

			Test_Util.ReportDataSetResult(Payroll_CatA_SuiteXls, this.getClass().getSimpleName(), count+2, "Fail");
		}else{
			Test_Util.ReportDataSetResult(Payroll_CatA_SuiteXls, this.getClass().getSimpleName(), count+2, "Pass");
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
			Test_Util.ReportDataSetResult(Payroll_CatA_SuiteXls, "first", Test_Util.GetRowNum(Payroll_CatA_SuiteXls, this.getClass().getSimpleName()),"Pass");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_CatA_SuiteXls, "first", Test_Util.GetRowNum(Payroll_CatA_SuiteXls, this.getClass().getSimpleName()),"Fail");
		}	

		closeBrowser();
	}


}
