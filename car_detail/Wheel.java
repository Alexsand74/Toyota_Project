package car_detail;

public class Wheel {
    public boolean conditionPunctured; // проколотый = true
    public int diameter;

    public Wheel(boolean conditionPunctured, int diameter) {
        this.conditionPunctured = conditionPunctured;
        this.diameter = diameter;
    }

    @Override
    public String toString() {
        return "Wheel{" +
                "conditionPunctured=" + conditionPunctured +
                ", diameter=" + diameter +
                '}';
    }
}
