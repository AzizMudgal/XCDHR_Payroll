package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.hrms_Payroll_SMP_Statutory_Scenario;


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

public class SmallEmployerRelief extends TestSuiteBase
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

	@BeforeTest
	public void CheckTestSkip() throws Throwable{
		processDesiredTaxYearInputExcelFile(TaxYear);

		if(! Test_Util.IsTestcaseRunMode(Payroll_Statutory_maternitypay_SuiteXls, this.getClass().getSimpleName())){

			Skip=true;
			Test_Util.ReportDataSetResult(Payroll_Statutory_maternitypay_SuiteXls, "first", Test_Util.GetRowNum(Payroll_Statutory_maternitypay_SuiteXls, this.getClass().getSimpleName()),"Skipped");
			//Test_Util.ReportDataSetResult(Payroll_Statutory_maternitypay_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase" +this.getClass().getSimpleName() +" as the runmode is set to 'no' ");// this message would display in logs

			throw new Exception("Testcase is being skipped" + this.getClass().getSimpleName()+ "as it's Runmode is set to 'NO'"); // this msg would display in Reports.

		}

		// Load the runmodes of the tests

		runmodes=Test_Util.getDataSetRunmodes(Payroll_Statutory_maternitypay_SuiteXls, this.getClass().getSimpleName());

	}
	
	public String payfreqncy;
	boolean employeeFirsttimeView = true;
	boolean AllowanceFirsttimeView = true;
	boolean shouldOpenBrowser = true; 
	boolean MyCompany = true;
	
	@Test(dataProvider="getData")
	public void EmpsSetup_WithNICategory(String EmpName,String SmallEmployerRelief) throws Throwable
	{								  //String empFirstName, String LastName,String Email, String UserName, String WorkMobile, String WorkPhone, String Profile, String ActivateLicense, String Post,String Company,String EmploymentType, String Location, String EmploymentStatus, String Department, String PatternType, String NoOfWorkingDays, String ContractualHours, String SpinalPoint, String Manager, String StartDate, String ContinousStdate, String PayrollStDate, String Rejoiner,  String EmpDOB,String Gender,String Nationality,String FromDate,String ToDate,String Address1,String  Address2,String Street,String City,String Country,String PostCode,String Region,String ParentLocation,String AddnalContrctualLeave,String MinimumYrsService,String HoursAM,String HoursPM,String WorkingDays,String AnnualSalary,String Bonus,String BonusOTE,String Commission,String CommissionOTE,String EmpContrbnPenSal,String EmployerContrbPenSal,String AddnalEmplyeeContrbn,String AddnalEmployerContrbn,String bonusNotes,String DailyRateOfPay,String Departmentt,String EmployeeSalarySacrifice,String EmployeeContbnlnLeiu,String Payfrequency,String CreateLeaveYrs
		//APP_LOGS.debug("Entering the Leave parameters");
		//APP_LOGS.debug(EmpName+"--"+NICategory+"--"+AnnualSalary+"--"+PayFrequency);
		processDesiredTaxYearInputExcelFile(TaxYear);

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
		
		FetchEmployeeRecord(EmpName,SmallEmployerRelief);

	/*************************************************************************/

}


