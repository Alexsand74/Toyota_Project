package personal;

import cars.Car;

import java.math.BigDecimal;

public class Cashier {
    private BigDecimal income = BigDecimal.ZERO;

    public void addIncome(Car car) {
        income = income.add(car.getPriceCents());
    }

    public BigDecimal getIncomeTotal() {
        return income;
    }
}
