package de.tomse.misc.utilities.randomizer;

import java.util.Random;

public abstract class Randomizer<T> {
    protected static final Random random = new Random();

    public abstract T draw();
}
