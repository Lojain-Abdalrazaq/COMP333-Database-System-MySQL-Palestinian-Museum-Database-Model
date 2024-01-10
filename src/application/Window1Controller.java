package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Window1Controller implements Initializable {

	@FXML
	private Button Enter_Button, Login_Button, Signup_Button, Exit_Button;
	@FXML
	private TextField username;
	@FXML
	private AnchorPane Ap;
	@FXML
	private PasswordField password;
	@FXML
	private Label message;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		/*Ap.setOnMousePressed(pressEvent -> {
			Ap.setOnMouseDragged(dragEvent -> {
				Main.stg.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
				Main.stg.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
			});
		});*/
	}

	@FXML
	void Enter_Button_Action(ActionEvent event) throws IOException {

		if (username.getText().isBlank() == false && password.getText().isBlank() == false) {
			validetedLogIn();
		} else {
			message.setText("Please enter username and password");
		}

	}

	@FXML
	void Login_Button_Action(ActionEvent event) {

	}

	@FXML
	void Signup_Button_Action(ActionEvent event) throws IOException {
		Main m = new Main(); 
		m.changeScene("SignUp.fxml");

	}

	@FXML /* exit from the window */
	void Exit_Button_Action(ActionEvent event) {
		Platform.exit();

	}

	/* for connecting the database */
	public void validetedLogIn() {
		LoginDataBaseConnection connectNow = new LoginDataBaseConnection();
		Connection connectDB = connectNow.getConnection();
		String verifyLogin = "Select count(1) from Employees WHERE Ename = '" + username.getText() + "'AND password ='"
				+ password.getText() + "'";

		try {

			Statement stat = connectDB.createStatement();
			ResultSet res = stat.executeQuery(verifyLogin);

			while (res.next()) {
				if (res.getInt(1) == 1) {
				//	message.setText("YOU ENTERED THE QUERY! ");
					Main m = new Main(); 
					m.changeScene("Bord.fxml");
					System.out.println("your connection is true!!!" );

				} else {
					message.setText("invalid login, Please try again! ");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
	}

}
