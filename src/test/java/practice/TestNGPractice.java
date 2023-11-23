package practice;

import org.testng.annotations.Test;


public class TestNGPractice {

	@Test(enabled = true)
	public void createCustomer() {
		//Assert.fail();//this will fail the test script
		System.out.println("create");
	}
	
	@Test(invocationCount = 2,dependsOnMethods = "createCustomer")
	public void modifyCustomer() {
		System.out.println("modify");
	}
	//in negative first -3 will run then -1
	//in positive first 1 will run then 2,3,4
	@Test(priority = -3,invocationCount = 1)
	public void deleteCustomer() {
		System.out.println("delete");
	}
}
