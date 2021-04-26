package seternes.napkinIdea;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import seternes.napkinIdea.Tools.Tool;

// layer klassen inneholder alt om layer, som er inneholder slags tegneoppgave som har blitt gjort og dataen om den. Blir brukt for Undo/Redo history
public class Layer {
    private Tool tool;
    private float size;
    private Color color;
    private ArrayList<Pair<Double, Double>> data = new ArrayList<Pair<Double, Double>>();

    public Layer(Tool tool, float size, Color color, ArrayList<Pair<Double,Double>> data) {
        if(!(tool instanceof Tool) || size < 1 || size > 50 || data.isEmpty()) throw new IllegalArgumentException();
        this.tool = tool;
        this.size = size;
        this.color = color;
        this.data = data;
    }

    public ArrayList<Pair<Double, Double>> getData() {
        return this.data;
    }

    public float getSize() {
        return this.size;
    }

    public Color getColor() {
        return this.color;
    }

    public Tool getTool() {
        return this.tool;
    }

    @Override
    public String toString() {
        return String.format("tool:%s, size:%f, color:%h, dataSize:%d", this.tool, this.size, this.color, this.data.size());
    }
}
