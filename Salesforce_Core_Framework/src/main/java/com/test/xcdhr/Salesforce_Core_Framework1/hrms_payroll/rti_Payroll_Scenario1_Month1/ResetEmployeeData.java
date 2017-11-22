package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.rti_Payroll_Scenario1_Month1;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.ErrorUtil;
import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Test_Util;




public class ResetEmployeeData extends TestSuiteBase
{

	String runmodes[] = null;
	static int count = -1;
	static int countAllowance = -1;

	public static boolean Fail=false;
	public static boolean Skip=false;
	public static boolean IsTestPass=true;
	public String divId;
	public String divId2;
	public String firstxpath;
	public int Row_count;
	public String eiththCellOfBody1;
	public String EmpName;
	public String eSAL;
	public int rownum;
	public int rownum1;
	
	public String AnnualSalary;
	public String EffectiveFrom;
	public String bonusPercentage;
	public String commnPercentage;
	public String bonusOTE;
	public String commissonOTE;
	public String bonusNotes;
	public String emplrcontrbnPensonPercent;
	public String employeecontrbnPensonPercent;
	public String employeeSalarySacrifice;
	public String benfitRecrdType;
	public String Ttype;
	public String EffctvFrm;
	public String EffctvTo;
	public String pymtFrqncyyy;
	public String EmplyrContbn;
	public String RgularPymtAmt;
	public String EmplyeeContbn;
	public String Empcontbn;


	@BeforeTest
	public void CheckTestSkip() throws Throwable{
		processDesiredTaxYearInputExcelFile(TaxYear);

		if(! Test_Util.IsTestcaseRunMode(Payroll_RecognitionScenarious_SuiteXls, this.getClass().getSimpleName())){

			Skip=true;
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarious_SuiteXls, "first", Test_Util.GetRowNum(Payroll_RecognitionScenarious_SuiteXls, this.getClass().getSimpleName()),"Skipped");
			//Test_Util.ReportDataSetResult(Payroll_RecognitionScenarious_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase" +this.getClass().getSimpleName() +" as the runmode is set to 'no' ");// this message would display in logs

			throw new Exception("Testcase is being skipped" + this.getClass().getSimpleName()+ "as it's Runmode is set to 'NO'"); // this msg would display in Reports.

		}

		// Load the runmodes of the tests

