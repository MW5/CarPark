/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the addEditor.
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
public class AddEditDialogController implements Initializable {
    @FXML
    private ComboBox addEditDialogLocation;
    @FXML
    private TextField addEditDialogRegNum;
    @FXML
    private TextField addEditDialogMake;
    @FXML
    private TextField addEditDialogModel;
    @FXML
    private TextField addEditDialogFirstName;
    @FXML
    private TextField addEditDialogLastName;
    @FXML
    private TextField addEditDialogPhoneNumber;
    
    private Stage dialogStage;
    private Car car;
    private final int parkingLocationsNumber = 99; //change later!!!
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public void setCar(Car car) {
        this.car = car;
        if (car.getLocation()!=0) {
            fillCarData(car);
        }
    }
    private void fillCarData(Car car) {
        //assigns the values to textfields
        addEditDialogLocation.setValue(String.valueOf(car.getLocation()));
        addEditDialogRegNum.setText(car.getRegNum());
        addEditDialogMake.setText(car.getMake());
        addEditDialogModel.setText(car.getModel());
        addEditDialogFirstName.setText(car.getFirstName());
        addEditDialogLastName.setText(car.getLastName());
        addEditDialogPhoneNumber.setText(String.valueOf(car.getPhoneNumber()));
    }
    public void handleSave() {
        //to get values
        Integer addEditLocationVal = Integer.valueOf(String.valueOf(addEditDialogLocation.getValue()));
        String addEditRegNumVal = addEditDialogRegNum.getText();
        String addEditMakeVal = addEditDialogMake.getText();
        String addEditModelVal = addEditDialogModel.getText();
        String addEditFirstNameVal = addEditDialogFirstName.getText();
        String addEditLastNameVal = addEditDialogLastName.getText();
        Integer addEditPhoneNumberVal = Integer.valueOf(addEditDialogPhoneNumber.getText());
        if (validateInput(addEditLocationVal, addEditRegNumVal, addEditMakeVal,
                addEditModelVal, addEditFirstNameVal, addEditLastNameVal, addEditPhoneNumberVal)) {
            car.setLocation(addEditLocationVal);
            car.setRegNum(addEditRegNumVal);
            car.setMake(addEditMakeVal);
            car.setModel(addEditModelVal);
            car.setFirstName(addEditFirstNameVal);
            car.setLastName(addEditLastNameVal);
            car.setPhoneNumber(addEditPhoneNumberVal);
            dialogStage.close();
        }
    }
    public void handleClose() {
        dialogStage.close();
    }
    private boolean validateInput( Integer addEditLocationVal, String addEditRegNumVal,
            String addEditMakeVal, String addEditModelVal, String addEditFirstNameVal,
            String addEditLastNameVal, Integer addEditPhoneNumberVal) {
        String alertText = ""; //to initialize it
        Boolean incorrectInput = false;
        if (addEditLocationVal == 0 || addEditLocationVal > parkingLocationsNumber) {
            incorrectInput = true;
            alertText += "\nWybierz lokalizację (1-"+parkingLocationsNumber+")";
        }
        if (addEditRegNumVal.length() != 7) {
            incorrectInput = true;
            alertText += "\nWprowadź siedmoiznakowy numer rejestracyjny.";
        }
        if (addEditMakeVal.length() == 0 || addEditMakeVal.length() > 20) {
            incorrectInput = true;
            alertText += "\nWprowadź markę (1-20 znaków).";
        }
        if (addEditModelVal.length() == 0 || addEditModelVal.length() > 20) {
            incorrectInput = true;
            alertText += "\nWprowadź model (1-20 znaków).";
        }
        if (addEditFirstNameVal.length() == 0 || addEditFirstNameVal.length() > 20) {
            incorrectInput = true;
            alertText += "\nWprowadź imię (1-20 znaków).";
        }
        if (addEditLastNameVal.length() == 0 || addEditLastNameVal.length() > 20) {
            incorrectInput = true;
            alertText += "\nWprowadź nazwisko pomiędzy (1-20 znaków).";
        }
        //ADD PROPER PHONE NUM VALIDATION! crash on no value input
        if (String.valueOf(addEditPhoneNumberVal).length() == 0 ||
                String.valueOf(addEditPhoneNumberVal).length() > 20) {
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
        return true;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addEditDialogLocation.getItems().removeAll(addEditDialogLocation.getItems());
        addEditDialogLocation.getItems().addAll(makeLocationChoices());
        addEditDialogLocation.getSelectionModel().select(0);
    }
    private ArrayList<Integer> makeLocationChoices() {
        ArrayList<Integer> locationChoices = new ArrayList();
        for (int i=1; i<=parkingLocationsNumber; i++) {
            locationChoices.add(i);
        }
        return locationChoices;
    }
    
}
