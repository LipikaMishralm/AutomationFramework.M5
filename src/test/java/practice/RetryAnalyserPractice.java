package practice;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RetryAnalyserPractice {

	@Test(retryAnalyzer = genericUtilities.RetryAnalyserImplimentation.class)
	public void sample()
	{
		Assert.fail();
		System.out.println("Hiiiiii.........");
	}
	
	@Test
	public void sample1()
	{
		System.out.println("Hellooooo.........");
	}
	
	
}
