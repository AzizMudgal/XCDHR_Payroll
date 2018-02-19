package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.IncomeTax_TCMnth6_CSBRNTK50RL;



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

import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Test_Util;

public class TestReports extends TestSuiteBase
{
	String runmodes[] = null;
	static int count = -1;
	public static boolean Fail=false;
	public static boolean Skip=false;
	public static boolean IsTestPass=true;
	public String firstCellOfBody;
	public String titlename;

	
	
	@BeforeTest
	public void CheckTestSkip() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		if(! Test_Util.IsTestcaseRunMode(TaxPayroll_TaxMonth6CSBRNTK50PercentRegulatory_SuiteXls, this.getClass().getSimpleName()))
		{
			Skip=true;
			Test_Util.ReportDataSetResult(
					TaxPayroll_TaxMonth6CSBRNTK50PercentRegulatory_SuiteXls, "first", Test_Util.GetRowNum(TaxPayroll_TaxMonth6CSBRNTK50PercentRegulatory_SuiteXls, this.getClass().getSimpleName()),"Skipped");
			//Test_Util.ReportDataSetResult(TaxPayroll_TaxMonth6CSBRNTK50PercentRegulatory_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase" +this.getClass().getSimpleName() +" as the runmode is set to 'no' ");// this message would display in logs
			throw new Exception("Testcase is being skipped" + this.getClass().getSimpleName()+ "as it's Runmode is set to 'NO'"); // this msg would display in Reports.
		}
		// Load the runmodes of the tests
		runmodes=Test_Util.getDataSetRunmodes(TaxPayroll_TaxMonth6CSBRNTK50PercentRegulatory_SuiteXls, this.getClass().getSimpleName());
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
		
		/* Added by Swamy*/
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
			defaultWaitTime();
		}
		Thread.sleep(4000L);
		if(existsElementchkFor1mts(OR.getProperty("reportTablocator")))
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



	@DataProvider
	public Object[][] getData() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		return Test_Util.getData(TaxPayroll_TaxMonth6CSBRNTK50PercentRegulatory_SuiteXls,"TestReports");
	}



	@AfterMethod
	public void ReportDataSetResult() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		if(Skip)
		{
			Test_Util.ReportDataSetResult(TaxPayroll_TaxMonth6CSBRNTK50PercentRegulatory_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
		}
		else if(Fail)
		{
			IsTestPass = false;
			Test_Util.ReportDataSetResult(TaxPayroll_TaxMonth6CSBRNTK50PercentRegulatory_SuiteXls, this.getClass().getSimpleName(), count+2, "Fail");
		}
		else
		{
			Test_Util.ReportDataSetResult(TaxPayroll_TaxMonth6CSBRNTK50PercentRegulatory_SuiteXls, this.getClass().getSimpleName(), count+2, "Pass");
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
			Test_Util.ReportDataSetResult(TaxPayroll_TaxMonth6CSBRNTK50PercentRegulatory_SuiteXls, "first", Test_Util.GetRowNum(TaxPayroll_TaxMonth6CSBRNTK50PercentRegulatory_SuiteXls, this.getClass().getSimpleName()),"Pass");
		}
		else
		{
			Test_Util.ReportDataSetResult(TaxPayroll_TaxMonth6CSBRNTK50PercentRegulatory_SuiteXls, "first", Test_Util.GetRowNum(TaxPayroll_TaxMonth6CSBRNTK50PercentRegulatory_SuiteXls, this.getClass().getSimpleName()),"Fail");
		}
		System.out.println("closing the browser");
		closeBrowser();
	}

}

