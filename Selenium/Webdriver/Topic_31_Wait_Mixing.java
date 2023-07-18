package Webdriver;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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

public class Topic_31_Wait_Mixing {
	WebDriver driver;
	Select select;
	Random rand = new Random();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName, lastName, emailAddress, companyName, password, day, month, year;
	String countryName, provinceName, cityName, addressName, postalCode, phoneNumber;
	WebDriverWait explicitWait;
	


	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows"))
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	 else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		
	
		
	}

	//@Test
	public void TC_01_Found() {


		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait = new WebDriverWait(driver, 3);
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		

		String helloText = driver.findElement(By.cssSelector("div#finish>h4")).getText();
		
		
		Assert.assertEquals(helloText, "Hello World!");
		
	}
	@Test
	public void TC_04_Element_Not_Found_Implicit_Explicit() {
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("https://www.facebook.com/");
		
		
		//Implicit
		// Maaus chot: Luon luon uu tien implicit truoc
		// Tim dc element r moi lam gi lam
		System.out.println("1 - start: " + getDateTimeNow());
		driver.findElement(By.cssSelector("input#automation")).click();
		System.out.println("1 - End: " + getDateTimeNow());
		
		
		
		//Explicit
		// Bi ap dung ca 2 loa wait trong step nay
		// 10s cua implicit cho findelement
		// 10s cua explicit cho dieu kien
		System.out.println("1 - start: " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#automation")));
		} catch (Exception e) {
			e.printStackTrace();	
		}
		 System.out.println("1 - End: " + getDateTimeNow());
		
		
	}
	@Test
	public void TC_05_Element_Not_Found_Implicit_Less_Than_Explicit() {
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.get("https://www.facebook.com/");	
		//Implicit
		// Maaus chot: Luon luon uu tien implicit truoc
		// Tim dc element r moi lam gi lam
	
		
		
		//Explicit
		// Bi ap dung ca 2 loa wait trong step nay
		// 5s cua implicit cho findelement
		// 10s cua explicit cho dieu kien
		System.out.println("1 - start: " + getDateTimeNow());
		try {
			//Viec findelement (apply implicit timeout) no nam trong ham cua explicit visibilityOfElementLocated
			// Bat dong bo = song song
		 explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#automation")));
		} catch (Exception e) {
			e.printStackTrace();	
		}
		 System.out.println("1 - End: " + getDateTimeNow());
		
		
	}

	@Test
	public void TC_06_Element_Not_Found_Implicit_Greater_Than_Explicit() {
		explicitWait = new WebDriverWait(driver, 5);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("https://www.facebook.com/");	
		//Implicit
		// Maaus chot: Luon luon uu tien implicit truoc
		// Tim dc element r moi lam gi lam
	
		
		
		//Explicit
		// Bi ap dung ca 2 loa wait trong step nay
		// 10s cua implicit cho findelement
		// 5s cua explicit cho dieu kien
		System.out.println("1 - start: " + getDateTimeNow());
		try {
		 explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#automation")));
		} catch (Exception e) {
			e.printStackTrace();	
		}
		 System.out.println("1 - End: " + getDateTimeNow());	
	}
	
	@Test
	public void TC_07_Element_Not_Found_Implicit_Less_Than_Explicit_WebElement_Param() {
		explicitWait = new WebDriverWait(driver, 5);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("https://www.facebook.com/");	
		//Implicit
		// Ko set thi thoi gian de tim Element bang 0
		// Maaus chot: Luon luon uu tien implicit truoc
		// Tim dc element r moi lam gi lam
	
		//Explicit
		// Bi ap dung ca 2 loa wait trong step nay
		// 10s cua implicit cho findelement
		// 5s cua explicit cho dieu kien
		System.out.println("1 - start: " + getDateTimeNow());
		try {
			// Explicit co nhung ham co tham so la By/ Webelement (cung 1 chuc nangw nhu nhau)
			// Hang doi: findElement truoc, apply dk sau
			// Het 5s thoi
			// Cach dung ham se ra thoi gian khac nha/ thong bao khaac nhau
			// Van fail tren cung 1 dong code
		 explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#automation"))));
		} catch (Exception e) {
			e.printStackTrace();	
		}
		 System.out.println("1 - End: " + getDateTimeNow());	
	}
	
	
	// Th1: chi dung Explicit, ko dung implicit
	// Tat ca cacs step khac lien quan den findElement thi minh deu phai dung wait explicit het toan bo
	// Rui ro quen ko wait la fial ngay
	// Neu nhu minhf viet ham dung chung cho framework sau nay: wait chi dc dung tham so By thoi
	// Tc chay ko on dinh, fail bat thuong maf ko biet loi do dau
	
	// Th2: Dung ca 2
	// Tha set wait du va no chayj cham 1 ti
	// Timeout 2 cai bang nhau
	// 
	

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}
    
	
	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}