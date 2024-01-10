package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AddEvent_sController {

	@FXML
	private DatePicker Ed;

	@FXML
	private TextField Ev;

	@FXML
	private TextField Loc;

	@FXML
	private Button clear;

	@FXML
	private Button save;

	String query = null;
	Connection connection = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement;
	Event_s e = null;
	private boolean update;
	int Eid;

	String d, l;
	int id;

	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

	@FXML
	void Clear() {
		Ed.setPromptText(null);
		Ev.setText(null);
		Loc.setText(null);

	}

	@FXML
	void Save(ActionEvent event) {
		connection = DBconnect.getConnect();

		if (Ed.getValue() == null || Ev.getText().isEmpty() || Loc.getText().isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Please Fill All DATA");
			alert.showAndWait();

		} else {
			id = Integer.parseInt(Ev.getText());
			l = Loc.getText();
			d = Ed.getPromptText();

			getQuery();
			insert();
			Clear();

		}
	}

	private void insert() {

		try {

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(Ev.getText()));
			preparedStatement.setString(2, Loc.getText());
			preparedStatement.setString(3, Ed.getValue().toString());
			preparedStatement.execute();

		} catch (SQLException ex) {
			Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	private void getQuery() {

		if (update == false) {

			query = "INSERT INTO `Event_s`( `E_id`, `Location`, `Dates`) VALUES (?,?,?)";

		} else {
			query = "UPDATE `Event_s` SET " + "`E_id`=?," + "`Location`=?," + "`Dates`= ? WHERE E_id = '" + Eid + "'";
		}

	}

	void setTextField(int id, String Loca, String date) {

		Eid = id;
		Ev.setText(Integer.toString(id));
		Loc.setText(Loca);
		Ed.setPromptText(date);

	}

	void setUpdate(boolean b) {
		this.update = b;

	}

}
