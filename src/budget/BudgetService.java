package budget;

import java.time.LocalDate;

public class BudgetService {
    private final BudgetRepo repo;

    public BudgetService(BudgetRepo repo) {
        this.repo = repo;
    }

    public double queryTotal(LocalDate start, LocalDate end) {
        Period period = new Period(start, end);

        return repo.getAll().stream()
                .mapToDouble(budget -> budget.getOverlappingAmount(period))
                .sum();
    }

}
