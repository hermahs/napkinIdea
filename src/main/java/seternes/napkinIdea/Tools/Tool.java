package seternes.napkinIdea.Tools;

import javafx.scene.paint.Color;

public interface Tool {
	public void startDraw(double x, double y);
	public void dragDraw(double x, double y);
	public void endDraw(double x, double y);
	public void setSize(float s);
	public void setOpacity(float o);
	public void setColor(Color c);
	public float getSize();
	public float getOpacity();
	public Color getColor();
}
