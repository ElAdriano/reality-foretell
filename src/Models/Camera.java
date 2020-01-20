package Models;

public class Camera extends Model {

    private static final int price = 1000;
    private static final double priority = 0.75;

    public Camera(double x, double y) {
        super(x, 1, y, 1);
    }

    public static double getPriority() {
        return priority;
    }

    public static int getPrice() {
        return price;
    }

}
