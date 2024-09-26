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

import java.util.ArrayList;
import java.util.List;

public class RunnerStage4 {
    public static void main(String[] args) {
//        4) Этап 4
//        У нас уже есть машины, которые могут храниться на складах!
//                Теперь нам необходимо создать дилерскую сеть, которая будет продавать машины! (в Runner)
//        Для этого нам потребуется:
//        Создать склад, производство и сборку в Японии.
//        1. Создать стандартные цены для машин: camry 10000, solara 12000, hiance 15000, dyna 22000.
//        2. Создать 4 модели машины, со стандартными ценами: camry black, solara white, hiance black, dyna black.
//        3. Занести их в склад.
//        4. Создать покупателя, у которого мы задаем сумму денег, на которую он может купить машину,
//        а так же его имя
//        5. Создать Менеджера: он ожидает нового покупателя, и за его сумму предлагает купить машину.
//        У менеджера есть метод - продать машину клиенту: возвращается самая дорогая машина,
//        которую может купить покупатель со своей суммой денег из имеющихся. Если машин на складе нет, то
//        идет запрос на сборку и производство по ценам выше (на 10% выше от стандартной цены),
//        и машина заносится на склад.
//        Если клиенту не хватает денег, то никакую машину он не получает.
//        6. Создать кассира: кассир принимает заказ - берет машину,
//        и заносит ее цену в общий для всех кассиров счет доходов.
//
//        В первый день придет 8 покупателей.
//        у первого будет 10000 - уйдет с камри, которая в наличии на складе
//        у второго будет 12000 - уйдет с соларой, которая в наличии на складе
//        у третьего будет 15000 - уйдет с хайянс, которая в наличии на складе
//        у четвертого будет 22000 - уйдет с дюной, которая в наличии на складе
//        у пятого будет 11000 - уйдет с камри, созданной по запросу на производство (цена камри + 10%)
//        у шестого будет 13200 - уйдет с соларой, созданной по запросу на производство (цена солары + 10%)
//        у седьмого будет 8000 - уйдет ни с чем
//        у восьмого будет 30000 - уйдет с дюной, созданной по запросу на производство (цена дюны + 10%)
//
//        Процесс продажи:
//        Создается менеджер
//        Создается покупатель №1
//        Менеджер принимает покупателя №1 и возвращает машину
//        Кассир принимает машину для продажи и заносит в счет проданную машину
//         ...
//        Создается покупатель №2 и начинается снова продажа машин
//        В конце выводится сумма проданных машин
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

            List list = List.of(10000, 12000, 15000, 22000, 11000, 13200, 8000, 30000);
            ArrayList<Integer> amountMoneyBuyers = new ArrayList<>(list);
            int counter = 1;
            for (Integer amountMoneyBuyer : amountMoneyBuyers) {
                String currentNameBuyer = "Покупатель" + counter;
                double currentHasSumMoney = (double) amountMoneyBuyer;
                Buyer currentBuyer = new Buyer(currentNameBuyer, currentHasSumMoney);
                System.out.printf("Покупатель %d ", counter);
                try {
                    Car currentCar = manager.sellCar(currentBuyer);
                    System.out.printf("Покупатель %d берет машину марки %s \n", counter, currentCar);
                    System.out.println("Машина стоит " + currentCar.getPriceCents().doubleValue());
                    cashier.addIncome(currentCar);
                } catch (NotEnoughMoneyException exc) {
                    System.out.println(exc.getMessage());
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
