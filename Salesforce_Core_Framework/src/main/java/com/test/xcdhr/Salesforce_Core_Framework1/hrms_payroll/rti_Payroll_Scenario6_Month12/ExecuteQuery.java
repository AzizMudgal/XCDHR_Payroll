package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.rti_Payroll_Scenario6_Month12;




import java.util.List;





import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Test_Util;

public class ExecuteQuery extends TestSuiteBase {



	String runmodes[] = null;
	static int count = -1;
	static int countCompensation = -1;
	public static boolean Fail=false;
	public static boolean Skip=false;
	public static boolean IsTestPass=true;
	public String titlename;
	public String status;

	@BeforeTest
	public void CheckTestSkip() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);

		if(! Test_Util.IsTestcaseRunMode(Payroll_RecognitionScenarioSix_SuiteXls, this.getClass().getSimpleName())){

			Skip=true;
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarioSix_SuiteXls, "first", Test_Util.GetRowNum(Payroll_RecognitionScenarioSix_SuiteXls, this.getClass().getSimpleName()),"Skipped");
			//Test_Util.ReportDataSetResult(Payroll_RecognitionScenarioSix_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase" +this.getClass().getSimpleName() +" as the runmode is set to 'no' ");// this message would display in logs

			throw new Exception("Testcase is being skipped" + this.getClass().getSimpleName()+ "as it's Runmode is set to 'NO'"); // this msg would display in Reports.

		}

		// Load the runmodes of the tests

		runmodes=Test_Util.getDataSetRunmodes(Payroll_RecognitionScenarioSix_SuiteXls, this.getClass().getSimpleName());

	}


	/*
	 * This test login into salesforce app ==> Develope console ==> and executes the following query
	 * whenever the test scripts are getting executed from say 'Month' to 'Week' or vice versa the YTD value needs 
	 * to be deleted. This makes sure for testing for all kinds of scenarios.
	 *  
	 * List<Pay_Summary_YTD__c> deleteLst = [Select p.Tax_year__c, p.Tax_paid_YTD__c, p.Name, p.Id From Pay_Summary_YTD__c p where p.Tax_year__c = '2015/2016'] ;
		if(deleteLst.size() > 0)
		delete deleteLst;
	 * 
	 */


	@Test
	public  void queryExecute() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);

		boolean shouldOpenBrowser = true;
		if(shouldOpenBrowser)
		{
			shouldOpenBrowser = false;
			openBrowser();
			logingIntoDesiredORG(OrgFlag);
			
			driver.manage().window().maximize();
			try
			{
				titlename = driver.getTitle();
				Assert.assertEquals(driver.getTitle(), "Salesforce - Enterprise Edition");
				System.out.println("1> The test script logged in successfully into salesforce account and now in Home page");
				System.out.println("");
			}
			catch(Throwable t)
			{
				APP_LOGS.debug("Could not assert the home page title, Check for error");
				System.out.println("");
				defaultWaitTime();
			}

		
			String ParentWindow = driver.getWindowHandle();
			if(existsElementchkFor1mts(OR.getProperty("Hover")))
			{
				getObject("Hover").click();
				defaultWaitTime();
			}

			if(existsElementchkFor1mts(OR.getProperty("developerConsole")))
			{
				getObject("developerConsole").click();
				System.out.println("2> script clicked to Developer console.");

				defaultWaitTime();
			}

			for(String childWindow : driver.getWindowHandles())
			{
				driver.switchTo().window(childWindow);
				defaultWaitTime();
			}
			Thread.sleep(4000L);
			if(existsElementchkFor1mts(OR.getProperty("HoverDebug")))
			{
				getObject("HoverDebug").sendKeys("");
				getObject("HoverDebug").click();
				System.out.println("2> script clicked to Hover Debug button");

				defaultWaitTime();
				
			}
			//String ParentWindow2 = driver.getWindowHandle();

			

			if(existsElementchkFor1mts(OR.getProperty("openExecute")))
			{
				getObject("openExecute").click();
				System.out.println("3> Clicked to openExecute link from developer console");
			}
			Thread.sleep(4000L);
			for(String childWindow2 : driver.getWindowHandles())
			{
				driver.switchTo().window(childWindow2);
				defaultWaitTime();
			}
			
			WebElement targetElement = driver.findElement(By.xpath("//div[6]/div[1]/div/div/div/div[3]"));
				int length = targetElement.toString().length();
				System.out.println("The length of the string is "+length);
				 List<WebElement> allOptions = targetElement.findElements(By.xpath("//div[6]/div[1]/div/div/div/div[3]/div/pre"));
				 java.util.Iterator<WebElement> Rx = allOptions.iterator();
				 int rowwwnum = 1;
					while(Rx.hasNext())
					{
						System.out.println("The row number now is "+rowwwnum);
						 String s = "//div[6]/div[1]/div/div/div/div[3]/div"+"["+rowwwnum+"]"+"/pre";
						WebElement wow = driver.findElement(By.xpath(s));
						 int length1 = wow.toString().length();
						 
						// for (int i = 0; i < length1; i++)
						// {
							 //wow.click();
							 wow.sendKeys(Keys.TAB);
							 wow.sendKeys(Keys.DELETE,"value to set");
							// wow.sendKeys("abc");
						
							// wow.sendKeys(Keys.chord(Keys.CONTROL, "a"));
							// wow.sendKeys(Keys.DELETE,"value to set");
							 Thread.sleep(2000L);
							// wow.clear();
							// System.out.println("The row"+ rowwwnum+" got deleted now");
						// }
						 rowwwnum++;
						 if(rowwwnum==4)
						 {
							 System.out.println("no more rows to delete,hence breaking out of loop");
								wow.sendKeys("abc");
							 break;
						 }
				    }
					Thread.sleep(2000L);
					getObject("scriptExecuteButton").sendKeys("");
					getObject("scriptExecuteButton").click();
					System.out.println("The butto got clicked");
					Thread.sleep(4000L); 
				}
			 	//driver.close();
				//driver.switchTo().window(ParentWindow);
	}
	

			
				
			
	
	
	
					


	@AfterMethod
	public void ReportDataSetResult() throws Throwable{
		processDesiredTaxYearInputExcelFile(TaxYear);

		if(Skip){
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarioSix_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
		}else if(Fail){

			IsTestPass = false;

			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarioSix_SuiteXls, this.getClass().getSimpleName(), count+2, "Fail");
		}else{
			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarioSix_SuiteXls, this.getClass().getSimpleName(), count+2, "Pass");
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

			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarioSix_SuiteXls, "first", Test_Util.GetRowNum(Payroll_RecognitionScenarioSix_SuiteXls, this.getClass().getSimpleName()),"Pass");

		}else{

			Test_Util.ReportDataSetResult(Payroll_RecognitionScenarioSix_SuiteXls, "first", Test_Util.GetRowNum(Payroll_RecognitionScenarioSix_SuiteXls, this.getClass().getSimpleName()),"Fail");

		}	

		closeBrowser();
	}



}
