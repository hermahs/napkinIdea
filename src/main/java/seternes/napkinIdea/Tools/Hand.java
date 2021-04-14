package seternes.napkinIdea.Tools;

import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import seternes.napkinIdea.PannableCanvas.DragContext;

public class Hand implements Tool {

	private Pane canvasContainer;
	private DragContext dragContext;

	public Hand(Pane canvasContainer) {
		this.canvasContainer = canvasContainer;
	}

	@Override
	public void setSize(float s) {
	}

	@Override
	public void setOpacity(float o) {
	}

	@Override
	public void setColor(Color c) {
	}

	@Override
	public float getSize() {
		return 0;
	}

	@Override
	public float getOpacity() {
		return 0;
	}

	@Override
	public Color getColor() {
		return null;
	}

	@Override
	public void handleOnMousePressedEvent(MouseEvent event) {
		// TODO Auto-generated method stub
		event.consume();
	}

	@Override
	public void handleOnMouseDraggedEvent(MouseEvent event) {
		// TODO Auto-generated method stub
		event.consume();
	}

	@Override
	public void handleOnMouseReleasedEvent(MouseEvent event) {
		// TODO Auto-generated method stub
		event.consume();
	}

	@Override
	public void handleOnScrollEvent(ScrollEvent event) {
		// TODO Auto-generated method stub
		event.consume();
	}


}
