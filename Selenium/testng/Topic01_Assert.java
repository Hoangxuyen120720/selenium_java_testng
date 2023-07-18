package testng;

import java.util.Random;


public class Topic01_Assert {

	
	// Mong đợi 1 đk trả về đúng  true, kiểu dữ liệu boolean
	
	String fullname1 = "Automation testing";
	Assert.assertTrue(fullname.contains("Automation"));
	
	// Mong đợi 1 đk trả về sai  fail, kiểu dữ liệu boolean
	
	String fullname = "Automation testing";
	Assert.assertFalse(fullname.contains("manual"));
	
	// mong đợi 2 điều kiện bằng nhau expected result = actual result
	
	Assert.assertEquals(fullname,"Automation testing");
	
		
	
	
	
}
	
	
	
	
	
	
	
	
	
	
	

