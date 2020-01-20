package Models;

public class MonitoringRoom extends ComplexModel {

    private static final int price = 10000;
    private static final double priority = 0.5;

    public MonitoringRoom(double startX, double sizeX, double startY, double sizeY) {
        super(startX, sizeX, startY, sizeY);
    }

    public static double getPriority() {
        return priority;
    }

    public static int getPrice() {
        return price;
    }
    
}
