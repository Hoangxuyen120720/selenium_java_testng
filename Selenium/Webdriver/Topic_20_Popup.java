package Webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.DiscoveryStrategy.Explicit;

public class Topic_20_Popup {
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
	public void TC_01_Java_Code_Geeks_Not_In_HTML() {
		driver.get("https://www.javacodegeeks.com/");
		sleepInSecond(30);
		
		
		//List findElements
		
		By firstStepPopup = By.cssSelector("div[data-title='Newsletter Free eBooks']:not([data-page='confirmation'])");
		By secondStepPopup = By.cssSelector("div[data-title='Newsletter Free eBooks']:[data-page='confirmation']");
		
		

		List<WebElement> firstStepPopupElement = driver.findElements(firstStepPopup);
		
		//1. Neeus nos cos hieenj thij thijf nhaapj thoong tin vaof nhaans ok
		//Xuwr lis tieeps step tieeps theo cho ddeen khi naof popup ddong laij
		
		if (firstStepPopupElement.size() > 0 && firstStepPopupElement.get(0).isDisplayed()) {
			
			driver.findElement(By.cssSelector("input[placeholder='Enter your e-mail address']")).sendKeys(emailAddress);
			sleepInSecond(3);
			driver.findElement(By.xpath("//span[text()='OK' or text()='Get the Books']")).click();
			sleepInSecond(5);
			
			Assert.assertTrue(driver.findElement(secondStepPopup).isDisplayed());			
			sleepInSecond(10);
			
			Assert.assertFalse(driver.findElement(firstStepPopup).isDisplayed());	
			Assert.assertFalse(driver.findElement(secondStepPopup).isDisplayed());	
			
					
		}
		
		//2. Neeus nhuw k hieenj thij thif qua step tieeps theo
		
		driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained");
		driver.findElement(By.cssSelector("button#search-submit")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("h1.page-title>span")).getText(), "Agile Testing Explained");
			

		}

	@Test
	public void TC_02_VNK_IN_HTML() {
		driver.get("https://vnk.edu.vn/");
		sleepInSecond(5);
		
		By Popup = By.cssSelector("div#tve_editor");
		

		
		//Luoon luoon cos trong HTMl -> findelement
        if (driver.findElement(Popup).isDisplayed()) {
			
			driver.findElement(By.cssSelector("div.tcb-icon-display")).click();
			sleepInSecond(3);
			
			Assert.assertFalse(driver.findElement(Popup).isDisplayed());			
			
        }
        
		//Step tieeps theo
		driver.findElement(By.cssSelector("button.btn-danger")).click();
		sleepInSecond(3);
		
		
		//verify
		Assert.assertEquals(driver.getCurrentUrl(),"https://vnk.edu.vn/lich-khai-giang/");

			  
		}

	@Test
	public void TC_03_DEHIEU_IN_HTML() {
		driver.get("https://dehieu.vn/");
		sleepInSecond(10);
		
		List<WebElement> Popup = driver.findElements(By.cssSelector("div.popup-content"));
		

		
		//1. cos hien thi popup
       if (Popup.size() > 0 && Popup.get(0).isDisplayed()) {
    	   
    	   //Close popup ddi hoacwj nhap thong tin
    	   driver.findElement(By.cssSelector("button.close")).click();
		   sleepInSecond(3);
    	   
       }
			
		//2. k hien thi popup	
       driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
       sleepInSecond(3);
       //verify
       Assert.assertEquals(driver.getCurrentUrl(),"https://dehieu.vn/khoa-hoc");

		  
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