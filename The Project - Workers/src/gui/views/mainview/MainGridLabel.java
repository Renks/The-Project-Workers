package gui.views.mainview;

import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class MainGridLabel extends Label {


	public MainGridLabel(String text) {
		super(text);
		this.getStyleClass().add("lblMainGrid");
		this.setPrefWidth(1000);
		this.setAlignment(Pos.CENTER);
	}

}
