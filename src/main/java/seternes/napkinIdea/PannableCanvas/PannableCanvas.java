package seternes.napkinIdea.PannableCanvas;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

// Inspired by https://stackoverflow.com/questions/29506156/javafx-8-zooming-relative-to-mouse-pointer

public class PannableCanvas extends Pane {

    private DoubleProperty scale  = new SimpleDoubleProperty(1.0);

    public PannableCanvas(double w, double h) {
        setPrefSize(w, h);
        setStyle("-fx-background-color: lightgrey; -fx-border-color: blue;");

        scaleXProperty().bind(this.scale);
        scaleYProperty().bind(this.scale);
    }

    public void addCanvas() {

        double w = getBoundsInLocal().getWidth();
        double h = getBoundsInLocal().getHeight();

        Canvas canvas = new Canvas(w,h);

        getChildren().add(canvas);

    }

    public void setScale(double s) {
        this.scale.set(s);
    }

    public double getScale() {
        return this.scale.get();
    }

    public void setPivot(double x, double y) {
        setTranslateX(getTranslateX()-x);
        setTranslateY(getTranslateY()-y);
    }
}
