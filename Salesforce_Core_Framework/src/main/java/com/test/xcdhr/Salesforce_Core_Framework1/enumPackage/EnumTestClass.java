package com.test.xcdhr.Salesforce_Core_Framework1.enumPackage;

import com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.NI_MonthlyCatA.TestSuiteBase;


public class EnumTestClass extends TestSuiteBase 
{
	public void runTestReport(ModifiedReport report)throws Throwable
	{
		Thread.sleep(2000L);
		String locatorName = report.getReportName();
		if(existsElement(OR.getProperty(locatorName)))
		{
			getObject(locatorName).sendKeys("");
			Thread.sleep(2000L);
			getObject(locatorName).click();
		}
		
	}
	
	
	
	
	
}
