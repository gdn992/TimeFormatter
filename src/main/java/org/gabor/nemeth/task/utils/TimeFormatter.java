package org.gabor.nemeth.task.utils;

import java.util.ArrayList;

public class TimeFormatter {
    private enum TimeUnit {
        SECOND(1),
        MINUTE(SECOND.getSeconds() * 60),
        HOUR(MINUTE.getSeconds() * 60),
        DAY(HOUR.getSeconds() * 24),
        YEAR(DAY.getSeconds() * 365);

        private final int seconds;

        TimeUnit(int second) {
            this.seconds = second;
        }

        public int getSeconds() {
            return this.seconds;
        }
    }

    private static String joinParts(ArrayList<String> parts) {
        if (parts.size() == 1) {
            return parts.getFirst();
        }

        String lastPart = parts.removeLast();

        return String.join(", ", parts).concat(" and " + lastPart);
    }

    private static String pluralize(long count, String name) {
        return count == 1 ? count + " " + name : count + " " + name + "s";
    }

    public static String formatDuration(long seconds) {
        if (seconds < 0) {
            throw new IllegalArgumentException("The parameter cannot be negative");
        }
        if (seconds == 0) {
            return "now";
        }


        long years = seconds / (long) TimeUnit.YEAR.seconds;
        seconds %= TimeUnit.YEAR.seconds;
        long days = seconds / (long) TimeUnit.DAY.seconds;
        seconds %= TimeUnit.DAY.seconds;
        long hours = seconds / (long) TimeUnit.HOUR.seconds;
        seconds %= TimeUnit.HOUR.seconds;
        long minutes = seconds / (long) TimeUnit.MINUTE.seconds;
        seconds %= TimeUnit.MINUTE.seconds;

        ArrayList<String> parts = new ArrayList<>();
        if (0 < years) {
            parts.add(pluralize(years, "year"));
        }
        if (0 < days) {
            parts.add(pluralize(days, "day"));
        }
        if (0 < hours) {
            parts.add(pluralize(hours, "hour"));
        }
        if (0 < minutes) {
            parts.add(pluralize(minutes, "minute"));
        }
        if (0 < seconds) {
            parts.add(pluralize((int) seconds, "second"));
        }

        return joinParts(parts);
    }
}
