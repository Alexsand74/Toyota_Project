package factory;

import car_detail.*;
import enums.Country;
import enums.CarBrandWheelDiameter;

public class Factory {
    private Country country;

    public Factory(Country country) {
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }

    public Engine createEngine() {
        return new Engine(true);
    }

    public Electrics createElectrics() {
        return new Electrics(true);
    }

    public FuelTank createFuelTank() {
        return new FuelTank();
    }

    public Headlights cteateHeadlights() {
        return new Headlights(false);
    }

    public Wheel createWheel(CarBrandWheelDiameter carBrand) {
        return new Wheel(false, carBrand.getDiameter());
    }

}
