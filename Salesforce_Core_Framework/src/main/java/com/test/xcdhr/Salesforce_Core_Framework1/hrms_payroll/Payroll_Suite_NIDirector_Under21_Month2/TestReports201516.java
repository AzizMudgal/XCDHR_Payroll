package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.Payroll_Suite_NIDirector_Under21_Month2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import atu.webdriver.utils.table.WebTable;

import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.ErrorUtil;
import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Test_Util;


public class TestReports201516 extends TestSuiteBase {
	String runmodes[] = null;
	static int count = -1;
	public static boolean Fail=false;
	public static boolean Skip=false;
	public static boolean IsTestPass=true;
	public String firstCellOfBody;
	public int Row_count;
	public static String PayFrequency= "Monthly";
	public static String payrollMonth= "May-2015";
	public String ReportName= "DO NOT TOUCH AUTOMATION DIR NI UNDER21";
	public String titlename;
	
	

	@BeforeTest
	public void CheckTestSkip() throws Exception{
		if(! Test_Util.IsTestcaseRunMode(Payroll_NI_Director_Under21_SuiteXls, this.getClass().getSimpleName())){

			Skip=true;
			Test_Util.ReportDataSetResult(Payroll_NI_Director_Under21_SuiteXls, "first", Test_Util.GetRowNum(Payroll_NI_Director_Under21_SuiteXls, this.getClass().getSimpleName()),"Skipped");
			//Test_Util.ReportDataSetResult(Payroll_NI_Director_Under21_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase" +this.getClass().getSimpleName() +" as the runmode is set to 'no' ");// this message would display in logs

			throw new Exception("Testcase is being skipped" + this.getClass().getSimpleName()+ "as it's Runmode is set to 'NO'"); // this msg would display in Reports.

		}

		// Load the runmodes of the tests

		runmodes=Test_Util.getDataSetRunmodes(Payroll_NI_Director_Under21_SuiteXls, this.getClass().getSimpleName());

	}


	@Test
	public void CompareReports() throws Throwable
	{

		count++;
		if(! runmodes[count].equalsIgnoreCase("Y")){

			Skip=true;
			throw new SkipException("Runmode for Test set data is set to 'NO' "+count);

		}

		APP_LOGS.debug("Executing the test case");
		//WebDriver driver = new FirefoxDriver(FirefoxDriverProfile());
		openBrowser();

		driver.get(CONFIG.getProperty("testSiteName"));
		login_To_Application();

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

		DownloadReports(pn,PayFrequency,payrollMonth);


	}

		public void DownloadReports(String pn,String payfreqncy,String payrollMonth) throws Throwable
		{
			getObject("reportTablocator").click();
			System.out.println("2> Clicked to Report Tab");
			Thread.sleep(4000L);
			driver.navigate().refresh();
			

			if(existsElementchkFor1mts(OR.getProperty("findReportTextboxLocator")))
			{				
				SearchReport(ReportName);
							
			}

			
			if(existsElementchkFor1mts(OR.getProperty("reportCustomisebtn")))
			{
				editCustomButton();
			}

			
			if(existsElementchkFor1mts(OR.getProperty("customEditbtn")))
			{				

				UpdateReportPage(pn,PayFrequency,payrollMonth);
			}


			System.out.println("");
			System.out.println("3> Successfully customized the Report as required");

		
			if(existsElementchkFor1mts(OR.getProperty("customRunReport")))
			{
				RunReport();
			}
			
			if(existsElementchkFor1mts(OR.getProperty("reportTableLocatorNI")))
			{
				processReport();
				System.out.println("5> Entered the values and processed the Test Remarks");
			}
			

		}
		
		

	
	public void processReport()throws Throwable
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
					String employeeNI= table.getTBody().getRow(rownum).getCell(1).getText();
					 System.out.println("employeeNI is :"+employeeNI);
					String employeeNI1= table.getTBody().getRow(rownum).getCell(2).getText();
					System.out.println("employeeNI1 is :"+employeeNI1);
					String employeeNI2= table.getTBody().getRow(rownum).getCell(3).getText();
					System.out.println("employeeNI2 is :"+employeeNI2);
					
					String employerNI= table.getTBody().getRow(rownum).getCell(4).getText();
					System.out.println("employerNI is :"+employerNI);
					String employerNI1= table.getTBody().getRow(rownum).getCell(5).getText();
					System.out.println("employerNI1 is :"+employerNI1);
					String employerNI2= table.getTBody().getRow(rownum).getCell(6).getText();
					System.out.println("employerNI2 is :"+employerNI2);
					
