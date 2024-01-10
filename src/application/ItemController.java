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

public class ItemController implements Initializable {

	@FXML
	private TableView<Itemm> ItemTable;

	@FXML
	private TableColumn<Itemm, String> artist;

	@FXML
	private HBox border;

	@FXML
	private TableColumn<Itemm, String> cid;

	@FXML
	private Circle circle;

	@FXML
	private TableColumn<Itemm, String> des;

	@FXML
	private TableColumn<Itemm, String> eid;

	@FXML
	private TableColumn<Itemm, String> name;

	@FXML
	private TableColumn<Itemm, String> price;

	@FXML
	private Button refreshButton;

	@FXML
	private TableColumn<Itemm, String> tid;

	@FXML
	private TableColumn<Itemm, String> usage;

	@FXML
	private TableColumn<Itemm, String> Editt;
	static Image img;
	static Image img2;

	String query = null;
	Connection connection = null;
	PreparedStatement ps = null;
	ResultSet result = null;
	Itemm it = null;

	ObservableList<Itemm> itemList = FXCollections.observableArrayList();

	@FXML
	void RefreshItem() {
		try {
			itemList.clear();
			query = "Select * From Itemss";
			ps = connection.prepareStatement(query);
			result = ps.executeQuery();
			while (result.next()) {
				itemList.add(new Itemm(result.getInt("Item_id"), result.getInt("Cid"), result.getInt("E_id"),
						result.getString("Item_Description"), result.getString("Item_useage"),
						result.getString("Item_name"), result.getString("Artistes_Name"),
						result.getDouble("Item_Price")));
				ItemTable.setItems(itemList);

			}
			// connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void loadDate() {

		connection = DBconnect.getConnect();
		RefreshItem();
		tid.setCellValueFactory(new PropertyValueFactory<>("Item_id"));
		cid.setCellValueFactory(new PropertyValueFactory<>("Cid"));
		eid.setCellValueFactory(new PropertyValueFactory<>("E_id"));
		des.setCellValueFactory(new PropertyValueFactory<>("Item_Description"));
		usage.setCellValueFactory(new PropertyValueFactory<>("Item_useage"));
		name.setCellValueFactory(new PropertyValueFactory<>("Item_name"));
		artist.setCellValueFactory(new PropertyValueFactory<>("Artistes_Name"));
		price.setCellValueFactory(new PropertyValueFactory<>("Item_Price"));

		// add cell of button edit
		Callback<TableColumn<Itemm, String>, TableCell<Itemm, String>> cellFoctory = (
				TableColumn<Itemm, String> param) -> {
			// make cell containing buttons
			final TableCell<Itemm, String> cell = new TableCell<Itemm, String>() {
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
								it = ItemTable.getSelectionModel().getSelectedItem();
								query = "DELETE FROM `Itemss` WHERE Item_id  =" + (it.getItem_id());
								connection = DBconnect.getConnect();
								ps = connection.prepareStatement(query);
								ps.execute();
								RefreshItem();

							} catch (SQLException ex) {
								Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
							}

						});
						editIcon.setOnMouseClicked((MouseEvent event) -> {

							it = ItemTable.getSelectionModel().getSelectedItem();
							FXMLLoader loader = new FXMLLoader();
							loader.setLocation(getClass().getResource("AddItem.fxml"));
							try {
								loader.load();
							} catch (IOException ex) {
								Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
							}

							AddItemController addtController = loader.getController();
							addtController.setUpdate(true);
							addtController.setTextField(it.getItem_id(), it.getCid(), it.getE_id(),
									it.getItem_Description(), it.getItem_useage(), it.getItem_name(),
									it.getArtistes_Name(), it.getItem_Price());
							Parent parent = loader.getRoot();
							Stage stage = new Stage();
							stage.setScene(new Scene(parent));
							stage.initStyle(StageStyle.UTILITY);
							stage.show();

						});

						HBox managebtn = new HBox(editIcon, deleteIcon);
						managebtn.setStyle("-fx-alignment:center");
						// HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
						// HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

						setGraphic(managebtn);

						setText(null);

					}
				}

			};

			return cell;
		};
		Editt.setCellFactory(cellFoctory);
		ItemTable.setItems(itemList);
	}

	@FXML
	private void getAddView() {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("AddItem.fxml"));
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
    public void Key() {
    	try {
    	Parent parent = FXMLLoader.load(getClass().getResource("ForItem.fxml"));
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
