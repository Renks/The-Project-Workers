package gui;

import javafx.scene.control.Button;

/**
 * <b>CButton = C</b>ustom <b>Button</b><br/>
 * <br/><b>FEATURES:</b>
 * <ul><li>It provides CSS class for buttons by default:
 * <dd>CSS Class 'titleBtn' if you use {@link #CButton(String text, String... classes)}</dd>
 * <dd>CSS Class 'navBtn' if you use {@link #CButton(String text)}</dd>
 * </li>
 * 
 * <li>It Glows when hovered.</li>
 * </ul>
 * <b>USAGE:</b>
 * <dd><b>For title Bar: </b>Just provide the "button text" followed by "CSS classes(separated by comma)" in the Constructor.</dd>
 * <dd><b>For Navigation Bar: </b>Just provide the "button text" only in the Constructor.</dd>
 * @author Raman
 */
public class CButton extends Button {
	private final String DEFAULT_CSS_CLASS = "titleBtn";
	private final javafx.scene.effect.Glow glow = new javafx.scene.effect.Glow();
	private int defaultWidth = 45;
	private int defaultHeight = 45;
	private javafx.scene.effect.DropShadow dropShadow = new javafx.scene.effect.DropShadow();
	
	/**
	 * Use this constructor for title bar buttons only
	 * @param text : Button text(Usually empty)
	 * @param classes : User specified CSS Classes separated by comma
	 */
	public CButton(String text, String... classes) {
		super(text);
		loadSettings1(classes);
	}
	
	/**
	 * Use this constructor for navigation buttons only.
	 * @param text : Button text
	 */
	public CButton(String text) {
		super(text);
		loadSettings2();
	}
	
	/**
	 * This method adds the default settings
	 * @param classes : User specified CSS class(es)
	 */
	private void loadSettings1(String...classes) {
		
		// Adding default and user provided css classes
		super.getStyleClass().add(DEFAULT_CSS_CLASS);
		super.getStyleClass().addAll(classes);
		
		
		// Adding hover effect
		super.setOnMouseEntered(e->{
			super.setEffect(glow);
		});
		super.setOnMouseExited(e->{
			super.setEffect(null);
		});
	}
	
	/**
	 * This method adds the settings 2, which is usually for navigation buttons
	 */
	private void loadSettings2() {
		super.getStyleClass().add("navBtn");	
		super.setPrefSize(defaultWidth, defaultHeight);
		dropShadow.setBlurType(javafx.scene.effect.BlurType.GAUSSIAN);
		dropShadow.setColor(javafx.scene.paint.Color.ROYALBLUE);
		dropShadow.setOffsetX(1);
		dropShadow.setOffsetY(1.5);
		super.setFocusTraversable(false);
		super.setEffect(dropShadow);
	}
	

}