		runmodes=Test_Util.getDataSetRunmodes(Payroll_RecognitionScenarious_SuiteXls, this.getClass().getSimpleName());

	}


	public String payfreqncy;
	boolean employeeFirsttimeView = true;
	boolean AllowanceFirsttimeView = true;
	boolean shouldOpenBrowser = true; 
	boolean MyCompany = true;


	@Test(dataProvider = "getData")
	public void EmpsPayroll_Setup_ForIncomeTax(String EmpName,String Payrolid,String Niable,String Taxable) throws Throwable
	{								  //String empFirstName, String LastName,String Email, String UserName, String WorkMobile, String WorkPhone, String Profile, String ActivateLicense, String Post,String Company,String EmploymentType, String Location, String EmploymentStatus, String Department, String PatternType, String NoOfWorkingDays, String ContractualHours, String SpinalPoint, String Manager, String StartDate, String ContinousStdate, String PayrollStDate, String Rejoiner,  String EmpDOB,String Gender,String Nationality,String FromDate,String ToDate,String Address1,String  Address2,String Street,String City,String Country,String PostCode,String Region,String ParentLocation,String AddnalContrctualLeave,String MinimumYrsService,String HoursAM,String HoursPM,String WorkingDays,String AnnualSalary,String Bonus,String BonusOTE,String Commission,String CommissionOTE,String EmpContrbnPenSal,String EmployerContrbPenSal,String AddnalEmplyeeContrbn,String AddnalEmployerContrbn,String bonusNotes,String DailyRateOfPay,String Departmentt,String EmployeeSalarySacrifice,String EmployeeContbnlnLeiu,String Payfrequency,String CreateLeaveYrs
		//APP_LOGS.debug("Entering the Leave parameters");
		//APP_LOGS.debug(EmpName+"--"+NICategory+"--"+AnnualSalary+"--"+PayFrequency);
		processDesiredTaxYearInputExcelFile(TaxYear);
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
				if(existsElement(OR.getProperty("Homepage_txt")))
				{
					//Assert.assertEquals(driver.getTitle(), "salesforce.com - Enterprise Edition");
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

		FetchEmployeeRecord(EmpName,Payrolid,Niable,Taxable);

		/*************************************************************************/
	}

	

	public void FetchEmployeeRecord(String EmpName,String Payrolid,String Niable,String Taxable) throws Throwable
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
			WebElement tableheader = driver.findElement(By.xpath(OR.getProperty("PersonalAndCompensationHeadingTable")));
			List<WebElement> th=tableheader.findElements(By.tagName("td"));
			for(a=0;a<th.size();a++) 
			{
				if("Employee".equalsIgnoreCase(th.get(a).getText()))
				{
					empcolnum = a+1;
					break;
				}
			}
			WebElement postsTable = driver.findElement(By.xpath(OR.getProperty("firstRecordOfTaxCodecoulmnTable")));
			if(existsWebElement(postsTable))
			{
				List<WebElement> rows = postsTable.findElements(By.xpath(OR.getProperty("firstRecordOfTaxCodecoulmnTableRows")));
				java.util.Iterator<WebElement> x = rows.iterator();
				rownum = 1;			
				while(x.hasNext())
				{
					//Thread.sleep(2000L);
					String firstRowOfEmployeeColumn="//div["+rownum+"]/table/tbody/tr/td"+"["+empcolnum+"]"+"/"+"div/a/span";
					if(existsElement(firstRowOfEmployeeColumn))
					{
						WebElement tempElement= driver.findElement(By.xpath(firstRowOfEmployeeColumn));
						String tempEmp= tempElement.getText();
						//System.out.println(tempEmp+"-------"+empName+"------"+rownum);
						if(tempEmp!=null && tempEmp.equalsIgnoreCase(EmpName))
						{
							System.out.println("Employee name  :"+tempEmp+ "  matched ");
							if(existsWebElement(tempElement))
							{
								tempElement.click();
								System.out.println("The employee namely :"+tempEmp+"got clicked");
								break;
							}
						}
						
						rownum++;
					}
				}
			}
			Thread.sleep(3000L);
			empEmploymentTab(EmpName,Payrolid,Niable,Taxable);
			
			//RewardTab(EmpName,Payrolid,Niable,Taxable);
		}
		catch(Throwable t)
		{
			APP_LOGS.debug(" Check for error in NI Category method");
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
			ErrorUtil.addVerificationFailure(t);
			System.out.println("");
		}
	}



	public void empEmploymentTab(String EmpName,String Payrolid,String Niable,String Taxable)throws Throwable
	{
		try
		{
			if(existsElement(OR.getProperty("EmploymentTabLocator")))
			{
				getObject("EmploymentTabLocator").sendKeys("");
				getObject("EmploymentTabLocator").click();
				System.out.println("The Employment Tab got clicked");
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
			ErrorUtil.addVerificationFailure(t);
			System.out.println("");	
		}

		try
		{
			Thread.sleep(2000L);
			if(existsElement(OR.getProperty("emplymentTabEditBtn")))
			{
				getObject("emplymentTabEditBtn").sendKeys("");
				getObject("emplymentTabEditBtn").click();
				System.out.println("The edit button of employer tab got clicked");
			}
			Thread.sleep(2000L);
			if(existsElement(OR.getProperty("empPayrollNumber")))
			{
				//getObject("empPayrollNumber").clear();
				//Thread.sleep(2000L);
				getObject("empPayrollNumber").sendKeys(Payrolid);
				System.out.println("The payroll id got updated");
			}
			Thread.sleep(2000L);
			if(existsElement(OR.getProperty("emplymntSaveButn")))
			{
				getObject("emplymntSaveButn").sendKeys("");
				getObject("emplymntSaveButn").click();
				System.out.println("The employement save button got clicked");
			}
			RewardTab(EmpName,Payrolid,Niable,Taxable);
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
			ErrorUtil.addVerificationFailure(t);
			System.out.println("");	
		}
	}


	public void RewardTab(String EmpName,String Payrolid,String Niable,String Taxable)throws Throwable
	{
		try
		{
			if(existsElementchkFor1mts(OR.getProperty("rewardtabClk")))
			{
				getObject("rewardtabClk").sendKeys("");
				getObject("rewardtabClk").click();
				if(existsElement(OR.getProperty("compensationTableLocator")))
				{
					compensationLink(Niable, Taxable);
				}
				
				if(existsElementchkFor1mts(OR.getProperty("compensationEditButtonLoctor")))
				{
					getObject("compensationEditButtonLoctor").sendKeys("");
					getObject("compensationEditButtonLoctor").click();
					Thread.sleep(5000L);
				}
			
				if(existsElementchkFor1mts(OR.getProperty("compnNiableTaxableTable")))
				{
					selectNiableCheckbox(Niable, Taxable);
				}
				
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}


	
	
	public void compensationLink(String Niable, String Taxable)throws Throwable
	{
		try
		{
			WebElement postsTable = driver.findElement(By.xpath(OR.getProperty("compensationTableLocator")));
			if(existsWebElement(postsTable))
			{
				System.out.println("compensation record table exists");
				List<WebElement> rows = postsTable.findElements(By.xpath(OR.getProperty("compensationTableRowsLocator")));
				System.out.println("NUMBER OF ROWS IN THIS TABLE = "+rows.size());
				WebElement compensationLink = driver.findElement(By.xpath("//div[2]/table[contains(@id, 'j_id0:rewardHelp:CompensationBlock')]/tbody/tr/td[1]/a"));
				compensationLink.sendKeys("");
				compensationLink.click();
				System.out.println("The compensation record got clicked");
			}
			selectNiableCheckbox(Niable,Taxable);
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
			System.out.println("");	
		}
	}
	
	
	/*
	 * This method will only be implemented when 
	 * told to implement by Imran. becuase "Final compensation"
	 * is being checked by trigger / work flow itseems.
	 * So in editmode this field is not being seen.
	 * 
	 */
	public void selectFinalCompenCheckbox(String FinalCompensation,String Niable, String Taxable)throws Throwable
	{
		try
		{
			WebElement postsTable = driver.findElement(By.xpath(OR.getProperty("compnNiableTaxableTable")));
			if(existsWebElement(postsTable))
			{
				System.out.println("details table exists");
				List<WebElement> rows = postsTable.findElements(By.xpath(OR.getProperty("compnNiableTaxableTableRows")));
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
							if(tdElement.getText()!=null && tdElement.getText().equalsIgnoreCase("Final compensation"))
							{
								Thread.sleep(4000L);
								System.out.println("Label name  :"+tdElement.getText()+ "  matched ");
								/*
								 * You should provide the tabindex .
								 * this tab index varies from org to org hence 
								 * just change the value of tab indes if you are testing 
								 * QA Org and shifting to Regress Org.
								 */
								WebElement Niablechkbox = driver.findElement(By.xpath("//following-sibling::td[1]/input[@type='checkbox'][@id='00Nb0000009I798']"));
								String tabindexval = Niablechkbox.getAttribute("tabindex");
								System.out.println("tab index is :"+tabindexval);
								boolean	Nblrchkbox = Niablechkbox.isSelected();
								System.out.println("The checkbox selection is :"+Nblrchkbox);
								boolean Nblrchkboxdisplyed = Niablechkbox.isDisplayed();
								System.out.println("The checkbox is displayed :"+Nblrchkboxdisplyed);
								Thread.sleep(4000L);

								boolean smallIsEnabled = Niablechkbox.isEnabled();
								System.out.println("The checkbox is isEnabled :"+smallIsEnabled);

								double valueOfsmallReliefChkbox = Double.parseDouble(Niable);
								System.out.println("converted smallER value is :"+valueOfsmallReliefChkbox);
								if(valueOfsmallReliefChkbox== 1.0)
								{
									if(Nblrchkbox)
									{
										
										System.out.println("Niable checkbox was allready checked, Hence our condition got satisfied");
										break  outerloop;
									}
									else
									{
										Niablechkbox.sendKeys("");
										Niablechkbox.click();
										System.out.println("Niable checkbox was not checked previously.Now it is correct, Hence Condition now satisfied successfully");
										break  outerloop;
									}
								}
							}
							col_num++;
						}
						row_num++;
					} 
			}
			Thread.sleep(2000L);
			selectTaxableCheckbox(Niable, Taxable);
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
			System.out.println("");	
		}
	}
	
	
	
	
	public void selectNiableCheckbox(String Niable, String Taxable)throws Throwable
	{
		try
		{
			WebElement postsTable = driver.findElement(By.xpath(OR.getProperty("compnNiableTaxableTable")));
			if(existsWebElement(postsTable))
			{
				System.out.println("details table exists");
				List<WebElement> rows = postsTable.findElements(By.xpath(OR.getProperty("compnNiableTaxableTableRows")));
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
							if(tdElement.getText()!=null && tdElement.getText().equalsIgnoreCase("NI-able"))
							{
								Thread.sleep(4000L);
								System.out.println("Label name  :"+tdElement.getText()+ "  matched ");
								/*
								 * You should provide the tabindex .
								 * this tab index varies from org to org hence 
								 * just change the value of tab indes if you are testing 
								 * QA Org and shifting to Regress Org.
								 */
								WebElement Niablechkbox = driver.findElement(By.xpath("//following-sibling::td[1]/input[@type='checkbox'][@id='00Nb0000009I798']"));
								String tabindexval = Niablechkbox.getAttribute("tabindex");
								System.out.println("tab index is :"+tabindexval);
								boolean	Nblrchkbox = Niablechkbox.isSelected();
								System.out.println("The checkbox selection is :"+Nblrchkbox);
								boolean Nblrchkboxdisplyed = Niablechkbox.isDisplayed();
								System.out.println("The checkbox is displayed :"+Nblrchkboxdisplyed);
								Thread.sleep(4000L);

								boolean smallIsEnabled = Niablechkbox.isEnabled();
								System.out.println("The checkbox is isEnabled :"+smallIsEnabled);

								double valueOfsmallReliefChkbox = Double.parseDouble(Niable);
								System.out.println("converted smallER value is :"+valueOfsmallReliefChkbox);
								if(valueOfsmallReliefChkbox== 1.0)
								{
									if(Nblrchkbox)
									{
										System.out.println("Niable checkbox was allready checked which is correct, Hence Condition now satisfied successfully");
	
										break  outerloop;
									}
									else
									{
										Niablechkbox.sendKeys("");
										Niablechkbox.click();
										System.out.println("Niable checkbox was now checked, Hence our condition got satisfied");
										break  outerloop;
									}
								}
							}
							col_num++;
						}
						row_num++;
					} 
			}
			Thread.sleep(2000L);
			selectTaxableCheckbox(Niable, Taxable);
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
			System.out.println("");	
		}
	}
	
	
	
	public void selectTaxableCheckbox(String Niable, String Taxable)throws Throwable
	{
		try
		{
			WebElement postsTable = driver.findElement(By.xpath(OR.getProperty("compnNiableTaxableTable")));
			if(existsWebElement(postsTable))
			{
				System.out.println("details table exists");
				List<WebElement> rows = postsTable.findElements(By.xpath(OR.getProperty("compnNiableTaxableTableRows")));
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
							if(tdElement.getText()!=null && tdElement.getText().equalsIgnoreCase("Taxable"))
							{
								Thread.sleep(4000L);
								System.out.println("Label name  :"+tdElement.getText()+ "  matched ");
								/*
								 * You should provide the tabindex .
								 * this tab index varies from org to org hence 
								 * just change the value of tab indes if you are testing 
								 * QA Org and shifting to Regress Org.
								 */
								WebElement Taxblechkbox = driver.findElement(By.xpath("//following-sibling::td[1]/input[@type='checkbox'][@id='00Nb0000009I79g']"));
								String tabindexval = Taxblechkbox.getAttribute("tabindex");
								System.out.println("tab index is :"+tabindexval);
								boolean	Nblrchkbox = Taxblechkbox.isSelected();
								System.out.println("The checkbox selection is :"+Nblrchkbox);
								boolean Nblrchkboxdisplyed = Taxblechkbox.isDisplayed();
								System.out.println("The checkbox is displayed :"+Nblrchkboxdisplyed);
								Thread.sleep(4000L);

								boolean smallIsEnabled = Taxblechkbox.isEnabled();
								System.out.println("The checkbox is isEnabled :"+smallIsEnabled);

								double valueOfsmallReliefChkbox = Double.parseDouble(Taxable);
								System.out.println("converted smallER value is :"+valueOfsmallReliefChkbox);
								if(valueOfsmallReliefChkbox== 1.0)
								{
									if(Nblrchkbox)
									{
										System.out.println("Taxable checkbox was allready checked which is correct, Hence Condition now satisfied successfully");
										break  outerloop;
									}
									else
									{
										Taxblechkbox.sendKeys("");
										Taxblechkbox.click();
										System.out.println("Taxable checkbox was now checked, Hence our condition got satisfied");
										break  outerloop;
									}
								}
							}
							col_num++;
						}
						row_num++;
					} 
			}
			if(existsElementchkFor1mts(OR.getProperty("SERsaveButtnCheckboxlocator")))
			{
				saveCompnRecord();
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
			System.out.println("");	
		}
	}
	
	
	
	

	public void saveCompnRecord()throws Throwable
	{
		try
		{
			getObject("SERsaveButtnCheckboxlocator").sendKeys("");
			getObject("SERsaveButtnCheckboxlocator").click();
			System.out.println("The save button got clicked successfully");
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
		return Test_Util.getData(Payroll_RecognitionScenarious_SuiteXls,"ResetEmployeeData");
	}



	@AfterMethod
	public void ReportDataSetResult() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		if(Skip)
		{
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarious_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
		}
		else if(Fail)
		{
			IsTestPass = false;
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarious_SuiteXls, this.getClass().getSimpleName(), count+2, "Fail");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarious_SuiteXls, this.getClass().getSimpleName(), count+2, "Pass");
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
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarious_SuiteXls, "first", Test_Util.GetRowNum(Payroll_RecognitionScenarious_SuiteXls, this.getClass().getSimpleName()),"Pass");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarious_SuiteXls, "first", Test_Util.GetRowNum(Payroll_RecognitionScenarious_SuiteXls, this.getClass().getSimpleName()),"Fail");
		}	
		closeBrowser();
	}
}

