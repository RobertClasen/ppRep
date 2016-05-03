package pp.app;

import java.util.ArrayList;
import java.util.List;

public class PpApp {
	private InputValidation inputValidation = new InputValidation();
	private boolean isLoggedIn;
	private List<User> users = new ArrayList<>();
	private List<Project> projects = new ArrayList<>();

	public boolean logIn(String userId) {
		userId = userId.toLowerCase();
		if ("joni".equals(userId)) {
			isLoggedIn = true;
		}
		return isLoggedIn;
	}

	public void registerUser(User u) {
		UserId userId = new UserId(this, u);
		u.setUserId(userId.toString());
		users.add(u);
	}
	
	public void deregisterUser(User u) {		
		if(!users.contains(u)) {
			throw new RegistrationException("User is not registered.");
		}
		users.remove(u);
	}

	public void addProject(Project p) {
		if (projects.size() >= 50)
			throw new ProjectException("Number of projects reached upper limit.");
		
		p.setRunningNumber();
		projects.add(p);
	}
	
	
	/**
	 * Getters and setters. 
	 */
	public List<User> getUsers() { return users; }
	public List<Project> getProjects() { return projects; }
	public void setProjects(List<Project> projects) { this.projects = projects; }
	public InputValidation getInputValidation() { return inputValidation; }
	
}
