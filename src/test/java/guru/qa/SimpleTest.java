package guru.qa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SimpleTest {
    @Disabled
    @DisplayName("Демо тест")
    @Test
    void firstTest() {
        Assertions.assertTrue( 3 > 2, "fail");
        Assertions.assertFalse(3 < 2, "fail too");
        Assertions.assertEquals("Doo", "Doo");
        Assertions.assertAll(
                () -> Assertions.assertTrue(3 < 2),
                () -> Assertions.assertTrue(3 > 2)
        );
    }
}
