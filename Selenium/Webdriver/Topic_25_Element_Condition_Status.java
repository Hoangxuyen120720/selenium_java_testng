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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.DiscoveryStrategy.Explicit;

public class Topic_25_Element_Condition_Status {
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
		// dang apply 15s cho vc tim element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
	}

	@Test
	public void TC_01_Visible_Displayed_Visibility() {
		driver.get("https://www.facebook.com/");
		
		// 1. cos tren UI
		// 1. Co trong HTML 
		
		// Wait cho den khi addresstextbox xuat hien
		explicitwait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		
		driver.findElement(By.id("email")).sendKeys("bnhxuyen@gmail.com");
		
		
		}
	
	@Test
	public void TC_02_Invisible_UnDisplayed_InVisibility_I() {
		driver.get("https://www.facebook.com/");
		
		// 1. ko cos tren UI
		// 1. Co trong HTML 
		
		
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		
		
		// cho cho re enter email textbox k hien thi trong 10s
		explicitwait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
		
		
		}
	@Test
	public void TC_02_Invisible_UnDisplayed_InVisibility_II() {
		driver.get("https://www.facebook.com/");
		
		// 1. ko cos tren UI
		// 1. ko Co trong HTML 
		
		// cho cho re enter email textbox k hien thi trong 10s
		
		explicitwait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("firstname")));
		
		
	}
	@Test
	public void TC_03_Presence_I() {
		driver.get("https://www.facebook.com/");
		
		
		// 1. cos tren UI
		// 1. Co trong HTML (bat buoc)
		
		// Wait cho den khi addresstextbox xuat hien
		explicitwait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
		
		
	}
	@Test
	public void TC_03_Presence_II() {
		driver.get("https://www.facebook.com/");
		
		
		// 2. ko cos tren UI
		// 1. Co trong HTML (bat buoc)
		

		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		
		
		
		// cho cho re enter email textbox k hien thi trong 10s
		
		explicitwait.until(ExpectedConditions.presenceOfElementLocated(By.name("reg_email_confirmation__")));
		
		
	}
	@Test
	public void TC_04_Staleness() {
		driver.get("https://www.facebook.com/");
			
		// 1. ko cos tren UI
		// 1. ko Co trong HTML 
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		
		
		//Th 1: cos trong html
		WebElement reEnterEmailAddressTextBox = driver.findElement(By.name("reg_email_confirmation__"));
		
		
		// Thao tac voi element khac lam cho element k hien thi nua
		
		
		
		
		// Close popup di
		driver.findElement(By.cssSelector("img._8idr"));
		explicitwait.until(ExpectedConditions.stalenessOf(reEnterEmailAddressTextBox));
		
		
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