package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.AutoEnrolment_Employee_Turns22;


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

import atu.webdriver.utils.table.WebTable;
import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Test_Util;

public class ValidatePersonalAndCompnForMay extends TestSuiteBase {

	String runmodes[] = null;
	static int count = -1;
	static int countCompensation = -1;

	public static boolean Fail=false;
	public static boolean Skip=false;
	public static boolean IsTestPass=true;
	public String EmpName;
	public String firstRowOfCompnRecord;
	public String AutoEnrolNotifyAttahment;
	public String defferedDate;
	public String AutoEnrolNotifyAttahmentFalse;
	


	@BeforeTest
	public void CheckTestSkip() throws Exception{
		if(! Test_Util.IsTestcaseRunMode(Payroll_AutoEnrolment_Employee_Turns22_SuiteXls, this.getClass().getSimpleName())){

			Skip=true;
			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Employee_Turns22_SuiteXls, "first", Test_Util.GetRowNum(Payroll_AutoEnrolment_Employee_Turns22_SuiteXls, this.getClass().getSimpleName()),"Skipped");
			//Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Employee_Turns22_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase" +this.getClass().getSimpleName() +" as the runmode is set to 'no' ");// this message would display in logs
			throw new Exception("Testcase is being skipped" + this.getClass().getSimpleName()+ "as it's Runmode is set to 'NO'"); // this msg would display in Reports.
		}
		// Load the runmodes of the tests
		runmodes=Test_Util.getDataSetRunmodes(Payroll_AutoEnrolment_Employee_Turns22_SuiteXls, this.getClass().getSimpleName());
	}

	public String payfreqncy;
	boolean employeeFirsttimeView = true;
	boolean compensationFirsttimeView = true;
	boolean shouldOpenBrowser = true; 


	@Test(dataProvider = "getData")
	public void validateAutoenrolmentForMayMonth(String EmployerName,String EmpName,String Payrolid,String Frquency,String MonthName,String ExcelInputSheet,String FirstReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,String PayrollVeiw) throws Throwable
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
			login_To_Application();

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
		validatePersonalAndRewardTab(EmpName,TestResultExcelFilePath);

		/*************************************************************************/

	}




	public void validatePersonalAndRewardTab(String EmpName,String TestResultExcelFilePath) throws Throwable
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
			System.out.println("from compensation record");
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}

		Thread.sleep(3000L);
		try
		{
			
			if(existsElement(OR.getProperty("AutoEnrolNotifyNoticeTable")))
			{
				checkingAttachments();
			}
			
			if(existsElement(OR.getProperty("rewardtabClk")))
			{
				RewardTab();
			}
			System.out.println("Reward tab validation completed");
			Thread.sleep(6000L);
			/*
			 * when passing the argument to the 'ReadsExpectedData' method , first declare the public string at the top and use it in the method as argument.
			 * But keep in mind, you are passing the arguments in the same order (sequence) that of method parameters
			 */
			ReadsExpectedData(EmpName,AutoEnrolNotifyAttahment,defferedDate,TestResultExcelFilePath);
			
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
		
	}



	/*
	 * while passing the parameter to the below method you can pass with any string name.
	 * 
	 */
	public void ReadsExpectedData(String EmpName,String AutoEnrolNotifyAttahment,String DefferedDate,String TestResultExcelFilePath) throws Throwable
	{
		File excel = new File(TestResultExcelFilePath);
		FileInputStream fis = new FileInputStream(excel);
		org.apache.poi.ss.usermodel.Workbook wb = WorkbookFactory.create(fis);
		org.apache.poi.ss.usermodel.Sheet ws = wb.getSheetAt(3);

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
		Row row4 = ws.getRow(4);
		Row row5 = ws.getRow(5);
				

		String employeeUnderTest = cellToString(row2.getCell(0));
		String deferreddate = cellToString(row5.getCell(2));
		row4.createCell(5).setCellValue(AutoEnrolNotifyAttahment);
		row4.createCell(2).setCellValue(AutoEnrolNotifyAttahment);
		String New_attachment=cellToString(row4.getCell(2));
		row5.createCell(5).setCellValue(DefferedDate);
				
	
		if(employeeUnderTest != null && employeeUnderTest.equalsIgnoreCase(EmpName))
		{
			if(New_attachment != (AutoEnrolNotifyAttahmentFalse) && New_attachment.equalsIgnoreCase(AutoEnrolNotifyAttahment))
			{
				Cell cell1 = row4.createCell(6);
				row4.createCell(6).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row4.createCell(6);
				row4.createCell(6).setCellValue("FALSE");
				cell1.setCellStyle(styleFalse);
			}

			if(deferreddate != null && deferreddate.equalsIgnoreCase(DefferedDate))
			{
				Cell cell1 = row5.createCell(6);
				row5.createCell(6).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row5.createCell(6);
				row5.createCell(6).setCellValue("FALSE");
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

	
	public void checkingAttachments()throws Throwable
	{
		try
		{
			if(existsElement(OR.getProperty("AutoEnrolNotifyNoticeTable")))
			{
				WebElement AEnotifyNoticeTablelocator = driver.findElement(By.xpath(OR.getProperty("AutoEnrolNotifyNoticeTable")));
				List<WebElement> rows = AEnotifyNoticeTablelocator.findElements(By.xpath(OR.getProperty("AutoEnrolNotifyNoticeTableRows")));
				int ttrows= rows.size();
				System.out.println("Total attachment records are :"+ttrows);
				//java.util.Iterator<WebElement> x = rows.iterator();
				
				if(ttrows==1)
				{
					System.out.println("no new attachement record created for Autoenrolment august");
					AutoEnrolNotifyAttahmentFalse="no new attachement record created for Autoenrolment august";
				}
				/*
				 * if the attachments are being created for
				 * April,May and June also then in 'else if' condition ttrows>2 needs to be set.
				 * and in "if condition" the ttrows==2 needs to be set.
				 */
				else if(ttrows>1)
				{
					WebTable NotifyNoticetable = WebTable.getTable(AEnotifyNoticeTablelocator);
					AutoEnrolNotifyAttahment= NotifyNoticetable.getTBody().getRow(0).getCell(0).getText();
					System.out.println("The Enrolment Notify Notice text is :"+AutoEnrolNotifyAttahment);
					System.out.println("Threre are more than one Attachement record ");
				}
				
			}
						
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}
	
	
	
	


	public void RewardTab()throws Throwable
	{
		try
		{
			if(compensationFirsttimeView)
			{
				compensationFirsttimeView=false;
				if(existsElement(OR.getProperty("rewardtabClk")))
				{
					getObject("rewardtabClk").sendKeys("");
					getObject("rewardtabClk").click();
					Thread.sleep(3000L);
				}
			}
				
				if(existsElement(OR.getProperty("compensationButtoncomppage")))
				{
					System.out.println("the Compn button text exist");
					if(existsElement(OR.getProperty("compensationTableLocator")))
					{
					WebElement postsTable = driver.findElement(By.xpath(OR.getProperty("compensationTableLocator")));
					//WebTable table = WebTable.getTable(postsTable);
					List<WebElement> rows = postsTable.findElements(By.xpath(OR.getProperty("compensationTableRowsLocator")));
					int ttrows= rows.size();
					System.out.println("Total compensation records are :"+ttrows);
					java.util.Iterator<WebElement> x = rows.iterator();
					int rownumv = 2;	
					endSearchingCompnRecord:
					while(x.hasNext())
					{
						System.out.println("the index of rownumv is  :"+rownumv);
						if(existsElement(OR.getProperty("compensationTableLocator")))
						{
							firstRowOfCompnRecord="//div[contains(@id, 'CompensationBlock')]/div[2]/table/tbody/"+"tr["+rownumv+"]"+"/td[1]/a";
							
							WebElement compensationlink= driver.findElement(By.xpath(firstRowOfCompnRecord));
							compensationlink.click();
							System.out.println("compensation link got clicked");
							if(rownumv==2)
							{
								if(existsElement(OR.getProperty("defferedDateTableLocator")))
								{
									WebElement SecondCompnTablelocator = driver.findElement(By.xpath(OR.getProperty("defferedDateTableLocator")));
									WebTable Comptable = WebTable.getTable(SecondCompnTablelocator);
									defferedDate= Comptable.getTBody().getRow(0).getCell(3).getText();
									System.out.println("The defferedDate is  :"+defferedDate);
																		
									if(existsElement(OR.getProperty("compnbackButton")))
									{
										compensationBackClick();
									}
								}
							}
														
							rownumv +=1;
							if(rownumv == 3)
							{
								System.out.println("The method has searched the 1 required compensation record,"
										+ "hence comming out of this validate compn method");
								break endSearchingCompnRecord;
							}
							
						}
						
					  }
							
					}
					
		     }
				
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}
	
	
	

	public void compensationBackClick()throws Throwable
	{
		try
		{
			if(existsElement(OR.getProperty("compnbackButton")))
			{
				getObject("compnbackButton").sendKeys("");
				getObject("compnbackButton").click();
				Thread.sleep(6000L);
			}
			
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}
	
	
	

	public String cellToString(Cell cell)
	{
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
		return Test_Util.getData(Payroll_AutoEnrolment_Employee_Turns22_SuiteXls,"ProcessPayrollForMay");
	}
	
	


	@AfterMethod
	public void ReportDataSetResult()
	{
		if(Skip)
		{
			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Employee_Turns22_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
		}
		else if(Fail)
		{
			IsTestPass = false;
			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Employee_Turns22_SuiteXls, this.getClass().getSimpleName(), count+2, "Fail");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Employee_Turns22_SuiteXls, this.getClass().getSimpleName(), count+2, "Pass");
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
			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Employee_Turns22_SuiteXls, "first", Test_Util.GetRowNum(Payroll_AutoEnrolment_Employee_Turns22_SuiteXls, this.getClass().getSimpleName()),"Pass");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Employee_Turns22_SuiteXls, "first", Test_Util.GetRowNum(Payroll_AutoEnrolment_Employee_Turns22_SuiteXls, this.getClass().getSimpleName()),"Fail");
		}	
		closeBrowser();
	}

}