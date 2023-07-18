package Webdriver;

import org.openqa.selenium.support.Color;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Colors;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_10_Button_Default_CheckBox_RadioButton {
	WebDriver driver;
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		sleepInSecond(2);
		
		By loginButton = By.cssSelector("button.fhs-btn-login");
		
		//Verify login button is disable
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		
		Assert.assertTrue(driver.findElement(loginButton).getCssValue("background").contains("rgb(224, 224, 224)")); // chỗ 224 có dấu cách, hùi trước queen thêm nên chạy fail
		
		// nhập email, pass
		driver.findElement(By.cssSelector("input#login_username")).sendKeys("0907946763");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456789");
        sleepInSecond(2);
        
        //Verify login button is enable
        Assert.assertTrue(driver.findElement(loginButton).isEnabled());

		// lấy ra mã màu của element
		// chrome/ edge/.. rgb
		// firefox ... rgba
		
        String loginButtonBackgroundColor = driver.findElement(loginButton).getCssValue("background-color");
        
	
        // chuyển từ rgba/ rgb qua kiểu color
        Color loginButtonColor  = Color.fromString(loginButtonBackgroundColor);
        String loginButtonHexa = loginButtonColor.asHex().toUpperCase();
        
        //verify backgroundcolor rgb(201, 33, 39)
        Assert.assertEquals(loginButtonHexa, "#C92127");
		
		
		
	}

	//@Test
	public void TC_02_Default_CheckBox_Radio_Single() {
		
		driver.get("https://automationfc.github.io/multiple-fields/");
		// Click chọn checkbox
		driver.findElement(By.xpath("//label[contains(text(),\"Diabetes\")]/preceding-sibling::input")).click();
		
		
		// Click chọn radio
		driver.findElement(By.xpath("//label[contains(text(),\" I don't drink \")]/preceding-sibling::input")).click();
				
		// Verify các checkbox, radio đã đc chọn
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),\"Diabetes\")]/preceding-sibling::input")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),\" I don't drink \")]/preceding-sibling::input")).isSelected());
		
		// Checkbox có thể tự bỏ chọn
		driver.findElement(By.xpath("//label[contains(text(),\"Diabetes\")]/preceding-sibling::input")).click();
		
		//Verify các checkbox đã bỏ chọn
		Assert.assertFalse(driver.findElement(By.xpath("//label[contains(text(),\"Diabetes\")]/preceding-sibling::input")).isSelected());
		
		// Radio ko có thể tự bỏ chọn
		driver.findElement(By.xpath("//label[contains(text(),\" I don't drink \")]/preceding-sibling::input")).click();
		
		//Verify các radio vẫn đc chọn
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),\" I don't drink \")]/preceding-sibling::input")).isSelected());
		
		
		
		}

	//@Test
	public void TC_03_Default_CheckBox_Multiple() {
		
		List <WebElement> allCheckbox = driver.findElements(By.cssSelector("input.form-checkbox"));
		
		// Dùng vòng lặp for để duyệt qua và check hết các checkbox
		for (WebElement checkbox : allCheckbox) {
			checkbox.click();
			sleepInSecond(1);
					}
		// Verify tất cả checkbox
		for (WebElement checkbox : allCheckbox) {
			Assert.assertTrue(checkbox.isSelected());
		}
		
		// Gặp 1 checkbox yêu cầu mới check
		//for (WebElement checkbox : allCheckbox) {
		//	if (checkbox.getAttribute("value").equals("Arthritis")) {
		//		checkbox.click();
		//	}
		//}
	}
	//@Test
	public void TC_04_Default_CheckBox() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		// Click chọn checkbox
		
		checkTocheckBox(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
			driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).click();			
		
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
		
		uncheckTocheckBox(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
			driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).click();			
		
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
		
		
		
	}
		
		
	public void checkTocheckBox(By by) {
		if (!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	
	public void uncheckTocheckBox(By by) {
		if (driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
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