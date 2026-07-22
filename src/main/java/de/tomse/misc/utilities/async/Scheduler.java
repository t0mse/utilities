package de.tomse.misc.utilities.async;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class Scheduler {
    private final ScheduledExecutorService scheduledExecutorService;
    private final ScheduledFuture<?> scheduledFuture;

    public static Scheduler runTask(Runnable runnable) {
        final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        return new Scheduler(scheduledExecutorService,
                scheduledExecutorService.schedule(runnable, 0L, TimeUnit.SECONDS));
    }

    public static Scheduler runTask(Runnable runnable, ScheduledExecutorService scheduledExecutorService) {
        return new Scheduler(scheduledExecutorService,
                scheduledExecutorService.schedule(runnable, 0L, TimeUnit.SECONDS));
    }

    public static Scheduler runTaskLater(Runnable runnable, int delay, TimeUnit timeUnit) {
        final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        return new Scheduler(scheduledExecutorService, scheduledExecutorService
                .schedule(runnable, delay, timeUnit));
    }

    public static Scheduler runTaskLater(Runnable runnable,
                                         ScheduledExecutorService scheduledExecutorService,
                                         int delay,
                                         TimeUnit timeUnit) {
        return new Scheduler(scheduledExecutorService, scheduledExecutorService
                .schedule(runnable, delay, timeUnit));
    }

    public static Scheduler repeatingTask(Runnable runnable, int period, TimeUnit timeUnit) {
        return delayedRepeatingTask(runnable, 0, period, timeUnit);
    }

    public static Scheduler repeatingTask(Runnable runnable,
                                          ScheduledExecutorService scheduledExecutorService,
                                          int period,
                                          TimeUnit timeUnit) {
        return delayedRepeatingTask(runnable, scheduledExecutorService, 0, period, timeUnit);
    }

    public static Scheduler delayedRepeatingTask(Runnable runnable, int delay, int period, TimeUnit timeUnit) {
        final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        return new Scheduler(scheduledExecutorService, scheduledExecutorService
                .scheduleAtFixedRate(runnable, delay, period, timeUnit));
    }

    public static Scheduler delayedRepeatingTask(Runnable runnable,
                                                 ScheduledExecutorService scheduledExecutorService,
                                                 int delay,
                                                 int period,
                                                 TimeUnit timeUnit) {
        return new Scheduler(scheduledExecutorService, scheduledExecutorService
                .scheduleAtFixedRate(runnable, delay, period, timeUnit));
    }

    public void shutdown() {
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
        }
    }
}
