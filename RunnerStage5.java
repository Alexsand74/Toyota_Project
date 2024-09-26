import buyer.Buyer;
import cars.*;
import enums.CarTypePrice;
import enums.Country;
import exception.CountyFactoryNotEqualException;
import exception.NotEnoughMoneyException;
import factory.Conveyor;
import factory.Factory;
import garage.Garage;
import personal.Cashier;
import personal.Manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RunnerStage5 {
    public static void main(String[] args) {
//        5) Этап 5
//        У нас уже есть все для старта продаж, но как мы поймем сколько машин мы продали, какую прибыль получили?
//        Нам необходима статистика продаж!
//        Необходимо:
//        1. Дать менеджеру имя.
//        2. Создать класс Report, в котором будет отображаться вся статистика менеджера: имя менеджера,
//        проданные машины.
//        3. Во время продажи, менеджер должен обновить свой report - добавить проданную машину в список.
//        4. Сделать справочник по моделям машин: camry - себестоимость 5000, solara - 8000,
//        hiance - 10000, dyna - 12000
//        5. У менеджера должен появиться метод: сгенерируй отчет,
//        в котором он выводит информацию в файл txt формата:
//        Имя менеджера
//        Модель №1 - стоимость продажи - себестоимость
//        Модель №2 - стоимость продажи - себестоимость
//....
//        Итог: доходы - (сумма всех продаж), расходы (сумма всех себестоимостей), прибыль - (доходы - расходы)
        try {
            Factory factoryJapan = new Factory(Country.JAPAN);
            Conveyor conveyorJapan = new Conveyor(Country.JAPAN, factoryJapan);

            Camry camry = conveyorJapan.createCamry("black", CarTypePrice.CAMRY.getPrice());
            Solara solara = conveyorJapan.createSolara("white", CarTypePrice.SOLARA.getPrice());
            Hiance hiance = conveyorJapan.createHiance("black", CarTypePrice.HIANCE.getPrice());
            Dyna dyna = conveyorJapan.createDyna("black", CarTypePrice.DYNA.getPrice());

            Garage garage = new Garage();
            System.out.println("________________________________________");
            showNumberCar(garage);
            System.out.println("++++++++++++++++++++++++++++++++++++++++");

            garage.addCarage(camry);
            garage.addCarage(solara);
            garage.addCarage(hiance);
            garage.addCarage(dyna);

            System.out.println("________________________________________");
            showNumberCar(garage);
            System.out.println("++++++++++++++++++++++++++++++++++++++++");

            Manager manager = new Manager("Evgeniy", garage, conveyorJapan);
            Cashier cashier = new Cashier();

            List listBuyers = List.of(10000, 12000, 15000, 22000, 11000, 13200, 8000, 30000);
            ArrayList<Integer> amountMoneyBuyers = new ArrayList<>(listBuyers);
            int counter = 1;
            for (Integer amountMoneyBuyer : amountMoneyBuyers) {
                String currentNameBuyer = "Покупатель" + counter;
                double currentHasSumMoney = (double) amountMoneyBuyer;
                Buyer currentBuyer = new Buyer(currentNameBuyer, currentHasSumMoney);
                System.out.printf("Покупатель %d ", counter);
                try {
                    Car currentCar = manager.sellCar(currentBuyer);
                    manager.getAndWriteReport(currentCar);
                    System.out.printf("Покупатель %d берет машину марки %s \n", counter, currentCar);
                    System.out.println("Машина стоит " + currentCar.getPriceCents().doubleValue());
                    cashier.addIncome(currentCar);
                } catch (NotEnoughMoneyException exc) {
                    System.out.println(exc.getMessage());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Тотальная сумма прибыли = " + cashier.getIncomeTotal());
                counter++;
            }
        } catch (CountyFactoryNotEqualException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void showNumberCar(Garage garage) {
        garage.numberCarsRemaining();
        garage.numberCarRemaining(CarTypePrice.CAMRY);
        garage.numberCarRemaining(CarTypePrice.SOLARA);
        garage.numberCarRemaining(CarTypePrice.DYNA);
        garage.numberCarRemaining(CarTypePrice.HIANCE);
    }
}
