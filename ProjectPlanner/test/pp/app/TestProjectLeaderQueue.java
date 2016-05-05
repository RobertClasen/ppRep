package pp.app;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestProjectLeaderQueue {
	private PpApp ppApp;
	
	@Before
	public void setUp() throws RegistrationException {
		ppApp = new PpApp();
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void registerUsers_usersEnqueued_printQueue() {
		User user1 = makeUser("John", "Nielsen");		
		User user2 = makeUser("Andreas", "Ustrup");
		User user3 = makeUser("Ulla", "Brit");
		ppApp.registerUser(user1);
		ppApp.registerUser(user2);
		ppApp.registerUser(user3);

		assertEquals("joni, anus, ulbr, ", ppApp.getProjectLeaderQueue().toString());
	}
	
	@Test
	public void deregisterUser_userDenqueued_printQueue() {
		User user1 = makeUser("John", "Nielsen");		
		User user2 = makeUser("Andreas", "Ustrup");
		User user3 = makeUser("Ulla", "Brit");
		ppApp.registerUser(user1);
		ppApp.registerUser(user2);
		ppApp.registerUser(user3);

		ppApp.deregisterUser(user2);

		assertEquals("joni, ulbr, ", ppApp.getProjectLeaderQueue().toString());
	}

	/**
	 *  Helper method.
	 *  Creates a new User object. Sets the firstName and lastName fields as dictated
	 *  by the functional test tables. 
	 */
	private User makeUser(String firstName, String lastName) {
		User user = new User(ppApp);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		return user;
	}

}
