package seternes.napkinIdea.Tools;

import javafx.scene.paint.Color;

public interface Tool {
	public void draw();
	public void setSize(float s);
	public void setOpacity(float o);
	public void setColor(Color c);
	public float getSize();
	public float getOpacity();
	public Color getColor();
}
