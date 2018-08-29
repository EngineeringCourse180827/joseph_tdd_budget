package budget;

import java.time.LocalDate;
import java.time.YearMonth;

public class Budget {
    private final String yearMonth;

    public Budget(String yearMonth, int amount) {
        this.yearMonth = yearMonth;
    }

    public LocalDate getFirstDay() {
        return YearMonth.parse(yearMonth).atDay(1);
    }
}
