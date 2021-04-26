package seternes.napkinIdea;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;


public class TestToolController {

    @Test
    @DisplayName("constructor Test")
    public void testConstructor() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ToolController(null, null);
        });
    }

}
