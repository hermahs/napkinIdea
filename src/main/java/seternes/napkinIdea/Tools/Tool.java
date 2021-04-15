package seternes.napkinIdea.Tools;

import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import seternes.napkinIdea.Layer;

public interface Tool {
	public void setSize(float s);
	public void setColor(Color c);
	public float getSize();
	public Color getColor();

	public void handleOnMousePressedEvent(MouseEvent event);
	public void handleOnMouseDraggedEvent(MouseEvent event);
	public void handleOnMouseReleasedEvent(MouseEvent event);
	public void reDraw(Layer l);
	//public void reDraw(float s, Color c, ArrayList<Pair<Double, Double>> data);
}
