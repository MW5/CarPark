/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carpark;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author MW5
 */
public class CarPark extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        StateManager r = new StateManager();
        

        //creates a group of elements
        Group root = new Group();

        
        Scene scene = new Scene(r.prepareView(root));
        stage.setTitle("Program do zarządzania przestrzenią parkingową");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }  
}
