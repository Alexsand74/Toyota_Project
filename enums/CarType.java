package enums;

import java.math.BigDecimal;

public enum CarType {
    CAMRY(10000), SOLARA(12000), HIANCE(15000), DYNA(22000);

    private BigDecimal price;

    CarType(int price) {
        this.price = BigDecimal.valueOf(price);
    }

    public double getPrice() {
        return price.doubleValue();
    }

    public BigDecimal getPriceInBigDecimal() {
        return price;
    }

}
