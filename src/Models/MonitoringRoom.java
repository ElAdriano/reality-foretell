package Models;

public class MonitoringRoom extends ComplexModel{

    public MonitoringRoom(double startX, double sizeX, double startY, double sizeY, Door door, Camera camera, LightBulb lightBulb, Window window){
        super(startX, sizeX, startY, sizeY, door, camera, lightBulb, window, 10000, 10, 0.5);
    }
}
