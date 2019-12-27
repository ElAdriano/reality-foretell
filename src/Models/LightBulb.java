package Models;

public class LightBulb {
    public final double coordinateX;
    public final double coordinateY;

    public final double price;
    public final double priority = 0.33;

    public LightBulb(double x, double y, double price){
        coordinateX = x;
        coordinateY = y;
        this.price = price;
    }
}
