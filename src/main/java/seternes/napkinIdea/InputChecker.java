package seternes.napkinIdea;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

// passer på at inputet i et tekstfelt bare er tall og andre ting
public class InputChecker {
    
    private TextField input;

    public InputChecker(TextField i) {
        if(!(i instanceof TextField)) throw new IllegalArgumentException();
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

    public TextField getInput() {
        return this.input;
    }

}
