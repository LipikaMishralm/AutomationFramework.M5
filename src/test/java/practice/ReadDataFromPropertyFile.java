package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadDataFromPropertyFile {

	public static void main(String[] args) throws IOException{
		
		//Step 1: Open the document in java readable format
         FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\CommomData.properties");
          
		//Step 2: Create an object of properties class from java.util package
         Properties p=new Properties();
         
         //Step 3: Load the input stream into properties
         p.load(fis);
         
         //Step 4: Provide the keys to read the values
         String value = p.getProperty("url");
         System.out.println(value);
         
         String value1 = p.getProperty("username");
         System.out.println(value1);
	}	 
}