package seternes.napkinIdea.Tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Hand implements Tool {

	private GraphicsContext gc;
	
	public Hand(GraphicsContext gc) {
		this.gc = gc;
	}
	
	@Override
	public void draw() {
		// TODO implement panning functionality here
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

}
