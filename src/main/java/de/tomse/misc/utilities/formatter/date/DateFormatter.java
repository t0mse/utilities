package de.tomse.misc.utilities.formatter.date;

import de.tomse.misc.utilities.formatter.Formatter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.function.Consumer;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public final class DateFormatter extends Formatter<String> {
    private final SimpleDateFormat simpleDateFormat;
    private long timestampMillis;

    public DateFormatter() {
        this(System.currentTimeMillis());
    }

    public DateFormatter(long timestampMillis) {
        this(DateFormatPreset.DATE_TIME, timestampMillis);
    }

    public DateFormatter(DateFormatPreset dateFormatPreset) {
        this(dateFormatPreset, System.currentTimeMillis());
    }

    public DateFormatter(String dateFormat) {
        this(dateFormat, System.currentTimeMillis());
    }

    public DateFormatter(DateFormatPreset dateFormatPreset, long timestampMillis) {
        this(new SimpleDateFormat(dateFormatPreset.getDateFormat()), timestampMillis);
    }

    public DateFormatter(String dateFormat, long timestampMillis) {
        this(new SimpleDateFormat(dateFormat), timestampMillis);
    }

    public DateFormatter timeZone(TimeZone timeZone) {
        simpleDateFormat.setTimeZone(timeZone);
        return this;
    }

    public DateFormatter timeZone(String timeZoneId) {
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timeZoneId));
        return this;
    }

    public DateFormatter consumeDateFormat(Consumer<SimpleDateFormat> dateFormatConsumer) {
        dateFormatConsumer.accept(simpleDateFormat);
        return this;
    }

    @Override
    public String format() {
        return simpleDateFormat.format(timestampMillis == 0 ? new Date() : new Date(timestampMillis));
    }
}
