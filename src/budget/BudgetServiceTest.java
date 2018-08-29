package budget;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static java.time.LocalDate.of;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BudgetServiceTest {

    BudgetService budgetService = new BudgetService();

    @Test
    public void no_budget() {
        totalAmountShouldBe(0,
                of(2018, 8, 29),
                of(2018, 8, 29));
    }

    private void totalAmountShouldBe(double expected, LocalDate start, LocalDate end) {
        assertEquals(expected, budgetService.queryTotal(start, end));
    }
}
