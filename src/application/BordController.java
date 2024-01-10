package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class BordController {

    @FXML
    private Button BacktoMain_Button,Report_Button;
    
    @FXML
    void BacktoMain(ActionEvent event) throws IOException {
    	Main m = new Main(); /* open new window */
		m.changeScene("Window1.fxml");

    }
    
    @FXML
    void CustomerButton(ActionEvent event) throws IOException {
    	
    	Main m = new Main(); /* open new window */
		m.changeScene("Customer.fxml");
    	System.out.println("\nWorking!!");
    }
    
    @FXML
    void ItemsButton(ActionEvent event) throws IOException {
    	
    	Main m = new Main(); /* open new window */
		m.changeScene("Item.fxml");
    	System.out.println("\nWorking!!");

    }
    
    @FXML
    void EventsButton(ActionEvent event) throws IOException {
    	Main m = new Main(); /* open new window */
		m.changeScene("Event_s.fxml");
    	System.out.println("\nWorking!!");

    }

    @FXML
    void BooksButton(ActionEvent event) throws IOException {
    	
    	
    	Main m = new Main(); /* open new window */
		m.changeScene("Book.fxml");
    	System.out.println("\nWorking!!");


    }
    
    @FXML
    void MasterPeicesButton(ActionEvent event) throws IOException {
    	
    	Main m = new Main(); /* open new window */
		m.changeScene("Masterpieces.fxml");
    	System.out.println("\nWorking!!");

    }
    
    @FXML
    void ClothesButton(ActionEvent event) throws IOException {
    	Main m = new Main(); /* open new window */
		m.changeScene("Clothes.fxml");
    	System.out.println("\nWorking!!");
    	

    }
    
    @FXML
    void Report_Button_Action(ActionEvent event) throws IOException {
    	Main m = new Main(); /* open new window */
		m.changeScene("Queries.fxml");
    	System.out.println("\nWorking!!");
    }



}