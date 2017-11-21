package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.Payroll_Suite_NIDirector_Under21_Month1;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Test_Util;

public class NIDirector_Under21Month1 extends TestSuiteBase
{
	String runmodes[] = null;
	static int count = -1;
	static int countCompensation = -1;

	public static boolean Fail=false;
	public static boolean Skip=false;
	public static boolean IsTestPass=true;
	public int Row_count;
	public int rownum;
	public int rownumNI;




	@BeforeTest
	public void CheckTestSkip() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		if(! Test_Util.IsTestcaseRunMode(Payroll_NI_Director_Under21_SuiteXls, this.getClass().getSimpleName()))
		{
			Skip=true;
			Test_Util.ReportDataSetResult(Payroll_NI_Director_Under21_SuiteXls, "first", Test_Util.GetRowNum(Payroll_NI_Director_Under21_SuiteXls, this.getClass().getSimpleName()),"Skipped");
			//Test_Util.ReportDataSetResult(Payroll_NI_Director_Under21_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase" +this.getClass().getSimpleName() +" as the runmode is set to 'no' ");// this message would display in logs
			throw new Exception("Testcase is being skipped" + this.getClass().getSimpleName()+ "as it's Runmode is set to 'NO'"); // this msg would display in Reports.
		}
		// Load the runmodes of the tests
		runmodes=Test_Util.getDataSetRunmodes(Payroll_NI_Director_Under21_SuiteXls, this.getClass().getSimpleName());
	}




	public String payfreqncy;
	boolean employeeFirsttimeView = true;
	boolean compensationFirsttimeView = true;
	boolean shouldOpenBrowser = true; 

	@Test(dataProvider="getData", priority=1)
	public void EmpsSetup_WithNICategory(String EmpName,String NICategory,String DirectorsNIBasis,String DirectorSince,String DOB, String AnnualSalary, String PayFrequency ) throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);

		//APP_LOGS.debug("Entering the Leave parameters");
		//APP_LOGS.debug(EmpName+"--"+NICategory+"--"+AnnualSalary+"--"+PayFrequency);
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
				if(existsElement(OR.getProperty("Homepage_txt")))
				{
					Assert.assertEquals(driver.getTitle(), "salesforce.com - Enterprise Edition");
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

		// The script updates the NI Category for the Automation employees
		UpdateEmployeeNICategoryDraft(EmpName,NICategory,DirectorsNIBasis,DirectorSince,DOB);

		/*************************************************************************/
	}

	
	
	public void UpdateEmployeeNICategoryDraft(String empName,String NICategory,String DirectorsNIBasis,String DirectorSince,String DateOfBirth) throws Throwable
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
						selectByValue.selectByVisibleText("DO NOT TOUCH PAYROLL AUTOMATION TESTING");
						Thread.sleep(2000L);
						getObject("ViewGoButton").sendKeys("");
						getObject("ViewGoButton").click();

						Thread.sleep(7000L);
					}

				}

			}

			Row_count = driver.findElements(By.xpath("//div[@id='ext-gen11']/div/table/tbody/tr")).size();
			WebElement postsTable = driver.findElement(By.xpath(OR.getProperty("firstRecordOfNIcoulmnTable")));
			if(existsWebElement(postsTable))
			{
				List<WebElement> rows = postsTable.findElements(By.xpath(OR.getProperty("firstRecordOfNIcoulmnTableRows")));
	
				java.util.Iterator<WebElement> x = rows.iterator();
				rownumNI = 1;			
				while(x.hasNext())
				{
					String firstRowOfEmployeeColumn="//div["+rownumNI+"]/table/tbody/tr/td[4]/div/a/span";
					if(existsElement(firstRowOfEmployeeColumn))
						{
						WebElement firstEmployee= driver.findElement(By.xpath(firstRowOfEmployeeColumn));
						String AppnEmp= firstEmployee.getText();
						//System.out.println(tempEmp+"-------"+empName+"------"+rownum);
						if(AppnEmp!=null && AppnEmp.equalsIgnoreCase(empName))
						{
							System.out.println("Employee matched");
							Thread.sleep(3000L);
							String firstRowOfNIColumn="//div["+rownumNI+"]"+"/"+"table/"+"tbody/"+"tr/"+"td["+"6]"+"/"+"div";
							if(existsElement(firstRowOfNIColumn))
							{
								String rowNumberOfNIColumn = "//div["+rownumNI+"]"+"/"+"table/"+"tbody/"+"tr/"+"td["+"6]"+"/"+"div";
								Actions action = new Actions(driver);
								action.doubleClick(driver.findElement(By.xpath(rowNumberOfNIColumn))).perform();
								action.moveToElement(getObject("InlineDropdown")).perform();
								Thread.sleep(2000L);
								if(existsElement(OR.getProperty("InlineDropdown")))
								{
									getObject("InlineDropdown").sendKeys(NICategory);
									Thread.sleep(2000L);
									getObject("InlineUpdateButn").click();
									System.out.println("updated NI Category successfully");
									Thread.sleep(6000L);
								}
							}
							UpdateDirectorsNIBasis(empName,NICategory,DirectorsNIBasis,DirectorSince,DateOfBirth);
							Thread.sleep(3000L);
							UpdateDirectorsSince(empName,NICategory,DirectorsNIBasis,DirectorSince,DateOfBirth);
							Thread.sleep(3000L);
							DateofBirth(empName,NICategory,DirectorsNIBasis,DirectorSince,DateOfBirth);
							break;
						}
					
					else if(rownumNI == lastRowCount && AppnEmp!=null && AppnEmp!=(empName))
					{
						System.out.println("The row number of the page reached"+ rownumNI +" to 200 and"
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
					rownumNI++;
				}
			}
		}
		catch(Throwable t)
		{
			APP_LOGS.debug(" Check for error in NI Category method");
			System.out.println(t.getStackTrace().toString());
			//ErrorUtil.addVerificationFailure(t);
			System.out.println("");
		}
	}
	




	@Test(dataProvider="getData", priority=2)
	public void EmpsSetup_WithAnnualSalary(String EmpName,String NICategory,String DirctorsNibasis,String DirectorSince,String DOB, String AnnualSalary,String PayFrequency) throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);

		countCompensation++;
		if(! runmodes[countCompensation].equalsIgnoreCase("Y"))
		{
			Skip=true;
			throw new SkipException("Runmode for Test set data is set to 'NO' "+countCompensation);
		}

		/*************************************************************************/
		// The script updates the Annual salary in the compensation Tab for the Automation employees
		UpdateAnnualSalary(EmpName,AnnualSalary,PayFrequency);
		/*************************************************************************/
	}



	@DataProvider
	public Object[][] getData() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);

		return Test_Util.getData(Payroll_NI_Director_Under21_SuiteXls,"NIDirector_Under21Month1");
	}



	@AfterMethod
	public void ReportDataSetResult() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);

		if(Skip)
		{
			Test_Util.ReportDataSetResult(Payroll_NI_Director_Under21_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
		}
		else if(Fail)
		{
			IsTestPass = false;
			Test_Util.ReportDataSetResult(Payroll_NI_Director_Under21_SuiteXls, this.getClass().getSimpleName(), count+2, "Fail");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_NI_Director_Under21_SuiteXls, this.getClass().getSimpleName(), count+2, "Pass");
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
			Test_Util.ReportDataSetResult(Payroll_NI_Director_Under21_SuiteXls, "first", Test_Util.GetRowNum(Payroll_NI_Director_Under21_SuiteXls, this.getClass().getSimpleName()),"Pass");
		}
		else
		{
			Test_Util.ReportDataSetResult(Payroll_NI_Director_Under21_SuiteXls, "first", Test_Util.GetRowNum(Payroll_NI_Director_Under21_SuiteXls, this.getClass().getSimpleName()),"Fail");
		}	
		closeBrowser();
	}
}
