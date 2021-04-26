package seternes.napkinIdea;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.DisplayName;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.stage.Stage;

public class TestChangeController extends ApplicationTest {

    private Parent parentOpen;

    @Override
    public void start(Stage stage) throws Exception {
        parentOpen = App.loadFXML("openApp").load();
        stage.setScene(new Scene(parentOpen));
        stage.show();
    }

    @Test
    @DisplayName("test change to main window no input")
    public void testChangeControllerBasic() throws InterruptedException {
        // forst uten noe -> canvas er 200x200
        clickOn("#createButton");
        //Thread.sleep(3000);
        CanvasContainer cc = (CanvasContainer) lookup("#subscene").queryAllAs(SubScene.class).stream().findFirst().get().getRoot();
        assertEquals(200, cc.getCanvas().getWidth());
        assertEquals(200, cc.getCanvas().getHeight());
    }

    @Test
    @DisplayName("test change to main window, less than 200")
    public void testChangeControllerLess() {
        // forst uten noe -> canvas er 200x200
        clickOn("#widthInput").write("100");
        clickOn("#heightInput").write("100");
        clickOn("#createButton");
        //Thread.sleep(3000);
        CanvasContainer cc = (CanvasContainer) lookup("#subscene").queryAllAs(SubScene.class).stream().findFirst().get().getRoot();
        assertEquals(200, cc.getCanvas().getWidth());
        assertEquals(200, cc.getCanvas().getHeight());
    }

    @Test
    @DisplayName("test change main window, more than 200")
    public void testChangeControllerMore() {
        clickOn("#widthInput").write("500");
        clickOn("#heightInput").write("500");
        clickOn("#createButton");
        //Thread.sleep(3000);
        CanvasContainer cc = (CanvasContainer) lookup("#subscene").queryAllAs(SubScene.class).stream().findFirst().get().getRoot();
        assertEquals(500, cc.getCanvas().getWidth());
        assertEquals(500, cc.getCanvas().getHeight());
    }

    @Test
    @DisplayName("test change main window, input with text")
    public void testChangeControllerText() {
        clickOn("#widthInput").write("3s0f0");
        clickOn("#heightInput").write("3d00ds");
        clickOn("#createButton");
        //Thread.sleep(3000);
        CanvasContainer cc = (CanvasContainer) lookup("#subscene").queryAllAs(SubScene.class).stream().findFirst().get().getRoot();
        assertEquals(300, cc.getCanvas().getWidth());
        assertEquals(300, cc.getCanvas().getHeight());
    }

}
