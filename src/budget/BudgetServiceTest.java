package budget;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BudgetServiceTest {

    @Test
    public void no_budget() {
        BudgetService budgetService = new BudgetService();

        double actual = budgetService.queryTotal(LocalDate.of(2018, 8, 29),
                LocalDate.of(2018, 8, 29));

        assertEquals(0, actual);
    }
}
