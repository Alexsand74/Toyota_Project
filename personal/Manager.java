package personal;

import buyer.Buyer;
import exception.NotEnoughMoneyException;
import cars.Car;
import enums.CarTypePrice;
import factory.Conveyor;
import garage.Garage;
import report.Report;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Manager {
    static final private int COMPARISON_WITH_ZERO = 0;

    private BigDecimal incomeTotal = BigDecimal.ZERO;
    private BigDecimal costTotal = BigDecimal.ZERO;
    private String name;
    private Garage garage;
    private Conveyor conveyor;
    private List<Report> reportsList = new ArrayList<>();

    public Manager(String name, Garage garage, Conveyor conveyor) {
        this.name = name;
        this.garage = garage;
        this.conveyor = conveyor;
    }

    public String getName() {
        return name;
    }

//        У менеджера есть метод - продать машину клиенту: возвращается самая дорогая машина,
//        которую может купить покупатель со своей суммой денег из имеющихся. Если машин на складе нет,
//        то идет запрос на сборку и производство по ценам выше (на 10% выше от стандартной цены),
//        и машина заносится на склад.
//        Если клиенту не хватает денег, то никакую машину он не получает.
    public Car sellCar(Buyer buyer)  {
        CarTypePrice[] carTypePrice = CarTypePrice.values();
        Car car = null;
        BigDecimal typeOfCarMinimumPrice = Arrays.stream(carTypePrice)
                               .min((a1, a2) -> a1.getPriceInBigDecimal()
                                                  .compareTo(a2.getPriceInBigDecimal()))
                               .get().getPriceInBigDecimal();

        if (buyer.getHasSumMoneyBigDecimal().compareTo(typeOfCarMinimumPrice) >= COMPARISON_WITH_ZERO) {

            car = garage.getAllCars().stream()
                    .sorted((a1, a2) -> a1.getPriceCents().compareTo(a2.getPriceCents()))
                    .filter((auto) -> auto.getPriceCents()
                                          .compareTo(buyer.getHasSumMoneyBigDecimal()) <= COMPARISON_WITH_ZERO)
                    .reduce((e1, e2) -> e2)
                    .orElse(null);
            garage.getCarCarage(car);
        } else {
            throw new NotEnoughMoneyException();
        }

        if (car == null) {
            car = creationCarIfNotInGarage(conveyor, buyer);
        }
        return car;
    }

    private static Car creationCarIfNotInGarage(Conveyor conveyor, Buyer buyer) {
        List<CarTypePrice> carTypePriceList = Arrays.asList(CarTypePrice.values());

        CarTypePrice carTypePriceOne =  carTypePriceList.stream()
                .sorted((a1, a2) -> a1.getPriceInBigDecimal().compareTo(a2.getPriceInBigDecimal()))
                .filter((type) -> type.getPriceInBigDecimal()
                        .add(type.getPriceInBigDecimal()
                                 .divide(BigDecimal.TEN))
                        .compareTo(buyer.getHasSumMoneyBigDecimal()) <= COMPARISON_WITH_ZERO)
                .reduce((e1, e2) -> e2)
                .orElseThrow(() -> new NotEnoughMoneyException());

        BigDecimal price = carTypePriceOne.getPriceInBigDecimal().add(carTypePriceOne
                                                                 .getPriceInBigDecimal()
                                                                 .divide(BigDecimal.TEN));

        return  conveyor.createCar("black",
                                   price.doubleValue(),
                                         carTypePriceOne);
    }


    public void getAndWriteReport(Car car) throws IOException {
        String reportName = name.toLowerCase() + "-log.txt";
        Report report = new Report(car);
        try (FileWriter fileWriter = new FileWriter(reportName, true)) {
            fileWriter.write(name + '\n');
            costTotal.add(report.getCostPrice());
            String data = " Модель - "
                        + report.getCarTypeModel() + " "
                        + report.getCarPrice() + " "
                        + report.getIncome() + " "
                        + report.getCostPrice() + "\n";
            fileWriter.write(data);

            incomeTotal = incomeTotal.add(report.getIncome());
            costTotal = costTotal.add(report.getCarPrice());
            data = String.format(" Итого: доходы - %.2f, расходы - %.2f, прибыль - %.2f %n",
                    incomeTotal.doubleValue(),
                    costTotal.doubleValue(),
                    incomeTotal.subtract(costTotal).doubleValue());
            fileWriter.write(data);
        }
    }
}
