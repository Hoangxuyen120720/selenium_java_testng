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

public class Topic_17_Popup {
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


	//@Test
	public void TC_01_Fixed_IN_Dom() {
		driver.get("https://ngoaingu24h.vn/");
		sleepInSecond(3);
		
		By loginPopup = By.cssSelector("div[id='modal-login-v1'][style]>div.modal-dialog");
		

		
		//Click vaof button login
		driver.findElement(By.cssSelector("button.login_")).click();
		sleepInSecond(3);
		
		
		//verify popup iss displayed
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
				
		driver.findElement(By.xpath("//div[@class='modal fade in']//div[@class='modal-content']//following-sibling::div[@class='modal-body']//input[@id='account-input']")).sendKeys("automationfc");
		driver.findElement(By.xpath("//div[@class='modal fade in']//div[@class='modal-content']//following-sibling::div[@class='modal-body']//input[@id='password-input']")).sendKeys("automationfc");
		driver.findElement(By.xpath("//div[@class='modal fade in']//div[@class='modal-content']//following-sibling::div[@class='modal-body']//button[@class='btn-v1 btn-login-v1 buttonLoading']")).click();
		sleepInSecond(3);
		
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='modal fade in']//div[@class='modal-content']//following-sibling::div[@class='modal-body']//div[@class='row error-login-panel']")).getText(), "Tài khoản không tồn tại!");
		
		//close popup
		driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.close")).click();
		sleepInSecond(3);
		
		
		//verify popup iss undisplayed
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());

		}

	//@Test
	public void TC_02_Fixed_IN_HTML_Kyna() {
		driver.get("https://skills.kynaenglish.vn/");
		sleepInSecond(3);
		
		By loginPopup = By.cssSelector("div.k-popup-account-mb div.modal-content");
		

		
		//Click vaof button login
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(3);
		
		
		//verify popup iss displayed
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
				
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("automationfc@gmail.com");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		
		sleepInSecond(3);
		
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
		
		//close popup
		driver.findElement(By.cssSelector("button.k-popup-account-close")).click();
		sleepInSecond(3);
		
		
		//verify popup iss undisplayed
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());

			  
		}

	//@Test
	public void TC_03_Fixed_NOT_IN_HTML_Tiki() {
		driver.get("https://tiki.vn/");
		sleepInSecond(3);
		
		By loginPopup = By.cssSelector("div.ReactModal__Content");
		

		
		//Click vaof button login
		driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
		sleepInSecond(3);
		
		
		//verify popup iss displayed
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		driver.findElement(By.cssSelector("p.login-with-email")).click();
		sleepInSecond(1);
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		sleepInSecond(1);
				
		
		
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='email']/parent::div/following-sibling::span")).getText(), "Email không được để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@type='password']/parent::div/following-sibling::span")).getText(), "Mật khẩu không được để trống");
		
		//close popup
		driver.findElement(By.cssSelector("img.close-img")).click();
		sleepInSecond(3);
		
		
		//verify popup iss undisplayed
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
		//chowf toiws heets timeout moiws ngungwf
		

			  
		}
	
	@Test
	public void TC_04_Fixed_NOT_IN_HTML_FB() {
		driver.get("https://www.facebook.com/");
		sleepInSecond(3);
		
		By loginPopup = By.cssSelector("//div[text()='Sign Up']/parent::div/parent::div");
		

		
		//Click vaof button login
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		sleepInSecond(3);
		
		
		//verify popup iss displayed
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
	
		
		//close popup
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/parent::div//preceding-sibling::img")).click();
		sleepInSecond(3);
		
		
		//verify popup iss undisplayed
		Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
		//chowf toiws heets timeout moiws ngungwf
		
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