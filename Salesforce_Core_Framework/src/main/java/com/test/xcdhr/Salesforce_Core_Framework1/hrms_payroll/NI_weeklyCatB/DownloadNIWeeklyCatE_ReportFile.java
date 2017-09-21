package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.NI_weeklyCatB;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.xcdhr.Salesforce_Core_Framework1.testBase.TestBase;
import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.ErrorUtil;
import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Test_Util;


public class DownloadNIWeeklyCatE_ReportFile extends TestSuiteBase {
	String runmodes[] = null;
	static int count = -1;
	public static boolean Fail=false;
	public static boolean Skip=false;
	public static boolean IsTestPass=true;
	public String divId;

	@BeforeTest
	public void CheckTestSkip() throws Exception{
		if(! Test_Util.IsTestcaseRunMode(Payroll_CatE_SuiteXls, this.getClass().getSimpleName())){

			Skip=true;
			Test_Util.ReportDataSetResult(Payroll_CatE_SuiteXls, "first", Test_Util.GetRowNum(Payroll_CatE_SuiteXls, this.getClass().getSimpleName()),"Skipped");
			//Test_Util.ReportDataSetResult(Payroll_CatE_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
			APP_LOGS.debug("skipping the testcase" +this.getClass().getSimpleName() +" as the runmode is set to 'no' ");// this message would display in logs

			throw new Exception("Testcase is being skipped" + this.getClass().getSimpleName()+ "as it's Runmode is set to 'NO'"); // this msg would display in Reports.

		}

		// Load the runmodes of the tests

		runmodes=Test_Util.getDataSetRunmodes(Payroll_CatE_SuiteXls, this.getClass().getSimpleName());

	}


	@Test
	public void CompareReports() throws Throwable{


		count++;
		if(! runmodes[count].equalsIgnoreCase("Y")){

			Skip=true;
			throw new SkipException("Runmode for Test set data is set to 'NO' "+count);

		}

		APP_LOGS.debug("Executing the test case");
		//WebDriver driver = new FirefoxDriver(FirefoxDriverProfile());
		openBrowser();

		driver.get(CONFIG.getProperty("testSiteName"));
		login_To_Application();

		driver.manage().window().maximize();

		try
		{
			WaitforElement(("Homepage_txt"));
			if(existsElement(OR.getProperty("Homepage_txt")))
			{

				Assert.assertEquals(driver.getTitle(), "salesforce.com - Enterprise Edition");
				System.out.println("The test script logged in successfully into salesforce account");
				System.out.println("");
				System.out.println("The test script successfully into salesforce account's home page");
				System.out.println("");
			}
		}catch(Throwable t){
			APP_LOGS.debug("Could not assert the home page title due to unsuccessfull login account");
			System.out.println("");
			ErrorUtil.addVerificationFailure(t);
			CaptureScreenshot("EmployeeProfile"+this.getClass().getSimpleName()+"  Due to this Error Could not Assert Title");

		}
		
		DownloadReports();
		//OpenReport_XLSX_Excelsheet();
		
		
}
	/*
	public static FirefoxProfile FirefoxDriverProfile() throws Exception {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("browser.download.folderList", 2);
		profile.setPreference("browser.download.manager.showWhenStarting", false);
		profile.setPreference("browser.download.dir", downloadPath);
		profile.setPreference("browser.helperApps.neverAsk.openFile",
				"text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
"text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
		profile.setPreference("browser.helperApps.alwaysAsk.force", false);
		profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
		profile.setPreference("browser.download.manager.focusWhenStarting", false);
		profile.setPreference("browser.download.manager.useWindow", false);
		profile.setPreference("browser.download.manager.showAlertOnComplete", false);
		profile.setPreference("browser.download.manager.closeWhenDone", false);
		return profile;
	}
	*/
	
	public void DownloadReports() throws Throwable{
		
	WebElement ReportTab = driver.findElement(By.xpath("//*[@id='report_Tab']/a"));
	ReportTab.click();
	driver.navigate().refresh();
	Thread.sleep(8000L);
	
	WebElement SearchReport= driver.findElement(By.xpath("//div[2]/div/div/div/input"));
	
		SearchReport.sendKeys("");								//*[@id='ext-comp-1015']
		Thread.sleep(1000L);
		SearchReport.sendKeys("DO NOT TOUCH - PAYROLL AUTOMATION CHECK");
		Thread.sleep(4000L);
		WebElement DonttouchEmpReport = driver.findElement(By.xpath("//*[@id='00Ob0000003n2N9_NAME']/div[2]/a/span"));
		DonttouchEmpReport.click();
	
	
	Thread.sleep(4000L); // //*[@id='report']/div[1]/div[2]/input[8]
	WebElement ExportButon = driver.findElement(By.xpath("//*[@id='report']/div[1]/div[2]/input[8]"));
	ExportButon.click();
	Thread.sleep(3000L);
	/****************/
	// This needs to be pasted in all the  A TO K Category
	System.out.println("I recognised the type of File");
	Select selectByValue = new Select(driver.findElement(By.xpath(OR.getProperty("reportType"))));
	selectByValue.selectByValue("localecsv");
	Thread.sleep(2000L);
	
		// //*[@id='bottomButtonRow']/input[1]
	WebElement ReportClickdownload = driver.findElement(By.xpath("//*[@id='bottomButtonRow']/input[1]"));
	ReportClickdownload.click();
	
	}
	
	// This function is to open the Excel file of type ".xlsx"
	/*
	public void OpenReport_XLSX_Excelsheet() throws Throwable{
		
		Thread.sleep(4000L);
		
		 File file = new File("F:\\Automation XCD\\Excel Compare software tool\\ComparisionResult\\Weekly CAT A Actual Result.xlsx");
	      FileInputStream fIP = new FileInputStream(file);
	      //Get the workbook instance for XLSX file 
	      
	      XSSFWorkbook workbook = new XSSFWorkbook(fIP);
	      if(file.isFile() && file.exists())
	      {
	         System.out.println(
	         "HMRC_File.xlsx file open successfully.");
	      }
	      else
	      {
	         System.out.println(
	         "Error to open openworkbook.xlsx file.");
	      }
		
		
	}
	*/
	
	
	
	
	@AfterMethod
	public void ReportDataSetResult(){
		if(Skip){
			Test_Util.ReportDataSetResult(Payroll_CatE_SuiteXls, this.getClass().getSimpleName(), count+2, "Skip");
		}else if(Fail){

			IsTestPass = false;

			Test_Util.ReportDataSetResult(Payroll_CatE_SuiteXls, this.getClass().getSimpleName(), count+2, "Fail");
		}else{
			Test_Util.ReportDataSetResult(Payroll_CatE_SuiteXls, this.getClass().getSimpleName(), count+2, "Pass");
		}

		Skip=false;
		Fail=false;


	}


	@AfterTest
	public void ReportTestResult(){

		if(IsTestPass){

			// This will update the testresult in the first worksheet where in for that test case , even if one of the test data specified in second worksheet fails, the test 
			// would be considered as fail.And the same would be updated.

			Test_Util.ReportDataSetResult(Payroll_CatE_SuiteXls, "first", Test_Util.GetRowNum(Payroll_CatE_SuiteXls, this.getClass().getSimpleName()),"Pass");

		}else{

			Test_Util.ReportDataSetResult(Payroll_CatE_SuiteXls, "first", Test_Util.GetRowNum(Payroll_CatE_SuiteXls, this.getClass().getSimpleName()),"Fail");

		}	


	}




}

