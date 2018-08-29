package budget;

import java.time.LocalDate;

public class BudgetService {
    private final BudgetRepo repo;

    public BudgetService(BudgetRepo repo) {
        this.repo = repo;
    }

    public double queryTotal(LocalDate start, LocalDate end) {
        Period period = new Period(start, end);

        if (!repo.getAll().isEmpty()) {
            return period.getDayCount();
        }

        return 0;
    }

}
