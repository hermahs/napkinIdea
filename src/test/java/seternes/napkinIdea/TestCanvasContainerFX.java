package seternes.napkinIdea;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.geometry.VerticalDirection;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class TestCanvasContainerFX extends ApplicationTest {
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(App.loadFXML("drawingApp").load()));
        stage.show();
    }

    @Test
    @DisplayName("test Center To cavnas FX")
    public void testCenterToCanvas() {
        Canvas c = (Canvas) lookup("#subscene").queryAllAs(SubScene.class).stream().findFirst().get().getRoot().lookup("#canvas");
        CanvasContainer cc = (CanvasContainer) lookup("#subscene").queryAllAs(SubScene.class).stream().findFirst().get().getRoot();
        moveTo(c);
        scroll(1, VerticalDirection.UP);
        sleep(100);
        clickOn("#editButtonContainer");
        sleep(100);
        clickOn("#centerCanvasButton");
        assertEquals(c.getTranslateX(), (cc.getWidth()/2 - c.getWidth()/2));
        assertEquals(c.getTranslateY(), (cc.getHeight()/2 - c.getHeight()/2));
    }

}
