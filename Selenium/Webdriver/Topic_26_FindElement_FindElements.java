package Webdriver;

import java.util.Date;
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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_26_FindElement_FindElements {
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
		explicitwait = new WebDriverWait(driver, 10);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}


	@Test
	public void TC_01_FindElement() {
		driver.get("https://www.facebook.com/");
		
		// 1. Tim thay duy nhat 1 element
		// ko caan phai cho het thoi gian
		//System.out.println("Start time: " + getDateTimeNow());
		
		//driver.findElement(By.cssSelector("input#email"));
		//System.out.println("End time: " + getDateTimeNow());
		
		// 2. Ko Tim thay element nao
		// no sex theo co che tim laij, tim ma k thay va het timeout thi se danh fail
		// nem ra (throw) 1 ngoai le: nosuchelementexception (ko tim thay element)
		
        // System.out.println("Start time: " + getDateTimeNow());
		
		//driver.findElement(By.cssSelector("input[name='reg_email_confirmation__']"));
		//System.out.println("End time: " + getDateTimeNow());
		
		// 3. Tim thay nhieu honw 1 element
		// lay ra element dau tien
		// khi thao tac voi 1 element nao do=> toi uu o phan locator trc
		driver.findElement(By.xpath("//input[@id='email' or @id='pass']")).sendKeys("hi");
		
		
		
		}
	
	@Test
	public void TC_02_FindElements() {
		driver.get("https://www.facebook.com/");
		
		// Ctrl shift O der import
		List<WebElement> elements;
		
		// 1. Tim thay duy nhat 1 element
		// No se k can phair cho het timeout
		elements = driver.findElements(By.cssSelector("input#email"));
		System.out.println("Duy nhat 1 element: "+ elements.size());
		
		
		// 2. Ko Tim thay element nao
		// no sex theo co che tim laij, tim ma k thay va het timeout thi se ko danh fail
		// Tra ve 1 list rong (o element)
		elements = driver.findElements(By.cssSelector("input[name='reg_email_confirmation__']"));
		System.out.println("Duy nhat 1 element: "+ elements.size());
		Assert.assertTrue(elements.isEmpty());		
	  
		// 3. Tim thay nhieu honw 1 element
		// ko can cho het timeout
		// tra ve list tat ca elemen
		
		elements = driver.findElements(By.xpath("//input[@id='email' or @id='pass']"));
		System.out.println("nhieu hon 1 element: "+ elements.size());
		Assert.assertFalse(elements.isEmpty());	
		
		
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
	
	
	
	public String getDateTimeNow() {
		Date date = new Date();
		return  date.toString();
	    
		
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