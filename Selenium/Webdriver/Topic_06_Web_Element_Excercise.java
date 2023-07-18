package Webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_Excercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	By emailtextbox = By.id("mail");
	By agender18radio = By.cssSelector("#under_18");
	By educationTextarea = By.cssSelector("#edu");
	By nameUser5Text = By.xpath("//h5[text()='Name: User5']");
	By passwordTexbox = By.cssSelector("#disable_password");
	By biographyTextarea = By.cssSelector("#bio");
	By deverlopmentCheckbox = By.cssSelector("#development");
	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}


	@Test
	public void TC_01_Display() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
	 // TExtbox/ Textarea nếu có hiển thị thì nhập text vào và in ra console
		if (driver.findElement(emailtextbox).isDisplayed()) {
			driver.findElement(emailtextbox).sendKeys("bnhxuyen@gmail.com");
		System.out.println("Email textbox is displayed");}
		else {
			System.out.println("Email textbox is not displayed");
		}
	
		if (driver.findElement(educationTextarea).isDisplayed()) {
			driver.findElement(educationTextarea).sendKeys("Hoangxuyen");
		System.out.println("Education textarea is displayed");}
		else {
			System.out.println("Education textarea is not displayed");
		}
	
	  // radio button

		if (driver.findElement(agender18radio).isDisplayed()) {
			driver.findElement(agender18radio).click();
		System.out.println("Age Under 18 is displayed");}
		else {
			System.out.println("Age Under 18 is not displayed");
		}
		
		
		if (driver.findElement(nameUser5Text).isDisplayed()) {
			
		System.out.println("Name User 5 is displayed");}
		else {
			System.out.println("Name User 5 is not displayed");
		}
		
		
	}

	@Test
	public void TC_02_Enabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		if (driver.findElement(passwordTexbox).isEnabled()) {
			
			System.out.println("Password textbox is enable");}
			else {
				System.out.println("Password textbox is disable");}
		
		
        if (driver.findElement(biographyTextarea).isEnabled()) {
			
			System.out.println("Biography textbox is enable");}
			else {
				System.out.println("Biography textbox is disable");}
		
        if (driver.findElement(emailtextbox).isEnabled()) {
			
			System.out.println("Email textbox is enable");}
			else {
				System.out.println("Email textbox is disable");}
		
	}

	@Test
	public void TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// Verify checkbox/ radion button are deselected
		Assert.assertFalse(driver.findElement(agender18radio).isSelected());
		Assert.assertFalse(driver.findElement(deverlopmentCheckbox).isSelected());
		
		
		// click vào checkbox/ radio
		driver.findElement(agender18radio).click();
		driver.findElement(deverlopmentCheckbox).click();
	    sleepInSecond(3);
	    
	    // Verify checkbox/ radio button are deselected
	    Assert.assertTrue(driver.findElement(agender18radio).isSelected());
		Assert.assertTrue(driver.findElement(deverlopmentCheckbox).isSelected());
		
	}
	@Test
	public void TC_04_MailChimp() {
		driver.get("https://login.mailchimp.com/signup/");
		
		driver.findElement(By.id("email")).sendKeys("bnhxuyen@tma.com.vn");
	  
		By passwordTextbox = By.id("new_password");
		By signupButton = By.id("create-account-enabled");
		driver.findElement(passwordTextbox).sendKeys("abc");
		
	
		sleepInSecond(3);
		
		// Verify lowercase
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("ABC");
		
	
		sleepInSecond(3);
	
		// Verify uppercase
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("123");
			
	
		sleepInSecond(3);
	
		// Verify number
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("!@#");
		
	
		sleepInSecond(3);
	
		// Verify special char
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("ABCXYZHM");
		
	
		sleepInSecond(3);
	
		// Verify char >= 8
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("123abcABC!@$");
		
	
		sleepInSecond(3);
	
		// Verify full data
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		
		
		
		
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