package Models;

public class Door extends Model{

    public Door(double startX, double endX, double startY, double endY, double price){
        coordinateStartX = startX;
        coordinateEndX = endX;
        coordinateStartY = startY;
        coordinateEndY = endY;
        this.price = price;
    }
}
