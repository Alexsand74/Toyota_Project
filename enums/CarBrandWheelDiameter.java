package enums;

public enum CarBrandWheelDiameter {
    CAMRY(17), SOLARA(16), TRUCK(20);

    private final int diameter;

    CarBrandWheelDiameter(int diameter) {
        this.diameter = diameter;
    }

    public int getDiameter() {
        return diameter;
    }
}
