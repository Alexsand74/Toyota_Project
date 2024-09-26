import cars.*;
import enums.CarTypePrice;
import enums.Country;
import factory.Conveyor;
import factory.Factory;
import exception.CountyFactoryNotEqualException;
import garage.Garage;

public class RunnerStage3 {
    public static void main(String[] args) {
        try {
            Factory factoryJapan = new Factory(Country.JAPAN);
            Factory factoryKorea = new Factory(Country.KOREA);
            Conveyor conveyorJapan = new Conveyor(Country.JAPAN, factoryJapan);
            Conveyor conveyorKorea = new Conveyor(Country.KOREA, factoryKorea);

            Camry camry1 = conveyorJapan.createCamry("red", 10_000);
            System.out.println(camry1);
            Camry camry2 = conveyorJapan.createCamry("red", 10_000);
            System.out.println(camry2);
            Camry camry3 = conveyorJapan.createCamry("red", 10_000);
            System.out.println(camry3);
            Solara solara1 = conveyorKorea.createSolara("white", 12_000);
            System.out.println(solara1);
            Solara solara2 = conveyorKorea.createSolara("white", 12_000);
            System.out.println(solara2);
            Hiance hiance = conveyorJapan.createHiance("blue", 20_000);
            System.out.println(hiance);
            Dyna  dyna = conveyorKorea.createDyna("green", 21_000);
            System.out.println(dyna);
            Dyna  dyna1 = conveyorKorea.createDyna("green", 21_000);
            System.out.println(dyna1);
            Dyna  dyna2 = conveyorKorea.createDyna("green", 21_000);
            System.out.println(dyna2);
            Dyna  dyna3 = conveyorKorea.createDyna("green", 21_000);
            System.out.println(dyna3);

            Garage garage = new Garage();

            System.out.println("________________________________________");
            showNumberCar(garage);
            System.out.println("++++++++++++++++++++++++++++++++++++++++");

            garage.addCarage(camry1);
            camry1.connectMusicUsb();
            garage.addCarage(camry2);
            garage.addCarage(camry2);
            garage.addCarage(camry3);
            garage.addCarage(solara1);
            garage.addCarage(solara2);
            garage.addCarage(hiance);
            garage.addCarage(hiance);
            garage.addCarage(dyna);
            garage.addCarage(dyna1);
            garage.addCarage(dyna2);
            garage.addCarage(dyna3);

            System.out.println("________________________________________");
            showNumberCar(garage);
            System.out.println("++++++++++++++++++++++++++++++++++++++++");

            garage.getCarCarage(camry1);
            garage.getCarCarage(camry2);
            garage.getCarCarage(solara1);
            garage.getCarCarage(dyna);
            garage.getCarCarage(dyna1);
            garage.getCarCarage(dyna2);

            System.out.println("________________________________________");
            showNumberCar(garage);
            System.out.println("++++++++++++++++++++++++++++++++++++++++");

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
