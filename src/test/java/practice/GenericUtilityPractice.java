package practice;

import java.io.IOException;

import genericUtilities.ExcelFileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.PropertyFileUtility;

public class GenericUtilityPractice {

	public static void main(String[] args) throws IOException {

		//test script
		
		//property file methods
		PropertyFileUtility putil=new PropertyFileUtility();
		String value = putil.readDataFromPropertyFile("username");
		System.out.println(value);
		String value1 = putil.readDataFromPropertyFile("password");
		System.out.println(value1);
		
		//excel file method
		ExcelFileUtility eutil=new ExcelFileUtility();
		String lastName=eutil.readDataFromExcel("Contacts", 1, 2);
		System.out.println(lastName);
		
		//java utility methods
		JavaUtility jutil=new JavaUtility();
		String date = jutil.getSystemDateInFormat();
		System.out.println(date);
		
		int ran = jutil.getRandomNumber();
		System.out.println(ran);
		
		String lastnameWithRandom = lastName+ran;
		System.out.println(lastnameWithRandom);
	}
}
