module seternes.napkinIdea {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
	requires transitive javafx.graphics;
    requires javafx.swing;
    requires junit;

    opens seternes.napkinIdea to javafx.fxml;
    exports seternes.napkinIdea;
    exports seternes.napkinIdea.Tools;
}
