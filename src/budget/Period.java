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

    public double getOverlappingDayCount(Budget budget) {
        if (start.isAfter(budget.getLastDay())) {
            return 0;
        }

        if (end.isBefore(budget.getFirstDay())) {
            return 0;
        }

        LocalDate overlappingStart = start.isAfter(budget.getFirstDay()) ? start : budget.getFirstDay();
        return DAYS.between(overlappingStart, end) + 1;
    }
}
