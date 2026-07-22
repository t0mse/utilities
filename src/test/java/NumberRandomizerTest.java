import de.tomse.misc.utilities.randomizer.impl.NumberRandomizer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public final class NumberRandomizerTest {
    @Test
    public void randomizeSmallNumber() {
        final int randomNumber = new NumberRandomizer(1, 10).draw();
        assertTrue(randomNumber >= 1 && randomNumber <= 10);
    }

    @Test
    public void randomizeMediumNumber() {
        final int randomNumber = new NumberRandomizer(1_000, 10_000).draw();
        assertTrue(randomNumber >= 1_000 && randomNumber <= 10_000);
    }

    @Test
    public void randomizeLargeNumber() {
        final int randomNumber = new NumberRandomizer(100_000, 1_000_000).draw();
        assertTrue(randomNumber >= 100_000 && randomNumber <= 1_000_000);
    }
}
