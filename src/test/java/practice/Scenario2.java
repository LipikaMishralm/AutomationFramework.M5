package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Scenario2 {

	public static void main(String[] args) throws Throwable {

		 //step-1  launch the browser
		WebDriverManager.edgedriver().setup();
		WebDriver driver=new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//step-2 load the application
		driver.get("http://localhost:8888");
		
		//step-3 Login to the application
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		//step-4 navigate to organisation link
        driver.findElement(By.linkText("Organizations")).click();
        
		//step-5 click on create organization look up icon
        driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
        
		//fill the mandatory field
         driver.findElement(By.name("accountname")).sendKeys("Wipro");
         
		//click on save button
         driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
         
         //validate
          String organizationHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
         if(organizationHeader.contains("Wipro"))
         {
        	 System.out.println("PASS");
         }
         else
         {
        	 System.out.println("FAIL");
         }

         //Logout
         WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
         Actions a=new Actions(driver);
         a.moveToElement(ele).perform();
         Thread.sleep(3000);
         driver.findElement(By.linkText("Sign Out")).click();
         System.out.println("Signed out successfully....");
	}

}
