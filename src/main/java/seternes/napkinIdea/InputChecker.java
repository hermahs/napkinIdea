package seternes.napkinIdea;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class InputChecker {
    
    private TextField input;

    public InputChecker(TextField i) {
        this.input = i;
    }

    private ChangeListener<String> checkForNumbersChangeListener = new ChangeListener<String>(){
        @Override
        public void changed(ObservableValue<? extends String> observableValue, String oldString, String newString) {
            if(!newString.matches("\\d*")) {
                input.setText(newString.replaceAll("[^\\d]", ""));
            }
        }
    };

    public ChangeListener<String> getCheckForNumbersChangeListener() {
        return this.checkForNumbersChangeListener;
    }

}
