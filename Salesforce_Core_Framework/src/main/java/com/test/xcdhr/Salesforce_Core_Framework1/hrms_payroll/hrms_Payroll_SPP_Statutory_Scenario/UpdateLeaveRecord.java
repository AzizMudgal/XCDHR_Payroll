package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.hrms_Payroll_SPP_Statutory_Scenario;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.ErrorUtil;
import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Test_Util;

public class UpdateLeaveRecord extends TestSuiteBase
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
	public String leaveEndDate;
	public String leaveCategory;
	public String leaveType;
	public String leaveapprovalStatus;
	public String RowOfAttachementRecord;
	public int ttrows;
	public String ckbox;
	public String ckboxQA;
	
	
	@BeforeTest
	public void CheckTestSkip() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);

		if(! Test_Util.IsTestcaseRunMode(Payroll_Statutory_Paternitypay_SuiteXls, this.getClass().getSimpleName()))
		{
			Skip=true;
			Test_Util.ReportDataSetResult(Payroll_Statutory_Paternitypay_SuiteXls, "first", Test_Util.GetRowNum(Payroll_Statutory_Paternitypay_SuiteXls, this.getClass().getSimpleName()),"Skipped");
			//Test_Util.ReportDataSetResult(Payroll_Statutory_Paternitypay_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase" +this.getClass().getSimpleName() +" as the runmode is set to 'no' ");// this message would display in logs
			throw new Exception("Testcase is being skipped" + this.getClass().getSimpleName()+ "as it's Runmode is set to 'NO'"); // this msg would display in Reports.
		}
		// Load the runmodes of the tests
		runmodes=Test_Util.getDataSetRunmodes(Payroll_Statutory_Paternitypay_SuiteXls, this.getClass().getSimpleName());
	}
	
	
	

	public String payfreqncy;
	boolean employeeFirsttimeView = true;
	boolean compensationFirsttimeView = true;
	boolean shouldOpenBrowser = true; 


	@Test(dataProvider = "getData")
	public void EmpsSetup_WithNICategory(String EmpName,String LeaveYear,String LeaveCategry,String BirthdueDate, String BabyBorndate,String StatutoryPaybasis,String ConditionSatisfied) throws Throwable
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
			}

		}

		/*************************************************************************/

		FetchEmployeeRecord(EmpName,LeaveYear,LeaveCategry,BirthdueDate,BabyBorndate,StatutoryPaybasis,ConditionSatisfied);

		/*************************************************************************/

	}


	
	
	
	public void FetchEmployeeRecord(String EmpName,String LeaveYear,String LeaveCategory,String BirthdueDate, String BabyBorndate,String StatutoryPaybasis,String ConditionSatisfied) throws Throwable
	{
		try
		{
			if(employeeFirsttimeView)
			{
				employeeFirsttimeView = false;
				getObject("PersonalTab").click();
				System.out.println("I am in personal page");
				if(existsElement(OR.getProperty("EmployeeView")))
				{
					System.out.println("I recognised the Employee view");
					Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("EmployeeView"))));
					selectByValue.selectByVisibleText("DO NOT TOUCH PAYROLL AUTOMATION TESTING");
					Thread.sleep(2000L);
					if(existsElement(OR.getProperty("ViewGoButton")))
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
						System.out.println(AppnEmp+"-------"+EmpName+"------"+rownum);
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
							System.out.println("The row number of the page reached"+ rownum +" to 200 and"
									+ " Required Employee not found hence clicking the"
									+ " pagination link so that Employee search continues for next page");
							if (existsElementchkFor1mts(OR.getProperty("paginationElementPersonal")))
							{
								getObject("paginationNextPersonal").sendKeys("");
								getObject("paginationNextPersonal").click();
								System.out.println("As the required employees are not found in first page,hence clicked to next page of personal Tab");
								Thread.sleep(8000L);
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
		LeaveTab(LeaveYear,LeaveCategory,BirthdueDate,BabyBorndate,StatutoryPaybasis,ConditionSatisfied);
	}

	
	
	

	public void LeaveTab(String LeaveYear,String LeaveCategory,String BirthdueDate, String BabyBorndate,String StatutoryPaybasis,String ConditionSatisfied)throws Throwable
	{
		try
		{
			if(existsElement(OR.getProperty("leaveTabclk")))
			{
				getObject("leaveTabclk").sendKeys("");
				getObject("leaveTabclk").click();
				Thread.sleep(3000L);
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
		Thread.sleep(3000L);
		System.out.println("after submitting leave ,i am going to leave tab now");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,800)", "");
		Thread.sleep(3000L);
		if(existsElement(OR.getProperty("sppLeavSummaryTableLocator")))
		{
			UpdateLeavedetails(StatutoryPaybasis,ConditionSatisfied);
		}
	}



	
	
	public void UpdateLeavedetails(String StatutoryPaybasis,String ConditionSatisfied)throws Throwable
	{
		try
		{
			System.out.println("i am now in Updateleave details method");
			if(existsElement(OR.getProperty("sppLeavSummaryTableLocator")))
			{
				System.out.println("recognised the leave table");
				WebElement LeaveTable = driver.findElement(By.xpath(OR.getProperty("sppLeavSummaryTableLocator")));
				WebTable table = WebTable.getTable(LeaveTable);
				if(existsWebElement(LeaveTable))
				{
					List<WebElement> rows = LeaveTable.findElements(By.xpath(OR.getProperty("sppLeavSummaryTableRowsLocator")));
					 ttrows= rows.size();
					java.util.Iterator<WebElement> x = rows.iterator();
					int rownumv = ttrows;
					while(x.hasNext())
					{
						if(existsElement(OR.getProperty("sppLeavSummaryTableLocator")))
						{
							RowOfAttachementRecord="//div[contains(@id,'leaveReq')]/div/table/tbody/"+"tr["+(rownumv - 1)+"]"+"/td[2]/a";
							WebElement attachmentlink= driver.findElement(By.xpath(RowOfAttachementRecord));
							attachmentlink.click();
							System.out.println("Leave record link got clicked");
							Thread.sleep(4000L);
							sickLeaveRecordEdit(StatutoryPaybasis,ConditionSatisfied);
							break;
						}
						
						/*
						//String leaveReqId="//form/table/tbody/tr[2]/td/table/tbody/tr/td/span/div[3]/div/div[2]/table/tbody/tr["+((rownum)+1)+"]/td[2]/a";
						String leaveReqId="//form[2]/div[1]/div/div[2]/table/tbody/tr["+((rownum)+1)+"]/td[2]/a";	
						WebElement firstEmployee= driver.findElement(By.xpath(leaveReqId));
						
						leaveRequestId= table.getTBody().getRow((rownum)+1).getCell(1).getText();
						System.out.println("The leave start date is "+leaveRequestId);
						firstEmployee.sendKeys("");
						firstEmployee.click();
						break;
						*/
						
						/*
						leaveStDate= table.getTBody().getRow((rownum)+1).getCell(2).getText();
						System.out.println("The leave start date is "+leaveStDate);
						leaveEndDate=table.getTBody().getRow((rownum)+1).getCell(3).getText();
						System.out.println("The leave Enddate is "+leaveEndDate);
						leaveCategory=table.getTBody().getRow((rownum)+1).getCell(5).getText();
						System.out.println("The leave category is "+leaveCategory);
						leaveapprovalStatus=table.getTBody().getRow((rownum)+1).getCell(7).getText();
						System.out.println("The leave approval status is "+leaveapprovalStatus);
						if(leaveStDate!=null && leaveStDate.equalsIgnoreCase(applnLeaveStDate)
								&& leaveEndDate!=null && leaveEndDate.equalsIgnoreCase(applnLeaveEndDate)
								&& leaveCategory!= null && leaveCategory.equalsIgnoreCase(applnLeaveCategory)
								&& leaveapprovalStatus != null && leaveapprovalStatus.equalsIgnoreCase(applnApprovalStatus))
						{
							System.out.println("All the conditions are matching, hence leave request Id would be clicked");
							firstEmployee.sendKeys("");
							firstEmployee.click();
							break;
						}
						

						rownum++;
						*/
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


	
	
	public void sickLeaveRecordEdit(String StatutoryPaybasis,String ConditionSatisfied)throws Throwable
	{
		try
		{
			if(existsElement(OR.getProperty("sppEditbutton")))
			{
				getObject("sppEditbutton").sendKeys("");
				getObject("sppEditbutton").click();
				Thread.sleep(6000L);
				System.out.println("We are in the sick record edit mode");
			}
			
			selectCondnSatisfy(ConditionSatisfied);
			
		}
		
		catch(Throwable t)
		{
			APP_LOGS.debug("could not find the Edit button locator");
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
		
	}


	
	
	
	

	public void SickSavebutton()throws Throwable
	{
		try
		{
			if(existsElement(OR.getProperty("sickSaveButton")))
			{
				getObject("sickSaveButton").sendKeys("");
				getObject("sickSaveButton").click();
				System.out.println("");
				System.out.println("The paternity save button got clicked sucessfully");
			}
			Thread.sleep(4000L);
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}

	}
	
	
	public void selectCondnSatisfy(String ConditionSatisfied)throws Throwable
	{
		try
		{
			WebElement postsTable = driver.findElement(By.xpath(OR.getProperty("SaapLeaveTablelocator")));
			if(existsWebElement(postsTable))
			{
				System.out.println("details table exists");
				List<WebElement> rows = postsTable.findElements(By.xpath(OR.getProperty("SaapLeaveTablelocatorRows")));

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

							if(tdElement.getText()!=null && tdElement.getText().equalsIgnoreCase("Statutory conditions met - make payment"))
							{
								System.out.println("Label name  :"+tdElement.getText()+ "  matched ");
								Thread.sleep(1000L);
								WebElement clkchkbox;							
								ckbox = "//following-sibling::td[1]/input[(@id='00Nb0000009I7J5')]";
							
								
								if(driver.findElement(By.xpath(ckbox)) != null) 
								{
									System.out.println("this is automation org's locator");
									clkchkbox = driver.findElement(By.xpath(ckbox));
									clkchkbox.sendKeys("");
								
								boolean	smallERchekbox = clkchkbox.isSelected();
								if(smallERchekbox)
								{
									System.out.println("yes the condition is checked");
								}
								double valueOfsmallReliefChkbox = Double.parseDouble(ConditionSatisfied);
								System.out.println("converted smallER value is :"+valueOfsmallReliefChkbox);
								if(valueOfsmallReliefChkbox == 1.0)
								{
									Thread.sleep(4000L);
									if(smallERchekbox)
									{
										System.out.println("Small Employer relief checkbox was allready checked, Hence our condition got satisfied");
										SickSavebutton();
										break  outerloop;
									}
									else
									{
										clkchkbox.sendKeys("");
										clkchkbox.click();
										System.out.println("Small Employer relief checkbox was ALLREADY NOT checked, hence now checked thus Condition now satisfied successfully");
										Thread.sleep(2000L);
										SickSavebutton();
										break  outerloop;
									}

								}	
								
							}
							}
							else if((tdElement.getText()!=null && tdElement.getText().equalsIgnoreCase("Statutory payment conditions")))
							{
								WebElement clkchkbox;
								ckboxQA = "//following-sibling::td[1]/input[(@id='00Nb0000009I7J5')]";
								
								
									System.out.println("this is qa org's locator");
									clkchkbox = driver.findElement(By.xpath(ckboxQA));
								
								Thread.sleep(1000L);
								clkchkbox.sendKeys("");//1
								
								boolean	smallERchekbox = clkchkbox.isSelected();
								if(smallERchekbox)
								{
									System.out.println("yes the condition is checked");
								}
								double valueOfsmallReliefChkbox = Double.parseDouble(ConditionSatisfied);
								System.out.println("converted smallER value is :"+valueOfsmallReliefChkbox);
								if(valueOfsmallReliefChkbox == 1.0)
								{
									Thread.sleep(4000L);
									if(smallERchekbox)
									{
										System.out.println("Small Employer relief checkbox was allready checked, Hence our condition got satisfied");
										SickSavebutton();
										break  outerloop;
									}
									else
									{
										clkchkbox.sendKeys("");//3
										clkchkbox.click();//2
										System.out.println("Small Employer relief checkbox was ALLREADY NOT checked, hence now checked thus Condition now satisfied successfully");
										Thread.sleep(2000L);
										SickSavebutton();
										break  outerloop;
									}

								}	
							}

							col_num++;

						}
						row_num++;

					}

			}
		}
		catch(Throwable t)
		{

		}
	} 




	@DataProvider
	public Object[][] getData() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		return Test_Util.getData(Payroll_Statutory_Paternitypay_SuiteXls,"UpdateLeaveRecord");
	}



	@AfterMethod
	public void ReportDataSetResult() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		if(Skip)
		{
			Test_Util.ReportDataSetResult(Payroll_Statutory_Paternitypay_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
		}else if(Fail)
		{
			IsTestPass = false;
			Test_Util.ReportDataSetResult(Payroll_Statutory_Paternitypay_SuiteXls, this.getClass().getSimpleName(), count+2, "Fail");
		}else
		{
			Test_Util.ReportDataSetResult(Payroll_Statutory_Paternitypay_SuiteXls, this.getClass().getSimpleName(), count+2, "Pass");
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
			Test_Util.ReportDataSetResult(Payroll_Statutory_Paternitypay_SuiteXls, "first", Test_Util.GetRowNum(Payroll_Statutory_Paternitypay_SuiteXls, this.getClass().getSimpleName()),"Pass");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_Statutory_Paternitypay_SuiteXls, "first", Test_Util.GetRowNum(Payroll_Statutory_Paternitypay_SuiteXls, this.getClass().getSimpleName()),"Fail");
		}	
		closeBrowser();
	}

}
