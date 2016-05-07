package pp.ui;

import javafx.application.Platform;

public class Controller_start extends Controller {

	public Controller_start(View view) {
		super(view);
		String text = "0) Login" + "\n" +
					  "1) Exit" + "\n" +
					  "\n";
		view.getScreen().appendText(text);
	}
	
	@Override
	public void processInput(String input) {
		if ("0".equals(input)) {
			view.setController(new Controller_login(view));
		} else if ("1".equals(input)) {
			Platform.exit();
		} else {
			view.getScreen().appendText("Lorem ipsum dolor sit amet..." + "\n");;
		}
	}

}
