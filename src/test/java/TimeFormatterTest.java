import de.tomse.misc.utilities.formatter.time.TimeFormatter;
import org.junit.jupiter.api.Test;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TimeFormatterTest {
    private static final long testTimeMillis = TimeUnit.DAYS.toMillis(1_500);

    @Test
    public void format() {
        final TimeFormatter timeFormatter = new TimeFormatter(testTimeMillis)
                .displayTimeUnits(TimeUnit.DAYS, TimeUnit.HOURS, TimeUnit.MINUTES);
        assertEquals("1,500d, 0h, 0m", timeFormatter.format());
    }

    @Test
    public void formatSubtracted() {
        final TimeFormatter timeFormatter = new TimeFormatter(testTimeMillis + System.currentTimeMillis())
                .displayTimeUnits(TimeUnit.DAYS, TimeUnit.HOURS, TimeUnit.MINUTES)
                .subtractCurrentMillis(true);
        assertEquals("1,500d, 0h, 0m", timeFormatter.format());
    }

    @Test
    public void formatOnlyBiggest() {
        final TimeFormatter timeFormatter = new TimeFormatter(testTimeMillis)
                .displayTimeUnits(TimeUnit.DAYS, TimeUnit.HOURS, TimeUnit.MINUTES)
                .onlyDisplayBiggest(true);
        assertEquals("1,500d", timeFormatter.format());
    }

    @Test
    public void formatWithSeparator() {
        final TimeFormatter timeFormatter = new TimeFormatter(testTimeMillis)
                .formatSeparator(" ")
                .displayTimeUnits(TimeUnit.DAYS, TimeUnit.HOURS, TimeUnit.MINUTES);
        assertEquals("1,500d 0h 0m", timeFormatter.format());
    }

    @Test
    public void formatWithNumberFormat() {
        final TimeFormatter timeFormatter = new TimeFormatter(testTimeMillis,
                NumberFormat.getIntegerInstance(Locale.GERMAN))
                .displayTimeUnits(TimeUnit.DAYS, TimeUnit.HOURS, TimeUnit.MINUTES);
        assertEquals("1.500d, 0h, 0m", timeFormatter.format());
    }

    @Test
    public void formatSubtractedWithNumberFormat() {
        final TimeFormatter timeFormatter = new TimeFormatter(testTimeMillis + System.currentTimeMillis(),
                NumberFormat.getIntegerInstance(Locale.GERMAN))
                .displayTimeUnits(TimeUnit.DAYS, TimeUnit.HOURS, TimeUnit.MINUTES)
                .subtractCurrentMillis(true);
        assertEquals("1.500d, 0h, 0m", timeFormatter.format());
    }

    @Test
    public void formatOnlyBiggestWithNumberFormat() {
        final TimeFormatter timeFormatter = new TimeFormatter(testTimeMillis,
                NumberFormat.getIntegerInstance(Locale.GERMAN))
                .displayTimeUnits(TimeUnit.DAYS, TimeUnit.HOURS, TimeUnit.MINUTES)
                .onlyDisplayBiggest(true);
        assertEquals("1.500d", timeFormatter.format());
    }

    @Test
    public void formatWithNumberFormatAndSeparator() {
        final TimeFormatter timeFormatter = new TimeFormatter(testTimeMillis,
                NumberFormat.getIntegerInstance(Locale.GERMAN))
                .formatSeparator(" ")
                .displayTimeUnits(TimeUnit.DAYS, TimeUnit.HOURS, TimeUnit.MINUTES);
        assertEquals("1.500d 0h 0m", timeFormatter.format());
    }
}
