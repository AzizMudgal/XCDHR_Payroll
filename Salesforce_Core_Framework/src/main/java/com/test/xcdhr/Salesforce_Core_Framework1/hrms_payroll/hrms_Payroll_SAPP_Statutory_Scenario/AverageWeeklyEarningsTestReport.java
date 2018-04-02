package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.hrms_Payroll_SAPP_Statutory_Scenario;


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



public class AverageWeeklyEarningsTestReport extends TestSuiteBase {
	String runmodes[] = null;
	static int count = -1;
	public static boolean Fail=false;
	public static boolean Skip=false;
	public static boolean IsTestPass=true;
	public String firstCellOfBody;
	
	

	@BeforeTest
	public void CheckTestSkip() throws Exception
	{
		if(! Test_Util.IsTestcaseRunMode(Payroll_Statutory_AdoptionPaternitypay_SuiteXls, this.getClass().getSimpleName()))
		{
			Skip=true;
			Test_Util.ReportDataSetResult(Payroll_Statutory_AdoptionPaternitypay_SuiteXls, "first", Test_Util.GetRowNum(Payroll_Statutory_AdoptionPaternitypay_SuiteXls, this.getClass().getSimpleName()),"Skipped");
			//Test_Util.ReportDataSetResult(Payroll_Statutory_AdoptionPaternitypay_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase" +this.getClass().getSimpleName() +" as the runmode is set to 'no' ");// this message would display in logs
			throw new Exception("Testcase is being skipped" + this.getClass().getSimpleName()+ "as it's Runmode is set to 'NO'"); // this msg would display in Reports.
		}
		// Load the runmodes of the tests
		runmodes=Test_Util.getDataSetRunmodes(Payroll_Statutory_AdoptionPaternitypay_SuiteXls, this.getClass().getSimpleName());
	}


