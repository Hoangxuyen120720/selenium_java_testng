package Webdriver;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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

public class Topic_22_Window_Tab {
	WebDriver driver;
	Random rand = new Random();
	Alert alert;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitwait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAddress = "test" + rand.nextInt(9999) + "@gmail.net";

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


	//@Test
	public void TC_01_Kyna_Iframe() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(2);
		// Window/ tab nos sex cos 2 hamf ddeer laaysra IDcuar window/tab ddos ra
		// 1. nos sex laays ra cai id cuar tab/window maf nos ddang ddungws (active)
		
		
		// Lay ra dc ID cua tab hien tai
		String basicFormID = driver.getWindowHandle();
		System.out.println("Parent Page = " + basicFormID);
		
		// 2. nos sex laays ra taats car id
		//Set<String> allWindows = driver.getWindowHandles(); // set k cho pheps trung, list cho pheps trungf cho pheps null
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(2);
		
		switchtoWIndowByID(basicFormID);
		
		
		
		
		sleepInSecond(2);
		driver.findElement(By.name("q")).sendKeys("automation");
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
		
		// vi sao dung ham nay => lay ra dc ID cua tab Google (child)
		String googleWindowID = driver.getWindowHandle();
		
		switchtoWIndowByID(googleWindowID);
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
		
		
		// Case 1: chir cos duy nhat 2 window hoac 2 tab
		// Case 2: nhieu hon 2 window/ 2 tab
		

		}

	@Test
	public void TC_03_Live_Guru() {
		driver.get("http://live.techpanda.org/index.php/mobile.html");
		sleepInSecond(2);
		
		String parentID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[@title='Sony Xperia']//parent::h2//following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product Sony Xperia has been added to comparison list.");
		
		driver.findElement(By.xpath("//a[@title='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product Samsung Galaxy has been added to comparison list.");
		
		
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		switchtoWIndowByPAgeTitle("Products Comparison List - Magento Commerce");
		
		
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Compare Products']")).isDisplayed());
		
		
		// Close window
		//driver.findElement(By.xpath("//button[@title='Close Window']")).click();
		closeAllWindowWithoutParent(parentID);
		
		
		switchtoWIndowByPAgeTitle("Mobile");
		
		driver.findElement(By.xpath("//a[@title='IPhone']//parent::h2//following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product IPhone has been added to comparison list.");
		
		
		}
	
	   //@Test
		public void TC_02_Title() {
			driver.get("https://automationfc.github.io/basic-form/index.html");
			sleepInSecond(2);
			String parentID = driver.getWindowHandle();
			
			driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
			sleepInSecond(2);
			
			switchtoWIndowByPAgeTitle("Google");
			Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
			
			
			switchtoWIndowByPAgeTitle("Selenium WebDriver");
			Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
			
			
			driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
			sleepInSecond(2);
			
			switchtoWIndowByPAgeTitle("Facebook – log in or sign up");
			Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
			driver.findElement(By.cssSelector("input#email")).sendKeys("aaa@gmail.com");
			driver.findElement(By.cssSelector("input#pass")).sendKeys("12345678");
			
			switchtoWIndowByPAgeTitle("Selenium WebDriver");
			Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
			
			driver.findElement(By.xpath("//a[text()='TIKI']")).click();
			sleepInSecond(2);
			
			switchtoWIndowByPAgeTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
			Assert.assertEquals(driver.getCurrentUrl(), "https://tiki.vn/");
			
			
			closeAllWindowWithoutParent(parentID);
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