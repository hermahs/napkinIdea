package seternes.napkinIdea;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = loadFXML("openApp");
        scene = new Scene(loader.load(), 640, 480);
        stage.setScene(scene);

        stage.setMaximized(false);
        stage.show();
    }
    
    public static FXMLLoader loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader;
    }

    public static void main(String[] args) {
        launch();
    }

    public Scene getScene() {
        return scene;
    }

    // static void setRoot(String fxml) throws IOException {
    //     scene.setRoot(loadFXML(fxml).load);
    // }

    

}