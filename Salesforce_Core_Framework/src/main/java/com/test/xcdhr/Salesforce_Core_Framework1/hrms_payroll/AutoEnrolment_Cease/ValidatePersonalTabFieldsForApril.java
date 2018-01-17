package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.AutoEnrolment_Cease;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Test_Util;

public class ValidatePersonalTabFieldsForApril extends TestSuiteBase {

	String runmodes[] = null;
	static int count = -1;
	static int countCompensation = -1;

	public static boolean Fail=false;
	public static boolean Skip=false;
	public static boolean IsTestPass=true;
	public String AutoEnrolmtNotifnChkbox;
	public String AutEnrolNotfnChkboxStatusValue;



	@BeforeTest
	public void CheckTestSkip() throws Exception{
		if(! Test_Util.IsTestcaseRunMode(Payroll_AutoEnrolment_Employee_Cease_SuiteXls, this.getClass().getSimpleName())){

			Skip=true;
			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Employee_Cease_SuiteXls, "first", Test_Util.GetRowNum(Payroll_AutoEnrolment_Employee_Cease_SuiteXls, this.getClass().getSimpleName()),"Skipped");
			//Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Employee_Cease_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase" +this.getClass().getSimpleName() +" as the runmode is set to 'no' ");// this message would display in logs
			throw new Exception("Testcase is being skipped" + this.getClass().getSimpleName()+ "as it's Runmode is set to 'NO'"); // this msg would display in Reports.
		}
		// Load the runmodes of the tests
		runmodes=Test_Util.getDataSetRunmodes(Payroll_AutoEnrolment_Employee_Cease_SuiteXls, this.getClass().getSimpleName());
	}

	public String payfreqncy;
	boolean employeeFirsttimeView = true;
	boolean compensationFirsttimeView = true;
	boolean shouldOpenBrowser = true; 
	public String notfnchkbox="1.0";
	/*
	 * 1>empname
	 * 2>Test result file path
	 * 
	 */

	@Test(dataProvider = "getData")
	public void CompareValidationReports(String EmployerName,String EmpName,String Payrolid,String Frquency,String MonthName,String ExcelInputSheet,String FirstReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,String PayrollVeiw) throws Throwable
	{
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
			driver.get(CONFIG.getProperty("testSiteName"));
			login_To_QA_Org();

			driver.manage().window().maximize();

			try
			{
				if(existsElement(OR.getProperty("Homepage_txt")))
				{
					Assert.assertEquals(driver.getTitle(), "Salesforce - Enterprise Edition");
					System.out.println("The test script logged in successfully into salesforce account");
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

		// The script updates the compensation record for the Automation employees
		CreateCompenstionRecord(EmpName,TestResultExcelFilePath);

		/*************************************************************************/
	}




	public void CreateCompenstionRecord(String EmpName,String TestResultExcelFilePath) throws Throwable
	{
		try
		{
			if(employeeFirsttimeView)
			{
				employeeFirsttimeView = false;
				getObject("PersonalTab").click();
				if(existsElement(OR.getProperty("PersonalText")))
				{
					System.out.println("I am in personal page");
					if(existsElement(OR.getProperty("EmployeeView")))
					{
						System.out.println("I recognised the Employee view");
						Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("EmployeeView"))));
						// This select by value needs to be called from OR.Properties
						selectByValue.selectByValue("00Bb0000004A5rt");
						Thread.sleep(1000L);
						getObject("ViewGoButton").click();
						Thread.sleep(4000L);
					}

				}

			}

			WebElement postsTable = driver.findElement(By.xpath(OR.getProperty("firstRecordOfNIcoulmnTable")));
			List<WebElement> rows = postsTable.findElements(By.xpath(OR.getProperty("firstRecordOfNIcoulmnTableRows")));

			java.util.Iterator<WebElement> x = rows.iterator();
			int rownum = 1;			
			while(x.hasNext())
			{
				String empRecord="//div["+rownum+"]/table/tbody/tr/td[4]/div/a/span";
				WebElement empwebelement= driver.findElement(By.xpath(empRecord));
				String AppnEmp= empwebelement.getText();
				//System.out.println(tempEmp+"-------"+empName+"------"+rownum);
				if(AppnEmp!=null && AppnEmp.equalsIgnoreCase(EmpName))
				{
					System.out.println("Employee matched");
					System.out.println("Employee name is  :"+EmpName);
					Thread.sleep(3000L);
					empwebelement.click();
					break;
				}
				rownum++;
			}
			System.out.println("I am in Employees page now");
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}

		Thread.sleep(3000L);
		try
		{
			if(existsElement(OR.getProperty("otherDetailsTableLocator")))
			{
				WebElement PersonalSectionTablelocator = driver.findElement(By.xpath(OR.getProperty("otherDetailsTableLocator")));
				//WebTable table = WebTable.getTable(PersonalSectionTablelocator);
				List<WebElement> rows = PersonalSectionTablelocator.findElements(By.xpath(OR.getProperty("otherDetailsTableLocatorRows")));
				int rowscount = rows.size();
				System.out.println("total rows are :"+ rowscount);
				/*
				 * Here we need to capture the Auto Enrolment notification Checkbox as CHECKED
				 */
				uncheckNotificationChkbox(notfnchkbox);
				
			}
						
			System.out.println("Personal tab validation completed");
			Thread.sleep(6000L);
			/*
			 * when passing the argument to the 'ReadsExpectedData' method , first declare the public string at the top and use it in the method as argument.
			 * But keep in mind, you are passing the arguments in the same order (sequence) that of method parameters
			 */
			ReadsExpectedData(EmpName,TestResultExcelFilePath,AutEnrolNotfnChkboxStatusValue);

		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}

	}

	
	
	
	public void uncheckNotificationChkbox(String notfnchkbox)throws Throwable
	{
		try
		{
			if(existsElement(OR.getProperty("personalEditButtonLoctor")))
			{
				getObject("personalEditButtonLoctor").sendKeys("");
				getObject("personalEditButtonLoctor").click();
				Thread.sleep(3000L);
			}
			
			if(existsElement(OR.getProperty("autoEnrolmentNotfnChkbox")))
			{
				AutoEnrlNotfnChkbox(notfnchkbox);
			}
			
			if(existsElement(OR.getProperty("personalSavebutonLocator")))
			{
				getObject("personalSavebutonLocator").sendKeys("");
				getObject("personalSavebutonLocator").click();
			}
			
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
		}
	}
	
	
	
	public void AutoEnrlNotfnChkbox(String notfnchkbox)throws Throwable
	{

		try
		{
			boolean	autoEnrolmtChkboxx = getObject("autoEnrolmentNotfnChkbox").isSelected();
			double valueOfsmallReliefChkbox = Double.parseDouble(notfnchkbox);
			System.out.println("converted smallER value is :"+valueOfsmallReliefChkbox);
			if(valueOfsmallReliefChkbox==1.0)
			{
				Thread.sleep(4000L);
				uncheckAEChkbox(autoEnrolmtChkboxx);
			}

		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}

	}


	
	
	public boolean uncheckAEChkbox(boolean autoEnrolmtChkboxx)throws Throwable
	{
		if(autoEnrolmtChkboxx)
		{
			AutoEnrolmtNotifnChkbox="TRUE";
			System.out.println("The AutoEnrolment notification checkbox is set to TRUE");
		}
		else
		{
			AutoEnrolmtNotifnChkbox="FALSE";
			System.out.println("The AutoEnrolment notification checkbox is set to TRUE");
		}
		return autoEnrolmtChkboxx;
	}
	
	



	/*
	 * while passing the parameter to the below method you can pass with any string name.
	 * 
	 */
	public void ReadsExpectedData(String EmpName,String TestResultExcelFilePath,String AutEnrolNotfnChkboxStatusValue) throws Throwable
	{
		File excel = new File(TestResultExcelFilePath);
		FileInputStream fis = new FileInputStream(excel);
		org.apache.poi.ss.usermodel.Workbook wb = WorkbookFactory.create(fis);
		org.apache.poi.ss.usermodel.Sheet ws = wb.getSheetAt(11);
		
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

		Row row2 = ws.getRow(2);
		

		String employeeUnderTest = cellToString(row2.getCell(0));
		String AutEnrolNotfn_ChkboxStatusValue = cellToString(row2.getCell(2));


		row2.createCell(5).setCellValue(AutoEnrolmtNotifnChkbox);
		
		
		if(employeeUnderTest != null && employeeUnderTest.equalsIgnoreCase(EmpName))
		{
			if(AutEnrolNotfn_ChkboxStatusValue != null && AutEnrolNotfn_ChkboxStatusValue.equalsIgnoreCase(AutoEnrolmtNotifnChkbox))
			{
				Cell cell1 = row2.createCell(6);
				row2.createCell(6).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row2.createCell(6);
				row2.createCell(6).setCellValue("FALSE");
				cell1.setCellStyle(styleFalse);
			}

			
			/*
			 * add1 = expected value
			 * addr1 = the actual value which is pasted in the excel sheet.
			 * so on comparision we are going to display TRUE or FALSE
			 */
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

		case 5: result=cell.getDateCellValue();
		break;

		default: 
			throw new RuntimeException("there are no othe values");

		}
		return result.toString();
	}


	@DataProvider
	public Object[][] getData()
	{
		return Test_Util.getData(Payroll_AutoEnrolment_Employee_Cease_SuiteXls,"ProcessPayrollForApril");
	}




	@AfterMethod
	public void ReportDataSetResult()
	{
		if(Skip)
		{
			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Employee_Cease_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
		}
		else if(Fail)
		{
			IsTestPass = false;
			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Employee_Cease_SuiteXls, this.getClass().getSimpleName(), count+2, "Fail");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Employee_Cease_SuiteXls, this.getClass().getSimpleName(), count+2, "Pass");
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
			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Employee_Cease_SuiteXls, "first", Test_Util.GetRowNum(Payroll_AutoEnrolment_Employee_Cease_SuiteXls, this.getClass().getSimpleName()),"Pass");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Employee_Cease_SuiteXls, "first", Test_Util.GetRowNum(Payroll_AutoEnrolment_Employee_Cease_SuiteXls, this.getClass().getSimpleName()),"Fail");
		}	
		closeBrowser();
	}

}
