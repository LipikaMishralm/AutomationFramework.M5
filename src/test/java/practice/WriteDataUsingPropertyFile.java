package practice;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class WriteDataUsingPropertyFile {

	public static void main(String[] args) throws IOException {

		Properties p=new Properties();
        p.setProperty("username", "admin");
        p.setProperty("password", "admin");
        FileOutputStream fos=new FileOutputStream(".\\src\\test\\resources\\WriteData.properties");
        p.store(fos, "VTiger application credentials");
        System.out.println("File created successfully...");
	}

}
