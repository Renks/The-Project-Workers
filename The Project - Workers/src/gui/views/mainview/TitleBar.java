package gui.views.mainview;

import gui.AlertBox;
import gui.CButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

public class TitleBar extends FlowPane {

	public TitleBar() {
		super();
		this.setHgap(5);
		this.setPadding(new Insets(5));
		this.setId("titleBar");

		CButton btnNew = new CButton("", "titleBtnNew");
		CButton btnUpdate = new CButton("", "titleBtnUpdate");
		CButton btnDelete = new CButton("", "titleBtnDelete");

		Label lblInfo = new Label(); // This is where the information regarding update and delete will be shown.
		lblInfo.setAlignment(Pos.CENTER);
		lblInfo.setStyle("-fx-font-size:1.2em; -fx-text-fill:red; -fx-font-weight:bold;");

		btnNew.setOnMouseReleased(e -> {
			AddEntry.add();
			if (AddEntry.getSucces()) {
				lblInfo.setText("Entry Added: Where WorkerId=" + AddEntry.getLastId());
			}
		});
		btnUpdate.setOnMouseReleased(e -> {
			boolean result = AlertBox.alertConfirm("Update - Overwrite",
					"Updating will overwrite this entry. Are you sure you want to continue?");
			if (result) {
				// Validating if fields are not empty
				if (MainGrid.txtId.getText().length() > 2 && MainGrid.txtName.getText().length() > 2
						&& MainGrid.txtAddress.getText().length() > 4 && MainGrid.txtCity.getText().length() > 2
						&& MainGrid.txtHoursWorked.getText().length() != 0
						&& MainGrid.txtHourRate.getText().length() != 0) {
					// Calling update method
					MainGrid.API.updateDb(MainGrid.txtId.getText(), MainGrid.txtName.getText(),
							MainGrid.txtAddress.getText(), MainGrid.txtCity.getText(),
							MainGrid.txtHoursWorked.getText(), MainGrid.txtHourRate.getText());
					lblInfo.setText("Entry Updated");
				} else {
					// If fields are not entered correctly
					AlertBox.alertError("Error - Fields are not filled correctly.",
							"Please input all fields correctly and then try again.\nMake sure fields aren't empty.");
				}
			}
		});

		btnDelete.setOnMouseReleased(e -> {
			boolean result = AlertBox.alertConfirm("Delete", "Are you sure you want to delete this entry?");
			if (result) {
				MainGrid.API.deleteDb();
				lblInfo.setText("Entry Deleted");
				MainGrid.API.getLastEntry();
				MainGrid.txtId.setText(MainGrid.API.getId()+"");
				MainGrid.txtName.setText(MainGrid.API.getName());
				MainGrid.txtAddress.setText(MainGrid.API.getAddress());
				MainGrid.txtCity.setText(MainGrid.API.getCity());
				MainGrid.txtHoursWorked.setText(MainGrid.API.getHoursWorked()+"");
				MainGrid.txtHourRate.setText(MainGrid.API.getHourlyRate()+"");
				MainGrid.txtWeeklyWage.setText(MainGrid.API.getWeeklyWage()+"");
			}
		});

		this.getChildren().addAll(btnNew, btnUpdate, btnDelete, lblInfo);
	}

}
