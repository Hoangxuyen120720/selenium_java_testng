package Webdriver;




import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_16_AXES2 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
    String OsName = System.getProperty("os.name");
	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}



	@Test
	public void TC_01_errormessage(){
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//ctrl+pace: suggest hàm
	    driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();
	//verifi
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");
		
	}
 

	@Test
	public void TC_02_Invalid_Email(){
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//ctrl+pace: suggest hàm
	    driver.findElement(By.id("txtFirstname")).sendKeys("Hoangxuyen");
	    driver.findElement(By.id("txtEmail")).sendKeys("Hoangxuyen@@gmai.com");
	    driver.findElement(By.id("txtCEmail")).sendKeys("Hoangxuyen@@gmai.com");
	    driver.findElement(By.id("txtPassword")).sendKeys("12072000");
	    driver.findElement(By.id("txtCPassword")).sendKeys("12072000");
	    driver.findElement(By.id("txtPhone")).sendKeys("0907946763");
	    
	    driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();
		
    
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
		
	}
	
	@Test
	public void TC_03_Incorrect_Email(){
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//ctrl+pace: suggest hàm
	    driver.findElement(By.id("txtFirstname")).sendKeys("Hoangxuyen");
	    driver.findElement(By.id("txtEmail")).sendKeys("Hoangxuyen@gmail.com");
	    driver.findElement(By.id("txtCEmail")).sendKeys("Hoangxuyen@gmail.net");
	    driver.findElement(By.id("txtPassword")).sendKeys("12072000");
	    driver.findElement(By.id("txtCPassword")).sendKeys("12072000");
	    driver.findElement(By.id("txtPhone")).sendKeys("0907946763");
	    
	    driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();
	
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
		
	}

	@Test
	public void TC_04_Invalid_password(){
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//ctrl+pace: suggest hàm
	    driver.findElement(By.id("txtFirstname")).sendKeys("Hoangxuyen");
	    driver.findElement(By.id("txtEmail")).sendKeys("Hoangxuyen@gmail.com");
	    driver.findElement(By.id("txtCEmail")).sendKeys("Hoangxuyen@gmail.com");
	    driver.findElement(By.id("txtPassword")).sendKeys("1207");
	    driver.findElement(By.id("txtCPassword")).sendKeys("1207");
	    driver.findElement(By.id("txtPhone")).sendKeys("0907946763");
	    
	    driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();
	
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		
	}
	
	@Test
	public void TC_05_Incorect_password(){
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//ctrl+pace: suggest hàm
	    driver.findElement(By.id("txtFirstname")).sendKeys("Hoangxuyen");
	    driver.findElement(By.id("txtEmail")).sendKeys("Hoangxuyen@gmail.com");
	    driver.findElement(By.id("txtCEmail")).sendKeys("Hoangxuyen@gmail.com");
	    driver.findElement(By.id("txtPassword")).sendKeys("12072000");
	    driver.findElement(By.id("txtCPassword")).sendKeys("12072001");
	    driver.findElement(By.id("txtPhone")).sendKeys("0907946763");
	    
	    driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();
	
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");
		
	}
	
	@Test
	public void TC_06_Invalid_phone(){
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		//ctrl+pace: suggest hàm
	    driver.findElement(By.id("txtFirstname")).sendKeys("Hoangxuyen");
	    driver.findElement(By.id("txtEmail")).sendKeys("Hoangxuyen@gmail.com");
	    driver.findElement(By.id("txtCEmail")).sendKeys("Hoangxuyen@gmail.com");
	    driver.findElement(By.id("txtPassword")).sendKeys("12072000");
	    driver.findElement(By.id("txtCPassword")).sendKeys("12072000");
	    driver.findElement(By.id("txtPhone")).sendKeys("090794676");
	    
	    driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();
	
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
		
		 driver.findElement(By.id("txtPhone")).clear();
		 driver.findElement(By.id("txtPhone")).sendKeys("090794676301");
		    
		 driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();
		
	     Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
		
	     driver.findElement(By.id("txtPhone")).clear();
		 driver.findElement(By.id("txtPhone")).sendKeys("00907946763");
		    
		 driver.findElement(By.xpath("//div[@class='form frmRegister']//button[text()='ĐĂNG KÝ']")).click();
		
	     Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
			
		
		
	}
	
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}