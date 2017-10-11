package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.rti_Payroll_Scenario4_Month1;


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



public class TestAprilReports extends TestSuiteBase
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
		if(! Test_Util.IsTestcaseRunMode(Payroll_RecognitionScenarioFour_SuiteXls, this.getClass().getSimpleName()))
		{
			Skip=true;
			Test_Util.ReportDataSetResult(
					Payroll_RecognitionScenarioFour_SuiteXls, "first", Test_Util.GetRowNum(Payroll_RecognitionScenarioFour_SuiteXls, this.getClass().getSimpleName()),"Skipped");
			//Test_Util.ReportDataSetResult(Payroll_RecognitionScenarioFour_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase" +this.getClass().getSimpleName() +" as the runmode is set to 'no' ");// this message would display in logs
			throw new Exception("Testcase is being skipped" + this.getClass().getSimpleName()+ "as it's Runmode is set to 'NO'"); // this msg would display in Reports.
		}
		// Load the runmodes of the tests
		runmodes=Test_Util.getDataSetRunmodes(Payroll_RecognitionScenarioFour_SuiteXls, this.getClass().getSimpleName());
	}



	@Test(dataProvider = "getData")
	public void EmpsPayroll_Setup_ForIncomeTax(String EmployerName,String EmpName,String Payrolid,String Frquency,String MonthName,String ExcelInputSheet,String FirstReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,String PayrollVeiw,String TestReportworksheetNo,String ExpectedResultRowNumOfTestResultFile,String ActualResultRowNumOfTestResultFile,String TestRemarkRowNumOfTestResultFile) throws Throwable
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
			titlename = driver.getTitle();
			Assert.assertEquals(driver.getTitle(), titlename);
			System.out.println("1> The test script logged in successfully into salesforce account and now in Home page");
			System.out.println("");
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
			DownloadReports(EmployerName,EmpName,Payrolid,Frquency,MonthName,ExcelInputSheet,FirstReportNameInApplication,TestResultExcelFilePath,worksheetNo,PayrollVeiw,TestReportworksheetNo,ExpectedResultRowNumOfTestResultFile,ActualResultRowNumOfTestResultFile,TestRemarkRowNumOfTestResultFile); // pn means payroll id. in this case 8512
		}
		else
		{
			System.out.println("Report Tab doesnot exist hence quitting this test");
		}
	}



	public void DownloadReports(String EmployerName,String EmpName,String Payrolid,String Frquency,String MonthName,String ExcelInputSheet,String FirstReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,String PayrollVeiw,String TestReportworksheetNo,String ExpectedResultRowNumOfTestResultFile,String ActualResultRowNumOfTestResultFile,String TestRemarkRowNumOfTestResultFile) throws Throwable
	{
		if(existsElementchkFor1mts(OR.getProperty("reportTablocator")))
		{
			getObject("reportTablocator").click();
			System.out.println("2> Clicked to Report Tab");
			Thread.sleep(4000L);
			driver.navigate().refresh();
		}

		if(existsElementchkFor1mts(OR.getProperty("findReportTextboxLocator")))
		{				
			SearchReport(FirstReportNameInApplication);
		}
		Thread.sleep(2000L);
		if(existsElementchkFor1mts(OR.getProperty("reportCustomisebtn")))
		{
			editCustomButton();
		}
		Thread.sleep(2000L);

		if(existsElementchkFor1mts(OR.getProperty("customEditbtn")))
		{				
			UpdateReportPage(Payrolid,Frquency,MonthName);
			System.out.println("");
		}
		Thread.sleep(2000L);

		if(existsElementchkFor1mts(OR.getProperty("customRunReport")))
		{
			RunReport();
		}
		Thread.sleep(2000L);

		if(existsElementchkFor1mts(OR.getProperty("reportTableLocatorNI")))
		{
			processReport1(EmployerName,EmpName,Payrolid,Frquency,MonthName,ExcelInputSheet,FirstReportNameInApplication,TestResultExcelFilePath,worksheetNo,PayrollVeiw,TestReportworksheetNo,ExpectedResultRowNumOfTestResultFile,ActualResultRowNumOfTestResultFile,TestRemarkRowNumOfTestResultFile);
			System.out.println("7> Entered the values and processed the Test Remarks");
		}
	}


	
	public void processReport1(String EmployerName,String EmpName,String Payrolid,String Frquency,String MonthName,String ExcelInputSheet,String FirstReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,String PayrollVeiw,String TestReportworksheetNo,String ExpectedResultRowNumOfTestResultFile,String ActualResultRowNumOfTestResultFile,String TestRemarkRowNumOfTestResultFile)throws Throwable
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
						System.out.println("Employee name is :"+firstCellOfBody);
						
						String nationalInsurance = table.getTBody().getRow(rownum).getCell(1).getText();
						System.out.println("nationalInsurance is :"+nationalInsurance);

						String Title= table.getTBody().getRow(rownum).getCell(2).getText();
						System.out.println("Title is :"+Title);

						String Forename = table.getTBody().getRow(rownum).getCell(3).getText();
						System.out.println("Forename is :"+Forename);
											

						String Surname= table.getTBody().getRow(rownum).getCell(4).getText();
						System.out.println("Surname is :"+Surname);

						String addressLine1 = table.getTBody().getRow(rownum).getCell(5).getText();
						System.out.println("addressLine1 is :"+addressLine1);

						String addressLine2= table.getTBody().getRow(rownum).getCell(6).getText();
						System.out.println("addressLine2 is :"+addressLine2);

						String ukPostcode = table.getTBody().getRow(rownum).getCell(7).getText();
						System.out.println("ukPostcode is :"+ukPostcode);

						String dateOfBirth= table.getTBody().getRow(rownum).getCell(8).getText();
						System.out.println("dateOfBirth is :"+dateOfBirth);

						String currentGender = table.getTBody().getRow(rownum).getCell(9).getText();
						System.out.println("currentGender is :"+currentGender);

						String leavingDate= table.getTBody().getRow(rownum).getCell(10).getText();
						System.out.println("leavingDate is :"+leavingDate);

						String taxablePay = table.getTBody().getRow(rownum).getCell(11).getText();
						System.out.println("taxablePay is :"+taxablePay);

						String totaltax= table.getTBody().getRow(rownum).getCell(12).getText();
						System.out.println("totaltax is :"+totaltax);
						
						
						String bacsHashcode = table.getTBody().getRow(rownum).getCell(13).getText();
						System.out.println("bacsHashcode is :"+bacsHashcode);

						String PayFrequency= table.getTBody().getRow(rownum).getCell(14).getText();
						System.out.println("PayFrequency is :"+PayFrequency);

						String paymentDate = table.getTBody().getRow(rownum).getCell(15).getText();
						System.out.println("paymentDate is :"+paymentDate);

						String taxMonthNumber= table.getTBody().getRow(rownum).getCell(16).getText();
						System.out.println("taxMonthNumber is :"+taxMonthNumber);

						String numberOfEarningsperiodsCovered = table.getTBody().getRow(rownum).getCell(17).getText();
						System.out.println("numberOfEarningsperiodsCovered is :"+numberOfEarningsperiodsCovered);

						String numberOfNormalHoursWorked= table.getTBody().getRow(rownum).getCell(18).getText();
						System.out.println("numberOfNormalHoursWorked is :"+numberOfNormalHoursWorked);

						
						String taxCode= table.getTBody().getRow(rownum).getCell(19).getText();
						System.out.println("taxCode is :"+taxCode);

						String taxRegimen = table.getTBody().getRow(rownum).getCell(20).getText();
						System.out.println("taxBasis is :"+taxRegimen);

						
						String taxablePayInPeriod = table.getTBody().getRow(rownum).getCell(21).getText();
						System.out.println("taxablePayInPeriod is :"+taxablePayInPeriod);

						String payAfterStatutoryDeductions= table.getTBody().getRow(rownum).getCell(22).getText();
						System.out.println("payAfterStatutoryDeductions is :"+payAfterStatutoryDeductions);
						
					
						String taxDeductedORrefunded = table.getTBody().getRow(rownum).getCell(23).getText();
						System.out.println("taxDeductedORrefunded is :"+taxDeductedORrefunded);
						
						String latePayeReportingReason = table.getTBody().getRow(rownum).getCell(24).getText();
						System.out.println("taxDeductedORrefunded is :"+taxDeductedORrefunded);
										
					
						String niCategory= table.getTBody().getRow(rownum).getCell(25).getText();
						System.out.println("niCategory is :"+niCategory);

						String grossEarningsNICsInPeriod = table.getTBody().getRow(rownum).getCell(26).getText();
						System.out.println("grossEarningsNICsInPeriod is :"+grossEarningsNICsInPeriod);

						String grossEarningsNICsInYTD= table.getTBody().getRow(rownum).getCell(27).getText();
						System.out.println("grossEarningsNICsInYTD is :"+grossEarningsNICsInYTD);

						String earningsAtLowerEarningslimitYTD = table.getTBody().getRow(rownum).getCell(28).getText();
						System.out.println("earningsAtLowerEarningslimitYTD is :"+earningsAtLowerEarningslimitYTD);

						String earningsLELUpToIncludingPTYTD= table.getTBody().getRow(rownum).getCell(29).getText();
						System.out.println("earningsLELUpToIncludingPTYTD is :"+earningsLELUpToIncludingPTYTD);

						String earningsPTIncludingUELYTD = table.getTBody().getRow(rownum).getCell(30).getText();
						System.out.println("earningsPTIncludingUELYTD is :"+earningsPTIncludingUELYTD);

						String totalOfEmployerContributions= table.getTBody().getRow(rownum).getCell(31).getText();
						System.out.println("totalOfEmployerContributions is :"+totalOfEmployerContributions);

						String totalOfEmployerContributionsYTD= table.getTBody().getRow(rownum).getCell(32).getText();
						System.out.println("totalOfEmployerContributionsYTD is :"+totalOfEmployerContributionsYTD);

						String employeeContributionsPayable= table.getTBody().getRow(rownum).getCell(33).getText();
						System.out.println("employeeContributionsPayable is :"+employeeContributionsPayable);

						String employeeContributionsPayableYTD= table.getTBody().getRow(rownum).getCell(34).getText();
						System.out.println("employeeContributionsPayableYTD is :"+employeeContributionsPayableYTD);
						
						//call the functions which reads the excel sheet.
						ReadsExpectedData1(EmpName,nationalInsurance,Title,Forename,Surname,addressLine1,addressLine2,ukPostcode,dateOfBirth,currentGender,leavingDate,taxablePay,totaltax,bacsHashcode,PayFrequency,paymentDate,taxMonthNumber,numberOfEarningsperiodsCovered,numberOfNormalHoursWorked,taxCode,taxRegimen,taxablePayInPeriod,payAfterStatutoryDeductions,taxDeductedORrefunded,latePayeReportingReason,niCategory,grossEarningsNICsInPeriod,grossEarningsNICsInYTD,earningsAtLowerEarningslimitYTD,earningsLELUpToIncludingPTYTD,earningsPTIncludingUELYTD,totalOfEmployerContributions,totalOfEmployerContributionsYTD,employeeContributionsPayable,employeeContributionsPayableYTD,TestResultExcelFilePath,TestReportworksheetNo,ExpectedResultRowNumOfTestResultFile,ActualResultRowNumOfTestResultFile,TestRemarkRowNumOfTestResultFile);

						ReadsExpectedData11a(EmpName,nationalInsurance,Title,Forename,Surname,addressLine1,addressLine2,ukPostcode,dateOfBirth,currentGender,leavingDate,taxablePay,totaltax,bacsHashcode,PayFrequency,paymentDate,taxMonthNumber,numberOfEarningsperiodsCovered,numberOfNormalHoursWorked,taxCode,taxRegimen,taxablePayInPeriod,payAfterStatutoryDeductions,taxDeductedORrefunded,latePayeReportingReason,niCategory,grossEarningsNICsInPeriod,grossEarningsNICsInYTD,earningsAtLowerEarningslimitYTD,earningsLELUpToIncludingPTYTD,earningsPTIncludingUELYTD,totalOfEmployerContributions,totalOfEmployerContributionsYTD,employeeContributionsPayable,employeeContributionsPayableYTD,TestResultExcelFilePath,TestReportworksheetNo,ExpectedResultRowNumOfTestResultFile,ActualResultRowNumOfTestResultFile,TestRemarkRowNumOfTestResultFile);

						ReadsExpectedData11(EmpName,nationalInsurance,Title,Forename,Surname,addressLine1,addressLine2,ukPostcode,dateOfBirth,currentGender,leavingDate,taxablePay,totaltax,bacsHashcode,PayFrequency,paymentDate,taxMonthNumber,numberOfEarningsperiodsCovered,numberOfNormalHoursWorked,taxCode,taxRegimen,taxablePayInPeriod,payAfterStatutoryDeductions,taxDeductedORrefunded,latePayeReportingReason,niCategory,grossEarningsNICsInPeriod,grossEarningsNICsInYTD,earningsAtLowerEarningslimitYTD,earningsLELUpToIncludingPTYTD,earningsPTIncludingUELYTD,totalOfEmployerContributions,totalOfEmployerContributionsYTD,employeeContributionsPayable,employeeContributionsPayableYTD,TestResultExcelFilePath,TestReportworksheetNo,ExpectedResultRowNumOfTestResultFile,ActualResultRowNumOfTestResultFile,TestRemarkRowNumOfTestResultFile);
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



	public void ReadsExpectedData1(String EmpName,String nationalInsurance,String Title,String Forename,String Surname,String addressLine1,String addressLine2,String ukPostcode,String dateOfBirth,String currentGender,String leavingDate,String taxablePay,String totaltax,String bacsHashcode,String PayFrequency,String paymentDate,String taxMonthNumber,String numberOfEarningsperiodsCovered,String numberOfNormalHoursWorke,String taxCode,String taxRegimen,String taxablePayInPeriod,String payAfterStatutoryDeductions,String taxDeductedORrefunded,String latePayeReportingReason,String niCategory,String grossEarningsNICsInPeriod,String grossEarningsNICsInYTD,String earningsAtLowerEarningslimitYTD,String earningsLELUpToIncludingPTYTD,String earningsPTIncludingUELYTD,String totalOfEmployerContributions,String totalOfEmployerContributionsYTD,String employeeContributionsPayable,String employeeContributionsPayableYTD,String TestResultExcelFilePath,String TestReportworksheetNo,String ExpectedResultRowNumOfTestResultFile,String ActualResultRowNumOfTestResultFile,String TestRemarkRowNumOfTestResultFile) throws Throwable
	{
		double worksheetvalue = Double.parseDouble(TestReportworksheetNo);
		DecimalFormat df = new DecimalFormat("###.#");
		String worksheetNoWithoutDecimal= df.format(worksheetvalue);
		int TRwNo=Integer.parseInt(worksheetNoWithoutDecimal);
		System.out.println("The converted integer TestReportWorksheet value is  :"+TRwNo);

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
		int rowNum1 = ws.getLastRowNum()+1;
		System.out.println("i am in first method");

		double	expectdDataRowNo= Double.parseDouble(ExpectedResultRowNumOfTestResultFile);
		DecimalFormat expctdData = new DecimalFormat("###.#");
		String expctdRowNoWithoutDecimal= expctdData.format(expectdDataRowNo);
		int expctdRowIntValue=Integer.parseInt(expctdRowNoWithoutDecimal);
		System.out.println("The converted integer TestReportWorksheet value is  :"+expctdRowIntValue);


		for(int i =expctdRowIntValue; i< rowNum1; i++) // getting the expected data from expected result data row
		{
			Row row = ws.getRow(i);
			value1 = cellToString(row.getCell(1)); // and storing each cell value in each public variable 
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
			value12 = cellToString(row.getCell(12));
			value13 = cellToString(row.getCell(13));
			value14 = cellToString(row.getCell(14));
			value15 = cellToString(row.getCell(15));
			value16 = cellToString(row.getCell(16));
			value17 = cellToString(row.getCell(17));
			value18 = cellToString(row.getCell(18));
			value19 = cellToString(row.getCell(19));
			value20 = cellToString(row.getCell(20));
			value21 = cellToString(row.getCell(21));
			value22 = cellToString(row.getCell(22));
			value23 = cellToString(row.getCell(23));
			value24 = cellToString(row.getCell(24));
			value25 = cellToString(row.getCell(25));
			value26 = cellToString(row.getCell(26));
			value27 = cellToString(row.getCell(27));
			value28 = cellToString(row.getCell(28));
			value29 = cellToString(row.getCell(29));
			value30 = cellToString(row.getCell(30));
			value31 = cellToString(row.getCell(31));
			value32 = cellToString(row.getCell(32));
			value33 = cellToString(row.getCell(33));
			value34 = cellToString(row.getCell(34));
			value35 = cellToString(row.getCell(35));
			
			if(value1 != null && value1.equalsIgnoreCase(firstCellOfBody))
			{
				System.out.println("The employee name got matched");
				System.out.println("captured all the values and stored in the global variables");
				break;	
			}
		}
		System.out.println("stored all the values from the first method");
		wb.write(webdata);
		webdata.close();
		fis.close();
	}
	

	public void ReadsExpectedData11a(String EmpName,String nationalInsurance,String Title,String Forename,String Surname,String addressLine1,String addressLine2,String ukPostcode,String dateOfBirth,String currentGender,String leavingDate,String taxablePay,String totaltax,String bacsHashcode,String PayFrequency,String paymentDate,String taxMonthNumber,String numberOfEarningsperiodsCovered,String numberOfNormalHoursWorke,String taxCode,String taxRegimen,String taxablePayInPeriod,String payAfterStatutoryDeductions,String taxDeductedORrefunded,String latePayeReportingReason,String niCategory,String grossEarningsNICsInPeriod,String grossEarningsNICsInYTD,String earningsAtLowerEarningslimitYTD,String earningsLELUpToIncludingPTYTD,String earningsPTIncludingUELYTD,String totalOfEmployerContributions,String totalOfEmployerContributionsYTD,String employeeContributionsPayable,String employeeContributionsPayableYTD,String TestResultExcelFilePath,String TestReportworksheetNo,String ExpectedResultRowNumOfTestResultFile,String ActualResultRowNumOfTestResultFile,String TestRemarkRowNumOfTestResultFile) throws Throwable
	{
		double worksheetvalue = Double.parseDouble(TestReportworksheetNo);
		DecimalFormat df = new DecimalFormat("###.#");
		String worksheetNoWithoutDecimal= df.format(worksheetvalue);
		int TRwNo=Integer.parseInt(worksheetNoWithoutDecimal);
		System.out.println("The converted integer TestReportWorksheet value is  :"+TRwNo);

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

		double actualDataRowNo= Double.parseDouble(ActualResultRowNumOfTestResultFile);
		DecimalFormat actualData = new DecimalFormat("###.#");
		String actualRowNoWithoutDecimal= actualData.format(actualDataRowNo);
		int actualRowIntValue=Integer.parseInt(actualRowNoWithoutDecimal);
		System.out.println("The converted integer actualRowdataNo value is  :"+actualRowIntValue);


		int rowNum = ws.getLastRowNum()+1;
		for(int i =actualRowIntValue; i< rowNum; i++) // getting the expected data from expected result data row
		{
			Row row = ws.getRow(i);	

			if(value1 != null && value1.equalsIgnoreCase(firstCellOfBody))
			{
				row.createCell(2).setCellValue(nationalInsurance);
				row.createCell(3).setCellValue(Title);
				row.createCell(4).setCellValue(Forename);
				row.createCell(5).setCellValue(Surname);
				row.createCell(6).setCellValue(addressLine1);
				row.createCell(7).setCellValue(addressLine2);

				row.createCell(8).setCellValue(ukPostcode);
				row.createCell(9).setCellValue(dateOfBirth);
				row.createCell(10).setCellValue(currentGender);

				
				row.createCell(11).setCellValue(leavingDate);
				row.createCell(12).setCellValue(taxablePay);
				
				row.createCell(13).setCellValue(totaltax);
				row.createCell(14).setCellValue(bacsHashcode);

				row.createCell(15).setCellValue(PayFrequency);
				row.createCell(16).setCellValue(paymentDate);
				row.createCell(17).setCellValue(taxMonthNumber);
				row.createCell(18).setCellValue(numberOfEarningsperiodsCovered);
				row.createCell(19).setCellValue(numberOfNormalHoursWorke);
				row.createCell(20).setCellValue(taxCode);
				
				row.createCell(21).setCellValue(taxRegimen);

				row.createCell(22).setCellValue(taxablePayInPeriod);
				row.createCell(23).setCellValue(payAfterStatutoryDeductions);
				row.createCell(24).setCellValue(taxDeductedORrefunded);
				
				row.createCell(25).setCellValue(latePayeReportingReason);

				row.createCell(26).setCellValue(niCategory);
				row.createCell(27).setCellValue(grossEarningsNICsInPeriod);

				row.createCell(28).setCellValue(grossEarningsNICsInYTD);
				row.createCell(29).setCellValue(earningsAtLowerEarningslimitYTD);
				row.createCell(30).setCellValue(earningsLELUpToIncludingPTYTD);

				row.createCell(31).setCellValue(earningsPTIncludingUELYTD);
				row.createCell(32).setCellValue(totalOfEmployerContributions);
				row.createCell(33).setCellValue(totalOfEmployerContributionsYTD);

				row.createCell(34).setCellValue(employeeContributionsPayable);
				row.createCell(35).setCellValue(employeeContributionsPayableYTD);
				System.out.println("pasted actual Result data into the test result excel file");
				break;
			}
		}
		wb.write(webdata);
		webdata.close();
		fis.close();
	}
	
	
	public void ReadsExpectedData11(String EmpName,String nationalInsurance,String Title,String Forename,String Surname,String addressLine1,String addressLine2,String ukPostcode,String dateOfBirth,String currentGender,String leavingDate,String taxablePay,String totaltax,String bacsHashcode,String PayFrequency,String paymentDate,String taxMonthNumber,String numberOfEarningsperiodsCovered,String numberOfNormalHoursWorke,String taxCode,String taxRegimen,String taxablePayInPeriod,String payAfterStatutoryDeductions,String taxDeductedORrefunded,String latePayeReportingReason,String niCategory,String grossEarningsNICsInPeriod,String grossEarningsNICsInYTD,String earningsAtLowerEarningslimitYTD,String earningsLELUpToIncludingPTYTD,String earningsPTIncludingUELYTD,String totalOfEmployerContributions,String totalOfEmployerContributionsYTD,String employeeContributionsPayable,String employeeContributionsPayableYTD,String TestResultExcelFilePath,String TestReportworksheetNo,String ExpectedResultRowNumOfTestResultFile,String ActualResultRowNumOfTestResultFile,String TestRemarkRowNumOfTestResultFile) throws Throwable
	{
	System.out.println("This is ReadExpected data1");
	double worksheetvalue = Double.parseDouble(TestReportworksheetNo);
	DecimalFormat df = new DecimalFormat("###.#");
	String worksheetNoWithoutDecimal= df.format(worksheetvalue);
	int TRwNo=Integer.parseInt(worksheetNoWithoutDecimal);
	System.out.println("The converted integer TestReportWorksheet value is  :"+TRwNo);

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

	double testRemarkDataRowNo= Double.parseDouble(TestRemarkRowNumOfTestResultFile);
	DecimalFormat testRmrkData= new DecimalFormat("###.#");
	String testRmrkRowNoWithoutDecimal= testRmrkData.format(testRemarkDataRowNo);
	int testRmrkRowIntValue=Integer.parseInt(testRmrkRowNoWithoutDecimal);
	System.out.println("The converted integer TestRemarkRowNo value is  :"+testRmrkRowIntValue);


	int rowNum = ws.getLastRowNum()+1;
	for(int j =testRmrkRowIntValue; j< rowNum; j++)
	{
		Row row = ws.getRow(j);
		System.out.println("the value stored in value1 is :"+value1);
		if(value1 != null && value1.equalsIgnoreCase(firstCellOfBody))
		{
			System.out.println("the value stored in value2 is :"+value2 +"needs to be compared");
			if(value2 != null && value2.equalsIgnoreCase(nationalInsurance))
			{
				Cell cell1 = row.createCell(2);			
				row.createCell(2).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row.createCell(2);
				row.createCell(2).setCellValue("FALSE");
				cell1.setCellStyle(styleFalse);
			}
			if(value3 != null && value3.equalsIgnoreCase(Title))
			{
				Cell cell1 = row.createCell(3);			
				row.createCell(3).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row.createCell(3);
				row.createCell(3).setCellValue("FALSE");
				cell1.setCellStyle(styleFalse);
			}

			if(value4 != null && value4.equalsIgnoreCase(Forename))
			{
				Cell cell1 = row.createCell(4);			
				row.createCell(4).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row.createCell(4);
				row.createCell(4).setCellValue("FALSE");
				cell1.setCellStyle(styleFalse);
			}
					

			if(value5 != null && value5.equalsIgnoreCase(Surname))
			{
				Cell cell1 = row.createCell(5);			
				row.createCell(5).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row.createCell(5);
				row.createCell(5).setCellValue("FALSE");
				cell1.setCellStyle(styleFalse);
			}

			if(value6 != null && value6.equalsIgnoreCase(addressLine1))
			{
				Cell cell1 = row.createCell(6);			
				row.createCell(6).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row.createCell(6);
				row.createCell(6).setCellValue("FALSE");
				cell1.setCellStyle(styleFalse);
			}

			if(value7 != null && value7.equalsIgnoreCase(addressLine2))
			{
				Cell cell1 = row.createCell(7);			
				row.createCell(7).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row.createCell(7);
				row.createCell(7).setCellValue("FALSE");
				cell1.setCellStyle(styleFalse);
			}

			if(value8 != null && value8.equalsIgnoreCase(ukPostcode))
			{
				Cell cell1 = row.createCell(8);			
				row.createCell(8).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row.createCell(8);
				row.createCell(8).setCellValue("FALSE");
				cell1.setCellStyle(styleFalse);
			}

			if(value9 != null && value9.equalsIgnoreCase(dateOfBirth))
			{
				Cell cell1 = row.createCell(9);			
				row.createCell(9).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row.createCell(9);
				row.createCell(9).setCellValue("FALSE");
				cell1.setCellStyle(styleFalse);
			}

			if(value10 != null && value10.equalsIgnoreCase(currentGender))
			{
				Cell cell1 = row.createCell(10);			
				row.createCell(10).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row.createCell(10);
				row.createCell(10).setCellValue("FALSE");
				cell1.setCellStyle(styleFalse);
			}
			

			if(value11 != null && value11.equalsIgnoreCase(leavingDate))
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

			
			if(value12 != null && value12.equalsIgnoreCase(taxablePay))
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

			if(value13 != null && value13.equalsIgnoreCase(totaltax))
			{
				Cell cell1 = row.createCell(13);			
				row.createCell(13).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row.createCell(13);
				row.createCell(13).setCellValue("FALSE");
				cell1.setCellStyle(styleFalse);
			}
					
			if(value14 != null && value14.equalsIgnoreCase(bacsHashcode))
			{
				Cell cell1 = row.createCell(14);			
				row.createCell(14).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row.createCell(14);
				row.createCell(14).setCellValue("FALSE");
				cell1.setCellStyle(styleFalse);
			}

			if(value15 != null && value15.equalsIgnoreCase(PayFrequency))
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

			if(value16 != null && value16.equalsIgnoreCase(paymentDate))
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
			

			if(value17 != null && value17.equalsIgnoreCase(taxMonthNumber))
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
					

			if(value18 != null && value18.equalsIgnoreCase(numberOfEarningsperiodsCovered))
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
					

			if(value19 != null && value19.equalsIgnoreCase(numberOfNormalHoursWorke))
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
					

			if(value20 != null && value20.equalsIgnoreCase(taxCode))
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
			
			if(value21 != null && value21.equalsIgnoreCase(taxRegimen))
			{
				Cell cell1 = row.createCell(21);			
				row.createCell(21).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row.createCell(21);
				row.createCell(21).setCellValue("FALSE");
				cell1.setCellStyle(styleFalse);
			}
			
			

			if(value22 != null && value22.equalsIgnoreCase(taxablePayInPeriod))
			{
				Cell cell1 = row.createCell(22);			
				row.createCell(22).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row.createCell(22);
				row.createCell(22).setCellValue("FALSE");
				cell1.setCellStyle(styleFalse);
			}
						

			if(value23 != null && value23.equalsIgnoreCase(payAfterStatutoryDeductions))
			{
				Cell cell1 = row.createCell(23);			
				row.createCell(23).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row.createCell(23);
				row.createCell(23).setCellValue("FALSE");
				cell1.setCellStyle(styleFalse);
			}
			
			if(value24 != null && value24.equalsIgnoreCase(taxDeductedORrefunded))
			{
				Cell cell1 = row.createCell(24);			
				row.createCell(24).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row.createCell(24);
				row.createCell(24).setCellValue("FALSE");
				cell1.setCellStyle(styleFalse);
			}
			
			if(value25 != null && value25.equalsIgnoreCase(latePayeReportingReason))
			{
				Cell cell1 = row.createCell(25);			
				row.createCell(25).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row.createCell(25);
				row.createCell(25).setCellValue("FALSE");
				cell1.setCellStyle(styleFalse);
			}
			

			if(value26 != null && value26.equalsIgnoreCase(niCategory))
			{
				Cell cell1 = row.createCell(26);			
				row.createCell(26).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row.createCell(26);
				row.createCell(26).setCellValue("FALSE");
				cell1.setCellStyle(styleFalse);
			}

			if(value27 != null && value27.equalsIgnoreCase(grossEarningsNICsInPeriod))
			{
				Cell cell1 = row.createCell(27);			
				row.createCell(27).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row.createCell(27);
				row.createCell(27).setCellValue("FALSE");
				cell1.setCellStyle(styleFalse);
			}
			
			
			if(value28 != null && value28.equalsIgnoreCase(grossEarningsNICsInYTD))
			{
				Cell cell1 = row.createCell(28);			
				row.createCell(28).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row.createCell(28);
				row.createCell(28).setCellValue("FALSE");
				cell1.setCellStyle(styleFalse);
			}

			if(value29 != null && value29.equalsIgnoreCase(earningsAtLowerEarningslimitYTD))
			{
				Cell cell1 = row.createCell(29);			
				row.createCell(29).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row.createCell(29);
				row.createCell(29).setCellValue("FALSE");
				cell1.setCellStyle(styleFalse);
			}

			if(value30 != null && value30.equalsIgnoreCase(earningsLELUpToIncludingPTYTD))
			{
				Cell cell1 = row.createCell(30);			
				row.createCell(30).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row.createCell(30);
				row.createCell(30).setCellValue("FALSE");
				cell1.setCellStyle(styleFalse);
			}

			if(value31 != null && value31.equalsIgnoreCase(earningsPTIncludingUELYTD))
			{
				Cell cell1 = row.createCell(31);			
				row.createCell(31).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row.createCell(31);
				row.createCell(31).setCellValue("FALSE");
				cell1.setCellStyle(styleFalse);
			}

			if(value32 != null && value32.equalsIgnoreCase(totalOfEmployerContributions))
			{
				Cell cell1 = row.createCell(32);			
				row.createCell(32).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row.createCell(32);
				row.createCell(32).setCellValue("FALSE");
				cell1.setCellStyle(styleFalse);
			}

			if(value33 != null && value33.equalsIgnoreCase(totalOfEmployerContributionsYTD))
			{
				Cell cell1 = row.createCell(33);			
				row.createCell(33).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row.createCell(33);
				row.createCell(33).setCellValue("FALSE");
				cell1.setCellStyle(styleFalse);
			}

			if(value34 != null && value34.equalsIgnoreCase(employeeContributionsPayable))
			{
				Cell cell1 = row.createCell(34);			
				row.createCell(34).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row.createCell(34);
				row.createCell(34).setCellValue("FALSE");
				cell1.setCellStyle(styleFalse);
			}

			if(value35 != null && value35.equalsIgnoreCase(employeeContributionsPayableYTD))
			{
				Cell cell1 = row.createCell(35);			
				row.createCell(35).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row.createCell(35);
				row.createCell(35).setCellValue("FALSE");
				cell1.setCellStyle(styleFalse);
			}
			break;
		}
	}	
	System.out.println("Entered the test remarks into the excel sheet successfully");
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
		case 5: result = cell.getCellType();
		default: 
			throw new RuntimeException("there are no othe values");
		}
		return result.toString();
	}

	


	@DataProvider
	public Object[][] getData() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		return Test_Util.getData(Payroll_RecognitionScenarioFour_SuiteXls,"TestAprilReports");
	}



	@AfterMethod
	public void ReportDataSetResult() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		if(Skip)
		{
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarioFour_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
		}
		else if(Fail)
		{
			IsTestPass = false;
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarioFour_SuiteXls, this.getClass().getSimpleName(), count+2, "Fail");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarioFour_SuiteXls, this.getClass().getSimpleName(), count+2, "Pass");
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
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarioFour_SuiteXls, "first", Test_Util.GetRowNum(Payroll_RecognitionScenarioFour_SuiteXls, this.getClass().getSimpleName()),"Pass");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarioFour_SuiteXls, "first", Test_Util.GetRowNum(Payroll_RecognitionScenarioFour_SuiteXls, this.getClass().getSimpleName()),"Fail");
		}
		System.out.println("closing the browser");
		closeBrowser();
	}

}

