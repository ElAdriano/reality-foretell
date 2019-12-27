package Models;

public class MonitoringRoom {
    public final double basePointCoordinateX;
    public final double basePointCoordinateY;
    public final double width;
    public final double height;

    public final Door door;
    public final Camera camera;
    public final LightBulb lightBulb;
    public final Window window;

    public final double priority = 0.5;


    public MonitoringRoom(double width, double  height, double basePointX, double basePointY, Door door, Camera camera, LightBulb lightBulb, Window window){
        this.width = width;
        this.height = height;
        basePointCoordinateX = basePointX;
        basePointCoordinateY = basePointY;

        this.door = door;
        this.camera = camera;
        this.lightBulb = lightBulb;
        this.window = window;
    }
}
