
package carpark;

import carpark.Model.Car;
import carpark.View.AddEditDialogController;
import carpark.View.ListViewController;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CarPark extends Application {
    public Stage primaryStage;
    public BorderPane rootLayout;
    public AnchorPane listView;
    public AnchorPane mapView;
    public AnchorPane closeModal;
    
    @FXML private Button listBtn;
    @FXML private Button mapBtn;
    @FXML private Button closeBtn;
    
    private ObservableList<Car> carData = FXCollections.observableArrayList();
    
    public ObservableList<Car> getCarData() {
        return carData;
    }
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        loadFromFile();
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("System zarządzania parkingiem");
        initRootLayout();
        showListView();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public void loadFromFile() {
        //LOADING FROM FILES HERE
        //DOESN`T SEEM TO READ CORRECTLY
        try {
            FileReader fileReader = new FileReader("./src/carpark/DB/carDB.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while(bufferedReader.readLine()!=null) { //for some reason this while skips odd lines ?
                String oneLine = bufferedReader.readLine();
                if (oneLine!=null) { // to check if the line is not empty 
                    //splits the line string using regex pattern 
                    String lineSeparated[] = oneLine.split(";");
                    Integer location = Integer.parseInt(lineSeparated[0]);
                    String regNum = lineSeparated[1];
                    String make = lineSeparated[2];
                    String model = lineSeparated[3];
                    String firstName = lineSeparated[4];
                    String lastName = lineSeparated[5];
                    String phoneNumber = lineSeparated[6];
                    LocalDateTime startTime = parseDateTime(lineSeparated[7]);
                    //makes a car object from from the text file data
                    carData.add(new Car(location, regNum, make, model, firstName, lastName, phoneNumber, startTime));
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
    //required when loading date from textfile
    private LocalDateTime parseDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
        return localDateTime;
    }
    
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CarPark.class.getResource("View/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
    
    public void showListView() {
        try {
            //load view
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CarPark.class.getResource("View/ListView.fxml"));
            listView = (AnchorPane) loader.load();
            //set view
            rootLayout.setCenter(listView);
            //provide controller access to the main class
            ListViewController controller = loader.getController();
            controller.setCarPark(this);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        } 
    }
    public void showAddEditDialog(Car car) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CarPark.class.getResource("View/AddEditDialog.fxml"));
            //load view from fxml
            AnchorPane addEditDialog = loader.load();
            //create dialog stage
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Dodaj/Edytuj samochód");
            //initializes modality - this stage will be "owned" by primary stage
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            //create scene
            Scene scene = new Scene(addEditDialog);
            dialogStage.setScene(scene);
            
            AddEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setCarPark(this); //to let controller add and remove from observable list
            controller.setCar(car);
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
    public void addToCarData(Car car) {
        carData.add(car);
    }
    public void removeFromCarData(Car car) {
        carData.remove(car);
    }
    public void showMapView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CarPark.class.getResource("View/MapView.fxml"));
            mapView = (AnchorPane) loader.load();
            rootLayout.setCenter(mapView);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
    
    @FXML
    private void listButtonAction(ActionEvent event){
        primaryStage = (Stage) listBtn.getScene().getWindow(); //because action event creates anonymous inner class
        rootLayout = (BorderPane) listBtn.getParent().getParent();
        showListView();
    }
    @FXML
    private void mapButtonAction(ActionEvent event){
        primaryStage = (Stage) mapBtn.getScene().getWindow(); //because action event creates anonymous inner class
        rootLayout = (BorderPane) mapBtn.getParent().getParent();
        showMapView();
    }
    @FXML
    private void closeButtonAction(ActionEvent event) {
        primaryStage = (Stage) closeBtn.getScene().getWindow();
        rootLayout = (BorderPane) closeBtn.getParent().getParent();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Zamknij program");
        alert.setHeaderText(null);
        alert.setContentText("Czy jesteś pewien, że chcesz zamknąć program?");
        Optional<ButtonType> confirm = alert.showAndWait();
        if (confirm.get() == ButtonType.OK) {
            System.exit(0);
        }
    }
}
