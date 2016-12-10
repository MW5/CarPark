/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carpark;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author MW5
 */
public class ViewController implements Initializable {
    @FXML private Button listBtn;
    @FXML private Button mapBtn;
    private Stage stage;
    private Parent root;
    
    public void init(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/ListView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void listButtonAction(ActionEvent event) throws Exception {
        stage = (Stage) listBtn.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("View/ListView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void mapButtonAction(ActionEvent event) throws Exception {
        stage = (Stage) mapBtn.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("View/MapView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void closeButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        //modal to confirm
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
