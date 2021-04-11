package seternes.napkinIdea.Tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Pencil implements Tool {

	private float size;
	private float opacity;
	private Color color;
	private GraphicsContext gc;
	
	public Pencil(float s, float o, Color c, GraphicsContext gc) {
		this.size = s;
		this.opacity = o;
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

	@Override
	public void startDraw(double x, double y) {
		this.gc.setStroke(this.color);
		this.gc.beginPath();
		this.gc.lineTo(x,y);
	}

	@Override
	public void dragDraw(double x, double y) {
		this.gc.lineTo(x, y);
		this.gc.stroke();
	}

	@Override
	public void endDraw(double x, double y) {
		this.gc.lineTo(x, y);
		this.gc.stroke();
		this.gc.closePath();
	}

}
