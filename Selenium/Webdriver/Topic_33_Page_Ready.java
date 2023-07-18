package Webdriver;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_33_Page_Ready {
	WebDriver driver;
	Select select;
	Random rand = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExcutor;

	FluentWait<WebDriver> fluentDriver;
	// T - Type: WebDriver/ WebElement/ List<WebElement>/ String/ Boolean
	
	long timeOutDurationTime = 30;
	long pollingTime = 1;
	// viet nhu v de de sua code
	Actions action;
	

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 30);
		action = new Actions(driver);

	}

	//@Test
	public void TC_01_Admin_Nopcommerce() {
		driver.get("https://admin-demo.nopcommerce.com/login");
		
		
		driver.findElement(By.cssSelector("input#Email")).clear();
		driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
		driver.findElement(By.cssSelector("input#Password")).clear();
		driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.login-button")).click();
		
		
		Assert.assertTrue(isPageLoadedSuccess());
		
		driver.findElement(By.xpath("//ul[@role='menu']/li/a[@class='nav-link']//p[contains(text(), 'Customers')]")).click();
		driver.findElement(By.xpath("//li/a/p[text()=' Customers']")).click();
		Assert.assertTrue(isPageLoadedSuccess());
		
		Assert.assertTrue(driver.findElement(By.cssSelector("h1.float-left")).isDisplayed());
		

	    
	}
	
	
	@Test
	public void TC_02_Test_Automation_Blog() {
		driver.get("https://blog.testproject.io/");
		
		
		action.moveToElement(driver.findElement(By.cssSelector("section#search-2 input.search-field"))).perform();
		
		Assert.assertTrue(isPageLoadedSuccess());
		
		
		
		driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys("Selenium");
		driver.findElement(By.cssSelector("section#search-2 span.glass")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='main-heading' and text()='Search Results']")));
		
		Assert.assertTrue(isPageLoadedSuccess());
		
		List<WebElement> postTitles = driver.findElements(By.cssSelector("div.post-content>h3>a"));
		for (WebElement title : postTitles) {
			
			System.out.println(title.getText());
			Assert.assertTrue(title.getText().contains("Selenium"));
			
			
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	// cach 1
	public Boolean waitForAjaxIconInvisible() {
		return explicitWait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div#ajaxBusy"))));
		
		
		
	}
	
	
	// cach 2
	public boolean isPageLoadedSuccess() {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		
		
		// Dkien 1
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
		
		
        // Dkien 2
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}
	
	
	public boolean isElementDisplay(By by) {

		fluentDriver = new FluentWait<WebDriver>(driver);

		fluentDriver.withTimeout(Duration.ofSeconds(30)).

				pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);

		return fluentDriver.until(new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver Boolean) {
				return driver.findElement(by).isDisplayed();
			}

		});

	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}