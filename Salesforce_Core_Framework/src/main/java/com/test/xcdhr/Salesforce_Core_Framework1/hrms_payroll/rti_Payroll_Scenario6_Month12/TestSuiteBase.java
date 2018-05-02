package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.rti_Payroll_Scenario6_Month12;

import org.testng.annotations.BeforeSuite;

import com.test.xcdhr.Salesforce_Core_Framework1.testBase.TestBase;
import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Test_Util;


public class TestSuiteBase extends TestBase
{
	@BeforeSuite
	public void CheckSuiteSkip() throws Throwable
	{
		initialize();
		processDesiredTaxYearInputExcelFile(TaxYear);
		APP_LOGS.debug("Checking runmode of"+ PayrollRecognitionScenario6_Inputsheet);
		if(! Test_Util.isSuiteRunnable(SuiteXls,PayrollRecognitionScenario6_Inputsheet))
		{
			APP_LOGS.debug("Setting the Payroll Suite to OFF as the runmode is set to 'N'");
			throw new Exception("Payroll suite is not going to execute as its being skipped");
		}
	}

}
