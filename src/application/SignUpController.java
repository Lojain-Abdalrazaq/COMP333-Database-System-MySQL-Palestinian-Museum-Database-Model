package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController {

	@FXML
	private TextField Eaddress, Eemail, Egender, name, Ephone;

	@FXML
	private PasswordField Epassword, passwordConfrim;

	@FXML
	private Label SignUpMessage, matchedPass;

	@FXML
	void BackButton(ActionEvent event) throws IOException {
		Main m = new Main();
		m.changeScene("Window1.fxml");

	}

	@FXML
	void SignUpButton(ActionEvent event) {
		VarifiyEnteredVlaues();
	}

	void VarifiyEnteredVlaues() {
		if (name.getText().isBlank() == false && Eemail.getText().isBlank() == false
				&& Eaddress.getText().isBlank() == false && Egender.getText().isBlank() == false
				&& Ephone.getText().isBlank() == false && Epassword.getText().isBlank() == false) {
			
			if (Epassword.getText().equals(passwordConfrim.getText())) {
				RegisterUser();
				matchedPass.setText("");
				SignUpMessage.setText("You Sucessfully Signed up!");

			} else {
				matchedPass.setText("Password Does not match !");
			}

		} else {
			SignUpMessage.setText("Review your Data !");
		}

	}

	void RegisterUser() {
		LoginDataBaseConnection connectNow = new LoginDataBaseConnection();
		Connection connectDB = connectNow.getConnection();
		String Ename = name.getText();
		String Email = Eemail.getText();
		String address = Eaddress.getText();
		String gender = Egender.getText();
		String EphoneNum = Ephone.getText();
		String password = Epassword.getText();

		String insertFields = "insert  into Employees(Ename,Email, address, gender,EphoneNum, password) values ('";
		String insertValues = Ename + "','" + Email + "','" + address + "','" + gender + "','" + EphoneNum + "','"
				+ password + "')";
		String insertToRegister = insertFields + insertValues;

		try {

			Statement stat = connectDB.createStatement();
			stat.executeUpdate(insertToRegister);
			SignUpMessage.setText("You Sucessfully Signed up!");

		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}

	}
}