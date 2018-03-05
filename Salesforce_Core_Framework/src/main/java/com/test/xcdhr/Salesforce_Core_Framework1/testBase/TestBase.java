package com.test.xcdhr.Salesforce_Core_Framework1.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.openqa.selenium.TakesScreenshot;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.support.ui.Select;

import com.test.xcdhr.Salesforce_Core_Framework1.enumPackage.EnumTestClass;
import com.test.xcdhr.Salesforce_Core_Framework1.enumPackage.ModifiedReport;

import atu.webdriver.utils.table.WebTable;

import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.ErrorUtil;
import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Xls_Reader;

public class TestBase {

	public static String downloadPath = "F:\\Automation XCD\\Excel Compare software tool\\ComparisionResult";
	public int Repeat;
	public int finalRows;
	public static Logger APP_LOGS = null;
	public static Properties CONFIG = null;
	public static Properties OR = null;
	public static Xls_Reader SuiteXls = null;
	public String compnDonottouch;
	public String formattedDate;

	public String payfreqncy;
	public String sbmtBtn;
	public int rowtd;
	WebElement element;
	public int rownumc;
	public int lastRowCount;
	public String value1;
	public String value2;
	public String value3;
	public String value4;
	public String value5;
	public String value6;
	public String value7;
	public String value8;
	public String value9;
	public String value10;
	public String value11;
	public String value12;
	public String value13;
	public String value14;
	public String value15;
	public String value16;
	public String value17;
	public String value18;
	public String value19;
	public String value20;
	public String value21;
	public String value22;
	public String value23;
	public String value24;
	public String value25;
	public String value26;
	public String value27;
	public String value28;
	public String value29;
	public String value30;
	public String value31;
	public String value32;
	public String value33;
	public String value34;
	public String value35;
	public String value36;
	public String value37;
	public String value38;
	public String value39;
	public String value40;
	public String value41;
	public String value42;
	public String value43;
	public String value44;
	public String value45;
	public String RowOfAttachementRecord;
	public int ttrows;

	public String pymtAftrLeavingDate;
	public String StudntLoanInd;
	public String paymentAfterLeaving;

	public String firstCellOfBody;
	public int counter;

	public static Xls_Reader Payroll_CatA_SuiteXls = null;
	public static Xls_Reader Payroll_CatB_SuiteXls = null;
	public static Xls_Reader Payroll_CatC_SuiteXls = null;
	public static Xls_Reader Payroll_CatH_SuiteXls = null;
	public static Xls_Reader Payroll_CatD_SuiteXls = null;
	public static Xls_Reader Payroll_CatE_SuiteXls = null;
	public static Xls_Reader Payroll_CatI_SuiteXls = null;
	public static Xls_Reader Payroll_CatJ_SuiteXls = null;
	public static Xls_Reader Payroll_CatK_SuiteXls = null;
	public static Xls_Reader Payroll_CatL_SuiteXls = null;
	public static Xls_Reader Payroll_CatM_SuiteXls = null;
	public static Xls_Reader Payroll_CatZ_SuiteXls = null;
	public static Xls_Reader Payroll_2WeeklyCatA_SuiteXls = null;
	public static Xls_Reader Payroll_2WeeklyCatB_SuiteXls = null;
	public static Xls_Reader Payroll_2WeeklyCatC_SuiteXls = null;
	public static Xls_Reader Payroll_2WeeklyCatD_SuiteXls = null;
	public static Xls_Reader Payroll_2WeeklyCatE_SuiteXls = null;
	public static Xls_Reader Payroll_2WeeklyCatH_SuiteXls = null;
	public static Xls_Reader Payroll_2WeeklyCatI_SuiteXls = null;
	public static Xls_Reader Payroll_2WeeklyCatJ_SuiteXls = null;
	public static Xls_Reader Payroll_2WeeklyCatK_SuiteXls = null;
	public static Xls_Reader Payroll_2WeeklyCatL_SuiteXls = null;
	public static Xls_Reader Payroll_2WeeklyCatM_SuiteXls = null;
	public static Xls_Reader Payroll_2WeeklyCatZ_SuiteXls = null;
	public static Xls_Reader Payroll_4WeeklyCatA_SuiteXls = null;
	public static Xls_Reader Payroll_4WeeklyCatB_SuiteXls = null;
	public static Xls_Reader Payroll_4WeeklyCatC_SuiteXls = null;
	public static Xls_Reader Payroll_4WeeklyCatD_SuiteXls = null;
	public static Xls_Reader Payroll_4WeeklyCatE_SuiteXls = null;
	public static Xls_Reader Payroll_4WeeklyCatH_SuiteXls = null;
	public static Xls_Reader Payroll_4WeeklyCatI_SuiteXls = null;
	public static Xls_Reader Payroll_4WeeklyCatJ_SuiteXls = null;
	public static Xls_Reader Payroll_4WeeklyCatK_SuiteXls = null;
	public static Xls_Reader Payroll_4WeeklyCatL_SuiteXls = null;
	public static Xls_Reader Payroll_4WeeklyCatM_SuiteXls = null;
	public static Xls_Reader Payroll_4WeeklyCatZ_SuiteXls = null;
	public static Xls_Reader Payroll_MonthlyCatA_SuiteXls = null;
	public static Xls_Reader Payroll_MonthlyCatB_SuiteXls = null;
	public static Xls_Reader Payroll_MonthlyCatC_SuiteXls = null;
	public static Xls_Reader Payroll_MonthlyCatH_SuiteXls = null;
	public static Xls_Reader Payroll_MonthlyCatD_SuiteXls = null;
	public static Xls_Reader Payroll_MonthlyCatE_SuiteXls = null;
	public static Xls_Reader Payroll_MonthlyCatI_SuiteXls = null;
	public static Xls_Reader Payroll_MonthlyCatJ_SuiteXls = null;
	public static Xls_Reader Payroll_MonthlyCatK_SuiteXls = null;
	public static Xls_Reader Payroll_MonthlyCatL_SuiteXls = null;
	public static Xls_Reader Payroll_MonthlyCatM_SuiteXls = null;
	public static Xls_Reader Payroll_MonthlyCatZ_SuiteXls = null;
	public static Xls_Reader Payroll_ResetCategory_Taxyear201718_SuiteXls = null;
	public static Xls_Reader Payroll_GenerateTaxrateMonthly_SuiteXls201718 = null;
	public static Xls_Reader Payroll_GenerateTaxrateWeekly201718_SuiteXls = null;

	public static Xls_Reader Payroll_GenerateTaxrateMonthly_SuiteXls = null;
	public static Xls_Reader Payroll_GenerateTaxrateWeekly_SuiteXls = null;
	public static Xls_Reader Payroll_GenerateTaxrateMonth1LD0D1BR_SuiteXls = null;
	public static Xls_Reader Payroll_GenerateTaxrateMonth2LDOD1BRNTOTK50percentRL_SuiteXls = null;
	public static Xls_Reader Payroll_GenerateTaxrateMonth3LDOD1BRNTOTK50percentRL_SuiteXls = null;
	public static Xls_Reader Payroll_GenerateTaxrateMonth4LDOD1BRNTOTK50percentRL_SuiteXls = null;
	public static Xls_Reader Payroll_GenerateTaxrateWeek1LDOD1BRNTOTK50percentRL_SuiteXls = null;
	public static Xls_Reader Payroll_GenerateTaxrateWeek2LDOD1BRNTOTK50percentRL_SuiteXls = null;
	public static Xls_Reader Payroll_GenerateTaxrateWeek3LDOD1BRNTOTK50percentRL_SuiteXls = null;
	public static Xls_Reader Payroll_GenerateTaxrateWeek4LDOD1BRNTOTK50percentRL_SuiteXls = null;
	public static Xls_Reader Payroll_IncomeTax_TCWeek1_204045VariablePayANDWeekly50RL_SuiteXls = null;
	public static Xls_Reader Payroll_IncomeTax_TCWeek2_204045VariablePayANDWeekly50RL_SuiteXls = null;
	public static Xls_Reader Payroll_IncomeTax_TCWeek3_204045VariablePayANDWeekly50RL_SuiteXls = null;
	public static Xls_Reader Payroll_IncomeTax_TCWeek4_204045VariablePayANDWeekly50RL_SuiteXls = null;
	public static Xls_Reader Payroll_IncomeTax_TCWeek5_204045VariablePayANDWeekly50RL_SuiteXls = null;
	public static Xls_Reader Payroll_IncomeTax_TCWeek6_204045VariablePayANDWeekly50RL_SuiteXls = null;
	public static Xls_Reader Payroll_IncomeTax_TCWeek7_204045VariablePayANDWeekly50RL_SuiteXls = null;
	public static Xls_Reader Payroll_IncomeTax_TCWeek8_204045VariablePayANDWeekly50RL_SuiteXls = null;
	public static Xls_Reader Payroll_IncomeTax_TCWeek9_204045VariablePayANDWeekly50RL_SuiteXls = null;
	public static Xls_Reader Payroll_IncomeTax_TCWeek10_204045VariablePayANDWeekly50RL_SuiteXls = null;
	public static Xls_Reader Payroll_IncomeTax_TCWeek11_204045VariablePayANDWeekly50RL_SuiteXls = null;
	public static Xls_Reader Payroll_IncomeTax_TCWeek12_204045VariablePayANDWeekly50RL_SuiteXls = null;
	public static Xls_Reader Payroll_IncomeTax_TCMonth1_204045VariablePayANDMonthly50RL_SuiteXls = null;
	public static Xls_Reader Payroll_IncomeTax_TCMonth2_204045VariablePayANDMonthly50RL_SuiteXls = null;
	public static Xls_Reader Payroll_IncomeTax_TCMonth3_204045VariablePayANDMonthly50RL_SuiteXls = null;
	public static Xls_Reader Payroll_IncomeTax_TCMonth4_204045VariablePayANDMonthly50RL_SuiteXls = null;
	public static Xls_Reader Payroll_IncomeTax_TCMonth5_204045VariablePayANDMonthly50RL_SuiteXls = null;
	public static Xls_Reader Payroll_IncomeTax_TCMonth6_204045VariablePayANDMonthly50RL_SuiteXls = null;
	public static Xls_Reader Payroll_IncomeTax_TCMonth7_204045VariablePayANDMonthly50RL_SuiteXls = null;
	public static Xls_Reader Payroll_IncomeTax_TCMonth8_204045VariablePayANDMonthly50RL_SuiteXls = null;
	public static Xls_Reader Payroll_IncomeTax_TCMonth9_204045VariablePayANDMonthly50RL_SuiteXls = null;
	public static Xls_Reader Payroll_IncomeTax_TCMonth10_204045VariablePayANDMonthly50RL_SuiteXls = null;
	public static Xls_Reader Payroll_IncomeTax_TCMonth11_204045VariablePayANDMonthly50RL_SuiteXls = null;
	public static Xls_Reader Payroll_IncomeTax_TCMonth12_204045VariablePayANDMonthly50RL_SuiteXls = null;
	public static Xls_Reader Payroll_NI_DirectorAsEmployee_SuiteXls = null;
	public static Xls_Reader Payroll_NI_DirectorProrata_SuiteXls = null;
	public static Xls_Reader Payroll_NI_DirectorReachesFor_PensionAge_SuiteXls = null;
	public static Xls_Reader Payroll_NI_Director_AtoD_SuiteXls = null;
	public static Xls_Reader Payroll_Monthly_Taxable_And_Niable_PayCalcn_SuiteXls = null;
	public static Xls_Reader Payroll_Weekly_Taxable_And_Niable_PayCalcn_SuiteXls = null;
	public static Xls_Reader Product_NewJoiner_ScenNo1_SuiteXls = null;
	public static Xls_Reader Payroll_NI_CeaseandRecommence_SuiteXls = null;
	public static Xls_Reader Payroll_NI_Deferment_SuiteXls = null;
	public static Xls_Reader Payroll_NI_Director_Under21_SuiteXls = null;
	public static Xls_Reader Payroll_NI_Director_U21Lifecycle_SuiteXls = null;
	public static Xls_Reader Payroll_NI_Director_U25Aprentice_SuiteXls = null;

	public static Xls_Reader Payroll_AutoEnrolment_Initial_Setup_SuiteXls = null;
	public static Xls_Reader Payroll_AutoEnrolment_Starter_SuiteXls = null;
	public static Xls_Reader Payroll_AutoEnrolment_Employee_Turns22_SuiteXls = null;
	public static Xls_Reader Payroll_AutoEnrolment_Employee_ChangeInEarnings_SuiteXls = null;
	public static Xls_Reader Payroll_AutoEnrolment_Employee_OptIn_SuiteXls = null;
	public static Xls_Reader Payroll_AutoEnrolment_Employee_Join_SuiteXls = null;
	public static Xls_Reader Payroll_AutoEnrolment_Employee_Cease_SuiteXls = null;

	public static Xls_Reader Payroll_Statutory_maternitypay_SuiteXls = null;
	public static Xls_Reader Payroll_SMP_CreateLeaveRequest_SuiteXls = null;
	public static Xls_Reader Payroll_SMP_ProcessPayroll_SuiteXls = null;
	public static Xls_Reader Payroll_Statutory_Adoption_SuiteXls = null;
	public static Xls_Reader Payroll_SAP_ProcessPayroll_SuiteXls = null;
	public static Xls_Reader Payroll_SAP_CreateLeaveRequest_SuiteXls = null;
	public static Xls_Reader Payroll_Statutory_SickPay_SuiteXls = null;
	public static Xls_Reader Payroll_SSP_ProcessPayroll_SuiteXls = null;
	// public static Xls_Reader Payroll_SSP_CreateLeaveRequest_SuiteXls=null;
	// public static Xls_Reader Payroll_SSP_Create2ndLeaveRequest_SuiteXls=null;
	public static Xls_Reader Payroll_Statutory_Paternitypay_SuiteXls = null;
	public static Xls_Reader Payroll_SPP_CreateLeaveRequest_SuiteXls = null;
	public static Xls_Reader Payroll_SPP_ProcessPayroll_SuiteXls = null;
	public static Xls_Reader Payroll_Statutory_Paternitypay_Case2_SuiteXls = null;
	public static Xls_Reader Payroll_SPP_Case2_CreateLeaveRequest_SuiteXls = null;
	public static Xls_Reader Payroll_SPP_Case2_ProcessPayroll_SuiteXls = null;
	public static Xls_Reader Payroll_Statutory_AdoptionPaternitypay_SuiteXls = null;
	// public static Xls_Reader Payroll_SAPP_ProcessPayroll_SuiteXls=null;
	// public static Xls_Reader Payroll_SAPP_CreateLeaveRequest_SuiteXls=null;
	public static Xls_Reader Payroll_Statutory_SharedParentalpay_SuiteXls = null;
	public static Xls_Reader Payroll_ShPP_ProcessPayroll_SuiteXls = null;
	public static Xls_Reader Payroll_ShPP_CreateLeaveRequest_SuiteXls = null;

	public static Xls_Reader LeaveDemo_SuiteXls = null;
	public String LeaveDemo_Inputsheet;
	public static Xls_Reader Payroll_CreateEmployees_For_RecognitionScenarious_SuiteXls = null;

	public static Xls_Reader Payroll_RecognitionScenarious_SuiteXls = null;
	public static Xls_Reader Payroll_RecognitionScenarioTwo_SuiteXls = null;
	public static Xls_Reader Payroll_RecognitionScenarioThree_SuiteXls = null;
	public static Xls_Reader Payroll_RecognitionScenarioFour_SuiteXls = null;
	public static Xls_Reader Payroll_RecognitionScenarioFive_SuiteXls = null;
	public static Xls_Reader Payroll_RecognitionScenarioSix_SuiteXls = null;
	public static Xls_Reader Payroll_RecognitionScenarioSeven_SuiteXls = null;
	
	
	
	
	public static Xls_Reader TaxPayroll_TaxMonth1CSBRNTK50PercentRegulatory_SuiteXls = null;
	public static Xls_Reader TaxPayroll_TaxMonth2CSBRNTK50PercentRegulatory_SuiteXls = null;
	
	public static Xls_Reader TaxPayroll_TaxWeek1CSBRNTK50PercentRegulatory_SuiteXls = null;
	public static Xls_Reader TaxPayroll_TaxWeek2CSBRNTK50PercentRegulatory_SuiteXls = null;
	public static Xls_Reader TaxPayroll_TaxWeek3CSBRNTK50PercentRegulatory_SuiteXls = null;
	public static Xls_Reader TaxPayroll_TaxWeek4CSBRNTK50PercentRegulatory_SuiteXls = null;
	public static Xls_Reader TaxPayroll_TaxWeek5CSBRNTK50PercentRegulatory_SuiteXls = null;
	public static Xls_Reader TaxPayroll_TaxWeek6CSBRNTK50PercentRegulatory_SuiteXls = null;
	public static Xls_Reader TaxPayroll_TaxWeek7CSBRNTK50PercentRegulatory_SuiteXls = null;
	public static Xls_Reader TaxPayroll_TaxWeek8CSBRNTK50PercentRegulatory_SuiteXls = null;

	public static Xls_Reader TaxPayroll_TaxWeek9CSBRNTK50PercentRegulatory_SuiteXls = null;
	public static Xls_Reader TaxPayroll_TaxWeek10CSBRNTK50PercentRegulatory_SuiteXls = null;

	
	
	
	
	
	

	public String TaxPayroll_TaxMonth1CSBRNTK50PercentRegulatory_Inputsheet;
	
	public String TaxPayroll_TaxMonth2CSBRNTK50PercentRegulatory_Inputsheet;
	

	public String TaxPayroll_TaxWeek1CSBRNTK50PercentRegulatory_Inputsheet;
	public String TaxPayroll_TaxWeek2CSBRNTK50PercentRegulatory_Inputsheet;
	public String TaxPayroll_TaxWeek3CSBRNTK50PercentRegulatory_Inputsheet;
	public String TaxPayroll_TaxWeek4CSBRNTK50PercentRegulatory_Inputsheet;
	public String TaxPayroll_TaxWeek5CSBRNTK50PercentRegulatory_Inputsheet;
	public String TaxPayroll_TaxWeek6CSBRNTK50PercentRegulatory_Inputsheet;
	public String TaxPayroll_TaxWeek7CSBRNTK50PercentRegulatory_Inputsheet;
	public String TaxPayroll_TaxWeek8CSBRNTK50PercentRegulatory_Inputsheet;
	public String TaxPayroll_TaxWeek9CSBRNTK50PercentRegulatory_Inputsheet;
	public String TaxPayroll_TaxWeek10CSBRNTK50PercentRegulatory_Inputsheet;

	

	public static Xls_Reader TaxPayroll_TaxMonth3CSBRNTK50PercentRegulatory_SuiteXls = null;
	public String TaxPayroll_TaxMonth3CSBRNTK50PercentRegulatory_Inputsheet;
	
	public static Xls_Reader TaxPayroll_TaxMonth4CSBRNTK50PercentRegulatory_SuiteXls = null;
	public String TaxPayroll_TaxMonth4CSBRNTK50PercentRegulatory_Inputsheet;
	
	public static Xls_Reader TaxPayroll_TaxMonth5CSBRNTK50PercentRegulatory_SuiteXls = null;
	public String TaxPayroll_TaxMonth5CSBRNTK50PercentRegulatory_Inputsheet;
	
	public static Xls_Reader TaxPayroll_TaxMonth6CSBRNTK50PercentRegulatory_SuiteXls = null;
	public String TaxPayroll_TaxMonth6CSBRNTK50PercentRegulatory_Inputsheet;
	
	public static Xls_Reader TaxPayroll_TaxMonth7CSBRNTK50PercentRegulatory_SuiteXls = null;
	public String TaxPayroll_TaxMonth7CSBRNTK50PercentRegulatory_Inputsheet;
	
	public static Xls_Reader TaxPayroll_TaxMonth8CSBRNTK50PercentRegulatory_SuiteXls = null;
	public String TaxPayroll_TaxMonth8CSBRNTK50PercentRegulatory_Inputsheet;
	
	public static Xls_Reader TaxPayroll_TaxMonth9CSBRNTK50PercentRegulatory_SuiteXls = null;
	public String TaxPayroll_TaxMonth9CSBRNTK50PercentRegulatory_Inputsheet;
	
	public static Xls_Reader TaxPayroll_TaxMonth10CSBRNTK50PercentRegulatory_SuiteXls = null;
	public String TaxPayroll_TaxMonth10CSBRNTK50PercentRegulatory_Inputsheet;

	
	

	public String PayrollRecognition_Inputsheet;
	public String EmployeeCreation_For_PayrollRecognition_Inputsheet;
	public String PayrollRecognitionScenario2_Inputsheet;
	public String PayrollRecognitionScenario3_Inputsheet;
	public String PayrollRecognitionScenario4_Inputsheet;
	public String PayrollRecognitionScenario5_Inputsheet;
	public String PayrollRecognitionScenario6_Inputsheet;
	public String PayrollRecognitionScenario7_Inputsheet;

	public static boolean isInitialized = false;
	public static WebDriver driver = null;
	public static boolean IsbrowserOpened = false;
	boolean exlude = true;
	boolean windowExclude = true;
	public String PayRunTextName;

	public static String TaxReport = "DO NOT TOUCH - TAX PAYROLL AUTOMATION";
	public static String NIReport = "DO NOT TOUCH - PAYROLL AUTOMATION CHECK";
	public static String DirAsEmployee = "DO NOT TOUCH - Director as employee";
	public static String DirAsProRata = "DO NOT TOUCH - AUTOMATION DIR PRORATA";
	public static String CeaseAndRecommence = "DO NOT TOUCH- DIR Cease and recommence";
	public static String ReachesPensionAge = "DO NOT TOUCH - AUTOMN DIR REACHES PENSEN";
	public static String Deferment = "DO NOT TOUCH AUTOMATION DIR NI DEFERMENT";
	public static String Deferment201718 = "DO NOT TOUCH ATOMTION DIR DEFRMNT201718";

	public static String AtoD = "DO NOT TOUCH - AUTOMATION DIR A to D";
	public static String Under21 = "DO NOT TOUCH AUTOMATION DIR NI UNDER21";
	public static String Under21201718 = "DO NOT TOUCH ATOMTION DIR UNDER21 201718";
	public static String U25Aprentice201718 = "DO NOT TOUCH-AUTOMNDIR U25Aprntice1718";

	public static String SMP1stReport = "DO NOT TOUCH SMP GROSS PAYMENTS";
	public static String SMP2ndReport = "DO NOT TOUCH SMP AVERAGE WEEKLY EARNINGS";
	public static String SMP3dReport = "DO NOT TOUCH SMP Maternity Payment";
	public static String SAP1stReport = "DO NOT TOUCH SAP GROSS PAYMENTS";
	public static String SAP2ndReport = "DO NOT TOUCH SAP AVERAGE WEEKLY EARNINGS";
	public static String SAP3dReport = "DO NOT TOUCH SAP Adoption Payment";
	public static String SSP1stReport = "DO NOT TOUCH SSP GROSS PAYMENTS";
	public static String SSP2ndReport = "DO NOT TOUCH SSP AVERAGE WEEKLY EARNINGS";
	public static String SSP3dReport = "DO NOT TOUCH SSP Payment";
	public static String SSP4thReport = "DO NOT TOUCH SSP AVERAGE WEEKLY EARNGS2";
	public static String SPP1stReport = "DO NOT TOUCH SPP GROSS PAYMENTS";
	public static String SPP2ndReport = "DO NOT TOUCH SPP AVERAGE WEEKLY EARNINGS";
	public static String SPP3dReport = "DO NOT TOUCH Statutory Paternity Payment";
	public static String SPPCase2_1stReport = "DO NOT TOUCH SPPCase2 GROSS PAYMENTS";
	public static String SPPCase2_2ndReport = "DO NOT TOUCH SPPCase2 AVERAGE WEEKLY EAR";
	public static String SPPCase2_3dReport = "DO NOT TOUCH Statory Paternty Pymt Case2";
	public static String SAPP_1stReport = "DO NOT TOUCH SAPP GROSS PAYMENTS";
	public static String SAPP_2ndReport = "DO NOT TOUCH SAPP AVERAGE WEEKLY EARNING";
	public static String SAPP_3dReport = "DO NOT TOUCH SAAP Payments";
	public static String ShPP_1stReport = "DO NOT TOUCH ShPP GROSS PAYMENTS";
	public static String ShPP_2ndReport = "DO NOT TOUCH ShPP AVERAGE WEEKLY EARNING";
	public static String ShPP_3dReport = "DO NOT TOUCH ShPP Payments";
	public static String AutoEnrol_StarterReport = "DO NOT TOUCH AUTO ENROLMENT AUTOMATION";
	public static String PayrollRTI_RecognitionReport = "DO NOT TOUCH PAYROL RTI RECGNTION REPORT";
	public static String PayrollRTI_RecognitionS2Report = "DO NOT TOUCH PAYROL RTI SCENRIO2 REPORT";
	public static String PayrollRTI_RecognitionS3Report = "DO NOT TOUCH PAYROL RTI SCENRIO3 REPORT";
	public static String PayrollRTI_RecognitionS4Report = "DO NOT TOUCH PAYROL RTI SCENRIO4 REPORT";
	public static String PayrollRTI_RecognitionS5Report = "DO NOT TOUCH PAYROL RTI SCENRIO5 REPORT";
	public static String PayrollRTI_RecognitionS6Report = "DO NOT TOUCH PAYROL RTI SCENRIO6 REPORT";
	public static String PayrollRTI_RecognitionS5Report_ReJoin = "DO NOT TOUCH PAYROL RTI SCENRIO5 REJOIN";
	public static String PayrollRTI_RecognitionS7Report = "DO NOT TOUCH PAYROL RTI SCENRIO7 REPORT";
	public static String PayrollRTI_RecognitionS7Report_JuneToMarch = "DO NOT TOUCH PAYROL RTI SCENRIO71 REPORT";

	public String EMPLOYERNAMEWeekly = "DO NOT TOUCH AUTO ENROLMENT TEST EMPLOYER 1";
	public String PayrollIdWeekly = "PN-10679";
	public String NameOfReprt;
	public String pnReport = "PN-10834";
	public String pn = "Payroll-0070";
	public String pnWeek = "Payroll-0075";
	public String pnAutoEnrol2016 = "PN-10678";
	public String pnAutoEnrol2016_Employer2 = "PN-10680";
	public String pnAutoEnrol2016_Weekly = "PN-10679";

	public String AutoenrolPNWeek = "PN-10679";
	public String pnFourWeek = "Payroll-0007";
	public String pnTwoWeek = "Payroll-0096";
	public String selectedWeek;
	public int ReportSelected;
	public String PayrollReport;
	public int rownum;
	public String payrollRecordId;
	public String weekOneRecordId;
	public String weekRecordId;
	public String Month;
	public boolean employeeFirsttimeView = true;
	public boolean compensationFirsttimeView = true;
	public int Row_count;
	public int rownumNI;
	public int rownumx;
	public int totalRows;
	public String payrunMonth;
	public String ckbox;
	public int payrollcol_position = 0;
	public int Emplpoyercol_position = 0;
	public int frequencyCol_Postition = 0;

	public int empcolnum;
	public int taxcodecolnum;
	public int niCategoryColumn;
	public int directorNIBasis;
	public int directorSince;
	public int taxbasiscolnum;
	public int compnAnnualSalColumn;
	public int compPayfrequencyColumn;
	public int a, b, c, d, e;

	public int payruncol_position = 0;

	public String PayrollSuiteResetNICategory_InputExcelFile;
	public String GenerateTaxrateMonthly_InputExcelFile;
	public String GenerateTaxrateWeekly_InputExcelFile;
	public String GenerateTaxrateMonth1LD0D1BR_InputExcelFile;
	public String GenerateTaxrateMonth2LD0D1BR_InputExcelFile;
	public String GenerateTaxrateMonth3LD0D1BR_InputExcelFile;
	public String GenerateTaxrateMonth4LD0D1BR_InputExcelFile;
	public String GenerateTaxrateWeek1LDOD1BRNTOTK50percentRL_InputExcelFile;
	public String GenerateTaxrateWeek2LDOD1BRNTOTK50percentRL_InputExcelFile;
	public String GenerateTaxrateWeek3LDOD1BRNTOTK50percentRL_InputExcelFile;
	public String GenerateTaxrateWeek4LDOD1BRNTOTK50percentRL_InputExcelFile;

	public String IncomeTax_TCWeek1_204045VariablePayANDWeekly50RL_InputExcelFile;
	public String IncomeTax_TCWeek2_204045VariablePayANDWeekly50RL_InputExcelFile;
	public String IncomeTax_TCWeek3_204045VariablePayANDWeekly50RL_InputExcelFile;
	public String IncomeTax_TCWeek4_204045VariablePayANDWeekly50RL_InputExcelFile;
	public String IncomeTax_TCWeek5_204045VariablePayANDWeekly50RL_InputExcelFile;
	public String IncomeTax_TCWeek6_204045VariablePayANDWeekly50RL_InputExcelFile;
	public String IncomeTax_TCWeek7_204045VariablePayANDWeekly50RL_InputExcelFile;
	public String IncomeTax_TCWeek8_204045VariablePayANDWeekly50RL_InputExcelFile;
	public String IncomeTax_TCWeek9_204045VariablePayANDWeekly50RL_InputExcelFile;
	public String IncomeTax_TCWeek10_204045VariablePayANDWeekly50RL_InputExcelFile;
	public String IncomeTax_TCWeek11_204045VariablePayANDWeekly50RL_InputExcelFile;
	public String IncomeTax_TCWeek12_204045VariablePayANDWeekly50RL_InputExcelFile;

	public String IncomeTax_TCMonth1_204045VariablePayANDMonthly50RL_InputExcelFile;
	public String IncomeTax_TCMonth2_204045VariablePayANDMonthly50RL_InputExcelFile;
	public String IncomeTax_TCMonth3_204045VariablePayANDMonthly50RL_InputExcelFile;
	public String IncomeTax_TCMonth4_204045VariablePayANDMonthly50RL_InputExcelFile;
	public String IncomeTax_TCMonth5_204045VariablePayANDMonthly50RL_InputExcelFile;
	public String IncomeTax_TCMonth6_204045VariablePayANDMonthly50RL_InputExcelFile;
	public String IncomeTax_TCMonth7_204045VariablePayANDMonthly50RL_InputExcelFile;
	public String IncomeTax_TCMonth8_204045VariablePayANDMonthly50RL_InputExcelFile;
	public String IncomeTax_TCMonth9_204045VariablePayANDMonthly50RL_InputExcelFile;
	public String IncomeTax_TCMonth10_204045VariablePayANDMonthly50RL_InputExcelFile;
	public String IncomeTax_TCMonth11_204045VariablePayANDMonthly50RL_InputExcelFile;
	public String IncomeTax_TCMonth12_204045VariablePayANDMonthly50RL_InputExcelFile;
	public String IncomeTax_TCMonth13_204045VariablePayANDMonthly50RL_InputExcelFile;

	public String NI_Payroll_CatA_SuiteXls_InputExcelFile;
	public String NI_Payroll_CatB_SuiteXls_InputExcelFile;
	public String NI_Payroll_CatC_SuiteXls_InputExcelFile;
	public String NI_Payroll_CatD_SuiteXls_InputExcelFile;
	public String NI_Payroll_CatE_SuiteXls_InputExcelFile;
	public String NI_Payroll_CatH_SuiteXls_InputExcelFile;
	public String NI_Payroll_CatI_SuiteXls_InputExcelFile;
	public String NI_Payroll_CatJ_SuiteXls_InputExcelFile;
	public String NI_Payroll_CatK_SuiteXls_InputExcelFile;
	public String NI_Payroll_CatL_SuiteXls_InputExcelFile;
	public String NI_Payroll_CatM_SuiteXls_InputExcelFile;
	public String NI_Payroll_CatZ_SuiteXls_InputExcelFile;

	public String NI_Payroll_2WeeklyCatA_SuiteXls_InputExcelFile;
	public String NI_Payroll_2WeeklyCatB_SuiteXls_InputExcelFile;
	public String NI_Payroll_2WeeklyCatC_SuiteXls_InputExcelFile;
	public String NI_Payroll_2WeeklyCatD_SuiteXls_InputExcelFile;
	public String NI_Payroll_2WeeklyCatE_SuiteXls_InputExcelFile;
	public String NI_Payroll_2WeeklyCatH_SuiteXls_InputExcelFile;
	public String NI_Payroll_2WeeklyCatI_SuiteXls_InputExcelFile;
	public String NI_Payroll_2WeeklyCatJ_SuiteXls_InputExcelFile;
	public String NI_Payroll_2WeeklyCatK_SuiteXls_InputExcelFile;
	public String NI_Payroll_2WeeklyCatL_SuiteXls_InputExcelFile;
	public String NI_Payroll_2WeeklyCatM_SuiteXls_InputExcelFile;
	public String NI_Payroll_2WeeklyCatZ_SuiteXls_InputExcelFile;

	public String NI_Payroll_4WeeklyCatA_SuiteXls_InputExcelFile;
	public String NI_Payroll_4WeeklyCatB_SuiteXls_InputExcelFile;
	public String NI_Payroll_4WeeklyCatC_SuiteXls_InputExcelFile;
	public String NI_Payroll_4WeeklyCatD_SuiteXls_InputExcelFile;
	public String NI_Payroll_4WeeklyCatE_SuiteXls_InputExcelFile;
	public String NI_Payroll_4WeeklyCatH_SuiteXls_InputExcelFile;
	public String NI_Payroll_4WeeklyCatI_SuiteXls_InputExcelFile;
	public String NI_Payroll_4WeeklyCatJ_SuiteXls_InputExcelFile;
	public String NI_Payroll_4WeeklyCatK_SuiteXls_InputExcelFile;
	public String NI_Payroll_4WeeklyCatL_SuiteXls_InputExcelFile;
	public String NI_Payroll_4WeeklyCatM_SuiteXls_InputExcelFile;
	public String NI_Payroll_4WeeklyCatZ_SuiteXls_InputExcelFile;

	public String NI_Payroll_MonthlyCatA_SuiteXls_InputExcelFile;
	public String NI_Payroll_MonthlyCatB_SuiteXls_InputExcelFile;
	public String NI_Payroll_MonthlyCatC_SuiteXls_InputExcelFile;
	public String NI_Payroll_MonthlyCatD_SuiteXls_InputExcelFile;
	public String NI_Payroll_MonthlyCatE_SuiteXls_InputExcelFile;
	public String NI_Payroll_MonthlyCatH_SuiteXls_InputExcelFile;
	public String NI_Payroll_MonthlyCatI_SuiteXls_InputExcelFile;
	public String NI_Payroll_MonthlyCatJ_SuiteXls_InputExcelFile;
	public String NI_Payroll_MonthlyCatK_SuiteXls_InputExcelFile;
	public String NI_Payroll_MonthlyCatL_SuiteXls_InputExcelFile;
	public String NI_Payroll_MonthlyCatM_SuiteXls_InputExcelFile;
	public String NI_Payroll_MonthlyCatZ_SuiteXls_InputExcelFile;

	public String Payroll_NI_DirectorAsEmployee_SuiteXls_InputExcelFile;
	public String Payroll_NI_DirectorAsProrata_SuiteXls_InputExcelFile;
	public String Payroll_NI_CeaseandRecommence_SuiteXls_InputExcelFile;
	public String Payroll_NI_ReachesANDPension_SuiteXls_InputExcelFile;
	public String Payroll_NI_Deferment_SuiteXls_InputExcelFile;
	public String Payroll_NI_Under21_SuiteXls_InputExcelFile;
	public String Payroll_NI_U25Apprentice_SuiteXls_InputExcelFile;

	public String Payroll_Month7_NI_DirectorAsEmployee_SuiteXls_InputExcelFile;
	public String Payroll_Month8_NI_DirectorAsEmployee_SuiteXls_InputExcelFile;
	public String Payroll_Month9_NI_DirectorAsEmployee_SuiteXls_InputExcelFile;
	public String Payroll_Month10_NI_DirectorAsEmployee_SuiteXls_InputExcelFile;
	public String Payroll_Month11_NI_DirectorAsEmployee_SuiteXls_InputExcelFile;
	public String Payroll_Month12_NI_DirectorAsEmployee_SuiteXls_InputExcelFile;

	public String Statutory_MaternityPay_SuiteXls_InputExcelFile;
	public String Statutory_AdoptionPay_SuiteXls_InputExcelFile;
	public String Statutory_StatutoryPaternityPay_InputExcelFile;
	public String StatutoryPaternityPayCase2_InputExcelFile;
	public String SAPP_InputExcelFile;
	public String SharedParental_InputExcelFile;
	public String SSP_InputExcelFile;

	public void openNewTab() {
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
		ArrayList<String> tabs = new ArrayList<String>(
				driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
	}

	public boolean isAlertPresent() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			System.out.println("The ok button of the popup alert dialog box got accepted successfully");
			return true;
		} catch (NoAlertPresentException Ex) {
			System.out
					.println("The exception occured hence alert dialog box did not displayed");
			return false;
		}
	}

	public void closeNewTab() {
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "w");
	}

	public void compensationSelectValue() throws Throwable {
		if (existsElementchkFor1mts(OR.getProperty("SelectView"))) {
			System.out.println("I am in Compensation page");
			System.out.println("I recognised the view");
			Select selectByValue = new Select(driver.findElement(By.xpath(OR
					.getProperty("SelectView"))));
			// selectByValue.selectByValue("00Bb0000004Ala1");
			selectByValue
					.selectByVisibleText("DO NOT TOUCH PAYROLL AUTOMATION TESTING");
			Thread.sleep(1000L);
			getObject("ViewGOClick").sendKeys("");
			getObject("ViewGOClick").click();
			System.out.println("The compensation GO Button got clicked");

			Thread.sleep(8000L);
		}
	}

	public static WebDriver getHandleToWindow(String url) {
		// parentWindowHandle =
		// WebDriverInitialize.getDriver().getWindowHandle(); // save the
		// current window handle.
		WebDriver popup = null;
		Set<String> windowIterator = driver.getWindowHandles();
		System.err.println("No of windows :  " + windowIterator.size());
		for (String s : windowIterator) {
			String windowHandle = s;
			popup = driver.switchTo().window(windowHandle);
			System.out.println("Window Title : " + popup.getTitle());
			System.out.println("Window Url : " + popup.getCurrentUrl());
			if (popup.getCurrentUrl().indexOf(url) != -1) {
				System.out.println("Selected Window Title : "
						+ popup.getTitle());
				return popup;
			}

		}
		System.out.println("Window Title :" + popup.getTitle());
		System.out.println();
		return popup;
	}

	public double ParseDouble(String strNumber) {
		if (strNumber != null && strNumber.length() > 0) {
			try {
				return Double.parseDouble(strNumber);
			} catch (Exception e) {
				return -1; // or some value to mark this field is wrong. or make
							// a function validates field first ...
			}
		} else
			return 0;
	}

	// This 'existsElement method works excellent use this method only for
	// explicit wait in the code

	public boolean existsElement(String id) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60/*
															 * Timeout in
															 * seconds
															 */);
			element = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath(id)));
			element.sendKeys("");

		} catch (Throwable t) {
			ErrorUtil.addVerificationFailure(t);
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
			APP_LOGS.debug("id" + id + " is not present");

			return false;
		}

		return true;
	}

	public boolean existsElementchkFor5mts(String id) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 300/*
															 * Timeout in
															 * seconds
															 */);
			element = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath(id)));
			element.sendKeys("");

		} catch (Throwable t) {
			ErrorUtil.addVerificationFailure(t);
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
			APP_LOGS.debug("id" + id + " is not present");

			return false;
		}

		return true;
	}

	public boolean existsElementchkFor1mts(String id) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 120/*
															 * Timeout in
															 * seconds
															 */);
			element = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath(id)));
			element.sendKeys("");

		} catch (Throwable t) {
			ErrorUtil.addVerificationFailure(t);
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
			APP_LOGS.debug("id" + id + " is not present");

			return false;
		}

		return true;
	}

	public boolean existsElementchkFor2mts(String id) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 120/*
															 * Timeout in
															 * seconds
															 */);
			element = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath(id)));
			element.sendKeys("");

		} catch (Throwable t) {
			ErrorUtil.addVerificationFailure(t);
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
			APP_LOGS.debug("id" + id + " is not present");

			return false;
		}

		return true;
	}

	public boolean existsElementchkFor3mts(String id) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 180/*
															 * Timeout in
															 * seconds
															 */);
			element = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath(id)));
			element.sendKeys("");

		} catch (Throwable t) {
			ErrorUtil.addVerificationFailure(t);
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
			APP_LOGS.debug("id" + id + " is not present");

			return false;
		}

		return true;
	}

	public boolean existsElementchkFor10mts(String id) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 600/*
															 * Timeout in
															 * seconds
															 */);
			element = wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath(id)));
			element.sendKeys("");

		} catch (Throwable t) {
			ErrorUtil.addVerificationFailure(t);
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
			APP_LOGS.debug("id" + id + " is not present");

			return false;
		}

		return true;
	}

	public boolean existsElementCSS(String id) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20/*
															 * Timeout in
															 * seconds
															 */);
			element = wait.until(ExpectedConditions.elementToBeClickable(By
					.cssSelector(id)));
			element.sendKeys("");

		} catch (Throwable t) {
			ErrorUtil.addVerificationFailure(t);
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
			APP_LOGS.debug("id" + id + " is not present");

			return false;
		}

		return true;
	}

	public boolean existsWebElement(WebElement wid) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 120/*
															 * Timeout in
															 * seconds
															 */);
			element = wait
					.until(ExpectedConditions.elementToBeClickable((wid)));
			element.sendKeys("");

		} catch (Throwable t) {
			ErrorUtil.addVerificationFailure(t);
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
			APP_LOGS.debug("id" + wid + " is not present");

			return false;
		}

		return true;
	}

	public void pressEnter(String id) {
		try {

			WebDriverWait wait = new WebDriverWait(driver, 8);
			// wait.until(ExpectedConditions.elementToBeClickable(By.xpath(id)));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(id)));
			driver.findElement(By.xpath(id)).sendKeys("");
			driver.findElement(By.xpath(id)).click();
			driver.findElement(By.xpath(id)).click();
			// getObject("reportNameLocatorNI").click();

			System.out.println("the report got cliked");
			Thread.sleep(4000L);

		} catch (Throwable t) {
			ErrorUtil.addVerificationFailure(t);

			// APP_LOGS.debug("id is not present");

		}

	}

	public boolean existsElementcss(String id) {
		try {
			driver.findElement(By.cssSelector(id));
		} catch (Throwable t) {
			ErrorUtil.addVerificationFailure(t);

			APP_LOGS.debug("id is not present");
			// System.out.println("id is not present ");
			return false;
		}

		return true;
	}

	// this function is used to initialize the tests

	public void initialize() throws IOException {

		if (!isInitialized) {

			// intialise the logs,
			APP_LOGS = Logger.getLogger("devpinoyLogger");
			APP_LOGS.debug("Loading Prorperty files");

			// configs
			CONFIG = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir")
							+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_Config//config.properties");
			CONFIG.load(ip);
			System.out.println(CONFIG.getProperty("Screenshot"));
			ip.close();

			OR = new Properties();
			ip = new FileInputStream(
					System.getProperty("user.dir")
							+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_Config//OR.properties");
			OR.load(ip);
			System.out.println(OR.get("Login_link"));
			APP_LOGS.debug("Loaded Property files successfully");
			APP_LOGS.debug("Loading Xlsx Files");
			ip.close();

			// xls files.

			// Payroll_GenerateTaxrateWeekly_SuiteXls201718=new
			// Xls_Reader(System.getProperty("user.dir") +
			// "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite GeneralAndLargeTaxcodeWeekly201718.xlsx");
			// Payroll_GenerateTaxrateMonthly_SuiteXls=new
			// Xls_Reader(System.getProperty("user.dir") +
			// "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite GeneralAndLargeTaxcodeMonthly.xlsx");

			/* Weekly Tax Files */
			Payroll_IncomeTax_TCWeek1_204045VariablePayANDWeekly50RL_SuiteXls = new Xls_Reader(
					System.getProperty("user.dir")
							+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite IncomeTax_TCWeek1_204045VariablePay&Weekly50RL.xlsx");
			Payroll_IncomeTax_TCWeek2_204045VariablePayANDWeekly50RL_SuiteXls = new Xls_Reader(
					System.getProperty("user.dir")
							+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite IncomeTax_TCWeek2_204045VariablePay&Weekly50RL.xlsx");
			Payroll_IncomeTax_TCWeek3_204045VariablePayANDWeekly50RL_SuiteXls = new Xls_Reader(
					System.getProperty("user.dir")
							+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite IncomeTax_TCWeek3_204045VariablePay&Weekly50RL.xlsx");
			Payroll_IncomeTax_TCWeek4_204045VariablePayANDWeekly50RL_SuiteXls = new Xls_Reader(
					System.getProperty("user.dir")
							+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite IncomeTax_TCWeek4_204045VariablePay&Weekly50RL.xlsx");
			Payroll_IncomeTax_TCWeek5_204045VariablePayANDWeekly50RL_SuiteXls = new Xls_Reader(
					System.getProperty("user.dir")
							+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite IncomeTax_TCWeek5_204045VariablePay&Weekly50RL.xlsx");
			Payroll_IncomeTax_TCWeek6_204045VariablePayANDWeekly50RL_SuiteXls = new Xls_Reader(
					System.getProperty("user.dir")
							+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite IncomeTax_TCWeek6_204045VariablePay&Weekly50RL.xlsx");
			Payroll_IncomeTax_TCWeek7_204045VariablePayANDWeekly50RL_SuiteXls = new Xls_Reader(
					System.getProperty("user.dir")
							+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite IncomeTax_TCWeek7_204045VariablePay&Weekly50RL.xlsx");
			Payroll_IncomeTax_TCWeek8_204045VariablePayANDWeekly50RL_SuiteXls = new Xls_Reader(
					System.getProperty("user.dir")
							+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite IncomeTax_TCWeek8_204045VariablePay&Weekly50RL.xlsx");
			Payroll_IncomeTax_TCWeek9_204045VariablePayANDWeekly50RL_SuiteXls = new Xls_Reader(
					System.getProperty("user.dir")
							+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite IncomeTax_TCWeek9_204045VariablePay&Weekly50RL.xlsx");
			Payroll_IncomeTax_TCWeek10_204045VariablePayANDWeekly50RL_SuiteXls = new Xls_Reader(
					System.getProperty("user.dir")
							+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite IncomeTax_TCWeek10_204045VariablePay&Weekly50RL.xlsx");
			Payroll_IncomeTax_TCWeek11_204045VariablePayANDWeekly50RL_SuiteXls = new Xls_Reader(
					System.getProperty("user.dir")
							+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite IncomeTax_TCWeek11_204045VariablePay&Weekly50RL.xlsx");
			Payroll_IncomeTax_TCWeek12_204045VariablePayANDWeekly50RL_SuiteXls = new Xls_Reader(
					System.getProperty("user.dir")
							+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite IncomeTax_TCWeek12_204045VariablePay&Weekly50RL.xlsx");

			/* Monyhly Tax Files */
			Payroll_IncomeTax_TCMonth2_204045VariablePayANDMonthly50RL_SuiteXls = new Xls_Reader(
					System.getProperty("user.dir")
							+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Month2of204045VariablePayAnd50regulatoryLimit.xlsx");
			Payroll_IncomeTax_TCMonth3_204045VariablePayANDMonthly50RL_SuiteXls = new Xls_Reader(
					System.getProperty("user.dir")
							+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Month3of204045VariablePayAnd50regulatoryLimit.xlsx");
			Payroll_IncomeTax_TCMonth4_204045VariablePayANDMonthly50RL_SuiteXls = new Xls_Reader(
					System.getProperty("user.dir")
							+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Month4of204045VariablePayAnd50regulatoryLimit.xlsx");
			Payroll_IncomeTax_TCMonth5_204045VariablePayANDMonthly50RL_SuiteXls = new Xls_Reader(
					System.getProperty("user.dir")
							+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Month5of204045VariablePayAnd50regulatoryLimit.xlsx");
			Payroll_IncomeTax_TCMonth6_204045VariablePayANDMonthly50RL_SuiteXls = new Xls_Reader(
					System.getProperty("user.dir")
							+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Month6of204045VariablePayAnd50regulatoryLimit.xlsx");
			Payroll_IncomeTax_TCMonth7_204045VariablePayANDMonthly50RL_SuiteXls = new Xls_Reader(
					System.getProperty("user.dir")
							+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Month7of204045VariablePayAnd50regulatoryLimit.xlsx");
			Payroll_IncomeTax_TCMonth8_204045VariablePayANDMonthly50RL_SuiteXls = new Xls_Reader(
					System.getProperty("user.dir")
							+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Month8of204045VariablePayAnd50regulatoryLimit.xlsx");
			Payroll_IncomeTax_TCMonth9_204045VariablePayANDMonthly50RL_SuiteXls = new Xls_Reader(
					System.getProperty("user.dir")
							+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Month9of204045VariablePayAnd50regulatoryLimit.xlsx");
			Payroll_IncomeTax_TCMonth10_204045VariablePayANDMonthly50RL_SuiteXls = new Xls_Reader(
					System.getProperty("user.dir")
							+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Month10of204045VariablePayAnd50regulatoryLimit.xlsx");
			Payroll_IncomeTax_TCMonth11_204045VariablePayANDMonthly50RL_SuiteXls = new Xls_Reader(
					System.getProperty("user.dir")
							+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Month11of204045VariablePayAnd50regulatoryLimit.xlsx");
			Payroll_IncomeTax_TCMonth12_204045VariablePayANDMonthly50RL_SuiteXls = new Xls_Reader(
					System.getProperty("user.dir")
							+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Month12of204045VariablePayAnd50regulatoryLimit.xlsx");

			Payroll_NI_Director_AtoD_SuiteXls = new Xls_Reader(
					System.getProperty("user.dir")
							+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite NI Director AtoD.xlsx");

			// Payroll_Monthly_Taxable_And_Niable_PayCalcn_SuiteXls=new
			// Xls_Reader(System.getProperty("user.dir") +
			// "//src//salesforce_XLS_Files//monthlyTaxableNiablePaycalculn.xlsx");
			// Payroll_Weekly_Taxable_And_Niable_PayCalcn_SuiteXls=new
			// Xls_Reader(System.getProperty("user.dir") +
			// "//src//salesforce_XLS_Files//WeeklyTaxableNiablePaycalculn.xlsx");
			// Product_NewJoiner_ScenNo1_SuiteXls=new
			// Xls_Reader(System.getProperty("user.dir") +
			// "//src//salesforce_XLS_Files//Product NewJoiner_ScenarioNo1.xlsx");
			// Payroll_NI_Director_U21Lifecycle_SuiteXls=new
			// Xls_Reader(System.getProperty("user.dir") +
			// "//src//salesforce_XLS_Files//U21 NI Life cycle.xlsx");
			// Payroll_SAP_ProcessPayroll_SuiteXls=new
			// Xls_Reader(System.getProperty("user.dir") +
			// "//src//salesforce_XLS_Files//Payroll Suite SAP ProcessPayroll.xlsx");
			// Payroll_SAP_CreateLeaveRequest_SuiteXls=new
			// Xls_Reader(System.getProperty("user.dir") +
			// "//src//salesforce_XLS_Files//Payroll Suite SAP CreateLeaveYear.xlsx");
			Payroll_Statutory_SickPay_SuiteXls = new Xls_Reader(
					System.getProperty("user.dir")
							+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite StatutorySickPay.xlsx");
			// Payroll_SPP_ProcessPayroll_SuiteXls=new
			// Xls_Reader(System.getProperty("user.dir") +
			// "//src//salesforce_XLS_Files//Payroll Suite SPP ProcessPayroll.xlsx");
			// Payroll_SPP_CreateLeaveRequest_SuiteXls=new
			// Xls_Reader(System.getProperty("user.dir") +
			// "//src//salesforce_XLS_Files//Payroll Suite SPP CreateLeaveYear.xlsx");
			// Payroll_SPP_Case2_CreateLeaveRequest_SuiteXls=new
			// Xls_Reader(System.getProperty("user.dir") +
			// "//src//salesforce_XLS_Files//Payroll Suite SPPCase2 CreateLeaveYear.xlsx");
			// Payroll_SPP_Case2_ProcessPayroll_SuiteXls=new
			// Xls_Reader(System.getProperty("user.dir") +
			// "//src//salesforce_XLS_Files//Payroll Suite SPPCase2 ProcessPayroll.xlsx");
			// Payroll_SAPP_ProcessPayroll_SuiteXls=new
			// Xls_Reader(System.getProperty("user.dir") +
			// "//src//salesforce_XLS_Files//Payroll Suite SAPP ProcessPayroll.xlsx");
			// Payroll_SAPP_CreateLeaveRequest_SuiteXls=new
			// Xls_Reader(System.getProperty("user.dir") +
			// "//src//salesforce_XLS_Files//Payroll Suite SAPP CreateLeaveYear.xlsx");
			// Payroll_ShPP_ProcessPayroll_SuiteXls=new
			// Xls_Reader(System.getProperty("user.dir") +
			// "//src//salesforce_XLS_Files//Payroll Suite ShPP ProcessPayroll.xlsx");
			// Payroll_ShPP_CreateLeaveRequest_SuiteXls=new
			// Xls_Reader(System.getProperty("user.dir") +
			// "//src//salesforce_XLS_Files//Payroll Suite ShPP CreateLeaveYear.xlsx");

			Payroll_AutoEnrolment_Initial_Setup_SuiteXls = new Xls_Reader(
					System.getProperty("user.dir")
							+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite AutoEnrolment Initial Setup.xlsx");
			Payroll_AutoEnrolment_Starter_SuiteXls = new Xls_Reader(
					System.getProperty("user.dir")
							+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite AutoEnrolment Starter.xlsx");
			Payroll_AutoEnrolment_Employee_Turns22_SuiteXls = new Xls_Reader(
					System.getProperty("user.dir")
							+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite AutoEnrolment Employee Turns22.xlsx");
			Payroll_AutoEnrolment_Employee_ChangeInEarnings_SuiteXls = new Xls_Reader(
					System.getProperty("user.dir")
							+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite AutoEnrolment Change in Earnings.xlsx");
			Payroll_AutoEnrolment_Employee_OptIn_SuiteXls = new Xls_Reader(
					System.getProperty("user.dir")
							+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite AutoEnrolment OptIn.xlsx");
			Payroll_AutoEnrolment_Employee_Join_SuiteXls = new Xls_Reader(
					System.getProperty("user.dir")
							+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite AutoEnrolment Join.xlsx");
			Payroll_AutoEnrolment_Employee_Cease_SuiteXls = new Xls_Reader(
					System.getProperty("user.dir")
							+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite AutoEnrolment Cease.xlsx");

			SuiteXls = new Xls_Reader(
					System.getProperty("user.dir")
							+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Suite.xlsx");

			APP_LOGS.debug("Loaded Xlsx Files Successfully");
			isInitialized = true;

		}

		// intialize the tests
		// initialize the RC OR WebDriver interface. in step 2 of core frame
		// work this will happen.

	}

	// Webdriver functions to open the browser.
	public void openBrowser() throws Exception {
		if (!IsbrowserOpened) {
			if (CONFIG.getProperty("browserType").equalsIgnoreCase("Mozilla")) {
				// System.setProperty("webdriver.firefox.marionette","F:\\Automation XCD\\Webdriver\\geckodriver.exe");
				System.setProperty("webdriver.firefox.marionette",
						System.getProperty("user.dir")
								+ "//drivers//geckodriver.exe");
				driver = new FirefoxDriver(FirefoxDriverProfile());

			} else if (CONFIG.getProperty("browserType").equalsIgnoreCase("IE")) {

				driver = new InternetExplorerDriver();

			} else if (CONFIG.getProperty("browserType").equalsIgnoreCase(
					"Chrome")) {

				driver = new ChromeDriver();

				IsbrowserOpened = true;
				String waitTime = CONFIG.getProperty("default_implicitWait");
				driver.manage()
						.timeouts()
						.implicitlyWait((Long.parseLong(waitTime)),
								TimeUnit.SECONDS);
			}
		}
	}

	public void defaultWaitTime() throws Throwable {
		try {
			String waitTime = CONFIG.getProperty("default_implicitWait");
			driver.manage()
					.timeouts()
					.implicitlyWait((Long.parseLong(waitTime)),
							TimeUnit.SECONDS);

		} catch (Throwable t) {
			t.getMessage();
			t.getStackTrace();
		}
	}

	public static FirefoxProfile FirefoxDriverProfile() throws Exception {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("browser.download.folderList", 2);
		profile.setPreference("browser.download.manager.showWhenStarting",
				false);
		profile.setPreference("browser.download.dir", downloadPath);
		profile.setPreference(
				"browser.helperApps.neverAsk.openFile",
				"text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
		profile.setPreference(
				"browser.helperApps.neverAsk.saveToDisk",
				"text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml");
		profile.setPreference("browser.helperApps.alwaysAsk.force", false);
		profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
		profile.setPreference("browser.download.manager.focusWhenStarting",
				false);
		profile.setPreference("browser.download.manager.useWindow", false);
		profile.setPreference("browser.download.manager.showAlertOnComplete",
				false);
		profile.setPreference("browser.download.manager.closeWhenDone", false);
		return profile;
	}

	public void closeBrowser() {
		System.out.println("The script now is going to close the browser");
		driver.close();
		driver.quit();
	}

	public void scrolldown(String dd) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0)" + "," + dd + ")+", "");
	}

	public boolean compareTitle(String Expectedval) {

		try {
			Assert.assertEquals(driver.getTitle(), Expectedval);

		} catch (Throwable t) {

			ErrorUtil.addVerificationFailure(t);

			APP_LOGS.debug("Titles do not match");
			return false;
		}
		return true;

	}

	public List<WebElement> getColumnValues(int colIndex) {
		// WebElement colElement;
		List<WebElement> colValues = new ArrayList<WebElement>();

		colValues = driver
				.findElements(By
						.cssSelector("#j_id0:HolidaySummaryList:j_id199:leaveReq:j_id201:0:j_id216("
								+ colIndex + ")"));
		System.out.println(colValues);
		return colValues;

	}

	public boolean compareNumber(int ActualVal, int Expectedval) {
		try {
			Assert.assertEquals(ActualVal, Expectedval);
		} catch (Throwable t) {

			ErrorUtil.addVerificationFailure(t);

			APP_LOGS.debug("Numbers do not match");
			return false;
		}
		return true;
	}

	public boolean compareText(String ActualVal, String Expectedval) {

		try {
			Assert.assertEquals(ActualVal, Expectedval);
		} catch (Throwable t) {

			ErrorUtil.addVerificationFailure(t);

			APP_LOGS.debug("Specified Text do not match");

			return false;
		}
		return true;
	}

	public boolean Checkelementspresent(String xpathKey) {

		int count = driver.findElements(By.xpath(CONFIG.getProperty(xpathKey)))
				.size();
		try {
			Assert.assertTrue(count > 0, "No element present");
		} catch (Throwable t) {
			ErrorUtil.addVerificationFailure(t);
			APP_LOGS.debug("No element present");
			return false;

		}
		return true;

	}

	public boolean Checkelementpresent(String xpathKey) {

		driver.findElements(By.xpath(CONFIG.getProperty(xpathKey)));
		try {
			Assert.assertTrue(false, "No element present");
		} catch (Throwable t) {
			ErrorUtil.addVerificationFailure(t);
			APP_LOGS.debug("No element present");
			return false;

		}
		return true;

	}

	public void CaptureScreenshot(String filename) throws IOException {

		try {
			File scrnsht = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrnsht, new File(System.getProperty("user.dir")
					+ "\\Screenshots\\" + filename + ".png"));
		} catch (Exception e) {
			e.printStackTrace();

			APP_LOGS.debug("Error encountered at"
					+ this.getClass().getSimpleName());
		}
	}

	public boolean WaitforElement(String xpath) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 13);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath(OR.getProperty(xpath))));
		} catch (Throwable t) {

			ErrorUtil.addVerificationFailure(t);
			APP_LOGS.debug("May be due to slow network, could not found the element even after waiting for 10 seconds");
			return false;
		}
		return true;

	}

	public boolean WaitforElementhover(String xpath) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By
					.xpath(OR.getProperty(xpath))));
		} catch (Throwable t) {

			ErrorUtil.addVerificationFailure(t);
			// APP_LOGS.debug("May be due to slow network, could not found the element even after waiting for 10 seconds");
			return false;
		}
		return true;

	}

	public boolean login_To_QA_Org() throws Throwable {
		try {

			driver.get(CONFIG.getProperty("test_QA_Org"));
			// Thread.sleep(6000L);
			WebElement username = driver.findElement(By.id(OR
					.getProperty("login_Username")));
			username.sendKeys("azizm@xcdhr.com");
			Thread.sleep(2000L);
			WebElement password = driver.findElement(By.id(OR
					.getProperty("login_Password")));
			password.sendKeys("arsbamrast*53");

			Thread.sleep(2000L);
			getObject("Submit_Button").click();
			System.out.println("Logged into the QA Org");

			Thread.sleep(2000L);

			/*
			 * Some TLS Continue button is being displayed when we login into QA
			 * org. Hence written the following code to click to 'CONTINUE'
			 * button so as to proceed with intended execution of scripts.
			 * 
			 * Need to remove following code once Salesforce no longer displays
			 * this TLS Message.
			 */
			/*
			 * if(existsElementchkFor1mts(OR.getProperty("afterLoginContinueButon"
			 * ))) { getObject("afterLoginContinueButon").click();
			 * System.out.println
			 * ("The TLS Continue button got clicked successfully");
			 * Thread.sleep(2000L); }
			 */

		} catch (Throwable t) {
			CaptureScreenshot(this.getClass().getSimpleName());
			ErrorUtil.addVerificationFailure(t);
			APP_LOGS.debug("Login unsuccessfull");
			return false;
		}
		return true;

	}

	/*
	 * 
	 * 
	 */

	public boolean login_To_Automation_RegOrg() throws Throwable
	{
		try
		{
			driver.get(CONFIG.getProperty("test_Automation_Reg_Org"));
			WebElement username = driver.findElement(By.id(OR
					.getProperty("login_Username")));
			username.sendKeys("payrollautoregress@xcdhr.com");
			WebElement password = driver.findElement(By.id(OR
					.getProperty("login_Password")));
			password.sendKeys("bristol2018");
			getObject("Submit_Button").click();
			Thread.sleep(1000L);
			System.out.println("Logged into the New Automation Org");
		} 
		catch (Throwable t)
		{
			CaptureScreenshot(this.getClass().getSimpleName());
			ErrorUtil.addVerificationFailure(t);
			APP_LOGS.debug("Login unsuccessfull");
			return false;
		}
		return true;
	}
	
	

	// Notification mail verification
	public boolean login_To_Gmail(String Username, String Password)
			throws Throwable
	{
		try
		{
			WebElement username = driver.findElement(By.xpath(OR
					.getProperty("gmaillogin_Username")));
			username.sendKeys(Username);
			WebElement password = driver.findElement(By.xpath(OR
					.getProperty("gmaillogin_Password")));
			password.sendKeys(Password);

		}
		catch (Throwable t)
		{
			CaptureScreenshot(this.getClass().getSimpleName());
			ErrorUtil.addVerificationFailure(t);
			APP_LOGS.debug("Gmail Login unsuccessfull");
			return false;
		}
		return true;
	}
	

	public WebElement getObject(String xpathkey) {
		try {
			return driver.findElement(By.xpath(OR.getProperty(xpathkey)));
		} catch (Throwable t) {
			APP_LOGS.debug("Cannot find the object " + xpathkey);

			return null;
		}
	}

	// with css seleector

	public WebElement getObjectCSS(String csspathkey) {
		try {
			return driver
					.findElement(By.cssSelector(OR.getProperty(csspathkey)));
		} catch (Throwable t) {
			APP_LOGS.debug("Cannot find the object " + csspathkey);

			return null;
		}
	}

	public void Waitingperiod() throws Throwable {
		Thread.sleep(199000L);
	}

	public void searchTaxSpecificReport() throws Throwable {
		try {
			if (existsElement(OR.getProperty("findReportTextboxLocator"))) {
				getObject("findReportTextboxLocator").sendKeys("");
				Thread.sleep(1000L);
				getObject("findReportTextboxLocator").sendKeys(
						"DO NOT TOUCH - TAX PAYROLL AUTOMATION");
				Thread.sleep(4000L);
				if (existsElement(OR.getProperty("reportNameLocator"))) {
					getObject("reportNameLocator").click();
					System.out.println("");
					System.out
							.println("2> Searched successfully specific Report i.e DO NOT TOUCH - PAYROLL AUTOMATION CHECK");
				}

			} else {
				getObject("findReportTextboxLocator").clear();
				Thread.sleep(1000L);
				getObject("findReportTextboxLocator").sendKeys(
						"DO NOT TOUCH - TAX PAYROLL AUTOMATION");
				Thread.sleep(4000L);
				if (existsElement(OR.getProperty("reportNameLocator"))) {
					getObject("reportNameLocator").click();
					System.out.println("");
					System.out
							.println("2> Searched successfully specific Report i.e DO NOT TOUCH - PAYROLL AUTOMATION CHECK");
				}
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void searchSpecificReport() throws Throwable {
		try {
			if (existsElement(OR.getProperty("findReportTextboxLocator"))) {
				getObject("findReportTextboxLocator").sendKeys("");
				Thread.sleep(1000L);
				getObject("findReportTextboxLocator").sendKeys(
						"DO NOT TOUCH- DIR Cease and recommence");
				Thread.sleep(5000L);
				System.out.println("entered the report name");
				getObject("ReportDircease").sendKeys("");
				getObject("ReportDircease").click();
				System.out.println("");
				System.out
						.println("2> Searched successfully specific Report i.e DO NOT TOUCH - PAYROLL AUTOMATION DIR NI");

			} else {
				driver.navigate().refresh();
				getObject("findReportTextboxLocator").clear();
				Thread.sleep(1000L);
				getObject("findReportTextboxLocator")
						.sendKeys(
								"2> Searched successfully specific Report i.e DO NOT TOUCH - PAYROLL AUTOMATION DIR NI");
				Thread.sleep(4000L);
				if (existsElement(OR.getProperty("ReportDircease"))) {
					getObject("ReportDircease").sendKeys("");
					getObject("ReportDircease").click();
					System.out.println("");
					System.out
							.println("2> Searched successfully specific Report i.e DO NOT TOUCH - PAYROLL AUTOMATION DIR NI");
				}

			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void customzeReport(String PayrollId, String PayFrequency,
			String Monthname) throws Throwable {
		try {
			if (existsElement(OR.getProperty("reportCustomisebtn"))) {
				getObject("reportCustomisebtn").click();
				Thread.sleep(1000L);
				if (existsElement(OR.getProperty("customEditbtn"))) {
					getObject("customEditbtn").click();
					Thread.sleep(3000L);
					getObject("customPayrollRecordid").clear();
					getObject("customPayrollRecordid").sendKeys(PayrollId);
					Thread.sleep(1000L);
					getObject("customOkbtn").click();
					Thread.sleep(3000L);

					getObject("customPayfrequencyEditbtn").click();
					Thread.sleep(2000L);
					getObject("customPayfrqncyTextfield").clear();
					getObject("customPayfrqncyTextfield")
							.sendKeys(PayFrequency);
					Thread.sleep(2000L);
					getObject("2ncustombtn").click();
					Thread.sleep(4000L);

					getObject("customPayrunEditbtn").click();
					Thread.sleep(3000L);
					getObject("customPaytextfield").clear();
					getObject("customPaytextfield").sendKeys(Monthname);
					Thread.sleep(2000L);
					getObject("3dcustomOkbutton").click();
					Thread.sleep(4000L);
				}

				if (existsElement(OR.getProperty("customSave"))) {
					getObject("customSave").click();
					Thread.sleep(3000L);
				}
				if (existsElement(OR.getProperty("customRunReport"))) {
					getObject("customRunReport").click();
					Thread.sleep(3000L);
					System.out.println("");
					System.out
							.println("3> Successfully customized the Report as required");
				}

			}

		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	/***************************************/
	/*
	 * Following is the Report methods which will be used in the Report scripts
	 * These methods help to process the report.
	 */

	/***********************************************************/

	/*
	 * This SearchReport() and FetchReport() methods is for Tax Report
	 */
	public void SearchReport(String TaxReport) throws Throwable {
		try {
			if (existsElementchkFor1mts(OR
					.getProperty("findReportTextboxLocator"))) {
				getObject("findReportTextboxLocator").sendKeys("");
				Thread.sleep(1000L);
				getObject("findReportTextboxLocator").sendKeys(TaxReport);
				Thread.sleep(2000L);
				if (existsElementchkFor1mts(OR
						.getProperty("ReportTablelocator"))) {
					System.out.println("Entered FetchReport==========");
					FetchReport();
				}

			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
			SearchReport(TaxReport);
		}
	}

	public void FetchReport() throws Throwable {

		try {
			System.out.println("Entered FetchReport==========");
			WebElement TableOfReportGrid = driver.findElement(By.xpath(OR
					.getProperty("ReportTablelocator")));
			WebTable RTable = WebTable.getTable(TableOfReportGrid);
			List<WebElement> Table_Report = TableOfReportGrid.findElements(By
					.xpath(OR.getProperty("ReportTableRows")));
			java.util.Iterator<WebElement> Rx = Table_Report.iterator();
			int Reportrownum = 0;
			while (Rx.hasNext()) {
				NameOfReprt = RTable.getTBody().getRow(Reportrownum).getCell(2)
						.getText();
				System.out.println("Report name is :" + NameOfReprt);
				System.out.println("Report name is matched");
				String modifiedReport = getReportNamee(NameOfReprt);
				if (modifiedReport != null) {
					ModifiedReport rp = Enum.valueOf(ModifiedReport.class,
							modifiedReport);
					EnumTestClass enumTestCls = new EnumTestClass();
					enumTestCls.runTestReport(rp);
				}
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
		System.out.println("Exit FetchReport==========");
	}

	/*
	 * public void taxreportlink()throws Throwable {
	 * if(existsElement(OR.getProperty("reportNameLocator"))) {
	 * getObject("reportNameLocator").sendKeys("");
	 * getObject("reportNameLocator").click();
	 * System.out.println("The report  :"+TaxReport+" got clicked");
	 * System.out.println(""); System.out.println("");
	 * System.out.println("2> Searched successfully specific Report i.e "+
	 * TaxReport);
	 * 
	 * }
	 * 
	 * }
	 * 
	 * public void nireportlink()throws Throwable {
	 * if(existsElement(OR.getProperty("reportNameLocatorNI"))) {
	 * getObject("reportNameLocatorNI").sendKeys("");
	 * getObject("reportNameLocatorNI").click();
	 * System.out.println("The report  :"+NIReport+" got clicked");
	 * System.out.println("");
	 * System.out.println("2> Searched successfully specific Report i.e  "+
	 * NIReport); } }
	 * 
	 * public void DirAsEmployee_ReportLink()throws Throwable {
	 * if(existsElement(OR.getProperty("DirAsEmpReportName"))) {
	 * getObject("DirAsEmpReportName").sendKeys("");
	 * getObject("DirAsEmpReportName").click();
	 * System.out.println("The report  :"+DirAsEmployee+" got clicked");
	 * System.out.println("");
	 * System.out.println("2> Searched successfully specific Report i.e  "+
	 * DirAsEmployee);
	 * 
	 * } }
	 * 
	 * 
	 * public void DirAsProRata_ReportLink()throws Throwable {
	 * if(existsElement(OR.getProperty("ProrataReportnameLocator"))) {
	 * getObject("ProrataReportnameLocator").sendKeys("");
	 * getObject("ProrataReportnameLocator").click();
	 * System.out.println("The report  :"+DirAsProRata+" got clicked");
	 * System.out.println("");
	 * System.out.println("2> Searched successfully specific Report i.e  "+
	 * DirAsProRata);
	 * 
	 * } }
	 * 
	 * public void CeaseAndRecommence_ReportLink()throws Throwable {
	 * if(existsElement(OR.getProperty("DirAsCeaseAndRecommenceReport"))) {
	 * getObject("DirAsCeaseAndRecommenceReport").sendKeys("");
	 * getObject("DirAsCeaseAndRecommenceReport").click();
	 * System.out.println("The report  :"+CeaseAndRecommence+" got clicked");
	 * System.out.println("");
	 * System.out.println("2> Searched successfully specific Report i.e  "+
	 * CeaseAndRecommence);
	 * 
	 * } }
	 * 
	 * public void ReachesPensionAge_ReportLink()throws Throwable {
	 * if(existsElement(OR.getProperty("reportReachesPensionAgelnk"))) {
	 * getObject("reportReachesPensionAgelnk").sendKeys("");
	 * getObject("reportReachesPensionAgelnk").click();
	 * System.out.println("The report  :"+ReachesPensionAge+" got clicked");
	 * System.out.println("");
	 * System.out.println("2> Searched successfully specific Report i.e  "+
	 * ReachesPensionAge);
	 * 
	 * } }
	 * 
	 * public void Deferment_ReportLink()throws Throwable {
	 * if(existsElement(OR.getProperty("reportDefermentlnk"))) {
	 * getObject("reportDefermentlnk").sendKeys("");
	 * getObject("reportDefermentlnk").click();
	 * System.out.println("The report  :"+Deferment+" got clicked");
	 * System.out.println("");
	 * System.out.println("2> Searched successfully specific Report i.e  "+
	 * Deferment);
	 * 
	 * } }
	 * 
	 * public void AtoD_ReportLink()throws Throwable {
	 * if(existsElement(OR.getProperty("DirAsReportAtoD"))) {
	 * getObject("DirAsReportAtoD").sendKeys("");
	 * getObject("DirAsReportAtoD").click();
	 * System.out.println("The report  :"+AtoD+" got clicked");
	 * System.out.println("");
	 * System.out.println("2> Searched successfully specific Report i.e  "+
	 * AtoD);
	 * 
	 * } }
	 * 
	 * public void Under21_ReportLink()throws Throwable {
	 * if(existsElement(OR.getProperty("reportDir_Under21"))) {
	 * getObject("reportDir_Under21").sendKeys("");
	 * getObject("reportDir_Under21").click();
	 * System.out.println("The report  :"+Under21+" got clicked");
	 * System.out.println("");
	 * System.out.println("2> Searched successfully specific Report i.e "+
	 * Under21); } }
	 * 
	 * 
	 * public void Smp1st_ReportLink()throws Throwable {
	 * if(existsElement(OR.getProperty("smpfirstReport"))) {
	 * getObject("smpfirstReport").sendKeys("");
	 * getObject("smpfirstReport").click();
	 * System.out.println("The report  :"+SMP1stReport+" got clicked");
	 * System.out.println("");
	 * System.out.println("2> Searched successfully specific Report i.e "+
	 * SMP1stReport); } }
	 * 
	 * public void Smp2nd_ReportLink()throws Throwable {
	 * if(existsElement(OR.getProperty("smp2ndReport"))) {
	 * getObject("smp2ndReport").sendKeys("");
	 * getObject("smp2ndReport").click();
	 * System.out.println("The report  :"+SMP2ndReport+" got clicked");
	 * System.out.println("");
	 * System.out.println("2> Searched successfully specific Report i.e "+
	 * SMP2ndReport); } }
	 * 
	 * public void Smp3d_ReportLink()throws Throwable {
	 * if(existsElement(OR.getProperty("smp3dReport"))) {
	 * getObject("smp3dReport").sendKeys(""); getObject("smp3dReport").click();
	 * System.out.println("The report  :"+SMP3dReport+" got clicked");
	 * System.out.println("");
	 * System.out.println("2> Searched successfully specific Report i.e "+
	 * SMP3dReport); } }
	 * 
	 * public void Sap1st_ReportLink() throws Throwable {
	 * if(existsElement(OR.getProperty("sap1stReportlocator"))) {
	 * getObject("sap1stReportlocator").sendKeys("");
	 * getObject("sap1stReportlocator").click();
	 * System.out.println("The report  :"+SAP1stReport+" got clicked");
	 * System.out.println("");
	 * System.out.println("2> Searched successfully specific Report i.e "+
	 * SAP1stReport); } }
	 * 
	 * 
	 * public void Sap2nd_ReportLink() throws Throwable {
	 * if(existsElement(OR.getProperty("sap2ndReportlocator"))) {
	 * getObject("sap2ndReportlocator").sendKeys("");
	 * getObject("sap2ndReportlocator").click();
	 * System.out.println("The report  :"+SAP2ndReport+" got clicked");
	 * System.out.println("");
	 * System.out.println("2> Searched successfully specific Report i.e "+
	 * SAP2ndReport); } }
	 * 
	 * 
	 * public void Sap3d_ReportLink() throws Throwable {
	 * if(existsElement(OR.getProperty("sap3dReportlocator"))) {
	 * getObject("sap3dReportlocator").sendKeys("");
	 * getObject("sap3dReportlocator").click();
	 * System.out.println("The report  :"+SAP3dReport+" got clicked");
	 * System.out.println("");
	 * System.out.println("2> Searched successfully specific Report i.e "+
	 * SAP3dReport); } }
	 * 
	 * public void SSP1st_ReportLink() throws Throwable {
	 * if(existsElement(OR.getProperty("ssp1stReportlocator"))) {
	 * getObject("ssp1stReportlocator").sendKeys("");
	 * getObject("ssp1stReportlocator").click();
	 * System.out.println("The report  :"+SSP1stReport+" got clicked");
	 * System.out.println("");
	 * System.out.println("2> Searched successfully specific Report i.e "+
	 * SSP1stReport); } }
	 * 
	 * public void SSP2nd_ReportLink() throws Throwable {
	 * if(existsElement(OR.getProperty("ssp2ndReportlocator"))) {
	 * getObject("ssp2ndReportlocator").sendKeys("");
	 * getObject("ssp2ndReportlocator").click();
	 * System.out.println("The report  :"+SSP2ndReport+" got clicked");
	 * System.out.println("");
	 * System.out.println("2> Searched successfully specific Report i.e "+
	 * SSP2ndReport); } }
	 * 
	 * public void SSP3rd_ReportLink() throws Throwable {
	 * if(existsElement(OR.getProperty("ssp3dReportLocator"))) {
	 * getObject("ssp3dReportLocator").sendKeys("");
	 * getObject("ssp3dReportLocator").click();
	 * System.out.println("The report  :"+SSP3dReport+" got clicked");
	 * System.out.println("");
	 * System.out.println("2> Searched successfully specific Report i.e "+
	 * SSP3dReport); } }
	 */
	/**********************************************************************/
	// This method is used in the AutoEnrolment New starter package / Test
	// reports.

	public void UpdateReportPageAutoEnrolment(String PayrollId, String Monthname)
			throws Throwable {

		Thread.sleep(2000L);
		if (existsElement(OR.getProperty("customEditbtn"))) {
			System.out.println("Custom Edit button exists");
			getObject("customEditbtn").click();
			Thread.sleep(1000L);
		}

		if (existsElement(OR.getProperty("customPayrollRecordid"))) {
			getObject("customPayrollRecordid").clear();
			getObject("customPayrollRecordid").sendKeys(PayrollId);
			Thread.sleep(1000L);
			getObject("customOkbtn").click();
			System.out.println("Payroll record id updated");
			Thread.sleep(6000L);
		}

		getObject("customPayfrequencyEditbtn").click();
		Thread.sleep(1000L);
		getObject("customPayfrqncyTextfield").clear();
		getObject("customPayfrqncyTextfield").sendKeys(Monthname);
		Thread.sleep(1000L);
		getObject("2ncustombtn").click();

		Thread.sleep(6000L);
		/*
		 * getObject("customPayrunEditbtn").click(); Thread.sleep(1000L);
		 * getObject("customPaytextfield").clear();
		 * getObject("customPaytextfield").sendKeys(Monthname);
		 * Thread.sleep(1000L); getObject("3dcustomOkbutton").click();
		 * System.out.println("Payrun is updated successfully");
		 * Thread.sleep(6000L);
		 */

	}

	/***********************************************************************/

	public void editCustomButton() throws Throwable {
		try {
			getObject("reportCustomisebtn").click();
			System.out.println("custom button got clicked");
			Thread.sleep(3000L);
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void UpdateReportPage(String PayrollId, String PayFrequency,
			String Monthname) throws Throwable {

		Thread.sleep(2000L);
		if (existsElementchkFor1mts(OR.getProperty("customEditbtn"))) {
			System.out.println("Custom Edit button exists");
			getObject("customEditbtn").sendKeys("");
			getObject("customEditbtn").click();
			Thread.sleep(1000L);
		}

		if (existsElementchkFor1mts(OR.getProperty("customPayrollRecordid"))) {
			getObject("customPayrollRecordid").sendKeys("");
			getObject("customPayrollRecordid").clear();
			/*
			 * temporarily i am not passing parameter for payroll id since the
			 * report is not taking the 'Monthly_Payroll' as payroll id.
			 */
			getObject("customPayrollRecordid").sendKeys(PayrollId);
			Thread.sleep(4000L);
			getObject("customOkbtn").click();
			System.out.println("Payrun updated");
			Thread.sleep(6000L);
		}
		getObject("customPayfrequencyEditbtn").sendKeys("");
		getObject("customPayfrequencyEditbtn").click();
		Thread.sleep(1000L);
		getObject("customPayfrqncyTextfield").clear();
		getObject("customPayfrqncyTextfield").sendKeys(PayFrequency);
		Thread.sleep(1000L);
		getObject("2ncustombtn").click();
		Thread.sleep(6000L);

		getObject("customPayrunEditbtn").click();
		Thread.sleep(4000L);
		getObject("customPaytextfield").clear();
		getObject("customPaytextfield").sendKeys(Monthname);
		Thread.sleep(4000L);
		getObject("3dcustomOkbutton").click();
		Thread.sleep(6000L);

	}

	/**********************************************/
	// This method is used for AutoEnrolment New starter method
	public void RunReportAutoEnrol() throws Throwable {
		try {
			if (existsElement(OR.getProperty("customRunAutoEnrol"))) {
				getObject("customRunAutoEnrol").click();
				Thread.sleep(6000L);
				System.out.println("");
				System.out
						.println("3> Successfully customized the Report as required");
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	/*********************************************/

	public void RunReport() throws Throwable {
		try {
			if (existsElement(OR.getProperty("customRunReport"))) {
				getObject("customRunReport").click();
				Thread.sleep(6000L);
				System.out.println("");
				System.out
						.println("3> Successfully customized the Report as required");
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	/******************************************************/
	/*
	 * Following is the Method used in the payroll script which selects the
	 * employees and processes the 'Generate draft payroll' functionality
	 */
	/******************************************************/
	public int rowMatchedDD = 0;

	public void ExcludeIncludeEmp(String EmpName, String Exclinputsheet,
			String worksheetNo) throws Throwable {
		try {
			System.out.println("entering into ExcludeIncludeEmp method");
			double worksheetvalue = Double.parseDouble(worksheetNo);
			DecimalFormat df = new DecimalFormat("###.#");
			String worksheetNoWithoutDecimal = df.format(worksheetvalue);
			int wNo = Integer.parseInt(worksheetNoWithoutDecimal);
			System.out.println("The converted post value is  :" + wNo);

			FileInputStream fis = new FileInputStream(
					new File(
							System.getProperty("user.dir")
									+ "\\src\\main\\java\\com\\test\\xcdhr\\Salesforce_Core_Framework1\\salesforce_XLS_Files\\"
									+ Exclinputsheet));

			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet spreadsheet = workbook.getSheetAt(wNo);
			totalRows = spreadsheet.getLastRowNum();
			System.out
					.println("Total rows in the processpayrollforMonthlytax worksheet is :"
							+ totalRows);
			String oldWindow = driver.getWindowHandle();

			if (existsElementchkFor5mts(OR.getProperty("changeToDraft"))) {
				System.out.println("yest the Change to Draft button exist");
				retryForGenerateDraft();
			}
			driver.switchTo().window(driver.getWindowHandle());
			if (windowExclude) {
				windowExclude = false;
				if (getObject("excludeAllemployees").isDisplayed()) {
					System.out
							.println("the exclude include check box is displayed");
					getObject("excludeAllemployees").click();
					System.out
							.println("the exclude include check box got checked");
					Thread.sleep(1000L);
					if (getObject("excludeAllemployees").isSelected()) {
						getObject("excludeAllemployees").click();
						System.out
								.println("After checking the chckbox onceagain the exclude include check box is made UNchecked");
						// Thread.sleep(1000L);
					}
				}
			}
			if (existsElementchkFor1mts(OR
					.getProperty("excludeIncludeAllEmployees"))) {
				WebElement excludeincludeTable = driver.findElement(By.xpath(OR
						.getProperty("excludeIncludeAllEmployees")));
				List<WebElement> rows = excludeincludeTable
						.findElements(By.xpath(OR
								.getProperty("excludeIncludeAllEmployeesrows")));
				java.util.Iterator<WebElement> x = rows.iterator();
				rownumx = 1;
				while (x.hasNext()) {
					System.out
							.println("111@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
					WebElement appEmployes = driver
							.findElement(By
									.xpath("//div[@id='turtle-info']/div/div/div[2]/table[2]/tbody/tr["
											+ rownumx + "]/td[2]/a"));
					/*
					 * WebElement appEmployes = driver .findElement(By .xpath(
					 * "//div[@id='turtle-info']/div/div/div[2]/table[2]/tbody/tr[1]/td[2]/a"
					 * ));
					 */

					System.out
							.println("222@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
					String appEmployeesName = appEmployes.getText();
					System.out.println("empname is  :" + appEmployeesName);
					if (appEmployeesName != null
							&& appEmployeesName.equalsIgnoreCase(EmpName)) {
						rowMatchedDD++;
						WebElement empchkBox = driver
								.findElement(By
										.xpath("//div[@id='turtle-info']/div/div/div[2]/table[2]/tbody/tr["
												+ rownumx + "]/td/input"));
						/*
						 * WebElement empchkBox = driver .findElement(By .xpath(
						 * "//div[@id='turtle-info']/div/div/div[2]/table[2]//thead/tr/th["
						 * + rownumx + "]/input"));
						 */
						System.out.println("empchkBox=====" + empchkBox);
						
						if (existsWebElement(empchkBox)) {
							empchkBox.click();
							System.out.println("");
							System.out.println("The Employee name  : "
									+ appEmployeesName
									+ "  check box got clicked");
							System.out.println("The rowMatchedDD------>:" +rowMatchedDD);
						}
						if (totalRows == rowMatchedDD) {
							System.out
									.println("The employees rows now matched,hence will now exit the window by saving the required employees");
							break;
						}
					}
					rownumx++;
				}
				
				
				if (existsElementchkFor1mts(OR.getProperty("closeWindow"))) {
					getObject("closeWindow").click();
					System.out
							.println("The save button of the popup window got clicked");
					Thread.sleep(1000L);
				}
				driver.switchTo().window(oldWindow);
				Thread.sleep(1000L);
				if (existsElementchkFor1mts(OR
						.getProperty("genratedraftPayroll"))) {
					getObject("genratedraftPayroll").sendKeys("");
					getObject("genratedraftPayroll").click();
					if (existsElementchkFor1mts(OR.getProperty("progressBar"))) {
						System.out.println("");
						System.out
								.println("The generate draft button got clicked, please wait till draft payroll process gets executed");
						Thread.sleep(4000L);
						payRunExecution();
						Thread.sleep(6000L);
						if (existsElementchkFor1mts(OR
								.getProperty("emprecordsTableRowsAftergeneratedraft"))) {
							verifyEmpRecordInPaySummaryTable();
						}
					}
				}
			}

		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	// /////7

	/*
	 * For autoenrolment payroll scripts
	 */

	/*
	 * int rowMatched=0; public void ExcludeIncludeEmp(String EmpName,String
	 * Exclinputsheet) throws Throwable {
	 * 
	 * try { FileInputStream fis = new FileInputStream( new File(
	 * "F:\\Automation XCD\\eclipse\\WebDriver\\Salesforce_Core_Framework\\src\\salesforce_XLS_Files\\"
	 * +Exclinputsheet)); XSSFWorkbook workbook = new XSSFWorkbook(fis);
	 * XSSFSheet spreadsheet = workbook.getSheetAt(2); totalRows =
	 * spreadsheet.getLastRowNum(); System.out.println(
	 * "Total rows in the processpayrollforMonthlytax worksheet is :"
	 * +totalRows); String oldWindow = driver.getWindowHandle(); if (exlude) {
	 * exlude = false; Thread.sleep(3000L);
	 * 
	 * if(existsElement(OR.getProperty("excludeIncludeEmployees"))) {
	 * getObject("excludeIncludeEmployees").click(); Thread.sleep(5000); } else
	 * { System.out.println("Exclude include button link not exist");
	 * 
	 * if (existsElement(OR.getProperty("payrollEditButton"))) {
	 * getObject("payrollEditButton").click();
	 * System.out.println("The generate draft button got clicked"); }
	 * 
	 * if (existsElement(OR.getProperty("Payrollstatus"))) { Select
	 * selectByValue = new
	 * Select(driver.findElement(By.xpath(OR.getProperty("Payrollstatus"))));
	 * selectByValue.selectByVisibleText("Draft"); Thread.sleep(2000L); }
	 * 
	 * if (existsElement(OR.getProperty("payrunEditSave"))) {
	 * getObject("payrunEditSave").sendKeys("");
	 * getObject("payrunEditSave").click(); }
	 * 
	 * if(existsElement(OR.getProperty("excludeIncludeEmployees"))) {
	 * getObject("excludeIncludeEmployees").click(); Thread.sleep(5000); }
	 * 
	 * }
	 * 
	 * } driver.switchTo().window(driver.getWindowHandle()); if
	 * (getObject("excludeAllemployees").isSelected()) {
	 * getObject("excludeAllemployees").click();
	 * System.out.println("the exclude include check box got unchecked"); }
	 * if(existsElement(OR.getProperty("excludeIncludeAllEmployees"))) {
	 * WebElement excludeincludeTable = driver.findElement(By
	 * .xpath(OR.getProperty("excludeIncludeAllEmployees"))); List<WebElement>
	 * rows = excludeincludeTable.findElements(By
	 * .xpath(OR.getProperty("excludeIncludeAllEmployeesrows")));
	 * java.util.Iterator<WebElement> x = rows.iterator();
	 * 
	 * rownumx = 1; while (x.hasNext()) { WebElement appEmployes =
	 * driver.findElement(By .xpath("//span/div[2]/div/table/tbody/tr[" +
	 * rownumx+ "]/td[2]/a")); String appEmployeesName = appEmployes.getText();
	 * System.out.println("empname is  :"+appEmployeesName); if
	 * (appEmployeesName != null && appEmployeesName.equalsIgnoreCase(EmpName))
	 * { rowMatched++; WebElement empchkBox = driver.findElement(By
	 * .xpath("//span/div[2]/div/table/tbody/tr[" + rownumx+ "]/td/input"));
	 * if(existsWebElement(empchkBox)) { empchkBox.click();
	 * System.out.println("The Employee name  : "
	 * +appEmployeesName+"  check box got clicked"); } if (totalRows ==
	 * rowMatched) { break; } } else {
	 * System.out.println("employee name did not matched"); } rownumx++; }
	 * 
	 * getObject("closeWindow").click();
	 * System.out.println("close window got clicked");
	 * driver.switchTo().window(oldWindow); Thread.sleep(4000L);
	 * 
	 * if (existsElement(OR.getProperty("genratedraftPayroll"))) {
	 * getObject("genratedraftPayroll").click(); System.out.println("");
	 * System.out.println("The generate draft button got clicked");
	 * Thread.sleep(4000L); payRunExecution(); }
	 * 
	 * 
	 * } Thread.sleep(60000L);
	 * if(existsElement(OR.getProperty("emprecordsTableAftergeneratedraft"))) {
	 * verifyEmpRecordInPaySummaryTable(); }
	 * 
	 * } catch(Throwable t) { System.out.println(t.getMessage().toString());
	 * System.out.println(t.getStackTrace().toString()); } }
	 */

	int rowMatched = 0;

	public void ExcludeIncludeEmp(String EmpName, String Exclinputsheet)
			throws Throwable {

		try {
			FileInputStream fis = new FileInputStream(
					new File(
							System.getProperty("user.dir")
									+ "\\src\\main\\java\\com\\test\\xcdhr\\Salesforce_Core_Framework1\\salesforce_XLS_Files\\"
									+ Exclinputsheet));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet spreadsheet = workbook.getSheetAt(2);
			totalRows = spreadsheet.getLastRowNum();
			System.out
					.println("Total rows in the processpayrollforMonthlytax worksheet is :"
							+ totalRows);
			String oldWindow = driver.getWindowHandle();
			if (exlude) {
				exlude = false;
				Thread.sleep(3000L);

				if (existsElement(OR.getProperty("excludeIncludeEmployees"))) {
					getObject("excludeIncludeEmployees").click();
					System.out
							.println("The exclude Include button got clicked");
					Thread.sleep(5000);
				} else {
					System.out
							.println("Exclude include button link not exist as the Record type status is still new");
					System.out
							.println("Hence the script would make the Record type status to Draft");

					if (existsElement(OR.getProperty("payrollEditButton"))) {
						getObject("payrollEditButton").click();
						System.out
								.println("The generate draft button got clicked");
					}

					if (existsElement(OR.getProperty("Payrollstatus"))) {
						Select selectByValue = new Select(driver.findElement(By
								.xpath(OR.getProperty("Payrollstatus"))));
						selectByValue.selectByVisibleText("Draft");
						System.out
								.println("The status is now set to Draft and saved. The application would now display ExcludeInclude link");

						Thread.sleep(2000L);
					}

					if (existsElement(OR.getProperty("payrunEditSave"))) {
						getObject("payrunEditSave").sendKeys("");
						getObject("payrunEditSave").click();
						System.out
								.println("The payrun save button got clicked");
					}

					if (existsElement(OR.getProperty("excludeIncludeEmployees"))) {
						getObject("excludeIncludeEmployees").click();
						System.out
								.println("As the ExcludeIncldueEmployees link"
										+ " got dispalyed now , the script clicked to "
										+ "the same so as to select Employees for "
										+ "processing payroll");
						Thread.sleep(5000);
					}

				}

			}
			driver.switchTo().window(driver.getWindowHandle());
			if (getObject("excludeAllemployees").isSelected()) {
				getObject("excludeAllemployees").click();
				System.out
						.println("the exclude include check box got unchecked");
			}
			if (existsElement(OR.getProperty("excludeIncludeAllEmployees"))) {
				WebElement excludeincludeTable = driver.findElement(By.xpath(OR
						.getProperty("excludeIncludeAllEmployees")));
				List<WebElement> rows = excludeincludeTable
						.findElements(By.xpath(OR
								.getProperty("excludeIncludeAllEmployeesrows")));
				java.util.Iterator<WebElement> x = rows.iterator();

				rownumx = 1;
				while (x.hasNext()) {
					WebElement appEmployes = driver.findElement(By
							.xpath("//span/div[2]/div/table/tbody/tr["
									+ rownumx + "]/td[2]/a"));

					String appEmployeesName = appEmployes.getText();

					// System.out.println("empname is  :"+appEmployeesName);
					if (appEmployeesName != null
							&& appEmployeesName.equalsIgnoreCase(EmpName)) {

						rowMatched++;
						WebElement empchkBox = driver.findElement(By
								.xpath("//span/div[2]/div/table/tbody/tr["
										+ rownumx + "]/td/input"));
						if (existsWebElement(empchkBox)) {
							empchkBox.click();
							System.out.println("");
							System.out.println("The Employee name  : "
									+ appEmployeesName
									+ "  check box got clicked");
						}
						// Thread.sleep(2000L);
						if (totalRows == rowMatched) {
							break;
						}
					}

					rownumx++;
				}

				getObject("closeWindow").click();
				System.out.println("close window got clicked");
				driver.switchTo().window(oldWindow);
				Thread.sleep(4000L);
				if (existsElement(OR.getProperty("genratedraftPayroll"))) {
					getObject("genratedraftPayroll").sendKeys("");
					getObject("genratedraftPayroll").click();
					if (existsElement(OR
							.getProperty("payRunProcessTo100Percent"))) {

						System.out.println("");
						System.out
								.println("The generate draft button got clicked");
						Thread.sleep(4000L);
						payRunExecution();

					} else {
						getObject("genratedraftPayroll").sendKeys("");
						getObject("genratedraftPayroll").click();
						if (existsElement(OR
								.getProperty("payRunProcessTo100Percent"))) {

							System.out.println("");
							System.out
									.println("The generate draft button got clicked");
							Thread.sleep(4000L);
							payRunExecution();
							Thread.sleep(6000L);
							if (existsElement(OR
									.getProperty("emprecordsTableAftergeneratedraft"))) {
								verifyEmpRecordInPaySummaryTable();
							}

						}
					}

				}

			}
			/*
			 * Thread.sleep(6000L); if (existsElement(OR
			 * .getProperty("emprecordsTableAftergeneratedraft"))) {
			 * verifyEmpRecordInPaySummaryTable(); }
			 */

		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public int dTRows;
	public int draftTotalRows;

	public void verifyEmpRecordInPaySummaryTable() throws Throwable {
		try {
			System.out
					.println("Now the new method 'verifyEmpRecordInPaySummaryTable()' "
							+ "would execute to find out the employee record in PaySummary Table after waiting period of 10 seconds");
			Thread.sleep(8000L);
			if (existsElement(OR
					.getProperty("emprecordsTableAftergeneratedraft"))) {
				System.out
						.println("The script recognised the tax generated employee table locator");
				Thread.sleep(9000L);
				System.out.println("Waited for 9 seconds");
				WebElement empTableAfterDraftgenerate = getObject("emprecordsTableAftergeneratedraft");
				List<WebElement> draftRows = empTableAfterDraftgenerate
						.findElements(By.xpath(OR
								.getProperty("emprecordsTableRowsAftergeneratedraft")));
				Thread.sleep(3000L);
				draftTotalRows = draftRows.size();
				System.out.println("Total rows are " + draftTotalRows);
				if (totalRows == (draftTotalRows - 1)) {
					Thread.sleep(1000L);
					System.out
							.println("After generating draft payroll the app is displaying employee records same"
									+ " as excel file employees of this Tax worksheet");
				} else {
					System.out
							.println(" the app is not displaying employee records same"
									+ " as excel file employees of this Tax worksheet");
					finalRows = totalRows;
					dTRows = draftTotalRows;
					System.out.println("The Final rows in the table are :"
							+ finalRows);

					System.out.println("The rows in the table are :" + dTRows);

				}
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	/*************** payroll NI Weekly script methods ******************/

	public void PayrollForNIWeekly() throws Throwable {

		if (existsElement(OR.getProperty("payrollTab"))) {
			getObject("payrollTab").click();

		}
		Thread.sleep(5000L);

		if (existsElement(OR.getProperty("payrollViewLocator"))) {
			Select selectByValue = new Select(driver.findElement(By.xpath(OR
					.getProperty("payrollViewLocator"))));
			// This select by value needs to be called from OR.Properties
			selectByValue.selectByValue("All");
		}
		Thread.sleep(8000L);
		/*
		 * This code clicks to pagination from 1 to last page till it finds the
		 * '2Weekly' pay run Once it finds the '2Weekly' payrun, it clicks to
		 * it. Hence the following code finds the '2Weekly' payrun automatically
		 * from pagination ProcessingTo2Weekly() method searches the required
		 * company name and payrun
		 */
		try {
			WebElement table = driver.findElement(By.xpath(OR
					.getProperty("payroll2weeklytable")));
			List<WebElement> allpages = driver.findElements(By.xpath(OR
					.getProperty("paginationElement")));
			System.out.println("Total pages :" + allpages.size());
			for (int i = 0; i <= (allpages.size()); i++) {

				if (existsElement(OR.getProperty("paginationElement"))) {
					allpages.get(i).click();
				}
				List<WebElement> allrows = table
						.findElements(By
								.xpath("//*[@id='j_id0:j_id3']/div[2]/div/table[2]/tbody/tr[1]"));
				System.out.println("Total rows :" + allrows.size());
				for (int row = 1; row <= allrows.size(); row++) {

					ProcessingToWeekly();
				}
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void ProcessingToWeekly() throws Throwable {
		try {
			//
			WebElement tableheader = getObject("payroll2weeklytable");
			List<WebElement> th = tableheader.findElements(By.tagName("th"));

			for (int i = 0; i < th.size(); i++) {
				if ("Payroll".equalsIgnoreCase(th.get(i).getText())) {
					payrollcol_position = i + 1;
					break;
				}
			}

			for (int j = 0; j < th.size(); j++) {
				if ("Employer".equalsIgnoreCase(th.get(j).getText())) {
					Emplpoyercol_position = j + 1;
					break;
				}

			}

			for (int k = 0; k < th.size(); k++) {
				if ("Frequency".equalsIgnoreCase(th.get(k).getText())) {
					frequencyCol_Postition = k + 1;
					break;

				}

			}

			//

			WebElement niweeklyPayrollTable = getObject("payroll2weeklytable");
			// need to check webelement exist
			// WebTable table = WebTable.getTable(niweeklyPayrollTable);
			List<WebElement> rows = niweeklyPayrollTable.findElements(By
					.xpath(OR.getProperty("payroll2weeklytablerows")));
			java.util.Iterator<WebElement> x = rows.iterator();
			rownum = 1;
			System.out.println("rownum is  :" + rownum);
			while (x.hasNext()) {
				WebElement emr1 = driver.findElement(By.xpath("//table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/"
						+ "td[" + Emplpoyercol_position + "]"));
				String empr = emr1.getText();

				WebElement ffr = driver.findElement(By.xpath("//table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/"
						+ "td				[" + frequencyCol_Postition + "]"));
				String ffr1 = ffr.getText();

				WebElement ppr1 = driver.findElement(By.xpath("//table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/"
						+ "td[" + payrollcol_position + "]"));
				String ppr = ppr1.getText();

				payrollRecordId = "//table[2]/" + "tbody/" + "tr" + "["
						+ (rownum + 1) + "]" + "/" + "td				["
						+ payrollcol_position + "]" + "/" + "a";

				if (empr != null
						&& empr.equalsIgnoreCase("DO NOT TOUCH PAYROLL AUTOMATION EMPLOYER")
						&& ppr.equalsIgnoreCase(pnWeek)
						&& ffr1.equalsIgnoreCase("Weekly")) {
					System.out
							.println("Finally the Employer name,Payfrequency 'Monthly' and required Payroll 					matched");

					driver.findElement(By.xpath(payrollRecordId)).click();
					if (existsElement(OR.getProperty("twoweeklyPayrolldetails"))) {
						String pfrequencey = getObject(
								"twoweeklyPayrolldetails").getText();
						System.out.println("the payfrequency is :"
								+ pfrequencey);
						TaxPayRun_For_Week1();
						break;
					}
					break;
				} else {
					System.out.println("payfrequency not matched");
				}
				rownum++;
			}

		} catch (Throwable t) {
			t.getMessage().toString();
			t.getStackTrace().toString();
		}

	}

	/*
	 * public void ProcessingToWeekly() throws Throwable { try { WebElement
	 * niweeklyPayrollTable = getObject("payroll2weeklytable"); // need to check
	 * webelement exist WebTable table =
	 * WebTable.getTable(niweeklyPayrollTable); List<WebElement> rows =
	 * niweeklyPayrollTable .findElements(By
	 * .xpath(OR.getProperty("payroll2weeklytablerows")));
	 * java.util.Iterator<WebElement> x = rows.iterator(); rownum = 1;
	 * System.out.println("rownum is  :" + rownum); while (x.hasNext()) { String
	 * companyname = table.getTBody().getRow(rownum).getCell(0).getText();
	 * String payrollId = table.getTBody().getRow(rownum).getCell(3).getText();
	 * 
	 * String payFrequency =
	 * table.getTBody().getRow(rownum).getCell(4).getText(); payrollRecordId =
	 * "//table[2]/" + "tbody/" + "tr" + "[" + (rownum + 1)+ "]" + "/" +
	 * "td[4]/" + "a";
	 * 
	 * if (payFrequency != null && companyname
	 * .equalsIgnoreCase("DO NOT TOUCH PAYROLL AUTOMATION EMPLOYER") &&
	 * payFrequency.equalsIgnoreCase("Weekly")&&
	 * payrollId.equalsIgnoreCase(pnWeek))//Two Weekly {
	 * System.out.println("payfrequency 'Weekly' matched");
	 * driver.findElement(By.xpath(payrollRecordId)).click(); if
	 * (existsElement(OR.getProperty("twoweeklyPayrolldetails"))) { String
	 * pfrequencey = getObject("twoweeklyPayrolldetails").getText();
	 * System.out.println("the payfrequency is :" + pfrequencey);
	 * TaxPayRun_For_Week1(); break; } break; } else {
	 * System.out.println("payfrequency not matched"); } rownum++; }
	 * 
	 * } catch(Throwable t) { t.getMessage().toString();
	 * t.getStackTrace().toString(); }
	 * 
	 * }
	 */

	public void TaxPayRun_For_Week1() throws Throwable {
		try {
			if (existsElement(OR.getProperty("payrollDetailsTableheader1"))) {
				WebElement tableheader = getObject("payrollDetailsTableheader1");
				// driver.findElement(By.xpath(OR.getProperty("payrollDetailsTableheader")));
				List<WebElement> th = tableheader
						.findElements(By.tagName("th"));

				for (int i = 1; i < th.size(); i++) {
					payrunMonth = null;
					payrunMonth = driver
							.findElement(
									By.xpath("//*[contains(@id, '_body')]/table/tbody/tr[1]/th["
											+ i + "]")).getText();
					System.out.println("THE Column name is :" + payrunMonth);
					if (payrunMonth.equalsIgnoreCase("Pay run")) {
						System.out.println("THE Column payrun matched :"
								+ payrunMonth);
						break;
					}

					if (existsElement(OR.getProperty("payrunMonthlyTable"))) {
						WebElement payRunWeekOneTable = getObject("payrunMonthlyTable");
						if (existsWebElement(payRunWeekOneTable)) {

							List<WebElement> rows = payRunWeekOneTable
									.findElements(By.xpath(OR
											.getProperty("payrunMonthlyTableRows")));
							java.util.Iterator<WebElement> x = rows.iterator();

							rownum = 2;

							while (x.hasNext()) {
								System.out.println("ruwnum is :" + rownum);

								Thread.sleep(2000L);
								WebElement Week = driver
										.findElement(By
												.xpath("//div[" + "5" + "]/"
														+ "div[" + "1]/"
														+ "div/" + "div["
														+ "2]/" + "table/" +

														"tbody/tr[" + rownum
														+ "]/th/a"));

								String WeekName = Week.getText();
								//
								if (WeekName != null
										&& WeekName.equalsIgnoreCase("Week-1")) {
									System.out.println("The matched name is :"
											+ WeekName);
									System.out
											.println("The matched name is matched");
									Week.sendKeys("");
									Week.click();
									break;
								} else {
									System.out.println("payRun text "
											+ WeekName + " did not matched");
									rownum++;
								}

							}
							rownum++;
						}

					}
				}

			}
		} catch (Throwable t) {
			System.out.println(t.getMessage());
			System.out.println(t.getStackTrace());
		}
	}

	/*
	 * public void TaxPayRun_For_Week1() throws Throwable { try {
	 * Thread.sleep(4000L); if(existsElement(OR.getProperty("payRunWeekTable")))
	 * { WebElement payRunWeekOneTable = getObject("payRunWeekTable");
	 * WebElement Week1 = driver.findElement(By
	 * .xpath(OR.getProperty("WeekOneLocator"))); List<WebElement> rows =
	 * payRunWeekOneTable .findElements(By
	 * .xpath(OR.getProperty("WeekOneTablerows")));
	 * java.util.Iterator<WebElement> x = rows.iterator();
	 * 
	 * rownum = 1; while (x.hasNext()) { //div[2]/div/table/tbody/tr[15]/td[4]/a
	 * weekOneRecordId = "//div[" + "5" + "]/" + "div[" + "1]/" + "div/" +
	 * "div[" + "2]/" + "table/" + "tbody/tr[" + (rownum + 1) + "]/" + "th/" +
	 * "a"; Thread.sleep(2000L);
	 * 
	 * String weekText = Week1.getText(); if (weekText != null &&
	 * weekText.equalsIgnoreCase("Week-1")) {
	 * System.out.println("The week name is :"+weekText);
	 * driver.findElement(By.xpath(weekOneRecordId)).click(); break; } else {
	 * System.out.println("payRun text 'Week-1' did not matched"); rownum++; }
	 * 
	 * 
	 * }
	 * 
	 * }
	 * 
	 * } catch(Throwable t) { System.out.println(t.getMessage());
	 * 
	 * } }
	 */
	/************************************************************************/

	/********************************************************/

	/*
	 * weekly methods with parameter
	 */

	public void PayrollNIWeekly(String WeekName) throws Throwable {

		if (existsElement(OR.getProperty("payrollTab"))) {
			getObject("payrollTab").click();
		}
		Thread.sleep(5000L);
		if (existsElement(OR.getProperty("payrollViewLocator"))) {
			Select selectByValue = new Select(driver.findElement(By.xpath(OR
					.getProperty("payrollViewLocator"))));
			// This select by value needs to be called from OR.Properties
			selectByValue.selectByValue("All");
		}
		Thread.sleep(8000L);
		/*
		 * This code clicks to pagination from 1 to last page till it finds the
		 * '2Weekly' pay run Once it finds the '2Weekly' payrun, it clicks to
		 * it. Hence the following code finds the '2Weekly' payrun automatically
		 * from pagination ProcessingTo2Weekly() method searches the required
		 * company name and payrun
		 */

		try {
			Thread.sleep(14000L);
			if (existsElement(OR.getProperty("payroll2weeklytable"))) {

				WebElement table = driver.findElement(By.xpath(OR
						.getProperty("payroll2weeklytable")));
				List<WebElement> allpages = driver.findElements(By.xpath(OR
						.getProperty("paginationElement")));
				System.out.println("Total pages :" + allpages.size());
				for (int i = 0; i <= (allpages.size()); i++) {

					if (existsElement(OR.getProperty("paginationElement"))) {
						allpages.get(i).click();
					}
					List<WebElement> allrows = table
							.findElements(By
									.xpath("//*[@id='j_id0:j_id3']/div[2]/div/table[2]/tbody/tr[1]"));
					System.out.println("Total rows :" + allrows.size());
					for (int row = 1; row <= allrows.size(); row++) {
						ProcessingToWeekly(WeekName);
					}
				}
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}

	}

	public void ProcessingToWeekly(String WeekName) throws Throwable {
		try {
			WebElement tableheader = getObject("payroll2weeklytable");
			List<WebElement> th = tableheader.findElements(By.tagName("th"));
			for (int i = 0; i < th.size(); i++) {
				if ("Payroll".equalsIgnoreCase(th.get(i).getText())) {
					payrollcol_position = i + 1;
					break;
				}
			}

			for (int j = 0; j < th.size(); j++) {
				if ("Employer".equalsIgnoreCase(th.get(j).getText())) {
					Emplpoyercol_position = j + 1;
					break;
				}
			}

			for (int k = 0; k < th.size(); k++) {
				if ("Frequency".equalsIgnoreCase(th.get(k).getText())) {
					frequencyCol_Postition = k + 1;
					break;
				}
			}
			WebElement niweeklyPayrollTable = getObject("payroll2weeklytable");
			// need to check webelement exist
			// WebTable table = WebTable.getTable(niweeklyPayrollTable);
			List<WebElement> rows = niweeklyPayrollTable.findElements(By
					.xpath(OR.getProperty("payroll2weeklytablerows")));

			java.util.Iterator<WebElement> x = rows.iterator();
			rownum = 1;
			System.out.println("rownum is  :" + rownum);
			while (x.hasNext()) {
				WebElement emr1 = driver.findElement(By.xpath("//table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) +

						"]" + "/" + "td[" + Emplpoyercol_position + "]"));
				String empr = emr1.getText();
				WebElement ffr = driver.findElement(By.xpath("//table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/"
						+ "td[" + frequencyCol_Postition + "]"));
				String ffr1 = ffr.getText();
				WebElement ppr1 = driver.findElement(By.xpath("//table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/"
						+ "td[" + payrollcol_position + "]"));
				String ppr = ppr1.getText();
				payrollRecordId = "//table[2]/" + "tbody/" + "tr" + "["
						+ (rownum + 1) + "]" + "/" + "td["
						+ payrollcol_position + "]" + "/" + "a";
				if (empr != null
						&& empr.equalsIgnoreCase("DO NOT TOUCH PAYROLL AUTOMATION EMPLOYER")
						&& ppr.equalsIgnoreCase(pnWeek)
						&& ffr1.equalsIgnoreCase("Weekly")) {
					System.out
							.println("Finally the Employer name,Payfrequency 'Monthly' and required Payroll matched");
					driver.findElement(By.xpath(payrollRecordId)).click();
					if (existsElement(OR.getProperty("twoweeklyPayrolldetails"))) {
						String pfrequencey = getObject(
								"twoweeklyPayrolldetails").getText();
						System.out.println("the payfrequency is :"
								+ pfrequencey);
						paginForWeek(WeekName);
						break;
					}
					break;
				} else {
					System.out.println("payfrequency not matched");
				}
				rownum++;
			}

		} catch (Throwable t) {
			t.getMessage().toString();
			t.getStackTrace().toString();
		}
	}

	public void paginForWeek(String WeekName) throws Throwable {
		try {
			Thread.sleep(4000L);
			WebElement payRunWeekOneTable = getObject("payRunWeekTable");
			List<WebElement> allpages = driver.findElements(By.xpath(OR
					.getProperty("weekPagination")));
			System.out.println("Total pages :" + allpages.size());
			for (int i = 0; i <= (allpages.size()); i++) {
				if (existsElement(OR.getProperty("weekPagination"))) {
					allpages.get(i).click();
				}
				List<WebElement> allrows = payRunWeekOneTable.findElements(By
						.xpath(OR.getProperty("WeekOneTablerows")));
				System.out.println("Total rows :" + allrows.size());
				for (int row = 1; row <= allrows.size(); row++) {
					TaxPayRun_For_Week1Stat(WeekName);
				}
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void TaxPayRun_For_Week1Stat(String WeekName) throws Throwable {
		try {
			WebElement payRunWeekOneTable = getObject("payRunWeekTable");
			List<WebElement> rows = payRunWeekOneTable.findElements(By.xpath(OR
					.getProperty("WeekOneTablerows")));
			int totalRows = rows.size();
			System.out.println("total no of week rows are " + totalRows);
			java.util.Iterator<WebElement> x = rows.iterator();
			rownum = 1;
			comeoutOfwhile: while (x.hasNext()) {
				weekOneRecordId = "//div[" + "5" + "]/" + "div[" + "1]/"
						+ "div/" + "div[" + "2]/" + "table/" + "tbody/tr["
						+ (rownum + 1) + "]/" + "th/" + "a";
				WebElement Week1 = driver
						.findElement(By.xpath(weekOneRecordId));
				if (existsWebElement(Week1)) {
					String weekText = Week1.getText();
					if (weekText != null && weekText.equalsIgnoreCase(WeekName)) {
						System.out
								.println("The week name is Matched i.e. the week name is  :"
										+ weekText);
						Week1.sendKeys("");
						Week1.click();
						System.out.println("the weekname got clicked");
						break comeoutOfwhile;
					}

				}
				rownum++;
			}
		} catch (Throwable t) {
			System.out.println("some problem after click of weekname");
			System.out.println(t.getMessage());
		}
	}

	/*************** AutoEnrolment weekly payroll script methods **************************/

	public void PayrollNIWeeklyAutoEnrol(String EmployerName, String EmpName,
			String Payrolid, String Frquency, String WeekName,
			String ExcelInputSheet, String FirstReportNameInApplication,
			String TestResultExcelFilePath, String PayrollVeiw)
			throws Throwable {
		if (existsElement(OR.getProperty("payrollTab"))) {
			getObject("payrollTab").click();
		}
		Thread.sleep(5000L);
		if (existsElement(OR.getProperty("payrollViewLocator"))) {
			Select selectByValue = new Select(driver.findElement(By.xpath(OR
					.getProperty("payrollViewLocator"))));
			// This select by value needs to be called from OR.Properties
			selectByValue.selectByValue(PayrollVeiw);
		}
		Thread.sleep(8000L);
		/*
		 * This code clicks to pagination from 1 to last page till it finds the
		 * '2Weekly' pay run Once it finds the '2Weekly' payrun, it clicks to
		 * it. Hence the following code finds the '2Weekly' payrun automatically
		 * from pagination ProcessingTo2Weekly() method searches the required
		 * company name and payrun
		 */
		try {
			WebElement table = driver.findElement(By.xpath(OR
					.getProperty("payroll2weeklytable")));
			List<WebElement> allpages = driver.findElements(By.xpath(OR
					.getProperty("paginationElement")));
			System.out.println("Total pages :" + allpages.size());
			// endSearch:
			for (int i = 0; i <= (allpages.size()); i++) {

				if (existsElement(OR.getProperty("paginationElement"))) {
					allpages.get(i).click();
				}
				List<WebElement> allrows = table
						.findElements(By
								.xpath("//*[@id='j_id0:j_id3']/div[2]/div/table[2]/tbody/tr[1]"));
				System.out.println("Total rows :" + allrows.size());
				for (int row = 1; row <= allrows.size(); row++) {
					ProcessingToWeeklystat(EmployerName, EmpName, Payrolid,
							Frquency, WeekName, ExcelInputSheet,
							FirstReportNameInApplication,
							TestResultExcelFilePath);
				}
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void ProcessingToWeeklystat(String EmployerName, String EmpName,
			String Payrolid, String Frquency, String WeekName,
			String ExcelInputSheet, String FirstReportNameInApplication,
			String TestResultExcelFilePath) throws Throwable {
		try {
			WebElement tableheader = getObject("payroll2weeklytable");
			List<WebElement> th = tableheader.findElements(By.tagName("th"));
			for (int i = 0; i < th.size(); i++) {
				if ("Payroll".equalsIgnoreCase(th.get(i).getText())) {
					payrollcol_position = i + 1;
					break;
				}
			}

			for (int j = 0; j < th.size(); j++) {
				if ("Employer".equalsIgnoreCase(th.get(j).getText())) {
					Emplpoyercol_position = j + 1;
					break;
				}
			}

			for (int k = 0; k < th.size(); k++) {
				if ("Frequency".equalsIgnoreCase(th.get(k).getText())) {
					frequencyCol_Postition = k + 1;
					break;
				}
			}

			WebElement niweeklyPayrollTable = getObject("payroll2weeklytable");
			// need to check webelement exist
			// WebTable table = WebTable.getTable(niweeklyPayrollTable);
			List<WebElement> rows = niweeklyPayrollTable.findElements(By
					.xpath(OR.getProperty("payroll2weeklytablerows")));

			java.util.Iterator<WebElement> x = rows.iterator();
			rownum = 1;
			System.out.println("rownum is  :" + rownum);
			while (x.hasNext()) {
				WebElement emr1 = driver.findElement(By.xpath("//table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/"
						+ "td[" + Emplpoyercol_position + "]"));
				String empr = emr1.getText();
				WebElement ffr = driver.findElement(By.xpath("//table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/"
						+ "td[" + frequencyCol_Postition + "]"));
				String ffr1 = ffr.getText();
				WebElement ppr1 = driver.findElement(By.xpath("//table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/"
						+ "td[" + payrollcol_position + "]"));
				String ppr = ppr1.getText();
				payrollRecordId = "//table[2]/" + "tbody/" + "tr" + "["
						+ (rownum + 1) + "]" + "/" + "td["
						+ payrollcol_position + "]" + "/" + "a";
				if (empr != null && empr.equalsIgnoreCase(EmployerName)
						&& ppr.equalsIgnoreCase(Payrolid)
						&& ffr1.equalsIgnoreCase(Frquency)) {
					System.out
							.println("Finally the Employer name,Payfrequency 'Monthly' and required Payroll matched");
					// EmployerName Frquency Payrolid
					System.out.println("payfrequency 'Weekly' matched");
					driver.findElement(By.xpath(payrollRecordId)).click();
					if (existsElement(OR.getProperty("twoweeklyPayrolldetails"))) {
						String pfrequencey = getObject(
								"twoweeklyPayrolldetails").getText();
						System.out.println("the payfrequency is :"
								+ pfrequencey);
						paginForWeekStat(EmployerName, EmpName, Payrolid,
								Frquency, WeekName, ExcelInputSheet,
								FirstReportNameInApplication,
								TestResultExcelFilePath);
						break;
					}
					break;
				} else {
					System.out.println("payfrequency not matched");

				}
				rownum++;
			}
		} catch (Throwable t) {
			t.getMessage().toString();
			t.getStackTrace().toString();
		}

	}

	public void paginForWeekStat(String EmployerName, String EmpName,
			String Payrolid, String Frquency, String WeekName,
			String ExcelInputSheet, String FirstReportNameInApplication,
			String TestResultExcelFilePath) throws Throwable {
		try {
			Thread.sleep(4000L);
			WebElement payRunWeekOneTable = getObject("AutoEnrolpayRunWeekTable");
			List<WebElement> allpages = driver.findElements(By.xpath(OR
					.getProperty("weekPagination")));
			System.out.println("Total pages :" + allpages.size());
			// endInnerSearch:
			for (int i = 0; i <= (allpages.size()); i++) {
				if (existsElement(OR.getProperty("weekPagination"))) {
					allpages.get(i).click();
				}
				List<WebElement> allrows = payRunWeekOneTable.findElements(By
						.xpath(OR.getProperty("AutoEnrolpayRunWeekTablerows")));
				System.out.println("Total rows :" + allrows.size());
				for (int row = 1; row <= allrows.size(); row++) {
					TaxPayRun_For_Week1(EmployerName, EmpName, Payrolid,
							Frquency, WeekName, ExcelInputSheet,
							FirstReportNameInApplication,
							TestResultExcelFilePath);
				}
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}

	}

	public void TaxPayRun_For_Week1(String EmployerName, String EmpName,
			String Payrolid, String Frquency, String WeekName,
			String ExcelInputSheet, String FirstReportNameInApplication,
			String TestResultExcelFilePath) throws Throwable {
		try {
			WebElement payRunWeekOneTable = getObject("AutoEnrolpayRunWeekTable");
			List<WebElement> rows = payRunWeekOneTable.findElements(By.xpath(OR
					.getProperty("AutoEnrolpayRunWeekTablerows")));
			int totalRows = rows.size();
			System.out.println("total no of week rows are " + totalRows);
			java.util.Iterator<WebElement> x = rows.iterator();
			rownum = 1;
			while (x.hasNext()) {
				weekOneRecordId = "//div[" + "5" + "]/" + "div[" + "1]/"
						+ "div/" + "div[" + "2]/" + "table/" + "tbody/tr["
						+ (rownum + 1) + "]/" + "th/" + "a";
				WebElement Week2 = driver
						.findElement(By.xpath(weekOneRecordId));
				if (existsWebElement(Week2)) {
					String weekText = Week2.getText();
					if (weekText != null && weekText.equalsIgnoreCase(WeekName)) {
						System.out
								.println("The week name is Matched i.e. the week name is  :"
										+ weekText);
						Week2.sendKeys("");
						Week2.click();
						System.out.println("the weekname" + Week2
								+ " got clicked");
					} else {
						rownum++;
					}
				}
			}
		} catch (Throwable t) {
			System.out.println("some problem after click of weekname");
			System.out.println(t.getMessage());
		}
	}

	/*************** payroll NI 2Weekly script methods ******************/

	public void PayrollForNI2Weekly() throws Throwable {

		if (existsElement(OR.getProperty("payrollTab"))) {
			getObject("payrollTab").click();
		}
		Thread.sleep(5000L);

		if (existsElement(OR.getProperty("payrollViewLocator"))) {
			Select selectByValue = new Select(driver.findElement(By.xpath(OR
					.getProperty("payrollViewLocator"))));
			// This select by value needs to be called from OR.Properties
			selectByValue.selectByValue("All");
		}
		Thread.sleep(8000L);
		/*
		 * This code clicks to pagination from 1 to last page till it finds the
		 * '2Weekly' pay run Once it finds the '2Weekly' payrun, it clicks to
		 * it. Hence the following code finds the '2Weekly' payrun automatically
		 * from pagination ProcessingTo2Weekly() method searches the required
		 * company name and payrun
		 */
		try {
			WebElement table = driver.findElement(By.xpath(OR
					.getProperty("payroll2weeklytable")));
			List<WebElement> allpages = driver.findElements(By.xpath(OR
					.getProperty("paginationElement")));
			System.out.println("Total pages :" + allpages.size());
			for (int i = 0; i <= (allpages.size()); i++) {
				if (existsElement(OR.getProperty("paginationElement"))) {
					allpages.get(i).click();
				}
				List<WebElement> allrows = table
						.findElements(By
								.xpath("//*[@id='j_id0:j_id3']/div[2]/div/table[2]/tbody/tr[1]"));
				System.out.println("Total rows :" + allrows.size());
				for (int row = 1; row <= allrows.size(); row++) {
					ProcessingTo2Weekly();
				}
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void ProcessingTo2Weekly() throws Throwable {
		try {
			WebElement tableheader = getObject("payroll2weeklytable");
			List<WebElement> th = tableheader.findElements(By.tagName("th"));
			for (int i = 0; i < th.size(); i++) {
				if ("Payroll".equalsIgnoreCase(th.get(i).getText())) {
					payrollcol_position = i + 1;
					break;
				}
			}

			for (int j = 0; j < th.size(); j++) {
				if ("Employer".equalsIgnoreCase(th.get(j).getText())) {
					Emplpoyercol_position = j + 1;
					break;
				}
			}

			for (int k = 0; k < th.size(); k++) {
				if ("Frequency".equalsIgnoreCase(th.get(k).getText())) {
					frequencyCol_Postition = k + 1;
					break;
				}
			}
			WebElement niweeklyPayrollTable = getObject("payroll2weeklytable");
			// WebTable table = WebTable.getTable(niweeklyPayrollTable);
			List<WebElement> rows = niweeklyPayrollTable.findElements(By
					.xpath(OR.getProperty("payroll2weeklytablerows")));
			java.util.Iterator<WebElement> x = rows.iterator();
			rownum = 1;
			System.out.println("rownum is  :" + rownum);
			while (x.hasNext()) {
				WebElement emr1 = driver.findElement(By.xpath("//table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/"
						+ "td[" + Emplpoyercol_position + "]"));
				String empr = emr1.getText();
				WebElement ffr = driver.findElement(By.xpath("//table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/"
						+ "td				[" + frequencyCol_Postition + "]"));
				String ffr1 = ffr.getText();
				WebElement ppr1 = driver.findElement(By.xpath("//table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/"
						+ "td[" + payrollcol_position + "]"));
				String ppr = ppr1.getText();
				payrollRecordId = "//table[2]/" + "tbody/" + "tr" + "["
						+ (rownum + 1) + "]" + "/" + "td				["
						+ payrollcol_position + "]" + "/" + "a";
				if (empr != null
						&& empr.equalsIgnoreCase("DO NOT TOUCH PAYROLL AUTOMATION EMPLOYER")
						&& ppr.equalsIgnoreCase(pnTwoWeek)
						&& ffr1.equalsIgnoreCase("Two Weekly")) {
					System.out
							.println("Finally the Employer name,Payfrequency 'Monthly' and required Payroll 					matched");
					driver.findElement(By.xpath(payrollRecordId)).click();
					if (existsElement(OR.getProperty("twoweeklyPayrolldetails"))) {
						String pfrequencey = getObject(
								"twoweeklyPayrolldetails").getText();
						System.out.println("the payfrequency is :"
								+ pfrequencey);
						PayRunTwoWeek2();
						break;
					}
					break;
				} else {
					System.out.println("payfrequency not matched");
				}
				rownum++;
			}
		} catch (Throwable t) {
			t.getMessage().toString();
			t.getStackTrace().toString();
		}
	}

	/*
	 * public void PayRunTwoWeek2() throws Throwable { try {
	 * Thread.sleep(4000L); WebElement payRunWeekOneTable =
	 * getObject("payRunTwoWeekTable"); List<WebElement> rows =
	 * payRunWeekOneTable .findElements(By
	 * .xpath("//*[contains(@id, '_body')]/table/tbody/tr"));
	 * java.util.Iterator<WebElement> x = rows.iterator(); rownum = 1; while
	 * (x.hasNext()) { weekRecordId = "//div[" + "5" + "]/" + "div[" + "1]/" +
	 * "div/" + "div[" + "2]/" + "table/" + "tbody/tr[" + (rownum + 1) + "]/" +
	 * "th/" + "a"; WebElement firstWeek = driver.findElement(By
	 * .xpath("//table/tbody/tr[2]/th/a")); String weekText =
	 * firstWeek.getText(); if (weekText != null &&
	 * weekText.equalsIgnoreCase("Two Week-2")) {
	 * driver.findElement(By.xpath(weekRecordId)).click(); break;
	 * 
	 * } else { System.out.println("payRun text 'Two Week-2' did not matched");
	 * } }
	 * 
	 * } catch(Throwable t) { t.getMessage().toString();
	 * t.getStackTrace().toString();
	 * 
	 * } }
	 */

	public void PayRunTwoWeek2() throws Throwable {
		try {
			if (existsElement(OR.getProperty("payrollDetailsTableheader1"))) {
				WebElement tableheader = getObject("payrollDetailsTableheader1");
				// driver.findElement(By.xpath(OR.getProperty("payrollDetailsTableheader")));
				List<WebElement> th = tableheader
						.findElements(By.tagName("th"));
				for (int i = 1; i < th.size(); i++) {
					payrunMonth = null;
					payrunMonth = driver
							.findElement(
									By.xpath("//*[contains(@id, '_body')]/table/tbody/tr[1]/th["
											+ i + "]")).getText();
					System.out.println("THE Column name is :" + payrunMonth);
					if (payrunMonth.equalsIgnoreCase("Pay run")) {
						System.out.println("THE Column payrun matched :"
								+ payrunMonth);
						break;
					}

					if (existsElement(OR.getProperty("payrunMonthlyTable"))) {
						WebElement payRunWeekOneTable = getObject("payrunMonthlyTable");
						if (existsWebElement(payRunWeekOneTable)) {
							List<WebElement> rows = payRunWeekOneTable
									.findElements(By.xpath(OR
											.getProperty("payrunMonthlyTableRows")));
							java.util.Iterator<WebElement> x = rows.iterator();
							rownum = 2;
							while (x.hasNext()) {
								System.out.println("ruwnum is :" + rownum);
								Thread.sleep(2000L);
								WebElement Week = driver
										.findElement(By
												.xpath("//div[" + "5" + "]/"
														+ "div[" + "1]/"
														+ "div/" + "div["
														+ "2]/" + "table/" +

														"tbody/tr[" + rownum
														+ "]/th/a"));
								String WeekName = Week.getText();
								if (WeekName != null
										&& WeekName
												.equalsIgnoreCase("Two Week-2")) {
									System.out.println("The month name is :"
											+ WeekName);
									System.out
											.println("The month name is matched");
									Week.sendKeys("");
									Week.click();
									break;
								} else {
									System.out.println("payRun text "
											+ WeekName + " did not matched");
									rownum++;
								}

							}
							rownum++;
						}

					}
				}

			}
		} catch (Throwable t) {
			System.out.println(t.getMessage());
			System.out.println(t.getStackTrace());
		}
	}

	/************ 4weekly payroll script methods ********************/

	public void PayrollForNI4Weekly() throws Throwable {
		if (existsElement(OR.getProperty("payrollTab"))) {
			getObject("payrollTab").click();

		}
		Thread.sleep(5000L);

		if (existsElement(OR.getProperty("payrollViewLocator"))) {
			Select selectByValue = new Select(driver.findElement(By.xpath(OR
					.getProperty("payrollViewLocator"))));
			// This select by value needs to be called from OR.Properties
			selectByValue.selectByValue("All");
		}
		Thread.sleep(8000L);
		/*
		 * This code clicks to pagination from 1 to last page till it finds the
		 * '2Weekly' pay run Once it finds the '2Weekly' payrun, it clicks to
		 * it. Hence the following code finds the '2Weekly' payrun automatically
		 * from pagination ProcessingTo2Weekly() method searches the required
		 * company name and payrun
		 */
		try {
			WebElement table = driver.findElement(By.xpath(OR
					.getProperty("payroll2weeklytable")));
			List<WebElement> allpages = driver.findElements(By.xpath(OR
					.getProperty("paginationElement")));
			System.out.println("Total pages :" + allpages.size());
			for (int i = 0; i <= (allpages.size()); i++) {
				if (existsElement(OR.getProperty("paginationElement"))) {
					allpages.get(i).click();
				}
				List<WebElement> allrows = table
						.findElements(By
								.xpath("//*[@id='j_id0:j_id3']/div[2]/div/table[2]/tbody/tr[1]"));
				System.out.println("Total rows :" + allrows.size());
				for (int row = 1; row <= allrows.size(); row++) {
					ProcessingTo4Weekly();
				}
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void ProcessingTo4Weekly() throws Throwable {
		try {
			WebElement tableheader = getObject("payroll2weeklytable");
			List<WebElement> th = tableheader.findElements(By.tagName("th"));

			for (int i = 0; i < th.size(); i++) {
				if ("Payroll".equalsIgnoreCase(th.get(i).getText())) {
					payrollcol_position = i + 1;
					break;
				}
			}

			for (int j = 0; j < th.size(); j++) {
				if ("Employer".equalsIgnoreCase(th.get(j).getText())) {
					Emplpoyercol_position = j + 1;
					break;
				}

			}

			for (int k = 0; k < th.size(); k++) {
				if ("Frequency".equalsIgnoreCase(th.get(k).getText())) {
					frequencyCol_Postition = k + 1;
					break;

				}

			}

			WebElement niweeklyPayrollTable = getObject("payroll2weeklytable");
			// WebTable table = WebTable.getTable(niweeklyPayrollTable);
			List<WebElement> rows = niweeklyPayrollTable.findElements(By
					.xpath(OR.getProperty("payroll2weeklytablerows")));
			java.util.Iterator<WebElement> x = rows.iterator();
			rownum = 1;
			System.out.println("rownum is  :" + rownum);
			while (x.hasNext()) {
				WebElement emr1 = driver.findElement(By.xpath("//table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) +

						"]" + "/" + "td[" + Emplpoyercol_position + "]"));
				String empr = emr1.getText();

				WebElement ffr = driver.findElement(By.xpath("//table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/"
						+ "td[" + frequencyCol_Postition + "]"));
				String ffr1 = ffr.getText();

				WebElement ppr1 = driver.findElement(By.xpath("//table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/" +

						"td[" + payrollcol_position + "]"));
				String ppr = ppr1.getText();

				payrollRecordId = "//table[2]/" + "tbody/" + "tr" + "["
						+ (rownum + 1) + "]" + "/" + "td["
						+ payrollcol_position + "]" + "/" + "a";

				if (empr != null
						&& empr.equalsIgnoreCase("DO NOT TOUCH PAYROLL AUTOMATION EMPLOYER")
						&& ppr.equalsIgnoreCase(pnFourWeek)
						&& ffr1.equalsIgnoreCase("Four Weekly")) {
					System.out
							.println("Finally the Employer name,Payfrequency 'Monthly' and required Payroll matched");

					driver.findElement(By.xpath(payrollRecordId)).click();
					if (existsElement(OR.getProperty("twoweeklyPayrolldetails"))) {
						String pfrequencey = getObject(
								"twoweeklyPayrolldetails").getText();
						System.out.println("the payfrequency is :"
								+ pfrequencey);
						PayRun4Week4();
						break;
					}
					break;
				} else {
					System.out.println("payfrequency not matched");
				}
				rownum++;
			}

		} catch (Throwable t) {
			t.getMessage().toString();
			t.getStackTrace().toString();
		}

	}

	/*
	 * public void ProcessingTo4Weekly() throws Throwable { try { WebElement
	 * niweeklyPayrollTable = getObject("payroll2weeklytable"); WebTable table =
	 * WebTable.getTable(niweeklyPayrollTable); List<WebElement> rows =
	 * niweeklyPayrollTable .findElements(By
	 * .xpath(OR.getProperty("payroll2weeklytablerows")));
	 * java.util.Iterator<WebElement> x = rows.iterator(); rownum = 1;
	 * System.out.println("rownum is  :" + rownum); while (x.hasNext()) { String
	 * companyname = table.getTBody().getRow(rownum).getCell(0).getText();
	 * String payrollId = table.getTBody().getRow(rownum).getCell(3).getText();
	 * 
	 * String payFrequency =
	 * table.getTBody().getRow(rownum).getCell(4).getText(); payrollRecordId =
	 * "//table[2]/" + "tbody/" + "tr" + "[" + (rownum + 1)+ "]" + "/" +
	 * "td[4]/" + "a";
	 * 
	 * if (payFrequency != null && companyname
	 * .equalsIgnoreCase("DO NOT TOUCH PAYROLL AUTOMATION EMPLOYER") &&
	 * payFrequency.equalsIgnoreCase("Four Weekly")&&
	 * payrollId.equalsIgnoreCase(pnFourWeek))//Two Weekly {
	 * System.out.println("payfrequency 'Weekly' matched");
	 * driver.findElement(By.xpath(payrollRecordId)).click(); if
	 * (existsElement(OR.getProperty("twoweeklyPayrolldetails"))) { String
	 * pfrequencey = getObject("twoweeklyPayrolldetails").getText();
	 * System.out.println("the payfrequency is :" + pfrequencey);
	 * PayRun4Week4(); break; } break; } else {
	 * System.out.println("payfrequency not matched"); } rownum++; }
	 * 
	 * } catch(Throwable t) { t.getMessage().toString();
	 * t.getStackTrace().toString(); }
	 * 
	 * }
	 */
	//

	/************************/

	public void PayRun4Week4() throws Throwable {
		try {
			if (existsElement(OR.getProperty("payrollDetailsTableheader1"))) {
				WebElement tableheader = getObject("payrollDetailsTableheader1");
				// driver.findElement(By.xpath(OR.getProperty("payrollDetailsTableheader")));
				List<WebElement> th = tableheader
						.findElements(By.tagName("th"));

				for (int i = 1; i < th.size(); i++) {
					payrunMonth = null;
					payrunMonth = driver
							.findElement(
									By.xpath("//*[contains(@id, '_body')]/table/tbody/tr[1]/th["
											+ i + "]")).getText();
					System.out.println("THE Column name is :" + payrunMonth);
					if (payrunMonth.equalsIgnoreCase("Pay run")) {
						System.out.println("THE Column payrun matched :"
								+ payrunMonth);
						break;
					}

					if (existsElement(OR.getProperty("payrunMonthlyTable"))) {
						WebElement payRunWeekOneTable = getObject("payrunMonthlyTable");
						if (existsWebElement(payRunWeekOneTable)) {

							List<WebElement> rows = payRunWeekOneTable
									.findElements(By.xpath(OR
											.getProperty("payrunMonthlyTableRows")));
							java.util.Iterator<WebElement> x = rows.iterator();

							rownum = 2;

							while (x.hasNext()) {
								System.out.println("ruwnum is :" + rownum);

								Thread.sleep(2000L);
								WebElement Week = driver
										.findElement(By
												.xpath("//div[" + "5" + "]/"
														+ "div[" + "1]/"
														+ "div/" + "div["
														+ "2]/" + "table/" +

														"tbody/tr[" + rownum
														+ "]/th/a"));

								String WeekName = Week.getText();

								if (WeekName != null
										&& WeekName
												.equalsIgnoreCase("Four Week-4")) {
									System.out.println("The month name is :"
											+ WeekName);
									System.out
											.println("The month name is matched");
									Week.sendKeys("");
									Week.click();
									break;
								} else {
									System.out.println("payRun text "
											+ WeekName + " did not matched");
									rownum++;
								}

							}
							rownum++;
						}

					}
				}

			}
		} catch (Throwable t) {
			System.out.println(t.getMessage());
			System.out.println(t.getStackTrace());
		}
	}

	/**************************/

	/*
	 * public void PayRun4Week4() throws Throwable { try { Thread.sleep(4000L);
	 * WebElement payRunWeekOneTable = getObject("payrunFourweekTable");
	 * List<WebElement> rows = payRunWeekOneTable .findElements(By
	 * .xpath(OR.getProperty("payrunFourweekTableRows")));
	 * java.util.Iterator<WebElement> x = rows.iterator(); rownum = 1; while
	 * (x.hasNext()) { weekRecordId = "//div[" + "5" + "]/" + "div[" + "1]/" +
	 * "div/" + "div[" + "2]/" + "table/" + "tbody/tr[" + (rownum + 1) + "]/" +
	 * "th/" + "a"; WebElement firstWeek = driver.findElement(By
	 * .xpath("//table/tbody/tr[2]/th/a")); String weekText =
	 * firstWeek.getText(); if (weekText != null &&
	 * weekText.equalsIgnoreCase("Four Week-4")) {
	 * driver.findElement(By.xpath(weekRecordId)).click(); break;
	 * 
	 * 
	 * } else { System.out.println("payRun text 'Four Week-4' did not matched");
	 * } }
	 * 
	 * } catch(Throwable t) { System.out.println(t.getMessage());
	 * 
	 * } }
	 */

	/*******************************************************/
	// to change mehthod name.

	public void PayrollForNIFourWeekly(String FourWeekName) throws Throwable {

		if (existsElement(OR.getProperty("payrollTab"))) {
			getObject("payrollTab").click();

		}
		Thread.sleep(5000L);

		if (existsElement(OR.getProperty("payrollViewLocator"))) {
			Select selectByValue = new Select(driver.findElement(By.xpath(OR
					.getProperty("payrollViewLocator"))));
			// This select by value needs to be called from OR.Properties
			selectByValue.selectByValue("All");
		}
		Thread.sleep(8000L);
		/*
		 * This code clicks to pagination from 1 to last page till it finds the
		 * '2Weekly' pay run Once it finds the '2Weekly' payrun, it clicks to
		 * it. Hence the following code finds the '2Weekly' payrun automatically
		 * from pagination ProcessingTo2Weekly() method searches the required
		 * company name and payrun
		 */
		try {
			WebElement table = driver.findElement(By.xpath(OR
					.getProperty("payroll2weeklytable")));
			List<WebElement> allpages = driver.findElements(By.xpath(OR
					.getProperty("paginationElement")));
			System.out.println("Total pages :" + allpages.size());
			for (int i = 0; i <= (allpages.size()); i++) {
				if (existsElement(OR.getProperty("paginationElement"))) {
					allpages.get(i).click();
				}
				List<WebElement> allrows = table
						.findElements(By
								.xpath("//*[@id='j_id0:j_id3']/div[2]/div/table[2]/tbody/tr[1]"));
				System.out.println("Total rows :" + allrows.size());
				for (int row = 1; row <= allrows.size(); row++) {

					ProcessingToFourWeekly(FourWeekName);
				}
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void ProcessingToFourWeekly(String FourWeekName) throws Throwable {
		try {
			WebElement tableheader = getObject("payroll2weeklytable");
			List<WebElement> th = tableheader.findElements(By.tagName("th"));
			for (int i = 0; i < th.size(); i++) {
				if ("Payroll".equalsIgnoreCase(th.get(i).getText())) {
					payrollcol_position = i + 1;
					break;
				}
			}

			for (int j = 0; j < th.size(); j++) {
				if ("Employer".equalsIgnoreCase(th.get(j).getText())) {
					Emplpoyercol_position = j + 1;
					break;
				}

			}

			for (int k = 0; k < th.size(); k++) {
				if ("Frequency".equalsIgnoreCase(th.get(k).getText())) {
					frequencyCol_Postition = k + 1;
					break;

				}

			}
			WebElement niweeklyPayrollTable = getObject("payroll2weeklytable");
			// WebTable table = WebTable.getTable(niweeklyPayrollTable);
			List<WebElement> rows = niweeklyPayrollTable.findElements(By
					.xpath(OR.getProperty("payroll2weeklytablerows")));
			java.util.Iterator<WebElement> x = rows.iterator();
			rownum = 1;
			System.out.println("rownum is  :" + rownum);
			while (x.hasNext()) {
				WebElement emr1 = driver.findElement(By.xpath("//table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) +

						"]" + "/" + "td[" + Emplpoyercol_position + "]"));
				String empr = emr1.getText();

				WebElement ffr = driver.findElement(By.xpath("//table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/"
						+ "td[" + frequencyCol_Postition + "]"));
				String ffr1 = ffr.getText();

				WebElement ppr1 = driver.findElement(By.xpath("//table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/" +

						"td[" + payrollcol_position + "]"));
				String ppr = ppr1.getText();

				payrollRecordId = "//table[2]/" + "tbody/" + "tr" + "["
						+ (rownum + 1) + "]" + "/" + "td["
						+ payrollcol_position + "]" + "/" + "a";

				if (empr != null
						&& empr.equalsIgnoreCase("DO NOT TOUCH PAYROLL AUTOMATION EMPLOYER")
						&& ppr.equalsIgnoreCase(pnFourWeek)
						&& ffr1.equalsIgnoreCase("Four Weekly")) {
					System.out
							.println("Finally the Employer name,Payfrequency 'Monthly' and required Payroll matched");
					driver.findElement(By.xpath(payrollRecordId)).click();
					if (existsElement(OR.getProperty("twoweeklyPayrolldetails"))) {
						String pfrequencey = getObject(
								"twoweeklyPayrolldetails").getText();
						System.out.println("the payfrequency is :"
								+ pfrequencey);
						PayRunFourWeek(FourWeekName);
						break;
					}
					break;
				} else {
					System.out.println("payfrequency not matched");
				}
				rownum++;
			}

		} catch (Throwable t) {
			t.getMessage().toString();
			t.getStackTrace().toString();
		}

	}

	//

	public void PayRunFourWeek(String FourWeekName) throws Throwable {
		try {
			Thread.sleep(4000L);
			WebElement payRunWeekOneTable = getObject("payrunFourweekTable");
			List<WebElement> rows = payRunWeekOneTable.findElements(By.xpath(OR
					.getProperty("payrunFourweekTableRows")));
			java.util.Iterator<WebElement> x = rows.iterator();
			rownum = 1;
			while (x.hasNext()) {
				weekRecordId = "//div[" + "5" + "]/" + "div[" + "1]/" + "div/"
						+ "div[" + "2]/" + "table/" + "tbody/tr["
						+ (rownum + 1) + "]/" + "th/" + "a";

				// div[5]/div[1]/div/div[2]/table/tbody/tr[2]/th/a
				WebElement firstWeek = driver.findElement(By
						.xpath("//table/tbody/tr[" + (rownum + 1) + "]/"
								+ "th/" + "a"));
				String weekText = firstWeek.getText();
				System.out.println("four week name is  " + weekText);
				if (weekText != null && weekText.equalsIgnoreCase(FourWeekName)) {
					driver.findElement(By.xpath(weekRecordId)).click();
					break;

				} else {
					System.out.println("Four week-20" + "  not matched");

				}
				rownum++;

			}

		} catch (Throwable t) {
			System.out.println(t.getMessage());

		}
	}

	/********************* Payroll NI Monthly script Methods ********************/

	public void PayrollForNIMonthly() throws Throwable {
		if (existsElement(OR.getProperty("payrollTab"))) {
			getObject("payrollTab").click();
		}
		Thread.sleep(5000L);
		if (existsElement(OR.getProperty("payrollViewLocator"))) {
			Select selectByValue = new Select(driver.findElement(By.xpath(OR
					.getProperty("payrollViewLocator"))));
			// This select by value needs to be called from OR.Properties
			selectByValue.selectByValue("All");
		}
		Thread.sleep(8000L);
		/*
		 * This code clicks to pagination from 1 to last page till it finds the
		 * '2Weekly' pay run Once it finds the '2Weekly' payrun, it clicks to
		 * it. Hence the following code finds the '2Weekly' payrun automatically
		 * from pagination ProcessingTo2Weekly() method searches the required
		 * company name and payrun
		 */
		try {
			WebElement table = driver.findElement(By.xpath(OR
					.getProperty("payroll2weeklytable")));
			List<WebElement> allpages = driver.findElements(By.xpath(OR
					.getProperty("paginationElement")));
			System.out.println("Total pages :" + allpages.size());
			for (int i = 0; i <= (allpages.size()); i++) {
				if (existsElement(OR.getProperty("paginationElement"))) {
					allpages.get(i).click();
				}
				List<WebElement> allrows = table
						.findElements(By
								.xpath("//*[@id='j_id0:j_id3']/div[2]/div/table[2]/tbody/tr[1]"));
				// System.out.println("Total rows :" +allrows.size());
				Thread.sleep(2000L);
				for (int row = 1; row <= allrows.size(); row++) {
					PayrollNIMonthly();
				}
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void PayrollNIMonthly() throws Throwable {
		try {
			WebElement tableheader = getObject("payroll2weeklytable");
			List<WebElement> th = tableheader.findElements(By.tagName("th"));

			for (int i = 0; i < th.size(); i++) {
				if ("Payroll".equalsIgnoreCase(th.get(i).getText())) {
					payrollcol_position = i + 1;
					break;
				}
			}

			for (int j = 0; j < th.size(); j++) {
				if ("Employer".equalsIgnoreCase(th.get(j).getText())) {
					Emplpoyercol_position = j + 1;
					break;
				}

			}

			for (int k = 0; k < th.size(); k++) {
				if ("Frequency".equalsIgnoreCase(th.get(k).getText())) {
					frequencyCol_Postition = k + 1;
					break;
				}

			}

			WebElement niweeklyPayrollTable = getObject("payroll2weeklytable");
			if (existsWebElement(niweeklyPayrollTable)) {
				// WebTable table = WebTable.getTable(niweeklyPayrollTable);
				List<WebElement> rows = niweeklyPayrollTable.findElements(By
						.xpath(OR.getProperty("payroll2weeklytablerows")));
				java.util.Iterator<WebElement> x = rows.iterator();
				rownum = 1;
				// System.out.println("rownum is  :" + rownum);
				nowbreak: while (x.hasNext()) {
					WebElement emr1 = driver.findElement(By.xpath("//table[2]/"
							+ "tbody/" + "tr" + "[" + (rownum + 1) +

							"]" + "/" + "td[" + Emplpoyercol_position + "]"));
					String empr = emr1.getText();

					WebElement ffr = driver.findElement(By.xpath("//table[2]/"
							+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/"
							+ "td[" + frequencyCol_Postition + "]"));
					String ffr1 = ffr.getText();

					WebElement ppr1 = driver.findElement(By.xpath("//table[2]/"
							+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/" +

							"td[" + payrollcol_position + "]"));
					String ppr = ppr1.getText();

					payrollRecordId = "//table[2]/" + "tbody/" + "tr" + "["
							+ (rownum + 1) + "]" + "/" + "td["
							+ payrollcol_position + "]" + "/" + "a";

					if (empr != null
							&& empr.equalsIgnoreCase("DO NOT TOUCH PAYROLL AUTOMATION EMPLOYER")
							&& ppr.equalsIgnoreCase(pn)
							&& ffr1.equalsIgnoreCase("Monthly")) {
						System.out
								.println("Finally the Employer name,Payfrequency 'Monthly' and required Payroll matched");
						driver.findElement(By.xpath(payrollRecordId)).click();
						if (existsElement(OR
								.getProperty("twoweeklyPayrolldetails"))) {
							String pfrequencey = getObject(
									"twoweeklyPayrolldetails").getText();
							System.out.println("the payfrequency is :"
									+ pfrequencey);
							TaxPayRun_For_Month();
							break nowbreak;
						}
						break;
					} else {
						System.out.println("");
					}
					rownum++;
				}

			}

		} catch (Throwable t) {
			t.getMessage().toString();
			t.getStackTrace().toString();
		}

	}

	/*
	 * public void PayrollNIMonthly() throws Throwable { try { WebElement
	 * niweeklyPayrollTable = getObject("payroll2weeklytable");
	 * if(existsWebElement(niweeklyPayrollTable)) {
	 * 
	 * 
	 * WebTable table = WebTable.getTable(niweeklyPayrollTable);
	 * List<WebElement> rows = niweeklyPayrollTable .findElements(By
	 * .xpath(OR.getProperty("payroll2weeklytablerows")));
	 * java.util.Iterator<WebElement> x = rows.iterator(); rownum = 1; //
	 * System.out.println("rownum is  :" + rownum); nowbreak: while
	 * (x.hasNext()) { String companyname =
	 * table.getTBody().getRow(rownum).getCell(0).getText(); String payrollId =
	 * table.getTBody().getRow(rownum).getCell(3).getText();
	 * 
	 * String payFrequency =
	 * table.getTBody().getRow(rownum).getCell(4).getText(); payrollRecordId =
	 * "//table[2]/" + "tbody/" + "tr" + "[" + (rownum + 1)+ "]" + "/" +
	 * "td[4]/" + "a";
	 * 
	 * if (payFrequency != null && companyname
	 * .equalsIgnoreCase("DO NOT TOUCH PAYROLL AUTOMATION EMPLOYER") &&
	 * payFrequency.equalsIgnoreCase("Monthly")&&
	 * payrollId.equalsIgnoreCase(pn))//Two Weekly {
	 * System.out.println("payfrequency 'Monthly' matched");
	 * driver.findElement(By.xpath(payrollRecordId)).click(); if
	 * (existsElement(OR.getProperty("twoweeklyPayrolldetails"))) { String
	 * pfrequencey = getObject("twoweeklyPayrolldetails").getText();
	 * System.out.println("the payfrequency is :" + pfrequencey);
	 * TaxPayRun_For_Month(); break nowbreak; } break; } else {
	 * System.out.println(""); } rownum++; }
	 * 
	 * }
	 * 
	 * } catch(Throwable t) { t.getMessage().toString();
	 * t.getStackTrace().toString(); }
	 * 
	 * }
	 */

	public void TaxPayRun_For_Month() throws Throwable {
		try {
			if (existsElement(OR.getProperty("payrollDetailsTableheader1"))) {
				WebElement tableheader = getObject("payrollDetailsTableheader1");
				// driver.findElement(By.xpath(OR.getProperty("payrollDetailsTableheader")));
				List<WebElement> th = tableheader
						.findElements(By.tagName("th"));

				for (int i = 1; i < th.size(); i++) {
					payrunMonth = null;
					payrunMonth = driver
							.findElement(
									By.xpath("//*[contains(@id, '_body')]/table/tbody/tr[1]/th["
											+ i + "]")).getText();
					System.out.println("THE Column name is :" + payrunMonth);
					if (payrunMonth.equalsIgnoreCase("Pay run")) {
						System.out.println("THE Column payrun matched :"
								+ payrunMonth);
						break;
					}

					if (existsElement(OR.getProperty("payrunMonthlyTable"))) {
						WebElement payRunWeekOneTable = getObject("payrunMonthlyTable");
						if (existsWebElement(payRunWeekOneTable)) {

							List<WebElement> rows = payRunWeekOneTable
									.findElements(By.xpath(OR
											.getProperty("payrunMonthlyTableRows")));
							java.util.Iterator<WebElement> x = rows.iterator();

							rownum = 2;

							while (x.hasNext()) {
								System.out.println("ruwnum is :" + rownum);

								Thread.sleep(2000L);
								WebElement Week = driver
										.findElement(By
												.xpath("//div[" + "5" + "]/"
														+ "div[" + "1]/"
														+ "div/" + "div["
														+ "2]/" + "table/" +

														"tbody/tr[" + rownum
														+ "]/th/a"));

								String WeekName = Week.getText();
								//
								if (WeekName != null
										&& WeekName
												.equalsIgnoreCase("April-2015")) {
									System.out.println("The matched name is :"
											+ WeekName);
									System.out
											.println("The matched name is matched");
									Week.sendKeys("");
									Week.click();
									break;
								} else {
									System.out.println("payRun text "
											+ WeekName + " did not matched");
									rownum++;
								}

							}
							rownum++;
						}

					}
				}

			}
		} catch (Throwable t) {
			System.out.println(t.getMessage());
			System.out.println(t.getStackTrace());
		}
	}

	/*
	 * public void TaxPayRun_For_Month() throws Throwable { try {
	 * //Thread.sleep(4000L);
	 * if(existsElement(OR.getProperty("payrunMonthlyTable"))) { WebElement
	 * payRunWeekOneTable = getObject("payrunMonthlyTable");
	 * if(existsWebElement(payRunWeekOneTable)) { List<WebElement> rows =
	 * payRunWeekOneTable .findElements(By
	 * .xpath(OR.getProperty("payrunMonthlyTableRows")));
	 * java.util.Iterator<WebElement> x = rows.iterator();
	 * 
	 * rownum = 2;
	 * 
	 * while (x.hasNext()) { System.out.println("ruwnum is :"+rownum);
	 * 
	 * 
	 * Thread.sleep(2000L); WebElement Month4 = driver.findElement(By
	 * .xpath("//div[" + "5" + "]/" + "div[" + "1]/" + "div/"+ "div[" + "2]/" +
	 * "table/" + "tbody/tr[" + rownum+ "]/" + "th/" + "a"));
	 * 
	 * String MonthName = Month4.getText();
	 * //System.out.println("The month name is :"+MonthName); if (MonthName !=
	 * null && MonthName.equalsIgnoreCase("April-2015")) {
	 * System.out.println("The month name is :"+MonthName); Month4.sendKeys("");
	 * Month4.click(); break; } else {
	 * System.out.println("payRun text 'April-2015' did not matched"); rownum++;
	 * }
	 * 
	 * } rownum++; } else { driver.navigate().refresh(); }
	 * 
	 * } } catch(Throwable t) { System.out.println(t.getMessage());
	 * System.out.println(t.getStackTrace());
	 * 
	 * } }
	 */

	/************************* Auto enrolment weekly methods *************************/

	/************************************************************************/

	public void ProcessingToWeeklyForStatutory(String EmployerName,
			String EmpName, String Payrolid, String Frquency, String MonthName,
			String ExcelInputSheet, String FirstReportNameInApplication,
			String TestResultExcelFilePath, String PayrollView)
			throws Throwable {
		try {
			WebElement tableheader = getObject("payroll2weeklytable");
			List<WebElement> th = tableheader.findElements(By.tagName("th"));

			for (int i = 0; i < th.size(); i++) {
				if ("Payroll".equalsIgnoreCase(th.get(i).getText())) {
					payrollcol_position = i + 1;
					break;
				}
			}

			for (int j = 0; j < th.size(); j++) {
				if ("Employer".equalsIgnoreCase(th.get(j).getText())) {
					Emplpoyercol_position = j + 1;
					break;
				}

			}

			for (int k = 0; k < th.size(); k++) {
				if ("Frequency".equalsIgnoreCase(th.get(k).getText())) {
					frequencyCol_Postition = k + 1;
					break;

				}

			}

			WebElement niweeklyPayrollTable = getObject("payroll2weeklytable");
			// need to check webelement exist
			// WebTable table = WebTable.getTable(niweeklyPayrollTable);
			List<WebElement> rows = niweeklyPayrollTable.findElements(By
					.xpath(OR.getProperty("payroll2weeklytablerows")));

			java.util.Iterator<WebElement> x = rows.iterator();
			rownum = 1;
			System.out.println("rownum is  :" + rownum);
			while (x.hasNext()) {
				WebElement emr1 = driver.findElement(By.xpath("//table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) +

						"]" + "/" + "td[" + Emplpoyercol_position + "]"));
				String empr = emr1.getText();

				WebElement ffr = driver.findElement(By.xpath("//table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/"
						+ "td[" + frequencyCol_Postition + "]"));
				String ffr1 = ffr.getText();

				WebElement ppr1 = driver.findElement(By.xpath("//table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/" +

						"td[" + payrollcol_position + "]"));
				String ppr = ppr1.getText();

				payrollRecordId = "//table[2]/" + "tbody/" + "tr" + "["
						+ (rownum + 1) + "]" + "/" + "td["
						+ payrollcol_position + "]" + "/" + "a";

				if (empr != null
						&& empr.equalsIgnoreCase("DO NOT TOUCH PAYROLL AUTOMATION EMPLOYER")
						&& ppr.equalsIgnoreCase(pnWeek)
						&& ffr1.equalsIgnoreCase("Weekly")) {
					System.out
							.println("Finally the Employer name,Payfrequency 'Monthly' and required Payroll matched");

					driver.findElement(By.xpath(payrollRecordId)).click();
					if (existsElement(OR.getProperty("twoweeklyPayrolldetails"))) {
						String pfrequencey = getObject(
								"twoweeklyPayrolldetails").getText();
						System.out.println("the payfrequency is :"
								+ pfrequencey);
						paginForWeekstat(EmployerName, EmpName, Payrolid,
								Frquency, MonthName, ExcelInputSheet,
								FirstReportNameInApplication,
								TestResultExcelFilePath);
						break;
					}
					break;
				} else {
					System.out.println("payfrequency not matched");

				}
				rownum++;
			}

		} catch (Throwable t) {
			t.getMessage().toString();
			t.getStackTrace().toString();
		}

	}

	public void paginForWeekstat(String EmployerName, String EmpName,
			String Payrolid, String Frquency, String MonthName,
			String ExcelInputSheet, String FirstReportNameInApplication,
			String TestResultExcelFilePath) throws Throwable {
		try {
			Thread.sleep(4000L);

			WebElement payRunWeekOneTable = getObject("payRunWeekTable");
			List<WebElement> allpages = driver.findElements(By.xpath(OR
					.getProperty("weekPagination")));
			System.out.println("Total pages :" + allpages.size());
			for (int i = 0; i <= (allpages.size()); i++) {
				if (existsElement(OR.getProperty("weekPagination"))) {
					allpages.get(i).click();

				}
				List<WebElement> allrows = payRunWeekOneTable.findElements(By
						.xpath(OR.getProperty("WeekOneTablerows")));

				System.out.println("Total rows :" + allrows.size());
				for (int row = 1; row <= allrows.size(); row++) {
					TaxPayRun_For_Week1stat(MonthName);

				}
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}

	}

	public void TaxPayRun_For_Week1stat(String MonthName) throws Throwable {
		try {
			// Thread.sleep(4000L);

			WebElement payRunWeekOneTable = getObject("payRunWeekTable");
			List<WebElement> rows = payRunWeekOneTable.findElements(By.xpath(OR
					.getProperty("WeekOneTablerows")));
			int totalRows = rows.size();
			System.out.println("total no of week rows are " + totalRows);
			java.util.Iterator<WebElement> x = rows.iterator();

			rownum = 1;
			while (x.hasNext()) {

				weekOneRecordId = "//div[" + "5" + "]/" + "div[" + "1]/"
						+ "div/" + "div[" + "2]/" + "table/" + "tbody/tr["
						+ (rownum + 1) + "]/" + "th/" + "a";
				WebElement Week1 = driver
						.findElement(By.xpath(weekOneRecordId));
				if (existsWebElement(Week1)) {
					String weekText = Week1.getText();
					if (weekText != null
							&& weekText.equalsIgnoreCase(MonthName)) {
						System.out
								.println("The week name is Matched i.e. the week name is  :"
										+ weekText);
						Week1.sendKeys("");
						Week1.click();
						System.out.println("the weekname got clicked");
						// break;
					} else {
						rownum++;
					}

				}

			}

		} catch (Throwable t) {
			System.out.println("some problem after click of weekname");
			System.out.println(t.getMessage());

		}
	}

	//

	/**************************** Auto enrolment MONTHLY methods *********************************************/
	public void PayrollForMonthlyTax(String EmployerName, String EmpName,
			String Payrolid, String Frquency, String MonthName,
			String ExcelInputSheet, String FirstReportNameInApplication,
			String TestResultExcelFilePath, String worksheetNo,
			String PayrollView) throws Throwable {

		if (existsElement(OR.getProperty("payrollTab"))) {
			getObject("payrollTab").click();

		}
		Thread.sleep(5000L);

		if (existsElement(OR.getProperty("payrollViewLocator"))) {
			Select selectByValue = new Select(driver.findElement(By.xpath(OR
					.getProperty("payrollViewLocator"))));
			// This select by value needs to be called from OR.Properties
			selectByValue.selectByValue(PayrollView);// "Current"
		}
		Thread.sleep(8000L);
		/*
		 * This code clicks to pagination from 1 to last page till it finds the
		 * '2Weekly' pay run Once it finds the '2Weekly' payrun, it clicks to
		 * it. Hence the following code finds the '2Weekly' payrun automatically
		 * from pagination ProcessingTo2Weekly() method searches the required
		 * company name and payrun
		 */
		try {
			WebElement table = driver.findElement(By.xpath(OR
					.getProperty("payroll2weeklytable")));
			if (existsWebElement(table)) {
				List<WebElement> allpages = driver.findElements(By.xpath(OR
						.getProperty("paginationElement")));
				System.out.println("Total pages :" + allpages.size());
				//
				for (int i = 0; i <= (allpages.size()); i++) {

					if (existsElement(OR.getProperty("paginationElement"))) {
						allpages.get(i).click();
					}
					List<WebElement> allrows = table
							.findElements(By
									.xpath("//*[@id='j_id0:j_id3']/div[2]/div/table[2]/tbody/tr[1]"));

					for (int row = 1; row <= allrows.size(); row++) {

						ProcessingMonthlyTax(EmployerName, EmpName, Payrolid,
								Frquency, MonthName, ExcelInputSheet,
								FirstReportNameInApplication,
								TestResultExcelFilePath);

					}

					//

				}
			}

		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void ProcessingMonthlyTax(String EmployerName, String EmpName,
			String Payrolid, String Frquency, String MonthName,
			String ExcelInputSheet, String FirstReportNameInApplication,
			String TestResultExcelFilePath) throws Throwable {
		try {
			WebElement tableheader = getObject("payroll2weeklytable");
			List<WebElement> th = tableheader.findElements(By.tagName("th"));

			for (int i = 0; i < th.size(); i++) {
				if ("Payroll".equalsIgnoreCase(th.get(i).getText())) {
					payrollcol_position = i + 1;
					break;
				}
			}

			for (int j = 0; j < th.size(); j++) {
				if ("Employer".equalsIgnoreCase(th.get(j).getText())) {
					Emplpoyercol_position = j + 1;
					break;
				}

			}

			for (int k = 0; k < th.size(); k++) {
				if ("Frequency".equalsIgnoreCase(th.get(k).getText())) {
					frequencyCol_Postition = k + 1;
					break;

				}

			}

			WebElement niweeklyPayrollTable = getObject("payroll2weeklytable");
			if (existsWebElement(niweeklyPayrollTable)) {
				// WebTable table = WebTable.getTable(niweeklyPayrollTable);
				List<WebElement> rows = niweeklyPayrollTable.findElements(By
						.xpath(OR.getProperty("payroll2weeklytablerows")));
				java.util.Iterator<WebElement> x = rows.iterator();
				rownum = 1;

				while (x.hasNext()) {
					WebElement emr1 = driver.findElement(By.xpath("//table[2]/"
							+ "tbody/" + "tr" + "[" + (rownum + 1) +

							"]" + "/" + "td[" + Emplpoyercol_position + "]"));
					String empr = emr1.getText();

					WebElement ffr = driver.findElement(By.xpath("//table[2]/"
							+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/"
							+ "td[" + frequencyCol_Postition + "]"));
					String ffr1 = ffr.getText();

					WebElement ppr1 = driver.findElement(By.xpath("//table[2]/"
							+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/" +

							"td[" + payrollcol_position + "]"));
					String ppr = ppr1.getText();

					payrollRecordId = "//table[2]/" + "tbody/" + "tr" + "["
							+ (rownum + 1) + "]" + "/" + "td["
							+ payrollcol_position + "]" + "/" + "a";

					if (empr != null && empr.equalsIgnoreCase(EmployerName)
							&& ppr.equalsIgnoreCase(Payrolid)
							&& ffr1.equalsIgnoreCase(Frquency)) {
						System.out.println("payfrequency 'Monthly' matched");
						driver.findElement(By.xpath(payrollRecordId)).click();
						if (existsElement(OR.getProperty("compypayrolldetails"))) {
							String pfrequencey = getObject(
									"compypayrolldetails").getText();
							System.out.println("the payfrequency is :"
									+ pfrequencey);
							TaxPayRun_For_Month1AutoEnrol(MonthName);
							break;
						}
						break;
					} else {
						System.out.println("");
					}
					rownum++;
				}

			}

		} catch (Throwable t) {
			t.getMessage().toString();
			t.getStackTrace().toString();
		}

	}

	public void TaxPayRun_For_Month1AutoEnrol(String MonthName)
			throws Throwable {
		try {
			if (existsElement(OR.getProperty("payrunMonthlyTableAutoEnrolmt"))) {
				WebElement payRunWeekOneTable = getObject("payrunMonthlyTableAutoEnrolmt");
				if (existsWebElement(payRunWeekOneTable)) {

					List<WebElement> rows = payRunWeekOneTable
							.findElements(By.xpath(OR
									.getProperty("payrunMonthlyTableRowsAutoEnrolmt")));
					java.util.Iterator<WebElement> x = rows.iterator();

					rownum = 2;

					while (x.hasNext()) {
						System.out.println("ruwnum is :" + rownum);

						Thread.sleep(2000L);
						WebElement Month4 = driver.findElement(By
								.xpath("//div[" + "5" + "]/" + "div[" + "1]/"
										+ "div/" + "div[" + "2]/" + "table/"
										+ "tbody/tr[" + rownum + "]/" + "th/"
										+ "a"));
						// div[5]/div[1]/div/div[2]/table/tbody/tr[2]/th/a
						String MontName = Month4.getText();
						System.out.println("The month name is :" + MontName);
						if (MontName != null
								&& MontName.equalsIgnoreCase(MonthName)) {
							System.out.println("The month name is :"
									+ MonthName);
							Month4.sendKeys("");
							Month4.click();
							break;
						} else {
							System.out.println("payRun text  :" + MonthName
									+ " did not matched");
							rownum++;
						}

					}
					// rownum++;
				} else {
					driver.navigate().refresh();
				}

			}

		} catch (Throwable t) {
			System.out.println(t.getMessage());
			System.out.println(t.getStackTrace());

		}
	}

	//
	/*************************************************************************/
	// For AutoEnrolment employer2

	public void PayrollForMonthlyTaxForAutoEnrolEmployer2(String EmployerName,
			String EmpName, String Payrolid, String Frquency, String MonthName,
			String ExcelInputSheet, String FirstReportNameInApplication,
			String TestResultExcelFilePath, String PayrollVeiw)
			throws Throwable {

		if (existsElement(OR.getProperty("payrollTab"))) {
			getObject("payrollTab").click();

		}
		Thread.sleep(5000L);

		if (existsElement(OR.getProperty("payrollViewLocator"))) {
			Select selectByValue = new Select(driver.findElement(By.xpath(OR
					.getProperty("payrollViewLocator"))));
			// This select by value needs to be called from OR.Properties
			selectByValue.selectByValue(PayrollVeiw);
		}
		Thread.sleep(8000L);
		/*
		 * This code clicks to pagination from 1 to last page till it finds the
		 * '2Weekly' pay run Once it finds the '2Weekly' payrun, it clicks to
		 * it. Hence the following code finds the '2Weekly' payrun automatically
		 * from pagination ProcessingTo2Weekly() method searches the required
		 * company name and payrun
		 */
		try {
			WebElement table = driver.findElement(By.xpath(OR
					.getProperty("payroll2weeklytable")));
			if (existsWebElement(table)) {
				List<WebElement> allpages = driver.findElements(By.xpath(OR
						.getProperty("paginationElement")));
				System.out.println("Total pages :" + allpages.size());
				for (int i = 0; i <= (allpages.size()); i++) {
					if (existsElement(OR.getProperty("paginationElement"))) {
						allpages.get(i).click();
					}
					List<WebElement> allrows = table
							.findElements(By
									.xpath("//*[@id='j_id0:j_id3']/div[2]/div/table[2]/tbody/tr[1]"));
					// System.out.println("Total rows :" +allrows.size());
					for (int row = 1; row <= allrows.size(); row++) {

						ProcessingMonthlyTaxForAutoEnrolEmplyr2(EmployerName,
								Payrolid, MonthName);
					}
				}
			}

		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void ProcessingMonthlyTaxForAutoEnrolEmplyr2(String employerName,
			String PayrollId, String Name_Of_TheMonth) throws Throwable {
		try {
			WebElement tableheader = getObject("payroll2weeklytable");
			List<WebElement> th = tableheader.findElements(By.tagName("th"));

			for (int i = 0; i < th.size(); i++) {
				if ("Payroll".equalsIgnoreCase(th.get(i).getText())) {
					payrollcol_position = i + 1;
					break;
				}
			}

			for (int j = 0; j < th.size(); j++) {
				if ("Employer".equalsIgnoreCase(th.get(j).getText())) {
					Emplpoyercol_position = j + 1;
					break;
				}

			}

			for (int k = 0; k < th.size(); k++) {
				if ("Frequency".equalsIgnoreCase(th.get(k).getText())) {
					frequencyCol_Postition = k + 1;
					break;

				}

			}

			WebElement niweeklyPayrollTable = getObject("payroll2weeklytable");
			if (existsWebElement(niweeklyPayrollTable)) {
				// WebTable table = WebTable.getTable(niweeklyPayrollTable);
				List<WebElement> rows = niweeklyPayrollTable.findElements(By
						.xpath(OR.getProperty("payroll2weeklytablerows")));
				java.util.Iterator<WebElement> x = rows.iterator();
				rownum = 1;
				// System.out.println("rownum is  :" + rownum);
				while (x.hasNext()) {
					WebElement emr1 = driver.findElement(By.xpath("//table[2]/"
							+ "tbody/" + "tr" + "[" + (rownum + 1) +

							"]" + "/" + "td[" + Emplpoyercol_position + "]"));
					String empr = emr1.getText();

					WebElement ffr = driver.findElement(By.xpath("//table[2]/"
							+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/"
							+ "td[" + frequencyCol_Postition + "]"));
					String ffr1 = ffr.getText();

					WebElement ppr1 = driver.findElement(By.xpath("//table[2]/"
							+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/" +

							"td[" + payrollcol_position + "]"));
					String ppr = ppr1.getText();

					payrollRecordId = "//table[2]/" + "tbody/" + "tr" + "["
							+ (rownum + 1) + "]" + "/" + "td["
							+ payrollcol_position + "]" + "/" + "a";
					// employerName PayrollId
					if (empr != null && empr.equalsIgnoreCase(employerName)
							&& ppr.equalsIgnoreCase(PayrollId)
							&& ffr1.equalsIgnoreCase("Monthly")) {
						System.out
								.println("Finally the Employer name,Payfrequency 'Monthly' and required Payroll matched");
						driver.findElement(By.xpath(payrollRecordId)).click();
						if (existsElement(OR.getProperty("compypayrolldetails"))) {
							String pfrequencey = getObject(
									"compypayrolldetails").getText();
							System.out.println("the payfrequency is :"
									+ pfrequencey);
							// TaxPayRun_For_Month1AutoEnrol(Name_Of_TheMonth);
							TaxPayRun_For_Month1AutoEnrolForEmployer2(Name_Of_TheMonth);
							break;
						}
						break;
					} else {
						System.out.println("");
					}
					rownum++;
				}

			}

		} catch (Throwable t) {
			t.getMessage().toString();
			t.getStackTrace().toString();
		}

	}

	public void TaxPayRun_For_Month1AutoEnrolForEmployer2(
			String Name_Of_TheMonth) throws Throwable {
		try {
			if (existsElement(OR
					.getProperty("payrunMonthlyTableAutoEnrolmtEmployer2"))) {
				WebElement payRunWeekOneTable = getObject("payrunMonthlyTableAutoEnrolmtEmployer2");
				if (existsWebElement(payRunWeekOneTable)) {

					List<WebElement> rows = payRunWeekOneTable
							.findElements(By.xpath(OR
									.getProperty("payrunMonthlyTableRowsAutoEnrolmtEmployer2")));
					java.util.Iterator<WebElement> x = rows.iterator();

					rownum = 2;

					while (x.hasNext()) {
						System.out.println("ruwnum is :" + rownum);

						Thread.sleep(2000L);
						WebElement Month4 = driver.findElement(By
								.xpath("//div[" + "5" + "]/" + "div[" + "1]/"
										+ "div/" + "div[" + "2]/" + "table/"
										+ "tbody/tr[" + rownum + "]/" + "th/"
										+ "a"));
						// div[5]/div[1]/div/div[2]/table/tbody/tr[2]/th/a
						String MonthName = Month4.getText();
						System.out.println("The month name is :" + MonthName);
						if (MonthName != null
								&& MonthName.equalsIgnoreCase(Name_Of_TheMonth)) {
							System.out.println("The month name is :"
									+ MonthName);
							Month4.sendKeys("");
							Month4.click();
							break;
						} else {
							System.out
									.println("payRun text 'April-2016' did not matched");
							rownum++;
						}

					}
					rownum++;
				} else {
					driver.navigate().refresh();
				}

			}

		} catch (Throwable t) {
			System.out.println(t.getMessage());
			System.out.println(t.getStackTrace());

		}
	}

	/*********************************** Payroll Monthly Tax Methods ******************/

	public void PayrollForMonthlyTax(String Name_Of_TheMonth) throws Throwable {

		if (existsElement(OR.getProperty("payrollTab"))) {
			getObject("payrollTab").click();

		}
		Thread.sleep(5000L);

		if (existsElement(OR.getProperty("payrollViewLocator"))) {
			Select selectByValue = new Select(driver.findElement(By.xpath(OR
					.getProperty("payrollViewLocator"))));
			// This select by value needs to be called from OR.Properties
			selectByValue.selectByValue("All");
		}
		Thread.sleep(8000L);
		
		if (existsElementchkFor1mts(OR.getProperty("payrollSearchField")))
		{
			System.out.println("The payroll search field is displayed");
			getObject("payrollSearchField").sendKeys("");
			getObject("payrollSearchField").sendKeys(pn);
		}
		Thread.sleep(5000L);
		/*
		 * This code clicks to pagination from 1 to last page till it finds the
		 * '2Weekly' pay run Once it finds the '2Weekly' payrun, it clicks to
		 * it. Hence the following code finds the '2Weekly' payrun automatically
		 * from pagination ProcessingTo2Weekly() method searches the required
		 * company name and payrun
		 */
		try {
			WebElement table = driver.findElement(By.xpath(OR
					.getProperty("payroll2weeklytable")));
			if (existsWebElement(table)) {
				List<WebElement> allpages = driver.findElements(By.xpath(OR
						.getProperty("paginationElement")));
				System.out.println("Total pages :" + allpages.size());
				for (int i = 0; i <= (allpages.size()); i++) {
					if (existsElement(OR.getProperty("paginationElement"))) {
						allpages.get(i).click();
					}
					List<WebElement> allrows = table
							.findElements(By
									.xpath("//*[@id='j_id0:j_id3']/div[2]/div/table[2]/tbody/tr[1]"));
					// System.out.println("Total rows :" +allrows.size());
					for (int row = 1; row <= allrows.size(); row++) {

						ProcessingMonthlyTax(Name_Of_TheMonth);
					}
				}
			}

		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void ProcessingMonthlyTax(String Name_Of_TheMonth) throws Throwable {
		try {
			WebElement tableheader = getObject("payroll2weeklytable");
			List<WebElement> th = tableheader.findElements(By.tagName("th"));

			for (int i = 0; i < th.size(); i++) {
				if ("Payroll".equalsIgnoreCase(th.get(i).getText())) {
					payrollcol_position = i + 1;
					break;
				}
			}

			for (int j = 0; j < th.size(); j++) {
				if ("Employer".equalsIgnoreCase(th.get(j).getText())) {
					Emplpoyercol_position = j + 1;
					break;
				}

			}

			for (int k = 0; k < th.size(); k++) {
				if ("Frequency".equalsIgnoreCase(th.get(k).getText())) {
					frequencyCol_Postition = k + 1;
					break;

				}

			}

			WebElement niweeklyPayrollTable = getObject("payroll2weeklytable");
			if (existsWebElement(niweeklyPayrollTable)) {
				// WebTable table = WebTable.getTable(niweeklyPayrollTable);
				List<WebElement> rows = niweeklyPayrollTable.findElements(By
						.xpath(OR.getProperty("payroll2weeklytablerows")));
				java.util.Iterator<WebElement> x = rows.iterator();
				rownum = 1;
				// System.out.println("rownum is  :" + rownum);
				while (x.hasNext()) {
					WebElement emr1 = driver.findElement(By.xpath("//table[2]/"
							+ "tbody/" + "tr" + "[" + (rownum + 1) +

							"]" + "/" + "td[" + Emplpoyercol_position + "]"));
					String empr = emr1.getText();

					WebElement ffr = driver.findElement(By.xpath("//table[2]/"
							+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/"
							+ "td[" + frequencyCol_Postition + "]"));
					String ffr1 = ffr.getText();

					WebElement ppr1 = driver.findElement(By.xpath("//table[2]/"
							+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/" +

							"td[" + payrollcol_position + "]"));
					String ppr = ppr1.getText();

					payrollRecordId = "//table[2]/" + "tbody/" + "tr" + "["
							+ (rownum + 1) + "]" + "/" + "td["
							+ payrollcol_position + "]" + "/" + "a";

					if (empr != null
							&& empr.equalsIgnoreCase("DO NOT TOUCH PAYROLL AUTOMATION EMPLOYER")
							&& ppr.equalsIgnoreCase(pn)
							&& ffr1.equalsIgnoreCase("Monthly")) {
						System.out
								.println("Finally the Employer name,Payfrequency 'Monthly' and required Payroll matched");
						driver.findElement(By.xpath(payrollRecordId)).click();
						if (existsElement(OR
								.getProperty("twoweeklyPayrolldetails"))) {
							String pfrequencey = getObject(
									"twoweeklyPayrolldetails").getText();
							System.out.println("the payfrequency is :"
									+ pfrequencey);
							TaxPayRun_For_Month(Name_Of_TheMonth);
							break;
						}
						break;
					} else {
						System.out.println("");
					}
					rownum++;
				}

			}

		} catch (Throwable t) {
			t.getMessage().toString();
			t.getStackTrace().toString();
		}

	}

	public void TaxPayRun_For_Month(String Name_Of_TheMonth) throws Throwable {
		try {
			if (existsElement(OR.getProperty("payrollDetailsTableheader1"))) {
				WebElement tableheader = getObject("payrollDetailsTableheader1");
				// driver.findElement(By.xpath(OR.getProperty("payrollDetailsTableheader")));
				List<WebElement> th = tableheader
						.findElements(By.tagName("th"));

				for (int i = 1; i < th.size(); i++) {
					payrunMonth = null;
					payrunMonth = driver
							.findElement(
									By.xpath("//*[contains(@id, '_body')]/table/tbody/tr[1]/th["
											+ i + "]")).getText();
					System.out.println("THE Column name is :" + payrunMonth);
					if (payrunMonth.equalsIgnoreCase("Pay run")) {
						System.out.println("THE Column payrun matched :"
								+ payrunMonth);
						break;
					}

					if (existsElement(OR.getProperty("payrunMonthlyTable"))) {
						WebElement payRunWeekOneTable = getObject("payrunMonthlyTable");
						if (existsWebElement(payRunWeekOneTable)) {

							List<WebElement> rows = payRunWeekOneTable
									.findElements(By.xpath(OR
											.getProperty("payrunMonthlyTableRows")));
							java.util.Iterator<WebElement> x = rows.iterator();

							rownum = 2;

							while (x.hasNext()) {
								System.out.println("ruwnum is :" + rownum);

								Thread.sleep(2000L);
								WebElement Month = driver
										.findElement(By
												.xpath("//div[" + "5" + "]/"
														+ "div[" + "1]/"
														+ "div/" + "div["
														+ "2]/" + "table/" +

														"tbody/tr[" + rownum
														+ "]/th/a"));

								String MonthName = Month.getText();
								//
								if (MonthName != null
										&& MonthName
												.equalsIgnoreCase(Name_Of_TheMonth)) {
									System.out.println("The month name is :"
											+ MonthName);
									System.out
											.println("The month name is matched");
									Month.sendKeys("");
									Month.click();
									break;
								} else {
									System.out
											.println("payRun text 'April-2015' did not matched");
									rownum++;
								}

							}
							rownum++;
						}

					}
				}

			}
		} catch (Throwable t) {
			System.out.println(t.getMessage());
			System.out.println(t.getStackTrace());
		}
	}

	// Thread.sleep(4000L);

	/*
	 * if(existsElement(OR.getProperty("payrunMonthlyTable"))) { WebElement
	 * payRunWeekOneTable = getObject("payrunMonthlyTable");
	 * if(existsWebElement(payRunWeekOneTable)) {
	 * 
	 * List<WebElement> rows = payRunWeekOneTable .findElements(By
	 * .xpath(OR.getProperty("payrunMonthlyTableRows")));
	 * java.util.Iterator<WebElement> x = rows.iterator();
	 * 
	 * rownum = 2;
	 * 
	 * while (x.hasNext()) { System.out.println("ruwnum is :"+rownum);
	 * 
	 * Thread.sleep(2000L); WebElement Month = driver.findElement(By
	 * .xpath("//div[" + "5" + "]/" + "div[" + "1]/" + "div/"+ "div[" + "2]/" +
	 * "table/" +
	 * 
	 * "tbody/tr[" + rownum+ "]/" + "th["+payruncol_position+"]+/" + "a"));
	 * 
	 * String MonthName = Month.getText(); // if (MonthName != null &&
	 * MonthName.equalsIgnoreCase(Name_Of_TheMonth)) {
	 * System.out.println("The month name is :"+MonthName);
	 * System.out.println("The month name is matched"); Month.sendKeys("");
	 * Month.click(); break; } else {
	 * System.out.println("payRun text 'April-2015' did not matched"); rownum++;
	 * }
	 * 
	 * } rownum++; } else { driver.navigate().refresh(); }
	 * 
	 * }
	 */

	/*
	 * public void TaxPayRun_For_Month(String Name_Of_TheMonth) throws Throwable
	 * { try { //Thread.sleep(4000L);
	 * if(existsElement(OR.getProperty("payrunMonthlyTable"))) { WebElement
	 * payRunWeekOneTable = getObject("payrunMonthlyTable");
	 * if(existsWebElement(payRunWeekOneTable)) {
	 * 
	 * List<WebElement> rows = payRunWeekOneTable .findElements(By
	 * .xpath(OR.getProperty("payrunMonthlyTableRows")));
	 * java.util.Iterator<WebElement> x = rows.iterator();
	 * 
	 * rownum = 2;
	 * 
	 * while (x.hasNext()) { System.out.println("ruwnum is :"+rownum);
	 * 
	 * 
	 * Thread.sleep(2000L); WebElement Month4 = driver.findElement(By
	 * .xpath("//div[" + "5" + "]/" + "div[" + "1]/" + "div/"+ "div[" + "2]/" +
	 * "table/" + "tbody/tr[" + rownum+ "]/" + "th/" + "a"));
	 * 
	 * String MonthName = Month4.getText();
	 * //System.out.println("The month name is :"+MonthName); if (MonthName !=
	 * null && MonthName.equalsIgnoreCase(Name_Of_TheMonth)) {
	 * System.out.println("The month name is :"+MonthName); Month4.sendKeys("");
	 * Month4.click(); break; } else {
	 * System.out.println("payRun text 'April-2015' did not matched"); rownum++;
	 * }
	 * 
	 * } rownum++; } else { driver.navigate().refresh(); }
	 * 
	 * }
	 * 
	 * } catch(Throwable t) { System.out.println(t.getMessage());
	 * System.out.println(t.getStackTrace()); } }
	 */

	/*************************** Tax worksheet Monthly First script methods **************/

	/*
	 * Methods of GeneralTaxRateMonthly
	 * 
	 * hrms_payroll.IncomeTax_TCMnth1_204045VarblePayAnd50RL to
	 * hrms_payroll.IncomeTax_TCMnth12_204045VarblePayAnd50RL
	 */

	/************************* Tax payroll weekly methods ************************/

	public void PayrollForWeeklyTax(String Name_Of_TheWeek) throws Throwable {
		try {
			if (existsElement(OR.getProperty("payrollTab"))) {
				getObject("payrollTab").click();
			}
			Thread.sleep(5000L);

			if (existsElement(OR.getProperty("payrollViewLocator"))) {
				Select selectByValue = new Select(driver.findElement(By
						.xpath(OR.getProperty("payrollViewLocator"))));
				// This select by value needs to be called from OR.Properties
				selectByValue.selectByValue("All");
			}
			Thread.sleep(8000L);
			/*
			 * This code clicks to pagination from 1 to last page till it finds
			 * the '2Weekly' pay run Once it finds the '2Weekly' payrun, it
			 * clicks to it. Hence the following code finds the '2Weekly' payrun
			 * automatically from pagination ProcessingTo2Weekly() method
			 * searches the required company name and payrun
			 */
			try {
				WebElement table = driver.findElement(By.xpath(OR
						.getProperty("payroll2weeklytable")));
				List<WebElement> allpages = driver.findElements(By.xpath(OR
						.getProperty("paginationElement")));
				System.out.println("Total pages :" + allpages.size());
				for (int i = 0; i <= (allpages.size()); i++) {
					if (existsElement(OR.getProperty("paginationElement"))) {
						allpages.get(i).click();
					}
					List<WebElement> allrows = table
							.findElements(By
									.xpath("//*[@id='j_id0:j_id3']/div[2]/div/table[2]/tbody/tr[1]"));
					System.out.println("Total rows :" + allrows.size());
					for (int row = 1; row <= allrows.size(); row++) {
						ProcessForWeekTaxrate(Name_Of_TheWeek);
					}
				}
			} catch (Throwable t) {
				System.out.println(t.getMessage().toString());
				System.out.println(t.getStackTrace().toString());
			}

		}

		catch (Throwable t) {
			System.out.println(t.getMessage());
		}
	}

	public void ProcessForWeekTaxrate(String Name_Of_TheWeek) throws Throwable {

		try {
			WebElement tableheader = getObject("payroll2weeklytable");
			List<WebElement> th = tableheader.findElements(By.tagName("th"));

			for (int i = 0; i < th.size(); i++) {
				if ("Payroll".equalsIgnoreCase(th.get(i).getText())) {
					payrollcol_position = i + 1;
					break;
				}
			}

			for (int j = 0; j < th.size(); j++) {
				if ("Employer".equalsIgnoreCase(th.get(j).getText())) {
					Emplpoyercol_position = j + 1;
					break;
				}

			}

			for (int k = 0; k < th.size(); k++) {
				if ("Frequency".equalsIgnoreCase(th.get(k).getText())) {
					frequencyCol_Postition = k + 1;
					break;

				}

			}
			WebElement niweeklyPayrollTable = getObject("payrolltable");
			// WebTable table = WebTable.getTable(niweeklyPayrollTable);
			List<WebElement> rows = niweeklyPayrollTable.findElements(By
					.xpath(OR.getProperty("payrollTableRows")));
			java.util.Iterator<WebElement> x = rows.iterator();
			rownum = 1;

			while (x.hasNext()) {

				WebElement emr1 = driver.findElement(By.xpath("//table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) +

						"]" + "/" + "td[" + Emplpoyercol_position + "]"));
				String empr = emr1.getText();

				WebElement ffr = driver.findElement(By.xpath("//table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/"
						+ "td[" + frequencyCol_Postition + "]"));
				String ffr1 = ffr.getText();

				WebElement ppr1 = driver.findElement(By.xpath("//table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/" +

						"td[" + payrollcol_position + "]"));
				String ppr = ppr1.getText();

				payrollRecordId = "//table[2]/" + "tbody/" + "tr" + "["
						+ (rownum + 1) + "]" + "/" + "td["
						+ payrollcol_position + "]" + "/" + "a";
				if (empr != null
						&& empr.equalsIgnoreCase("DO NOT TOUCH PAYROLL AUTOMATION EMPLOYER")
						&& ppr.equalsIgnoreCase(pnWeek)
						&& ffr1.equalsIgnoreCase("Weekly")) {
					System.out
							.println("Finally the Employer name,Payfrequency 'Monthly' and required Payroll matched");

					driver.findElement(By.xpath(payrollRecordId)).click();
					System.out
							.println("The weekly payroll record link got clicked");

					Thread.sleep(8000L);

					if (existsElement(OR.getProperty("payrollMonthlydetails"))) {
						String pfrequencey = getObject("payrollMonthlydetails")
								.getText();
						System.out.println("the payfrequency is :"
								+ pfrequencey);

						TaxPayRun_For_Week(Name_Of_TheWeek);
						break;
					}
					break;
				} else {
					System.out.println("payfrequency not matched");
				}
				rownum++;

				System.out.println("");

			}

		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	/*
	 * public void ProcessForWeekTaxrate(String Name_Of_TheWeek) throws
	 * Throwable {
	 * 
	 * try { WebElement niweeklyPayrollTable = getObject("payrolltable");
	 * WebTable table = WebTable.getTable(niweeklyPayrollTable);
	 * List<WebElement> rows = niweeklyPayrollTable .findElements(By
	 * .xpath(OR.getProperty("payrollTableRows")));
	 * java.util.Iterator<WebElement> x = rows.iterator(); rownum = 1;
	 * 
	 * while (x.hasNext()) { System.out.println("rownum is  :" + rownum); String
	 * companyname = table.getTBody().getRow(rownum).getCell(0).getText();
	 * String payrollId = table.getTBody().getRow(rownum).getCell(3).getText();
	 * String payFrequency =
	 * table.getTBody().getRow(rownum).getCell(4).getText();
	 * System.out.println("frequency name is :"+payFrequency);
	 * 
	 * if
	 * (companyname.equalsIgnoreCase("DO NOT TOUCH PAYROLL AUTOMATION EMPLOYER")
	 * && payFrequency.equalsIgnoreCase("Weekly")&&
	 * payrollId.equalsIgnoreCase(pnWeek)) { payrollRecordId = "//table[2]/" +
	 * "tbody/" + "tr" + "[" +(rownum+1)+ "]" +"/"+" td"+"["+"4"+"]"+"/"+"a";
	 * System.out.println("Pay frequency WEEKLY matched");
	 * 
	 * driver.findElement(By.xpath(payrollRecordId)).click();
	 * Thread.sleep(8000L);
	 * 
	 * if (existsElement(OR.getProperty("payrollMonthlydetails"))) { String
	 * pfrequencey = getObject("payrollMonthlydetails").getText();
	 * System.out.println("the payfrequency is :" + pfrequencey);
	 * 
	 * TaxPayRun_For_Week(Name_Of_TheWeek); break; } break; } else {
	 * System.out.println("payfrequency not matched"); } rownum++;
	 * 
	 * System.out.println("");
	 * 
	 * }
	 * 
	 * 
	 * } catch(Throwable t) { System.out.println(t.getMessage().toString());
	 * System.out.println(t.getStackTrace().toString()); } }
	 */

	public void TaxPayRun_For_Week(String Name_Of_TheWeek) throws Throwable {
		try {
			Thread.sleep(4000L);
			if (existsElement(OR.getProperty("payRunWeekTable"))) {
				WebElement payRunWeekOneTable = getObject("payRunWeekTable");
				// WebElement Week1 = driver.findElement(By
				// .xpath(OR.getProperty("WeekOneLocator")));
				List<WebElement> rows = payRunWeekOneTable.findElements(By
						.xpath(OR.getProperty("WeekOneTablerows")));
				java.util.Iterator<WebElement> x = rows.iterator();
				System.out.println("total number of week records are :"
						+ rows.size());

				rownum = 2;
				while (x.hasNext()) {
					WebElement Weekrecord = driver.findElement(By
							.xpath("//div[" + "5" + "]/" + "div[" + "1]/"
									+ "div/" + "div[" + "2]/" + "table/"
									+ "tbody/tr[" + (rownum) + "]/" + "th/"
									+ "a"));
					// /div[5]/div[1]/div/div[2]/table/tbody/tr

					String weekText = Weekrecord.getText();

					if (weekText != null
							&& weekText.equalsIgnoreCase(Name_Of_TheWeek)) {
						System.out.println("The week name" + Name_Of_TheWeek
								+ " matched");
						Weekrecord.sendKeys("");
						Weekrecord.click();
						break;
					} else {
						System.out.println("payRun text " + Name_Of_TheWeek
								+ "did not matched");
						rownum++;
					}

				}

			}

		} catch (Throwable t) {
			System.out.println(t.getMessage());

		}
	}

	/******************************* PAYROLL NI Module first script methods ***********************/
	/*
	 * Here Annualsalary method is not being taken as it is common for
	 * Taxworksheets and NI Worksheets
	 */
	public void UpdateEmployeeNICategoryOld(String empName, String NICategory)
			throws Throwable {
		try {
			if (employeeFirsttimeView) {
				employeeFirsttimeView = false;
				getObject("PersonalTab").click();
				if (existsElement(OR.getProperty("PersonalText"))) {
					System.out.println("I am in personal page");
					if (existsElement(OR.getProperty("EmployeeView"))) {

						System.out.println("I recognised the Employee view");
						Select selectByValue = new Select(driver.findElement(By
								.xpath(OR.getProperty("EmployeeView"))));
						selectByValue
								.selectByVisibleText("DO NOT TOUCH PAYROLL AUTOMATION TESTING");
						Thread.sleep(2000L);
						if (existsElement(OR.getProperty("ViewGoButton"))) {
							getObject("ViewGoButton").sendKeys("");
							getObject("ViewGoButton").click();
							System.out.println("The Go button got clicked");

						}

						Thread.sleep(7000L);
					}

				}

			}

			WebElement tableheader = driver.findElement(By.xpath(OR
					.getProperty("PersonalAndCompensationHeadingTable")));

			List<WebElement> th = tableheader.findElements(By.tagName("td"));

			for (a = 0; a < th.size(); a++) {
				if ("Employee".equalsIgnoreCase(th.get(a).getText())) {
					empcolnum = a + 1;
					break;

				}

			}

			for (b = 0; b < th.size(); b++) {
				if ("NI category".equalsIgnoreCase(th.get(b).getText())) {
					niCategoryColumn = b + 1;
					break;

				}

			}

			WebElement postsTable = driver.findElement(By.xpath(OR
					.getProperty("firstRecordOfTaxCodecoulmnTable")));
			if (existsWebElement(postsTable)) {
				List<WebElement> rows = postsTable.findElements(By.xpath(OR
						.getProperty("firstRecordOfTaxCodecoulmnTableRows")));

				java.util.Iterator<WebElement> x = rows.iterator();
				rownum = 1;
				while (x.hasNext()) {
					try {
						Thread.sleep(2000L);
						String firstRowOfEmployeeColumn = "//div[" + rownum
								+ "]/table/tbody/tr/td" + "[" + empcolnum + "]"
								+ "/" + "div/a/span";
						if (existsElement(firstRowOfEmployeeColumn)) {
							WebElement tempElement = driver.findElement(By
									.xpath(firstRowOfEmployeeColumn));
							String tempEmp = tempElement.getText();
							// System.out.println(tempEmp+"-------"+empName+"------"+rownum);
							String firstRowOfTaxCode = "//div[" + rownum + "]"
									+ "/" + "table/" + "tbody/" + "tr/" + "td["
									+ niCategoryColumn + "]" + "/" + "div";
							if (tempEmp != null
									&& tempEmp.equalsIgnoreCase(empName)) {
								System.out.println("Employee name  :" + tempEmp
										+ "  matched ");
								Thread.sleep(2000L);
								if (existsElement(firstRowOfTaxCode)) {
									// String
									// firstRowOfTaxCode22="//div["+rownum+"]"+"/"+"table/"+"tbody/"+"tr/"+"td["+niCategoryColumn+"]"+"/"+"div";

									Actions action = new Actions(driver);
									action.doubleClick(
											driver.findElement(By
													.xpath(firstRowOfTaxCode)))
											.perform();
									action.moveToElement(
											getObject("InlineDropdown"))
											.perform();
									Thread.sleep(2000L);
									if (existsElement(OR
											.getProperty("InlineDropdown"))) {
										// Select selectByValue = new
										// Select(driver.findElement(By.xpath(OR.getProperty("InlineDropdown"))));
										// selectByValue.selectByVisibleText(NICategory);
										getObject("InlineDropdown")
												.sendKeys("");
										getObject("InlineDropdown").sendKeys(
												NICategory);
										System.out
												.println("Selected the NI Picklist item "
														+ NICategory);
										Thread.sleep(2000L);
										if (existsElement(OR
												.getProperty("InlineUpdateButn"))) {
											getObject("InlineUpdateButn")
													.click();
											System.out
													.println("The update button got clicked and NI Category got saved");
											break;
											// Thread.sleep(3000L);
										}

									}
								}
							}
						} else {
							System.out.println("");
							// System.out.println("Employee name not matched");
						}
						rownum++;
					} catch (Throwable t) {
						System.out.println(t.getMessage());
						System.out.println(t.getStackTrace().toString());
					}

				}

			}

		} catch (Throwable t) {
			APP_LOGS.debug(" Check for error in NI Category method");
			System.out.println(t.getStackTrace().toString());
			ErrorUtil.addVerificationFailure(t);
			System.out.println("");
		}

	}

	/*************************************** Directors first script methods *********************/

	/*
	 * Cease and recommense worksheet methods
	 */

	/*
	 * public void UpdateEmployeeNICategory(String empName,String
	 * NICategory,String DirectorsNIBasis,String DirectorSince) throws Throwable
	 * { try { if(employeeFirsttimeView) { employeeFirsttimeView = false;
	 * getObject("PersonalTab").click();
	 * if(existsElement(OR.getProperty("PersonalText"))) {
	 * System.out.println("I am in personal page");
	 * if(existsElement(OR.getProperty("EmployeeView"))) {
	 * 
	 * System.out.println("I recognised the Employee view"); Select
	 * selectByValue = new
	 * Select(driver.findElement(By.xpath(OR.getProperty("EmployeeView"))));
	 * selectByValue
	 * .selectByVisibleText("DO NOT TOUCH PAYROLL AUTOMATION TESTING");
	 * Thread.sleep(2000L); if(existsElement(OR.getProperty("ViewGoButton"))) {
	 * getObject("ViewGoButton").sendKeys("");
	 * getObject("ViewGoButton").click(); }
	 * 
	 * Thread.sleep(7000L); }
	 * 
	 * }
	 * 
	 * } Thread.sleep(7000L); Row_count =
	 * driver.findElements(By.xpath("//div[@id='ext-gen11']/div/table/tbody/tr"
	 * )).size(); WebElement postsTable =
	 * driver.findElement(By.xpath(OR.getProperty
	 * ("firstRecordOfNIcoulmnTable"))); if(existsWebElement(postsTable)) {
	 * 
	 * List<WebElement> rows = postsTable.findElements(By.xpath(OR.getProperty(
	 * "firstRecordOfNIcoulmnTableRows")));
	 * 
	 * java.util.Iterator<WebElement> x = rows.iterator(); rownumNI = 1;
	 * while(x.hasNext()) { String firstRowOfEmployeeColumn="//div["+rownumNI+
	 * "]/table/tbody/tr/td[4]/div/a/span";
	 * if(existsElement(firstRowOfEmployeeColumn)) { WebElement firstEmployee=
	 * driver.findElement(By.xpath(firstRowOfEmployeeColumn)); String AppnEmp=
	 * firstEmployee.getText();
	 * //System.out.println(tempEmp+"-------"+empName+"------"+rownum);
	 * if(AppnEmp!=null && AppnEmp.equalsIgnoreCase(empName)) {
	 * System.out.println("Employee matched"); Thread.sleep(3000L); String
	 * firstRowOfNIColumn
	 * ="//div["+rownumNI+"]"+"/"+"table/"+"tbody/"+"tr/"+"td["+"6]"+"/"+"div";
	 * if(existsElement(firstRowOfNIColumn)) { String rowNumberOfNIColumn =
	 * "//div["+rownumNI+"]"+"/"+"table/"+"tbody/"+"tr/"+"td["+"6]"+"/"+"div";
	 * Actions action = new Actions(driver);
	 * action.doubleClick(driver.findElement
	 * (By.xpath(rowNumberOfNIColumn))).perform();
	 * action.moveToElement(getObject("InlineDropdown")).perform();
	 * Thread.sleep(2000L); if(existsElement(OR.getProperty("InlineDropdown")))
	 * { getObject("InlineDropdown").sendKeys("");
	 * getObject("InlineDropdown").sendKeys(NICategory);
	 * System.out.println("Selected the NI Picklist item "+NICategory);
	 * Thread.sleep(2000L);
	 * if(existsElement(OR.getProperty("InlineUpdateButn"))) {
	 * getObject("InlineUpdateButn").click();
	 * System.out.println("The update button got clicked and NI Category got saved"
	 * );
	 * 
	 * }
	 * 
	 * }
	 * 
	 * }
	 * UpdateDirectorsNIBasis(empName,NICategory,DirectorsNIBasis,DirectorSince
	 * ); Thread.sleep(3000L);
	 * UpdateDirectorsSince(empName,NICategory,DirectorsNIBasis,DirectorSince);
	 * 
	 * break; }
	 * 
	 * } else { System.out.println("");
	 * //System.out.println("Employee name not matched"); } rownumNI++; }
	 * 
	 * }
	 * 
	 * } catch(Throwable t) {
	 * APP_LOGS.debug(" Check for error in NI Category method");
	 * System.out.println(t.getStackTrace().toString());
	 * ErrorUtil.addVerificationFailure(t); System.out.println(""); }
	 * 
	 * }
	 */

	// ////

	public void UpdateEmployeeNICategoryOld(String empName, String NICategory,
			String DirectorsNIBasis, String DirectorSince) throws Throwable {
		try {
			if (employeeFirsttimeView) {
				employeeFirsttimeView = false;
				getObject("PersonalTab").click();
				if (existsElement(OR.getProperty("PersonalText"))) {
					System.out.println("I am in personal page");
					if (existsElement(OR.getProperty("EmployeeView"))) {

						System.out.println("I recognised the Employee view");
						Select selectByValue = new Select(driver.findElement(By
								.xpath(OR.getProperty("EmployeeView"))));
						selectByValue
								.selectByVisibleText("DO NOT TOUCH PAYROLL AUTOMATION TESTING");
						Thread.sleep(2000L);
						if (existsElement(OR.getProperty("ViewGoButton"))) {
							getObject("ViewGoButton").sendKeys("");
							getObject("ViewGoButton").click();
						}

						Thread.sleep(7000L);
					}

				}

			}

			WebElement tableheader = driver.findElement(By.xpath(OR
					.getProperty("PersonalAndCompensationHeadingTable")));

			List<WebElement> th = tableheader.findElements(By.tagName("td"));
			System.out.println("recognised the header");
			for (a = 0; a < th.size(); a++) {
				if ("Employee".equalsIgnoreCase(th.get(a).getText())) {
					System.out.println("employee");
					empcolnum = a + 1;
					break;

				}

			}

			for (b = 0; b < th.size(); b++) {
				if ("NI category".equalsIgnoreCase(th.get(b).getText())) {
					System.out.println("ni category");
					niCategoryColumn = b + 1;
					break;

				}

			}

			for (d = 0; d < th.size(); d++) {
				if ("Director's NI basis".equalsIgnoreCase(th.get(d).getText())) {
					System.out.println("nibasis");
					directorNIBasis = d + 1;
					break;

				}

			}

			for (e = 0; e < th.size(); e++) {
				if ("Director since".equalsIgnoreCase(th.get(e).getText())) {
					System.out.println("directory since");
					directorSince = e + 1;
					break;

				}

			}

			WebElement postsTable = driver.findElement(By.xpath(OR
					.getProperty("firstRecordOfTaxCodecoulmnTable")));
			if (existsWebElement(postsTable)) {
				List<WebElement> rows = postsTable.findElements(By.xpath(OR
						.getProperty("firstRecordOfTaxCodecoulmnTableRows")));

				java.util.Iterator<WebElement> x = rows.iterator();
				rownum = 1;
				while (x.hasNext()) {

					try {
						Thread.sleep(2000L);
						String firstRowOfEmployeeColumn = "//div[" + rownum
								+ "]/table/tbody/tr/td" + "[" + empcolnum + "]"
								+ "/" + "div/a/span";
						// div/div[1]/table/tbody/tr/td[4]/div/a/span
						if (existsElement(firstRowOfEmployeeColumn)) {
							WebElement tempElement = driver.findElement(By
									.xpath(firstRowOfEmployeeColumn));
							String tempEmp = tempElement.getText();
							// System.out.println(tempEmp+"-------"+empName+"------"+rownum);
							String firstRowOfTaxCode = "//div[" + rownum + "]"
									+ "/" + "table/" + "tbody/" + "tr/" + "td["
									+ niCategoryColumn + "]" + "/" + "div";

							if (tempEmp != null
									&& tempEmp.equalsIgnoreCase(empName)) {
								System.out.println("Employee name  :" + tempEmp
										+ "  matched ");
								Thread.sleep(2000L);
								if (existsElement(firstRowOfTaxCode)) {
									// String
									// firstRowOfTaxCode22="//div["+rownum+"]"+"/"+"table/"+"tbody/"+"tr/"+"td["+niCategoryColumn+"]"+"/"+"div";

									Actions action = new Actions(driver);
									action.doubleClick(
											driver.findElement(By
													.xpath(firstRowOfTaxCode)))
											.perform();
									action.moveToElement(
											getObject("InlineDropdown"))
											.perform();
									Thread.sleep(2000L);
									if (existsElement(OR
											.getProperty("InlineDropdown"))) {
										// Select selectByValue = new
										// Select(driver.findElement(By.xpath(OR.getProperty("InlineDropdown"))));
										// selectByValue.selectByVisibleText(NICategory);
										getObject("InlineDropdown")
												.sendKeys("");
										getObject("InlineDropdown").sendKeys(
												NICategory);
										System.out
												.println("Selected the NI Picklist item "
														+ NICategory);
										Thread.sleep(2000L);
										if (existsElement(OR
												.getProperty("InlineUpdateButn"))) {
											getObject("InlineUpdateButn")
													.click();
											System.out
													.println("The update button got clicked and NI Category got saved");
											// break;
											// Thread.sleep(3000L);
										}

									}

								}

								UpdateDirectorsNIBasisOld(empName, NICategory,
										DirectorsNIBasis, DirectorSince);
								Thread.sleep(3000L);
								UpdateDirectorsSinceOld(empName, NICategory,
										DirectorsNIBasis, DirectorSince);

								break;
							}

						} else {
							System.out.println("");
							// System.out.println("Employee name not matched");
						}
						rownum++;
					} catch (Throwable t) {
						APP_LOGS.debug(" Check for error in NI Category method");
						System.out.println(t.getStackTrace().toString());
						ErrorUtil.addVerificationFailure(t);
						System.out.println("");
					}

				}

			}
		} catch (Throwable t) {
			APP_LOGS.debug(" Check for error in NI Category method");
			System.out.println(t.getStackTrace().toString());
			ErrorUtil.addVerificationFailure(t);
			System.out.println("");
		}

	}

	// ////

	public void UpdateDirectorsNIBasisOld(String epName, String NICat,
			String DIBasis, String DtorSince) throws Throwable {
		try {
			Thread.sleep(3000L);
			String firstRowOfDirtorsNIbasisColumn = "//div[" + rownum + "]"
					+ "/" + "table/" + "tbody/" + "tr/" + "td["
					+ directorNIBasis + "]" + "/" + "div";
			// String
			// firstRowOfTaxCode="//div["+rownum+"]"+"/"+"table/"+"tbody/"+"tr/"+"td["+niCategoryColumn+"]"+"/"+"div";

			if (existsElement(firstRowOfDirtorsNIbasisColumn)) {
				// String rowNumberOfDirctrNIbsisColumn =
				// "//div["+rownumNI+"]"+"/"+"table/"+"tbody/"+"tr/"+"td["+"9]"+"/"+"div";
				Actions action3a = new Actions(driver);
				action3a.doubleClick(
						driver.findElement(By
								.xpath(firstRowOfDirtorsNIbasisColumn)))
						.perform();
				action3a.moveToElement(getObject("InlineDropdown")).perform();
				Thread.sleep(2000L);
				if (existsElement(OR.getProperty("InlineDropdown"))) {
					getObject("InlineDropdown").sendKeys("");
					getObject("InlineDropdown").sendKeys(DIBasis);
					System.out.println("Selected the DI Basis item " + DIBasis);
					Thread.sleep(2000L);
					if (existsElement(OR.getProperty("InlineUpdateButn"))) {
						getObject("InlineUpdateButn").click();
						System.out
								.println("updated DirectorsNI Basis successfully");
						// break;
						// Thread.sleep(3000L);
					}

				}

				/*
				 * if(existsElement(OR.getProperty("InlineDropdown"))) {
				 * getObject("InlineDropdown").sendKeys(DIBasis);
				 * Thread.sleep(2000L); getObject("InlineUpdateButn").click();
				 * Thread.sleep(6000L);
				 * System.out.println("updated DirectorsNI Basis successfully");
				 * }
				 */

			}

		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void UpdateDirectorsSinceOld(String epName, String NICat,
			String DIBasis, String DtorSince) throws Throwable {
		try {
			Thread.sleep(3000L);
			String firstRowOfDirtorsSinceColumn = "//div[" + rownum + "]" + "/"
					+ "table/" + "tbody/" + "tr/" + "td[" + directorSince + "]"
					+ "/" + "div";
			// String
			// firstRowOfDirtorsNIbasisColumn="//div["+rownumNI+"]"+"/"+"table/"+"tbody/"+"tr/"+"td["+directorSince+"]"+"/"+"div";

			if (existsElement(firstRowOfDirtorsSinceColumn)) {
				// String rowNumberOfDirctrSinceColumn =
				// "//div["+rownumNI+"]"+"/"+"table/"+"tbody/"+"tr/"+"td["+"10]"+"/"+"div";
				Actions action4a = new Actions(driver);
				action4a.doubleClick(
						driver.findElement(By
								.xpath(firstRowOfDirtorsSinceColumn)))
						.perform();
				action4a.moveToElement(getObject("directorsincetxtfild"))
						.perform();
				Thread.sleep(1000L);

				getObject("directorsincetxtfild").sendKeys("");
				getObject("directorsincetxtfild").clear();
				String dateStr = DtorSince;
				DateFormat readFormat = new SimpleDateFormat("MM/dd/yyyy");
				DateFormat writeFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date date = null;
				try {
					date = readFormat.parse(dateStr.trim());
					System.out.println(date.toString());
				} catch (ParseException e) {
					System.out.println(e.getMessage());
				}

				String formattedDate = null;
				if (date != null) {
					formattedDate = writeFormat.format(date);
				}
				System.out.println("The entered date is  " + formattedDate);
				Thread.sleep(4000L);
				getObject("directorsincetxtfild").sendKeys(formattedDate);
				getObject("outersideclk").click();

				Thread.sleep(1000L);
				getObject("drsinceupdatebttn").click();
				Thread.sleep(6000L);
				System.out.println("updated DirectorsSince successfully");

			}

		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	/********************** Director reaches and pension **********************/

	public void UpdateEmployeeNICategoryOld(String empName, String NICategory,
			String DirectorsNIBasis, String DirectorSince, String DateOfBirth)
			throws Throwable {
		try {
			if (employeeFirsttimeView) {
				employeeFirsttimeView = false;
				getObject("PersonalTab").click();
				if (existsElement(OR.getProperty("PersonalText"))) {
					System.out.println("I am in personal page");
					if (existsElement(OR.getProperty("EmployeeView"))) {

						System.out.println("I recognised the Employee view");

						Select selectByValue = new Select(driver.findElement(By
								.xpath(OR.getProperty("EmployeeView"))));
						selectByValue
								.selectByVisibleText("DO NOT TOUCH PAYROLL AUTOMATION TESTING");
						Thread.sleep(2000L);
						getObject("ViewGoButton").sendKeys("");
						getObject("ViewGoButton").click();

						Thread.sleep(7000L);
					}

				}

			}

			Row_count = driver.findElements(
					By.xpath("//div[@id='ext-gen11']/div/table/tbody/tr"))
					.size();
			WebElement postsTable = driver.findElement(By.xpath(OR
					.getProperty("firstRecordOfNIcoulmnTable")));
			if (existsWebElement(postsTable)) {
				List<WebElement> rows = postsTable.findElements(By.xpath(OR
						.getProperty("firstRecordOfNIcoulmnTableRows")));

				java.util.Iterator<WebElement> x = rows.iterator();
				rownumNI = 1;
				while (x.hasNext()) {
					String firstRowOfEmployeeColumn = "//div[" + rownumNI
							+ "]/table/tbody/tr/td[4]/div/a/span";
					if (existsElement(firstRowOfEmployeeColumn)) {
						WebElement firstEmployee = driver.findElement(By
								.xpath(firstRowOfEmployeeColumn));
						String AppnEmp = firstEmployee.getText();
						// System.out.println(tempEmp+"-------"+empName+"------"+rownum);
						if (AppnEmp != null
								&& AppnEmp.equalsIgnoreCase(empName)) {
							System.out.println("Employee matched");
							Thread.sleep(3000L);
							String firstRowOfNIColumn = "//div[" + rownumNI
									+ "]" + "/" + "table/" + "tbody/" + "tr/"
									+ "td[" + "6]" + "/" + "div";
							if (existsElement(firstRowOfNIColumn)) {
								String rowNumberOfNIColumn = "//div["
										+ rownumNI + "]" + "/" + "table/"
										+ "tbody/" + "tr/" + "td[" + "6]" + "/"
										+ "div";
								Actions action = new Actions(driver);
								action.doubleClick(
										driver.findElement(By
												.xpath(rowNumberOfNIColumn)))
										.perform();
								action.moveToElement(
										getObject("InlineDropdown")).perform();
								Thread.sleep(2000L);
								if (existsElement(OR
										.getProperty("InlineDropdown"))) {
									getObject("InlineDropdown").sendKeys(
											NICategory);
									Thread.sleep(2000L);
									getObject("InlineUpdateButn").click();
									System.out
											.println("updated NI Category successfully");
									Thread.sleep(6000L);

								}

							}
							UpdateDirectorsNIBasisOld(empName, NICategory,
									DirectorsNIBasis, DirectorSince,
									DateOfBirth);
							Thread.sleep(3000L);
							UpdateDirectorsSinceOld(empName, NICategory,
									DirectorsNIBasis, DirectorSince,
									DateOfBirth);
							Thread.sleep(3000L);
							DateofBirthOld(empName, NICategory,
									DirectorsNIBasis, DirectorSince,
									DateOfBirth);
							break;

						}

					} else {
						System.out.println("");
						// System.out.println("Employee name not matched");
					}
					rownumNI++;
				}

			}

		} catch (Throwable t) {
			APP_LOGS.debug(" Check for error in NI Category method");
			System.out.println(t.getStackTrace().toString());
			ErrorUtil.addVerificationFailure(t);
			System.out.println("");
		}

	}

	public void UpdateDirectorsNIBasisOld(String epName, String NICat,
			String DIBasis, String DtorSince, String DateOfBirth)
			throws Throwable {
		try {
			Thread.sleep(3000L);
			String firstRowOfDirtorsNIbasisColumn = "//div[" + rownumNI + "]"
					+ "/" + "table/" + "tbody/" + "tr/" + "td[" + "9]" + "/"
					+ "div";
			if (existsElement(firstRowOfDirtorsNIbasisColumn)) {
				String rowNumberOfDirctrNIbsisColumn = "//div[" + rownumNI
						+ "]" + "/" + "table/" + "tbody/" + "tr/" + "td["
						+ "9]" + "/" + "div";
				Actions action3a = new Actions(driver);
				action3a.doubleClick(
						driver.findElement(By
								.xpath(rowNumberOfDirctrNIbsisColumn)))
						.perform();
				action3a.moveToElement(getObject("InlineDropdown")).perform();
				Thread.sleep(2000L);
				if (existsElement(OR.getProperty("InlineDropdown"))) {
					getObject("InlineDropdown").sendKeys(DIBasis);
					Thread.sleep(2000L);
					getObject("InlineUpdateButn").click();
					Thread.sleep(6000L);
					System.out
							.println("updated DirectorsNI Basis successfully");
				}
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void UpdateDirectorsSinceOld(String epName, String NICat,
			String DIBasis, String DtorSince, String DateOfBirth)
			throws Throwable {
		try {
			Thread.sleep(3000L);
			String firstRowOfDirtorsSinceColumn = "//div[" + rownumNI + "]"
					+ "/" + "table/" + "tbody/" + "tr/" + "td[" + "10]" + "/"
					+ "div";
			if (existsElement(firstRowOfDirtorsSinceColumn)) {
				String rowNumberOfDirctrSinceColumn = "//div[" + rownumNI + "]"
						+ "/" + "table/" + "tbody/" + "tr/" + "td[" + "10]"
						+ "/" + "div";
				Actions action4a = new Actions(driver);
				action4a.doubleClick(
						driver.findElement(By
								.xpath(rowNumberOfDirctrSinceColumn)))
						.perform();
				action4a.moveToElement(getObject("directorsincetxtfild"))
						.perform();
				Thread.sleep(1000L);

				getObject("directorsincetxtfild").sendKeys("");
				String dateStr = DtorSince;
				DateFormat readFormat = new SimpleDateFormat("MM/dd/yyyy");
				DateFormat writeFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date date = null;
				try {
					date = readFormat.parse(dateStr.trim());
					System.out.println(date.toString());
				} catch (ParseException e) {
					System.out.println(e.getMessage());
				}

				String formattedDate = null;
				if (date != null) {
					formattedDate = writeFormat.format(date);
				}
				System.out.println("The entered date is  " + formattedDate);
				Thread.sleep(4000L);
				getObject("directorsincetxtfild").sendKeys(formattedDate);
				getObject("outersideclk").click();

				Thread.sleep(1000L);
				getObject("drsinceupdatebttn").click();
				Thread.sleep(6000L);
				System.out.println("updated DirectorsSince successfully");

			}

		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void DateofBirthOld(String epName, String NICat, String DIBasis,
			String DtorSince, String DateOfBirth) throws Throwable {
		try {
			Thread.sleep(3000L);
			String firstRowOfDirtorsSinceColumn = "//div[" + rownumNI + "]"
					+ "/" + "table/" + "tbody/" + "tr/" + "td[" + "11]" + "/"
					+ "div";
			if (existsElement(firstRowOfDirtorsSinceColumn)) {
				String rowNumberOfDirctrSinceColumn = "//div[" + rownumNI + "]"
						+ "/" + "table/" + "tbody/" + "tr/" + "td[" + "11]"
						+ "/" + "div";

				Actions action5a = new Actions(driver);
				action5a.doubleClick(
						driver.findElement(By
								.xpath(rowNumberOfDirctrSinceColumn)))
						.perform();
				action5a.moveToElement(getObject("directorsincetxtfild"))
						.perform();
				Thread.sleep(1000L);

				getObject("directorsincetxtfild").sendKeys("");
				String dateStr = DateOfBirth;
				DateFormat readFormat = new SimpleDateFormat("MM/dd/yyyy");
				DateFormat writeFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date date = null;
				try {
					date = readFormat.parse(dateStr.trim());
					System.out.println(date.toString());
				} catch (ParseException e) {
					System.out.println(e.getMessage());
				}

				String formattedDate = null;
				if (date != null) {
					formattedDate = writeFormat.format(date);
				}
				System.out.println("The entered date is  " + formattedDate);
				Thread.sleep(4000L);
				getObject("directorsincetxtfild").sendKeys(formattedDate);
				getObject("outersideclk").click();
				Thread.sleep(2000L);
				getObject("drsinceupdatebttn").click();
				Thread.sleep(6000L);
				System.out.println("updated DirectorsSince successfully");
			}

		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	/*************************************** Color Coding method relating to Report script ******************/

	public void colorCodeTestRemarks(int sheetNo) throws Throwable {
		try {
			// File excel = new
			// File("C:\\Users\\Admin\\AutomationXCD\\Updated Income tax calculation script creation scenarios.xlsx");
			File excel = new File(
					"F:\\Automation NI Reports\\HMRCTestData\\Updated Income tax calculation script creation scenarios.xlsx");
			FileInputStream fis = new FileInputStream(excel);
			org.apache.poi.ss.usermodel.Workbook wb = WorkbookFactory
					.create(fis);
			org.apache.poi.ss.usermodel.Sheet ws = wb.getSheetAt(sheetNo);

		} catch (Throwable t) {

		}
	}

	// getReportName
	public String getReportNamee(String NameOfReprt) {
		String rpName = null;
		if ((NameOfReprt).equalsIgnoreCase(TaxReport)) {
			rpName = "TaxReport";
		} else if ((NameOfReprt).equalsIgnoreCase(NIReport)) {
			rpName = "NIReport";
		} else if ((NameOfReprt).equalsIgnoreCase(DirAsEmployee)) {
			rpName = "DirAsEmployee";
		} else if ((NameOfReprt).equalsIgnoreCase(DirAsProRata)) {
			rpName = "DirAsProRata";
		} else if ((NameOfReprt).equalsIgnoreCase(CeaseAndRecommence)) {
			rpName = "CeaseAndRecommence";
		} else if ((NameOfReprt).equalsIgnoreCase(ReachesPensionAge)) {
			rpName = "ReachesPensionAge";
		} else if ((NameOfReprt).equalsIgnoreCase(Deferment)) {
			rpName = "Deferment";
		} else if ((NameOfReprt).equalsIgnoreCase(AtoD)) {
			rpName = "AtoD";
		} else if ((NameOfReprt).equalsIgnoreCase(Under21)) {
			rpName = "Under21";
		} else if ((NameOfReprt).equalsIgnoreCase(SMP1stReport)) {
			rpName = "SMP1stReport";
		} else if ((NameOfReprt).equalsIgnoreCase(SMP2ndReport)) {
			rpName = "SMP2ndReport";
		} else if ((NameOfReprt).equalsIgnoreCase(SMP3dReport)) {
			rpName = "SMP3dReport";
		} else if ((NameOfReprt).equalsIgnoreCase(SAP1stReport)) {
			rpName = "SAP1stReport";
		} else if ((NameOfReprt).equalsIgnoreCase(SAP2ndReport)) {
			rpName = "SAP2ndReport";
		} else if ((NameOfReprt).equalsIgnoreCase(SAP3dReport)) {
			rpName = "SAP3dReport";
		} else if ((NameOfReprt).equalsIgnoreCase(SSP1stReport)) {
			rpName = "SSP1stReport";
		} else if ((NameOfReprt).equalsIgnoreCase(SSP2ndReport)) {
			rpName = "SSP2ndReport";
		} else if ((NameOfReprt).equalsIgnoreCase(SSP3dReport)) {
			rpName = "SSP3dReport";
		} else if ((NameOfReprt).equalsIgnoreCase(SPP1stReport)) {
			rpName = "SPP1stReport";
		} else if ((NameOfReprt).equalsIgnoreCase(SPP2ndReport)) {
			rpName = "SPP2ndReport";
		} else if ((NameOfReprt).equalsIgnoreCase(SPP3dReport)) {
			rpName = "SPP3dReport";
		} else if ((NameOfReprt).equalsIgnoreCase(SPPCase2_1stReport)) {
			rpName = "SPPCase2_1stReport";
		} else if ((NameOfReprt).equalsIgnoreCase(SPPCase2_2ndReport)) {
			rpName = "SPPCase2_2ndReport";
		} else if ((NameOfReprt).equalsIgnoreCase(SPPCase2_3dReport)) {
			rpName = "SPPCase2_3dReport";
		} else if ((NameOfReprt).equalsIgnoreCase(SAPP_1stReport)) {
			rpName = "SAPP_1stReport";
		} else if ((NameOfReprt).equalsIgnoreCase(SAPP_2ndReport)) {
			rpName = "SAPP_2ndReport";
		} else if ((NameOfReprt).equalsIgnoreCase(SAPP_3dReport)) {
			rpName = "SAPP_3dReport";
		} else if ((NameOfReprt).equalsIgnoreCase(ShPP_1stReport)) {
			rpName = "ShPP_1stReport";
		} else if ((NameOfReprt).equalsIgnoreCase(ShPP_2ndReport)) {
			rpName = "ShPP_2ndReport";
		} else if ((NameOfReprt).equalsIgnoreCase(ShPP_3dReport)) {
			rpName = "ShPP_3dReport";
		} else if ((NameOfReprt).equalsIgnoreCase(SSP4thReport)) {
			rpName = "SSP4thReport";
		} else if ((NameOfReprt).equalsIgnoreCase(AutoEnrol_StarterReport)) {
			rpName = "AutoEnrol_StarterReport";
		} else if ((NameOfReprt).equalsIgnoreCase(Deferment201718)) {
			rpName = "Deferment201718";
		} else if ((NameOfReprt).equalsIgnoreCase(Under21201718)) {
			rpName = "Under21201718";
		} else if ((NameOfReprt).equalsIgnoreCase(U25Aprentice201718)) {
			rpName = "U25Aprentice201718";

		} else if ((NameOfReprt).equalsIgnoreCase(PayrollRTI_RecognitionReport)) {
			rpName = "PayrollRTI_RecognitionReport";
		} else if ((NameOfReprt)
				.equalsIgnoreCase(PayrollRTI_RecognitionS2Report)) {
			rpName = "PayrollRTI_RecognitionS2Report";
		} else if ((NameOfReprt)
				.equalsIgnoreCase(PayrollRTI_RecognitionS3Report)) {
			rpName = "PayrollRTI_RecognitionS3Report";
		} else if ((NameOfReprt)
				.equalsIgnoreCase(PayrollRTI_RecognitionS4Report)) {
			rpName = "PayrollRTI_RecognitionS4Report";
		}

		else if ((NameOfReprt).equalsIgnoreCase(PayrollRTI_RecognitionS5Report)) {
			rpName = "PayrollRTI_RecognitionS5Report";
		} else if ((NameOfReprt)
				.equalsIgnoreCase(PayrollRTI_RecognitionS6Report)) {
			rpName = "PayrollRTI_RecognitionS6Report";
		}

		else if ((NameOfReprt).equalsIgnoreCase(PayrollRTI_RecognitionS5Report)) {
			rpName = "PayrollRTI_RecognitionS5Report";
		}

		else if ((NameOfReprt)
				.equalsIgnoreCase(PayrollRTI_RecognitionS5Report_ReJoin)) {
			rpName = "PayrollRTI_RecognitionS5Report_ReJoin";
		}

		else if ((NameOfReprt).equalsIgnoreCase(PayrollRTI_RecognitionS7Report)) {
			rpName = "PayrollRTI_RecognitionS7Report";
		}

		else if ((NameOfReprt)
				.equalsIgnoreCase(PayrollRTI_RecognitionS7Report_JuneToMarch)) {
			rpName = "PayrollRTI_RecognitionS7Report_JuneToMarch";
		}
		return rpName;
	}

	public void payRunExecution() throws Throwable {
		try {
			if (existsElementchkFor1mts(OR.getProperty("progressBar"))) {
				System.out
						.println("Still generate draft payroll functionality execution did not completed...please wait.");
				payRunExecution();
			} else {
				System.out.println("progress bar is not being seen now..");
				System.out
						.println("Finally  generate draft payroll functionality execution completed successfully");
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void clickToGenerateDraftOnceMore() throws Throwable {
		try {
			if (existsElementchkFor5mts(OR.getProperty("genratedraftPayroll"))) {
				getObject("genratedraftPayroll").click();
				System.out.println("");
				System.out.println("The generate draft button got clicked");
				Thread.sleep(4000L);
				if (existsElementchkFor5mts(OR.getProperty("progressBarImage"))) {
					payRunExecution();
					System.out
							.println("The generate draft payroll functionality sucessfully executed"
									+ "hence progress bar is no longer visible");
				}
			}
		} catch (Throwable t) {

		}
	}

	/*
	 * FOLLOWING METHODS ARE FOR PAYROLL TAX MODULE : GenerateTaxRateMonthly
	 * script
	 */

	public void UpdateEmployeeTaxCodeOld(String empName, String Taxcode,
			String TaxBasis) throws Throwable {
		try {
			if (employeeFirsttimeView) {
				employeeFirsttimeView = false;
				getObject("PersonalTab").click();
				System.out.println("The personal tab got clicked");
				Thread.sleep(1000L);
				if (existsElement(OR.getProperty("PersonalText"))) {
					System.out
							.println("I am in personal page and found Personal text on left corner of the screen");
					if (existsElement(OR.getProperty("EmployeeView"))) {
						System.out.println("I recognised the Employee view");
						/*
						 * Rather than selecting the drop down item by
						 * 'selectByValue',it is better to select by
						 * 'selectByVisibleText' as select by value may change
						 * during the course of time which means your locators
						 * needs to be updated periodically.
						 */
						Select selectByValue = new Select(driver.findElement(By
								.xpath(OR.getProperty("EmployeeView"))));
						selectByValue
								.selectByVisibleText("DO NOT TOUCH PAYROLL AUTOMATION TESTING");
						Thread.sleep(2000L);
						if (existsElement(OR.getProperty("ViewGoButton"))) {
							getObject("ViewGoButton").sendKeys("");
							getObject("ViewGoButton").click();
						}
						Thread.sleep(7000L);
					}
				}
			}
			WebElement tableheader = driver.findElement(By.xpath(OR
					.getProperty("PersonalAndCompensationHeadingTable")));
			List<WebElement> th = tableheader.findElements(By.tagName("td"));
			for (a = 0; a < th.size(); a++) {
				if ("Employee".equalsIgnoreCase(th.get(a).getText())) {
					empcolnum = a + 1;
					break;
				}
			}
			for (b = 0; b < th.size(); b++) {
				if ("Tax code".equalsIgnoreCase(th.get(b).getText())) {
					taxcodecolnum = b + 1;
					break;

				}

			}

			for (c = 0; c < th.size(); c++) {
				if ("Tax basis".equalsIgnoreCase(th.get(c).getText())) {
					taxbasiscolnum = c + 1;
					break;

				}

			}

			WebElement postsTable = driver.findElement(By.xpath(OR
					.getProperty("firstRecordOfTaxCodecoulmnTable")));
			if (existsWebElement(postsTable)) {
				List<WebElement> rows = postsTable.findElements(By.xpath(OR
						.getProperty("firstRecordOfTaxCodecoulmnTableRows")));

				java.util.Iterator<WebElement> x = rows.iterator();
				rownum = 1;
				while (x.hasNext()) {
					// Thread.sleep(2000L);
					String firstRowOfEmployeeColumn = "//div[" + rownum
							+ "]/table/tbody/tr/td" + "[" + empcolnum + "]"
							+ "/" + "div/a/span";
					if (existsElement(firstRowOfEmployeeColumn)) {
						WebElement tempElement = driver.findElement(By
								.xpath(firstRowOfEmployeeColumn));
						String tempEmp = tempElement.getText();
						// System.out.println(tempEmp+"-------"+empName+"------"+rownum);
						if (tempEmp != null
								&& tempEmp.equalsIgnoreCase(empName)) {
							System.out.println("Employee name  :" + tempEmp
									+ "  matched ");

							Thread.sleep(2000L);
							String firstRowOfTaxCode = "//div[" + rownum + "]"
									+ "/" + "table/" + "tbody/" + "tr/" + "td["
									+ taxcodecolnum + "]" + "/" + "div";
							if (existsElement(firstRowOfTaxCode)) {
								Actions action1 = new Actions(driver);
								action1.doubleClick(
										driver.findElement(By
												.xpath(firstRowOfTaxCode)))
										.perform();
								WebElement updateTaxcode = driver
										.findElement(By.xpath(OR
												.getProperty("taxCodeTextfield")));
								action1.moveToElement(updateTaxcode).perform();
								Thread.sleep(1000L);
								// updateTaxcode.clear();
								updateTaxcode.sendKeys(Taxcode);
								Thread.sleep(1000L);
								if (existsElement(OR
										.getProperty("taxCodeSavebutton"))) {
									getObject("taxCodeSavebutton").click();
									System.out
											.println("Tax code got saved successfully");
								}
								Thread.sleep(6000L);

							}

							UpdateTaxBasis(empName, Taxcode, TaxBasis);
							break;
						}
					} else {
						System.out.println("");
						// System.out.println("Employee name not matched");
					}
					rownum++;
				}

			}

		} catch (Throwable t) {
			APP_LOGS.debug(" Check for error in NI Category method");
			System.out.println(t.getStackTrace().toString());
			ErrorUtil.addVerificationFailure(t);
			System.out.println("");
		}

	}

	public void UpdateTaxBasis1(String ename, String TCode, String TaxBasis)
			throws Throwable {
		try {
			String firstRowOfTaxBasis = "//div[" + rownum + "]" + "/"
					+ "table/" + "tbody/" + "tr/" + "td[" + taxbasiscolnum
					+ "]" + "/" + "div";
			if (existsElement(firstRowOfTaxBasis)) {
				// Thread.sleep(1000L);
				Actions action2 = new Actions(driver);
				action2.doubleClick(
						driver.findElement(By.xpath(firstRowOfTaxBasis)))
						.build().perform();
				action2.moveToElement(getObject("taxBasisdropdown")).perform();
				Thread.sleep(1000L);
				if (existsElement(OR.getProperty("taxBasisdropdown"))) {
					getObject("taxBasisdropdown").sendKeys(TaxBasis);
					// Thread.sleep(2000L);
				}

				if (existsElement(OR.getProperty("taxCodeSavebutton"))) {
					getObject("taxCodeSavebutton").click();
					System.out.println("Tax basis got saved successfully");
				}
				Thread.sleep(6000L);
			}
		} catch (Throwable t) {
			APP_LOGS.debug("Check the tax basis Method for errors");
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
			ErrorUtil.addVerificationFailure(t);
		}

	}

	public void UpdateAnnualSalaryOLD(String EmpName, String annualSalary,
			String PayFrequency) throws Throwable {

		try {

			if (compensationFirsttimeView) {
				compensationFirsttimeView = false;
				if (existsElement(OR.getProperty("CompensationTab"))) {
					getObject("CompensationTab").click();
					Thread.sleep(4000L);
					/*
					 * Calling the following method from the base class since
					 * "Select value is not able to call the value from
					 * OR.Properties page.
					 */
					compensationSelectValue();
				}

			}
			Thread.sleep(1000L);
			WebElement tableheader = driver.findElement(By.xpath(OR
					.getProperty("PersonalAndCompensationHeadingTable")));

			List<WebElement> th = tableheader.findElements(By.tagName("td"));

			for (a = 0; a < th.size(); a++) {
				if ("Employee".equalsIgnoreCase(th.get(a).getText())) {
					empcolnum = a + 1;
					break;

				}

			}

			for (b = 0; b < th.size(); b++) {
				if ("Annual salary".equalsIgnoreCase(th.get(b).getText())) {
					compnAnnualSalColumn = b + 1;
					break;
				}

			}

			for (c = 0; c < th.size(); c++) {
				if ("Payroll frequency".equalsIgnoreCase(th.get(c).getText())) {
					compPayfrequencyColumn = c + 1;
					break;
				}

			}

			WebElement postsTable = driver.findElement(By.xpath(OR
					.getProperty("firstRecordOfTaxCodecoulmnTable")));
			if (existsWebElement(postsTable)) {
				List<WebElement> rows = postsTable.findElements(By.xpath(OR
						.getProperty("firstRecordOfTaxCodecoulmnTableRows")));
				java.util.Iterator<WebElement> x = rows.iterator();
				rownum = 1;
				while (x.hasNext()) {
					String firstEmpXpath = "//div[" + rownum
							+ "]/table/tbody/tr/td" + "[" + empcolnum + "]"
							+ "/" + "div/a/span";
					if (existsElement(firstEmpXpath)) {
						WebElement FirstrowofEmpColumn = driver.findElement(By
								.xpath(firstEmpXpath));
						String ApplnEmp = FirstrowofEmpColumn.getText();
						if (ApplnEmp != null
								&& ApplnEmp.equalsIgnoreCase(EmpName)) {
							// System.out.println("Employee matched");
							Thread.sleep(1000L);
							String firstRowOfAnnualsalary = "//div[" + rownum
									+ "]" + "/" + "table/" + "tbody/" + "tr/"
									+ "td[" + compnAnnualSalColumn + "]" + "/"
									+ "div";

							if (existsElement(firstRowOfAnnualsalary)) {
								// String RowOfAnnualsalary =
								// "//div["+rownum+"]"+"/"+"table/"+"tbody/"+"tr/"+"td["+"6]"+"/"+"div";
								Actions action1 = new Actions(driver);
								action1.doubleClick(
										driver.findElement(By
												.xpath(firstRowOfAnnualsalary)))
										.perform();
								WebElement updatesal = driver
										.findElement(By.xpath(OR
												.getProperty("annualSalTextField")));
								action1.moveToElement(updatesal).perform();
								Thread.sleep(1000L);
								updatesal.clear();
								Thread.sleep(1000L);
								updatesal.sendKeys(annualSalary);
								Thread.sleep(1000L);
								if (existsElement(OR
										.getProperty("CompnSavebuton"))) {
									getObject("CompnSavebuton").sendKeys("");
									getObject("CompnSavebuton").click();
									System.out
											.println("The annual salary got saved");
								}
								Thread.sleep(3000L);

							}

							UpdatePayFrequency(EmpName, annualSalary,
									PayFrequency);
							break;
						}

					} else {
						System.out.println("");
						// System.out.println("Employee name not matched");
					}
					rownum++;
				}

			}

		} catch (Throwable t) {
			APP_LOGS.debug("Check the Annual salary Method for errors");
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
			ErrorUtil.addVerificationFailure(t);
		}
	}

	public void UpdatePayFrequency1(String empName, String AnnualSalary,
			String PayFrequency) throws Throwable {
		try {
			String firstRowOfPayFrequency = "//div[" + rownum + "]" + "/"
					+ "table/" + "tbody/" + "tr/" + "td["
					+ compPayfrequencyColumn + "]" + "/" + "div";
			if (existsElement(firstRowOfPayFrequency)) {
				Thread.sleep(2000L);
				// String RowOfPayFrequency =
				// "//div["+rownum+"]"+"/"+"table/"+"tbody/"+"tr/"+"td["+"7]"+"/"+"div";
				Actions action2 = new Actions(driver);
				action2.doubleClick(
						driver.findElement(By.xpath(firstRowOfPayFrequency)))
						.perform();
				action2.moveToElement(getObject("payFrequencyDropdown"))
						.perform();
				Thread.sleep(2000L);
				if (existsElement(OR.getProperty("payFrequencyDropdown"))) {
					// Select selectByValue = new
					// Select(driver.findElement(By.xpath(OR.getProperty("payFrequencyDropdown"))));
					// selectByValue.selectByVisibleText(PayFrequency);
					getObject("payFrequencyDropdown").sendKeys("");
					getObject("payFrequencyDropdown").sendKeys(PayFrequency);
					System.out.println("Selected the PayFrequency item as :"
							+ PayFrequency);
					Thread.sleep(2000L);
					if (existsElement(OR.getProperty("payFrequencyUpdate"))) {
						getObject("payFrequencyUpdate").click();
						System.out
								.println("The update button got clicked and Pay frequency Category got saved");

					}

				}

				/*
				 * getObject("payFrequencyDropdown").sendKeys(PayFrequency);
				 * 
				 * if(existsElement(OR.getProperty("payFrequencyUpdate"))) {
				 * getObject("payFrequencyUpdate").click();
				 * 
				 * System.out.println("The pay frequency got updated"); }
				 */

			}

		} catch (Throwable t) {
			APP_LOGS.debug("Check the Pay frequency Method for errors");
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
			ErrorUtil.addVerificationFailure(t);
		}

	}

	/******************* Statutory scenarios weekly payroll methods for first page *********************************/
	/* PayrollForRequiredPayruns */
	public void PayrollForStatutoryMonthly(String EmployerName, String EmpName,
			String Payrolid, String Frquency, String MonthName,
			String ExcelInputSheet, String FirstReportNameInApplication,
			String TestResultExcelFilePath, String PayrollView)
			throws Throwable {

		if (existsElementchkFor1mts(OR.getProperty("payrollTab"))) {
			getObject("payrollTab").click();
			System.out.println("The payroll tab got clicked");
		}
		Thread.sleep(5000L);
		if (existsElementchkFor1mts(OR.getProperty("payrollViewLocator"))) {
			Select selectByValue = new Select(driver.findElement(By.xpath(OR
					.getProperty("payrollViewLocator"))));
			// This select by value needs to be called from OR.Properties
			selectByValue.selectByValue(PayrollView);// "Current"
		}
		Thread.sleep(2000L);
		
		if (existsElementchkFor1mts(OR.getProperty("payrollSearchField")))
		{
			System.out.println("The payroll search field is displayed");
			getObject("payrollSearchField").sendKeys("");
			getObject("payrollSearchField").sendKeys(Payrolid);
		}
		Thread.sleep(5000L);
		
		/*
		 * This code clicks to pagination from 1 to last page till it finds the
		 * '2Weekly' pay run Once it finds the '2Weekly' payrun, it clicks to
		 * it. Hence the following code finds the '2Weekly' payrun automatically
		 * from pagination ProcessingTo2Weekly() method searches the required
		 * company name and payrun
		 */
		try {
			if (existsElementchkFor1mts(OR.getProperty("payroll2weeklytable"))) {
				WebElement table = driver.findElement(By.xpath(OR
						.getProperty("payroll2weeklytable")));
				if (existsWebElement(table)) {
					System.out.println("payroll table existt");
					/*
					 * Since the pagination is changed and the next button holds
					 * constant value as 3 . i have taken this as locator
					 * element.
					 */
					// List<WebElement> allpages =
					// driver.findElements(By.xpath(OR.getProperty("totalPages")));
					System.out.println("Total pages :");
					//
					for (int i = 2; i <= 50; i++) {

						List<WebElement> allrows = table
								.findElements(By.xpath(OR
										.getProperty("payroll2weeklytablerowss")));

						for (int row = 1; row <= allrows.size(); row++) {
							ProcessingToWeeklyForStatutory1(EmployerName,
									EmpName, Payrolid, Frquency, MonthName,
									ExcelInputSheet,
									FirstReportNameInApplication,
									TestResultExcelFilePath, PayrollView);
							System.out
									.println("No record matched in first page hence clicked to next page");

						}

						if (existsElement(OR.getProperty("paginationElement")))
						{
							//getObject("paginationNext").sendKeys("");
							//getObject("paginationNext").click();
							System.out.println("hence clicked to next page");

						}
						List<WebElement> allrows1 = table
								.findElements(By.xpath(OR
										.getProperty("payroll2weeklytablerowss")));

						for (int row = 1; row <= allrows1.size(); row++) {
							ProcessingToWeeklyForStatutory1(EmployerName,
									EmpName, Payrolid, Frquency, MonthName,
									ExcelInputSheet,
									FirstReportNameInApplication,
									TestResultExcelFilePath, PayrollView);
						}

					}
				}
			}

		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void ProcessingToWeeklyForStatutory1(String EmployerName,
			String EmpName, String Payrolid, String Frquency, String MonthName,
			String ExcelInputSheet, String FirstReportNameInApplication,
			String TestResultExcelFilePath, String PayrollView)
			throws Throwable {
		try {
			WebElement tableheader = getObject("payroll2weeklytable");
			List<WebElement> th = tableheader.findElements(By.tagName("th"));

			for (int i = 0; i < th.size(); i++) {
				if ("Payroll".equalsIgnoreCase(th.get(i).getText())) {
					payrollcol_position = i + 1;
					break;
				}
			}

			for (int j = 0; j < th.size(); j++) {
				if ("Employer".equalsIgnoreCase(th.get(j).getText())) {
					Emplpoyercol_position = j + 1;
					break;
				}

			}

			for (int k = 0; k < th.size(); k++) {
				if ("Frequency".equalsIgnoreCase(th.get(k).getText())) {
					frequencyCol_Postition = k + 1;
					break;

				}

			}
			Thread.sleep(4000L);
			WebElement niweeklyPayrollTable = getObject("payroll2weeklytable");
			// need to check webelement exist
			// WebTable table = WebTable.getTable(niweeklyPayrollTable);
			List<WebElement> rows = niweeklyPayrollTable.findElements(By
					.xpath(OR.getProperty("payroll2weeklytablerows")));
			// //
			java.util.Iterator<WebElement> x = rows.iterator();
			rownum = 1;
			System.out.println("rownum is  :" + rownum);
			while (x.hasNext()) {
				WebElement emr1 = driver.findElement(By.xpath("//table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/"
						+ "td[" + Emplpoyercol_position + "]"));
				String empr = emr1.getText();

				WebElement ffr = driver.findElement(By.xpath("//table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/"
						+ "td[" + frequencyCol_Postition + "]"));
				String ffr1 = ffr.getText();

				WebElement ppr1 = driver.findElement(By.xpath("//table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/"
						+ "td[" + payrollcol_position + "]"));
				String ppr = ppr1.getText();
				// Thread.sleep(4000L);

				payrollRecordId = "//table/tbody/tr/td/form/div[1]/table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/"
						+ "td[" + payrollcol_position + "]" + "/" + "a";

				if (empr != null && empr.equalsIgnoreCase(EmployerName)
						&& ppr.equalsIgnoreCase(Payrolid)
						&& ffr1.equalsIgnoreCase(Frquency)) {
					System.out
							.println("Finally the Employer name,Payfrequency 'Monthly' and required Payroll matched");

					driver.findElement(By.xpath(payrollRecordId)).click();
					/*
					 * ENTERING INTO NEXT PAGE
					 */
					if (existsElement(OR.getProperty("twoweeklyPayrolldetails"))) {
						String pfrequencey = getObject(
								"twoweeklyPayrolldetails").getText();
						System.out.println("the employer name is :"
								+ pfrequencey);
						if (Frquency.equalsIgnoreCase("Four Weekly")) {
							TaxPayRun_For_FourWeek(MonthName, ExcelInputSheet,
									FirstReportNameInApplication,
									TestResultExcelFilePath);
						} else if (Frquency.equalsIgnoreCase("Two Weekly")) {
							TaxPayRun_For_TwoWeek(MonthName, ExcelInputSheet,
									FirstReportNameInApplication,
									TestResultExcelFilePath);
						} else if (Frquency.equalsIgnoreCase("Weekly")) {
							TaxPayRun_For_Week(MonthName, ExcelInputSheet,
									FirstReportNameInApplication,
									TestResultExcelFilePath);
						} else if (Frquency.equalsIgnoreCase("Monthly")) {
							TaxPayRun_For_Monthly(MonthName, ExcelInputSheet,
									FirstReportNameInApplication,
									TestResultExcelFilePath);
						}
						break;
					}
					break;
				} else {
					System.out.println("payfrequency not matched");
				}
				rownum++;
			}

		} catch (Throwable t) {
			t.getMessage().toString();
			t.getStackTrace().toString();
		}

	}

	/*
	 * FOLLOWING Method may needs to be deleted to check doing r&d
	 */

	public void paginForWeekstatFor1stPage(String EmployerName, String EmpName,
			String Payrolid, String Frquency, String MonthName,
			String ExcelInputSheet, String FirstReportNameInApplication,
			String TestResultExcelFilePath) throws Throwable {
		try {
			Thread.sleep(4000L);

			WebElement payRunWeekOneTable = getObject("payRunWeekTable");
			List<WebElement> allpages = driver.findElements(By.xpath(OR
					.getProperty("weekPagination")));
			System.out.println("Total pages :" + allpages.size());
			for (int i = 0; i <= (allpages.size()); i++) {
				if (existsElement(OR.getProperty("weekPagination"))) {
					allpages.get(i).click();

				}
				List<WebElement> allrows = payRunWeekOneTable.findElements(By
						.xpath(OR.getProperty("WeekOneTablerows")));

				System.out.println("Total rows :" + allrows.size());
				for (int row = 1; row <= allrows.size(); row++) {
					TaxPayRun_For_Week1statFor1stPage(MonthName);
					/*
					 * I have replaced this method on dec 7 2017 . If it wont
					 * work needs to be reverted back with following method
					 * //TaxPayRun_For_Week1statFor1stPage(MonthName);
					 */

					/*
					 * TaxPayRun_For_Week(MonthName, ExcelInputSheet,
					 * FirstReportNameInApplication, TestResultExcelFilePath);
					 */
				}
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}

	}

	public void TaxPayRun_For_Week1statFor1stPage(String MonthName)
			throws Throwable {
		try {

			if (existsElementchkFor1mts(OR
					.getProperty("payrollMonthWeeekSubPaginToDisplayAllRecords"))) {
				for (int i = 1; i < 3; i++) {
					getObject("payrollMonthWeeekSubPaginToDisplayAllRecords")
							.sendKeys("");
					getObject("payrollMonthWeeekSubPaginToDisplayAllRecords")
							.click();
					Thread.sleep(4000L);
					System.out
							.println("Inorder to show all the Months record, the expandable page got clicked for"
									+ i + "st time");
				}
			}

			Thread.sleep(6000L);
			if (existsElementchkFor1mts(OR.getProperty("payRunWeekTable"))) {
				System.out.println("table exists");
			}
			WebElement payRunWeekOneTable = getObject("payRunWeekTable");
			List<WebElement> rows = payRunWeekOneTable.findElements(By.xpath(OR
					.getProperty("WeekOneTablerows")));
			int totalRows = rows.size();
			System.out
					.println("total no of Month record rows are " + totalRows);
			java.util.Iterator<WebElement> x = rows.iterator();

			rownum = 1;
			while (x.hasNext()) {

				weekOneRecordId = "//div[" + "5" + "]/" + "div[" + "1]/"
						+ "div/" + "div[" + "2]/" + "table/" + "tbody/tr["
						+ (rownum + 1) + "]/" + "th/" + "a";
				WebElement Week1 = driver
						.findElement(By.xpath(weekOneRecordId));
				if (existsWebElement(Week1)) {
					String weekText = Week1.getText();
					if (weekText != null
							&& weekText.equalsIgnoreCase(MonthName)) {
						System.out
								.println("The Month name is Matched i.e. the Month name is  :"
										+ weekText);
						Week1.sendKeys("");
						Week1.click();
						System.out.println("the Month Name got clicked");
						// break;
					} else {
						rownum++;
					}

				}

			}

		} catch (Throwable t) {
			System.out.println("some problem after click of Month link");
			System.out.println(t.getMessage());

		}
	}

	public void ProcessingMonthlyStatutoryFor1stPage(String EmployerName,
			String EmpName, String Payrolid, String Frquency, String MonthName,
			String ExcelInputSheet, String FirstReportNameInApplication,
			String TestResultExcelFilePath) throws Throwable {
		try {
			WebElement tableheader = getObject("payroll2weeklytable");
			List<WebElement> th = tableheader.findElements(By.tagName("th"));
			System.out.println("recognised the table columns");
			for (int i = 0; i < th.size(); i++) {
				if ("Payroll".equalsIgnoreCase(th.get(i).getText())) {
					payrollcol_position = i + 1;
					break;
				}
			}

			for (int j = 0; j < th.size(); j++) {
				if ("Employer".equalsIgnoreCase(th.get(j).getText())) {
					Emplpoyercol_position = j + 1;
					break;
				}

			}

			for (int k = 0; k < th.size(); k++) {
				if ("Frequency".equalsIgnoreCase(th.get(k).getText())) {
					frequencyCol_Postition = k + 1;
					break;

				}

			}

			WebElement niweeklyPayrollTable = getObject("payroll2weeklytable");
			if (existsWebElement(niweeklyPayrollTable)) {
				List<WebElement> rows = niweeklyPayrollTable.findElements(By
						.xpath(OR.getProperty("payroll2weeklytablerows")));
				java.util.Iterator<WebElement> x = rows.iterator();
				rownum = 1;

				while (x.hasNext()) {
					WebElement emr1 = driver.findElement(By.xpath("//table[2]/"
							+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/"
							+ "td[" + Emplpoyercol_position + "]"));
					String empr = emr1.getText();
					System.out.println(empr);
					WebElement ffr = driver.findElement(By.xpath("//table[2]/"
							+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/"
							+ "td[" + frequencyCol_Postition + "]"));
					String ffr1 = ffr.getText();
					System.out.println(ffr1);
					WebElement ppr1 = driver.findElement(By.xpath("//table[2]/"
							+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/"
							+ "td[" + payrollcol_position + "]"));
					String ppr = ppr1.getText();
					System.out.println(ppr);
					payrollRecordId = "//table[2]/" + "tbody/" + "tr" + "["
							+ (rownum + 1) + "]" + "/" + "td["
							+ payrollcol_position + "]" + "/" + "a";

					if (empr != null && empr.equalsIgnoreCase(EmployerName)
							&& ppr.equalsIgnoreCase(Payrolid)
							&& ffr1.equalsIgnoreCase(Frquency)) {
						System.out
								.println("Finally the Employer name,Payfrequency 'Monthly' and required Payroll matched");
						driver.findElement(By.xpath(payrollRecordId)).click();
						System.out
								.println("The payrun got clicked successfully so that appln displays Payroll details");
						if (existsElement(OR.getProperty("compypayrolldetails"))) {
							String pfrequencey = getObject(
									"compypayrolldetails").getText();
							System.out.println("the payfrequency is :"
									+ pfrequencey);
							TaxPayRun_For_Month1StatutoryFor1stPage(MonthName);
							break;
						}
						break;
					} else {
						System.out.println("");
					}
					rownum++;
				}

			}

		} catch (Throwable t) {
			t.getMessage().toString();
			t.getStackTrace().toString();
		}

	}

	public int totalWeekRows;
	public int rownumm;

	public void TaxPayRun_For_Month1StatutoryFor1stPage(String MonthName)
			throws Throwable {
		try {
			Thread.sleep(1000L);
			if (existsElement(OR.getProperty("payrunMonthlyTableForStatutory"))) {
				WebElement payRunWeekOneTable = getObject("payrunMonthlyTableForStatutory");
				if (existsWebElement(payRunWeekOneTable)) {

					List<WebElement> rows = payRunWeekOneTable
							.findElements(By.xpath(OR
									.getProperty("payrunMonthlyTableRowsForStatutory")));
					System.out.println("The total rows are " + rows.size());
					totalWeekRows = rows.size();
					java.util.Iterator<WebElement> x = rows.iterator();
					rownum = 2;
					while (x.hasNext()) {
						System.out.println("ruwnum is :" + rownum);
						// Thread.sleep(2000L);
						String wkkkname = "//div[" + "5" + "]/" + "div["
								+ "1]/" + "div/" + "div[" + "2]/" + "table/"
								+ "tbody/tr[" + rownum + "]/" + "th/" + "a";
						; // String wkkkname="div[" + "3" + "]/"+ "div/" +
							// "div[" + "2]/" + "table/" + "tbody/tr[" +rownum+
							// "]/" + "th/" + "a";
						WebElement Month4 = driver.findElement(By
								.xpath(wkkkname));
						String MontName = Month4.getText();
						System.out.println("The month name is :" + MontName);
						if (MontName != null
								&& MontName.equalsIgnoreCase(MonthName)) {
							System.out.println("The month name matched i.e :"
									+ MonthName);
							Month4.sendKeys("");
							Month4.click();
							System.out
									.println("The required Pay period got clicked successfully so that appln displays Generate draft payroll details page");
							break;
						} else {
							System.out.println("payRun text  :" + MonthName
									+ " did not matched");
						}
						rownum++;
					}

				} else {
					driver.navigate().refresh();
				}

			}

		} catch (Throwable t) {
			System.out.println(t.getMessage());
			System.out.println(t.getStackTrace());

		}
	}

	/*************************************************************************/

	public void selectPayinStartPeriod(String payinStartPeriod)
			throws Throwable {
		try {
			Thread.sleep(4000L);
			WebElement postsTable = driver.findElement(By.xpath(OR
					.getProperty("sspEditTable")));
			if (existsWebElement(postsTable)) {
				System.out.println("details table exists");
				List<WebElement> rows = postsTable.findElements(By.xpath(OR
						.getProperty("sspEditTableRows")));
				System.out.println("NUMBER OF ROWS IN THIS TABLE = "
						+ rows.size());
				int row_num, col_num;
				row_num = 1;
				outerloop: for (WebElement trElement : rows) {
					List<WebElement> td_collection = trElement.findElements(By
							.xpath("th"));
					System.out.println("NUMBER OF COLUMNS="
							+ td_collection.size());
					col_num = 1;
					for (WebElement tdElement : td_collection) {
						System.out.println("row # " + row_num + ", col # "
								+ col_num + "text=" + tdElement.getText());
						if (tdElement.getText() != null
								&& tdElement.getText().equalsIgnoreCase(
										"Pay in start period")) {
							System.out.println("Label name  :"
									+ tdElement.getText() + "  matched ");
							ckbox = "//following-sibling::td[1]/input[contains(@id,'j_id0:j_id2:')][@type='checkbox']";
							WebElement clkchkbox = driver.findElement(By
									.xpath(ckbox));
							boolean smallERchekbox = clkchkbox.isSelected();
							if (smallERchekbox) {
								System.out
										.println("yes the condition is checked");
							}
							double valueOfsmallReliefChkbox = Double
									.parseDouble(payinStartPeriod);
							System.out.println("converted smallER value is :"
									+ valueOfsmallReliefChkbox);
							if (valueOfsmallReliefChkbox == 1.0) {
								Thread.sleep(4000L);
								if (smallERchekbox) {
									System.out
											.println("Pay in start period checkbox was allready checked, Hence our condition got satisfied");
									employmentSavebutton();
									System.out
											.println("Save button got clicked and all data saved sucessfully");
									break outerloop;
								} else {
									clkchkbox.sendKeys("");
									clkchkbox.click();
									System.out
											.println("Pay in start period checkbox was NOT checked,and now checked hence Condition now satisfied successfully");
									employmentSavebutton();
									System.out
											.println("Save button got clicked and all data saved sucessfully");
									break outerloop;
								}
							}
						}
						col_num++;
					}
					row_num++;
				}
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void employmentSavebutton() throws Throwable {
		try {
			if (existsElement(OR.getProperty("employmentTabSave"))) {
				getObject("employmentTabSave").sendKeys("");
				getObject("employmentTabSave").click();
				System.out
						.println("The employment save button got clicked");
				Thread.sleep(4000L);
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}

	}

	// ///////////////
	/*
	 * public void PayrollForWeeklyTax(String EmployerName,String EmpName,String
	 * Payrolid,String Frquency,String WeekName,String ExcelInputSheet,String
	 * FirstReportNameInApplication,String TestResultExcelFilePath,String
	 * worksheetNo,String PayrollVeiw) throws Throwable { try { if
	 * (existsElement(OR.getProperty("payrollTab"))) {
	 * getObject("payrollTab").click(); } Thread.sleep(5000L);
	 * 
	 * if(existsElement(OR.getProperty("payrollViewLocator"))) { Select
	 * selectByValue = new
	 * Select(driver.findElement(By.xpath(OR.getProperty("payrollViewLocator"
	 * )))); // This select by value needs to be called from OR.Properties
	 * selectByValue.selectByValue("All"); } Thread.sleep(8000L); /* This code
	 * clicks to pagination from 1 to last page till it finds the '2Weekly' pay
	 * run Once it finds the '2Weekly' payrun, it clicks to it. Hence the
	 * following code finds the '2Weekly' payrun automatically from pagination
	 * ProcessingTo2Weekly() method searches the required company name and
	 * payrun
	 */
	/*
	 * try { WebElement table =
	 * driver.findElement(By.xpath(OR.getProperty("payroll2weeklytable")));
	 * List<WebElement> allpages =
	 * driver.findElements(By.xpath(OR.getProperty("paginationElement")));
	 * System.out.println("Total pages :" +allpages.size()); for(int i=0;
	 * i<=(allpages.size()); i++) { if
	 * (existsElement(OR.getProperty("paginationElement"))) {
	 * allpages.get(i).click(); } List<WebElement> allrows =
	 * table.findElements(By
	 * .xpath("//*[@id='j_id0:j_id3']/div[2]/div/table[2]/tbody/tr[1]"));
	 * System.out.println("Total rows :" +allrows.size()); for(int row=1;
	 * row<=allrows.size(); row++) {
	 * ProcessForWeekTaxrate(EmployerName,EmpName,Payrolid
	 * ,Frquency,WeekName,ExcelInputSheet
	 * ,FirstReportNameInApplication,TestResultExcelFilePath
	 * ,worksheetNo,PayrollVeiw); } } } catch(Throwable t) {
	 * System.out.println(t.getMessage().toString());
	 * System.out.println(t.getStackTrace().toString()); }
	 * 
	 * }
	 * 
	 * catch(Throwable t) { System.out.println(t.getMessage()); } }
	 */

	public void PayrollForWeeklyTax(String EmployerName, String EmpName,
			String Payrolid, String Frquency, String MonthName,
			String ExcelInputSheet, String FirstReportNameInApplication,
			String TestResultExcelFilePath, String worksheetNo,
			String PayrollView) throws Throwable {

		if (existsElementchkFor1mts(OR.getProperty("payrollTab"))) {
			getObject("payrollTab").click();
			System.out.println("The payroll tab got clicked");

		}
		Thread.sleep(5000L);

		if (existsElementchkFor1mts(OR.getProperty("payrollViewLocator"))) {
			Select selectByValue = new Select(driver.findElement(By.xpath(OR
					.getProperty("payrollViewLocator"))));
			// This select by value needs to be called from OR.Properties
			selectByValue.selectByValue(PayrollView);// "Current"
		}
		Thread.sleep(3000L);
		/*
		 * This code clicks to pagination from 1 to last page till it finds the
		 * '2Weekly' pay run Once it finds the '2Weekly' payrun, it clicks to
		 * it. Hence the following code finds the '2Weekly' payrun automatically
		 * from pagination ProcessingTo2Weekly() method searches the required
		 * company name and payrun //payrollSearchField
		 */
		if (existsElementchkFor1mts(OR.getProperty("payrollSearchField")))
		{
			System.out.println("The payroll search field is displayed");
			getObject("payrollSearchField").sendKeys("");
			getObject("payrollSearchField").sendKeys(Payrolid);
		}
		Thread.sleep(3000L);
		
		try {
			if (existsElementchkFor1mts(OR.getProperty("payroll2weeklytable"))) {
				WebElement table = driver.findElement(By.xpath(OR
						.getProperty("payroll2weeklytable")));
				if (existsWebElement(table)) {
					System.out.println("payroll table existt");
					/*
					 * Since the pagination is changed and the next button holds
					 * constant value as 3 . i have taken this as locator
					 * element.
					 */
					// List<WebElement> allpages =
					// driver.findElements(By.xpath(OR.getProperty("totalPages")));
					System.out.println("Total pages :");
					//
					for (int i = 2; i <= 100; i++) {

						if (existsElement(OR.getProperty("paginationElement")))
						{
							//getObject("paginationNext").sendKeys("");
							//getObject("paginationNext").click();
							// allpages.get(i).click();
						}
						List<WebElement> allrows = table
								.findElements(By.xpath(OR
										.getProperty("payroll2weeklytablerowss")));

						for (int row = 1; row <= allrows.size(); row++) {
							ProcessForWeekTaxrate1(EmployerName, EmpName,
									Payrolid, Frquency, MonthName,
									ExcelInputSheet,
									FirstReportNameInApplication,
									TestResultExcelFilePath, PayrollView);
						}

						//

					}
				}
			}

		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void ProcessForWeekTaxrate1(String EmployerName, String EmpName,
			String Payrolid, String Frquency, String MonthName,
			String ExcelInputSheet, String FirstReportNameInApplication,
			String TestResultExcelFilePath, String PayrollView)
			throws Throwable {
		try {
			WebElement tableheader = getObject("payroll2weeklytable");
			List<WebElement> th = tableheader.findElements(By.tagName("th"));

			for (int i = 0; i < th.size(); i++) {
				if ("Payroll".equalsIgnoreCase(th.get(i).getText())) {
					payrollcol_position = i + 1;
					break;
				}
			}

			for (int j = 0; j < th.size(); j++) {
				if ("Employer".equalsIgnoreCase(th.get(j).getText())) {
					Emplpoyercol_position = j + 1;
					break;
				}

			}

			for (int k = 0; k < th.size(); k++) {
				if ("Frequency".equalsIgnoreCase(th.get(k).getText())) {
					frequencyCol_Postition = k + 1;
					break;

				}

			}

			WebElement niweeklyPayrollTable = getObject("payroll2weeklytable");
			// need to check webelement exist
			// WebTable table = WebTable.getTable(niweeklyPayrollTable);
			List<WebElement> rows = niweeklyPayrollTable.findElements(By
					.xpath(OR.getProperty("payroll2weeklytablerows")));

			java.util.Iterator<WebElement> x = rows.iterator();
			rownum = 1;
			System.out.println("rownum is  :" + rownum);
			while (x.hasNext()) {
				WebElement emr1 = driver.findElement(By.xpath("//table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) +

						"]" + "/" + "td[" + Emplpoyercol_position + "]"));
				String empr = emr1.getText();

				WebElement ffr = driver.findElement(By.xpath("//table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/"
						+ "td[" + frequencyCol_Postition + "]"));
				String ffr1 = ffr.getText();

				WebElement ppr1 = driver.findElement(By.xpath("//table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/" +

						"td[" + payrollcol_position + "]"));
				String ppr = ppr1.getText();

				payrollRecordId = "//table[2]/" + "tbody/" + "tr" + "["
						+ (rownum + 1) + "]" + "/" + "td["
						+ payrollcol_position + "]" + "/" + "a";

				if (empr != null && empr.equalsIgnoreCase(EmployerName)
						&& ppr.equalsIgnoreCase(Payrolid)
						&& ffr1.equalsIgnoreCase(Frquency)) {
					System.out
							.println("Finally the Employer name,Payfrequency 'Monthly' and required Payroll matched");

					driver.findElement(By.xpath(payrollRecordId)).click();
					if (existsElement(OR.getProperty("twoweeklyPayrolldetails"))) {
						String pfrequencey = getObject(
								"twoweeklyPayrolldetails").getText();
						System.out.println("the employer name is :"
								+ pfrequencey);
						if (Frquency.equalsIgnoreCase("Four Weekly")) {
							TaxPayRun_For_FourWeek(MonthName, ExcelInputSheet,
									FirstReportNameInApplication,
									TestResultExcelFilePath);
						} else if (Frquency.equalsIgnoreCase("Two Weekly")) {
							TaxPayRun_For_TwoWeek(MonthName, ExcelInputSheet,
									FirstReportNameInApplication,
									TestResultExcelFilePath);
						} else if (Frquency.equalsIgnoreCase("Weekly")) {
							TaxPayRun_For_Week(MonthName, ExcelInputSheet,
									FirstReportNameInApplication,
									TestResultExcelFilePath);
						}

						break;
					}
					break;
				} else {
					System.out.println("payfrequency not matched");

				}
				rownum++;
			}

		} catch (Throwable t) {
			t.getMessage().toString();
			t.getStackTrace().toString();
		}

	}

	public void ProcessForWeekTaxrate(String EmployerName, String EmpName,
			String Payrolid, String Frquency, String WeekName,
			String ExcelInputSheet, String FirstReportNameInApplication,
			String TestResultExcelFilePath, String worksheetNo,
			String PayrollVeiw) throws Throwable {

		try {
			WebElement tableheader = getObject("payroll2weeklytable");
			List<WebElement> th = tableheader.findElements(By.tagName("th"));

			for (int i = 0; i < th.size(); i++) {
				if ("Payroll".equalsIgnoreCase(th.get(i).getText())) {
					payrollcol_position = i + 1;
					break;
				}
			}

			for (int j = 0; j < th.size(); j++) {
				if ("Employer".equalsIgnoreCase(th.get(j).getText())) {
					Emplpoyercol_position = j + 1;
					break;
				}
			}

			for (int k = 0; k < th.size(); k++) {
				if ("Frequency".equalsIgnoreCase(th.get(k).getText())) {
					frequencyCol_Postition = k + 1;
					break;
				}
			}
			WebElement niweeklyPayrollTable = getObject("payrolltable");
			// WebTable table = WebTable.getTable(niweeklyPayrollTable);
			List<WebElement> rows = niweeklyPayrollTable.findElements(By
					.xpath(OR.getProperty("payrollTableRows")));
			java.util.Iterator<WebElement> x = rows.iterator();
			rownum = 1;

			while (x.hasNext()) {
				WebElement emr1 = driver.findElement(By.xpath("//table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/"
						+ "td[" + Emplpoyercol_position + "]"));
				String empr = emr1.getText();
				WebElement ffr = driver.findElement(By.xpath("//table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/"
						+ "td[" + frequencyCol_Postition + "]"));
				String ffr1 = ffr.getText();
				WebElement ppr1 = driver.findElement(By.xpath("//table[2]/"
						+ "tbody/" + "tr" + "[" + (rownum + 1) + "]" + "/" +

						"td[" + payrollcol_position + "]"));
				String ppr = ppr1.getText();
				payrollRecordId = "//table[2]/" + "tbody/" + "tr" + "["
						+ (rownum + 1) + "]" + "/" + "td["
						+ payrollcol_position + "]" + "/" + "a";
				if (empr != null && empr.equalsIgnoreCase(EmployerName)
						&& ppr.equalsIgnoreCase(Payrolid)
						&& ffr1.equalsIgnoreCase(Frquency)) {
					System.out
							.println("Finally the Employer name,Payfrequency 'Monthly' and required Payroll matched");

					driver.findElement(By.xpath(payrollRecordId)).click();
					System.out
							.println("The weekly payroll record link got clicked");

					Thread.sleep(8000L);

					if (existsElement(OR.getProperty("payrollMonthlydetails"))) {
						String pfrequencey = getObject("payrollMonthlydetails")
								.getText();
						System.out.println("the payfrequency is :"
								+ pfrequencey);
						// TaxPayRun_For_Week1statFor1stPage(WeekName);

						TaxPayRun_For_FourWeek(WeekName, ExcelInputSheet,
								FirstReportNameInApplication,
								TestResultExcelFilePath);
						break;
					}
					break;
				} else {
					System.out.println("payfrequency not matched");
				}
				rownum++;
				System.out.println("");
			}

		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void TaxPayRun_For_Monthly(String MonthName, String ExcelInputSheet,
			String FirstReportNameInApplication, String TestResultExcelFilePath)
			throws Throwable {
		try {/*
			 * 
			 * if (existsElementchkFor1mts(OR
			 * .getProperty("payrollMonthWeeekSubPaginToDisplayAllRecords"))) {
			 * for (int i = 1; i < 3; i++) {
			 * getObject("payrollMonthWeeekSubPaginToDisplayAllRecords")
			 * .sendKeys("");
			 * getObject("payrollMonthWeeekSubPaginToDisplayAllRecords")
			 * .click();
			 * System.out.println("The expandable page got clicked for" + i +
			 * "st time"); Thread.sleep(4000L); }
			 * 
			 * }
			 * 
			 * 
			 * Thread.sleep(4000L); if
			 * (existsElement(OR.getProperty("payRunWeekTable"))) {
			 * System.out.println("Thee table exists");
			 * 
			 * WebElement payRunWeekOneTable = getObject("payRunWeekTable");
			 * 
			 * List<WebElement> rows = payRunWeekOneTable.findElements(By
			 * .xpath(OR.getProperty("WeekOneTablerows")));
			 * java.util.Iterator<WebElement> x = rows.iterator();
			 * System.out.println("total number of week records are :" +
			 * rows.size());
			 * 
			 * rownum = 2; while (x.hasNext()) { WebElement Weekrecord =
			 * driver.findElement(By .xpath("//div[" + "5" + "]/" + "div[" +
			 * "1]/" + "div/" + "div[" + "2]/" + "table/" + "tbody/tr[" +
			 * (rownum) + "]/" + "th/" + "a"));
			 * 
			 * String weekText = Weekrecord.getText();
			 * 
			 * if (weekText != null && weekText.equalsIgnoreCase(WeekName)) {
			 * System.out.println("The week name" + WeekName + " matched");
			 * Weekrecord.sendKeys(""); Thread.sleep(4000L); Weekrecord.click();
			 * break; } else { System.out.println("payRun text " + WeekName +
			 * "did not matched"); rownum++; }
			 * 
			 * }
			 * 
			 * }
			 */

			// Code Added By Swamy

			// Thread.sleep(2000L);
			if (existsElement(OR.getProperty("payRunWeekTable"))) {
				System.out.println("Thee table exists");

				WebElement payRunWeekOneTable = getObject("payRunWeekTable");

				List<WebElement> rows = payRunWeekOneTable.findElements(By
						.xpath(OR.getProperty("WeekOneTablerows")));
				lastRowCount = rows.size();
				System.out
						.println("The total pay run records for the page is equal to : "
								+ lastRowCount);
				java.util.Iterator<WebElement> x = rows.iterator();
				rownum = 2;
				counter = 1;
				while (x.hasNext()) {
					// Thread.sleep(2000L);
					System.out
							.println("Now the count of Rownum is : " + rownum);
					WebElement MonthPayRun_Record = driver.findElement(By
							.xpath("//div[" + "5" + "]/" + "div[" + "1]/"
									+ "div/" + "div[" + "2]/" + "table/"
									+ "tbody/tr[" + (rownum) + "]/" + "th/"
									+ "a"));

					if (existsWebElement(MonthPayRun_Record)) {
						System.out.println("first payroll table record existt");
						String PayRunTextName = MonthPayRun_Record.getText();
						System.out.println("The Month name is :"
								+ PayRunTextName);
						if (PayRunTextName != null
								&& PayRunTextName.equalsIgnoreCase(MonthName)) {
							System.out.println("The Month name"
									+ PayRunTextName + " matched");
							MonthPayRun_Record.sendKeys("");
							// Thread.sleep(1000L);
							MonthPayRun_Record.click();
							System.out
									.println("The Payrun record whose Month name is "
											+ MonthName
											+ "successfully clicked for processing payroll");

							break;
						}
						System.out.println("The Month name" + PayRunTextName
								+ " is not matched");
						if (counter < 12 && rownum == 6 || rownum > 10
								&& PayRunTextName != null
								&& PayRunTextName != (MonthName)) {
							System.out
									.println("The row number of the page reached"
											+ rownum

											+ " Required payrun not found hence clicking the"
											+ " pagination link so that payrun search continues for next page");

							if (existsElementchkFor1mts(OR
									.getProperty("payrollMonthWeeekSubPaginToDisplayAllRecords"))) {
								getObject(
										"payrollMonthWeeekSubPaginToDisplayAllRecords")
										.sendKeys("");
								getObject(
										"payrollMonthWeeekSubPaginToDisplayAllRecords")
										.click();
								System.out
										.println("As the required Payrun is not found in first page,hence clicked to pagination link");
								Thread.sleep(5000L);

							}
						}

						System.out
								.println("Payrun not matched hence incrementing the row number");
						rownum++;
						counter++;
					}
				}
			}

		} catch (Throwable t) {
			System.out.println(t.getMessage());

		}
	}

	/*
	 * The following method needs to be deleted 
	 */
/*	public void TaxPayRun_For_Week(String WeekName, String ExcelInputSheet,
			String FirstReportNameInApplication, String TestResultExcelFilePath)
			throws Throwable {
		try {

			if (existsElementchkFor1mts(OR
					.getProperty("payrollMonthWeeekSubPaginToDisplayAllRecords"))) {
				for (int i = 1; i < 5; i++) {
					getObject("payrollMonthWeeekSubPaginToDisplayAllRecords")
							.sendKeys("");
					getObject("payrollMonthWeeekSubPaginToDisplayAllRecords")
							.click();
					System.out.println("The expandable page got clicked for"
							+ i + "st time");
					Thread.sleep(2000L);
				}
			}
			Thread.sleep(4000L);
			if (existsElementchkFor1mts(OR.getProperty("payRunWeekTable"))) {
				System.out.println("table exists");
				WebElement payRunWeekOneTable = getObject("payRunWeekTable");
				List<WebElement> rows = payRunWeekOneTable.findElements(By
						.xpath(OR.getProperty("WeekOneTablerows")));
				java.util.Iterator<WebElement> x = rows.iterator();
				System.out.println("total number of week records are :"
						+ rows.size());

				rownum = 2;
				while (x.hasNext()) {
					WebElement Weekrecord = driver.findElement(By
							.xpath("//div[" + "5" + "]/" + "div[" + "1]/"
									+ "div/" + "div[" + "2]/" + "table/"
									+ "tbody/tr[" + (rownum) + "]/" + "th/"
									+ "a"));

					String weekText = Weekrecord.getText();

					if (weekText != null && weekText.equalsIgnoreCase(WeekName)) {
						System.out.println("The week name" + WeekName
								+ " matched");
						Weekrecord.sendKeys("");
						Weekrecord.click();
						break;
					} else {
						System.out.println("payRun text " + WeekName
								+ "did not matched");
						rownum++;
					}

				}

			}

		} catch (Throwable t) {
			System.out.println(t.getMessage());

		}
	}
*/
	
	
	
	public void TaxPayRun_For_Week(String WeekName, String ExcelInputSheet,
			String FirstReportNameInApplication, String TestResultExcelFilePath)
			throws Throwable
	{
		try {
				Thread.sleep(4000L);
				if (existsElementchkFor1mts(OR.getProperty("payRunWeekTable"))) 
				{
				System.out.println("table exists");
				WebElement payRunWeekOneTable = getObject("payRunWeekTable");
				List<WebElement> rows = payRunWeekOneTable.findElements(By
						.xpath(OR.getProperty("WeekOneTablerows")));
				java.util.Iterator<WebElement> x = rows.iterator();
				System.out.println("total number of week records are :"
						+ rows.size());
				counter = 1;
				rownum = 2;
				while (x.hasNext())
				{
					WebElement Weekrecord = driver.findElement(By
							.xpath("//div/" + "div[" + "2]/" + "table/"
									+ "tbody/tr[" + (rownum) + "]/" + "th/"
									+ "a"));
					if (existsWebElement(Weekrecord))
					{
					System.out.println("first payroll table record existt");
					PayRunTextName = Weekrecord.getText();
					System.out.println("The Week name is :" + PayRunTextName);
					if (PayRunTextName != null && PayRunTextName.equalsIgnoreCase(WeekName))
					{
						System.out.println("The week name" + PayRunTextName
								+ " matched");
						Weekrecord.sendKeys("");
						Weekrecord.click();
						System.out.println("payRun text " + PayRunTextName
								+ "got clicked successfully");
						break;
					}
					System.out.println("The Week name" + PayRunTextName
							+ " is not matched");
					System.out.println("The count of the Record now is :"+rownum);
					System.out.println("The count of the Counter now is :"+counter);
					if (counter < 53 && rownum == 6 && PayRunTextName != null
							&& PayRunTextName != (WeekName))
					{
						System.out
								.println("The row number of the page reached"
								+ rownum
								
								+ " Required payrun not found hence clicking the"
								+ " pagination link so that payrun search continues for next page");
						Thread.sleep(3000L);

					if (existsElementchkFor1mts(OR
								.getProperty("payrollWeeekSubPaginToDisplayAllRecords")))
						{
							getObject("payrollWeeekSubPaginToDisplayAllRecords").sendKeys("");
							getObject("payrollWeeekSubPaginToDisplayAllRecords").click();
							System.out
									.println("As the required Payrun is not found in first page,hence clicked to pagination link");
							Thread.sleep(5000L);
						}
					}
				System.out.println("Payrun not matched hence incrementing the row number");
				rownum++;
				}
				counter++;
			  }
	       }
		}
		catch (Throwable t)
		{
			System.out.println(t.getMessage());
		}
	}

	
	
	public void TaxPayRun_For_TwoWeek(String WeekName, String ExcelInputSheet,
			String FirstReportNameInApplication, String TestResultExcelFilePath)
			throws Throwable {
		try {

			/*
			 * if (existsElementchkFor1mts(OR
			 * .getProperty("payrollMonthWeeekSubPaginToDisplayAllRecords"))) {
			 * for (int i = 1; i < 4; i++) {
			 * getObject("payrollMonthWeeekSubPaginToDisplayAllRecords")
			 * .sendKeys("");
			 * getObject("payrollMonthWeeekSubPaginToDisplayAllRecords")
			 * .click();
			 * System.out.println("The expandable page got clicked for" + i +
			 * "st time"); Thread.sleep(2000L); } } Thread.sleep(4000L); if
			 * (existsElementchkFor1mts(OR.getProperty("payRunWeekTable"))) {
			 * System.out.println("table exists"); WebElement payRunWeekOneTable
			 * = getObject("payRunWeekTable"); List<WebElement> rows =
			 * payRunWeekOneTable.findElements(By
			 * .xpath(OR.getProperty("WeekOneTablerows")));
			 * java.util.Iterator<WebElement> x = rows.iterator();
			 * System.out.println("total number of week records are :" +
			 * rows.size());
			 * 
			 * rownum = 2; while (x.hasNext()) { WebElement Weekrecord =
			 * driver.findElement(By .xpath("//div[" + "5" + "]/" + "div[" +
			 * "1]/" + "div/" + "div[" + "2]/" + "table/" + "tbody/tr[" +
			 * (rownum) + "]/" + "th/" + "a"));
			 * 
			 * String weekText = Weekrecord.getText();
			 * 
			 * if (weekText != null && weekText.equalsIgnoreCase(WeekName)) {
			 * System.out.println("The week name" + WeekName + " matched");
			 * Weekrecord.sendKeys(""); Weekrecord.click(); break; } else {
			 * System.out.println("payRun text " + WeekName +
			 * "did not matched"); rownum++; }
			 * 
			 * }
			 * 
			 * }
			 * 
			 * }
			 */

			// Code Added By Swamy

			// Thread.sleep(2000L);
			if (existsElement(OR.getProperty("payRunWeekTable"))) {
				System.out.println("Thee table exists");

				WebElement payRunWeekOneTable = getObject("payRunWeekTable");

				List<WebElement> rows = payRunWeekOneTable.findElements(By
						.xpath(OR.getProperty("WeekOneTablerows")));
				lastRowCount = rows.size();
				System.out
						.println("The total pay run records for the page is equal to : "
								+ lastRowCount);
				java.util.Iterator<WebElement> x = rows.iterator();
				rownum = 2;
				counter = 1;
				while (x.hasNext()) {
					// Thread.sleep(2000L);
					System.out
							.println("Now the count of Rownum is : " + rownum);
					WebElement MonthPayRun_Record = driver.findElement(By
							.xpath("//div[" + "5" + "]/" + "div[" + "1]/"
									+ "div/" + "div[" + "2]/" + "table/"
									+ "tbody/tr[" + (rownum) + "]/" + "th/"
									+ "a"));

					if (existsWebElement(MonthPayRun_Record)) {
						System.out.println("first payroll table record existt");
						String PayRunTextName = MonthPayRun_Record.getText();
						System.out.println("The Month name is :"
								+ PayRunTextName);
						if (PayRunTextName != null
								&& PayRunTextName.equalsIgnoreCase(WeekName)) {
							System.out.println("The Month name"
									+ PayRunTextName + " matched");
							MonthPayRun_Record.sendKeys("");
							// Thread.sleep(1000L);
							MonthPayRun_Record.click();
							System.out
									.println("The Payrun record whose Month name is "
											+ WeekName
											+ "successfully clicked for processing payroll");

							break;
						}
						System.out.println("The Month name" + PayRunTextName
								+ " is not matched");
						if (counter < 27 && rownum == 6 || rownum > 10
								&& PayRunTextName != null
								&& PayRunTextName != (WeekName)) {
							System.out
									.println("The row number of the page reached"
											+ rownum

											+ " Required payrun not found hence clicking the"
											+ " pagination link so that payrun search continues for next page");

							if (existsElementchkFor1mts(OR
									.getProperty("payrollMonthWeeekSubPaginToDisplayAllRecords"))) {
								getObject(
										"payrollMonthWeeekSubPaginToDisplayAllRecords")
										.sendKeys("");
								getObject(
										"payrollMonthWeeekSubPaginToDisplayAllRecords")
										.click();
								System.out
										.println("As the required Payrun is not found in first page,hence clicked to pagination link");
								Thread.sleep(5000L);

							}
						}

						System.out
								.println("Payrun not matched hence incrementing the row number");
						rownum++;
						counter++;
					}
				}
			}

		} catch (Throwable t) {
			System.out.println(t.getMessage());

		}
	}

	int rowMatchedDDWeek = 0;

	public void ExcludeIncludeEmp112(String EmpName, String Exclinputsheet,
			String worksheetNo) throws Throwable {
		try {
			System.out.println();
			double worksheetvalue = Double.parseDouble(worksheetNo);
			DecimalFormat df = new DecimalFormat("###.#");
			String worksheetNoWithoutDecimal = df.format(worksheetvalue);
			int wNo = Integer.parseInt(worksheetNoWithoutDecimal);
			System.out.println("The converted post value is  :" + wNo);
			FileInputStream fis = new FileInputStream(
					new File(
							System.getProperty("user.dir")
									+ "\\src\\main\\java\\com\\test\\xcdhr\\Salesforce_Core_Framework1\\salesforce_XLS_Files\\"
									+ Exclinputsheet));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet spreadsheet = workbook.getSheetAt(wNo);
			totalRows = spreadsheet.getLastRowNum();
			System.out
					.println("Total rows in the processpayrollforMonthlytax worksheet is :"
							+ totalRows);
			String oldWindow = driver.getWindowHandle();
			// /////
			if (existsElementchkFor5mts(OR.getProperty("changeToDraft"))) {
				System.out.println("yest the Change to Draft button exist");
				retryForGenerateDraft();
			}
			driver.switchTo().window(driver.getWindowHandle());
			if (windowExclude) {
				windowExclude = false;
				if (getObject("excludeAllemployees").isDisplayed()) {
					System.out
							.println("the exclude include check box is displayed");
					getObject("excludeAllemployees").click();
					System.out
							.println("the exclude include check box got checked");
					Thread.sleep(1000L);
					if (getObject("excludeAllemployees").isSelected()) {
						getObject("excludeAllemployees").click();
						System.out
								.println("After checking the chckbox onceagain the exclude include check box is made UNchecked");
						// Thread.sleep(1000L);
					}
				}
			}
			if (existsElementchkFor1mts(OR
					.getProperty("excludeIncludeAllEmployees"))) {
				WebElement excludeincludeTable = driver.findElement(By.xpath(OR
						.getProperty("excludeIncludeAllEmployees")));
				List<WebElement> rows = excludeincludeTable
						.findElements(By.xpath(OR
								.getProperty("excludeIncludeAllEmployeesrows")));
				java.util.Iterator<WebElement> x = rows.iterator();
				rownumx = 1;
				while (x.hasNext()) {
					WebElement appEmployes = driver
							.findElement(By
									.xpath("//div[@id='turtle-info']/div/div/div[2]/table[2]/tbody/tr["
											+ rownumx + "]/td[2]/a"));
					String appEmployeesName = appEmployes.getText();
					System.out.println("empname is  :" + appEmployeesName);
					if (appEmployeesName != null
							&& appEmployeesName.equalsIgnoreCase(EmpName)) {
						rowMatchedDDWeek++;
						WebElement empchkBox = driver
								.findElement(By
										.xpath("//div[@id='turtle-info']/div/div/div[2]/table[2]/tbody/tr["
												+ rownumx + "]/td/input"));
						if (existsWebElement(empchkBox)) {
							empchkBox.click();
							System.out.println("");
							System.out.println("The Employee name  : "
									+ appEmployeesName
									+ "  check box got clicked");
						}
						if (totalRows == rowMatchedDDWeek) {
							System.out
									.println("The employees rows now matched,hence will now exit the window by saving the required employees");
							break;
						}
					}
					rownumx++;
				}
				if (existsElementchkFor1mts(OR.getProperty("closeWindow"))) {
					getObject("closeWindow").click();
					System.out
							.println("The save button of the popup window got clicked");
					Thread.sleep(1000L);
				}
				driver.switchTo().window(oldWindow);
				Thread.sleep(1000L);
				if (existsElementchkFor1mts(OR
						.getProperty("genratedraftPayroll"))) {
					getObject("genratedraftPayroll").sendKeys("");
					getObject("genratedraftPayroll").click();
					if (existsElementchkFor1mts(OR.getProperty("progressBar"))) {
						System.out.println("");
						System.out
								.println("The generate draft button got clicked, please wait till draft payroll process gets executed");
						Thread.sleep(4000L);
						payRunExecution();
						Thread.sleep(6000L);
						if (existsElementchkFor1mts(OR
								.getProperty("emprecordsTableAftergeneratedraft"))) {
							verifyEmpRecordInPaySummaryTable();
						}
					}
				}
			}

		} catch (Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	/*
	 * loging into desired orgs
	 */

	public int OrgFlag = 0;

	public void logingIntoDesiredORG(int OrgFlag) throws Throwable {
		switch (OrgFlag) {
		// if you want to login to QA org then
		// choose org flag as 0
		case 0:
			login_To_QA_Org();

			break; // if you want to login to Regress and automation org then
					// choose org flag as 1
		case 1:
			login_To_Automation_RegOrg();

			break;
		default:
			System.out.println("not specified to run in any org");

		}

	}

	// What Tax year needs to be run is controlled by passing the value for
	// Taxyear as below.
	// 201516 201718 2017183
	/*
	 * 2015160 = inQA Org for 201516
	 * 
	 * 2017181 = inQA Org for 201718
	 * 
	 * 2015162 = In Regress Org for 201516
	 * 
	 * 2017183 = In Regress Org for 201718
	 */

	public int TaxYear = 201819;
	public String aa = "UK";

	public void processDesiredTaxYearInputExcelFile(int TaxYear)
			throws Throwable {
		try {
			switch (TaxYear) {
			case 201516:

				Payroll_GenerateTaxrateMonthly_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite GeneralAndLargeTaxcodeMonthly.xlsx");
				GenerateTaxrateMonthly_InputExcelFile = "Payroll Suite GeneralAndLargeTaxcodeMonthly";

				Payroll_GenerateTaxrateWeekly_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite GeneralAndLargeTaxcodeWeekly.xlsx");
				GenerateTaxrateWeekly_InputExcelFile = "Payroll Suite GeneralAndLargeTaxcodeWeekly";

				Payroll_GenerateTaxrateMonth1LD0D1BR_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite GeneralTaxRateMonth1LDOD1BRNTOTK50percentRL.xlsx");
				GenerateTaxrateMonth1LD0D1BR_InputExcelFile = "Payroll Suite GeneralTaxRateMonth1LDOD1BRNTOTK50percentRL";

				Payroll_GenerateTaxrateMonth2LDOD1BRNTOTK50percentRL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite GeneralTaxRateMonth2LDOD1BRNTOTK50percentRL.xlsx");
				GenerateTaxrateMonth2LD0D1BR_InputExcelFile = "Payroll Suite GeneralTaxRateMonth2LDOD1BRNTOTK50percentRL";

				Payroll_GenerateTaxrateMonth3LDOD1BRNTOTK50percentRL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite GeneralTaxRateMonth3LDOD1BRNTOTK50percentRL.xlsx");
				GenerateTaxrateMonth3LD0D1BR_InputExcelFile = "Payroll Suite GeneralTaxRateMonth3LDOD1BRNTOTK50percentRL";

				Payroll_GenerateTaxrateMonth4LDOD1BRNTOTK50percentRL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite GeneralTaxRateMonth4LDOD1BRNTOTK50percentRL.xlsx");
				GenerateTaxrateMonth4LD0D1BR_InputExcelFile = "Payroll Suite GeneralTaxRateMonth4LDOD1BRNTOTK50percentRL";

				Payroll_GenerateTaxrateWeek1LDOD1BRNTOTK50percentRL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite GeneralTaxRateWeek1LDOD1BRNTOTK50percentRL.xlsx");
				GenerateTaxrateWeek1LDOD1BRNTOTK50percentRL_InputExcelFile = "Payroll Suite GeneralTaxRateWeek1LDOD1BRNTOTK50percentRL";

				Payroll_GenerateTaxrateWeek2LDOD1BRNTOTK50percentRL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite GeneralTaxRateWeek2LDOD1BRNTOTK50percentRL.xlsx");
				GenerateTaxrateWeek2LDOD1BRNTOTK50percentRL_InputExcelFile = "Payroll Suite GeneralTaxRateWeek2LDOD1BRNTOTK50percentRL";

				Payroll_GenerateTaxrateWeek3LDOD1BRNTOTK50percentRL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite GeneralTaxRateWeek3LDOD1BRNTOTK50percentRL.xlsx");
				GenerateTaxrateWeek3LDOD1BRNTOTK50percentRL_InputExcelFile = "Payroll Suite GeneralTaxRateWeek3LDOD1BRNTOTK50percentRL";

				Payroll_GenerateTaxrateWeek4LDOD1BRNTOTK50percentRL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite GeneralTaxRateWeek4LDOD1BRNTOTK50percentRL.xlsx");
				GenerateTaxrateWeek4LDOD1BRNTOTK50percentRL_InputExcelFile = "Payroll Suite GeneralTaxRateWeek4LDOD1BRNTOTK50percentRL";

				Payroll_IncomeTax_TCWeek1_204045VariablePayANDWeekly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite IncomeTax_TCWeek1_204045VariablePay&Weekly50RL.xlsx");
				IncomeTax_TCWeek1_204045VariablePayANDWeekly50RL_InputExcelFile = "Payroll Suite IncomeTax_TCWeek1_204045VariablePay&Weekly50RL";

				Payroll_IncomeTax_TCWeek2_204045VariablePayANDWeekly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite IncomeTax_TCWeek2_204045VariablePay&Weekly50RL.xlsx");
				IncomeTax_TCWeek2_204045VariablePayANDWeekly50RL_InputExcelFile = "Payroll Suite IncomeTax_TCWeek2_204045VariablePay&Weekly50RL";

				Payroll_IncomeTax_TCWeek3_204045VariablePayANDWeekly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite IncomeTax_TCWeek3_204045VariablePay&Weekly50RL.xlsx");
				IncomeTax_TCWeek3_204045VariablePayANDWeekly50RL_InputExcelFile = "Payroll Suite IncomeTax_TCWeek3_204045VariablePay&Weekly50RL";

				Payroll_IncomeTax_TCWeek4_204045VariablePayANDWeekly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite IncomeTax_TCWeek4_204045VariablePay&Weekly50RL.xlsx");
				IncomeTax_TCWeek4_204045VariablePayANDWeekly50RL_InputExcelFile = "Payroll Suite IncomeTax_TCWeek4_204045VariablePay&Weekly50RL";

				Payroll_IncomeTax_TCWeek5_204045VariablePayANDWeekly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite IncomeTax_TCWeek5_204045VariablePay&Weekly50RL.xlsx");
				IncomeTax_TCWeek5_204045VariablePayANDWeekly50RL_InputExcelFile = "Payroll Suite IncomeTax_TCWeek5_204045VariablePay&Weekly50RL";

				Payroll_IncomeTax_TCWeek6_204045VariablePayANDWeekly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite IncomeTax_TCWeek6_204045VariablePay&Weekly50RL.xlsx");
				IncomeTax_TCWeek6_204045VariablePayANDWeekly50RL_InputExcelFile = "Payroll Suite IncomeTax_TCWeek6_204045VariablePay&Weekly50RL";

				Payroll_IncomeTax_TCWeek7_204045VariablePayANDWeekly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite IncomeTax_TCWeek7_204045VariablePay&Weekly50RL.xlsx");
				IncomeTax_TCWeek7_204045VariablePayANDWeekly50RL_InputExcelFile = "Payroll Suite IncomeTax_TCWeek7_204045VariablePay&Weekly50RL";

				Payroll_IncomeTax_TCWeek8_204045VariablePayANDWeekly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite IncomeTax_TCWeek8_204045VariablePay&Weekly50RL.xlsx");
				IncomeTax_TCWeek8_204045VariablePayANDWeekly50RL_InputExcelFile = "Payroll Suite IncomeTax_TCWeek8_204045VariablePay&Weekly50RL";

				Payroll_IncomeTax_TCWeek9_204045VariablePayANDWeekly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite IncomeTax_TCWeek9_204045VariablePay&Weekly50RL.xlsx");
				IncomeTax_TCWeek9_204045VariablePayANDWeekly50RL_InputExcelFile = "Payroll Suite IncomeTax_TCWeek9_204045VariablePay&Weekly50RL";

				Payroll_IncomeTax_TCWeek10_204045VariablePayANDWeekly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite IncomeTax_TCWeek10_204045VariablePay&Weekly50RL.xlsx");
				IncomeTax_TCWeek10_204045VariablePayANDWeekly50RL_InputExcelFile = "Payroll Suite IncomeTax_TCWeek10_204045VariablePay&Weekly50RL";

				Payroll_IncomeTax_TCWeek11_204045VariablePayANDWeekly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite IncomeTax_TCWeek11_204045VariablePay&Weekly50RL.xlsx");
				IncomeTax_TCWeek11_204045VariablePayANDWeekly50RL_InputExcelFile = "Payroll Suite IncomeTax_TCWeek11_204045VariablePay&Weekly50RL";

				Payroll_IncomeTax_TCWeek12_204045VariablePayANDWeekly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite IncomeTax_TCWeek12_204045VariablePay&Weekly50RL.xlsx");
				IncomeTax_TCWeek12_204045VariablePayANDWeekly50RL_InputExcelFile = "Payroll Suite IncomeTax_TCWeek12_204045VariablePay&Weekly50RL";

				Payroll_IncomeTax_TCMonth1_204045VariablePayANDMonthly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Month1of204045VariablePayAnd50regulatoryLimit.xlsx");
				IncomeTax_TCMonth1_204045VariablePayANDMonthly50RL_InputExcelFile = "Payroll Suite Month1of204045VariablePayAnd50regulatoryLimit";

				Payroll_IncomeTax_TCMonth2_204045VariablePayANDMonthly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Month2of204045VariablePayAnd50regulatoryLimit.xlsx");
				IncomeTax_TCMonth2_204045VariablePayANDMonthly50RL_InputExcelFile = "Payroll Suite Month2of204045VariablePayAnd50regulatoryLimit";

				Payroll_IncomeTax_TCMonth3_204045VariablePayANDMonthly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Month3of204045VariablePayAnd50regulatoryLimit.xlsx");
				IncomeTax_TCMonth3_204045VariablePayANDMonthly50RL_InputExcelFile = "Payroll Suite Month3of204045VariablePayAnd50regulatoryLimit";

				Payroll_IncomeTax_TCMonth4_204045VariablePayANDMonthly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Month4of204045VariablePayAnd50regulatoryLimit.xlsx");
				IncomeTax_TCMonth4_204045VariablePayANDMonthly50RL_InputExcelFile = "Payroll Suite Month4of204045VariablePayAnd50regulatoryLimit";

				Payroll_IncomeTax_TCMonth5_204045VariablePayANDMonthly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Month5of204045VariablePayAnd50regulatoryLimit.xlsx");
				IncomeTax_TCMonth5_204045VariablePayANDMonthly50RL_InputExcelFile = "Payroll Suite Month5of204045VariablePayAnd50regulatoryLimit";

				Payroll_IncomeTax_TCMonth6_204045VariablePayANDMonthly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Month6of204045VariablePayAnd50regulatoryLimit.xlsx");
				IncomeTax_TCMonth6_204045VariablePayANDMonthly50RL_InputExcelFile = "Payroll Suite Month6of204045VariablePayAnd50regulatoryLimit";

				Payroll_IncomeTax_TCMonth7_204045VariablePayANDMonthly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Month7of204045VariablePayAnd50regulatoryLimit.xlsx");
				IncomeTax_TCMonth7_204045VariablePayANDMonthly50RL_InputExcelFile = "Payroll Suite Month7of204045VariablePayAnd50regulatoryLimit";

				Payroll_IncomeTax_TCMonth8_204045VariablePayANDMonthly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Month8of204045VariablePayAnd50regulatoryLimit.xlsx");
				IncomeTax_TCMonth8_204045VariablePayANDMonthly50RL_InputExcelFile = "Payroll Suite Month8of204045VariablePayAnd50regulatoryLimit";

				Payroll_IncomeTax_TCMonth9_204045VariablePayANDMonthly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Month9of204045VariablePayAnd50regulatoryLimit.xlsx");
				IncomeTax_TCMonth9_204045VariablePayANDMonthly50RL_InputExcelFile = "Payroll Suite Month9of204045VariablePayAnd50regulatoryLimit";

				Payroll_IncomeTax_TCMonth10_204045VariablePayANDMonthly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Month10of204045VariablePayAnd50regulatoryLimit.xlsx");
				IncomeTax_TCMonth10_204045VariablePayANDMonthly50RL_InputExcelFile = "Payroll Suite Month10of204045VariablePayAnd50regulatoryLimit";

				Payroll_IncomeTax_TCMonth11_204045VariablePayANDMonthly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Month11of204045VariablePayAnd50regulatoryLimit.xlsx");
				IncomeTax_TCMonth11_204045VariablePayANDMonthly50RL_InputExcelFile = "Payroll Suite Month11of204045VariablePayAnd50regulatoryLimit";

				Payroll_IncomeTax_TCMonth12_204045VariablePayANDMonthly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Month12of204045VariablePayAnd50regulatoryLimit.xlsx");
				IncomeTax_TCMonth12_204045VariablePayANDMonthly50RL_InputExcelFile = "Payroll Suite Month12of204045VariablePayAnd50regulatoryLimit";

				Payroll_CatA_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite WeeklyCatA.xlsx");
				NI_Payroll_CatA_SuiteXls_InputExcelFile = "Payroll Suite WeeklyCatA";

				Payroll_CatB_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite WeeklyCatB.xlsx");
				NI_Payroll_CatB_SuiteXls_InputExcelFile = "Payroll Suite WeeklyCatB";

				Payroll_CatC_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite WeeklyCatC.xlsx");
				NI_Payroll_CatC_SuiteXls_InputExcelFile = "Payroll Suite WeeklyCatC";

				Payroll_CatD_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite WeeklyCatD.xlsx");
				NI_Payroll_CatD_SuiteXls_InputExcelFile = "Payroll Suite WeeklyCatD";

				Payroll_CatE_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite WeeklyCatE.xlsx");
				NI_Payroll_CatE_SuiteXls_InputExcelFile = "Payroll Suite WeeklyCatE";

				Payroll_CatI_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite WeeklyCatI.xlsx");
				NI_Payroll_CatI_SuiteXls_InputExcelFile = "Payroll Suite WeeklyCatI";

				Payroll_CatJ_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite WeeklyCatJ.xlsx");
				NI_Payroll_CatJ_SuiteXls_InputExcelFile = "Payroll Suite WeeklyCatJ";

				Payroll_CatK_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite WeeklyCatK.xlsx");
				NI_Payroll_CatK_SuiteXls_InputExcelFile = "Payroll Suite WeeklyCatK";

				Payroll_CatL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite WeeklyCatL.xlsx");
				NI_Payroll_CatL_SuiteXls_InputExcelFile = "Payroll Suite WeeklyCatL";

				Payroll_CatM_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite WeeklyCatM.xlsx");
				NI_Payroll_CatM_SuiteXls_InputExcelFile = "Payroll Suite WeeklyCatM";

				Payroll_CatZ_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite WeeklyCatZ.xlsx");
				NI_Payroll_CatZ_SuiteXls_InputExcelFile = "Payroll Suite WeeklyCatZ";

				// Payroll_MonthlyCatA_SuiteXls=new
				// Xls_Reader(System.getProperty("user.dir") +
				// "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite MonthlyCatA.xlsx");

				Payroll_2WeeklyCatA_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 2WeeklyCatA.xlsx");
				NI_Payroll_2WeeklyCatA_SuiteXls_InputExcelFile = "Payroll Suite 2WeeklyCatA";

				Payroll_2WeeklyCatB_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 2WeeklyCatB.xlsx");
				NI_Payroll_2WeeklyCatB_SuiteXls_InputExcelFile = "Payroll Suite 2WeeklyCatB";

				Payroll_2WeeklyCatC_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 2WeeklyCatC.xlsx");
				NI_Payroll_2WeeklyCatC_SuiteXls_InputExcelFile = "Payroll Suite 2WeeklyCatC";

				Payroll_2WeeklyCatD_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 2WeeklyCatD.xlsx");
				NI_Payroll_2WeeklyCatD_SuiteXls_InputExcelFile = "Payroll Suite 2WeeklyCatD";

				Payroll_2WeeklyCatE_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 2WeeklyCatE.xlsx");
				NI_Payroll_2WeeklyCatE_SuiteXls_InputExcelFile = "Payroll Suite 2WeeklyCatE";

				Payroll_2WeeklyCatI_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 2WeeklyCatI.xlsx");
				NI_Payroll_2WeeklyCatI_SuiteXls_InputExcelFile = "Payroll Suite 2WeeklyCatI";

				Payroll_2WeeklyCatJ_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 2WeeklyCatJ.xlsx");
				NI_Payroll_2WeeklyCatJ_SuiteXls_InputExcelFile = "Payroll Suite 2WeeklyCatJ";

				Payroll_2WeeklyCatK_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 2WeeklyCatK.xlsx");
				NI_Payroll_2WeeklyCatK_SuiteXls_InputExcelFile = "Payroll Suite 2WeeklyCatK";

				Payroll_2WeeklyCatL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 2WeeklyCatL.xlsx");
				NI_Payroll_2WeeklyCatL_SuiteXls_InputExcelFile = "Payroll Suite 2WeeklyCatL";

				Payroll_2WeeklyCatM_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 2WeeklyCatM.xlsx");
				NI_Payroll_2WeeklyCatM_SuiteXls_InputExcelFile = "Payroll Suite 2WeeklyCatM";

				Payroll_2WeeklyCatZ_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 2WeeklyCatZ.xlsx");
				NI_Payroll_2WeeklyCatZ_SuiteXls_InputExcelFile = "Payroll Suite 2WeeklyCatZ";

				Payroll_4WeeklyCatA_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 4WeeklyCatA.xlsx");
				NI_Payroll_4WeeklyCatA_SuiteXls_InputExcelFile = "Payroll Suite 4WeeklyCatA";

				Payroll_4WeeklyCatB_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 4WeeklyCatB.xlsx");
				NI_Payroll_4WeeklyCatB_SuiteXls_InputExcelFile = "Payroll Suite 4WeeklyCatB";

				Payroll_4WeeklyCatC_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 4WeeklyCatC.xlsx");
				NI_Payroll_4WeeklyCatC_SuiteXls_InputExcelFile = "Payroll Suite 4WeeklyCatC";

				Payroll_4WeeklyCatD_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 4WeeklyCatD.xlsx");
				NI_Payroll_4WeeklyCatD_SuiteXls_InputExcelFile = "Payroll Suite 4WeeklyCatD";

				Payroll_4WeeklyCatE_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 4WeeklyCatE.xlsx");
				NI_Payroll_4WeeklyCatE_SuiteXls_InputExcelFile = "Payroll Suite 4WeeklyCatE";

				Payroll_4WeeklyCatI_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 4WeeklyCatI.xlsx");
				NI_Payroll_4WeeklyCatI_SuiteXls_InputExcelFile = "Payroll Suite 4WeeklyCatI";

				Payroll_4WeeklyCatJ_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 4WeeklyCatJ.xlsx");
				NI_Payroll_4WeeklyCatJ_SuiteXls_InputExcelFile = "Payroll Suite 4WeeklyCatJ";

				Payroll_4WeeklyCatK_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 4WeeklyCatK.xlsx");
				NI_Payroll_4WeeklyCatK_SuiteXls_InputExcelFile = "Payroll Suite 4WeeklyCatK";

				Payroll_4WeeklyCatL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 4WeeklyCatL.xlsx");
				NI_Payroll_4WeeklyCatL_SuiteXls_InputExcelFile = "Payroll Suite 4WeeklyCatL";

				Payroll_4WeeklyCatM_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 4WeeklyCatM.xlsx");
				NI_Payroll_4WeeklyCatM_SuiteXls_InputExcelFile = "Payroll Suite 4WeeklyCatM";

				Payroll_4WeeklyCatZ_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 4WeeklyCatZ.xlsx");
				NI_Payroll_4WeeklyCatZ_SuiteXls_InputExcelFile = "Payroll Suite 4WeeklyCatZ";

				Payroll_MonthlyCatA_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite MonthlyCatA.xlsx");
				NI_Payroll_MonthlyCatA_SuiteXls_InputExcelFile = "Payroll Suite MonthlyCatA";

				Payroll_MonthlyCatB_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite MonthlyCatB.xlsx");
				NI_Payroll_MonthlyCatB_SuiteXls_InputExcelFile = "Payroll Suite MonthlyCatB";

				Payroll_MonthlyCatC_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite MonthlyCatC.xlsx");
				NI_Payroll_MonthlyCatC_SuiteXls_InputExcelFile = "Payroll Suite MonthlyCatC";

				Payroll_MonthlyCatD_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite MonthlyCatD.xlsx");
				NI_Payroll_MonthlyCatD_SuiteXls_InputExcelFile = "Payroll Suite MonthlyCatD";

				Payroll_MonthlyCatE_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite MonthlyCatE.xlsx");
				NI_Payroll_MonthlyCatE_SuiteXls_InputExcelFile = "Payroll Suite MonthlyCatE";

				Payroll_MonthlyCatI_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite MonthlyCatI.xlsx");
				NI_Payroll_MonthlyCatI_SuiteXls_InputExcelFile = "Payroll Suite MonthlyCatI";

				Payroll_MonthlyCatJ_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite MonthlyCatJ.xlsx");
				NI_Payroll_MonthlyCatJ_SuiteXls_InputExcelFile = "Payroll Suite MonthlyCatJ";

				Payroll_MonthlyCatK_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite MonthlyCatK.xlsx");
				NI_Payroll_MonthlyCatK_SuiteXls_InputExcelFile = "Payroll Suite MonthlyCatK";

				Payroll_MonthlyCatL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite MonthlyCatL.xlsx");
				NI_Payroll_MonthlyCatL_SuiteXls_InputExcelFile = "Payroll Suite MonthlyCatL";

				Payroll_MonthlyCatM_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite MonthlyCatM.xlsx");
				NI_Payroll_MonthlyCatM_SuiteXls_InputExcelFile = "Payroll Suite MonthlyCatM";

				Payroll_MonthlyCatZ_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite MonthlyCatZ.xlsx");
				NI_Payroll_MonthlyCatZ_SuiteXls_InputExcelFile = "Payroll Suite MonthlyCatZ";

				// new Xls_Reader(System.getProperty("user.dir") +
				// "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//
				Payroll_NI_DirectorAsEmployee_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite NI Director as employee.xlsx");
				Payroll_NI_DirectorAsEmployee_SuiteXls_InputExcelFile = "Payroll Suite NI Director as employee";

				Payroll_NI_DirectorProrata_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite NI Director Prorata.xlsx");
				Payroll_NI_DirectorAsProrata_SuiteXls_InputExcelFile = "Payroll Suite NI Director Prorata";

				Payroll_NI_CeaseandRecommence_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite NI Director Cease and recommence.xlsx");
				Payroll_NI_CeaseandRecommence_SuiteXls_InputExcelFile = "Payroll Suite NI Director Cease and recommence";

				Payroll_NI_DirectorReachesFor_PensionAge_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite NI Director ReachesPensionAge.xlsx");
				Payroll_NI_ReachesANDPension_SuiteXls_InputExcelFile = "Payroll Suite NI Director ReachesPensionAge";

				Payroll_NI_Deferment_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite NI Director Deferment.xlsx");
				Payroll_NI_Deferment_SuiteXls_InputExcelFile = "Payroll Suite NI Director Deferment";

				Payroll_NI_Director_Under21_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite NI Director Under21.xlsx");
				Payroll_NI_Under21_SuiteXls_InputExcelFile = "Payroll Suite NI Director Under21";

				Payroll_Statutory_maternitypay_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite StatutoryMaternityPay.xlsx");
				Statutory_MaternityPay_SuiteXls_InputExcelFile = "Payroll Suite StatutoryMaternityPay";

				// new Xls_Reader(System.getProperty("user.dir") +
				// "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//
				Payroll_Statutory_Adoption_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite StatutoryAdoptionPay.xlsx");
				Statutory_AdoptionPay_SuiteXls_InputExcelFile = "Payroll Suite StatutoryAdoptionPay";

				Payroll_Statutory_Paternitypay_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite StatutoryPaternityPay.xlsx");
				Statutory_StatutoryPaternityPay_InputExcelFile = "Payroll Suite StatutoryPaternityPay";

				Payroll_Statutory_Paternitypay_Case2_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite StatutoryPaternityPayCase2.xlsx");
				StatutoryPaternityPayCase2_InputExcelFile = "Payroll Suite StatutoryPaternityPayCase2";

				Payroll_Statutory_AdoptionPaternitypay_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Statutory Adoption PaternityPay.xlsx");
				SAPP_InputExcelFile = "Payroll Suite Statutory Adoption PaternityPay";

				Payroll_Statutory_SharedParentalpay_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Statutory Shared ParentalPay.xlsx");
				SharedParental_InputExcelFile = "Payroll Suite Statutory Shared ParentalPay";

				Payroll_SSP_ProcessPayroll_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Statutory SSP201718.xlsx");
				SSP_InputExcelFile = "Payroll Suite Statutory SSP201718";

				break; // if you want to process desired Tax year's input sheet
						// then pass that tax year as parameter

			case 201718:
				Payroll_ResetCategory_Taxyear201718_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite ResetNICategory for TaxYear201718.xlsx");
				PayrollSuiteResetNICategory_InputExcelFile = "Payroll Suite ResetNICategory for TaxYear201718";

				Payroll_GenerateTaxrateMonthly_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite GeneralAndLargeTaxcodeMonthly201718.xlsx");
				GenerateTaxrateMonthly_InputExcelFile = "Payroll Suite GeneralAndLargeTaxcodeMonthly201718";

				Payroll_GenerateTaxrateWeekly_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite GeneralAndLargeTaxcodeWeekly201718.xlsx");
				GenerateTaxrateWeekly_InputExcelFile = "Payroll Suite GeneralAndLargeTaxcodeWeekly201718";

				Payroll_GenerateTaxrateMonth1LD0D1BR_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite GeneralTaxRateMonth1LDOD1BRNTOTK50percentRL201718.xlsx");
				GenerateTaxrateMonth1LD0D1BR_InputExcelFile = "Payroll Suite GeneralTaxRateMonth1LDOD1BRNTOTK50percentRL201718";

				Payroll_GenerateTaxrateMonth2LDOD1BRNTOTK50percentRL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite GeneralTaxRateMonth2LDOD1BRNTOTK50percentRL201718.xlsx");
				GenerateTaxrateMonth2LD0D1BR_InputExcelFile = "Payroll Suite GeneralTaxRateMonth2LDOD1BRNTOTK50percentRL201718";

				Payroll_GenerateTaxrateMonth3LDOD1BRNTOTK50percentRL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite GeneralTaxRateMonth3LDOD1BRNTOTK50percentRL201718.xlsx");
				GenerateTaxrateMonth3LD0D1BR_InputExcelFile = "Payroll Suite GeneralTaxRateMonth3LDOD1BRNTOTK50percentRL201718";

				Payroll_GenerateTaxrateMonth4LDOD1BRNTOTK50percentRL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite GeneralTaxRateMonth4LDOD1BRNTOTK50percentRL201718.xlsx");
				GenerateTaxrateMonth4LD0D1BR_InputExcelFile = "Payroll Suite GeneralTaxRateMonth4LDOD1BRNTOTK50percentRL201718";

				Payroll_GenerateTaxrateWeek1LDOD1BRNTOTK50percentRL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite GeneralTaxRateWeek1LDOD1BRNTOTK50percentRL201718.xlsx");
				GenerateTaxrateWeek1LDOD1BRNTOTK50percentRL_InputExcelFile = "Payroll Suite GeneralTaxRateWeek1LDOD1BRNTOTK50percentRL201718";

				Payroll_GenerateTaxrateWeek2LDOD1BRNTOTK50percentRL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite GeneralTaxRateWeek2LDOD1BRNTOTK50percentRL201718.xlsx");
				GenerateTaxrateWeek2LDOD1BRNTOTK50percentRL_InputExcelFile = "Payroll Suite GeneralTaxRateWeek2LDOD1BRNTOTK50percentRL201718";

				Payroll_GenerateTaxrateWeek3LDOD1BRNTOTK50percentRL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite GeneralTaxRateWeek3LDOD1BRNTOTK50percentRL201718.xlsx");
				GenerateTaxrateWeek3LDOD1BRNTOTK50percentRL_InputExcelFile = "Payroll Suite GeneralTaxRateWeek3LDOD1BRNTOTK50percentRL201718";

				Payroll_GenerateTaxrateWeek4LDOD1BRNTOTK50percentRL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite GeneralTaxRateWeek4LDOD1BRNTOTK50percentRL201718.xlsx");
				GenerateTaxrateWeek4LDOD1BRNTOTK50percentRL_InputExcelFile = "Payroll Suite GeneralTaxRateWeek4LDOD1BRNTOTK50percentRL201718";

				Payroll_IncomeTax_TCWeek1_204045VariablePayANDWeekly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite IncomeTax_TCWeek1_204045VariablePay&Weekly50RL201718.xlsx");
				IncomeTax_TCWeek1_204045VariablePayANDWeekly50RL_InputExcelFile = "Payroll Suite IncomeTax_TCWeek1_204045VariablePay&Weekly50RL201718";

				Payroll_IncomeTax_TCWeek2_204045VariablePayANDWeekly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite IncomeTax_TCWeek2_204045VariablePay&Weekly50RL201718.xlsx");
				IncomeTax_TCWeek2_204045VariablePayANDWeekly50RL_InputExcelFile = "Payroll Suite IncomeTax_TCWeek2_204045VariablePay&Weekly50RL201718";

				Payroll_IncomeTax_TCWeek3_204045VariablePayANDWeekly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite IncomeTax_TCWeek3_204045VariablePay&Weekly50RL201718.xlsx");
				IncomeTax_TCWeek3_204045VariablePayANDWeekly50RL_InputExcelFile = "Payroll Suite IncomeTax_TCWeek3_204045VariablePay&Weekly50RL201718";

				Payroll_IncomeTax_TCWeek4_204045VariablePayANDWeekly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite IncomeTax_TCWeek4_204045VariablePay&Weekly50RL201718.xlsx");
				IncomeTax_TCWeek4_204045VariablePayANDWeekly50RL_InputExcelFile = "Payroll Suite IncomeTax_TCWeek4_204045VariablePay&Weekly50RL201718";

				Payroll_IncomeTax_TCWeek5_204045VariablePayANDWeekly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite IncomeTax_TCWeek5_204045VariablePay&Weekly50RL201718.xlsx");
				IncomeTax_TCWeek5_204045VariablePayANDWeekly50RL_InputExcelFile = "Payroll Suite IncomeTax_TCWeek5_204045VariablePay&Weekly50RL201718";

				Payroll_IncomeTax_TCWeek6_204045VariablePayANDWeekly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite IncomeTax_TCWeek6_204045VariablePay&Weekly50RL201718.xlsx");
				IncomeTax_TCWeek6_204045VariablePayANDWeekly50RL_InputExcelFile = "Payroll Suite IncomeTax_TCWeek6_204045VariablePay&Weekly50RL201718";

				Payroll_IncomeTax_TCWeek7_204045VariablePayANDWeekly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite IncomeTax_TCWeek7_204045VariablePay&Weekly50RL201718.xlsx");
				IncomeTax_TCWeek7_204045VariablePayANDWeekly50RL_InputExcelFile = "Payroll Suite IncomeTax_TCWeek7_204045VariablePay&Weekly50RL201718";

				Payroll_IncomeTax_TCWeek8_204045VariablePayANDWeekly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite IncomeTax_TCWeek8_204045VariablePay&Weekly50RL201718.xlsx");
				IncomeTax_TCWeek8_204045VariablePayANDWeekly50RL_InputExcelFile = "Payroll Suite IncomeTax_TCWeek8_204045VariablePay&Weekly50RL201718";

				Payroll_IncomeTax_TCWeek9_204045VariablePayANDWeekly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite IncomeTax_TCWeek9_204045VariablePay&Weekly50RL201718.xlsx");
				IncomeTax_TCWeek9_204045VariablePayANDWeekly50RL_InputExcelFile = "Payroll Suite IncomeTax_TCWeek9_204045VariablePay&Weekly50RL201718";

				Payroll_IncomeTax_TCWeek10_204045VariablePayANDWeekly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite IncomeTax_TCWeek10_204045VariablePay&Weekly50RL201718.xlsx");
				IncomeTax_TCWeek10_204045VariablePayANDWeekly50RL_InputExcelFile = "Payroll Suite IncomeTax_TCWeek10_204045VariablePay&Weekly50RL201718";

				Payroll_IncomeTax_TCWeek11_204045VariablePayANDWeekly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite IncomeTax_TCWeek11_204045VariablePay&Weekly50RL201718.xlsx");
				IncomeTax_TCWeek11_204045VariablePayANDWeekly50RL_InputExcelFile = "Payroll Suite IncomeTax_TCWeek11_204045VariablePay&Weekly50RL201718";

				Payroll_IncomeTax_TCWeek12_204045VariablePayANDWeekly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite IncomeTax_TCWeek12_204045VariablePay&Weekly50RL201718.xlsx");
				IncomeTax_TCWeek12_204045VariablePayANDWeekly50RL_InputExcelFile = "Payroll Suite IncomeTax_TCWeek12_204045VariablePay&Weekly50RL201718";

				Payroll_IncomeTax_TCMonth1_204045VariablePayANDMonthly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Month1of204045VariablePayAnd50regulatoryLimit201718.xlsx");
				IncomeTax_TCMonth1_204045VariablePayANDMonthly50RL_InputExcelFile = "Payroll Suite Month1of204045VariablePayAnd50regulatoryLimit201718";

				Payroll_IncomeTax_TCMonth2_204045VariablePayANDMonthly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Month2of204045VariablePayAnd50regulatoryLimit201718.xlsx");
				IncomeTax_TCMonth2_204045VariablePayANDMonthly50RL_InputExcelFile = "Payroll Suite Month2of204045VariablePayAnd50regulatoryLimit201718";

				Payroll_IncomeTax_TCMonth3_204045VariablePayANDMonthly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Month3of204045VariablePayAnd50regulatoryLimit201718.xlsx");
				IncomeTax_TCMonth3_204045VariablePayANDMonthly50RL_InputExcelFile = "Payroll Suite Month3of204045VariablePayAnd50regulatoryLimit201718";

				Payroll_IncomeTax_TCMonth4_204045VariablePayANDMonthly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Month4of204045VariablePayAnd50regulatoryLimit201718.xlsx");
				IncomeTax_TCMonth4_204045VariablePayANDMonthly50RL_InputExcelFile = "Payroll Suite Month4of204045VariablePayAnd50regulatoryLimit201718";

				Payroll_IncomeTax_TCMonth5_204045VariablePayANDMonthly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Month5of204045VariablePayAnd50regulatoryLimit201718.xlsx");
				IncomeTax_TCMonth5_204045VariablePayANDMonthly50RL_InputExcelFile = "Payroll Suite Month5of204045VariablePayAnd50regulatoryLimit201718";

				Payroll_IncomeTax_TCMonth6_204045VariablePayANDMonthly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Month6of204045VariablePayAnd50regulatoryLimit201718.xlsx");
				IncomeTax_TCMonth6_204045VariablePayANDMonthly50RL_InputExcelFile = "Payroll Suite Month6of204045VariablePayAnd50regulatoryLimit201718";

				Payroll_IncomeTax_TCMonth7_204045VariablePayANDMonthly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Month7of204045VariablePayAnd50regulatoryLimit201718.xlsx");
				IncomeTax_TCMonth7_204045VariablePayANDMonthly50RL_InputExcelFile = "Payroll Suite Month7of204045VariablePayAnd50regulatoryLimit201718";

				Payroll_IncomeTax_TCMonth8_204045VariablePayANDMonthly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Month8of204045VariablePayAnd50regulatoryLimit201718.xlsx");
				IncomeTax_TCMonth8_204045VariablePayANDMonthly50RL_InputExcelFile = "Payroll Suite Month8of204045VariablePayAnd50regulatoryLimit201718";

				Payroll_IncomeTax_TCMonth9_204045VariablePayANDMonthly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Month9of204045VariablePayAnd50regulatoryLimit201718.xlsx");
				IncomeTax_TCMonth9_204045VariablePayANDMonthly50RL_InputExcelFile = "Payroll Suite Month9of204045VariablePayAnd50regulatoryLimit201718";

				Payroll_IncomeTax_TCMonth10_204045VariablePayANDMonthly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Month10of204045VariablePayAnd50regulatoryLimit201718.xlsx");
				IncomeTax_TCMonth10_204045VariablePayANDMonthly50RL_InputExcelFile = "Payroll Suite Month10of204045VariablePayAnd50regulatoryLimit201718";

				Payroll_IncomeTax_TCMonth11_204045VariablePayANDMonthly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Month11of204045VariablePayAnd50regulatoryLimit201718.xlsx");
				IncomeTax_TCMonth11_204045VariablePayANDMonthly50RL_InputExcelFile = "Payroll Suite Month11of204045VariablePayAnd50regulatoryLimit201718";

				Payroll_IncomeTax_TCMonth12_204045VariablePayANDMonthly50RL_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Month12of204045VariablePayAnd50regulatoryLimit201718.xlsx");
				IncomeTax_TCMonth12_204045VariablePayANDMonthly50RL_InputExcelFile = "Payroll Suite Month12of204045VariablePayAnd50regulatoryLimit201718";

				Payroll_CatA_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite WeeklyCatA201718.xlsx");
				NI_Payroll_CatA_SuiteXls_InputExcelFile = "Payroll Suite WeeklyCatA201718";

				Payroll_CatB_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite WeeklyCatB201718.xlsx");
				NI_Payroll_CatB_SuiteXls_InputExcelFile = "Payroll Suite WeeklyCatB201718";

				Payroll_CatC_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite WeeklyCatC201718.xlsx");
				NI_Payroll_CatC_SuiteXls_InputExcelFile = "Payroll Suite WeeklyCatC201718";

				Payroll_CatH_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite WeeklyCatH201718.xlsx");
				NI_Payroll_CatH_SuiteXls_InputExcelFile = "Payroll Suite WeeklyCatH201718";

				Payroll_CatJ_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite WeeklyCatJ201718.xlsx");
				NI_Payroll_CatJ_SuiteXls_InputExcelFile = "Payroll Suite WeeklyCatJ201718";

				Payroll_CatM_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite WeeklyCatM201718.xlsx");
				NI_Payroll_CatM_SuiteXls_InputExcelFile = "Payroll Suite WeeklyCatM201718";

				Payroll_CatZ_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite WeeklyCatZ201718.xlsx");
				NI_Payroll_CatZ_SuiteXls_InputExcelFile = "Payroll Suite WeeklyCatZ201718";

				Payroll_2WeeklyCatA_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 2WeeklyCatA201718.xlsx");
				NI_Payroll_2WeeklyCatA_SuiteXls_InputExcelFile = "Payroll Suite 2WeeklyCatA201718";

				Payroll_2WeeklyCatB_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 2WeeklyCatB201718.xlsx");
				NI_Payroll_2WeeklyCatB_SuiteXls_InputExcelFile = "Payroll Suite 2WeeklyCatB201718";

				Payroll_2WeeklyCatC_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 2WeeklyCatC201718.xlsx");
				NI_Payroll_2WeeklyCatC_SuiteXls_InputExcelFile = "Payroll Suite 2WeeklyCatC201718";

				Payroll_2WeeklyCatH_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 2WeeklyCatH201718.xlsx");
				NI_Payroll_2WeeklyCatH_SuiteXls_InputExcelFile = "Payroll Suite 2WeeklyCatH201718";

				Payroll_2WeeklyCatJ_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 2WeeklyCatJ201718.xlsx");
				NI_Payroll_2WeeklyCatJ_SuiteXls_InputExcelFile = "Payroll Suite 2WeeklyCatJ201718";

				Payroll_2WeeklyCatM_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 2WeeklyCatM201718.xlsx");
				NI_Payroll_2WeeklyCatM_SuiteXls_InputExcelFile = "Payroll Suite 2WeeklyCatM201718";

				Payroll_2WeeklyCatZ_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 2WeeklyCatZ201718.xlsx");
				NI_Payroll_2WeeklyCatZ_SuiteXls_InputExcelFile = "Payroll Suite 2WeeklyCatZ201718";

				Payroll_4WeeklyCatA_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 4WeeklyCatA201718.xlsx");
				NI_Payroll_4WeeklyCatA_SuiteXls_InputExcelFile = "Payroll Suite 4WeeklyCatA201718";

				Payroll_4WeeklyCatB_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 4WeeklyCatB201718.xlsx");
				NI_Payroll_4WeeklyCatB_SuiteXls_InputExcelFile = "Payroll Suite 4WeeklyCatB201718";

				Payroll_4WeeklyCatC_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 4WeeklyCatC201718.xlsx");
				NI_Payroll_4WeeklyCatC_SuiteXls_InputExcelFile = "Payroll Suite 4WeeklyCatC201718";

				Payroll_4WeeklyCatH_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 4WeeklyCatH201718.xlsx");
				NI_Payroll_4WeeklyCatH_SuiteXls_InputExcelFile = "Payroll Suite 4WeeklyCatH201718";

				Payroll_4WeeklyCatJ_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 4WeeklyCatJ201718.xlsx");
				NI_Payroll_4WeeklyCatJ_SuiteXls_InputExcelFile = "Payroll Suite 4WeeklyCatJ201718";

				Payroll_4WeeklyCatM_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 4WeeklyCatM201718.xlsx");
				NI_Payroll_4WeeklyCatM_SuiteXls_InputExcelFile = "Payroll Suite 4WeeklyCatM201718";

				Payroll_4WeeklyCatM_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 4WeeklyCatZ201718.xlsx");
				NI_Payroll_4WeeklyCatZ_SuiteXls_InputExcelFile = "Payroll Suite 4WeeklyCatZ201718";

				Payroll_MonthlyCatA_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite MonthlyCatA201718.xlsx");
				NI_Payroll_MonthlyCatA_SuiteXls_InputExcelFile = "Payroll Suite MonthlyCatA201718";

				Payroll_MonthlyCatB_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite MonthlyCatB201718.xlsx");
				NI_Payroll_MonthlyCatB_SuiteXls_InputExcelFile = "Payroll Suite MonthlyCatB201718";

				Payroll_MonthlyCatC_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite MonthlyCatC201718.xlsx");
				NI_Payroll_MonthlyCatC_SuiteXls_InputExcelFile = "Payroll Suite MonthlyCatC201718";

				Payroll_MonthlyCatH_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite MonthlyCatH201718.xlsx");
				NI_Payroll_MonthlyCatH_SuiteXls_InputExcelFile = "Payroll Suite MonthlyCatH201718";

				Payroll_MonthlyCatJ_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite MonthlyCatJ201718.xlsx");
				NI_Payroll_MonthlyCatJ_SuiteXls_InputExcelFile = "Payroll Suite MonthlyCatJ201718";

				Payroll_MonthlyCatM_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite MonthlyCatM201718.xlsx");
				NI_Payroll_MonthlyCatM_SuiteXls_InputExcelFile = "Payroll Suite MonthlyCatM201718";

				Payroll_MonthlyCatZ_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite MonthlyCatZ201718.xlsx");
				NI_Payroll_MonthlyCatZ_SuiteXls_InputExcelFile = "Payroll Suite MonthlyCatZ201718";

				Payroll_NI_DirectorAsEmployee_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite NI Director as employee201718.xlsx");
				Payroll_NI_DirectorAsEmployee_SuiteXls_InputExcelFile = "Payroll Suite NI Director as employee201718";

			
				Payroll_NI_DirectorProrata_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite NI Director Prorata201718.xlsx");
				Payroll_NI_DirectorAsProrata_SuiteXls_InputExcelFile = "Payroll Suite NI Director Prorata201718";

				Payroll_NI_CeaseandRecommence_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite NI Director Cease and recommence201718.xlsx");
				Payroll_NI_CeaseandRecommence_SuiteXls_InputExcelFile = "Payroll Suite NI Director Cease and recommence201718";

				Payroll_NI_DirectorReachesFor_PensionAge_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite NI Director ReachesPensionAge201718.xlsx");
				Payroll_NI_ReachesANDPension_SuiteXls_InputExcelFile = "Payroll Suite NI Director ReachesPensionAge201718";

				Payroll_NI_Deferment_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite NI Director Deferment201718.xlsx");
				Payroll_NI_Deferment_SuiteXls_InputExcelFile = "Payroll Suite NI Director Deferment201718";

				Payroll_NI_Director_Under21_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite NI Director Under21201718.xlsx");
				Payroll_NI_Under21_SuiteXls_InputExcelFile = "Payroll Suite NI Director Under21201718";

				Payroll_NI_Director_U25Aprentice_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite NI Director U25Aprentice201718.xlsx");
				Payroll_NI_U25Apprentice_SuiteXls_InputExcelFile = "Payroll Suite NI Director U25Aprentice201718";

				Payroll_Statutory_maternitypay_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite StatutoryMaternityPay201718.xlsx");
				Statutory_MaternityPay_SuiteXls_InputExcelFile = "Payroll Suite StatutoryMaternityPay201718";


				Payroll_Statutory_Adoption_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite StatutoryAdoptionPay201718.xlsx");
				Statutory_AdoptionPay_SuiteXls_InputExcelFile = "Payroll Suite StatutoryAdoptionPay201718";

				Payroll_Statutory_Paternitypay_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite StatutoryPaternityPay201718.xlsx");
				Statutory_StatutoryPaternityPay_InputExcelFile = "Payroll Suite StatutoryPaternityPay201718";

				Payroll_Statutory_Paternitypay_Case2_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite StatutoryPaternityPayCase2201718.xlsx");
				StatutoryPaternityPayCase2_InputExcelFile = "Payroll Suite StatutoryPaternityPayCase2201718";

				Payroll_Statutory_AdoptionPaternitypay_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Statutory Adoption PaternityPay201718.xlsx");
				SAPP_InputExcelFile = "Payroll Suite Statutory Adoption PaternityPay201718";

				Payroll_Statutory_SharedParentalpay_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Statutory Shared ParentalPay201718.xlsx");
				SharedParental_InputExcelFile = "Payroll Suite Statutory Shared ParentalPay201718";

				Payroll_SSP_ProcessPayroll_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite Statutory SSP201718.xlsx");
				SSP_InputExcelFile = "Payroll Suite Statutory SSP201718";

				Payroll_CreateEmployees_For_RecognitionScenarious_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
						+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Create RTI Employees201718.xlsx");
				EmployeeCreation_For_PayrollRecognition_Inputsheet = "Create RTI Employees201718";
				
				Payroll_RecognitionScenarious_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Recognition Scenario201718.xlsx");
				PayrollRecognition_Inputsheet = "Payroll Recognition Scenario201718";

				Payroll_RecognitionScenarioTwo_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Recognition ScenarioTwo201718.xlsx");
				PayrollRecognitionScenario2_Inputsheet = "Payroll Recognition ScenarioTwo201718";

				Payroll_RecognitionScenarioThree_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Recognition ScenarioThree201718.xlsx");
				PayrollRecognitionScenario3_Inputsheet = "Payroll Recognition ScenarioThree201718";

				Payroll_RecognitionScenarioFour_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Recognition ScenarioFour201718.xlsx");
				PayrollRecognitionScenario4_Inputsheet = "Payroll Recognition ScenarioFour201718";

				Payroll_RecognitionScenarioFive_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Recognition ScenarioFive201718.xlsx");
				PayrollRecognitionScenario5_Inputsheet = "Payroll Recognition ScenarioFive201718";

				Payroll_RecognitionScenarioSix_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Recognition ScenarioSix201718.xlsx");
				PayrollRecognitionScenario6_Inputsheet = "Payroll Recognition ScenarioSix201718";

				Payroll_RecognitionScenarioSeven_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Recognition ScenarioSeven201718.xlsx");
				PayrollRecognitionScenario7_Inputsheet = "Payroll Recognition ScenarioSeven201718";

				break;
				
				
				
						
			case 201819:
				if(aa.equalsIgnoreCase("UK"))
				{
					Payroll_2WeeklyCatA_SuiteXls = new Xls_Reader(
							System.getProperty("user.dir")
									+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 2WeeklyCatA201819.xlsx");
					NI_Payroll_2WeeklyCatA_SuiteXls_InputExcelFile = "Payroll Suite 2WeeklyCatA201819";
				}
				else
				{
					
				}
				System.out
				.println("You have specified to run tax year 201819");
				
				/*
				 * Payroll Tax TaxMonth1CSBRNTK50PercentRegulatory Weekly SuiteXls for 201819 F.Y
				 * 	
				 */
				
				TaxPayroll_TaxWeek1CSBRNTK50PercentRegulatory_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
						+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite TaxWeek1CSBRNTK50PercentRegulatory201819.xlsx");
				TaxPayroll_TaxWeek1CSBRNTK50PercentRegulatory_Inputsheet = "Payroll Suite TaxWeek1CSBRNTK50PercentRegulatory201819";
				
				TaxPayroll_TaxWeek2CSBRNTK50PercentRegulatory_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
						+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite TaxWeek2CSBRNTK50PercentRegulatory201819.xlsx");
				TaxPayroll_TaxWeek2CSBRNTK50PercentRegulatory_Inputsheet = "Payroll Suite TaxWeek2CSBRNTK50PercentRegulatory201819";
				
				TaxPayroll_TaxWeek3CSBRNTK50PercentRegulatory_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
						+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite TaxWeek3CSBRNTK50PercentRegulatory201819.xlsx");
				TaxPayroll_TaxWeek3CSBRNTK50PercentRegulatory_Inputsheet = "Payroll Suite TaxWeek3CSBRNTK50PercentRegulatory201819";
				
				TaxPayroll_TaxWeek4CSBRNTK50PercentRegulatory_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
						+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite TaxWeek4CSBRNTK50PercentRegulatory201819.xlsx");
				TaxPayroll_TaxWeek4CSBRNTK50PercentRegulatory_Inputsheet = "Payroll Suite TaxWeek4CSBRNTK50PercentRegulatory201819";
				
				TaxPayroll_TaxWeek5CSBRNTK50PercentRegulatory_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
						+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite TaxWeek5CSBRNTK50PercentRegulatory201819.xlsx");
				TaxPayroll_TaxWeek5CSBRNTK50PercentRegulatory_Inputsheet = "Payroll Suite TaxWeek5CSBRNTK50PercentRegulatory201819";
				
				
				TaxPayroll_TaxWeek6CSBRNTK50PercentRegulatory_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
						+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite TaxWeek6CSBRNTK50PercentRegulatory201819.xlsx");
				TaxPayroll_TaxWeek6CSBRNTK50PercentRegulatory_Inputsheet = "Payroll Suite TaxWeek6CSBRNTK50PercentRegulatory201819";
				
				TaxPayroll_TaxWeek7CSBRNTK50PercentRegulatory_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
						+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite TaxWeek7CSBRNTK50PercentRegulatory201819.xlsx");
				TaxPayroll_TaxWeek7CSBRNTK50PercentRegulatory_Inputsheet = "Payroll Suite TaxWeek7CSBRNTK50PercentRegulatory201819";
				
				TaxPayroll_TaxWeek8CSBRNTK50PercentRegulatory_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
						+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite TaxWeek8CSBRNTK50PercentRegulatory201819.xlsx");
				TaxPayroll_TaxWeek8CSBRNTK50PercentRegulatory_Inputsheet = "Payroll Suite TaxWeek8CSBRNTK50PercentRegulatory201819";
				
				TaxPayroll_TaxWeek9CSBRNTK50PercentRegulatory_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
						+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite TaxWeek9CSBRNTK50PercentRegulatory201819.xlsx");
				TaxPayroll_TaxWeek9CSBRNTK50PercentRegulatory_Inputsheet = "Payroll Suite TaxWeek9CSBRNTK50PercentRegulatory201819";
				
				TaxPayroll_TaxWeek10CSBRNTK50PercentRegulatory_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
						+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite TaxWeek10CSBRNTK50PercentRegulatory201819.xlsx");
				TaxPayroll_TaxWeek10CSBRNTK50PercentRegulatory_Inputsheet = "Payroll Suite TaxWeek10CSBRNTK50PercentRegulatory201819";
				
				/*
				 * Payroll Tax TaxMonth1CSBRNTK50PercentRegulatory monthly SuiteXls for 201819 F.Y
				 * 	
				 */
				
				
				TaxPayroll_TaxMonth1CSBRNTK50PercentRegulatory_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
						+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite TaxMonth1CSBRNTK50PercentRegulatory201819.xlsx");
				TaxPayroll_TaxMonth1CSBRNTK50PercentRegulatory_Inputsheet = "Payroll Suite TaxMonth1CSBRNTK50PercentRegulatory201819";
				
				TaxPayroll_TaxMonth2CSBRNTK50PercentRegulatory_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
						+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite TaxMonth2CSBRNTK50PercentRegulatory201819.xlsx");
				TaxPayroll_TaxMonth2CSBRNTK50PercentRegulatory_Inputsheet = "Payroll Suite TaxMonth2CSBRNTK50PercentRegulatory201819";
				
				TaxPayroll_TaxMonth3CSBRNTK50PercentRegulatory_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
						+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite TaxMonth3CSBRNTK50PercentRegulatory201819.xlsx");
				TaxPayroll_TaxMonth3CSBRNTK50PercentRegulatory_Inputsheet = "Payroll Suite TaxMonth3CSBRNTK50PercentRegulatory201819";
				
				TaxPayroll_TaxMonth4CSBRNTK50PercentRegulatory_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
						+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite TaxMonth4CSBRNTK50PercentRegulatory201819.xlsx");
				TaxPayroll_TaxMonth4CSBRNTK50PercentRegulatory_Inputsheet = "Payroll Suite TaxMonth4CSBRNTK50PercentRegulatory201819";
				
				TaxPayroll_TaxMonth5CSBRNTK50PercentRegulatory_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
						+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite TaxMonth5CSBRNTK50PercentRegulatory201819.xlsx");
				TaxPayroll_TaxMonth5CSBRNTK50PercentRegulatory_Inputsheet = "Payroll Suite TaxMonth5CSBRNTK50PercentRegulatory201819";
				
				TaxPayroll_TaxMonth6CSBRNTK50PercentRegulatory_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
						+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite TaxMonth6CSBRNTK50PercentRegulatory201819.xlsx");
				TaxPayroll_TaxMonth6CSBRNTK50PercentRegulatory_Inputsheet = "Payroll Suite TaxMonth6CSBRNTK50PercentRegulatory201819";
				
				TaxPayroll_TaxMonth7CSBRNTK50PercentRegulatory_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
						+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite TaxMonth7CSBRNTK50PercentRegulatory201819.xlsx");
				TaxPayroll_TaxMonth7CSBRNTK50PercentRegulatory_Inputsheet = "Payroll Suite TaxMonth7CSBRNTK50PercentRegulatory201819";
				
				TaxPayroll_TaxMonth8CSBRNTK50PercentRegulatory_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
						+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite TaxMonth8CSBRNTK50PercentRegulatory201819.xlsx");
				TaxPayroll_TaxMonth8CSBRNTK50PercentRegulatory_Inputsheet = "Payroll Suite TaxMonth8CSBRNTK50PercentRegulatory201819";
				
				TaxPayroll_TaxMonth9CSBRNTK50PercentRegulatory_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
						+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite TaxMonth9CSBRNTK50PercentRegulatory201819.xlsx");
				TaxPayroll_TaxMonth9CSBRNTK50PercentRegulatory_Inputsheet = "Payroll Suite TaxMonth9CSBRNTK50PercentRegulatory201819";
				
				TaxPayroll_TaxMonth10CSBRNTK50PercentRegulatory_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
						+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite TaxMonth10CSBRNTK50PercentRegulatory201819.xlsx");
				TaxPayroll_TaxMonth10CSBRNTK50PercentRegulatory_Inputsheet = "Payroll Suite TaxMonth10CSBRNTK50PercentRegulatory201819";

				/*
				 * Payroll Tax General Tax rate monthly and weekly SuiteXls for 201819 F.Y
				 * 	
				 */
				
				Payroll_GenerateTaxrateMonthly_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite TaxGeneralAndLargeTaxcodeMonthly201819.xlsx");
				GenerateTaxrateMonthly_InputExcelFile = "Payroll Suite TaxGeneralAndLargeTaxcodeMonthly201819";

				Payroll_GenerateTaxrateWeekly_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite TaxGeneralAndLargeTaxcodeWeekly201819.xlsx");
				GenerateTaxrateWeekly_InputExcelFile = "Payroll Suite TaxGeneralAndLargeTaxcodeWeekly201819";

				/*
				 * Payroll NI Weekly SuiteXls for 201819 F.Y
				 * 	
				 */
				
				Payroll_CatA_SuiteXls =  new Xls_Reader(
						System.getProperty("user.dir")
						+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite WeeklyCatA201819.xlsx");
					
				NI_Payroll_CatA_SuiteXls_InputExcelFile = "Payroll Suite WeeklyCatA201819";

				
				Payroll_CatB_SuiteXls =  new Xls_Reader(
						System.getProperty("user.dir")
						+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite WeeklyCatB201819.xlsx");
					
				NI_Payroll_CatB_SuiteXls_InputExcelFile = "Payroll Suite WeeklyCatB201819";
				
				Payroll_CatC_SuiteXls =  new Xls_Reader(
						System.getProperty("user.dir")
						+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite WeeklyCatC201819.xlsx");
					
				NI_Payroll_CatC_SuiteXls_InputExcelFile = "Payroll Suite WeeklyCatC201819";
				
				Payroll_CatH_SuiteXls =  new Xls_Reader(
						System.getProperty("user.dir")
						+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite WeeklyCatH201819.xlsx");
					
				NI_Payroll_CatH_SuiteXls_InputExcelFile = "Payroll Suite WeeklyCatH201819";
				
				Payroll_CatJ_SuiteXls =  new Xls_Reader(
						System.getProperty("user.dir")
						+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite WeeklyCatJ201819.xlsx");
					
				NI_Payroll_CatJ_SuiteXls_InputExcelFile = "Payroll Suite WeeklyCatJ201819";
				
				Payroll_CatM_SuiteXls =  new Xls_Reader(
						System.getProperty("user.dir")
						+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite WeeklyCatM201819.xlsx");
					
				NI_Payroll_CatM_SuiteXls_InputExcelFile = "Payroll Suite WeeklyCatM201819";
				
				
				Payroll_CatZ_SuiteXls =  new Xls_Reader(
						System.getProperty("user.dir")
						+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite WeeklyCatZ201819.xlsx");
					
				NI_Payroll_CatZ_SuiteXls_InputExcelFile = "Payroll Suite WeeklyCatZ201819";
				
				
				/*
				 * Payroll NI 2Weekly SuiteXls for 201819 F.Y
				 * 	
				 */
				
				
				Payroll_2WeeklyCatB_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 2WeeklyCatB201819.xlsx");
				NI_Payroll_2WeeklyCatB_SuiteXls_InputExcelFile = "Payroll Suite 2WeeklyCatB201819";

				Payroll_2WeeklyCatC_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 2WeeklyCatC201819.xlsx");
				NI_Payroll_2WeeklyCatC_SuiteXls_InputExcelFile = "Payroll Suite 2WeeklyCatC201819";

				Payroll_2WeeklyCatH_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 2WeeklyCatH201819.xlsx");
				NI_Payroll_2WeeklyCatH_SuiteXls_InputExcelFile = "Payroll Suite 2WeeklyCatH201819";

				Payroll_2WeeklyCatJ_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 2WeeklyCatJ201819.xlsx");
				NI_Payroll_2WeeklyCatJ_SuiteXls_InputExcelFile = "Payroll Suite 2WeeklyCatJ201819";

				Payroll_2WeeklyCatM_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 2WeeklyCatM201819.xlsx");
				NI_Payroll_2WeeklyCatM_SuiteXls_InputExcelFile = "Payroll Suite 2WeeklyCatM201819";

				Payroll_2WeeklyCatZ_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 2WeeklyCatZ201819.xlsx");
				NI_Payroll_2WeeklyCatZ_SuiteXls_InputExcelFile = "Payroll Suite 2WeeklyCatZ201819";
				
				
				/*
				 * Payroll NI 4Weekly SuiteXls for 201819 F.Y
				 * 	
				 */

				Payroll_4WeeklyCatA_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 4WeeklyCatA201819.xlsx");
				NI_Payroll_4WeeklyCatA_SuiteXls_InputExcelFile = "Payroll Suite 4WeeklyCatA201819";

				Payroll_4WeeklyCatB_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 4WeeklyCatB201819.xlsx");
				NI_Payroll_4WeeklyCatB_SuiteXls_InputExcelFile = "Payroll Suite 4WeeklyCatB201819";

				Payroll_4WeeklyCatC_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 4WeeklyCatC201819.xlsx");
				NI_Payroll_4WeeklyCatC_SuiteXls_InputExcelFile = "Payroll Suite 4WeeklyCatC201819";

				Payroll_4WeeklyCatH_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 4WeeklyCatH201819.xlsx");
				NI_Payroll_4WeeklyCatH_SuiteXls_InputExcelFile = "Payroll Suite 4WeeklyCatH201819";

				Payroll_4WeeklyCatJ_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 4WeeklyCatJ201819.xlsx");
				NI_Payroll_4WeeklyCatJ_SuiteXls_InputExcelFile = "Payroll Suite 4WeeklyCatJ201819";

				Payroll_4WeeklyCatM_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite 4WeeklyCatM201819.xlsx");
				NI_Payroll_4WeeklyCatM_SuiteXls_InputExcelFile = "Payroll Suite 4WeeklyCatM201819";
				
				
			/*
			 * Payroll NI Monthly SuiteXls for 201819 F.Y
			 * 	
			 */
				
				Payroll_MonthlyCatA_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite MonthlyCatA201819.xlsx");
				NI_Payroll_MonthlyCatA_SuiteXls_InputExcelFile = "Payroll Suite MonthlyCatA201819";

				Payroll_MonthlyCatB_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite MonthlyCatB201819.xlsx");
				NI_Payroll_MonthlyCatB_SuiteXls_InputExcelFile = "Payroll Suite MonthlyCatB201819";

				Payroll_MonthlyCatC_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite MonthlyCatC201819.xlsx");
				NI_Payroll_MonthlyCatC_SuiteXls_InputExcelFile = "Payroll Suite MonthlyCatC201819";

				Payroll_MonthlyCatH_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite MonthlyCatH201819.xlsx");
				NI_Payroll_MonthlyCatH_SuiteXls_InputExcelFile = "Payroll Suite MonthlyCatH201819";

				Payroll_MonthlyCatJ_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite MonthlyCatJ201819.xlsx");
				NI_Payroll_MonthlyCatJ_SuiteXls_InputExcelFile = "Payroll Suite MonthlyCatJ201819";

				Payroll_MonthlyCatM_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite MonthlyCatM201819.xlsx");
				NI_Payroll_MonthlyCatM_SuiteXls_InputExcelFile = "Payroll Suite MonthlyCatM201819";

				Payroll_MonthlyCatZ_SuiteXls = new Xls_Reader(
						System.getProperty("user.dir")
								+ "//src//main//java//com//test//xcdhr//Salesforce_Core_Framework1//salesforce_XLS_Files//Payroll Suite MonthlyCatZ201819.xlsx");
				NI_Payroll_MonthlyCatZ_SuiteXls_InputExcelFile = "Payroll Suite MonthlyCatZ201819";

				
				
				
				
				
				
				
				
				
				
				break;	
					
			default:
				System.out
						.println("not specified to run any specific tax year");
			}

		} catch (Throwable t) {
			System.out.println(t.getMessage());
			System.out.println(t.getStackTrace());
		}
	}

	/****************** RTI GENERATE FINAL PAYROLL METHODS *****************/

	public void generateFinalDraft() throws Throwable {
		try {
			retryForGenerateFinal();
			Thread.sleep(15000L);
			if (existsElement(OR.getProperty("generateFinalDraft"))) {
				System.out.println("Generate final draft button exists");
				getObject("generateFinalDraft").sendKeys("");
				getObject("generateFinalDraft").click();
				System.out
						.println("The generate Final draft button got clicked");
				Thread.sleep(3000L);
				if (existsElement(OR.getProperty("progressBar"))) {
					if (!existsElement(OR.getProperty("createFPS"))) {
						System.out.println("The progress bar got displayed");
						System.out.println("");
						Thread.sleep(4000L);
						payRunExecution1();
					}
				}
			}
			Thread.sleep(30000L);
			createFPS();
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void retryForGenerateFinal() throws Throwable {
		try {
			if (!existsElement(OR.getProperty("generateFinalDraft"))) {
				if (existsElement(OR.getProperty("changeToDraft"))) {
					getObject("changeToDraft").sendKeys("");
					getObject("changeToDraft").click();
					Thread.sleep(5000L);
					retryForGenerateFinal();
				}
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void payrunFinaldraft() throws Throwable {
		try {
			if (!existsElement(OR.getProperty("generateFinalDraft"))) {
				System.out
						.println("Still generate final payroll button did not displayed ,please wait");
				retryForGenerateFinal();
			} else {
				System.out
						.println("Now ,generate final payroll got displayed successfully");
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void createFPS() throws Throwable {
		try {
			if (existsElement(OR.getProperty("createFPS"))) {
				if (existsElement(OR.getProperty("finalPayruntext"))) {
					getObject("createFPS").sendKeys("");
					getObject("createFPS").click();
				}
				Thread.sleep(4000L);
				payRunExecution2();
			}
			if (existsElement(OR.getProperty("fpsSubmitTable"))) {
				payrollSubmit();
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void payRunExecution1() throws Throwable {
		try {
			if (!existsElement(OR.getProperty("createFPS"))) {
				Thread.sleep(180000L);
				System.out
						.println("Still generate final payroll functionality execution did not completed...please wait");
				generateFinalDraft();
			} else {
				System.out
						.println("Now ,generate final payroll functionality execution completed successfully");
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void payRunExecution2() throws Throwable {
		try {
			if (existsElement(OR.getProperty("finalPayruntext"))) {
				Thread.sleep(120000L);
				System.out
						.println("Still generate create FPS functionality execution did not completed...please wait");
				createFPS();
			} else {
				System.out
						.println("Finally  fps creation functionality execution completed successfully");
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void submitExecution() throws Throwable {
		try {
			if (!existsElement(OR.getProperty("refreshButton"))) {
				System.out
						.println("Still submit functionality execution did not completed...please wait");
				Thread.sleep(120000L);
				payrollSubmit();
			} else if (existsElement(OR.getProperty("refreshButton"))) {
				System.out.println("Refresh button got displayed");
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void payrollSubmit() throws Throwable {
		try {
			WebElement submitTable = driver.findElement(By.xpath(OR
					.getProperty("fpsSubmitTable")));
			List<WebElement> rows = submitTable.findElements(By.xpath(OR
					.getProperty("fpsSubmitTableRows")));
			java.util.Iterator<WebElement> x = rows.iterator();
			rowtd = 10;
			while (x.hasNext()) {
				try {
					System.out.println("The payroll submit table is found");
					Thread.sleep(2000L);
					String firstRowOfEmployeeColumn = "//div[2]/table/tbody/tr/td"
							+ "["
							+ rowtd
							+ "]"
							+ "/"
							+ "span/span[2]/input[@value='Submit']";
					if (existsElement(firstRowOfEmployeeColumn)) {
						WebElement sbtn = driver.findElement(By
								.xpath(firstRowOfEmployeeColumn));
						if (sbtn.getAttribute("value").equalsIgnoreCase(
								"Submit")) {
							System.out.println("button name matched ");
							Thread.sleep(1000L);
							sbtn.sendKeys("");
							sbtn.click();
							System.out.println("The submit button got clicked");
							submitExecution();
						}
					}
					break;
				} catch (Throwable t) {
					System.out.println(t.getMessage().toString());
					System.out.println(t.getStackTrace().toString());
				}
			}
			Thread.sleep(3000L);
			payrollRefresh();
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void payrollRefresh() throws Throwable {
		try {
			WebElement submitTable = driver.findElement(By.xpath(OR
					.getProperty("fpsSubmitTable")));
			List<WebElement> rows = submitTable.findElements(By.xpath(OR
					.getProperty("fpsSubmitTableRows")));
			java.util.Iterator<WebElement> x = rows.iterator();
			rowtd = 10;
			while (x.hasNext()) {
				try {
					System.out.println("The payroll submit table");
					Thread.sleep(2000L);
					String firstRowOfEmployeeColumn = "//div[2]/table/tbody/tr/td"
							+ "["
							+ rowtd
							+ "]"
							+ "/"
							+ "input[@value='Refresh']";
					if (existsElement(firstRowOfEmployeeColumn)) {
						WebElement sbtn = driver.findElement(By
								.xpath(firstRowOfEmployeeColumn));
						if (sbtn.getAttribute("value").equalsIgnoreCase(
								"Refresh")) {
							System.out.println("Refresh button name matched");
							Thread.sleep(1000L);
							sbtn.sendKeys("");
							sbtn.click();
							sbtn.click();
							System.out
									.println("The Refresh button got clicked");
							viewExecution();
						}
					}
					break;
				} catch (Throwable t) {
					System.out.println(t.getMessage().toString());
					System.out.println(t.getStackTrace().toString());
				}
			}
			Thread.sleep(3000L);
			payrollView();
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void viewExecution() throws Throwable {
		try {
			if (!existsElement(OR.getProperty("viewbuttontable"))) {
				System.out
						.println("Still Refresh functionality execution did not completed...please wait");
				Thread.sleep(1000L);
				payrollRefresh();
			} else if (existsElement(OR.getProperty("viewbuttontable"))) {
				System.out
						.println("Refresh button got displayed hence submit functionality got executed successfully");
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void payrollView() throws Throwable {
		try {
			WebElement submitTable = driver.findElement(By.xpath(OR
					.getProperty("fpsSubmitTable")));
			List<WebElement> rows = submitTable.findElements(By.xpath(OR
					.getProperty("fpsSubmitTableRows")));
			java.util.Iterator<WebElement> x = rows.iterator();
			rowtd = 10;
			endInnerSearch: while (x.hasNext()) {
				try {
					System.out
							.println("The payroll submit table is found last one i.e in view table");
					Thread.sleep(2000L);
					String firstRowOfEmployeeColumn = "//div[2]/table/tbody/tr/td"
							+ "[" + rowtd + "]" + "/" + "input[@value='View']";
					if (existsElement(firstRowOfEmployeeColumn)) {
						WebElement sbtn = driver.findElement(By
								.xpath(firstRowOfEmployeeColumn));
						if (sbtn.getAttribute("value").equalsIgnoreCase("View")) {
							System.out.println("View button name matched");
							Thread.sleep(1000L);
							sbtn.sendKeys("");
							sbtn.click();
							System.out.println("The View button got clicked");
							rtiExecution();
							System.out.println("ending the script");
							break endInnerSearch;
						}
					}
				} catch (Throwable t) {
					System.out.println(t.getMessage().toString());
					System.out.println(t.getStackTrace().toString());
				}
			}
			Thread.sleep(3000L);
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void rtiExecution() throws Throwable {
		try {
			if (existsElement(OR.getProperty("rtiSubmissionDetails"))) {
				System.out
						.println("RTI Submission details got displayed hence submit functionality got executed successfully");
				closeBrowser();
			} else {
				System.out
						.println("Still View functionality execution did not completed...please wait");
				payrollView();
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void retryForGenerateDraft() throws Throwable {
		try {

			if (existsElement(OR.getProperty("genratedraftPayroll"))) {
				if (existsElement(OR.getProperty("changeToDraft"))) {
					getObject("changeToDraft").sendKeys("");
					getObject("changeToDraft").click();
					Thread.sleep(2000L);
					// /
					if (existsElementchkFor1mts(OR
							.getProperty("statusPickList"))) {
						Select selectByValue = new Select(driver.findElement(By
								.xpath(OR.getProperty("statusPickList"))));
						selectByValue.selectByVisibleText("Draft");
					}
					Thread.sleep(2000L);
					if (existsElementchkFor1mts(OR
							.getProperty("payrollDraftSave"))) {
						getObject("payrollDraftSave").sendKeys("");
						getObject("payrollDraftSave").click();
						System.out.println("The Save button got clicked");
					}
				}
				if (existsElementchkFor1mts(OR
						.getProperty("genratedraftPayroll"))) {
					if (existsElementchkFor1mts(OR
							.getProperty("excludeIncludeEmployees"))) {
						getObject("excludeIncludeEmployees").click();
						System.out
								.println("Exclude Include Employees link got clicked");
						Thread.sleep(5000);
					}
				} else {
					System.out
							.println("May be the payroll status is still New. Please make this payroll status to Draft and rerun the script");
				}
			}

		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	/********************* INPUT SCRIPT METHODS ******************************/

	public void UpdateEmployeeNICategory111(String EmpName, String NICategory,
			String Taxcode, String TaxBasis) throws Throwable {
		try {
			if (employeeFirsttimeView) {
				employeeFirsttimeView = false;
				getObject("PersonalTab").click();
				if (existsElementchkFor1mts(OR.getProperty("PersonalText"))) {
					System.out.println("I am in personal page");
					if (existsElementchkFor1mts(OR.getProperty("EmployeeView")))
					{
						System.out.println("I recognised the Employee view");
						Select selectByValue = new Select(driver.findElement(By
								.xpath(OR.getProperty("EmployeeView"))));
						selectByValue
								.selectByVisibleText("DO NOT TOUCH PAYROLL AUTOMATION TESTING");
						Thread.sleep(2000L);
						if (existsElementchkFor1mts(OR
								.getProperty("ViewGoButton"))) {
							getObject("ViewGoButton").sendKeys("");
							getObject("ViewGoButton").click();
							System.out.println("The Go button got clicked");
						}
						Thread.sleep(7000L);
					}
				}
			}
			WebElement tableheader = driver.findElement(By.xpath(OR
					.getProperty("PersonalAndCompensationHeadingTable")));
			List<WebElement> th = tableheader.findElements(By.tagName("td"));
			for (a = 0; a < th.size(); a++) {
				if ("Employee".equalsIgnoreCase(th.get(a).getText())) {
					empcolnum = a + 1;
					break;
				}
			}

			for (b = 0; b < th.size(); b++) {
				if ("NI category".equalsIgnoreCase(th.get(b).getText())) {
					niCategoryColumn = b + 1;
					break;
				}
			}

			for (c = 0; b < th.size(); c++) {
				if ("Tax code".equalsIgnoreCase(th.get(c).getText())) {
					taxcodecolnum = c + 1;
					break;
				}
			}

			for (d = 0; c < th.size(); d++) {
				if ("Tax basis".equalsIgnoreCase(th.get(d).getText())) {
					taxbasiscolnum = d + 1;
					break;
				}
			}
			WebElement postsTable = driver.findElement(By.xpath(OR
					.getProperty("firstRecordOfTaxCodecoulmnTable")));
			if (existsWebElement(postsTable)) {
				System.out.println("found the personal table");
				List<WebElement> rows = postsTable.findElements(By.xpath(OR
						.getProperty("firstRecordOfTaxCodecoulmnTableRows")));
				lastRowCount = rows.size();
				java.util.Iterator<WebElement> x = rows.iterator();
				rownum = 1;
				outerbreak: while (x.hasNext()) {
					try {
						// Thread.sleep(1000L);
						String firstRowOfEmployeeColumn = "//div[" + rownum
								+ "]/table/tbody/tr/td" + "[" + empcolnum + "]"
								+ "/" + "div/a/span";
						if (existsElementchkFor1mts(firstRowOfEmployeeColumn)) {
							WebElement tempElement = driver.findElement(By
									.xpath(firstRowOfEmployeeColumn));
							String tempEmp = tempElement.getText();
							// System.out.println(tempEmp+"-------"+empName+"------"+rownum);
							String firstRowOfTaxCode = "//div[" + rownum + "]"
									+ "/" + "table/" + "tbody/" + "tr/" + "td["
									+ niCategoryColumn + "]" + "/" + "div";
							if (tempEmp != null
									&& tempEmp.equalsIgnoreCase(EmpName)) {
								System.out.println("Employee name  :" + tempEmp
										+ "  matched ");
								Thread.sleep(1000L);
								if (existsElementchkFor1mts(firstRowOfTaxCode)) {
									Actions action = new Actions(driver);
									action.doubleClick(
											driver.findElement(By
													.xpath(firstRowOfTaxCode)))
											.perform();
									action.moveToElement(
											getObject("InlineDropdown"))
											.perform();
									Thread.sleep(2000L);
									if (existsElementchkFor1mts(OR
											.getProperty("InlineDropdown"))) {
										// Select selectByValue = new
										// Select(driver.findElement(By.xpath(OR.getProperty("InlineDropdown"))));
										// selectByValue.selectByVisibleText(NICategory);
										getObject("InlineDropdown")
												.sendKeys("");
										getObject("InlineDropdown").sendKeys(
												NICategory);
										System.out
												.println("Selected the NI Picklist item "
														+ NICategory);
										Thread.sleep(2000L);
										if (existsElementchkFor1mts(OR
												.getProperty("InlineUpdateButn"))) {
											getObject("InlineUpdateButn")
													.click();
											System.out
													.println("The update button got clicked and NI Category got saved");
										}
									}
								}
								/*
								 * Calling the method for updating taxcode and
								 * taxbasis.
								 */
								enterTaxcodeAndTaxBasis(EmpName, Taxcode,
										TaxBasis);
								break outerbreak;
							} else if (rownum == lastRowCount
									&& tempEmp != null && tempEmp != (EmpName)) {
								System.out
										.println("The row number of the page reached"
												+ rownum
												+ " to 200 and"
												+ " 	Required Employee not found "
												+ "hence clicking the"
												+ "	pagination link so that Employee search continues for next page");
								if (existsElementchkFor1mts(OR
										.getProperty("paginationElementPersonal"))) {
									getObject("paginationNextPersonal")
											.sendKeys("");
									getObject("paginationNextPersonal").click();
									System.out
											.println("As the required employees are "
													+ "not found in first page,hence clicked to next page of personal Tab");
									Thread.sleep(8000L);
									rownum = 0;
								}
							}
						} else {
							System.out.println("");
						}
						rownum++;
					} catch (Throwable t) {
						System.out.println(t.getMessage());
						System.out.println(t.getStackTrace().toString());
					}
				}
			}
		} catch (Throwable t) {
			APP_LOGS.debug(" Check for error in NI Category method");
			System.out.println(t.getMessage());
			System.out.println(t.getStackTrace().toString());
			System.out.println("");
		}
	}

	public void enterTaxcodeAndTaxBasis(String EmpName, String Taxcode,
			String TaxBasis) throws Throwable {
		try {
			Thread.sleep(3000L);
			String firstRowOfTaxCode = "//div[" + rownum + "]" + "/" + "table/"
					+ "tbody/" + "tr/" + "td[" + taxcodecolnum + "]" + "/"
					+ "div";
			if (existsElementchkFor1mts(firstRowOfTaxCode)) {
				Actions action1 = new Actions(driver);
				action1.doubleClick(
						driver.findElement(By.xpath(firstRowOfTaxCode)))
						.perform();
				WebElement updateTaxcode = driver.findElement(By.xpath(OR
						.getProperty("taxCodeTextfield")));
				action1.moveToElement(updateTaxcode).perform();
				Thread.sleep(1000L);
				// updateTaxcode.clear();
				updateTaxcode.sendKeys(Taxcode);
				Thread.sleep(1000L);
				if (existsElementchkFor1mts(OR.getProperty("taxCodeSavebutton"))) {
					getObject("taxCodeSavebutton").click();
					System.out.println("Tax code got saved successfully");
				}
				Thread.sleep(2000L);
			}
			UpdateTaxBasis111(EmpName, Taxcode, TaxBasis);
		} catch (Throwable t) {
			APP_LOGS.debug(" Check for error in NI Category method");
			System.out.println(t.getMessage());
			System.out.println(t.getStackTrace().toString());
			System.out.println("");
		}
	}

	public void UpdateTaxBasis111(String ename, String TCode, String TaxBasis)
			throws Throwable {
		try {
			String firstRowOfTaxBasis = "//div[" + rownum + "]" + "/"
					+ "table/" + "tbody/" + "tr/" + "td[" + taxbasiscolnum
					+ "]" + "/" + "div";
			if (existsElement(firstRowOfTaxBasis)) {
				// Thread.sleep(1000L);
				Actions action2 = new Actions(driver);
				action2.doubleClick(
						driver.findElement(By.xpath(firstRowOfTaxBasis)))
						.build().perform();
				action2.moveToElement(getObject("taxBasisdropdown")).perform();
				Thread.sleep(1000L);
				if (existsElement(OR.getProperty("taxBasisdropdown"))) {
					getObject("taxBasisdropdown").sendKeys(TaxBasis);
					// Thread.sleep(2000L);
				}

				if (existsElement(OR.getProperty("taxCodeSavebutton"))) {
					getObject("taxCodeSavebutton").click();
					System.out.println("Tax basis got saved successfully");
				}
				Thread.sleep(3000L);
			}
		} catch (Throwable t) {
			APP_LOGS.debug("Check the tax basis Method for errors");
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}

	}

	public void UpdateAnnualSalary111(String EmpName, String NICategory,
			String Taxcode, String TaxBasis, String annualSalary,
			String PayFrequency) throws Throwable {
		try {
			if (compensationFirsttimeView) {
				compensationFirsttimeView = false;
				if (existsElement(OR.getProperty("CompensationTab"))) {
					getObject("CompensationTab").click();
					Thread.sleep(4000L);
					/*
					 * Calling the following method from the base class since
					 * "Select value is not able to call the value from
					 * OR.Properties page.
					 */
					compensationSelectValue();
				}
			}
			Thread.sleep(1000L);
			WebElement tableheader = driver.findElement(By.xpath(OR
					.getProperty("PersonalAndCompensationHeadingTable")));
			List<WebElement> th = tableheader.findElements(By.tagName("td"));
			for (a = 0; a < th.size(); a++) {
				if ("Employee".equalsIgnoreCase(th.get(a).getText())) {
					empcolnum = a + 1;
					break;
				}
			}
			for (b = 0; b < th.size(); b++) {
				if ("Annual salary".equalsIgnoreCase(th.get(b).getText())) {
					compnAnnualSalColumn = b + 1;
					break;
				}
			}
			for (c = 0; c < th.size(); c++) {
				if ("Payroll frequency".equalsIgnoreCase(th.get(c).getText())) {
					compPayfrequencyColumn = c + 1;
					break;
				}
			}
			WebElement postsTable = driver.findElement(By.xpath(OR
					.getProperty("firstRecordOfTaxCodecoulmnTable")));
			if (existsWebElement(postsTable)) {
				List<WebElement> rows = postsTable.findElements(By.xpath(OR
						.getProperty("firstRecordOfTaxCodecoulmnTableRows")));
				lastRowCount = rows.size();
				java.util.Iterator<WebElement> x = rows.iterator();
				rownum = 1;
				outerbreak: while (x.hasNext()) {
					String firstEmpXpath = "//div[" + rownum
							+ "]/table/tbody/tr/td" + "[" + empcolnum + "]"
							+ "/" + "div/a/span";
					if (existsElement(firstEmpXpath)) {
						WebElement FirstrowofEmpColumn = driver.findElement(By
								.xpath(firstEmpXpath));
						String ApplnEmp = FirstrowofEmpColumn.getText();
						if (ApplnEmp != null
								&& ApplnEmp.equalsIgnoreCase(EmpName)) {
							Thread.sleep(1000L);
							String firstRowOfAnnualsalary = "//div[" + rownum
									+ "]" + "/" + "table/" + "tbody/" + "tr/"
									+ "td[" + compnAnnualSalColumn + "]" + "/"
									+ "div";
							if (existsElement(firstRowOfAnnualsalary)) {
								Actions action1 = new Actions(driver);
								action1.doubleClick(
										driver.findElement(By
												.xpath(firstRowOfAnnualsalary)))
										.perform();
								WebElement updatesal = driver
										.findElement(By.xpath(OR
												.getProperty("annualSalTextField")));
								action1.moveToElement(updatesal).perform();
								Thread.sleep(1000L);
								updatesal.clear();
								Thread.sleep(1000L);
								updatesal.sendKeys(annualSalary);
								Thread.sleep(1000L);
								if (existsElement(OR
										.getProperty("CompnSavebuton"))) {
									getObject("CompnSavebuton").sendKeys("");
									getObject("CompnSavebuton").click();
									System.out
											.println("The annual salary got saved");
								}
								Thread.sleep(3000L);
							}
							UpdatePayFrequency111(EmpName, NICategory, Taxcode,
									TaxBasis, annualSalary, PayFrequency);
							break outerbreak;
						}

						else if (rownum == lastRowCount && ApplnEmp != null
								&& ApplnEmp != (EmpName)) {
							System.out
									.println("The row number of the page reached"
											+ rownum
											+ " to 200 and"
											+ " 	Required Employee not found "
											+ "hence clicking the"
											+ "	pagination link so that Employee search continues for next page");
							if (existsElementchkFor1mts(OR
									.getProperty("paginationElementPersonal"))) {
								getObject("paginationNextPersonal")
										.sendKeys("");
								getObject("paginationNextPersonal").click();
								System.out
										.println("As the required employees are "
												+ "not found in first page,hence clicked to next page of personal Tab");
								Thread.sleep(8000L);
								rownum = 0;
							}
						}
					} else {
						System.out.println("");
					}
					rownum++;
				}
			}
		} catch (Throwable t) {
			APP_LOGS.debug("Check the Annual salary Method for errors");
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
			ErrorUtil.addVerificationFailure(t);
		}
	}

	public void UpdatePayFrequency111(String empName, String NICategory,
			String Taxcode, String TaxBasis, String AnnualSalary,
			String PayFrequency) throws Throwable {
		try {
			String firstRowOfPayFrequency = "//div[" + rownum + "]" + "/"
					+ "table/" + "tbody/" + "tr/" + "td["
					+ compPayfrequencyColumn + "]" + "/" + "div";
			if (existsElement(firstRowOfPayFrequency)) {
				Thread.sleep(2000L);
				Actions action2 = new Actions(driver);
				action2.doubleClick(
						driver.findElement(By.xpath(firstRowOfPayFrequency)))
						.perform();
				action2.moveToElement(getObject("payFrequencyDropdown"))
						.perform();
				Thread.sleep(2000L);
				if (existsElement(OR.getProperty("payFrequencyDropdown"))) {
					getObject("payFrequencyDropdown").sendKeys("");
					getObject("payFrequencyDropdown").sendKeys(PayFrequency);
					System.out.println("Selected the PayFrequency item as :"
							+ PayFrequency);
					Thread.sleep(2000L);
					if (existsElement(OR.getProperty("payFrequencyUpdate"))) {
						getObject("payFrequencyUpdate").click();
						System.out
								.println("The update button got clicked and Pay frequency Category got saved");
					}
				}
			}
		} catch (Throwable t) {
			APP_LOGS.debug("Check the Pay frequency Method for errors");
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
			ErrorUtil.addVerificationFailure(t);
		}
	}

	/************************* RTI REPORT METHODS ****************************/

	public void processReport(String EmployerName, String EmpName,
			String Payrolid, String Frquency, String MonthName,
			String ExcelInputSheet, String FirstReportNameInApplication,
			String TestResultExcelFilePath, String worksheetNo,
			String PayrollVeiw, String TestReportworksheetNo,
			String ExpectedResultRowNumOfTestResultFile,
			String ActualResultRowNumOfTestResultFile,
			String TestRemarkRowNumOfTestResultFile) throws Throwable {
		try {
			if (existsElement(OR.getProperty("reportTableLocatorNI"))) {
				// Get number of rows In table using table/tbody/tr
				Row_count = driver.findElements(
						By.xpath(OR.getProperty("reportTableRowsLocatorNI")))
						.size();
				System.out.println("Number Of Rows = " + Row_count);
				// Get number of columns In table by using Tr/td
				int Col_count = driver.findElements(
						By.xpath(OR.getProperty("reportTableColumnsNI")))
						.size();
				System.out.println("Number Of Columns = " + Col_count); // DISPLAYING
			}
			Thread.sleep(3000L);
			WebElement threecolms = driver.findElement(By.xpath(OR
					.getProperty("reportTableLocatorNI")));
			WebTable table = WebTable.getTable(threecolms);
			List<WebElement> rows = threecolms.findElements(By.xpath(OR
					.getProperty("reportTableRowsLocatorNI")));
			java.util.Iterator<WebElement> x = rows.iterator();
			int rownum = 1;
			gotobreak: while (x.hasNext()) {
				if (rownum == (Row_count - 2)) {
					System.out.println("no of rows is equal to expected rows");
					System.out
							.println("4> Total count of Employee records displayed in the report are :"
									+ rownum);
					System.out.println("");
					System.out
							.println("5> The script successfully read and output the values and accordingly gave the TEST REMARKS in NI-HMRC Excel file");
					break gotobreak;
				} else {
					firstCellOfBody = table.getTBody().getRow(rownum)
							.getCell(0).getText();
					System.out.println("Employee name is :" + firstCellOfBody);
					String nationalInsurance = table.getTBody().getRow(rownum)
							.getCell(1).getText();
					System.out.println("nationalInsurance is :"
							+ nationalInsurance);

					String Title = table.getTBody().getRow(rownum).getCell(2)
							.getText();
					System.out.println("Title is :" + Title);

					String Forename = table.getTBody().getRow(rownum)
							.getCell(3).getText();
					System.out.println("Forename is :" + Forename);

					String Surname = table.getTBody().getRow(rownum).getCell(4)
							.getText();
					System.out.println("Surname is :" + Surname);

					String addressLine1 = table.getTBody().getRow(rownum)
							.getCell(5).getText();
					System.out.println("addressLine1 is :" + addressLine1);

					String addressLine2 = table.getTBody().getRow(rownum)
							.getCell(6).getText();
					System.out.println("addressLine2 is :" + addressLine2);

					String ukPostcode = table.getTBody().getRow(rownum)
							.getCell(7).getText();
					System.out.println("ukPostcode is :" + ukPostcode);

					String dateOfBirth = table.getTBody().getRow(rownum)
							.getCell(8).getText();
					System.out.println("dateOfBirth is :" + dateOfBirth);

					String currentGender = table.getTBody().getRow(rownum)
							.getCell(9).getText();
					System.out.println("currentGender is :" + currentGender);

					String payrollId = table.getTBody().getRow(rownum)
							.getCell(10).getText();
					System.out.println("payrollId is :" + payrollId);

					String payrollIdChanged = table.getTBody().getRow(rownum)
							.getCell(11).getText();
					System.out.println("payrollIdChanged is :"
							+ payrollIdChanged);

					String oldPayrollId = table.getTBody().getRow(rownum)
							.getCell(12).getText();
					System.out.println("oldPayrollId is :" + oldPayrollId);

					String leavingDate = table.getTBody().getRow(rownum)
							.getCell(13).getText();
					System.out.println("leavingDate is :" + leavingDate);

					String startDate = table.getTBody().getRow(rownum)
							.getCell(14).getText();
					System.out.println("startDate is :" + startDate);

					String startingDeclaration = table.getTBody()
							.getRow(rownum).getCell(15).getText();
					System.out.println("startingDeclaration is :"
							+ startingDeclaration);

					String taxablePay = table.getTBody().getRow(rownum)
							.getCell(16).getText();
					System.out.println("taxablePay is :" + taxablePay);

					String totaltax = table.getTBody().getRow(rownum)
							.getCell(17).getText();
					System.out.println("totaltax is :" + totaltax);

					String bacsHashcode = table.getTBody().getRow(rownum)
							.getCell(18).getText();
					System.out.println("bacsHashcode is :" + bacsHashcode);

					String PayFrequency = table.getTBody().getRow(rownum)
							.getCell(19).getText();
					System.out.println("PayFrequency is :" + PayFrequency);

					String paymentDate = table.getTBody().getRow(rownum)
							.getCell(20).getText();
					System.out.println("paymentDate is :" + paymentDate);

					String taxMonthNumber = table.getTBody().getRow(rownum)
							.getCell(21).getText();
					System.out.println("taxMonthNumber is :" + taxMonthNumber);

					String numberOfEarningsperiodsCovered = table.getTBody()
							.getRow(rownum).getCell(22).getText();
					System.out.println("numberOfEarningsperiodsCovered is :"
							+ numberOfEarningsperiodsCovered);

					String numberOfNormalHoursWorked = table.getTBody()
							.getRow(rownum).getCell(23).getText();
					System.out.println("numberOfNormalHoursWorked is :"
							+ numberOfNormalHoursWorked);

					String taxBasis = table.getTBody().getRow(rownum)
							.getCell(24).getText();
					System.out.println("taxBasis is :" + taxBasis);

					String taxCode = table.getTBody().getRow(rownum)
							.getCell(25).getText();
					System.out.println("taxCode is :" + taxCode);

					String taxablePayInPeriod = table.getTBody().getRow(rownum)
							.getCell(26).getText();
					System.out.println("taxablePayInPeriod is :"
							+ taxablePayInPeriod);

					String payAfterStatutoryDeductions = table.getTBody()
							.getRow(rownum).getCell(27).getText();
					System.out.println("payAfterStatutoryDeductions is :"
							+ payAfterStatutoryDeductions);

					String taxDeductedORrefunded = table.getTBody()
							.getRow(rownum).getCell(28).getText();
					System.out.println("taxDeductedORrefunded is :"
							+ taxDeductedORrefunded);

					String niCategory = table.getTBody().getRow(rownum)
							.getCell(29).getText();
					System.out.println("niCategory is :" + niCategory);

					String grossEarningsNICsInPeriod = table.getTBody()
							.getRow(rownum).getCell(30).getText();
					System.out.println("grossEarningsNICsInPeriod is :"
							+ grossEarningsNICsInPeriod);

					String grossEarningsNICsInYTD = table.getTBody()
							.getRow(rownum).getCell(31).getText();
					System.out.println("grossEarningsNICsInYTD is :"
							+ grossEarningsNICsInYTD);

					String earningsAtLowerEarningslimitYTD = table.getTBody()
							.getRow(rownum).getCell(32).getText();
					System.out.println("earningsAtLowerEarningslimitYTD is :"
							+ earningsAtLowerEarningslimitYTD);

					String earningsLELUpToIncludingPTYTD = table.getTBody()
							.getRow(rownum).getCell(33).getText();
					System.out.println("earningsLELUpToIncludingPTYTD is :"
							+ earningsLELUpToIncludingPTYTD);

					String earningsPTIncludingUELYTD = table.getTBody()
							.getRow(rownum).getCell(34).getText();
					System.out.println("earningsPTIncludingUELYTD is :"
							+ earningsPTIncludingUELYTD);

					String totalOfEmployerContributions = table.getTBody()
							.getRow(rownum).getCell(35).getText();
					System.out.println("totalOfEmployerContributions is :"
							+ totalOfEmployerContributions);

					String totalOfEmployerContributionsYTD = table.getTBody()
							.getRow(rownum).getCell(36).getText();
					System.out.println("totalOfEmployerContributionsYTD is :"
							+ totalOfEmployerContributionsYTD);

					String employeeContributionsPayable = table.getTBody()
							.getRow(rownum).getCell(37).getText();
					System.out.println("employeeContributionsPayable is :"
							+ employeeContributionsPayable);

					String employeeContributionsPayableYTD = table.getTBody()
							.getRow(rownum).getCell(38).getText();
					System.out.println("employeeContributionsPayableYTD is :"
							+ employeeContributionsPayableYTD);
					WebElement clkchkbox = driver.findElement(By.xpath(OR
							.getProperty("pymentCheckbox")));

					String pymtAftrLeavingDate11 = clkchkbox
							.getAttribute("title");
					System.out.println("the value of the title is :"
							+ pymtAftrLeavingDate11);
					if (pymtAftrLeavingDate11.equalsIgnoreCase("Not Checked")) {
						pymtAftrLeavingDate = "Not applicable for this month";
						System.out.println("the payment After leaving is :"
								+ pymtAftrLeavingDate);
					} else if (pymtAftrLeavingDate11
							.equalsIgnoreCase("Checked")) {
						pymtAftrLeavingDate = "Yes";
						System.out.println("the payment After leaving is :"
								+ pymtAftrLeavingDate);
					}

					// call the functions which reads the excel sheet.
					ReadsExpectedData(EmpName, nationalInsurance, Title,
							Forename, Surname, addressLine1, addressLine2,
							ukPostcode, dateOfBirth, currentGender, payrollId,
							payrollIdChanged, oldPayrollId, leavingDate,
							startDate, startingDeclaration, taxablePay,
							totaltax, bacsHashcode, PayFrequency, paymentDate,
							taxMonthNumber, numberOfEarningsperiodsCovered,
							numberOfNormalHoursWorked, taxBasis, taxCode,
							taxablePayInPeriod, payAfterStatutoryDeductions,
							taxDeductedORrefunded, niCategory,
							grossEarningsNICsInPeriod, grossEarningsNICsInYTD,
							earningsAtLowerEarningslimitYTD,
							earningsLELUpToIncludingPTYTD,
							earningsPTIncludingUELYTD,
							totalOfEmployerContributions,
							totalOfEmployerContributionsYTD,
							employeeContributionsPayable,
							employeeContributionsPayableYTD,
							paymentAfterLeaving, TestResultExcelFilePath,
							TestReportworksheetNo,
							ExpectedResultRowNumOfTestResultFile,
							ActualResultRowNumOfTestResultFile,
							TestRemarkRowNumOfTestResultFile);

					ReadsExpectedData1a(EmpName, nationalInsurance, Title,
							Forename, Surname, addressLine1, addressLine2,
							ukPostcode, dateOfBirth, currentGender, payrollId,
							payrollIdChanged, oldPayrollId, leavingDate,
							startDate, startingDeclaration, taxablePay,
							totaltax, bacsHashcode, PayFrequency, paymentDate,
							taxMonthNumber, numberOfEarningsperiodsCovered,
							numberOfNormalHoursWorked, taxBasis, taxCode,
							taxablePayInPeriod, payAfterStatutoryDeductions,
							taxDeductedORrefunded, niCategory,
							grossEarningsNICsInPeriod, grossEarningsNICsInYTD,
							earningsAtLowerEarningslimitYTD,
							earningsLELUpToIncludingPTYTD,
							earningsPTIncludingUELYTD,
							totalOfEmployerContributions,
							totalOfEmployerContributionsYTD,
							employeeContributionsPayable,
							employeeContributionsPayableYTD,
							paymentAfterLeaving, TestResultExcelFilePath,
							TestReportworksheetNo,
							ExpectedResultRowNumOfTestResultFile,
							ActualResultRowNumOfTestResultFile,
							TestRemarkRowNumOfTestResultFile);

					ReadsExpectedData1(EmpName, nationalInsurance, Title,
							Forename, Surname, addressLine1, addressLine2,
							ukPostcode, dateOfBirth, currentGender, payrollId,
							payrollIdChanged, oldPayrollId, leavingDate,
							startDate, startingDeclaration, taxablePay,
							totaltax, bacsHashcode, PayFrequency, paymentDate,
							taxMonthNumber, numberOfEarningsperiodsCovered,
							numberOfNormalHoursWorked, taxBasis, taxCode,
							taxablePayInPeriod, payAfterStatutoryDeductions,
							taxDeductedORrefunded, niCategory,
							grossEarningsNICsInPeriod, grossEarningsNICsInYTD,
							earningsAtLowerEarningslimitYTD,
							earningsLELUpToIncludingPTYTD,
							earningsPTIncludingUELYTD,
							totalOfEmployerContributions,
							totalOfEmployerContributionsYTD,
							employeeContributionsPayable,
							employeeContributionsPayableYTD,
							paymentAfterLeaving, TestResultExcelFilePath,
							TestReportworksheetNo,
							ExpectedResultRowNumOfTestResultFile,
							ActualResultRowNumOfTestResultFile,
							TestRemarkRowNumOfTestResultFile);
				}
				rownum++;
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void ReadsExpectedData(String EmpName, String nationalInsurance,
			String Title, String Forename, String Surname, String addressLine1,
			String addressLine2, String ukPostcode, String dateOfBirth,
			String currentGender, String payrollId, String payrollIdChanged,
			String oldPayrollId, String leavingDate, String startDate,
			String startingDeclaration, String taxablePay, String totaltax,
			String bacsHashcode, String PayFrequency, String paymentDate,
			String taxMonthNumber, String numberOfEarningsperiodsCovered,
			String numberOfNormalHoursWorked, String taxBasis, String taxCode,
			String taxablePayInPeriod, String payAfterStatutoryDeductions,
			String taxDeductedORrefunded, String niCategory,
			String grossEarningsNICsInPeriod, String grossEarningsNICsInYTD,
			String earningsAtLowerEarningslimitYTD,
			String earningsLELUpToIncludingPTYTD,
			String earningsPTIncludingUELYTD,
			String totalOfEmployerContributions,
			String totalOfEmployerContributionsYTD,
			String employeeContributionsPayable,
			String employeeContributionsPayableYTD, String paymentAfterLeaving,
			String TestResultExcelFilePath, String TestReportworksheetNo,
			String ExpectedResultRowNumOfTestResultFile,
			String ActualResultRowNumOfTestResultFile,
			String TestRemarkRowNumOfTestResultFile) throws Throwable {
		double worksheetvalue = Double.parseDouble(TestReportworksheetNo);
		DecimalFormat df = new DecimalFormat("###.#");
		String worksheetNoWithoutDecimal = df.format(worksheetvalue);
		int TRwNo = Integer.parseInt(worksheetNoWithoutDecimal);
		System.out
				.println("The converted integer TestReportWorksheet value is  :"
						+ TRwNo);

		File excel = new File(TestResultExcelFilePath);
		FileInputStream fis = new FileInputStream(excel);
		org.apache.poi.ss.usermodel.Workbook wb = WorkbookFactory.create(fis);
		org.apache.poi.ss.usermodel.Sheet ws = wb.getSheetAt(TRwNo);

		CellStyle style = wb.createCellStyle();
		style.setFillPattern(CellStyle.ALIGN_FILL);
		style.setFillBackgroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
		Font font = wb.createFont();
		font.setColor(IndexedColors.BLACK.getIndex());
		style.setFont(font);

		CellStyle styleFalse = wb.createCellStyle();
		styleFalse.setFillPattern(CellStyle.ALIGN_FILL);
		styleFalse.setFillBackgroundColor(IndexedColors.GOLD.getIndex());

		FileOutputStream webdata = new FileOutputStream(TestResultExcelFilePath);
		int rowNum1 = ws.getLastRowNum() + 1;
		System.out.println("i am in first method");

		double expectdDataRowNo = Double
				.parseDouble(ExpectedResultRowNumOfTestResultFile);
		DecimalFormat expctdData = new DecimalFormat("###.#");
		String expctdRowNoWithoutDecimal = expctdData.format(expectdDataRowNo);
		int expctdRowIntValue = Integer.parseInt(expctdRowNoWithoutDecimal);
		System.out
				.println("The converted integer TestReportWorksheet value is  :"
						+ expctdRowIntValue);

		for (int i = expctdRowIntValue; i < rowNum1; i++) // getting the
															// expected data
															// from expected
															// result data row
		{
			Row row = ws.getRow(i);
			value1 = cellToString(row.getCell(1)); // and storing each cell
													// value in each public
													// variable
			value2 = cellToString(row.getCell(2));
			value3 = cellToString(row.getCell(3));
			value4 = cellToString(row.getCell(4));
			value5 = cellToString(row.getCell(5));
			value6 = cellToString(row.getCell(6));
			value7 = cellToString(row.getCell(7));
			value8 = cellToString(row.getCell(8));
			value9 = cellToString(row.getCell(9));
			value10 = cellToString(row.getCell(10));
			value11 = cellToString(row.getCell(11));
			value12 = cellToString(row.getCell(12));
			value13 = cellToString(row.getCell(13));
			value14 = cellToString(row.getCell(14));
			value15 = cellToString(row.getCell(15));
			value16 = cellToString(row.getCell(16));
			value17 = cellToString(row.getCell(17));
			value18 = cellToString(row.getCell(18));
			value19 = cellToString(row.getCell(19));
			value20 = cellToString(row.getCell(20));
			value21 = cellToString(row.getCell(21));
			value22 = cellToString(row.getCell(22));
			value23 = cellToString(row.getCell(23));
			value24 = cellToString(row.getCell(24));
			value25 = cellToString(row.getCell(25));
			value26 = cellToString(row.getCell(26));
			value27 = cellToString(row.getCell(27));
			value28 = cellToString(row.getCell(28));
			value29 = cellToString(row.getCell(29));
			value30 = cellToString(row.getCell(30));
			value31 = cellToString(row.getCell(31));
			value32 = cellToString(row.getCell(32));
			value33 = cellToString(row.getCell(33));
			value34 = cellToString(row.getCell(34));
			value35 = cellToString(row.getCell(35));
			value36 = cellToString(row.getCell(36));
			value37 = cellToString(row.getCell(37));
			value38 = cellToString(row.getCell(38));
			value39 = cellToString(row.getCell(39));
			value40 = cellToString(row.getCell(40));

			if (value1 != null && value1.equalsIgnoreCase(firstCellOfBody)) {
				System.out.println("The employee name got matched");
				System.out
						.println("captured all the values and stored in the global variables");
				break;
			}
		}
		System.out.println("stored all the values from the first method");
		wb.write(webdata);
		webdata.close();
		fis.close();
	}

	public void ReadsExpectedData1a(String EmpName, String nationalInsurance,
			String Title, String Forename, String Surname, String addressLine1,
			String addressLine2, String ukPostcode, String dateOfBirth,
			String currentGender, String payrollId, String payrollIdChanged,
			String oldPayrollId, String leavingDate, String startDate,
			String startingDeclaration, String taxablePay, String totaltax,
			String bacsHashcode, String PayFrequency, String paymentDate,
			String taxMonthNumber, String numberOfEarningsperiodsCovered,
			String numberOfNormalHoursWorked, String taxBasis, String taxCode,
			String taxablePayInPeriod, String payAfterStatutoryDeductions,
			String taxDeductedORrefunded, String niCategory,
			String grossEarningsNICsInPeriod, String grossEarningsNICsInYTD,
			String earningsAtLowerEarningslimitYTD,
			String earningsLELUpToIncludingPTYTD,
			String earningsPTIncludingUELYTD,
			String totalOfEmployerContributions,
			String totalOfEmployerContributionsYTD,
			String employeeContributionsPayable,
			String employeeContributionsPayableYTD, String paymentAfterLeaving,
			String TestResultExcelFilePath, String TestReportworksheetNo,
			String ExpectedResultRowNumOfTestResultFile,
			String ActualResultRowNumOfTestResultFile,
			String TestRemarkRowNumOfTestResultFile) throws Throwable {
		double worksheetvalue = Double.parseDouble(TestReportworksheetNo);
		DecimalFormat df = new DecimalFormat("###.#");
		String worksheetNoWithoutDecimal = df.format(worksheetvalue);
		int TRwNo = Integer.parseInt(worksheetNoWithoutDecimal);
		System.out
				.println("The converted integer TestReportWorksheet value is  :"
						+ TRwNo);

		File excel = new File(TestResultExcelFilePath);
		FileInputStream fis = new FileInputStream(excel);
		org.apache.poi.ss.usermodel.Workbook wb = WorkbookFactory.create(fis);
		org.apache.poi.ss.usermodel.Sheet ws = wb.getSheetAt(TRwNo);

		CellStyle style = wb.createCellStyle();
		style.setFillPattern(CellStyle.ALIGN_FILL);
		style.setFillBackgroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
		Font font = wb.createFont();
		font.setColor(IndexedColors.BLACK.getIndex());
		style.setFont(font);

		CellStyle styleFalse = wb.createCellStyle();
		styleFalse.setFillPattern(CellStyle.ALIGN_FILL);
		styleFalse.setFillBackgroundColor(IndexedColors.GOLD.getIndex());

		FileOutputStream webdata = new FileOutputStream(TestResultExcelFilePath);

		double actualDataRowNo = Double
				.parseDouble(ActualResultRowNumOfTestResultFile);
		DecimalFormat actualData = new DecimalFormat("###.#");
		String actualRowNoWithoutDecimal = actualData.format(actualDataRowNo);
		int actualRowIntValue = Integer.parseInt(actualRowNoWithoutDecimal);
		System.out.println("The converted integer actualRowdataNo value is  :"
				+ actualRowIntValue);

		int rowNum = ws.getLastRowNum() + 1;
		for (int i = actualRowIntValue; i < rowNum; i++) // getting the expected
															// data from
															// expected result
															// data row
		{
			Row row = ws.getRow(i);

			if (value1 != null && value1.equalsIgnoreCase(firstCellOfBody)) {
				row.createCell(2).setCellValue(nationalInsurance);
				row.createCell(3).setCellValue(Title);
				row.createCell(4).setCellValue(Forename);

				row.createCell(5).setCellValue(Surname);
				row.createCell(6).setCellValue(addressLine1);
				row.createCell(7).setCellValue(addressLine2);

				row.createCell(8).setCellValue(ukPostcode);
				row.createCell(9).setCellValue(dateOfBirth);
				row.createCell(10).setCellValue(currentGender);

				row.createCell(11).setCellValue(payrollId);
				row.createCell(12).setCellValue(payrollIdChanged);
				row.createCell(13).setCellValue(oldPayrollId);
				row.createCell(14).setCellValue(leavingDate);

				row.createCell(15).setCellValue(startDate);
				row.createCell(16).setCellValue(startingDeclaration);

				row.createCell(17).setCellValue(taxablePay);
				row.createCell(18).setCellValue(totaltax);
				row.createCell(19).setCellValue(bacsHashcode);

				row.createCell(20).setCellValue(PayFrequency);
				row.createCell(21).setCellValue(paymentDate);
				row.createCell(22).setCellValue(taxMonthNumber);

				row.createCell(23).setCellValue(numberOfEarningsperiodsCovered);
				row.createCell(24).setCellValue(numberOfNormalHoursWorked);
				row.createCell(25).setCellValue(taxBasis);

				row.createCell(26).setCellValue(taxCode);
				row.createCell(27).setCellValue(taxablePayInPeriod);
				row.createCell(28).setCellValue(payAfterStatutoryDeductions);

				row.createCell(29).setCellValue(taxDeductedORrefunded);
				row.createCell(30).setCellValue(niCategory);
				row.createCell(31).setCellValue(grossEarningsNICsInPeriod);

				row.createCell(32).setCellValue(grossEarningsNICsInYTD);
				row.createCell(33)
						.setCellValue(earningsAtLowerEarningslimitYTD);
				row.createCell(34).setCellValue(earningsLELUpToIncludingPTYTD);

				row.createCell(35).setCellValue(earningsPTIncludingUELYTD);
				row.createCell(36).setCellValue(totalOfEmployerContributions);
				row.createCell(37)
						.setCellValue(totalOfEmployerContributionsYTD);

				row.createCell(38).setCellValue(employeeContributionsPayable);
				row.createCell(39)
						.setCellValue(employeeContributionsPayableYTD);
				row.createCell(40).setCellValue(pymtAftrLeavingDate);
				System.out
						.println("pasted actual Result data into the test result excel file");
				break;
			}
		}
		wb.write(webdata);
		webdata.close();
		fis.close();
	}

	public void ReadsExpectedData1(String EmpName, String nationalInsurance,
			String Title, String Forename, String Surname, String addressLine1,
			String addressLine2, String ukPostcode, String dateOfBirth,
			String currentGender, String payrollId, String payrollIdChanged,
			String oldPayrollId, String leavingDate, String startDate,
			String startingDeclaration, String taxablePay, String totaltax,
			String bacsHashcode, String PayFrequency, String paymentDate,
			String taxMonthNumber, String numberOfEarningsperiodsCovered,
			String numberOfNormalHoursWorked, String taxBasis, String taxCode,
			String taxablePayInPeriod, String payAfterStatutoryDeductions,
			String taxDeductedORrefunded, String niCategory,
			String grossEarningsNICsInPeriod, String grossEarningsNICsInYTD,
			String earningsAtLowerEarningslimitYTD,
			String earningsLELUpToIncludingPTYTD,
			String earningsPTIncludingUELYTD,
			String totalOfEmployerContributions,
			String totalOfEmployerContributionsYTD,
			String employeeContributionsPayable,
			String employeeContributionsPayableYTD, String paymentAfterLeaving,
			String TestResultExcelFilePath, String TestReportworksheetNo,
			String ExpectedResultRowNumOfTestResultFile,
			String ActualResultRowNumOfTestResultFile,
			String TestRemarkRowNumOfTestResultFile) throws Throwable {
		System.out.println("This is ReadExpected data1");
		double worksheetvalue = Double.parseDouble(TestReportworksheetNo);
		DecimalFormat df = new DecimalFormat("###.#");
		String worksheetNoWithoutDecimal = df.format(worksheetvalue);
		int TRwNo = Integer.parseInt(worksheetNoWithoutDecimal);
		System.out
				.println("The converted integer TestReportWorksheet value is  :"
						+ TRwNo);

		File excel = new File(TestResultExcelFilePath);
		FileInputStream fis = new FileInputStream(excel);
		org.apache.poi.ss.usermodel.Workbook wb = WorkbookFactory.create(fis);
		org.apache.poi.ss.usermodel.Sheet ws = wb.getSheetAt(TRwNo);

		CellStyle style = wb.createCellStyle();
		style.setFillPattern(CellStyle.ALIGN_FILL);
		style.setFillBackgroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
		Font font = wb.createFont();
		font.setColor(IndexedColors.BLACK.getIndex());
		style.setFont(font);

		CellStyle styleFalse = wb.createCellStyle();
		styleFalse.setFillPattern(CellStyle.ALIGN_FILL);
		styleFalse.setFillBackgroundColor(IndexedColors.GOLD.getIndex());

		FileOutputStream webdata = new FileOutputStream(TestResultExcelFilePath);

		double testRemarkDataRowNo = Double
				.parseDouble(TestRemarkRowNumOfTestResultFile);
		DecimalFormat testRmrkData = new DecimalFormat("###.#");
		String testRmrkRowNoWithoutDecimal = testRmrkData
				.format(testRemarkDataRowNo);
		int testRmrkRowIntValue = Integer.parseInt(testRmrkRowNoWithoutDecimal);
		System.out.println("The converted integer TestRemarkRowNo value is  :"
				+ testRmrkRowIntValue);

		int rowNum = ws.getLastRowNum() + 1;
		for (int j = testRmrkRowIntValue; j < rowNum; j++) {
			Row row = ws.getRow(j);
			System.out.println("the value stored in value1 is :" + value1);
			if (value1 != null && value1.equalsIgnoreCase(firstCellOfBody)) {
				System.out.println("the value stored in value2 is :" + value2
						+ "needs to be compared");
				if (value2 != null
						&& value2.equalsIgnoreCase(nationalInsurance)) {
					Cell cell1 = row.createCell(2);
					row.createCell(2).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(2);
					row.createCell(2).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}
				if (value3 != null && value3.equalsIgnoreCase(Title)) {
					Cell cell1 = row.createCell(3);
					row.createCell(3).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(3);
					row.createCell(3).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value4 != null && value4.equalsIgnoreCase(Forename)) {
					Cell cell1 = row.createCell(4);
					row.createCell(4).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(4);
					row.createCell(4).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value5 != null && value5.equalsIgnoreCase(Surname)) {
					Cell cell1 = row.createCell(5);
					row.createCell(5).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(5);
					row.createCell(5).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value6 != null && value6.equalsIgnoreCase(addressLine1)) {
					Cell cell1 = row.createCell(6);
					row.createCell(6).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(6);
					row.createCell(6).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value7 != null && value7.equalsIgnoreCase(addressLine2)) {
					Cell cell1 = row.createCell(7);
					row.createCell(7).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(7);
					row.createCell(7).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value8 != null && value8.equalsIgnoreCase(ukPostcode)) {
					Cell cell1 = row.createCell(8);
					row.createCell(8).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(8);
					row.createCell(8).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value9 != null && value9.equalsIgnoreCase(dateOfBirth)) {
					Cell cell1 = row.createCell(9);
					row.createCell(9).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(9);
					row.createCell(9).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value10 != null && value10.equalsIgnoreCase(currentGender)) {
					Cell cell1 = row.createCell(10);
					row.createCell(10).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(10);
					row.createCell(10).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value11 != null && value11.equalsIgnoreCase(payrollId)) {
					Cell cell1 = row.createCell(11);
					row.createCell(11).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(11);
					row.createCell(11).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value12 != null
						&& value12.equalsIgnoreCase(payrollIdChanged)) {
					Cell cell1 = row.createCell(12);
					row.createCell(12).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(12);
					row.createCell(12).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value13 != null && value13.equalsIgnoreCase(oldPayrollId)) {
					Cell cell1 = row.createCell(13);
					row.createCell(13).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(13);
					row.createCell(13).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value14 != null && value14.equalsIgnoreCase(leavingDate)) {
					Cell cell1 = row.createCell(14);
					row.createCell(14).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(14);
					row.createCell(14).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value15 != null && value15.equalsIgnoreCase(startDate)) {
					Cell cell1 = row.createCell(15);
					row.createCell(15).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(15);
					row.createCell(15).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value16 != null
						&& value16.equalsIgnoreCase(startingDeclaration)) {
					Cell cell1 = row.createCell(16);
					row.createCell(16).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(16);
					row.createCell(16).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value17 != null && value17.equalsIgnoreCase(taxablePay)) {
					Cell cell1 = row.createCell(17);
					row.createCell(17).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(17);
					row.createCell(17).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value18 != null && value18.equalsIgnoreCase(totaltax)) {
					Cell cell1 = row.createCell(18);
					row.createCell(18).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(18);
					row.createCell(18).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value19 != null && value19.equalsIgnoreCase(bacsHashcode)) {
					Cell cell1 = row.createCell(19);
					row.createCell(19).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(19);
					row.createCell(19).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value20 != null && value20.equalsIgnoreCase(PayFrequency)) {
					Cell cell1 = row.createCell(20);
					row.createCell(20).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(20);
					row.createCell(20).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value21 != null && value21.equalsIgnoreCase(paymentDate)) {
					Cell cell1 = row.createCell(21);
					row.createCell(21).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(21);
					row.createCell(21).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value22 != null && value22.equalsIgnoreCase(taxMonthNumber)) {
					Cell cell1 = row.createCell(22);
					row.createCell(22).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(22);
					row.createCell(22).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value23 != null
						&& value23
								.equalsIgnoreCase(numberOfEarningsperiodsCovered)) {
					Cell cell1 = row.createCell(23);
					row.createCell(23).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(23);
					row.createCell(23).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value24 != null
						&& value24.equalsIgnoreCase(numberOfNormalHoursWorked)) {
					Cell cell1 = row.createCell(24);
					row.createCell(24).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(24);
					row.createCell(24).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value25 != null && value25.equalsIgnoreCase(taxBasis)) {
					Cell cell1 = row.createCell(25);
					row.createCell(25).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(25);
					row.createCell(25).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value26 != null && value26.equalsIgnoreCase(taxCode)) {
					Cell cell1 = row.createCell(26);
					row.createCell(26).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(26);
					row.createCell(26).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value27 != null
						&& value27.equalsIgnoreCase(taxablePayInPeriod)) {
					Cell cell1 = row.createCell(27);
					row.createCell(27).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(27);
					row.createCell(27).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value28 != null
						&& value28
								.equalsIgnoreCase(payAfterStatutoryDeductions)) {
					Cell cell1 = row.createCell(28);
					row.createCell(28).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(28);
					row.createCell(28).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value29 != null
						&& value29.equalsIgnoreCase(taxDeductedORrefunded)) {
					Cell cell1 = row.createCell(29);
					row.createCell(29).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(29);
					row.createCell(29).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value30 != null && value30.equalsIgnoreCase(niCategory)) {
					Cell cell1 = row.createCell(30);
					row.createCell(30).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(30);
					row.createCell(30).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value31 != null
						&& value31.equalsIgnoreCase(grossEarningsNICsInPeriod)) {
					Cell cell1 = row.createCell(31);
					row.createCell(31).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(31);
					row.createCell(31).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value32 != null
						&& value32.equalsIgnoreCase(grossEarningsNICsInYTD)) {
					Cell cell1 = row.createCell(32);
					row.createCell(32).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(32);
					row.createCell(32).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value33 != null
						&& value33
								.equalsIgnoreCase(earningsAtLowerEarningslimitYTD)) {
					Cell cell1 = row.createCell(33);
					row.createCell(33).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(33);
					row.createCell(33).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value34 != null
						&& value34
								.equalsIgnoreCase(earningsLELUpToIncludingPTYTD)) {
					Cell cell1 = row.createCell(34);
					row.createCell(34).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(34);
					row.createCell(34).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value35 != null
						&& value35.equalsIgnoreCase(earningsPTIncludingUELYTD)) {
					Cell cell1 = row.createCell(35);
					row.createCell(35).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(35);
					row.createCell(35).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value36 != null
						&& value36
								.equalsIgnoreCase(totalOfEmployerContributions)) {
					Cell cell1 = row.createCell(36);
					row.createCell(36).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(36);
					row.createCell(36).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value37 != null
						&& value37
								.equalsIgnoreCase(totalOfEmployerContributionsYTD)) {
					Cell cell1 = row.createCell(37);
					row.createCell(37).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(37);
					row.createCell(37).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value38 != null
						&& value38
								.equalsIgnoreCase(employeeContributionsPayable)) {
					Cell cell1 = row.createCell(38);
					row.createCell(38).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(38);
					row.createCell(38).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}

				if (value39 != null
						&& value39
								.equalsIgnoreCase(employeeContributionsPayableYTD)) {
					Cell cell1 = row.createCell(39);
					row.createCell(39).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(39);
					row.createCell(39).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}
				if (value40 != null
						&& value40.equalsIgnoreCase(pymtAftrLeavingDate)) {
					Cell cell1 = row.createCell(40);
					row.createCell(40).setCellValue("TRUE");
					cell1.setCellStyle(style);
				} else {
					Cell cell1 = row.createCell(40);
					row.createCell(40).setCellValue("FALSE");
					cell1.setCellStyle(styleFalse);
				}
				break;
			}
		}
		System.out
				.println("Entered the test remarks into the excel sheet successfully");
		wb.write(webdata);
		webdata.close();
		fis.close();
	}

	public String cellToString(Cell cell) {
		int type;
		Object result;
		type = cell.getCellType();
		switch (type) {
		case 0: // to get numeric value from the cell
			result = Double.toString(cell.getNumericCellValue());
			break;
		case 1: // to get string value from the cell
			result = cell.getStringCellValue();
			break;
		case 2:
			result = cell.getCellFormula();
			break;
		case 3:
			result = cell == null;
			break;
		case 4:
			result = cell.getRichStringCellValue();
			break;
		case 5:
			result = cell.getCellType();
		default:
			throw new RuntimeException("there are no othe values");
		}
		return result.toString();
	}

	/*
	 * Payroll Tax,NI,etc input scripts methods.
	 */
	
	public void UpdateEmployeeNICategory(String empName, String NICategory)
			throws Throwable {
		try {
			if (employeeFirsttimeView) {
				employeeFirsttimeView = false;
				getObject("PersonalTab").click();
				if (existsElementchkFor1mts(OR.getProperty("PersonalText"))) {
					System.out.println("I am in personal page");
					if (existsElementchkFor1mts(OR.getProperty("EmployeeView"))) {
						System.out.println("I recognised the Employee view");
						Select selectByValue = new Select(driver.findElement(By
								.xpath(OR.getProperty("EmployeeView"))));
						selectByValue
								.selectByVisibleText("DO NOT TOUCH PAYROLL AUTOMATION TESTING");
						Thread.sleep(2000L);
						if (existsElementchkFor1mts(OR
								.getProperty("ViewGoButton"))) {
							getObject("ViewGoButton").sendKeys("");
							getObject("ViewGoButton").click();
							System.out.println("The Go button got clicked");
						}
						Thread.sleep(7000L);
					}
				}
			}
			Thread.sleep(2000L);
			try {
				if (existsElementchkFor1mts(OR
						.getProperty("firstRecordOfTaxCodecoulmnTable"))) {
					WebElement postsTable = driver.findElement(By.xpath(OR
							.getProperty("firstRecordOfTaxCodecoulmnTable")));
					if (existsWebElement(postsTable)) {
						try {
							WebElement tableheader = driver.findElement(By.xpath(OR
									.getProperty("PersonalAndCompensationHeadingTable")));
							List<WebElement> th = tableheader.findElements(By.tagName("td"));
							for (a = 0; a < th.size(); a++) {
								if ("Employee".equalsIgnoreCase(th.get(a).getText())) {
									empcolnum = a + 1;
									break;
								}
							}

							for (b = 0; b < th.size(); b++) {
								if ("NI category".equalsIgnoreCase(th.get(b).getText())) {
									niCategoryColumn = b + 1;
									break;
								}
							}
							WebElement postsTable1 = driver.findElement(By.xpath(OR
									.getProperty("firstRecordOfTaxCodecoulmnTable")));
							List<WebElement> rows = postsTable1.findElements(By.xpath(OR
									.getProperty("firstRecordOfTaxCodecoulmnTableRows")));
							lastRowCount = rows.size();
							java.util.Iterator<WebElement> x = rows.iterator();
							rownum = 1;
							outerbreak: while (x.hasNext()) {
								// Thread.sleep(2000L);
								String firstRowOfEmployeeColumn = "//div[" + rownum
										+ "]/table/tbody/tr/td" + "[" + empcolnum + "]" + "/"
										+ "div/a/span";
								WebElement tempElement = driver.findElement(By
										.xpath(firstRowOfEmployeeColumn));
								String tempEmp = tempElement.getText();
								System.out.println(tempEmp + "-------" + empName + "------"
										+ rownum);
								String firstRowOfTaxCode = "//div[" + rownum + "]" + "/"
										+ "table/" + "tbody/" + "tr/" + "td["
										+ niCategoryColumn + "]" + "/" + "div";
								if (tempEmp != null && tempEmp.equalsIgnoreCase(empName)) {
									System.out.println("Employee name  :" + tempEmp
											+ "  matched ");
									Thread.sleep(2000L);
									if (existsElementchkFor1mts(firstRowOfTaxCode)) {
										Actions action = new Actions(driver);
										action.doubleClick(
												driver.findElement(By.xpath(firstRowOfTaxCode)))
												.perform();
										action.moveToElement(getObject("InlineDropdown"))
												.perform();
										// Thread.sleep(2000L);
										if (existsElementchkFor1mts(OR
												.getProperty("InlineDropdown"))) {
											getObject("InlineDropdown").sendKeys("");
											getObject("InlineDropdown").sendKeys(NICategory);
											System.out.println("Selected the NI Picklist item "
													+ NICategory);
											Thread.sleep(2000L);
											if (existsElementchkFor1mts(OR
													.getProperty("InlineUpdateButn"))) {
												getObject("InlineUpdateButn").click();
												System.out
														.println("The update button got clicked and NI Category got saved");
												Thread.sleep(8000L);
												break outerbreak;
											}
										}
									}
								} else if (rownum == lastRowCount && tempEmp != null
										&& tempEmp != (empName)) {
									rownum++;
									System.out
											.println("The row number of the page reached"
													+ rownum
													+ " to 200 and"
													+ " Required Employee not found hence clicking the"
													+ " pagination link so that Employee search continues for next page");
									if (existsElementchkFor1mts(OR
											.getProperty("paginationElementPersonal"))) {
										getObject("paginationNextPersonal").sendKeys("");
										getObject("paginationNextPersonal").click();
										System.out
												.println("As the required employees are not found in first page,hence clicked to next page of personal Tab");
										rownum = 0;
										Thread.sleep(8000L);
										
									} else {
										System.out
												.println("The employee which you are searching "
														+ "is not available in all the pages"
														+ "of this Personal / Compensation Tab "
														+ "of the Application. Hence the script unfortunately is "
														+ "not able to execute successfully. Please include the said employee"
														+ "in the said Tab of the application and run once again the script");
										closeBrowser();
									}

								} else
									System.out.println("incrementing the row number");
								rownum++;
							}
						} catch (Throwable t) {
							System.out.println(t.getMessage());
							System.out.println(t.getStackTrace().toString());
						}
					}
				}
			} catch (Throwable t) {
				System.out.println(t.getStackTrace().toString());
				System.out.println("");
			}
		} catch (Throwable t) {
			System.out.println(t.getStackTrace().toString());
			System.out.println("");
		}
	}


	
	/*************** Director's NI As Employee related methods ***************************************************/

	/*
	 * The fllowing are Director as employee mehtods.Pagination code is
	 * implemented in the following methods where employee is based on
	 * pagination and processed required functionality.
	 */

	public void UpdateEmployeeNICategory(String empName, String NICategory,
			String DirectorsNIBasis, String DirectorSince) throws Throwable {
		try {
			if (employeeFirsttimeView) {
				employeeFirsttimeView = false;
				getObject("PersonalTab").click();
				if (existsElement(OR.getProperty("PersonalText"))) {
					System.out.println("I am in personal page");
					if (existsElement(OR.getProperty("EmployeeView"))) {
						System.out.println("I recognised the Employee view");
						Select selectByValue = new Select(driver.findElement(By
								.xpath(OR.getProperty("EmployeeView"))));
						selectByValue
								.selectByVisibleText("DO NOT TOUCH PAYROLL AUTOMATION TESTING");
						Thread.sleep(2000L);
						if (existsElement(OR.getProperty("ViewGoButton"))) {
							getObject("ViewGoButton").sendKeys("");
							getObject("ViewGoButton").click();
						}
						Thread.sleep(7000L);
					}
				}
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage());
			System.out.println(t.getStackTrace().toString());
		}

		WebElement tableheader = driver.findElement(By.xpath(OR
				.getProperty("PersonalAndCompensationHeadingTable")));
		List<WebElement> th = tableheader.findElements(By.tagName("td"));
		System.out.println("recognised the header");
		for (a = 0; a < th.size(); a++) {
			if ("Employee".equalsIgnoreCase(th.get(a).getText())) {
				System.out.println("employee");
				empcolnum = a + 1;
				break;
			}
		}
		for (b = 0; b < th.size(); b++) {
			if ("NI category".equalsIgnoreCase(th.get(b).getText())) {
				System.out.println("ni category");
				niCategoryColumn = b + 1;
				break;
			}
		}

		for (d = 0; d < th.size(); d++) {
			if ("Director's NI basis".equalsIgnoreCase(th.get(d).getText())) {
				System.out.println("nibasis");
				directorNIBasis = d + 1;
				break;
			}
		}

		for (e = 0; e < th.size(); e++) {
			if ("Director since".equalsIgnoreCase(th.get(e).getText())) {
				System.out.println("directory since");
				directorSince = e + 1;
				break;
			}
		}
		// Thread.sleep(2000L);

		WebElement postsTable = driver.findElement(By.xpath(OR
				.getProperty("firstRecordOfTaxCodecoulmnTable")));
		if (existsWebElement(postsTable)) {
			System.out.println("Found the table");
			List<WebElement> rows = postsTable.findElements(By.xpath(OR
					.getProperty("firstRecordOfTaxCodecoulmnTableRows")));
			lastRowCount = rows.size();
			java.util.Iterator<WebElement> x = rows.iterator();
			rownum = 1;
			outerbreak: while (x.hasNext()) {
				try {
					// Thread.sleep(2000L);
					String firstRowOfEmployeeColumn = "//div[" + rownum
							+ "]/table/tbody/tr/td" + "[" + empcolnum + "]"
							+ "/" + "div/a/span";
					if (existsElement(firstRowOfEmployeeColumn)) {
						WebElement tempElement = driver.findElement(By
								.xpath(firstRowOfEmployeeColumn));
						String tempEmp = tempElement.getText();
						System.out.println(tempEmp + "-------" + empName
								+ "------" + rownum);
						String firstRowOfTaxCode = "//div[" + rownum + "]"
								+ "/" + "table/" + "tbody/" + "tr/" + "td["
								+ niCategoryColumn + "]" + "/" + "div";
						if (tempEmp != null
								&& tempEmp.equalsIgnoreCase(empName)) {
							System.out.println("Employee name  :" + tempEmp
									+ "  matched ");
							Thread.sleep(2000L);
							if (existsElement(firstRowOfTaxCode)) {
								Actions action = new Actions(driver);
								action.doubleClick(
										driver.findElement(By
												.xpath(firstRowOfTaxCode)))
										.perform();
								action.moveToElement(
										getObject("InlineDropdown")).perform();
								Thread.sleep(2000L);
								if (existsElement(OR
										.getProperty("InlineDropdown"))) {
									getObject("InlineDropdown").sendKeys("");
									getObject("InlineDropdown").sendKeys(
											NICategory);
									System.out
											.println("Selected the NI Picklist item "
													+ NICategory);
									Thread.sleep(2000L);
									if (existsElement(OR
											.getProperty("InlineUpdateButn"))) {
										getObject("InlineUpdateButn").click();
										System.out
												.println("The update button got clicked and NI Category got saved");
									}
								}
							}
							UpdateDirectorsNIBasis(empName, NICategory,
									DirectorsNIBasis, DirectorSince);
							Thread.sleep(3000L);
							UpdateDirectorsSince(empName, NICategory,
									DirectorsNIBasis, DirectorSince);
							break outerbreak;
						} else if (rownum == lastRowCount && tempEmp != null
								&& tempEmp != (empName)) {
							System.out
									.println("The row number of the page reached"
											+ rownum
											+ " to 200 and"
											+ " Required Employee not found hence clicking the"
											+ " pagination link so that Employee search continues for next page");
							if (existsElementchkFor1mts(OR
									.getProperty("paginationElementPersonal"))) {
								getObject("paginationNextPersonal")
										.sendKeys("");
								getObject("paginationNextPersonal").click();
								System.out
										.println("As the required employees are not found in first page,hence clicked to next page of personal Tab");
								Thread.sleep(8000L);
								rownum = 0;
							} else {
								System.out
										.println("The employee which you are searching "
												+ "is not available in all the pages"
												+ "of this Personal / Compensation Tab "
												+ "of the Application. Hence the script unfortunately is "
												+ "not able to execute successfully. Please include the said employee"
												+ "in the said Tab of the application and run once again the script");
								closeBrowser();
							}

						} else
							System.out.println("incrementing the row number");
						rownum++;
					}
				} catch (Throwable t) {
					System.out.println(t.getMessage());
					System.out.println(t.getStackTrace().toString());
				}
			}
		}

	}

	public void UpdateDirectorsNIBasis(String epName, String NICat,
			String DIBasis, String DtorSince) throws Throwable {
		try {
			Thread.sleep(3000L);
			String firstRowOfDirtorsNIbasisColumn = "//div[" + rownum + "]"
					+ "/" + "table/" + "tbody/" + "tr/" + "td["
					+ directorNIBasis + "]" + "/" + "div";
			if (existsElement(firstRowOfDirtorsNIbasisColumn)) {
				Actions action3a = new Actions(driver);
				action3a.doubleClick(
						driver.findElement(By
								.xpath(firstRowOfDirtorsNIbasisColumn)))
						.perform();
				action3a.moveToElement(getObject("InlineDropdown")).perform();
				Thread.sleep(2000L);
				if (existsElement(OR.getProperty("InlineDropdown"))) {
					getObject("InlineDropdown").sendKeys("");
					getObject("InlineDropdown").sendKeys(DIBasis);
					System.out.println("Selected the DI Basis item " + DIBasis);
					Thread.sleep(2000L);
					if (existsElement(OR.getProperty("InlineUpdateButn"))) {
						getObject("InlineUpdateButn").click();
						System.out
								.println("updated DirectorsNI Basis successfully");
					}
				}
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void UpdateDirectorsSince(String epName, String NICat,
			String DIBasis, String DtorSince) throws Throwable {
		try {
			Thread.sleep(3000L);
			String firstRowOfDirtorsSinceColumn = "//div[" + rownum + "]" + "/"
					+ "table/" + "tbody/" + "tr/" + "td[" + directorSince + "]"
					+ "/" + "div";
			if (existsElement(firstRowOfDirtorsSinceColumn)) {
				Actions action4a = new Actions(driver);
				action4a.doubleClick(
						driver.findElement(By
								.xpath(firstRowOfDirtorsSinceColumn)))
						.perform();
				action4a.moveToElement(getObject("directorsincetxtfild"))
						.perform();
				Thread.sleep(1000L);
				getObject("directorsincetxtfild").sendKeys("");
				getObject("directorsincetxtfild").clear();
				String dateStr = DtorSince;
				DateFormat readFormat = new SimpleDateFormat("MM/dd/yyyy");
				DateFormat writeFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date date = null;
				try {
					date = readFormat.parse(dateStr.trim());
					System.out.println(date.toString());
				} catch (ParseException e) {
					System.out.println(e.getMessage());
				}

				String formattedDate = null;
				if (date != null) {
					formattedDate = writeFormat.format(date);
				}
				System.out.println("The entered date is  " + formattedDate);
				Thread.sleep(4000L);
				getObject("directorsincetxtfild").sendKeys(formattedDate);
				getObject("outersideclk").click();
				Thread.sleep(1000L);
				getObject("drsinceupdatebttn").click();
				Thread.sleep(6000L);
				System.out.println("updated DirectorsSince successfully");
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	/******************************** 'Director_Under21Month1' method ***************************/

	public void UpdateEmployeeNICategory(String empName, String NICategory,
			String DirectorsNIBasis, String DirectorSince, String DateOfBirth)
			throws Throwable {
		try {
			if (employeeFirsttimeView) {
				employeeFirsttimeView = false;
				getObject("PersonalTab").click();
				if (existsElement(OR.getProperty("PersonalText"))) {
					System.out.println("I am in personal page");
					if (existsElement(OR.getProperty("EmployeeView"))) {
						System.out.println("I recognised the Employee view");
						Select selectByValue = new Select(driver.findElement(By
								.xpath(OR.getProperty("EmployeeView"))));
						selectByValue
								.selectByVisibleText("DO NOT TOUCH PAYROLL AUTOMATION TESTING");
						Thread.sleep(2000L);
						getObject("ViewGoButton").sendKeys("");
						getObject("ViewGoButton").click();
						Thread.sleep(7000L);
					}
				}
			}
			Row_count = driver.findElements(
					By.xpath("//div[@id='ext-gen11']/div/table/tbody/tr"))
					.size();
			WebElement postsTable = driver.findElement(By.xpath(OR
					.getProperty("firstRecordOfNIcoulmnTable")));
			if (existsWebElement(postsTable)) {
				List<WebElement> rows = postsTable.findElements(By.xpath(OR
						.getProperty("firstRecordOfNIcoulmnTableRows")));
				lastRowCount = rows.size();
				java.util.Iterator<WebElement> x = rows.iterator();
				rownumNI = 1;
				outerbreak: while (x.hasNext()) {
					String firstRowOfEmployeeColumn = "//div[" + rownumNI
							+ "]/table/tbody/tr/td[4]/div/a/span";
					if (existsElement(firstRowOfEmployeeColumn)) {
						WebElement firstEmployee = driver.findElement(By
								.xpath(firstRowOfEmployeeColumn));
						String AppnEmp = firstEmployee.getText();
						System.out.println(AppnEmp + "-------" + empName
								+ "------" + rownumNI);
						if (AppnEmp != null
								&& AppnEmp.equalsIgnoreCase(empName)) {
							System.out.println("Employee matched");
							Thread.sleep(3000L);
							String firstRowOfNIColumn = "//div[" + rownumNI
									+ "]" + "/" + "table/" + "tbody/" + "tr/"
									+ "td[" + "6]" + "/" + "div";
							if (existsElement(firstRowOfNIColumn)) {
								String rowNumberOfNIColumn = "//div["
										+ rownumNI + "]" + "/" + "table/"
										+ "tbody/" + "tr/" + "td[" + "6]" + "/"
										+ "div";
								Actions action = new Actions(driver);
								action.doubleClick(
										driver.findElement(By
												.xpath(rowNumberOfNIColumn)))
										.perform();
								action.moveToElement(
										getObject("InlineDropdown")).perform();
								Thread.sleep(2000L);
								if (existsElement(OR
										.getProperty("InlineDropdown"))) {
									getObject("InlineDropdown").sendKeys(
											NICategory);
									Thread.sleep(2000L);
									getObject("InlineUpdateButn").click();
									System.out
											.println("updated NI Category successfully");
									Thread.sleep(6000L);
								}
							}
							UpdateDirectorsNIBasis(empName, NICategory,
									DirectorsNIBasis, DirectorSince,
									DateOfBirth);
							Thread.sleep(3000L);
							UpdateDirectorsSince(empName, NICategory,
									DirectorsNIBasis, DirectorSince,
									DateOfBirth);
							Thread.sleep(3000L);
							DateofBirth(empName, NICategory, DirectorsNIBasis,
									DirectorSince, DateOfBirth);
							break outerbreak;
						} else if (rownumNI == lastRowCount && AppnEmp != null
								&& AppnEmp != (empName)) {
							System.out
									.println("The row number of the page reached"
											+ rownumNI
											+ " to 200 and"
											+ " Required Employee not found hence clicking the"
											+ " pagination link so that Employee search continues for next page");
							if (existsElementchkFor1mts(OR
									.getProperty("paginationElementPersonal"))) {
								getObject("paginationNextPersonal")
										.sendKeys("");
								getObject("paginationNextPersonal").click();
								System.out
										.println("As the required employees are not found in first page,hence clicked to next page of personal Tab");
								Thread.sleep(8000L);
								rownumNI = 0;
							}
						}
					}
					rownumNI++;
				}
			}
		} catch (Throwable t) {
			APP_LOGS.debug(" Check for error in NI Category method");
			System.out.println(t.getStackTrace().toString());
			// ErrorUtil.addVerificationFailure(t);
			System.out.println("");
		}
	}

	public void UpdateDirectorsNIBasis(String epName, String NICat,
			String DIBasis, String DtorSince, String DateOfBirth)
			throws Throwable {
		try {
			Thread.sleep(3000L);
			String firstRowOfDirtorsNIbasisColumn = "//div[" + rownumNI + "]"
					+ "/" + "table/" + "tbody/" + "tr/" + "td[" + "9]" + "/"
					+ "div";
			if (existsElement(firstRowOfDirtorsNIbasisColumn)) {
				String rowNumberOfDirctrNIbsisColumn = "//div[" + rownumNI
						+ "]" + "/" + "table/" + "tbody/" + "tr/" + "td["
						+ "9]" + "/" + "div";
				Actions action3a = new Actions(driver);
				action3a.doubleClick(
						driver.findElement(By
								.xpath(rowNumberOfDirctrNIbsisColumn)))
						.perform();
				action3a.moveToElement(getObject("InlineDropdown")).perform();
				Thread.sleep(2000L);
				if (existsElement(OR.getProperty("InlineDropdown"))) {
					getObject("InlineDropdown").sendKeys(DIBasis);
					Thread.sleep(2000L);
					getObject("InlineUpdateButn").click();
					Thread.sleep(6000L);
					System.out
							.println("updated DirectorsNI Basis successfully");
				}
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void UpdateDirectorsSince(String epName, String NICat,
			String DIBasis, String DtorSince, String DateOfBirth)
			throws Throwable {
		try {
			Thread.sleep(3000L);
			String firstRowOfDirtorsSinceColumn = "//div[" + rownumNI + "]"
					+ "/" + "table/" + "tbody/" + "tr/" + "td[" + "10]" + "/"
					+ "div";
			if (existsElement(firstRowOfDirtorsSinceColumn)) {
				String rowNumberOfDirctrSinceColumn = "//div[" + rownumNI + "]"
						+ "/" + "table/" + "tbody/" + "tr/" + "td[" + "10]"
						+ "/" + "div";
				Actions action4a = new Actions(driver);
				action4a.doubleClick(
						driver.findElement(By
								.xpath(rowNumberOfDirctrSinceColumn)))
						.perform();
				action4a.moveToElement(getObject("directorsincetxtfild"))
						.perform();
				Thread.sleep(1000L);
				getObject("directorsincetxtfild").sendKeys("");
				String dateStr = DtorSince;
				DateFormat readFormat = new SimpleDateFormat("MM/dd/yyyy");
				DateFormat writeFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date date = null;
				try {
					date = readFormat.parse(dateStr.trim());
					System.out.println(date.toString());
				} catch (ParseException e) {
					System.out.println(e.getMessage());
				}
				String formattedDate = null;
				if (date != null) {
					formattedDate = writeFormat.format(date);
				}
				System.out.println("The entered date is  " + formattedDate);
				Thread.sleep(4000L);
				getObject("directorsincetxtfild").sendKeys(formattedDate);
				getObject("outersideclk").click();
				Thread.sleep(1000L);
				getObject("drsinceupdatebttn").click();
				Thread.sleep(6000L);
				System.out.println("updated DirectorsSince successfully");
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	public void DateofBirth(String epName, String NICat, String DIBasis,
			String DtorSince, String DateOfBirth) throws Throwable {
		try {
			Thread.sleep(3000L);
			String firstRowOfDirtorsSinceColumn = "//div[" + rownumNI + "]"
					+ "/" + "table/" + "tbody/" + "tr/" + "td[" + "11]" + "/"
					+ "div";
			if (existsElement(firstRowOfDirtorsSinceColumn)) {
				String rowNumberOfDirctrSinceColumn = "//div[" + rownumNI + "]"
						+ "/" + "table/" + "tbody/" + "tr/" + "td[" + "11]"
						+ "/" + "div";
				Actions action5a = new Actions(driver);
				action5a.doubleClick(
						driver.findElement(By
								.xpath(rowNumberOfDirctrSinceColumn)))
						.perform();
				action5a.moveToElement(getObject("directorsincetxtfild"))
						.perform();
				Thread.sleep(1000L);
				getObject("directorsincetxtfild").sendKeys("");
				String dateStr = DateOfBirth;
				DateFormat readFormat = new SimpleDateFormat("MM/dd/yyyy");
				DateFormat writeFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date date = null;
				try {
					date = readFormat.parse(dateStr.trim());
					System.out.println(date.toString());
				} catch (ParseException e) {
					System.out.println(e.getMessage());
				}

				String formattedDate = null;
				if (date != null) {
					formattedDate = writeFormat.format(date);
				}
				System.out.println("The entered date is  " + formattedDate);
				Thread.sleep(4000L);
				getObject("directorsincetxtfild").sendKeys(formattedDate);
				getObject("outersideclk").click();
				Thread.sleep(2000L);
				getObject("drsinceupdatebttn").click();
				Thread.sleep(6000L);
				System.out.println("updated Directors DOB successfully");
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	/***************************** Statutory module common methods ************************/

	public void DeleteLeavefunction(String EmpName, String firstXCDpayDate,
			String payinStartPeriod) throws Throwable {
		try {
			if (employeeFirsttimeView) {
				employeeFirsttimeView = false;
				getObject("PersonalTab").click();
				System.out.println("I am in personal page");
				if (existsElement(OR.getProperty("EmployeeView"))) {
					System.out.println("I recognised the Employee view");
					Select selectByValue = new Select(driver.findElement(By
							.xpath(OR.getProperty("EmployeeView"))));
					selectByValue
							.selectByVisibleText("DO NOT TOUCH PAYROLL AUTOMATION TESTING");
					Thread.sleep(2000L);
					if (existsElement(OR.getProperty("ViewGoButton"))) {
						getObject("ViewGoButton").sendKeys("");
						getObject("ViewGoButton").click();
					}
					Thread.sleep(7000L);
				}
			}

			WebElement postsTable = driver.findElement(By.xpath(OR
					.getProperty("firstRecordOfNIcoulmnTable")));
			List<WebElement> rows = postsTable.findElements(By.xpath(OR
					.getProperty("firstRecordOfNIcoulmnTableRows")));
			lastRowCount = rows.size();

			java.util.Iterator<WebElement> x = rows.iterator();
			int rownum = 1;
			outerbreak: while (x.hasNext()) {
				String empRecord = "//div[" + rownum
						+ "]/table/tbody/tr/td[4]/div/a/span";
				WebElement empwebelement = driver.findElement(By
						.xpath(empRecord));
				String AppnEmp = empwebelement.getText();
				System.out.println(AppnEmp + "-------" + EmpName + "------"
						+ rownum);
				if (AppnEmp != null && AppnEmp.equalsIgnoreCase(EmpName)) {
					System.out.println("Employee matched");
					System.out.println("Employee name is  :" + EmpName);
					Thread.sleep(3000L);
					empwebelement.click();
					break outerbreak;
				} else if (rownum == lastRowCount && AppnEmp != null
						&& AppnEmp != (EmpName)) {
					System.out
							.println("The row number of the page reached"
									+ rownum
									+ " to 200 and"
									+ " Required Employee not found hence clicking the"
									+ " pagination link so that Employee search continues for next page");
					if (existsElementchkFor1mts(OR
							.getProperty("paginationElementPersonal"))) {
						getObject("paginationNextPersonal").sendKeys("");
						getObject("paginationNextPersonal").click();
						System.out
								.println("As the required employees are not found in first page,hence clicked to next page of personal Tab");
						Thread.sleep(8000L);
						rownum = 0;
					}
				}
				rownum++;
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
		Thread.sleep(3000L);
		try {
			/*
			 * if(existsElement(OR.getProperty("employmentTab"))) {
			 * getObject("employmentTab").sendKeys("");
			 * getObject("employmentTab").click();
			 * System.out.println("The employment tab got clicked");
			 * Thread.sleep(4000L); }
			 * 
			 * if(existsElement(OR.getProperty("employmentTabEdit"))) {
			 * updateFirstXcdPayDate(firstXCDpayDate); //Thread.sleep(2000L);
			 * //getObject("makeWaytoDisplayChkbox").sendKeys("");
			 * //getObject("makeWaytoDisplayChkbox").click(); }
			 * 
			 * Thread.sleep(2000L);
			 * if(existsElement(OR.getProperty("sspEditTable"))) {
			 * selectPayinStartPeriod(payinStartPeriod); Thread.sleep(4000L); }
			 */

			if (existsElement(OR.getProperty("leaveTabclk"))) {
				deleteLeaveRecords();
				Thread.sleep(2000L);
			}

			/*
			 * when passing the argument to the 'ReadsExpectedData' method ,
			 * first declare the public string at the top and use it in the
			 * method as argument. But keep in mind, you are passing the
			 * arguments in the same order (sequence) that of method parameters
			 */
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}

	}

	/*
	 * while passing the parameter to the below method you can pass with any
	 * string name.
	 */

	public void deleteLeaveRecords() throws Throwable {
		try {

			if (compensationFirsttimeView) {
				compensationFirsttimeView = false;
				if (existsElement(OR.getProperty("leaveTabclk"))) {
					getObject("leaveTabclk").sendKeys("");
					getObject("leaveTabclk").click();
					Thread.sleep(3000L);
				}
			}

			if (existsElement(OR.getProperty("sppLeavSummaryTableLocator"))) {
				WebElement AEnotifyNoticeTablelocator = driver.findElement(By
						.xpath(OR.getProperty("sppLeavSummaryTableLocator")));
				List<WebElement> rows = AEnotifyNoticeTablelocator
						.findElements(By.xpath(OR
								.getProperty("sppLeavSummaryTableRowsLocator")));
				ttrows = rows.size();
				System.out.println("Total Leave records are :" + ttrows);
				java.util.Iterator<WebElement> x = rows.iterator();
				int rownumv = ttrows;
				endSearchingCompnRecord: while (x.hasNext()) {
					System.out.println("the index of rownumv is  :" + rownumv);

					if (existsElement(OR
							.getProperty("sppLeavSummaryTableLocator"))) {
						RowOfAttachementRecord = "//div[contains(@id,'leaveReq')]/div/table/tbody/"
								+ "tr[" + (rownumv - 1) + "]" + "/td[2]/a";

						WebElement attachmentlink = driver.findElement(By
								.xpath(RowOfAttachementRecord));
						attachmentlink.click();
						System.out.println("Leave record link got clicked");
					}

					if (existsElement(OR
							.getProperty("leaverecordDeleteLocator"))) {
						getObject("leaverecordDeleteLocator").sendKeys("");
						getObject("leaverecordDeleteLocator").click();
						System.out
								.println("The leave record delete button got clicked");
						Thread.sleep(5000L);
						isAlertPresent();
					}

					rownumv--;
					if (rownumv == 1) {
						System.out
								.println("All the leave records got deleted ");
						break endSearchingCompnRecord;
					}
				}
			} else if (!existsElement(OR
					.getProperty("sppLeavSummaryTableLocator"))) {
				System.out.println("Threre are no leave records to delete");
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}

	/************** latest payroll tax module methods *****************************/

	public void UpdateEmployeeTaxCode(String empName, String Taxcode,
			String TaxBasis) throws Throwable {
		try {
			if (employeeFirsttimeView) {
				employeeFirsttimeView = false;
				getObject("PersonalTab").click();
				System.out.println("The personal tab got clicked");
				Thread.sleep(1000L);
				if (existsElement(OR.getProperty("PersonalText"))) {
					System.out
							.println("I am in personal page and found Personal text on left corner of the screen");
					if (existsElement(OR.getProperty("EmployeeView"))) {
						System.out.println("I recognised the Employee view");
						/*
						 * Rather than selecting the drop down item by
						 * 'selectByValue',it is better to select by
						 * 'selectByVisibleText' as select by value may change
						 * during the course of time which means your locators
						 * needs to be updated periodically.
						 */
						Select selectByValue = new Select(driver.findElement(By
								.xpath(OR.getProperty("EmployeeView"))));
						selectByValue
								.selectByVisibleText("DO NOT TOUCH PAYROLL AUTOMATION TESTING");
						Thread.sleep(2000L);
						if (existsElement(OR.getProperty("ViewGoButton"))) {
							getObject("ViewGoButton").sendKeys("");
							getObject("ViewGoButton").click();
						}
						Thread.sleep(7000L);
					}
				}
			}
			WebElement tableheader = driver.findElement(By.xpath(OR
					.getProperty("PersonalAndCompensationHeadingTable")));
			List<WebElement> th = tableheader.findElements(By.tagName("td"));
			for (a = 0; a < th.size(); a++) {
				if ("Employee".equalsIgnoreCase(th.get(a).getText())) {
					empcolnum = a + 1;
					break;
				}
			}
			for (b = 0; b < th.size(); b++) {
				if ("Tax code".equalsIgnoreCase(th.get(b).getText())) {
					taxcodecolnum = b + 1;
					break;
				}
			}

			for (c = 0; c < th.size(); c++) {
				if ("Tax basis".equalsIgnoreCase(th.get(c).getText())) {
					taxbasiscolnum = c + 1;
					break;
				}
			}
			WebElement postsTable = driver.findElement(By.xpath(OR
					.getProperty("firstRecordOfTaxCodecoulmnTable")));
			if (existsWebElement(postsTable)) {
				List<WebElement> rows = postsTable.findElements(By.xpath(OR
						.getProperty("firstRecordOfTaxCodecoulmnTableRows")));
				lastRowCount = rows.size();
				java.util.Iterator<WebElement> x = rows.iterator();

				rownum = 1;
				outerbreak: while (x.hasNext()) {
					// Thread.sleep(2000L);
					String firstRowOfEmployeeColumn = "//div[" + rownum
							+ "]/table/tbody/tr/td" + "[" + empcolnum + "]"
							+ "/" + "div/a/span";
					if (existsElement(firstRowOfEmployeeColumn)) {
						WebElement tempElement = driver.findElement(By
								.xpath(firstRowOfEmployeeColumn));
						String AppnEmp = tempElement.getText();
						System.out.println(AppnEmp + "-------" + empName
								+ "------" + rownum);
						if (AppnEmp != null
								&& AppnEmp.equalsIgnoreCase(empName)) {
							System.out.println("Employee name  :" + AppnEmp
									+ "  matched ");

							Thread.sleep(2000L);
							String firstRowOfTaxCode = "//div[" + rownum + "]"
									+ "/" + "table/" + "tbody/" + "tr/" + "td["
									+ taxcodecolnum + "]" + "/" + "div";
							if (existsElement(firstRowOfTaxCode)) {
								Actions action1 = new Actions(driver);
								action1.doubleClick(
										driver.findElement(By
												.xpath(firstRowOfTaxCode)))
										.perform();
								WebElement updateTaxcode = driver
										.findElement(By.xpath(OR
												.getProperty("taxCodeTextfield")));
								action1.moveToElement(updateTaxcode).perform();
								Thread.sleep(1000L);
								// updateTaxcode.clear();
								updateTaxcode.sendKeys(Taxcode);
								Thread.sleep(1000L);
								if (existsElement(OR
										.getProperty("taxCodeSavebutton"))) {
									getObject("taxCodeSavebutton").click();
									System.out
											.println("Tax code got saved successfully");
								}
								Thread.sleep(6000L);

							}

							UpdateTaxBasis(empName, Taxcode, TaxBasis);
							break outerbreak;
						} else if (rownum == lastRowCount && AppnEmp != null
								&& AppnEmp != (empName)) {
							System.out
									.println("The row number of the page reached"
											+ rownum
											+ " to 200 and"
											+ " Required Employee not found hence clicking the"
											+ " pagination link so that Employee search continues for next page");
							if (existsElementchkFor1mts(OR
									.getProperty("paginationElementPersonal"))) {
								getObject("paginationNextPersonal")
										.sendKeys("");
								getObject("paginationNextPersonal").click();
								System.out
										.println("As the required employees are not found in first page,hence clicked to next page of personal Tab");
								Thread.sleep(8000L);
								rownum = 0;
							} else {
								System.out
										.println("The employee which you are searching "
												+ "is not available in all the pages"
												+ "of this Personal / Compensation Tab "
												+ "of the Application. Hence the script unfortunately is "
												+ "not able to execute successfully. Please include the said employee"
												+ "in the said Tab of the application and run once again the script");
								closeBrowser();
							}

						}
					}
					rownum++;
				}
			}
		} catch (Throwable t) {
			APP_LOGS.debug(" Check for error in NI Category method");
			System.out.println(t.getStackTrace().toString());
			ErrorUtil.addVerificationFailure(t);
			System.out.println("");
		}

	}

	public void UpdateTaxBasis(String ename, String TCode, String TaxBasis)
			throws Throwable {
		try {
			String firstRowOfTaxBasis = "//div[" + rownum + "]" + "/"
					+ "table/" + "tbody/" + "tr/" + "td[" + taxbasiscolnum
					+ "]" + "/" + "div";
			if (existsElement(firstRowOfTaxBasis)) {
				// Thread.sleep(1000L);
				Actions action2 = new Actions(driver);
				action2.doubleClick(
						driver.findElement(By.xpath(firstRowOfTaxBasis)))
						.build().perform();
				action2.moveToElement(getObject("taxBasisdropdown")).perform();
				Thread.sleep(1000L);
				if (existsElement(OR.getProperty("taxBasisdropdown"))) {
					getObject("taxBasisdropdown").sendKeys(TaxBasis);
					// Thread.sleep(2000L);
				}

				if (existsElement(OR.getProperty("taxCodeSavebutton"))) {
					getObject("taxCodeSavebutton").click();
					System.out.println("Tax basis got saved successfully");
				}
				Thread.sleep(6000L);
			}
		} catch (Throwable t) {
			APP_LOGS.debug("Check the tax basis Method for errors");
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
			ErrorUtil.addVerificationFailure(t);
		}

	}

	public void UpdateAnnualSalary(String EmpName, String annualSalary,
			String PayFrequency) throws Throwable {
		try {
			if (compensationFirsttimeView) {
				compensationFirsttimeView = false;
				if (existsElement(OR.getProperty("CompensationTab"))) {
					getObject("CompensationTab").click();
					Thread.sleep(4000L);
					/*
					 * Calling the following method from the base class since
					 * "Select value is not able to call the value from
					 * OR.Properties page.
					 */
					compensationSelectValue();
				}
			}
			Thread.sleep(1000L);
			WebElement tableheader = driver.findElement(By.xpath(OR
					.getProperty("PersonalAndCompensationHeadingTable")));
			List<WebElement> th = tableheader.findElements(By.tagName("td"));
			for (a = 0; a < th.size(); a++) {
				if ("Employee".equalsIgnoreCase(th.get(a).getText())) {
					empcolnum = a + 1;
					break;
				}
			}

			for (b = 0; b < th.size(); b++) {
				if ("Annual salary".equalsIgnoreCase(th.get(b).getText())) {
					compnAnnualSalColumn = b + 1;
					break;
				}
			}

			for (c = 0; c < th.size(); c++) {
				if ("Payroll frequency".equalsIgnoreCase(th.get(c).getText())) {
					compPayfrequencyColumn = c + 1;
					break;
				}
			}
			WebElement postsTable = driver.findElement(By.xpath(OR
					.getProperty("firstRecordOfTaxCodecoulmnTable")));
			if (existsWebElement(postsTable)) {
				List<WebElement> rows = postsTable.findElements(By.xpath(OR
						.getProperty("firstRecordOfTaxCodecoulmnTableRows")));
				lastRowCount = rows.size();
				java.util.Iterator<WebElement> x = rows.iterator();
				rownum = 1;
				outerbreak: while (x.hasNext()) {
					String firstEmpXpath = "//div[" + rownum
							+ "]/table/tbody/tr/td" + "[" + empcolnum + "]"
							+ "/" + "div/a/span";
					if (existsElementchkFor1mts(firstEmpXpath)) {
						WebElement FirstrowofEmpColumn = driver.findElement(By
								.xpath(firstEmpXpath));
						String AppnEmp = FirstrowofEmpColumn.getText();
						if (AppnEmp != null
								&& AppnEmp.equalsIgnoreCase(EmpName)) {
							// System.out.println("Employee matched");
							Thread.sleep(1000L);
							String firstRowOfAnnualsalary = "//div[" + rownum
									+ "]" + "/" + "table/" + "tbody/" + "tr/"
									+ "td[" + compnAnnualSalColumn + "]" + "/"
									+ "div";
							if (existsElement(firstRowOfAnnualsalary)) {
								Actions action1 = new Actions(driver);
								action1.doubleClick(
										driver.findElement(By
												.xpath(firstRowOfAnnualsalary)))
										.perform();
								WebElement updatesal = driver
										.findElement(By.xpath(OR
												.getProperty("annualSalTextField")));
								action1.moveToElement(updatesal).perform();
								Thread.sleep(1000L);
								updatesal.clear();
								Thread.sleep(1000L);
								updatesal.sendKeys(annualSalary);
								Thread.sleep(1000L);
								if (existsElement(OR
										.getProperty("CompnSavebuton"))) {
									getObject("CompnSavebuton").sendKeys("");
									getObject("CompnSavebuton").click();
									System.out
											.println("The annual salary got saved");
								}
								Thread.sleep(3000L);
							}
							UpdatePayFrequency(EmpName, annualSalary,
									PayFrequency);
							break outerbreak;
						} else if (rownum == lastRowCount && AppnEmp != null
								&& AppnEmp != (EmpName)) {
							System.out
									.println("The row number of the page reached"
											+ rownum
											+ " to 200 and"
											+ " Required Employee not found hence clicking the"
											+ " pagination link so that Employee search continues for next page");
							if (existsElementchkFor1mts(OR
									.getProperty("paginationElementPersonal"))) {
								getObject("paginationNextPersonal")
										.sendKeys("");
								getObject("paginationNextPersonal").click();
								System.out
										.println("As the required employees are not found in first page,hence clicked to next page of personal Tab");
								Thread.sleep(8000L);
								rownum = 0;
							} else {
								System.out
										.println("The employee which you are searching "
												+ "is not available in all the pages"
												+ "of this Personal / Compensation Tab "
												+ "of the Application. Hence the script unfortunately is "
												+ "not able to execute successfully. Please include the said employee"
												+ "in the said Tab of the application and run once again the script");
								closeBrowser();
							}
						}
					}
					rownum++;
				}
			}
		} catch (Throwable t) {
			APP_LOGS.debug("Check the Annual salary Method for errors");
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
			ErrorUtil.addVerificationFailure(t);
		}
	}

	public void UpdatePayFrequency(String empName, String AnnualSalary,
			String PayFrequency) throws Throwable {
		try {
			String firstRowOfPayFrequency = "//div[" + rownum + "]" + "/"
					+ "table/" + "tbody/" + "tr/" + "td["
					+ compPayfrequencyColumn + "]" + "/" + "div";
			if (existsElement(firstRowOfPayFrequency)) {
				Thread.sleep(2000L);
				// String RowOfPayFrequency =
				// "//div["+rownum+"]"+"/"+"table/"+"tbody/"+"tr/"+"td["+"7]"+"/"+"div";
				Actions action2 = new Actions(driver);
				action2.doubleClick(
						driver.findElement(By.xpath(firstRowOfPayFrequency)))
						.perform();
				action2.moveToElement(getObject("payFrequencyDropdown"))
						.perform();
				Thread.sleep(2000L);
				if (existsElement(OR.getProperty("payFrequencyDropdown"))) {
					// Select selectByValue = new
					// Select(driver.findElement(By.xpath(OR.getProperty("payFrequencyDropdown"))));
					// selectByValue.selectByVisibleText(PayFrequency);
					getObject("payFrequencyDropdown").sendKeys("");
					getObject("payFrequencyDropdown").sendKeys(PayFrequency);
					System.out.println("Selected the PayFrequency item as :"
							+ PayFrequency);
					Thread.sleep(2000L);
					if (existsElement(OR.getProperty("payFrequencyUpdate"))) {
						getObject("payFrequencyUpdate").click();
						System.out
								.println("The update button got clicked and Pay frequency Category got saved");
					}
				}
			}

		} catch (Throwable t) {
			APP_LOGS.debug("Check the Pay frequency Method for errors");
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
			ErrorUtil.addVerificationFailure(t);
		}

	}

	public void TaxPayRun_For_FourWeek(String FourWeekly,
			String ExcelInputSheet, String FirstReportNameInApplication,
			String TestResultExcelFilePath) throws Throwable {
		try {/*
			 * 
			 * if (existsElementchkFor1mts(OR
			 * .getProperty("payrollMonthWeeekSubPaginToDisplayAllRecords"))) {
			 * for (int i = 1; i < 3; i++) {
			 * getObject("payrollMonthWeeekSubPaginToDisplayAllRecords")
			 * .sendKeys("");
			 * getObject("payrollMonthWeeekSubPaginToDisplayAllRecords")
			 * .click();
			 * System.out.println("The expandable page got clicked for" + i +
			 * "st time"); Thread.sleep(4000L); }
			 * 
			 * }
			 * 
			 * 
			 * Thread.sleep(4000L); if
			 * (existsElement(OR.getProperty("payRunWeekTable"))) {
			 * System.out.println("Thee table exists");
			 * 
			 * WebElement payRunWeekOneTable = getObject("payRunWeekTable");
			 * 
			 * List<WebElement> rows = payRunWeekOneTable.findElements(By
			 * .xpath(OR.getProperty("WeekOneTablerows")));
			 * java.util.Iterator<WebElement> x = rows.iterator();
			 * System.out.println("total number of week records are :" +
			 * rows.size());
			 * 
			 * rownum = 2; while (x.hasNext()) { WebElement Weekrecord =
			 * driver.findElement(By .xpath("//div[" + "5" + "]/" + "div[" +
			 * "1]/" + "div/" + "div[" + "2]/" + "table/" + "tbody/tr[" +
			 * (rownum) + "]/" + "th/" + "a"));
			 * 
			 * String weekText = Weekrecord.getText();
			 * 
			 * if (weekText != null && weekText.equalsIgnoreCase(WeekName)) {
			 * System.out.println("The week name" + WeekName + " matched");
			 * Weekrecord.sendKeys(""); Thread.sleep(4000L); Weekrecord.click();
			 * break; } else { System.out.println("payRun text " + WeekName +
			 * "did not matched"); rownum++; }
			 * 
			 * }
			 * 
			 * }
			 */

			// Code Added By Swamy

			// Thread.sleep(2000L);
			if (existsElement(OR.getProperty("payRunWeekTable"))) {
				System.out.println("Thee table exists");

				WebElement payRunWeekOneTable = getObject("payRunWeekTable");

				List<WebElement> rows = payRunWeekOneTable.findElements(By
						.xpath(OR.getProperty("WeekOneTablerows")));
				lastRowCount = rows.size();
				System.out
						.println("The total pay run records for the page is equal to : "
								+ lastRowCount);
				java.util.Iterator<WebElement> x = rows.iterator();
				rownum = 2;
				counter = 1;
				while (x.hasNext()) {
					// Thread.sleep(2000L);
					System.out
							.println("Now the count of Rownum is : " + rownum);
					WebElement MonthPayRun_Record = driver.findElement(By
							.xpath("//div[" + "5" + "]/" + "div[" + "1]/"
									+ "div/" + "div[" + "2]/" + "table/"
									+ "tbody/tr[" + (rownum) + "]/" + "th/"
									+ "a"));

					if (existsWebElement(MonthPayRun_Record)) {
						System.out.println("first payroll table record existt");
						String PayRunTextName = MonthPayRun_Record.getText();
						System.out.println("The Month name is :"
								+ PayRunTextName);
						if (PayRunTextName != null
								&& PayRunTextName.equalsIgnoreCase(FourWeekly)) {
							System.out.println("The Month name"
									+ PayRunTextName + " matched");
							MonthPayRun_Record.sendKeys("");
							// Thread.sleep(1000L);
							MonthPayRun_Record.click();
							System.out
									.println("The Payrun record whose Month name is "
											+ FourWeekly
											+ "successfully clicked for processing payroll");

							break;
						}
						System.out.println("The Month name" + PayRunTextName
								+ " is not matched");
						if (counter < 14 && rownum == 6 || rownum > 10
								&& PayRunTextName != null
								&& PayRunTextName != (FourWeekly)) {
							System.out
									.println("The row number of the page reached"
											+ rownum

											+ " Required payrun not found hence clicking the"
											+ " pagination link so that payrun search continues for next page");

							if (existsElementchkFor1mts(OR
									.getProperty("payrollMonthWeeekSubPaginToDisplayAllRecords"))) {
								getObject(
										"payrollMonthWeeekSubPaginToDisplayAllRecords")
										.sendKeys("");
								getObject(
										"payrollMonthWeeekSubPaginToDisplayAllRecords")
										.click();
								System.out
										.println("As the required Payrun is not found in first page,hence clicked to pagination link");
								Thread.sleep(5000L);

							}
						}

						System.out
								.println("Payrun not matched hence incrementing the row number");
						rownum++;
						counter++;
					}
				}
			}

		} catch (Throwable t) {
			System.out.println(t.getMessage());

		}
	}
	
	
	public void dateFormaterMethod(String dateStr)throws Throwable
	{
		try
		{
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

			formattedDate = null;
			if( date != null ) 
			{
				formattedDate = writeFormat.format( date );
			}
			System.out.println("The entered date is  " +formattedDate);		
			Thread.sleep(4000L);
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}
	

	public void submitSickleave()throws Throwable
	{
		try
		{  												
			if(existsElementchkFor1mts(OR.getProperty("submitLeaverqstlocator")))
			{
				getObject("submitLeaverqstlocator").sendKeys("");
				getObject("submitLeaverqstlocator").click();
				System.out.println("The submit leave request button got clicked sucessfully");
				if(existsElementchkFor1mts(OR.getProperty("leaveRequstOkbutton")))
				{			
					getObject("leaveRequstOkbutton").sendKeys("");
					getObject("leaveRequstOkbutton").click();
					System.out.println("");
					System.out.println("The submit leave request ok button also got clicked sucessfully");
				}
			}
			else if(!existsElementchkFor1mts(OR.getProperty("submitLeaverqstlocator")))
			{
				System.out.println("waiting for the submit button to be dispalyed please wait..");
				submitSickleave();
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}
	
	
	public void closePopupWindow()throws Throwable
	{
		try
		{
			Thread.sleep(5000L);
			if(existsElement(OR.getProperty("popupwindowAfterLoginSuccess")))
			{
				String oldWindow = driver.getWindowHandle();
				driver.switchTo().window(driver.getWindowHandle());
				getObject("popupwindowAfterLoginSuccess").click();
				//driver.findElement(By.xpath("//div/a[@id='tryLexDialogX']")).click();
				System.out.println("The Popup window got closed");
				driver.switchTo().window(oldWindow);
			}
			else
			{
				System.out.println("The Popwindow does not exist in this Org");
			}
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage().toString());
			System.out.println(t.getStackTrace().toString());
		}
	}
	
	

}
