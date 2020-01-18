package Models;

public class SanitaryNook extends ComplexModel{

    public SanitaryNook(double startX, double sizeX, double startY, double sizeY, Door door, Camera camera, LightBulb lightBulb, Window window){
        super(startX, sizeX, startY, sizeY, 5000, 0.5);
    }
}
