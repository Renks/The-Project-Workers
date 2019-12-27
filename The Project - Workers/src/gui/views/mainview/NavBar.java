package gui.views.mainview;

import gui.AlertBox;
import gui.CButton;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class NavBar extends HBox {

	public NavBar() {
		super();
		// Spacing between child nodes of HBox
		this.setSpacing(10);

		// This also aligns child nodes in center
		this.setAlignment(Pos.CENTER);
		this.setPrefHeight(50);
		this.setId("navBar");

		CButton btnFirst = new CButton("\u2039" + "\u2039");
		CButton btnPrevious = new CButton("\u25C4");
		CButton btnNext = new CButton("\u25BA");
		CButton btnLast = new CButton("\u203A" + "\u203A");
		
		btnFirst.setOnMouseReleased(e->{
			if(MainGrid.API.getFirstEntry()) {
				MainGrid.txtId.setText(MainGrid.API.getId()+"");
				MainGrid.txtName.setText(MainGrid.API.getName());
				MainGrid.txtAddress.setText(MainGrid.API.getAddress());
				MainGrid.txtCity.setText(MainGrid.API.getCity());
				MainGrid.txtHoursWorked.setText(MainGrid.API.getHoursWorked()+"");
				MainGrid.txtHourRate.setText(MainGrid.API.getHourlyRate()+"");
				MainGrid.txtWeeklyWage.setText(MainGrid.API.getWeeklyWage()+"");
			}else {
				AlertBox.alertError("Error", "Already on the first entry.");
			}
			
		});
		btnNext.setOnMouseReleased(e->{
			if(MainGrid.API.getNextEntry()) {
				MainGrid.txtId.setText(MainGrid.API.getId()+"");
				MainGrid.txtName.setText(MainGrid.API.getName());
				MainGrid.txtAddress.setText(MainGrid.API.getAddress());
				MainGrid.txtCity.setText(MainGrid.API.getCity());
				MainGrid.txtHoursWorked.setText(MainGrid.API.getHoursWorked()+"");
				MainGrid.txtHourRate.setText(MainGrid.API.getHourlyRate()+"");
				MainGrid.txtWeeklyWage.setText(MainGrid.API.getWeeklyWage()+"");
			}else {
				AlertBox.alertError("Error", "This is the last entry.");
			}
			
		});
		
		btnPrevious.setOnMouseReleased(e->{
			if(MainGrid.API.getPreviousEntry()) {
				MainGrid.txtId.setText(MainGrid.API.getId()+"");
				MainGrid.txtName.setText(MainGrid.API.getName());
				MainGrid.txtAddress.setText(MainGrid.API.getAddress());
				MainGrid.txtCity.setText(MainGrid.API.getCity());
				MainGrid.txtHoursWorked.setText(MainGrid.API.getHoursWorked()+"");
				MainGrid.txtHourRate.setText(MainGrid.API.getHourlyRate()+"");
				MainGrid.txtWeeklyWage.setText(MainGrid.API.getWeeklyWage()+"");
			}else {
				AlertBox.alertError("Error", "This is the first entry.");
			}
			
		});
		
		btnLast.setOnMouseReleased(e->{
			if(MainGrid.API.getLastEntry()) {
				MainGrid.txtId.setText(MainGrid.API.getId()+"");
				MainGrid.txtName.setText(MainGrid.API.getName());
				MainGrid.txtAddress.setText(MainGrid.API.getAddress());
				MainGrid.txtCity.setText(MainGrid.API.getCity());
				MainGrid.txtHoursWorked.setText(MainGrid.API.getHoursWorked()+"");
				MainGrid.txtHourRate.setText(MainGrid.API.getHourlyRate()+"");
				MainGrid.txtWeeklyWage.setText(MainGrid.API.getWeeklyWage()+"");
			}else {
				AlertBox.alertError("Error", "Already on the last entry.");
			}
			
		});
		
		this.getChildren().addAll(btnFirst, btnPrevious, btnNext, btnLast);
	}
}
