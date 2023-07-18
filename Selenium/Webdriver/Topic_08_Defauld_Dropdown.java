package Webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.DiscoveryStrategy.Explicit;

public class Topic_08_Defauld_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;//Wait tuong minh
	Select select;
	Random rand = new Random();
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
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //Wait ngam dinh
		
	}

	//@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		
		// Muốn chọn item cho Speed dropdown
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Medium");
		sleepInSecond(3);
		
	    Assert.assertEquals(	driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Medium");
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Fast");
		Assert.assertEquals(	driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Fast");
		
		sleepInSecond(3);
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role='option']", "Slow");
		Assert.assertEquals(	driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Slow");
		
	}

	//@Test
	public void TC_01_Dạng_Viết_Rõ() {	
		/* Chọn lần đầu */
		// 1. Click vaof 1 ther baats kif lamf sao cho nos xoor ra heets cac item cua dropdown
	    driver.findElement(By.cssSelector("span#speed-button")).click();
	    
	    // 2. Cho cho cac item load ra thanh cong
	    // Locator phai daij dien cho tat car element
	    // VA laay den the chua text lun
	    explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#speed-menu div[role='option']")));
	    
	    // 3. Tim item xem dung cai dang can hay k dungf vong lap de duyet qua
	    // 3.1 Neu k nam trong khoang nhin thay cua user thi can phai scoll xuong
	    // 3.2 Neeus no nam trong khoang nhin thay cua user thi k can phai scroll xuong
	    // Dua het tat ca item vao 1 list
	    List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector("ul#speed-menu div[role='option']"));
	    
	    for (WebElement temItem : speedDropdownItems) {
	    	String itemText = temItem.getText();
	    	System.out.println(itemText);
		
	    	
	    // 4. Kiem tra cais text cuar item dung voi cai minh mong muon
	 	   if (itemText.equals("Medium")) {
	 		   
	 	// 5. Click vao item do
	 		       temItem.click();
	 		       // Thoát ra khỏi vòng lặp k xét cho các case còn lại nữa	 		
	 		       	 		       	 		 	 		   
	 	   }
		}
	    sleepInSecond(3);
	    
	    /* chon lan 2 */
	    

	}


	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}
	
	//@Test
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		
		// Muốn chọn item cho Speed dropdown
		selectItemInDropdown("i.dropdown.icon", "span.text", "Elliot Fu");
		sleepInSecond(3);
		 Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Elliot Fu");
			
		
	    selectItemInDropdown("i.dropdown.icon", "span.text", "Stevie Feliciano");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Stevie Feliciano");
		
		selectItemInDropdown("i.dropdown.icon", "span.text", "Christian");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Christian");
		
	}
	
	
	
	//@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		
		// Muốn chọn item cho Speed dropdown
		selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
		sleepInSecond(3);
		 Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
			
	}
	
	
	@Test
	public void TC_04_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

		
		// Muốn chọn item cho Speed dropdown
		EnterandselectItemInDropdown("input.search", "span.text", "Angola");
		sleepInSecond(3);
		 Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Angola");
		
		 
		 EnterandselectItemInDropdown("input.search", "span.text", "Australia");
			sleepInSecond(3);
			 Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Australia");
			
	}
	
	
	
	
	
	
	
	    // Tránh lặp lại code nhiều lần
	    public void selectItemInDropdown (String parentCss, String allItemCss, String expectTextItem) {
	 	
		// 1. Click vaof 1 ther baats kif lamf sao cho nos xoor ra heets cac item cua dropdown
	    driver.findElement(By.cssSelector(parentCss)).click();
	    
	    // 2. Cho cho cac item load ra thanh cong
	    // Locator phai daij dien cho tat car element
	    // VA laay den the chua text lun
	    explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
	    
	    // 3. Tim item xem dung cai dang can hay k dungf vong lap de duyet qua
	    // 3.1 Neu k nam trong khoang nhin thay cua user thi can phai scoll xuong
	    // 3.2 Neeus no nam trong khoang nhin thay cua user thi k can phai scroll xuong
	    // Dua het tat ca item vao 1 list
	    List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector(allItemCss));
		    
	    for (WebElement temItem : speedDropdownItems) {
	  	
	    // 4. Kiem tra cais text cuar item dung voi cai minh mong muon
	 	   if (temItem.getText().trim().equals(expectTextItem)) {//trim dung để xóa khoảng trắng
	 		   
	 	// 5. Click vao item do
	 		       temItem.click();
	 		       // Thoát ra khỏi vòng lặp k xét cho các case còn lại nữa	 		
	 		       	 		       	 		 	 		   
	 	   }
		}
		
		
		
		
	}
		
	
        public void EnterandselectItemInDropdown (String textboxCss, String allItemCss, String expectTextItem) {
		
		// 1. nhập expected text item vao - xổ ra các item matching
        driver.findElement(By.cssSelector(textboxCss)).clear();
        driver.findElement(By.cssSelector(textboxCss)).sendKeys(expectTextItem);
	    sleepInSecond(1);
	    
	    // 2. Cho cho cac item load ra thanh cong
	    // Locator phai daij dien cho tat car element
	    // VA laay den the chua text lun
	    explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allItemCss)));
	    
	    // 3. Tim item xem dung cai dang can hay k dungf vong lap de duyet qua
	    // 3.1 Neu k nam trong khoang nhin thay cua user thi can phai scoll xuong
	    // 3.2 Neeus no nam trong khoang nhin thay cua user thi k can phai scroll xuong
	    // Dua het tat ca item vao 1 list
	    List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector(allItemCss));
		    
	    for (WebElement temItem : speedDropdownItems) {
	  	
	    // 4. Kiem tra cais text cuar item dung voi cai minh mong muon
	 	   if (temItem.getText().trim().equals(expectTextItem)) {//trim dung để xóa khoảng trắng
	 		   sleepInSecond(1);
	 	// 5. Click vao item do
	 		       temItem.click();
	 		       // Thoát ra khỏi vòng lặp k xét cho các case còn lại nữa	 		
	 		       	 		       	 		 	 		   
	 	   }
		}
		
		
		
		
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