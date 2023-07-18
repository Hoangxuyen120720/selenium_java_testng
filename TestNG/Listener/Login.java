package Listener;

import java.util.concurrent.TimeUnit;

import javax.management.RuntimeErrorException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;



@Listeners(ListenerReport.class)
public class Login {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String userName = "selenium_11_01@gmail.com";
	String password = "111111";

	By emailTextbox = By.xpath("//*[@id='email']");
	By passwordTextbox = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//*[@id='send2']");

	@org.testng.annotations.Parameters("browser") // phai chay tu file xml
	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_LoginToSystem() {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");

		driver.findElement(emailTextbox).sendKeys(userName);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(loginButton).click();
		
	}
	@Test
	public void TC_02_Verify() {
		Assert.assertFalse(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(userName));
		
	}

	
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
