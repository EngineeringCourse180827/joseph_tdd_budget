package budget;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Period {
    private final LocalDate start;
    private final LocalDate end;

    public Period(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    public long getDayCount() {
        return DAYS.between(start, end) + 1;
    }

    public LocalDate getEnd() {
        return end;
    }

    public LocalDate getStart() {
        return start;
    }

    public double getOverlappingDayCount(Period another) {
        if (start.isAfter(another.getEnd())) {
            return 0;
        }

        if (end.isBefore(another.getStart())) {
            return 0;
        }

        LocalDate overlappingStart = start.isAfter(another.getStart()) ? start : another.getStart();
        LocalDate overlappingEnd = end.isBefore(another.getEnd()) ? end : another.getEnd();
        return DAYS.between(overlappingStart, overlappingEnd) + 1;
    }
}
