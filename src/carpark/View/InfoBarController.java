/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carpark.View;

import carpark.CarPark;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author MW5
 */
public class InfoBarController implements Initializable {
    private CarPark carPark;
    @FXML
    private Text fillPercentage;
    @FXML
    private Text emptySpaces;
    
    public void setCarPark(CarPark carPark) {
        this.carPark = carPark;
    }
    
    public void updateSpaceInfo() {
        Integer fillPer = carPark.getCarData().size()*100/carPark.getParkingSpacesNum();
        Integer emptySp = carPark.getParkingSpacesNum()-carPark.getCarData().size();
        fillPercentage.setText(String.valueOf(fillPer));
        emptySpaces.setText(String.valueOf(emptySp)+"/"+carPark.getParkingSpacesNum());
    }
    @FXML
    public void closeButtonAction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Zamknij program");
        alert.setHeaderText(null);
        alert.setContentText("Czy jesteś pewien, że chcesz zamknąć program?");
        Optional<ButtonType> confirm = alert.showAndWait();
        if (confirm.get() == ButtonType.OK) {
            System.exit(0);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
