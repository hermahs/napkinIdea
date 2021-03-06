package seternes.napkinIdea;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SubScene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import seternes.napkinIdea.Tools.ToolList;

// Controller til drawingApp
public class DrawingController implements Initializable {
    // fxml data
    @FXML private Canvas canvas;
    @FXML private ColorPicker colorPicker;
    @FXML private Slider sizeSlider;
    @FXML private Label sizeLabel;
    @FXML private CanvasContainer canvasContainer;
    @FXML private BorderPane root;
    @FXML private SubScene subscene;
    @FXML private Pane subsceneContainer;

    // tool buttons
    @FXML private Button moveButton;
    @FXML private Button pencilButton;
    @FXML private Button boxButton;
    @FXML private Button circleButton;
    @FXML private Button eraserButton;
    @FXML private Button undoButton;
    @FXML private Button redoButton;

    // menu buttons
    @FXML private MenuItem newButton;
    @FXML private MenuItem saveButton;
    @FXML private MenuItem openButton;
    @FXML private MenuItem closeButton;
    @FXML private MenuItem centerCanvasButton;

    // other data
    private GraphicsContext gc;
    private ToolController tc;
    private FileController fc;
    private SizeColorChanger scc;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        this.gc = this.canvas.getGraphicsContext2D();
        this.tc = new ToolController(this.gc, this.canvasContainer);
        this.fc = new FileController(this.canvasContainer);
        this.canvasContainer.init(this.canvas, this.tc, this.gc);
        this.scc = new SizeColorChanger(tc, sizeLabel);
        

        // setup background color for drawing area
        this.root.setStyle("-fx-background-color: grey;");

        // initialize canvas
        this.canvasContainer.clearCanvas();

        // setup drawing area scaling dynamicaly
        this.subscene.widthProperty().bind(this.subsceneContainer.widthProperty());
        this.subscene.heightProperty().bind(this.subsceneContainer.heightProperty());

        // colorpicker
        this.colorPicker.setOnAction(this.scc.getHandleChangeColor());

        // size slider
        this.sizeSlider.valueProperty().addListener(this.scc.getSizeChanger());

        // Buttons
        this.undoButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                tc.undo();
            }
        });

        this.redoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {              
                tc.redo();
            }
        });

        this.pencilButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tc.changeTool(ToolList.PENCIL);
            }
        });

        this.boxButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tc.changeTool(ToolList.BOX);
            }
        });

        this.circleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tc.changeTool(ToolList.ELLIPSE);
            }
        });

        this.eraserButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tc.changeTool(ToolList.ERASER);
            }
        });

        this.moveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tc.changeTool(ToolList.HAND);
            }
        });

        this.centerCanvasButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                canvasContainer.returnCanvasToCenter();
            }
        });

        this.openButton.setOnAction(this.fc.getOpenFileActionHandler());

        this.saveButton.setOnAction(this.fc.getSaveFileActionHandler());

        this.newButton.setOnAction(this.fc.getNewFileActionHandler());
    }

    public void initCanvasSize(double w, double h) {
        this.canvasContainer.setCanvasSize(w, h);
    }

    public FileController getFileController() {
        return this.fc;
    }

    public ToolController getToolController() {
        return this.tc;
    }
}
