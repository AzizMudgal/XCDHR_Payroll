package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.AutoEnrollment_Initial_Setup;




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


public class UpdatePensionSchemes extends TestSuiteBase
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
	public String compName;
	public String eSAL;
	public int rownum;
	
	

	@BeforeTest
	public void CheckTestSkip() throws Exception
	{
		if(! Test_Util.IsTestcaseRunMode(Payroll_AutoEnrolment_Initial_Setup_SuiteXls, this.getClass().getSimpleName()))
		{

			Skip=true;
			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Initial_Setup_SuiteXls, "first", Test_Util.GetRowNum(Payroll_AutoEnrolment_Initial_Setup_SuiteXls, this.getClass().getSimpleName()),"Skipped");
			//Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Initial_Setup_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase" +this.getClass().getSimpleName() +" as the runmode is set to 'no' ");// this message would display in logs

			throw new Exception("Testcase is being skipped" + this.getClass().getSimpleName()+ "as it's Runmode is set to 'NO'"); // this msg would display in Reports.

		}

		// Load the runmodes of the tests

		runmodes=Test_Util.getDataSetRunmodes(Payroll_AutoEnrolment_Initial_Setup_SuiteXls, this.getClass().getSimpleName());

	}
	
	public String payfreqncy;
	boolean companyFirsttimeView = true;
	boolean AllowanceFirsttimeView = true;
	boolean shouldOpenBrowser = true; 
	boolean compnees = true;
	
	@Test(dataProvider="getData")
	public void EmpsSetup_WithNICategory(String schemName, String compnay,String qualifyingschme,String firstcontbn,String postpone,String SEmployrCPercentage,String SEmpCPercentage) throws Throwable
	{	
		//APP_LOGS.debug("Entering the Leave parameters");
		//APP_LOGS.debug(compName+"--"+NICategory+"--"+AnnualSalary+"--"+PayFrequency);

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
/*
 * NOTE: Creating the Female Employee script needs to be run only once
 *  after that in the suite , the script should
 *   be set to 'NO' in the "Payroll Suite StatutoryMaternityPay"  excel sheet
 *   
 *   
 */
		
		
		// The script updates the BenifitAndAward for the Automation employees
		UpdateCompnay(schemName,compnay,qualifyingschme,firstcontbn,postpone,SEmployrCPercentage,SEmpCPercentage);

		/*************************************************************************/
	
	}
	
	
	public void UpdateCompnay(String schemName, String compnay,String qualifyingschme,String firstcontbn,String postpone,String SEmployrCPercentage,String SEmpCPercentage) throws Throwable
	{
		try
		{
			if(companyFirsttimeView)
			{
				companyFirsttimeView = false;
				if(existsElement(OR.getProperty("CompaniesTab")))
				{
					getObject("CompaniesTab").click();
				}
				Thread.sleep(3000L);
				if(existsElement(OR.getProperty("companyText")))
				{
					System.out.println("I am in companees page");
					if(existsElement(OR.getProperty("companyView")))
					{

						System.out.println("I recognised the company view");

						Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("companyView"))));
						// This select by value needs to be called from OR.Properties
						selectByValue.selectByValue(OR.getProperty("companyOption"));
						Thread.sleep(2000L);
						if(existsElement(OR.getProperty("compnayViewGoButton")))
						{
							getObject("compnayViewGoButton").sendKeys("");
							getObject("compnayViewGoButton").click();
						}
						Thread.sleep(7000L);
					}

				}

			}

		
			WebElement postsTable = driver.findElement(By.xpath(OR.getProperty("firstRecordOfcompanycoulmnTable")));
			if(existsWebElement(postsTable))
			{
				List<WebElement> rows = postsTable.findElements(By.xpath(OR.getProperty("firstRecordOfcompanycoulmnTableRows")));

				java.util.Iterator<WebElement> x = rows.iterator();
				rownum = 1;			
				while(x.hasNext())
				{
					String firstRowOfcompanyColumn="//div["+rownum+"]/table/tbody/tr/td[4]/div/a/span";
													
					if(existsElement(firstRowOfcompanyColumn))
					{
					WebElement tempElement= driver.findElement(By.xpath(firstRowOfcompanyColumn));
					String companeeNam= tempElement.getText();
					//System.out.println(companeeNam+"-------"+compName+"------"+rownum);
						if(companeeNam!=null && companeeNam.equalsIgnoreCase(compnay))
						{
							System.out.println("company name  :"+companeeNam+ "  matched ");
							tempElement.sendKeys("");
							tempElement.click();
							System.out.println("company name  :"+companeeNam+ " got clicked");
						
							if(existsElement(OR.getProperty("autoCompanyRewardTab")))
							{
								getObject("autoCompanyRewardTab").sendKeys("");
								getObject("autoCompanyRewardTab").click();
							}
							
							
							if(existsElement(OR.getProperty("rewardTabText")))
							{
								String penScheme = getObject("rewardTabText").getText();
								Assert.assertEquals("Pension schemes", penScheme);
								System.out.println("we are in right tab");
							}
							
							if(existsElement(OR.getProperty("newPensionSchemeBton")))
							{
								getObject("newPensionSchemeBton").sendKeys("");
								System.out.println("we are in pension scheme page.");
							}
							updateNewPensionScheme(schemName,compnay,qualifyingschme,firstcontbn,postpone,SEmployrCPercentage,SEmpCPercentage);
							
															
						}
					
					}
					else
					{
						System.out.println("");
						System.out.println("company name not matched");
					}
					rownum++;
				}
	
			}
			
		}
		catch(Throwable t)
		{
			APP_LOGS.debug(" Check for error in NI Category method");
			System.out.println(t.getStackTrace().toString());
			ErrorUtil.addVerificationFailure(t);
			System.out.println("");
		}


	}
	
	
	
	public void updateNewPensionScheme(String schemName, String compnay,String qualifyingschme,String postpone,String SEmployrCPercentage,String SEmpCPercentage,String firstcontbn)throws Throwable
	{
		try
		{
			if(existsElement(OR.getProperty("pensionSchemeLink")))
			{
				getObject("pensionSchemeLink").sendKeys("");
				getObject("pensionSchemeLink").click();
			}
			
			if(existsElement(OR.getProperty("compnPensionSchemeEditBton")))
			{
				getObject("compnPensionSchemeEditBton").sendKeys("");
				getObject("compnPensionSchemeEditBton").click();
			}
			
			//call the qualifying scheme and pospone methods here.
			qualifyingScheme(qualifyingschme);
			
			postpone(postpone);
			
			if(existsElement(OR.getProperty("stdEmployerContbn")))
			{
				getObject("stdEmployerContbn").sendKeys("");
				getObject("stdEmployerContbn").sendKeys(firstcontbn);
			}
			
			if(existsElement(OR.getProperty("stdEmpContbn")))
			{
				getObject("stdEmpContbn").sendKeys("");
				getObject("stdEmpContbn").sendKeys(firstcontbn);
			}
			
			if(existsElement(OR.getProperty("firstContbnlookup")))
			{
				Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("firstContbnlookup"))));
				// This select by value needs to be called from OR.Properties
				selectByValue.selectByValue(OR.getProperty("On enrolment"));
				
			}
			
		
			if(existsElement(OR.getProperty("cPSupdateButon")))
			{
				getObject("cPSupdateButon").sendKeys("");
				getObject("cPSupdateButon").click();
			}
			
		}
		catch(Throwable t)
		{
			APP_LOGS.debug("Check for error in 'updateNewPensionScheme' method");
			System.out.println(t.getMessage());
			System.out.println(t.getStackTrace().toString());
			ErrorUtil.addVerificationFailure(t);
			System.out.println("");
		}
		
	}

	public void qualifyingScheme(String Conditionsatisfd)throws Throwable
	{
		try
		{
			boolean	CondnSatisfiedchekbox = getObject("qaulifingShmChkbox").isSelected();
			double valueOfCondnChkbox = Double.parseDouble(Conditionsatisfd);
			System.out.println("converted condition satisfied value is :"+valueOfCondnChkbox);
			if(valueOfCondnChkbox== 1.0)
			{
				Thread.sleep(4000L);
				isConditionSatisfiedchecked(CondnSatisfiedchekbox);
			}
			else if(valueOfCondnChkbox== 0.0)
			{
				qualifyingcondnNotSatisfied(CondnSatisfiedchekbox);
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}
	

	public void postpone(String Conditionsatisfd)throws Throwable
	{
		try
		{
			boolean	CondnSatisfiedchekbox = getObject("cPSpostponeChkbox").isSelected();
			double valueOfCondnChkbox = Double.parseDouble(Conditionsatisfd);
			System.out.println("converted condition satisfied value is :"+valueOfCondnChkbox);
			if(valueOfCondnChkbox== 1.0)
			{
				Thread.sleep(4000L);
				postPonecondnSetToOne(CondnSatisfiedchekbox);
			}
			else if(valueOfCondnChkbox== 0.0)
			{
				postPonecondnSetToZero(CondnSatisfiedchekbox);
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}
	
	

	public boolean isConditionSatisfiedchecked(boolean Condnchecked)throws Throwable
	{
		if(Condnchecked)
		{
			System.out.println("qualifying checkbox is already checked");
		}
		else
		{
			getObject("qaulifingShmChkbox").click();
			System.out.println("qualifing checkbox was not Checked But now is checked successfully");
			Thread.sleep(2000L);
			
		}
		return Condnchecked;
	}
	
	public boolean qualifyingcondnNotSatisfied(boolean Condnchecked)throws Throwable
	{
		if(Condnchecked)
		{
			getObject("qaulifingShmChkbox").click();
			System.out.println("qaulifing checkbox was Checked But now is unchecked successfully");
			Thread.sleep(2000L);
		}
		else
		{
			System.out.println("qaulifing checkbox is already unchecked hence satisfied the requirment");
		}
		return Condnchecked;
	}
	//
	
	
	
	public boolean postPonecondnSetToZero(boolean Condnchecked)throws Throwable
	{
		if(Condnchecked)
		{
			getObject("cPSpostponeChkbox").click();
			System.out.println("postpone checkbox was Checked But now is unchecked successfully");
			Thread.sleep(2000L);
		}
		else
		{
			System.out.println("postpone checkbox is already unchecked, hence satisfied the condition");
		}
		return Condnchecked;
	}
	
	public boolean postPonecondnSetToOne(boolean Condnchecked)throws Throwable
	{
		if(Condnchecked)
		{
			System.out.println("postpone checkbox is already unchecked");
		}
		else
		{
			getObject("cPSpostponeChkbox").click();
			System.out.println("postpone checkbox was Checked But now is unchecked successfully");
			Thread.sleep(2000L);
			
		}
		return Condnchecked;
	}
	
	
	@DataProvider
	public Object[][] getData()
	{
		return Test_Util.getData(Payroll_AutoEnrolment_Initial_Setup_SuiteXls,"CreatePensionSchemes");
	}


	@AfterMethod
	public void ReportDataSetResult()
	{
		if(Skip){
			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Initial_Setup_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
		}else if(Fail)
		{

			IsTestPass = false;

			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Initial_Setup_SuiteXls, this.getClass().getSimpleName(), count+2, "Fail");
		}else
		{
			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Initial_Setup_SuiteXls, this.getClass().getSimpleName(), count+2, "Pass");
		}

		Skip=false;
		Fail=false;


	}


	@AfterTest
	public void ReportTestResult(){

		if(IsTestPass){

			// This will update the testresult in the first worksheet where in for that test case , even if one of the test data specified in second worksheet fails, the test 
			// would be considered as fail.And the same would be updated.

			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Initial_Setup_SuiteXls, "first", Test_Util.GetRowNum(Payroll_AutoEnrolment_Initial_Setup_SuiteXls, this.getClass().getSimpleName()),"Pass");

		}else{

			Test_Util.ReportDataSetResult(Payroll_AutoEnrolment_Initial_Setup_SuiteXls, "first", Test_Util.GetRowNum(Payroll_AutoEnrolment_Initial_Setup_SuiteXls, this.getClass().getSimpleName()),"Fail");

		}	

		closeBrowser();
	}


	
	
}

