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
import javafx.scene.text.Text;


public class AddCustomerController {

	  @FXML
	    private Button clear;

	    @FXML
	    private Text enterA;

	    @FXML
	    private TextField enterAf;

	    @FXML
	    private Text enterCid;

	    @FXML
	    private TextField enterCidf;

	    @FXML
	    private Text enterD;

	    @FXML
	    private DatePicker enterDf;

	    @FXML
	    private Text enterEid;

	    @FXML
	    private TextField enterEidf;

	    @FXML
	    private Text enterG;

	    @FXML
	    private TextField enterGf;

	    @FXML
	    private Text enterName;

	    @FXML
	    private TextField enterNamef;

	    @FXML
	    private Button save;

	
	    String query = null;
	    Connection connection = null;
	    ResultSet resultSet = null;
	    PreparedStatement preparedStatement;
	    NewCustomer c = null;
	    private boolean update;
	    int customerId;

	    String name,address,gender,bd;
	    int id,evid;
	    
	    public void initialize(URL url, ResourceBundle rb) {
	        // TODO
	    }
	    
	    
	    @FXML
	    void Clear() {
	    	enterEidf.setText(null);
	    	enterCidf.setText(null);
	        enterNamef.setText(null);
	        enterDf.getEditor().clear();
	        enterAf.setText(null);
	        enterGf.setText(null);
	        enterEidf.setText(null);

	    }

	    @FXML
	    void Save(ActionEvent event) {
	    	 connection = DBconnect.getConnect();
	         id = Integer.parseInt(enterCidf.getText());
	         name = enterNamef.getText();
	        		 //String.valueOf(birthFld.getValue());
	         //bd= enterDf.getValue().toString();
	         bd=enterDf.getPromptText();
	         address = enterAf.getText();
	         gender = enterGf.getText();
	         evid = Integer.parseInt(enterEidf.getText());


	         if (enterNamef.getText().isEmpty() ||enterDf.getValue()==null ||enterCidf.getText()==null  || enterAf.getText()==null || enterGf.getText().isEmpty()||enterEidf.getText()==null) {
	             Alert alert = new Alert(Alert.AlertType.ERROR);
	             alert.setHeaderText(null);
	             alert.setContentText("Please Fill All DATA");
	             alert.showAndWait();

	         } else {
	             getQuery();
	             insert();
	             Clear();

	         }
	    }

	    
	    private void insert() {

	        try {
	        	

	            preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setInt(1, Integer.parseInt(enterCidf.getText()));
	            preparedStatement.setString(2, enterNamef.getText());
	            preparedStatement.setString(3, enterDf.getValue().toString());
	            preparedStatement.setString(4, enterAf.getText());
	            preparedStatement.setString(5, enterGf.getText());
	            preparedStatement.setInt(6,Integer.parseInt( enterEidf.getText()));

	            preparedStatement.execute();

	        } catch (SQLException ex) {
	            Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
	        }

	    }

	  
	    private void getQuery() {

	        if (update == false) {
	            
	            query = "INSERT INTO `NewCustomer`( `Cid`, `Cname`, `birthdate`,`address`, `gender`,`E_id`) VALUES (?,?,?,?,?,?)";

	        }else{
	            query = "UPDATE `NewCustomer` SET "
	                    + "`Cid`=?,"
	                    + "`Cname`=?,"
	                    + "`birthdate`=?,"
	                    + "`address`=?,"
	                    + "`gender`=?,"
	                    + "`E_id`= ? WHERE Cid = '"+customerId+"'";
	        }

	    }
	    
	    void setTextField(int id, String name, String date, String address, String gender, int evid) {

	    	customerId= id;
	    	enterCidf.setText(Integer.toString(id));
            enterNamef.setText(name);
            enterDf.setPromptText(date);
            //enterDf.setPromptText("2020-02-02");
            enterAf.setText(address);
            enterGf.setText(gender);
            enterEidf.setText(Integer.toString(evid));


	    }

	    void setUpdate(boolean b) {
	        this.update = b;

	    }
	    
	   

}

