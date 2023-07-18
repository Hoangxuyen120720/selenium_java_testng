package groupTest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SkipTestcase {

	
	@BeforeClass (alwaysRun = true) // luoon luon chay
	public void beforeClass() {
		System.out.println("before");
	}
		
	@Test (description = "MenuItems_004_Careers - Careers menu item")
	public void Create_New_User() {
		System.out.println("tc01");
		
		
	}
	
	// TMS: Test Management system - jira/ Testlink/ TestRail/...
	
	
		
	@Test (description = " JIRA #8493 - View an existing User....")
	public void view_Existing_User() {
		System.out.println("tc02");
	}
		
	
	
	@Test (enabled = false)
	public void Update_User() {
		System.out.println("tc03");
	}
	
	
	@Test 
	public void Move_user() {
		System.out.println("tc014");
	}
	
	
	@Test 
	public void Delete_User() {
		System.out.println("tc05");
	}
	
	
	
	
	@AfterClass (groups = "user")
	public void affterClass() {
		System.out.println("before");
	}
}
