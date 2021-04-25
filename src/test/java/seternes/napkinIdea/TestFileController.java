package seternes.napkinIdea;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TestFileController extends ApplicationTest {

    private FileController fc;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = App.loadFXML("drawingApp");

        stage.setScene(new Scene(loader.load()));
        fc = ((DrawingController) loader.getController()).getFileController();
        stage.show();
    }

    // @BeforeEach
    // public void setup() {
    //     Canvas canvas = new Canvas();
    //     GraphicsContext gc = canvas.getGraphicsContext2D();
    //     canvasContainer = new CanvasContainer();
    //     ToolController tc = new ToolController(gc, canvasContainer);
    //     canvasContainer.init(canvas, tc, gc);
    //     fc = new FileController(canvasContainer);
    // }

    @Test
    @DisplayName("Test open file")
    public void testOpenFile() throws FileNotFoundException, InterruptedException {

        CanvasContainer cc = (CanvasContainer) lookup("#subscene").queryAllAs(SubScene.class).stream().findFirst().get().getRoot();
        
        File file = new File(TestFileController.class.getResource("testOpenFile.jpg").getFile());
        FileInputStream stream;

        try {
            stream = new FileInputStream(file);
            System.out.println(stream);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("BIG ERROR");
            return;
        }

        Image image = new Image(stream);
        fc.openFile(file);
        assertEquals(image.getWidth(), cc.getCanvas().getWidth());
        assertEquals(image.getHeight(), cc.getCanvas().getHeight());

        
        assertThrows(FileNotFoundException.class, () -> {
            fc.openFile(null);
        });
    }

    @Test
    @DisplayName("test save file")
    public void testSaveFile() throws IOException {

        Platform.runLater(() -> {
            CanvasContainer cc = (CanvasContainer) lookup("#subscene").queryAllAs(SubScene.class).stream().findFirst().get().getRoot();
            cc.setCanvasSize(500, 500);
            File file;
            try {
                file = new File("src/main/resources/seternes/napkinIdea/testSaveFile.png");
                if(file.createNewFile()) {
                    System.out.println("File created");
                } else {
                    System.out.println("File not created");
                }
            } catch(Exception e) {
                e.printStackTrace();
                return;
            }
            
            FileInputStream stream;
            cc.getGC().setFill(Color.BLUE);
            cc.getGC().fillRect(0, 0, cc.getCanvas().getWidth(), cc.getCanvas().getHeight());
            try {
                fc.saveFile(file);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            file = null;

            sleep(1500, TimeUnit.MILLISECONDS);

            file = new File(TestFileController.class.getResource("testSaveFile.png").getFile());
            try {
                stream = new FileInputStream(file);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("BIG ERROR");
                return;
            }

            Image image = new Image(stream);
            //System.out.println(image.getHeight());
            assertEquals(500, image.getWidth());
            assertEquals(500, image.getHeight()); 
        });

        
    }

    @AfterAll
    public static void cleanUp() throws InterruptedException {
        Thread.sleep(1000);
        File file = new File("src/main/resources/seternes/napkinIdea/testSaveFile.png");
        if(file.delete()) {
            System.out.println("file deleted");
        } else {
            System.out.println("file not deleted");
        }
    }

}