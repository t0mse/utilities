# utilities
![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)
![Gradle](https://img.shields.io/badge/Gradle-9.2.0-02303A?logo=gradle)
![Version](https://img.shields.io/badge/Version-1.0.0-blue)
![License](https://img.shields.io/badge/License-MIT-green)
## Introduction
This library contains some helpful utilities for asynchronous tasks, char/number randomization and date/time formatting.
## Implementation
```kotlin
repositories {
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.github.t0mse:utilities:1.0.0")
}
```
## Functionalities
### 1. Countdown
A simple asynchronous countdown that counts down the specified ticks in the specified time unit and stops when hitting 0. (Also works with `Scheduler`)
<br>
<br>Located at: [de.tomse.misc.utilities.async.Countdown](https://github.com/t0mse/utilities/blob/master/src/main/java/de/tomse/misc/utilities/async/Countdown.java)
<br>Example code:
```java
Countdown countdown = new Countdown(5, TimeUnit.SECONDS) {
    @Override
    public void onTick(int tick) {
        // Countdown ticking
    }

    @Override
    public void onLastTick() {
        // Countdown finished, stops and resets automatically
    }
};

countdown.start(); // Start the countdown

countdown.stop(); // Manually stop and reset the countdown
```
### 2. Scheduler
A builder and holder class to set up, store and manage ScheduledExecutorServices
<br>
<br>Located at: [de.tomse.misc.utilities.async.Scheduler](https://github.com/t0mse/utilities/blob/master/src/main/java/de/tomse/misc/utilities/async/Scheduler.java)
<br>Example code:
#### Run task asynchronously
```java
Scheduler scheduler = Scheduler.runTask(() -> {
    // Do something here
});
```
#### Run task later asynchronously
```java
Scheduler scheduler = Scheduler.runTaskLater(() -> {
    // Do something here
}, 5, TimeUnit.SECONDS);
```
#### Run task asynchronously repeatedly
```java
Scheduler scheduler = Scheduler.repeatingTask(() -> {
    // Do something here
}, 5, TimeUnit.SECONDS);
```
#### Run task asynchronously repeatedly with start delay
```java
Scheduler scheduler = Scheduler.delayedRepeatingTask(() -> {
    // Do something here
}, 10, 5, TimeUnit.SECONDS);
```
#### Shutdown a running scheduler task
```java
scheduler.shutdown();
```
### 3. Date Formatter
A builder class designed to format a given millisecond timestamp to a readable string with the formatted date timestamp in a specific date format.
<br>
<br>Located at: [de.tomse.misc.utilities.formatter.DateFormatter](https://github.com/t0mse/utilities/blob/master/src/main/java/de/tomse/misc/utilities/formatter/date/DateFormatter.java)
#### Available Date Formats
`DEFAULT`: Default Date Format | Example: `Jul 22 2026, 12:34:56 PM CEST`
<br>`RFC`: RFC Date Format | Example: `2026-07-22T12:34:56Z`
<br>
<br>Example code:
```java
// Default date format, Current time
new DateFormatter(DateFormatter.DateSet.DEFAULT).format();
// Default date format, Custom time
new DateFormatter(DateFormatter.DateSet.DEFAULT, 1784719985092L).format();

// RFC date format, Current time
new DateFormatter(DateFormatter.DateSet.RFC).format();
// RFC date format, Custom time
new DateFormatter(DateFormatter.DateSet.RFC, 1784719985092L).format();
```
### 4. Time Formatter
A builder class designed to format a given millisecond time value to a readable string with the formatted timestamp.
<br>
<br>Located at: [de.tomse.misc.utilities.formatter.TimeFormatter](https://github.com/t0mse/utilities/blob/master/src/main/java/de/tomse/misc/utilities/formatter/time/TimeFormatter.java)
<br>Example code:
```java
long timeMillis = 1784719985092L + TimeUnit.DAYS.toMillis(7);

new TimeFormatter(timeMillis)
        .displayTimeUnits(TimeUnit.DAYS, TimeUnit.HOURS, TimeUnit.MINUTES)
        .onlyDisplayBiggest(true) /* Only display the biggest formatted unit */
        .subtractCurrentMillis(true) /* Subtract current time millis from specified time millis*/
        .formatSeparator(" - ") /* Separator between formatted units */
        .fullUnitNames(false) /* Example: 'd' <-> 'days' */
        .hideNullValues(true) /* Hide null values of formatted units */;
        .format(); // Format result
```
### 5. Char Randomizer
A builder class designed to generate/draw a random string outcome of a specified `CharSet` and length.
<br>
<br>Located at: [de.tomse.misc.utilities.randomizer.ChatRandomizer](https://github.com/t0mse/utilities/blob/master/src/main/java/de/tomse/misc/utilities/randomizer/impl/CharRandomizer.java)
#### Available CharSets
`ALPHA`: Only letters
<br>`ALPHANUMERIC`: Only letters and numbers
<br>`NUMERIC`: Only numbers
<br>
<br>Example code:
```java
new CharRandomizer(CharRandomizer.CharSet.ALPHA, 16).draw();

new CharRandomizer(CharRandomizer.CharSet.ALPHANUMERIC, 32).draw();

new CharRandomizer(CharRandomizer.CharSet.NUMERIC, 8).draw();
```
### 6. Number Randomizer
A builder class designed to generate/draw a random number between a specified range.
<br>
<br>Located at: [de.tomse.misc.utilities.randomizer.NumberRandomizer](https://github.com/t0mse/utilities/blob/master/src/main/java/de/tomse/misc/utilities/randomizer/impl/NumberRandomizer.java)
<br>Example code:
```java
new NumberRandomizer(1, 10).draw();

new NumberRandomizer(1_000, 10_000).draw();

new NumberRandomizer(100_000, 1_000_000).draw();
```