package seternes.napkinIdea;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class TestLayer {
    
    // @Override
    // public void start(Stage stage) throws Exception {
    //     stage.setScene(new Scene(App.loadFXML("drawingApp").load(), 640, 480));
    //     stage.show();
    //     stage.toFront();
    // }

    

    @Test
    @DisplayName("test constructor")
    public void testConstructor() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Layer(null, 0 , null, null);
        });
    }

}
