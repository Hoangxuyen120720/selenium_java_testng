package Webdriver;

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

public class Topic_29_ExplicitWait {
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
		// Apply 15s cho cac dieu kien/ trang thai cu the
		explicitWait = new WebDriverWait(driver, 15);
	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Not_Enough_Time() {


		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait = new WebDriverWait(driver, 3);
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		

		String helloText = driver.findElement(By.cssSelector("div#finish>h4")).getText();
		
		
		Assert.assertEquals(helloText, "Hello World!");
		
	}
	@Test
	public void TC_02_Enough_Time() {
		
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait = new WebDriverWait(driver, 5);
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
		
		
		String helloText = driver.findElement(By.cssSelector("div#finish>h4")).getText();
		
		
		Assert.assertEquals(helloText, "Hello World!");
		
	}
	//@Test
	public void TC_03_More_Time() {
		
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait = new WebDriverWait(driver, 50);
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
			
		String helloText = driver.findElement(By.cssSelector("div#finish>h4")).getText();
		
		
		Assert.assertEquals(helloText, "Hello World!");
		
	}
	
	
	//@Test
	public void TC_04_theo_lop() {
			
			
		driver.get("https://www.facebook.com/");
		
		
		// cho 1 alert xuat hien trong html
		//**
	    explicitWait.until(ExpectedConditions.alertIsPresent()); //**
	    
		// cho cho 1 attribute co value
	    // Dung trc cho ham getAttribute
	  //*
	    explicitWait.until(ExpectedConditions.attributeContains(By.id("email"), "placeholder", "Email address or phone number"));  
	    explicitWait.until(ExpectedConditions.attributeToBe(By.id("email"), "placeholder", "Email address or phone number"));
	    
	    // Dung der cho cho 1 element co dc click hay ko
	  //**
	    explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[name='login']")));
	    
	    // Cho cho 1 element da dc chonj hay chua
	  //*
	    explicitWait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("input[name='sex']")));
		   
	    // Cho cho frame xuat hin vaf switch vao frame do
	  //*
	    WebDriver newDriver = explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("")));
	    
	    
	    // Cho cho 1 elenment ko con visible nua
	  //*
	    explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));
	    
	    // Cho cho nhieu elenment ko con visible nua
	    explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector(""))));
	    
	    // Var Argument
	    explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElement(By.cssSelector("")), driver.findElement(By.cssSelector(""))));
	    
	    // cho cho so element co tong la bao nhieu
	    // Can lay ra so luong element la bao nhieu
	  //*
	    explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(""), 10));
	    explicitWait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(""), 10));
	    explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(""), 10));
	    
	    // Lay ra so luong la bao nhieu
	    int radioNumber = driver.findElements(By.cssSelector("")).size();
	    
	    
	    // Thao tac va no bat ra tab/window
	    // Cho cho bao nhieu cua so/ tab duoc xuat hien
	    boolean windowActive = explicitWait.until(ExpectedConditions.numberOfWindowsToBe(3));
	    
	    
	    // Cho cho element co trong HTml (ko can q tam visible)
	  //*
	    explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(addressName)));
	    
	    //Cho cho nhieu element co trong HTml (ko can q tam visible)
	    // Dropdown (Item)
	    explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(addressName)));
	    
	    
	    //
	    WebElement loginUsername = explicitWait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.cssSelector(addressName), By.cssSelector(addressName)));
	    
	    List<WebElement> loginUsernames = explicitWait.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(By.cssSelector(addressName), By.cssSelector(addressName)));
	    
	    
	    // CHo cho 1 element ko ton taij trong HTML nua
	    explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector(addressName))));
	    // Verify 
	    Assert.assertTrue(explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector(addressName)))));
	    
	    
	    
	    // Truoc ham get text
	  //*
	    explicitWait.until(ExpectedConditions.textToBe(By.className(addressName), "huhuhu"));
	    
	    
	    // Text presen it khi dung
	    explicitWait.until(ExpectedConditions.textToBePresentInElementLocated(By.className(addressName), "huhuhu"));
	   
	    
	    explicitWait.until(ExpectedConditions.textToBePresentInElementValue(By.className(addressName), "huhuhu"));
	    
	    // Dung truoc ham get title ( tren tuong doi, duoi tuyet doi)
	    explicitWait.until(ExpectedConditions.titleContains("login"));
	    explicitWait.until(ExpectedConditions.titleIs("login"));
	    
	    // DUng trc ham get currents url
	    explicitWait.until(ExpectedConditions.urlContains("login.com"));
	    
	    explicitWait.until(ExpectedConditions.urlToBe("loginblabla.com"));
	    
	    
	    // Cho cho 1 element hien thi
	    WebElement element = driver.findElement(By.cssSelector(addressName));
	    explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(addressName))));
	    
	  //**
	    explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(addressName)));
	    
	    
	    // Cho cho nhieu element hien thi
	    explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector(addressName))));
	    explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.cssSelector(addressName)), driver.findElement(By.cssSelector("")))); 
	  //**
	    explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("")));
	    
	    
	    // CHo cho 1 element hien thi, long nhau
	    explicitWait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(By.cssSelector(""), By.cssSelector(addressName)));
	    explicitWait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(driver.findElement(By.cssSelector("")), By.cssSelector(addressName)));
	    

	    
	    
	    
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