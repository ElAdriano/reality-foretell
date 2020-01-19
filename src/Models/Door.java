package Models;

public class Door extends Model {

    public static final double price = 100;
    public static final double priority = 0.33;

    public Door(double startX, double startY) {
        super(startX, 1, startY, 1);
    }

}
