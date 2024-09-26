package cars;

import car_detail.*;
import enums.CarType;
import enums.Country;
import enums.GearboxType;
import exception.StartCarException;

import java.math.BigDecimal;

public abstract class Car {
    private static final int ZERO_FUEL = 0;
    protected String color;
    protected int maximumSpeed;
    protected GearboxType gearboxType;      // automatic, manual, robot
    protected boolean stateOfMotion = false; // driving = true, stands = false
    protected Wheel leftFront;
    protected Wheel rightFront;
    protected Wheel leftRear;
    protected Wheel rightRear;
    protected FuelTank fuelTank;
    protected Engine engine;
    protected Electrics electrics;
    protected Headlights headlights;
    protected BigDecimal priceCents;
    protected Country country;
    protected CarType carType;

    public Car(String color, int maximumSpeed, GearboxType gearboxType, boolean stateOfMotion,
               Wheel wheel, FuelTank fuelTank, Engine engine, Electrics electrics,
               Headlights headlights, BigDecimal priceCents, Country country, CarType carType) {

        this.color = color;
        this.maximumSpeed = maximumSpeed;
        this.gearboxType = gearboxType;
        this.stateOfMotion = stateOfMotion;
        this.leftFront = wheel;
        this.rightFront = wheel;
        this.leftRear = wheel;
        this.rightRear = wheel;
        this.fuelTank = fuelTank;
        this.engine = engine;
        this.electrics = electrics;
        this.headlights = headlights;
        this.priceCents = priceCents;
        this.country = country;
        this.carType = carType;
    }

    public void setFuelTank(int fuelTank) {
        try {
            this.fuelTank.setFuelQuantity(fuelTank);
        } catch (NullPointerException e) {
            throw new StartCarException(" Автомобиль не создан, куда заливать топливо !");
        }
    }

    public void startMoving() {

//        Начать движение возможно только при:
//        наличии всех колес и они не проколоты,
//        непустом бензобаке,
//        работоспособным электрике и двигателю.
//        Если что-то из этого не выполняется, то выкидывается ошибка StartCarException,
//        в сообщении которой содержится причина невозможности движения.

        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append(!leftFront.conditionPunctured ? "" : "левое_переднее_колесо ");
        errorMessage.append(!rightFront.conditionPunctured ? "" :  "правое_переднее_колесо ");
        errorMessage.append(!leftRear.conditionPunctured ? "" :  "левое_заднее_колесо ");
        errorMessage.append(!rightRear.conditionPunctured ? "" :  "правое_заднее_колесо ");
        errorMessage.append(electrics.isWorking() ? "" : "неисправна_электрика ");
        errorMessage.append(engine.isWorking() ? "" : "не_работоспособен_мотор ");
        errorMessage.append(fuelTank.getFuelQuantity() > ZERO_FUEL ? "" : "нет_бензина ");
        if (!errorMessage.isEmpty()) {
            errorMessage = new StringBuilder(errorMessage.toString().trim().replace(" ", ", "));
            String message = "Движение объекта, от класса " + this
                             + ", не возможно, из-за, неисправности : " + errorMessage + ".";
            throw new StartCarException(message);
        }
        System.out.println("Объект от класса " + this + " движется ");
    }

    public void stopMoving() {
        System.out.println("Объект от класса " + this + " стоит ");
    }

    public void turnOnHeadlights() {
        System.out.println("Объект от класса " + this + " включил свет ");
    }

    public CarType getCarType() {
        return carType;
    }

    public BigDecimal getPriceCents() {
        return priceCents;
    }
}
