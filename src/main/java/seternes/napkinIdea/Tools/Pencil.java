package seternes.napkinIdea.Tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import seternes.napkinIdea.Layer;

public class Pencil implements Tool {

	private float size;
	private Color color;
	private GraphicsContext gc;
	
	public Pencil(float s, Color c, GraphicsContext gc) {
		this.size = s;
		this.color = c;
		this.gc = gc;
	}

	@Override
	public void setSize(float s) throws IllegalArgumentException {
		if(s < 0) throw new IllegalArgumentException("size can not be negative");
		if(s > 50) throw new IllegalArgumentException("size can not be greater than 50");
		
		this.size = s;
		this.gc.setLineWidth(s);
	}

	@Override
	public float getSize() {
		return this.size;
	}

	@Override
	public void setColor(Color c) {
		this.color = c;
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public void handleOnMousePressedEvent(MouseEvent event) {
		this.gc.setStroke(this.color);
		this.gc.beginPath();
		this.gc.lineTo(event.getX(),event.getY());
		event.consume();
	}

	@Override
	public void handleOnMouseDraggedEvent(MouseEvent event) {
		this.gc.lineTo(event.getX(),event.getY());
		this.gc.stroke();
		event.consume();
	}

	@Override
	public void handleOnMouseReleasedEvent(MouseEvent event) {
		this.gc.lineTo(event.getX(), event.getY());
		this.gc.stroke();
		this.gc.closePath();
		event.consume();
	}

	@Override
	public void reDraw(Layer l) {
		this.gc.moveTo(l.getData().get(0).getKey(), l.getData().get(0).getValue());
		this.gc.setLineWidth(l.getSize());
		this.gc.setStroke(l.getColor());
		this.gc.beginPath();
		for(Pair<Double, Double> p : l.getData()) {
			this.gc.lineTo(p.getKey(), p.getValue());
			this.gc.stroke();
		}
		this.gc.closePath();
	}
}
