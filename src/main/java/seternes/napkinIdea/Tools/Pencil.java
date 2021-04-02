package seternes.napkinIdea.Tools;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Pencil implements Tool {

	private float size = 1;
	private float opacity = 1;
	private Color color;
	private GraphicsContext gc;
	
	public Pencil(float s, float o, Color c, GraphicsContext gc) {
		// TODO implement
	}
	
	@Override
	public void draw() {
		// TODO implement
	}

	@Override
	public void setSize(float s) throws IllegalArgumentException {
		if(s < 0) throw new IllegalArgumentException("size can not be negative");
		if(s > 255) throw new IllegalArgumentException("size can not be greater than 255");
		
		this.size = s;
	}

	@Override
	public void setOpacity(float o) throws IllegalArgumentException {
		if(o < 0) throw new IllegalArgumentException("Opacity can not be negative");
		else if(o > 1) throw new IllegalArgumentException("Opacity can not be greater than 1");
		
		this.opacity = o;
	}

	@Override
	public float getSize() {
		return this.size;
	}

	@Override
	public float getOpacity() {
		return this.opacity;
	}

	@Override
	public void setColor(Color c) {
		this.color = c;
	}

	@Override
	public Color getColor() {
		return this.color;
	}

}
