package practice;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class AssertionsPractice {

	//HardAssert
	@Test
	public void practice() 
	{
		System.out.println("Step 1");
		System.out.println("Step 2"); 

		Assert.assertEquals(2, 1);
		System.out.println("Step 3");
	}
	
	//SoftAssert
	@Test
	public void practice1()
	{		
		SoftAssert sa=new SoftAssert();
		
		System.out.println("Step 1");
		
		sa.assertEquals(true, false);
		
		System.out.println("Step 2");
		
		sa.assertEquals("B", "A");
		
		System.out.println("Step 3");
		
		sa.assertAll();//failures logged
	}
}
