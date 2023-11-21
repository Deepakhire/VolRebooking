package com.lh.reportsfreemaker;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.genz.xray.ObjTestCoverage;
import com.genz.xray.ObjTestExecution;
import com.lh.runner.JunitRunner;

/**
 * Entry class for the report generator
 */
public class ReportBuilder {

	public static void generateReport() throws Exception {

		TimeUnit.SECONDS.sleep(5);

		Date currentDate = new Date();
//		te.setExecutionDate(currentDate.toString());
		JunitRunner.folderNameReport = "LH_" + currentDate.toString().replace(":", "_").replace(" ", "_");

		ReportGenerator rg = new ReportGenerator();

		rg.generateReport(JunitRunner.PATH_TO_CUCUMBER_REPORT, JunitRunner.folderNameReport);

	}
}
