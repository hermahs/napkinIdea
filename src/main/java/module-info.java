module seternes.napkinIdea {
    requires javafx.controls;
    requires javafx.fxml;

    opens seternes.napkinIdea to javafx.fxml;
    exports seternes.napkinIdea;
}