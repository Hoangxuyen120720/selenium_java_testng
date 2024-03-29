package Webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Custom_Dropdown {
	WebDriver driver;
	Select select;
	Random rand = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName, lastName, emailAddress, companyName, password, day, month, year;
	String countryName, provinceName, cityName, addressName, postalCode, phoneNumber;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows"))
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		firstName = "Hoang";
		lastName = "Xuyen";
		emailAddress = "xuyen" + getRandomNumber() + "@gmail.com";
		companyName = "TMA";
		password = "123456789";
		day = "12";
		month = "September";
		year = "2000";
		
		countryName = "United States";
		provinceName = "Florida";
		cityName = "Miami";
		addressName = "123 PO Box";
		postalCode = "33111";
		phoneNumber = "+13055555584";
	}

	@Test
	public void TC_01_Register_New_Account() {

		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.cssSelector("a.ico-register")).click();
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);

		// select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		// select.selectByIndex(7);
		// select.selectByValue("12");
		// select.selectByVisibleText("18");

		// cachs gonj honw
		new Select(driver.findElement(By.name("DateOfBirthDay"))).selectByVisibleText(day);
		new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText(month);
		new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText(year);

		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);

		driver.findElement(By.id("register-button")).click();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");

		driver.findElement(By.xpath("//a[text()='My account']")).click();

		sleepInSecond(3);
		driver.findElement(By.cssSelector("input.email")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("input.password")).sendKeys(password);
		driver.findElement(By.cssSelector("button.login-button")).click();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);

		Assert.assertEquals(
				new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(), day);
		Assert.assertEquals(
				new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(), month);
		Assert.assertEquals(
				new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(), year);

		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), companyName);

		driver.findElement(By.cssSelector("li.customer-addresses>a")).click();

	}

	@Test
	public void TC_02_Add_Address() {

		driver.findElement(By.cssSelector("li.customer-addresses>a")).click();
		driver.findElement(By.cssSelector("button.add-address-button")).click();
		
		driver.findElement(By.id("Address_FirstName")).sendKeys(firstName);
		driver.findElement(By.id("Address_LastName")).sendKeys(lastName);
		driver.findElement(By.id("Address_Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Address_Company")).sendKeys(companyName);
		new Select(driver.findElement(By.id("Address_CountryId"))).selectByVisibleText(countryName);
		new Select(driver.findElement(By.id("Address_StateProvinceId"))).selectByVisibleText(provinceName);
		driver.findElement(By.id("Address_City")).sendKeys(cityName);
		driver.findElement(By.id("Address_Address1")).sendKeys(addressName);
		driver.findElement(By.id("Address_ZipPostalCode")).sendKeys(postalCode);
		driver.findElement(By.id("Address_PhoneNumber")).sendKeys(phoneNumber);
		
		driver.findElement(By.cssSelector("button.save-address-button")).click();
	
		Assert.assertEquals(driver.findElement(By.cssSelector("li.name")).getText(), firstName+ " " + lastName);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.email")).getText().contains(emailAddress));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.phone")).getText().contains(phoneNumber));
		Assert.assertEquals(driver.findElement(By.cssSelector("li.company")).getText(), companyName);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.address1")).getText(), addressName);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zip")).getText().contains(cityName));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zip")).getText().contains(postalCode));
		Assert.assertEquals(driver.findElement(By.cssSelector("li.country")).getText(), countryName);
		
	
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