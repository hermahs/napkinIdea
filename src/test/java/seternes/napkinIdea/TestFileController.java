package seternes.napkinIdea;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestFileController extends ApplicationTest {

    private Parent parent;
    private DrawingController app;

    @Override
    public void start(Stage stage) throws Exception {
        app = App.loadFXML("drawingApp").getController();
        parent = App.loadFXML("drawingApp").load();

        stage.setScene(new Scene(parent));
        stage.show();
    }



    // @Before
    // public void setup() throws Exception {
    //     Platform.runLater(() -> {
    //       try {
    //         Scene scene = new Scene(App.loadFXML("drawingApp").load(), 640, 480);
    //         stage.setScene(scene);
    //         stage.show();
    //       } catch (Exception e) {
    //         System.err.println(e);
    //       }
    //     });
    // }

    @Test
    @DisplayName("Test test")
    public void testTest() {
      System.out.println("Test");
      
    }
}
