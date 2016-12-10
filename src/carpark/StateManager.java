///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package carpark;
//
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Group;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
///**
// *
// * @author MW5
// */
//public class StateManager {
//    private Group root;
//    public Stage stage;
//    public Parent listState;
//    public Parent mapState;
//    public Parent menuAddModal;
//    public final String states[] = {"listState", "mapState"};
//    public String currentState;
//    public final int LISTSTATE = 0;
//    public final int MAPSTATE = 1;
//    
//    public StateManager(Stage stage) throws Exception {
//                //creates a group of elements
//        root = new Group();
//        this.stage = stage; 
//        listState = FXMLLoader.load(getClass().getResource("View/ListView.fxml"));
//        mapState = FXMLLoader.load(getClass().getResource("View/MapView.fxml"));
//        menuAddModal = FXMLLoader.load(getClass().getResource("View/MenuAddModal.fxml"));
//        //sets primary state
//        currentState = states[LISTSTATE];
//    }
//    public void setState(String newState) {
//        this.currentState = newState;
//    }
//    
//    public Parent getListView() {
//        return listState;
//    }
//    public Parent getMapView() {
//        return mapState;
//    }
//    public Parent getMenuAddModal() {
//        return menuAddModal;
//    }
//    
//    public void prepareView() {
//        if(currentState.equals(states[LISTSTATE])) {
//            root.getChildren().add(getListView());
//        }
//        if(currentState.equals(states[MAPSTATE])) {
//            root.getChildren().add(getListView());
//        }
//        Scene scene = new Scene(root);
//        stage.setTitle("Program do zarządzania przestrzenią parkingową");
//        //test
//        stage.setScene(scene);
//        stage.show();
//    }
//}