	@Test(dataProvider = "getData")
	public void EmpsPayroll_Setup_ForIncomeTax(String EmployerName,String EmpName,String Payrolid,String Frquency,String MonthName,String ExcelInputSheet,String FirstReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,String PayrollVeiw,String TestReportworksheetNo) throws Throwable
	{
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
		}catch(Throwable t)
		{
			APP_LOGS.debug("Could not assert the home page title due to unsuccessfull login account");
			System.out.println("");
			ErrorUtil.addVerificationFailure(t);
			CaptureScreenshot("EmployeeProfile"+this.getClass().getSimpleName()+"  Due to this Error Could not Assert Title");
		}
		DownloadReports(EmpName,Payrolid,Frquency,MonthName,FirstReportNameInApplication,TestResultExcelFilePath,TestReportworksheetNo);
	}

		public void DownloadReports(String EmpName,String Payrolid,String Frquency,String MonthName,String FirstReportNameInApplication,String TestResultExcelFilePath,String TestReportworksheetNo) throws Throwable
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
				processReport(EmpName,TestResultExcelFilePath,TestReportworksheetNo);
				System.out.println("5> Entered the values and processed the Test Remarks");
			}
		}
		
		
	
	public void processReport(String EmpName,String TestResultExcelFilePath,String TestReportworksheetNo)throws Throwable
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
				firstCellOfBody= table.getTBody().getRow(rownum).getCell(0).getText();
				System.out.println("firstCellOfBody is :"+firstCellOfBody);
				//String employeeNI= table.getTBody().getRow(rownum).getCell(1).getText();
				String averageWeeklyEarnings= table.getTBody().getRow(rownum).getCell(1).getText();
				System.out.println("averageWeeklyEarnings is :"+averageWeeklyEarnings);
				String statutoryStandardRate = table.getTBody().getRow(rownum).getCell(2).getText();
				System.out.println("statutoryStandardRate is :"+statutoryStandardRate );// Added by Swamy
				//String employerNI= table.getTBody().getRow(rownum).getCell(3).getText();
				String leaveRequestId = table.getTBody().getRow(rownum).getCell(3).getText();
				System.out.println("leaveRequestId is :"+leaveRequestId);
				//String employeeNIPaidYTD= table.getTBody().getRow(rownum).getCell(4).getText();
				String leaveYear= table.getTBody().getRow(rownum).getCell(4).getText();
				System.out.println("leaveYear is :"+leaveYear);
							
				//call the function which reads the excel sheet.
				
				 /*
				 * introduce 1 stmt.aziz updage
				 */
				ReadsExpectedData(EmpName, TestResultExcelFilePath,averageWeeklyEarnings,statutoryStandardRate,leaveRequestId,leaveYear,TestReportworksheetNo);
			}
			rownum++;
		  }
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
		}
	}

	 /*
	 * introduce 1 stmt.aziz updage
	 */
	public void ReadsExpectedData(String EmpName,String TestResultExcelFilePath,String averageWeeklyEarnings,String statutoryStandardRate,String leaveRequestId,String leaveYear,String TestReportworksheetNo) throws Throwable
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
			System.out.println("value2 is=====> :"+value2);
			String value3 = cellToString(row.getCell(8));// Added by Swamy
			System.out.println("value3 is=====> :"+value3);
			String value4 = cellToString(row.getCell(9));
			System.out.println("value4 is=====> :"+value4);
			String value5 = cellToString(row.getCell(10));
			System.out.println("value5 is=====> :"+value5);
					
			if(value1 != null && value1.equalsIgnoreCase(EmpName))
			{
				row.createCell(9).setCellValue(leaveRequestId);
				row.createCell(11).setCellValue(averageWeeklyEarnings);
				row.createCell(12).setCellValue(statutoryStandardRate);
				row.createCell(13).setCellValue(leaveRequestId);
				row.createCell(14).setCellValue(leaveYear);
							
				if(value2 != null && value2.equalsIgnoreCase(averageWeeklyEarnings))
				{
					 Cell cell1 = row.createCell(15);	
					row.createCell(15).setCellValue("TRUE");
					 cell1.setCellStyle(style);
				}
				else
				{
					 Cell cell1 = row.createCell(15);	
					row.createCell(15).setCellValue("FALSE");
					 cell1.setCellStyle(styleFalse);
				}
				
				if(value3 != null && value3.equalsIgnoreCase(statutoryStandardRate))
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
				
				
				if(value4 != null && value4.equalsIgnoreCase(leaveRequestId))
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
				
				if(value5 != null && value4.equalsIgnoreCase(leaveYear))
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
	
	
	
	@DataProvider
	public Object[][] getData()
	{
		return Test_Util.getData(Payroll_Statutory_AdoptionPaternitypay_SuiteXls,"AverageWeeklyEarningsTestReport");
	}

	

	@AfterMethod
	public void ReportDataSetResult()
	{
		if(Skip)
		{
			Test_Util.ReportDataSetResult(Payroll_Statutory_AdoptionPaternitypay_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
		}
		else if(Fail)
		{
			IsTestPass = false;
			Test_Util.ReportDataSetResult(Payroll_Statutory_AdoptionPaternitypay_SuiteXls, this.getClass().getSimpleName(), count+2, "Fail");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_Statutory_AdoptionPaternitypay_SuiteXls, this.getClass().getSimpleName(), count+2, "Pass");
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
			Test_Util.ReportDataSetResult(Payroll_Statutory_AdoptionPaternitypay_SuiteXls, "first", Test_Util.GetRowNum(Payroll_Statutory_AdoptionPaternitypay_SuiteXls, this.getClass().getSimpleName()),"Pass");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_Statutory_AdoptionPaternitypay_SuiteXls, "first", Test_Util.GetRowNum(Payroll_Statutory_AdoptionPaternitypay_SuiteXls, this.getClass().getSimpleName()),"Fail");
		}	
		closeBrowser();
	}

}

