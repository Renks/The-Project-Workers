package gui.views.mainview;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import workerAPI.Worker;

public class MainGrid extends GridPane {

	// Worker - for getting and setting the fields
	public static final Worker API = new Worker();
	
	// Static TexFields - to change their values from other classes
	public static final TextField txtId = new TextField();
	public static final TextField txtName = new TextField();
	public static final TextField txtAddress = new TextField();
	public static final TextField txtCity = new TextField();
	public static final TextField txtHoursWorked = new TextField();
	public static final TextField txtHourRate = new TextField();
	public static final TextField txtWeeklyWage = new TextField();
	
	/**
	 * 7x2 Grid<br />
	 * 7 - Rows<br />
	 * 2 - Columns
	 */
	public MainGrid() {
		super();
		this.setPadding(new Insets(15));
		this.setAlignment(Pos.CENTER);
		
		// Generating 7 rows for the grid
		for (int i = 0; i < 7; i++) {
			RowConstraints rowCon = new RowConstraints();
			rowCon.setPercentHeight(10); // 10% of parent height
			this.getRowConstraints().add(rowCon);

		}
		
		// 1st Column
		ColumnConstraints colCon1 = new ColumnConstraints();
		colCon1.setPercentWidth(25); // 25% of total width
		this.getColumnConstraints().add(colCon1);
		
		// 2nd Column
		ColumnConstraints colCon2 = new ColumnConstraints();
		colCon2.setPercentWidth(50); //
		this.getColumnConstraints().add(colCon2);
		
		// Creating Custom Labels with 'lblMainGrid' as default CSS class
		MainGridLabel lblId = new MainGridLabel("WorkerID:");
		MainGridLabel lblName = new MainGridLabel("Name:");
		MainGridLabel lblAddress = new MainGridLabel("Address:");
		MainGridLabel lblCity = new MainGridLabel("City:");
		MainGridLabel lblHoursWorked = new MainGridLabel("Hours worked:");
		MainGridLabel lblHourRate = new MainGridLabel("Hourly rate:");
		MainGridLabel lblWeeklyWage = new MainGridLabel("Weekly wage:");
		
		// Setting initial TextField values from API
		txtId.setText(API.getId()+"");
		txtName.setText(API.getName());
		txtAddress.setText(API.getAddress());
		txtCity.setText(API.getCity());
		txtHoursWorked.setText(API.getHoursWorked()+"");
		txtHourRate.setText(API.getHourlyRate()+"");
		txtWeeklyWage.setText(API.getWeeklyWage()+"");
		
		// Adding all labels to grid
		this.add(lblId, 0, 0);
		this.add(lblName, 0, 1);
		this.add(lblAddress, 0, 2);
		this.add(lblCity, 0, 3);
		this.add(lblHoursWorked, 0, 4);
		this.add(lblHourRate, 0, 5);
		this.add(lblWeeklyWage, 0, 6);
		
		// Adding all TextFields to grid
		this.add(txtId, 1, 0);
		this.add(txtName, 1, 1);
		this.add(txtAddress, 1, 2);
		this.add(txtCity, 1, 3);
		this.add(txtHoursWorked, 1, 4);
		this.add(txtHourRate, 1, 5);
		this.add(txtWeeklyWage, 1, 6);
		
	}

}
