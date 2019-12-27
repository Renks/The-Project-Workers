package gui.views.mainview;

import gui.ParentGrid;
import javafx.scene.layout.BorderPane;

public class MainView extends BorderPane {

	public MainView() {
		super();
		this.setPrefHeight(ParentGrid.PARENT_HEIGHT);
		this.setId("mainView");
		
		// Top View using FlowPane
		TitleBar titleBar = new TitleBar();

		// Bottom view using HBox
		NavBar navBar = new NavBar();

		// Center View using GridPane
		MainGrid mg = new MainGrid();
		
		this.setTop(titleBar);
		this.setCenter(mg);
		this.setBottom(navBar);
	}

}
