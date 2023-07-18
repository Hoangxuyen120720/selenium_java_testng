package Webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_30_ExplicitWait2 {
	WebDriver driver;
	Select select;
	Random rand = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName, lastName, emailAddress, companyName, password, day, month, year;
	String countryName, provinceName, cityName, addressName, postalCode, phoneNumber;
	WebDriverWait explicitWait;
	String beach1filename = "beach1.jfif";
	String beach2filename = "beach2.jfif";
	String beach3filename = "beach3.jfif";
	
	
	String beach1FilePath = projectPath + "\\uploadfile\\" + beach1filename;
	String beach2FilePath = projectPath + "\\uploadfile\\" + beach2filename;
	String beach3FilePath = projectPath + "\\uploadfile\\" + beach3filename;


	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows"))
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		
	
		
	}

	//@Test
	public void TC_01_Not_Enough_Time() {


		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait = new WebDriverWait(driver, 3);
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		

		String helloText = driver.findElement(By.cssSelector("div#finish>h4")).getText();
		
		
		Assert.assertEquals(helloText, "Hello World!");
		
	}
	//@Test
	public void TC_02_Enough_Time() {
		
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait = new WebDriverWait(driver, 5);
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		
		
		String helloText = driver.findElement(By.cssSelector("div#finish>h4")).getText();
		
		
		Assert.assertEquals(helloText, "Hello World!");
		
	}
	//@Test
	public void TC_03_More_Time() {
		
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait = new WebDriverWait(driver, 50);
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
			
		String helloText = driver.findElement(By.cssSelector("div#finish>h4")).getText();
		
		
		Assert.assertEquals(helloText, "Hello World!");
		
	}
	
	//@Test
	public void TC_04_Telerik() {
	    driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
					
		explicitWait = new WebDriverWait(driver, 10);
		
		
		// Wait cho day picker visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#ctl00_ContentPlaceholder1_Panel1")));
			
		// Wait cho locator chua text
		Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div#ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_Label1Panel"), "No Selected Dates to display.")));
			
		// Wait cho ngay click dc
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='12']/parent::td"))).click();
		
		// Wait cho loading icon bien mat
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id$='RadCalendar1']>div.raDiv")));
		
		
		
		// Wait vaf verify cho locator chua text
		Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"), "Friday, May 12, 2023")));
		}
		
	@Test
	public void TC_05_upload() {
		driver.get("https://gofile.io/welcome");
		
		explicitWait = new WebDriverWait(driver, 10);
		
		
		// Wait cho tat ca loading ikon bien mat
		Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border")))));
		
		// click upload file
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Upload Files']"))).click();
		
		// Wait cho tat ca loading ikon bien mat
		Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border")))));
				
		
		
	    // upload 3 file len
	    driver.findElement(By.cssSelector("input#filesUploadInput")).sendKeys(beach1FilePath + "\n" + beach2FilePath + "\n" + beach3FilePath);
	
	    // Wait cho tat ca loading bar bien mat
	    explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress"))));
	    
	    
	    // Wait for text
	    Assert.assertTrue( explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div.mainUploadSuccess div.border-success.text-white"), "Your files have been successfully uploaded")));
	    
	    
	    driver.get(driver.findElement(By.cssSelector("div.mainUploadSuccessLink a.ajaxLink")).getAttribute("href"));
	    
	    
	    // Wait cho tat ca loading ikon bien mat
	    Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border")))));
	 		
	    
	    Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='contentName' and text()='beach1.jfif']"))).isDisplayed());
	    Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='contentName' and text()='beach2.jfif']"))).isDisplayed());
	    Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='contentName' and text()='beach3.jfif']"))).isDisplayed());
	    
	    
	  
		}
	

	
	
	
	
	
	
	

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}