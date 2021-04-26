package seternes.napkinIdea;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

public class TestHistoryControllerFX extends ApplicationTest {
    
    private HistoryController hc;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = App.loadFXML("drawingApp");
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    @Test
    @DisplayName("test undo")
    public void testUndo() {
        Canvas c = (Canvas) lookup("#subscene").queryAllAs(SubScene.class).stream().findFirst().get().getRoot().lookup("#canvas");
        hc = ((CanvasContainer) lookup("#subscene").queryAllAs(SubScene.class).stream().findFirst().get().getRoot()).getToolController().getHistoryController();
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
        sleep(500);
        moveTo(c);
        press(MouseButton.PRIMARY);
        sleep(100);
        moveTo(800, 500);
        sleep(100);
        release(MouseButton.PRIMARY);
        sleep(100);
        assertEquals(1, hc.getUndoHistorySize());
        assertEquals(0, hc.getRedoHistorySize());
        sleep(100);
        clickOn("#undoButton");
        sleep(100);
        assertEquals(0, hc.getUndoHistorySize());
        assertEquals(1, hc.getRedoHistorySize());
    }

    @Test
    @DisplayName("test redo button")
    public void testRedo() {
        Canvas c = (Canvas) lookup("#subscene").queryAllAs(SubScene.class).stream().findFirst().get().getRoot().lookup("#canvas");
        hc = ((CanvasContainer) lookup("#subscene").queryAllAs(SubScene.class).stream().findFirst().get().getRoot()).getToolController().getHistoryController();
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
        sleep(500);
        moveTo(c);
        press(MouseButton.PRIMARY);
        sleep(100);
        moveTo(800, 500);
        sleep(100);
        release(MouseButton.PRIMARY);
        sleep(100);
        clickOn("#undoButton");
        sleep(100);
        assertEquals(0, hc.getUndoHistorySize());
        assertEquals(1, hc.getRedoHistorySize());
        sleep(100);
        clickOn("#redoButton");
        sleep(100);
        assertEquals(1, hc.getUndoHistorySize());
        assertEquals(0, hc.getRedoHistorySize());
    }

}
