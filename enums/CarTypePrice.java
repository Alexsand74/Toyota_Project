package enums;

import java.math.BigDecimal;

public enum CarTypePrice {
    CAMRY(10000), SOLARA(12000), HIANCE(15000), DYNA(22000);

    private BigDecimal price;

    CarTypePrice(int price) {
        this.price = BigDecimal.valueOf(price);
    }

    public double getPrice() {
        return price.doubleValue();
    }

    public BigDecimal getPriceInBigDecimal() {
        return price;
    }

}
