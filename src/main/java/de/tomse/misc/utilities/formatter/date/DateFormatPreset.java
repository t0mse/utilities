package de.tomse.misc.utilities.formatter.date;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@SuppressWarnings("SpellCheckingInspection")
@Getter
@RequiredArgsConstructor
public enum DateFormatPreset {
    TIME("HH:mm:ss"),
    YEAR("yyyy"),
    DATE("yyyy-MM-dd"),
    DATE_YEAR_MONTH("yyyy-MM"),
    EUROPEAN_DATE("dd.MM.yyyy"),
    DATE_TIME("MMM dd yyyy HH:mm:ss"),
    EUROPEAN_DATETIME("dd.MM.yyyy HH:mm:ss"),
    LONG_DATE("MMMM dd yyyy"),
    FULL_DATE("EEEE, MMMM dd yyyy"),
    FULL_MONTH_YEAR("MMMM yyyy"),
    LOG("yyyy-MM-dd HH:mm:ss"),
    LOG_WITH_MILLIS("yyyy-MM-dd HH:mm:ss.SSS"),
    RFC_1123("EEE, dd MMM yyyy HH:mm:ss zzz"),
    UNIX_SECONDS("yyyy-MM-dd'T'HH:mm:ss"),
    ISO_8601("yyyy-MM-dd'T'HH:mm:ss'Z'"),
    ISO_8601_WITH_MILLIS("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"),
    ISO_8601_WITH_OFFSET("yyyy-MM-dd'T'HH:mm:ssXXX"),
    ISO_8601_WITH_MILLIS_OFFSET("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    private final String dateFormat;
}
