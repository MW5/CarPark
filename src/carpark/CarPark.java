
package carpark;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author MW5
 */
public class CarPark extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        ViewController vC = new ViewController();
        vC.init(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }  
}
