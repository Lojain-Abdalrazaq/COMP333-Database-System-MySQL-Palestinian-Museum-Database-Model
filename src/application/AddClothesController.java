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
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;


public class AddClothesController {

    @FXML
    private TextField COLOR_FID;

    @FXML
    private TextField Clothes_FID;

    @FXML
    private Text Clothes_ID;

    @FXML
    private HBox Color;

    @FXML
    private TextField DESCRIPTION_FID;

    @FXML
    private Text Description;

    @FXML
    private TextField GENDER_FID;

    @FXML
    private Text Gender;

    @FXML
    private TextField Item_FID;

    @FXML
    private Text Item_ID;

    @FXML
    private Text SIZE;

    @FXML
    private TextField SIZE_FID;
    
    @FXML
    private Button save;
    
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Clothes c = null;
    private boolean update;
    int Clothes_IDDD;
    
    
    String Clo_Size,Clo_Color,Gen,Clo_Description;
    int Clo_id,Item_id;
    
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    void Delete(){
    	COLOR_FID.setText(null);
    	Clothes_FID.setText(null);
    	DESCRIPTION_FID.setText(null);
    	GENDER_FID.setText(null);
    	Item_FID.setText(null);
    	SIZE_FID.setText(null);

    }

    @FXML
    void Save(ActionEvent event) {
    	
    	 connection = DBconnect.getConnect();
    	 
    	 Item_id = Integer.parseInt(Item_FID.getText());
    	 Clo_id = Integer.parseInt(Clothes_FID.getText());
    	 Clo_Size=SIZE_FID.getText();
    	 Clo_Color=COLOR_FID.getText();
    	 Gen=GENDER_FID.getText();
    	 Clo_Description=DESCRIPTION_FID.getText();


         if (Item_FID.getText().isEmpty() || Clothes_FID.getText()==null ||SIZE_FID.getText()==null  || COLOR_FID.getText()==null || GENDER_FID.getText().isEmpty()||DESCRIPTION_FID.getText()==null) {
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText(null);
             alert.setContentText("Please Fill All DATA");
             alert.showAndWait();

         } else {
        	 System.out.println("Get query--");
             getQuery();
             System.out.println("insert--");
             insert();
             Delete();

         }
    }
     
    private void insert() {

        try {
        	
        	System.out.println("connection");
            preparedStatement = connection.prepareStatement(query);
            System.out.println("insert1");
            preparedStatement.setInt(1, Integer.parseInt(Item_FID.getText()));
            System.out.println("insert2-");
            preparedStatement.setInt(2, Integer.parseInt(Clothes_FID.getText()));
            System.out.println("insert3-");
            preparedStatement.setString(3, SIZE_FID.getText());
            System.out.println("insert4-");
            preparedStatement.setString(4, COLOR_FID.getText());
            System.out.println("insert5-");
            preparedStatement.setString(5, GENDER_FID.getText());
            System.out.println("insert6-");
            preparedStatement.setString(6, DESCRIPTION_FID.getText());
            System.out.println("excute--");
            preparedStatement.execute();

        } catch (SQLException ex) {
        	
            Logger.getLogger(AddClothesController.class.getName()).log(Level.SEVERE, null, ex);
            
        }

    }
    private void getQuery() {

        if (update == false) {
            
            query = "INSERT INTO `Clothes` (`Item_id`, `Clothes_id`, `Clothes_Size`,`Clothes_Color`, `Gender`,`Clothes_Description`) VALUES (?,?,?,?,?,?)";

        }else{
            query = "UPDATE `Clothes` SET "
                    + "`Item_id`=?,"
                    + "`Clothes_id`=?,"
                    + "`Clothes_Size`=?,"
                    + "`Clothes_Color`=?,"
                    + "`Gender`=?,"
                    + "`Clothes_Description`= ? WHERE Clothes_id = '"+Clothes_IDDD+"'";
        }

    }
    void setTextField(int Item_id, int Clo_id, String Clothes_Size, String Clothes_Color, String Gender, String Clothes_Description) {
    	
    	
    	
    	Clothes_IDDD=Clo_id;
    	
    	Item_FID.setText(Integer.toString(Item_id));
    	Clothes_FID.setText(Integer.toString(Clo_id));
    	SIZE_FID.setText(Clothes_Size);
    	COLOR_FID.setText(Clothes_Color);
    	GENDER_FID.setText(Gender);
    	DESCRIPTION_FID.setText(Clothes_Description); 	    
    	
    }
    
    void setUpdate(boolean b) {
        this.update = b;

    }

}
