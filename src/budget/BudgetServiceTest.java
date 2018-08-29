package budget;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static java.time.LocalDate.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BudgetServiceTest {

    BudgetRepo stubBudgetRepo = mock(BudgetRepo.class);
    BudgetService budgetService = new BudgetService(stubBudgetRepo);

    @Test
    public void no_budget() {
        totalAmountShouldBe(0,
                of(2018, 8, 29),
                of(2018, 8, 29));
    }

    @Test
    public void period_inside_budget_month() {
        givenBudgets(new Budget("2018-08", 31));

        totalAmountShouldBe(1,
                of(2018, 8, 29),
                of(2018, 8, 29));
    }

    private void givenBudgets(Budget... budgets) {
        when(stubBudgetRepo.getAll()).thenReturn(Arrays.asList(budgets));
    }

    private void totalAmountShouldBe(double expected, LocalDate start, LocalDate end) {
        assertEquals(expected, budgetService.queryTotal(start, end));
    }
}
