package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.Rti_EmployeeCreation;

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



public class CreateNewRTIEmployees extends TestSuiteBase
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
	public boolean companyFirsttimeView = true;

	@BeforeTest
	public void CheckTestSkip() throws Exception
	{
		if(! Test_Util.IsTestcaseRunMode(Payroll_CreateEmployees_For_RecognitionScenarious_SuiteXls, this.getClass().getSimpleName()))
		{
			Skip=true;
			Test_Util.ReportDataSetResult(Payroll_CreateEmployees_For_RecognitionScenarious_SuiteXls, "first", Test_Util.GetRowNum(Payroll_CreateEmployees_For_RecognitionScenarious_SuiteXls, this.getClass().getSimpleName()),"Skipped");
			//Test_Util.ReportDataSetResult(Payroll_CreateEmployees_For_RecognitionScenarious_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase" +this.getClass().getSimpleName() +" as the runmode is set to 'no' ");// this message would display in logs
			throw new Exception("Testcase is being skipped" + this.getClass().getSimpleName()+ "as it's Runmode is set to 'NO'"); // this msg would display in Reports.
		}
		// Load the runmodes of the tests
		runmodes=Test_Util.getDataSetRunmodes(Payroll_CreateEmployees_For_RecognitionScenarious_SuiteXls, this.getClass().getSimpleName());
	}

	public String payfreqncy;
	boolean employeeFirsttimeView = true;
	boolean AllowanceFirsttimeView = true;
	boolean shouldOpenBrowser = true; 
	boolean MyCompany = true;


	@Test(dataProvider="getData")
	public void EmpsSetup_WithNICategory(String CompanyName,String Title,String FirstName, String LastName,String Email, String UserName, String Profile, String ActivateLicense, String JobTitle,String EmploymentType, String EmploymentStatus,String PatternType,String NoOfWorkingDays, String ContractualHours, String Manager, String StartDate, String ContinousStDate, String KnownName, String DOB,String FromDate,String AddressLine1,String AddressLine2,String City,String UKPostCode,String Country,String AddressType,String PayrollId, String Gender, String RegularPay, String Period, String PayrollEligibility, String PayrollFrequency,String TaxCode,String TaxBasis,String StudentLoan,String NICategory,String EffectiveFrom,String StudentLoanPlan,String PayinStartPeriod,String NINO,String StartDeclaration,String ProcessLeaverNeeded, String DateOfNoticeOfTermination,String LeavingDate,String LastWorkingDate,String ReasonForLeaving,String LastPayrunDate) throws Throwable
	{	                                      //CompanyName	Title	FirstName	LastName	Email	UserName	Profile	ActivateLicense	                                                 JobTitle	      EmploymentType	Emplyment Status	             PatternType	NoOffWorkingDays	ContractualHours	Manager	StartDate	                      ContinousStDate	KnownName	DOB	FromDate	                        Address line1	Address line2	       City	UKPostcode	Country	AddressType	PayrollId	                                 Gender	RegularPay	                Period	         PayrollEligibility	PayrollFrequency	         TaxCode	TaxBasis	        StudentLoan	NICategory	              EffectiveFrom	StudentLoanPlan	             PayinStartPeriod	NINO	          StartDeclaration	ProcessLeaverNeeded	DateOfNoticeOfTermination	                  LeavingDate	LastWorkingDate	ReasonForLeaving	LastPayrunDate
 
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
				Thread.sleep(5000L);
				if(existsElementchkFor1mts(OR.getProperty("popupwindowAfterLoginSuccess")))
				{
					String oldWindow = driver.getWindowHandle();
					driver.switchTo().window(driver.getWindowHandle());
					getObject("popupwindowAfterLoginSuccess").click();
					System.out.println("The Popup window got closed");
					driver.switchTo().window(oldWindow);
				}
				else
				{
					System.out.println("The Popwindow does not exist in this Org");
				}

				if(existsElementchkFor1mts(OR.getProperty("PersonalTab")))
				{
					String personalTab = getObject("PersonalTab").getText();
					System.out.println("Tab name is :"+ personalTab);
					Assert.assertEquals("Personal", personalTab);
					System.out.println("The test script verified that it successfully logged into XCD HR Org.");
					System.out.println("");
				}
				//RTICore rtic = new RTICore();
				//rtic.FetchCompanyRecord(CompanyName,Title,FirstName,LastName,Email,UserName,Profile,ActivateLicense,JobTitle,EmploymentType,EmploymentStatus,PatternType,NoOfWorkingDays,ContractualHours,Manager,StartDate,ContinousStDate,KnownName,DOB,FromDate,AddressLine1,AddressLine2,City,UKPostCode,Country,AddressType,PayrollId,Gender,RegularPay,Period,PayrollEligibility,PayrollFrequency,TaxCode,TaxBasis,StudentLoan,NICategory,EffectiveFrom,StudentLoanPlan,PayinStartPeriod,NINO,StartDeclaration,ProcessLeaverNeeded,DateOfNoticeOfTermination,LeavingDate,LastWorkingDate,ReasonForLeaving,LastPayrunDate);

			}
			catch(Throwable t)
			{
				APP_LOGS.debug("Could not assert the home page title, Check for error");
				System.out.println("");
			}
		}
		FetchCompanyRecord(CompanyName,Title,FirstName,LastName,Email,UserName,Profile,ActivateLicense,JobTitle,EmploymentType,EmploymentStatus,PatternType,NoOfWorkingDays,ContractualHours,Manager,StartDate,ContinousStDate,KnownName,DOB,FromDate,AddressLine1,AddressLine2,City,UKPostCode,Country,AddressType,PayrollId,Gender,RegularPay,Period,PayrollEligibility,PayrollFrequency,TaxCode,TaxBasis,StudentLoan,NICategory,EffectiveFrom,StudentLoanPlan,PayinStartPeriod,NINO,StartDeclaration,ProcessLeaverNeeded,DateOfNoticeOfTermination,LeavingDate,LastWorkingDate,ReasonForLeaving,LastPayrunDate);

		
	}
	@Test(dependsOnMethods={"EmpsSetup_WithNICategory"})
	public void FetchCompanyRecord(String CompanyName,String Title,String FirstName, String LastName,String Email, String UserName, String Profile, String ActivateLicense, String JobTitle,String EmploymentType, String EmploymentStatus,String PatternType,String NoOfWorkingDays, String ContractualHours, String Manager, String StartDate, String ContinousStDate, String KnownName, String DOB,String FromDate,String AddressLine1,String AddressLine2,String City,String UKPostCode,String Country,String AddressType,String PayrollId, String Gender, String RegularPay, String Period, String PayrollEligibility, String PayrollFrequency,String TaxCode,String TaxBasis,String StudentLoan,String NICategory,String EffectiveFrom,String StudentLoanPlan,String PayinStartPeriod,String NINO,String StartDeclaration,String ProcessLeaverNeeded, String DateOfNoticeOfTermination,String LeavingDate,String LastWorkingDate,String ReasonForLeaving,String LastPayrunDate) throws Throwable
	{
		try
		{
			Thread.sleep(5000L);
			if(existsElementchkFor1mts(OR.getProperty("MyCompanyTab")))
			{
				System.out.println("I am in FetchCompany Record");
				if(companyFirsttimeView)
				{
					//companyFirsttimeView = false;
					getObject("MyCompanyTab").click();
					Thread.sleep(2000L);
					if(existsElementchkFor1mts(OR.getProperty("ViewGoButton")))
					{
						System.out.println("The test script verified that it successfully landed into Companies Tab.");
						System.out.println("");
						Thread.sleep(2000L);
						if (existsElementchkFor1mts(OR
								.getProperty("ViewGoButton")))
						{
							getObject("ViewGoButton").sendKeys("");
							getObject("ViewGoButton").click();
							System.out.println("The Go button got clicked");
						}
						Thread.sleep(7000L);
					}
				}
			}
			WebElement postsTable = driver.findElement(By.xpath(OR.getProperty("firstRecordOfNIcoulmnTable")));
			if(existsWebElement(postsTable))
			{
				System.out.println("The table rows got recognised");
				List<WebElement> rows = postsTable.findElements(By.xpath(OR.getProperty("firstRecordOfNIcoulmnTableRows")));
				lastRowCount = rows.size();
				java.util.Iterator<WebElement> x = rows.iterator();
				int rownum = 1;	
				outerbreak:
					while(x.hasNext())
					{								
						String firstRowOfEmployeeColumn="//div[" +rownum+ "]"+"/table/tbody/tr/td[4]/div/a";
						WebElement RequiredCompany= driver.findElement(By.xpath(firstRowOfEmployeeColumn));
						if(existsWebElement(RequiredCompany))
						{
							String AppnCompany= RequiredCompany.getText();
							//System.out.println(tempEmp+"-------"+empName+"------"+rownum);
							if(AppnCompany!=null && AppnCompany.equalsIgnoreCase(CompanyName))
							{
								System.out.println("Company matched");
								System.out.println("Company name is  :"+AppnCompany);
								if(existsWebElement(RequiredCompany))
								{
									RequiredCompany.click();
									System.out.println("The Company namely :"+AppnCompany+" got clicked successfully and displaying employee record");
									break outerbreak;
								}
							}
							else if(rownum == lastRowCount && AppnCompany!=null && AppnCompany!=(CompanyName))
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
						}
						rownum++;
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
		CreateNewJoiner(CompanyName,Title,FirstName,LastName,Email,UserName,Profile,ActivateLicense,JobTitle,EmploymentType,EmploymentStatus,PatternType,NoOfWorkingDays,ContractualHours,Manager,StartDate,ContinousStDate,KnownName,DOB,FromDate,AddressLine1,AddressLine2,City,UKPostCode,Country,AddressType,PayrollId,Gender,RegularPay,Period,PayrollEligibility,PayrollFrequency,TaxCode,TaxBasis,StudentLoan,NICategory,EffectiveFrom,StudentLoanPlan,PayinStartPeriod,NINO,StartDeclaration,ProcessLeaverNeeded,DateOfNoticeOfTermination,LeavingDate,LastWorkingDate,ReasonForLeaving,LastPayrunDate);
		
		EmployeeCreationNextPage(CompanyName,Title,FirstName,LastName,Email,UserName,Profile,ActivateLicense,JobTitle,EmploymentType,EmploymentStatus,PatternType,NoOfWorkingDays,ContractualHours,Manager,StartDate,ContinousStDate,KnownName,DOB,FromDate,AddressLine1,AddressLine2,City,UKPostCode,Country,AddressType,PayrollId,Gender,RegularPay,Period,PayrollEligibility,PayrollFrequency,TaxCode,TaxBasis,StudentLoan,NICategory,EffectiveFrom,StudentLoanPlan,PayinStartPeriod,NINO,StartDeclaration,ProcessLeaverNeeded,DateOfNoticeOfTermination,LeavingDate,LastWorkingDate,ReasonForLeaving,LastPayrunDate);
		
		employmentSavebutton();
		
		UpdateEmployeePersonalPage(CompanyName,Title,FirstName,LastName,Email,UserName,Profile,ActivateLicense,JobTitle,EmploymentType,EmploymentStatus,PatternType,NoOfWorkingDays,ContractualHours,Manager,StartDate,ContinousStDate,KnownName,DOB,FromDate,AddressLine1,AddressLine2,City,UKPostCode,Country,AddressType,PayrollId,Gender,RegularPay,Period,PayrollEligibility,PayrollFrequency,TaxCode,TaxBasis,StudentLoan,NICategory,EffectiveFrom,StudentLoanPlan,PayinStartPeriod,NINO,StartDeclaration,ProcessLeaverNeeded,DateOfNoticeOfTermination,LeavingDate,LastWorkingDate,ReasonForLeaving,LastPayrunDate);
		
		double PLD = Double.parseDouble(ProcessLeaverNeeded);
		System.out.println("converted smallER value is :"+PLD);
		if(PLD==0)
		{
			System.out.println("Processing the" + "PROCESS LEAVER FUNCTIONALITY"+"is not needed for this scenario");
		}
		else
		{
			ProcessLeaver(CompanyName,Title,FirstName,LastName,Email,UserName,Profile,ActivateLicense,JobTitle,EmploymentType,EmploymentStatus,PatternType,NoOfWorkingDays,ContractualHours,Manager,StartDate,ContinousStDate,KnownName,DOB,FromDate,AddressLine1,AddressLine2,City,UKPostCode,Country,AddressType,PayrollId,Gender,RegularPay,Period,PayrollEligibility,PayrollFrequency,TaxCode,TaxBasis,StudentLoan,NICategory,EffectiveFrom,StudentLoanPlan,PayinStartPeriod,NINO,StartDeclaration,ProcessLeaverNeeded,DateOfNoticeOfTermination,LeavingDate,LastWorkingDate,ReasonForLeaving,LastPayrunDate);
		}
		
	}


	@Test(dependsOnMethods={"FetchCompanyRecord"})
	public void CreateNewJoiner(String CompanyName,String Title,String FirstName, String LastName,String Email, String UserName, String Profile, String ActivateLicense, String JobTitle,String EmploymentType, String EmploymentStatus,String PatternType,String NoOfWorkingDays, String ContractualHours, String Manager, String StartDate, String ContinousStDate, String KnownName, String DOB,String FromDate,String AddressLine1,String AddressLine2,String City,String UKPostCode,String Country,String AddressType,String PayrollId, String Gender, String RegularPay, String Period, String PayrollEligibility, String PayrollFrequency,String TaxCode,String TaxBasis,String StudentLoan,String NICategory,String EffectiveFrom,String StudentLoanPlan,String PayinStartPeriod,String NINO,String StartDeclaration,String ProcessLeaverNeeded, String DateOfNoticeOfTermination,String LeavingDate,String LastWorkingDate,String ReasonForLeaving,String LastPayrunDate) throws Throwable
	{
		try
		{
			Thread.sleep(4000L);
			System.out.println("I am in CreateNewJoiner method");
			if(existsElementchkFor1mts(OR.getProperty("companyNewjoiner")))
			{
				Thread.sleep(1000L);
				getObject("companyNewjoiner").click();
				System.out.println("New joiner button got clicked successfully");
				Thread.sleep(4000L);
			}

			if(existsElementchkFor1mts(OR.getProperty("FirstName")))
			{
				getObject("FirstName").sendKeys(FirstName);
				System.out.println("First name got entered successfully");
			}
			Thread.sleep(2000L);

			if(existsElementchkFor1mts(OR.getProperty("LastName")))
			{
				getObject("LastName").sendKeys(LastName);
				System.out.println("Last name got entered successfully");
			}
			Thread.sleep(2000L);

			if(existsElementchkFor1mts(OR.getProperty("Email")))
			{
				getObject("Email").sendKeys(Email);
				System.out.println("Email got entered successfully");
			}
			Thread.sleep(2000L);

			if(existsElementchkFor1mts(OR.getProperty("UserName")))
			{
				getObject("UserName").sendKeys(UserName);
				System.out.println("User name got entered successfully");
			}
			Thread.sleep(2000L);

			if(existsElementchkFor1mts(OR.getProperty("Profile")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("Profile"))));
				selectByValue.selectByVisibleText(Profile);
				System.out.println("Profile got selected successfully");
			}
			Thread.sleep(2000L);

			if(existsElementchkFor1mts(OR.getProperty("ActivateLicense")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("ActivateLicense"))));
				selectByValue.selectByVisibleText(ActivateLicense);
				System.out.println("Activate license got set to NO successfully");
			}

			Thread.sleep(2000L);
			if(existsElementchkFor1mts(OR.getProperty("JobTitle")))
			{
				getObject("JobTitle").sendKeys(JobTitle);
				System.out.println("Job Title got entered successfully");
			}

			Thread.sleep(2000L);
			if(existsElementchkFor1mts(OR.getProperty("CompanySelection")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("CompanySelection"))));
				selectByValue.selectByVisibleText(CompanyName);
				System.out.println("Company got selected successfully");
			}

			Thread.sleep(2000L);
			if(existsElementchkFor1mts(OR.getProperty("EmploymentType")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("EmploymentType"))));
				selectByValue.selectByVisibleText(EmploymentType);
				System.out.println("Employment got selected successfully");
			}

			Thread.sleep(2000L);
			if(existsElementchkFor1mts(OR.getProperty("EmploymentStatus")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("EmploymentStatus"))));
				selectByValue.selectByVisibleText(EmploymentStatus);
				System.out.println("Employment Status selected successfully");
			}

			Thread.sleep(2000L);
			if(existsElementchkFor1mts(OR.getProperty("PatternType")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("PatternType"))));
				selectByValue.selectByVisibleText(PatternType);
				System.out.println("Pattern got selected successfully");
			}
			Thread.sleep(2000L);
			if(existsElementchkFor1mts(OR.getProperty("ContractualHours")))
			{
				getObject("ContractualHours").clear();
				getObject("ContractualHours").sendKeys(ContractualHours);
				System.out.println("No of Contractual hours got entered successfully");
			}

			Thread.sleep(2000);
			if(existsElementchkFor1mts(OR.getProperty("SelectManager")))
			{
				getObject("SelectManager").click();
				Thread.sleep(5000);
				String mainHandle3 = driver.getWindowHandle(); // To save the parent window
				// create one more method for reading employee from excel sheet.
				ReadEmployee(Manager);
				Thread.sleep(2000L);
				driver.switchTo().window(mainHandle3); // finally switch back to parent window and perform the operations.
				System.out.println("The Manager got selected successfully");
				Thread.sleep(2000L);
			}
			System.out.println("Still emp start date needs to input");
			keyDates(StartDate,ContinousStDate);
			System.out.println("The keydates got entered successfully");
			Thread.sleep(2000L);
			if(existsElementchkFor1mts(OR.getProperty("JoinerNext")))
			{
				getObject("JoinerNext").click();
				System.out.println("Next button got clicked successfully");
			}
			Thread.sleep(10000L);
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}


	@Test(dependsOnMethods={"FetchCompanyRecord"})
	public void EmployeeCreationNextPage(String CompanyName,String Title,String FirstName, String LastName,String Email, String UserName, String Profile, String ActivateLicense, String JobTitle,String EmploymentType, String EmploymentStatus,String PatternType,String NoOfWorkingDays, String ContractualHours, String Manager, String StartDate, String ContinousStDate, String KnownName, String DOB,String FromDate,String AddressLine1,String AddressLine2,String City,String UKPostCode,String Country,String AddressType,String PayrollId, String Gender, String RegularPay, String Period, String PayrollEligibility, String PayrollFrequency,String TaxCode,String TaxBasis,String StudentLoan,String NICategory,String EffectiveFrom,String StudentLoanPlan,String PayinStartPeriod,String NINO,String StartDeclaration,String ProcessLeaverNeeded, String DateOfNoticeOfTermination,String LeavingDate,String LastWorkingDate,String ReasonForLeaving,String LastPayrunDate)throws Throwable
	{
		try
		{
			if (existsElementchkFor1mts(OR.getProperty("DOBofNextPage")))
			{
				getObject("DOBofNextPage").sendKeys("");
				String dateStr = DOB;
				dateFormaterMethod(dateStr);
				System.out.println("The stdate entered is  " +formattedDate);		
				Thread.sleep(4000L);
				getObject("DOBofNextPage").sendKeys(formattedDate);
				System.out.println("The DOB got entered successfully");
			}
			Thread.sleep(2000L);
			if (existsElementchkFor1mts(OR.getProperty("genderOfNextPage")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("genderOfNextPage"))));
				selectByValue.selectByVisibleText(Gender);
				System.out.println("Gender got selected successfully");
			}
			Thread.sleep(2000L);
			if (existsElementchkFor1mts(OR.getProperty("starterDeclaration")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("starterDeclaration"))));
				selectByValue.selectByVisibleText(StartDeclaration);
				System.out.println("Starter decalration got selected successfully");
			}
			Thread.sleep(2000L);
			if (existsElementchkFor1mts(OR.getProperty("Taxbasis")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("Taxbasis"))));
				selectByValue.selectByVisibleText(TaxBasis);
				System.out.println("Taxbasis got selected successfully");
			}

			Thread.sleep(2000L);
			if (existsElementchkFor1mts(OR.getProperty("Taxcode")))
			{
				getObject("Taxcode").sendKeys("");
				getObject("Taxcode").sendKeys(TaxCode);
				System.out.println("Tax code is entered successfully");
			}

			Thread.sleep(2000L);
			if (existsElementchkFor1mts(OR.getProperty("NICategory")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("NICategory"))));
				selectByValue.selectByVisibleText(NICategory);
				System.out.println("NICategory got selected successfully");
			}
			
			Thread.sleep(2000L);
			if (existsElementchkFor1mts(OR.getProperty("FromDate")))
			{
				getObject("FromDate").sendKeys("");
				String dateStr = DOB;
				dateFormaterMethod(dateStr);
				System.out.println("The stdate entered is  " +formattedDate);		
				Thread.sleep(4000L);
				getObject("FromDate").sendKeys(formattedDate);
				System.out.println("The From date got entered successfully");
			}

			Thread.sleep(2000L);
			if (existsElementchkFor1mts(OR.getProperty("AddressLineOne")))
			{
				getObject("AddressLineOne").sendKeys("");
				getObject("AddressLineOne").clear();
				getObject("AddressLineOne").sendKeys(AddressLine1);
				System.out.println("Address line one got entered successfully");
			}
			
			Thread.sleep(2000L);
			if (existsElementchkFor1mts(OR.getProperty("AddressLineTwo")))
			{
				getObject("AddressLineTwo").sendKeys("");
				getObject("AddressLineTwo").clear();
				getObject("AddressLineTwo").sendKeys(AddressLine2);
				System.out.println("Address line2 got entered successfully");
			}
			
			Thread.sleep(2000L);
			if (existsElementchkFor1mts(OR.getProperty("ACity")))
			{
				getObject("ACity").sendKeys("");
				getObject("ACity").clear();
				getObject("ACity").sendKeys(City);
				System.out.println("City got selected successfully");
			}
			
			Thread.sleep(2000L);
			if (existsElementchkFor1mts(OR.getProperty("ACountry")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("ACountry"))));
				selectByValue.selectByVisibleText(Country);
				System.out.println("Country got selected successfully");
			}
			
			Thread.sleep(2000L);
			if (existsElementchkFor1mts(OR.getProperty("UKPostCodee")))
			{
				getObject("UKPostCodee").sendKeys("");
				getObject("UKPostCodee").clear();
				getObject("UKPostCodee").sendKeys(AddressLine2);
				System.out.println("UK Postcode got entered successfully");
			}
			
			Thread.sleep(2000L);
			if (existsElementchkFor1mts(OR.getProperty("AAddressType")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("AAddressType"))));
				selectByValue.selectByVisibleText(AddressType);
				System.out.println("AddressType got selected successfully");
			}

			Thread.sleep(2000L);
			if (existsElementchkFor1mts(OR.getProperty("RegularPayOfNextPage")))
			{
				getObject("RegularPayOfNextPage").sendKeys("");
				getObject("RegularPayOfNextPage").clear();
				getObject("RegularPayOfNextPage").sendKeys(RegularPay);
				System.out.println("Regular pay got entered successfully");
			}

			Thread.sleep(2000L);
			if (existsElementchkFor1mts(OR.getProperty("PeriodOfNextPage")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("PeriodOfNextPage"))));
				selectByValue.selectByVisibleText(Period);
				System.out.println("Period got selected successfully");
			}

			Thread.sleep(2000L);
			if (existsElementchkFor1mts(OR.getProperty("PayrollEligibilityOfNextPage")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("PayrollEligibilityOfNextPage"))));
				selectByValue.selectByVisibleText(PayrollEligibility);
				System.out.println("PayrollEligibility got selected successfully");
			}

			Thread.sleep(2000L);
			if (existsElementchkFor1mts(OR.getProperty("PayrollFrequencyOfNextPage")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("PayrollFrequencyOfNextPage"))));
				selectByValue.selectByVisibleText(PayrollFrequency);
				System.out.println("PayrollFrequency got selected successfully");
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());	
		}
	} 


	@Test(dependsOnMethods={"EmployeeCreationNextPage"})
	public void UpdateEmployeePersonalPage(String CompanyName,String Title,String FirstName, String LastName,String Email, String UserName, String Profile, String ActivateLicense, String JobTitle,String EmploymentType, String EmploymentStatus,String PatternType,String NoOfWorkingDays, String ContractualHours, String Manager, String StartDate, String ContinousStDate, String KnownName, String DOB,String FromDate,String AddressLine1,String AddressLine2,String City,String UKPostCode,String Country,String AddressType,String PayrollId, String Gender, String RegularPay, String Period, String PayrollEligibility, String PayrollFrequency,String TaxCode,String TaxBasis,String StudentLoan,String NICategory,String EffectiveFrom,String StudentLoanPlan,String PayinStartPeriod,String NINO,String StartDeclaration,String ProcessLeaverNeeded, String DateOfNoticeOfTermination,String LeavingDate,String LastWorkingDate,String ReasonForLeaving,String LastPayrunDate)throws Throwable
	{
		try
		{
			Thread.sleep(2000L);
			if (existsElementchkFor1mts(OR.getProperty("personalTab")))
			{
				getObject("personalTab").sendKeys("");
				getObject("personalTab").click();
				System.out.println("Personal tab got clicked successfully");
			}

			Thread.sleep(2000L);
			if (existsElementchkFor1mts(OR.getProperty("personalEditBtn")))
			{
				getObject("personalEditBtn").sendKeys("");
				getObject("personalEditBtn").click();
				System.out.println("Personal edit button got clicked successfully");
			}

			Thread.sleep(2000L);
			if (existsElementchkFor1mts(OR.getProperty("legalNameOrKnownName")))
			{
				getObject("legalNameOrKnownName").sendKeys("");
				getObject("legalNameOrKnownName").clear();
				getObject("legalNameOrKnownName").sendKeys(KnownName);
				System.out.println("legal name or known name got entered successfully");
			}

			Thread.sleep(2000L);
			if (existsElementchkFor1mts(OR.getProperty("nino")))
			{
				getObject("nino").sendKeys("");
				getObject("nino").clear();
				getObject("nino").sendKeys(NINO);
				System.out.println("NINO got entered successfully");
			}
			double valueOfStudentLoanNeeded = Double.parseDouble(StudentLoan);
			System.out.println("converted smallER value is :"+valueOfStudentLoanNeeded);
			if(valueOfStudentLoanNeeded== 1.0)
			{
				System.out.println("Yes for this scenario, Student Loan check box to check"
						+ " and Student loan plan needs to be selected");
				boolean	studentLoanchekbox = getObject("studentLoan").isSelected();
				System.out.println("The studentLoanchekbox selection is :"+studentLoanchekbox);
				boolean studentLoanchekboxdisplyed = getObject("studentLoan").isDisplayed();
				System.out.println("The studentLoanchekboxdisplyed is displayed :"+studentLoanchekboxdisplyed);
				Thread.sleep(4000L);
				boolean studentLoanchkboxIsEnabled = getObject("studentLoan").isEnabled();
				System.out.println("The studentLoanchkboxIsEnabled is isEnabled :"+studentLoanchkboxIsEnabled);
				double valueOfStudentLoanChkbox = Double.parseDouble(StudentLoan);
				System.out.println("converted smallER value is :"+valueOfStudentLoanChkbox);
				outerloop:
				if(valueOfStudentLoanChkbox== 1.0)
				{
					Thread.sleep(2000L);
					if(studentLoanchekbox)
					{
						System.out.println("Student loan checkbox was allready checked, Hence our condition got satisfied");
						break outerloop;
					}
					else
					{
						getObject("studentLoan").sendKeys("");
						getObject("studentLoan").click();
						System.out.println("Student loan checkbox was not checked and now checked hence Condition now satisfied successfully");
						Thread.sleep(2000L);
						if (existsElementchkFor1mts(OR.getProperty("studentLoanPlann")))
						{
							Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("studentLoanPlann"))));
							selectByValue.selectByVisibleText(StudentLoanPlan);
							System.out.println("Student Loan Plan got selected successfully");
						}
						break outerloop;
					}
				}
			}
			else
			{
				System.out.println("As per the requirment spec for this scenario,"
						+ " Student Loan check box check"
						+ " and Student loan plan selection is not needed");
			}
			Thread.sleep(2000L);
			if (existsElementchkFor1mts(OR.getProperty("personalSavebutton")))
			{
				getObject("personalSavebutton").sendKeys("");
				getObject("personalSavebutton").click();
				System.out.println("Personal page Save button got clicked successfully");
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());	
		}
	}


	@Test(dependsOnMethods={"UpdateEmployeePersonalPage"})
	public void ProcessLeaver(String CompanyName,String Title,String FirstName, String LastName,String Email, String UserName, String Profile, String ActivateLicense, String JobTitle,String EmploymentType, String EmploymentStatus,String PatternType,String NoOfWorkingDays, String ContractualHours, String Manager, String StartDate, String ContinousStDate, String KnownName, String DOB,String FromDate,String AddressLine1,String AddressLine2,String City,String UKPostCode,String Country,String AddressType,String PayrollId, String Gender, String RegularPay, String Period, String PayrollEligibility, String PayrollFrequency,String TaxCode,String TaxBasis,String StudentLoan,String NICategory,String EffectiveFrom,String StudentLoanPlan,String PayinStartPeriod,String NINO,String StartDeclaration,String ProcessLeaverNeeded, String DateOfNoticeOfTermination,String LeavingDate,String LastWorkingDate,String ReasonForLeaving,String LastPayrunDate)throws Throwable
	{
		try
		{
			Thread.sleep(2000L);
			if (existsElementchkFor1mts(OR.getProperty("processLeaver")))
			{
				getObject("processLeaver").sendKeys("");
				getObject("processLeaver").click();
				System.out.println("Process Leaver button got clicked successfully");
			}
			Thread.sleep(2000L);
			System.out.println("Still emp start date needs to input");
			keyDates(DateOfNoticeOfTermination,LeavingDate,LastPayrunDate,LastWorkingDate);
			System.out.println("The keydates got entered successfully");
			Thread.sleep(2000L);
			if(existsElementchkFor1mts(OR.getProperty("reasonForLeaving")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("reasonForLeaving"))));
				selectByValue.selectByVisibleText(ReasonForLeaving);
				System.out.println("Reason for leaving got selected successfully");
			}
			Thread.sleep(2000L);
			if (existsElementchkFor1mts(OR.getProperty("ProcessleaverButton")))
			{
				getObject("ProcessleaverButton").sendKeys("");
				getObject("ProcessleaverButton").click();
				System.out.println("Process Leaver button got clicked successfully");
			}
			Thread.sleep(2000L);
			if (existsElementchkFor1mts(OR.getProperty("CompleteProcessButton")))
			{
				getObject("CompleteProcessButton").sendKeys("");
				getObject("CompleteProcessButton").click();
				System.out.println("Complete Process button got clicked successfully");
				Thread.sleep(3000L);
				isAlertPresent();
				System.out.println("Process leaver functionality got executed successfully");
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}




	public void ReadEmployee(String manager)throws Throwable
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
			Thread.sleep(3000);
			getObject("searchField").sendKeys(manager);
			System.out.println("I entered the empname reading from excel sheet");
			Thread.sleep(1000);
			getObject("Gobutton").click();
			System.out.println("I clicked Go button");
			Thread.sleep(3000);
			driver.switchTo().defaultContent();        // now that you encountered one more frame hence switch back to main page
			WebElement dddframe2 = driver.findElement(By.id("resultsFrame"));// and save the frame id and 
			driver.switchTo().frame(dddframe2); // switch to the other frame and perform the operations
			System.out.println("I switched to Results Frame");
			Thread.sleep(2000L);
			if(existsElement(OR.getProperty("clkSortedone1a")))
			{
				getObject("clkSortedone1a").click();
			}
			else if(existsElement(OR.getProperty("clkSortedone2")))
			{
				getObject("clkSortedone2").click();
			}
			System.out.println("I clicked the user finally");
		}
	}


	public void keyDates(String StartDate,String ContinousStdate)throws Throwable
	{
		try
		{
			if(existsElementchkFor1mts(OR.getProperty("newJoinerStDate")))
			{
				getObject("newJoinerStDate").sendKeys("");
				String dateStr = StartDate;
				dateFormaterMethod(dateStr);
				System.out.println("The stdate entered is  " +formattedDate);		
				Thread.sleep(4000L);
				getObject("newJoinerStDate").sendKeys(formattedDate);
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}

		try
		{
			if(existsElementchkFor1mts(OR.getProperty("newJoinerContnousStDate")))
			{
				getObject("newJoinerContnousStDate").sendKeys("");
				String dateStr = ContinousStdate;
				dateFormaterMethod(dateStr);
				System.out.println("The continous start date entered is" +formattedDate);		
				Thread.sleep(4000L);
				getObject("newJoinerContnousStDate").sendKeys(formattedDate);
				Thread.sleep(3000L);
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	
	public void keyDates(String DateOfNoticeOfTermination,String LeavingDate,String LastPayrunDate,String LastWorkingDate)throws Throwable
	{
		try
		{
			Thread.sleep(4000L);

			if(existsElementchkFor1mts(OR.getProperty("noticeOfTermination")))
			{
				getObject("noticeOfTermination").sendKeys("");
				String dateStr = DateOfNoticeOfTermination;
				dateFormaterMethod(dateStr);
				System.out.println("The notice of termination entered is  " +formattedDate);		
				Thread.sleep(4000L);
				getObject("noticeOfTermination").sendKeys(formattedDate);
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}

		try
		{
			if(existsElementchkFor1mts(OR.getProperty("leavingDate")))
			{
				getObject("leavingDate").sendKeys("");
				String dateStr = LeavingDate;
				dateFormaterMethod(dateStr);
				System.out.println("The leaving date entered is" +formattedDate);		
				Thread.sleep(4000L);
				getObject("leavingDate").sendKeys(formattedDate);
				Thread.sleep(3000L);
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
		
		try
		{
			if(existsElementchkFor1mts(OR.getProperty("lastPayRun")))
			{
				getObject("lastPayRun").sendKeys("");
				String dateStr = LastPayrunDate;
				dateFormaterMethod(dateStr);
				System.out.println("The Last Pay run date entered is" +formattedDate);		
				Thread.sleep(4000L);
				getObject("lastPayRun").sendKeys(formattedDate);
				Thread.sleep(3000L);
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}


		try
		{
			if(existsElementchkFor1mts(OR.getProperty("lastWorkingDate")))
			{
				getObject("lastWorkingDate").sendKeys("");
				String dateStr = LastWorkingDate;
				dateFormaterMethod(dateStr);
				System.out.println("The lastworking date entered is" +formattedDate);		
				Thread.sleep(4000L);
				getObject("lastWorkingDate").sendKeys(formattedDate);
				Thread.sleep(3000L);
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}



	@DataProvider
	public Object[][] getData()
	{
		return Test_Util.getData(Payroll_CreateEmployees_For_RecognitionScenarious_SuiteXls,"CreateNewRTIEmployees");
	}


	@AfterMethod
	public void ReportDataSetResult()
	{
		if(Skip)
		{
			Test_Util.ReportDataSetResult(Payroll_CreateEmployees_For_RecognitionScenarious_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
		}
		else if(Fail)
		{
			IsTestPass = false;
			Test_Util.ReportDataSetResult(Payroll_CreateEmployees_For_RecognitionScenarious_SuiteXls, this.getClass().getSimpleName(), count+2, "Fail");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_CreateEmployees_For_RecognitionScenarious_SuiteXls, this.getClass().getSimpleName(), count+2, "Pass");
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
			Test_Util.ReportDataSetResult(Payroll_CreateEmployees_For_RecognitionScenarious_SuiteXls, "first", Test_Util.GetRowNum(Payroll_CreateEmployees_For_RecognitionScenarious_SuiteXls, this.getClass().getSimpleName()),"Pass");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_CreateEmployees_For_RecognitionScenarious_SuiteXls, "first", Test_Util.GetRowNum(Payroll_CreateEmployees_For_RecognitionScenarious_SuiteXls, this.getClass().getSimpleName()),"Fail");
		}	
		closeBrowser();
	}
}
