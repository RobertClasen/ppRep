package pp.app;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestLogin {
	private static final String VALID_ID = "joni";
	private static final String VALID_ID_MIXED_CASE = "Joni";
	private static final String INVALID_ID_UNKNOWN = "jona";
	
	PpApp ppApp;	

	@Before
	public void setUp() {
		ppApp = new PpApp();
	}

	@Test
	public void validUserLogin() {
		assertTrue(ppApp.logIn(VALID_ID));
	}
	
	@Test
	public void validUserLogin_CaseSensitivity() {
		assertTrue(ppApp.logIn(VALID_ID_MIXED_CASE));
	}
	
	@Test
	public void invalidUserLogin_UnknownId() {
		assertFalse(ppApp.logIn(INVALID_ID_UNKNOWN));
	}	
	
	

}
