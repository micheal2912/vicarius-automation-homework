package listeners;

import java.io.IOException;

import base.SeleniumBaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import reporting.ExtentReporterNG;

public class TestNGListener extends SeleniumBaseTest implements ITestListener, ISuiteListener, IInvokedMethodListener {

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		//Screenshot
		try {
			extentTestNode.get().addScreenCaptureFromPath(getScreenShotPath(result.getTestContext().getName(),driversPool.get()), result.getTestContext().getName());
			extentTestNode.get().fail(result.getThrowable());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	}

	@Override
	public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {

	}

	@Override
	public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {

	}

	@Override
	public void onStart(ISuite iSuite) {

	}

	@Override
	public void onFinish(ISuite iSuite) {

	}
}
