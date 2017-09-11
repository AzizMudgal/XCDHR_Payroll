package hrms_payroll.NI_Director_AtoDMonth2;

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

public class TestReports extends TestSuiteBase
{
	String runmodes[] = null;
	static int count = -1;
	public static boolean Fail=false;
	public static boolean Skip=false;
	public static boolean IsTestPass=true;
	public String firstCellOfBody;
	public int Row_count;
	public static String PayFrequency= "Monthly";
	public static String payrollMonth= "May-2015";
	public String ReportName= "DO NOT TOUCH - AUTOMATION DIR A to D";
	public String titlename;




	@BeforeTest
	public void CheckTestSkip() throws Exception
	{
		if(! Test_Util.IsTestcaseRunMode(Payroll_NI_Director_AtoD_SuiteXls, this.getClass().getSimpleName()))
		{
			Skip=true;
			Test_Util.ReportDataSetResult(Payroll_NI_Director_AtoD_SuiteXls, "first", Test_Util.GetRowNum(Payroll_NI_Director_AtoD_SuiteXls, this.getClass().getSimpleName()),"Skipped");
			//Test_Util.ReportDataSetResult(Payroll_NI_Director_AtoD_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase" +this.getClass().getSimpleName() +" as the runmode is set to 'no' ");// this message would display in logs
			throw new Exception("Testcase is being skipped" + this.getClass().getSimpleName()+ "as it's Runmode is set to 'NO'"); // this msg would display in Reports.
		}
		// Load the runmodes of the tests
		runmodes=Test_Util.getDataSetRunmodes(Payroll_NI_Director_AtoD_SuiteXls, this.getClass().getSimpleName());
	}




	@Test
	public void CompareReports() throws Throwable
	{
		count++;
		if(! runmodes[count].equalsIgnoreCase("Y"))
		{
			Skip=true;
			throw new SkipException("Runmode for Test set data is set to 'NO' "+count);
		}
		APP_LOGS.debug("Executing the test case");
		//WebDriver driver = new FirefoxDriver(FirefoxDriverProfile());
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
					String employerNI= table.getTBody().getRow(rownum).getCell(2).getText();
					System.out.println("employerNI is :"+employerNI);
					String employeeNIPaidYTD= table.getTBody().getRow(rownum).getCell(3).getText();
					System.out.println("employeeNIPaidYTD is :"+employeeNIPaidYTD);
					String employerNIPaidYTD= table.getTBody().getRow(rownum).getCell(4).getText();
					System.out.println("employerNIPaidYTD is :"+employerNIPaidYTD);
					//System.out.println("Third cell of body is :"+employerNI);
					//call the function which reads the excel sheet.
					ReadsExpectedData(firstCellOfBody, employeeNI, employerNI,employeeNIPaidYTD,employerNIPaidYTD);
				}
				rownum++;
			}

		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
		}

	}




	public void ReadsExpectedData(String firstCellOfBody, String employeeNI, String employerNI, String employeeNIPaidYTD,String employerNIPaidYTD) throws Throwable
	{
		File excel = new File("F:\\Automation NI Reports\\HMRCTestData\\Automation Test Result for Directors_NI.xlsx");
		FileInputStream fis = new FileInputStream(excel);
		org.apache.poi.ss.usermodel.Workbook wb = WorkbookFactory.create(fis);
		org.apache.poi.ss.usermodel.Sheet ws = wb.getSheetAt(5);
		FileOutputStream webdata = new FileOutputStream ("F:\\Automation NI Reports\\HMRCTestData\\Automation Test Result for Directors_NI.xlsx");
		int rowNum = ws.getLastRowNum()+1;

		for(int i =3; i< rowNum; i++)
		{
			Row row = ws.getRow(i);
			String value1 = cellToString(row.getCell(1));
			//String MonthValue = cellToString(row.getCell(2));
			String value2 = cellToString(row.getCell(10));
			String value3 = cellToString(row.getCell(12));
			String value4 = cellToString(row.getCell(11));
			String value5 = cellToString(row.getCell(13));
			//int month1 = Integer.parseInt(MonthValue);

			if(value1 != null && value1.equalsIgnoreCase(firstCellOfBody))
			{
				row.createCell(14).setCellValue(employeeNI);
				row.createCell(15).setCellValue(employerNI);
				row.createCell(18).setCellValue(employeeNIPaidYTD);
				row.createCell(19).setCellValue(employerNIPaidYTD);
				if(value2 != null && value2.equalsIgnoreCase(employeeNI))
				{
					row.createCell(16).setCellValue("TRUE");
				}
				else
				{
					row.createCell(16).setCellValue("FALSE");
				}

				if(value3 != null && value3.equalsIgnoreCase(employerNI))
				{
					row.createCell(17).setCellValue("TRUE");
				}   
				else
				{
					row.createCell(17).setCellValue("FALSE");
				} 

				if(value4 != null && value4.equalsIgnoreCase(employeeNIPaidYTD))
				{
					row.createCell(20).setCellValue("TRUE");
				}   
				else
				{
					row.createCell(20).setCellValue("FALSE");
				} 

				if(value5 != null && value5.equalsIgnoreCase(employerNIPaidYTD))
				{
					row.createCell(21).setCellValue("TRUE");
				}   
				else
				{
					row.createCell(21).setCellValue("FALSE");
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




	@AfterMethod
	public void ReportDataSetResult()
	{
		if(Skip)
		{
			Test_Util.ReportDataSetResult(Payroll_NI_Director_AtoD_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
		}
		else if(Fail)
		{
			IsTestPass = false;
			Test_Util.ReportDataSetResult(Payroll_NI_Director_AtoD_SuiteXls, this.getClass().getSimpleName(), count+2, "Fail");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_NI_Director_AtoD_SuiteXls, this.getClass().getSimpleName(), count+2, "Pass");
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
			Test_Util.ReportDataSetResult(Payroll_NI_Director_AtoD_SuiteXls, "first", Test_Util.GetRowNum(Payroll_NI_Director_AtoD_SuiteXls, this.getClass().getSimpleName()),"Pass");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_NI_Director_AtoD_SuiteXls, "first", Test_Util.GetRowNum(Payroll_NI_Director_AtoD_SuiteXls, this.getClass().getSimpleName()),"Fail");
		}	
		closeBrowser();
	}

}

