package seternes.napkinIdea.Tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import seternes.napkinIdea.Layer;

public class ImageTool extends Tool {

    private Image img;

    public ImageTool(float s, Color c, GraphicsContext gc, Image img) {
        super(s, c, gc);
        this.img = img;
    }

    @Override
    public void handleOnMousePressedEvent(MouseEvent event) {
        event.consume();
    }

    @Override
    public void handleOnMouseDraggedEvent(MouseEvent event) {
        event.consume();
    }

    @Override
    public void handleOnMouseReleasedEvent(MouseEvent event) {
        event.consume();
    }

    @Override
    public void reDraw(Layer l) {
        this.gc.drawImage(img, 0, 0);
    }
    
}