public void FetchEmployeeRecord(String EmpName,String SmallEmployerRelief) throws Throwable
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
	
	empEmploymentTab(SmallEmployerRelief);

}

	public void empEmploymentTab(String SmallEmployerRelief)throws Throwable
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
			if(existsElement(OR.getProperty("JobTitleLocator")))
			{
				getObject("JobTitleLocator").sendKeys("");
				getObject("JobTitleLocator").click();
				System.out.println("The Job Title got clicked");
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
			WebElement postsTable = driver.findElement(By.xpath(OR.getProperty("jobRoleTablelocator")));
			if(existsWebElement(postsTable))
			{
				System.out.println("details table exists");
				List<WebElement> rows = postsTable.findElements(By.xpath(OR.getProperty("jobRoleTablelocatorrows")));
				
				  System.out.println("NUMBER OF ROWS IN THIS TABLE = "+rows.size());
        int row_num,col_num;
        row_num=1;
        for(WebElement trElement : rows)
        {
            List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
            System.out.println("NUMBER OF COLUMNS="+td_collection.size());
            col_num=1;
            for(WebElement tdElement : td_collection)
            {
                System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
             
                if(tdElement.getText()!=null && tdElement.getText().equalsIgnoreCase("DO NOT TOUCH AUTO ENROLMENT TEST COMPANY 1"))
				{																	
					System.out.println("Company name  :"+tdElement.getText()+ "  matched ");
					tdElement.sendKeys("");
					tdElement.click();
					System.out.println("The Company  got clicked successfully");
					break;
				}
                col_num++;
            }
            row_num++;
        } 
				
				
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
		Thread.sleep(4000L);
			WebElement postsTable1 = driver.findElement(By.xpath(OR.getProperty("employerTableLocator")));
			if(existsWebElement(postsTable1))
			{
				List<WebElement> rows1 = postsTable1.findElements(By.xpath(OR.getProperty("employerTableLocatorRows")));
				java.util.Iterator<WebElement> x1 = rows1.iterator();
				rownum1 = 1;
								
				while(x1.hasNext())
				{
					System.out.println("the count is "+rownum1);
					try
					{							///table/tbody/tr[2]/td[2]/a
						String EmployerName = "//div[2]/div[5]/table/tbody/tr"+"["+rownum1+"]"+"/td[2]/a";
						if(existsElement(EmployerName))
						{
							System.out.println("Employer details table exists");
							WebElement tempElement1= driver.findElement(By.xpath(EmployerName));
							String tempEmp1= tempElement1.getText();
							System.out.println("Field name is :"+tempEmp1);
							//System.out.println(tempEmp+"-------"+empName+"------"+rownum);
							if(tempEmp1!=null && tempEmp1.equalsIgnoreCase("DO NOT TOUCH PAYROLL AUTOMATION EMPLOYER_17/18"))
							{											
								System.out.println("Employer name  :"+tempEmp1+ "  matched ");

								if(existsWebElement(tempElement1))
								{
									tempElement1.click();
									System.out.println("The employee namely :"+tempEmp1+"got clicked");
									break;
								}

							}
							else
							{
								System.out.println("Employer name did not matched");
							}
							
						}
					}
					catch(Throwable t)
					{
						System.out.println("the specified employer name does not exist in this row");
						System.out.println(t.getMessage().toString());
						System.out.println(t.getStackTrace().toString());
					}
					
					rownum1++;
				}
						
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
			if(existsElement(OR.getProperty("EditButtonLocator")))
			{
				getObject("EditButtonLocator").sendKeys("");
				getObject("EditButtonLocator").click();
				System.out.println("The Employer Edit button got clicked");
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
			ErrorUtil.addVerificationFailure(t);
			System.out.println("");	
		}
		
		selectCheckbox(SmallEmployerRelief);
	
		Thread.sleep(2000L);
		saveSmallEmployerRbtn();
		
	}
	
	
	
	public void toCheckSER(String SmallEmployerRelief)throws Throwable
	{
		try
		{
			boolean	smallERchekbox = getObject("SERcheckboxLocator").isSelected();
			double valueOfsmallReliefChkbox = Double.parseDouble(SmallEmployerRelief);
			System.out.println("converted smallER value is :"+valueOfsmallReliefChkbox);
			if(valueOfsmallReliefChkbox== 0.0)
			{
				if(existsElement(OR.getProperty("SERcheckboxLocator")))
				{
					Thread.sleep(4000L);
					issmallEmplyrchecBox(smallERchekbox);
				}
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}

	}

	
	

	public boolean issmallEmplyrchecBox(boolean smallERchekbox)throws Throwable
	{
		if(smallERchekbox)
		{
			System.out.println("Small Employer relief checkbox is already checked, Hence unchecking now");
			getObject("SERcheckboxLocator").click();
			System.out.println("Condition checkbox got unchecked successfully");
		}
		else
		{
			System.out.println("Small Employer relief checkbox was 'NOT Checked' by default, hence our condition got satisfied as per functioanlity requirement");
			Thread.sleep(2000L);
		}
		return smallERchekbox;
	}
	
	
	
	public void saveSmallEmployerRbtn()throws Throwable
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
	
	
	public void selectCheckbox(String SmallEmployerRelief)throws Throwable
	{
		try
		{
			WebElement postsTable = driver.findElement(By.xpath(OR.getProperty("employerDetailsTablelocator")));
			if(existsWebElement(postsTable))
			{
				System.out.println("details table exists");
				List<WebElement> rows = postsTable.findElements(By.xpath(OR.getProperty("employerDetailsTablelocatorRows")));
				
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
             
                if(tdElement.getText()!=null && tdElement.getText().equalsIgnoreCase("Small employer relief"))
				{
					System.out.println("Label name  :"+tdElement.getText()+ "  matched ");
					//String ckkkbox = "td/input";
					
					//String ckbox = "//label[contains(text(),'Small employer relief')]//following-sibling::td/input";
					String ckbox ="//following-sibling::td[2]/input[@type='checkbox'][@tabindex='11']";
					WebElement clkchkbox = driver.findElement(By.xpath(ckbox));
					boolean	smallERchekbox = clkchkbox.isSelected();
					double valueOfsmallReliefChkbox = Double.parseDouble(SmallEmployerRelief);
					System.out.println("converted smallER value is :"+valueOfsmallReliefChkbox);
					if(valueOfsmallReliefChkbox== 0.0)
					{
						
							Thread.sleep(4000L);
							if(smallERchekbox)
							{
								clkchkbox.sendKeys("");
								clkchkbox.click();
								System.out.println("Small Employer relief checkbox was allready checked,and now unchecked hence Condition now satisfied successfully");
								break  outerloop;
							}
							else
							{
								System.out.println("Small Employer relief checkbox was allready unchecked, Hence our condition got satisfied");
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
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
			ErrorUtil.addVerificationFailure(t);
			System.out.println("");	
		}
	}


		
		
		@DataProvider
		public Object[][] getData() throws Throwable
		{
			processDesiredTaxYearInputExcelFile(TaxYear);

			return Test_Util.getData(Payroll_Statutory_maternitypay_SuiteXls,"SmallEmployerRelief");
		}


		@AfterMethod
		public void ReportDataSetResult() throws Throwable
		{
			processDesiredTaxYearInputExcelFile(TaxYear);

			if(Skip){
				Test_Util.ReportDataSetResult(Payroll_Statutory_maternitypay_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			}else if(Fail)
			{

				IsTestPass = false;

				Test_Util.ReportDataSetResult(Payroll_Statutory_maternitypay_SuiteXls, this.getClass().getSimpleName(), count+2, "Fail");
			}else
			{
				Test_Util.ReportDataSetResult(Payroll_Statutory_maternitypay_SuiteXls, this.getClass().getSimpleName(), count+2, "Pass");
			}

			Skip=false;
			Fail=false;


		}


		@AfterTest
		public void ReportTestResult() throws Throwable{
			processDesiredTaxYearInputExcelFile(TaxYear);


			if(IsTestPass){

				// This will update the testresult in the first worksheet where in for that test case , even if one of the test data specified in second worksheet fails, the test 
				// would be considered as fail.And the same would be updated.

				Test_Util.ReportDataSetResult(Payroll_Statutory_maternitypay_SuiteXls, "first", Test_Util.GetRowNum(Payroll_Statutory_maternitypay_SuiteXls, this.getClass().getSimpleName()),"Pass");

			}else{

				Test_Util.ReportDataSetResult(Payroll_Statutory_maternitypay_SuiteXls, "first", Test_Util.GetRowNum(Payroll_Statutory_maternitypay_SuiteXls, this.getClass().getSimpleName()),"Fail");

			}	

			closeBrowser();
		}


		
		
	}

