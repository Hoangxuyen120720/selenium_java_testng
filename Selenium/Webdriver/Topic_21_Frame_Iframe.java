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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.DiscoveryStrategy.Explicit;

public class Topic_21_Frame_Iframe {
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


	@Test
	public void TC_01_Kyna_Iframe() {
		driver.get("https://skills.kynaenglish.vn/");
		sleepInSecond(2);
		
		//Verify Facebook iframe hieenr thij
		//Fb iframe vaanx laf mootj element cuar trang kyna
		Assert.assertTrue(driver.findElement(By.xpath("//iframe[contains(@src,'kyna.vn')]")).isDisplayed());	
		
		// Caanf pahir switch vaof dungs cais ther iframe chuaw element ddos
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'kyna.vn')]")));
		
		String facebookLike = driver.findElement(By.xpath("//a[text()='Kyna.vn']//parent::div//following-sibling::div")).getText();
		System.out.println(facebookLike);
		
		
		Assert.assertEquals(facebookLike,"165K likes");
		
		// Caanf switch veef mainpage
		driver.switchTo().defaultContent();
		
		// Tuwf mainpage switch qua iframe chat
		
		driver.switchTo().frame("cs_chat_iframe");
		
		// Click vaof chat ddeer show chat sp
		driver.findElement(By.cssSelector("div.button_bar")).click();
		
		
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("aaa");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("08975632123");
		
		new Select(driver.findElement(By.id("serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.findElement(By.name("message")).sendKeys("Tu van khoa hoc excel");
		sleepInSecond(2);
		
		//quay veef mainpage
		driver.switchTo().defaultContent();
		
		//search
		driver.findElement(By.id("live-search-bar")).sendKeys("excel");
		driver.findElement(By.cssSelector("button.search-button")).click();
		
		 
		List<WebElement> courseName = driver.findElements(By.cssSelector("div.content>h4"));
		
		for (WebElement course : courseName) {
			
			Assert.assertTrue(course.getText().contains("Excel"));
		
		   }

		}

	//@Test
	public void TC_02_HDFC_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		sleepInSecond(2);
		
		//Switch qua frame chuws login textbox
		driver.switchTo().frame("login_page");
		
		driver.findElement(By.name("fldLoginUserId")).sendKeys("hoangxuyen");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		
 
		driver.switchTo().defaultContent();
		
		// verify password hien thi
		Assert.assertTrue(driver.findElement(By.id("keyboard")).isDisplayed());
		
		driver.findElement(By.id("keyboard")).sendKeys("automation");
		sleepInSecond(2);
			  
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