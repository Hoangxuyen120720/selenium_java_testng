package DependTc;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Depend {

	
	@BeforeClass
	public void beforeClass() {
		
	}
		
	@Test (groups = "pay")
	public void TC_01_() {
		System.out.println("tc01");
		
		
	}
		
	@Test (groups = "pay")
	public void Tc_02_Login() {
		System.out.println("tc02");
	}
		
	@Test (groups = "pay")
	public void Tc_03_Login() {
		System.out.println("tc03");
	}
	@Test (groups = "pay")
	public void Tc_04_Login() {
		System.out.println("tc014");
	}
	@Test (groups = "pay")
	public void Tc_05_Login() {
		System.out.println("tc05");
	}
	@Test (groups = "pay")
	public void Tc_06_Login() {
		System.out.println("tc06");
	}
		
}
