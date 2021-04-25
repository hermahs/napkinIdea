package seternes.napkinIdea;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class TestLayer {

    @Test
    @DisplayName("test constructor")
    public void testConstructor() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Layer(null, 0 , null, null);
        });
    }

}
