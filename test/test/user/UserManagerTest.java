package test.user;

import static org.junit.Assert.*;

import org.junit.Test;
import main.user.UserManager;

public class UserManagerTest {
	
	/******************************/
	/*      validUsername()       */
	/******************************/
	@Test
	public void validUsernameTestNull(){
		UserManager um = new UserManager();
		boolean valid = um.validUsername(null);
		assertEquals(valid, false);
	}
	
	@Test
	public void validUsernameTestEmpty(){
		UserManager um = new UserManager();
		boolean valid = um.validUsername("");
		assertEquals(valid, false);
	}
	
	@Test
	public void validUsernameTestSingleCharacter(){
		UserManager um = new UserManager();
		boolean valid = um.validUsername("a");
		assertEquals(valid, true);
	}
	
	/******************************/
	/*        validCount()        */
	/******************************/
	@Test
	public void validCountTestNull(){
		UserManager um = new UserManager();
		boolean valid = um.validCount(null);
		assertEquals(valid, false);
	}
	
	@Test
	public void validCountTestEmpty(){
		UserManager um = new UserManager();
		boolean valid = um.validCount("");
		assertEquals(valid, false);
	}
	
	@Test
	public void validCountTestNegative(){
		UserManager um = new UserManager();
		boolean valid = um.validCount("-1");
		assertEquals(valid, false);
	}
	
	@Test
	public void validCountTestGreater(){
		UserManager um = new UserManager();
		boolean valid = um.validCount("3");
		assertEquals(valid, false);
	}
	
	@Test
	public void validCountTestZero(){
		UserManager um = new UserManager();
		boolean valid = um.validCount("0");
		assertEquals(valid, true);
	}
	
	@Test
	public void validCountTestOne(){
		UserManager um = new UserManager();
		boolean valid = um.validCount("1");
		assertEquals(valid, true);
	}
	
	@Test
	public void validCountTestTwo(){
		UserManager um = new UserManager();
		boolean valid = um.validCount("2");
		assertEquals(valid, true);
	}

}
