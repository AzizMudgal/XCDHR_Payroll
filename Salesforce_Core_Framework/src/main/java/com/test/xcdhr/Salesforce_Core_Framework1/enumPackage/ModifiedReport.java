package com.test.xcdhr.Salesforce_Core_Framework1.enumPackage;



public enum ModifiedReport
{
	TaxReport("reportNameLocator"),
	NIReport("reportNameLocatorNI"),
	DirAsEmployee("DirAsEmpReportName"),
	DirAsProRata("ProrataReportnameLocator"),
	CeaseAndRecommence("DirAsCeaseAndRecommenceReport"),
	ReachesPensionAge("reportReachesPensionAgelnk"),
	Deferment("reportDefermentlnk"),
	Deferment201718("reportDefermentlnk201718"),
	AtoD("DirAsReportAtoD"),
	Under21("reportDir_Under21"),
	Under21201718("reportDir_Under21201718"),
	U25Aprentice201718("reportDir_U25Aperentice201718"),
	SMP1stReport("smpfirstReport"),
	SMP2ndReport("smp2ndReport"),
	SMP3dReport("smp3dReport"),
	SAP1stReport("sap1stReportlocator"),
	SAP2ndReport("sap2ndReportlocator"),
	SAP3dReport("sap3dReportlocator"),
	SSP1stReport("ssp1stReportlocator"),
	SSP2ndReport("ssp2ndReportlocator"),
	SSP3dReport("ssp3dReportLocator"),
	SPP1stReport("spp1stReportLocator"),
	SPP2ndReport("spp2ndReportLocator"),
	SPP3dReport("spp3dReportLocator"),
	SPPCase2_1stReport("sppCase2_1stReportLocator"),
	SPPCase2_2ndReport("sppCase2_2ndReportLocator"),
	SPPCase2_3dReport("sppCase2_3dReportLocator"),
	SAPP_1stReport("saap_1stReportLocator"),
	SAPP_2ndReport("saap_2ndReportLocator"),
	SAPP_3dReport("saap_3dReportLocator"),
	ShPP_1stReport("shpp_1stReportLocator"),
	ShPP_2ndReport("shpp_2ndReportLocator"),
	ShPP_3dReport("shpp_3dReportLocator"),
	SSP4thReport("AvgWeeklyEarning2"),
	AutoEnrol_StarterReport("AutoEnrolStarterReportlocator"),
	PayrollRTI_RecognitionReport("PayrollRTI_RecognitionReportLocator"),
	PayrollRTI_RecognitionS2Report("PayrollRTI_RecognitionS2ReportLocator"),
	PayrollRTI_RecognitionS3Report("PayrollRTI_RecognitionS3ReportLocator"),
	PayrollRTI_RecognitionS4Report("PayrollRTI_RecognitionS4ReportLocator"),
	PayrollRTI_RecognitionS5Report("PayrollRTI_RecognitionS5ReportLocator"),
	PayrollRTI_RecognitionS6Report("PayrollRTI_RecognitionS6ReportLocator"),
	PayrollRTI_RecognitionS7Report("PayrollRTI_RecognitionS7ReportLocator"),
	PayrollRTI_RecognitionS5Report_ReJoin("PayrollRTI_RecognitionS5ReportLocator_ReJoin"),
	PayrollRTI_RecognitionS7Report_JuneToMarch("PayrollRTI_RecognitionS7ReportLocator_JuneToMarch"),
	SSPCaseTwo_2ndReport("sspCaseTwo2ndReportlocator"),
	SSPCaseTwo_3dReport("sspCaseTwo3dReportlocator");
	
	
	private String ReportName;
	
	public String getReportName()
	{
		return ReportName;
	}
	
	ModifiedReport(final String ReportName)
	{
		this.ReportName= ReportName;
	}

}



