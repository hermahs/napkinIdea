package seternes.napkinIdea;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestSizeColorChange {
    
    @Test
    @DisplayName("test constructor")
    public void testConstructor() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SizeColorChanger(null, null);
        });
    }

}
