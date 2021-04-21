package seternes.napkinIdea;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChangeController {
    
    public static void changeController(TextField width, TextField height) {
        double w = 200;
        double h = 200;

        try {
            w = Double.parseDouble(width.getText());
            h = Double.parseDouble(height.getText());
        } catch(Exception e) {
            System.out.println("Width or height input was not an integer");
        }

        // get other controller
        try {
            FXMLLoader loader = App.loadFXML("drawingApp");

            Stage stage = new Stage();
            
            stage.setScene(new Scene(loader.load(), 640, 480));

            DrawingController controller = loader.getController();
            controller.initCanvasSize(w, h);

            stage.show();

            Stage ownStage = (Stage) width.getScene().getWindow();
            ownStage.close();

        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Cannot find controller? Who deleted it!!");
            System.exit(-1);
        }
    }

}
