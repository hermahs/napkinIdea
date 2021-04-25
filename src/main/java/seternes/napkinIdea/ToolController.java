package seternes.napkinIdea;

import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import seternes.napkinIdea.Tools.*;

public class ToolController {
	private Tool[] tools = new Tool[ToolList.values().length];
	private ToolList tool;
	private HistoryController hc = new HistoryController();
	private GraphicsContext gc;
	private Boolean isImage = false;
	private Image img;

	//history data to send to historyController
	private ArrayList<Pair<Double, Double>> xyData = new ArrayList<Pair<Double, Double>>();
	
	public ToolController(GraphicsContext gc, CanvasContainer canvasContainer) {
		if(!(gc instanceof GraphicsContext) || !(canvasContainer instanceof CanvasContainer)) throw new IllegalArgumentException();
		this.gc = gc;
		this.tool = ToolList.PENCIL;
		
		//create tools in TOOL
		this.tools[ToolList.HAND.getValue()] = new Hand(canvasContainer, gc);
		this.tools[ToolList.PENCIL.getValue()] = new Pencil(2, Color.WHITE, gc);
		this.tools[ToolList.BOX.getValue()] = new Rectangle(Color.WHITE, gc);
		this.tools[ToolList.ELLIPSE.getValue()] = new Ellipse(Color.WHITE, gc);
		this.tools[ToolList.ERASER.getValue()] = new Eraser(2, gc);
	}
	
	public void changeTool(ToolList tool) {
		if(!(tool instanceof ToolList)) throw new IllegalArgumentException();
		this.tool = tool;
	}

	public void setColor(Color c) {
		for(Tool t : this.tools) {
			t.setColor(c);
		}
	}

	public void setSize(float s) {
		if(s < 1 || s > 50) throw new IllegalArgumentException();
		for(Tool t : this.tools) {
			t.setSize(s);
		}
	}

	public ToolList getCurrentTool() {
		return this.tool;
	}

	public Tool getTool() {
		return this.tools[this.tool.getValue()];
	}

	public HistoryController getHistoryController() {
		return this.hc;
	}

	public void undo() {
		if(this.hc.getUndoHistory().isEmpty()) return;
		if(!this.isImage) {
			this.gc.setFill(Color.WHITE);
			this.gc.fillRect(0, 0, this.gc.getCanvas().getWidth(), this.gc.getCanvas().getHeight());
		} else {
			this.gc.drawImage(img, 0, 0, this.gc.getCanvas().getWidth(), this.gc.getCanvas().getHeight());
		}
		this.hc.pushRedoHistory(this.hc.popUndoHistory());

		if(!this.hc.getTotalHistory().isEmpty()) {
			this.hc.getTotalHistory().stream().forEach((l) -> {
				l.getTool().reDraw(l);
			});
		}

		if(!this.hc.getUndoHistory().isEmpty()) {
			this.hc.getUndoHistory().stream().forEach(l -> {
				l.getTool().reDraw(l);
			});
		}
	}

	public void redo() {
		if(this.hc.getRedoHistory().isEmpty()) return;
		if(!this.isImage) {
			this.gc.setFill(Color.WHITE);
			this.gc.fillRect(0, 0, this.gc.getCanvas().getWidth(), this.gc.getCanvas().getHeight());
		} else {
			this.gc.drawImage(img, 0, 0, this.gc.getCanvas().getWidth(), this.gc.getCanvas().getHeight());
		}
		this.hc.pushUndoHistory(this.hc.popRedoHistory());

		if(!this.hc.getTotalHistory().isEmpty()) {
			this.hc.getTotalHistory().stream().forEach((l) -> {
				l.getTool().reDraw(l);
			});
		}

		if(!this.hc.getUndoHistory().isEmpty()) {
			this.hc.getUndoHistory().stream().forEach(l -> {
				l.getTool().reDraw(l);
			});
		}
	}

	public void drawImage() {
		this.gc.drawImage(img, 0, 0, this.gc.getCanvas().getWidth(), this.gc.getCanvas().getHeight());
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

	private EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			// send data to history here
			xyData.add(new Pair<Double, Double>(event.getX(), event.getY()));
			tools[tool.getValue()].handleOnMousePressedEvent(event);
		}
	};

	private EventHandler<MouseEvent> onMouseDraggedEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			xyData.add(new Pair<Double, Double>(event.getX(), event.getY()));
			tools[tool.getValue()].handleOnMouseDraggedEvent(event);
		}
	};

	private EventHandler<MouseEvent> onMouseReleasedEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			xyData.add(new Pair<Double, Double>(event.getX(), event.getY()));
			ArrayList<Pair<Double, Double>> _c = (ArrayList<Pair<Double, Double>>) xyData.clone();
			xyData.clear();
			hc.pushUndoHistory(new Layer(tools[tool.getValue()], tools[tool.getValue()].getSize(), tools[tool.getValue()].getColor(), _c));
			tools[tool.getValue()].handleOnMouseReleasedEvent(event);
		}
	};

	@Override
	public String toString() {
		return String.format("ToolController. Tools:%s", this.tools.toString());
	}

	public void setImage(Image img) {
		this.isImage = true;
		this.img = img;
	}

	public Image getImage() {
		return this.img;
	}

}