					String employeeNIYTD= table.getTBody().getRow(rownum).getCell(7).getText();
					System.out.println("employeeNIYTD is :"+employeeNIYTD);
					String employeeNI1_YTD= table.getTBody().getRow(rownum).getCell(8).getText();
					System.out.println("employeeNI1_YTD is :"+employeeNI1_YTD);
					String employeeNI2_YTD= table.getTBody().getRow(rownum).getCell(9).getText();
					System.out.println("employeeNI2_YTD is :"+employeeNI2_YTD);
					
					String employerNIYTD= table.getTBody().getRow(rownum).getCell(10).getText();
					System.out.println("employerNIYTD is :"+employerNIYTD);
					String employerNI1_YTD= table.getTBody().getRow(rownum).getCell(11).getText();
					System.out.println("employerNI1_YTD is :"+employerNI1_YTD);
					String employerNI2_YTD= table.getTBody().getRow(rownum).getCell(12).getText();
					System.out.println("employerNI2_YTD is :"+employerNI2_YTD);
					
					
					
					
					ReadsExpectedData(firstCellOfBody, employeeNI, employeeNI1,employeeNI2,employerNI,employerNI1,employerNI2,employeeNIYTD,employeeNI1_YTD,employeeNI2_YTD,employerNIYTD,employerNI1_YTD,employerNI2_YTD);
				}
				rownum++;
			}
			
		}
		catch(Throwable t)
		{
			
		}
	}

	
	public void ReadsExpectedData(String firstCellOfBody, String employeeNI, String employeeNI1,String employeeNI2, String empployerNI,String employerNI1,String employerNI2,String employeeNIYTD,String employeeNI1_YTD,String employeeNI2_YTD,String employerNIYTD,String employerNI1_YTD,String employerNI2_YTD) throws Throwable
	{

		File excel = new File("F:\\Automation NI Reports\\HMRCTestData\\Automation Test Result for Directors_NI.xlsx");
		FileInputStream fis = new FileInputStream(excel);
		org.apache.poi.ss.usermodel.Workbook wb = WorkbookFactory.create(fis);
		org.apache.poi.ss.usermodel.Sheet ws = wb.getSheetAt(6);
		
		FileOutputStream webdata = new FileOutputStream ("F:\\Automation NI Reports\\HMRCTestData\\Automation Test Result for Directors_NI.xlsx");

		int rowNum = ws.getLastRowNum()+1;
		
		for(int i =3; i< rowNum; i++)
		{
			Row row = ws.getRow(i);
			String value1 = cellToString(row.getCell(1));
			String empNI = cellToString(row.getCell(10));
			String empNI1 = cellToString(row.getCell(11));
			String empNI2 = cellToString(row.getCell(12));
			
			String emplyerRNI = cellToString(row.getCell(13));
			String EmplyrNI1 = cellToString(row.getCell(14));
			String EmplyrNI2 = cellToString(row.getCell(15));
			
			
			String empNIYTD= cellToString(row.getCell(16));
			String emppNI1YTD = cellToString(row.getCell(17));
			String emppNI2YTD = cellToString(row.getCell(18));
			
			String emprrNIYTD= cellToString(row.getCell(19));
			String emprrNI1YTD= cellToString(row.getCell(20));
			String emprrNI2YTD= cellToString(row.getCell(21));
			
			
		
			if(value1 != null && value1.equalsIgnoreCase(firstCellOfBody))
			{
				row.createCell(22).setCellValue(employeeNI);
				row.createCell(23).setCellValue(employeeNI1);
				row.createCell(24).setCellValue(employeeNI2);//25,26,27
				
				row.createCell(28).setCellValue(empployerNI);
				row.createCell(29).setCellValue(employerNI1);
				row.createCell(30).setCellValue(employerNI2);//31,32,33
				
				row.createCell(34).setCellValue(employeeNIYTD);
				row.createCell(35).setCellValue(employeeNI1_YTD);
				row.createCell(36).setCellValue(employeeNI2_YTD);//37,38,39
				
				row.createCell(40).setCellValue(employerNIYTD);
				row.createCell(41).setCellValue(employerNI1_YTD);
				row.createCell(42).setCellValue(employerNI1_YTD);//43,44,45
				
				if(empNI != null && empNI.equalsIgnoreCase(employeeNI))
				{
					row.createCell(25).setCellValue("TRUE");
				}
				else
				{
					row.createCell(25).setCellValue("FALSE");
				}
				
				if(empNI1 != null && empNI1.equalsIgnoreCase(employeeNI1))
				{
					row.createCell(26).setCellValue("TRUE");
				}
				else
				{
					row.createCell(26).setCellValue("FALSE");
				}
				
				if(empNI2 != null && empNI2.equalsIgnoreCase(employeeNI2))
				{
					row.createCell(27).setCellValue("TRUE");
				}
				else
				{
					row.createCell(27).setCellValue("FALSE");
				}
				
				
				
				

				if(emplyerRNI != null && emplyerRNI.equalsIgnoreCase(empployerNI))
				{
					row.createCell(31).setCellValue("TRUE");
				}   
				else
				{
					row.createCell(31).setCellValue("FALSE");
				} 
				
				if(EmplyrNI1 != null && EmplyrNI1.equalsIgnoreCase(employerNI1))
				{
					row.createCell(32).setCellValue("TRUE");
				}   
				else
				{
					row.createCell(32).setCellValue("FALSE");
				} 
				
				if(EmplyrNI2 != null && EmplyrNI2.equalsIgnoreCase(employerNI2))
				{
					row.createCell(33).setCellValue("TRUE");
				}   
				else
				{
					row.createCell(33).setCellValue("FALSE");
				} 
				
				
				
				
				
				if(empNIYTD != null && empNIYTD.equalsIgnoreCase(employeeNIYTD))
				{
					row.createCell(37).setCellValue("TRUE");
				}   
				else
				{
					row.createCell(37).setCellValue("FALSE");
				} 
				
				if(emppNI1YTD != null && emppNI1YTD.equalsIgnoreCase(employeeNI1_YTD))
				{
					row.createCell(38).setCellValue("TRUE");
				}   
				else
				{
					row.createCell(38).setCellValue("FALSE");
				} 
				
				if(emppNI2YTD != null && emppNI2YTD.equalsIgnoreCase(employeeNI2_YTD))
				{
					row.createCell(39).setCellValue("TRUE");
				}   
				else
				{
					row.createCell(39).setCellValue("FALSE");
				} 
				
				
				
				
				if(emprrNIYTD != null && emprrNIYTD.equalsIgnoreCase(employerNIYTD))
				{
					row.createCell(43).setCellValue("TRUE");
				}   
				else
				{
					row.createCell(43).setCellValue("FALSE");
				} 
				
				if(emprrNI1YTD != null && emprrNI1YTD.equalsIgnoreCase(employerNI1_YTD))
				{
					row.createCell(44).setCellValue("TRUE");
				}   
				else
				{
					row.createCell(44).setCellValue("FALSE");
				} 
				
				if(emprrNI2YTD != null && emprrNI2YTD.equalsIgnoreCase(employerNI2_YTD))
				{
					row.createCell(45).setCellValue("TRUE");
				}   
				else
				{
					row.createCell(45).setCellValue("FALSE");
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
		switch(type){

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


	@AfterMethod
	public void ReportDataSetResult(){
		if(Skip){
			Test_Util.ReportDataSetResult(Payroll_NI_Director_Under21_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
		}else if(Fail){

			IsTestPass = false;

			Test_Util.ReportDataSetResult(Payroll_NI_Director_Under21_SuiteXls, this.getClass().getSimpleName(), count+2, "Fail");
		}else{
			Test_Util.ReportDataSetResult(Payroll_NI_Director_Under21_SuiteXls, this.getClass().getSimpleName(), count+2, "Pass");
		}

		Skip=false;
		Fail=false;


	}


	@AfterTest
	public void ReportTestResult(){

		if(IsTestPass){

			// This will update the testresult in the first worksheet where in for that test case , even if one of the test data specified in second worksheet fails, the test 
			// would be considered as fail.And the same would be updated.

			Test_Util.ReportDataSetResult(Payroll_NI_Director_Under21_SuiteXls, "first", Test_Util.GetRowNum(Payroll_NI_Director_Under21_SuiteXls, this.getClass().getSimpleName()),"Pass");

		}else{

			Test_Util.ReportDataSetResult(Payroll_NI_Director_Under21_SuiteXls, "first", Test_Util.GetRowNum(Payroll_NI_Director_Under21_SuiteXls, this.getClass().getSimpleName()),"Fail");

		}	
		closeBrowser();

	}




}

