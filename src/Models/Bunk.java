package Models;

public class Bunk extends Model {

    public static final int price = 100;
    public static final double priority = 0.33;

    public Bunk(double startX, double sizeX, double startY, double sizeY) {
        super(startX, sizeX, startY, sizeY, price, priority);
    }

}
