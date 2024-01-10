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

public class EventKeyController implements Initializable {

	public EventKeyController() {
		// TODO Auto-generated constructor stub
	}

	@FXML
    private TableView<Event_s> EvTable;

    
    
    String query=null;
    Connection connection = null;
    PreparedStatement ps=null;
    Statement statement;
    ResultSet result=null;   
  
    ObservableList <Event_s> ckey= FXCollections.observableArrayList();
   
    public void keys() {
    	
    	 
        query = "select E_id from Event_s " ;
    	connection = DBconnect.getConnect();
        try {
    		statement = connection.createStatement();
    		  ResultSet resultSet = statement.executeQuery(query);
    		  EvTable.getItems().clear();
    		  EvTable.getColumns().clear();

        TableColumn<Event_s, String> column = new TableColumn<>();
        column.setText("Event ID");
        column.setMinWidth(100);
        column.setCellValueFactory(new PropertyValueFactory<Event_s, String>("E_id"));
        EvTable.getColumns().add(column);
   
        while(resultSet.next()) {
    		Event_s ev = new Event_s();
    	    ev.setE_id(resultSet.getInt(1));;
    	   // product.setItem_Price(resultSet.getDouble(2));
    	    ckey.add(ev);
    	}
        
        } catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
      
        EvTable.setItems(ckey);

          }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		keys();
	}
    

}
