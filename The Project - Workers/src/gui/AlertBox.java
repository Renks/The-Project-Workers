package gui;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AlertBox {

	public static boolean alertConfirm(String title, String msg) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setContentText(msg);
		alert.setHeaderText(null);

		// Get the window so that we can set the icon
		Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
		alertStage.getIcons().add(new Image(AlertBox.class.getResource("imgs/Confirm.png").toString()));

		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			return true; // If okay return true
		} else {
			return false;
		}

	}

	public static void alertInfo(String title, String msg) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setContentText(msg);
		alert.setHeaderText(null);

		// Get the window so that we can set the icon
		Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
		alertStage.getIcons().add(new Image(AlertBox.class.getResource("imgs/Info.png").toString()));

		alert.showAndWait();

	}

	public static void alertError(String title, String msg) {
		// Type = Error
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setContentText(msg);
		alert.setHeaderText(null);

		// Get the window so that we can set the icon
		Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
		alertStage.getIcons().add(new Image(AlertBox.class.getResource("imgs/Error.png").toString()));

		// Finally show and wait
		alert.showAndWait();

	}

}
