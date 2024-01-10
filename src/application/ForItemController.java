package application;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ForItemController implements Initializable {

	public ForItemController() {
		// TODO Auto-generated constructor stub
	}

	 	@FXML
	    private TableView<NewCustomer> CTable;    
	    String query=null;
	    Connection connection = null;
	    PreparedStatement ps=null;
	    Statement statement;
	    ResultSet result=null;   
	  
	    ObservableList <NewCustomer> ckey= FXCollections.observableArrayList();
	   
	    public void keys() {
	    	
	    	 
	        query = "select E_id, Cid from NewCustomer " ;
	    	connection = DBconnect.getConnect();
	        try {
	    		statement = connection.createStatement();
	    		  ResultSet resultSet = statement.executeQuery(query);
	    		  CTable.getItems().clear();
	    		  CTable.getColumns().clear();

	        TableColumn<NewCustomer, String> column = new TableColumn<>();
	        column.setText("Event ID");
	        column.setMinWidth(100);
	        column.setCellValueFactory(new PropertyValueFactory<NewCustomer, String>("E_id"));
	        CTable.getColumns().add(column);
	        
	        TableColumn<NewCustomer, String> column2 = new TableColumn<>();
	        column2.setText("Customer ID");
	        column2.setMinWidth(100);
	        column2.setCellValueFactory(new PropertyValueFactory<NewCustomer, String>("Cid"));
	        CTable.getColumns().add(column2);
	   
	        while(resultSet.next()) {
	    		NewCustomer ev = new NewCustomer();
	    	    ev.setE_id(resultSet.getInt(1));
	    	    ev.setCid(resultSet.getInt(2));
	    	   // product.setItem_Price(resultSet.getDouble(2));
	    	    ckey.add(ev);
	    	}
	        
	        } catch (SQLException e) {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    	}
	      
	        CTable.setItems(ckey);

	          }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			keys();
		}
	    

	
}

