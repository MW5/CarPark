
package carpark;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author MW5
 */
public class CarPark extends Application {
    public Stage primaryStage;
    public BorderPane rootLayout;
    public AnchorPane listView;
    public AnchorPane mapView;
    @FXML private Button listBtn;
    @FXML private Button mapBtn;
    @FXML private Button closeBtn;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

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
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showListView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CarPark.class.getResource("View/ListView.fxml"));
            listView = (AnchorPane) loader.load();
            rootLayout.setCenter(listView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void listButtonAction(ActionEvent event) throws Exception {
        try {
            //it workds but it`s not very nice - should be changed
            primaryStage = (Stage) listBtn.getScene().getWindow(); //because action event creates anonymous inner class
            rootLayout = (BorderPane) listBtn.getParent().getParent(); ////because action event creates anonymous inner class
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CarPark.class.getResource("View/ListView.fxml"));
            listView = (AnchorPane) loader.load();
            rootLayout.setCenter(listView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void mapButtonAction(ActionEvent event) throws Exception {
        try {
            //it works but it`s not very nice - should be changed
            primaryStage = (Stage) mapBtn.getScene().getWindow(); //because action event creates anonymous inner class
            rootLayout = (BorderPane) mapBtn.getParent().getParent(); ////because action event creates anonymous inner class
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(CarPark.class.getResource("View/MapView.fxml"));
            mapView = (AnchorPane) loader.load();
            rootLayout.setCenter(mapView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void closeButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        //modal to confirm
    }
}
