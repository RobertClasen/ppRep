package pp.app;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestActivity {
	
	private static final String VALID_TITLE = "Design";
	private static final String VALID_DESCRIPTION = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. "
			+ "Aenean commodo ligula eget dolor. Aenean m";
	private static final LocalDate VALID_DATE = LocalDate.of(2020, Month.JANUARY, 1);
	private static final int VALID_EST_TIME = 20;
	
	private static final String INVALID_TITLE_TOO_SHORT = "D";
	private static final String INVALID_TITLE_TOO_LONG = "Desiiiiiiiiiiiiiiiiiiiiign";
	private static final String INVALID_DESCRIPTION = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. "
			+ "Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient "
			+ "montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. "
			+ "Nulla consequat massa quis enim. Donec.";
	private static final LocalDate INVALID_DATE = LocalDate.of(2006, Month.JANUARY, 1);
	private static final int INVALID_EST_TIME = 0;
	
	@Before
	public void setUp() throws RegistrationException {
		ppApp = new PpApp();
		project1 = new Project();
		activity1 = new Activity();
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none(); 
	
	@Test
	public void addActivityToProject() throws Exception {
		project1.addActivity(activity1);
		assertEquals(1, project1.getActivities().size());
	}
	
	@Test
	public void activity_setTitle() throws Exception {
		activity1.setTitle(VALID_TITLE);
		assertEquals(VALID_TITLE, activity1.getTitle());
	}
	
	@Test
	public void activity_setDescription() throws Exception {
		activity1.setDescription(VALID_DESCRIPTION);
		assertEquals(VALID_DESCRIPTION, activity1.getDescription());
	}
	
	@Test
	public void activity_tooShortTitle() throws Exception {
		thrown.expect(InputException.class);
		thrown.expectMessage("Invalid length.");
		activity.setTitle(INVALID_TITLE_TOO_SHORT);
	}
	
	@Test
	public void project_tooLongTitle() throws Exception {
		thrown.expect(InputException.class);
		thrown.expectMessage("Invalid length.");
		activity.setTitle(INVALID_TITLE_TOO_LONG);
	}
	
	
	@Test
	public void validActivityInput() {
		
		fail("Not yet implemented");
	}

}
