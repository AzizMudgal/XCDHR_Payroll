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
public class ValidatePersonalAndCompnForAug extends TestSuiteBase {

	String runmodes[] = null;
	static int count = -1;
	static int countCompensation = -1;

	public static boolean Fail=false;
	public static boolean Skip=false;
	public static boolean IsTestPass=true;

	public String firstCellOfBody;
	public String firstRowOfCompnRecord;
	public int RecordCount;
	public String AutoEnrolmntdate;
	public String AutoEnrolmtNotifnChkbox;
	public String AutoEnrolNotifyAttahment;
	public String EffctFrom;
	public String pensionScheme;
	public String employrPension;
	public String empPension;
	public String effectTo;
	public String Action;
	public boolean AutoEnrolmtNotifnChkbox1;
	public String AutEnrolNotfnChkboxStatusValue;
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
	public String notfnchkbox="1.0";
	public String RowOfAttachementRecord;
	
	@Test(dataProvider = "getData")
	public void validateAutoenrolmentForAugustMonth(String EmployerName,String EmpName,String Payrolid,String Frquency,String MonthName,String ExcelInputSheet,String FirstReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,String PayrollVeiw) throws Throwable
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
			System.out.println("this is personal Tab");
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}

		Thread.sleep(3000L);
		try
		{
			if(existsElement(OR.getProperty("personalEditButtonLoctor")))
			{
				uncheckNotificationChkbox(notfnchkbox);
				
			}
			
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
				
				if(existsElement(OR.getProperty("otherDetailsTableLocator")))
				{
				WebElement Autodateenrol = driver.findElement(By.xpath(OR.getProperty("otherDetailsTableLocator")));
				WebTable table1 = WebTable.getTable(Autodateenrol);
				List<WebElement> rowss = Autodateenrol.findElements(By.xpath(OR.getProperty("otherDetailsTableLocatorRows")));
				int rowscountt = rowss.size();
				System.out.println("total rows are :"+ rowscountt);
				AutoEnrolmntdate= table1.getTBody().getRow(2).getCell(0).getText();
				System.out.println("The Auto Enrolment date is :"+AutoEnrolmntdate);
				
				}
				
			}
				
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
				ReadsExpectedData(EmpName,AutoEnrolmntdate,AutEnrolNotfnChkboxStatusValue,AutoEnrolNotifyAttahment,effectTo,Action,EffctFrom,pensionScheme,employrPension,empPension,TestResultExcelFilePath);
			
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
	public void ReadsExpectedData(String EmpName,String AutoEnrolmntdate,String AutEnrolNotfnChkboxStatusValue,String AutoEnrolNotifyAttahment,String effectTo,String Action,String EffctFrom,String pensionScheme,String employrPension,String empPension,String TestResultExcelFilePath) throws Throwable
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
		
		Row row6 = ws.getRow(6);
		Row row7 = ws.getRow(7);
		Row row8 = ws.getRow(8);
		
		Row row9 = ws.getRow(9);
		Row row10 = ws.getRow(10);
		Row row11 = ws.getRow(11);
		Row row12 = ws.getRow(12);
		
		Row row13 = ws.getRow(13);
		Row row14 = ws.getRow(14);
		

		String employeeUnderTest = cellToString(row2.getCell(0));
		String Auto_enrolment_date = cellToString(row6.getCell(2));
		String Auto_enrolment_notification = cellToString(row7.getCell(2));
	
		
		String Effective_from = cellToString(row9.getCell(2));
		String Pension_scheme = cellToString(row10.getCell(2));
		String Employer_contribution = cellToString(row11.getCell(2));
		String Employee_Contribution = cellToString(row12.getCell(2));
		
		
		String Effective_to = cellToString(row13.getCell(2));
		String Actionn = cellToString(row14.getCell(2));
		
		row6.createCell(5).setCellValue(AutoEnrolmntdate);
		row7.createCell(5).setCellValue(AutoEnrolmtNotifnChkbox);
		row8.createCell(2).setCellValue(AutoEnrolNotifyAttahment);
		row8.createCell(5).setCellValue(AutoEnrolNotifyAttahment);
		String New_attachment=cellToString(row8.getCell(2));
		
		row9.createCell(5).setCellValue(EffctFrom);
		row10.createCell(5).setCellValue(pensionScheme);
		row11.createCell(5).setCellValue(employrPension);
		row12.createCell(5).setCellValue(empPension);
	
		
		row13.createCell(5).setCellValue(effectTo);
		row14.createCell(5).setCellValue(Action);
		
		
	
		if(employeeUnderTest != null && employeeUnderTest.equalsIgnoreCase(EmpName))
		{
			if(Auto_enrolment_date != null && Auto_enrolment_date.equalsIgnoreCase(AutoEnrolmntdate))
			{
				Cell cell1 = row6.createCell(6);
				row6.createCell(6).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row6.createCell(6);
				row6.createCell(6).setCellValue("FALSE");
				cell1.setCellStyle(styleFalse);
			}

			if(Auto_enrolment_notification != null && Auto_enrolment_notification.equalsIgnoreCase(AutoEnrolmtNotifnChkbox))
			{
				Cell cell1 = row7.createCell(6);
				row7.createCell(6).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row7.createCell(6);
				row7.createCell(6).setCellValue("FALSE");
				cell1.setCellStyle(styleFalse);
			}

			if(New_attachment != (AutoEnrolNotifyAttahmentFalse) && New_attachment.equalsIgnoreCase(AutoEnrolNotifyAttahment))
			{
				Cell cell1 = row8.createCell(6);
				row8.createCell(6).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row8.createCell(6);
				row8.createCell(6).setCellValue("FALSE");
				cell1.setCellStyle(styleFalse);
			}
			
			
			
			
			if(Effective_from != null && Effective_from.equalsIgnoreCase(EffctFrom))
			{
				Cell cell1 = row9.createCell(6);
				row9.createCell(6).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row9.createCell(6);
				row9.createCell(6).setCellValue("FALSE");
				cell1.setCellStyle(styleFalse);
			}
			
			
			
			if(Pension_scheme != null && Pension_scheme.equalsIgnoreCase(pensionScheme))
			{
				Cell cell1 = row10.createCell(6);
				row10.createCell(6).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row10.createCell(6);
				row10.createCell(6).setCellValue("FALSE");
				cell1.setCellStyle(styleFalse);
			}
			
			if(Employer_contribution != null && Employer_contribution.equalsIgnoreCase(employrPension))
			{
				Cell cell1 = row11.createCell(6);
				row11.createCell(6).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row11.createCell(6);
				row11.createCell(6).setCellValue("FALSE");
				cell1.setCellStyle(styleFalse);
			}
			
			
			if(Employee_Contribution != null && Employee_Contribution.equalsIgnoreCase(empPension))
			{
				Cell cell1 = row12.createCell(6);
				row12.createCell(6).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row12.createCell(6);
				row12.createCell(6).setCellValue("FALSE");
				cell1.setCellStyle(styleFalse);
			}
			
			if(Effective_to != null && Effective_to.equalsIgnoreCase(effectTo))
			{
				Cell cell1 = row13.createCell(6);
				row13.createCell(6).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row13.createCell(6);
				row13.createCell(6).setCellValue("FALSE");
				cell1.setCellStyle(styleFalse);
			}
			
			
			if(Actionn != null && Actionn.equalsIgnoreCase(Action))
			{
				Cell cell1 = row14.createCell(6);
				row14.createCell(6).setCellValue("TRUE");
				cell1.setCellStyle(style);
			}
			else
			{
				Cell cell1 = row14.createCell(6);
				row14.createCell(6).setCellValue("FALSE");
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
					System.out.println("the comp button exist");
					if(existsElement(OR.getProperty("compensationTableLocator")))
					{
					WebElement postsTable = driver.findElement(By.xpath(OR.getProperty("compensationTableLocator")));
					//WebTable table = WebTable.getTable(postsTable);
					List<WebElement> rows = postsTable.findElements(By.xpath(OR.getProperty("compensationTableRowsLocator")));
					int ttrows= rows.size();
					System.out.println("Total compensation records are :"+ttrows);
					java.util.Iterator<WebElement> x = rows.iterator();
					int rownumv = 1;	
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
							if(rownumv==1)
							{
								if(existsElement(OR.getProperty("ScondCompnTable")))
								{
									WebElement SecondCompnTablelocator = driver.findElement(By.xpath(OR.getProperty("ScondCompnTable")));
									WebTable Comptable = WebTable.getTable(SecondCompnTablelocator);
									EffctFrom= Comptable.getTBody().getRow(2).getCell(1).getText();
									System.out.println("The EffectiveFrom date is  :"+EffctFrom);
																		
									WebElement PensionTablelocator = driver.findElement(By.xpath(OR.getProperty("AutoEnrolPensionSchemeTableLocator")));
									WebTable Pensiontable = WebTable.getTable(PensionTablelocator);
									pensionScheme= Pensiontable.getTBody().getRow(0).getCell(1).getText();
									System.out.println("The Pension scheme is  :"+pensionScheme);
									
									employrPension= Pensiontable.getTBody().getRow(1).getCell(3).getText();
									System.out.println("The Pension scheme is  :"+employrPension);
									
									empPension= Pensiontable.getTBody().getRow(1).getCell(1).getText();
									System.out.println("The Pension scheme is  :"+empPension);
									
									if(existsElement(OR.getProperty("compnbackButton")))
									{
										compensationBackClick();
									}
								}
							}
							else if(rownumv == 2) 
							{
								System.out.println("Indeed this is first compensation record");
								if(existsElement(OR.getProperty("compnbackButton")))
								{
									WebElement compn1TableLocator = driver.findElement(By.xpath(OR.getProperty("AutoEnrolEffectiveToTableLocator")));
									WebTable EffectiveTotable = WebTable.getTable(compn1TableLocator);
									effectTo= EffectiveTotable.getTBody().getRow(3).getCell(3).getText();
									System.out.println("The first Compensatin record's Effective to date is  :"+effectTo);
									
									WebElement ActionTableLocator = driver.findElement(By.xpath(OR.getProperty("AutoEnrolActionTableLocator")));
									WebTable ActionTable = WebTable.getTable(ActionTableLocator);
									Action= ActionTable.getTBody().getRow(3).getCell(1).getText();
									System.out.println("The first Compensatin record's Action value is  :"+Action);
									
									if(existsElement(OR.getProperty("compnEditButton")))
									{
										getObject("compnEditButton").sendKeys("");
										getObject("compnEditButton").click();
										
										WebElement Actiontext = driver.findElement(By.xpath(OR.getProperty("actionTextLocator")));
										
										if(Actiontext.getAttribute("value").isEmpty())
										{
											Action="Null";
											System.out.println("The value in the Action text field is EMPTY ");
											getObject("compCancel").sendKeys("");
											getObject("compCancel").click();
										}
										else
										{
											System.out.println("it is not empty");
										}
									
									}
									
								}
							}
							
							rownumv +=1;
							
							if(rownumv == 3)
							{
								System.out.println("The method has searched the 2 required compensation record,"
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
				
				if(ttrows==2)
				{
					System.out.println("no new attachement record created for Autoenrolment august");
					AutoEnrolNotifyAttahmentFalse="no new attachement record created for Autoenrolment august";
				}
				/*
				 * if the attachements are being created for
				 * April,May and June also then in 'else if' condition ttrows>2 needs to be set.
				 * and in "if condition" the ttrows==2 needs to be set.
				 */
				else if(ttrows>2)
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
		return Test_Util.getData(Payroll_AutoEnrolment_Employee_Turns22_SuiteXls,"ProcessPayrollForAugust");
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
