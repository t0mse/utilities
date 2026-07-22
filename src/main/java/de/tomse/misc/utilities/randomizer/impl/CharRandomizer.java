package de.tomse.misc.utilities.randomizer.impl;

import de.tomse.misc.utilities.randomizer.Randomizer;
import lombok.*;

import java.util.Random;

@Getter
@RequiredArgsConstructor
public final class CharRandomizer extends Randomizer<String> {
    private final CharSet charSet;
    private final int length;

    @SneakyThrows
    @Override
    public String draw() {
        final StringBuilder textBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            textBuilder.append(charSet.getChars()
                    .charAt(new Random()
                            .nextInt(charSet.getChars()
                                    .length())));
        }
        return textBuilder.toString();
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public enum CharSet {
        ALPHA("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"),
        ALPHANUMERIC("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"),
        NUMERIC("0123456789");

        private final String chars;
    }
}
