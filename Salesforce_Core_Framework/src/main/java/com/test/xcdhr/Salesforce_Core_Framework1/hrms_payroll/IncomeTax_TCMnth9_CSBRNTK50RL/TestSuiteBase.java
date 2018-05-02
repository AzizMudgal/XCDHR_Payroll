package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.IncomeTax_TCMnth9_CSBRNTK50RL;

import org.testng.annotations.BeforeSuite;

import com.test.xcdhr.Salesforce_Core_Framework1.testBase.TestBase;
import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Test_Util;


public class TestSuiteBase extends TestBase
{

	@BeforeSuite
	public void CheckSuiteSkip() throws Throwable
	{
		processDesiredTaxYearInputExcelFile(TaxYear);
		initialize();
		APP_LOGS.debug("Checking runmode of  "+ TaxPayroll_TaxMonth9CSBRNTK50PercentRegulatory_Inputsheet);
		if(! Test_Util.isSuiteRunnable(SuiteXls,TaxPayroll_TaxMonth9CSBRNTK50PercentRegulatory_Inputsheet))
		{
			APP_LOGS.debug("Setting the Payroll Suite to OFF as the runmode is set to 'N'");
			throw new Exception("Payroll suite is not going to execute as its being skipped");
		}
	}

}
