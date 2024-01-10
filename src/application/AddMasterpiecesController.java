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

public class AddMasterpiecesController {

    @FXML
    private TextField ArtistesName_FID;

    @FXML
    private Text Artistes_Name;

    @FXML
    private HBox Color;

    @FXML
    private TextField Item_FID;

    @FXML
    private Text Item_ID;

    @FXML
    private TextField Masterpieces_FID;

    @FXML
    private Text Masterpieces_ID;

    @FXML
    private Text Masterpieces_Name;

    @FXML
    private TextField Masterpieces_Name_FID;

    @FXML
    private Text Material;

    @FXML
    private TextField Material_FID;

    @FXML
    private Text Weight;

    @FXML
    private TextField Weight_FID;
    
    
    @FXML
    private Button save;
    
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Masterpieces m = null;
    private boolean update;
    int Master_IDDD;
    
    
    String M_Material,M_Name,Artist_Name;
    int Mp_id,Item_id,weight;
    
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    void Delete(){
    	
    	Masterpieces_FID.setText(null);
    	Masterpieces_Name_FID.setText(null);
    	Material_FID.setText(null);
    	Weight_FID.setText(null);
    	Item_FID.setText(null);
    	ArtistesName_FID.setText(null);

    }

    @FXML
    void Save(ActionEvent event) {
    	
    	 connection = DBconnect.getConnect();
    	 
    	 Item_id = Integer.parseInt(Item_FID.getText());
    	 Mp_id = Integer.parseInt(Masterpieces_FID.getText());
    	 weight = Integer.parseInt(Weight_FID.getText());
    	 M_Material=Material_FID.getText();
    	 M_Name=Masterpieces_Name_FID.getText();
    	 Artist_Name=ArtistesName_FID.getText();
    	

         if (Item_FID.getText().isEmpty() || Masterpieces_FID.getText()==null ||Weight_FID.getText()==null  || Material_FID.getText()==null || Masterpieces_Name_FID.getText().isEmpty() || ArtistesName_FID.getText()==null) {
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText(null);
             alert.setContentText("Please Fill All DATA");
             alert.showAndWait();

         } else {
        	 System.out.println("Get query--");
             getQuery();
             System.out.println("insert--");
             insert();
             System.out.println("delete--");
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
            preparedStatement.setInt(2, Integer.parseInt(Masterpieces_FID.getText()));
            System.out.println("insert3-");
            preparedStatement.setInt(3, Integer.parseInt(Weight_FID.getText()));
            System.out.println("insert4-");
            preparedStatement.setString(4, Material_FID.getText());
            System.out.println("insert5-");
            preparedStatement.setString(5, Masterpieces_Name_FID.getText());
            System.out.println("insert6-");
            preparedStatement.setString(6, ArtistesName_FID.getText());
            System.out.println("excute--");
            preparedStatement.execute();


        } catch (SQLException ex) {
        	
            Logger.getLogger(AddClothesController.class.getName()).log(Level.SEVERE, null, ex);
            
        }

    }
    private void getQuery() {

        if (update == false) {
            
            query = "INSERT INTO `Masterpieces` (`Item_id`, `Mp_id`, `Wight`,`Meterial`, `Masterpieces_Name`,`Artistes_Name`) VALUES (?,?,?,?,?,?)";

        }else{
            query = "UPDATE `Masterpieces` SET "
                    + "`Item_id`=?,"
                    + "`Mp_id`=?,"
                    + "`Wight`=?,"
                    + "`Meterial`=?,"
                    + "`Masterpieces_Name`=?,"
                    + "`Artistes_Name`= ? WHERE Mp_id = '"+Master_IDDD+"'";
        }

    }
    
    void setTextField(int Item_id,int Mp_id,int Wight,String Meterial,String Masterpieces_Name,String Artistes_Name) {
    	
    	
    	
    	Master_IDDD=Mp_id;
    	
    	Item_FID.setText(Integer.toString(Item_id));
    	Masterpieces_FID.setText(Integer.toString(Mp_id));
    	Weight_FID.setText(Integer.toString(Wight));
    	Material_FID.setText(Meterial);
    	Masterpieces_Name_FID.setText(Masterpieces_Name);
    	ArtistesName_FID.setText(Artistes_Name); 	 
    	
    }
    
    void setUpdate(boolean b) {
        this.update = b;

    }

}
