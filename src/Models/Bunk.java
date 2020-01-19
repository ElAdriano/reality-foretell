package Models;

public class Bunk extends Model {

    public static final double price = 100;
    public static final double priority = 0.33;

    public Bunk(double startX, double startY) {
        super(startX, 4, startY, 2);
    }

}
