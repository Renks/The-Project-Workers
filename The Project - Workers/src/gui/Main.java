package gui;

import gui.views.mainview.MainView;
import gui.views.searchview.SearchView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		// Container - GridPane
		ParentGrid parent = new ParentGrid();
		// Left pane - VBox
		SearchView searchView = new SearchView();
		// Right pane - BorderPane
		MainView mainView = new MainView();
		
		parent.add(searchView, 0, 0);
		parent.add(mainView, 1, 0);
		
		Scene scene = new Scene(parent,ParentGrid.PARENT_WIDTH, ParentGrid.PARENT_HEIGHT);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		
		stage.getIcons().add(new javafx.scene.image.Image(getClass().getResource("imgs/icon.png").toString()));
		stage.setTitle("The Project - Workers Application");
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
