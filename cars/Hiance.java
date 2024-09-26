package cars;

import car_detail.*;
import enums.Country;
import enums.GearboxType;
import enums.CarBrandWheelDiameter;
import java.math.BigDecimal;
import static enums.CarTypePrice.HIANCE;

public class Hiance extends Trucks {
    private Wheel spare = new Wheel(false, CarBrandWheelDiameter.TRUCK.getDiameter());

    public Hiance(String color, int maximumSpeed, GearboxType gearboxType, boolean stateOfMotion, Wheel wheel,
                   FuelTank fuelTank, Engine engine, Electrics electrics, Headlights headlights,
                   BigDecimal price, int loadCapacity, Country country) {
        super(color, maximumSpeed, gearboxType, stateOfMotion, wheel,
                fuelTank, engine, electrics, headlights, price, loadCapacity, country, HIANCE);
    }

    public Wheel getSpare() {
        return spare;
    }

    public void setSpare(Wheel spare) {
        this.spare = spare;
    }

    @Override
    public String toString() {
        return "Hiance";
    }

}
