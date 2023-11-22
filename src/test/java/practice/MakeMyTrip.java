package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;


public class MakeMyTrip {

	public static void main(String[] args) throws Throwable {

		WebDriverManager.firefoxdriver().setup();
		WebDriver driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://www.makemytrip.com/");
        
        Thread.sleep(1000);
        
        Actions a=new Actions(driver);
        a.moveByOffset(10, 10).click().perform();
        
        Thread.sleep(1000);

        driver.findElement(By.xpath("//span[text()='Departure']")).click();
	}

}
