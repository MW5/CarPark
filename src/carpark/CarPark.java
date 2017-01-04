
package carpark;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CarPark extends Application {
    public Stage primaryStage;
    public Group root; //test
    public BorderPane rootLayout;
    public AnchorPane listView;
    public AnchorPane mapView;
    public AnchorPane closeModal;
    @FXML private Button listBtn;
    @FXML private Button mapBtn;
    @FXML private Button closeBtn;
    @FXML private Button closeModalYesBtn;
    @FXML private Button closeModalNoBtn;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("System zarządzania parkingiem");
        this.root= new Group(); //test

        initRootLayout();
        showListView();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CarPark.class.getResource("View/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            root.getChildren().add(rootLayout);//test
            Scene scene = new Scene(root); //root layout before
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }
    
    public void showListView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CarPark.class.getResource("View/ListView.fxml"));
            listView = (AnchorPane) loader.load();
            rootLayout.setCenter(listView);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
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
    public void showCloseModal(){
        //to confirm close
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CarPark.class.getResource("View/CloseModal.fxml"));
            closeModal = (AnchorPane) loader.load();
            root.getChildren().add(closeModal);
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
        root = (Group) closeBtn.getParent().getParent().getParent(); //not cool but works :)
        showCloseModal();
    }
    @FXML
    private void yesCloseModalAction(ActionEvent event) {
        System.exit(0);
    }
    @FXML
    private void noCloseModalAction(ActionEvent event) {
        closeModal = (AnchorPane) closeModalNoBtn.getParent().getParent();
        root = (Group) closeModalNoBtn.getParent().getParent().getParent(); //not cool but works :)
        root.getChildren().remove(closeModal);
    }
}
