package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class QueriesController implements Initializable {

	@FXML
	private TextField value,value1;
	@FXML
    private CategoryAxis xAxis;
	@FXML
	private Button b1;
	@FXML
	private RadioButton b0, b2, b3,b4,b5;
	@FXML
	private TableView<Itemm> valueTable;
    @FXML
    private TableView<Masterpieces> valueTable1;
	@FXML
	private ToggleGroup choseOp;
	@FXML
	private BarChart <String, Integer> barchart;
	String query = null;
	String query1 = null;
	Connection con = null;
	Statement statement;
	int NumFemales;
	int NumMales;

	ObservableList<Itemm> list = FXCollections.observableArrayList();
	ObservableList<Masterpieces> list1 = FXCollections.observableArrayList();

	@FXML
	void RadioButtons_Action(ActionEvent event) {
		if (b0.isSelected()) {
			Phone_Empo();
		} else if (b2.isSelected()) {
			Avg();
		} else if (b3.isSelected()) {
			barchart.getData().clear();
			Customer_Gender_Event();
			
		}
		else if (b4.isSelected()) {
		    barchart.getData().clear();
			Cloth_Type();
		}
		else if (b5.isSelected()) {
		    barchart.getData().clear();
			Customer_Gender();
		}
	}

	@FXML
	public void get() {  /**  Rania Part **/ 

		double val = Double.parseDouble(value.getText());
		query = "select Item_name, Item_Price from Itemss where Item_Price >= " + val;
		con = DBconnect.getConnect();
		try {
			statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			valueTable.getItems().clear();
			valueTable.getColumns().clear();

			TableColumn<Itemm, String> column = new TableColumn<>();
			column.setText("Name");
			column.setMinWidth(100);
			column.setCellValueFactory(new PropertyValueFactory<Itemm, String>("Item_name"));
			valueTable.getColumns().add(column);

			TableColumn<Itemm, String> column2 = new TableColumn<>();
			column2.setText("Selling Price");
			column2.setMinWidth(100);
			column2.setCellValueFactory(new PropertyValueFactory<Itemm, String>("Item_Price"));
			valueTable.getColumns().add(column2);

			while (resultSet.next()) {
				Itemm product = new Itemm();
				product.setItem_name(resultSet.getString(1));
				product.setItem_Price(resultSet.getDouble(2));
				list.add(product);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		valueTable.setItems(list);
	}

	@FXML
	public void Avg() { /**  Rania Part **/ 

		query = "select avg(Item_Price) from Itemss";
		con = DBconnect.getConnect();
		double avg = 0;
		Statement statement;
		try {
			statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				avg = resultSet.getDouble(1);
			}

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText("Average Item Price");
			alert.setContentText("Average price for items  " + avg);
			alert.showAndWait();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	@FXML
	public void Cloth_Type() { /**  Aseel Part **/ 

		query = "select c.Clothes_Description, COUNT(*) from Clothes c GROUP BY c.Clothes_Description";
		con = DBconnect.getConnect();
		int num = 0;
		String str = "";
		Statement statement;
		XYChart.Series<String, Integer> s1 = new XYChart.Series<>();
		try {
			statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				str = resultSet.getString(1);
				num = resultSet.getInt(2);
				s1.getData().add(new Data<String, Integer>(str, num));
				System.out.println(str + "\n" + num);

			}
			
			xAxis.setAnimated(false);
			s1.setName("Clothes Type");
			//barchart.categoryGapProperty()
			barchart.getData().addAll(s1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void Phone_Empo() { /**  Aseel Part **/ 

		query = "select COUNT(*) AS NUMBEROFEMPLO from employees e where e.EphoneNum LIKE '056%'";
		query1= "select COUNT(*) AS NUMBEROFEMPLO from employees e where e.EphoneNum LIKE '059%'";
		
		con = DBconnect.getConnect();
		int num = 0;
		int num1 = 0;
		Statement statement;
		Statement statement1;
		try {
			
			statement = con.createStatement();
			statement1 = con.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			ResultSet resultSet1 = statement1.executeQuery(query1);

			while (resultSet.next() && resultSet1.next()) {
				num = resultSet.getInt(1);
				num1 = resultSet1.getInt(1);
			}
			
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText("Poeple With Different Phone numbers");
			alert.setContentText("Number of poeple who has Jawal as Their Phone number is " + num1 + '\n'
					+ "Number of poeple who has Oridow as Their Phone number is " +num);
			alert.showAndWait();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	@FXML
	public void Customer_Gender_Event() { /**  Aseel Part **/ 

		query = "select c.gender, COUNT(*) from NewCustomer c , Event_s e where c.E_id = e.E_id and  e.Location = 'Birzeit' GROUP BY c.gender";
		con = DBconnect.getConnect();
		int num = 0;
		String str = "";
		Statement statement;
		XYChart.Series<String, Integer> s1 = new XYChart.Series<>();
		try {
			statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				str = resultSet.getString(1);
				num = resultSet.getInt(2);
				s1.getData().add(new XYChart.Data<>(str, num));
				System.out.println(str + "\n" + num);

			}
			s1.setName("Gender");
			xAxis.setAnimated(false);
			barchart.getData().addAll(s1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	@SuppressWarnings("unchecked")
	@FXML
	public void Customer_Gender() {  /**  lojain Part **/ 

		String query_M = "Select Count(*) From NewCustomer Where NewCustomer.gender = 'M'";
		String query_F = "Select Count(*) From NewCustomer Where NewCustomer.gender = 'F'";

		con = DBconnect.getConnect();
		XYChart.Series<String, Integer> s1 = new XYChart.Series<>();
		try {
			Statement statement2 = con.createStatement();
			Statement statement3 = con.createStatement();
			ResultSet resultSet = statement2.executeQuery(query_M);
			ResultSet resultSet1 = statement3.executeQuery(query_F);

			while (resultSet.next() && resultSet1.next()) {
				NumMales = resultSet.getInt(1);
				NumFemales = resultSet1.getInt(1);
			}

			s1.getData().add(new XYChart.Data<>("Female", NumFemales));
			s1.getData().add(new XYChart.Data<>("Male", NumMales));
			s1.setName("Gender");
			xAxis.setAnimated(false);
			barchart.getData().addAll(s1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	@FXML
    public void get2() {
		
		
    	String val = value1.getText();
        String query = "Select Masterpieces_Name,Meterial FROM Masterpieces WHERE Artistes_Name ='"+ val+"'";
         con = DBconnect.getConnect();
        try {
            Statement statement;
    		statement = con.createStatement();
    		  ResultSet resultSet = statement.executeQuery(query);
    		  valueTable1.getItems().clear();
    		  valueTable1.getColumns().clear();

        TableColumn<Masterpieces, String> column = new TableColumn<>();
        column.setText("Masterpieces Name");
        column.setMinWidth(100);
        column.setCellValueFactory(new PropertyValueFactory<Masterpieces, String>("Masterpieces_Name"));
        valueTable1.getColumns().add(column);

        TableColumn<Masterpieces, String> column2 = new TableColumn<>();
        column2.setText("Material");
        column2.setMinWidth(100);
        column2.setCellValueFactory(new PropertyValueFactory<Masterpieces, String>("Meterial"));
        valueTable1.getColumns().add(column2);

        while(resultSet.next()) {
        	Masterpieces product = new Masterpieces();
    	    product.setMasterpieces_Name(resultSet.getString(1));
    	    product.setMeterial(resultSet.getString(2));
    	    list1.add(product);
    	}
        
        } catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
      
        valueTable1.setItems(list1);
}

	
    @FXML
    void Back_Button(ActionEvent event) throws IOException {
     	Main m = new Main();
    	m.changeScene("Bord.fxml");

    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
