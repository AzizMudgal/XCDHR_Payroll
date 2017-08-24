package com.test.xcdhr.Salesforce_Core_Framework1.Salesforce_Util;


public class Test_Util
{
	// Collective Test Suit .The following function returns true if it finds the runnable mode is set to 'yes'.

	public static boolean isSuiteRunnable(Xls_Reader xls, String suite)
	{
		boolean IsExecutable = false;
		for(int i = 2; i <= xls.getRowCount("FullSuite"); i++)
		{
			String suitname = xls.getCellData("FullSuite", "TCID", i);
			String runmde = xls.getCellData("FullSuite", "Runmode", i);
			if(suitname.equals(suite))
			{
				if (runmde.equalsIgnoreCase("Y"))
				{
					return IsExecutable = true;
				}
				else
				{
					return IsExecutable = false;
				}

			}

		}
		xls = null; // to release memory.

		return IsExecutable;

	}


	// Individual Test suite. The following function returns true  if "y" else false.
	public static boolean IsTestcaseRunMode(Xls_Reader sss, String LeaveSuite)
	{

		boolean Isexecutable = false;

		for(int i = 2; i <= sss.getRowCount("first"); i++)
		{

			//System.out.println(sss.getRowCount("first"));

			String tstcs = sss.getCellData("first", "TC", i);

			String rnmd = sss.getCellData("first", "Runmode", i);

			//	System.out.println("The testcase name is: "+ tstcs + "---"+"Runmode is: "+ rnmd);


			if(tstcs.equalsIgnoreCase(LeaveSuite))
			{
				if(rnmd.equalsIgnoreCase("Y"))
				{
					Isexecutable = true;

				}else{

					Isexecutable = false;
				}
			}

		}

		sss = null;
		return Isexecutable;


	}

	// return the test data from a test in a 2 dim array
	public static Object[][] getData(Xls_Reader xls , String testCaseName){
		// if the sheet is not present
		if(! xls.isSheetExist(testCaseName)){
			xls=null;
			return new Object[1][0];
		}


		int rows=xls.getRowCount(testCaseName);
		int cols=xls.getColumnCount(testCaseName);
		//System.out.println("Rows are -- "+ rows);
		//System.out.println("Cols are -- "+ cols);

		Object[][] data =new Object[rows-1][cols-3];
		for(int rowNum=2;rowNum<=rows;rowNum++){
			for(int colNum=0;colNum<cols-3;colNum++){
				//System.out.print(xls.getCellData(testCaseName, colNum, rowNum) + " -- ");
				data[rowNum-2][colNum] = xls.getCellData(testCaseName, colNum, rowNum);
			}
			//System.out.println();
		}
		return data;

	}

	// This function updates the test result in to each test case after that test case executed.
	public static void ReportDataSetResult(Xls_Reader xls, String TestCaseName, int Rownum, String Result){


		xls.setCellData(TestCaseName, "Results", Rownum, Result);
		System.out.println("successfully updated the result123");

	}

	// returns true if runmode of the test is equal to Y
	public static boolean isTestCaseRunnable(Xls_Reader xls, String testCaseName){
		boolean isExecutable=false;
		for(int i=2; i<= xls.getRowCount("Test Cases") ; i++){
			//String tcid=xls.getCellData("Test Cases", "TCID", i);
			//String runmode=xls.getCellData("Test Cases", "Runmode", i);
			//System.out.println(tcid +" -- "+ runmode);


			if(xls.getCellData("Test Cases", "TCID", i).equalsIgnoreCase(testCaseName)){
				if(xls.getCellData("Test Cases", "Runmode", i).equalsIgnoreCase("Y")){
					isExecutable= true;
				}else{
					isExecutable= false;
				}
			}
		}

		return isExecutable;

	}




	//Checks runmode for dataset.

	public static String[] getDataSetRunmodes(Xls_Reader xlsFile, String SheetName){
		String[] runmodes= null;
		if(!xlsFile.isSheetExist(SheetName)){
			xlsFile=null;
			SheetName=null;
			runmodes=new String[1];
			runmodes[0]="Y";
			xlsFile=null;
			SheetName=null;
			return runmodes;
		}
		runmodes = new String[xlsFile.getRowCount(SheetName)-1];
		for(int i=2; i<=runmodes.length+1;i++){
			runmodes[i-2]=xlsFile.getCellData(SheetName,"Runmode",i);
		}
		xlsFile=null;
		SheetName=null;
		return runmodes;
	}




	//following function is usefull to get the Row number of the Testcase

	public static int GetRowNum(Xls_Reader xsl, String TestCaseId){


		for(int i = 2; i <= xsl.getRowCount("first"); i++){

			String tcid = xsl.getCellData("first", "TC", i);

			if(tcid.equals(TestCaseId)){

				xsl=null;
				return i;

			}


		}



		return -1;

	}


}
