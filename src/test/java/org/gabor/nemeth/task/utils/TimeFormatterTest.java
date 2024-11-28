package org.gabor.nemeth.task.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TimeFormatterTest {
    final int minute = 60;
    final int hour = 60 * minute;
    final int day = 24 * hour;
    final int year = 365 * day;

    @Test
    void formatDuration_negativeNumberShouldThrownException() {
        assertThrows(IllegalArgumentException.class, () -> TimeFormatter.formatDuration(-1), "Negative number should throw an IllegalArgumentException");
    }

    @Test
    void formatDuration_simpleDateTypeChecksWithPlural() {
        // 2 years = 126144000 sec
        Assertions.assertEquals("2 years", TimeFormatter.formatDuration(2 * year), "Input 2 should return \"2 years\", plural");
        // 1 year = 63072000 sec
        Assertions.assertEquals("1 year", TimeFormatter.formatDuration(year), "Input 1 should return \"1 year\"");
        // 2 days = 172800 sec
        Assertions.assertEquals("2 days", TimeFormatter.formatDuration(2 * day), "Input 2 should return \"2 days\", plural");
        // 1 day = 86400 sec
        Assertions.assertEquals("1 day", TimeFormatter.formatDuration(day), "Input 1 should return \"1 day\"");
        // 2 hours = 7200 sec
        Assertions.assertEquals("2 hours", TimeFormatter.formatDuration(2 * hour), "Input 2 should return \"2 hours\", plural");
        // 1 hour = 3600 sec
        Assertions.assertEquals("1 hour", TimeFormatter.formatDuration(hour), "Input 1 should return \"1 hour\"");
        // 2 minutes = 120 sec
        Assertions.assertEquals("2 minutes", TimeFormatter.formatDuration(2 * minute), "Input 2 should return \"2 minutes\", plural");
        // 1 minute = 60 sec
        Assertions.assertEquals("1 minute", TimeFormatter.formatDuration(minute), "Input 1 should return \"1 minute\"");
        Assertions.assertEquals("2 seconds", TimeFormatter.formatDuration(2), "Input 2 should return \"2 seconds\", plural");
        Assertions.assertEquals("1 second", TimeFormatter.formatDuration(1), "Input 1 should return \"1 second\"");
    }

    @Test
    void formatDuration_zeroShouldReturnNow() {
        Assertions.assertEquals("now", TimeFormatter.formatDuration(0), "When the input is 0 it should return \"now\"");
    }

    @Test
    void formatDuration_complexDateShouldContainEndBeforeTheLastPart() {
        Assertions.assertEquals("2 years, 3 days, 4 hours, 5 minutes and 6 seconds", TimeFormatter.formatDuration(2 * year + 3 * day + 4 * hour + 5 * minute + 6));
        Assertions.assertEquals("2 years, 4 hours and 5 minutes", TimeFormatter.formatDuration(2 * year + 4 * hour + 5 * minute));
        Assertions.assertEquals("4 hours and 5 minutes", TimeFormatter.formatDuration(4 * hour + 5 * minute));
    }

}