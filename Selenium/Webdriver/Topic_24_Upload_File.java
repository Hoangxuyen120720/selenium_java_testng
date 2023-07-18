package Webdriver;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.security.auth.x500.X500Principal;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

import net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.DiscoveryStrategy.Explicit;

public class Topic_24_Upload_File {
	WebDriver driver;
	Random rand = new Random();
	Alert alert;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitwait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAddress = "test" + rand.nextInt(9999) + "@gmail.net";
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
		
		//luôn luôn khởi tạo sau biens driver
		explicitwait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}


	@Test
	public void TC_01_One_File_Per_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		sleepInSecond(2);
		
		// load file leen
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(beach1FilePath);
		sleepInSecond(2);
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(beach2FilePath);
		sleepInSecond(2);
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(beach3FilePath);
		sleepInSecond(2);

		
		
		// Verify file ddc upload thanh coong
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + beach1filename + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + beach2filename + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + beach3filename + "']")).isDisplayed());
		
		// Click upload
		
		List<WebElement> buttonUpload = driver.findElements(By.cssSelector("table button.start"));
		for(WebElement button : buttonUpload) {
			button.click();
			sleepInSecond(2);
			
		}
		
		// Verify upload thanh cong (link)
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + beach1filename + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + beach2filename + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + beach3filename + "']")).isDisplayed());
		
		
		// Verify upload thanh cong (link)
		Assert.assertTrue(isImageLoaded("//img[contains(@src, '" + beach1filename + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src, '" + beach2filename + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src, '" + beach3filename + "')]"));
		
		}

	//@Test
	public void TC_02_Multiple_File_Per_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		sleepInSecond(2);
		
		// load file leen
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(beach1FilePath + "\n" + beach2FilePath + "\n" + beach3FilePath);
		sleepInSecond(2);
		
		// Verify file ddc upload thanh coong
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + beach1FilePath + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + beach2FilePath + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + beach3FilePath + "']")).isDisplayed());
				
		// Click upload
				
		List<WebElement> buttonUpload = driver.findElements(By.cssSelector("table button.start"));
		for(WebElement button : buttonUpload) {
			button.click();
			sleepInSecond(2);
					
				}
				
		// Verify upload thanh cong (link)
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + beach1FilePath + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + beach2FilePath + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + beach3FilePath + "']")).isDisplayed());
				
				
		
		}
	
	
	
	
	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));
		return status;
	}
   
	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
	
	public void switchtoWIndowByID (String otherID) {
    	
    	// Lay het tat ca ID ra
		Set<String> allWindowIDs = driver.getWindowHandles();
		
       // Sau do dung vong lap duyet qua va kiem tra
		
		for (String id : allWindowIDs) {
			if (!id.equals(otherID)) {
				driver.switchTo().window(id);
}		
}
    	
    	
    }
	
    public void closeAllWindowWithoutParent  (String parentID) {
    	
    	// Lay het tat ca ID ra
		Set<String> allWindowIDs = driver.getWindowHandles();
		
       // Sau do dung vong lap duyet qua va kiem tra
		
		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
				sleepInSecond(2);
           }		
      }   
		driver.switchTo().window(parentID);
    }
    
    
    
    public void switchtoWIndowByPAgeTitle  (String expectedPaggeTitle) {
    	
		Set<String> allWindowIDs = driver.getWindowHandles();
	
		for (String id : allWindowIDs) {
			// Switch tung ID truoc
            driver.switchTo().window(id);
            
            // Lay ra title cuar page nay
            String actualPageTitle = driver.getTitle();
            
            if (actualPageTitle.equals(expectedPaggeTitle)) {
            	break;
		}		
     }    	    	
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