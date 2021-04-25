package seternes.napkinIdea;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.DisplayName;

import javafx.scene.control.TextField;

public class TestInputChecker extends ApplicationTest {
    
    @Test
    @DisplayName("test Constructor")
    public void testConstructor() {
        TextField i = new TextField();
        InputChecker ic = new InputChecker(i);
        assertEquals(i, ic.getInput());
        assertThrows(IllegalArgumentException.class, () -> {
            new InputChecker(null);
        });
    }

}
