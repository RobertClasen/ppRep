package pp.ui;

import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import pp.app.PpApp;

/**
 * @author Kristian
 */

public class View extends BorderPane {
	private PpApp ppApp;
	private Controller controller;
	private Effect effect;

	private ImageView imageView;
	private Image logo;
	private ScrollPane scrollPane;
	private Screen text;
	private InputField inputField;

	public View(PpApp ppApp) {
		this.ppApp = ppApp;
		logo = new Image("pp/ui/Logo.png");
		effect = new DropShadow(10, 2, 2, Color.BLACK);
		imageView = new ImageView(logo);
		setId("root");
		
		createNodes();
		setupNodes();
		arrangeNodes();
		
		controller = new Controller_start(this);
	}
	
	private void createNodes() {
		scrollPane = new ScrollPane();
		text = new Screen(this);
		inputField = new InputField(this);
	}
	
	private void setupNodes() {
		text.textProperty().addListener((observable) -> {
			scrollPane.setVvalue(1.0);
			inputField.clear();
		});
	}
	
	private void arrangeNodes() {
		setTop(imageView);
		setCenter(scrollPane);
		setBottom(inputField);
		scrollPane.setContent(text);
	}

	public void setController(Controller newController) { controller = newController; }
	
	public Screen getScreen() { return text; }
	public ScrollPane getScrollPane() {	return scrollPane; }
	public PpApp getPpApp() { return ppApp; }
	public Controller getController(){ return controller; }
	
}
