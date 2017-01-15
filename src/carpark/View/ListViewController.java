/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carpark.View;

import carpark.Model.Car;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author MW5
 */
public class ListViewController implements Initializable {
    @FXML
    private TableView<Car> carTable;
    @FXML
    private TableColumn<Car, Number> locationCol; //api mixup
    @FXML
    private TableColumn<Car, String> makeCol;
    @FXML
    private TableColumn<Car, String> modelCol;
    @FXML
    private TableColumn<Car, String> firstNameCol;
    @FXML
    private TableColumn<Car, String> lastNameCol;
    @FXML
    private TableColumn<Car, Number> phoneNumberCol; //same here
    @FXML
    private TableColumn<Car, LocalDateTime> startDateTimeCol;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        locationCol.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
        makeCol.setCellValueFactory(cellData -> cellData.getValue().makeProperty());
    }    
    
}
