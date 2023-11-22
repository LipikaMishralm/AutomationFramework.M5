package practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderPractice {

	@Test(dataProvider = "getData")
	public void readData(String name,String model,int qnty,int price)//this is one test script
	{
		System.out.println(name+"----"+model+"----"+qnty+"----"+price);
	}
	
	@DataProvider
	public Object[][] getData()//return type is object bcoz it behaves like excel sheet row and column
	{                           //row column
		Object[][] data=new Object[3][4];//3 , 4 meaning 3 diff data sets with 4 details each
		
		data[0][0]="Samsung";
		data[0][1]="A80";
		data[0][2]=12;
		data[0][3]=12000;
		
		data[1][0]="Iphone";
		data[1][1]="S14";
		data[1][2]=20;
		data[1][3]=15000;

		data[2][0]="Vivo";
		data[2][1]="V21";
		data[2][2]=15;
		data[2][3]=10000;
				
		return data;
		
	}
}
