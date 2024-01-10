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



public class CustomerController implements Initializable{
	

		@FXML
	    private TableView<NewCustomer> customerTable;
		
	    @FXML
	    private TableColumn<NewCustomer,String> caddress;

	    @FXML
	    private TableColumn<NewCustomer,String> cdate;

	    @FXML
	    private TableColumn<NewCustomer,String> cgender;

	    @FXML
	    private TableColumn<NewCustomer,String> cid;

	    @FXML
	    private TableColumn<NewCustomer,String> cname;
	    
	    @FXML
	    private TableColumn<NewCustomer,String> eid;
	    
	    @FXML
	    private TableColumn<NewCustomer,String> Editc;
	    
	    static Image img;
	    static Image img2;
	    

	    @FXML
	    private Button refreshButton;
	    
	    @FXML
	    private HBox border; 


	    
	    String query=null;
	    Connection connection = null;
	    PreparedStatement ps=null;
	    ResultSet result=null;
	    NewCustomer c=null;
	   
	   
	    
	    ObservableList <NewCustomer> cList= FXCollections.observableArrayList();
	    
		
	    @FXML
	    public void RefreshCustomer() {

	    	  try {
	    		  cList.clear();
	    		  query="Select * From NewCustomer";
	    		  ps=connection.prepareStatement(query);
					result= ps.executeQuery();
		            while(result.next()){
		            	cList.add(new NewCustomer(
								result.getInt("Cid"),
								result.getString("Cname"),
								result.getString("birthdate"),
								result.getString("address"),
								result.getString("gender"),
								result.getInt("E_id"))	);
								customerTable.setItems(cList);
								
						
		            }
		           // connection.close();
		        }
		        catch (SQLException e) {
		            e.printStackTrace();
		        }
	    }
	 
	private void loadDate() {
		
		connection= DBconnect.getConnect();
		RefreshCustomer();
		caddress.setCellValueFactory(new PropertyValueFactory<>("address"));
		cdate.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
		cgender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		cid.setCellValueFactory(new PropertyValueFactory<>("Cid"));
		cname.setCellValueFactory(new PropertyValueFactory<>("Cname"));
		eid.setCellValueFactory(new PropertyValueFactory<>("E_id"));


		 //add cell of button edit 
        Callback<TableColumn<NewCustomer, String>, TableCell<NewCustomer, String>> cellFoctory = (TableColumn<NewCustomer, String> param) -> {
           // make cell containing buttons
           final TableCell<NewCustomer, String> cell = new TableCell<NewCustomer, String>() {
               @Override
               public void updateItem(String item, boolean empty) {
                   super.updateItem(item, empty);
                   //that cell created only on non-empty rows
                   if (empty) {
                       setGraphic(null);
                       setText(null);

                   } else {
                	   	
                	   Button deleteIcon = new Button();
                	   Button editIcon = new Button();
                	   deleteIcon.setPrefSize(20, 20);
                	   
                	   try {
                		   
						img = new Image(getClass().getResource("img/x-button.png").toURI().toString());
						img2 = new Image(getClass().getResource("img/edit.png").toURI().toString());
						  ImageView view = new ImageView(img);	
						  ImageView view2 = new ImageView(img2);			
					      view.setFitHeight(20);
					      view2.setFitHeight(20);
					      view.setPreserveRatio(true);	
					      view2.setPreserveRatio(true);		
						  deleteIcon.setGraphic(view);
						  editIcon.setGraphic(view2);
						
					} catch (URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
              
                       
                       deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                           
                           try {
                               c = customerTable.getSelectionModel().getSelectedItem();
                               query = "DELETE FROM `NewCustomer` WHERE Cid  ="+ (c.getCid());
                               connection = DBconnect.getConnect();
                               ps = connection.prepareStatement(query);
                               ps.execute();
                               RefreshCustomer();
                               
                           } catch (SQLException ex) {
                               Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
                           }
                           
                          

                         

                       });
                       editIcon.setOnMouseClicked((MouseEvent event) -> {
                           
                           c = customerTable.getSelectionModel().getSelectedItem();
                           FXMLLoader loader = new FXMLLoader ();
                           loader.setLocation(getClass().getResource("AddCustomer.fxml"));
                           try {
                               loader.load();
                           } catch (IOException ex) {
                               Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
                           }
                           
                           AddCustomerController addcController = loader.getController();
                           addcController.setUpdate(true);
                           addcController.setTextField(c.getCid(),c.getCname(),c.getBirthdate(),c.getAddress(),c.getGender(),c.getE_id());
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
       customerTable.setItems(cList);
	}
       

	
	
    @FXML
    private void getAddView() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("AddCustomer.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    public void addd() {
    	
    	  c = customerTable.getSelectionModel().getSelectedItem();
          FXMLLoader loader = new FXMLLoader ();
          loader.setLocation(getClass().getResource("AddCustomer.fxml"));
          try {
              loader.load();
          } catch (IOException ex) {
              Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
          }
          
          AddCustomerController addCustomerController = loader.getController();
          addCustomerController.setUpdate(true);
          addCustomerController.setTextField( c.getCid(),c.getCname(),c.getBirthdate(),c.getAddress(),c.getGender(),c.getE_id());
          Parent parent = loader.getRoot();
          Stage stage = new Stage();
          stage.setScene(new Scene(parent));
          stage.initStyle(StageStyle.UTILITY);
          stage.show();
          

          }
    

    @FXML
    void BackButton(ActionEvent event) throws IOException {
    	Main m = new Main();
		m.changeScene("Bord.fxml");

    }
    
    
    @FXML
    public void key() {
    	
    	 try {
             Parent parent = FXMLLoader.load(getClass().getResource("EventKey.fxml"));
             Scene scene = new Scene(parent);
             Stage stage = new Stage();
             stage.setScene(scene);
             stage.initStyle(StageStyle.UTILITY);
             stage.show();
         } catch (IOException ex) {
             Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
         }

 }
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		loadDate();
	}
}