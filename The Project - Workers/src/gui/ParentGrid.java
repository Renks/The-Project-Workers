package gui;

import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * 1x2 Parent GridPane
 * 1st Column for Search bar Pane
 * 2nd Column for Main view Pane
 * @author Raman
 *
 */
public class ParentGrid extends GridPane {
	public static final int PARENT_WIDTH = 900;
	public static final int PARENT_HEIGHT = 600;
	
	public ParentGrid() {
		super();
		this.setAlignment(Pos.CENTER);
		this.setId("root");
		this.setMinSize(PARENT_WIDTH, PARENT_HEIGHT);
		// Parent GridPane would have 1 row and 2 columns
		// 1st column for search pane
		// 2nd for main view
		RowConstraints rowCon = new RowConstraints();
		rowCon.setPercentHeight(100); // Covers the 100% height
		// Adding row to parent
		this.getRowConstraints().add(rowCon);
		// Adding 2 columns to parent
		// Search column should cover 25% of total width
		// Main column should cover 75% of total width
		ColumnConstraints searchColCon = new ColumnConstraints();
		// First for search bar
		searchColCon.setPercentWidth(25);
		// Adding it
		this.getColumnConstraints().add(searchColCon);
		//Now for main view
		ColumnConstraints mainColCon = new ColumnConstraints();
		mainColCon.setPercentWidth(75);
		this.getColumnConstraints().add(mainColCon);
	}

}
