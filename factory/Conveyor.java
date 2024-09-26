package factory;

import cars.*;
import enums.CarTypePrice;
import enums.Country;
import enums.GearboxType;
import enums.CarBrandWheelDiameter;
import exception.CountyFactoryNotEqualException;
import java.math.BigDecimal;

import static java.lang.String.format;

public class Conveyor {
    private final String message = "Конвеер %s и завод деталей %s, находятся в разных странах !";
    private final Country country;
    private final Factory factory;

    public Conveyor(Country country, Factory factory) throws CountyFactoryNotEqualException {
        if (!country.equals(factory.getCountry())) {
            String error = format(message, country, factory.getCountry());
            throw new CountyFactoryNotEqualException(error);
        }
        this.country = country;
        this.factory = factory;
    }

    public Camry createCamry(String color, double price) {
        return new Camry(color, 160, GearboxType.AUTOMATIC, false,
                    factory.createWheel(CarBrandWheelDiameter.CAMRY),
                    factory.createFuelTank(), factory.createEngine(),
                    factory.createElectrics(), factory.cteateHeadlights(),
                    BigDecimal.valueOf(price), true, country);
    }

    public Solara createSolara(String color, double price) {
        return new Solara(color, 160, GearboxType.ROBOT, false,
                factory.createWheel(CarBrandWheelDiameter.SOLARA),
                factory.createFuelTank(), factory.createEngine(),
                factory.createElectrics(), factory.cteateHeadlights(),
                BigDecimal.valueOf(price), true, country);
    }

    public Dyna createDyna(String color, double price) {
        return new Dyna(color, 140, GearboxType.MANUAL, false,
                factory.createWheel(CarBrandWheelDiameter.TRUCK),
                factory.createFuelTank(), factory.createEngine(),
                factory.createElectrics(), factory.cteateHeadlights(),
                BigDecimal.valueOf(price), 3_500, country);
    }

    public Hiance createHiance(String color, double price) {
        return new Hiance(color, 140, GearboxType.MANUAL, false,
                factory.createWheel(CarBrandWheelDiameter.TRUCK),
                factory.createFuelTank(), factory.createEngine(),
                factory.createElectrics(), factory.cteateHeadlights(),
                BigDecimal.valueOf(price), 4_000, country);
    }

    public Car createCar(String color, double price, CarTypePrice type) {
        if (type == CarTypePrice.HIANCE) {
            return createHiance(color, price);
        } else if (type == CarTypePrice.CAMRY) {
            return createCamry(color,price);
        } else if (type == CarTypePrice.SOLARA) {
            return createSolara(color, price);
        }
        return createDyna(color, price);
    }

}
