package Webdriver;

import java.util.concurrent.TimeUnit;
import java.util.function.LongToDoubleFunction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_Excercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Url() {
		driver.get("http://live.techpanda.org/");
		
    // Click vào My Account
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	//	driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']"));
		sleepInSecond(2);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
	
	
	// Click vào Create an Account
		
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		
		
	}

	

	@Test
	public void TC_02_Title() {
        driver.get("http://live.techpanda.org/");
		
    // Click vào My Account
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	//	driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']"));
		sleepInSecond(2);
		Assert.assertEquals(driver.getTitle(), "Customer Login");
	
	
	// Click vào Create an Account
		
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		
		}

	@Test
	public void TC_03_Navigate() {
        driver.get("http://live.techpanda.org/");
		
	// Page Login
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	//	driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']"));
		sleepInSecond(2);
	
	
	// Click vào Create an Account
		
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		sleepInSecond(2);
    
	// BAck lại
		driver.navigate().back();
		sleepInSecond(2);
		Assert.assertEquals(driver.getTitle(), "Customer Login");
	
	// Foward
		driver.navigate().forward();
		sleepInSecond(2);
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		
	
		}
	@Test
	public void TC_04_Page_Source_HTML() {
		driver.get("http://live.techpanda.org/");
			
    // Page Login
				
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
    //	driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']"));
	    sleepInSecond(3);
		
	// Verify page HTML có chứa 1 chuỗi mong mún hay k
	    Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		
	// Click vào Create an Account
		
	 	driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
	 	sleepInSecond(3);
	// Verify page HTML có chứa 1 chuỗi mong mún hay k
	    Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
	    
		}

	
	     public void sleepInSecond(long timeInsecond) {
	     try {
			Thread.sleep(timeInsecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    // 1000 ms = 1s	 
	     } 
	     
	     
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}