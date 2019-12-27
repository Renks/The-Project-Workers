package gui.views.searchview;

import java.util.ArrayList;

import gui.AlertBox;
import gui.ParentGrid;
import gui.views.mainview.MainGrid;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class SearchView extends VBox {

	private ArrayList<SearchResultLbl> resultsArray;
	private final VBox searchResult = new VBox();
	
	public SearchView() {
		super();
		// Setting height same as parent
		this.setPrefHeight(ParentGrid.PARENT_HEIGHT);
		this.setId("searchView");
		
		Label lblSearch = new Label("S E A R C H");
		lblSearch.setPrefWidth(300);
		lblSearch.setAlignment(Pos.CENTER);
		lblSearch.setId("lblSearch");
		
		FlowPane searchPane = new FlowPane();
		searchPane.setId("searchPane");
		searchPane.setAlignment(Pos.CENTER);
		searchPane.setPrefHeight(100);
		
		Label lblSearchBy = new Label("Search by:");
		lblSearchBy.setPrefWidth(105);
		lblSearchBy.setStyle("-fx-text-fill:#fff; -fx-font-weight:bold; -fx-font-size: 1.5em;");
		
		
		ChoiceBox<String> cb = new ChoiceBox<>();
		cb.setPrefWidth(105);
		cb.getItems().add("Id");
		cb.getItems().add("Name");
		cb.getItems().add("City");
		cb.getItems().add("Hours Worked");
		// Default value
		cb.setValue("Id");
		
		TextField searchBar = new TextField();
		searchBar.setFocusTraversable(false);
		searchBar.setPromptText("Search");
		searchBar.setPrefWidth(175);
		Button btnSearch = new Button("Go");
		
		btnSearch.setOnAction(e->{
			// Parse the search result
			String searchBy = cb.getValue();
			String searchTxt = searchBar.getText();
			//System.out.println("Search By:\""+searchBy+"\" SearchTxt:\""+searchTxt+"\"");
			// validating
			if(searchBy.equals("Id") || searchBy.equals("Name") || searchBy.equals("City") || searchBy.equals("Hours Worked")) {
				if(searchTxt.length()>0) {
					if(resultsArray != null) {
						if(resultsArray.size()>0) {
							for(int i=0; i<resultsArray.size(); i++) {
								resultsArray.remove(i);
							}
						}
					}
					resultsArray = MainGrid.API.search(searchBy, searchTxt);
					showResults();
				}else {
					AlertBox.alertError("Error", "Search query can not be empty.");
				}
			}else {
				// User has messed with searchBy field
				AlertBox.alertError("Error", "Invalid search by String provided.");
			}
		});
		
		
		searchPane.getChildren().addAll(lblSearchBy,cb,searchBar,btnSearch);
		
		
		searchResult.setPrefWidth(350);
		searchResult.setId("searchResult");
		searchResult.setAlignment(Pos.CENTER);
		
		ScrollPane searchResultScroller = new ScrollPane(searchResult);
		searchResultScroller.setFitToWidth(true);
		searchResultScroller.setId("searchResultScroller");
		
		
		/* FOR DEBUGGING ONLY
		  for(int i=0; i<26; i++) {
			Label lbl = new Label("Fredd Dust");
			lbl.getStyleClass().add("lblSearchResult");
			
			// We add labels using this
			searchResult.getChildren().add(lbl);
		}*/
		this.getChildren().addAll(lblSearch,searchPane,searchResultScroller);
	}
	
	private void showResults() {
		if(resultsArray.size()<=0) {
			// search query yielded empty array i.e NO RESULTS
			AlertBox.alertError("Error", "No results found.");
		}else {
			// Empty the search result first
			if(searchResult.getChildren().size()>0) {
				for(int i=0; i<searchResult.getChildren().size(); i++) {
					searchResult.getChildren().remove(i);
				}
			}
			
			for(int i=0; i<resultsArray.size(); i++) {
				searchResult.getChildren().add(resultsArray.get(i));
			}
		}
	}
}
