package seternes.napkinIdea;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import seternes.napkinIdea.Tools.ToolList;

public class TestController implements Initializable {
    // fxml data
    @FXML private Canvas canvas;
    @FXML private ColorPicker colorPicker;
    @FXML private Slider sizeSlider;
    @FXML private Label sizeLabel;
    @FXML private CanvasContainer canvasContainer;
    @FXML private Node root;
    // tool buttons
    @FXML private Button moveButton;
    @FXML private Button pencilButton;
    @FXML private Button boxButton;
    @FXML private Button circleButton;
    @FXML private Button eraserButton;
    @FXML private Button undoButton;
    @FXML private Button redoButton;

    // other data
    private GraphicsContext gc;
    private ToolController tc;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        this.gc = this.canvas.getGraphicsContext2D();
        this.tc = new ToolController(this.gc, canvasContainer);
        this.canvasContainer.init(this.canvas, this.tc);

        this.root.setStyle("-fx-background-color: grey;");

        // initialize canvas
        this.gc.setFill(Color.WHITE);
        this.gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

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

        this.undoButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {

                tc.undo();

                event.consume();
            }
        });

        this.redoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                tc.redo();

                event.consume();
            }
        });

        this.pencilButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tc.changeTool(ToolList.PENCIL);
                event.consume();
            }
        });

        this.boxButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tc.changeTool(ToolList.BOX);
                event.consume();
            }
        });

        this.circleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tc.changeTool(ToolList.ELLIPSE);
                event.consume();
            }
        });

        this.eraserButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tc.changeTool(ToolList.ERASER);
                event.consume();
            }
        });

        this.moveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tc.changeTool(ToolList.HAND);
                event.consume();
            }
        });
    }
}
