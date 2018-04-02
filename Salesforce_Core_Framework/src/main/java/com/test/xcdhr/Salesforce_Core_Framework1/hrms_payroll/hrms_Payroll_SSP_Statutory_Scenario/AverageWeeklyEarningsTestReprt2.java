package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.hrms_Payroll_SSP_Statutory_Scenario;

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
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import atu.webdriver.utils.table.WebTable;

import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.ErrorUtil;
import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Test_Util;




public class AverageWeeklyEarningsTestReprt2 extends TestSuiteBase {
	String runmodes[] = null;
	static int count = -1;
	public static boolean Fail=false;
	public static boolean Skip=false;
	public static boolean IsTestPass=true;
	public String EmployeeName;
	public int Row_count;


	@BeforeTest
	public void CheckTestSkip() throws Exception{
		if(! Test_Util.IsTestcaseRunMode(Payroll_SSP_ProcessPayroll_SuiteXls, this.getClass().getSimpleName())){

			Skip=true;
			Test_Util.ReportDataSetResult(Payroll_SSP_ProcessPayroll_SuiteXls, "first", Test_Util.GetRowNum(Payroll_SSP_ProcessPayroll_SuiteXls, this.getClass().getSimpleName()),"Skipped");
			//Test_Util.ReportDataSetResult(Payroll_SSP_ProcessPayroll_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase" +this.getClass().getSimpleName() +" as the runmode is set to 'no' ");// this message would display in logs

			throw new Exception("Testcase is being skipped" + this.getClass().getSimpleName()+ "as it's Runmode is set to 'NO'"); // this msg would display in Reports.

		}

		// Load the runmodes of the tests

		runmodes=Test_Util.getDataSetRunmodes(Payroll_SSP_ProcessPayroll_SuiteXls, this.getClass().getSimpleName());

	}


