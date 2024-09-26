package car_detail;

public class Engine extends Detail {

    public Engine(boolean working) {
        this.isWorked = working;
    }

    public boolean isWorking() {
        return super.isWorked;
    }
}
