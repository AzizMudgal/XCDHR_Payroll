package com.test.xcdhr.Salesforce_Core_Framework1.hrms_payroll.NI_weeklyCatD;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;






import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util.Test_Util;

 
public class CsvToExcel extends TestSuiteBase{
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

	
@ Test
	public static void ConverstionToExcel() throws IOException
	{ 
		ArrayList arList=null;
		ArrayList al=null;
		//////////////
		File f = new File("F:\\Automation XCD\\Excel Compare software tool\\ComparisionResult");
		if(f.isDirectory())
		{
			File[] file= f.listFiles();
			for(File f1 :file)
			{
				if(f1.getName().endsWith(".csv"))
				{
					System.out.println(f1.getName());
					// String foundCSVFile=f1.getName();
					String thisLine; 
					int count=0; 
					FileInputStream fis = new FileInputStream(f+"\\"+f1.getName());
					DataInputStream myInput = new DataInputStream(fis);
					int i=0;
					arList = new ArrayList();
					while ((thisLine = myInput.readLine()) != null)
					{
						al = new ArrayList();
						String strar[] = thisLine.split(",");
						for(int j=0;j<strar.length;j++)
						{
							al.add(strar[j]);
						}
						arList.add(al);
						System.out.println();
						i++;
					} 

					try
					{
						XSSFWorkbook hwb = new XSSFWorkbook();
						XSSFSheet sheet = hwb.createSheet("new sheet");
						for(int k=0;k<arList.size();k++)
						{
							ArrayList ardata = (ArrayList)arList.get(k);
							Row row = sheet.createRow((short) 0+k);
							for(int p=0;p<ardata.size();p++)
							{
								Cell cell = row.createCell((short) p);
								String data = ardata.get(p).toString();
								if(data.startsWith("=")){
									cell.setCellType(Cell.CELL_TYPE_STRING);
									data=data.replaceAll("\"", "");
									data=data.replaceAll("=", "");
									cell.setCellValue(data);
								}else if(data.startsWith("\"")){
									data=data.replaceAll("\"", "");
									cell.setCellType(Cell.CELL_TYPE_STRING);
									cell.setCellValue(data);
								}else{
									data=data.replaceAll("\"", "");
									cell.setCellType(Cell.CELL_TYPE_NUMERIC);
									cell.setCellValue(data);
								}
								
							}
							System.out.println();
						} 
						
						//
						MakeDirectory();
						
						//give the correct folder name
						FileOutputStream fileOut = new FileOutputStream("F:\\Automation NI Reports\\NICatEReport\\source.xlsx");
						hwb.write(fileOut);
						System.out.println("Your excel file has been generated");
					
						fileOut.close();
						myInput.close();
						
						//f1.deleteOnExit();
						
						
						boolean csvdelete = false;
						csvdelete = f1.delete();
						if(csvdelete){
							System.out.println("After conversion of excel file the CSV File got deleted");

						}
						else
						{
							System.out.println("could not delete the csv file");
						}
						
						
					} 
					catch ( Exception ex ) 
					{
						ex.printStackTrace();
					} //main method ends
				}
				else
				{
					System.out.println("No file of type 'csv' found in the directory");
				}

			}
		}
	}
	

	public static void MakeDirectory(){
		
		 boolean success = false;
			// give the correct folder name
			String dir = "F:\\Automation NI Reports\\NICatEReport";
			// Creating new directory in Java, if it doesn't exists
			File directory = new File(dir);
			if (directory.exists())
			{
				System.out.println("Directory already exists ...");
			}
				else
				{
					System.out.println("Directory not exists, creating now");
					success = directory.mkdir();
					if (success)
					{ 
						System.out.printf("Successfully created new directory : %s%n", dir);
					}
						else
						{ 
							System.out.printf("Failed to create new directory: %s%n", dir);
						} 
				
				}
		//
	}


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

