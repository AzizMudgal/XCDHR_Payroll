package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.AutoEnrolment_Opt_in;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
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

public class UpdateCompensationRecord extends TestSuiteBase {

	String runmodes[] = null;
	static int count = -1;
	static int countCompensation = -1;

	public static boolean Fail=false;
	public static boolean Skip=false;
	public static boolean IsTestPass=true;
	public String firstRowOfCompnRecord;



	@BeforeTest
	public void CheckTestSkip() throws Exception
	{
		if(! Test_Util.IsTestcaseRunMode(Payroll_AutoEnrolment_Employee_OptIn_SuiteXls, this.getClass().getSimpleName())){

			Skip=true;
			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Employee_OptIn_SuiteXls, "first", Test_Util.GetRowNum(Payroll_AutoEnrolment_Employee_OptIn_SuiteXls, this.getClass().getSimpleName()),"Skipped");
			//Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Employee_OptIn_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase" +this.getClass().getSimpleName() +" as the runmode is set to 'no' ");// this message would display in logs
			throw new Exception("Testcase is being skipped" + this.getClass().getSimpleName()+ "as it's Runmode is set to 'NO'"); // this msg would display in Reports.
		}
		// Load the runmodes of the tests
		runmodes=Test_Util.getDataSetRunmodes(Payroll_AutoEnrolment_Employee_OptIn_SuiteXls, this.getClass().getSimpleName());
	}

	public String payfreqncy;
	boolean employeeFirsttimeView = true;
	boolean compensationFirsttimeView = true;
	boolean shouldOpenBrowser = true; 


	@Test(dataProvider = "getData")
	public void ToUpdateCompnRecords(String EmpName,String PensionJoinNotice,String PensionJoinDate) throws Throwable
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
		ModifyCompenstionRecord(EmpName,PensionJoinNotice,PensionJoinDate);

		/*************************************************************************/

	}




	public void ModifyCompenstionRecord(String EmpName,String PensionJoinNotice,String PensionJoinDate) throws Throwable
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
			System.out.println("from compensation record");
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}

		Thread.sleep(3000L);
		try
		{


			if(existsElement(OR.getProperty("rewardtabClk")))
			{
				RewardTab(EmpName,PensionJoinNotice,PensionJoinDate);
			}
			System.out.println("Reward tab validation completed");
			Thread.sleep(6000L);
			/*
			 * when passing the argument to the 'ReadsExpectedData' method , first declare the public string at the top and use it in the method as argument.
			 * But keep in mind, you are passing the arguments in the same order (sequence) that of method parameters
			 */
			
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




	public void RewardTab(String EmpName,String PensionJoinNotice,String PensionJoinDate)throws Throwable
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

			if(existsElement(OR.getProperty("someTextIncomppage")))
			{
				System.out.println("the text exist");
				if(existsElement(OR.getProperty("compensationTableLocator")))
				{
					WebElement postsTable = driver.findElement(By.xpath(OR.getProperty("compensationTableLocator")));
					//WebTable table = WebTable.getTable(postsTable);
					List<WebElement> rows = postsTable.findElements(By.xpath(OR.getProperty("compensationTableRowsLocator")));
					int ttrows= rows.size();
					System.out.println("Total compensation records are :"+ttrows);
					java.util.Iterator<WebElement> x = rows.iterator();
					int rownumv = 2;	
					endSearchingCompnRecord:
						while(x.hasNext())
						{
							System.out.println("the index of rownumv is  :"+rownumv);
							if(existsElement(OR.getProperty("compenElementrow")))
							{
								firstRowOfCompnRecord="//tr/td/span[4]/table/tbody/tr/td[2]/table/tbody/tr[2]/td[7]/table/tbody/tr/td/div[1]/div/div[2]/table/tbody/"+"tr["+rownumv+"]"+"/td[1]/a";

								WebElement compensationlink= driver.findElement(By.xpath(firstRowOfCompnRecord));
								compensationlink.click();
								System.out.println("compensation link got clicked");
								if(existsElement(OR.getProperty("compnEditButton")))
								{
									getObject("compnEditButton").sendKeys("");
									getObject("compnEditButton").click();
								}

								if(existsElement(OR.getProperty("pensionJoinNotice")))
								{
									Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("pensionJoinNotice"))));
									// This select by value needs to be called from OR.Properties
									selectByValue.selectByValue(PensionJoinNotice);
								}

								if(existsElement(OR.getProperty("pensionJoinDate")))
								{
									keyDates(PensionJoinDate);
								}

								if(existsElement(OR.getProperty("compSaveeButton")))
								{
									getObject("compSaveeButton").sendKeys("");
									getObject("compSaveeButton").click();
								}

								rownumv +=1;
								if(rownumv == 3)
								{
									System.out.println("The method has searched the 1 required compensation record,"
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




	public void keyDates(String PensionJoinDate)throws Throwable
	{
		try
		{

			if(existsElement(OR.getProperty("pensionJoinDate")))
			{
				getObject("pensionJoinDate").sendKeys("");
				String dateStr = PensionJoinDate;
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
				getObject("pensionJoinDate").sendKeys(formattedDate);


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
		return Test_Util.getData(Payroll_AutoEnrolment_Employee_OptIn_SuiteXls,"UpdateCompensationRecord");
	}





	@AfterMethod
	public void ReportDataSetResult()
	{
		if(Skip)
		{
			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Employee_OptIn_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
		}
		else if(Fail)
		{
			IsTestPass = false;
			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Employee_OptIn_SuiteXls, this.getClass().getSimpleName(), count+2, "Fail");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Employee_OptIn_SuiteXls, this.getClass().getSimpleName(), count+2, "Pass");
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
			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Employee_OptIn_SuiteXls, "first", Test_Util.GetRowNum(Payroll_AutoEnrolment_Employee_OptIn_SuiteXls, this.getClass().getSimpleName()),"Pass");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Employee_OptIn_SuiteXls, "first", Test_Util.GetRowNum(Payroll_AutoEnrolment_Employee_OptIn_SuiteXls, this.getClass().getSimpleName()),"Fail");
		}	
		closeBrowser();
	}

}
