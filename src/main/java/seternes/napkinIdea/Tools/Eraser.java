package seternes.napkinIdea.Tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import seternes.napkinIdea.Layer;

public class Eraser extends Tool {

    public Eraser(float s, GraphicsContext gc) {
        super(s, Color.WHITE, gc);
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
        l.getData().stream().forEach(p -> {
            System.out.println(p.getKey() + ":" + p.getValue());
            this.gc.lineTo(p.getKey(), p.getValue());
			this.gc.stroke();
        });
		this.gc.closePath();
    }
}
