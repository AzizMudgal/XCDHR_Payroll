package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.AutoEnrollment_Initial_Setup;

import org.testng.annotations.BeforeSuite;

import com.test.xcdhr.Salesforce_Core_Framework1.testBase.TestBase;
import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Test_Util;


public class TestSuiteBase extends TestBase{

	

	@BeforeSuite
	public void CheckSuiteSkip() throws Exception {
		initialize();
		APP_LOGS.debug("Checking runmode of Payroll Suite AutoEnrolment Initial Setup");
		if(! Test_Util.isSuiteRunnable(SuiteXls,"Payroll Suite AutoEnrolment Initial Setup")){
			APP_LOGS.debug("Setting the Payroll Suite to OFF as the runmode is set to 'N'");
			throw new Exception("Payroll suite is not going to execute as its being skipped");
		}
	}

}
