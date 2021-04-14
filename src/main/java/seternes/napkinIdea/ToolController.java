package seternes.napkinIdea;

import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import seternes.napkinIdea.Tools.*;

public class ToolController {
	private Tool[] tools = new Tool[ToolList.values().length];
	private ToolList tool;
	
	public ToolController(GraphicsContext gc, Pane canvasContainer) {
		this.tool = ToolList.PENCIL;
		
		
		//create tools in TOOL
		this.tools[ToolList.HAND.getValue()] = new Hand(canvasContainer);
		this.tools[ToolList.PENCIL.getValue()] = new Pencil(1, 1, Color.WHITE, gc);
	}
	
	public void changeTool(ToolList tool) {
		this.tool = tool;
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

	public ToolList getCurrentTool() {
		return this.tool;
	}

	public EventHandler<MouseEvent> getOnMousePressedEventHandler() {
		return onMousePressedEventHandler;
	}

	public EventHandler<MouseEvent> getOnMouseDraggedEventHandler() {
		return onMouseDraggedEventHandler;
	}

	public EventHandler<MouseEvent> getOnMouseReleasedEventHandler() {
		return onMouseReleasedEventHandler;
	}

	public EventHandler<ScrollEvent> getOnScrollEventHandler() {
		return onScrollEventHandler;
	}

	private EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			tools[tool.getValue()].handleOnMousePressedEvent(event);
		}
	};

	private EventHandler<MouseEvent> onMouseDraggedEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			tools[tool.getValue()].handleOnMouseDraggedEvent(event);
		}
	};

	private EventHandler<MouseEvent> onMouseReleasedEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			tools[tool.getValue()].handleOnMouseReleasedEvent(event);
		}
	};

	private EventHandler<ScrollEvent> onScrollEventHandler = new EventHandler<ScrollEvent>() {

		@Override
		public void handle(ScrollEvent event) {
			tools[tool.getValue()].handleOnScrollEvent(event);
		}
	};
}
