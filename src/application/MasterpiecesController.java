package application;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class MasterpiecesController implements Initializable {

	
    @FXML
    private TableView<Masterpieces> MasterpiecesTable;
    
    @FXML
    private TableColumn<Masterpieces, String> Artists_NameCol;

    @FXML
    private TableColumn<Masterpieces, String> Editc;

    @FXML
    private TableColumn<Masterpieces, String> Masterpieces_NameCol;

    @FXML
    private TableColumn<Masterpieces, String> Material_Col;

    @FXML
    private TableColumn<Masterpieces, String> Mp_idCol;

    @FXML
    private TableColumn<Masterpieces, String> WeightCol;

    @FXML
    private TableColumn<Masterpieces, String> item_idCol;
    
    static Image img;
    static Image img2;
    
    
    
    String query=null;
    Connection connection = null;
    PreparedStatement ps=null;
    ResultSet result=null;
    Masterpieces m=null;
    
    // ObservableList intialization
    ObservableList <Masterpieces> mList= FXCollections.observableArrayList();
    
    
    @FXML
    void RefreshTable() {
    	try {
    		  mList.clear();
     		  query="Select * From Masterpieces";
     		  ps=connection.prepareStatement(query);
  				result= ps.executeQuery();
  	            while(result.next()){
  	            	mList.add(new Masterpieces(
  	            			result.getInt("Item_id"), 
  							result.getInt("Mp_id"),
  							result.getInt("Wight"),
  							result.getString("Meterial"),
  							result.getString("Masterpieces_Name"),
  							result.getString("Artistes_Name")));
  	            	
  	            	 MasterpiecesTable.setItems(mList);
  							
  	            }
  	           // connection.close();
  	        }
  	        catch (SQLException e) {
  	            e.printStackTrace();
  	        }

    }
    private void loadDate() {
    	// loading the data
    	connection= DBconnect.getConnect();
    	RefreshTable();
    	item_idCol.setCellValueFactory(new PropertyValueFactory<>("Item_id"));
    	Mp_idCol.setCellValueFactory(new PropertyValueFactory<>("Mp_id"));
    	WeightCol.setCellValueFactory(new PropertyValueFactory<>("Wight"));
    	Material_Col.setCellValueFactory(new PropertyValueFactory<>("Meterial"));
    	Masterpieces_NameCol.setCellValueFactory(new PropertyValueFactory<>("Masterpieces_Name"));
    	Artists_NameCol.setCellValueFactory(new PropertyValueFactory<>("Artistes_Name"));
    	
    	
    	Callback<TableColumn<Masterpieces, String>, TableCell<Masterpieces, String>> cellFoctory = (TableColumn<Masterpieces, String> param) -> {
            // make cell containing buttons
    		
            final TableCell<Masterpieces, String> cell = new TableCell<Masterpieces, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                 	   Button deleteIcon = new Button();
                 	   Button editIcon = new Button();
                 	  deleteIcon.setPrefSize(15, 15);
                 	  editIcon.setPrefSize(15, 15);
                 	  
                 	 try {
                 		   
   						img = new Image(getClass().getResource("img/x-button.png").toURI().toString());
   						img2 = new Image(getClass().getResource("img/edit.png").toURI().toString());
   						  ImageView view = new ImageView(img);	
   						  ImageView view2 = new ImageView(img2);			
   					      view.setFitHeight(15);
   					      view2.setFitHeight(15);
   					      view.setPreserveRatio(true);	
   					      view2.setPreserveRatio(true);		
   						  deleteIcon.setGraphic(view);
   						  editIcon.setGraphic(view2);
   						
   					} catch (URISyntaxException e) {
   						// TODO Auto-generated catch block
   						e.printStackTrace();
   					}

                      /* deleteIcon.setStyle( 
                     		  "-fx-background-color: #D40032; "
                     		  
                        );
                        editIcon.setStyle(
                     		   "-fx-background-color: #00D100; "
                        );*/
                        
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            try {
                                m = MasterpiecesTable.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `Masterpieces` WHERE Mp_id ="+ (m.getMp_id());
                                connection = DBconnect.getConnect();
                                ps = connection.prepareStatement(query);
                                ps.execute();
                                RefreshTable();
                                
                           } catch (SQLException ex) {
                            	Logger.getLogger(MasterpiecesController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            

                        });  
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            m = MasterpiecesTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("AddMasterpieces.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(MasterpiecesController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            AddMasterpiecesController addcController = loader.getController();
                            addcController.setUpdate(true);
                            addcController.setTextField(m.getItem_id(),m.getMp_id(),m.getWight(),m.getMeterial(),m.getMasterpieces_Name(),m.getArtistes_Name());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                       
                        });
                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);
                        
                    } 
                }

                    };
                    return cell;
                 
    	};
    	
    	 Editc.setCellFactory(cellFoctory);
    	 MasterpiecesTable.setItems(mList);
    }
	
    @FXML
    private void getAddView() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("AddMasterpieces.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ClothesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    void BackButton(ActionEvent event) throws IOException {
    	Main m = new Main();
		m.changeScene("Bord.fxml");

    }
    
    public void initialize(URL arg0, ResourceBundle arg1) {
		
 		loadDate();
 	}
     



}
