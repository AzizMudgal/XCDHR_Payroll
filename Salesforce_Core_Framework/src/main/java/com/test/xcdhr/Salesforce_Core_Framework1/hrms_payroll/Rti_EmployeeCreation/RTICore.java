package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.Rti_EmployeeCreation;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.ErrorUtil;

public class RTICore extends CreateNewRTIEmployees
{
	public boolean companyFirsttimeView = true;
	public String knownNamee;
	public String dob;
	public String gender;
	public String regularPay;
	public String period;
	public String payrollFrequency;
	public String payrollElegiblty;
	
	
	public void FetchCompanyRecord(String CompanyName,String FirstName, String LastName,String Email, String UserName, String Profile, String ActivateLicense, String JobTitle, String Company,String EmploymentType, String EmploymentStatus,String PatternType,String NoOfWorkingDays, String ContractualHours, String Manager, String StartDate, String ContinousStDate, String KnownName, String DOB, String Gender, String RegularPay, String Period, String PayrollEligibility, String PayrollFrequency,String TaxCode,String TaxBasis,String StudentLoan,String NICategory,String EffectiveFrom,String StudentLoanPlan,String PayinStartPeriod,String NINO,String StartDeclaration,String DateOfNoticeOfTermination,String LeavingDate,String LastWorkingDate,String ReasonForLeaving) throws Throwable
	{
		try
		{
			Thread.sleep(5000L);
			if(existsElementchkFor1mts(OR.getProperty("MyCompanyTab")))
			{
				System.out.println("I am in FetchCompany Record");
				if(companyFirsttimeView)
				{
					companyFirsttimeView = false;
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
		CreateNewJoiner(CompanyName, FirstName, LastName, Email, UserName, Profile, ActivateLicense, JobTitle, Company, EmploymentType, EmploymentStatus, PatternType, NoOfWorkingDays, ContractualHours, Manager, StartDate, ContinousStDate, KnownName, DOB, Gender, RegularPay, Period, PayrollEligibility, PayrollFrequency, TaxCode, TaxBasis, StudentLoan, NICategory, EffectiveFrom, StudentLoanPlan, PayinStartPeriod, NINO, StartDeclaration, DateOfNoticeOfTermination, LeavingDate, LastWorkingDate, ReasonForLeaving);
		EmployeeCreationNextPage(KnownName,DOB,Gender,RegularPay,Period,PayrollEligibility,PayrollFrequency, TaxCode, TaxBasis, StudentLoan, NICategory,StartDeclaration );
		employmentSavebutton();
		UpdateEmployeePersonalPage(KnownName,NINO);
		ProcessLeaver(DateOfNoticeOfTermination,LeavingDate,LastWorkingDate,ReasonForLeaving);
	}
	
	
	@Test(dependsOnMethods={"FetchCompanyRecord"})
	public void CreateNewJoiner(String CompanyName,String FirstName, String LastName,String Email, String UserName, String Profile, String ActivateLicense, String JobTitle, String Company,String EmploymentType, String EmploymentStatus,String PatternType,String NoOfWorkingDays, String ContractualHours, String Manager, String StartDate, String ContinousStDate, String KnownName, String DOB, String Gender, String RegularPay, String Period, String PayrollEligibility, String PayrollFrequency,String TaxCode,String TaxBasis,String StudentLoan,String NICategory,String EffectiveFrom,String StudentLoanPlan,String PayinStartPeriod,String NINO,String StartDeclaration,String DateOfNoticeOfTermination,String LeavingDate,String LastWorkingDate,String ReasonForLeaving) throws Throwable
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
					// This select by value needs to be called from OR.Properties
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
					selectByValue.selectByVisibleText(Company);
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
			/*	
				Thread.sleep(2000L);
				if(existsElementchkFor1mts(OR.getProperty("NoOffWorkingDays")))
				{
					getObject("NoOffWorkingDays").sendKeys(NoOfWorkingDays);
					System.out.println("No of working days got entered successfully");
				}
				
				
				Thread.sleep(2000L);
				if(existsElementchkFor1mts(OR.getProperty("ContractualHours")))
				{
					getObject("ContractualHours").clear();
					getObject("ContractualHours").sendKeys(ContractualHours);
					System.out.println("No of Contractual hours got entered successfully");

				}
				
				
					*/
				
				
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
	public void EmployeeCreationNextPage(String KnownName, String DOB, String Gender, String RegularPay, String Period, String PayrollEligibility, String PayrollFrequency,String TaxCode,String TaxBasis,String StudentLoan,String NICategory,String StartDeclaration )throws Throwable
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
			
         /*	Thread.sleep(2000L);
			if (existsElementchkFor1mts(OR.getProperty("starterDeclaration")))
			{
				// Select(driver.findElement(By.xpath(OR.getProperty("Profile"))));
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("starterDeclaration"))));
				selectByValue.selectByVisibleText(StartDeclaration);
				System.out.println("Starter decalration got selected successfully");
			}
		*/	
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
	public void UpdateEmployeePersonalPage(String KnownName,String NINO)throws Throwable
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
	
	/*
	 * @Test(dependsOnMethods={"EmployeeCreationNextPage"})
	public void UpdateEmployeePersonalPage(String KnownN
	 * 
	 */
	 @Test(dependsOnMethods={"UpdateEmployeePersonalPage"})
	public void ProcessLeaver(String DateOfNoticeOfTermination,String LeavingDate,String LastWorkingDate,String ReasonForLeaving)throws Throwable
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
			keyDates(DateOfNoticeOfTermination,LeavingDate,LastWorkingDate);
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
	
	
	public void CreateCompenLeavedetails(String EmpDOB,String Gender,String Nationality,String FromDate,String ToDate,String Address1,String  Address2,String Street,String City,String Country,String PostCode,String Region,String AddrssType,String ParentLocation,String MinimumYrsService,String HoursAM,String HoursPM,String WorkingDays,String AnnualSalary,String Bonus,String BonusOTE,String Commission,String CommissionOTE,String EmpContrbnPenSal,String EmployerContrbPenSal,String ReglrSalary,String AddnalEmplyeeContrbn,String AddnalEmployerContrbn,String bonusNotes,String DailyRateOfPay, String Period,String Departmentt,String EmployeeSalarySacrifice,String EmployeeContbnlnLeiu,String Payfrequency,String CreateLeaveYrs)throws Throwable
	{
		try
		{
			if (existsElement(OR.getProperty("empDOB")))
			{
				EnrDOB(EmpDOB);
			}
			Thread.sleep(2000);
			if (existsElement(OR.getProperty("gender")))
			{
				getObject("gender").sendKeys(Gender);
			}
			Thread.sleep(2000);
			if (existsElement(OR.getProperty("nationality")))
			{
				getObject("nationality").sendKeys(Nationality);
			}
			Thread.sleep(2000);
			if (existsElement(OR.getProperty("fromDate")))
			{
				EntrFromdt(FromDate);
			}
			Thread.sleep(2000);
			if (existsElement(OR.getProperty("toDate")))
			{
				EntrTOdt(ToDate);
			}
			Thread.sleep(2000);
			if(existsElement(OR.getProperty("removeCalendercntrl")))
			{
				getObject("removeCalendercntrl").sendKeys("");
				getObject("removeCalendercntrl").click();
			}
			Thread.sleep(2000);
			if (existsElement(OR.getProperty("address1")))
			{
				getObject("address1").sendKeys(Address1);
			}
			Thread.sleep(2000);
			if (existsElement(OR.getProperty("address2")))
			{
				getObject("address2").sendKeys(Address2);
			}
			Thread.sleep(2000);
			if (existsElement(OR.getProperty("street")))
			{
				getObject("street").sendKeys(Street);
			}
			Thread.sleep(2000);
			if (existsElement(OR.getProperty("city")))
			{
				getObject("city").sendKeys(City);
			}
			Thread.sleep(2000);
			if (existsElement(OR.getProperty("country")))
			{
				getObject("country").sendKeys(Country);
			}
			Thread.sleep(2000);
			
		
			 
			double postcodewithDecimal = Double.parseDouble(PostCode);
			DecimalFormat df = new DecimalFormat("###.#");
			String withoutDecimal= df.format(postcodewithDecimal);
			Thread.sleep(2000);
			if (existsElement(OR.getProperty("postcode")))
			{
				getObject("postcode").sendKeys(withoutDecimal);
			}
			Thread.sleep(2000);
			if (existsElement(OR.getProperty("region")))
			{
				getObject("region").sendKeys(Region);
			}
			Thread.sleep(2000L);
			if(existsElement(OR.getProperty("AddresTypePicklist")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("AddresTypePicklist"))));
				selectByValue.selectByValue(AddrssType);
				System.out.println("address type selected is :"+ AddrssType);
			}
			Thread.sleep(2000);
			getObject("parentLocation").click();
			Thread.sleep(5000);
			String mainHandle3 = driver.getWindowHandle(); // To save the parent window
			// create one more method for reading employee from excel sheet.
			ReadEmployee(ParentLocation);
			Thread.sleep(2000L);
			driver.switchTo().window(mainHandle3); // finally switch back to parent window and perform the operations.
			
		
			
			Thread.sleep(2000L);
			if (existsElement(OR.getProperty("MinimumYrsService")))
			{
				getObject("MinimumYrsService").clear();
				Thread.sleep(2000L);
				getObject("MinimumYrsService").sendKeys(MinimumYrsService);
			}
			Thread.sleep(2000L);
			if (existsElement(OR.getProperty("HoursAM")))
			{
				getObject("HoursAM").clear();
				Thread.sleep(2000L);
				getObject("HoursAM").sendKeys(HoursAM);
			}
			Thread.sleep(2000L);
			if (existsElement(OR.getProperty("HoursPM")))
			{
				getObject("HoursPM").clear();
				Thread.sleep(2000L);
				getObject("HoursPM").sendKeys(HoursPM);
			}
			
			Thread.sleep(2000L);
			
			if (existsElement(OR.getProperty("WorkingDays")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("WorkingDays"))));
				// This select by value needs to be called from OR.Properties
				selectByValue.selectByValue(WorkingDays);
			}
			
			Thread.sleep(2000L);
			if (existsElement(OR.getProperty("AnnualSalary")))
			{
				getObject("AnnualSalary").clear();
				Thread.sleep(2000L);
				getObject("AnnualSalary").sendKeys(AnnualSalary);
			}
			Thread.sleep(2000L);
			if (existsElement(OR.getProperty("Bonus")))
			{
				getObject("Bonus").sendKeys(Bonus);
			}
			Thread.sleep(2000L);
			if (existsElement(OR.getProperty("BonusOTE")))
			{
				getObject("BonusOTE").sendKeys(BonusOTE);
			}
			Thread.sleep(2000L);
			if (existsElement(OR.getProperty("Commission")))
			{
				getObject("Commission").sendKeys(Commission);
			}
			Thread.sleep(2000L);
			if (existsElement(OR.getProperty("CommissionOTE")))
			{
				getObject("CommissionOTE").sendKeys(CommissionOTE);
			}
			Thread.sleep(2000L);
			if (existsElement(OR.getProperty("EmpContrbnPenSal")))
			{
				getObject("EmpContrbnPenSal").sendKeys(EmpContrbnPenSal);
			}
			
			Thread.sleep(2000L);
			if (existsElement(OR.getProperty("EmployerContrbPenSal")))
			{
				getObject("EmployerContrbPenSal").sendKeys(EmployerContrbPenSal);
			}
			Thread.sleep(2000L);
			
			double rsal = Double.parseDouble(ReglrSalary);
			DecimalFormat df1 = new DecimalFormat("###.#");
			String rsalwithoutDecimal= df1.format(rsal);
			Thread.sleep(2000);
			if (existsElement(OR.getProperty("regularSalary")))
			{
				getObject("regularSalary").clear();
				Thread.sleep(1000L);
				getObject("regularSalary").sendKeys(rsalwithoutDecimal);
			}
			
			
			
			Thread.sleep(2000L);
			if (existsElement(OR.getProperty("AddnalEmplyeeContrbn")))
			{
				getObject("AddnalEmplyeeContrbn").sendKeys(AddnalEmplyeeContrbn);
			}
			Thread.sleep(2000L);
			if (existsElement(OR.getProperty("AddnalEmployerContrbn")))
			{
				getObject("AddnalEmployerContrbn").sendKeys(AddnalEmployerContrbn);
			}
			Thread.sleep(2000L);
			if (existsElement(OR.getProperty("bonunsNotes")))
			{
				getObject("bonunsNotes").sendKeys(bonusNotes);
			}
			Thread.sleep(2000L);
			if (existsElement(OR.getProperty("DailyRateOfPay")))
			{
				getObject("DailyRateOfPay").sendKeys(DailyRateOfPay);
			}
			
			Thread.sleep(2000L);
			if (existsElement(OR.getProperty("period")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("period"))));
				// This select by value needs to be called from OR.Properties
				selectByValue.selectByValue(Period);
			}
			Thread.sleep(2000L);
			
			
			if (existsElement(OR.getProperty("Department")))
			{
				getObject("Department").sendKeys(Departmentt);
			}
			
			Thread.sleep(2000L);
			if (existsElement(OR.getProperty("EmployeeSalarySacrifice")))
			{
				getObject("EmployeeSalarySacrifice").sendKeys(EmployeeSalarySacrifice);
			}
			Thread.sleep(2000L);
			
			if (existsElement(OR.getProperty("EmployeeContbnInLeiu")))
			{
				getObject("EmployeeContbnInLeiu").clear();
				getObject("EmployeeContbnInLeiu").sendKeys(EmployeeContbnlnLeiu);
			}
			Thread.sleep(2000L);
			
			if (existsElement(OR.getProperty("Payfrequency")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("Payfrequency"))));
				// This select by value needs to be called from OR.Properties
				selectByValue.selectByValue(Payfrequency);
			}
			
			
			Thread.sleep(2000L);
			if (existsElement(OR.getProperty("CreateuserPageSavebutn")))
			{
				getObject("CreateuserPageSavebutn").click();
				Thread.sleep(13000L);
				System.out.println("script created the compensation record, Leave and other details successfully");
			}
		}
		catch(Throwable t)
		{
			t.getMessage().toString();
			t.getStackTrace().toString();
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
	
	public void keyDates(String DateOfNoticeOfTermination,String LeavingDate,String LastWorkingDate)throws Throwable
	{
		try
		{
			Thread.sleep(4000L);

			if(existsElementchkFor1mts(OR.getProperty("noticeOfTermination")))
			{
				getObject("noticeOfTermination").sendKeys("");
				String dateStr = DateOfNoticeOfTermination;
				dateFormaterMethod(dateStr);
				System.out.println("The stdate entered is  " +formattedDate);		
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
				System.out.println("The continous start date entered is" +formattedDate);		
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
			if(existsElementchkFor1mts(OR.getProperty("lastWorkingDate")))
			{
				getObject("lastWorkingDate").sendKeys("");
				String dateStr = LastWorkingDate;
				dateFormaterMethod(dateStr);
				System.out.println("The continous start date entered is" +formattedDate);		
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
	
	
	public void EnrDOB(String EmpDOB) throws Throwable
	{
		try
		{
			if(existsElementchkFor1mts(OR.getProperty("empDOB")))
			{
				getObject("empDOB").sendKeys("");
				String dateStr = EmpDOB;
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
				getObject("empDOB").sendKeys(formattedDate);
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}
	
	
	public void EntrFromdt(String fromDate) throws Throwable
	{
		try
		{
			if(existsElementchkFor1mts(OR.getProperty("fromDate")))
			{
				getObject("fromDate").sendKeys("");
				String dateStr = fromDate;
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
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}
	
	
	public void EntrTOdt(String toDate) throws Throwable
	{
		try
		{
			if(existsElementchkFor1mts(OR.getProperty("toDate")))
			{
				getObject("toDate").sendKeys("");
				String dateStr = toDate;
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
				getObject("toDate").sendKeys(formattedDate);
			}
     	}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}
	
	
	public void TestRejoinerchkbox(String Rejoiner)throws Throwable
	{
		boolean	Rejoinerchekbox = getObject("rejoinerLocator").isSelected();
		double inttpp = Double.parseDouble(Rejoiner);
		Thread.sleep(2000L);
		if(existsElement(OR.getProperty("rejoinerLocator")))
		{
			if(inttpp==0.0)
			{
				isRejoinerchecked(Rejoinerchekbox);
				Thread.sleep(2000L);
				getObject("clikOutside").click();
				Thread.sleep(2000L);
				getObject("CreateJoiner&Next").click();
				Thread.sleep(10000L);
				System.out.println("Save button clicked successfully");
			}
			else if(inttpp==1.0)
			{
				System.out.println("The Rejoiner checkbox needs to be checked");
				Thread.sleep(2000L);
				getObject("clikOutside").click();
				Thread.sleep(2000L);
				getObject("CreateJoiner&Next").click();
				Thread.sleep(10000L);
				System.out.println("Save button clicked successfully");
			}
		}
	}

	
	public boolean isRejoinerchecked(boolean Rejoinerchekbox)throws Throwable
	{
		if(Rejoinerchekbox)
		{
			System.out.println("Rejoiner checkbox is already checked hence need to uncheck");
			getObject("rejoinerLocator").click();
			System.out.println("Rejoiner checkbox is unchecked successfully");
		}
		else
		{
			System.out.println("Rejoiner checkbox was not Checked hence satisfying the condition");
			Thread.sleep(2000L);
		}
		return Rejoinerchekbox;
	}
	
	
	public boolean isNIchecked(boolean NI)throws Throwable
	{
		if(NI)
		{
			System.out.println("Allowance Niable checkbox is already checked");
		}
		else
		{
			getObject("AllownceNiable").click();
			System.out.println("Allowance NIable checkbox was not checked But now is checked successfully");
			Thread.sleep(2000L);
		}
		return NI;
	}
}
