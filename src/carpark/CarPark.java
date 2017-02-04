package carpark;

import carpark.Model.Car;
import carpark.View.AddEditDialogController;
import carpark.View.InfoBarController;
import carpark.View.ListViewController;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CarPark extends Application {
    public Stage primaryStage;
    public BorderPane rootLayout;
    public AnchorPane listView;
    public AnchorPane infoBar;
    public AnchorPane closeModal;
    private final Integer parkingSpacesNum = 50;
    
    private ObservableList<Car> carData = FXCollections.observableArrayList();
    
    public ObservableList<Car> getCarData() {
        return carData;
    }
    public void addToCarData(Car car) {
        carData.add(car);
    }
    public void removeFromCarData(Car car) {
        carData.remove(car);
    }
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    public Integer getParkingSpacesNum() {
        return parkingSpacesNum;
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        loadFromFile();
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("System zarządzania parkingiem");
        initRootLayout();
        initInfoBar();
        initListView();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public void loadFromFile() {
        try {
            FileReader fileReader = new FileReader("./src/carpark/DB/carDB.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while(bufferedReader.readLine()!=null) { //z jakiegoś powodu pomija nieparzyste linie
                String oneLine = bufferedReader.readLine();
                if (oneLine!=null) { // sprawdza czy nie dotarł do końca pliku
                    //za pomocą regexa rozdziela linię
                    String lineSeparated[] = oneLine.split(";");
                    Integer location = Integer.parseInt(lineSeparated[0]);
                    String regNum = lineSeparated[1];
                    String make = lineSeparated[2];
                    String model = lineSeparated[3];
                    String firstName = lineSeparated[4];
                    String lastName = lineSeparated[5];
                    String phoneNumber = lineSeparated[6];
                    LocalDateTime startTime = toLocalDateTime(lineSeparated[7]);
                    //tworzy obiekt na podstawie pobranych z pliku danych
                    carData.add(new Car(location, regNum, make, model, firstName, lastName, phoneNumber, startTime));
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
    public void updateFile() { //nadpisuje cały plik
        try {
            OutputStream out = new FileOutputStream(new File("./src/carpark/DB/carDB.txt"));
            String dataToWrite = "";
            for (Car car : carData) {
                dataToWrite += System.lineSeparator()+car.getLocation()+";"+car.getRegNum()+";"+
                car.getMake()+";"+car.getModel()+";"+car.getFirstName()+";"+
                car.getLastName()+";"+car.getPhoneNumber()+";"+car.getStartDateTime()+System.lineSeparator();
               
            }
            out.write(dataToWrite.getBytes(StandardCharsets.UTF_8)); //dzięki temu nie dodaje BOM (Byte Order Mark)
            out.close();
        } catch (IOException e) {
                e.printStackTrace(System.out);
        }
    }
    //zmienia typ pobranej z pliku daty
    private LocalDateTime toLocalDateTime(String dateTime) {
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
    public void initInfoBar() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CarPark.class.getResource("View/InfoBar.fxml"));
            infoBar = (AnchorPane) loader.load();
            rootLayout.setTop(infoBar);
            InfoBarController controller = loader.getController();
            controller.setCarPark(this);
            controller.updateSpaceInfo();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
    
    public void initListView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CarPark.class.getResource("View/ListView.fxml"));
            listView = (AnchorPane) loader.load();
            rootLayout.setCenter(listView);
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
            AnchorPane addEditDialog = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Dodaj/Edytuj samochód");
            //ustawia właściciela okna dialogowego
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(addEditDialog);
            dialogStage.setScene(scene);
            AddEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setCarPark(this); //klasa okna dialogowego musi mieć observable list carParku
            controller.setCar(car);
            controller.setLocationChoices();
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }  
}
