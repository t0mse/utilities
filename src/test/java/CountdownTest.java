import de.tomse.misc.utilities.async.Countdown;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class CountdownTest {
    @Test
    public void initializeCountdown() {
        final Countdown countdown = new Countdown(5, TimeUnit.SECONDS) {
            @Override
            public void onTick(int tick) {
                // Countdown ticking
                assertTrue(isTicking());
            }

            @Override
            public void onLastTick() {
                // Countdown finished
                assertEquals(0, currentTick);
            }
        };
        countdown.start();
    }
}
