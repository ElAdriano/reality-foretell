package Models;

public class PrisonWard extends ComplexModel{

    public PrisonWard(double startX, double sizeX, double startY, double sizeY, Door door, Camera camera, LightBulb lightBulb, Window window){
        super(startX, sizeX, startY, sizeY, 100000, 1);
    }
}
