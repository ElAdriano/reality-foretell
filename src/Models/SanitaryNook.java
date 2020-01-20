package Models;

import Management.SchemeGenerator;

public class SanitaryNook extends ComplexModel {

    public static final int price = 5000;
    public static final double priority = 0.5;

    public SanitaryNook(double startX, double startY) {
        super(startX, SchemeGenerator.conditions.sizeOfSanitaryNook, startY, SchemeGenerator.conditions.sizeOfSanitaryNook, price, priority);
    }

    public static double getPriority() {
        return priority;
    }

    public static int getPrice() {
        return price;
    }

}
