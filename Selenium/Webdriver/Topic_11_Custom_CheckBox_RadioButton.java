package Webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Custom_CheckBox_RadioButton {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
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
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	//@Test
	public void TC_01_() {
		
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(5);
		// Thẻ input bị che ko thao tác đc
		// nhưng lại dùng để verify đc  vì hàm selected chỉ work vs thẻ input
		
		// Thao tác chọn
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']//preceding-sibling::div/input")).click();
		
		
		// Verify đã chọn tahnhf công
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']//preceding-sibling::div/input")).isSelected());
		
	}

	//@Test
	public void TC_02_() {
		
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(5);
		// click thẻ khác input
		// nhưng lại ko dùng để verify đc  vì hàm selected chỉ work vs thẻ input
		
		// Thao tác chọn
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']//preceding-sibling::div/div")).click();
		
		
		// Verify đã chọn tahnhf công
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']//preceding-sibling::div/div")).isSelected());
		
		}

	//@Test
	public void TC_03_() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(5);
		// click thẻ khác input
		// dùng thẻ input để verify
		
		// Thao tác chọn
		driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']//preceding-sibling::div/div")).click();
		
		
		// Verify đã chọn tahnhf công
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Đăng ký cho người thân']//preceding-sibling::div/input")).isSelected());
		//ko nên apply vào framework/ dự án thực tế vì phải define tới nhiều locator, dex bị hiểu nhầm , mất thời gian
		
		}

	//@Test
	public void TC_04_() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		sleepInSecond(5);
		// thẻ input dùng để click
		// Hàm click của webelement k thao tác với element bị ẩn đc
		// nên sẽ dùng click của js 
		// selenium cung cấp 1 thư viện để có thể nhúng nhuungwx đoạn code js vào kịch bản test đc => JavascriptExcutor
		// dùng thẻ input để verify
		
		By radioButton = By.xpath("//div[text()='Đăng ký cho người thân']//preceding-sibling::div/input");
		
		// Thao tác chọn
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(radioButton));
		sleepInSecond(3);
		
		
		// Verify đã chọn tahnhf công
		Assert.assertTrue(driver.findElement(radioButton).isSelected());
		
		}
	
	@Test
	public void TC_05_() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		sleepInSecond(5);
		
		By radioButton = By.cssSelector("div[aria-label='Hà Nội']");
		By checkbox = By.cssSelector("div[aria-label='Quảng Ngãi']");
		
		
		// Thao tác chọn
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(radioButton));
		sleepInSecond(3);
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(checkbox));
		sleepInSecond(3);
		
		
		// Verify đã chọn tahnhf công
		//c1
		Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Hà Nội'][aria-checked='true']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Quảng Ngãi'][aria-checked='true']")).isDisplayed());
		
		
		// c2
		Assert.assertEquals(driver.findElement(radioButton).getAttribute("aria-checked"), "true");
		Assert.assertEquals(driver.findElement(checkbox).getAttribute("aria-checked"), "true");
		
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