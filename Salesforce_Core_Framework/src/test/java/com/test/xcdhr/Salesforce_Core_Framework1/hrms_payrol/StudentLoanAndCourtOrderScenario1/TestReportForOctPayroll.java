package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payrol.StudentLoanAndCourtOrderScenario1;
import com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.CourtOrderAEO1971civildebt_Scenario.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.List;
import atu.webdriver.utils.table.WebTable;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Test_Util;


public class TestReportForOctPayroll extends CourtOrderAEO1971civildebt
{
	String runmodes[] = null;
	static int count = -1;
	public static boolean Fail=false;
	public static boolean Skip=false;
	public static boolean IsTestPass=true;
	public String titlename;


	@BeforeTest
	public void CheckTestSkip() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		if(! Test_Util.IsTestcaseRunMode(Payroll_CourtOrderScenarioOne_SuiteXls, this.getClass().getSimpleName()))
		{
			Skip=true;
			Test_Util.ReportDataSetResult(
					Payroll_CourtOrderScenarioOne_SuiteXls, "first", Test_Util.GetRowNum(Payroll_CourtOrderScenarioOne_SuiteXls, this.getClass().getSimpleName()),"Skipped");
			//Test_Util.ReportDataSetResult(Payroll_CourtOrderScenarioOne_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase" +this.getClass().getSimpleName() +" as the runmode is set to 'no' ");// this message would display in logs
			throw new Exception("Testcase is being skipped" + this.getClass().getSimpleName()+ "as it's Runmode is set to 'NO'"); // this msg would display in Reports.
		}
		// Load the runmodes of the tests
		runmodes=Test_Util.getDataSetRunmodes(Payroll_CourtOrderScenarioOne_SuiteXls, this.getClass().getSimpleName());
	}

	
	@Test(dataProvider="getData", priority=1)
	public void toSelectOrgForPerformingAutomationTests(String EmpName,String NICategory, String AnnualSalary, String PayFrequency,String EmployerName,String Payrolid,String MonthName,String ExcelInputSheet,String FirstReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,String PayrollView,String TestReportworksheetNo,String ExpectedResultRowNumOfTestResultFile,
			String ActualResultRowNumOfTestResultFile,
			String TestRemarkRowNumOfTestResultFile) throws Throwable
	{
		//APP_LOGS.debug(EmpName+"--"+NICategory+"--"+AnnualSalary+"--"+PayFrequency);
		count++;
		if(! runmodes[count].equalsIgnoreCase("Y"))
		{
			Skip=true;
			throw new SkipException("Runmode for Test set data is set to 'NO' "+count);
		}
		System.out.println("Runmode for Test set data is set to YES hence Executing the test case to select the desired Org");
		System.out.println("");
		toSelectDesiredOrg(OrgFlag);
	}
	
	@Test(dataProvider="getData", priority=2,dependsOnMethods = {"toSelectOrgForPerformingAutomationTests"})
	public void toSearchAndProcessReport(String EmpName,String NICategory, String AnnualSalary, String PayFrequency,String EmployerName,String Payrolid,String MonthName,String ExcelInputSheet,String FirstReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,String PayrollView,String TestReportworksheetNo,String ExpectedResultRowNumOfTestResultFile,String ActualResultRowNumOfTestResultFile,String TestRemarkRowNumOfTestResultFile) throws Throwable
	{
		try
		{
			// The script updates the NI Category for the Automation employees
			System.out.println("");
			//System.out.println("The script now going to select the desired employee(s) as per input sheet and updates the NI Category");
			DownloadReports(EmpName,NICategory,AnnualSalary,PayFrequency,EmployerName,Payrolid,MonthName,ExcelInputSheet,FirstReportNameInApplication,TestResultExcelFilePath,worksheetNo,PayrollView,TestReportworksheetNo,ExpectedResultRowNumOfTestResultFile,ActualResultRowNumOfTestResultFile,TestRemarkRowNumOfTestResultFile); // pn means payroll id. in this case 8512
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage());
			System.out.println(t.getStackTrace());
		}
	}

	public void DownloadReports(String EmpName,String NICategory, String AnnualSalary, String PayFrequency,String EmployerName,String Payrolid,String MonthName,String ExcelInputSheet,String FirstReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,String PayrollView,String TestReportworksheetNo,String ExpectedResultRowNumOfTestResultFile,String ActualResultRowNumOfTestResultFile,String TestRemarkRowNumOfTestResultFile) throws Throwable
	{
		try
		{
			if(existsElementchkFor1mts(OR.getProperty("reportTablocator")))
			{
				getObject("reportTablocator").click();
				System.out.println("2> Clicked to Report Tab");
				Thread.sleep(4000L);
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
				UpdateReportPage(Payrolid,PayFrequency,MonthName);
				System.out.println("");
			}

			if(existsElementchkFor1mts(OR.getProperty("customRunReport")))
			{
				RunReport();
			}

			if(existsElementchkFor1mts(OR.getProperty("reportTableLocatorNI")))
			{
				processReport(EmpName,NICategory,AnnualSalary,PayFrequency,EmployerName,Payrolid,MonthName,ExcelInputSheet,FirstReportNameInApplication,TestResultExcelFilePath,worksheetNo,PayrollView,TestReportworksheetNo,ExpectedResultRowNumOfTestResultFile,ActualResultRowNumOfTestResultFile,TestRemarkRowNumOfTestResultFile);
				System.out.println("7> Entered the values and processed the Test Remarks");
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getStackTrace().toString());
			System.out.println(t.getCause().toString());
		}
	}

	
	
	
	public void processReport(String EmpName,String NICategory, String AnnualSalary, String PayFrequency,String EmployerName,String Payrolid,String MonthName,String ExcelInputSheet,String FirstReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,String PayrollView,String TestReportworksheetNo,String ExpectedResultRowNumOfTestResultFile,String ActualResultRowNumOfTestResultFile,String TestRemarkRowNumOfTestResultFile)throws Throwable
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
						/*
						 * Capturing the actual values from the corresponding Report
						 * 
						 */
						appEmpName= table.getTBody().getRow(rownum).getCell(0).getText();
						System.out.println("The application name is :"+appEmpName);

						String tax= table.getTBody().getRow(rownum).getCell(1).getText();
						System.out.println("The tax is :"+tax);

						String employeeNI= table.getTBody().getRow(rownum).getCell(2).getText();
						System.out.println("The employeeNI is :"+employeeNI);

						String courtOrders= table.getTBody().getRow(rownum).getCell(3).getText();
						System.out.println("The courtOrders is :"+courtOrders);

						String attachableEarnings= table.getTBody().getRow(rownum).getCell(4).getText();
						System.out.println("The attachableEarnings is :"+attachableEarnings);

						String netPay= table.getTBody().getRow(rownum).getCell(5).getText();
						System.out.println("The netPay is :"+netPay);

						String initialDeductionAmount= table.getTBody().getRow(rownum).getCell(6).getText();
						System.out.println("The initialDeductionAmount is :"+initialDeductionAmount);

						String attachableEarnings2= table.getTBody().getRow(rownum).getCell(7).getText();
						System.out.println("The attachableEarnings2 is :"+attachableEarnings2);

						String protectedEarnings= table.getTBody().getRow(rownum).getCell(8).getText();
						System.out.println("The protectedEarnings is :"+protectedEarnings);

						String courtOrderDeductionAmountType= table.getTBody().getRow(rownum).getCell(9).getText();
						System.out.println("The courtOrderDeductionAmountType is :"+courtOrderDeductionAmountType);
						if(courtOrderDeductionAmountType.trim().equalsIgnoreCase(type1))
						{
							type1AmountValue= table.getTBody().getRow(rownum).getCell(10).getText();
							System.out.println("The type1AmountValue is :"+type1AmountValue);
						}

						String CourtOrderAdministrativeType = table.getTBody().getRow(rownum).getCell(9).getText();
						System.out.println("The CourtOrderAdministrativeType is :"+CourtOrderAdministrativeType);
						if(CourtOrderAdministrativeType.trim().equalsIgnoreCase(type2))
						{
							type2AmountValue= table.getTBody().getRow(rownum).getCell(10).getText();
							System.out.println("The type2AmountValue is :"+type2AmountValue);
						}


						String CourtOrderPaymentThirdPartyType = table.getTBody().getRow(rownum).getCell(9).getText();
						System.out.println("The CourtOrderPaymentThirdPartyType is :"+CourtOrderPaymentThirdPartyType);
						if(CourtOrderPaymentThirdPartyType.trim().equalsIgnoreCase(type3))
						{
							type3AmountValue= table.getTBody().getRow(rownum).getCell(10).getText();
							System.out.println("The type3AmountValue is :"+type3AmountValue);
						}
						//call the function which reads the expected result,copy the actual result
						//and finally process the Test remarks into excel sheet.

						ReadsExpectedDataFromTestResult(EmpName,tax,employeeNI,courtOrders,attachableEarnings,netPay,initialDeductionAmount,attachableEarnings2,protectedEarnings,TestResultExcelFilePath,TestReportworksheetNo,ExpectedResultRowNumOfTestResultFile,ActualResultRowNumOfTestResultFile,TestRemarkRowNumOfTestResultFile);

						copyActualResultIntoResultSheetFile(EmpName,tax,employeeNI,courtOrders,attachableEarnings,netPay,initialDeductionAmount,attachableEarnings2,protectedEarnings,TestResultExcelFilePath,TestReportworksheetNo,ExpectedResultRowNumOfTestResultFile,ActualResultRowNumOfTestResultFile,TestRemarkRowNumOfTestResultFile);

						toProcessTestRemarks(EmpName,tax,employeeNI,courtOrders,attachableEarnings,netPay,initialDeductionAmount,attachableEarnings2,protectedEarnings,TestResultExcelFilePath,TestReportworksheetNo,ExpectedResultRowNumOfTestResultFile,ActualResultRowNumOfTestResultFile,TestRemarkRowNumOfTestResultFile);
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


	public void ReadsExpectedDataFromTestResult(String EmpName,String tax,String employeeNI,String courtOrders,String attachableEarnings,
			String netPay,String initialDeductionAmount,String attachableEarnings2,String protectedEarnings,
			String TestResultExcelFilePath,
			String TestReportworksheetNo,String ExpectedResultRowNumOfTestResultFile,String ActualResultRowNumOfTestResultFile,String TestRemarkRowNumOfTestResultFile) throws Throwable
	{

		double worksheetvalue = Double.parseDouble(TestReportworksheetNoFromCode);
		DecimalFormat df = new DecimalFormat("###.#");
		String worksheetNoWithoutDecimal = df.format(worksheetvalue);
		int TRwNo = Integer.parseInt(worksheetNoWithoutDecimal);
		System.out
		.println("The converted integer TestReportWorksheet value is  :"
				+ TRwNo);
       // String TestResultExcelFilePathFromCode =System.getProperty("user.dir")+"/TestOutPutResultFolder\\201819 Payroll Court order and Student Loan Test result.xlsx";
		File excel = new File(TestResultExcelFilePathFromCode);
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

		FileOutputStream webdata = new FileOutputStream(TestResultExcelFilePathFromCode);
		int rowNum1 = ws.getLastRowNum() + 1;
		System.out.println("i am in first method");

		double expectdDataRowNo = Double
				.parseDouble(ExpectedResultRowNumOfTestResultFileFromCode);
		DecimalFormat expctdData = new DecimalFormat("###.#");
		String expctdRowNoWithoutDecimal = expctdData.format(expectdDataRowNo);
		int expctdRowIntValue = Integer.parseInt(expctdRowNoWithoutDecimal);
		System.out
		.println("The converted integer TestReportWorksheet value is  :"
				+ expctdRowIntValue);

		for (int i = expctdRowIntValue; i < rowNum1; i++) // getting the
			// expected data
			// from expected
			// result data row
		{
			Row row = ws.getRow(i);
			value1 = cellToString(row.getCell(1)); // and storing each cell
			// value in each public
			// variable
			value2 = cellToString(row.getCell(2));
			value3 = cellToString(row.getCell(3));
			value4 = cellToString(row.getCell(4));
			value5 = cellToString(row.getCell(5));
			value6 = cellToString(row.getCell(6));
			value7 = cellToString(row.getCell(7));
			value8 = cellToString(row.getCell(8));
			value9 = cellToString(row.getCell(9));
			value10 = cellToString(row.getCell(10));
			value11 = cellToString(row.getCell(11));
			value12 = cellToString(row.getCell(12));//enough for courtOrders and student loan.


			if (value1 != null && value1.equalsIgnoreCase(appEmpName)) {
				System.out.println("The employee name got matched");
				System.out
				.println("captured all the values and stored in the global variables");
				break;
			}
		}
		System.out.println("stored all the values from the first method");
		wb.write(webdata);
		webdata.close();
		fis.close();
	}


	public void copyActualResultIntoResultSheetFile(String EmpName,String tax,String employeeNI,String courtOrders,String attachableEarnings,
			String netPay,String initialDeductionAmount,String attachableEarnings2,String protectedEarnings,
			String TestResultExcelFilePath,
			String TestReportworksheetNo,String ExpectedResultRowNumOfTestResultFile,String ActualResultRowNumOfTestResultFile,String TestRemarkRowNumOfTestResultFile) throws Throwable {
		double worksheetvalue = Double.parseDouble(TestReportworksheetNo);
		DecimalFormat df = new DecimalFormat("###.#");
		String worksheetNoWithoutDecimal = df.format(worksheetvalue);
		int TRwNo = Integer.parseInt(worksheetNoWithoutDecimal);
		System.out
		.println("The converted integer TestReportWorksheet value is  :"
				+ TRwNo);

		File excel = new File(TestResultExcelFilePathFromCode);
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

		FileOutputStream webdata = new FileOutputStream(TestResultExcelFilePathFromCode);

		double actualDataRowNo = Double
				.parseDouble(ActualResultRowNumOfTestResultFileFromCode);
		DecimalFormat actualData = new DecimalFormat("###.#");
		String actualRowNoWithoutDecimal = actualData.format(actualDataRowNo);
		int actualRowIntValue = Integer.parseInt(actualRowNoWithoutDecimal);
		System.out.println("The converted integer actualRowdataNo value is  :"
				+ actualRowIntValue);

		int rowNum = ws.getLastRowNum() + 1;
		for (int i = actualRowIntValue; i < rowNum; i++) // getting the expected
			// data from
			// expected result
			// data row
		{
			Row row = ws.getRow(i);
			if (value1 != null && value1.equalsIgnoreCase(appEmpName))
			{
				//setting the actual result into test result from Org / corresponding Report file
				row.createCell(2).setCellValue(tax);
				row.createCell(3).setCellValue(employeeNI);
				row.createCell(4).setCellValue(courtOrders);
				row.createCell(5).setCellValue(attachableEarnings);
				row.createCell(6).setCellValue(netPay);
				row.createCell(7).setCellValue(initialDeductionAmount);
				row.createCell(8).setCellValue(attachableEarnings2);
				row.createCell(9).setCellValue(protectedEarnings);
				row.createCell(10).setCellValue(type1AmountValue);
				row.createCell(11).setCellValue(type2AmountValue);
				row.createCell(12).setCellValue(type3AmountValue);

				System.out
				.println("pasted actual Result data into the test result excel file");
				break;
			}
		}
		wb.write(webdata);
		webdata.close();
		fis.close();
	}


	public void toProcessTestRemarks(String EmpName,String tax,String employeeNI,String courtOrders,String attachableEarnings,
			String netPay,String initialDeductionAmount,String attachableEarnings2,String protectedEarnings,
			String TestResultExcelFilePath,
			String TestReportworksheetNo,String ExpectedResultRowNumOfTestResultFile,String ActualResultRowNumOfTestResultFile,String TestRemarkRowNumOfTestResultFile) throws Throwable
	{
		System.out.println("This is ReadExpected data1");
		double worksheetvalue = Double.parseDouble(TestReportworksheetNo);
		DecimalFormat df = new DecimalFormat("###.#");
		String worksheetNoWithoutDecimal = df.format(worksheetvalue);
		int TRwNo = Integer.parseInt(worksheetNoWithoutDecimal);
		System.out
		.println("The converted integer TestReportWorksheet value is  :"
				+ TRwNo);

		File excel = new File(TestResultExcelFilePathFromCode);
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

		FileOutputStream webdata = new FileOutputStream(TestResultExcelFilePathFromCode);

		double testRemarkDataRowNo = Double
				.parseDouble(TestRemarkRowNumOfTestResultFileFromCode);
		DecimalFormat testRmrkData = new DecimalFormat("###.#");
		String testRmrkRowNoWithoutDecimal = testRmrkData
				.format(testRemarkDataRowNo);
		int testRmrkRowIntValue = Integer.parseInt(testRmrkRowNoWithoutDecimal);
		System.out.println("The converted integer TestRemarkRowNo value is  :"
				+ testRmrkRowIntValue);
		int rowNum = ws.getLastRowNum() + 1;
		for (int j = testRmrkRowIntValue; j < rowNum; j++)
		{
			Row row = ws.getRow(j);
			System.out.println("the value stored in value1 is :" + value1);
			if (value1 != null && value1.equalsIgnoreCase(appEmpName)) {
				System.out.println("the value stored in value2 is :" + value2
						+ "needs to be compared");
				if (value2 != null
						&& value2.equalsIgnoreCase(tax)) {
					Cell cell1 = row.createCell(2);
					row.createCell(2).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(2);
					row.createCell(2).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}
				if (value3 != null && value3.equalsIgnoreCase(employeeNI)) {
					Cell cell1 = row.createCell(3);
					row.createCell(3).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(3);
					row.createCell(3).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value4 != null && value4.equalsIgnoreCase(courtOrders)) {
					Cell cell1 = row.createCell(4);
					row.createCell(4).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(4);
					row.createCell(4).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value5 != null && value5.equalsIgnoreCase(attachableEarnings)) {
					Cell cell1 = row.createCell(5);
					row.createCell(5).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(5);
					row.createCell(5).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value6 != null && value6.equalsIgnoreCase(netPay)) {
					Cell cell1 = row.createCell(6);
					row.createCell(6).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(6);
					row.createCell(6).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value7 != null && value7.equalsIgnoreCase(initialDeductionAmount)) {
					Cell cell1 = row.createCell(7);
					row.createCell(7).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(7);
					row.createCell(7).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value8 != null && value8.equalsIgnoreCase(attachableEarnings2)) {
					Cell cell1 = row.createCell(8);
					row.createCell(8).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(8);
					row.createCell(8).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value9 != null && value9.equalsIgnoreCase(protectedEarnings)) {
					Cell cell1 = row.createCell(9);
					row.createCell(9).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(9);
					row.createCell(9).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value10 != null && value10.equalsIgnoreCase(type1AmountValue)) {
					Cell cell1 = row.createCell(10);
					row.createCell(10).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(10);
					row.createCell(10).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value11 != null && value11.equalsIgnoreCase(type2AmountValue)) {
					Cell cell1 = row.createCell(11);
					row.createCell(11).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(11);
					row.createCell(11).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value12 != null && value12.equalsIgnoreCase(type3AmountValue)) {
					Cell cell1 = row.createCell(12);
					row.createCell(12).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(12);
					row.createCell(12).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}
				break;
			}
		}
		System.out
		.println("Entered the test remarks into the excel sheet successfully");
		wb.write(webdata);
		webdata.close();
		fis.close();
	}

	

	/*
	 * To get the data from the specific input excel sheet 
	 */
	@DataProvider
	public Object[][] getData() throws Throwable
	{
		return Test_Util.getData(Payroll_CourtOrderScenarioOne_SuiteXls,"TestReportForOctPayroll");
	}



	@AfterMethod
	public void toReportDataSetResult() throws Throwable
	{
		if(Skip)
		{
			Test_Util.ReportDataSetResult(Payroll_CourtOrderScenarioOne_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
		}
		else if(Fail)
		{
			IsTestPass = false;
			Test_Util.ReportDataSetResult(Payroll_CourtOrderScenarioOne_SuiteXls, this.getClass().getSimpleName(), count+2, "Fail");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_CourtOrderScenarioOne_SuiteXls, this.getClass().getSimpleName(), count+2, "Pass");
		}
		Skip=false;
		Fail=false;
	}


	/*
	 *  This will update the testresult in the first worksheet where in for that test case , even if one of the test data specified in second worksheet fails, the test 
		would be considered as fail.And the same would be updated.
	 */
	@AfterTest
	public void toReportTestResult() throws Throwable
	{
		if(IsTestPass)
		{
			Test_Util.ReportDataSetResult(Payroll_CourtOrderScenarioOne_SuiteXls, "first", Test_Util.GetRowNum(Payroll_CourtOrderScenarioOne_SuiteXls, this.getClass().getSimpleName()),"Pass");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_CourtOrderScenarioOne_SuiteXls, "first", Test_Util.GetRowNum(Payroll_CourtOrderScenarioOne_SuiteXls, this.getClass().getSimpleName()),"Fail");
		}	
		//After performing all the said functionalities the test script closes the browser.
		closeBrowser();
	}
	

}
