
package carpark;

import carpark.Model.Car;
import carpark.View.AddEditDialogController;
import carpark.View.ListViewController;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
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
            String oneLine = "";
            int i = 0;
            while (bufferedReader.readLine() != null) {
                i++;
                System.out.println(oneLine+i); //TESTING
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        //TEST
        carData.add(new Car(12,"WE34553", "BMW", "e38", "Jan", "Kowalski", 605444222, LocalDateTime.now())); //test
        carData.add(new Car(4, "KR73829", "BMW", "e34", "Janka", "Kowalska", 605444222, LocalDateTime.now())); //test
        carData.add(new Car(6, "KR256SE", "AUDI", "a4", "Grzegorz", "Szcześniak", 605444222, LocalDateTime.of(2004, Month.MARCH, 23, 17, 54))); //test
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
