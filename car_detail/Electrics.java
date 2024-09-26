package car_detail;

public class Electrics extends Detail {
    public Electrics(boolean working) {
        this.isWorked = working;
    }

    public boolean isWorking() {
        return super.isWorked;
    }

}
