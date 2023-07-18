package groupTest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class User {

	
	@BeforeClass (alwaysRun = true) // luoon luon chay
	public void beforeClass() {
		System.out.println("before");
	}
		
	@Test (groups = {"user", "other"})
	public void TC_01_() {
		System.out.println("tc01");
		
		
	}
		
	@Test (groups = "user")
	public void Tc_02_Login() {
		System.out.println("tc02");
	}
		
	@Test (groups = "user")
	public void Tc_03_Login() {
		System.out.println("tc03");
	}
	@Test (groups = "user")
	public void Tc_04_Login() {
		System.out.println("tc014");
	}
	@Test (groups = "user")
	public void Tc_05_Login() {
		System.out.println("tc05");
	}
	@Test (groups = "user")
	public void Tc_06_Login() {
		System.out.println("tc06");
	}
		
	
	@AfterClass (groups = "user")
	public void affterClass() {
		System.out.println("before");
	}
}
