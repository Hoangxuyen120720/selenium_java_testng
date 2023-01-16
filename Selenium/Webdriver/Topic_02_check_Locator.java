package Webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_02_check_Locator {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
    String OsName = System.getProperty("os.name");
	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		// mở trang register
		driver.get("https://demo.nopcommerce.com/register");
	}

	
	 // tên thẻ: tag name
	//attribute
	//attribute value
	

	@Test
	public void TC_01_ID(){
		
	    driver.findElement(By.id("FirstName")).sendKeys("Selenium");
		
	}
 
	@Test
	public void TC_02_Class(){
		driver.get("https://demo.nopcommerce.com/search");
	    driver.findElement(By.className("search-text")).sendKeys("search");
		
	}

	@Test
	public void TC_03_Name() {
		//click vào advanced search check box
		
		driver.findElement(By.name("advs")).click();
	}
		

	@Test
	public void TC_04_Tagname() {
		//tìm ra bao nhiêu thẻ input trên màn hình hiện tại 
		System.out.println(driver.findElement(By.tagName("input")).getSize());
	}
	
	
	@Test
	public void TC_05_Linktext() {
		//tuyệt đối
		driver.findElement(By.linkText("Addresses")).click();	
	}
	

	@Test
	public void TC_06_PartialLinktext() {
		//tương đối
		driver.findElement(By.partialLinkText("vendor account")).click();	
	}
	
	
	
	@Test
	public void TC_07_Css() {
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Hoang");	
		driver.findElement(By.cssSelector("input[id='LastName']")).sendKeys("Xuyen");	
		driver.findElement(By.cssSelector("input[name='Email']")).sendKeys("bangocxuyenntn@gmail.com");	
	}	
	
	@Test
	public void TC_08_Xpath() {
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.xpath("//input[@data-val-required='First name is required.']")).sendKeys("Hoang");	
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Xuyen");	
		driver.findElement(By.xpath("//label[text()='Email:']/following-sibling::input")).sendKeys("bangocxuyenntn@gmail.com");	
	}
	
	
	
	
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}
