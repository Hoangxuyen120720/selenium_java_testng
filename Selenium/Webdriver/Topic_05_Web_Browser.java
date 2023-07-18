package Webdriver;


import java.awt.Window;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class Topic_05_Web_Browser {
	
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
	
		//Tương tác với browser sẽ thông qua biến WebDriver driver
		//Tương tác với Element thì sẽ thông qua biến WebElement element
		//arg=argument= tham số
		//Tip: hàm nào action thì k trả về (void), hàm nào lấy dữ liệu ra thì cần trả về (return)
		
	}

	public void TC_01_() {
		//Java document (cách sử dụng hàm này: f3)
		
		
		// >=2 cửa sổ: nó sẽ đóng tab/window mà nó đang đóng
		driver.close();//*
		
		// ko quan tâm bao nhiêu tab/window, đóng lun
		driver.quit();//**
		
		//Có thể lưu vào 1 biến để sử dụng cho step sau/ có thể sử dụng lun ko cần tạo biến
		WebElement email = driver.findElement(By.xpath(""));
		email.clear();
		email.sendKeys("");
		//hoặc -> bad code
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		
		
		// Tìm nhiều element
		@SuppressWarnings("unused")
		List<WebElement> checkbox  = driver.findElements(By.xpath(""));
		
		
	    // Mở ra 1 Url nào đó
		driver.get("");//**
		
		// Trả vể URl của page hiện tại (lưu vào biến hoặc dùng lun)S
		driver.getCurrentUrl();
		Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com/");
		
		
		// Trả về source code HTML của page hiện tại
		// Verifi tương đối
		driver.getPageSource();
		
		Assert.assertTrue(driver.getPageSource().contains(""));
		
		//Trả về title của page hiện tại
		driver.getTitle();
		Assert.assertEquals(driver.getTitle(), "Trang chủ - Canva");
		
		//Lấy ra đc ID của window/ tab mà driver đang đứng (active)
		driver.getWindowHandle();
		
		String loginWindowID = driver.getWindowHandle();//*
		
	
		// Lấy ra id của tất cả Window/ tab (có bài nâng cao, java intermediate)
		@SuppressWarnings("unused")
		Set<String> allIDs = driver.getWindowHandles();//*
		
		
		//Cookie và cache
		driver.manage();
		Options opt = driver.manage();
		//login thành công > lưu lại
		opt.getCookies();//*
		// Testcase khác > SEt cookie vào lại > k cần phải login nữa
		
		// học sau
		opt.logs();
		
		Timeouts time = opt.timeouts();
		
		
		//Implicit wait and depend on: FindElement/ FindElements"
		// khoảng thời gian chờ element xuất hiện
		
		time.implicitlyWait(5, TimeUnit.SECONDS);// 5s = 5000 ms = 5000000 micro giây//**
		time.implicitlyWait(5000, TimeUnit.MILLISECONDS);
		time.implicitlyWait(5000000, TimeUnit.MICROSECONDS);
		
		
		// Khoảng thời gian chờ page load xong trong vòng x giây
		time.pageLoadTimeout(5, TimeUnit.SECONDS);
		
		
		
		// WebDriver API - Javascript Excutor (JavascriptExcutor library)
		// Khoảng thời gian chờ script đc thực thi xong trong vòng x giây
		time.setScriptTimeout(5, TimeUnit.SECONDS);
		
		
		
		org.openqa.selenium.WebDriver.Window win = opt.window();
		win.fullscreen();
		win.maximize();//**
		
		
		// Test GUI: font/ size/ color/ position/ location/..
		win.getPosition();// vị trí của cửa sổ, của sổ nằm ở tọa độ nào của màn hình
		win.getSize();// kích thước bên trong của trình duyệt
		
		
		
		Navigation nav = driver.navigate();
		nav.back();// về trước
		nav.refresh();// về sau
		nav.forward();// load lại trang
		nav.to("");// truyền vào 1 url như driver.get
		
		
		TargetLocator tar = driver.switchTo();
		// WebDriver API - alert/ Authentication Alert (alert library)
		tar.alert();//*
		
		// WebDriver API - Frame / Iframe (Frame library)
		tar.frame(0);
		
		//WebDriver API - Windows/ TAbs
		tar.window(loginWindowID);
		
		
	}

	public void TC_02_() {
		}

	public void TC_03_() {
		}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}