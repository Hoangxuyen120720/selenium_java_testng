package Webdriver;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

import net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.DiscoveryStrategy.Explicit;

public class Topic_23_JsExecutor {
	WebDriver driver;
	Random rand = new Random();
	Alert alert;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitwait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String emailAddress = "test" + rand.nextInt(9999) + "@gmail.net";

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) 
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		
		//luôn luôn khởi tạo sau biens driver
		explicitwait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}


	//@Test
	public void TC_01_Kyna_Iframe() {
		navigateToUrlByJS("http://live.techpanda.org/");
		sleepInSecond(5);
		
		Assert.assertEquals(executeForBrowser("return document.domain;"), "live.techpanda.org");
		Assert.assertEquals(executeForBrowser("return document.URL;"), "http://live.techpanda.org/");
		
		hightlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		sleepInSecond(3);
		
		
		hightlightElement("//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']/button");
		clickToElementByJS("//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']/button");
		sleepInSecond(3);
		
		Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));
		

		hightlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");
		sleepInSecond(3);
		
		hightlightElement("//input[@id='newsletter']");
		scrollToElementOnDown("//input[@id='newsletter']");
		
		sleepInSecond(3);
		
		sendkeyToElementByJS("//input[@id='newsletter']", emailAddress);
		
		hightlightElement("//button[@title='Subscribe']");
		clickToElementByJS("//button[@title='Subscribe']");
		
		sleepInSecond(3);
		
		Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));
		
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		sleepInSecond(5);
		
		Assert.assertEquals(executeForBrowser("return document.domain;"), "demo.guru99.com/");
		
	
		}

	
	

	@Test
	public void TC_02_HTML5_Validation_Message() {
		navigateToUrlByJS("https://warranty.rode.com/");
		sleepInSecond(5);
		
		String registerButton = "//button[contains(text(), 'Register')]";
		String firstNameTextbox = "//div[contains(text(), 'Register')]//following-sibling::div//input[@id='firstname']";
		String surNameTextbox = "//div[contains(text(), 'Register')]//following-sibling::div//input[@id='surname']";
		String emailTextbox = "//div[contains(text(), 'Register')]//following-sibling::div//input[@id='email']";
		String passwordTextbox = "//div[contains(text(), 'Register')]//following-sibling::div//input[@id='password']";
		String comfirmPasswordTextbox = "//div[contains(text(), 'Register')]//following-sibling::div//input[@id='password-confirm']";
		
	
		clickToElementByJS(registerButton);
		sleepInSecond(2);
		
		Assert.assertEquals(getElementValidationMessage(firstNameTextbox), "Please fill out this field.");
		
		sendkeyToElementByJS(firstNameTextbox, "Automationtest");
        clickToElementByJS(registerButton);
		sleepInSecond(2);
		
		Assert.assertEquals(getElementValidationMessage(surNameTextbox), "Please fill out this field.");
		
		sendkeyToElementByJS(surNameTextbox, "Test");
        clickToElementByJS(registerButton);
		sleepInSecond(2);
		
		Assert.assertEquals(getElementValidationMessage(emailTextbox), "Please fill out this field.");
		
		
		sendkeyToElementByJS(emailTextbox, "auto@test@gmail.com");
        clickToElementByJS(registerButton);
		sleepInSecond(2);
		
		Assert.assertEquals(getElementValidationMessage(emailTextbox), "Please enter an email address.");
		
		sendkeyToElementByJS(emailTextbox, "auto@gmail.com");
        clickToElementByJS(registerButton);
		sleepInSecond(2);
		
		Assert.assertEquals(getElementValidationMessage(passwordTextbox), "Please fill out this field.");
		
		sendkeyToElementByJS(passwordTextbox, "123456789");
        clickToElementByJS(registerButton);
		sleepInSecond(2);
		
		Assert.assertEquals(getElementValidationMessage(comfirmPasswordTextbox), "Please fill out this field.");
		
		
		}
	
	
	
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
	
    public void switchtoWIndowByID (String otherID) {
    	
    	// Lay het tat ca ID ra
		Set<String> allWindowIDs = driver.getWindowHandles();
		
       // Sau do dung vong lap duyet qua va kiem tra
		
		for (String id : allWindowIDs) {
			if (!id.equals(otherID)) {
				driver.switchTo().window(id);
}		
}
    	
    	
    }
	
    public void closeAllWindowWithoutParent  (String parentID) {
    	
    	// Lay het tat ca ID ra
		Set<String> allWindowIDs = driver.getWindowHandles();
		
       // Sau do dung vong lap duyet qua va kiem tra
		
		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
				sleepInSecond(2);
           }		
      }   
		driver.switchTo().window(parentID);
    }
    
    
    
    public void switchtoWIndowByPAgeTitle  (String expectedPaggeTitle) {
    	
		Set<String> allWindowIDs = driver.getWindowHandles();
	
		for (String id : allWindowIDs) {
			// Switch tung ID truoc
            driver.switchTo().window(id);
            
            // Lay ra title cuar page nay
            String actualPageTitle = driver.getTitle();
            
            if (actualPageTitle.equals(expectedPaggeTitle)) {
            	break;
		}		
     }    	    	
 }
    
    
	public String getUrlByUserPass (String url, String username, String password) {

		String[] newurl = url.split("//");
		//http:
		//the-internet.herokuapp.com/basic_auth
		
		url = newurl[0] + "//" + "admin" + ":" + "admin" + "@" + newurl[1];
		
		return url;
				
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