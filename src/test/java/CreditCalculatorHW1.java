import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditCalculatorHW1 {

    @Test
    public void creditCalculator() {

        double creditTotalAmountFromUser = 13600.00;
        double creditTotalAmount = creditTotalAmount(4000);

        Assertions.assertEquals(creditTotalAmountFromUser, creditTotalAmount, 0.009, "This is not true Amount!");
    }

    private double creditTotalAmount(double creditAmount) {
        return creditAmount * 2 + creditAmount * 0.8 + creditAmount * 0.6;

    }
}
