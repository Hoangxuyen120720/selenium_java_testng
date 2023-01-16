package Webdriver;




import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_16_BAITAP {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
    String OsName = System.getProperty("os.name");
	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}



	@Test
	public void TC_01_errormessage(){
		driver.get("http://live.techpanda.org/index.php/mobile.html");
		
		//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']/button
	
		
		driver.get("https://subscription.packtpub.com/search?query=&_ga=2.216240854.1351797786.1672929876-1103561794.1672929876");
		//div[text()='JavaScript from Beginner to Professional']/ancestor::a/following-sibling::a//div/div[1]
		
	}
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}