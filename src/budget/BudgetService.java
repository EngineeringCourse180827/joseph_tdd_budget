package budget;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class BudgetService {
    private final BudgetRepo repo;

    public BudgetService(BudgetRepo repo) {
        this.repo = repo;
    }

    public double queryTotal(LocalDate start, LocalDate end) {
        if (!repo.getAll().isEmpty()) {
            return DAYS.between(start, end) + 1;
        }

        return 0;
    }
}
