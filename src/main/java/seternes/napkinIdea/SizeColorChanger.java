package seternes.napkinIdea;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;

public class SizeColorChanger {
    
    private ToolController tc;
    private Label sizeLabel;

    public SizeColorChanger(ToolController tc, Label sizeLabel) {
        if(!(tc instanceof ToolController) || !(sizeLabel instanceof Label)) throw new IllegalArgumentException();
        this.tc = tc;
        this.sizeLabel = sizeLabel;
    }

    public EventHandler<ActionEvent> getHandleChangeColor() {
        return this.handleChangeColor;
    }

    public ChangeListener<Number> getSizeChanger() {
        return this.sizeChanger;
    }

    private EventHandler<ActionEvent> handleChangeColor = new EventHandler<ActionEvent>(){
        @Override
            public void handle(ActionEvent e) {
                tc.setColor(((ColorPicker)e.getTarget()).getValue());
            }
        
    };

    private ChangeListener<Number> sizeChanger = new ChangeListener<Number>(){
        @Override
        public void changed(ObservableValue<? extends Number> observedvalue, Number oldValue, Number newValue) {
            tc.setSize(newValue.floatValue());
            sizeLabel.textProperty().setValue(String.valueOf(newValue.intValue()));
        }
    };

}