	@Test(dataProvider = "getData")
	public void CompareReports(String EmployerName,String EmpName,String Payrolid,String Frquency,String MonthName,String ExcelInputSheet,String FirstReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,String PayrollVeiw,String TestReportworksheetNo) throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);

		count++;
		if(! runmodes[count].equalsIgnoreCase("Y"))
		{
			Skip=true;
			throw new SkipException("Runmode for Test set data is set to 'NO' "+count);
		}
		APP_LOGS.debug("Executing the test case");
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
			APP_LOGS.debug("Could not assert the home page title due to unsuccessfull login account");
			System.out.println("");
			ErrorUtil.addVerificationFailure(t);
			CaptureScreenshot("EmployeeProfile"+this.getClass().getSimpleName()+"  Due to this Error Could not Assert Title");
		}
		if(existsElement(OR.getProperty("reportTablocator")))
		{
			DownloadReports(EmployerName,EmpName,Payrolid,Frquency,MonthName,ExcelInputSheet,FirstReportNameInApplication,TestResultExcelFilePath,worksheetNo,PayrollVeiw,TestReportworksheetNo); // pn means payroll id. in this case 8512
		}
		else
		{
			System.out.println("Report Tab doesnot exist hence quitting this test");
		}
	}

	public void DownloadReports(String EmployerName,String EmpName,String Payrolid,String Frquency,String MonthName,String ExcelInputSheet,String FirstReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,String PayrollVeiw,String TestReportworksheetNo) throws Throwable
	{
		if(existsElementchkFor1mts(OR.getProperty("reportTablocator")))
		{
			getObject("reportTablocator").click();
			System.out.println("2> Clicked to Report Tab");
		}

		if(existsElement(OR.getProperty("findReportTextboxLocator")))
		{				
			SearchReport(FirstReportNameInApplication);
		}
			
		if(existsElement(OR.getProperty("reportTableLocatorNI")))
		{
			processReport(EmployerName,EmpName,Payrolid,Frquency,MonthName,ExcelInputSheet,FirstReportNameInApplication,TestResultExcelFilePath,worksheetNo,PayrollVeiw,TestReportworksheetNo);
			System.out.println("7> Entered the values and processed the Test Remarks");
		}
	}
		
		
	

	public void processReport(String EmployerName,String EmpName,String Payrolid,String Frquency,String MonthName,String ExcelInputSheet,String FirstReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,String PayrollVeiw,String TestReportworksheetNo)throws Throwable
	{
		try
		{
		if(existsElement(OR.getProperty("reportTableLocatorNI")))
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
		while(x.hasNext())
		{
			if(rownum==(Row_count-2))
			{	
				System.out.println("Total count of Employee records displayed in the report are :"+rownum);
				System.out.println("");
				System.out.println("The script successfully read and output the values and accordingly gave the TEST REMARKS in NI-HMRC Excel file");
				CaptureScreenshot("GeneralTaxRateMonthly"+this.getClass().getSimpleName());
				break;
			}
			else
			{
				EmployeeName= table.getTBody().getRow(rownum).getCell(0).getText();
				System.out.println("EmployeeName is :"+EmployeeName);
				String PIW2= table.getTBody().getRow(rownum).getCell(1).getText();
				System.out.println("PIW2 is :"+PIW2);
				String AWE= table.getTBody().getRow(rownum).getCell(2).getText();
				System.out.println("AWE is :"+AWE);
				String statutoryStandardRate = table.getTBody().getRow(rownum).getCell(3).getText();
				System.out.println("statutoryStandardRate  is :"+statutoryStandardRate);
				String LeaveRequest= table.getTBody().getRow(rownum).getCell(4).getText();
				System.out.println("LeaveRequest is :"+LeaveRequest);
				String LeaveYear=table.getTBody().getRow(rownum).getCell(5).getText();
				System.out.println("LeaveRequest is :"+LeaveYear);
				
				
				//call the function which reads the excel sheet.
				ReadsExpectedData(EmployeeName, PIW2, AWE,statutoryStandardRate,LeaveRequest,LeaveYear,TestResultExcelFilePath,TestReportworksheetNo);
				//ReadsExpectedData(firstCellOfBody, employeeNI, employerNI,employeeNIPaidYTD,TestResultExcelFilePath,TestReportworksheetNo);

			}
			rownum++;
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
		}
	}


	public void ReadsExpectedData(String EmployeeName, String PIW2, String AWE, String statutoryStandardRate, String LeaveRequest,String LeaveYear,String TestResultExcelFilePath,String TestReportworksheetNo) throws Throwable
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
			String value2 = cellToString(row.getCell(6));
			String value3 = cellToString(row.getCell(7));
			String value4 = cellToString(row.getCell(8));
			String value5 = cellToString(row.getCell(9));
			String value6 = cellToString(row.getCell(10));
		
			if(value1 != null && value1.equalsIgnoreCase(EmployeeName))
			{
				row.createCell(6).setCellValue(PIW2);
				row.createCell(9).setCellValue(LeaveRequest);
				row.createCell(11).setCellValue(PIW2);
				row.createCell(12).setCellValue(AWE);
				row.createCell(13).setCellValue(statutoryStandardRate);
				row.createCell(14).setCellValue(LeaveRequest);
				row.createCell(15).setCellValue(LeaveYear);
				
				//String piwval = cellToString(row.getCell(10));
				if(value2 != null && value2.equalsIgnoreCase(PIW2))
				{
					 Cell cell1 = row.createCell(16);	
			 		row.createCell(16).setCellValue("TRUE");
					 cell1.setCellStyle(style);
				}
				else
				{
					 Cell cell1 = row.createCell(16);	
					row.createCell(16).setCellValue("FALSE");
					 cell1.setCellStyle(styleFalse);
				}
				
				
				if(value3 != null && value3.equalsIgnoreCase(AWE))
				{
					 Cell cell1 = row.createCell(17);
					row.createCell(17).setCellValue("TRUE");
					 cell1.setCellStyle(style);
				}   
				else
				{
					 Cell cell1 = row.createCell(17);
					row.createCell(17).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				} 
				
				
				//String value4 = cellToString(row.getCell(12));
				if(value4 != null && value4.equalsIgnoreCase(statutoryStandardRate))
				{
					 Cell cell1 = row.createCell(18);
					row.createCell(18).setCellValue("TRUE");
					 cell1.setCellStyle(style);
				}   
				else
				{
					 Cell cell1 = row.createCell(18);
					row.createCell(18).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				} 
				
				if(value5 != null && value5.equalsIgnoreCase(LeaveRequest))
				{
					 Cell cell1 = row.createCell(19);
					row.createCell(19).setCellValue("TRUE");
					 cell1.setCellStyle(style);
				}   
				else
				{
					 Cell cell1 = row.createCell(19);
					row.createCell(19).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				} 
				
				if(value6 != null && value6.equalsIgnoreCase(LeaveYear))
				{
					 Cell cell1 = row.createCell(20);
					row.createCell(20).setCellValue("TRUE");
					 cell1.setCellStyle(style);
				}   
				else
				{
					 Cell cell1 = row.createCell(20);
					row.createCell(20).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				} 
				
				break;
			}
		}	
		wb.write(webdata);
		webdata.close();
		fis.close();
	}


	public String cellToString(Cell cell){
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
	
	@DataProvider
	public Object[][] getData() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		return Test_Util.getData(Payroll_SSP_ProcessPayroll_SuiteXls,"AverageWeeklyEarningsTestReprt2");
	}



	@AfterMethod
	public void ReportDataSetResult()
	{
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
	public void ReportTestResult()
	{
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

