package Models;

public class Door extends Model {

    private static final int price = 100;
    private static final double priority = 0.33;

    public Door(double startX, double sizeX, double startY, double sizeY) {
        super(startX, sizeX, startY, sizeY);
    }

    public static double getPriority() {
        return priority;
    }

    public static int getPrice() {
        return price;
    }

}
