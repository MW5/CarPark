/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carpark.View;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MW5
 */
public class AddDialogController implements Initializable {

    @FXML
    private TextField addDialogLocation;
    @FXML
    private TextField addDialogRegNum;
    @FXML
    private TextField addDialogMake;
    @FXML
    private TextField addDialogModel;
    @FXML
    private TextField addDialogFirstName;
    @FXML
    private TextField addDialogLastName;
    @FXML
    private TextField addDialogPhoneNumber;
    
    private Stage dialogStage;
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void handleSave() {
        if (validateInput()) {
            System.out.println("ok"); //testing
        }
    }
    public void handleClose() {
        dialogStage.close();
    }
    private boolean validateInput() {
        return true; //testing
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
