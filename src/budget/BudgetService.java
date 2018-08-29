package budget;

import java.time.LocalDate;
import java.util.List;

public class BudgetService {
    private final BudgetRepo repo;

    public BudgetService(BudgetRepo repo) {
        this.repo = repo;
    }

    public double queryTotal(LocalDate start, LocalDate end) {
        Period period = new Period(start, end);

        List<Budget> budgets = repo.getAll();
        if (budgets.isEmpty()) {
            return 0;
        }

        Budget budget = budgets.get(0);
        return budget.getAmount() / budget.getPeriod().getDayCount() * period.getOverlappingDayCount(budget.getPeriod());
    }

}
