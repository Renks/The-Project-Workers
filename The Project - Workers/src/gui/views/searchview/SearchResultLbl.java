package gui.views.searchview;

import gui.views.mainview.MainGrid;
import javafx.scene.control.Label;

public class SearchResultLbl extends Label {

	private int position = -1;
	
	public SearchResultLbl(String text) {
		super(text);
		this.getStyleClass().add("lblSearchResult");
		this.setOnMouseClicked(e->{
			// If user clicked on this particular label
			MainGrid.API.getEntry(this.getPos());
			
			// Now we got the entry change txtFields
			MainGrid.txtId.setText(MainGrid.API.getId()+"");
			MainGrid.txtName.setText(MainGrid.API.getName());
			MainGrid.txtAddress.setText(MainGrid.API.getAddress());
			MainGrid.txtCity.setText(MainGrid.API.getCity());
			MainGrid.txtHoursWorked.setText(MainGrid.API.getHoursWorked()+"");
			MainGrid.txtHourRate.setText(MainGrid.API.getHourlyRate()+"");
			MainGrid.txtWeeklyWage.setText(MainGrid.API.getWeeklyWage()+"");
		});
	}
	
	public void setPos(int pos) {
		this.position = pos;
	}
	public int getPos() {
		return this.position;
	}
}
