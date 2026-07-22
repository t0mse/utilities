package de.tomse.misc.utilities.formatter.date;

import de.tomse.misc.utilities.formatter.Formatter;
import de.tomse.misc.utilities.formatter.time.TimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public final class DateFormatter extends Formatter<String> {
    public static final SimpleDateFormat FALLBACK_DATE_FORMAT;
    public static final SimpleDateFormat FALLBACK_RFC_DATE_FORMAT;

    static {
        final TimeZone fallbackTimeZone = TimeFormatter.FALLBACK_TIME_ZONE;
        FALLBACK_DATE_FORMAT = new SimpleDateFormat("MMM dd yyyy, HH:mm:ss a z");
        FALLBACK_DATE_FORMAT.setTimeZone(fallbackTimeZone);
        FALLBACK_RFC_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        FALLBACK_RFC_DATE_FORMAT.setTimeZone(fallbackTimeZone);
    }

    private final DateSet dateSet;
    private long timestampMillis;

    @Override
    public String format() {
        final Date date = timestampMillis == 0 ? new Date() : new Date(timestampMillis);
        String formattedDate;
        switch (dateSet) {
            case DEFAULT -> formattedDate = FALLBACK_DATE_FORMAT.format(date);
            case RFC -> formattedDate = FALLBACK_RFC_DATE_FORMAT.format(date);
            default -> throw new IllegalStateException("Unsupported date set: " + dateSet);
        }
        return formattedDate;
    }

    public enum DateSet {
        DEFAULT,
        RFC
    }
}
