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

    @Test
    public void no_overlap_period_before_budget_first_day() {
        givenBudgets(new Budget("2018-08", 31));

        totalAmountShouldBe(0,
                of(2018, 7, 30),
                of(2018, 7, 30));
    }

    @Test
    public void no_overlap_period_after_budget_last_day() {
        givenBudgets(new Budget("2018-08", 31));

        totalAmountShouldBe(0,
                of(2018, 9, 1),
                of(2018, 9, 1));
    }

    @Test
    public void period_overlap_budget_first_day() {
        givenBudgets(new Budget("2018-08", 31));

        totalAmountShouldBe(1,
                of(2018, 7, 31),
                of(2018, 8, 1));
    }

    @Test
    public void period_overlap_budget_last_day() {
        givenBudgets(new Budget("2018-08", 31));

        totalAmountShouldBe(1,
                of(2018, 8, 31),
                of(2018, 9, 1));
    }

    @Test
    public void daily_amount_extend() {
        givenBudgets(new Budget("2018-08", 310));

        totalAmountShouldBe(20,
                of(2018, 8, 29),
                of(2018, 8, 30));
    }

    @Test
    public void total_amount_is_double() {
        givenBudgets(new Budget("2018-08", 3));

        totalAmountShouldBe(0.1,
                of(2018, 8, 29),
                of(2018, 8, 29));
    }

    @Test
    public void multiply_budgets() {
        givenBudgets(
                new Budget("2018-08", 31),
                new Budget("2018-09", 300));

        totalAmountShouldBe(21,
                of(2018, 8, 31),
                of(2018, 9, 2));
    }

    private void givenBudgets(Budget... budgets) {
        when(stubBudgetRepo.getAll()).thenReturn(Arrays.asList(budgets));
    }

    private void totalAmountShouldBe(double expected, LocalDate start, LocalDate end) {
        assertEquals(expected, budgetService.queryTotal(start, end), 0.01);
    }
}
