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
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToolBar;

public class TestController implements Initializable {
    // fxml data
    @FXML private Canvas canvas;
    @FXML private ColorPicker colorPicker;
    @FXML private Slider sizeSlider;
    @FXML private Label sizeLabel;
    @FXML private Slider opacitySlider;
    @FXML private Label opacityLabel;
    @FXML private ToolBar layerContainer;

    // other data
    private GraphicsContext gc;
    private ToolController tc;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.gc = this.canvas.getGraphicsContext2D();
        this.tc = new ToolController(this.gc);

        // drawing
        canvas.setOnMousePressed(e->{
            this.tc.startDraw(e.getX(), e.getY());
        });

        canvas.setOnMouseDragged(e->{
            this.tc.dragDraw(e.getX(), e.getY());
        });

        canvas.setOnMouseReleased(e->{
            this.tc.endDraw(e.getX(), e.getY());
        });

        // color picker
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
