package budget;

import java.time.LocalDate;
import java.time.YearMonth;

public class Budget {
    private final String yearMonth;
    private final int amount;

    public Budget(String yearMonth, int amount) {
        this.yearMonth = yearMonth;
        this.amount = amount;
    }

    public LocalDate getFirstDay() {
        return YearMonth.parse(yearMonth).atDay(1);
    }

    public LocalDate getLastDay() {
        return YearMonth.parse(yearMonth).atEndOfMonth();
    }

    public Period getPeriod() {
        return new Period(getFirstDay(), getLastDay());
    }

    public int getAmount() {
        return amount;
    }
}
