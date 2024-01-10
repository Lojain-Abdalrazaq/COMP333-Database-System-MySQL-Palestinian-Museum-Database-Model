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

public class BookController implements Initializable {

	@FXML
	private TableColumn<Book, String> Editc;

	@FXML
	private TableColumn<Book, String> Location;

	@FXML
	private TableColumn<Book, String> bid;

	@FXML
	private HBox border;

	@FXML
	private Circle circle;

	@FXML
	private TableView<Book> bookTable;

	@FXML
	private TableColumn<Book, String> edition;

	@FXML
	private TableColumn<Book, String> page;

	@FXML
	private Button refreshButton;

	@FXML
	private TableColumn<Book, String> tid;
	static Image img;
	static Image img2;

	String query = null;
	Connection connection = null;
	PreparedStatement ps = null;
	ResultSet result = null;
	Book b = null;

	ObservableList<Book> bList = FXCollections.observableArrayList();

	@FXML
	void RefreshCustomer() {
		try {
			bList.clear();
			query = "Select * From Book";
			ps = connection.prepareStatement(query);
			result = ps.executeQuery();
			while (result.next()) {
				bList.add(new Book(result.getInt("Item_id"), result.getInt("Books_id"), result.getInt("Page_num"),
						result.getString("Edition"), result.getString("location")));
				bookTable.setItems(bList);

			}
			// connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void loadDate() {

		connection = DBconnect.getConnect();
		RefreshCustomer();
		tid.setCellValueFactory(new PropertyValueFactory<>("Item_id"));
		bid.setCellValueFactory(new PropertyValueFactory<>("Books_id"));
		page.setCellValueFactory(new PropertyValueFactory<>("Page_num"));
		edition.setCellValueFactory(new PropertyValueFactory<>("Edition"));
		Location.setCellValueFactory(new PropertyValueFactory<>("location"));

		// add cell of button edit
		Callback<TableColumn<Book, String>, TableCell<Book, String>> cellFoctory = (
				TableColumn<Book, String> param) -> {
			// make cell containing buttons
			final TableCell<Book, String> cell = new TableCell<Book, String>() {
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
								b = bookTable.getSelectionModel().getSelectedItem();
								query = "DELETE FROM `Book` WHERE Books_id  =" + (b.getBooks_id());
								connection = DBconnect.getConnect();
								ps = connection.prepareStatement(query);
								ps.execute();
								RefreshCustomer();

							} catch (SQLException ex) {
								Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
							}

						});
						editIcon.setOnMouseClicked((MouseEvent event) -> {

							b = bookTable.getSelectionModel().getSelectedItem();
							FXMLLoader loader = new FXMLLoader();
							loader.setLocation(getClass().getResource("AddBook.fxml"));
							try {
								loader.load();
							} catch (IOException ex) {
								Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
							}

							AddBookController addbController = loader.getController();
							addbController.setUpdate(true);
							addbController.setTextField(b.getItem_id(), b.getBooks_id(), b.getPage_num(),
									b.getEdition(), b.getLocation());
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
		bookTable.setItems(bList);
	}

	@FXML
	private void getAddView() {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("AddBook.fxml"));
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		loadDate();
	}

}
