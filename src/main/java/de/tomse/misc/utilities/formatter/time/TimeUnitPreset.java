package de.tomse.misc.utilities.formatter.time;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public final class TimeUnitPreset {
    private static final String fallbackNegatedEntryDisplay;
    public static final TimeUnitPreset FALLBACK;

    static {
        fallbackNegatedEntryDisplay = "Permanent";
        FALLBACK = new TimeUnitPreset()
                .add(TimeUnit.DAYS, "day", "days", "d")
                .add(TimeUnit.HOURS, "hour", "hours", "h")
                .add(TimeUnit.MINUTES, "minute", "minutes", "m")
                .add(TimeUnit.SECONDS, "second", "seconds", "s")
                .add(TimeUnit.MILLISECONDS, "millisecond", "milliseconds", "ms")
                .add(TimeUnit.MICROSECONDS, "microsecond", "microseconds", "µs")
                .add(TimeUnit.NANOSECONDS, "nanosecond", "nanoseconds", "ns");
    }

    @Getter
    private final String negatedEntryDisplay;
    private final Map<TimeUnit, TimeUnitSetEntry> entries;

    public TimeUnitPreset() {
        this(fallbackNegatedEntryDisplay);
    }

    public TimeUnitPreset(String negatedEntryDisplay) {
        this(negatedEntryDisplay, new HashMap<>());
    }

    public TimeUnitPreset add(TimeUnit timeUnit, String singularName, String pluralName, String shortName) {
        entries.put(timeUnit, new TimeUnitSetEntry(singularName, pluralName, shortName));
        return this;
    }

    public TimeUnitPreset add(TimeUnit timeUnit, TimeUnitSetEntry entry) {
        entries.put(timeUnit, entry);
        return this;
    }

    public Map<TimeUnit, TimeUnitSetEntry> getEntries() {
        if (entries.size() != TimeUnit.values().length) {
            throw new IllegalStateException("TimeUnitPreset is lacking of entries");
        }
        return entries;
    }

    @Getter
    @RequiredArgsConstructor
    public static final class TimeUnitSetEntry {
        private final String singularName, pluralName, shortName;
    }
}
