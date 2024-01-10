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
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class Event_sController implements Initializable {

	@FXML
	private TableColumn<Event_s, String> Editc;

	@FXML
	private HBox border;

	@FXML
	private TableColumn<Event_s, String> cdate;

	@FXML
	private TableColumn<Event_s, String> ceid;

	@FXML
	private Circle circle;

	@FXML
	private TableColumn<Event_s, String> cloc;

	@FXML
	private TableView<Event_s> eventTable;

	@FXML
	private Button refreshButton;
    static Image img;
    static Image img2;
	String query = null;
	Connection connection = null;
	PreparedStatement ps = null;
	ResultSet result = null;
	Event_s e = null;

	ObservableList<Event_s> eList = FXCollections.observableArrayList();

	@FXML
	public void RefreshCustomer() {

		try {
			eList.clear();
			query = "Select * From Event_s";
			ps = connection.prepareStatement(query);
			result = ps.executeQuery();
			while (result.next()) {
				eList.add(new Event_s(result.getInt("E_id"), result.getString("Location"), result.getString("Dates")));
				eventTable.setItems(eList);

			}
			// connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void loadDate() {

		connection = DBconnect.getConnect();
		RefreshCustomer();
		cloc.setCellValueFactory(new PropertyValueFactory<>("Location"));
		cdate.setCellValueFactory(new PropertyValueFactory<>("Dates"));
		ceid.setCellValueFactory(new PropertyValueFactory<>("E_id"));

		// add cell of button edit
		Callback<TableColumn<Event_s, String>, TableCell<Event_s, String>> cellFoctory = (
				TableColumn<Event_s, String> param) -> {
			// make cell containing buttons
			final TableCell<Event_s, String> cell = new TableCell<Event_s, String>() {
				@Override
				public void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					// that cell created only on non-empty rows
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

						deleteIcon.setOnMouseClicked((MouseEvent event) -> {

							try {
								e = eventTable.getSelectionModel().getSelectedItem();
								query = "DELETE FROM `Event_s` WHERE E_id  =" + (e.getE_id());
								connection = DBconnect.getConnect();
								ps = connection.prepareStatement(query);
								ps.execute();
								RefreshCustomer();

							} catch (SQLException ex) {
								Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
							}

						});
						editIcon.setOnMouseClicked((MouseEvent event) -> {

							e = eventTable.getSelectionModel().getSelectedItem();
							FXMLLoader loader = new FXMLLoader();
							loader.setLocation(getClass().getResource("AddEvent_s.fxml"));
							try {
								loader.load();
							} catch (IOException ex) {
								Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
							}

							AddEvent_sController addeController = loader.getController();
							addeController.setUpdate(true);
							addeController.setTextField(e.getE_id(), e.getLocation(), e.getDates());
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
		eventTable.setItems(eList);
	}

	@FXML
	private void getAddView() {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("AddEvent_s.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initStyle(StageStyle.UTILITY);
			stage.show();
		} catch (IOException ex) {
			Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
		}

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
