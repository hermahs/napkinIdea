package seternes.napkinIdea;

import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestCanvasContainer {

    private CanvasContainer canvasContainer;
    private ToolController toolController;
    private Canvas canvas;
    private GraphicsContext gc;

    @BeforeEach
    public void setUp() {
        canvas = new Canvas();
        gc = canvas.getGraphicsContext2D();
        canvasContainer = new CanvasContainer();
        toolController = new ToolController(gc, canvasContainer);
    }

    @Test
    @DisplayName("init Test")
    public void testInit() {
        // good case
        canvasContainer.init(canvas, toolController, gc);
        assertEquals(canvas, canvasContainer.getCanvas());
        assertEquals(gc, canvasContainer.getGC());
        assertEquals(toolController, canvasContainer.getToolController());
        // bad case
        assertThrows(IllegalArgumentException.class, () -> {
            canvasContainer.init(null, null, null);
        });
    }

    @Test
    @DisplayName("return canvas to center test")
    public void testReturnToCenter() {
        canvasContainer.init(canvas, toolController, gc);
        canvas.setTranslateX(100);
        canvas.setTranslateY(100);
        canvasContainer.returnCanvasToCenter();
        assertEquals(canvas.getTranslateX(), (canvasContainer.getWidth()/2 - canvas.getWidth()/2));
        assertEquals(canvas.getTranslateY(), (canvasContainer.getHeight()/2 - canvas.getHeight()/2));
    }

    @Test
    @DisplayName("test canvas center")
    public void testSetCanvasScale() {
        canvasContainer.init(canvas, toolController, gc);
        canvasContainer.setCanvasSize(500, 500);
        assertEquals(500, canvas.getWidth());
        assertEquals(500, canvas.getHeight());
        canvasContainer.setCanvasSize(100, 100);
        assertEquals(200, canvas.getWidth());
        assertEquals(200, canvas.getHeight());
    }
}
