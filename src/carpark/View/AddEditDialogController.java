/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the addEditor.
 */
package carpark.View;

import carpark.CarPark;
import carpark.Model.Car;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
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
    @FXML
    private Text addEditDialogStartTime;
    
    private Stage dialogStage;
    private Car car;
    private final int parkingLocationsNumber = 99; //change later!!!
    public CarPark carPark;
    private Boolean editMode = false;
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public void setCarPark(CarPark carPark) {
        this.carPark = carPark;
    }
    public void setCar(Car car) {
        this.car = car;
        fillCarData(car); 
    }
    private void fillCarData(Car car) {
        if (car.getLocation()!=0) {
        //turns edit mode on
            editMode = true;
        //assigns the values to textfields
            addEditDialogLocation.setValue(String.valueOf(car.getLocation()));
            addEditDialogRegNum.setText(car.getRegNum());
            addEditDialogMake.setText(car.getMake());
            addEditDialogModel.setText(car.getModel());
            addEditDialogFirstName.setText(car.getFirstName());
            addEditDialogLastName.setText(car.getLastName());
            addEditDialogPhoneNumber.setText(car.getPhoneNumber());
        }
        addEditDialogStartTime.setText(car.getStartDateTime()); //new and edited cars should have this value
    }
    public void handleSave() {
        //to get values
        Integer addEditLocationVal = Integer.parseInt(String.valueOf(addEditDialogLocation.getValue())); //because integer method doesn`t accept objects and string one does
        String addEditRegNumVal = addEditDialogRegNum.getText();
        String addEditMakeVal = addEditDialogMake.getText();
        String addEditModelVal = addEditDialogModel.getText();
        String addEditFirstNameVal = addEditDialogFirstName.getText();
        String addEditLastNameVal = addEditDialogLastName.getText();
        String addEditPhoneNumberVal = addEditDialogPhoneNumber.getText();
        String addEditStartDateTime = addEditDialogStartTime.getText();
        
        if (validateInput(addEditLocationVal, addEditRegNumVal, addEditMakeVal,
                addEditModelVal, addEditFirstNameVal, addEditLastNameVal, addEditPhoneNumberVal)) {
            car.setLocation(addEditLocationVal);
            car.setRegNum(addEditRegNumVal);
            car.setMake(addEditMakeVal);
            car.setModel(addEditModelVal);
            car.setFirstName(addEditFirstNameVal);
            car.setLastName(addEditLastNameVal);
            car.setPhoneNumber(addEditPhoneNumberVal);
            
            saveToFile(addEditLocationVal, addEditRegNumVal, addEditMakeVal,
                addEditModelVal, addEditFirstNameVal, addEditLastNameVal, addEditPhoneNumberVal,
                addEditStartDateTime);
            
            //if edit mode false
            if (!editMode) {
                carPark.addToCarData(car); //adds car to observable list
            }
            editMode = false; //edit mode turned off to its default value
            dialogStage.close();
        }
    }
    public void handleClose() {
        dialogStage.close();
    }
    private boolean validateInput(Integer addEditLocationVal, String addEditRegNumVal,
            String addEditMakeVal, String addEditModelVal, String addEditFirstNameVal,
            String addEditLastNameVal, String addEditPhoneNumberVal) {
        String alertText = ""; //to initialize it
        Boolean incorrectInput = false;
        if (addEditLocationVal == 0) {
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
        if (addEditPhoneNumberVal.length() == 0 || addEditPhoneNumberVal.length() > 20) {
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
    private void saveToFile (Integer addEditLocationVal, String addEditRegNumVal,
            String addEditMakeVal, String addEditModelVal, String addEditFirstNameVal,
            String addEditLastNameVal, String addEditPhoneNumberVal, String startTime) {
        //MUST CHECK IF IT`S ALREADY PRESENT! can be checked by regnum
        try {
            String lineToWrite = addEditLocationVal+";"+addEditRegNumVal+";"+
             addEditMakeVal+";"+addEditModelVal+";"+addEditFirstNameVal+";"+
             addEditLastNameVal+";"+addEditPhoneNumberVal+";"+startTime+System.lineSeparator();
            
            OutputStream out = new FileOutputStream(new File("./src/carpark/DB/carDB.txt"), true);
            out.write(lineToWrite.getBytes(StandardCharsets.UTF_8)); //to omit adding BOM to the beginning of file
        } catch (IOException e) {
            e.printStackTrace(System.out); //CHANGE FOR PROPER ERROR
        }
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
