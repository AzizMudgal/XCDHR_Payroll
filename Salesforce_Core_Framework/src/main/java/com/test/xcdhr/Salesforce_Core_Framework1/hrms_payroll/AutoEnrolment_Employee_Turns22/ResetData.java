package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.AutoEnrolment_Employee_Turns22;




import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.Alert;
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

import atu.webdriver.utils.table.WebTable;
import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Test_Util;



public class ResetData extends TestSuiteBase {

	String runmodes[] = null;
	static int count = -1;
	static int countCompensation = -1;

	public static boolean Fail=false;
	public static boolean Skip=false;
	public static boolean IsTestPass=true;
	public String firstRowOfCompnRecord;
	public String effectiveFrom;
	public String AutoEnrolNotifyAttahment;
	public String AutoEnrolNotifyAttahmentFalse;
	public String RowOfAttachementRecord;
	

	@BeforeTest
	public void CheckTestSkip() throws Exception
	{
		if(! Test_Util.IsTestcaseRunMode(Payroll_AutoEnrolment_Employee_Turns22_SuiteXls, this.getClass().getSimpleName())){

			Skip=true;
			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Employee_Turns22_SuiteXls, "first", Test_Util.GetRowNum(Payroll_AutoEnrolment_Employee_Turns22_SuiteXls, this.getClass().getSimpleName()),"Skipped");
			//Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Employee_Turns22_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase" +this.getClass().getSimpleName() +" as the runmode is set to 'no' ");// this message would display in logs
			throw new Exception("Testcase is being skipped" + this.getClass().getSimpleName()+ "as it's Runmode is set to 'NO'"); // this msg would display in Reports.
		}
		// Load the runmodes of the tests
		runmodes=Test_Util.getDataSetRunmodes(Payroll_AutoEnrolment_Employee_Turns22_SuiteXls, this.getClass().getSimpleName());
	}

	public String payfreqncy;
	boolean employeeFirsttimeView = true;
	boolean compensationFirsttimeView = true;
	boolean shouldOpenBrowser = true; 


	@Test(dataProvider = "getData")
	public void ToUpdateCompnRecords(String EmpName,String notfnchkbox,String payrollfrequency,String Effctivefrom,String annualSalary,String empJobrole) throws Throwable
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
		DeleteCompensation2Record(EmpName,notfnchkbox,payrollfrequency,Effctivefrom,annualSalary,empJobrole);

		/*************************************************************************/

	}




	public void DeleteCompensation2Record(String EmpName,String notfnchkbox,String payrollfrequency,String Effctivefrom,String annualSalary,String empJobrole) throws Throwable
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
			/*
			 * call the personal tab for unchecking the notification check box and deleting the 
			 * attachements.
			 */
					
			
			if(existsElement(OR.getProperty("AutoEnrolNotifyNoticeTable")))
			{
				deleteAttachments();
			}
			
			
			if(existsElement(OR.getProperty("personalEditButtonLoctor")))
			{
				uncheckNotificationChkbox(notfnchkbox);
			}
			
			
			if(existsElement(OR.getProperty("rewardtabClk")))
			{
				RewardTab(EmpName,payrollfrequency,Effctivefrom,annualSalary,empJobrole);
			}
			
			if(existsElement(OR.getProperty("compensationButtoncomppage")))
			{
				createCompnRecord(EmpName,payrollfrequency,Effctivefrom,annualSalary,empJobrole);
				System.out.println("Finally the Reset functionality got completed successfully");
				Thread.sleep(6000L);
			}
			
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




	public void RewardTab(String EmpName,String payrollfrequency,String Effctivefrom,String annualSalary,String empJobrole)throws Throwable
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

			if(existsElement(OR.getProperty("compensationButtoncomppage")))
			{
				System.out.println("the comp button exist");
				if(existsElement(OR.getProperty("compensationTableLocator")))
				{
					WebElement postsTable = driver.findElement(By.xpath(OR.getProperty("compensationTableLocator")));
					//WebTable table = WebTable.getTable(postsTable);
					List<WebElement> rows = postsTable.findElements(By.xpath(OR.getProperty("compensationTableRowsLocator")));
					int ttrows= rows.size();
					System.out.println("Total compensation records are :"+ttrows);
					java.util.Iterator<WebElement> x = rows.iterator();
					int rownumv = rows.size();	
					endSearchingCompnRecord:
						while(x.hasNext())
						{
							System.out.println("the index of rownumv is  :"+rownumv);
							firstRowOfCompnRecord="//div[contains(@id, 'CompensationBlock')]/div[2]/table/tbody/"+"tr["+rownumv+"]"+"/td[1]/a";
									
							Thread.sleep(3000L);
						
							WebElement 	compensationlink= driver.findElement(By.xpath(firstRowOfCompnRecord));
							if(existsElement(OR.getProperty("compensationTableLocator")))
							{
								   	compensationlink.click();
									System.out.println("compensation link got clicked");
							    	if(existsElement(OR.getProperty("compensationDeleteLoctor")))
									{
										getObject("compensationDeleteLoctor").sendKeys("");
										getObject("compensationDeleteLoctor").click();
										Thread.sleep(3000L);
										Alert alert = driver.switchTo().alert();
										alert.accept();
										System.out.println("The compensation record got deleted");
									}
							   							    
							    rownumv--;
							    if(rownumv == 0)
								{
									System.out.println("All the existing records got deleted,"
										+ "hence now the script creates new compensation record(s) as per the requirment specification");
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

	
	

	public void uncheckNotificationChkbox(String notfnchkbox)throws Throwable
	{
		try
		{
			if(existsElement(OR.getProperty("personalEditButtonLoctor")))
			{
				getObject("personalEditButtonLoctor").sendKeys("");
				getObject("personalEditButtonLoctor").click();
				Thread.sleep(3000L);
			}
			
			if(existsElement(OR.getProperty("autoEnrolmentDatefield")))
			{
				getObject("autoEnrolmentDatefield").sendKeys("");
				getObject("autoEnrolmentDatefield").clear();
				System.out.println("The auto enrolment date got cleared");
				Thread.sleep(3000L);
			}
			
			
			
			if(existsElement(OR.getProperty("autoEnrolmentNotfnChkbox")))
			{
				AutoEnrlNotfnChkbox(notfnchkbox);
			}
			
			if(existsElement(OR.getProperty("personalSavebutonLocator")))
			{
				getObject("personalSavebutonLocator").sendKeys("");
				getObject("personalSavebutonLocator").click();
			}
			
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
		}
	}
	
	
	
	public void AutoEnrlNotfnChkbox(String notfnchkbox)throws Throwable
	{

		try
		{
			boolean	autoEnrolmtChkboxx = getObject("autoEnrolmentNotfnChkbox").isSelected();
			double valueOfsmallReliefChkbox = Double.parseDouble(notfnchkbox);
			System.out.println("converted smallER value is :"+valueOfsmallReliefChkbox);
			if(valueOfsmallReliefChkbox==1.0)
			{
				Thread.sleep(4000L);
				uncheckAEChkbox(autoEnrolmtChkboxx);
				System.out.println("The auto enrolment notification check box got unchecked successfully");
				
			}

		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}

	}


	public void deleteAttachments()throws Throwable
	{
		try
		{
			if(existsElement(OR.getProperty("AutoEnrolNotifyNoticeTable")))
			{
				WebElement AEnotifyNoticeTablelocator = driver.findElement(By.xpath(OR.getProperty("AutoEnrolNotifyNoticeTable")));
				WebTable NotifyNoticetable = WebTable.getTable(AEnotifyNoticeTablelocator);
				AutoEnrolNotifyAttahment= NotifyNoticetable.getTBody().getRow(0).getCell(0).getText();
				System.out.println("The Enrolment Notify Notice text is :"+AutoEnrolNotifyAttahment);
				
				List<WebElement> rows = AEnotifyNoticeTablelocator.findElements(By.xpath(OR.getProperty("AutoEnrolNotifyNoticeTableRows")));
				int ttrows= rows.size();
				System.out.println("Total attachment records are :"+ttrows);
				java.util.Iterator<WebElement> x = rows.iterator();
				int rownumv = rows.size();	
				endSearchingCompnRecord:
					while(x.hasNext())
					{
						System.out.println("the index of rownumv is  :"+rownumv);
													
						if(existsElement(OR.getProperty("AutoEnrolNotifyNoticeTable")))
						{
							RowOfAttachementRecord="//form[3]/div[1]/div/div[2]/div[1]/div/table/tbody/tr[2]/td/table/tbody/"+"tr["+rownumv+"]"+"/td[1]/a";
							WebElement attachmentlink= driver.findElement(By.xpath(RowOfAttachementRecord));
							
							// Store the current window handle
							String winHandleBefore = driver.getWindowHandle();

							attachmentlink.click();
							System.out.println("attachment link got clicked");
							// Switch to new window opened
							for(String winHandle : driver.getWindowHandles()){
							    driver.switchTo().window(winHandle);
							}
							
							if(existsElement(OR.getProperty("attachmentDeleteButtonLoctor")))
							{
								getObject("attachmentDeleteButtonLoctor").sendKeys("");
								getObject("attachmentDeleteButtonLoctor").click();
								System.out.println("The attachment got deleted successfully");
							}
							
							// Close the new window, if that window no more required
							driver.close();
							
							
							// Switch back to original browser (first window)
							driver.switchTo().window(winHandleBefore);
							if(existsElement(OR.getProperty("AutoEnrolNotifyNoticeTable")))
							{
								driver.navigate().refresh();
								System.out.println("The page got refreshed");
							}
						}
						rownumv--;
						
						if(rownumv==0)
						{
							System.out.println("There are no attachments to delete");
							break endSearchingCompnRecord;
						}
					}
			}
			else if(!existsElement(OR.getProperty("AutoEnrolNotifyNoticeTable")))
			{
				System.out.println("Threre are no Attachement records to delete");
				
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}
	
	
	public boolean uncheckAEChkbox(boolean autoEnrolmtChkboxx)throws Throwable
	{
		if(autoEnrolmtChkboxx)
		{
			Thread.sleep(2000L);
			getObject("autoEnrolmentNotfnChkbox").click();
			System.out.println("Condition checkbox got unchecked successfully");
			
		}
		else
		{
			System.out.println("Small Employer relief checkbox was allready unchecked, Hence our condition got satisfied");


		}
		return autoEnrolmtChkboxx;
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


	public void createCompnRecord(String EmpName,String payrollfrequency,String Effctivefrom,String annualSalary,String empJobrole)throws Throwable
	{
		try
		{
			if(existsElement(OR.getProperty("compensationButtoncomppage")))
			{
				System.out.println("the comp button exist");
				getObject("compensationButtoncomppage").sendKeys("");
				getObject("compensationButtoncomppage").click();
				if(existsElement(OR.getProperty("compnPayrollFrqncy")))
				{
					Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("compnPayrollFrqncy"))));
					// This select by value needs to be called from OR.Properties
					selectByValue.selectByValue(payrollfrequency);
					System.out.println("Monthly payroll frequency got selected");
				}
				
				if(existsElement(OR.getProperty("compnEffctiveFrom")))
				{
					if(existsElement(OR.getProperty("compnEffctiveFrom")))
					{
						
						getObject("compnEffctiveFrom").sendKeys("");
						String dateStr = Effctivefrom;
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
						getObject("compnEffctiveFrom").sendKeys(formattedDate);
										
					}
					
				}
				
				if(existsElement(OR.getProperty("compnannualSalary")))
				{
					getObject("compnannualSalary").sendKeys("");
					getObject("compnannualSalary").clear();
					getObject("compnannualSalary").sendKeys(annualSalary);
				}
				
				if(existsElement(OR.getProperty("compnJobrollLookup")))
				{
					getObject("compnJobrollLookup").sendKeys("");
					getObject("compnJobrollLookup").click();
					String mainHandle3 = driver.getWindowHandle(); // To save the parent window
					// create one more method for reading employee from excel sheet.
					ReadEmployeeJobRole(EmpName,empJobrole);
					
					driver.switchTo().window(mainHandle3); // finally switch back to parent window and perform the operations.
					Thread.sleep(2000L);
				}
				
				if(existsElement(OR.getProperty("compnSaveButton")))
				{
					getObject("compnSaveButton").sendKeys("");
					getObject("compnSaveButton").click();
					
					Thread.sleep(3000L);
					String clkToCompnOkbutton = driver.getWindowHandle();
					if(existsElement(OR.getProperty("compnCreationOkbutton")))
					{
						getObject("compnCreationOkbutton").sendKeys("");
						getObject("compnCreationOkbutton").click();
					}
					
					driver.switchTo().window(clkToCompnOkbutton);
					
					System.out.println("The compensation record got created");
				}
			}
			
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}
	

	public void ReadEmployeeJobRole(String EmpName,String empJobrole)throws Throwable
	{
		
		String[] handles = driver.getWindowHandles().toArray(new String[0]); // To get the child window(s)
		driver.switchTo().window(handles[handles.length - 1]); 
			
				String axb=  driver.getTitle();
				System.out.println(axb);
				if (driver.getTitle().equalsIgnoreCase(axb))
				{
					
						driver.switchTo().defaultContent();        // now that you encountered one more frame hence switch back to main page
						Thread.sleep(2000);					
						WebElement dddframe2a = driver.findElement(By.id("resultsFrame"));// and save the frame id and 
						driver.switchTo().frame(dddframe2a); // switch to the other frame and perform the operations
						System.out.println("I switched to show all Results ");
						if(existsElement(OR.getProperty("showAllResults")))
						{
							getObject("showAllResults").sendKeys("");
							getObject("showAllResults").click();
							
							if(existsElement(OR.getProperty("compnJobrolefirstrowTable")))
							{
								WebElement JobRoleTable = driver.findElement(By.xpath(OR.getProperty("compnJobrolefirstrowTable")));

								List<WebElement> rows = JobRoleTable.findElements(By.xpath(OR.getProperty("compnJobrolefirstrowTableRows")));
								java.util.Iterator<WebElement> x = rows.iterator();
								int rownum = 2;			
								while(x.hasNext())
								{
									System.out.println("The rownum count is  :"+rownum);
									String empRecord="//*[@id='new']/div/div[2]/div[2]/div/div[2]/table/tbody/"+"tr["+rownum+"]"+"/td[1]";
									WebElement empwebelement= driver.findElement(By.xpath(empRecord));
									String AppnEmp= empwebelement.getText();
									//System.out.println(tempEmp+"-------"+empName+"------"+rownum);
									if(AppnEmp!=null && AppnEmp.equalsIgnoreCase(EmpName))
									{
										String JobRoleRecord="//*[@id='new']/div/div[2]/div[2]/div/div[2]/table/tbody/"+"tr["+rownum+"]"+"/th/a";
										WebElement JobRoleClk= driver.findElement(By.xpath(JobRoleRecord));
										System.out.println("Employee matched");
										System.out.println("Employee name is  :"+AppnEmp);
										System.out.println("I clicked the user finally");
										Thread.sleep(2000);		
										JobRoleClk.click();
										break;
									}
									rownum++;
								}
								
							}
						}
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
		return Test_Util.getData(Payroll_AutoEnrolment_Employee_Turns22_SuiteXls,"ResetData");
	}





	@AfterMethod
	public void ReportDataSetResult()
	{
		if(Skip)
		{
			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Employee_Turns22_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
		}
		else if(Fail)
		{
			IsTestPass = false;
			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Employee_Turns22_SuiteXls, this.getClass().getSimpleName(), count+2, "Fail");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Employee_Turns22_SuiteXls, this.getClass().getSimpleName(), count+2, "Pass");
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
			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Employee_Turns22_SuiteXls, "first", Test_Util.GetRowNum(Payroll_AutoEnrolment_Employee_Turns22_SuiteXls, this.getClass().getSimpleName()),"Pass");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Employee_Turns22_SuiteXls, "first", Test_Util.GetRowNum(Payroll_AutoEnrolment_Employee_Turns22_SuiteXls, this.getClass().getSimpleName()),"Fail");
		}	
		closeBrowser();
	}

}
