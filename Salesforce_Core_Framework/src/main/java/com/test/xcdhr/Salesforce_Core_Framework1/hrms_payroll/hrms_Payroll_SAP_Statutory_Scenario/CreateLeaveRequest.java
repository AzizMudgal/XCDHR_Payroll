package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.hrms_Payroll_SAP_Statutory_Scenario;


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
import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Test_Util;


public class CreateLeaveRequest extends TestSuiteBase
{
	String runmodes[] = null;
	static int count = -1;
	static int countCompensation = -1;

	public static boolean Fail=false;
	public static boolean Skip=false;
	public static boolean IsTestPass=true;
	public String datefield;
	public String datefield1;
	public String datefield2;
	public String inputdateone;
	public String inputdatetwo;
	public String ckbox;
	public String SAPMatchingDate;
	public String formattedDate;


	@BeforeTest
	public void CheckTestSkip() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);

		if(! Test_Util.IsTestcaseRunMode(Payroll_Statutory_Adoption_SuiteXls, this.getClass().getSimpleName())){
			Skip=true;
			Test_Util.ReportDataSetResult(Payroll_Statutory_Adoption_SuiteXls, "first", Test_Util.GetRowNum(Payroll_Statutory_Adoption_SuiteXls, this.getClass().getSimpleName()),"Skipped");
			//Test_Util.ReportDataSetResult(Payroll_Statutory_Adoption_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase" +this.getClass().getSimpleName() +" as the runmode is set to 'no' ");// this message would display in logs
			throw new Exception("Testcase is being skipped" + this.getClass().getSimpleName()+ "as it's Runmode is set to 'NO'"); // this msg would display in Reports.
		}
		// Load the runmodes of the tests
		runmodes=Test_Util.getDataSetRunmodes(Payroll_Statutory_Adoption_SuiteXls, this.getClass().getSimpleName());
	}

	public String payfreqncy;
	boolean employeeFirsttimeView = true;
	boolean compensationFirsttimeView = true;
	boolean shouldOpenBrowser = true; 


	@Test(dataProvider = "getData")
	public void EmpsSetup_WithNICategory(String EmpName,String LeaveYear,String LeaveCategry,String MatchingDate, String ExpectedplacementDate, String LeaveStDate,String LeaveEndDate,String StatutoryPaybasis,String Conditionsatisfd,String employeeTaxable,String employeeNiable,String includeInHolidayEarnings) throws Throwable
	{
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
				closePopupWindow();
				if(existsElementchkFor1mts(OR.getProperty("PersonalTab")))
				{
					String personalTab = getObject("PersonalTab").getText();
					System.out.println("Tab name is :"+ personalTab);
					Assert.assertEquals("Personal", personalTab);
					System.out.println("The test script verified that it successfully logged into XCD HR Org.");
					System.out.println("");
				}
			}catch(Throwable t)
			{
				System.out.println(t.getMessage().toString());

			}
		}
		/*************************************************************************/

		FetchEmployeeRecord(EmpName,LeaveYear,LeaveCategry,MatchingDate,ExpectedplacementDate,LeaveStDate,LeaveEndDate,StatutoryPaybasis,Conditionsatisfd,employeeTaxable,employeeNiable,includeInHolidayEarnings);

		/*************************************************************************/
	}


	public void FetchEmployeeRecord(String EmpName,String LeaveYear,String LeaveCategory,String MatchingDate, String ExpectedplacementDate, String LeaveStDate,String LeaveEndDate,String StatutoryPaybasis,String Conditionsatisfd,String employeeTaxable,String employeeNiable,String includeInHolidayEarnings) throws Throwable
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
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
			System.out.println("");
		}
		Thread.sleep(3000L);
		LeaveTab(LeaveYear,LeaveCategory,MatchingDate,ExpectedplacementDate,LeaveStDate,LeaveEndDate,StatutoryPaybasis,Conditionsatisfd,employeeTaxable,employeeNiable,includeInHolidayEarnings);
	}


	public void LeaveTab(String LeaveYear,String LeaveCategory,String MatchingDate, String ExpectedplacementDate, String LeaveStDate,String LeaveEndDate,String StatutoryPaybasis,String Conditionsatisfd,String employeeTaxable,String employeeNiable,String includeInHolidayEarnings)throws Throwable
	{
		try
		{
			if(existsElement(OR.getProperty("leaveTabclk")))
			{
				getObject("leaveTabclk").sendKeys("");
				getObject("leaveTabclk").click();
				System.out.println("The leave tab got clicked");
				Thread.sleep(3000L);
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
		selectLeaveYear(LeaveYear,LeaveCategory,MatchingDate,ExpectedplacementDate,LeaveStDate,LeaveEndDate,StatutoryPaybasis,Conditionsatisfd,employeeTaxable,employeeNiable,includeInHolidayEarnings);
	}


	public void selectLeaveYear(String LeaveYear,String LeaveCategory,String MatchingDate, String ExpectedplacementDate, String LeaveStDate,String LeaveEndDate,String StatutoryPaybasis,String Conditionsatisfd,String employeeTaxable,String employeeNiable,String includeInHolidayEarnings)throws Throwable
	{
		try
		{
			Thread.sleep(3000L);
			bookLeave(LeaveYear,LeaveCategory,MatchingDate,ExpectedplacementDate,LeaveStDate,LeaveEndDate,StatutoryPaybasis,Conditionsatisfd,employeeTaxable,employeeNiable,includeInHolidayEarnings);
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}


	public void bookLeave(String LeaveYear,String LeaveCategory,String MatchingDate, String ExpectedplacementDate, String LeaveStDate,String LeaveEndDate,String StatutoryPaybasis,String ConditionSatisfied,String employeeTaxable,String employeeNiable,String includeInHolidayEarnings)throws Throwable
	{
		try
		{
			if(existsElement(OR.getProperty("bookLeavebuttonlocator")))
			{
				getObject("bookLeavebuttonlocator").sendKeys("");
				getObject("bookLeavebuttonlocator").click();
			}
		}
		catch(Throwable t)
		{
			System.out.println("Book leave button did not got clicked");
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
		Thread.sleep(3000L);
		selectMaternityLeave(LeaveYear,LeaveCategory,MatchingDate,ExpectedplacementDate,LeaveStDate,LeaveEndDate,StatutoryPaybasis,ConditionSatisfied,employeeTaxable,employeeNiable,includeInHolidayEarnings);
	}


	public void selectMaternityLeave(String LeaveYear,String LeaveCategory,String MatchingDate, String ExpectedplacementDate, String LeaveStDate,String LeaveEndDate,String StatutoryPaybasis,String ConditionSatisfied,String employeeTaxable,String employeeNiable,String includeInHolidayEarnings)throws Throwable
	{
		try
		{
			if(existsElement(OR.getProperty("leaveCategorypicklistlocator")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("leaveCategorypicklistlocator"))));
				// This select by value needs to be called from OR.Properties
				//selectByValue.selectByValue(LeaveCategory);
				selectByValue.selectByVisibleText(LeaveCategory);
				System.out.println("");
				System.out.println("The MATERNITY PICK LIST ITEM got selected sucessfully");
				Thread.sleep(3000L);
				if(existsElementchkFor1mts(OR.getProperty("submitLeaverqstlocator")))
				{
					submitSickleave();
					System.out.println("Hence the sick leave got created sucessfully");
				}
			}
		}
		catch(Throwable t)
		{
			System.out.println("Book leave did not happened");
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
		enterLeaveDates(MatchingDate,ExpectedplacementDate,LeaveStDate,LeaveEndDate,StatutoryPaybasis,ConditionSatisfied,employeeTaxable,employeeNiable,includeInHolidayEarnings);
	}


	public void enterLeaveDates(String MatchingDate, String ExpectedplacementDate, String LeaveStDate,String LeaveEndDate,String StatutoryPaybasis,String ConditionSatisfied,String employeeTaxable,String employeeNiable,String includeInHolidayEarnings)throws Throwable
	{
		try
		{
			if(existsElement(OR.getProperty("materinityEditbuttonlocator")))
			{
				getObject("materinityEditbuttonlocator").sendKeys("");
				getObject("materinityEditbuttonlocator").click();
			}
			Thread.sleep(4000L);
			enterKeyDates(MatchingDate,ExpectedplacementDate,LeaveStDate,LeaveEndDate);
			Thread.sleep(1000L);
			selectStatutoryPayAndCondnSatisfy(StatutoryPaybasis,ConditionSatisfied);
			Thread.sleep(1000L);
			updateFinancialControlFeatures(employeeTaxable,employeeNiable);
			Thread.sleep(1000L);
			MaternitySavebutton();
		}
		catch(Throwable t)
		{
			System.out.println("Edit button did not got clicked");
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}


	public void updateFinancialControlFeatures(String employeeTaxable,String employeeNiable)throws Throwable
	{
		try
		{
			Thread.sleep(1000L);
			boolean	empTaxableChekbox = getObject("employeeTaxablecheckboxLocator").isSelected();
			boolean	empNiableChekbox = getObject("employeeNiablecheckboxLocator").isSelected();

			double valueOfemployeeTaxableChkbox = Double.parseDouble(employeeTaxable);
			System.out.println("converted smallER value is :"+valueOfemployeeTaxableChkbox);

			double valueOfemployeeNiableChkbox = Double.parseDouble(employeeNiable);
			System.out.println("converted smallER value is :"+valueOfemployeeNiableChkbox);

			if(valueOfemployeeTaxableChkbox == 1.0)
			{
				if(existsElement(OR.getProperty("employeeTaxablecheckboxLocator")))
				{
					isemployeeTaxablechecBox(empTaxableChekbox);
				}
				else if(existsElement(OR.getProperty("RegressOrgemployeeTaxablecheckboxLocator")))
				{
					RegressOrgisemployeeTaxablechecBox(empTaxableChekbox);
				}
			}

			if(valueOfemployeeNiableChkbox == 1.0)
			{
				if(existsElement(OR.getProperty("employeeNiablecheckboxLocator")))
				{
					isemployeeNiablechecBox(empNiableChekbox);
				}
				else if(existsElement(OR.getProperty("RegressOrgemployeeNiablecheckboxLocator")))
				{
					RegressOrgisemployeeNiablechecBox(empNiableChekbox);
				}
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}


	public void ReadStatutoryPayBasis(String StatutoryPaybasis)throws Throwable
	{

		String[] handles = driver.getWindowHandles().toArray(new String[0]); // To get the child window(s)
		driver.switchTo().window(handles[handles.length - 1]); 
		String axb=  driver.getTitle();
		System.out.println(axb);
		if (driver.getTitle().equalsIgnoreCase(axb))
		{
			WebElement dddframe1 = driver.findElement(By.id("searchFrame"));  // you encountered two frames so, find the frame id and save as webelement
			driver.switchTo().frame(dddframe1); // now using the frame id and switch to the frame
			System.out.println("title is matching");
			System.out.println("I am now in the child window");
			Thread.sleep(3000);
			getObject("searchField").clear();
			Thread.sleep(1000);
			getObject("searchField").sendKeys(StatutoryPaybasis);
			System.out.println("I entered the statutory pay basis reading from excel sheet");
			Thread.sleep(1000);
			getObject("Gobutton").click();
			System.out.println("I clicked Go button");
			Thread.sleep(3000);
			driver.switchTo().defaultContent();        // now that you encountered one more frame hence switch back to main page
			WebElement dddframe2 = driver.findElement(By.id("resultsFrame"));// and save the frame id and 
			driver.switchTo().frame(dddframe2); // switch to the other frame and perform the operations
			System.out.println("I switched to Results Frame");
			Thread.sleep(2000L);
			if(existsElement(OR.getProperty("clkSortedone1")))
			{
				getObject("clkSortedone1").click();
			}
			else if(existsElement(OR.getProperty("clkSortedone2")))
			{
				getObject("clkSortedone2").click();
			}
			System.out.println("I clicked the user finally");
		}
	}

	

	public void MaternitySavebutton()throws Throwable
	{
		try
		{
			if(existsElement(OR.getProperty("maternitySavelocator")))
			{
				getObject("maternitySavelocator").sendKeys("");
				getObject("maternitySavelocator").click();
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

	

	public void enterKeyDates(String MatchingDate,String ExpectedplacementDate,String LeaveStDate,String LeaveEndDate)throws Throwable
	{
		try
		{
			Thread.sleep(1000L);
			if(existsElement(OR.getProperty("SAPmatchingDate")))
			{
				System.out.println("matching date got recognised");
				getObject("SAPmatchingDate").sendKeys("");
				String dateStr = MatchingDate;
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

				formattedDate = null;
				if( date != null ) 
				{
					formattedDate = writeFormat.format( date );
				}

				getObject("SAPmatchingDate").sendKeys(formattedDate);
				System.out.println("");
				System.out.println("The Matching date was entered sucessfully");	
			}
			else if(existsElement(OR.getProperty("RegressOrgSAPmatchingDate")))
			{
				getObject("RegressOrgSAPmatchingDate").sendKeys("");
				String dateStr = MatchingDate;
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

				formattedDate = null;
				if( date != null ) 
				{
					formattedDate = writeFormat.format( date );
				}
				System.out.println("");
				getObject("RegressOrgSAPmatchingDate").sendKeys(formattedDate);
				System.out.println("");
				System.out.println("The Matching date was entered sucessfully");	
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}

		try
		{
			Thread.sleep(1000L);
			if(existsElement(OR.getProperty("SAPPlacementDate")))
			{
				getObject("SAPPlacementDate").sendKeys("");
				String dateStr = ExpectedplacementDate;
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

				formattedDate = null;
				if( date != null ) 
				{
					formattedDate = writeFormat.format( date );
				}
				getObject("SAPPlacementDate").sendKeys(formattedDate);

				System.out.println("The Placement date was entered sucessfully");	
			}
			else if(existsElement(OR.getProperty("RegressOrgSAPPlacementDate")))
			{
				getObject("RegressOrgSAPPlacementDate").sendKeys("");
				String dateStr = ExpectedplacementDate;
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

				formattedDate = null;
				if( date != null ) 
				{
					formattedDate = writeFormat.format( date );
				}
				getObject("RegressOrgSAPPlacementDate").sendKeys(formattedDate);
				System.out.println("The Placement date was entered sucessfully");	
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}


		try
		{
			Thread.sleep(1000L);

			if(existsElement(OR.getProperty("SAPstDate")))
			{
				getObject("SAPstDate").sendKeys("");
				String dateStr = LeaveStDate;
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

				formattedDate = null;
				if( date != null ) 
				{
					formattedDate = writeFormat.format( date );
				}
				getObject("SAPstDate").sendKeys(formattedDate);
				System.out.println("");
				System.out.println("The Start date was entered sucessfully");	
			}
			else if(existsElement(OR.getProperty("RegressOrgSAPstDate")))
			{
				getObject("RegressOrgSAPstDate").sendKeys("");
				String dateStr = LeaveStDate;
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

				formattedDate = null;
				if( date != null ) 
				{
					formattedDate = writeFormat.format( date );
				}
				getObject("RegressOrgSAPstDate").sendKeys(formattedDate);
				System.out.println("");
				System.out.println("The start date was entered sucessfully");	
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}


		try
		{
			Thread.sleep(1000L);

			if(existsElement(OR.getProperty("SAPendDate")))
			{
				getObject("SAPendDate").sendKeys("");
				String dateStr = LeaveEndDate;
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

				formattedDate = null;
				if( date != null ) 
				{
					formattedDate = writeFormat.format( date );
				}
				getObject("SAPendDate").sendKeys(formattedDate);
				System.out.println("");
				System.out.println("The end date was entered sucessfully");	
			}
			else if(existsElement(OR.getProperty("RegressOrgSAPendDate")))
			{
				getObject("RegressOrgSAPendDate").sendKeys("");
				String dateStr = LeaveEndDate;
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
				formattedDate = null;
				if( date != null ) 
				{
					formattedDate = writeFormat.format( date );
				}
				getObject("RegressOrgSAPendDate").sendKeys(formattedDate);
				System.out.println("");
				System.out.println("The end date was entered sucessfully");	
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}



	public void selectStatutoryPayAndCondnSatisfy(String StatutoryPaybasis,String ConditionSatisfied)throws Throwable
	{
		try
		{
			Thread.sleep(4000L);
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


							if(tdElement.getText()!=null && tdElement.getText().equalsIgnoreCase("Statutory conditions met - make payment")||(tdElement.getText()!=null && tdElement.getText().equalsIgnoreCase("Statutory payment conditions")))
							{
								System.out.println("Label name  :"+tdElement.getText()+ "  matched ");


								ckbox ="//following-sibling::td[1]/input[@type='checkbox']";
								WebElement clkchkbox = driver.findElement(By.xpath(ckbox));
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
										break  outerloop;
									}
									else
									{
										clkchkbox.sendKeys("");
										clkchkbox.click();
										System.out.println("Small Employer relief checkbox was NOT checked,and now checked hence Condition now satisfied successfully");

									}

								}	
							}

							if(tdElement.getText()!=null && tdElement.getText().equalsIgnoreCase("Payment basis")||(tdElement.getText()!=null && tdElement.getText().equalsIgnoreCase("Statutory pay basis")))
							{
								System.out.println("Label name  :"+tdElement.getText()+ "  matched ");
								String imglookup ="//following-sibling::td[1]/span/a/img";
								WebElement clkchkbox = driver.findElement(By.xpath(imglookup));
								clkchkbox.sendKeys("");
								clkchkbox.click();
								System.out.println("I clicked Go button");
								Thread.sleep(5000);
								String ParentWindow = driver.getWindowHandle(); // To save the parent window
								// create one more method for reading employee from excel sheet.
								ReadStatutoryPayBasis(StatutoryPaybasis);
								Thread.sleep(2000L);
								driver.switchTo().window(ParentWindow); // finally switch back to parent window and perform the operations.
								Thread.sleep(2000L);
								break  outerloop;
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



	public void toFormatDate(String passedDate)throws Throwable
	{
		String dateStr = passedDate;
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

		formattedDate = null;
		if( date != null ) 
		{
			formattedDate = writeFormat.format( date );
		}
		System.out.println("The entered date is  " +formattedDate);		
	}



	@DataProvider
	public Object[][] getData() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		return Test_Util.getData(Payroll_Statutory_Adoption_SuiteXls,"CreateLeaveRequest");
	}



	@AfterMethod
	public void ReportDataSetResult() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);

		if(Skip)
		{
			Test_Util.ReportDataSetResult(Payroll_Statutory_Adoption_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
		}
		else if(Fail)
		{
			IsTestPass = false;
			Test_Util.ReportDataSetResult(Payroll_Statutory_Adoption_SuiteXls, this.getClass().getSimpleName(), count+2, "Fail");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_Statutory_Adoption_SuiteXls, this.getClass().getSimpleName(), count+2, "Pass");
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
			Test_Util.ReportDataSetResult(Payroll_Statutory_Adoption_SuiteXls, "first", Test_Util.GetRowNum(Payroll_Statutory_Adoption_SuiteXls, this.getClass().getSimpleName()),"Pass");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_Statutory_Adoption_SuiteXls, "first", Test_Util.GetRowNum(Payroll_Statutory_Adoption_SuiteXls, this.getClass().getSimpleName()),"Fail");
		}	
		closeBrowser();
	}

}
