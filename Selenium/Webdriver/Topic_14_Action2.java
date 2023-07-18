package Webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.DiscoveryStrategy.Explicit;

public class Topic_14_Action2 {
	WebDriver driver;
	Actions action;
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
		
		action = new Actions(driver);
		//luôn luôn khởi tạo sau biens driver
		explicitwait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_Click_And_Hold_Block() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
	
		
		List<WebElement> ListNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		//Đang chứa 12 số
		
		// 1. click vào số 1=>  2. vẫn giữ chuột
		action.clickAndHold(ListNumber.get(0))
		// 3. di chuột tới target
		.moveToElement(ListNumber.get(7))
		// 4. nhả chuột trái
		.release()
		// Ececute
		.perform();
		sleepInSecond(5);
		

		List<WebElement> ListSelectedNumber = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
	
		Assert.assertEquals(ListSelectedNumber.size(),8);
		
		
		
	    }
	@Test
	public void TC_02_Click_And_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		
		List<WebElement> ListNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
		//Đang chứa 12 số
		
		// 1.nhấn ctrl xuống
		// chạy cho cả mac và win
		Keys key = null;
		if (osName.contains("Window")) {
			key = Keys.CONTROL;
		} else {
			key = Keys.COMMAND;
			
		}
		action.keyDown(Keys.CONTROL).perform();
		// 2. click chọn random
		action.click(ListNumber.get(0))
		.click(ListNumber.get(3))
		.click(ListNumber.get(5))
		.click(ListNumber.get(7)).perform();
	
		// 3. nhả ctrl
		action.keyUp(Keys.CONTROL).perform();
		// Ececute
	
		sleepInSecond(5);
		

		List<WebElement> ListSelectedNumber = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
	
		Assert.assertEquals(ListSelectedNumber.size(),4);
		
		
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