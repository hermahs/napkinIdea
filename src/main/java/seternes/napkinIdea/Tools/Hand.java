package seternes.napkinIdea.Tools;

import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import seternes.napkinIdea.CanvasContainer;
import seternes.napkinIdea.Layer;

public class Hand extends Tool {

	private CanvasContainer canvasContainer;

	private double mouseX;
	private double mouseY;

	private double translateAnchorX;
	private double translateAnchorY;

	public Hand(CanvasContainer canvasContainer, GraphicsContext gc) {
		super(2, Color.WHITE, gc);
		this.canvasContainer = canvasContainer;
	}

	@Override
	public void handleOnMousePressedEvent(MouseEvent event) {

		if(!event.isPrimaryButtonDown()) return;

		this.mouseX = event.getSceneX();
		this.mouseY = event.getSceneY();

		Node node = (Node) event.getSource();

		this.translateAnchorX = node.getTranslateX();
		this.translateAnchorY = node.getTranslateY();
		event.consume();
	}

	@Override
	public void handleOnMouseDraggedEvent(MouseEvent event) {

		if(!event.isPrimaryButtonDown()) return;

		this.canvasContainer.getChildren().get(0).setTranslateX(this.translateAnchorX + event.getSceneX() - this.mouseX);
		this.canvasContainer.getChildren().get(0).setTranslateY(this.translateAnchorY + event.getSceneY() - this.mouseY);

		event.consume();
	}

	@Override
	public void handleOnMouseReleasedEvent(MouseEvent event) {
		event.consume();
	}

	@Override
	public void reDraw(Layer l) {
		return;	
	}
}
