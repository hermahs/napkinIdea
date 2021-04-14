package seternes.napkinIdea.Tools;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;

public interface Tool {
	public void setSize(float s);
	public void setOpacity(float o);
	public void setColor(Color c);
	public float getSize();
	public float getOpacity();
	public Color getColor();

	public void handleOnMousePressedEvent(MouseEvent event);
	public void handleOnMouseDraggedEvent(MouseEvent event);
	public void handleOnMouseReleasedEvent(MouseEvent event);
	public void handleOnScrollEvent(ScrollEvent event);
}
