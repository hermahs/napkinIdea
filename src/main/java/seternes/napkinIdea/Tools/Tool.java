package seternes.napkinIdea.Tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import seternes.napkinIdea.Layer;

// public interface Tool {
// 	public void setSize(float s);
// 	public void setColor(Color c);
// 	public float getSize();
// 	public Color getColor();

// 	public void handleOnMousePressedEvent(MouseEvent event);
// 	public void handleOnMouseDraggedEvent(MouseEvent event);
// 	public void handleOnMouseReleasedEvent(MouseEvent event);
// 	public void reDraw(Layer l);
// }


public abstract class Tool {
    protected float size;
    protected Color color;
    protected GraphicsContext gc;

    public Tool(float s, Color c, GraphicsContext gc) {
        this.size = s;
        this.color = c;
        this.gc = gc;
    }

    public float getSize() {
        return this.size;
    }

    public Color getColor() {
        return this.color;
    }

    public void setSize(float s) {
        if(s < 0) throw new IllegalArgumentException("size can not be negative");
		if(s > 50) throw new IllegalArgumentException("size can not be greater than 50");
        this.size = s;
        this.gc.setLineWidth(s);
    }

    public void setColor(Color c) {
        this.color = c;
    }

    public abstract void handleOnMousePressedEvent(MouseEvent event);
    public abstract void handleOnMouseDraggedEvent(MouseEvent event);
    public abstract void handleOnMouseReleasedEvent(MouseEvent event);
    public abstract void reDraw(Layer l);
}
