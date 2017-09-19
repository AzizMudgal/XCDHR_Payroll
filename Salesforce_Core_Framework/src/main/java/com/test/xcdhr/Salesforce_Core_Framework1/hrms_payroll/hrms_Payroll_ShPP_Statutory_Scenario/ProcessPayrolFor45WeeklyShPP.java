package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.hrms_Payroll_ShPP_Statutory_Scenario;


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

import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Test_Util;

public class ProcessPayrolFor45WeeklyShPP extends TestSuiteBase
{
	String runmodes[] = null;
	static int count = -1;
	static int countCompensation = -1;
	public static boolean Fail = false;
	public static boolean Skip = false;
	public static boolean IsTestPass = true;
	public String payrollRecordId;
	public int rownum;
	public String weekOneRecordId;
	
	

	@BeforeTest
	public void CheckTestSkip() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		if (!Test_Util.IsTestcaseRunMode(Payroll_Statutory_SharedParentalpay_SuiteXls, this
				.getClass().getSimpleName()))
		{
			Skip = true;
			Test_Util.ReportDataSetResult(Payroll_Statutory_SharedParentalpay_SuiteXls, "first",
					Test_Util.GetRowNum(Payroll_Statutory_SharedParentalpay_SuiteXls, this
							.getClass().getSimpleName()), "Skipped");
			// Test_Util.ReportDataSetResult(Payroll_Statutory_SharedParentalpay_SuiteXls,
			// this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase"
					+ this.getClass().getSimpleName()
					+ " as the runmode is set to 'no' ");// this message would
			// display in logs

