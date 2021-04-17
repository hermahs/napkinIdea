package seternes.napkinIdea;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = loadFXML("applicationnew");
        scene = new Scene(loader.load(), 640, 480);
        scene.setFill(Color.GREY);
        stage.setScene(scene);
        // stage.setMinWidth(450);
        // stage.setMinHeight(300);
        // Screen screen = Screen.getPrimary();
        // Rectangle2D bounds = screen.getVisualBounds();
        // stage.setWidth(bounds.getWidth());
        // stage.setHeight(bounds.getHeight());

        stage.setMaximized(false);
        stage.show();
    }
    
    private static FXMLLoader loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader;
    }

    public static void main(String[] args) {
        launch();
    }

}