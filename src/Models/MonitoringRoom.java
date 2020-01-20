package Models;

public class MonitoringRoom extends ComplexModel {

    public static final int price = 10000;
    public static final double priority = 0.5;

    public MonitoringRoom(double startX, double sizeX, double startY, double sizeY) {
        super(startX, sizeX, startY, sizeY, price, priority);
    }
    
}
