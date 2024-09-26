package personal;

import buyer.Buyer;
import exception.NotEnoughMoneyException;
import cars.Car;
import enums.CarType;
import factory.Conveyor;
import garage.Garage;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Manager {

    static final private int COMPARISON_WITH_ZERO = 0;
    private String name;
    private Garage garage;
    private Conveyor conveyor;

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
        CarType[] carType = CarType.values();
        Car car = null;
        BigDecimal typeOfCarMinimumPrice = Arrays.stream(carType)
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
        } else {
            throw new NotEnoughMoneyException();
        }

        if (car == null) {
            car = creationCarIfNotInGarage(conveyor, buyer);
        }
        return car;
    }

    private static Car creationCarIfNotInGarage(Conveyor conveyor, Buyer buyer) {
        List<CarType> carTypeList = Arrays.asList(CarType.values());

        CarType carTypeOne =  carTypeList.stream()
                .sorted((a1, a2) -> a1.getPriceInBigDecimal().compareTo(a2.getPriceInBigDecimal()))
                .filter((type) -> type.getPriceInBigDecimal()
                        .add(type.getPriceInBigDecimal()
                                 .divide(BigDecimal.TEN))
                        .compareTo(buyer.getHasSumMoneyBigDecimal()) <= COMPARISON_WITH_ZERO)
                .reduce((e1, e2) -> e2)
                .orElseThrow(() -> new NotEnoughMoneyException());

        return  conveyor.createCar("black", carTypeOne.getPrice(), carTypeOne);
    }
}
