package pp.app;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestAssignActivity extends UsersForTesting {
	private Activity activity1;
	private Project project1;
	
	@Before
	public void setUp() throws RegistrationException {
		activity1 = new Activity(ppApp);
		project1 = new Project(ppApp);
		ppApp.addProject(project1);
//		this.ppApp = new PpApp();
	}

	@Test
	public void correctName() {
		ppApp.registerUser(user26);
		assertEquals("Harry Potter", user26.getFirstname() + " " + user26.getLastname());
	}
	
	//Input data set: A
	@Test
	public void assignOneUserToActivity() throws Exception {
		project1.assignUserToActivity(user1, activity1);
		assertEquals("joni", activity1.searchUser("joni").getUserId());
	}
	
	//Input data set: B
	@Test
	public void assignFiveUsersToActivity() throws Exception {
		assignUsers(testUsers, activity1, 5);
		assertEquals(5, activity1.getUsers().size());
	}
	
	//Input data set: C
	@Test
	public void assignAllUsersToActivity() throws Exception {
		assignUsers(testUsers, activity1, testUsers.size());
		assertEquals(50, activity1.getUsers().size());
	}
	
	
	/**
	 *  Helper method.
	 *  Assigns a requested number of users ('fictional' users from the class UsersForTesting) to an activity
	 */
	
	private void assignUsers (List<User> users, Activity activity, int numberOfUsers) {
		for(int i = 0; i < numberOfUsers; i++){
			System.out.println(users.get(i));
			project1.assignUserToActivity(users.get(i), activity1);
		}
	}


}
