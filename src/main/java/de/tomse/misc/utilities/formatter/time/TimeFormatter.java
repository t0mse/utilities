package de.tomse.misc.utilities.formatter.time;

import de.tomse.misc.utilities.formatter.Formatter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.text.NumberFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public final class TimeFormatter extends Formatter<String> {
    public static final TimeZone FALLBACK_TIME_ZONE;
    private static final NumberFormat fallbackNumberFormat;
    private static final List<TimeUnit> timeUnits;
    private static final String fallbackFormatSeparator;

    static {
        FALLBACK_TIME_ZONE = TimeZone.getTimeZone("Europe/Berlin");
        fallbackNumberFormat = NumberFormat.getIntegerInstance(Locale.US);
        timeUnits = new ArrayList<>(Arrays.asList(TimeUnit.values()));
        Collections.reverse(timeUnits);
        fallbackFormatSeparator = ", ";
    }

    private final long timeMillis;
    private final NumberFormat numberFormat;
    private final TimeUnitPreset timeUnitPreset;
    private Set<TimeUnit> displayTimeUnits = new HashSet<>();
    private String formatSeparator = fallbackFormatSeparator;
    private boolean subtractCurrentMillis = false,
            onlyDisplayBiggest = false,
            fullUnitNames = false,
            hideNullValues = false;

    public TimeFormatter(long timeMillis) {
        this(timeMillis, fallbackNumberFormat);
    }

    public TimeFormatter(long timeMillis, NumberFormat numberFormat) {
        this(timeMillis, numberFormat, TimeUnitPreset.FALLBACK);
    }

    public TimeFormatter formatSeparator(String formatSeparator) {
        this.formatSeparator = formatSeparator;
        return this;
    }

    public TimeFormatter noFormatSeparator(String formatSeparator) {
        return formatSeparator("");
    }

    public TimeFormatter subtractCurrentMillis(boolean subtractCurrentMillis) {
        this.subtractCurrentMillis = subtractCurrentMillis;
        return this;
    }

    public TimeFormatter onlyDisplayBiggest(boolean onlyDisplayBiggest) {
        this.onlyDisplayBiggest = onlyDisplayBiggest;
        if (displayTimeUnits.isEmpty()) {
            displayTimeUnits.addAll(timeUnits);
        }
        return this;
    }

    public TimeFormatter fullUnitNames(boolean wholeUnitNames) {
        this.fullUnitNames = wholeUnitNames;
        return this;
    }

    public TimeFormatter hideNullValues(boolean hideNullValues) {
        this.hideNullValues = hideNullValues;
        return this;
    }

    public TimeFormatter displayTimeUnits(TimeUnit... displayTimeUnit) {
        this.displayTimeUnits = new HashSet<>(Arrays.asList(displayTimeUnit));
        return this;
    }

    @Override
    public String format() {
        final String negatedEntryDisplay = timeUnitPreset.getNegatedEntryDisplay();
        if (timeMillis == -1L) {
            return negatedEntryDisplay;
        }
        long processedTimeMillis = timeMillis;
        if (subtractCurrentMillis) {
            processedTimeMillis = processedTimeMillis - System.currentTimeMillis();
        }
        if (processedTimeMillis < 0L) {
            return negatedEntryDisplay;
        }
        if (displayTimeUnits.isEmpty()) {
            displayTimeUnits.addAll(timeUnits);
        }
        final Map<TimeUnit, Long> timeUnitCounts = new LinkedHashMap<>();
        for (TimeUnit timeUnit : timeUnits) {
            if (!displayTimeUnits.contains(timeUnit)) {
                continue;
            }
            final long singletonUnitTimeMillis = timeUnit.toMillis(1L);
            long unitCount = (long) Math.floor((float) processedTimeMillis / singletonUnitTimeMillis);
            if (unitCount < 0) {
                unitCount = 0;
            } else {
                processedTimeMillis -= singletonUnitTimeMillis * unitCount;
            }
            timeUnitCounts.put(timeUnit, unitCount);
        }
        final StringJoiner formattedJoiner = new StringJoiner(formatSeparator);
        for (TimeUnit timeUnit : timeUnitCounts.keySet()) {
            final long unitCount = timeUnitCounts.get(timeUnit);
            final TimeUnitPreset.TimeUnitSetEntry unitSetEntry = timeUnitPreset.getEntries().get(timeUnit);
            final String unitDisplay = numberFormat.format(unitCount)
                    + (fullUnitNames
                    ? " "
                    + (unitCount == 1
                    ? unitSetEntry.getSingularName()
                    : unitSetEntry.getPluralName())
                    : unitSetEntry.getShortName());
            if (unitCount != 0L && onlyDisplayBiggest) {
                return unitDisplay;
            }
            if (hideNullValues && unitCount == 0L) {
                continue;
            }
            formattedJoiner.add(unitDisplay);
        }
        final String formatted = formattedJoiner.toString();
        if (onlyDisplayBiggest && formatted.contains(formatSeparator)) {
            final String[] finalDisplaySplit = formatted.split(formatSeparator);
            return finalDisplaySplit[finalDisplaySplit.length - 1];
        }
        return formatted;
    }
}
