package cars;

import car_detail.*;
import enums.CarType;
import enums.Country;
import enums.GearboxType;
import java.math.BigDecimal;

public abstract class Trucks extends Car {
    private int loadCapacity = 3_000; // в килограммах

    public Trucks(String color, int maximumSpeed, GearboxType gearboxType, boolean stateOfMotion,
                  Wheel wheel, FuelTank fuelTank, Engine engine, Electrics electrics, Headlights headlights,
                  BigDecimal price, int loadCapacity, Country country, CarType carType) {

        super(color, maximumSpeed, gearboxType, stateOfMotion, wheel,
                fuelTank, engine, electrics, headlights, price, country, carType);

        this.loadCapacity = loadCapacity;
    }

    public int getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(int loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    @Override
    public String toString() {
        return "Trucks";
    }

}
