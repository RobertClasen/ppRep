package pp.app;

import static java.time.temporal.ChronoUnit.MINUTES;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class User {
	private PpApp ppApp;
	private String firstName;
	private String lastName;
	private String userId;
	private LocalTime startWorkTime;
	protected List<Activity> activities = new ArrayList<>();
	protected List<Activity> assistanceActivities = new ArrayList<>();
	protected List<Absence> absenceTime= new ArrayList<>();
	protected List<WorkSession> workSession = new ArrayList<>();
	private Activity workingActivity;
	
	private final static int NAME_MAX_LENGTH = 15;
	private final static int NAME_MIN_LENGTH = 2;
	
	public User(PpApp ppApp) {
		this.ppApp = ppApp;
	}
	
	public void setFirstName(String firstName) {
		if (ppApp.getInputValidation().stringLength(firstName, NAME_MIN_LENGTH, NAME_MAX_LENGTH) &&
				ppApp.getInputValidation().legalCharacters(firstName)) {
			this.firstName = firstName;
		}
	}
	
	public void setLastName(String lastName) {
		if (ppApp.getInputValidation().stringLength(lastName, NAME_MIN_LENGTH, NAME_MAX_LENGTH) &&
				ppApp.getInputValidation().legalCharacters(lastName)) {
			this.lastName = lastName;
		}
	}
	
	public void startWork(Activity a) {
		this.startWorkTime = ppApp.getTime();
		this.workingActivity = a;
	}
	
	public void endWork() {
		long minutesWorked = MINUTES.between(this.startWorkTime, ppApp.getTime());
		if (minutesWorked >= 15L) {
			this.workingActivity.clockedTime += minutesWorked; 
			WorkSession thisWork = new WorkSession(ppApp.getDate(), this.workingActivity, minutesWorked);
			this.workSession.add(thisWork);
		}
	}
	
	public void editClockedTime(Activity a, int minutes) {
		this.workingActivity = a;
		this.workingActivity.clockedTime += minutes;
	}
	
	/**
	 * Getters and setters
	 */
	public String getFirstname() { return firstName; }
	public String getLastname() { return lastName; }
	public String getUserId() { return userId; }
	public void setUserId(String userId) { this.userId = userId; }

	public List<Activity> getActivities() {
		return this.activities;
	}

	public void seekAssistance(User u, Activity a) {
		u.assistanceActivities .add(a);
	}

	public void registerAbsence(Absence a) {
		this.absenceTime.add(a);
	}

	public List<Absence> getAbsence() {
		return this.absenceTime;
	}

	public boolean isAvailable(LocalDate date) {
		int count = 0;
		int absentWorkDays = 0;
		for (Activity a : this.activities){
			if (a.getStartDate().isAfter(date.minusMonths(1)) && a.getStartDate().isBefore(date.plusMonths(1))){
				count++;
			}
		}
		for (Absence ab : absenceTime) {
			//Hvis absence starter efter eller er sluttet før den pågældene periode, er denne absence ligegyldig.
			if(!ab.startDate.isAfter(date.plusMonths(1)) && !ab.endDate.isBefore(date.minusMonths(1))){
				
				//både start- of slutdato er inden for tidsperioden
				if(ab.startDate.isAfter(date.minusMonths(1)) && ab.endDate.isBefore(date.plusMonths(1))){
					absentWorkDays += ab.calcWorkDaysInTimePeriod(ab.startDate, ab.endDate);
				}
				//startdato inden for perioden, slutdato udenfor
				else if(ab.startDate.isAfter(date.minusMonths(1)) && ab.endDate.isAfter(date.plusMonths(1))){
					absentWorkDays += ab.calcWorkDaysInTimePeriod(ab.startDate, date.plusMonths(1L));
				}
				//startdato udenforperioden, slutdato indenfor
				else if(ab.startDate.isBefore(date.minusMonths(1)) && ab.endDate.isBefore(date.plusMonths(1))){
					absentWorkDays += ab.calcWorkDaysInTimePeriod(date.minusMonths(1), ab.endDate);					
				}else {absentWorkDays += ab.calcWorkDaysInTimePeriod(date.minusMonths(1), date.plusMonths(1));}
			}	
		}
		//count inkrimeres med det antal aktiviteter fraværet estimeret svarer til 
		count += absentWorkDays/4;
		
		if (count < 10){return true;} 
		else {return false;}
	}
	
	
}
