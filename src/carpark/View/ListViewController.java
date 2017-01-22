/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carpark.View;

import carpark.CarPark;
import carpark.Model.Car;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
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
    private TableColumn<Car, Number> locationCol; //api mixup, thus number...
    @FXML
    private TableColumn<Car, String> regNumCol;
    @FXML
    private TableColumn<Car, String> makeCol;
    @FXML
    private TableColumn<Car, String> modelCol;
    @FXML
    private TableColumn<Car, String> firstNameCol;
    @FXML
    private TableColumn<Car, String> lastNameCol;
    @FXML
    private TableColumn<Car, String> phoneNumberCol; //same here
    @FXML
    private TableColumn<Car, String> startDateTimeCol;
    
    private CarPark carPark;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        locationCol.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
        regNumCol.setCellValueFactory(cellData -> cellData.getValue().regNumProperty());
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
    private String stringifyDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formattedDateTime = localDateTime.format(formatter);
        return formattedDateTime;
    }
    private LocalDateTime parseDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
        return localDateTime;
    }
    private String timeDifference(LocalDateTime startTime, LocalDateTime exitTime) {
        String timeDiff = "";
        long years = ChronoUnit.YEARS.between(startTime, exitTime);
        long months = ChronoUnit.MONTHS.between(startTime, exitTime);
        long days = ChronoUnit.DAYS.between(startTime, exitTime);
        long hours = ChronoUnit.HOURS.between(startTime, exitTime);
        long minutes = ChronoUnit.MINUTES.between(startTime, exitTime);
        if (years!=0) {
            timeDiff="\nLat: "+String.valueOf(years);
        }
        if (months!=0) {
            timeDiff+="\nMiesięcy: "+String.valueOf(months);
        }
        if (days!=0) {
            timeDiff+="\nDni: "+String.valueOf(days);
        }
        if (hours!=0) {
            timeDiff+="\nGodzin: "+String.valueOf(hours);
        }
        if (minutes!=0) {
            timeDiff+="\nMinut: "+String.valueOf(minutes);
        }
        return timeDiff;
    }
    public void addCar() {
        Car defaultCar = new Car(0 ,"", "", "", "", "", "", LocalDateTime.now()); //default new car to serve as a blueprint
        carPark.showAddEditDialog(defaultCar); //location set to 0 distincts between add and edit
    }
    public void editCar() {
        int selectedIndex = carTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex>=0) {
            carPark.showAddEditDialog(carTable.getSelectionModel().getSelectedItem());
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(carPark.getPrimaryStage());
            alert.setTitle("Wybierz samochód");
            alert.setHeaderText(null);
            alert.setContentText("Wybierz samochód do edycji");
            alert.showAndWait();
        }
    }
    public void deleteCar() {
        int selectedIndex = carTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex>=0) {
            //string times start and car exit(present)
            String startTimeString = carTable.getSelectionModel().getSelectedItem().getStartDateTime();
            String exitTimeString = stringifyDateTime(LocalDateTime.now());
            //parse startTime from to string for calculations
            LocalDateTime startTime = parseDateTime(startTimeString);
            LocalDateTime exitTime = parseDateTime(exitTimeString);
            
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Usuń samochód");
            alert.setHeaderText("Data wjazdu: "+ startTimeString
                    +"\nObecny czas: "+ exitTimeString
                    +"\nCzas postoju: "+ timeDifference(startTime, exitTime));
            alert.setContentText("Czy jesteś pewien, że chcesz usunąć samochód o"
                     +" numerze rejestracyjnym "+carTable.getSelectionModel().getSelectedItem().getRegNum()+"?");
            Optional<ButtonType> confirm = alert.showAndWait();
            if (confirm.get() == ButtonType.OK) {
                carTable.getItems().remove(selectedIndex);
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Wybierz samochód");
            alert.setHeaderText(null);
            alert.setContentText("Wybierz samochód do usunięcia");
            alert.showAndWait();
        }
    }
    
}
