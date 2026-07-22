import de.tomse.misc.utilities.formatter.date.DateFormatter;
import org.junit.jupiter.api.Test;

public final class DateFormatterTest {
    @Test
    public void formatDefaultDate() {
        final DateFormatter defaultDateFormatter = new DateFormatter(DateFormatter.DateSet.DEFAULT);
        System.out.printf("Default DateFormatter: '%s'%n", defaultDateFormatter.format());
    }

    @Test
    public void formatRfcDate() {
        final DateFormatter rfcDateFormatter = new DateFormatter(DateFormatter.DateSet.RFC);
        System.out.printf("RFC DateFormatter: '%s'%n", rfcDateFormatter.format());
    }
}
