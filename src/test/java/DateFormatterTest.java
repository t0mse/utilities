import de.tomse.misc.utilities.formatter.date.DateFormatPreset;
import de.tomse.misc.utilities.formatter.date.DateFormatter;
import org.junit.jupiter.api.Test;

import java.util.TimeZone;

public final class DateFormatterTest {
    @Test
    public void formatDefaultDate() {
        final DateFormatter dateFormatter = new DateFormatter();
        System.out.printf("RFC DateFormatter: '%s'%n", dateFormatter.format());
    }

    @Test
    public void formatRfcDate() {
        final DateFormatter dateFormatter = new DateFormatter(DateFormatPreset.ISO_8601);
        System.out.printf("RFC DateFormatter: '%s'%n", dateFormatter.format());
    }

    @Test
    public void formatTimeZone() {
        final DateFormatter dateFormatter = new DateFormatter()
                .timeZone(TimeZone.getTimeZone("Europe/Berlin"));
        System.out.printf("TimeZone DateFormatter: '%s'%n", dateFormatter.format());
    }

    @Test
    public void formatConsumeTimeZone() {
        final DateFormatter dateFormatter = new DateFormatter()
                .consumeDateFormat(dateFormat
                        -> dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Berlin")));
        System.out.printf("Consume TimeZone DateFormatter: '%s'%n", dateFormatter.format());
    }
}
