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

public class AddBookController {

    @FXML
    private TextField Ebook;

    @FXML
    private TextField Eed;

    @FXML
    private TextField Eitem;

    @FXML
    private TextField Eloc;

    @FXML
    private TextField Epage;

    @FXML
    private Button clear;

    @FXML
    private Text enterA;

    @FXML
    private Text enterCid;

    @FXML
    private Text enterD;

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
    Book b = null;
    private boolean update;
    int bookId;

    String ed,loc;
    int id1,id2,num;
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    
    @FXML
    void Clear() {
    	Ebook.setText(null);
    	Eitem.setText(null);
        Epage.setText(null);
        Eed.setText(null);
        Eloc.setText(null);

    }

    @FXML
    void Save(ActionEvent event) {
    	 connection = DBconnect.getConnect();
      

         if (Ebook.getText()==null|| Eitem.getText()==null||Epage.getText()==null||Eed.getText().isEmpty()||Eloc.getText().isEmpty()) {
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText(null);
             alert.setContentText("Please Fill All DATA");
             alert.showAndWait();

         } else {
        	 id1 = Integer.parseInt(Ebook.getText());
        	 id2 = Integer.parseInt(Eitem.getText());
        	 num = Integer.parseInt(Epage.getText());
        	 ed=Eed.getText();
        	 loc=Eloc.getText();
             getQuery();
             insert();
             Clear();

         }
    }

    
    private void insert() {

        try {
        	

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(Eitem.getText()));
            preparedStatement.setInt(2, Integer.parseInt(Ebook.getText()));
            preparedStatement.setInt(3, Integer.parseInt(Epage.getText()));
            preparedStatement.setString(4, Eed.getText());
            preparedStatement.setString(5, Eloc.getText());

            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

  
    private void getQuery() {

        if (update == false) {
            
            query = "INSERT INTO `Book`( `Item_id`, `Books_id`, `Page_num`,`Edition`, `location`) VALUES (?,?,?,?,?)";

        }else{
            query = "UPDATE `Book` SET "
                    + "`Item_id`=?,"
                    + "`Books_id`=?,"
                    + "`Page_num`=?,"
                    + "`Edition`=?,"
                    + "`location`= ? WHERE Books_id = '"+bookId+"'";
        }

    }
    
    void setTextField(int id2, int id1, int num, String ed,String loc ) {

    	bookId= id1;
    	Eitem.setText(Integer.toString(id2));
    	Ebook.setText(Integer.toString(id1));
    	Epage.setText(Integer.toString(num));
    	Eed.setText(ed);
    	Eloc.setText(loc);



    }

    void setUpdate(boolean b) {
        this.update = b;

    }
    
   



}
