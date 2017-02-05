package carpark.View;

import carpark.CarPark;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
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
        ButtonType ok = new ButtonType("Zatwierdź");
        ButtonType cancel = new ButtonType("Anuluj");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ok, cancel);
        alert.setTitle("Zamknij program");
        alert.setHeaderText(null);
        alert.setContentText("Czy jesteś pewien, że chcesz zamknąć program?");
        Optional<ButtonType> confirm = alert.showAndWait();
        if (confirm.get() == ok) {
            System.exit(0);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
