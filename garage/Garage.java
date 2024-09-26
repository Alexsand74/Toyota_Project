package garage;

import cars.*;
import enums.CarTypePrice;
import exception.IllegalArgumentCarException;

import java.util.*;

public class Garage {
    private Map<CarTypePrice, HashSet<Car>> garageMap = new HashMap<>();
    private static final int FULL_GARAGE = 1000;
    private static int carsCounter = 0;

    public void addCarage(Car car) throws IllegalArgumentCarException {
        if (car == null) {
            throw new NullPointerException("Car = null !");
        }
        if (carsCounter > FULL_GARAGE) {
            System.out.println("Склад - гараж, заполнен полностью !");
            return;
        }
        HashSet<Car> carSet = garageMap.get(car.getCarType());
        if (carSet == null) {
            carSet = new HashSet<>();
        }
        if (carSet.add(car)) {
            garageMap.put(car.getCarType(), carSet);
            carsCounter++;
        }
    }

    public void getCarCarage(Car car) throws IllegalArgumentCarException {
        if (car == null) {
            return;
        }
        if (checkGarageCars()) {
            throw new IllegalArgumentCarException();
        }
        HashSet<Car> carSet = garageMap.get(car.getCarType());
        if (checkBrandCars(carSet)) {
            throw new IllegalArgumentCarException();
        }
        if (carSet != null) {
            if (carSet.remove(car)) {
                carsCounter--;
            }
        }
    }

    public List<Car> getAllCars() {
        return garageMap.values().stream().flatMap(Collection::stream).toList();
    }

    public int getCarsCounter() {
        return carsCounter;
    }

    public void numberCarsRemaining() {
        System.out.println("Всего на складе осталось - " + getCarsCounter() + " автомобилей.");
    }

    public int numberCarRemaining(CarTypePrice carTypePrice) {
        HashSet<Car> carSet = garageMap.get(carTypePrice);
        int result = 0;
        if (carSet != null) {
            result = carSet.size();
        }
        System.out.printf("Автомобилей марки %s на складе осталось - % d  \n", carTypePrice, result);
        return result;
    }

    private boolean checkGarageCars() {
        if (carsCounter <= 0) {
            System.out.println("Склад - гараж, пуст, автомобилей нет !");
            return true;
        }
        return false;
    }

    private boolean checkBrandCars(HashSet set) {
        if (set.isEmpty()) {
            System.out.println("На складе - гараже, данной марки автомобилей нет !");
            return  true;
        }
        return false;
    }
}
