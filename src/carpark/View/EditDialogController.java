/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carpark.View;

import carpark.Model.Car;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MW5
 */
public class EditDialogController implements Initializable {
    @FXML
    private TextField editDialogLocation;
    @FXML
    private TextField editDialogRegNum;
    @FXML
    private TextField editDialogMake;
    @FXML
    private TextField editDialogModel;
    @FXML
    private TextField editDialogFirstName;
    @FXML
    private TextField editDialogLastName;
    @FXML
    private TextField editDialogPhoneNumber;
    
    private Stage dialogStage;
    private Car car;
    private boolean confirmed = false;
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public void setCar(Car car) {
        this.car = car;
        //TO FILL CAR DATA AFTER FIXIND ISSUE
        //assigns the values to textfields
//        editDialogLocation.setText(String.valueOf(car.getLocation()));
//        editDialogRegNum.setText(car.getRegNum());
//        editDialogMake.setText(car.getMake());
//        editDialogModel.setText(car.getModel());
//        editDialogFirstName.setText(car.getFirstName());
//        editDialogLastName.setText(car.getLastName());
//        editDialogPhoneNumber.setText(String.valueOf(car.getPhoneNumber()));
        //fillCarData();
    }
    private void fillCarData() {
         
    }
    public void handleSave() {
        if (validateInput()) {
            System.out.println("ok"); //testing
        }
    }
    public void handleClose() {
        dialogStage.close();
    }
    private boolean validateInput() {
        return true; //testing
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
