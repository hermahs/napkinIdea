module seternes.napkinIdea {
    requires javafx.controls;
    requires javafx.fxml;
	requires transitive javafx.graphics;

    opens seternes.napkinIdea to javafx.fxml;
    exports seternes.napkinIdea;
    exports seternes.napkinIdea.Tools;
}