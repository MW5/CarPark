package carpark.View;

import carpark.CarPark;
import carpark.Model.Car;
import java.awt.print.PrinterException;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javax.swing.JTextPane;

/**
 * FXML Controller class
 *
 * @author MW5
 */
public class ListViewController implements Initializable {
    @FXML
    private TableView<Car> carTable;
    @FXML
    private TableColumn<Car, Number> locationCol; //number nie integer bo tak jest w api
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
    private TableColumn<Car, String> phoneNumberCol;
    @FXML
    private TableColumn<Car, String> startDateTimeCol;
    @FXML
    private TextField filterField;
    
    private CarPark carPark;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //inicjalizacja wierszy tabeli, wymagane są typy Property
        locationCol.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
        regNumCol.setCellValueFactory(cellData -> cellData.getValue().regNumProperty());
        makeCol.setCellValueFactory(cellData -> cellData.getValue().makeProperty());
        modelCol.setCellValueFactory(cellData -> cellData.getValue().modelProperty());
        firstNameCol.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameCol.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        phoneNumberCol.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
        startDateTimeCol.setCellValueFactory(cellData -> cellData.getValue().startDateTimeProperty());
    }
    //test //should be on click
    public void filterData() {
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            carPark.getCarDataFiltered().setPredicate(car -> {
                // jeśli filtr jest pusty to wyświetlaj wszystko
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                //
                String lowerCaseFilter = newValue.toLowerCase();
                if (car.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (car.getLastName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } 
                return false; // Does not match.
            });
        });
        carTable.setItems(carPark.getCarDataFiltered());
    }
    
    public void setCarPark (CarPark carPark) {
        this.carPark = carPark;
        // umieszcza w tabeli samochody z observable list
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
            timeDiff="\n    Lat: "+String.valueOf(years);
        }
        if (months!=0) {
            timeDiff+="\n   Miesięcy: "+String.valueOf(months);
        }
        if (days!=0) {
            timeDiff+="\n   Dni: "+String.valueOf(days);
        }
        if (hours!=0) {
            timeDiff+="\n   Godzin: "+String.valueOf(hours);
        }
        if (minutes!=0) {
            timeDiff+="\n   Minut: "+String.valueOf(minutes);
        }
        return timeDiff;
    }
    public void addCar() {
        //pusty nowy samochód który podczas dodawania zostanie wypełniony danymi, lub usunięty
        //location jest ustawione na zero bo addEditDialogController rozróżnia czy samochód jest
        //nowy czy edytowany
        Car defaultCar = new Car(0 ,"", "", "", "", "", "", LocalDateTime.now()); 
        carPark.showAddEditDialog(defaultCar); 
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
            String startTimeString = carTable.getSelectionModel().getSelectedItem().getStartDateTime();
            String exitTimeString = stringifyDateTime(LocalDateTime.now());
            //daty klasy localDateTime potrzebne są do kalkulacji przez ChronoUnit
            LocalDateTime startTime = parseDateTime(startTimeString);
            LocalDateTime exitTime = parseDateTime(exitTimeString);
            //tworzę nowe przyciski których użyję zamiast domyślnych dla alertu
            ButtonType cancel = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
            ButtonType ok = new ButtonType("Zatwierdź", ButtonBar.ButtonData.OK_DONE);
            Alert alert = new Alert(AlertType.CONFIRMATION, "", ok, cancel);
            alert.setTitle("Usuń samochód");
            alert.setHeaderText("Data wjazdu: "+ startTimeString
                    +"\nObecny czas: "+ exitTimeString
                    +"\nCzas postoju: "+ timeDifference(startTime, exitTime));
            alert.setContentText("Czy jesteś pewien, że chcesz usunąć samochód o"
                     +" numerze rejestracyjnym "+carTable.getSelectionModel().getSelectedItem().getRegNum()+"?");
            Optional<ButtonType> confirm = alert.showAndWait(); //czeka na wybór 
            if (confirm.get() == ok) {
                carTable.getItems().remove(selectedIndex);
                carPark.updateFile();
                carPark.initInfoBar(); //odświeża pasek na górze
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Wybierz samochód");
            alert.setHeaderText(null);
            alert.setContentText("Wybierz samochód do usunięcia");
            alert.showAndWait();
        }
    }
    public void printReport() {
        try {
            Integer fillPer = carPark.getCarData().size()*100/carPark.getParkingSpacesNum();
            Integer emptySp = carPark.getParkingSpacesNum()-carPark.getCarData().size();
            String toPdf = "Data drukowania raportu: "+stringifyDateTime(LocalDateTime.now())+System.lineSeparator()+
                    "Poziom zajętości: "+fillPer+"%"+System.lineSeparator()+
                    "Wolnych miejsc: "+emptySp+"/"+carPark.getParkingSpacesNum()+System.lineSeparator()
                    +System.lineSeparator()+"Dane według kolejności:"+System.lineSeparator()+
                    "Miejsce postoju, Numer rejestracyjny, Imię, Nazwisko, Numer telefonu, Czas wjazdu"
                    +System.lineSeparator();
            JTextPane textPane = new JTextPane();
            for(Car car : carPark.getCarData()) {
                toPdf+=System.lineSeparator()+car.getLocation()+"   "+
                        car.getRegNum()+"   "+car.getFirstName()+"   "+
                        car.getLastName()+"   "+car.getPhoneNumber()+"   "+
                        car.getStartDateTime()+System.lineSeparator();
            }
            textPane.setText(toPdf);
            textPane.print(null, null, false, null, null, false);
        } catch (PrinterException e) {
            e.printStackTrace(System.out);
        }
    } 
}
