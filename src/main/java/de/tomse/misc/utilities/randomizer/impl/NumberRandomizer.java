package de.tomse.misc.utilities.randomizer.impl;

import de.tomse.misc.utilities.randomizer.Randomizer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Getter
@RequiredArgsConstructor
public final class NumberRandomizer extends Randomizer<Integer> {
    private final int min, max;

    @SneakyThrows
    @Override
    public Integer draw() {
        return random.nextInt(max - min + 1) + min;
    }
}

