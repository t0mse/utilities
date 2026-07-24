# utilities
[![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)](https://www.oracle.com/java/)
[![Gradle](https://img.shields.io/badge/Gradle-9.2.0-02303A?logo=gradle)](https://gradle.org/)
[![Version](https://jitpack.io/v/t0mse/utilities.svg)](https://jitpack.io/#t0mse/utilities)
[![License](https://img.shields.io/badge/License-MIT-green)](LICENSE)
## Introduction
This library contains some helpful utilities for asynchronous tasks, char/number randomization and date/time formatting.
## Implementation
### Gradle
```kotlin
repositories {
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.github.t0mse:utilities:1.1.2")
}
```
### Maven
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.t0mse</groupId>
    <artifactId>utilities</artifactId>
    <version>1.1.2</version>
</dependency>
```
## Functionalities
### 1. Countdown
A simple asynchronous countdown that counts down the specified ticks in the specified time unit and automatically stops 
when hitting (works with `Scheduler`)
<br>
<br>Located at: [de.tomse.misc.utilities.async.Countdown](src/main/java/de/tomse/misc/utilities/async/Countdown.java)
#### Code Samples

<details>
<summary>Click to view</summary>

#### Setup Countdown
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
```
#### Start/resume countdown
```java
countdown.start();
```
#### Pause running countdown
```java
countdown.pause();
```
#### Stop and reset running countdown
```java
countdown.stop();
```

</details>

---
### 2. Scheduler
A builder and holder class to set up, store and manage ScheduledExecutorServices
<br>
<br>Located at: [de.tomse.misc.utilities.async.Scheduler](src/main/java/de/tomse/misc/utilities/async/Scheduler.java)
#### Code Samples

<details>
<summary>Click to view</summary>

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

</details>

---
### 3. Date Formatter
A builder class designed to format a given millisecond timestamp to a readable string with the formatted date timestamp 
in a specific date format.
<br>
<br>Located at: [de.tomse.misc.utilities.formatter.DateFormatter](src/main/java/de/tomse/misc/utilities/formatter/date/DateFormatter.java)
#### Date Format Presets

<details>
<summary>Click to view</summary>

| Enum Type                     | Date Format                     | Example Output                   |
|-------------------------------|---------------------------------|----------------------------------|
| `TIME`                        | `HH:mm:ss`                      | `11:05:09`                       |
| `YEAR`                        | `yyyy`                          | `2026`                           |
| `DATE`                        | `yyyy-MM-dd`                    | `2026-07-23`                     | 
| `DATE_YEAR_MONTH`             | `yyyy-MM`                       | `2026-07`                        |
| `EUROPEAN_DATE`               | `dd.MM.yyyy`                    | `23.07.2026`                     |
| `DATE_TIME`                   | `MMM dd yyyy HH:mm:ss`          | `Jul 23 2026 11:05:09`           |
| `EUROPEAN_DATETIME`           | `dd.MM.yyyy HH:mm:ss`           | `23.07.2026 11:05:09`            |
| `LONG_DATE`                   | `MMMM dd yyyy`                  | `July 23 2026`                   |
| `FULL_DATE`                   | `EEEE, MMMM dd yyyy`            | `Thursday, July 23 2026`         |
| `FULL_MONTH_YEAR`             | `MMMM yyyy`                     | `July 2026`                      |
| `LOG`                         | `yyyy-MM-dd HH:mm:ss`           | `2026-07-23 11:05:09`            |
| `LOG_WITH_MILLIS`             | `yyyy-MM-dd HH:mm:ss.SSS`       | `2026-07-23 11:05:09.123`        |
| `RFC_1123`                    | `EEE, dd MMM yyyy HH:mm:ss zzz` | `Thu, 23 Jul 2026 11:05:09 CEST` |
| `UNIX_SECONDS`                | `yyyy-MM-dd'T'HH:mm:ss`         | `2026-07-23T14:05:09`            |
| `ISO_8601`                    | `yyyy-MM-dd'T'HH:mm:ss'Z'`      | `2026-07-23T14:05:09Z`           |
| `ISO_8601_WITH_MILLIS`        | `yyyy-MM-dd'T'HH:mm:ss.SSS'Z'`  | `2026-07-23T14:05:09.123Z`       |
| `ISO_8601_WITH_OFFSET`        | `yyyy-MM-dd'T'HH:mm:ssXXX`      | `2026-07-23T14:05:09+02:00`      |
| `ISO_8601_WITH_MILLIS_OFFSET` | `yyyy-MM-dd'T'HH:mm:ss.SSSXXX`  | `2026-07-23T14:05:09.123+02:00`  |

</details>

#### Code samples

<details>
<summary>Click to view</summary>

#### Current date with fallback format preset
```java
DateFormatter dateFormatter = new DateFormatter();
```
#### Date format presets
```java
// Current date
DateFormatter dateFormatter = new DateFormatter(DateFormatPreset.RFC);
// Specified timestamp
DateFormatter dateFormatter = new DateFormatter(DateFormatPreset.RFC, 133742067L);
```
#### Custom date formats
```java
// Current date
DateFormatter dateFormatter = new DateFormatter("MMM dd yyyy, HH:mm:ss");
// Specified timestamp
DateFormatter dateFormatter = new DateFormatter("MMM dd yyyy, HH:mm:ss", 133742067L);
```
#### Custom time zone
```java
dateFormatter.timeZone("Europe/Berlin");
```
#### Custom date format consumption/modification
```java
dateFormatter.consumeDateFormat(dateFormat -> {
    // Modify dateFormat
});
```
#### Format specified date parameters
```java
String formattedDate = dateFormatter.format();
```

</details>

---
### 4. Time Formatter
A builder class designed to format a given millisecond time value to a readable string with the formatted timestamp.
<br>
<br>Located at: [de.tomse.misc.utilities.formatter.TimeFormatter](src/main/java/de/tomse/misc/utilities/formatter/time/TimeFormatter.java)
#### Code samples

<details>
<summary>Click to view</summary>

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

</details>

---
### 5. Char Randomizer
A builder class designed to generate/draw a random string outcome of a specified `CharSet` and length.
<br>
<br>Located at: [de.tomse.misc.utilities.randomizer.ChatRandomizer](src/main/java/de/tomse/misc/utilities/randomizer/impl/CharRandomizer.java)
#### CharSets

<details>
<summary>Click to view</summary>

| Enum Type      | Characters                                                       | Description              |
|----------------|------------------------------------------------------------------|--------------------------|
| `ALPHA`        | `ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz`           | Only letters             |
| `ALPHANUMERIC` | `ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789` | Only letters and numbers |
| `NUMERIC`      | `0123456789`                                                     | Only numbers             |

</details>

#### Code samples

<details>
<summary>Click to view</summary>

```java
String randomAlpha = new CharRandomizer(CharRandomizer.CharSet.ALPHA, 16).draw();

String randomAlphanumeric = new CharRandomizer(CharRandomizer.CharSet.ALPHANUMERIC, 32).draw();

String numeric = new CharRandomizer(CharRandomizer.CharSet.NUMERIC, 8).draw();
```

</details>

---
### 6. Number Randomizer
A builder class designed to generate/draw a random number between a specified range.
<br>
<br>Located
at: [de.tomse.misc.utilities.randomizer.NumberRandomizer](src/main/java/de/tomse/misc/utilities/randomizer/impl/NumberRandomizer.java)
#### Code samples

<details>
<summary>Click to view</summary>

```java
int smallRandomNumber = new NumberRandomizer(1,10).draw();

int mediumRandomNumber = new NumberRandomizer(1_000,10_000).draw();

int largeRandomNumber = new NumberRandomizer(100_000,1_000_000).draw();
```

</details>