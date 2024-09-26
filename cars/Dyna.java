package cars;

import car_detail.*;
import enums.Country;
import enums.GearboxType;
import java.math.BigDecimal;
import static enums.CarTypePrice.DYNA;

public class Dyna extends Trucks {

    public Dyna(String color, int maximumSpeed, GearboxType gearboxType, boolean stateOfMotion, Wheel wheel,
                FuelTank fuelTank, Engine engine, Electrics electrics, Headlights headlights, BigDecimal price,
                int loadCapacity, Country country) {
        super(color, maximumSpeed, gearboxType, stateOfMotion, wheel,
                fuelTank, engine, electrics, headlights, price, loadCapacity, country, DYNA);
    }

    public void chargeCell() {
        System.out.println("телефон заряжен");
    }

    @Override
    public String toString() {
        return "Dyna";
    }
}
