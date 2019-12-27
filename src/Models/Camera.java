package Models;

public class Camera {
    public final double coordinateX;
    public final double coordinateY;
    public final double price;
    public final double priority = 0.75;

    public Camera(double x, double y, double price){
        coordinateX = x;
        coordinateY = y;
        this.price = price;
    }
}
