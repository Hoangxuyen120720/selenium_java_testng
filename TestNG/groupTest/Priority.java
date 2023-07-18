package groupTest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Priority {

	
	@BeforeClass (alwaysRun = true) // luoon luon chay
	public void beforeClass() {
		System.out.println("before");
	}
		
	@Test (priority = 1)
	public void Create_New_User() {
		System.out.println("tc01");
		
		
	}
		
	@Test (priority = 2)
	public void view_Existing_User() {
		System.out.println("tc02");
	}
		
	
	
	@Test (priority = 3)
	public void Update_User() {
		System.out.println("tc03");
	}
	
	
	@Test (priority = 4)
	public void Move_user() {
		System.out.println("tc014");
	}
	
	
	@Test (priority = 5)
	public void Delete_User() {
		System.out.println("tc05");
	}
	
	//@Test (priority = 5)
	//public void User_01_...() {
		//System.out.println("tc05");
	//}
	
	
	
	@AfterClass (groups = "user")
	public void affterClass() {
		System.out.println("before");
	}
}
