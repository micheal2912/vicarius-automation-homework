package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

import static base.SeleniumBaseTest.OUTPUT_PATH;
import static base.SeleniumBaseTest.USER_DIR;

public class ExtentReporterNG {

	static ExtentReports extent;

	public static ExtentReports getReportObject() {

		String path = USER_DIR + OUTPUT_PATH + "html" + File.separator + "extent_reports_index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Micheal Shamshoum");
		return extent;

	}
}
