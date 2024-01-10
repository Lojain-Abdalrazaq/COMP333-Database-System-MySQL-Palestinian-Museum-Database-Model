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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;


public class ClothesController implements Initializable{
	
	
	@FXML
    private TextField SearchText;

    @FXML
    private TableView<Clothes> ClothesTable;

    @FXML
    private TableColumn<Clothes, String> DescriptionCol;

    @FXML
    private TableColumn<Clothes, String> clothes_idCol;

    @FXML
    private TableColumn<Clothes, String> colorCol;

    @FXML
    private TableColumn<Clothes, String> genderCol;

    @FXML
    private TableColumn<Clothes, String> item_idCol;

    @FXML
    private TableColumn<Clothes, String> sizeCol;
    
    @FXML
    private TableColumn<Clothes, String> Editc;
    
    @FXML
    private HBox border; 
    
    @FXML
    private Button refreshButton;
    


    
    ObservableList <Clothes> DataList;

    
    
    static Image img;
    static Image img2;
    
    String query=null;
    Connection connection = null;
    PreparedStatement ps=null;
    ResultSet result=null;
    Clothes c=null;
    
    // ObservableList intialization
    ObservableList <Clothes> cList= FXCollections.observableArrayList();
    
 
    @FXML
    void RefreshTable() {
    	 try {
   		  cList.clear();
   		  query="Select * From Clothes";
   		  ps=connection.prepareStatement(query);
				result= ps.executeQuery();
	            while(result.next()){
	            	cList.add(new Clothes(
	            			result.getInt("Item_id"), 
							result.getInt("Clothes_id"),
							result.getString("Clothes_Size"),
							result.getString("Clothes_Color"),
							result.getString("Gender"),
							result.getString("Clothes_Description")));
	            	
	            			ClothesTable.setItems(cList);
							
					
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
    	clothes_idCol.setCellValueFactory(new PropertyValueFactory<>("Clothes_id"));
    	sizeCol.setCellValueFactory(new PropertyValueFactory<>("Clothes_Size"));
    	colorCol.setCellValueFactory(new PropertyValueFactory<>("Clothes_Color"));
    	genderCol.setCellValueFactory(new PropertyValueFactory<>("Gender"));
    	DescriptionCol.setCellValueFactory(new PropertyValueFactory<>("Clothes_Description"));
    	
    	
    	Callback<TableColumn<Clothes, String>, TableCell<Clothes, String>> cellFoctory = (TableColumn<Clothes, String> param) -> {
            // make cell containing buttons
    		
            final TableCell<Clothes, String> cell = new TableCell<Clothes, String>() {
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
                 	  /*
                       deleteIcon.setStyle( 
                     		  "-fx-background-color: #D40032; "
                     		  
                        );
                        editIcon.setStyle(
                     		   "-fx-background-color: #00D100; "
                        );*/
                        
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            try {
                                c = ClothesTable.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `Clothes` WHERE Clothes_id ="+ (c.getClothes_id());
                                connection = DBconnect.getConnect();
                                ps = connection.prepareStatement(query);
                                ps.execute();
                                RefreshTable();
                                
                           } catch (SQLException ex) {
                            	Logger.getLogger(ClothesController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            

                        });  
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            c = ClothesTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("AddClothes.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(ClothesController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            AddClothesController addcController = loader.getController();
                            addcController.setUpdate(true);
                            addcController.setTextField(c.getItem_id(),c.getClothes_id(),c.getClothes_Size(),c.getClothes_Color(),c.getGender(),c.getClothes_Description());
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
    	 ClothesTable.setItems(cList);
  		

    }
	
    @FXML
    private void getAddView() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("AddClothes.fxml"));
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
    
    
    
    
	


