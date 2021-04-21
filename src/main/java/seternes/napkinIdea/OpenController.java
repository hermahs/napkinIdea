package seternes.napkinIdea;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class OpenController implements Initializable {

    @FXML private TextField widthInput;
    @FXML private TextField heightInput;
    @FXML private Button createButton;
    @FXML private Label widthError;
    @FXML private Label heightError;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        InputChecker widthChecker = new InputChecker(widthInput);
        InputChecker heightChecker = new InputChecker(heightInput);

        // make sure only numbers can be entered into textfields
        this.widthInput.textProperty().addListener(widthChecker.getCheckForNumbersChangeListener());

        this.heightInput.textProperty().addListener(heightChecker.getCheckForNumbersChangeListener());
        
        this.createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ChangeController.changeController(widthInput, heightInput);
            }
        });


    }
    
}
