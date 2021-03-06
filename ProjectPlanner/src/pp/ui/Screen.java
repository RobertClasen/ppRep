package pp.ui;

import javafx.scene.text.Text;

/**
 * @author Marco
 */

public class Screen extends Text {
	protected View view;
	protected String text;
	
	public Screen(View view) {
		setId("screen");
		this.view = view;
		text = "";
	}
	
	public void appendText(String s) {
		text += "\n" + s + "\n";
		setText(text);
	}
	
	public void invalidInput() {
		text += "\n" + "Invalid input." + "\n";
		setText(text);
	}
}
