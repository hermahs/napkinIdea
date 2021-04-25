package seternes.napkinIdea;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import seternes.napkinIdea.Tools.ToolList;

public class TestToolController {
    
    private ToolController tc;

    @BeforeEach
    public void setup() {
        Canvas canvas = new Canvas();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        CanvasContainer canvasContainer = new CanvasContainer();
        tc = new ToolController(gc, canvasContainer);
    }

    @Test
    @DisplayName("constructor Test")
    public void testConstructor() {
        assertThrows(IllegalAccessException.class, () -> {
            tc = new ToolController(null, null);
        });
    }

    @Test
    @DisplayName("test change tool")
    public void testChangeTool() {
        tc.changeTool(ToolList.HAND);
        assertEquals(ToolList.HAND, tc.getCurrentTool());

        assertThrows(IllegalArgumentException.class, () -> {
            tc.changeTool(null);
        });
    }

    @Test
    @DisplayName("Test undo")
    public void testUndo() {
        tc.changeTool(ToolList.PENCIL);
        HistoryController hc = tc.getHistoryController();
        ArrayList<Pair<Double,Double>> data = new ArrayList<Pair<Double,Double>>();
        data.add(new Pair<Double, Double>(10.0,10.0));
        Layer l = new Layer(tc.getTool(), 5, Color.BLUE, data);
        hc.pushUndoHistory(l);
        assertEquals(0, hc.getUndoHistorySize());
        tc.undo();
        assertEquals(0, hc.getUndoHistorySize());
    }

    @Test
    @DisplayName("Test redo")
    public void testRedo() {
        tc.changeTool(ToolList.PENCIL);
        HistoryController hc = tc.getHistoryController();
        ArrayList<Pair<Double,Double>> data = new ArrayList<Pair<Double,Double>>();
        data.add(new Pair<Double, Double>(10.0,10.0));
        Layer l = new Layer(tc.getTool(), 5, Color.BLUE, data);
        hc.pushUndoHistory(l);
        assertEquals(0, hc.getRedoHistorySize());
        tc.undo();
        assertEquals(1, hc.getRedoHistorySize());
    }

}
