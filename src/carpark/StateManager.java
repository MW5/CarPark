/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carpark;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;

/**
 *
 * @author MW5
 */
public class StateManager {
    public Parent listState;
    public Parent mapState;
    public Parent menuAddModal;
    public final String states[] = {"listState", "mapState"};
    public String currentState;
    
    public StateManager() throws Exception {
        listState = FXMLLoader.load(getClass().getResource("View/ListView.fxml"));
        mapState = FXMLLoader.load(getClass().getResource("View/MapView.fxml"));
        menuAddModal = FXMLLoader.load(getClass().getResource("View/MenuAddModal.fxml"));
        //sets primary state
        currentState = states[0];
    }
    
    public Parent getListView() {
        return listState;
    }
    public Parent getMapView() {
        return mapState;
    }
    public Parent getMenuAddModal() {
        return menuAddModal;
    }
    
    public Group prepareView(Group root) {
        root.getChildren().add(getListView());
        return root;
    }
}
