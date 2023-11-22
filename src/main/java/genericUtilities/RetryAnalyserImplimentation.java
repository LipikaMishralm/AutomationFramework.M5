package genericUtilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * This is a implimentation class for IRetryAnalyser
 * @author LIPIKA
 *
 */

public class RetryAnalyserImplimentation implements IRetryAnalyzer{

	int count = 0;
	int retryCount = 3;//manual analysis
	
	public boolean retry(ITestResult result) 
	{

		//0<3 , 1<3 , 2<3 , 3<3(false)it will go to line no 27
		while(count<retryCount)
		{
			count++;//1 , 2 , 3 
			return true;//retry--retry---retry
		}
		
		return false;//stop the execution / retrying
	
	}

}
