package genericUtilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * This class consist of generic methods related to java
 * @author LIPIKA
 *
 */
public class JavaUtility {

	/**
	 * This method will return the current system date
	 * in the given format
	 * @return
	 */
	public String getSystemDateInFormat()
	{
		Date d=new Date();
		SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		String currentDate = formatter.format(d);
		return currentDate;
	}
	
	/**
	 * This method will generate a 
	 * random number for every run
	 */
	public int getRandomNumber()
	{
		Random r=new Random();
		int value = r.nextInt(100);
		return value;
	}
}