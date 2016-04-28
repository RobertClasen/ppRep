package pp.app;

public class User {
	private String firstname;
	private String lastname;
	private String userId;	
	private final static int NAME_MAX_LENGTH = 15;
	private final static int NAME_MIN_LENGTH = 2;
	
	public User(String firstname, String lastname) throws RegistrationException {		
		validateNames(firstname, lastname);
	}
	
	private void validateNames(String firstname, String lastname) throws RegistrationException {
		
		if(firstname.length() > NAME_MAX_LENGTH || lastname.length() > NAME_MAX_LENGTH) {
			throw new RegistrationException("Invalid name length.");
		}
		if(firstname.length() < NAME_MIN_LENGTH || lastname.length() < NAME_MIN_LENGTH) {
			throw new RegistrationException("Invalid name length.");
		}
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
