package Models;

public class LightBulb extends Model {

    public static final int price = 10;
    public static final double priority = 0.33;

    public LightBulb(double x, double y) {
        super(x, 1, y, 1, price, priority);
    }

}
