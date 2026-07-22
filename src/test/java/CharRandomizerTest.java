import de.tomse.misc.utilities.randomizer.impl.CharRandomizer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public final class CharRandomizerTest {
    @Test
    public void randomizeAlpha() {
        final String randomized = new CharRandomizer(CharRandomizer.CharSet.ALPHA, 16).draw();
        System.out.printf("Randomized ALPHA (16): '%s'%n", randomized);
        assertEquals(16, randomized.length());
        assertTrue(randomized.matches("[a-zA-Z]+"));
    }

    @Test
    public void randomizeAlphanumeric() {
        final String randomized = new CharRandomizer(CharRandomizer.CharSet.ALPHANUMERIC, 16).draw();
        System.out.printf("Randomized ALPHANUMERIC (16): '%s'%n", randomized);
        assertEquals(16, randomized.length());
    }

    @Test
    public void randomizeNumeric() {
        final String randomized = new CharRandomizer(CharRandomizer.CharSet.NUMERIC, 16).draw();
        System.out.printf("Randomized NUMERIC (16): '%s'%n", randomized);
        assertEquals(16, randomized.length());
        assertTrue(randomized.matches("\\d+"));
    }
}
