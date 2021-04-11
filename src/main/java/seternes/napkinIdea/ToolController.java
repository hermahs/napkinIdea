package seternes.napkinIdea;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import seternes.napkinIdea.Tools.*;

public class ToolController {
	private Tool[] tools = new Tool[ToolList.values().length];
	private ToolList tool;
	
	public ToolController(GraphicsContext gc) {
		this.tool = ToolList.PENCIL;
		
		
		//create tools in TOOL
		this.tools[ToolList.HAND.getValue()] = new Hand(gc);
		this.tools[ToolList.PENCIL.getValue()] = new Pencil(1, 1, Color.WHITE, gc);
	}
	
	public void changeTool(ToolList tool) {
		this.tool = tool;
	}
	
	public void startDraw(double x, double y) { 
		tools[tool.getValue()].startDraw(x, y);
	}

	public void dragDraw(double x, double y) {
		tools[tool.getValue()].dragDraw(x,y);
	}

	public void endDraw(double x, double y) {
		tools[tool.getValue()].endDraw(x, y);
	}

	public void setColor(Color c) {
		for(Tool t : this.tools) {
			t.setColor(c);
		}
	}

	public void setSize(float s) {
		for(Tool t : this.tools) {
			t.setSize(s);
		}
	}

	public void setOpacity(float o) {
		for(Tool t: this.tools) {
			t.setOpacity(o);
		}
	}
}
