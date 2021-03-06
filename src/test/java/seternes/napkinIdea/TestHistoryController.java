package seternes.napkinIdea;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import seternes.napkinIdea.Tools.Pencil;

public class TestHistoryController {

    private HistoryController hc;

    @BeforeEach
    public void setup() {
        hc = new HistoryController();
    }

    @Test
    @DisplayName("test pushUndoHistory")
    public void testPushUndoHistory() {
        Canvas canvas = new Canvas();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        ArrayList<Pair<Double,Double>> data = new ArrayList<Pair<Double,Double>>();
        data.add(new Pair<Double, Double>(10.0,10.0));
        Layer l = new Layer(new Pencil(5, Color.BLUE, gc), 5, Color.BLUE, data);
        hc.pushUndoHistory(l);
        assertEquals(1, hc.getUndoHistorySize());
        assertEquals(l, hc.popUndoHistory());

        assertThrows(IllegalArgumentException.class, () -> {
            hc.pushUndoHistory(null);
        });
    }

    @Test
    @DisplayName("test pushRedoHistory")
    public void testPushRedoHistory() {
        Canvas canvas = new Canvas();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        ArrayList<Pair<Double,Double>> data = new ArrayList<Pair<Double,Double>>();
        data.add(new Pair<Double, Double>(10.0,10.0));
        Layer l = new Layer(new Pencil(5, Color.BLUE, gc), 5, Color.BLUE, data);
        hc.pushRedoHistory(l);
        assertEquals(1, hc.getRedoHistorySize());
        assertEquals(l, hc.popRedoHistory());

        assertThrows(IllegalArgumentException.class, () -> {
            hc.pushRedoHistory(null);
        });
    }

    @Test
    @DisplayName("test popUndoHistory")
    public void testPopUndoHistory() {
        Canvas canvas = new Canvas();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        ArrayList<Pair<Double,Double>> data = new ArrayList<Pair<Double,Double>>();
        data.add(new Pair<Double, Double>(10.0,10.0));
        Layer l = new Layer(new Pencil(5, Color.BLUE, gc), 5, Color.BLUE, data);
        hc.pushUndoHistory(l);
        assertEquals(l, hc.popUndoHistory());
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            hc.popUndoHistory();
        });
    }

    @Test
    @DisplayName("test popRedoHistory")
    public void testPopRedoHistory() {
        Canvas canvas = new Canvas();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        ArrayList<Pair<Double,Double>> data = new ArrayList<Pair<Double,Double>>();
        data.add(new Pair<Double, Double>(10.0,10.0));
        Layer l = new Layer(new Pencil(5, Color.BLUE, gc), 5, Color.BLUE, data);
        hc.pushRedoHistory(l);
        assertEquals(l, hc.popRedoHistory());
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            hc.popRedoHistory();
        });
    }

    @Test
    @DisplayName("test getIndexUndoHistory")
    public void testGetIndexUndoHistory() {
        Canvas canvas = new Canvas();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        ArrayList<Pair<Double,Double>> data = new ArrayList<Pair<Double,Double>>();
        data.add(new Pair<Double, Double>(10.0,10.0));
        Layer l = new Layer(new Pencil(5, Color.BLUE, gc), 5, Color.BLUE, data);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            hc.getIndexUndoHistory(0);
        });
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            hc.getIndexUndoHistory(5);
        });
        hc.pushUndoHistory(l);
        assertEquals(l, hc.getIndexUndoHistory(0));
    }

    @Test
    @DisplayName("test getIndexRedoHistory")
    public void testGetIndexRedoHistory() {
        Canvas canvas = new Canvas();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        ArrayList<Pair<Double,Double>> data = new ArrayList<Pair<Double,Double>>();
        data.add(new Pair<Double, Double>(10.0,10.0));
        Layer l = new Layer(new Pencil(5, Color.BLUE, gc), 5, Color.BLUE, data);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            hc.getIndexRedoHistory(0);
        });
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            hc.getIndexRedoHistory(5);
        });
        hc.pushRedoHistory(l);
        assertEquals(l, hc.getIndexRedoHistory(0));
    }

}
