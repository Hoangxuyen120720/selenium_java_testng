package dataDriven;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class DataDrivenTesting {
	
	
  @Test(dataProvider = "user")
  public void TC_01(String firstParam, String secondParam) {
	  System.out.println(firstParam);
	  System.out.println(secondParam);
	  
  }
  @Test(dataProvider = "admin")
  public void TC_02(String firstParam, String secondParam) {
	  System.out.println(firstParam);
	  System.out.println(secondParam);
	  
  }

  
  
  @DataProvider (name = "user")
  public Object[][] getUserData() {
    return new Object[][] {
      new Object[] { "name", "automation FC" },
      new Object[] { "address", "hcm" },
      new Object[] { "city", "Le Lai" },
      new Object[] { "username", "afc" },
      new Object[] { "password", "123456" },
      
    };
  }
  
  
  @DataProvider (name = "admin")
  public Object[][] getAdminData() {
	  return new Object[][] {
		  new Object[] { "name", "automation FC" },
		  new Object[] { "address", "hcm" },
		  new Object[] { "city", "Le Lai" },
		  new Object[] { "username", "afc" },
		  new Object[] { "password", "123456" },
		  
	  };
  }
  
  
  
  
  
  
  
}
