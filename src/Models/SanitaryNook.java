package Models;

import Management.SchemeGenerator;

public class SanitaryNook extends ComplexModel {

    public static final double price = 5000;
    public static final double priority = 0.5;

    public SanitaryNook(double startX, double startY) {
        super(startX, SchemeGenerator.conditions.sizeOfSanitaryNook, startY, SchemeGenerator.conditions.sizeOfSanitaryNook);
    }

}
