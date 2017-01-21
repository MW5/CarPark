/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carpark.View;

import carpark.Model.Car;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MW5
 */
public class EditDialogController implements Initializable {
    @FXML
    private ComboBox editDialogLocation;
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
    private final int parkingLocationsNumber = 99; //change later!!!
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public void setCar(Car car) {
        this.car = car;
        fillCarData(car);
    }
    private void fillCarData(Car car) {
        //assigns the values to textfields
        editDialogLocation.setValue(String.valueOf(car.getLocation()));
        editDialogRegNum.setText(car.getRegNum());
        editDialogMake.setText(car.getMake());
        editDialogModel.setText(car.getModel());
        editDialogFirstName.setText(car.getFirstName());
        editDialogLastName.setText(car.getLastName());
        editDialogPhoneNumber.setText(String.valueOf(car.getPhoneNumber()));
    }
    public void handleSave() {
        //to get textfield values
        Integer editLocationVal = Integer.valueOf(String.valueOf(editDialogLocation.getValue()));
        String editRegNumVal = editDialogRegNum.getText();
        String editMakeVal = editDialogMake.getText();
        String editModelVal = editDialogModel.getText();
        String editFirstNameVal = editDialogFirstName.getText();
        String editLastNameVal = editDialogLastName.getText();
        Integer editPhoneNumberVal = Integer.valueOf(editDialogPhoneNumber.getText());
        if (validateInput(editLocationVal, editRegNumVal, editMakeVal,
                editModelVal, editFirstNameVal, editLastNameVal, editPhoneNumberVal)) {
            car.setLocation(editLocationVal);
            car.setRegNum(editRegNumVal);
            car.setMake(editMakeVal);
            car.setModel(editModelVal);
            car.setFirstName(editFirstNameVal);
            car.setLastName(editLastNameVal);
            car.setPhoneNumber(editPhoneNumberVal);
            dialogStage.close();
        }
    }
    public void handleClose() {
        dialogStage.close();
    }
    private boolean validateInput( Integer editLocationVal, String editRegNumVal,
            String editMakeVal, String editModelVal, String editFirstNameVal,
            String editLastNameVal, Integer editPhoneNumberVal) {
        String alertText = ""; //to initialize it
        Boolean incorrectInput = false;
        if (editLocationVal == 0 || editLocationVal > parkingLocationsNumber) {
            incorrectInput = true;
            alertText += "\nWprowadź lokalizację (1-"+parkingLocationsNumber+")";
        }
        if (editRegNumVal.length() != 7) {
            incorrectInput = true;
            alertText += "\nWprowadź siedmoiznakowy numer rejestracyjny.";
        }
        if (editMakeVal.length() == 0 || editMakeVal.length() > 20) {
            incorrectInput = true;
            alertText += "\nWprowadź markę (1-20 znaków).";
        }
        if (editModelVal.length() == 0 || editModelVal.length() > 20) {
            incorrectInput = true;
            alertText += "\nWprowadź model (1-20 znaków).";
        }
        if (editFirstNameVal.length() == 0 || editFirstNameVal.length() > 20) {
            incorrectInput = true;
            alertText += "\nWprowadź imię (1-20 znaków).";
        }
        if (editLastNameVal.length() == 0 || editLastNameVal.length() > 99) {
            incorrectInput = true;
            alertText += "\nWprowadź nazwisko pomiędzy (1-20 znaków).";
        }
        if (String.valueOf(editPhoneNumberVal).length() == 0 || String.valueOf(editPhoneNumberVal).length() > 99) {
            incorrectInput = true;
            alertText += "\nWprowadź numer telefonu (1-20 cyfr).";
        }
        if (incorrectInput) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Wprowadź poprawne dane");
            alert.setHeaderText(null);
            alert.setContentText(alertText);
            alert.showAndWait();
            return false;
        }
        return true; //testing
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        editDialogLocation.getItems().removeAll(editDialogLocation.getItems());
        editDialogLocation.getItems().addAll(makeLocationChoices());
        editDialogLocation.getSelectionModel().select(0);
    }
    private ArrayList<Integer> makeLocationChoices() {
        ArrayList<Integer> locationChoices = new ArrayList();
        for (int i=1; i<=parkingLocationsNumber; i++) {
            locationChoices.add(i);
        }
        return locationChoices;
    }
    
}
