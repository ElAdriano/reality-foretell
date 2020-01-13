package Models;

public class ComplexModel extends Model {

    public final Door door;
    public final Camera camera;
    public final LightBulb lightBulb;
    public final Window window;

    public final double priority = 0.5;

    public ComplexModel(double startX, double sizeX, double startY, double sizeY, Door door, Camera camera, LightBulb lightBulb, Window window, double price, int imageID, double priority){
        super(startX, sizeX, startY, sizeY, price, imageID, priority);
        this.door = door;
        this.camera = camera;
        this.lightBulb = lightBulb;
        this.window = window;
    }
}
