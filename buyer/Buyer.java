package buyer;

import java.math.BigDecimal;

public class Buyer {
    private String name;
    private BigDecimal hasSumMoney;

    public Buyer(String name, double hasSumMoney) {
        this.name = name;
        this.hasSumMoney = BigDecimal.valueOf(hasSumMoney);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getHasSumMoneyBigDecimal() {
        return hasSumMoney;
    }

    public double getHasSumMoney() {
        return hasSumMoney.doubleValue();
    }
}
