package carpark.View;

import carpark.CarPark;
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
    public void setLocationChoices() {
        addEditDialogLocation.getItems().removeAll(addEditDialogLocation.getItems());
        addEditDialogLocation.getItems().addAll(makeLocationChoices());
        addEditDialogLocation.getSelectionModel().select(0);
    }
    private ArrayList<Integer> makeLocationChoices() {
        ArrayList<Integer> locationChoices = new ArrayList();
        for (int i=1; i<=carPark.getParkingSpacesNum(); i++) {
            locationChoices.add(i);
        }
        for (int i=0; i<carPark.getCarData().size(); i++) {
            locationChoices.remove(carPark.getCarData().get(i).getLocation());
        }
        return locationChoices;
    }
    private void fillCarData(Car car) {
        if (car.getLocation()!=0) {
        //włącza tryb edycji jeśli location nie jest równe zero
            editMode = true;
        //ustawia wartości dla pól tekstowych
            addEditDialogLocation.setValue(String.valueOf(car.getLocation()));
            addEditDialogRegNum.setText(car.getRegNum());
            addEditDialogMake.setText(car.getMake());
            addEditDialogModel.setText(car.getModel());
            addEditDialogFirstName.setText(car.getFirstName());
            addEditDialogLastName.setText(car.getLastName());
            addEditDialogPhoneNumber.setText(car.getPhoneNumber());
        }
        addEditDialogStartTime.setText(car.getStartDateTime()); //samochody nowe oraz edytowane mają tą wartość
    }
    public void handleSave() {
        //pobranie wartości z pól tekstowych
            //metoda integer nie akceptuje obiektow, dlatego najpierw z wartości robię stringa
        Integer addEditLocationVal = Integer.parseInt(String.valueOf(addEditDialogLocation.getValue()));
        String addEditRegNumVal = addEditDialogRegNum.getText();
        String addEditMakeVal = addEditDialogMake.getText();
        String addEditModelVal = addEditDialogModel.getText();
        String addEditFirstNameVal = addEditDialogFirstName.getText();
        String addEditLastNameVal = addEditDialogLastName.getText();
        String addEditPhoneNumberVal = addEditDialogPhoneNumber.getText();
        
        if (validateInput(addEditRegNumVal, addEditMakeVal,
                addEditModelVal, addEditFirstNameVal, addEditLastNameVal, addEditPhoneNumberVal)) {
            car.setLocation(addEditLocationVal);
            car.setRegNum(addEditRegNumVal);
            car.setMake(addEditMakeVal);
            car.setModel(addEditModelVal);
            car.setFirstName(addEditFirstNameVal);
            car.setLastName(addEditLastNameVal);
            car.setPhoneNumber(addEditPhoneNumberVal);
            
            //jeśli editMode==false to dodajemy nowy samochód
            if (!editMode) {
                carPark.addToCarData(car); //samochód zostaje dodany do observable list
                carPark.initInfoBar(); //odświeżamy informacje na górnym pasku
            }
            
            carPark.updateFile(); //nadpisje cały plik nowym stanem samochodów
            
            editMode = false; //editMode wraca do swojej domyślnej wartości
            dialogStage.close();
        }
    }
    public void handleClose() {
        dialogStage.close();
    }
    private boolean validateInput(String addEditRegNumVal,
            String addEditMakeVal, String addEditModelVal, String addEditFirstNameVal,
            String addEditLastNameVal, String addEditPhoneNumberVal) {
        String regex = "[0-9]+"; //do walidacji numeru telefonu
        String alertText = ""; //żeby dodawać do zmiennej muszę jej nadać jakąś wartość
        Boolean incorrectInput = false;
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
        if (addEditPhoneNumberVal.length() == 0 || addEditPhoneNumberVal.length() > 20 ||
                !addEditPhoneNumberVal.matches(regex)) {
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
        //inicjuję plik fxml wartościami, metoda kontrolera javaFx
    }
}
