package groupTest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Payment {

	
	@BeforeClass
	public void beforeClass() {
		
	}
		
	
	// Chi apply tc chay theo luong, co du lieu phu thuoc vao nhau
	@Test
	public void TC_01_create() {
		System.out.println("tc01");
		
		
	}
		
	@Test (dependsOnMethods = "TC_01_create")
	public void Tc_02_Login() {
		System.out.println("tc02");
	}
		
	@Test (dependsOnMethods = "Tc_02_Login")
	public void Tc_03_View() {
		System.out.println("tc03");
	}
	@Test (dependsOnMethods = "Tc_03_View")
	public void Tc_04_Edit() {
		System.out.println("tc014");
	}
	@Test 
	public void Tc_05_Delete() {
		System.out.println("tc05");
	}
	@Test(dependsOnMethods = {"TC_01_create","Tc_03_View" })
	public void Tc_06_Logout() {
		System.out.println("tc06");
	}
		
}
