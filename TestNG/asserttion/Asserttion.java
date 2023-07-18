package asserttion;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Asserttion {

	
	
	@Test
	public void TC_1() {
		
		// boolean: isDisplay/ isEnable...
		// Assert.assertTrue/false: tham so nhan vao la boolean
		Assert.assertTrue(isEmailTextboxDisplay());
		
		// int/ String/ float...
		// Assert.asserEquals: mong doi va thuc te nhu nhau
		
		Assert.assertEquals(getSuccessMessage(), isEmailTextboxDisplay());
		
		Object studentNumber = null;
		Assert.assertNull(studentNumber);
		
		studentNumber = 15;
		Assert.assertNotNull(studentNumber);
		
	}
	
	
	public boolean isEmailTextboxDisplay() {
		
		// Action
		//
		
		return false;
		
		
	}
	
	public String getSuccessMessage() {
		return "Done";
	}
	
}
