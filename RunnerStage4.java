import buyer.Buyer;
import cars.*;
import enums.CarType;
import enums.Country;
import exception.CountyFactoryNotEqualException;
import exception.IllegalArgumentCarException;
import factory.Conveyor;
import factory.Factory;
import garage.Garage;
import personal.Cashier;
import personal.Manager;

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
        try {
            Factory factoryJapan = new Factory(Country.JAPAN);
            Conveyor conveyorJapan = new Conveyor(Country.JAPAN, factoryJapan);
            Camry camry = conveyorJapan.createCamry("black", CarType.CAMRY.getPrice());
            Solara solara = conveyorJapan.createSolara("white", CarType.SOLARA.getPrice());
            Hiance hiance = conveyorJapan.createHiance("black", CarType.HIANCE.getPrice());
            Dyna dyna = conveyorJapan.createDyna("black", CarType.DYNA.getPrice());

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

            Buyer buyer = new Buyer("Nikolai", 15000);
            Manager manager = new Manager("Evgeniy", garage, conveyorJapan);
            Car car = manager.sellCar(buyer);
            System.out.printf("Покупатель берет машину марки %s \n", car);

            Cashier cashier = new Cashier();
            cashier.addIncome(car);
            System.out.println(cashier.getIncomeTotal());

        } catch (CountyFactoryNotEqualException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void showNumberCar(Garage garage) {
        garage.numberCarsRemaining();
        garage.numberCarRemaining(CarType.CAMRY);
        garage.numberCarRemaining(CarType.SOLARA);
        garage.numberCarRemaining(CarType.DYNA);
        garage.numberCarRemaining(CarType.HIANCE);
    }
}