			throw new Exception("Testcase is being skipped"
					+ this.getClass().getSimpleName()
					+ "as it's Runmode is set to 'NO'"); // this msg would
			// display in
			// Reports.
		}
		// Load the runmodes of the tests
		runmodes = Test_Util.getDataSetRunmodes(Payroll_Statutory_SharedParentalpay_SuiteXls, this
				.getClass().getSimpleName());
	}

	public String payfreqncy;
	boolean exlude = true;
	boolean compensationFirsttimeView = true;
	boolean shouldOpenBrowser = true;

	@Test(dataProvider = "getData")
	public void EmpsPayroll_Setup_ForIncomeTax(String EmployerName,String EmpName,String Payrolid,String Frquency,String MonthName,String ExcelInputSheet,String FirstReportNameInApplication,String TestResultExcelFilePath,String worksheetNo,String PayrollVeiw,String TestReportworksheetNo) throws Throwable
	{
		//APP_LOGS.debug(EmpName);
		count++;
		if (!runmodes[count].equalsIgnoreCase("Y"))
		{
			Skip = true;
			throw new SkipException("Runmode for Test set data is set to 'NO' "
					+ count);
		}

		APP_LOGS.debug("Executing the test case");
		if (shouldOpenBrowser) {
			shouldOpenBrowser = false;
			openBrowser();
			logingIntoDesiredORG(OrgFlag);
			driver.manage().window().maximize();
			try
			{
				System.out
				.println("The test script logged in successfully into salesforce account");
				System.out.println("");
				//PayrollNIWeekly(week);
				PayrollForStatutoryMonthlyForWeekly45to48(EmployerName,EmpName,Payrolid,Frquency,MonthName,ExcelInputSheet,FirstReportNameInApplication,TestResultExcelFilePath,PayrollVeiw);
			}
			catch (Throwable t)
			{
				System.out.println(t.getMessage().toString());
			}
		}
		
		/*
		 *Following two methods goes to 'Generate draft details page' and process the 'Payroll for required employees' .
		 */
		ExcludeIncludeEmp(EmpName,ExcelInputSheet,worksheetNo);
		
		if (finalRows != dTRows)
		{
			Thread.sleep(3000L);
			System.out.println("Since the app is not displaying employee records same"
					+ " as excel file employees of this Tax worksheet");
			ProcessPayrolFor45WeeklyShPP obj1 = new ProcessPayrolFor45WeeklyShPP();
			
			for(Repeat=2; Repeat < 5; Repeat++)
			{
				// I have set 3 times to repeat the payroll script so that by the time it processess
				// 4th round 7 minutes would be as per Tutu. the appln should process the generate draft functionality.
				System.out.println("The value of Repeat is "+Repeat);
				obj1.PayrollForStatutoryMonthlyForWeekly45to48(EmployerName,EmpName,Payrolid,Frquency,MonthName,ExcelInputSheet,FirstReportNameInApplication,TestResultExcelFilePath,PayrollVeiw);
				obj1.ExcludeIncludeEmp(EmpName,ExcelInputSheet,worksheetNo);
			}
		}
	}

	
	/*******************Statutory scenarios 45 to 48 weekly payroll methods*********************************/
	public void PayrollForStatutoryMonthlyForWeekly45to48(String EmployerName,String EmpName,String Payrolid,String Frquency,String MonthName,String ExcelInputSheet,String FirstReportNameInApplication,String TestResultExcelFilePath,String PayrollView) throws Throwable
	{

		if (existsElement(OR.getProperty("payrollTab")))
		{
			getObject("payrollTab").click();
			System.out.println("The payroll tab got clicked");

		}
		Thread.sleep(5000L);
	
		if(existsElement(OR.getProperty("payrollViewLocator")))
		{
			Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("payrollViewLocator"))));
			// This select by value needs to be called from OR.Properties
			selectByValue.selectByValue(PayrollView);//"Current"
		}
		Thread.sleep(8000L);
		/*
		 * This code clicks to pagination from 1 to last page till it finds the '2Weekly' pay run 
		 * Once it finds the '2Weekly' payrun, it clicks to it.
		 * Hence the following code finds the '2Weekly' payrun automatically from pagination
		 * ProcessingTo2Weekly() method searches the required company name and payrun
		 */
		try
		{
			WebElement table = driver.findElement(By.xpath(OR.getProperty("payroll2weeklytable")));
			if(existsWebElement(table))
			{
			List<WebElement> allpages = driver.findElements(By.xpath(OR.getProperty("paginationElement")));
			System.out.println("Total pages :" +allpages.size());
			//
			for(int i=0; i<=(allpages.size()); i++)
			{
				
				if (existsElement(OR.getProperty("paginationElement")))
				{
		    	allpages.get(i).click();
				}
		    	List<WebElement> allrows = table.findElements(By.xpath("//*[@id='j_id0:j_id3']/div[2]/div/table[2]/tbody/tr[1]"));
			
					for(int row=1; row<=allrows.size(); row++)
					{
							ProcessingMonthlyStatutory(EmployerName,EmpName,Payrolid,Frquency,MonthName,ExcelInputSheet,FirstReportNameInApplication,TestResultExcelFilePath);
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

	
	public void ProcessingMonthlyStatutory(String EmployerName,String EmpName,String Payrolid,String Frquency,String MonthName,String ExcelInputSheet,String FirstReportNameInApplication,String TestResultExcelFilePath) throws Throwable
	{
		try
		{
			WebElement tableheader = getObject("payroll2weeklytable");
			List<WebElement> th=tableheader.findElements(By.tagName("th"));
			System.out.println("recognised the table columns");
			    for(int i=0;i<th.size();i++)
			    {
			        	if("Payroll".equalsIgnoreCase(th.get(i).getText()))
			        	{
						payrollcol_position=i+1;
			            break;
			        	}
				     }
			    
			        	 for(int j=0;j<th.size();j++) 
			        	 {
					        if("Employer".equalsIgnoreCase(th.get(j).getText()))
					        {
					            Emplpoyercol_position=j+1;
					            break;
     					     }
					      
			        	 }
			        	 
			        	 for(int k=0;k<th.size();k++) 
			        	 {
					        if("Frequency".equalsIgnoreCase(th.get(k).getText()))
					        {
								frequencyCol_Postition=k+1;
					            break;
						     }
				   
			        	 }
			   			
			WebElement niweeklyPayrollTable = getObject("payroll2weeklytable");
			if(existsWebElement(niweeklyPayrollTable))
			{
			List<WebElement> rows = niweeklyPayrollTable
					.findElements(By
							.xpath(OR.getProperty("payroll2weeklytablerows")));
			java.util.Iterator<WebElement> x = rows.iterator();
			rownum = 1;
			
			while (x.hasNext())
			{
				WebElement emr1 = driver.findElement(By.xpath("//table[2]/" + "tbody/" + "tr" + "[" + (rownum + 1)+ "]" + "/" + "td["+Emplpoyercol_position+"]"));
				String empr = emr1.getText();
				System.out.println(empr);
				WebElement ffr = driver.findElement(By.xpath("//table[2]/" + "tbody/" + "tr" + "[" + (rownum + 1)+ "]" + "/" + "td["+frequencyCol_Postition+"]"));
				String ffr1 = ffr.getText();
				System.out.println(ffr1);
				WebElement ppr1 = driver.findElement(By.xpath("//table[2]/" + "tbody/" + "tr" + "[" + (rownum + 1)+ "]" + "/" + "td["+payrollcol_position+"]"));
				String ppr = ppr1.getText();
				System.out.println(ppr);
				payrollRecordId = "//table[2]/" + "tbody/" + "tr" + "[" + (rownum + 1)+ "]" + "/" + "td["+payrollcol_position+"]"+"/" + "a";

				if (empr != null && empr
						.equalsIgnoreCase(EmployerName)
						&& ppr.equalsIgnoreCase(Payrolid)&& ffr1.equalsIgnoreCase(Frquency))
				{	
					System.out.println("Finally the Employer name,Payfrequency 'Monthly' and required Payroll matched");
					driver.findElement(By.xpath(payrollRecordId)).click();
					System.out.println("The payrun got clicked successfully so that appln displays Payroll details");
					if (existsElement(OR.getProperty("compypayrolldetails")))
					{
						String pfrequencey = getObject("compypayrolldetails").getText();
						System.out.println("the payfrequency is :" + pfrequencey);
					}
					if(existsElement(OR.getProperty("nextLinksearchfor45andMore")))
					{
						getObject("nextLinksearchfor45andMore").sendKeys("");
						getObject("nextLinksearchfor45andMore").click();
						System.out.println("The 2nd pagination link got clicked");
						Thread.sleep(4000L);
					}
					
					if(existsElement(OR.getProperty("payrunMonthlyTableForStatutory45thWeek")))
					{
						TaxPayRun_For_Month1Statutory(MonthName);
						break;
					}
					break;
				}
				else
				{
					System.out.println("");
				}
				rownum++;
			}
			
			}

		}
		catch(Throwable t)
		{
			t.getMessage().toString();
			t.getStackTrace().toString();
		}

	}
	
	
	//public int weekcount=2;
	public int totalWeekRows;
	//public int totalWeekRowsAbove36;
	public int rownum36;
	public void TaxPayRun_For_Month1Statutory(String MonthName) throws Throwable
	{
		try
		{
			//Thread.sleep(1000L);
			if(existsElement(OR.getProperty("payrunMonthlyTableForStatutory45thWeek")))
			{
				WebElement payRunWeekOneTable = getObject("payrunMonthlyTableForStatutory45thWeek");
				if(existsWebElement(payRunWeekOneTable))
				{
				List<WebElement> rows = payRunWeekOneTable
						.findElements(By
								.xpath(OR.getProperty("payrunMonthlyTableForStatutory45thWeekRows")));
				System.out.println("The total rows are "+ rows.size());
				totalWeekRows=rows.size();
				java.util.Iterator<WebElement> x = rows.iterator();
				rownum = 2;
				while (x.hasNext())
				{
				    String wwkkName="//div[" + "3" + "]/"+ "div/" + "div[" + "2]/" + "table/" + "tbody/tr[" +rownum+ "]/" + "th/" + "a";
					System.out.println("ruwnum is :"+rownum);
					//Thread.sleep(1000L);
					WebElement Month4 = driver.findElement(By.xpath(wwkkName));
					String MontName = Month4.getText();
					System.out.println("The month name is :"+MontName);
					if (MontName != null && MontName.equalsIgnoreCase(MonthName))
					{
						System.out.println("The month name is :"+MonthName);
						Month4.sendKeys("");
						Month4.click();
						System.out.println("The required Pay period got clicked successfully so that appln displays Generate draft payroll details page");
						break;
					}
					else
					{
						System.out.println("payRun text  :"+MonthName+" did not matched");
					}
					rownum++;
			  }
				
			}
			else
			{
				driver.navigate().refresh();
			}
				
			}

		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage());
			System.out.println(t.getStackTrace());

		}
	}
	
	/**
	 * @throws Throwable ***********************************************************************/

	
	

	@DataProvider
	public Object[][] getData() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		return Test_Util.getData(Payroll_Statutory_SharedParentalpay_SuiteXls,"ProcessPayrolFor45WeeklyShPP");
	}

	@AfterMethod
	public void ReportDataSetResult() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		if (Skip)
		{
			Test_Util.ReportDataSetResult(Payroll_Statutory_SharedParentalpay_SuiteXls, this
					.getClass().getSimpleName(), count + 2, "Skip");
		}
		else if (Fail)
		{
			IsTestPass = false;
			Test_Util.ReportDataSetResult(Payroll_Statutory_SharedParentalpay_SuiteXls, this
					.getClass().getSimpleName(), count + 2, "Fail");
		} 
		else
		{
			Test_Util.ReportDataSetResult(Payroll_Statutory_SharedParentalpay_SuiteXls, this
					.getClass().getSimpleName(), count + 2, "Pass");
		}
		Skip = false;
		Fail = false;
	}
	
	

	@AfterTest
	public void ReportTestResult() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		if (IsTestPass)
		{
			// This will update the testresult in the first worksheet where in
			// for that test case , even if one of the test data specified in
			// second worksheet fails, the test
			// would be considered as fail.And the same would be updated.

			Test_Util.ReportDataSetResult(Payroll_Statutory_SharedParentalpay_SuiteXls, "first",
					Test_Util.GetRowNum(Payroll_Statutory_SharedParentalpay_SuiteXls, this
							.getClass().getSimpleName()), "Pass");

		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_Statutory_SharedParentalpay_SuiteXls, "first",
					Test_Util.GetRowNum(Payroll_Statutory_SharedParentalpay_SuiteXls, this
							.getClass().getSimpleName()), "Fail");
		}
		closeBrowser();
	}

}
