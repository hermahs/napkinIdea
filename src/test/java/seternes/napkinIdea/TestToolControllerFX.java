package seternes.napkinIdea;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import seternes.napkinIdea.Tools.ToolList;

public class TestToolControllerFX extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(App.loadFXML("drawingApp").load()));
        stage.show();
    }

    @Test
    @DisplayName("test change to tools")
    public void testChangeTools() {
        //CanvasContainer cc = (CanvasContainer) lookup("#subscene").queryAllAs(SubScene.class).stream().findFirst().get().getRoot();
        //ToolController tc = cc.getToolController();
        ToolController tc = ((CanvasContainer) lookup("#subscene").queryAllAs(SubScene.class).stream().findFirst().get().getRoot()).getToolController();
        clickOn("#moveButton");
        assertEquals(ToolList.HAND, tc.getCurrentTool());
        clickOn("#pencilButton");
        assertEquals(ToolList.PENCIL, tc.getCurrentTool());
        clickOn("#boxButton");
        assertEquals(ToolList.BOX, tc.getCurrentTool());
        clickOn("#circleButton");
        assertEquals(ToolList.ELLIPSE, tc.getCurrentTool());
        clickOn("#eraserButton");
        assertEquals(ToolList.ERASER, tc.getCurrentTool());
    }

    @Test
    @DisplayName("test change color")
    public void testChangeColor() {
        clickOn("#colorPicker");
        press(KeyCode.RIGHT);
        sleep(100);
        press(KeyCode.RIGHT);
        sleep(100);
        press(KeyCode.RIGHT);
        sleep(100);
        press(KeyCode.RIGHT);
        sleep(100);
        press(KeyCode.ENTER);
        assertEquals(Color.web("999999"), lookup("#colorPicker").queryAs(ColorPicker.class).getValue());
    }
}
