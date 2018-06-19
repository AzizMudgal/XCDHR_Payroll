package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.hrms_Payroll_SPP_Statutory_Scenario;




import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Test_Util;

public class ProcessPayrollForJan2016RndTwo extends TestSuiteBase
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
	boolean windowExclude = true;
	
	
	
	@BeforeTest
	public void CheckTestSkip() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);

		if (!Test_Util.IsTestcaseRunMode(Payroll_Statutory_Paternitypay_SuiteXls, this
				.getClass().getSimpleName()))
		{
			Skip = true;
			Test_Util.ReportDataSetResult(Payroll_Statutory_Paternitypay_SuiteXls, "first",
					Test_Util.GetRowNum(Payroll_Statutory_Paternitypay_SuiteXls, this
							.getClass().getSimpleName()), "Skipped");
			// Test_Util.ReportDataSetResult(Payroll_Statutory_Paternitypay_SuiteXls,
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
		runmodes = Test_Util.getDataSetRunmodes(Payroll_Statutory_Paternitypay_SuiteXls, this
				.getClass().getSimpleName());

	}

	public String payfreqncy;
	boolean exlude = true;
	boolean compensationFirsttimeView = true;
	boolean shouldOpenBrowser = true;

	@Test(dataProvider = "getData")
	public void EmpsPayroll_Setup_ForIncomeTax(String EmployerName,String EmpName,String Payrolid,String Frquency,String MonthName,String ExcelInputSheet,String FirstReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,String PayrollVeiw,String TestReportworksheetNo) throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);

		count++;
		if (!runmodes[count].equalsIgnoreCase("Y"))
		{
			Skip = true;
			throw new SkipException("Runmode for Test set data is set to 'NO' "
					+ count);
		}

		
		if (shouldOpenBrowser) {
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
				PayrollForStatutoryMonthly(EmployerName,EmpName,Payrolid,Frquency,MonthName,ExcelInputSheet,FirstReportNameInApplication,PayrollVeiw);

			}
			catch (Throwable t)
			{
				System.out.println(t.getMessage().toString());
				System.out.println(t.getStackTrace().toString());
			}
		}
		
		ExcludeIncludeEmpLocal(EmpName,ExcelInputSheet,worksheetNo);
	
	}
	
	public void ExcludeIncludeEmpLocal(String EmpName, String Exclinputsheet,
			String worksheetNo) throws Throwable {
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
									+ Exclinputsheet));

			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet spreadsheet = workbook.getSheetAt(wNo);
			totalRows = spreadsheet.getLastRowNum();
			System.out
					.println("Total rows in the processpayrollforMonthlytax worksheet is :"
							+ totalRows);
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
			catch (Throwable t) 
			{
				System.out.println(t.getMessage().toString());
				System.out.println(t.getStackTrace().toString());
			}
	}



	@DataProvider
	public Object[][] getData() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		return Test_Util.getData(Payroll_Statutory_Paternitypay_SuiteXls,"ProcessPayrollForJan2016RndTwo");
	}

	
	
	@AfterMethod
	public void ReportDataSetResult() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);

		if (Skip)
		{
			Test_Util.ReportDataSetResult(Payroll_Statutory_Paternitypay_SuiteXls, this
					.getClass().getSimpleName(), count + 2, "Skip");
		} else if (Fail)
		{

			IsTestPass = false;

			Test_Util.ReportDataSetResult(Payroll_Statutory_Paternitypay_SuiteXls, this
					.getClass().getSimpleName(), count + 2, "Fail");
		} else
		{
			Test_Util.ReportDataSetResult(Payroll_Statutory_Paternitypay_SuiteXls, this
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

			Test_Util.ReportDataSetResult(Payroll_Statutory_Paternitypay_SuiteXls, "first",
					Test_Util.GetRowNum(Payroll_Statutory_Paternitypay_SuiteXls, this
							.getClass().getSimpleName()), "Pass");

		} else
		{

			Test_Util.ReportDataSetResult(Payroll_Statutory_Paternitypay_SuiteXls, "first",
					Test_Util.GetRowNum(Payroll_Statutory_Paternitypay_SuiteXls, this
							.getClass().getSimpleName()), "Fail");

		}
		closeBrowser();
	}

}
