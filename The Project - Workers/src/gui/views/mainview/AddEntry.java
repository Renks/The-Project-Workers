package gui.views.mainview;

import gui.AlertBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddEntry {

	private static boolean success = false;
	private static int lastAddedEntryId;

	public static void add() {
		// Everytime it fires up we need to set success to false
		success = false;
		Stage addStage = new Stage();
		addStage.getIcons().add(new Image(gui.Main.class.getResource("imgs/newIcon.png").toString()));
		addStage.initModality(Modality.APPLICATION_MODAL);

		// GridPane
		GridPane addPane = new GridPane();
		addPane.setPrefSize(500, 500);
		addPane.setPadding(new Insets(15));
		addPane.setAlignment(Pos.CENTER);

		for (int i = 0; i < 6; i++) {
			RowConstraints rowCon = new RowConstraints();
			rowCon.setPercentHeight(10); // 10% of parent height
			addPane.getRowConstraints().add(rowCon);

		}
		// Two columns
		// 1st
		ColumnConstraints colCon1 = new ColumnConstraints();
		colCon1.setPercentWidth(25); // 25% of total width
		addPane.getColumnConstraints().add(colCon1);

		// 2nd
		ColumnConstraints colCon2 = new ColumnConstraints();
		colCon2.setPercentWidth(50); //
		addPane.getColumnConstraints().add(colCon2);

		Label lblName = new Label("Name:");
		Label lblAddress = new Label("Address:");
		Label lblCity = new Label("City:");
		Label lblHoursWorked = new Label("Hours worked:");
		Label lblHourRate = new Label("Hourly rate:");

		TextField txtName = new TextField();
		TextField txtAddress = new TextField();
		TextField txtCity = new TextField();
		TextField txtHoursWorked = new TextField();
		TextField txtHourRate = new TextField();

		// Two buttons for confirmation
		Button btnAdd = new Button("Add");
		Button btnCancel = new Button("Cancel");

		btnAdd.setOnAction(e -> {
			// Add the entry using worker class here
			if (txtName.getText().length() > 2 && txtAddress.getText().length() > 4 && txtCity.getText().length() > 2
					&& txtHoursWorked.getText().length() != 0 && txtHourRate.getText().length() != 0) {

				// MainGrid.API.add() returns id if successful otherwise -1
				if ((lastAddedEntryId= MainGrid.API.add(txtName.getText(), txtAddress.getText(), txtCity.getText(),
						txtHoursWorked.getText(), txtHourRate.getText()))!=-1) {
					setSuccess(true);
					// Close after adding entry
					addStage.close();
				}else {
					setSuccess(false);
					AlertBox.alertError("Error", "Could not add entry.");
				}

			}else {
				// If fields aren't properly filled.
				AlertBox.alertError("Error", "Please fill up all the fields.");
			}

		});
		btnCancel.setOnAction(e -> {
			// Close this stage
			addStage.close();
		});

		FlowPane btnHolder = new FlowPane(); // It will hold Add and Cancel buttons
		btnHolder.setHgap(5);
		btnHolder.setAlignment(Pos.CENTER);
		btnHolder.getChildren().addAll(btnAdd, btnCancel);

		GridPane.setColumnSpan(btnHolder, 2); // FlowPane btnHolder span's to two columns

		addPane.add(lblName, 0, 0);
		addPane.add(lblAddress, 0, 1);
		addPane.add(lblCity, 0, 2);
		addPane.add(lblHoursWorked, 0, 3);
		addPane.add(lblHourRate, 0, 4);

		addPane.add(txtName, 1, 0);
		addPane.add(txtAddress, 1, 1);
		addPane.add(txtCity, 1, 2);
		addPane.add(txtHoursWorked, 1, 3);
		addPane.add(txtHourRate, 1, 4);

		// Adding confirmation buttons to grid
		addPane.add(btnHolder, 0, 5);

		Scene addScene = new Scene(addPane);
		addStage.setScene(addScene);
		addStage.showAndWait();
	}

	private static void setSuccess(boolean val) {
		success = val;
	}

	public static boolean getSucces() {
		return success;
	}
	
	public static int getLastId() {
		return lastAddedEntryId;
	}
}
