package seternes.napkinIdea;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import seternes.napkinIdea.Tools.*;

public class ToolController {
	private Tool[] tools = new Tool[ToolList.values().length];
	private ToolList tool;
	
	public ToolController(GraphicsContext gc) {
		this.tool = ToolList.HAND;
		
		
		//create tools in TOOL
		this.tools[ToolList.HAND.getValue()] = new Hand(gc);
		this.tools[ToolList.PENCIL.getValue()] = new Pencil(0, 0, Color.WHITE, gc);
	}
	
	public void changeTool(ToolList tool) {
		this.tool = tool;
	}
	
	public void draw() {
		tools[tool.getValue()].draw();
	}
}
