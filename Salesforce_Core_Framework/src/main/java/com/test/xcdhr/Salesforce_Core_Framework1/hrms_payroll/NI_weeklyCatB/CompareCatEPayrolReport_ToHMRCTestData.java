package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.NI_weeklyCatB;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Test_Util;
public class CompareCatEPayrolReport_ToHMRCTestData extends TestSuiteBase
{
	String runmodes[] = null;
	static int count = -1;
	public static boolean Fail=false;
	public static boolean Skip=false;
	public static boolean IsTestPass=true;
	public String divId;

	@BeforeTest
	public void CheckTestSkip() throws Exception{
		if(! Test_Util.IsTestcaseRunMode(Payroll_CatE_SuiteXls, this.getClass().getSimpleName())){

			Skip=true;
			Test_Util.ReportDataSetResult(Payroll_CatE_SuiteXls, "first", Test_Util.GetRowNum(Payroll_CatE_SuiteXls, this.getClass().getSimpleName()),"Skipped");
			//Test_Util.ReportDataSetResult(Payroll_CatE_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase" +this.getClass().getSimpleName() +" as the runmode is set to 'no' ");// this message would display in logs

			throw new Exception("Testcase is being skipped" + this.getClass().getSimpleName()+ "as it's Runmode is set to 'NO'"); // this msg would display in Reports.

		}

		// Load the runmodes of the tests

		runmodes=Test_Util.getDataSetRunmodes(Payroll_CatE_SuiteXls, this.getClass().getSimpleName());

	}

	
	static XSSFRow row;
	@Test
	public static void CompareReports() throws IOException 
	{

		/*************************************************/

		// Sheet xlsxSheet = xlsxWb.createSheet("F:\\Automation XCD\\Excel Compare software tool\\ComparisionResult\\report1438765921932.xls");

		FileInputStream fis = new FileInputStream(
		new File("F:\\Automation NI Reports\\NICatEReport\\source.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet spreadsheet = workbook.getSheetAt(0);
		Iterator < Row > rowIterator = spreadsheet.iterator();

		//Map<String, Object[]> dataMap = new HashMap<String, Object[]>();
		List<Object[]> dataMap = new ArrayList<Object[]>();
		while (rowIterator.hasNext()) 
		{
			row = (XSSFRow) rowIterator.next();
			Iterator < Cell > cellIterator = row.cellIterator();

			Object[] objArray = new Object[5];
			int i = 0;
			while ( cellIterator.hasNext()) 
			{
				Cell cell = cellIterator.next();
				String dataStr = null;
				switch (cell.getCellType()) 
				{
				case Cell.CELL_TYPE_NUMERIC:
					dataStr = Double.toString(cell.getNumericCellValue());
					break;
				case Cell.CELL_TYPE_STRING:
					dataStr = cell.getStringCellValue();
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					dataStr = Boolean.toString(cell.getBooleanCellValue());
					break;
				case Cell.CELL_TYPE_FORMULA:
					dataStr = Integer.toString( new Integer(cell.getCellFormula()));
				}
				objArray[i] = dataStr;
				i++;
			}
			dataMap.add(objArray);
		}
		fis.close();
		FileInputStream fis1 = null;
		XSSFWorkbook workbook1 = null;
		XSSFSheet spreadsheet1 = null;
		try
		{
			fis1 = new FileInputStream(
					new File("F:\\Automation NI Reports\\HMRCTestData\\AUTOMATION Test Result FOR NI Calculations.xlsx"));
			
			workbook1 = new XSSFWorkbook(fis1);
			spreadsheet1 = workbook1.getSheetAt(4);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}

		// Set to Iterate and add rows into XLS file 
		//Set<String> newRows = dataMap.keySet(); 
		// get the last row number to append new data 
		//int rownum = 1;
		int rownum = spreadsheet1.getLastRowNum() + 3;
		System.out.println(rownum);
		for (Object[] objArr : dataMap) 
		{ 
			// Creating a new Row in existing XLSX sheet 
			Row row = spreadsheet1.createRow(rownum++);
			//System.out.println("row : "+row);
			//Object [] objArr = dataMap.get(key); 
			int cellnum = 0; 
			for (Object obj : objArr)
			{ 
				Cell cell = row.createCell(cellnum); 
				if (obj instanceof String) 
				{ 
					cell.setCellValue((String) obj); 
				} 
				else if (obj instanceof Boolean) 
				{ 
					cell.setCellValue((Boolean) obj); 
				} 
				else if (obj instanceof Date) 
				{ 
					cell.setCellValue((Date) obj); 
				} 
				else if (obj instanceof Double) 
				{ 
					cell.setCellValue((Double) obj); 
				} 
				cellnum++;
			}
		} 
		// open an OutputStream to save written data into XLSX file //AUTOMATIONFORNICalculations1516
		File myFile = new File("F:\\Automation NI Reports\\HMRCTestData\\AUTOMATION Test Result FOR NI Calculations.xlsx");
		FileOutputStream os = new FileOutputStream(myFile); 
		workbook1.write(os);
		os.close();
		fis1.close();
		System.out.println("Sucess");
	}


	@AfterMethod
	public void ReportDataSetResult(){
		if(Skip){
			Test_Util.ReportDataSetResult(Payroll_CatE_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
		}else if(Fail){

			IsTestPass = false;

			Test_Util.ReportDataSetResult(Payroll_CatE_SuiteXls, this.getClass().getSimpleName(), count+2, "Fail");
		}else{
			Test_Util.ReportDataSetResult(Payroll_CatE_SuiteXls, this.getClass().getSimpleName(), count+2, "Pass");
		}

		Skip=false;
		Fail=false;


	}


	@AfterTest
	public void ReportTestResult(){

		if(IsTestPass){

			// This will update the testresult in the first worksheet where in for that test case , even if one of the test data specified in second worksheet fails, the test 
			// would be considered as fail.And the same would be updated.

			Test_Util.ReportDataSetResult(Payroll_CatE_SuiteXls, "first", Test_Util.GetRowNum(Payroll_CatE_SuiteXls, this.getClass().getSimpleName()),"Pass");

		}else{

			Test_Util.ReportDataSetResult(Payroll_CatE_SuiteXls, "first", Test_Util.GetRowNum(Payroll_CatE_SuiteXls, this.getClass().getSimpleName()),"Fail");

		}	


	}





}
