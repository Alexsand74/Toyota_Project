package report;

import cars.Car;
import enums.CarTypePrice;

import java.math.BigDecimal;

public class Report {
    private final CarTypePrice carTypeModel;
    private final BigDecimal carPrice; //цена авто
    private final BigDecimal income; //доход
    private final BigDecimal costPrice; //себестоимость

    public Report(Car car) {
        this.carTypeModel = car.getCarType();
        this.carPrice = car.getCarType().getPriceInBigDecimal();
        this.income = car.getPriceCents();
        this.costPrice = car.getPriceCents().subtract(car.getCarType().getPriceInBigDecimal());
    }

    public CarTypePrice getCarTypeModel() {
        return carTypeModel;
    }

    public BigDecimal getCarPrice() {
        return carPrice;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }
}
