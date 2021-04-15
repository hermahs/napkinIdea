package seternes.napkinIdea.Tools;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import seternes.napkinIdea.Layer;

public class Ellipse implements Tool {

    private Color color;
    private GraphicsContext gc;
    private double startX;
    private double startY;

    public Ellipse(Color c, GraphicsContext gc) {
        this.color = c;
        this.gc = gc;
    }


    @Override
    public void setSize(float s) {
        return;        
    }

    @Override
    public void setColor(Color c) {
        this.color = c;
    }

    @Override
    public float getSize() {
        return 0;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public void handleOnMousePressedEvent(MouseEvent event) {
        this.gc.setStroke(this.color);
        this.gc.setFill(this.color);
        this.startX = event.getX();
        this.startY = event.getY();
        event.consume();
        
    }

    @Override
    public void handleOnMouseDraggedEvent(MouseEvent event) {
        // må cleare alt og tegne alt under på nytt da
        event.consume();
    }

    @Override
    public void handleOnMouseReleasedEvent(MouseEvent event) {
        double _startX = this.startX;
        double _startY = this.startY;

        double _endX = event.getX();
        double _endY = event.getY();

        double w,h;

        if(_endX < _startX) {
            double _t = _startX;
            _startX = _endX;
            _endX = _t;
        } 

        if(_endY < _startY) {
            double _t = _startY;
            _startY = _endY;
            _endY = _t;
        }

        w = Math.abs(_endX - _startX);
        h = Math.abs(_endY - _startY);

        this.gc.fillOval(_startX, _startY, w, h);
        this.gc.strokeOval(_startX, _startY, w, h);
        event.consume();
    }

    @Override
    public void reDraw(Layer l) {

        ArrayList<Pair<Double,Double>> data = l.getData();

        this.gc.setFill(l.getColor());
        this.gc.setStroke(l.getColor());
        
        double _startX = data.get(0).getKey();
        double _startY = data.get(0).getValue();

        double _endX = data.get(data.size() - 1).getKey();
        double _endY = data.get(data.size() - 1).getValue();

        double w, h;

        if(_endX < _startX) {
            double _t = _startX;
            _startX = _endX;
            _endX = _t;
        } 

        if(_endY < _startY) {
            double _t = _startY;
            _startY = _endY;
            _endY = _t;
        }

        w = Math.abs(_endX - _startX);
        h = Math.abs(_endY - _startY);

        this.gc.fillOval(_startX, _startY, w, h);
        this.gc.strokeOval(_startX, _startY, w, h);
    }
    
}
