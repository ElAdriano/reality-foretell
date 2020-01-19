package Models;

import Management.SchemeGenerator;

public class SanitaryNook extends ComplexModel{

    public SanitaryNook(double startX, double startY){
        super(startX, SchemeGenerator.conditions.sizeOfSanitaryNook, startY, SchemeGenerator.conditions.sizeOfSanitaryNook, 5000, 0.5);

    }
}
