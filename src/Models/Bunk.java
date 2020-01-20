package Models;

public class Bunk extends Model {

    private static final int price = 100;
    private static final double priority = 0.33;

    public Bunk(double startX, double startY) {
        super(startX, 4, startY, 2);
    }

    public static double getPriority() {
        return priority;
    }

    public static int getPrice() {
        return price;
    }

}
