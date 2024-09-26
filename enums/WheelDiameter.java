package enums;

public enum WheelDiameter {
//    диаметр (camry - 17, solara - 16, hiance - 20, dyna - 20).
    CAMRY(17), SOLARA(16), TRUCK(20);

    private int diameter;

    WheelDiameter(int diameter) {
        this.diameter = diameter;
    }

    public int getDiameter() {
        return diameter;
    }
}
