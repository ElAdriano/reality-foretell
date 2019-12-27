package Models;

public class Window {
    public final double coordinateStartX;
    public final double coordinateEndX;

    public final double coordinateStartY;
    public final double coordinateEndY;

    public final double price;
    public final double priority = 0.25;

    public Window(double startX, double endX, double startY, double endY, double price){
        coordinateStartX = startX;
        coordinateEndX = endX;
        coordinateStartY = startY;
        coordinateEndY = endY;
        this.price = price;
    }
}
