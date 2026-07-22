package de.tomse.misc.utilities.async;

import lombok.Getter;

import java.util.concurrent.TimeUnit;

@Getter
public abstract class Countdown {
    private static final TimeUnit fallbackTimeUnit;

    static {
        fallbackTimeUnit = TimeUnit.SECONDS;
    }

    protected final int ticks;
    protected final TimeUnit timeUnit;
    protected int currentTick;
    protected Scheduler scheduler;

    public Countdown(int ticks) {
        this(ticks, fallbackTimeUnit);
    }

    public Countdown(int ticks, TimeUnit timeUnit) {
        this.ticks = ticks;
        this.timeUnit = timeUnit;
        reset();
    }

    public boolean isTicking() {
        return currentTick > 0;
    }

    public void start() {
        scheduler = Scheduler.repeatingTask(() -> {
            if (currentTick > 0) {
                onTick(currentTick);
                currentTick--;
            } else {
                stop();
                onLastTick();
            }
        }, 1, timeUnit);
    }

    public void pause() {
        if (scheduler == null) {
            return;
        }
        scheduler.shutdown();
        scheduler = null;
    }

    public void stop() {
        pause();
        reset();
    }

    public void reset() {
        currentTick = ticks;
    }

    public abstract void onTick(int tick);

    public abstract void onLastTick();
}