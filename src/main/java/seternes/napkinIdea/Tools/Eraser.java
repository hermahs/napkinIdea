package seternes.napkinIdea.Tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import seternes.napkinIdea.Layer;

public class Eraser implements Tool {

    private float size;
    private GraphicsContext gc;

    public Eraser(float s, GraphicsContext gc) {
        this.size = s;
        this.gc = gc;
    }

    @Override
    public void setSize(float s) {
        if(s < 0) throw new IllegalArgumentException("size can not be negative");
		if(s > 50) throw new IllegalArgumentException("size can not be greater than 50");
        this.size = s;
        this.gc.setLineWidth(s);
    }

    @Override
    public void setColor(Color c) {
        return;
    }

    @Override
    public float getSize() {
        return this.size;
    }

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public void handleOnMousePressedEvent(MouseEvent event) {
        this.gc.setStroke(Color.WHITE);
        this.gc.beginPath();
        this.gc.lineTo(event.getX(), event.getY());
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
		this.gc.setStroke(Color.WHITE);
		this.gc.beginPath();
		for(Pair<Double, Double> p : l.getData()) {
			this.gc.lineTo(p.getKey(), p.getValue());
			this.gc.stroke();
		}
		this.gc.closePath();
    }
    
}
