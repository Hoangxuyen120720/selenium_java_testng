package groupTest;

import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AlwayRun {
	WebDriver driver;
	Select select;
	Random rand = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExcutor;

	FluentWait<WebDriver> fluentDriver;
	// T - Type: WebDriver/ WebElement/ List<WebElement>/ String/ Boolean

	

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 30);
		Assert.assertTrue(false);
	

	}

	
	
	@Test
	public void TC_01() {
		driver.get("https://admin-demo.nopcommerce.com/login");
		
	}
	
	
	
	
	
	
	@AfterClass (alwaysRun = true)
	public void afterClass() {
		System.out.println("Affterclass dc chay qua");
		driver.quit();
	
	
	}
	
	
	
}
