package seternes.napkinIdea;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import seternes.napkinIdea.PannableCanvas.PannableCanvas;
import seternes.napkinIdea.PannableCanvas.SceneGestures;

public class TestController implements Initializable {
    // fxml data
    @FXML private Canvas canvas;
    @FXML private ColorPicker colorPicker;
    @FXML private Slider sizeSlider;
    @FXML private Label sizeLabel;
    @FXML private Pane canvasContainer;
    @FXML private Button handButton;
    @FXML private Button pencilButton;
    @FXML private Button boxButton;
    @FXML private Button circleButton;
    @FXML private Button eraserButton;
    @FXML private Button undoButton;
    @FXML private Button redoButton;

    // other data
    private GraphicsContext gc;
    private ToolController tc; // TODO lage alle "tools" som en tegne app trenger

    // TODO add panning and zooming to canvas

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        this.gc = this.canvas.getGraphicsContext2D();
        this.tc = new ToolController(this.gc, canvasContainer);

        // initialize canvas
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // drawing
        canvas.addEventFilter(MouseEvent.MOUSE_PRESSED, this.tc.getOnMousePressedEventHandler());
        canvas.addEventFilter(MouseEvent.MOUSE_DRAGGED, this.tc.getOnMouseDraggedEventHandler());
        canvas.addEventFilter(MouseEvent.MOUSE_RELEASED, this.tc.getOnMouseReleasedEventHandler());
        canvas.addEventFilter(ScrollEvent.ANY, this.tc.getOnScrollEventHandler());
        

        this.colorPicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                tc.setColor(colorPicker.getValue());
            }
        });

        // size slider
        this.sizeSlider.valueProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observedvalue, Number oldValue, Number newValue) {
                tc.setSize(newValue.floatValue());
                sizeLabel.textProperty().setValue(String.valueOf(newValue.intValue()));
            }
        });

        // opacity slider

    }
}
