package pp.app;

import java.util.ArrayList;
import java.util.List;

public class PpApp {
	private boolean isLoggedIn;
	private List<User> users = new ArrayList<>();

	public boolean logIn(String userId) {
		userId = userId.toLowerCase();
		if ("kris".equals(userId)) {
			isLoggedIn = true;
		}
		return isLoggedIn;
	}

	
	public void registerUser(User u) throws RegistrationException {
		String userId = validUserId(u);
		u.setUserId(userId);
		users.add(u);
		
//		for (User user : users) {
//			if(u.getUserId().equals(user.getUserId())){
//				throw new RegistrationException("UserId already in use", "Register user");
//			}
//		}
		
	}

	/*
	 * Generates a userID based on the users names. If it's not unique: generate new userID.
	 */
	private String validUserId(User u) {
		String userId = "";
		boolean uniqueIdFound = false;
		int i = 0;
		int j = 2;
		
		while(!uniqueIdFound){
			userId = generateUserId(u, i, j);
			i++;
			j++;
			if (!checkForDuplicates(userId)){
				uniqueIdFound = true;
			}
		}		
		
		return userId;
		//Checks for dublicate userIDs. If such is found, generates a new userID and runs the loop again.
		
	}
	
	private String generateUserId(User u, int i, int j) {
		return (u.getFirstname().substring(0,2) + u.getLastname().substring(i,j)).toLowerCase();
	}

	private boolean checkForDuplicates(String userId){
		for (User user : users) {
				if (user.getUserId().equals(userId)) {
					return  true;
				}
			}
		return false;
	}

	public List<User> getUsers() {
		return users;
	}

	public void deregisterUser(User u) throws RegistrationException {		
		if(users.contains(u)) {			
			users.remove(u);
		} else {
			throw new RegistrationException("UserId does not exist", "Deregister user");
		}
	}

}
