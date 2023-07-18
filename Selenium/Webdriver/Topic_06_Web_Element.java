package Webdriver;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Webelement() {
	// Để tương tác với Element thì cần phải tìm đc element đó thông qua các locator của nó
	// by: id/ class/ name/ xpath/ css/ tagname/ linktext/ partiallinktext
	
		driver.get("https://demo.nopcommerce.com/login?ReturnUrl=%2Fcustomer%2Finfo");
	    
		// Khi Element này dùng nhiều lần mới cần khai báo biến
		WebElement emailTextbox = driver.findElement(By.id("Email"));
	    WebElement PasswordTextbox = driver.findElement(By.id("Password"));
	    emailTextbox.isDisplayed();
	    emailTextbox.clear();
	    emailTextbox.sendKeys("ccc");
	    
	    // Khi dùng 1 lần
	    driver.findElement(By.id("Email")).sendKeys("");
	    
	    
	    
	// Vô phần chính:
	    
	    // Dùng cho các textbox/ textarea/ dropdown (Editable)   
	    emailTextbox.clear();//*
	    //nhập liệu
	    emailTextbox.sendKeys("");//**
	    
	    // Click vào gì đó
	    emailTextbox.click();
	    
	    // Tìm Elemen liên tục nhưng không dùng kiểu này.
	    emailTextbox.findElement(null);
	    
	    //lấy ra cái placeholder
	    String searchAttribute = emailTextbox.getAttribute("placeholder");//**
	    //Search Store
	    
	    // GUI: Font/ Size/ Color/ Location/ Position/...
	    // button save màu gì
	    emailTextbox.getCssValue("");//*	    
	    // Lấy ra vị trí của element so với web (bên ngoài)
	    emailTextbox.getLocation();
	    org.openqa.selenium.Point point = emailTextbox.getLocation();
	    point.x = 324;
	    point.y = 324;
	    
	    //Kích thước của element (bên trong)
	    org.openqa.selenium.Dimension di = emailTextbox.getSize();
	    di.getHeight();
	    di.getWidth();
	    
	    System.out.println(di.height);
	    System.out.println(di.width);
	    
	    // Location + Size
	    org.openqa.selenium.Rectangle rec = emailTextbox.getRect();
	    
	    
	    
	    
	    // Chụp hình bị lỗi khi test case fail, xuất ra report
	    emailTextbox.getScreenshotAs(OutputType.BASE64);//*
	    
	    // Cần lấy ra tên thẻ HTML của element đó -> truyền vào cho 1 locator khác
	    emailTextbox.getTagName();
	    driver.findElement(By.xpath("//" + emailTextbox + "[@id='email']"));
	    
	    // Lấy text từ error message/ sucess message/ label/ header...
	    emailTextbox.getText();//**
	    
	    // Khi nào dùng getText - khi nào dùng getAttribute
	    // Khi cái value mình cần lấy nó nằm bên ngoài -> getText
	    // Khi cái value mình cần lấy nó nằm bên trong -> getAttribute
	    
	    
	    // Dùng để verify xem 1 element hiển thị hoặc ko
	    // Phạm vi: Tất cả các element
	    emailTextbox.isDisplayed();
	    Assert.assertTrue( emailTextbox.isDisplayed());//**
	    Assert.assertFalse( emailTextbox.isDisplayed());
	    
	    // Dùng để verify xem 1 element đó có thaop tác đc hay k
	    // Phạm vi: Tất cả các element
	    Assert.assertTrue(emailTextbox.isEnabled());
	    
	    // Dùng để verify xem 1 element đó được chọn hay chưa
	    // Phạm vi: checkbox/ Radio 
	    Assert.assertTrue(emailTextbox.isSelected());//*
	    
	    // Các element nằm trong thẻ form, tương ứng với hành động của user là enter
	    emailTextbox.submit();
	    
	
	
	
	
	
	
	
	
	
	
	
	}

	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}