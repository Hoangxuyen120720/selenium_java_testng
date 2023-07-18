package Webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test
public class Topic_06_Web_Element_Excercise2_p2 {
	WebDriver driver;
	Random rand;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
    String emailAddress, firstName, lastName, password, fullName;
   
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		
		rand = new Random();
		
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	
	    emailAddress = "automation" + rand.nextInt(9999) + "@gmail.com";
	    firstName = "Automation";
	    lastName = "FC";
	    fullName = firstName +" "+ lastName;
	    password = "12345678";
	
	
	
	}


	public void TC_01_Login_With_emty_email_and_password() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(2); 
		
		driver.findElement(By.id("send2")).click();
		sleepInSecond(2); 
		
		
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
		
	}

	public void TC_02_Login_With_invalid_email() {
		
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(2); 
		
		driver.findElement(By.id("email")).sendKeys("12345@");
		driver.findElement(By.id("pass")).sendKeys("123456");
		
		driver.findElement(By.id("send2")).click();
		sleepInSecond(2); 
		
		
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
		
	}

	public void TC_03_password_less_than_6_char() {
		
		sleepInSecond(2); 
		
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("bangocxuyenntn@gmail.com");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("123");
		
		driver.findElement(By.id("send2")).click();
		sleepInSecond(2); 
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
		

	}
	public void TC_04_Login_With_incorrect_email_or_password() {
		
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(2); 
		
		driver.findElement(By.id("email")).sendKeys(emailAddress);
		driver.findElement(By.id("pass")).sendKeys("123456");
		
		driver.findElement(By.id("send2")).click();
		sleepInSecond(2); 
		
		
		Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(), "Invalid login or password.");
		
		
	}
	
	
	public void TC_05_Create_New_Account() {
		
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(2); 
	
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		sleepInSecond(2); 
		
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("Email Address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		
		driver.findElement(By.cssSelector("button[title='Register']")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");
		
		String contactInformationText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
	    System.out.println(contactInformationText);
	    
	    Assert.assertTrue(contactInformationText.contains(fullName));
	    Assert.assertTrue(contactInformationText.contains(emailAddress));
	    

		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
	
	    Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'logo.png')]")).isDisplayed());
	
	}
	
	

	public void TC_06_Login_Valid_info() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSecond(2); 
	
		driver.findElement(By.id("email")).sendKeys(firstName);
		driver.findElement(By.id("pass")).sendKeys(password);
		

		driver.findElement(By.id("send2")).click();
		sleepInSecond(2); 
		
		
		String contactInformationText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
	    System.out.println(contactInformationText);
	    
	    Assert.assertTrue(contactInformationText.contains(fullName));
	    Assert.assertTrue(contactInformationText.contains(emailAddress));
	    
		
		
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