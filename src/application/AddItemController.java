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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AddItemController {

	@FXML
	private TextField BoxA;

	@FXML
	private TextField BoxCID;

	@FXML
	private TextField BoxDes;

	@FXML
	private TextField BoxEID;

	@FXML
	private TextField BoxName;

	@FXML
	private TextField BoxP;

	@FXML
	private TextField BoxTID;

	@FXML
	private TextField BoxU;

	@FXML
	private Button clear;

	@FXML
	private Text enterA;

	@FXML
	private Text enterCid;

	@FXML
	private Text enterD;

	@FXML
	private Text enterEid;

	@FXML
	private Text enterEid1;

	@FXML
	private Text enterEid2;

	@FXML
	private Text enterG;

	@FXML
	private Text enterName;

	@FXML
	private Button save;

	String query = null;
	Connection connection = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement;
	Itemm it = null;
	private boolean update;
	int TId;

	String d, u, n, a;
	int id1, id2, id3;
	double p;

	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

	@FXML
	void Clear() {
		BoxA.setText(null);
		BoxCID.setText(null);
		BoxEID.setText(null);
		BoxTID.setText(null);
		BoxDes.setText(null);
		BoxName.setText(null);
		BoxP.setText(null);
		BoxU.setText(null);

	}

	@FXML
	void Save(ActionEvent event) {
		connection = DBconnect.getConnect();

		if (BoxA.getText().isEmpty() || BoxP.getText().isEmpty() || BoxU.getText().isEmpty()
				|| BoxDes.getText().isEmpty() || BoxTID.getText().isEmpty() || BoxName.getText().isEmpty()
				|| BoxEID.getText().isEmpty() || BoxTID.getText().isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Please Fill All DATA");
			alert.showAndWait();

		} else {

			id1 = Integer.parseInt(BoxTID.getText());
			id2 = Integer.parseInt(BoxCID.getText());
			id3 = Integer.parseInt(BoxEID.getText());
			d = BoxDes.getText();
			u = BoxU.getText();
			n = BoxName.getText();
			a = BoxA.getText();
			p = Double.parseDouble(BoxP.getText());
			getQuery();
			insert();
			Clear();

		}
	}

	private void insert() {

		try {

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Integer.parseInt(BoxTID.getText()));
			preparedStatement.setInt(2, Integer.parseInt(BoxCID.getText()));
			preparedStatement.setInt(3, Integer.parseInt(BoxEID.getText()));
			preparedStatement.setString(4, BoxDes.getText());
			preparedStatement.setString(5, BoxU.getText());
			preparedStatement.setString(6, BoxName.getText());
			preparedStatement.setString(7, BoxA.getText());
			preparedStatement.setDouble(8, Double.parseDouble(BoxP.getText()));

			preparedStatement.execute();

		} catch (SQLException ex) {
			Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	private void getQuery() {

		if (update == false) {

			query = "INSERT INTO `Itemss`( `Item_id`, `Cid`, `E_id`,`Item_Description`, `Item_useage`,`Item_name`,`Artistes_Name`,`Item_Price`) VALUES (?,?,?,?,?,?,?,?)";

		} else {
			query = "UPDATE `Itemss` SET " + "`Item_id`=?," + "`Cid`=?," + "`E_id`=?," + "`Item_Description`=?,"
					+ "`Item_useage`=?," + "`Item_name`=?," + "`Artistes_Name`=?," + "`Item_Price`= ? WHERE Item_id = '"
					+ TId + "'";
		}

	}

	void setTextField(int id, int cid, int eid, String de, String use, String name, String an, Double p) {

		TId = id;
		BoxTID.setText(Integer.toString(id));
		BoxCID.setText(Integer.toString(cid));
		BoxEID.setText(Integer.toString(eid));
		BoxDes.setText(de);
		BoxU.setText(use);
		BoxName.setText(name);
		BoxA.setText(an);
		BoxP.setText(Double.toString(p));

	}

	void setUpdate(boolean b) {
		this.update = b;

	}

}
