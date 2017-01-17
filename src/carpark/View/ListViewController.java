/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carpark.View;

import carpark.CarPark;
import carpark.Model.Car;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    private TableColumn<Car, String> startDateTimeCol;
    
    private CarPark carPark;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        locationCol.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
        makeCol.setCellValueFactory(cellData -> cellData.getValue().makeProperty());
        modelCol.setCellValueFactory(cellData -> cellData.getValue().modelProperty());
        firstNameCol.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameCol.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        phoneNumberCol.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
        startDateTimeCol.setCellValueFactory(cellData -> cellData.getValue().startDateTimeProperty());
    }
    
    public void setCarPark (CarPark carPark) {
        this.carPark = carPark;
        // set table with data from observable list
        carTable.setItems(carPark.getCarData());
    }
    public void deleteCar() {
        int selectedIndex = carTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex>=0) {
            carTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(carPark.getPrimaryStage());
            alert.setTitle("Wybierz samochód");
            alert.setHeaderText(null);
            alert.setContentText("Wybierz samochód do usunięcia");
            alert.showAndWait();
        }
    }
    
}
