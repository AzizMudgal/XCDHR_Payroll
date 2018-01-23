package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.rti_Payroll_Scenario1_Month1;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class RTICore extends TestSuiteBase
{
	boolean MyCompany = true;
	
	
	public void CreateNewJoiner(String empFirstName, String LastName,String Email, String UserName, String WorkMobile, String WorkPhone, String Profile, String ActivateLicense,String JobTitle, String Post,String Company,String EmploymentType, String Location, String EmploymentStatus, String Department, String PatternType, String NoOfWorkingDays, String ContractualHours, String SpinalPoint, String Manager, String StartDate, String ContinousStdate, String PayrollStDate, String Rejoiner) throws Throwable
	{
		if(MyCompany)
		{
			try
			{
				if(existsElement(OR.getProperty("MyCompanyTab")))
				{
					getObject("MyCompanyTab").click();
				}
									
				if(existsElement(OR.getProperty("NewJoiner")))
				{
					System.out.println("I am in MyCompany page");
					Thread.sleep(1000L);
					getObject("NewJoiner").click();
					Thread.sleep(4000L);
				}
				
				if(existsElement(OR.getProperty("FirstName")))
				{
					getObject("FirstName").sendKeys(empFirstName);
				}
				Thread.sleep(2000L);
				if(existsElement(OR.getProperty("LastName")))
				{
					getObject("LastName").sendKeys(LastName);
				}
				Thread.sleep(2000L);
				if(existsElement(OR.getProperty("Email")))
				{
					getObject("Email").sendKeys(Email);
				}
				Thread.sleep(2000L);
				if(existsElement(OR.getProperty("UserName")))
				{
					getObject("UserName").sendKeys(UserName);
				}
				Thread.sleep(2000L);
				if(existsElement(OR.getProperty("WorkMobile")))
				{
					getObject("WorkMobile").sendKeys(WorkMobile);
				}
				Thread.sleep(2000L);
				if(existsElement(OR.getProperty("WorkPhone")))
				{
					getObject("WorkPhone").sendKeys(WorkPhone);
				}
				Thread.sleep(2000L);
				if(existsElement(OR.getProperty("ActivateLicense")))
				{
					Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("ActivateLicense"))));
					// This select by value needs to be called from OR.Properties
					selectByValue.selectByValue(ActivateLicense);
				}
			}
			catch(Throwable t)
			{
				System.out.println(t.getMessage().toString());
				System.out.println(t.getStackTrace().toString());
			}
		}	
		Thread.sleep(2000L);
		if(existsElement(OR.getProperty("JobTitle")))
		{
			getObject("JobTitle").sendKeys(JobTitle);
		}
			/*
			Thread.sleep(5000);
			getObject("PostLookup").click();
			double postvalue = Double.parseDouble(Post);
			DecimalFormat df = new DecimalFormat("###.#");
			String postconvert= df.format(postvalue);
			System.out.println("The converted post value is  :"+postconvert);
			Thread.sleep(5000);
			String mainHandle = driver.getWindowHandle(); // To save the parent window
			// create one more method for reading employee from excel sheet.
			ReadEmployee(postconvert);
			Thread.sleep(2000L);
			driver.switchTo().window(mainHandle); // finally switch back to parent window and perform the operations.
			*/
			Thread.sleep(2000L);
			if(existsElement(OR.getProperty("Companylistbox")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("Companylistbox"))));
				// This select by value needs to be called from OR.Properties
				selectByValue.selectByValue(Company);
			}
			Thread.sleep(2000L);
			if(existsElement(OR.getProperty("EmplymentType")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("EmplymentType"))));
				// This select by value needs to be called from OR.Properties
				selectByValue.selectByValue(EmploymentType);
			}
			Thread.sleep(2000L);
			if(existsElement(OR.getProperty("Location")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("Location"))));
				// This select by value needs to be called from OR.Properties
				selectByValue.selectByValue(Location);
			}
			Thread.sleep(2000L);
			if(existsElement(OR.getProperty("EmplymntStatus")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("EmplymntStatus"))));
				// This select by value needs to be called from OR.Properties
				selectByValue.selectByValue(EmploymentStatus);
			}
			Thread.sleep(5000);
			getObject("Departmentlookup").click();
			Thread.sleep(5000);
			String mainHandle2 = driver.getWindowHandle(); // To save the parent window
			// create one more method for reading employee from excel sheet.
			ReadEmployee(Department);
			Thread.sleep(2000L);
			driver.switchTo().window(mainHandle2); // finally switch back to parent window and perform the operations.
			Thread.sleep(2000L);
			if(existsElement(OR.getProperty("PatternType")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("PatternType"))));
				// This select by value needs to be called from OR.Properties
				selectByValue.selectByValue(PatternType);
			}
			Thread.sleep(2000L);
			if(existsElement(OR.getProperty("NoWorkingDays")))
			{
				getObject("NoWorkingDays").clear();
				Thread.sleep(1000L);
				getObject("NoWorkingDays").sendKeys(NoOfWorkingDays);
			}
			Thread.sleep(2000L);
			if(existsElement(OR.getProperty("ContractualHours")))
			{
				getObject("ContractualHours").clear();
				getObject("ContractualHours").sendKeys(ContractualHours);
			}
			/*
			Thread.sleep(2000L);
			if(existsElement(OR.getProperty("SpinalPoint")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("SpinalPoint"))));
				// This select by value needs to be called from OR.Properties
				selectByValue.selectByValue(SpinalPoint);
			}
			*/
			Thread.sleep(2000);
			getObject("SelectManager").click();
			Thread.sleep(5000);
			String mainHandle3 = driver.getWindowHandle(); // To save the parent window
			// create one more method for reading employee from excel sheet.
			ReadEmployee(Manager);
			Thread.sleep(2000L);
			driver.switchTo().window(mainHandle3); // finally switch back to parent window and perform the operations.
			Thread.sleep(2000L);
			//// still emp start date etc needs to be input.
			System.out.println("Still emp start date needs to input");
			keyDates(StartDate,ContinousStdate,PayrollStDate);
			
			Thread.sleep(2000L);
			getObject("clikOutside").click();
			Thread.sleep(2000L);
			getObject("CreateJoiner&Next").click();
			Thread.sleep(10000L);
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
			/*
			 * This code is used to remove the decimal part with dot
			 */
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
			/*
			Thread.sleep(2000L);
			if (existsElement(OR.getProperty("AddnalContrctualLeave")))
			{
				getObject("AddnalContrctualLeave").sendKeys(AddnalContrctualLeave);
			}
			*/
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
			/*
			Thread.sleep(2000L);
			
			if (existsElement(OR.getProperty("WorkingDays")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("WorkingDays"))));
				// This select by value needs to be called from OR.Properties
				selectByValue.selectByValue(WorkingDays);
			}
			*/
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
			
			/*
			
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
			*/
			Thread.sleep(2000L);
			if (existsElement(OR.getProperty("period")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("period"))));
				// This select by value needs to be called from OR.Properties
				selectByValue.selectByValue(Period);
			}
			Thread.sleep(2000L);
			
			/*
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
			*/
			
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
	
	
	public void ReadEmployee(String Post)throws Throwable
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
			getObject("searchField").sendKeys(Post);
			System.out.println("I entered the empname reading from excel sheet");
			Thread.sleep(2000);
			getObject("Gobutton").click();
			System.out.println("I clicked Go button");
			Thread.sleep(5000);
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

	
	
	public void keyDates(String StartDate,String ContinousStdate,String PayrollStDate)throws Throwable
	{
		try
		{
			if(existsElement(OR.getProperty("StDate")))
			{
				getObject("StDate").sendKeys("");
				String dateStr = StartDate;
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
				getObject("StDate").sendKeys(formattedDate);
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	
		try
		{
			if(existsElement(OR.getProperty("continousServiceDate")))
			{
				getObject("continousServiceDate").sendKeys("");
				String dateStr = ContinousStdate;
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
				getObject("continousServiceDate").sendKeys(formattedDate);
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
			if(existsElement(OR.getProperty("payrollStartDate")))
			{
				getObject("payrollStartDate").sendKeys("");
				String dateStr = PayrollStDate;
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
				getObject("payrollStartDate").sendKeys(formattedDate);
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
			if(existsElement(OR.getProperty("empDOB")))
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
			if(existsElement(OR.getProperty("fromDate")))
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
			if(existsElement(OR.getProperty("toDate")))
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
