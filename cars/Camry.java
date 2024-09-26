package cars;

import car_detail.*;
import enums.Country;
import enums.GearboxType;
import java.math.BigDecimal;
import static enums.CarTypePrice.CAMRY;

public class Camry extends Car {
//    Для camry легковых авто характерно наличие круиз контроля, и возможности включить или выключить его.
    private boolean cruiseControl = true;

    public Camry(String color, int maximumSpeed, GearboxType gearboxType, boolean stateOfMotion,
                 Wheel wheel, FuelTank fuelTank, Engine engine, Electrics electrics,
                 Headlights headlights, BigDecimal price, boolean cruiseControl, Country country) {

        super(color, maximumSpeed, gearboxType, stateOfMotion, wheel, fuelTank, engine, electrics,
                headlights, price, country, CAMRY);

        this.cruiseControl = cruiseControl;
    }

    public boolean isCruiseControl() {
        return cruiseControl;
    }

    public void onCruiseControl() {
        this.cruiseControl = true;
    }

    public void offCruiseControl() {
        this.cruiseControl = false;
    }

    public void connectMusicUsb() {
        System.out.println("музыка включена");
    }

    @Override
    public String toString() {
        return "Camry";
    }
}
