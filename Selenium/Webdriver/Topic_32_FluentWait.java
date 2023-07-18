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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_32_FluentWait {
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
	
	

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();

	}

	@Test
	public void TC_01_FluentWait() {

		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(By.xpath("//div[@id='start']/button")).click();

		fluentDriver = new FluentWait<WebDriver>(driver);

		// Set timeout tong bang bao nhieu
		fluentDriver.withTimeout(Duration.ofSeconds(5)).

		// 1/3 giay check 1 lan
				pollingEvery(Duration.ofMillis(200)).ignoring(NoSuchElementException.class); // phaie co, neu ko la fail
		// Action chaining = chuoi hanh dong lien tiep

		// Apply dieu kien wait
		// Wait cho element co locator nay isDisplay

		boolean textStatus = fluentDriver.until(new Function<WebDriver, Boolean>() {
			// T: tham số của hàm apply - type của fluent wait khi khởi tạo lên
			// V: kiểu trả về của hàm apply

			@Override
			public Boolean apply(WebDriver driver) {
				return driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed();

			}

		});
		Assert.assertTrue(textStatus);

		// Wait cho element co text => gettext
		// //div[@id='finish']/h4

		String helloText = fluentDriver.until(new Function<WebDriver, String>() {

			@Override
			public String apply(WebDriver t) {
				return driver.findElement(By.xpath("//div[@id='finish']/h4")).getText();
			}
		});

		Assert.assertEquals(helloText, "Hello World!");

	}

	@Test
	public void TC_02_FluentWait() {

		driver.get("https://automationfc.github.io/fluent-wait/");
		fluentDriver = new FluentWait<WebDriver>(driver);

		fluentDriver.withTimeout(Duration.ofSeconds(12)).

				pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);

		boolean textStatus = fluentDriver.until(new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				return driver.findElement(By.xpath("//div[@id='javascript_countdown_time'] and text()='01:01:00']"))
						.isDisplayed();

			}

		});

		Assert.assertTrue(textStatus);

		// C2

		fluentDriver.until(new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				String text = driver.findElement(By.cssSelector("div#javascript_countdown_time")).getText();
				System.out.println(text);
				return text.equals("01:01:00");

			}

		});

	}

	
	// Ham tu viet cho findElement
	public WebElement findWebElement(By by) {

		fluentDriver = new FluentWait<WebDriver>(driver);

		fluentDriver.withTimeout(Duration.ofSeconds(timeOutDurationTime)).

				pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class);

		return fluentDriver.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(by);
			}

		});

	}
	
	public String getElementText(By by) {

		fluentDriver = new FluentWait<WebDriver>(driver);

		fluentDriver.withTimeout(Duration.ofSeconds(30)).

				pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);

		return fluentDriver.until(new Function<WebDriver, String>() {

			@Override
			public String apply(WebDriver driver) {
				return driver.findElement(by).getText();
			}

		});

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