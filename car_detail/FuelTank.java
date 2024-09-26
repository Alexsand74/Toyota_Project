package car_detail;

public class FuelTank extends Detail {
    int fuelQuantity;

    public FuelTank() {
        this.fuelQuantity = 0;
    }

    public int getFuelQuantity() {
        return fuelQuantity;
    }

    public void setFuelQuantity(int fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }
}
