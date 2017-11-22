package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.rti_Payroll_Scenario2_Month7;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.ErrorUtil;
import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Test_Util;



public class UpdateLeaveRecord7 extends TestSuiteBase
{

	String runmodes[] = null;
	static int count = -1;
	static int countCompensation = -1;
	public static boolean Fail=false;
	public static boolean Skip=false;
	public static boolean IsTestPass=true;
	public String cancelbuttn;
	public String leaveRequestId;
	public String leaveStDate;
	public String leaveBabyBornDate;
	public String leaveCategory;
	public String leaveType;
	public String leaveapprovalStatus;
	public String ckbox;
	
	


	@BeforeTest
	public void CheckTestSkip() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		if(! Test_Util.IsTestcaseRunMode(Payroll_RecognitionScenarioTwo_SuiteXls, this.getClass().getSimpleName()))
		{
			Skip=true;
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarioTwo_SuiteXls, "first", Test_Util.GetRowNum(Payroll_RecognitionScenarioTwo_SuiteXls, this.getClass().getSimpleName()),"Skipped");
			//Test_Util.ReportDataSetResult(Payroll_RecognitionScenarioTwo_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase" +this.getClass().getSimpleName() +" as the runmode is set to 'no' ");// this message would display in logs
			throw new Exception("Testcase is being skipped" + this.getClass().getSimpleName()+ "as it's Runmode is set to 'NO'"); // this msg would display in Reports.
		}
		// Load the runmodes of the tests
		runmodes=Test_Util.getDataSetRunmodes(Payroll_RecognitionScenarioTwo_SuiteXls, this.getClass().getSimpleName());
	}

	public String payfreqncy;
	boolean employeeFirsttimeView = true;
	boolean compensationFirsttimeView = true;
	boolean shouldOpenBrowser = true; 
	
	


	@Test(dataProvider = "getData")
	public void EmpsSetup_WithNICategory(String EmpName,String LeaveYear,String LeaveCategry,String BabyBornDate) throws Throwable
	{
		count++;
		if(! runmodes[count].equalsIgnoreCase("Y"))
		{
			Skip=true;
			throw new SkipException("Runmode for Test set data is set to 'NO' "+count);
		}

		APP_LOGS.debug("Executing the test case");
		if(shouldOpenBrowser)
		{
			shouldOpenBrowser = false;
			openBrowser();
			logingIntoDesiredORG(OrgFlag);
			driver.manage().window().maximize();
			try
			{
				if(existsElementchkFor1mts(OR.getProperty("Homepage_txt")))
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
		FetchEmployeeRecord(EmpName,LeaveYear,LeaveCategry,BabyBornDate);
		/*************************************************************************/
	}
	
	


	public void FetchEmployeeRecord(String EmpName,String LeaveYear,String LeaveCategory,String BabyBornDate) throws Throwable
	{
		try
		{
			if(employeeFirsttimeView)
			{
				employeeFirsttimeView = false;
				getObject("PersonalTab").click();
				System.out.println("I am in personal page");
				if(existsElementchkFor1mts(OR.getProperty("EmployeeView")))
				{
					System.out.println("I recognised the Employee view");
					Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("EmployeeView"))));
					selectByValue.selectByVisibleText("DO NOT TOUCH PAYROLL AUTOMATION TESTING");
					Thread.sleep(2000L);
					if(existsElementchkFor1mts(OR.getProperty("ViewGoButton")))
					{
						getObject("ViewGoButton").sendKeys("");
						getObject("ViewGoButton").click();
					}
					Thread.sleep(7000L);
				}
			}

			WebElement postsTable = driver.findElement(By.xpath(OR.getProperty("firstRecordOfNIcoulmnTable")));
			if(existsWebElement(postsTable))
			{
				List<WebElement> rows = postsTable.findElements(By.xpath(OR.getProperty("firstRecordOfNIcoulmnTableRows")));
				lastRowCount = rows.size();
				java.util.Iterator<WebElement> x = rows.iterator();
				int rownum = 1;	
				outerbreak:
				while(x.hasNext())
				{
					String firstRowOfEmployeeColumn="//div["+rownum+"]/table/tbody/tr/td[4]/div/a/span";
					WebElement firstEmployee= driver.findElement(By.xpath(firstRowOfEmployeeColumn));
					if(existsWebElement(firstEmployee))
					{
						String AppnEmp= firstEmployee.getText();
						//System.out.println(tempEmp+"-------"+empName+"------"+rownum);
						if(AppnEmp!=null && AppnEmp.equalsIgnoreCase(EmpName))
						{
							System.out.println("Employee matched");
							System.out.println("Employee name is  :"+EmpName);
							if(existsWebElement(firstEmployee))
							{
								firstEmployee.click();
								System.out.println("The employee namely :"+AppnEmp+"got clicked");
								break outerbreak;
							}
						}
						
						else if(rownum == lastRowCount && AppnEmp!=null && AppnEmp!=(EmpName))
						{
							System.out.println("The row number of the page reached"+ rownum +" to 200 and"+ " 	Required Employee not found "
							+ "hence clicking the"	+ "	pagination link so that Employee search continues for next page");
							if(existsElementchkFor1mts(OR.getProperty("paginationElementPersonal")))
							{
								getObject
								("paginationNextPersonal").sendKeys("");
								getObject
								("paginationNextPersonal").click();
								System.out.println("As the required employees are "
								+ "not found in first page,hence clicked to next page of personal Tab");
								Thread.sleep
								(8000L);
								rownum = 0;
							}
						 }
						rownum++;
					}
				}
			}
		}
		catch(Throwable t)
		{
			APP_LOGS.debug(" Check for error in NI Category method");
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
			ErrorUtil.addVerificationFailure(t);
			System.out.println("");
		}
		Thread.sleep(3000L);
		LeaveTab(LeaveYear,LeaveCategory,BabyBornDate);
	}
	
	


	public void LeaveTab(String LeaveYear,String LeaveCategory,String BabyBornDate)throws Throwable
	{
		try
		{
			if(existsElementchkFor1mts(OR.getProperty("leaveTabclk")))
			{
				getObject("leaveTabclk").sendKeys("");
				getObject("leaveTabclk").click();
				System.out.println("The Leave Tab got clicked");
				Thread.sleep(3000L);
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
		UpdateLeavedetails(BabyBornDate);
	}


	
	public void UpdateLeavedetails(String BabyBornDate)throws Throwable
	{
		try
		{
			Thread.sleep(4000L);
			System.out.println("i am now in Updateleave details method");
			if(existsElementchkFor1mts(OR.getProperty("sspLeavSummaryTableLocator")))
			{
				WebElement LeaveTable = driver.findElement(By.xpath(OR.getProperty("sspLeavSummaryTableLocator")));
				System.out.println("The table exists");
				if(existsWebElement(LeaveTable))
				{
					List<WebElement> rows = LeaveTable.findElements(By.xpath(OR.getProperty("sspLeavSummaryTableRowsLocator")));
					java.util.Iterator<WebElement> x = rows.iterator();
					System.out.println("total rows are "+rows.size());
					int rownum = 0;	
					while(x.hasNext())
					{
						String leaveReqId="//div[@class='pbBody']/table/tbody/tr["+(rownum + 1)+"]/td[2]/a";
						WebElement LeavReqRecord= driver.findElement(By.xpath(leaveReqId));
						LeavReqRecord.sendKeys("");
						LeavReqRecord.click();
						System.out.println("The leave record got clicked");
						Thread.sleep(4000L);
						sickLeaveRecordEdit(BabyBornDate);	
						break;
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

	

	public void sickLeaveRecordEdit(String BabyBornDate)throws Throwable
	{
		try
		{
			if(existsElementchkFor1mts(OR.getProperty("sickLeaveEditbuttnLocator")))
			{
				getObject("sickLeaveEditbuttnLocator").sendKeys("");
				getObject("sickLeaveEditbuttnLocator").click();
				Thread.sleep(6000L);
				System.out.println("We are in the leave record edit mode");
			}
			selectCheckbox(BabyBornDate);
			SickSavebutton();
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}



	public void SickSavebutton()throws Throwable
	{
		try
		{
			if(existsElementchkFor1mts(OR.getProperty("sickSaveButton")))
			{
				getObject("sickSaveButton").sendKeys("");
				getObject("sickSaveButton").click();
				System.out.println("");
				System.out.println("The save button got clicked sucessfully");
			}
			Thread.sleep(4000L);
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}



	public void selectCheckbox(String BabyBornDate)throws Throwable
	{
		try
		{
			Thread.sleep(4000L);
			WebElement postsTable = driver.findElement(By.xpath(OR.getProperty("sspEditTable")));
			if(existsWebElement(postsTable))
			{
				System.out.println("details table exists");
				List<WebElement> rows = postsTable.findElements(By.xpath(OR.getProperty("sspEditTableRows")));	
				System.out.println("NUMBER OF ROWS IN THIS TABLE = "+rows.size());
				int row_num,col_num;
				row_num=1;
				outerloop:
					for(WebElement trElement : rows)
					{
						List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
						System.out.println("NUMBER OF COLUMNS="+td_collection.size());
						col_num=1;
						for(WebElement tdElement : td_collection)
						{
							System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
							if(tdElement.getText()!=null && tdElement.getText().equalsIgnoreCase("Baby born date"))
							{
								System.out.println("Label name  :"+tdElement.getText()+ "  matched ");
								ckbox ="//following-sibling::td[1]/span/input[@id='00Nb0000009I7Ix']";
								WebElement clkchkbox = driver.findElement(By.xpath(ckbox));
								try
								{
									if(existsWebElement(clkchkbox))
									{
										clkchkbox.sendKeys("");
										clkchkbox.clear();
										Thread.sleep(2000);
										String dateStr = BabyBornDate;
										DateFormat readFormat = new SimpleDateFormat("MM/dd/yyyy");
										DateFormat writeFormat = new SimpleDateFormat("dd/MM/yyyy");
										Date date = null;				
										try 
										{
											date = readFormat.parse( dateStr.trim() );
											System.out.println(date.toString());
										} 
										catch ( ParseException e ) 
										{
											System.out.println(e.getMessage());
										}

										String formattedDate = null;
										if( date != null ) 
										{
											formattedDate = writeFormat.format( date );
										}
										System.out.println("The entered date is  " +formattedDate);		
										Thread.sleep(4000L);
										clkchkbox.sendKeys(formattedDate);
										System.out.println("");
										System.out.println("The Baby born date was entered sucessfully");	
										Thread.sleep(2000);
									}
								}
								catch(Throwable t)
								{
									System.out.println(t.getMessage().toString());
									System.out.println(t.getStackTrace().toString());
								}
						col_num++;
						}
						row_num++;
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

	

	@DataProvider
	public Object[][] getData() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		return Test_Util.getData(Payroll_RecognitionScenarioTwo_SuiteXls,"UpdateLeaveRecord7");
	}



	@AfterMethod
	public void ReportDataSetResult() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);

		if(Skip)
		{
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarioTwo_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
		}else if(Fail)
		{
			IsTestPass = false;
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarioTwo_SuiteXls, this.getClass().getSimpleName(), count+2, "Fail");
		}else
		{
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarioTwo_SuiteXls, this.getClass().getSimpleName(), count+2, "Pass");
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
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarioTwo_SuiteXls, "first", Test_Util.GetRowNum(Payroll_RecognitionScenarioTwo_SuiteXls, this.getClass().getSimpleName()),"Pass");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarioTwo_SuiteXls, "first", Test_Util.GetRowNum(Payroll_RecognitionScenarioTwo_SuiteXls, this.getClass().getSimpleName()),"Fail");
		}	
		closeBrowser();
	}

}
