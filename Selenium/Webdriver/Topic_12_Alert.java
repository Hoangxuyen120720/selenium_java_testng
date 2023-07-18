package Webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.DiscoveryStrategy.Explicit;

public class Topic_12_Alert {
	WebDriver driver;
	Alert alert;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitwait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) 
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		
		//luôn luôn khởi tạo sau biens driver
		explicitwait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
	
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSecond(3);
		
		
		//Những cái cần phải switch qua: alert, frame/iframe, window
		//1. có thể switch qua và tương tác lun
		// alert = driver.switchTo().alert();
		
		//2. cần wait trc, khi nào xuất hiện mới switch qua
		   alert =  explicitwait.until(ExpectedConditions.alertIsPresent());
		// Accept
		   alert.accept();
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
	}

	@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		sleepInSecond(3);
		
		
		 alert =  explicitwait.until(ExpectedConditions.alertIsPresent());
		
		 Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		 // cancel
		 alert.dismiss();
		sleepInSecond(3);
		 Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
			
		}

	@Test
	public void TC_03_Promt_Alert() {
	    driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		sleepInSecond(3);
		
		
		 alert =  explicitwait.until(ExpectedConditions.alertIsPresent());
		
		 Assert.assertEquals(alert.getText(), "I am a JS prompt");
		 
		 String keyword = "hii";
		 // nhập
		 alert.sendKeys(keyword);
		 sleepInSecond(2);
		 
		 alert.accept();
		 sleepInSecond(2);  
		 Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + keyword);
			  
		}

	@Test
	public void TC_04_Authentication_Alert() {
		// ko dùng thư viện để xử lí đc
		//Selenium trick3
		// http://username:password@domain
		
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.example>p")).getText(), "Congratulations! You must have the proper credentials.");
			
		}
	
	@Test
	public void TC_05_Authentication_Alert2() {
		driver.get("https://the-internet.herokuapp.com/");
		String basicAuthenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		
		//http://the-internet.herokuapp.com/basic_auth
		driver.get(getUrlByUserPass(basicAuthenLink, "admin", "admin"));
		Assert.assertEquals(driver.findElement(By.cssSelector("div.example>p")).getText(), "Congratulations! You must have the proper credentials.");
			
		}
	
	@Test
	public void TC_06_Authentication_Alert_AutoIT() {
		driver.get("https://the-internet.herokuapp.com/");
		String basicAuthenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		
		//http://the-internet.herokuapp.com/basic_auth
		driver.get(getUrlByUserPass(basicAuthenLink, "admin", "admin"));
		Assert.assertEquals(driver.findElement(By.cssSelector("div.example>p")).getText(), "Congratulations! You must have the proper credentials.");
			
		}
	
	
	
	
	public String getUrlByUserPass (String url, String username, String password) {

		String[] newurl = url.split("//");
		//http:
		//the-internet.herokuapp.com/basic_auth
		
		url = newurl[0] + "//" + "admin" + ":" + "admin" + "@" + newurl[1];
		
		return url;
				
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