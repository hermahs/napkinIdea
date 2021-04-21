package seternes.napkinIdea;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class CanvasContainer extends Pane {

    private Canvas canvas;
    private ToolController tc;
    private GraphicsContext gc;

    private DoubleProperty scale = new SimpleDoubleProperty(1.0);
    private double offsetX = 0;
    private double offsetY = 0;
    private static final double MAX_SCALE = 10.0d;
    private static final double MIN_SCALE = 0.1d;


    public CanvasContainer() {
        this.setStyle("-fx-background-color: grey; -fx-border-color: black; -fx-border-width: 10px; -fx-border-style: solid;");
        this.setViewOrder(-3.0);
        this.scaleXProperty().bind(scale);
        this.scaleYProperty().bind(scale);
    }

    public void init(Canvas c, ToolController tc, GraphicsContext gc) {
        this.canvas = c;
        this.tc = tc;
        this.gc = gc;

        this.canvas.prefHeight(200);
        this.canvas.prefWidth(200);

        this.widthProperty().addListener(this.widthChangeListerer);
        this.heightProperty().addListener(this.heightChangeListener);
        this.addEventFilter(ScrollEvent.ANY, onScrollEventHandler);
        this.canvas.addEventFilter(MouseEvent.MOUSE_PRESSED, this.tc.getOnMousePressedEventHandler());
        this.canvas.addEventFilter(MouseEvent.MOUSE_DRAGGED, this.tc.getOnMouseDraggedEventHandler());
        this.canvas.addEventFilter(MouseEvent.MOUSE_RELEASED, this.tc.getOnMouseReleasedEventHandler());
        this.canvas.setViewOrder(-5.0);
    }

    public double getScale() {
        return this.scale.get();
    }

    public GraphicsContext getGC() {
        return this.gc;
    }

    public ToolController getToolController() {
        return this.tc;
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public void setScale(double scale) {
        this.scale.set(scale);
    }

    public void setCanvasSize(double w, double h) {
        if(w > 3000) w = 3000;
        if(h > 3000) h = 3000;
        if(w < 200) w = 200;
        if(h < 200) h = 200;

        this.canvas.widthProperty().set(w);
        this.canvas.heightProperty().set(h);
        this.gc.setFill(Color.WHITE);
        this.gc.fillRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
    }

    public void setTranslateXY(double x, double y) {
        this.setTranslateX(this.getTranslateX()-x);
        this.setTranslateY(this.getTranslateY()-y);
    }

    private ChangeListener<Number> widthChangeListerer = new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            canvas.setTranslateX((double) newValue * 0.5 - canvas.getWidth() / 2 - offsetX);
        }
    };

    private ChangeListener<Number> heightChangeListener = new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            canvas.setTranslateY((double) newValue * 0.5 - canvas.getHeight() / 2 - offsetY);
        }
    };

    private EventHandler<ScrollEvent> onScrollEventHandler = new EventHandler<ScrollEvent>() {
        @Override
        public void handle(ScrollEvent event) {
            if(event.isShiftDown()) {handleScrollX(event); return;}
            if(event.isControlDown()) {handleScroll(event); return;}
            handleScrollY(event);
        }
    };

    private void handleScrollY(ScrollEvent event) {
        this.offsetY += event.getDeltaY();
        this.canvas.setTranslateY(this.canvas.getTranslateY()-event.getDeltaY());
        event.consume();
    }

    private void handleScrollX(ScrollEvent event) {
        this.offsetX += event.getDeltaX();
        this.canvas.setTranslateX(this.canvas.getTranslateX()-event.getDeltaX());
        event.consume();
    }

    private void handleScroll(ScrollEvent event) {

        double delta = 1.2;

        double scale = this.getScale();
        double oldScale = scale;

        if(event.getDeltaY() > 0) scale *= delta;
        if(event.getDeltaY() < 0) scale /= delta;

        scale = clamp(scale, this.MIN_SCALE, this.MAX_SCALE);

        double f = (scale / oldScale)-1;

        double dx = (event.getSceneX() - (this.getBoundsInParent().getWidth()/2 + this.getBoundsInParent().getMinX()));
        double dy = (event.getSceneY() - (this.getBoundsInParent().getHeight()/2 + this.getBoundsInParent().getMinY()));

        this.setScale(scale);

        this.setTranslateXY(f*dx, f*dy);

        event.consume();
    }

    public void returnCanvasToCenter() {
        this.offsetX = 0;
        this.offsetY = 0;
        this.setScale(1);
        this.setTranslateX(0);
        this.setTranslateY(0);
        this.canvas.setTranslateX(this.getWidth() / 2 - this.canvas.getWidth() / 2);
        this.canvas.setTranslateY(this.getHeight() / 2 - this.canvas.getHeight() / 2);
    }

    private double clamp(double x, double min, double max) {
        if(Double.compare(x, min) < 0) return min;
        if(Double.compare(x,max) > 0) return max;
        return x;
    }

    public void clearCanvas() {
        this.gc.setFill(Color.WHITE);
        this.gc.fillRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
    }
}
