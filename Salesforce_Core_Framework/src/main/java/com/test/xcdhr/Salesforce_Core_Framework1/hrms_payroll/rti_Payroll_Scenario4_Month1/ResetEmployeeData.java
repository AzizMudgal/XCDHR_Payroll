package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.rti_Payroll_Scenario4_Month1;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
	public void CheckTestSkip() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		if(! Test_Util.IsTestcaseRunMode(Payroll_RecognitionScenarioFour_SuiteXls, this.getClass().getSimpleName()))
		{
			Skip=true;
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarioFour_SuiteXls, "first", Test_Util.GetRowNum(Payroll_RecognitionScenarioFour_SuiteXls, this.getClass().getSimpleName()),"Skipped");
			//Test_Util.ReportDataSetResult(Payroll_RecognitionScenarioFour_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase" +this.getClass().getSimpleName() +" as the runmode is set to 'no' ");// this message would display in logs
			throw new Exception("Testcase is being skipped" + this.getClass().getSimpleName()+ "as it's Runmode is set to 'NO'"); // this msg would display in Reports.
		}
		// Load the runmodes of the tests
		runmodes=Test_Util.getDataSetRunmodes(Payroll_RecognitionScenarioFour_SuiteXls, this.getClass().getSimpleName());
	}


	public String payfreqncy;
	boolean employeeFirsttimeView = true;
	boolean AllowanceFirsttimeView = true;
	boolean shouldOpenBrowser = true; 
	boolean MyCompany = true;


	@Test(dataProvider = "getData")
	public void EmpsPayroll_Setup_ForIncomeTax(String EmpName,String FromDate,String AddressFirstLine,String AddressSecondLine,String City,String PostCode,String AddressType,String NICatgory,String FirstNICatgoryChange,String SecondNICatgoryChange) throws Throwable
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
				if(existsElementchkFor1mts(OR.getProperty("Homepage_txt")))
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

		FetchEmployeeRecord(EmpName,FromDate,AddressFirstLine,AddressSecondLine,City,PostCode,AddressType,NICatgory,FirstNICatgoryChange,SecondNICatgoryChange);

		/*************************************************************************/
	}

	

	public void FetchEmployeeRecord(String EmpName,String FromDate,String AddressFirstLine,String AddressSecondLine,String City,String PostCode,String AddressType,String NICatgory,String FirstNICatgoryChange,String SecondNICatgoryChange) throws Throwable
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
					if(existsElementchkFor1mts(firstRowOfEmployeeColumn))
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
			empEmploymentTab(EmpName,FromDate,AddressFirstLine,AddressSecondLine,City,PostCode,AddressType,NICatgory,FirstNICatgoryChange,SecondNICatgoryChange);
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



	public void empEmploymentTab(String EmpName,String FromDate,String AddressFirstLine,String AddressSecondLine,String City,String PostCode,String AddressType,String NICatgory,String FirstNICatgoryChange,String SecondNICatgoryChange)throws Throwable
	{
		try
		{
			if(existsElementchkFor1mts(OR.getProperty("PersonalTabLocator")))
			{
				getObject("PersonalTabLocator").sendKeys("");
				getObject("PersonalTabLocator").click();
				System.out.println("The Personal Tab got clicked");
			}
			
			if(existsElementchkFor1mts(OR.getProperty("currentAddressLink")))
			{
				getObject("currentAddressLink").sendKeys("");
				getObject("currentAddressLink").click();
				System.out.println("The address link got clicked");
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
			if(existsElementchkFor1mts(OR.getProperty("compensationEditButtonLoctor")))
			{
				getObject("compensationEditButtonLoctor").sendKeys("");
				getObject("compensationEditButtonLoctor").click();
				System.out.println("The edit button of employer tab got clicked");
			}
			Thread.sleep(2000L);
			updatePersonalAddress(EmpName,FromDate,AddressFirstLine,AddressSecondLine,City,PostCode,AddressType,NICatgory,FirstNICatgoryChange,SecondNICatgoryChange);

			
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
			ErrorUtil.addVerificationFailure(t);
			System.out.println("");	
		}
	}

	
	public void updatePersonalAddress(String EmpName,String FromDate,String AddressFirstLine,String AddressSecondLine,String City,String PostCode,String AddressType,String NICatgory,String FirstNICatgoryChange,String SecondNICatgoryChange)throws Throwable
	{
		try
		{
     		
			if(existsElementchkFor1mts(OR.getProperty("fromDate")))
			{
				getObject("fromDate").sendKeys("");
				String dateStr = FromDate;
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
				getObject("fromDate").sendKeys(formattedDate);
				System.out.println("");
				System.out.println("The From date was entered sucessfully");	
				Thread.sleep(2000);
				//labelRequired
			}
     		
     		if(existsElementchkFor1mts(OR.getProperty("labelRequired")))
			{
     			getObject("labelRequired").click();
			}
     		Thread.sleep(2000);
			if(existsElementchkFor1mts(OR.getProperty("addressOne")))
			{
				getObject("addressOne").clear();
				getObject("addressOne").sendKeys(AddressFirstLine);
				System.out.println("The Address one was entered sucessfully");	

			}
			
			if(existsElementchkFor1mts(OR.getProperty("addressTwo")))
			{
				getObject("addressTwo").clear();
				getObject("addressTwo").sendKeys(AddressSecondLine);
				System.out.println("The address two was entered sucessfully");	

			}
			
			if(existsElementchkFor1mts(OR.getProperty("PersonalCity")))
			{
				getObject("PersonalCity").clear();
				getObject("PersonalCity").sendKeys(City);
				System.out.println("The personal city was entered sucessfully");	

			}
			
			if(existsElementchkFor1mts(OR.getProperty("PersonalPostcode")))
			{
				getObject("PersonalPostcode").clear();
				getObject("PersonalPostcode").sendKeys(PostCode);
				System.out.println("The post code was entered sucessfully");	

			}
			Thread.sleep(2000);//personalTabSavebtn
			if(existsElementchkFor1mts(OR.getProperty("AddressType")))
			{
				Select selectByValue1 = new Select(driver.findElement(By.xpath(OR.getProperty("AddressType"))));
				selectByValue1.selectByValue(AddressType);
				System.out.println("The address type was entered sucessfully");	
	    	}
			
			Thread.sleep(2000L);
			if(existsElementchkFor1mts(OR.getProperty("personalSave")))
			{
				getObject("personalSave").sendKeys("");
				getObject("personalSave").click();
				System.out.println("The employement save button got clicked");
			}
			uncheckFirstAnd2ndCatgoryChange(NICatgory,FirstNICatgoryChange,SecondNICatgoryChange);
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}
	

	public void saveCompnRecord()throws Throwable
	{
		try
		{
			getObject("personalTabSavebtn").sendKeys("");
			getObject("personalTabSavebtn").click();
			System.out.println("The save button got clicked successfully");
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}
	
	/*
	 * 
	 * need to implement for removing first and 2nd ni category change
	 */
	
	public void uncheckFirstAnd2ndCatgoryChange(String NICatgory,String FirstNICatgoryChange,String SecondNICatgoryChange)throws Throwable
	{
		try
		{
			if(existsElementchkFor1mts(OR.getProperty("PersonalTabLocator")))
			{
				getObject("PersonalTabLocator").sendKeys("");
				getObject("PersonalTabLocator").click();
				System.out.println("The Personal Tab got clicked");
			}
			
			if(existsElementchkFor1mts(OR.getProperty("personalTabEditButon")))
			{
				getObject("personalTabEditButon").sendKeys("");
				getObject("personalTabEditButon").click();
				System.out.println("The edit button of employer tab got clicked");
			}
			
			Thread.sleep(2000);
			if(existsElementchkFor1mts(OR.getProperty("niCategorypicklist")))
			{
				Select selectByValue2 = new Select(driver.findElement(By.xpath(OR.getProperty("niCategorypicklist"))));
				selectByValue2.selectByVisibleText(NICatgory);
				System.out.println("The required ni category sucessfully got selected");	
	    	}
			
			if(existsElementchkFor1mts(OR.getProperty("personalTabSavebtn")))
			{
				getObject("personalTabSavebtn").sendKeys("");
				getObject("personalTabSavebtn").click();
				System.out.println("The save button got clicked successfully");
	    	}
			uncheckFirstAnd2ndCatgoryChange1(NICatgory,FirstNICatgoryChange,SecondNICatgoryChange);
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}
	
	
	public void uncheckFirstAnd2ndCatgoryChange1(String NICatgory,String FirstNICatgoryChange,String SecondNICatgoryChange)throws Throwable
	{
		try
		{
			if(existsElementchkFor1mts(OR.getProperty("PersonalTabLocator")))
			{
				getObject("PersonalTabLocator").sendKeys("");
				getObject("PersonalTabLocator").click();
				System.out.println("The Personal Tab got clicked");
			}
			
			if(existsElementchkFor1mts(OR.getProperty("personalTabEditButon")))
			{
				getObject("personalTabEditButon").sendKeys("");
				getObject("personalTabEditButon").click();
				System.out.println("The edit button of employer tab got clicked");
			}
			System.out.println("I am now in once again edit mode of personal page");
			
			//prviosNICatgoryfiedl
			if(existsElementchkFor1mts(OR.getProperty("prviosNICatgoryfiedl")))
			{
				getObject("prviosNICatgoryfiedl").sendKeys("");
				getObject("prviosNICatgoryfiedl").clear();
				System.out.println("The catgory1 got cleared");
			}
			
			if(existsElementchkFor1mts(OR.getProperty("prviosNICatgoryfied2")))
			{
				getObject("prviosNICatgoryfied2").sendKeys("");
				getObject("prviosNICatgoryfied2").clear();
				System.out.println("The catgory2 got cleared");
			}
			selectNiableCheckbox(FirstNICatgoryChange,SecondNICatgoryChange);
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}
	
	
	
	public void selectNiableCheckbox(String FirstNICatgoryChange, String SecondNICatgoryChange)throws Throwable
	{
		try
		{
			WebElement postsTable = driver.findElement(By.xpath(OR.getProperty("personalNicatchangetable")));
			if(existsWebElement(postsTable))
			{
				System.out.println("details table exists");
				List<WebElement> rows = postsTable.findElements(By.xpath(OR.getProperty("personalNicatchangetableRows")));
				System.out.println("NUMBER OF ROWS IN THIS TABLE = "+rows.size());
				int row_num,col_num;
				row_num=1;
				outerloop:
					for(WebElement trElement : rows)
					{
						List<WebElement> td_collection=trElement.findElements(By.xpath("th"));
						System.out.println("NUMBER OF COLUMNS="+td_collection.size());
						col_num=1;
						for(WebElement tdElement : td_collection)
						{
							System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
							if(tdElement.getText()!=null && tdElement.getText().equalsIgnoreCase("First NI category change"))
							{
								Thread.sleep(4000L);
								System.out.println("Label name  :"+tdElement.getText()+ "  matched ");
								/*
								 * You should provide the tabindex .
								 * this tab index varies from org to org hence 
								 * just change the value of tab indes if you are testing 
								 * QA Org and shifting to Regress Org.
								 */
								WebElement Niablechkbox = driver.findElement(By.xpath("//following-sibling::td[1]/input[@type='checkbox'][@id='j_id0:j_id2:j_id28:j_id59:j_id60:5:j_id61']"));
							//	WebElement Niablechkbox = driver.findElement(By.xpath("//following-sibling::td[1]/input[@type='checkbox'][contains(@id,'j_id0:j_id2')]"));														
								String tabindexval = Niablechkbox.getAttribute("tabindex");														
								System.out.println("tab index is :"+tabindexval);
								boolean	Nblrchkbox = Niablechkbox.isSelected();
								System.out.println("The checkbox selection is :"+Nblrchkbox);
								boolean Nblrchkboxdisplyed = Niablechkbox.isDisplayed();
								System.out.println("The checkbox is displayed :"+Nblrchkboxdisplyed);
								Thread.sleep(4000L);
								boolean smallIsEnabled = Niablechkbox.isEnabled();
								System.out.println("The checkbox is isEnabled :"+smallIsEnabled);

								double valueOfsmallReliefChkbox = Double.parseDouble(FirstNICatgoryChange);
								System.out.println("converted smallER value is :"+valueOfsmallReliefChkbox);
								if(valueOfsmallReliefChkbox== 0.0)
								{
									if(Nblrchkbox)
									{
										Niablechkbox.sendKeys("");
										Niablechkbox.click();
										System.out.println("First NI category change checkbox was allready checked, Now it is unchecked ,Hence our condition got satisfied");
										break  outerloop;
									}
									else
									{
										System.out.println("First NI category change checkbox was ALLREADY unchecked which is correct, Hence Condition now satisfied successfully");
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
			selectTaxableCheckbox1(FirstNICatgoryChange, SecondNICatgoryChange);
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
			System.out.println("");	
		}
	}
	
	
	
	public void selectTaxableCheckbox1(String FirstNICatgoryChange, String SecondNICatgoryChange)throws Throwable
	{
		try
		{
			WebElement postsTable = driver.findElement(By.xpath(OR.getProperty("personalNicatchangetable")));
			if(existsWebElement(postsTable))
			{
				System.out.println("details table exists");
				List<WebElement> rows = postsTable.findElements(By.xpath(OR.getProperty("personalNicatchangetableRows")));
				System.out.println("NUMBER OF ROWS IN THIS TABLE = "+rows.size());
				int row_num,col_num;
				row_num=1;
				outerloop:
					for(WebElement trElement : rows)
					{
						List<WebElement> td_collection=trElement.findElements(By.xpath("th"));
						System.out.println("NUMBER OF COLUMNS="+td_collection.size());
						col_num=1;
						for(WebElement tdElement : td_collection)
						{
							System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
							if(tdElement.getText()!=null && tdElement.getText().equalsIgnoreCase("Second NI category change"))
							{
								Thread.sleep(4000L);
								System.out.println("Label name  :"+tdElement.getText()+ "  matched ");
								/*
								 * You should provide the tabindex .
								 * this tab index varies from org to org hence 
								 * just change the value of tab indes if you are testing 
								 * QA Org and shifting to Regress Org.
								 */
								WebElement Taxblechkbox = driver.findElement(By.xpath("//following-sibling::td[1]/input[@type='checkbox'][@id='j_id0:j_id2:j_id28:j_id59:j_id60:6:j_id61']"));
								String tabindexval = Taxblechkbox.getAttribute("tabindex");													
								System.out.println("tab index is :"+tabindexval);
								boolean	Nblrchkbox = Taxblechkbox.isSelected();
								System.out.println("The checkbox selection is :"+Nblrchkbox);
								boolean Nblrchkboxdisplyed = Taxblechkbox.isDisplayed();
								System.out.println("The checkbox is displayed :"+Nblrchkboxdisplyed);
								Thread.sleep(4000L);

								boolean smallIsEnabled = Taxblechkbox.isEnabled();
								System.out.println("The checkbox is isEnabled :"+smallIsEnabled);

								double valueOfsmallReliefChkbox = Double.parseDouble(SecondNICatgoryChange);
								System.out.println("converted smallER value is :"+valueOfsmallReliefChkbox);
								if(valueOfsmallReliefChkbox== 0.0)
								{
									if(Nblrchkbox)
									{
										Taxblechkbox.sendKeys("");
										Taxblechkbox.click();
										System.out.println("Second NI category change checkbox was allready checked,Now it is unchecked, Hence our condition got satisfied");
										break  outerloop;
									}
									else
									{
										System.out.println("Second NI category change checkbox was allready unchecked which is correct, Hence Condition now satisfied successfully");
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
			saveCompnRecord();
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
			System.out.println("");	
		}
	}
			
	
	@DataProvider
	public Object[][] getData() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		return Test_Util.getData(Payroll_RecognitionScenarioFour_SuiteXls,"ResetEmployeeData");
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
		closeBrowser();
	}
}

