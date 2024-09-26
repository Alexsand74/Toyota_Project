package cars;

import car_detail.*;
import enums.Country;
import enums.GearboxType;
import java.math.BigDecimal;
import static enums.CarTypePrice.SOLARA;

public class Solara extends Car {
    private boolean roof;  // крыша поднята  = true

    public Solara(String color, int maximumSpeed, GearboxType gearboxType, boolean stateOfMotion,
                  Wheel wheel, FuelTank fuelTank, Engine engine, Electrics electrics,
                  Headlights headlights, BigDecimal price, boolean roof, Country country) {

        super(color, maximumSpeed, gearboxType, stateOfMotion, wheel,
                fuelTank, engine, electrics, headlights, price, country, SOLARA);

        this.roof = roof;
    }

    public boolean isRoof() {
        return roof;
    }

    public void onRoof() {
        this.roof = true;
    }

    public void offRoof() {
        this.roof = false;
    }

    public void coolDrink() {
        System.out.println("напиток охлажден");
    }

    @Override
    public String toString() {
        return "Solara";
    }

}
