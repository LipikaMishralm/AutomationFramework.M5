package genericUtilities;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * This class provide implimentation to ITestListener interface to testNG
 * @author LIPIKA
 *
 */
public class ListenersImplimentation implements ITestListener{

	ExtentReports report;
	ExtentTest test;
	
	public void onTestStart(ITestResult result) 
	{
		String methodName = result.getMethod().getMethodName();//name of the test annotation method
		System.out.println(methodName+" --- Test execution started --- ");
	    
		//@Test execution started
		test = report.createTest(methodName);
		
		
	}

	public void onTestSuccess(ITestResult result) 
	{
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName+" --- Test PASS --- ");

		test.log(Status.PASS, methodName+" --- Test PASS --- ");
	}

	public void onTestFailure(ITestResult result) 
	{
		String methodName = result.getMethod().getMethodName();
		System.out.println(result.getThrowable());
		System.out.println(methodName+" --- Test FAIL --- ");	
		
		test.log(Status.FAIL, methodName+" --- Test FAIL --- ");
		test.log(Status.INFO, result.getThrowable());
		
		//ScreenShot
		WebDriverUtility wutil=new WebDriverUtility();
		JavaUtility jutil=new JavaUtility();
		
		String screenShotName= methodName+jutil.getSystemDateInFormat();
		
		try 
		{
			String path = wutil.captureScreenShot(BaseClass.sdriver,screenShotName );
		
		  test.addScreenCaptureFromPath(path);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) 
	{
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName+" --- Test SKIP --- ");		
		System.out.println(result.getThrowable());

		test.log(Status.SKIP, methodName+" --- Test SKIP --- ");
		test.log(Status.INFO, result.getThrowable());
	
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{

	
	}

	public void onTestFailedWithTimeout(ITestResult result) 
	{

		
	}

	public void onStart(ITestContext context) 
	{
		
		System.out.println("---- Suite execution started ----");
	
	    //Extent report configuration
		
		ExtentSparkReporter htmlreport=new ExtentSparkReporter(".\\ExtentReports\\Report-"+new JavaUtility().getSystemDateInFormat()+".html");
		htmlreport.config().setDocumentTitle("Execution Report");
		htmlreport.config().setTheme(Theme.DARK);
		htmlreport.config().setReportName("QCO-SOEAJD-M5-Execution Report");
		
		report=new ExtentReports();
		report.attachReporter(htmlreport);
		report.setSystemInfo("Base Browser", "Edge");
		report.setSystemInfo("Base Platform", "Windows Family");
		report.setSystemInfo("Base Env", "Testing");
		report.setSystemInfo("Base URL", "http://localhost:8888");
		report.setSystemInfo("Report Name", "Lipika");
		
	
	}

	public void onFinish(ITestContext context) 
	{
 
		System.out.println("--- Suite execution finished ----");
	
	    //generate the report
		report.flush();
	
	}
	
}
